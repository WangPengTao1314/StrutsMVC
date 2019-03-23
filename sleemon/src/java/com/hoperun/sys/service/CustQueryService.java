package com.hoperun.sys.service;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.CustomQueryBean;
import com.hoperun.sys.model.CustomQueryDtlBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface CustQueryService.
 */
public interface CustQueryService extends IBaseService {
	
	/**
	 * 根据查询条件【编号】、【名称】查询自定义报表.
	 * 
	 * @param params the params
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage custPageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * 查询自定义报表展示.
	 * 
	 * @param params the params
	 * 
	 * @return the i list page
	 */
	public IListPage custPageQueryDisplay(Map<String,String> params);
	
	/**
	 * 根据【自定义报表主键】更新自定义报表状态.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx update rpt state
	 */
	public boolean txUpdateRptState(Map<String,String> map);
	
	/**
	 * 根据【自定义报表主键】删除自定义报表.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx delete rpt row
	 */
	public boolean txDeleteRptRow(Map<String,String> map);
	
	/**
	 * 根据【自定义报表主键】查询自定义报表相关信息.
	 * 
	 * @param map the map
	 * 
	 * @return the custom query bean
	 */
	public CustomQueryBean queryForByRptPk(Map<String,String> map);
	
	/**
	 * 保存自定义报表基本信息.
	 * 
	 * @param map the map
	 * 
	 * @return the object
	 */
	public Object txInsertRptMain(Map<String,String> map);
	
	/**
	 * 更新自定义报表基本信息.
	 * 
	 * @param map the map
	 * 
	 * @return true, if tx update rpt main
	 */
	public boolean txUpdateRptMain(Map<String,String> map);
	
	/**
	 * 校验报表SQL是否正确.
	 * 
	 * @param map the map
	 * 
	 * @return true, if check rpt sql
	 */
	public boolean checkRptSql(Map<String,String> map);
	
	/**
	 * 根据自定义报表【主键】查询报表明细.
	 * 
	 * @param map the map
	 * 
	 * @return the list< custom query dtl bean>
	 */
	public List<CustomQueryDtlBean> queryRptQueryDtlByPk(Map<String,String> map);
	
	/**
	 * 根据Map<type = 1【查询条件】、=【结果集】>.
	 * 
	 * @param map the map
	 * 
	 * @return the list< custom query dtl bean>
	 */
	public List<CustomQueryDtlBean> queryDisplayRpt(Map<String,String> map);
	
	/**
	 * 保存自定义报表页面数据.
	 * 
	 * @param map the map
	 * @param list the list
	 * 
	 * @return true, if tx save rpt dtl date
	 */
	public boolean txSaveRptDtlDate(Map<String,String> map , List<CustomQueryDtlBean> list);
	
	/**
	 * Display rpt page query.
	 * 
	 * @param params the params
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage displayRptPageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * 导出Excel.
	 * 
	 * @param params the params
	 * @param page the page
	 * 
	 * @return the i list page
	 */
	public IListPage rptExport(Map<String,String> params,int page); 
	
	/**
	 * 处理权限.
	 * 
	 * @param params the params
	 * 
	 * @return true, if tx process role
	 */
	public boolean txProcessRole(Map<String,String> params);
	
	/**
	 * 查询表数据.
	 * 
	 * @param map the map
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage queryTable(Map<String,String> map, int pageNo);
	
	/**
	 * 查询列信息.
	 * 
	 * @param map the map
	 * 
	 * @return the array list< hash map< string, string>>
	 */
	public ArrayList<HashMap<String,String>> queryTableColLength(HashMap<String,String> map);
	
	/**
	 * 获取表主键.
	 * 
	 * @param map the map
	 * 
	 * @return the table primary kery
	 */
	public String getTablePrimaryKery(Map<String,String> map);
	
	/**
	 * 更新表数据.
	 * 
	 * @param map the map
	 * 
	 * @return true, if update table data
	 */
	public boolean updateTableData(ArrayList<Map<String,String>> map);
	
	/**
	 * 根据SQL校验出SQL中的表名.
	 * 
	 * @param map the map
	 * 
	 * @return the table name from where sql
	 */
	public String getTableNameFromWhereSql(Map<String,String> map);
	
	/**
	 * 查询表可编辑字段.
	 * 
	 * @param map the map
	 * 
	 * @return the hash map< string, string>
	 */
	public HashMap<String,String> enanbleEditTableColumn(Map<String,String> map);
	
	/**
	 * 查询导出结果集.
	 * 
	 * @param map the map
	 * 
	 * @return the list< map>
	 */
	public List<Map> qryExpData(Map<String,String> map);
	
	/**
	 * 查询报表列表
	 * 
	 * @param map the map
	 * 
	 * @return the list< map>
	 */
	public List<Map> getRptCol(String RPTMODELID);
	
	
}
