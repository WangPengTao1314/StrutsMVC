package com.hoperun.drp.sale.forecasttaskup.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.forecasttaskup.model.AdvcRptChannDtl;
import com.hoperun.sys.model.UserBean;

public interface ForecastTaskupService extends IBaseService{
	
	 /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);

	/**
	 * 查询填报的货品
	 * @param params
	 * @return
	 */
	public List<Map<String,String>> queryPrd(Map<String,String> params);
	/**
	 * 保存
	 * @param RPT_JOB_CHANN_ID
	 * @param chidList
	 */
	public void txEdit(String RPT_JOB_CHANN_ID, Map<String,String> entry,List<AdvcRptChannDtl> chidList,UserBean userBean);
	/**
	 * 提交
	 * @param RPT_JOB_CHANN_ID
	 */
	public void txCommit(String RPT_JOB_CHANN_ID,UserBean userBean);
	public void txRevoke(String RPT_JOB_CHANN_ID);
	
	public Map<String,String> load(String RPT_JOB_CHANN_ID);
	//获取数据库当前时间
	public String getData();
}
