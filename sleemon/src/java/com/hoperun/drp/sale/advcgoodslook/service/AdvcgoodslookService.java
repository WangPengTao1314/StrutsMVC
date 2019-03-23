package com.hoperun.drp.sale.advcgoodslook.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;

public interface AdvcgoodslookService extends IBaseService{
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
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID);
	
	
	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);
	/**
	 * 导出
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> expertExcelQuery(Map<String,String> map);

}
