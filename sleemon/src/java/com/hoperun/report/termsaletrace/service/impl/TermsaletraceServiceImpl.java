package com.hoperun.report.termsaletrace.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.termsaletrace.service.TermsaletraceService;

/**
 * 报表——门店销售跟踪表
 * @author gu_hongxiu
 *
 */
public class TermsaletraceServiceImpl  extends BaseRptService implements TermsaletraceService {

	/**
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {
		StringBuffer selectSql = new StringBuffer("select ");
		selectSql.append(" rownum,t.term_name,to_char(t.sale_date, 'YYYY-MM-DD') sale_date,d.brand,d.prd_name,d.std_unit,d.prd_spec,t.advc_order_no,d.price,d.dect_rate,d.dect_price,p.PRVD_PRICE,t.cre_time,to_char(t.CRE_TIME, 'YYYY-MM-DD') buyDate,to_char(t.order_recv_date, 'YYYY-MM-DD') order_recv_date ");
		selectSql.append(" from drp_advc_order t ");
		selectSql.append(" left join drp_advc_order_dtl d on t.advc_order_id = d.advc_order_id ");
		selectSql.append(" left join base_product p on p.prd_id = d.prd_id ");
		selectSql.append(whereSql);
						
		return selectSql.toString();
	}

}
