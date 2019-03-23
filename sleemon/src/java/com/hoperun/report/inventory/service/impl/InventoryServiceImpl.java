package com.hoperun.report.inventory.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.inventory.service.InventoryService;

/**
 * 报表——门店库存
 * @author gu_hongxiu
 *
 */
public class InventoryServiceImpl  extends BaseRptService implements InventoryService {

	/**
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {
		String queryParam = " p.prd_name,p.remark,p.prd_spec,a.STORE_NUM,p.PRVD_PRICE,NVL(a.STORE_NUM,0)*NVL(p.PRVD_PRICE,0) PRVD_ACCOUNT ";
		String sumParam = " '合计' prd_name, '' remark, '' prd_spec, sum(NVL(a.STORE_NUM, 0)) STORE_NUM, null PRVD_PRICE, sum(NVL(a.STORE_NUM,0)*NVL(p.PRVD_PRICE,0)) PRVD_ACCOUNT ";
		
		String fromSql = " from DRP_STORE_ACCT a left join BASE_PRODUCT p on a.prd_id =  p.prd_id ";
		
		String orderParam = "";//" ORDER_DATE asc ";
				
		return formatSql(queryParam,sumParam,orderParam,fromSql,whereSql);
	}

}
