package com.hoperun.base.table.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.base.table.model.TableModel;
import com.hoperun.base.table.service.TableService;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;

public class TableServiceImpl  extends BaseService implements TableService {
  
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("TABLE.pageQuery",
				"TABLE.pageCount", params, pageNo);
    }
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryDetail(Map <String, String> params, int pageNo){
    	return this.pageQuery("TABLE.pageQueryDetail",
				"TABLE.pageCountDetail", params, pageNo);
    }
    
    /**
     * @param TABLE_NAME_ZH
     * @return
     */
	public List<TableModel> load(String TABLE_NAME_EN){
		Map params = new HashMap();
		params.put("TABLE_NAME_EN", TABLE_NAME_EN);
		return this.findList("TABLE.load",
				params);
	}
	
	/**
	 * @param TABLE_NAME_EN
	 * @return
	 */
	public List<TableModel> loadByColName(String TABLE_COL_EN){
		Map params = new HashMap();
		params.put("TABLE_COL_EN", TABLE_COL_EN);
		return this.findList("TABLE.loadbycolName",
				params);
	}
	
	/**
	 * @param TABLE_NAME_EN
	 * @return
	 */
	public Map <String, String> loadByTableNameEn(String TABLE_NAME_EN){
		return (Map<String, String>) load("TABLE.loadByTableNameEn",
				TABLE_NAME_EN);
	}
}
