package com.hoperun.erp.sale.forecasttask.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.forecasttask.model.ForecastTaskModel;
import com.hoperun.erp.sale.forecasttask.service.ForecastTaskService;
import com.hoperun.sys.model.UserBean;

public class ForecastTaskServiceImpl extends BaseService implements
		ForecastTaskService {

	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("ForecastTask.pageQuery",
				"ForecastTask.pageCount", params, pageNo);
	}

	/**
	 * 渠道列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> childQuery(Map<String, String> params) {
		return this.findList("ForecastTask.queryPrdByparams", params);
	}

	/**
	 * @保存编辑
	 * @Description :
	 * @param ADVC_RPT_JOB_ID
	 * @param ForecastTaskModel
	 * @param userBean
	 *            .
	 * 
	 * @return
	 */
	public void txEdit(String ADVC_RPT_JOB_ID, ForecastTaskModel model,
			UserBean userBean){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("SENDER_ID", model.getSENDER_ID());
		params.put("SENDER_NAME", model.getSENDER_NAME());
		params.put("SENDER_TIME", model.getSENDER_TIME());
		params.put("REMARK", model.getREMARK());
		params.put("YEAR", model.getYEAR());
		params.put("MONTH", model.getMONTH());
		if(StringUtil.isEmpty(ADVC_RPT_JOB_ID)){
			ADVC_RPT_JOB_ID = StringUtil.uuid32len();
			params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
			params.put("ADVC_RPT_JOB_NO", LogicUtil.getBmgz("ERP_ADVC_RPT_JOB_NO"));
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
		    params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
			txUpdateById(params);
			clearCaches(ADVC_RPT_JOB_ID);
		}
		
	}
	
	public boolean txUpdateById(Map<String,String> params) {
		return update("ForecastTask.updateById", params);
	}

	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public void txDelete(String ADVC_RPT_JOB_ID, UserBean userBean){
		//删除父
		Map<String,String> params = new HashMap<String,String>();
		params.put("DEL_FLAG", "1");
		params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
		params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
        update("ForecastTask.delete", params);
        
        //删除上报任务渠道范围
		int num = this.queryForInt("ForecastTask.queryJobChannNum", params);
        if(num > 0){
        	
        }
        
	}

	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_RPT_JOB_ID
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_RPT_JOB_ID){
		return (Map<String,String>) load("ForecastTask.loadById", ADVC_RPT_JOB_ID);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("ForecastTask.insert", params);
		return true;
	}
	
	
	/**
	 * 发布
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txRelease(String ADVC_RPT_JOB_ID,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("STATE", "发布");
		params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
		params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
	    params.put("SENDER_TIME","sysdate");
		this.update("ForecastTask.updateStateById", params);
		Map<String,String> map = this.load(ADVC_RPT_JOB_ID);
		//插入上报任务渠道范围
		this.insertPrtjobChann(map);
		//插入上报任务货品范围
		this.insertPrtobPrd(map);
		
	}
	
	/**
	 * 取消发布
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txCancel(String ADVC_RPT_JOB_ID,UserBean userBean){ 
		Map<String,String> params = new HashMap<String,String>();
		params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
		int num = this.queryForInt("ForecastTask.queryJobChannNum", params);
		if(num > 0){
			throw new ServiceException("该任务已开始填报，不能取消");
		}
		params.put("STATE", "取消");
		params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
		this.update("ForecastTask.updateStateById", params);
		//删除渠道还没有提交的上报货品明细
		this.delete("ForecastTask.deleteChannDtl", params);
		//删除上报任务渠道范围
		detetePrtjobChann(params);
		detetePrtobPrd(params);
		
		
	}

	/**
	 * 插入上报任务渠道范围
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void insertPrtjobChann(Map<String,String> param){
	   detetePrtjobChann(param);
	   param.put("STATE", "未填报");
	   this.insert("ForecastTask.insertRptjobchann", param);
	}
	
	public void detetePrtjobChann(Map<String,String> param){
		this.delete("ForecastTask.deleteJobchann", param);
	}
	
	/**
	 * 插入上报任务渠道范围
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void insertPrtobPrd(Map<String,String> param){
		detetePrtobPrd(param);
	    this.insert("ForecastTask.insertRptjobprd", param);
	    
	}
	
	public void detetePrtobPrd(Map<String,String> param){
		this.delete("ForecastTask.deleteJobprd", param);
 	}
	
    /**
     * 查询填报的渠道
     * @param param
     * @return
     */
	public List<Map<String,String>> queryJobChann(Map<String,String> param){
		return this.findList("ForecastTask.queryJobChann", param);
	}
	
	/**
	 * 结束上报
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txOver(String ADVC_RPT_JOB_ID,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("STATE", "结束上报");
		params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
		params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
		this.update("ForecastTask.updateStateById", params);
	}
	
	/**
	 * 打开上报
	 * @param ADVC_RPT_JOB_ID
	 * @param userBean
	 */
	public void txOpen(String ADVC_RPT_JOB_ID,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("STATE", "发布");
		params.put("ADVC_RPT_JOB_ID", ADVC_RPT_JOB_ID);
		params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
		this.update("ForecastTask.updateStateById", params);
	}
	
	/**
	 * 查询同一个月份下的任务
	 * @param paramMap
	 * @return
	 */
	public int validateRptYearMonth(Map<String,String> paramMap){
		return this.queryForInt("ForecastTask.validateRptYearMonth", paramMap);
	}

}
