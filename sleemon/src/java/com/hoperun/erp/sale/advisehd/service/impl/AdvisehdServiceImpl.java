/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdvisehdServiceImpl
*/
package com.hoperun.erp.sale.advisehd.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModel;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelChld;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelProcess;
import com.hoperun.erp.sale.advisehd.service.AdvisehdService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public class AdvisehdServiceImpl extends BaseService implements AdvisehdService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Advisehd.pageQuery", "Advisehd.pageCount",params, pageNo);
	}
	
	/**
     * * 根据主表Id查询产品投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvisehdModelChld> prdcmplQuery(String CMPL_ADVS_ID) {
        Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Advisehd.queryChldByFkId", params);
    }
    
    /**
     * * 根据主表Id查询服务投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public AdvisehdModel sercmplQuery(String CMPL_ADVS_ID) {
        Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return (AdvisehdModel) this.load("Advisehd.querySerCmpl", params);
    }
    
    /**
     * * 根据主表Id查询建议信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public AdvisehdModel advsQuery(String CMPL_ADVS_ID) {
        Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return (AdvisehdModel) this.load("Advisehd.queryAdvs", params);
    }

    /**
     * 增加反馈信息
     * @param CMPL_ADVS_ID
     * @param feedback
     * @return
     */
	@SuppressWarnings("unchecked")
	@Override
	public String saveFeedback(String CMPL_ADVS_ID, String feedback,UserBean userBean) {
		Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        params.put("DEAL_PSON_ID", userBean.getXTYHID());
        params.put("DEAL_PSON_NAME", userBean.getXM());
        params.put("FEEDBACK_INFO", feedback);
        params.put("UPDATOR",userBean.getXTYHID());
        params.put("UPD_NAME",userBean.getXM());
        params.put("STATE", BusinessConsts.STATE_YFK);
        this.update("Advisehd.saveFeedback", params);
		return CMPL_ADVS_ID;
	}

	/**
     * 添加指定处理人
     * @param CMPL_ADVS_ID
     * @param APPOINT_PSON_ID
     * @param userBean
     */
	@SuppressWarnings("unchecked")
	@Override
	public void appointPson(String CMPL_ADVS_ID, String APPOINT_PSON_ID,
			UserBean userBean) {
		Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        params.put("APPOINT_PSON_ID", APPOINT_PSON_ID);
        params.put("UPDATOR",userBean.getXTYHID());
        params.put("UPD_NAME",userBean.getXM());
	    this.update("Advisehd.appointPson", params);
		
	}
	
	public List <AdvisehdModelProcess> processQuery(String CMPL_ADVS_ID){
		 Map params = new HashMap();
	     params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
	     //只查询0的记录。1为删除。0为正常
	     //params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	     return this.findList("Advisehd.queryProcessByFkId", params);
	}
 
    /**
     * 指派处理人操作 20140313
     */
    public void txToAppoint(Map<String,String> params,UserBean userBean){
    	
    	String DRP_CMPL_ADVS_TRACE_ID= StringUtil.uuid32len();
    	params.put("DRP_CMPL_ADVS_TRACE_ID", DRP_CMPL_ADVS_TRACE_ID);
    	
    	params.put("STATE", BusinessConsts.UNDONE);
    	this.update("Advisehd.insertADVSTRACE", params);
    	
    	params.remove("STATE");
    	params.remove("FEEDBACK_INFO");
    	params.put("STATE", BusinessConsts.STATE_WFK);
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getXM());
		params.put("UPD_TIME", "test");
		params.put("DEAL_TIME", "test");
    	this.update("Advisehd.updateADVS", params);
    	
    }
    /**
     * 处理完成操作 20140313
     */
    public Map<String,String>  txdealDone(Map<String,String> params,UserBean userBean){
    	
    	params.put("STATE", BusinessConsts.STATE_YFK);
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getXM());
		params.put("UPD_TIME", "test");
		params.put("DEAL_TIME", "test");
    	this.update("Advisehd.updateADVS", params);
    	
    	params.remove("STATE");
    	String DRP_CMPL_ADVS_TRACE_ID= StringUtil.uuid32len();
    	params.put("DRP_CMPL_ADVS_TRACE_ID", DRP_CMPL_ADVS_TRACE_ID);
    	
    	params.put("STATE", BusinessConsts.DONE);
    	this.update("Advisehd.insertADVSTRACE", params);
    	 
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
    	Map<String,String> map = (Map<String, String>) this.load("Advisehd.loadById", params);
    	return map;
    	
    }
    /**
	 * 导出建议
	 * @param map
	 * @return
	 */
	public List expAdvsImport(Map<String,String> map){
		return this.findList("Advisehd.expAdvsImport", map);
	}
	
	/**
	 * 导出服务投诉
	 * @param map
	 * @return
	 */
	public List expSrvcmplImport(Map<String,String> map){
		return this.findList("Advisehd.expSrvcmplImport", map);
	}
	/**
	 * 导出产品投诉
	 * @param map
	 * @return
	 */
	public List expPrdcmplImport(Map<String,String> map){
		return this.findList("Advisehd.expPrdcmplImport", map);
	}
	/**
	 * 退回
	 * @param CMPL_ADVS_ID
	 * @param REMARK
	 * @param userBean
	 */
	public void txReturn(String CMPL_ADVS_ID, String REMARK,UserBean userBean){
		String STATE=(String) this.load("Advisehd.getState", CMPL_ADVS_ID);
		if(!"未反馈".equals(STATE)&&!"重提".equals(STATE)){
			throw new ServiceException("该单据状态为:"+STATE+",不能退回");
		}else{
			Map<String,String> map=new HashMap<String, String>();
			map.put("STATE", BusinessConsts._BACK);
			map.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
			map.put("DEAL_PSON_ID", userBean.getXTYHID());
			map.put("DEAL_PSON_NAME", userBean.getXM());
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "test");
			map.put("DEAL_TIME", "test");
			map.put("FEEDBACK_INFO", REMARK);
	    	this.update("Advisehd.updateADVS", map);
	    	
	    	String DRP_CMPL_ADVS_TRACE_ID= StringUtil.uuid32len();
	    	map.put("DRP_CMPL_ADVS_TRACE_ID", DRP_CMPL_ADVS_TRACE_ID);
	    	this.update("Advisehd.insertADVSTRACE", map);
			
		}
	}
}