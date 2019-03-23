package com.hoperun.report.allocatecount.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——调拨表统计
 * @author gu_hongxiu
 *
 */
public interface AllocatecountService extends IBaseRptService{
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
}
