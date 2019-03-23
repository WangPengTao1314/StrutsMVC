package com.hoperun.drp.oamg.decorationreit.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.decorationreit.model.DecorationreitModel;
import com.hoperun.drp.oamg.decorationreit.service.DecorationreitService;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.sys.model.UserBean;

public class DecorationreitServiceImpl extends BaseService implements DecorationreitService {

	
	 /**
	 * @param RNVTN_REIT_REQ_ID
	 * @param obj
	 * @param userBean
	 */
	public void txEdit(String RNVTN_REIT_REQ_ID, DecorationreitModel model,
			UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_RNVTN_ID", model.getCHANN_RNVTN_ID());  
		if(model.getFORM_BILL_FLAG_T().equals("1")){
			params.put("CHANN_RNVTN_NO", "");  
		}else{
		    params.put("CHANN_RNVTN_NO", model.getCHANN_RNVTN_NO());  
		}
		params.put("RNVTN_PROP", model.getRNVTN_PROP());  
		params.put("CHANN_ID", model.getCHANN_ID()); //渠道ID
		params.put("CHANN_NO", model.getCHANN_NO()); //渠道编号
		params.put("CHANN_NAME", model.getCHANN_NAME()); //渠道名称
 		//params.put("CHANN_PERSON_CON", model.getchan); //渠道联系人
 		//params.put("CHANN_TEL", model.getCHANN_TEL()); //渠道联系电话
		params.put("TERM_ID", model.getTERM_ID()); //终端ID
		params.put("TERM_NO", model.getTERM_NO()); //终端编号
		params.put("TERM_NAME", model.getTERM_NAME()); //终端名称
		params.put("STATE", model.getSTATE()); //状态
		
		params.put("AREA_ID", model.getAREA_ID());
		params.put("AREA_NO", model.getAREA_NO()); //所属战区编号
		params.put("AREA_NAME", model.getAREA_NAME()); //所属战区名称
 
		params.put("AREA_MANAGE_ID", model.getAREA_MANAGE_ID()); 
		params.put("AREA_MANAGE_NAME", model.getAREA_MANAGE_NAME()); 
		params.put("AREA_MANAGE_TEL", model.getAREA_MANAGE_TEL()); 
		params.put("REIT_AMOUNT_PS", model.getREIT_AMOUNT_PS()); 
		params.put("REIT_AMOUNT", model.getREIT_AMOUNT()); 
		params.put("AMOUNT_DESC", model.getAMOUNT_DESC());    //金额说明
		
		params.put("DRAW_AREA", model.getDRAW_AREA()); 
		params.put("DRAW_REIT_AMOUNT_PS", model.getDRAW_REIT_AMOUNT_PS()); 
		params.put("DRAW_FISH_DATE", model.getDRAW_FISH_DATE()); 
		params.put("RNVTN_FISH_DATE", model.getRNVTN_FISH_DATE()); 
		params.put("PUNISH_REMARK", model.getPUNISH_REMARK()); 
		params.put("DEPOSIT", model.getDEPOSIT()); 
		params.put("RNVTN_DAYS", model.getRNVTN_DAYS()); 
		params.put("DEPOSIT_RETURN_AMOUNT", model.getDEPOSIT_RETURN_AMOUNT()); 
		
		params.put("REAL_REIT_AMOUNT", model.getREAL_REIT_AMOUNT()); 
		params.put("REIT_POLICY", model.getREIT_POLICY()); 
	    params.put("TOTAL_REITED_NUM", model.getTOTAL_REITED_NUM()); 
	    params.put("REITED_NUM", model.getREITED_NUM()); 
		 
		
		//当前报销次数报销比例
		String REITED_RATE =  model.getREITED_RATE();
		if(!StringUtil.isEmpty(REITED_RATE)){
			REITED_RATE = REITED_RATE.substring(0,REITED_RATE.length()-1);
		}else{
			REITED_RATE= "0";
		}
		params.put("REITED_RATE",REITED_RATE); 
		params.put("CAN_RETURN_AMOUNT", model.getCAN_RETURN_AMOUNT()); 
		params.put("TOTAL_RETURN_AMOUNT", model.getTOTAL_RETURN_AMOUNT()); 
		params.put("LEFT_CAN_RETURN_AMOUNT", model.getLEFT_CAN_RETURN_AMOUNT()); 
		params.put("SALE_COMPACT_AMOUNT", model.getSALE_COMPACT_AMOUNT()); 
		params.put("REQ_ID", model.getREQ_ID()); 
		params.put("REQ_NAME", model.getREQ_NAME()); 
		params.put("REQ_DATE", model.getREQ_DATE()); 
		params.put("REMARK", model.getREMARK());  
		params.put("OPEN_SALE_YEAR", model.getOPEN_SALE_YEAR());
		params.put("DESIGN_PERSON",  model.getDESIGN_PERSON());
		params.put("DESIGN_ID", model.getDESIGN_ID());
		params.put("SALE_STORE_ID", model.getSALE_STORE_ID());
		params.put("SALE_STORE_NAME", model.getSALE_STORE_NAME());
        params.put("ADDRESS", model.getADDRESS());
        params.put("BUSS_SCOPE", model.getBUSS_SCOPE());
        params.put("USE_AREA", model.getUSE_AREA());
        params.put("LOCAL_TYPE", model.getLOCAL_TYPE());
        params.put("IS_NORTH", model.getIS_NORTH());
        params.put("BUDGET_ITEM_ID", model.getBUDGET_ITEM_ID());
        params.put("BUDGET_ITEM_NO", model.getBUDGET_ITEM_NO());
        params.put("BUDGET_ITEM_NAME", model.getBUDGET_ITEM_NAME());
        params.put("BUDGET_QUOTA_ID", model.getBUDGET_QUOTA_ID());
        params.put("YEAR_GOODS_AMOUNT", model.getYEAR_GOODS_AMOUNT());
        params.put("QUARTER_RATE", model.getQUARTER_RATE());
        params.put("IS_VIOLATE_REMARK", model.getIS_VIOLATE_REMARK());
        params.put("FROM_BILL_FLAG", model.getFORM_BILL_FLAG_T());//
        params.put("AMOUNT_DESC", model.getAMOUNT_DESC());  //金额说明
        params.put("TOTAL_REIT_AMOUNT", model.getTOTAL_REIT_AMOUNT()); //总报销金额
        
		if (StringUtils.isEmpty(RNVTN_REIT_REQ_ID)) {
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//0
			RNVTN_REIT_REQ_ID = StringUtil.uuid32len();
			params.put("RNVTN_REIT_REQ_ID", RNVTN_REIT_REQ_ID);
			params.put("RNVTN_REIT_REQ_NO", LogicUtil.getBmgz("DRP_RNVTN_REIT_REQ_NO"));  
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
			this.insert("Decorationreit.insert", params);
			
			String att_path = loadAtt(RNVTN_REIT_REQ_ID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", RNVTN_REIT_REQ_ID);
			attParams.put("ATT_PATH",model.getPIC()
					+";"+model.getZHUANGXIUSQ()
					+";"+model.getYBXIANG()
					+";"+model.getMCYSTAB()
					+";"+model.getZGYANSHOU()
					+";"+model.getZXPIC()
					+";"+model.getZXFAPIAO()
					+";"+model.getOLD_OA_ORDER_PIC());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
			
		} else{
			params.put("RNVTN_REIT_REQ_ID", RNVTN_REIT_REQ_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称           
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
			this.update("Decorationreit.updateById", params);
			
            clearCaches(RNVTN_REIT_REQ_ID);
			Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",RNVTN_REIT_REQ_ID);
			attParamsT.put("ATT_PATH", model.getPIC()
					+";"+model.getZHUANGXIUSQ()
					+";"+model.getYBXIANG()
					+";"+model.getMCYSTAB()
					+";"+model.getZGYANSHOU()
					+";"+model.getZXPIC()
					+";"+model.getZXFAPIAO()
					+";"+model.getOLD_OA_ORDER_PIC()
			);//图片路径
			update("Att.updateAtt", attParamsT);
		}
		
		//插入附件表
		/*
		String ATT_PATH = model.getATT_PATH();//附件
		String ATT_ID = model.getATT_ID();//附件表ID
		if(!StringUtil.isEmpty(ATT_PATH)){
			if(StringUtil.isEmpty(ATT_ID)){
				this.insertFileUpload(RNVTN_REIT_REQ_ID, ATT_PATH, userBean);
			}else{
				this.updateFileUpload(RNVTN_REIT_REQ_ID, ATT_PATH, userBean);
			}
		}*/
	}

	/**
	 * 加载上传文件.
	 * @param param
	 * @return
	 */
	public String loadAtt(String param){		
		Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_BILL_ID", param);
        return (String) load("Att.loadAtt", params);
	}
	
	/**
	 * 分页查询
	 * Description :.
	 * @param params the params
	 * @param pageNo the page no
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Decorationreit.pageQuery",
				"Decorationreit.pageCount", params, pageNo);
	}

   /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
	public Map<String, String> loadById(String RNVTN_REIT_REQ_ID) {
		return (Map<String, String>) load("Decorationreit.loadById", RNVTN_REIT_REQ_ID);
	}
	
   /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
	public Map<String, String> loadByIdT(String RNVTN_REIT_REQ_ID) {
		return (Map<String, String>) load("Decorationreit.loadByIdT", RNVTN_REIT_REQ_ID);
	}
	
	  /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadT(String RNVTN_REIT_REQ_ID){
    	return (Map<String, String>) load("Decorationreit.loadT", RNVTN_REIT_REQ_ID);
    }
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public Map <String, String> loadTt(String RNVTN_REIT_REQ_ID){
    	return (Map<String, String>) load("Decorationreit.loadTt", RNVTN_REIT_REQ_ID);
    }

	public void updateState(String CHANN_RNVTN_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
		params.put("STATE", "提交");
		this.update("Decorationreit.updState", params);
	}
	
   /**
     * @param RNVTN_FISH_DATE
     * @param TERM_ID
     */
	public void updateTerm(String RNVTN_FISH_DATE,String TERM_ID){
		Map<String, String> params = new HashMap<String, String>();
		params.put("LAST_DECOR_TIME", RNVTN_FISH_DATE);
		params.put("TERM_ID", TERM_ID);
		this.update("Decorationreit.updateTerm", params);
	}

	/**
	 * 删除数据
	 * @param DATA_CONF 
	 * @return true, if tx delete
	 */
	public boolean txDelete(String RNVTN_REIT_REQ_ID, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("RNVTN_REIT_REQ_ID", RNVTN_REIT_REQ_ID);
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称           
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间      
		return update("Decorationreit.delete", params);
	}
	
	/**
	 * 查看终端状态
	 * @param DATA_CONF 
	 * @return true, if tx delete
	 */
	public List<Map<String,String>>  queryTermState(String TERM_ID){
		Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("TERM_ID", TERM_ID);
    	return  this.findList("Decorationreit.queryTermState", paramMap);
	}
	
	/**
     * 获取报销频次
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
    public List<Map<String,String>> getReimbursement(String CHANN_RNVTN_ID){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
    	return  this.findList("Decorationreit.loadRnvinReitDtl", paramMap);
    }
    
    /**
     * 计算已经报销的 报销金额
     * @param CHANN_RNVTN_ID  装修申请单ID
     * @return
     */
    public Map<String,String> sumCanReturnAmout(String CHANN_RNVTN_ID){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
    	return  (Map<String, String>) this.load("Decorationreit.sumCanReturnAmout", paramMap);
    }
    
    public BudgetquotaModel qyeryQuotaAmount(String RNVTN_REIT_REQ_ID){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("RNVTN_REIT_REQ_ID", RNVTN_REIT_REQ_ID);
    	List<BudgetquotaModel> list = this.findList("Budgetquota.qyeryQuotaAmount", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
    
//    public DecorationreitModel queryJudgeModel(String CHANN_RNVTN_ID){
//    	Map<String,String> paramMap = new HashMap<String,String>();
//    	paramMap.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
//    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//    	paramMap.put("orderField","u.REITED_NUM DESC ");
//     	List<DecorationreitModel> list = this.findList("Decorationreit.selectBrothers", paramMap);
//     	if(null != list && !list.isEmpty()){
//     		return list.get(0);
//     	}
//     	return null;
//    }
    
    
    /**
     * 插入附件表 
     * @param FROM_BILL_ID 表ID
     * @param ATT_PATH 附件路径
     * @param userBean
     */
    public void insertFileUpload(String FROM_BILL_ID,String ATT_PATH,UserBean userBean){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("ATT_ID", StringUtil.uuid32len());
    	paramMap.put("FROM_BILL_ID", FROM_BILL_ID);
    	paramMap.put("ATT_PATH", ATT_PATH);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	paramMap.put("CREATOR", userBean.getXTYHID());//制单人ID
    	paramMap.put("CRE_NAME", userBean.getXM());//制单人名称
    	paramMap.put("CRE_TIME", DateUtil.now());//制单时间
    	this.insert("Decorationreit.insertFileUpload", paramMap);
    }
    
    /**
     * 更新附件信息
     * @param ATT_ID 附件ID
     * @param ATT_PATH 附件路径
     * @param userbean
     */
    public void updateFileUpload(String FROM_BILL_ID,String ATT_PATH,UserBean userBean){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("FROM_BILL_ID", FROM_BILL_ID);
    	paramMap.put("ATT_PATH", ATT_PATH);
    	this.insert("Decorationreit.updateFileUpload", paramMap);
    }
    
    
    /**
     * 新增的时候 自动判断是第几次申请，如果不是首次数据项自动带出
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
    public DecorationreitModel queryJudgeModel(String CHANN_RNVTN_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CHANN_RNVTN_ID", CHANN_RNVTN_ID);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	paramMap.put("orderField","u.REITED_NUM DESC ");
     	List<DecorationreitModel> list = this.findList("Decorationreit.selectBrothers", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
    
    
    /**
     * 新增的时候 自动判断是第几次申请，如果不是首次数据项自动带出
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
    public Map<String,Object> queryJudgeModelT(String CHANN_ID,String YEAR){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CHANN_ID", CHANN_ID);
    	paramMap.put("YEAR", YEAR);
     	List<Map<String,Object>> list = this.findList("Decorationreit.queryJudgeModelT", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
    
    /**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public String queryFnshRate(String CHANN_ID,String YEAR){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_ID", CHANN_ID);
		params.put("YEAR", YEAR);
		List<Map<String,Object>> list =  this.findList("Decorationreit.selectFnshRate", params);
		if(null != list && !list.isEmpty()){
        Map<String, Object> map1 = list.get(0);
        if(map1.get("FNSH_RATE")!=null){
             return  String.valueOf(map1.get("FNSH_RATE").toString());
        }
		}
        return null;
    }
    
    /**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public String queryYearPlanAmount(String CHANN_ID,String YEAR){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_ID", CHANN_ID);
		params.put("PLAN_YEAR", YEAR);
		List<Map<String,Object>> list =  this.findList("Decorationreit.selectYearAmount", params);
		if(null != list && !list.isEmpty()){
        Map<String, Object> map1 = list.get(0);
        if(map1.get("YEAR_AMOUNT")!=null){
             return  String.valueOf(map1.get("YEAR_AMOUNT").toString());
        }
		}
        return null;
    }
    
    /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Decorationreit.expertExcel",params);
	}
	
	/**
     * 获取经营品牌
     * @return
     */
    public List<DecorationreitModel>  queryBussScopeT(){
    	Map<String, String> params = new HashMap<String, String>();
        return this.findList("Decorationapp.queryBussScope",params);
    }
    
	/**
     * 获取装修性质
     * @return
     */
    public List<DecorationreitModel>  queryRnvtnProp(){
    	Map<String, String> params = new HashMap<String, String>();
        return this.findList("Decorationapp.queryRnvtnProp",params);
    }
    
    /**
     * 获取商场位置类别
     * @return
     */
    public List<DecorationreitModel>  queryLocalType(){
    	Map<String, String> params = new HashMap<String, String>();
        return this.findList("Decorationapp.queryLocalType",params);
    }
    
    /**
     * @return
     */
    public  int   getRowcount(){
    	return queryForInt("Decorationreit.queryRowcount","");
    }
    
    /**
     * @param BUSS_SCOPE
     * @return
     */
    public String toQBussScope(String BUSS_SCOPE){
    	 String Scope = "";
    	 Map<String, String> params = new HashMap<String, String>();
    	 String[] str = BUSS_SCOPE.split(",");
    	 for(int i=0;i<str.length;i++){
    		 params.put("DATA_DTL_CODE", str[i]);
    		 Scope += (String)load("Decorationreit.queryName", params)+",";
    	 }
    	 Scope = Scope.substring(0,Scope.length()-1);
		 return Scope;
    }
    
    /**
     * @param AREA_ID
     * @return
     */
    public String queryWareaName(String AREA_ID){
    	Map<String, String> params = new HashMap<String, String>();
        params.put("AREA_ID",AREA_ID);
        return (String) load("Decorationreit.queryWareaName", params);
    }
    
    /**
     * @param LOCAL_TYPE
     * @return
     */
    public String toQLocalType(String LOCAL_TYPE){
    	 String Type = "";
    	 Map<String, String> params = new HashMap<String, String>();
    	 String[] str = LOCAL_TYPE.split(",");
    	 for(int i=0;i<str.length;i++){
    		 params.put("DATA_DTL_CODE", str[i]);
    		 Type += (String)load("Decorationreit.queryName", params)+",";
    	 }
    	 Type = Type.substring(0,Type.length()-1);
		 return Type;
    }
    
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZW(String RYXXID){
    	return queryForInt("Decorationapp.queryZW",RYXXID);
    }
    
    /**
     * @return
     */
    public String queryGZZXXID(){
    	Map<String, String> params = new HashMap<String, String>();
        return (String) load("Decorationreit.queryGZZXXID", params);	
    }
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZWT(String RYXXID,String GZZXXID){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("RYXXID", RYXXID);
    	params.put("GZZXXID", GZZXXID);
    	return queryForInt("Decorationreit.queryZWT",params);
    }
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
    public String queryReitAmount(String RNVTN_REIT_REQ_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("RNVTN_REIT_REQ_ID", RNVTN_REIT_REQ_ID);
		return (String) load("Decorationreit.queryRealReitAmount", params);
    }
    
    /**
     * @param RNVTN_REIT_REQ_ID
     * @return
     */
	public List <DecorationreitModel> childQuery(String RNVTN_REIT_REQ_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("RNVTN_REIT_REQ_ID", RNVTN_REIT_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Decorationreit.queryById", params);
	}
}
