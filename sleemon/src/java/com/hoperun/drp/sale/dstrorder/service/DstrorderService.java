/**
 * prjName:喜临门营销平台
 * ucName:分发指令接收
 * fileName:DstrorderService
*/
package com.hoperun.drp.sale.dstrorder.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.dstrorder.model.DstrorderModelChld;

/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-16 10:31:37
 */
public interface DstrorderService extends IBaseService {
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
	 * @param param DSTR_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String DSTR_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param DSTR_ORDER_ID the DSTR_ORDER_ID
     * @return the list
     */
    public List <DstrorderModelChld> childQuery(String DSTR_ORDER_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param DSTR_ORDER_DTL_IDs the DSTR_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <DstrorderModelChld> loadChilds(Map <String, String> params) ;
	
    
    /**
     * 修改状态为已接收
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txReceivedById(Map <String, String> params);
}