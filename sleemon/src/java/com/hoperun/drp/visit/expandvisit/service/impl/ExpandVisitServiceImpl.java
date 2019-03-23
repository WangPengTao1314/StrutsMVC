package com.hoperun.drp.visit.expandvisit.service.impl;

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
import com.hoperun.drp.visit.expandvisit.model.ExpandVisitDtlModel;
import com.hoperun.drp.visit.expandvisit.model.ExpandVisitModel;
import com.hoperun.drp.visit.expandvisit.service.ExpandVisitService;
import com.hoperun.sys.model.UserBean;

public class ExpandVisitServiceImpl extends BaseService implements ExpandVisitService{

	/**
	 * @param EXPAND_VISIT_ID
	 * @param obj
	 * @param userBean
	 */
    public void txEdit(String EXPAND_VISIT_ID, ExpandVisitModel obj, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();
		Map<String, String> paramsT = new HashMap<String, String>();
		String UID = "";
		params.put("EXPAND_VISIT_NO", obj.getEXPAND_VISIT_NO());
		params.put("TITLE", obj.getTITLE());
		params.put("EME_DEGREE", obj.getEME_DEGREE());
		params.put("APPLY_PERSON", obj.getAPPLY_PERSON());
		params.put("APPLY_DEP", obj.getAPPLY_DEP());
		params.put("APPLY_DATE", obj.getAPPLY_DATE());
		params.put("VISIT_PEOPLE_ID", userBean.getRYXXID());
		params.put("VISIT_PEOPLE", obj.getAPPLY_PERSON());
		params.put("VISIT_DATE", obj.getVISIT_DATE());
		params.put("EXIST_STORE_NAME", obj.getEXIST_STORE_NAME());
		params.put("EXIST_STORE_ADDR", obj.getEXIST_STORE_ADDR());
		params.put("EXIST_STORE_AREA", obj.getEXIST_STORE_AREA());
		params.put("EXIST_STORE_RANGE",obj.getEXIST_STORE_RANGE());
		params.put("EXIST_STORE_COMPETITION", obj.getEXIST_STORE_COMPETITION());
		
		params.put("NEW_STORE_NAME" , obj.getNEW_STORE_NAME());
		params.put("NEW_STORE_ADDR" , obj.getNEW_STORE_ADDR());
		params.put("NEW_STORE_AREA" , obj.getNEW_STORE_AREA());
		params.put("NEW_STORE_RANGE", obj.getNEW_STORE_RANGE());
		params.put("NEW_STORE_DATE", obj.getNEW_STORE_DATE());
		
	    params.put("CHANN_NAME", obj.getCHANN_NAME());
	    params.put("BUSS_SCOPE", obj.getBUSS_SCOPE());
	    params.put("STORE_NAME", obj.getSTORE_NAME());
	    params.put("PURPOSE_STORE_NAME", obj.getPURPOSE_STORE_NAME());
	    params.put("TEL", obj.getTEL());
	    
		params.put("CHANN_REMARK", obj.getCHANN_REMARK());
		params.put("SOLUTION", obj.getSOLUTION());
		params.put("COMPETITION_INFO",obj.getCOMPETITION_INFO());
		params.put("SUPPORT_DEMAND", obj.getSUPPORT_DEMAND());
		params.put("PROCESS", obj.getPROCESS());
		params.put("REMARK", obj.getREMARK());
		params.put("VISIT_TYPE", obj.getVISIT_TYPE());
		params.put("ADVANTAGES", obj.getADVANTAGES());
        
    	if (StringUtils.isEmpty(EXPAND_VISIT_ID)) {
		    UID = StringUtil.uuid32len();
			params.put("EXPAND_VISIT_ID", UID);
			String EXPAND_VISIT_NO=LogicUtil.getBmgz("ERP_EXPAND_VISIT_NO");	
			params.put("EXPAND_VISIT_NO",EXPAND_VISIT_NO);
			
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
			
			this.insert("Expandvisit.insert", params);
			
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
    		params.put("EXPAND_VISIT_ID", obj.getEXPAND_VISIT_ID());
    		this.insert("Expandvisit.updateById", params);
    		
    		Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",EXPAND_VISIT_ID);
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
		return this.pageQuery("Expandvisit.pageQuery",
				"Expandvisit.pageCount", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryWH(Map <String, String> params, int pageNo){
    	return this.pageQuery("Expandvisit.pageQueryT",
				"Expandvisit.pageCountT", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo){
		return this.pageQuery("Expandvisit.pageQueryT",
				"Expandvisit.pageCountT", params, pageNo);
    }
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<ExpandVisitDtlModel> loadChilds(String EXPAND_VISIT_DTL_ID){
    	return findList("ExpandvisitMX.loadByIds", EXPAND_VISIT_DTL_ID);
    }
    
    /**
     * @param EXPAND_VISIT_ID
     * @param userBean
     */
    public void txDelete(String EXPAND_VISIT_ID,UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("EXPAND_VISIT_ID", EXPAND_VISIT_ID);
		update("Expandvisit.delete", params);
    }
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfId(String EXPAND_VISIT_ID){
    	return (Map<String, String>) load("Expandvisit.loadByConfId",EXPAND_VISIT_ID);
    }
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfIdT(String EXPAND_VISIT_ID){
    	return (Map<String, String>) load("Expandvisit.loadByConfIdT",EXPAND_VISIT_ID);
    }
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<ExpandVisitDtlModel> childQuery(String EXPAND_VISIT_ID){
    	    Map params = new HashMap();
	        params.put("EXPAND_VISIT_ID", EXPAND_VISIT_ID);
	        return this.findList("Expandvisit.queryChildById", params);
    }
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public List<ExpandVisitDtlModel> childQueryT(String EXPAND_VISIT_ID){
	    Map params = new HashMap();
        params.put("EXPAND_VISIT_ID", EXPAND_VISIT_ID);
        return this.findList("ExpandvisitMX.queryChildByIdT", params);
    } 
    
    /**
     * @param EXPAND_VISIT_ID
     * @param model
     */
    public void insertChild(String EXPAND_VISIT_ID,  ExpandVisitDtlModel model){
    	 Map<String, String> paramsT = new HashMap<String, String>();
    	 if(model !=null) {
    		 
	    	     paramsT.put("EXPAND_VISIT_DTL_ID",StringUtil.uuid32len());
		    	 paramsT.put("EXPAND_VISIT_ID", EXPAND_VISIT_ID);
				 paramsT.put("STORE_NAME", model.getSTORE_NAME());
				 paramsT.put("SOFTWARE_LAYER", model.getSOFTWARE_LAYER());
				 paramsT.put("LOCATION", model.getLOCATION());
				 paramsT.put("POPULARITY",model.getPOPULARITY());
				 paramsT.put("OPENNING_TIME", model.getOPENNING_TIME());
				 paramsT.put("INVESTMENT_TIME",model.getINVESTMENT_TIME());
				 paramsT.put("OTHER_INFO", model.getOTHER_INFO());
				 paramsT.put("STORE_MNG_NAME", model.getSTORE_MNG_NAME());
				 paramsT.put("STORE_MNG_TEL",model.getSTORE_MNG_TEL());
				 paramsT.put("STORE_MNG_EMAIL", model.getSTORE_MNG_EMAIL());
				 paramsT.put("INVESTMENT_MNG_NAME", model.getINVESTMENT_MNG_NAME());
				 paramsT.put("INVESTMENT_MNG_TEL", model.getINVESTMENT_MNG_TEL());
				 paramsT.put("INVESTMENT_MNG_EMAIL", model.getINVESTMENT_MNG_EMAIL());
				 paramsT.put("CUST_NAME", model.getCUST_NAME());
				 paramsT.put("CUST_STATE", model.getCUST_STATE());
				 paramsT.put("CUST_FUNDS", model.getCUST_FUNDS());
				 paramsT.put("BUSINESS_PHI", model.getBUSINESS_PHI());
				 paramsT.put("CUST_OTHER_INFO",model.getCUST_OTHER_INFO());
				 paramsT.put("CUST_INTENTION", model.getCUST_INTENTION());
				 paramsT.put("FOLLOW_WAY", model.getFOLLOW_WAY());
				 paramsT.put("DEL_FLAG", "0");
				 this.insert("Expandvisit.insertChild", paramsT);
    	  }
    }
    
    /**
     * @param EXPAND_VISIT_DTL_ID
     * @param model
     */
    public void updateChild(String EXPAND_VISIT_DTL_ID,ExpandVisitDtlModel model){
    	Map<String, String> paramsT = new HashMap<String, String>();
   	    if(model !=null) {
   			 paramsT.put("EXPAND_VISIT_DTL_ID",EXPAND_VISIT_DTL_ID);
	    	 paramsT.put("EXPAND_VISIT_ID",  model.getEXPAND_VISIT_ID());
			 paramsT.put("STORE_NAME",  model.getSTORE_NAME());
			 paramsT.put("SOFTWARE_LAYER",  model.getSOFTWARE_LAYER());
			 paramsT.put("LOCATION",  model.getLOCATION());
			 paramsT.put("POPULARITY",  model.getPOPULARITY());
			 paramsT.put("OPENNING_TIME",  model.getOPENNING_TIME());
			 paramsT.put("INVESTMENT_TIME",  model.getINVESTMENT_TIME());
			 paramsT.put("OTHER_INFO",  model.getOTHER_INFO());
			 paramsT.put("STORE_MNG_NAME",  model.getSTORE_MNG_NAME());
			 paramsT.put("STORE_MNG_TEL", model.getSTORE_MNG_TEL());
			 paramsT.put("STORE_MNG_EMAIL",  model.getSTORE_MNG_EMAIL());
			 paramsT.put("INVESTMENT_MNG_NAME", model.getINVESTMENT_MNG_NAME());
			 paramsT.put("INVESTMENT_MNG_TEL",  model.getINVESTMENT_MNG_TEL());
			 paramsT.put("INVESTMENT_MNG_EMAIL",  model.getINVESTMENT_MNG_EMAIL());
			 paramsT.put("CUST_NAME",  model.getCUST_NAME());
			 paramsT.put("CUST_STATE", model.getCUST_STATE());
			 paramsT.put("CUST_FUNDS", model.getCUST_FUNDS());
			 paramsT.put("BUSINESS_PHI",  model.getBUSINESS_PHI());
			 paramsT.put("CUST_OTHER_INFO",  model.getCUST_OTHER_INFO());
			 paramsT.put("CUST_INTENTION", model.getCUST_INTENTION());
			 paramsT.put("FOLLOW_WAY",  model.getFOLLOW_WAY());
			 this.insert("Expandvisit.updateChild", paramsT);
   	     }
      }

    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String EXPAND_VISIT_DTL_IDS, String EXPAND_VISIT_ID,UserBean userBean){
    	Map <String, String> params = new HashMap <String, String>();
        params.put("EXPAND_VISIT_DTL_ID", EXPAND_VISIT_DTL_IDS);
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
        update("ExpandvisitMX.deleteByIds",params);
    }
    
    /**
     * 查询数据字典信息
     * @param DATA_DTL_ID
     * @return
     */
    public String queryPro(String DATA_DTL_ID){
    	 return (String) load("Expandvisit.queryPro", DATA_DTL_ID);
    }
    
    /**
	 * 导出excel
	 * @return
	 */
	public List<ExpandVisitModel> expertExcelQuery(Map<String,String> params){
		return findList("Expandvisit.expertExcel",params);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public List<ExpandVisitModel> expertExcelQuerySH(Map<String,String> params){
		return findList("Expandvisit.expertExcelSH",params);
	}
}
