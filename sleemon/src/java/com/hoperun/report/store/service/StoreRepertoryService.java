package com.hoperun.report.store.service;


public interface StoreRepertoryService {
	
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql,String ZTXXID);
	public String createSql(String whereSql);
	public String createSql(String whereSql,StringBuffer str);
	

}
