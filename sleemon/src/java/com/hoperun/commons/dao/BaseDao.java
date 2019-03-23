/**
  *@module 数据库操作基类   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.SqlMapClientCallback;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import com.ibatis.sqlmap.client.SqlMapExecutor;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseDao.
 */
public class BaseDao extends SqlMapClientDaoSupport {

	/** The Constant log. */
	private static final Log log = LogFactory.getLog(BaseDao.class);

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
	public Object insert(String statementName, Object paramMap) {
		log.debug("ibatis insert " + statementName);
		return getSqlMapClientTemplate().insert(statementName, paramMap);
	}

	/*
	 * 更新
	 * 
	 */
	/**
	 * Update.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return true, if successful
	 */
	public boolean update(String statementName, Object paramMap) {
		log.debug("ibatis update " + statementName);
		return this.getSqlMapClientTemplate().update(statementName, paramMap) > 0;
	}

	/*
	 * 删除
	 * 
	 */
	/**
	 * Delete.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return true, if successful
	 */
	public boolean delete(String statementName, Object paramMap) {
		log.debug("ibatis delete " + statementName);
		return getSqlMapClientTemplate().delete(statementName, paramMap) > 0;
	}

	/*
	 * 加载单条记录
	 * 
	 */
	/**
	 * Load.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the object
	 */
	public Object load(String statementName, Object paramMap) {
		log.debug("ibatis load " + statementName);
		return getSqlMapClientTemplate()
				.queryForObject(statementName, paramMap);
	}

	/*
	 * 可用于计数
	 * 
	 */
	/**
	 * Query for int.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the int
	 */
	public int queryForInt(String statementName, Object paramMap) {
		Integer rst = (Integer) load(statementName, paramMap);
		return rst == null ? 0 : rst.intValue();
	}

	/*
	 * 查询文本
	 * 
	 */
	/**
	 * Query for str.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the string
	 */
	public String queryForStr(String statementName, Object paramMap) {
		return (String) load(statementName, paramMap);
	}

	/*
	 * 查询列表
	 * 
	 */
	/**
	 * Query for list.
	 * 
	 * @param statementName the statement name
	 * @param paramObj the param obj
	 * 
	 * @return the list
	 */
	public List queryForList(String statementName, Object paramObj) {
		return this.getSqlMapClientTemplate().queryForList(statementName,
				paramObj);
	}

	/**
	 * 批量更新.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * 
	 * @return true, if batch4 update
	 */
	public boolean batch4Update(final String statementName, final List datas) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator iter = datas.iterator(); iter.hasNext();) {
					Map data = (Map) iter.next();
					executor.insert(statementName, data);
				}
				executor.executeBatch();
				return null;
			}
		});
		return true;
	}
	
	/**
	 * 批量更新(对象).
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * 
	 * @return true, if batch obj4 update
	 */
	public boolean batchObj4Update(final String statementName, final List datas) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				for (Iterator iter = datas.iterator(); iter.hasNext();) {
					Object data = iter.next();
					executor.insert(statementName, data);
				}
				executor.executeBatch();
				return null;
			}
		});
		return true;
	}

	/**
	 * 批量更新 可指定提交记录数.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * @param batchSize the batch size
	 * 
	 * @return true, if batch4 update
	 */
	public boolean batch4Update(final String statementName, final List datas,final int batchSize) {
		getSqlMapClientTemplate().execute(new SqlMapClientCallback() {
			public Object doInSqlMapClient(SqlMapExecutor executor)
					throws SQLException {
				executor.startBatch();
				int batch = 0;
				for (Iterator iter = datas.iterator(); iter.hasNext();) {
					Map data = (Map) iter.next();
					executor.insert(statementName, data);
					batch++;
					if (batch > batchSize) {
						batch = 0;
						executor.executeBatch();
					}
				}
				if (batch > 0)
				{
					executor.executeBatch();
				}
				return null;
			}
		});
		return true;
	}
	
	/**
	 * 调用存储过程.
	 * 
	 * @param statementName the statement name
	 * @param params the params
	 * @param outParams the out params
	 * @param outParamType the out param type
	 * 
	 * @return outParamType
	 */
	public Object callProcedure(String statementName,List<Object> params,List<String> outParams,List<Integer> outParamType){
		Map<String,Object> map = null;
		try {
			Connection  conn = this.getSqlMapClientTemplate().getDataSource().getConnection();
			conn.setAutoCommit(false);
			CallableStatement cstmt = conn.prepareCall(statementName);
			for(int i = 0;i<params.size();i++){
				cstmt.setObject(i+1,params.get(i));
			}
			if(outParams!=null){
				for(int i = 0;i<outParams.size();i++){
					cstmt.registerOutParameter(i+params.size()+1,outParamType.get(i), outParams.get(i));
				}
			}
			cstmt.execute();
			if(outParams!=null){
				map = new HashMap<String,Object>();
				for(int i = 0;i<outParams.size();i++){
					map.put(outParams.get(i), cstmt.getObject(i+params.size()+1));
					cstmt.registerOutParameter(i+params.size()+1,outParamType.get(i), outParams.get(i));
				}
			}
			cstmt.close();
			conn.commit();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return map;
	}
	
	

	/** The _caches. */
	private Map _caches = new HashMap();

	/**
	 * Clear caches.
	 * 
	 * @param key the key
	 */
	public void clearCaches(Object key) {
		_caches.remove(key);
	}

	/**
	 * Put caches.
	 * 
	 * @param key the key
	 * @param value the value
	 */
	public void putCaches(Object key, Object value) {
		_caches.put(key, value);
	}

	/**
	 * Gets the caches.
	 * 
	 * @param key the key
	 * 
	 * @return the caches
	 */
	public Object getCaches(Object key) {
		return _caches.get(key);
	}

}
