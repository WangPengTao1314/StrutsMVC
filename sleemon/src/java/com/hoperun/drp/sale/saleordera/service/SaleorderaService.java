/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderService
 */
package com.hoperun.drp.sale.saleordera.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.saleordera.model.SaleorderaModelChld;

/**
 * *@service *@func *@version 1.1 *@author lyg *@createdate 2013-08-11 17:17:29
 */
public interface SaleorderaService extends IBaseService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);


	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<SaleorderaModelChld> childQuery(String ADVC_ORDER_ID);
	public List downQuery(Map<String,String> map);
}