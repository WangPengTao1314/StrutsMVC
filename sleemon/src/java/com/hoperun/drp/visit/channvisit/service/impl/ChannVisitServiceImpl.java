package com.hoperun.drp.visit.channvisit.service.impl;

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
import com.hoperun.drp.visit.channvisit.model.ChannVisitDtlModel;
import com.hoperun.drp.visit.channvisit.model.ChannVisitModel;
import com.hoperun.drp.visit.channvisit.service.ChannVisitService;
import com.hoperun.sys.model.UserBean;

public class ChannVisitServiceImpl extends BaseService implements ChannVisitService {
   
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
	 public IListPage pageQuery(Map <String, String> params, int pageNo) {
		 return this.pageQuery("Channvisit.pageQuery",
					"Channvisit.pageCount", params, pageNo);
	 }
	 
	  /**
      * @param params
      * @param pageNo
      * @return
      */
     public IListPage pageQueryWH(Map <String, String> params, int pageNo){
    	 return this.pageQuery("Channvisit.pageQueryT",
					"Channvisit.pageCountT", params, pageNo);
     }
	 
     /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<ChannVisitDtlModel> childQuery(String CHANN_VISIT_ID){
	    Map params = new HashMap();
        params.put("CHANN_VISIT_ID", CHANN_VISIT_ID);
        return this.findList("Channvisit.queryChildById", params);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public List<ChannVisitDtlModel> childQueryT(String CHANN_VISIT_ID){
	    Map params = new HashMap();
        params.put("CHANN_VISIT_ID", CHANN_VISIT_ID);
        return this.findList("ChannvisitMX.queryChildByIdT", params);
    }
    
    /**
     * @param CHANN_VISIT_ID
     * @param obj
     * @param userBean
     */
    public void txEdit(String CHANN_VISIT_ID, ChannVisitModel obj, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();
		String UID = "";
		params.put("CHANN_VISIT_NO", obj.getCHANN_VISIT_NO());
		params.put("VISIT_DATE",obj.getVISIT_DATE());
	    params.put("CHANN_ID", obj.getCHANN_ID());
	    params.put("CHANN_NO", obj.getCHANN_NO());
		params.put("CHANN_NAME",obj.getCHANN_NAME());
		params.put("TITLE", obj.getTITLE());
		params.put("EME_DEGREE", obj.getEME_DEGREE());
		params.put("APPLY_PERSON", obj.getVISIT_PEOPLE());
		params.put("APPLY_DEP", obj.getAPPLY_DEP());
		params.put("APPLY_DATE", obj.getAPPLY_DATE());
		params.put("VISIT_PEOPLE_ID", userBean.getRYXXID());
		params.put("VISIT_PEOPLE", obj.getVISIT_PEOPLE());
		params.put("VISIT_DATE", obj.getVISIT_DATE());
		params.put("VISIT_TYPE", obj.getVISIT_TYPE());
		params.put("BED_STOCK", obj.getBED_STOCK());
		params.put("MATTRESS_STOCK", obj.getMATTRESS_STOCK());
		params.put("BEDSIDE_STOCK", obj.getBEDSIDE_STOCK());
		params.put("BEDDING_STOCK", obj.getBEDDING_STOCK());
		params.put("OTHER_STOCK", obj.getOTHER_STOCK());
		params.put("TOTAL_STOCK", obj.getTOTAL_STOCK());
		params.put("MONTH_ORDER_NUM", obj.getMONTH_ORDER_NUM());
		params.put("MONTH_ORDER_REALITY_RATE", obj.getMONTH_ORDER_REALITY_RATE());
		params.put("MONTH_ORDER_RATE", obj.getMONTH_ORDER_RATE());
		params.put("SEASON_ORDER_NUM", obj.getSEASON_ORDER_NUM());
		params.put("SEASON_ORDER_REALITY_RATE", obj.getSEASON_ORDER_REALITY_RATE());
		params.put("SEASON_ORDER_RATE", obj.getSEASON_ORDER_RATE());
		params.put("SALES_IMP_PLAN", obj.getSALES_IMP_PLAN());
		params.put("STORE_MONTH_ORDER_NUM", obj.getSTORE_MONTH_ORDER_NUM());
		params.put("STORE_MONTH_ORDER_REALITY_RATE", obj.getSTORE_MONTH_ORDER_REALITY_RATE());  //门店本月实际达成
		params.put("STORE_MONTH_ORDER_RATE", obj.getSTORE_MONTH_ORDER_RATE());
		params.put("STORE_SEASON_ORDER_NUM", obj.getSTORE_SEASON_ORDER_NUM());
		params.put("STORE_SEASON_REALITY_RATE", obj.getSTORE_SEASON_REALITY_RATE());
		params.put("STORE_SEASON_ORDER_RATE", obj.getSTORE_SEASON_ORDER_RATE());
		params.put("STORE_SALES_IMP_PLAN", obj.getSTORE_SALES_IMP_PLAN());
		params.put("SEASON_GOALS", obj.getSEASON_GOALS());
		params.put("CURRENT_REALITY_RATE", obj.getCURRENT_REALITY_RATE());
		params.put("CURRENT_RATE", obj.getCURRENT_RATE());
		params.put("SEASON_IMPROVE_PLAN", obj.getSEASON_IMPROVE_PLAN());
		params.put("CHANN_QUESTION", obj.getCHANN_QUESTION());
		params.put("MAIN_ACTION", obj.getMAIN_ACTION());
		params.put("COMPETITION_INFO", obj.getCOMPETITION_INFO());
		params.put("SUPPORT_DEMAND", obj.getSUPPORT_DEMAND());
		params.put("PROCESS", obj.getPROCESS());
		params.put("REMARK", obj.getREMARK());
		
    	if (StringUtils.isEmpty(CHANN_VISIT_ID)) {
		    UID = StringUtil.uuid32len();
			params.put("CHANN_VISIT_ID", UID);
			String CHANN_VISIT_NO=LogicUtil.getBmgz("ERP_CHANN_VISIT_NO");	
			params.put("CHANN_VISIT_NO",CHANN_VISIT_NO);
			
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("CRE_NAME", userBean.getXM());// 制单人名称
			params.put("CRE_TIME", DateUtil.now());// 制单时间
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID());// 制单机构id
			params.put("ORG_NAME", userBean.getJGMC());// 制单机构名称
			params.put("DEL_FLAG", "0");
			params.put("STATE", "未提交");
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			
			this.insert("Channvisit.insert", params);
			
			String att_path = loadAtt(UID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", UID);
			attParams.put("ATT_PATH",obj.getPIC_PATH());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
    	} else{
    		params.put("UPDATOR", userBean.getXTYHID());
    		params.put("UPD_TIME", DateUtil.now());
    		params.put("CHANN_VISIT_ID", CHANN_VISIT_ID);
    		this.insert("Channvisit.updateById", params);
    		
    		Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",CHANN_VISIT_ID);
			attParamsT.put("ATT_PATH", obj.getPIC_PATH());//图片路径
			update("Att.updateAtt", attParamsT);
    	}
    }
    
    /**
     * @param CHANN_VISIT_ID
     * @param obj
     * @param userBean
     * @param EME_DEGREE
     */
     public void txEditT(String CHANN_VISIT_ID, ChannVisitModel obj, UserBean userBean,String EME_DEGREE){
    	
    	Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();
		String UID = "";
		params.put("CHANN_VISIT_NO", obj.getCHANN_VISIT_NO());
		params.put("VISIT_DATE",obj.getVISIT_DATE());
	    params.put("CHANN_ID", obj.getCHANN_ID());
	    params.put("CHANN_NO", obj.getCHANN_NO());
		params.put("CHANN_NAME",obj.getCHANN_NAME());
		params.put("TITLE", obj.getTITLE());
		params.put("EME_DEGREE", EME_DEGREE);
		params.put("APPLY_PERSON", obj.getAPPLY_PERSON());
		params.put("APPLY_DEP", obj.getAPPLY_DEP());
		params.put("APPLY_DATE", obj.getAPPLY_DATE());
		params.put("VISIT_PEOPLE", obj.getVISIT_PEOPLE());
		params.put("VISIT_DATE", obj.getVISIT_DATE());
		params.put("VISIT_TYPE", obj.getVISIT_TYPE());
		params.put("BED_STOCK", obj.getBED_STOCK());
		params.put("MATTRESS_STOCK", obj.getMATTRESS_STOCK());
		params.put("BEDSIDE_STOCK", obj.getBEDSIDE_STOCK());
		params.put("BEDDING_STOCK", obj.getBEDDING_STOCK());
		params.put("OTHER_STOCK", obj.getOTHER_STOCK());
		params.put("TOTAL_STOCK", obj.getTOTAL_STOCK());
		params.put("MONTH_ORDER_NUM", obj.getMONTH_ORDER_NUM());
		params.put("MONTH_ORDER_REALITY_RATE", obj.getMONTH_ORDER_REALITY_RATE());
		params.put("MONTH_ORDER_RATE", obj.getMONTH_ORDER_RATE());
		params.put("SEASON_ORDER_NUM", obj.getSEASON_ORDER_NUM());
		params.put("SEASON_ORDER_REALITY_RATE", obj.getSEASON_ORDER_REALITY_RATE());
		params.put("SEASON_ORDER_RATE", obj.getSEASON_ORDER_RATE());
		params.put("SALES_IMP_PLAN", obj.getSALES_IMP_PLAN());
		params.put("STORE_MONTH_ORDER_NUM", obj.getSTORE_MONTH_ORDER_NUM());
		params.put("STORE_MONTH_ORDER_REALITY_RATE", obj.getSTORE_MONTH_ORDER_REALITY_RATE());  //门店本月实际达成
		params.put("STORE_MONTH_ORDER_RATE", obj.getSTORE_MONTH_ORDER_RATE());
		params.put("STORE_SEASON_ORDER_NUM", obj.getSTORE_SEASON_ORDER_NUM());
		params.put("STORE_SEASON_REALITY_RATE", obj.getSTORE_SEASON_REALITY_RATE());
		params.put("STORE_SEASON_ORDER_RATE", obj.getSTORE_SEASON_ORDER_RATE());
		params.put("STORE_SALES_IMP_PLAN", obj.getSTORE_SALES_IMP_PLAN());
		params.put("SEASON_GOALS", obj.getSEASON_GOALS());
		params.put("CURRENT_REALITY_RATE", obj.getCURRENT_REALITY_RATE());
		params.put("CURRENT_RATE", obj.getCURRENT_RATE());
		params.put("SEASON_IMPROVE_PLAN", obj.getSEASON_IMPROVE_PLAN());
		params.put("CHANN_QUESTION", obj.getCHANN_QUESTION());
		params.put("MAIN_ACTION", obj.getMAIN_ACTION());
		params.put("COMPETITION_INFO", obj.getCOMPETITION_INFO());
		params.put("SUPPORT_DEMAND", obj.getSUPPORT_DEMAND());
		params.put("PROCESS", obj.getPROCESS());
		params.put("REMARK", obj.getREMARK());
		
    	if (StringUtils.isEmpty(CHANN_VISIT_ID)) {
		    UID = StringUtil.uuid32len();
			params.put("CHANN_VISIT_ID", UID);
			String CHANN_VISIT_NO=LogicUtil.getBmgz("ERP_CHANN_VISIT_NO");	
			params.put("CHANN_VISIT_NO",CHANN_VISIT_NO);
			
			params.put("CREATOR", userBean.getXTYHID());// 制单人ID
			params.put("CRE_NAME", userBean.getXM());// 制单人名称
			params.put("CRE_TIME", DateUtil.now());// 制单时间
			params.put("DEPT_ID", userBean.getBMXXID());// 制单部门ID
			params.put("DEPT_NAME", userBean.getBMMC());// 制单部门名称
			params.put("ORG_ID", userBean.getJGXXID());// 制单机构id
			params.put("ORG_NAME", userBean.getJGMC());// 制单机构名称
			params.put("DEL_FLAG", "0");
			params.put("STATE", "未提交");
			params.put("LEDGER_ID", userBean.getLoginZTXXID()); //帐ID
			params.put("LEDGER_NAME", userBean.getLoginZTMC()); //帐套名称
			
			this.insert("Channvisit.insert", params);
			
			String att_path = loadAtt(UID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", UID);
			attParams.put("ATT_PATH",obj.getPIC_PATH());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
    	} else{
    		params.put("UPDATOR", userBean.getXTYHID());
    		params.put("UPD_TIME", DateUtil.now());
    		params.put("CHANN_VISIT_ID", obj.getCHANN_VISIT_ID());
    		this.insert("Channvisit.updateById", params);
    		
    		Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",CHANN_VISIT_ID);
			attParamsT.put("ATT_PATH", obj.getPIC_PATH());//图片路径
			update("Att.updateAtt", attParamsT);
    	}
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
     * @param CHANN_VISIT_ID
     * @param model
     */
    public void insertChild(String CHANN_VISIT_ID,  ChannVisitDtlModel model){
   	 Map<String, String> paramsT = new HashMap<String, String>();
   	 if(model !=null) {
   		 
	    	     paramsT.put("CHANN_VISIT_DTL_ID",StringUtil.uuid32len());
		    	 paramsT.put("CHANN_VISIT_ID", CHANN_VISIT_ID);
				 paramsT.put("PRO_NAME", model.getPRO_NAME());
				 paramsT.put("MAIN_TYPE",model.getMAIN_TYPE());
				 paramsT.put("SUB_TYPE", model.getSUB_TYPE());
				 paramsT.put("SIT_ANALYSIS",model.getSIT_ANALYSIS());
				 paramsT.put("ACTION_PLAN", model.getACTION_PLAN());
				 paramsT.put("COMPLETE_TIME",model.getCOMPLETE_TIME());
				 paramsT.put("OTHER_INFO", model.getOTHER_INFO());
				 paramsT.put("DEL_FLAG", "0");
				 this.insert("ChannvisitMX.insert", paramsT);
   	          }
         }
   
	/**
	 * @param CHANN_VISIT_DTL_ID
	 * @param model
	 */
   public void updateChild(String CHANN_VISIT_DTL_ID,ChannVisitDtlModel model){
   	
   	Map<String, String> paramsT = new HashMap<String, String>();
  	    if(model !=null) {
  			 paramsT.put("CHANN_VISIT_DTL_ID",CHANN_VISIT_DTL_ID);
	    	 paramsT.put("CHANN_VISIT_ID",  model.getCHANN_VISIT_ID());
	    	 paramsT.put("PRO_NAME", model.getPRO_NAME());
			 paramsT.put("MAIN_TYPE", model.getMAIN_TYPE());
			 paramsT.put("SUB_TYPE", model.getSUB_TYPE());
			 paramsT.put("SIT_ANALYSIS",model.getSIT_ANALYSIS());
			 paramsT.put("ACTION_PLAN", model.getACTION_PLAN());
			 paramsT.put("COMPLETE_TIME",model.getCOMPLETE_TIME());
			 paramsT.put("OTHER_INFO", model.getOTHER_INFO());
			 paramsT.put("DEL_FLAG", "0");
			 this.insert("ChannvisitMX.update", paramsT);
  	     }
     }
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<ChannVisitDtlModel> loadChilds(String CHANN_VISIT_DTL_ID){
    	return findList("ChannvisitMX.loadByIds", CHANN_VISIT_DTL_ID);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo) {
		 return this.pageQuery("Channvisit.pageQueryT",
					"Channvisit.pageCountT", params, pageNo);
	 }
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String CHANN_VISIT_DTL_IDS, String CHANN_VISIT_ID,UserBean userBean){
    	Map <String, String> params = new HashMap <String, String>();
        params.put("CHANN_VISIT_DTL_ID", CHANN_VISIT_DTL_IDS);
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
        update("ChannvisitMX.deleteByIds",params);
    }
    
    /**
     * @param CHANN_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfId(String CHANN_VISIT_ID){
    	return (Map<String, String>) load("Channvisit.loadByConfId",CHANN_VISIT_ID);
    }
    
    /**
     * @param CHANN_VISIT_ID
     * @param userBean
     */
    public void txDelete(String CHANN_VISIT_ID,UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("CHANN_VISIT_ID", CHANN_VISIT_ID);
		update("Channvisit.delete", params);
    }
    
    /**
     * 查询数据字典信息
     * @param DATA_DTL_ID
     * @return
     */
    public String queryPro(String DATA_DTL_ID){
    	return (String) load("Channvisit.queryPro", DATA_DTL_ID);
    }
    
    /**
 	 * 导出excel
 	 * @return
 	 */
 	 public List expertExcelQuery(Map<String,String> params){
 		return findList("Channvisit.expertExcel",params);
 	 }
 	 
 	 /**
 	  * @param params
 	  * @return
 	  */
 	 public List expertExcelQuerySH(Map<String,String> params){
 		return findList("Channvisit.expertExcelSH",params);
 	 }
}
