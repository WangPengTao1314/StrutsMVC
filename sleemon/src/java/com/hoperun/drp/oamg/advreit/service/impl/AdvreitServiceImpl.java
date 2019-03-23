package com.hoperun.drp.oamg.advreit.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.advreit.model.AdvreitModel;
import com.hoperun.drp.oamg.advreit.service.AdvreitService;
import com.hoperun.sys.model.UserBean;

public class AdvreitServiceImpl extends BaseService implements AdvreitService{

	/**
	 * @param ERP_ADV_REIT_ID
	 * @param obj
	 * @param userBean
	 */
	public void txEdit(String ERP_ADV_REIT_ID, AdvreitModel obj, UserBean userBean){
		
		Map<String, String> params = new HashMap<String, String>();

		params.put("ERP_ADV_REQ_ID", obj.getERP_ADV_REQ_ID());  
		params.put("ERP_ADV_REQ_NO", obj.getERP_ADV_REQ_NO());
		params.put("CHANN_ID", obj.getCHANN_ID());
		params.put("CHANN_NO", obj.getCHANN_NO());  
		params.put("CHANN_NAME", obj.getCHANN_NAME()); 
		params.put("AREA_ID",    obj.getAREA_ID());  
		params.put("AREA_NO",    obj.getAREA_NO()); 
		params.put("AREA_NAME",  obj.getAREA_NAME());  
		params.put("AREA_MANAGE_ID",   obj.getAREA_MANAGE_ID()); 
		params.put("AREA_MANAGE_NAME", obj.getAREA_MANAGE_NAME());
		params.put("CHANN_RANK",       obj.getCHANN_RANK());  
		params.put("ADV_CO_NAME",      obj.getADV_CO_NAME());
		params.put("ADV_CO_CONTACT",   obj.getADV_CO_CONTACT());  
		params.put("ADV_CO_TEL",       obj.getADV_CO_TEL()); 
		params.put("ADV_CONTENT",      obj.getADV_CONTENT());  
		params.put("ADV_ADDR",         obj.getADV_ADDR());  
		params.put("ADV_TOTAL_AMOUNT", obj.getADV_TOTAL_AMOUNT());  
		params.put("HAS_REIM_AMOUNT",  obj.getHAS_REIM_AMOUNT());  
		
		params.put("REIT_REASON", obj.getREIT_REASON());
		params.put("REIT_AMOUNT", obj.getREIT_AMOUNT());
 
		if (StringUtils.isEmpty(ERP_ADV_REIT_ID)) {
			
			String UID = StringUtil.uuid32len();
			params.put("ERP_ADV_REIT_ID", UID);
			String ERP_ADV_REIT_NO =LogicUtil.getBmgz("ERP_ADV_REQ_REIT_NO");	   
			params.put("ERP_ADV_REIT_NO", ERP_ADV_REIT_NO);
			params.put("RNVTN_REQ_NAME", userBean.getXM());
			params.put("RNVTN_REQ_DATE", DateUtil.now());
			
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
			
			this.insert("Advreit.insert", params);
			
			String att_path = loadAtt(UID);
			Map <String, String> attParams = new HashMap <String, String>();
			attParams.put("FROM_BILL_ID", UID);
			attParams.put("ATT_PATH",obj.getREQREITPATH());//图片路径
			if(StringUtils.isEmpty(att_path)){			
				attParams.put("ATT_ID", StringUtil.uuid32len());						
				attParams.put("CREATOR", params.get("CREATOR"));
				attParams.put("CRE_NAME", params.get("CRE_NAME"));
				attParams.put("CRE_TIME", DateUtil.now());//制单时间
				attParams.put("DEL_FLAG", params.get("DEL_FLAG"));
				insert("Att.insertAtt", attParams);
			}
			
		} else {
			params.put("DEL_FLAG","0");
			params.put("UPDATOR", userBean.getXTYHID());
    		params.put("UPD_TIME", DateUtil.now());
			params.put("ERP_ADV_REIT_ID", ERP_ADV_REIT_ID);
			this.update("Advreit.update", params);
			
			Map <String, String> attParamsT = new HashMap <String, String>();
			attParamsT.put("FROM_BILL_ID",obj.getERP_ADV_REIT_ID());
			attParamsT.put("ATT_PATH", obj.getREQREITPATH());//图片路径
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
    	return this.pageQuery("Advreit.pageQuery",
				"Advreit.pageCount", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo){
    	return this.pageQuery("Advreit.pageQueryT",
				"Advreit.pageCountT", params, pageNo);
    }
    
    /**
     * @param ERP_ADV_REQ_ID
     * @return
     */
    public  String  queryReitAmount(String ERP_ADV_REQ_ID){
        return (String) load("Advreit.queryReitAmount", ERP_ADV_REQ_ID);
    }
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String ERP_ADV_REIT_ID, UserBean userBean){
    	
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		params.put("ERP_ADV_REIT_ID", ERP_ADV_REIT_ID);
		return update("Advreit.delete", params);
    }
    
    /**
     * @param ERP_ADV_REIT_ID
     * @return
     */
    public Map<String, String> loadByConfId(String ERP_ADV_REIT_ID) {
    	return (Map<String, String>) load("Advreit.loadByConfId",ERP_ADV_REIT_ID);
	}
	
	 /**
     * @param ERP_ADV_REQ_ID
     * @return
     */
	public Map <String, String> loadPath(String ERP_ADV_REQ_ID){
		 return (Map<String, String>) load("Advreit.loadPath",ERP_ADV_REQ_ID);
	 }
}
