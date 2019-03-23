package com.hoperun.erp.sale.forecasttaskup.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.forecasttaskup.model.AdvcRptChannDtl;

public interface ForecastTaskupAuditService extends IBaseService{
	 /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	
	/**
	 * 查询上报货品
	 * @param paramMap
	 * @return
	 */
	public List<Map<String,String>> childQuery(String RPT_JOB_CHANN_ID);
	
	/**
	 * @param childList
	 */
	public void txSaveChild(List<AdvcRptChannDtl> childList);
	
	/***
	 * @param RPT_JOB_CHANN_ID
	 */
	public void txAudit(String RPT_JOB_CHANN_ID);
	
	/***
	 * @param RPT_JOB_CHANN_ID
	 */
	public void txReAudit(String RPT_JOB_CHANN_ID);
    
	/**
	 * @param RPT_JOB_CHANN_ID
	 * @return
	 */
	public Map <String, String> getTotalInfo(String RPT_JOB_CHANN_ID);

}
