package com.hoperun.report.allocatecount.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.allocatecount.service.AllocatecountService;

/**
 * 报表——调拨表统计
 * @author gu_hongxiu
 *
 */
public class AllocatecountServiceImpl  extends BaseRptService implements AllocatecountService {

	/**
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {		
		String queryParam = " to_char(t.CRE_TIME, 'YYYY-MM-DD') ALLO_DATE,t.ALLOC_OUT_STORE_NO, t.ALLOC_OUT_STORE_NAME, n.DEF_STORE_NO,n.def_store_name, d.prd_no, d.prd_name, d.prd_spec, d.alloc_num,d.dect_price, d.remark ";
		String sumParam = "  '合计' ALLO_DATE,'' ALLOC_OUT_STORE_NO,'' ALLOC_OUT_STORE_NAME, '' DEF_STORE_NO,'' def_store_name, '' prd_no, '' prd_name,'' prd_spec, sum(NVL(d.alloc_num, 0))  alloc_num, null dect_price, '' remark ";
		
		String fromSql = " from DRP_ALLOCATE t  left join Drp_Allocate_Dtl d on t.allocate_id = d.allocate_id left join drp_storeout s on s.from_bill_id = t.allocate_id left join DRP_STOREIN_NOTICE n on n.from_bill_id = s.storeout_id ";
		
		String orderParam = " ALLO_DATE asc ";
				
		return formatSql(queryParam,sumParam,orderParam,fromSql,whereSql);
	}

}
