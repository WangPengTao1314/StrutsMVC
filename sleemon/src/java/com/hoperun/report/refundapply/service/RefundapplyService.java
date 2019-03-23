package com.hoperun.report.refundapply.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——退款申请表
 * @author gu_hongxiu
 *
 */
public interface RefundapplyService extends IBaseRptService{
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
}
