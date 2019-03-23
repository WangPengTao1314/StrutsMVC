package com.hoperun.report.salecount.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.salecount.service.SalecountService;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public class SalecountServiceImpl  extends BaseRptService implements SalecountService {

	@Override
	public String createOneSql(String whereSql) {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum,t.advc_order_id,t.SALE_PSON_NAME,t.SALE_DATE,t.ORDER_RECV_DATE,t.ADVC_ORDER_NO,t.PAYABLE_AMOUNT,'单品' taocan,d.prd_type,")
		   .append("d.prd_type,d.prd_name,d.prd_spec,d.order_num,d.dect_price,b.FACT_PRICE,decode(t.bill_type,'终端销售','否','是') bill_type,t.TEL,t.RECV_ADDR ")
		   .append(" from DRP_ADVC_ORDER t")
		   	.append(" left join DRP_ADVC_ORDER_DTL d on t.advc_order_id = d.advc_order_id")
		   	.append(" left join base_product b on b.prd_id = d.prd_id ")
		   .append(" WHERE t.state = '提交' and t.del_flag = 0 ");
       
		return sql.append(whereSql).toString();
	}

	@Override
	public String createTwoSql() {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select t.advc_order_id,d.PAY_TYPE,d.PAY_AMONT, t.PAYABLE_AMOUNT ")
		   .append(" from DRP_PAYMENT_DTL d , DRP_ADVC_ORDER t  ")
		   .append(" WHERE t.advc_order_id = d.advc_order_id group by t.advc_order_id,d.PAY_TYPE,d.PAY_AMONT,t.PAYABLE_AMOUNT ");
		
	    return sql.toString();
	}

	@Override
	public String createNewSql() {
		StringBuffer sql = new StringBuffer();
		
		sql.append(" select t.ADVC_ORDER_ID,t.TERM_NAME,t.CRE_NAME,t.sale_date,d.ORDER_RECV_DATE,t.ADVC_ORDER_NO,NVL(t.TOTAL_AMOUNT, 0) TOTAL_AMOUNT,NVL(t.PAYABLE_AMOUNT,0)PAYABLE_AMOUNT,d.DECT_PRICE,i.REAL_NUM SEND_NUM,")
		   			.append(" (select sum(NVL(p.ADVC_AMOUNT, 0)) from DRP_STATEMENTS p ")
		   			.append(" where p.ADVC_ORDER_ID = t.ADVC_ORDER_ID and p.DEL_FLAG = 0 and p.state in ('已结算', '提交', '已核销')) ADVC_AMOUNT,")
		   			.append("nvl(t.DISCOUNT_AMOUNT, 0) DISCOUNT_AMOUNT,t.BILL_TYPE,d.PRD_NAME,d.PRD_SPEC,d.order_num,t.remark,nvl(d.dect_price,0) dect_price,t.tel,t.recv_addr,d.SEND_NUM,to_char(h.DEAL_TIME,'yyyy-mm-dd')DEAL_TIME ")
		   			.append(" from drp_advc_order t ")
					.append(" left join drp_advc_order_dtl d on t.advc_order_id = d.advc_order_id ")
					.append(" left join BASE_PRODUCT e on e.PRD_ID = d.PRD_ID ")
					.append(" left join DRP_ADVC_SEND_REQ f on t.ADVC_ORDER_ID=f.FROM_BILL_ID and f.STATE  not in ('未提交','提交') and f.DEL_FLAG=0 ")
					.append(" left join DRP_ADVC_SEND_REQ_DTL g on g.ADVC_SEND_REQ_ID=f.ADVC_SEND_REQ_ID and g.FROM_BILL_DTL_ID=d.ADVC_ORDER_DTL_ID and g.DEL_FLAG=0 ")
					.append(" left join drp_storeout h on h.from_bill_id=f.advc_send_req_id and h.state='已处理' and h.del_flag=0 and h.bill_type='销售出库' ")
					.append(" left join drp_storeout_dtl i on i.storeout_id=h.storeout_id and i.del_flag=0 and i.from_bill_dtl_id=g.advc_send_req_dtl_id ")
					.append(" where t.del_flag = 0 and d.STATE='正常' and t.bill_type = '终端销售'  and t.state in ('待结算', '已发货', '待发货', '审核通过', '待退货', '待核价', '待确认', '已结算', '部分发货') ");
		return sql.toString();
	}


}
