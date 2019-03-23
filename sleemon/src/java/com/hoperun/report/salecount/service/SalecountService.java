package com.hoperun.report.salecount.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public interface SalecountService extends IBaseRptService{
	/**
	 * 主体字段的sql
	 * @param whereSql
	 * @return
	 */
	public String createOneSql(String whereSql);
	
	/**
	 * 已付金额的sql
	 * @param whereSql
	 * @return
	 */
	public String createTwoSql();
	
	/**
	 * 最新的sql语句
	 * @return
	 */
	public String createNewSql();
}
