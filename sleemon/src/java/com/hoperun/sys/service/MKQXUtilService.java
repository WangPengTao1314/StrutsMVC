package com.hoperun.sys.service;


import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;

// TODO: Auto-generated Javadoc
/**
 * The Class MKQXUtilService.
 */
public class MKQXUtilService extends BaseService {
   
	
	 
	 
	
	
	
	//通用增删改查
	/**
	 * Selcom.
	 * 
	 * @param params the params
	 * 
	 * @return the map
	 */
	public Map selcom(Map params) {
		return (Map)load("sqlcom.query",params);
	}
	
	/**
	 * Selcom list.
	 * 
	 * @param params the params
	 * 
	 * @return the list
	 */
	public List selcomList(Map params) {
		return this.findList("sqlcom.query", params);
	}
	
	/**
	 * Delcom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean delcom(Map params) {
		return delete("sqlcom.delete", params);
	}
	
	/**
	 * Updcom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean updcom(Map params) {
		return update("sqlcom.update", params);
	}
	
	/**
	 * Inscom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean inscom(Map params) {
		insert("sqlcom.insert", params);
		return true;
	}
	
	/**
	 * Page query.
	 * 
	 * @param params the params
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("sqlcom.pageQuery", "sqlcom.pageCount",params, pageNo);
	}
	
	/**
	 * Insertmod.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean insertmod(Map params) {
		insert("flow.insertmod", params);
		return true;
	}
	
	/**
	 * Insertnod.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean insertnod(Map params) {
		insert("flow.insertnod", params);
		return true;
	}
	
	/**
	 * Insertpath.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean insertpath(Map params) {
		insert("flow.insertpath", params);
		return true;
	}
}
