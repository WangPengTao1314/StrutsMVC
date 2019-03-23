/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderService
*/
package com.hoperun.erp.sale.techordershow.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.techordershow.model.TechorderShowModelChld;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 16:47:06
 */
public interface TechorderShowService extends IBaseService {
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
    public List <TechorderShowModelChld> childQuery(String TECH_ORDER_ID);
	
}