package com.hoperun.drp.base.drpsaleprice.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.drpsaleprice.service.DrpsalepriceService;
import com.hoperun.drp.base.drpsaleprice.model.DrpsalepriceModel;
import com.hoperun.sys.model.UserBean;

public class DrpsalepriceServiceImpl  extends BaseService implements DrpsalepriceService {
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		// TODO Auto-generated method stub
		return this.pageQuery("Drpsaleprice.pageQuery", "Drpsaleprice.pageCount", params, pageNo);
	}
	public Map<String, String> load(String param) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
        params.put("RETAL_PRICE_ID", param);
        return (Map<String, String>) load("Drpsaleprice.loadById", params);
	}
	/**
     * 按货品id和渠道id查询该货品是否已存在
     * @param PRD_ID
     * @return
     */
    public List<String> getCountPRD_ID(String LEDGER_ID){
    	return this.findList("Drpsaleprice.getCountPRD_ID",LEDGER_ID);
    }
    
    /**
	 * @编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * 
	 * @return true, if tx txEdit
	 * @throws ParseException 
	 */
	public void txEdit(List<DrpsalepriceModel> modelList, UserBean userBean){
		List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
		List<Map<String,String>> upList=new ArrayList<Map<String,String>>();
		for (DrpsalepriceModel model : modelList) {
			Map<String, String> params = new HashMap<String, String>();
			String SALE_PRICE_ID=model.getSALE_PRICE_ID();
			if(StringUtil.isEmpty(SALE_PRICE_ID)){
				params.put("SALE_PRICE_ID", StringUtil.uuid32len());
				params.put("PRD_ID", model.getPRD_ID());
				params.put("DECT_PRICE", model.getDECT_PRICE());
				params.put("CREATOR", userBean.getRYXXID());
				params.put("CRE_NAME", userBean.getXM());
				params.put("CRE_TIME", "sysdate");
				params.put("LEDGER_ID", userBean.getLoginZTXXID());
				params.put("LEDGER_NAME", userBean.getLoginZTMC());
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(params);
			}else{
				params.put("UPDATOR", userBean.getRYXXID());
				params.put("SALE_PRICE_ID", SALE_PRICE_ID);
				params.put("UPD_NAME", userBean.getXM());
				params.put("UPD_TIME", "sysdate");
				params.put("DECT_PRICE", model.getDECT_PRICE());
				upList.add(params);
			}
		}
		if (!upList.isEmpty()) {
			this.batch4Update("Drpsaleprice.updateById", upList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Drpsaleprice.insert", addList);
		}
//		List<String> list=this.getCountPRD_ID(userBean.getLoginZTXXID());
//		if(!list.isEmpty()){
//			StringBuffer sub=new StringBuffer();
//			sub.append("货品编号：");
//			for (String str : list) {
//				sub.append(str).append("  ");
//			}
//			sub.append("重复,请检查后重新保存");
//		}
	}
	public long getCount(Map<String,String> map){
		Object obj=this.load("Drpsaleprice.pageCount", map);
		return Long.parseLong(obj.toString());
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
				Map<String,String> params = new HashMap<String,String>();
				if(j==0){//验证第一行第一列是否有hoperun
					if(!map.get("PRD_NO").equals("hoperun")){
						throw new ServiceException("对不起，请使用模版文件进行修改上传!");
					}
					continue;
				}
				if(j==1){//第二行为列名
					continue;
				}
				String PRD_NO=map.get("PRD_NO");
				String DECT_PRICE=map.get("DECT_PRICE");
				//如果货品编号和折扣价都为空，则跳过当前数据执行下一次循环
				if(StringUtil.isEmpty(PRD_NO)&&StringUtil.isEmpty(DECT_PRICE)){
					continue;
				}
				String checkStr="[0-9]+\\.{0,1}[0-9]{0,2}";
				if(!StringUtil.isEmpty(DECT_PRICE)&&!StringUtil.regexCheck(DECT_PRICE,checkStr)){
					throw new ServiceException("折扣价必须为数字，请重新编辑!");
				}
				params.putAll(map);
				params.put("SESSION_ID", SESSION_ID);
				params.put("SALE_PRICE_ID", StringUtil.uuid32len());
//				params.putAll(LogicUtil.sysFild(userBean));
				addList.add(params);
			}
		}
    	if(!addList.isEmpty()){
    		boolean flag=this.batch4Update("Drpsaleprice.insertTemp", addList);
    		if(flag==false){
         	   throw new ServiceException("插入货品临时表出错!");
            }
    	}
    	String mssage=checkPrdInfo(SESSION_ID,userBean.getLoginZTXXID());
    	if(!StringUtil.isEmpty(mssage)){
    		throw new ServiceException(mssage);
    	}
    	// 临时表数据存入渠道销售价格表，如果货品编号存在则修改，不存在则新增
    	potPriceInfo(SESSION_ID, userBean);
