package com.hoperun.report.saleordersendout.service;

public interface SaleorderSendoutService {
	
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
	/**
	 * 生成新发运单报表
	 * @param whereSql
	 * @return
	 */
	public String createNewSql(String whereSql);
}
