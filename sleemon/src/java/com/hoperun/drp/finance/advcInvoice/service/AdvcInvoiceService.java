/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderService
 */
package com.hoperun.drp.finance.advcInvoice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelTrace;
import com.hoperun.sys.model.UserBean;

/**
 * *@service *@func *@version 1.1 *@author lyg *@createdate 2013-08-11 17:17:29
 */
public interface AdvcInvoiceService extends IBaseService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);

	public boolean txUpdateById(Map<String, String> params);


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
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID);

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID);

	/**
	 * 根据主表Id查询跟踪记录
	 * 
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public List<AdvcorderModelTrace> traceQuery(String ADVC_ORDER_ID);

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 * 
	 * @return the list
	 */
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params);

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 * 
	 * @return the list
	 */
	public List<AdvcorderModelGchld> loadGchilds(Map<String, String> params);
	/**
	 * 预订单开票
	 * @param ADVC_ORDER_ID
	 */
	public void commitInvoice(Map<String,String> params);
	
}