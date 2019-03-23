/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseService
*/
package com.hoperun.drp.sale.advise.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advise.model.AdviseModel;
import com.hoperun.drp.sale.advise.model.AdviseModelChld;
import com.hoperun.erp.sale.advisehd.model.AdvisehdModelProcess;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public interface AdviseService extends IBaseService {
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
    public List <AdviseModelChld> prdcmplQuery(String CMPL_ADVS_ID);
	
    /**
     * * 根据主表Id查询服务投诉反馈信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    public AdviseModel sercmplQuery(String CMPL_ADVS_ID);
    
    /**
     * * 根据主表Id查询建议信息
     * @param CMPL_ADVS_ID the CMPL_ADVS_ID
     * 
     * @return the list< new master slavemx model>
     */
    public AdviseModel advsQuery(String CMPL_ADVS_ID);
    /**
     * 查询处理过程
     * @param CMPL_ADVS_ID
     * @return
     */
    public List <AdvisehdModelProcess> processQuery(String CMPL_ADVS_ID);
    
    /**
     * 重提
     * @param CMPL_ADVS_ID 单据ID
     * @param userBean
     */
    public void txAgain(String CMPL_ADVS_ID,UserBean userBean );
    
    
    
}