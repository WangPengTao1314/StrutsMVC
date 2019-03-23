package com.hoperun.report.refundapply.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.refundapply.service.RefundapplyService;

/**
 * 报表——退款申请表
 * @author gu_hongxiu
 *
 */
public class RefundapplyServiceImpl  extends BaseRptService implements RefundapplyService {

	/**
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {
		
		StringBuffer selectSql = new StringBuffer("select ");
		selectSql.append(" t.TERM_NAME,t.CRE_NAME,to_char(y.SALE_DATE,'yyyy-mm-dd') SALE_DATE,y.CUST_NAME,y.TEL  ,(select wmsys.wm_concat( chr(13)|| ' ('|| rownum || ') ' || d.remark ) from DRP_ADVC_ORDER_DTL d where d.advc_order_id = t.advc_order_id  and d.remark is not null) remark ");
		selectSql.append(" from DRP_ADVC_ORDER t ");
		selectSql.append(" left join DRP_ADVC_ORDER y on t.FROM_BILL_ID = y.ADVC_ORDER_ID ");
		selectSql.append(whereSql);
						
		return selectSql.toString();
	}

}
