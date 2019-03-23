package com.hoperun.report.noshippment.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——未出货统计表
 * @author gu_hongxiu
 *
 */
public interface NoshippmentService extends IBaseRptService{
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
}
