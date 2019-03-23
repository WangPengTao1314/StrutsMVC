/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.dao.BaseDao;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.inner.ListPage;
import com.hoperun.commons.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseService.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class BaseService implements IBaseService {

	/** The ibatis dao. */
	private BaseDao ibatisDao;
	
	/** The ibatis ods. */
	private BaseDao ibatisOds;
	
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#findListOds(java.lang.String, java.lang.Object)
	 */
	public List findListOds(String statementName, Object paramMap) {
		return ibatisOds.queryForList(statementName, paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#pageQueryOds(java.lang.String, java.lang.String, java.util.Map, int, int)
	 */
	public IListPage pageQueryOds(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize) {
		// 计数
		int total = ibatisOds.queryForInt(countStatementName, paramMap);
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;// 页码校验
		}
		int start = (pageNo - 1) * pageSize;
		int end = pageNo * pageSize;
		paramMap.put("startRow", new Integer(start));
		paramMap.put("endRow", new Integer(end));
		paramMap.put("pageSize", new Integer(pageSize));
		// 取单页结果集
		List data = ibatisOds.queryForList(queryStatementName, paramMap);
		return new ListPage(start, total, pageSize, data);
	}
	
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#getIbatisOds()
	 */
	public BaseDao getIbatisOds() {
		return ibatisOds;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#setIbatisOds(com.hoperun.commons.dao.BaseDao)
	 */
	public void setIbatisOds(BaseDao ibatisOds) {
		this.ibatisOds = ibatisOds;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#getIbatisDao()
	 */
	public BaseDao getIbatisDao() {
		return ibatisDao;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#setIbatisDao(com.hoperun.commons.dao.BaseDao)
	 */
	public void setIbatisDao(BaseDao ibatisDao) {
		this.ibatisDao = ibatisDao;
	}

	/*
	 * 新增
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#insert(java.lang.String, java.lang.Object)
	 */
	public Object insert(String statementName, Object paramMap) {
		return ibatisDao.insert(statementName, paramMap);
	}

	/*
	 * 更新
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#update(java.lang.String, java.lang.Object)
	 */
	public boolean update(String statementName, Object paramMap) {
		return ibatisDao.update(statementName, paramMap);
	}

	/*
	 * 删除
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#delete(java.lang.String, java.lang.Object)
	 */
	public boolean delete(String statementName, Object paramMap) {
		return ibatisDao.delete(statementName, paramMap);
	}
	
	/**
	 * 批量更新.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * 
	 * @return true, if batch4 update
	 */
	public boolean batch4Update(String statementName, List datas) {
		return ibatisDao.batch4Update(statementName, datas);
	}
	
	/**
	 * 批量更新（对象）.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * 
	 * @return true, if batch obj4 update
	 */
	public boolean batchObj4Update(String statementName, List datas) {
		return ibatisDao.batchObj4Update(statementName, datas);
	}
	
	/**
	 * 批量更新.
	 * 
	 * @param statementName the statement name
	 * @param datas the datas
	 * @param batchSize the batch size
	 * 
	 * @return true, if batch4 update
	 */
	public boolean batch4Update(String statementName, List datas, int batchSize) {
		return ibatisDao.batch4Update(statementName, datas, batchSize);
	}

	/*
	 * 加载单条记录
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#load(java.lang.String, java.lang.Object)
	 */
	public Object load(String statementName, Object paramMap) {
		return ibatisDao.load(statementName, paramMap);
	}

	/*
	 * 加载多条记录
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#findList(java.lang.String, java.lang.Object)
	 */
	public List findList(String statementName, Object paramMap) {
		return ibatisDao.queryForList(statementName, paramMap);
	}
	
	/*
	 * 加载自定义参数
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#findParamters(java.lang.String)
	 */
	public List findParamters(String paramType) {
		List rst = (List) ibatisDao.getCaches(paramType);
		if (rst != null) {
			return rst;
		}
		rst = ibatisDao.queryForList("SysvarInfo.findByVarType", paramType);
		ibatisDao.putCaches(paramType, rst);
		return rst;
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#clearCaches(java.lang.Object)
	 */
	public void clearCaches(Object key) {
		ibatisDao.clearCaches(key);
	}

	/**
	 * 加载参数.
	 * 
	 * @param varId the var id
	 * 
	 * @return the map
	 */
	public Map loadParameter(String varId) {
		Map rst = (Map) ibatisDao.getCaches(varId);
		if (rst != null) {
			return rst;
		}
		rst = (Map) ibatisDao.load("SysvarInfo.loadById", varId);
		ibatisDao.putCaches(varId, rst);
		return rst;
	}

	/**
	 * 加载报表.
	 * 
	 * @param rptId the rpt id
	 * 
	 * @return the map
	 */
	public Map loadReport(String rptId) {
		Map rst = (Map) ibatisDao.getCaches(rptId);
		if (rst != null) {
			return rst;
		}
		rst = (Map) ibatisDao.load("ReportInfo.loadById", rptId);
		ibatisDao.putCaches(rptId, rst);
		return rst;
	}

	/**
	 * 缓存用户.
	 * 
	 * @param userId the user id
	 * 
	 * @return the map
	 */
	public Map loadUser(String userId) {
		Map rst = (Map) ibatisDao.getCaches(userId);
		if (rst != null) {
			return rst;
		}
		rst = (Map) ibatisDao.load("UserInfo.loadById", userId);
		ibatisDao.putCaches(userId, rst);
		return rst;
	}

	/*
	 * 按默认行数分页
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#pageQuery(java.lang.String, java.lang.String, java.util.Map, int)
	 */
	public IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo) {
		int pageSize =  Consts.PAGE_SIZE;
		if(!StringUtils.isEmpty((String)paramMap.get("pageSize"))){
			pageSize = Integer.parseInt((String) paramMap.get("pageSize"));
		}
		return pageQuery(queryStatementName, countStatementName, paramMap,
				pageNo, pageSize);
	}

	/*
	 * 按指定行数分页
	 */
	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#pageQuery(java.lang.String, java.lang.String, java.util.Map, int, int)
	 */
	public IListPage pageQuery(String queryStatementName,
			String countStatementName, Map paramMap, int pageNo, int pageSize) {
		// 计数
		int total = ibatisDao.queryForInt(countStatementName, paramMap);
		int _pageNo = (total - 1) / pageSize + 1;
		if (_pageNo < pageNo) {
			pageNo = _pageNo;// 页码校验
		}
		int start = (pageNo - 1) * pageSize;
		int end = pageNo * pageSize;
		paramMap.put("startRow", new Integer(start));
		paramMap.put("endRow", new Integer(end));
		paramMap.put("pageSize", new Integer(pageSize));
		// 取单页结果集
		List data = ibatisDao.queryForList(queryStatementName, paramMap);
		return new ListPage(start, total, pageSize, data);
	}

	/* (non-Javadoc)
	 * @see com.hoperun.commons.service.IBaseService#saveLog(java.util.Map)
	 */
	public boolean saveLog(Map paramMap) {
		this.insert("TLER.LOGS.insert", paramMap);
		return true;
	}
	
	/**
	 * 可用于计数.
	 * 
	 * @param statementName the statement name
	 * @param paramMap the param map
	 * 
	 * @return the int
	 */
	public int queryForInt(String statementName, Object paramMap){
		return ibatisDao.queryForInt(statementName, paramMap);
	}
	
	/**
	 * 调用存储过程.
	 * 
	 * @param statementName the statement name
	 * @param params the params
	 * @param outParams the out params
	 * @param outParamType the out param type
	 * 
	 * @return the object
	 */
	public Object callProcedure(String statementName,List<Object> params,List<String> outParams,List<Integer> outParamType){
		return ibatisDao.callProcedure(statementName,params,outParams,outParamType);
	}
	
}
