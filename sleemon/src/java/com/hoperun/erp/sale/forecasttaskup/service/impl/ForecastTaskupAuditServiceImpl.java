package com.hoperun.erp.sale.forecasttaskup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.forecasttaskup.model.AdvcRptChannDtl;
import com.hoperun.erp.sale.forecasttaskup.service.ForecastTaskupAuditService;

public class ForecastTaskupAuditServiceImpl extends BaseService implements ForecastTaskupAuditService{
	
	 /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("ForecastTaskupAudit.pageQuery",
				"ForecastTaskupAudit.pageCount", params, pageNo);
	}
	
	/**
	 * 查询上报货品
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,String>> childQuery(String RPT_JOB_CHANN_ID){
		return this.findList("ForecastTaskupAudit.queryJobPrd", RPT_JOB_CHANN_ID);
	}
	
	/***
	 * @param RPT_JOB_CHANN_ID
	 */
	public void txSaveChild(List<AdvcRptChannDtl> childList){
		List<Map<String,String>> updateList = new ArrayList<Map<String,String>>();
		for(AdvcRptChannDtl model : childList){
			String ADVC_RPT_CHANN_DTL_ID = model.getADVC_RPT_CHANN_DTL_ID();
			Map<String,String> map = new HashMap<String,String>();
			map.put("SHIP_POINT_ID", model.getSHIP_POINT_ID());
			map.put("SHIP_POINT_NAME",model.getSHIP_POINT_NAME());
			map.put("ADVC_RPT_CHANN_DTL_ID",ADVC_RPT_CHANN_DTL_ID);
			updateList.add(map);
		}
		if(!updateList.isEmpty()){
			this.batch4Update("ForecastTaskupAudit.updateRptChannDtl", updateList);
		}
	}
	
	/***
	 * @param RPT_JOB_CHANN_ID
	 */
	public void txAudit(String RPT_JOB_CHANN_ID){
		Map<String,String> map = new HashMap<String,String>();
		map.put("RPT_JOB_CHANN_ID", RPT_JOB_CHANN_ID);
		map.put("STATE", "审核通过");
		this.update("ForecastTaskup.updateRptChann", map);
	}
	
	/***
	 * @param RPT_JOB_CHANN_ID
	 */
	public void txReAudit(String RPT_JOB_CHANN_ID){
		Map<String,String> map = new HashMap<String,String>();
		map.put("RPT_JOB_CHANN_ID", RPT_JOB_CHANN_ID);
		map.put("STATE", "提交");
		this.update("ForecastTaskup.updateRptChann", map);
	}
   
	/**
	 * @param RPT_JOB_CHANN_ID
	 * @return
	 */
	public Map <String, String> getTotalInfo(String RPT_JOB_CHANN_ID){
		return (Map<String, String>) load("ForecastTaskupAudit.getTotalInfo", RPT_JOB_CHANN_ID);
	}
}
