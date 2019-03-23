package com.hoperun.drp.oamg.rnvtnreformcheck.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.model.RnvtnreformcheckChildModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.model.RnvtnreformcheckModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.service.RnvtnreformcheckService;
import com.hoperun.sys.model.UserBean;

public class RnvtnreformcheckServiceImpl extends BaseService implements  RnvtnreformcheckService {

	
	 /**
     * @param RNVTN_REFORM_ID
     * @param obj
     * @param userBean
     */
	public void txEdit(String RNVTN_REFORM_ID, RnvtnreformcheckModel obj,UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("RNVTN_CHECK_NO", obj.getRNVTN_CHECK_NO());   //装修验收单编号
		params.put("CHANN_RNVTN_NO", obj.getCHANN_RNVTN_NO());   //装修申请单编号
	    params.put("CHECK_CHARGE",obj.getCHECK_CHARGE());        //验收负责人
	    params.put("CHECK_TIME", obj.getCHECK_TIME());           //验收时间
	    params.put("TERM_NO", obj.getTERM_NO());
		params.put("TERM_NAME",obj.getTERM_NAME());
		params.put("RNVTN_PROP",obj.getRNVTN_PROP());            //装修性质
		params.put("PLAN_SBUSS_DATE",obj.getPLAN_SBUSS_DATE());  //计划开业时间
		params.put("SALE_STORE_NAME",obj.getSALE_STORE_NAME());  //卖场名称
		params.put("LOCAL_TYPE",obj.getLOCAL_TYPE());            //商场位置类型
		params.put("BUSS_SCOPE",obj.getBUSS_SCOPE());            //品牌
		params.put("DESIGNER", obj.getDESIGNER());               //设计师
		params.put("RNVTN_REFORM_REMARK", obj.getRNVTN_REFORM_REMARK());   //整改验收意见
		params.put("PUNISH_REMARK", obj.getPUNISH_REMARK());               //处罚意见
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("RNVTN_REFORM_ID", obj.getRNVTN_REFORM_ID());
		this.update("rnvtnreformcheck.updateById", params);
		
		if(StringUtils.isNotEmpty(obj.getATTMEMT_PATH())){
			params.put("PIC_PATH", obj.getATTMEMT_PATH());//图片路径
			txInsertAtt(params);
		}else {
			params.put("PIC_PATH", obj.getATTMEMT_PATH());//图片路径
			update("Att.updateAtt",params);
		}	
	}
	
	
	/**
	 * 新增/修改附件表
	 * @param params
	 * @return
	 */
	public boolean txInsertAtt(Map<String,String> params){
		String fromBillId = params.get("RNVTN_REFORM_ID");
		
		//查询是否存在
		String fromBillIdT = loadAtt(fromBillId);
		Map <String, String> attParams = new HashMap <String, String>();
		attParams.put("FROM_BILL_ID", fromBillId);
		attParams.put("ATT_PATH", params.get("PIC_PATH"));//图片路径
		if(StringUtils.isEmpty(fromBillIdT)){			
			attParams.put("ATT_ID", StringUtil.uuid32len());						
			attParams.put("CREATOR", params.get("CREATOR"));
			attParams.put("CRE_NAME", params.get("CRE_NAME"));
			attParams.put("CRE_TIME", DateUtil.now());//制单时间
			attParams.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			insert("Att.insertAtt", attParams);
		}else{
			update("Att.updateAtt", attParams);
		}
		
        return true;
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
	 * 根据整改单ID获取验收单ID
	 * Description :.
	 * @param params the RNVTN_REFORM_ID
     * @return the i String
	 */
	public  String  queryCheckByReform(String RNVTN_REFORM_ID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
        return (String) load("rnvtnreformcheck.queryCheckByReform", params);
    }
	
    /**
     * @param RNVTN_CHECK_NO
     * @return
     */
	public  String  queryCheckById(String RNVTN_CHECK_NO){
		Map<String, String> params = new HashMap<String, String>();
        params.put("RNVTN_CHECK_NO", RNVTN_CHECK_NO);
        return (String) load("rnvtnreformcheck.queryCheckById", params);
	}
	
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("rnvtnreformcheck.pageQuery","rnvtnreformcheck.pageCount", params, pageNo);
    }
    
    /**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo){
    	return this.pageQuery("rnvtnreformcheck.pageQueryT","rnvtnreformcheck.pageCountT", params, pageNo);
    }
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<RnvtnreformcheckChildModel> childQuery(String RNVTN_REFORM_ID){
    	
	    Map params = new HashMap();
        params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
        return this.findList("rnvtnreformcheck.queryChildById", params);
    }
    
    /**
     * @param RNVTN_REFORM_ID
     * @return
     */
	public Map<String, String> loadByConfId(String RNVTN_REFORM_ID) {
		return (Map<String, String>) load("rnvtnreformcheck.loadByConfId",RNVTN_REFORM_ID);
	}
	
	 /**
     * @param RNVTN_REFORM_ID
     * @param checkId
     * @return
     */
	public Map <String, String> loadByConfIdTt(String RNVTN_REFORM_ID,String checkId){
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("RNVTN_REFORM_ID", RNVTN_REFORM_ID);
		params.put("RNVTN_CHECK_ID",  checkId);
		return (Map<String, String>) load("rnvtnreformcheck.loadByConfIdTt",params);
	}
	
	 /**
     * @param RNVTN_REFORM_ID
     * @return
     */
	public Map <String, String> loadByConfIdT(String RNVTN_REFORM_ID){
		return (Map<String, String>) load("rnvtnreformcheck.loadByConfIdT",RNVTN_REFORM_ID);
	}
	
	public void updateState(String RNVTN_REFORM_ID, RnvtnreformModel obj,
			UserBean userBean, String STATE) {
		Map params = new HashMap();
		params.put("STATE",STATE);
		params.put("RNVTN_REFORM_ID",RNVTN_REFORM_ID);
		this.update("rnvtnreformcheck.updateState",params);
	}
} 
