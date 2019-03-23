package com.hoperun.drp.oamg.saledateup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.saledateup.model.SaledateUpChildModel;
import com.hoperun.drp.oamg.saledateup.model.SaledateUpModel;
import com.hoperun.drp.oamg.saledateup.service.SaledateUpService;
import com.hoperun.sys.model.UserBean;
import com.runqian.report4.model.expression.function.Case;

public class SaledateUpServiceImpl extends BaseService implements SaledateUpService {

	
	 /**
	 * @param SALE_DATE_UP_ID
	 * @param obj
	 * @param userBean
	 */
	public void txEdit(String SALE_DATE_UP_ID, SaledateUpModel model,
			List<SaledateUpChildModel>childList,
			UserBean userBean) {
        //同一终端、同一个年月，不能多次上报
		String TERM_ID = model.getTERM_ID();
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("WAREA_ID", model.getWAREA_ID());  
		params.put("WAREA_NO", model.getWAREA_NO());  
		params.put("WAREA_NAME", model.getWAREA_NAME());  
		params.put("AREA_ID", model.getAREA_ID());  
		params.put("AREA_NO", model.getAREA_NO());  
		params.put("AREA_NAME", model.getAREA_NAME());  
		params.put("CHANN_ID", model.getCHANN_ID());  
		params.put("CHANN_NO", model.getCHANN_NO());  
		params.put("CHANN_NAME", model.getCHANN_NAME()); 
		params.put("TERM_ID", model.getTERM_ID());  
		params.put("TERM_NO", model.getTERM_NO());  
		params.put("TERM_NAME", model.getTERM_NAME()); 
		params.put("YEAR", model.getYEAR());  
		params.put("MONTH", model.getMONTH());  
		params.put("TOTAL_NUM", model.getTOTAL_NUM());  
		params.put("TOTAL_AMOUNT", model.getTOTAL_AMOUNT());  
		params.put("REMARK", model.getREMARK()); 
		params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
		int res = this.queryForInt("SaledateUp.getRepeatNum", params);
		if(res>0){
			String msg = "终端:"+model.getTERM_NAME()+"["+model.getTERM_NO()+"]在"+model.getYEAR()+"年"+model.getMONTH()+"月已经有数据";
			throw new ServiceException(msg);
		}
		if (StringUtils.isEmpty(SALE_DATE_UP_ID)) {
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//0
			SALE_DATE_UP_ID = StringUtil.uuid32len();
			params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
			params.put("SALE_DATE_UP_NO", LogicUtil.getBmgz("DRP_SALE_DATE_UP_NO"));  
			params.put("CREATOR", userBean.getXTYHID());//制单人ID
			params.put("CRE_NAME", userBean.getXM());//制单人名称
			params.put("CRE_TIME", DateUtil.now());//制单时间
			params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
			params.put("ORG_ID", userBean.getJGXXID());//制单机构id
			params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("STATE",BusinessConsts.UNCOMMIT); //未提交
			this.insert("SaledateUp.insert", params);
			
		} else{
			params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
			this.update("SaledateUp.updateById", params);
		}
		
		//子表保存
		if(childList!=null&&!childList.isEmpty()){
			txChildEdit(SALE_DATE_UP_ID,childList);
		}
		
		 
	}

	/**
	 * 分页查询
	 * Description :.
	 * @param params the params
	 * @param pageNo the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("SaledateUp.pageQuery",
				"SaledateUp.pageCount", params, pageNo);
	}

	
	 /**
     * * 根据主表Id查询子表记录
     * @param SALE_DATE_UP_ID the SALE_DATE_UP_ID
     * @return the list
     */
    public List <SaledateUpChildModel> childQuery(String SALE_DATE_UP_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
         //只查询0的记录。1为删除。0为正常
 		 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
         return this.findList("SaledateUp.queryChldByFkId", params);
    }
    
    /**
     * * 根据子表Id批量加载子表信息
     * @return the list
     */
    public List <SaledateUpChildModel> loadChilds(Map <String, String> params){
    	return findList("SaledateUp.loadChldByIds",params);
    }
    
