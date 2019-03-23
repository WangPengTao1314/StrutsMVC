package com.hoperun.erp.sale.forecasttask.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.forecasttask.model.ForecastTaskModel;
import com.hoperun.sys.model.UserBean;

public interface ForecastTaskService extends IBaseService{
	 /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	
	 /**
	 * @保存编辑
	 * @Description :
	 * @param ADVC_RPT_JOB_ID
	 * @param ForecastTaskModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String ADVC_RPT_JOB_ID, ForecastTaskModel model, UserBean userBean);
	/**
	 * @主表删除
	 * @return true, if tx delete
	 */
	public void txDelete(String ADVC_RPT_JOB_ID, UserBean userBean);
	
	/**
	 * @主表详细页面
	 * @param param ADVC_RPT_JOB_ID
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ADVC_RPT_JOB_ID);
	/**
	 * 发布
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txRelease(String ADVC_RPT_JOB_ID,UserBean userBean);
	
	/**
	 * 取消发布
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txCancel(String ADVC_RPT_JOB_ID,UserBean userBean);

	/**
	 * 结束上报
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txOver(String ADVC_RPT_JOB_ID,UserBean userBean);
	
	/**
	 * 打开上报
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txOpen(String ADVC_RPT_JOB_ID,UserBean userBean);
	/**
     * 查询填报的渠道
     * @param param
     * @return
     */
	public List<Map<String,String>> queryJobChann(Map<String,String> param);
	
	/**
	 * 查询同一个月份下的任务
	 * @param paramMap
	 * @return
	 */
	public int validateRptYearMonth(Map<String,String> paramMap);
}
