package com.hoperun.base.table.service;

import java.util.List;
import java.util.Map;
import com.hoperun.base.table.model.TableModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;

public interface TableService  extends IBaseService {
  
	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryDetail(Map <String, String> params, int pageNo);    
    
    /**
     * @param TABLE_NAME_ZH
     * @return
     */
	public List<TableModel> load(String TABLE_NAME_EN);
	
	/**
	 * @param TABLE_NAME_EN
	 * @return
	 */
	public List<TableModel> loadByColName(String TABLE_COL_EN);
	
	/**
	 * @param TABLE_NAME_EN
	 * @return
	 */
	public Map <String, String> loadByTableNameEn(String TABLE_NAME_EN);
}
