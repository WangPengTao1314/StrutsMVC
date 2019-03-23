package com.hoperun.drp.visit.storevisit.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.hoperun.drp.visit.storevisit.model.StoreVisitDtlModel;
import com.hoperun.drp.visit.storevisit.model.StoreVisitModel;
import com.hoperun.drp.visit.storevisit.service.StoreVisitService;
import com.hoperun.sys.model.UserBean;

public class StoreVisitServiceImpl extends BaseService implements StoreVisitService {

	/**
	 * @param STORE_VISIT_ID
	 * @param obj
	 * @param userBean
	 */
    public void txEdit(String STORE_VISIT_ID, StoreVisitModel obj, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();
		String UID = "";
		params.put("STORE_VISIT_NO", obj.getSTORE_VISIT_NO());
		params.put("TITLE", obj.getTITLE());
		params.put("EME_DEGREE", obj.getEME_DEGREE());
		params.put("APPLY_PERSON", obj.getAPPLY_PERSON());
		params.put("APPLY_DEP",obj.getAPPLY_DEP());
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
   	    Date ADATE = null;
   	    Date VDATE = null;
   	    Date EXEDATE = null;
   	    Date ACTIONDATE = null;
		try {
			ADATE   = sdf.parse(obj.getAPPLY_DATE());
			VDATE   = sdf.parse(obj.getVISIT_DATE());
			EXEDATE = sdf.parse(obj.getEXECUTE_ACTION_DATE());
			ACTIONDATE = sdf.parse(obj.getACTION_PLAN_DATE());
		} catch (Exception e) {
			e.printStackTrace();
		}  
   	    String APPLY_DATE = sdf.format(ADATE);
		params.put("APPLY_DATE", APPLY_DATE);
		params.put("VISIT_PEOPLE_ID", userBean.getRYXXID());
		params.put("VISIT_PEOPLE", obj.getAPPLY_PERSON());
		String VISIT_DATE = sdf.format(VDATE);
		params.put("VISIT_DATE", VISIT_DATE);
		String EXECUTE_ACTION_DATE = sdf.format(EXEDATE);
		params.put("EXECUTE_ACTION_DATE", EXECUTE_ACTION_DATE);
		String ACTION_PLAN_DATE = sdf.format(ACTIONDATE);
		params.put("ACTION_PLAN_DATE",  ACTION_PLAN_DATE);
		params.put("CITY", obj.getCITY());
		params.put("TERM_ID", obj.getTERM_ID());
		params.put("TERM_NO", obj.getTERM_NO());
		params.put("CHANN_ID", obj.getCHANN_ID());
		params.put("CHANN_NO", obj.getCHANN_NO());
		params.put("CHANN_NAME", obj.getCHANN_NAME());
		params.put("STORE_NAME", obj.getSTORE_NAME());
		params.put("STORE_STROKES", obj.getSTORE_STROKES());
		params.put("LIGHT_BOX", obj.getLIGHT_BOX());
		params.put("GROUND", obj.getGROUND());
		params.put("PRODUCTS", obj.getPRODUCTS());
		params.put("PROPERTIES", obj.getPROPERTIES());
		params.put("SOFT_DECORATION", obj.getSOFT_DECORATION());
		params.put("BLANKETS", obj.getBLANKETS());
		params.put("TELEVISION", obj.getTELEVISION());
		params.put("LIGHT_LAMP", obj.getLIGHT_LAMP());
		params.put("MATERIALS", obj.getMATERIALS());
		params.put("WATER_MACHINE", obj.getWATER_MACHINE());
		params.put("FIGURE", obj.getFIGURE());
		params.put("MANNER", obj.getMANNER());
		params.put("TECHNICAL", obj.getTECHNICAL());
		params.put("EXECUTE_ACTION_TOPIC", obj.getEXECUTE_ACTION_TOPIC());
		
		params.put("EXECUTE_ACTION_ADDR", obj.getEXECUTE_ACTION_ADDR());
		params.put("ACTION_PLAN", obj.getACTION_PLAN());
		params.put("ACTION_REALITY_RATE", obj.getACTION_REALITY_RATE());
		params.put("ACTION_RATE", obj.getACTION_RATE());
		params.put("ACTION_RIGHT", obj.getACTION_RIGHT());
		params.put("ACTION_BAD", obj.getACTION_BAD());
		params.put("MONTH_ORDER_NUM", obj.getMONTH_ORDER_NUM());       //本月计划
		params.put("MONTH_REALITY_RATE", obj.getMONTH_REALITY_RATE()); //实际达成
		params.put("MONTH_RATE", obj.getMONTH_RATE());
		params.put("SEASON_ORDER_NUM", obj.getSEASON_ORDER_NUM());     //本季目标
		params.put("SEASON_REALITY_RATE", obj.getSEASON_REALITY_RATE());
		params.put("SEASON_RATE", obj.getSEASON_RATE());
		params.put("EVALUATION_MONTH", obj.getEVALUATION_MONTH());
		params.put("EVALUATION_SEASON", obj.getEVALUATION_SEASON());
		params.put("ACTION_PLAN_TOPIC", obj.getACTION_PLAN_TOPIC());
		
		params.put("ACTION_PLAN_ADDR",  obj.getACTION_PLAN_ADDR());
		params.put("EXPECTED_GOAL", obj.getEXPECTED_GOAL());
		params.put("COMPETITION_INFO", obj.getCOMPETITION_INFO());
		params.put("SUPPORT_DEMAND", obj.getSUPPORT_DEMAND());
		params.put("PROCESS", obj.getPROCESS());
		params.put("REMARK", obj.getREMARK());
		params.put("VISIT_TYPE", obj.getVISIT_TYPE());
		params.put("ACTUAL_INVESTMENT", obj.getACTUAL_INVESTMENT());
		params.put("FORECAST_INVESTMENT", obj.getFORECAST_INVESTMENT());
		
    	if (StringUtils.isEmpty(STORE_VISIT_ID)) {
		    UID = StringUtil.uuid32len();
			params.put("STORE_VISIT_ID", UID);
			String STORE_VISIT_NO=LogicUtil.getBmgz("ERP_STORE_VISIT_NO");	
			params.put("STORE_VISIT_NO",STORE_VISIT_NO);
			
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
			
			this.insert("Storevisit.insert", params);
			
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
    		params.put("STORE_VISIT_ID", obj.getSTORE_VISIT_ID());
    		this.insert("Storevisit.updateById", params);
    		
    		Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",STORE_VISIT_ID);
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
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("Storevisit.pageQuery",
				"Storevisit.pageCount", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryWH(Map <String, String> params, int pageNo){
    	return this.pageQuery("Storevisit.pageQueryT",
				"Storevisit.pageCountT", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo){
    	return this.pageQuery("Storevisit.pageQueryT",
				"Storevisit.pageCountT", params, pageNo);
    }
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfId(String STORE_VISIT_ID){
    	return (Map<String, String>) load("Storevisit.loadByConfId",STORE_VISIT_ID);
    }
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfIdT(String STORE_VISIT_ID){
    	return (Map<String, String>) load("Storevisit.loadByConfIdT",STORE_VISIT_ID);
    }
    
    /**
     * @param STORE_VISIT_ID
     * @param userBean
     */
    public void txDelete(String STORE_VISIT_ID,UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("STORE_VISIT_ID", STORE_VISIT_ID);
		update("Storevisit.delete", params);
    }
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<StoreVisitDtlModel> childQuery(String STORE_VISIT_ID){
    	    Map params = new HashMap();
	        params.put("STORE_VISIT_ID", STORE_VISIT_ID);
	        return this.findList("Storevisit.queryChildById", params);
    }
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public List<StoreVisitDtlModel> childQueryT(String STORE_VISIT_ID){
	    Map params = new HashMap();
        params.put("STORE_VISIT_ID", STORE_VISIT_ID);
        return this.findList("StorevisitMX.queryChildByIdT", params);
    }
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<StoreVisitDtlModel> loadChilds(String STORE_VISIT_DTL_ID){
    	return findList("StorevisitMX.loadByIds", STORE_VISIT_DTL_ID);
    }
    
    /**
     * @param STORE_VISIT_ID
     * @param model
     */
    public void insertChild(String STORE_VISIT_ID,  StoreVisitDtlModel model){
   	 Map<String, String> paramsT = new HashMap<String, String>();
   	 if(model !=null) {
   		 
	    	     paramsT.put("STORE_VISIT_DTL_ID",StringUtil.uuid32len());
		    	 paramsT.put("STORE_VISIT_ID", STORE_VISIT_ID);
				 paramsT.put("PRO_NAME", model.getPRO_NAME());
				 paramsT.put("MAIN_TYPE", model.getMAIN_TYPE());
				 paramsT.put("SIT_ANALYSIS",model.getSIT_ANALYSIS());
				 paramsT.put("ACTION_PLAN",model.getACTION_PLAN());
				 paramsT.put("COMPLETE_TIME", model.getCOMPLETE_TIME());
				 paramsT.put("OTHER_INFO", model.getOTHER_INFO());
				 paramsT.put("DEL_FLAG", "0");
				 this.insert("StorevisitMX.insert", paramsT);
   	  }
   }
    
	/**
	 * @param STORE_VISIT_DTL_ID
	 * @param model
	 */
   public void updateChild(String STORE_VISIT_DTL_ID,StoreVisitDtlModel model){
   	
   	Map<String, String> paramsT = new HashMap<String, String>();
  	    if(model !=null) {
  			 paramsT.put("STORE_VISIT_DTL_ID",STORE_VISIT_DTL_ID);
  			 paramsT.put("STORE_VISIT_ID", model.getSTORE_VISIT_ID());
			 paramsT.put("PRO_NAME", model.getPRO_NAME());
			 paramsT.put("MAIN_TYPE", model.getMAIN_TYPE());
			 paramsT.put("SIT_ANALYSIS",model.getSIT_ANALYSIS());
			 paramsT.put("ACTION_PLAN",model.getACTION_PLAN());
			 paramsT.put("COMPLETE_TIME", model.getCOMPLETE_TIME());
			 paramsT.put("OTHER_INFO", model.getOTHER_INFO());
			 this.insert("StorevisitMX.update", paramsT);
  	     }
     }
   
   /**
    * 子表的批量删除
    * Description :.
    * @param sjzdmxids the sjzdmxids
    * @param userBean the user bean
    */
   public void txBatch4DeleteChild(String STORE_VISIT_DTL_IDS, String STORE_VISIT_ID,UserBean userBean){
       Map <String, String> params = new HashMap <String, String>();
       params.put("STORE_VISIT_DTL_ID", STORE_VISIT_DTL_IDS);
       params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
       update("StorevisitMX.deleteByIds",params);
   }
   
   /**
    * 查询数据字典信息
    * @param DATA_DTL_ID
    * @return
    */
   public String queryPro(String DATA_DTL_ID){
	   return (String) load("Storevisit.queryPro", DATA_DTL_ID);
   }
   
   /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params){
		return findList("Storevisit.expertExcel",params);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public List expertExcelQuerySH(Map<String,String> params){
		return findList("Storevisit.expertExcelSH",params);
	}
}
