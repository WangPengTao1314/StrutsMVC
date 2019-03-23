/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.dao.BaseDao;
import com.hoperun.commons.model.IListPage;

// TODO: Auto-generated Javadoc
/**
 * The Interface IBaseService.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public interface IBaseService {

	/**
	 * Sets the ibatis dao.
	 * 
	 * @param ibatisDao the new ibatis dao
	 */
	public abstract void setIbatisDao(BaseDao ibatisDao);

	/*
	 * 新增
	 */
	/**
	 * Insert.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the object
	 */
	public abstract Object insert(String statementName, Object paramMap);

	/*
	 * 更新
	 */
	/**
	 * Update.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return true, if successful
	 */
	public abstract boolean update(String statementName, Object paramMap);

	/*
	 * 删除
	 */
	/**
	 * Delete.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return true, if successful
	 */
	public abstract boolean delete(String statementName, Object paramMap);

	/*
	 * 加载单条记录
	 */
	/**
	 * Load.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the object
	 */
	public abstract Object load(String statementName, Object paramMap);

	/*
	 * 加载多条记录
	 */
	/**
	 * Find list.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the list
	 */
	public abstract List findList(String statementName, Object paramMap);

	/*
	 * 加载自定义参数
	 */
	/**
	 * Find paramters.
	 * 
	 * @param paramType the param type
	 * 
	 * @return the list
	 */
	public abstract List findParamters(String paramType);

	/*
	 * 按默认行数分页
	 */
	/**
	 * Page query.
	 * 
	 * @param queryStatementName the query statement name
	 * @param countStatementName the count statement name
	 * @param paramMap the param map
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public abstract IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo);

	/*
	 * 按指定行数分页
	 */
	/**
	 * Page query.
	 * 
	 * @param queryStatementName the query statement name
	 * @param countStatementName the count statement name
	 * @param paramMap the param map
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * 
	 * @return the i list page
	 */
	public abstract IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize);

	/**
	 * 保存系统访问日志.
	 * 
	 * @param paramMap the param map
	 * 
	 * @return true, if save log
	 */
	public abstract boolean saveLog(Map paramMap);
	
	/**
	 * Find list ods.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the list
	 */
	public List findListOds(String statementName, Object paramMap);
	
	/**
	 * Page query ods.
	 * 
	 * @param queryStatementName the query statement name
	 * @param countStatementName the count statement name
	 * @param paramMap the param map
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * 
	 * @return the i list page
	 */
	public IListPage pageQueryOds(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize);
	
	/**
	 * Gets the ibatis ods.
	 * 
	 * @return the ibatis ods
	 */
	public BaseDao getIbatisOds();

	/**
	 * Sets the ibatis ods.
	 * 
	 * @param ibatisOds the new ibatis ods
	 */
	public void setIbatisOds(BaseDao ibatisOds);

	/**
	 * Gets the ibatis dao.
	 * 
	 * @return the ibatis dao
	 */
	public BaseDao getIbatisDao() ;

	/**
	 * 批量更新.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * 
	 * @return true, if batch4 update
	 */
	public boolean batch4Update(String statementName, List datas);
	
	/**
	 * 批量更新.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * @param batchSize the batch size
	 * 
	 * @return true, if batch4 update
	 */
	public boolean batch4Update(String statementName, List datas, int batchSize);

	/**
	 * Clear caches.
	 * 
	 * @param key the key
	 */
	public void clearCaches(Object key);

	/**
	 * 加载参数.
	 * 
	 * @param varId the var id
	 * 
	 * @return the map
	 */
	public Map loadParameter(String varId);

	/**
	 * 加载报表.
	 * 
	 * @param rptId the rpt id
	 * 
	 * @return the map
	 */
	public Map loadReport(String rptId);

	/**
	 * 缓存用户.
	 * 
	 * @param userId the user id
	 * 
	 * @return the map
	 */
	public Map loadUser(String userId);
}