   /**
     * @param SALE_DATE_UP_ID
     * @return
     */
	public Map<String, Object> loadById(String SALE_DATE_UP_ID) {
		return (Map<String, Object>) load("SaledateUp.loadById", SALE_DATE_UP_ID);
	}
	
	 
	/**
	 * 删除数据
	 * @param SALE_DATE_UP_ID 
	 * @return true, if tx delete
	 */
	public boolean txDelete(String SALE_DATE_UP_ID, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
        //删除主表
        update("SaledateUp.delete", params);
		//删除子表 
		return update("SaledateUp.delChldByFkId", params);
	}
	
   
	   /**
     * 子表编辑
     * @param SALE_DATE_UP_ID
     * @param modelList
     * @return
     */
    public boolean txChildEdit(String SALE_DATE_UP_ID, List <SaledateUpChildModel> modelList){
    	
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        Double TOTAL_NUM = 0d;
        Double TOTAL_AMOUNT = 0d;
        Map<String,Object> pMap = new HashMap<String,Object>();
        
        for(SaledateUpChildModel model : modelList){
        	Map<String, Object> params = new HashMap<String, Object>();
        	params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
    		params.put("PRD_TYPE", model.getPRD_TYPE());
    		params.put("TOTAL_NUM", model.getTOTAL_NUM());
    		params.put("TOTAL_AMOUNT", model.getTOTAL_AMOUNT());
    		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    		
    		TOTAL_NUM = TOTAL_NUM+StringUtil.getDouble(model.getTOTAL_NUM());
    		TOTAL_AMOUNT = TOTAL_AMOUNT+StringUtil.getDouble(model.getTOTAL_AMOUNT());
    		
    		if(StringUtil.isEmpty(model.getSALE_DATE_UP_DTL_ID())){
    			params.put("SALE_DATE_UP_DTL_ID", StringUtil.uuid32len());
    			addList.add(params);
    		}else{
    			params.put("SALE_DATE_UP_DTL_ID", model.getSALE_DATE_UP_DTL_ID());
    			updateList.add(params);
    		}
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("SaledateUp.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("SaledateUp.insertChld", addList);
        }
        
        //更新主表的总数量，总金额
        pMap.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
        pMap.put("TOTAL_NUM", TOTAL_NUM);
        pMap.put("TOTAL_AMOUNT", TOTAL_AMOUNT);
        
        this.update("SaledateUp.updateById", pMap);
        return true;
    	
    }
	 /**
     * * 子表的批量删除
     * @param SALE_DATE_UP_DTL_IDS the SALE_DATE_UP_DTL_IDS
     */
    public void txBatch4DeleteChild(String SALE_DATE_UP_DTL_IDS){
    	Map<String, String> params = new HashMap<String, String>();
  	    params.put("SALE_DATE_UP_DTL_IDS", SALE_DATE_UP_DTL_IDS);
  	    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
        update("SaledateUp.deleteChldByIds", params);
    }
    
    /**
	 * @param SALE_DATE_UP_ID
	 * @param userBean
	 */
	public void upSaledataup(String SALE_DATE_UP_ID,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间  
        params.put("STATE", "提交");
		this.update("SaledateUp.updateById", params);
	}
    
    public Map<String,String>loadChannById(String CHANN_ID){
    	Map<String, String> params = new HashMap<String, String>();
  	    params.put("CHANN_ID", CHANN_ID);
    	return (Map<String, String>) this.load("SaledateUp.loadChannById", params);
    }
    
    /**
     * 导出用户渠道分管的终端信息
     * @param CHANNS_CHARG
     * @return
     */
    public List getChargTerminal(Map<String,String> params){
    	return this.findList("SaledateUp.getChargTerminal", params);
    }
    
    /**
     * 导入
     * @param list
     * @param userBean
     */
    public void txImportExcel(List list,UserBean userBean){
    	List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
    	String SESSION_ID=StringUtil.uuid32len();
    	for(int i=0;i<list.size();i++){
			List lists =(List) list.get(i);
			for (int j = 0; j < lists.size(); j++) {
				Map<String,String> map =(Map<String,String>)lists.get(j);
				if(j==0){//验证第一行第一列是否有hoperun
					if(!map.get("SALE_DATE_UP_NO").equals("hoperun")){
						throw new ServiceException("对不起，请使用模版文件进行修改上传!");
					}
					continue;
				}
				if(j==1){//第二行为列名
					continue;
				}
				String MATTRESS_AMOUNT=map.get("MATTRESS_AMOUNT");
				String HAMMOCK_AMOUNT=map.get("HAMMOCK_AMOUNT");
				String OTHER_AMOUNT=map.get("OTHER_AMOUNT");
				//金额全部为空时 跳过该行，进行下一行遍历
				if(StringUtil.isEmpty(MATTRESS_AMOUNT)&&StringUtil.isEmpty(HAMMOCK_AMOUNT)&&StringUtil.isEmpty(OTHER_AMOUNT)){
					continue;
				}
				String checkStr="[0-9]+\\.{0,1}[0-9]{0,2}";
				if(!StringUtil.isEmpty(MATTRESS_AMOUNT)&&!StringUtil.regexCheck(MATTRESS_AMOUNT,checkStr)){
					throw new ServiceException("床垫金额存在非法数字:"+MATTRESS_AMOUNT+"，请检查后重新上传");
				}
				if(!StringUtil.isEmpty(HAMMOCK_AMOUNT)&&!StringUtil.regexCheck(HAMMOCK_AMOUNT,checkStr)){
					throw new ServiceException("软床金额存在非法数字:"+HAMMOCK_AMOUNT+"，请检查后重新上传");
				}
				if(!StringUtil.isEmpty(OTHER_AMOUNT)&&!StringUtil.regexCheck(OTHER_AMOUNT,checkStr)){
					throw new ServiceException("其他金额存在非法数字:"+OTHER_AMOUNT+"，请检查后重新上传");
				}
				for (int a = 0; a < 3; a++) {
					Map<String,String> params = new HashMap<String,String>();
					params.put("SESSION_ID", SESSION_ID);
					params.put("AREA_NO", map.get("AREA_NO"));
					params.put("AREA_NAME", map.get("AREA_NAME"));
					params.put("CHANN_NO_P", map.get("CHANN_NO_P"));
					params.put("CHANN_NAME_P",map.get("CHANN_NAME_P") );
					params.put("TERM_NO", map.get("TERM_NO"));
					params.put("TERM_NAME", map.get("TERM_NAME"));
					params.put("YEAR", map.get("YEAR"));
					params.put("MONTH", getMonth(map.get("MONTH")));
					if(!StringUtil.isEmpty(MATTRESS_AMOUNT)&&a==0){
						params.put("PRD_TYPE", "床垫");
						params.put("TOTAL_AMOUNT", MATTRESS_AMOUNT);
						addList.add(params);
					}else if(!StringUtil.isEmpty(HAMMOCK_AMOUNT)&&a==1){
						params.put("PRD_TYPE", "软床");
						params.put("TOTAL_AMOUNT", HAMMOCK_AMOUNT);
						addList.add(params);
					}else if(!StringUtil.isEmpty(OTHER_AMOUNT)&&a==2){
						params.put("PRD_TYPE", "其他");
						params.put("TOTAL_AMOUNT", OTHER_AMOUNT);
						addList.add(params);
					}
				}
			}
		}
    	if(!addList.isEmpty()){
    		boolean flag=this.batch4Update("SaledateUp.insertTemp", addList);
    		if(flag==false){
         	   throw new ServiceException("插入临时表出错!");
            }
    	}
    	//校验
    	String message=checkInfo(SESSION_ID,userBean);
    	if(!StringUtil.isEmpty(message)){
    		throw new ServiceException(message);
    	}
    	//新增修改
    	operateSaleInfo(userBean,SESSION_ID);
    	//删除临时表
    	this.delete("SaledateUp.delPrdTemp", SESSION_ID);
    }
	/**
	 * 验证导入临时表的信息
	 * @return
	 */
	public String checkInfo(String SESSION_ID,UserBean userBean){
		String message;
		//验证终端，渠道，区域信息是否存在空值
		message=checkInfoNull(SESSION_ID);
		if(!StringUtil.isEmpty(message)){
			return message;
		}
		//验证终端，渠道，区域信息是否匹配
		message=checkMatchingInfo(SESSION_ID,userBean.getCHANNS_CHARG());
		if(!StringUtil.isEmpty(message)){
			return message;
		}
		//验证同一个终端，同一年份月份货品类型存在重复记录
		message=checkDateInfo(SESSION_ID);
		if(!StringUtil.isEmpty(message)){
			return message;
		}
		//验证是否存在不同年月
		message=checkYearInfo(SESSION_ID);
		if(!StringUtil.isEmpty(message)){
			return message;
		}
		return null;
	}
	/**
	 * 验证终端，渠道，区域信息是否存在空值
	 * @param SESSION_ID
	 * @return
	 */
	public String checkInfoNull(String SESSION_ID){
		int count=Integer.parseInt(this.load("SaledateUp.checkInfoNull", SESSION_ID).toString());
		if(count>0){
			return "区域/渠道/终端信息存在空值，请检查后重新上传！";
		}
		return null;
	}
	/**
	 * 验证终端，渠道，区域信息是否匹配
	 * @param SESSION_ID
	 * @return
	 */
	public String checkMatchingInfo(String SESSION_ID,String CHANNS_CHARG){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SESSION_ID", SESSION_ID);
		map.put("CHANNS_CHARG", CHANNS_CHARG);
		map.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		List list=this.findList("SaledateUp.checkMatchingInfo", map);
		StringBuffer buf=new StringBuffer();
		if(!list.isEmpty()&&list.size()>0){
			buf.append("终端名称：");
			for (int i = 0; i < list.size(); i++) {
				buf.append(list.get(i)).append(",");
			}
			String message=buf.toString();
			if (!StringUtil.isEmpty(message)) {
				message = message.substring(0,
						message.length() - 1);
				message=message+" 与渠道信息、区域信息不匹配，请检查后重新上传！";
				return message;
			}
		}
		return null;
	}
	/**
	 * 验证同一个终端，同一年份月份货品类型存在重复记录
	 * @param SESSION_ID
	 * @return
	 */
	public String checkDateInfo(String SESSION_ID){
		List list=this.findList("SaledateUp.checkDateInfo", SESSION_ID);
		StringBuffer buf=new StringBuffer();
		if(!list.isEmpty()&&list.size()>0){
			buf.append("终端名称：");
			for (int i = 0; i < list.size(); i++) {
				buf.append(list.get(i)).append(",");
			}
			String message=buf.toString();
			if (!StringUtil.isEmpty(message)) {
				message = message.substring(0,
						message.length() - 1);
				message=message+" 存在同年同月重复货品分类记录，请检查后重新上传！";
				return message;
			}
		}
		return null;
	}
	public String checkYearInfo(String SESSION_ID){
		int count=Integer.parseInt(this.load("SaledateUp.checkYearInfo", SESSION_ID).toString());
		if(count>1){
			return "存在不同年份、月份，请检查后重新上传！";
		}
		return null;
	}
	public void operateSaleInfo(UserBean userBean,String SESSION_ID){
		//主表
		List<Map<String,String>> list=this.findList("SaledateUp.queryTempInfo", SESSION_ID);
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
		for (int i = 0; i < list.size(); i++) {
			Map<String,String> map=list.get(i);
			String SALE_DATE_UP_ID=map.get("SALE_DATE_UP_ID");
			map.put("STATE", BusinessConsts.PASS);
			if(!StringUtil.isEmpty(SALE_DATE_UP_ID)){
//				map.put("UPDATOR", userBean.getRYXXID());
//				map.put("UPD_NAME", userBean.getXM());
//				map.put("UPD_TIME", "sysdate");
//				map.put("SALE_DATE_UP_ID", SALE_DATE_UP_ID);
//				upList.add(map);
			}else{
				map.putAll(LogicUtil.sysFild(userBean));
				map.put("SALE_DATE_UP_ID", StringUtil.uuid32len());
				map.put("SALE_DATE_UP_NO", LogicUtil.getBmgz("DRP_SALE_DATE_UP_NO"));
				map.put("AUDIT_ID", userBean.getXTYHID());
				map.put("AUDIT_NAME", userBean.getXM());
				map.put("AUDIT_TIME", "sysdate");
				addList.add(map);
			}
		}
//		if(!upList.isEmpty()){
//			this.batch4Update("SaledateUp.updateInfoById", upList);
//		}
		if(!addList.isEmpty()){
			this.batch4Update("SaledateUp.insertInfoById", addList);
		}
		
		
		
		
		//明细
		List<Map<String,String>> dtlList=this.findList("SaledateUp.queryTempDtlInfo", SESSION_ID);
		List<Map<String,String>> addDtlList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> upDtlList=new ArrayList<Map<String,String>>();
		for (int i = 0; i < dtlList.size(); i++) {
			Map<String,String> map=dtlList.get(i);
			String SALE_DATE_UP_DTL_ID=map.get("SALE_DATE_UP_DTL_ID");
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			if(!StringUtil.isEmpty(SALE_DATE_UP_DTL_ID)){
				upDtlList.add(map);
			}else{
				map.put("SALE_DATE_UP_DTL_ID", StringUtil.uuid32len());
				addDtlList.add(map);
			}
		}
		if(!upDtlList.isEmpty()){
			this.batch4Update("SaledateUp.updateDtlInfoById", upDtlList);
		}
		if(!addDtlList.isEmpty()){
			this.batch4Update("SaledateUp.insertDtlInfoById", addDtlList);
		}
		
		
		//更新主表总金额
		List<String> idList=this.findList("SaledateUp.getTempInfoId", SESSION_ID);
		StringBuffer sb=new StringBuffer();
		for (int i = 0; i < idList.size(); i++) {
			sb.append("'")
			  .append(idList.get(i))
			  .append("',");
		}
		String ids=sb.toString();
		if (!StringUtil.isEmpty(ids)) {
			ids = ids.substring(0,
					ids.length() - 1);
			Map<String,String> map=new HashMap<String, String>();
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "sysdate");
			map.put("STATE", BusinessConsts.PASS);
			map.put("SALE_DATE_UP_IDS", ids);
			this.update("SaledateUp.updateInfoByIdS", map);
		}
	}
	public String getMonth(String MONTH){
		int mo=Integer.parseInt(MONTH);
		switch (mo) {
		case 1:
			return "01";
		case 2:
			return "02";
		case 3:
			return "03";
		case 4:
			return "04";
		case 5:
			return "05";
		case 6:
			return "06";
		case 7:
			return "07";
		case 8:
			return "08";
		case 9:
			return "09";
		case 10:
			return "10";
		case 11:
			return "11";
		case 12:
			return "12";
		default:
			throw new ServiceException("月份："+MONTH+"输入有误，请查看excel单元格格式");
		}
	}
}
