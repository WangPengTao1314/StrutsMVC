/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseServiceImpl
*/
package com.hoperun.drp.sale.advise.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advise.model.AdviseModel;
import com.hoperun.drp.sale.advise.model.AdviseModelChld;
import com.hoperun.drp.sale.advise.service.AdviseService;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelProcess;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public class AdviseServiceImpl extends BaseService implements AdviseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Advise.pageQuery", "Advise.pageCount",params, pageNo);
	}
	
	/**
     * * 根据主表Id查询产品投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdviseModelChld> prdcmplQuery(String CMPL_ADVS_ID) {
        Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Advise.queryChldByFkId", params);
    }
    
    /**
     * * 根据主表Id查询服务投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public AdviseModel sercmplQuery(String CMPL_ADVS_ID) {
        Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return (AdviseModel) this.load("Advise.querySerCmpl", params);
    }
    
    /**
     * * 根据主表Id查询建议信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public AdviseModel advsQuery(String CMPL_ADVS_ID) {
        Map params = new HashMap();
        params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return (AdviseModel) this.load("Advise.queryAdvs", params);
    }
    

	public List <AdvisehdModelProcess> processQuery(String CMPL_ADVS_ID){
		 Map<String,String> params = new HashMap<String,String>();
	     params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
	     //只查询0的记录。1为删除。0为正常
	     //params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	     return this.findList("Advisehd.queryProcessByFkId", params);
	} 
	
	
	 /**
     * 重提
     * @param CMPL_ADVS_ID 单据ID
     * @param userBean
     */
    public void txAgain(String CMPL_ADVS_ID,UserBean userBean ){
    	 Map<String,String> params = new HashMap<String,String>();
	     params.put("CMPL_ADVS_ID", CMPL_ADVS_ID);
	     params.put("UPDATOR", userBean.getXTYHID());
	     params.put("UPD_NAME", userBean.getXM());
	     params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);
	     params.put("STATE","重提");
	     this.update("Advise.updateById", params);
	     
	     //插入生命周期
	     String DRP_CMPL_ADVS_TRACE_ID = StringUtil.uuid32len();
	     params.put("DEAL_PSON_ID", userBean.getXTYHID());
	     params.put("DEAL_PSON_NAME", userBean.getXM());
	     params.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
	     params.put("FEEDBACK_INFO", "由"+userBean.getXM()+"重提");
	     params.put("DRP_CMPL_ADVS_TRACE_ID", DRP_CMPL_ADVS_TRACE_ID);
	     this.insert("Adviseinit.insertADVSTRACE", params);
	    	
    }
    
    
}