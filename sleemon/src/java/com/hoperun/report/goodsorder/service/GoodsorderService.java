package com.hoperun.report.goodsorder.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public interface GoodsorderService extends IBaseRptService{
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
}
