package com.hoperun.report.termsaletrace.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——门店销售跟踪表
 * @author gu_hongxiu
 *
 */
public interface TermsaletraceService extends IBaseRptService{
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
}
