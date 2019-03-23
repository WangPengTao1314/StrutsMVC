/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseServiceImpl
*/
package com.hoperun.drp.sale.adviseinit.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.adviseinit.model.AdviseinitModel;
import com.hoperun.drp.sale.adviseinit.service.AdviseinitService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public class AdviseinitServiceImpl extends BaseService implements AdviseinitService {

	/**
	 * 新增产品投诉
	 * @param model
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	@Override
	public void txSaveProCmpl(AdviseinitModel model,List<AdviseinitModel> chldModelList, UserBean userBean) {
		Map<String,String> area = new HashMap<String,String>();
		model.setCMPL_ADVS_ID(StringUtil.uuid32len());
		Map<String,String> params = new HashMap<String,String>();
		params.put("CMPL_ADVS_ID", model.getCMPL_ADVS_ID());
		params.put("CMPL_ADVS_NO", LogicUtil.getBmgz("DRP_CMPL_ADVS_NO"));
		params.put("CMPL_ADVS_TITLE", model.getCMPL_ADVS_TITLE());
		params.put("CMPL_ADVS_TYPE", BusinessConsts.PRO_CMPL);
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		params.put("EMG_LVL", model.getEMG_LVL());//紧急程度
		params.put("PROV", model.getPROV());
		
		params.put("ADVS_SOURCE", model.getADVS_SOURCE());
		params.put("CUSTOMER_TEL", model.getCUSTOMER_TEL());
		params.put("CUSTOMER_NAME", model.getCUSTOMER_NAME());
		
		area = selectManager(model.getAREA_ID());
		if (area != null) {
			params.put("AREA_MANAGE_ID", area.get("XTYHID"));
			params.put("AREA_MANAGE_NAME", area.get("YHM"));
		} else {
			params.put("AREA_MANAGE_ID", null);
			params.put("AREA_MANAGE_NAME", null);
		}
		area = selectChief(model.getAREA_ID());
		if (area != null) {
			params.put("AREA_CHIEF_ID", area.get("XTYHID"));
			params.put("AREA_CHIEF_NAME", area.get("YHM"));
		} else {
			params.put("AREA_CHIEF_ID", null);
			params.put("AREA_CHIEF_NAME", null);
		}
		params.put("AREA_NO", model.getAREA_NO());
		params.put("AREA_NAME", model.getAREA_NAME());
		params.put("RAISE_PSON_NAME", model.getRAISE_PSON_NAME());
		params.put("TEL", model.getTEL());
		params.put("STATE", BusinessConsts.STATE_WFK);
		params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
	    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	    
	    params.put("APPOINT_PSON_ID","00");
	    this.insert("Adviseinit.insertProCmpl", params);
	    //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        if (chldModelList != null){
        	for(AdviseinitModel chidModel:chldModelList){
            	Map <String, String> paramsChild = new HashMap <String, String>();
            	paramsChild.put("CMPL_PRD_DTL_ID", StringUtil.uuid32len());
            	paramsChild.put("CMPL_ADVS_ID", model.getCMPL_ADVS_ID());
            	paramsChild.put("PRD_ID", chidModel.getPRD_ID());
            	paramsChild.put("PRD_NO", chidModel.getPRD_NO());
            	paramsChild.put("PRD_NAME", chidModel.getPRD_NAME());
            	paramsChild.put("PRD_SPEC", chidModel.getPRD_SPEC());
            	paramsChild.put("PRD_PROBLEM_TYPE", chidModel.getPRD_PROBLEM_TYPE());
            	paramsChild.put("REMARK", chidModel.getREMARK());
            	paramsChild.put("PRD_ATT", chidModel.getPRD_ATT());
            	paramsChild.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
            	paramsChild.put("USE_TIME", chidModel.getUSE_TIME());
            	paramsChild.put("SHIP_POINT_NO", chidModel.getSHIP_POINT_NO());
            	paramsChild.put("SHIP_POINT_ID", chidModel.getSHIP_POINT_ID());
            	paramsChild.put("SHIP_POINT_NAME", chidModel.getSHIP_POINT_NAME());
            	paramsChild.put("PRDC_DATE", chidModel.getPRDC_DATE());
            	paramsChild.put("BUY_DATE", chidModel.getBUY_DATE());
            	paramsChild.put("PRD_TYPE", chidModel.getPRD_TYPE());
            	paramsChild.put("PRD_NUM", chidModel.getPRD_NUM());
            	addList.add(paramsChild);
            }
            this.batch4Update("Adviseinit.insertProCmplChild", addList);
        }
	}

	/**
	 * 新增服务投诉
	 * @param model
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	@Override
	public void saveSerCmpl(AdviseinitModel model, String CMPL_ADVS_CONTENT,
			UserBean userBean) {
		Map<String,String> area = new HashMap<String,String>();
		Map<String,String> params = new HashMap<String,String>();
		params.put("CMPL_ADVS_ID", StringUtil.uuid32len());
		params.put("CMPL_ADVS_NO", LogicUtil.getBmgz("DRP_CMPL_ADVS_NO"));
		params.put("CMPL_ADVS_TITLE", model.getCMPL_ADVS_TITLE());
		params.put("CMPL_ADVS_TYPE", BusinessConsts.SER_CMPL);
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		params.put("EMG_LVL", model.getEMG_LVL());//紧急程度
		area = selectManager(model.getAREA_ID());
		if (area != null) {
			params.put("AREA_MANAGE_ID", area.get("XTYHID"));
			params.put("AREA_MANAGE_NAME", area.get("YHM"));
		} else {
			params.put("AREA_MANAGE_ID", null);
			params.put("AREA_MANAGE_NAME", null);
		}
		area = selectChief(model.getAREA_ID());
		if (area != null) {
			params.put("AREA_CHIEF_ID", area.get("XTYHID"));
			params.put("AREA_CHIEF_NAME", area.get("YHM"));
		} else {
			params.put("AREA_CHIEF_ID", null);
			params.put("AREA_CHIEF_NAME", null);
		}
		params.put("AREA_NO", model.getAREA_NO());
		params.put("AREA_NAME", model.getAREA_NAME());
		params.put("RAISE_PSON_NAME", model.getRAISE_PSON_NAME());
		params.put("TEL", model.getTEL());
		params.put("CMPL_OBJ", model.getCMPL_OBJ());
		params.put("CMPL_TO_PSON", model.getCMPL_TO_PSON());
		params.put("CMPL_ADVS_CONTENT", CMPL_ADVS_CONTENT);
		params.put("STATE", BusinessConsts.STATE_WFK);
		params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
	    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	    params.put("APPOINT_PSON_ID","00");
	    this.insert("Adviseinit.insertSerCmpl", params);
	}

	/**
	 * 新增建议
	 * @param model
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	@Override
	public void saveAdvsCmpl(AdviseinitModel model, String CMPL_ADVS_CONTENT,
			UserBean userBean) {
		Map<String,String> area = new HashMap<String,String>();
		Map<String,String> params = new HashMap<String,String>();
		params.put("CMPL_ADVS_ID", StringUtil.uuid32len());
		params.put("CMPL_ADVS_NO", LogicUtil.getBmgz("DRP_CMPL_ADVS_NO"));
		params.put("CMPL_ADVS_TITLE", model.getCMPL_ADVS_TITLE());
		params.put("CMPL_ADVS_TYPE", BusinessConsts.ADVS);
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		area = selectManager(model.getAREA_ID());
		if (area != null) {
			params.put("AREA_MANAGE_ID", area.get("XTYHID"));
			params.put("AREA_MANAGE_NAME", area.get("YHM"));
		} else {
			params.put("AREA_MANAGE_ID", null);
			params.put("AREA_MANAGE_NAME", null);
		}
		area = selectChief(model.getAREA_ID());
		if (area != null) {
			params.put("AREA_CHIEF_ID", area.get("XTYHID"));
			params.put("AREA_CHIEF_NAME", area.get("YHM"));
		} else {
			params.put("AREA_CHIEF_ID", null);
			params.put("AREA_CHIEF_NAME", null);
		}
		params.put("AREA_NO", model.getAREA_NO());
		params.put("AREA_NAME", model.getAREA_NAME());
		params.put("RAISE_PSON_NAME", model.getRAISE_PSON_NAME());
		params.put("TEL", model.getTEL());
		params.put("ADVS_TYPE", model.getADVS_TYPE());
		params.put("ADVS_SMMRY", model.getADVS_SMMRY());
		params.put("CMPL_ADVS_CONTENT", CMPL_ADVS_CONTENT);
		params.put("STATE", BusinessConsts.STATE_WFK);
		params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
	    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	    params.put("APPOINT_PSON_ID","00");
	    this.insert("Adviseinit.insertAdvs", params);
	}

	/**
	 * 区域经理查询
	 * @param AREA_ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> selectManager(String AREA_ID) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("AREA_ID", AREA_ID);
		List<Map<String,String>> list = this.findList("Adviseinit.AREA_MANAGE", params);
		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 区域总监查询
	 * @param AREA_ID
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,String> selectChief(String AREA_ID) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("AREA_ID", AREA_ID);
		List<Map<String,String>> list = this.findList("Adviseinit.AREA_CHIEF", params);
		if(null != list && !list.isEmpty()){
			return list.get(0);
		}
		return null;
	}

	public Map<String, String> getCHANNInfo(String CHANN_ID) {
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANN_ID);
		return (Map<String, String>) load("chann.loadById", params);
	}
   
}