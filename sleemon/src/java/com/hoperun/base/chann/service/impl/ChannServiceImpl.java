/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ChannServiceImpl.java
 */
package com.hoperun.base.chann.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.base.chann.model.DeliveraddrModelChld;
import com.hoperun.base.chann.service.ChannService;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ChannServiceImpl.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public class ChannServiceImpl extends BaseService implements ChannService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		// TODO Auto-generated method stub
		return this.pageQuery("chann.pageQuery", "chann.pageCount", params, pageNo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		// TODO Auto-generated method stub
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", param);
        return (Map<String, String>) load("chann.loadById", params);
	}

	@Override
	public int getCountCHANN_NO(String CHANN_NO) {
		// TODO Auto-generated method stub
		return queryForInt("chann.getCountCHANN_NO",CHANN_NO);
	}

	@Override
	public boolean loadSTATE(String CHANN_ID) {
		// TODO Auto-generated method stub
		if ("停用".equals(load("chann.loadSTATE", CHANN_ID))) {
            return false;
        } else {
            return true;
        }
	}
	 /**
     * 编辑.
     * 
     * @param CHANN_ID the chann id
     * @param ChannpunishModel the chann model
     * @param userBean the user bean
     * 
     * @return the string
     */
	@SuppressWarnings("unchecked")
	@Override
	public String txEdit(String CHANN_ID, ChannModel model, UserBean userBean) {
		// TODO Auto-generated method stub
		Map <String, String> params = new HashMap <String, String>();
		String SHIP_POINT_NO=model.getSHIP_POINT_NO();
		Map<String,String> maps=new HashMap<String,String>();
		maps.put("SHIP_POINT_NO", SHIP_POINT_NO);
		maps.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		maps.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		Map<String,String> map=(Map<String, String>) load("chann.getSHIP_POINTInfo",maps);
		if(map==null){
			throw new ServiceException("对不起，没有找到该发货点信息，请重新输入发货点编号 !");
		}
        params.put("CHANN_NO", model.getCHANN_NO());//渠道编号
        params.put("CHANN_NAME", model.getCHANN_NAME());//渠道名称
        params.put("CHANN_ABBR", model.getCHANN_ABBR());//渠道简称
        params.put("CHANN_TYPE", model.getCHANN_TYPE());//渠道类型
//        params.put("CHANN_ID_P", model.getCHANN_ID_P());//上级渠道id
//        params.put("CHANN_NO_P", model.getCHANN_NO_P());//上级渠道编号
//        params.put("CHANN_NAME_P", model.getCHANN_NAME_P());//上级渠道名称
        String TERM_DECT_CTR_FLAG=model.getTERM_DECT_CTR_FLAG();
        if(StringUtil.isEmpty(TERM_DECT_CTR_FLAG)){
        	TERM_DECT_CTR_FLAG="0";
        }
        params.put("TERM_DECT_CTR_FLAG", TERM_DECT_CTR_FLAG);//终端销售折扣控制标记
        params.put("AREA_SER_ID", model.getAREA_SER_ID());//区域服务中心ID
        params.put("AREA_SER_NO", model.getAREA_SER_NO());//区域服务中心编号
        params.put("AREA_SER_NAME", model.getAREA_SER_NAME());//区域服务中心名称
        
        params.put("TAX_RATE", model.getTAX_RATE());//税率
        params.put("COST_TYPE", model.getCOST_TYPE());//成本计算方式
      //-- 0156117--Start--刘曰刚--2014-06-16//
        //编辑区域经理信息
        params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID());//区域经理ID
        params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME());//区域经理名称
        params.put("AREA_MANAGE_TEL", model.getAREA_MANAGE_TEL());//区域经理电话
      //-- 0156117--End--刘曰刚--2014-06-16//
        params.put("INIT_YEAR", model.getINIT_YEAR());//初始化年份
        params.put("INIT_MONTH", model.getINIT_MONTH());//初始化月份
        params.put("CHAA_LVL", model.getCHAA_LVL());//渠道级别
        params.put("ZONE_ID", model.getZONE_ID());//行政区划id
        params.put("NATION", model.getNATION());//国家
        params.put("PROV", model.getPROV());//省份
        params.put("CITY", model.getCITY());//城市
        params.put("COUNTY", model.getCOUNTY());//区县
        params.put("CITY_TYPE", model.getCITY_TYPE());//城市类型
        params.put("PERSON_CON", model.getPERSON_CON());//联系人
        params.put("TEL", model.getTEL());//电话
        params.put("MOBILE", model.getMOBILE());//手机
        params.put("TAX", model.getTAX());//传真
        params.put("POST", model.getPOST());//邮政编号
        params.put("ADDRESS", model.getADDRESS());//详细地址
        params.put("EMAIL", model.getEMAIL());//电子邮件
        params.put("WEB", model.getWEB());//网站
        params.put("LEGAL_REPRE", model.getLEGAL_REPRE());//法人代表
        params.put("BUSS_LIC", model.getBUSS_LIC());//营业执照号
        params.put("AX_BURDEN", model.getAX_BURDEN());//税务登记号
        params.put("ORG_CODE_CERT", model.getORG_CODE_CERT());//组织机构代码证
        params.put("BUSS_NATRUE", model.getBUSS_NATRUE());//经营性质
        params.put("BUSS_SCOPE", model.getBUSS_SCOPE());//经营范围
        params.put("VAT_NO", model.getVAT_NO());//增值税号
        params.put("INVOICE_TI", model.getINVOICE_TI());//发票抬头
        params.put("INVOICE_ADDR", model.getINVOICE_ADDR());//发票地址;
        params.put("BANK_NAME", model.getBANK_NAME());//开户银行
        params.put("BANK_ACCT", model.getBANK_ACCT());//银行账号
        params.put("FI_CMP_NO", model.getFI_CMP_NO());//财务对照码
        params.put("DEPOSIT", model.getDEPOSIT()+"");//保证金
        params.put("DEBT_DEF", model.getDEBT_DEF()+"");//欠款期限
        params.put("PRICE_CLAUSE", model.getPRICE_CLAUSE());//价格条款
        params.put("BUSS_LIC_ATT", model.getBUSS_LIC_ATT());//营业执照附件
        params.put("TAX_BURDEN_ATT", model.getTAX_BURDEN_ATT());//税务登记附件
        params.put("ORG_CERT_ATT", model.getORG_CERT_ATT());//组织机构代码证附件
        params.put("REMARK", model.getREMARK());//备注
        params.put("SHIP_POINT_ID", map.get("SHIP_POINT_ID"));//发货点id
        params.put("SHIP_POINT_NO", map.get("SHIP_POINT_NO"));//发货点编号
        params.put("SHIP_POINT_NAME", map.get("SHIP_POINT_NAME"));//发货点名称
        params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
        params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
        params.put("AREA_ID", model.getAREA_ID());//区域id
        params.put("AREA_NO", model.getAREA_NO());//区域编号
        params.put("AREA_NAME", model.getAREA_NAME());//区域名称
        params.put("PAY_RATE", model.getPAY_RATE()+"");//付款比例
        params.put("JOIN_DATE", model.getJOIN_DATE());//加盟时间
        //-- 0156170--Start--zzb--2014-06-16//
        params.put("CHAA_SALE_LVL", model.getCHAA_SALE_LVL());//渠道销售级别
        params.put("IS_UPDATE_STOREIN_FLAG", model.getIS_UPDATE_STOREIN_FLAG()); //是否允许修改入库数量
        //-- 0156170--Start--end--2014-06-16//
        if (StringUtils.isEmpty(CHANN_ID)) {
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", DateUtil.now());//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
    		params.put("CHANN_ID", model.getCHANN_NO());//渠道id
        	params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//状态
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	params.put("PUNISH_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//惩罚标记
        	params.put("IS_BASE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);//是否总部
            txInsert(params);
        } else {
            params.put("CHANN_ID", CHANN_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称
            params.put("UPD_TIME", DateUtil.now());//更新时间
            txUpdateById(params);
            clearCaches(CHANN_ID);
        }
        return CHANN_ID;
	}
	   /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {
        insert("chann.insert", params);
        return true;
    }
    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {
    	update("chann.upZTXX", params);
    	update("chann.upJGXX", params);
    	update("chann.upBMXX", params);
    	update("chann.upTerminal",params);
        return update("chann.updateById", params);
        
    }
    /**
     * 校验是否有未停用的下级渠道.
     * 
     * @param params the params
     * 
     * @return the int
     */
    public int checkSubChann(Map <String, String> params) {

        return queryForInt("chann.getWtyXJ", params);
    }
    /**
     * 软删除.
     * 
     * @param CHANN_ID the chann id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
	public boolean txDelete(Map<String,String> map) {
          delete("chann.delete", map);
          //删除子表 
		 return update("chann.delChldByFkId", map);
	}

	/**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {
        // 系统用户表里 设置了分管所以渠道的标记为1时，删除 分管给该用户的渠道
    	update("XTYH.delChannChargByChangeChann", params);
    	//修改账套状态
    	update("chann.upZTXX", params);
    	
        return update("chann.updateStateById", params);
    }

    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
	public boolean txStartById(Map <String, String> params) {
    	//按渠道id获取当前状态下渠道状态
    	String CHANN_ID=params.get("CHANN_ID");
        String STATE=this.getSTATE(CHANN_ID);
        //如果渠道状态为制定的话，新增帐套信息，机构信息，部门信息，用户
        if(STATE.equals(BusinessConsts.JC_STATE_DEFAULT)){
        	LogicUtil.insertChannConf(CHANN_ID);
        }
        //如果渠道状态为启用的话，验证区域经理名称：  区域经理电话： 区域编号：  区域名称：是否为空
        if(params.get("STATE").equals(BusinessConsts.JC_STATE_ENABLE)){
        	if (checkChannInfoIntact(CHANN_ID)) {
        		throw new ServiceException("对不起，渠道信息不全，不能启用 !");
			}
        }
        //系统用户表里 设置了分管所以渠道的标记为1时，启用渠道时分管给该用户
        insert("XTYH.insertUserChannChargByChangeChann",params);
        //修改账套状态
        update("chann.upZTXX", params);
        return update("chann.updateStateById", params);
    }
    //按渠道id查找渠道状态
    public String getSTATE(String CHANN_ID){
    	return load("chann.getSTATE",CHANN_ID).toString();
    }
  //渠道状态为制定时点启用状态需要新增帐套信息，机构信息，部门信息，用户
    public boolean txInsertSundry(Map<String,String> map){
    	this.insert("chann.insertT_SYS_ZTXX", map);
    	this.insert("chann.insertT_SYS_JGXX", map);
    	this.insert("chann.insertT_SYS_BMXX", map);
    	this.insert("chann.insertT_SYS_RYXX", map);
    	this.insert("chann.insertT_SYS_XTYH", map);
    	this.insert("chann.insertT_SYS_XTYHZTDZ", map);
    	this.insert("chann.insertT_SYS_XTYHJS", map);
    	return true;
    }
    
    
	 /**
     * * 子表记录编辑：新增/修改
     * @param CHANN_ID the CHANN_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String CHANN_ID, List <DeliveraddrModelChld> modelList){
    	//新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        
        for(DeliveraddrModelChld model : modelList){
        	Map <String, String> params = new HashMap <String, String>();
        	params.put("CHANN_ID", CHANN_ID);
        	params.put("DELIVER_ADDR_NO", model.getDELIVER_ADDR_NO());
        	params.put("DELIVER_DTL_ADDR", model.getDELIVER_DTL_ADDR());
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        	if(StringUtil.isEmpty(model.getDELIVER_ADDR_ID())){
        		params.put("DELIVER_ADDR_ID", StringUtil.uuid32len());
        		params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//制定
        		addList.add(params);
        	}else{
        		params.put("DELIVER_ADDR_ID", model.getDELIVER_ADDR_ID());
        		updateList.add(params);
        	}
        	
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("chann.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("chann.insertChld", addList);
        }
        List<String> list=this.findList("chann.checkAddrCount", "");
        if(!list.isEmpty()){
        	StringBuffer str=new StringBuffer();
        	str.append("收货地址编号：");
        	for (int i = 0; i < list.size(); i++) {
				str.append(list.get(i)).append(",");
			}
        	str.deleteCharAt(str.length()-1);
        	str.append("   重复，请检查后重新保存");
        	throw new ServiceException(str.toString());
        }
    	return true;
    }
    
	/**
     * * 根据主表Id查询子表记录
     * @param CHANN_ID the CHANN_ID
     * @return  
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DeliveraddrModelChld> childQuery(String CHANN_ID) {
        Map params = new HashMap();
        params.put("CHANN_ID", CHANN_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("chann.queryChldByFkId", params);
    }
    
    /**
     * * 根据子表Id批量加载子表信息
     * 
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DeliveraddrModelChld> loadChilds(Map <String, String> params) {
       return findList("chann.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param DELIVER_ADDR_IDS the DELIVER_ADDR_IDS
     */
    @Override
    public void txBatch4DeleteChild(String DELIVER_ADDR_IDS) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("DELIVER_ADDR_IDS", DELIVER_ADDR_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("chann.deleteChldByIds", params);
    }
    
    
    public void txStarChld(String DELIVER_ADDR_IDS){
    	Map<String,String> params = new HashMap<String,String>();
 	    params.put("DELIVER_ADDR_IDS", DELIVER_ADDR_IDS);
 	    params.put("STATE", BusinessConsts.JC_STATE_ENABLE);//启用
        update("chann.stopChild", params);
    }
    
    public void txStopChld(String DELIVER_ADDR_IDS){
    	Map<String,String> params = new HashMap<String,String>();
 	    params.put("DELIVER_ADDR_IDS", DELIVER_ADDR_IDS);
 	    params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);//停用
        update("chann.stopChild", params);
    }
    
    /**
     * 查重
     * @param CHANN_ID 渠道ID
     * @param DELIVER_ADDR_IDS 地址信息IDs
     * @param DELIVER_ADDR_NOS 地址编号
     * @return
     */
    public int valiAddrNo(String CHANN_ID,String DELIVER_ADDR_IDS,String DELIVER_ADDR_NOS){
    	Map<String,String> params = new HashMap<String,String>();
 	    params.put("DELIVER_ADDR_NOS", DELIVER_ADDR_NOS);
 	    params.put("CHANN_ID", CHANN_ID);
 	    params.put("DELIVER_ADDR_IDS", DELIVER_ADDR_IDS);
    	return this.queryForInt("chann.countAddrNo", params);
    }
    //查询渠道分管
    public List chargQuery(String CHANN_ID){
    	return this.findList("chann.chargQuery", CHANN_ID);
    }
    /**
     * 查询导出数据
     */
    public  List <Map> qryChannExp(Map params){
        return findList("chann.qryChannExp",params);
    }
    
    /**
     * 批量编辑区域经理
     */
	public void txbatchUpdate(String flag,String CHANN_IDS, ChannModel model,UserBean userBean) {
		Map <String, String> params = new HashMap <String, String>();
		params.put("CHANN_IDS", CHANN_IDS);
		//1区域经理 2 区域 3 生产基地 4区服
        if("1".equals(flag)){
        	 params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID());//区域经理ID
             params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME());//区域经理名称
             params.put("AREA_MANAGE_TEL", model.getAREA_MANAGE_TEL());// 
             update("chann.batchUpdateChann_1", params);
        }
        
        if("2".equals(flag)){
        	params.put("AREA_ID", model.getAREA_ID());
            params.put("AREA_NO", model.getAREA_NO());
            params.put("AREA_NAME", model.getAREA_NAME());
            update("chann.batchUpdateChann_2", params);
            update("chann.upTerminalByChannIds",params);
        }
        if("3".equals(flag)){
        	 params.put("SHIP_POINT_NO", model.getSHIP_POINT_NO()); 
             params.put("SHIP_POINT_ID", model.getSHIP_POINT_ID()); 
             params.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME()); 
             update("chann.batchUpdateChann_3", params);
        }
        if("4".equals(flag)){
        	params.put("AREA_SER_ID", model.getAREA_SER_ID()); 
            params.put("AREA_SER_NO", model.getAREA_SER_NO()); 
            params.put("AREA_SER_NAME", model.getAREA_SER_NAME()); 
            update("chann.batchUpdateChann_4", params);
        }
	}
	
	
	/**
	 * @param flag
	 * @param CHANN_IDS
	 * @param model
	 * @param userBean
	 */
	public void txbatchUpdateStoreIn(String flag,String CHANN_IDS, ChannModel model,UserBean userBean){
		 Map <String, String> params = new HashMap <String, String>();
		 params.put("CHANN_IDS", CHANN_IDS);
    	 params.put("IS_UPDATE_STOREIN_FLAG", model.getIS_UPDATE_STOREIN_FLAG());//是否允许修改入库数量
         update("chann.batchUpdateStoreIn", params);
        
	}
   
	/**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageBatch(Map <String, String> params, int pageNo){
    	return this.pageQuery("chann.pageBatch",
				"chann.pageBatchCount", params, pageNo);
    }
    
    /****
     * @param params
     * @return
     */
    public   List<Map<String,String>>  childQuery(Map<String,String> params){
    	return this.findList("chann.queryChannByparams", params);
    }
    
    /**
     * 验证渠道信息是否完整 区域经理名称：  区域经理电话： 区域编号：  区域名称：
     * @param CHANN_ID
     * @return
     */
    public boolean checkChannInfoIntact(String CHANN_ID){
    	int count=Integer.parseInt(String.valueOf(this.load("chann.checkChannInfoIntact",CHANN_ID)));
    	if(count>0){
    		return true;
    	}
    	return false;
    }
}
