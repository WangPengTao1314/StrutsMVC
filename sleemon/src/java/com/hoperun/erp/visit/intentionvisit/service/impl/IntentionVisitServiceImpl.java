package com.hoperun.erp.visit.intentionvisit.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.visit.intentionvisit.model.IntentionVisitModel;
import com.hoperun.erp.visit.intentionvisit.service.IntentionVisitService;
import com.hoperun.sys.model.UserBean;

public class IntentionVisitServiceImpl extends BaseService implements IntentionVisitService{

	/**
	 * @param INTE_CHANN_ID
	 * @param obj
	 * @param userBean
	 */
    public void txEdit(String INTE_CHANN_ID, IntentionVisitModel model, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("APPLY_PERSON_ID", model.getAPPLY_PERSON_ID());
    	params.put("APPLY_PERSON_NAME", model.getAPPLY_PERSON_NAME());
    	params.put("APPLY_DEP", model.getAPPLY_DEP());
    	params.put("APPLY_DATE", model.getAPPLY_DATE());
    	params.put("CITY", model.getCITY());
    	params.put("CITY_TYPE", model.getCITY_TYPE());
    	params.put("AREA_CODE", model.getAREA_CODE());
    	params.put("CITY_LEVEL", model.getCITY_LEVEL());
    	params.put("INTE_CHANN_NAME", model.getINTE_CHANN_NAME());
    	params.put("INTE_CUSTOMER_NAME", model.getINTE_CUSTOMER_NAME());
    	params.put("TEL", model.getTEL());
    	params.put("SEX", model.getSEX());
    	params.put("AGE", model.getAGE());
    	params.put("EDUCATION", model.getEDUCATION());
    	params.put("ADDRESS", model.getADDRESS());
    	params.put("EXIST_STORE_NAME", model.getEXIST_STORE_NAME());
		params.put("EXIST_STORE_NAME", model.getEXIST_STORE_NAME());
		params.put("EXIST_STORE_ADDR", model.getEXIST_STORE_ADDR());
		params.put("EXIST_STORE_AREA", model.getEXIST_STORE_AREA());
		params.put("EXIST_STORE_RANGE",model.getEXIST_STORE_RANGE());
		params.put("EXIST_STORE_COMPETITION", model.getEXIST_STORE_COMPETITION());
		params.put("NEW_STORE_NAME" , model.getNEW_STORE_NAME());
		params.put("NEW_STORE_ADDR" , model.getNEW_STORE_ADDR());
		params.put("NEW_STORE_AREA" , model.getNEW_STORE_AREA());
		params.put("NEW_STORE_RANGE", model.getNEW_STORE_RANGE());
		params.put("NEW_STORE_DATE", model.getNEW_STORE_DATE());
		params.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());
		params.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());
		params.put("AREA_SER_FLG", model.getAREA_SER_FLG());
		params.put("AREA_SER_ID", model.getAREA_SER_ID());
		params.put("AREA_SER_NO", model.getAREA_SER_NO());
		params.put("AREA_SER_NAME", model.getAREA_SER_NAME());
		params.put("DOCUMENTS", model.getDOCUMENTS());
		params.put("VISIT_TYPE", model.getVISIT_TYPE());
		params.put("VISIT_REMARK", model.getVISIT_REMARK());
		params.put("BUSS_SCOPE", model.getBUSS_SCOPE());
		params.put("STORE_NAME", model.getSTORE_NAME());
		params.put("INTE_STORE_VSION", model.getINTE_STORE_VSION());
		params.put("INTE_STORE_AREA", model.getINTE_STORE_AREA());
		params.put("INTE_STORE_DATE", model.getINTE_STORE_DATE());
		params.put("IS_CONTRACT", model.getIS_CONTRACT());
		params.put("IS_PAY_INTEAMOUNT", model.getIS_PAY_INTEAMOUNT());
		params.put("IS_FIRST_SENTPDT", model.getIS_FIRST_SENTPDT());
		params.put("CHANN_REMARK", model.getCHANN_REMARK());
		params.put("SOLUTION", model.getSOLUTION());
		params.put("COMPETITION_INFO", model.getCOMPETITION_INFO());
	    params.put("SUPPORT_DEMAND", model.getSUPPORT_DEMAND());
		params.put("ADVANTAGES", model.getADVANTAGES());
        
    	if (StringUtils.isEmpty(INTE_CHANN_ID)) {
    		INTE_CHANN_ID = StringUtil.uuid32len();
			params.put("INTE_CHANN_ID", INTE_CHANN_ID);
			String EXPAND_VISIT_NO = LogicUtil.getBmgz("ERP_INTENTION_CHANN_NO");	
			params.put("INTE_CHANN_NO",EXPAND_VISIT_NO);
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
			this.insert("Intentionvisit.insert", params);
			
    	} else{
    		params.put("UPD_NAME",userBean.getXM());
    		params.put("UPDATOR", userBean.getXTYHID());
    		params.put("UPD_TIME", DateUtil.now());
    		params.put("INTE_CHANN_ID", INTE_CHANN_ID);
    		this.insert("Intentionvisit.updateById", params);
    	}
    	
    	//图片
    	String ATT_PATH = loadAtt(INTE_CHANN_ID);
		Map <String, String> attParams = new HashMap <String, String>();
		attParams.put("FROM_BILL_ID", INTE_CHANN_ID);
		attParams.put("ATT_PATH",model.getDOC_PATH());//图片路径
		if(StringUtils.isEmpty(ATT_PATH)){			
			attParams.put("ATT_ID", StringUtil.uuid32len());						
			attParams.put("CREATOR", params.get("CREATOR"));
			attParams.put("CRE_NAME", params.get("CRE_NAME"));
			attParams.put("CRE_TIME", DateUtil.now());//制单时间
			attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
			insert("Att.insertAtt", attParams);
		}else{
			update("Att.updateAtt", attParams);
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
		return this.pageQuery("Intentionvisit.pageQuery",
				"Intentionvisit.pageCount", params, pageNo);
    }
    
  
    
    /**
     * @param INTE_CHANN_ID
     * @param userBean
     */
    public void txDelete(String INTE_CHANN_ID,UserBean userBean){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("INTE_CHANN_ID", INTE_CHANN_ID);
		update("Intentionvisit.delete", params);
    }
    
    /**
     * @param INTE_CHANN_ID
     * @return
     */
    public Map<String,String> loadById(String INTE_CHANN_ID){
    	return (Map<String, String>) load("Intentionvisit.loadById",INTE_CHANN_ID);
    }
    
   
    
}
