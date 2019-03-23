/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderService
*/
package com.hoperun.erp.sale.saleorderque.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.saleorderque.model.SaleorderqueModelChld;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public interface SaleorderqueService extends IBaseService {
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
	 * @param param SALE_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String SALE_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    public List <SaleorderqueModelChld> childQuery(String SALE_ORDER_ID);
    
}