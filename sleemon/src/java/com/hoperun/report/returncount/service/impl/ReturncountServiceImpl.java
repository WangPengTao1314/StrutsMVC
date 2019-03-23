package com.hoperun.report.returncount.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.returncount.service.ReturncountService;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public class ReturncountServiceImpl  extends BaseRptService implements ReturncountService {

	@Override
	public String createOneSql(String whereSql) {
		StringBuffer sql = new StringBuffer();
		sql.append("select rownum,to_char(t.CRE_TIME, 'YYYY-MM-DD') rt_date,t.CUST_NAME,t.TEL,t.TERM_NO,t.TERM_NAME,d.PRD_NO,d.prd_name,d.prd_spec,d.order_num,to_char(tt.sale_date,'YYYY-MM-DD') sale_date,")
		   .append(" tt.advc_order_no,tt.payable_amount,t.payable_amount rt_amout,CONCAT(CONCAT('姓名:', t.cust_name), CONCAT(',手机:', t.tel)) cust_info,d.remark  ")
		   .append(" from drp_advc_order t ")
		   	.append(" left join DRP_ADVC_ORDER_DTL d on t.advc_order_id = d.advc_order_id ")
		   	.append(" left join DRP_ADVC_ORDER tt on tt.advc_order_id = t.from_bill_id ")
		   	.append(" left join BASE_PRODUCT prd on prd.PRD_ID=d.PRD_ID and prd.DEL_FLAG=0 ")
		   .append(" where t.bill_type = '终端退货'and t.del_flag = 0 and t.state <> '未提交' ");
       
		return sql.append(whereSql).toString();
	}
}
