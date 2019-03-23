package com.hoperun.report.noshippment.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.noshippment.service.NoshippmentService;

/**
 * 报表——未出货统计表
 * @author gu_hongxiu
 *
 */
public class NoshippmentServiceImpl  extends BaseRptService implements NoshippmentService {

	/**
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {
		String queryParam = " a.SALE_ORDER_NO,        a.AREA_NAME,        a.CRE_NAME,        a.ORDER_CHANN_NO,        a.ORDER_CHANN_NAME,        to_char(a.ORDER_DATE,'YYYY-MM-DD') ORDER_DATE,        to_char(a.CRE_TIME, 'yyyy-mm-dd') AUDIT_DATE,        b.PRD_NO,        b.PRD_NAME,        b.PRD_SPEC,        b.BRAND,        NVL(c.REMARK, '') REMARK,        b.ORDER_NUM,        b.SENDED_NUM,        (NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0)) NO_SEND_NUM,        b.PRICE,        b.PRICE * (NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0)) NO_SEND_AMOUNT,        b.ORDER_AMOUNT,        b.VOLUME,        a.RECV_ADDR_NO,        d.DELIVER_DTL_ADDR,        f.PRD_NAME PAR_PRD_NAME,        a.STATE ";
		String sumParam = "  '合计' SALE_ORDER_NO,                '' AREA_NAME,                '' CRE_NAME,                '' ORDER_CHANN_NO,                '' ORDER_CHANN_NAME,                '' ORDER_DATE,                '' AUDIT_DATE,                '' PRD_NO,                '' PRD_NAME,                '' PRD_SPEC,                '' BRAND,                '' REMARK,                sum(NVL(b.ORDER_NUM, 0)) ORDER_NUM,                sum(NVL(b.SENDED_NUM, 0)) SENDED_NUM,                sum((NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0))) NO_SEND_NUM,                null PRICE,                null NO_SEND_AMOUNT,                sum(NVL(b.ORDER_AMOUNT, 0)) ORDER_AMOUNT,                null VOLUME,                '' RECV_ADDR_NO,                '' DELIVER_DTL_ADDR,                '' PAR_PRD_NAME,                '' STATE ";
		
		String fromSql = " from         ERP_SALE_ORDER a               left join ERP_SALE_ORDER_DTL b on a.SALE_ORDER_ID=b.SALE_ORDER_ID               left join DRP_SPCL_TECH_DTL c on b.SPCL_TECH_ID=c.SPCL_TECH_ID and  c.SPCL_TECH_TYPE='非标工艺说明'               left join BASE_DELIVER_ADDR d on a.RECV_ADDR_NO =d.DELIVER_ADDR_NO d.DEL_FLAG =0 and d.STATE='启用'              left join BASE_PRODUCT e on b.PRD_ID=e.PRD_ID               left join BASE_PRODUCT f on e.PAR_PRD_ID=f.PRD_ID ";
		
		String orderParam = " ORDER_DATE asc ";
				
		return formatSql(queryParam,sumParam,orderParam,fromSql,whereSql);
	}

}
