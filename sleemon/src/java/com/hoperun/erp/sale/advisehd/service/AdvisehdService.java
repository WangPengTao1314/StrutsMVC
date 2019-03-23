/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseService
*/
package com.hoperun.erp.sale.advisehd.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModel;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelChld;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelProcess;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public interface AdvisehdService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	 /**
     * * 根据主表Id查询产品投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * @return the list
     */
    public List <AdvisehdModelChld> prdcmplQuery(String CMPL_ADVS_ID);
	
    /**
     * * 根据主表Id查询服务投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    public AdvisehdModel sercmplQuery(String CMPL_ADVS_ID);
    
    /**
     * * 根据主表Id查询建议信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    public AdvisehdModel advsQuery(String CMPL_ADVS_ID);
    
    /**
     * 增加反馈信息
     * @param CMPL_ADVS_ID
     * @param feedback
     * @return
     */
    public String saveFeedback(String CMPL_ADVS_ID,String feedback,UserBean userBean);
    
    /**
     * 添加指定处理人
     * @param CMPL_ADVS_ID
     * @param APPOINT_PSON_ID
     * @param userBean
     */
    public void appointPson(String CMPL_ADVS_ID,String APPOINT_PSON_ID,UserBean userBean);
    
    
    public List <AdvisehdModelProcess> processQuery(String CMPL_ADVS_ID);
    
    /**
     * 
     * 指派处理人操作
     * @param params
     * @param userBean
     */
    public void txToAppoint(Map<String,String> params,UserBean userBean);
    /**
     * 
     * 处理完成操作
     * @param params
     * @param userBean
     */
	public Map<String,String> txdealDone(Map<String, String> params, UserBean userBean);
    
	/**
	 * 导出建议
	 * @param map
	 * @return
	 */
	public List expAdvsImport(Map<String,String> map);
	
	/**
	 * 导出服务投诉
	 * @param map
	 * @return
	 */
	public List expSrvcmplImport(Map<String,String> map);
	/**
	 * 导出产品投诉
	 * @param map
	 * @return
	 */
	public List expPrdcmplImport(Map<String,String> map);
	/**
	 * 退回
	 * @param CMPL_ADVS_ID
	 * @param REMARK
	 * @param userBean
	 */
	public void txReturn(String CMPL_ADVS_ID, String REMARK,UserBean userBean);
    
}