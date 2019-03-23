package com.hoperun.report.inventory.service;

import com.hoperun.report.IBaseRptService;

/**
 * 报表——门店库存
 * @author gu_hongxiu
 *
 */
public interface InventoryService extends IBaseRptService{
	/**
	 * 生成报表sql
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql);
}