//    	删除临时表
    	this.delete("Drpsaleprice.delPrdTemp", SESSION_ID);
    }
	@Override
	public List queryPriceExp(Map<String, String> map) {
		return this.findList("Drpsaleprice.query", map);
	}
	 /**
     * 验证导入货品价格临时表数据
     * @return
     */
    public String checkPrdInfo(String SESSION_ID,String LEDGER_ID){
    	//检查是必填字段
    	String mssage=checkMustFld(SESSION_ID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//检查是否有重复记录
    	mssage = checkRepeatPdt(SESSION_ID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	//检查操作人所属机构是否和货品所属账套不符
    	mssage=checkPrdLedger(SESSION_ID,LEDGER_ID);
    	if(!StringUtil.isEmpty(mssage)){
    		return mssage;
    	}
    	return null;
    }
    /**检查是必填字段
	 * @param SESSION_ID
	 * @return
	 */
	private String checkMustFld(String SESSION_ID){
		int count=StringUtil.getInteger(this.load("Drpsaleprice.checkMustFld", SESSION_ID));
		if(count>0){
			return "有数据未输入货品编号或折扣价格，请检查后重新导入";
		}
		return null;
	}
	/**检查是否有重复记录
	 * @param SESSION_ID
	 * @return
	 */
	private String checkRepeatPdt(String SESSION_ID){
		List pdtList = this.findList("Drpsaleprice.checkPdtRepeat", SESSION_ID);
		StringBuffer str=new StringBuffer();
		if(!pdtList.isEmpty()){
			str.append("货品编号:");
			for (int i = 0; i < pdtList.size(); i++) {
				HashMap pdtMap = (HashMap)pdtList.get(i);
				String PRD_NO = (String)pdtMap.get("PRD_NO");
				str.append(PRD_NO).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"存在重复记录,请重新编辑!";
		}
		return message;
	}
	/**
	 * 检查操作人所属机构是否和货品所属账套不符
	 * @param SESSION_ID
	 * @param DRPFLAG
	 * @param LEDGER_ID
	 * @return
	 */
	private String checkPrdLedger(String SESSION_ID,String LEDGER_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("SESSION_ID", SESSION_ID);
		map.put("SQL", " a.LEDGER_ID!='"+LEDGER_ID+"' and a.COMM_FLAG!=1 ");
		List<String> prdList=this.findList("Drpsaleprice.checkPrdLedger", map);
		StringBuffer str=new StringBuffer();
		if(!prdList.isEmpty()){
			str.append("货品编号：");
			for (int i = 0; i < prdList.size(); i++) {
				str.append(prdList.get(i)).append(",");
			}
		}
		String message=str.toString();
		if (!StringUtil.isEmpty(message)) {
			message = message.substring(0,
					message.length() - 1);
			message=message+"不属于您所属的渠道,请重新编辑!";
		}
		return message;
	}
	/**
	 * 把临时库数据转入渠道货品表
	 * 货品编号存在的修改，不存在的新增
	 * @param SESSION_ID
	 * @return
	 */
	public void potPriceInfo(String SESSION_ID,UserBean userBean){
		Map<String,String> map=new HashMap<String, String>();
		map.putAll(LogicUtil.sysFild(userBean));
		map.put("UPDATOR", userBean.getRYXXID());
		map.put("UPD_NAME", userBean.getXM());
		map.put("SESSION_ID", SESSION_ID);
		//先修改
		this.update("Drpsaleprice.upDrpPriceInfo", map);
		//后新增
		this.update("Drpsaleprice.insertDrpPriceInfo", map);
	}
}
