/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderService
*/
package com.hoperun.erp.sale.techorder.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.techorder.model.TechorderModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 16:47:06
 */
public interface TechorderService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	
	/**
	 * @主表详细页面
	 * @param param TECH_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String TECH_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * @return the list
     */
    public List <TechorderModelChld> childQuery(String TECH_ORDER_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param TECH_ORDER_DTL_IDs the TECH_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <TechorderModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String TECH_ORDER_ID, List <TechorderModelChld> modelList,UserBean userBean);
    
    /**
     * 审核
     * @param TECH_ORDER_ID 工艺单ID
     * @param SALE_ORDER_ID 非标订单ID
     * @param TECH_ORDER_NO 工艺单编号
     * @param userBean
     */
	public void txAudit(String TECH_ORDER_ID,String SALE_ORDER_ID,String TECH_ORDER_NO, UserBean userBean);
    /**
     * * 根据主表Id查询生命周期子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
	public List <Map<String,String>> queryTrace(String SALE_ORDER_ID);
	
	 /**
	 * @查询编辑明细
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage childQueryEdit(Map<String,String> params, int pageNo);
	
	public int checkChild(String TECH_ORDER_DTL_IDS);
	//审核
	public void txChldAudit(String TECH_ORDER_DTL_IDS,UserBean userBean);
	//撤销
	public void txChldCancel(String TECH_ORDER_DTL_IDS);
	
}