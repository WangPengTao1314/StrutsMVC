package com.hoperun.report.returncount.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public interface ReturncountService extends IBaseRptService{
	/**
	 * 主体字段的sql
	 * @param whereSql
	 * @return
	 */
	public String createOneSql(String whereSql);
	
}
