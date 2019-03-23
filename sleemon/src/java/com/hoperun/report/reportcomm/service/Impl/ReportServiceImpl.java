package com.hoperun.report.reportcomm.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.mkmdayreport.model.MkmDayReportModel;
import com.hoperun.report.reportcomm.service.ReportService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表实现类
 * 
 * @author zhuxw
 * 
 */
public class ReportServiceImpl extends BaseService implements ReportService {

	/**
	 * 更新
	 * 
	 * @param params
	 * @return
	 */
	public boolean txUpdcom(Map params) {
		return update("sqlcom.update", params);
	}

	public void batch4Update(List datas) {
		batch4Update("sqlcom.update", datas);
	}

	/**
	 * 分页查询 Description :
	 * 
	 * @param params
	 * @param pageNo
	 * @return
	 */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("LXLL.pageQuery", "LXLL.pageCount", params,
				pageNo);
	}

	// 通用增删改查
	public Map selecTotalCount(Map params) {
		return (Map) load("sqlcom.query", params);
	}

	// 查询渠道信息
	public Map queryChannInfo(String CHANN_ID) {
		return (Map) load("chann.queryChannInfo", CHANN_ID);
	}

	/**
	 * 信用流水账查询sql
	 * 
	 * @param userBean
	 * @return
	 */
	public String getCreditAcctFXSQL(UserBean userBean, String conSql) {
		String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
		String CHANN_ID = userBean.getCHANN_ID();

		StringBuffer sqlBuf = new StringBuffer();

		sqlBuf
				.append("  select sys_guid(),")
				.append("'货品发运' BUSS_TYPE,")
				.append("t.deliver_order_id,")
				.append("t.deliver_order_no BILL_NO,")
				.append("d.deliver_order_dtl_id,")
				.append("t.ledger_id,")
				.append("t.order_chann_id CHANN_ID,")
				.append("t.order_chann_no CHANN_NO,")
				.append("t.order_chann_name CHANN_NAME,")
				.append("d.prd_id,")
				.append("d.spcl_tech_id,")
				.append("d.prd_no,")
				.append("d.prd_name,")
				.append("d.prd_spec,")
				.append("d.prd_color,")
				.append("d.brand,")
				.append("d.std_unit,")
				.append("d.dect_price,")
				.append("d.plan_num PRD_NUM,")
				.append(
						"(NVL(d.dect_price, 0) * NVL(d.plan_num, 0)) PRD_AMOUNT,")
				.append("to_char(t.cre_time, 'yyyy-MM-DD') DEAL_TIME,")
				.append("d.credit_freeze_price,")
				.append(
						"NVL(d.credit_freeze_price,0) *NVL(d.plan_num, 0)credit_freeze_AMOUNT,")
				.append("to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
				.append(" 1 ")
				.append("from erp_deliver_order t, erp_deliver_order_dtl d")
				.append(" where ")
				.append(" t.deliver_order_id = d.deliver_order_id ")
				.append(" and t.order_chann_id = '")
				.append(CHANN_ID)
				.append("' ")
				.append(" and t.del_flag = 0")
				.append(" and d.del_flag = 0")
				.append(" and nvl(d.IS_FREE_FLAG, 0) = 0")
				.append(" and t.state in ('已提交库房','部分发货','已发货')")
				.append(conSql)
				.append(" union all ")
				.append("select sys_guid(),")
				.append("'货品发运' BUSS_TYPE,")
				.append("t.deliver_order_id,")
				.append(" t.deliver_order_no BILL_NO,")
				.append("d.deliver_order_dtl_id,")
				.append(" t.ledger_id,")
				.append("t.order_chann_id,")
				.append("t.order_chann_no,")
				.append("t.order_chann_name,")
				.append("d.prd_id,")
				.append("d.spcl_tech_id,")
				.append("d.prd_no,")
				.append("d.prd_name,")
				.append("d.prd_spec,")
				.append("d.prd_color,")
				.append("d.brand,")
				.append("d.std_unit,")
				.append("d.dect_price,")
				.append("d.real_send_num PRD_NUM,")
				.append(
						"(NVL(d.dect_price,0)*NVL(d.real_send_num,0)) PRD_AMOUNT,")
				.append("to_char(t.cre_time, 'yyyy-MM-DD') DEAL_TIME,")
				.append("d.credit_freeze_price,")
				.append(
						"NVL(d.credit_freeze_price,0) *NVL(d.real_send_num, 0)credit_freeze_AMOUNT,")
				.append("to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,").append(
						"1 ").append(
						"from erp_deliver_order t, erp_deliver_order_dtl d ")
				.append("  where ").append(
						" t.deliver_order_id = d.deliver_order_id ").append(
						"  and t.order_chann_id = '").append(CHANN_ID).append(
						"' ").append("  and t.del_flag = 0").append(
						"  and d.del_flag = 0").append(
						"  and nvl(d.IS_FREE_FLAG, 0) = 0").append(
						"  and t.state in ('待收货','已收货')").append(conSql);

		return sqlBuf.toString();

	}

	/**
	 * 信用流水账查询 总部sql
	 * 
	 * @param userBean
	 * @return
	 */
	public String getCreditAcctSQL(UserBean userBean, String conSql) {
		StringBuffer sqlBuf = new StringBuffer();
		// 总部人员查 流水账表
		sqlBuf
				.append(
						"select t.BUSS_TYPE,t.BILL_ID,t.BILL_NO,t.BILL_DTL_ID,t.CHANN_ID,t.CHANN_NO,t.CHANN_NAME,t.PRD_ID,t.PRD_NO,")
				.append(
						"t.PRD_NAME,t.PRD_SPEC, t.brand,t.dect_price,t.prd_num,t.prd_amount,substr(t.deal_time, 0, 10) DEAL_TIME,t.FREEZE_PRICE ,")
				.append(
						"NVL(t.FREEZE_PRICE,0)*t.PRD_NUM CREDIT_FREEZE_AMOUNT, ")
				.append(
						"(case when t.DIRECTION =1 then '减' when t.DIRECTION =0 then '加' end ) DIRECTION")
				.append(" from BASE_JOURNAL_CREDIT_ACCT t where 1=1 ").append(
						conSql).append("   order by t.deal_time desc ");
		return sqlBuf.toString();
	}

	/**
	 * 统计流水账 DIRECTION=0加 DIRECTION=1减
	 * 
	 * @param conSql
	 * @return
	 */
	public List queryCreditTolal(String conSql) {
		Map<String, String> paramMap = new HashMap<String, String>();
		String sql = "select  sum(NVL(t.prd_amount,0))PRD_AMOUNT,sum(NVL(t.FREEZE_PRICE, 0) * t.PRD_NUM)CREDIT_FREEZE_AMOUNT, "
				+ "t.DIRECTION from BASE_JOURNAL_CREDIT_ACCT t where 1=1 "
				+ conSql + "  group by t.direction";

		paramMap.put("SelSQL", sql);
		return this.findList("sqlcom.query", paramMap);
	}

	/**
	 * 门店销售出货统计sql
	 * 
	 * @param userBean
	 * @param conSql
	 * @return
	 */

	public String getTermSaleSendSql(UserBean userBean, String conSql,
			String conSqloT) {
		String sql =
			      "  select  t.TERM_NAME,t.SALE_PSON_NAME,t.IN_OUT_NO, t.BILL_TYPE,t.IN_OUT_DATE,t.TEL, t.PROMOTE_NO,"
			    + "t.PROMOTE_NAME,t.RECV_ADDR, t.ADVC_ORDER_NO,t.CONTRACT_NO,t.STATEMENTS_AMOUNT, t.PRD_NO,"
			    + "t.PRD_NAME,t.PRD_SPEC,t.SPCL_DTL_REMARK,t.PAR_PRD_NAME,t.ORDER_NUM, t.DECT_PRICE,t.REAL_NUM,"
			    + "t.REAL_AMOUNT,t.COST_PRICE,t.COST_AMOUNT,t.PROFIT_AMOUNT,t.PROFIT_SCALING,"
			    + "SUM(t.DECT_PRICE) over() TOTAL_DECT_PRICE, SUM(t.REAL_NUM) over() TOTAL_REAL_NUM,SUM(t.REAL_AMOUNT) over() TOTAL_REAL_AMOUNT,"
			    + "SUM(t.COST_PRICE) over() TOTAL_COST_PRICE,SUM(t.COST_AMOUNT) over() TOTAL_COST_AMOUNT, SUM(t.PROFIT_AMOUNT) over() TOTAL_PROFIT_AMOUNT"
			    + " from ("
			    + " select temp.*,(NVL(u.COST_PRICE, 0) * NVL(temp.REAL_NUM, 0)) COST_AMOUNT,NVL(u.COST_PRICE, 0)COST_PRICE,"
				+ " (case when temp.REAL_AMOUNT!=0 then (temp.REAL_AMOUNT-(NVL(u.COST_PRICE, 0) * NVL(temp.REAL_NUM, 0))) else 0 end) PROFIT_AMOUNT, "
				+ " (case when temp.REAL_AMOUNT!=0 then (temp.REAL_AMOUNT-(NVL(u.COST_PRICE, 0) * NVL(temp.REAL_NUM, 0)))/temp.REAL_AMOUNT else 0 end) PROFIT_SCALING "
				+ " from (select "
				+ "a.TERM_NAME,a.SALE_PSON_NAME,a.STOREOUT_NO IN_OUT_NO,'销售出库' BILL_TYPE,to_char(DEAL_TIME,'yyyy-mm-dd')IN_OUT_DATE,e.ADVC_ORDER_NO,"
				+ "e.TEL,e.RECV_ADDR,"
				+ "b.PRD_NO,b.PRD_NAME,b.PRD_SPEC,p.PAR_PRD_NO,p.PAR_PRD_NAME, t.SPCL_DTL_REMARK ,  f.ORDER_NUM , f.PRD_TYPE,b.REAL_NUM,"
				+ "(b.REAL_NUM*decode(f.PRD_TYPE,'赠品',0,b.DECT_PRICE)) REAL_AMOUNT,decode(f.PRD_TYPE,'赠品',0,b.DECT_PRICE)DECT_PRICE,"
				+ "e.PROMOTE_NO,e.PROMOTE_NAME,e.CONTRACT_NO, "
				+ " stm.STATEMENTS_AMOUNT, " 
				+ " p.PRD_ID,t.SPCL_TECH_ID "
				+ " from "
				+ "DRP_STOREOUT a " +
						" left join DRP_STOREOUT_DTL b on a.STOREOUT_ID = b.STOREOUT_ID and b.DEL_FLAG = 0 " +
						" left join BASE_PRODUCT p  on b.PRD_ID=p.PRD_ID " +
						" left join DRP_SPCL_TECH t on b.SPCL_TECH_ID = t.SPCL_TECH_ID " + 
						" left join DRP_ADVC_SEND_REQ_DTL d on b.FROM_BILL_DTL_ID = d.ADVC_SEND_REQ_DTL_ID and d.DEL_FLAG = 0 " +
						" left join DRP_ADVC_SEND_REQ c on c.ADVC_SEND_REQ_ID = d.ADVC_SEND_REQ_ID and c.DEL_FLAG = 0 " +
						" left join DRP_ADVC_ORDER_DTL f on d.FROM_BILL_DTL_ID = f.ADVC_ORDER_DTL_ID and f.DEL_FLAG = 0 " +
						" left join DRP_ADVC_ORDER e on e.ADVC_ORDER_ID = f.ADVC_ORDER_ID and e.DEL_FLAG = 0 " +
						" left join (select sum(st.STATEMENTS_AMOUNT) STATEMENTS_AMOUNT,st.ADVC_ORDER_ID from DRP_STATEMENTS st where st.DEL_FLAG = 0 and st.STATE = '已结算' and st.BILL_TYPE = '销售退款' group by st.ADVC_ORDER_ID) stm on stm.ADVC_ORDER_ID = e.advc_order_id "
				+ " where "
				+ " a.DEL_FLAG=0 "
				+ "and a.DEAL_FLAG=1 " + "and a.BILL_TYPE='销售出库' "
				+ conSql
				+ " union all "
				+ "select "
				+ " e.TERM_NAME,e.SALE_PSON_NAME,a.STOREIN_NO IN_OUT_NO  ,'退货入库' BILL_TYPE,to_char(DEAL_TIME,'yyyy-mm-dd')IN_OUT_DATE,e.ADVC_ORDER_NO,"
				+ "e.TEL,e.RECV_ADDR,"
				+ " b.PRD_NO,b.PRD_NAME,b.PRD_SPEC,p.PAR_PRD_NO,p.PAR_PRD_NAME, t.SPCL_DTL_REMARK , b.NOTICE_NUM ORDER_NUM, f.PRD_TYPE,b.REAL_NUM*-1 REAL_NUM,"
				+ "  (decode(f.PRD_TYPE, '赠品', 0,b.DECT_AMOUNT)) * -1 REAL_AMOUNT, decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE) DECT_PRICE,"
				+ "e.PROMOTE_NO,e.PROMOTE_NAME,e.CONTRACT_NO ,"
				+ " stm.STATEMENTS_AMOUNT,p.PRD_ID,t.SPCL_TECH_ID "
				+ " from "
				+ " DRP_STOREIN a " +
						" left join DRP_STOREIN_DTL b on a.STOREIN_ID = b.STOREIN_ID and b.DEL_FLAG = 0 " +
						" left join BASE_PRODUCT p  on b.PRD_ID=p.PRD_ID " +
						" left join DRP_SPCL_TECH t on b.SPCL_TECH_ID = t.SPCL_TECH_ID " +
						" left join DRP_ADVC_ORDER_DTL f on b.FROM_BILL_DTL_ID = f.ADVC_ORDER_DTL_id and f.DEL_FLAG = 0 " +
						" left join DRP_ADVC_ORDER e on e.ADVC_ORDER_ID = f.ADVC_ORDER_ID and e.DEL_FLAG = 0 " + 
						" left join (select sum(st.STATEMENTS_AMOUNT) STATEMENTS_AMOUNT,ADVC_ORDER_ID from DRP_STATEMENTS st where st.DEL_FLAG = 0 and st.STATE = '已结算' and st.BILL_TYPE = '销售退款' group by st.ADVC_ORDER_ID) stm on stm.ADVC_ORDER_ID = e.ADVC_ORDER_ID "
				+ " where "
				+ "  a.DEL_FLAG=0"
				+ " and a.DEAL_FLAG=1"
				+ " and e.BILL_TYPE='终端退货'"
				+ conSqloT
				+ ") temp "
				+ " left join  V_PRODUCT_PRVD_PRICE u on  temp.PRD_ID = u.PRD_ID and NVL(temp.SPCL_TECH_ID, 'NONE') = NVL(u.SPCL_TECH_ID, 'NONE')   ) t ";

		return sql;

	}

	/**
	 * 门店销售出货统计sql 按货品分类查询
	 * 
	 * @param conSql
	 * @param conSqloT
	 * @return
	 */
	public String getTermSaleSendByPrdParentSql(String conSql, String conSqloT) {
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT t.TERM_NAME,t.SALE_PSON_NAME,t.IN_OUT_NO,t.BILL_TYPE,t.IN_OUT_DATE,t.PROMOTE_NO,t.PROMOTE_NAME,t.ADVC_ORDER_NO, ")
		        .append(" t.CONTRACT_NO,t.PAR_PRD_NAME,t.ORDER_NUM,t.REAL_NUM,t.DECT_PRICE,t.REAL_AMOUNT,")
		        .append(" SUM(t.ORDER_NUM) over() TOTAL_ORDER_NUM,SUM(t.DECT_PRICE) over() TOTAL_DECT_PRICE,")
		        .append(" SUM(t.REAL_NUM) over() TOTAL_REAL_NUM,SUM(t.REAL_AMOUNT) over() TOTAL_REAL_AMOUNT")
		        .append(" FROM (")
				.append("select a.TERM_NAME,")
				.append("a.SALE_PSON_NAME,")
				.append("a.STOREOUT_NO IN_OUT_NO,")
				.append("'销售出库' BILL_TYPE,")
				.append("to_char(a.DEAL_TIME, 'yyyy-mm-dd') IN_OUT_DATE,")
				.append("e.ADVC_ORDER_NO,")
				.append("p.PAR_PRD_NO,")
				.append("p.PAR_PRD_NAME,")
				.append("t.SPCL_DTL_REMARK,")
				.append("NVL(sum(f.ORDER_NUM), 0) ORDER_NUM,")
				.append("NVL(sum(b.REAL_NUM), 0) REAL_NUM,")
				.append(
						"(NVL(sum(b.REAL_NUM), 0) * decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE)) REAL_AMOUNT,")
				.append("decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE) DECT_PRICE,")
				.append("e.PROMOTE_NO,")
				.append("e.PROMOTE_NAME,")
				.append("e.CONTRACT_NO, ")
				.append(" sum(NVL(u.COST_PRICE, 0))COST_PRICE, ")
				.append(
						" (sum(NVL(u.COST_PRICE, 0)) * sum(NVL(b.REAL_NUM, 0))) COST_AMOUNT, ")
				.append(
						" (case when (NVL(sum(b.REAL_NUM), 0) * decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE))!=0 then ((NVL(sum(b.REAL_NUM), 0) * decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE))-sum(NVL(u.COST_PRICE, 0))) else 0 end) PROFIT_AMOUNT,")
				.append(
						" (case when (NVL(sum(b.REAL_NUM), 0) * decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE))!=0 then ((NVL(sum(b.REAL_NUM), 0) * decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE))-sum(NVL(u.COST_PRICE, 0)))/(NVL(sum(b.REAL_NUM), 0) * decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE)) else 0 end) PROFIT_SCALING ")
				.append(" from DRP_STOREOUT a, DRP_STOREOUT_DTL b ")
				.append(" left join BASE_PRODUCT p ")
				.append(" on b.PRD_ID = p.PRD_ID ")
				.append(" left join DRP_SPCL_TECH t ")
				.append(
						" on b.SPCL_TECH_ID = t.SPCL_TECH_ID, DRP_ADVC_SEND_REQ c, ")
				.append(
						" DRP_ADVC_SEND_REQ_DTL d, DRP_ADVC_ORDER e, DRP_ADVC_ORDER_DTL f ")
				.append(
						" left join  V_PRODUCT_PRVD_PRICE u on  f.PRD_ID = u.PRD_ID and NVL(f.SPCL_TECH_ID, 'NONE') = NVL(u.SPCL_TECH_ID, 'NONE') ")
				.append(" where a.STOREOUT_ID = b.STOREOUT_ID ")
				.append(" and c.ADVC_SEND_REQ_ID = d.ADVC_SEND_REQ_ID ")
				.append(" and e.ADVC_ORDER_ID = f.ADVC_ORDER_ID ")
				.append(" and b.FROM_BILL_DTL_ID = d.ADVC_SEND_REQ_DTL_ID ")
				.append(" and d.FROM_BILL_DTL_ID = f.ADVC_ORDER_DTL_ID ")
				.append(
						" and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 and c.DEL_FLAG = 0 and d.DEL_FLAG = 0 and e.DEL_FLAG = 0 ")
				.append(
						" and f.DEL_FLAG = 0 and a.DEAL_FLAG = 1 and a.BILL_TYPE = '销售出库' ")
				.append(conSql)
				.append(" group by a.TERM_NAME,")
				.append("a.SALE_PSON_NAME,")
				.append("a.STOREOUT_NO,")
				.append("a.DEAL_TIME,")
				.append("e.ADVC_ORDER_NO,")
				.append("p.PAR_PRD_NO,")
				.append("p.PAR_PRD_NAME,")
				.append("t.SPCL_DTL_REMARK,")
				.append("f.PRD_TYPE,")
				.append("b.DECT_PRICE,")
				.append("e.PROMOTE_NO,")
				.append("e.PROMOTE_NAME,")
				.append("e.CONTRACT_NO ")
				.append(" union all ")
				.append("select e.TERM_NAME,")
				.append("e.SALE_PSON_NAME,")
				.append("a.STOREIN_NO IN_OUT_NO,")
				.append("'退货入库' BILL_TYPE,")
				.append("to_char(a.DEAL_TIME, 'yyyy-mm-dd') IN_OUT_DATE,")
				.append("e.ADVC_ORDER_NO,")
				.append("p.PAR_PRD_NO,")
				.append("p.PAR_PRD_NAME,")
				.append("t.SPCL_DTL_REMARK,")
				.append("NVL(sum(b.NOTICE_NUM), 0) ORDER_NUM,")
				.append("NVL(sum(b.REAL_NUM), 0) * -1 REAL_NUM,")
				.append(
						"decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE) * -1 REAL_AMOUNT,")
				.append("decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE) DECT_PRICE,")
				.append("e.PROMOTE_NO,")
				.append("e.PROMOTE_NAME,")
				.append("e.CONTRACT_NO, ")
				.append(" sum(NVL(u.COST_PRICE, 0))COST_PRICE, ")
				.append(
						" (sum(NVL(u.COST_PRICE, 0)) * sum(NVL(b.REAL_NUM, 0))) COST_AMOUNT,0 PROFIT_AMOUNT ,0 PROFIT_SCALING ")
				.append(" from DRP_STOREIN a, DRP_STOREIN_DTL b ")
				.append(" left join BASE_PRODUCT p ")
				.append(" on b.PRD_ID = p.PRD_ID ")
				.append(" left join DRP_SPCL_TECH t ")
				.append(
						" on b.SPCL_TECH_ID = t.SPCL_TECH_ID, DRP_ADVC_ORDER e,")
				.append("DRP_ADVC_ORDER_DTL f,V_PRODUCT_PRVD_PRICE u ")
				.append(" where a.STOREIN_ID = b.STOREIN_ID ")
				.append("and e.ADVC_ORDER_ID = f.ADVC_ORDER_ID ")
				.append("and b.FROM_BILL_DTL_ID = f.ADVC_ORDER_DTL_id ")
				.append(
						" and b.PRD_ID = u.PRD_ID and NVL(b.SPCL_TECH_ID, 'NONE') = NVL(u.SPCL_TECH_ID, 'NONE') ")
				.append(
						"and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 and e.DEL_FLAG = 0 and f.DEL_FLAG = 0 and a.DEAL_FLAG = 1 ")
				.append("and e.BILL_TYPE = '终端退货' ").append(conSqloT).append(
						" group by e.TERM_NAME,").append("e.SALE_PSON_NAME,")
				.append("a.STOREIN_NO,").append("a.DEAL_TIME,").append(
						"e.ADVC_ORDER_NO,").append("p.PAR_PRD_NO,").append(
						"p.PAR_PRD_NAME,").append("t.SPCL_DTL_REMARK,").append(
						"f.PRD_TYPE,").append("b.DECT_PRICE,").append(
						"e.PROMOTE_NO,").append("e.PROMOTE_NAME,").append(
						"e.CONTRACT_NO ) t ");

		return sql.toString();
	}

	/**
	 * 合同执行情况sql
	 * 
	 * @return
	 */
	public String getContractSql(String SAEL_DATE_BENG, String SAEL_DATE_END) {
		String sql = "select rownum rno,a.ledger_name,"
				+ "to_char(a.SALE_DATE, 'yyyy-MM-DD') SALE_DATE ,"
				+ "a.TERM_NAME,"
				+ "a.ADVC_ORDER_NO,"
				+ "(a.PAYABLE_AMOUNT- NVL(u.RETUN_AMOUNT,0)) PAYABLE_AMOUNT,"
				+ "a.CUST_NAME,"
				+ "a.TEL,"
				+ "a.CONTRACT_NO,"
				+ "to_char(a.ORDER_RECV_DATE, 'yyyy-MM-DD')ORDER_RECV_DATE,"
				+ "NVL(b.PAR_PAYMENT_AMOUNT, 0) PAR_PAYMENT_AMOUNT,"
				+ "NVL(b.CUR_PAYMENT_AMOUNT, 0) CUR_PAYMENT_AMOUNT,"
				+ "NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) ALL_PAYMENT_AMOUNT,"
				+ "NVL(k.STOREOUT_AMOUNT, 0) STOREOUT_AMOUNT,"
				+ "(case when NVL(k.STOREOUT_AMOUNT, 0) = 0 then  '未发货'  when NVL(k.STOREOUT_AMOUNT, 0) >= NVL(a.PAYABLE_AMOUNT, 0) then  '已发货'  else  '部分发货'  end) SEND_STATE,"
				+ "(case when NVL(k.STOREOUT_AMOUNT, 0) <  NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) then  NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) - NVL(k.STOREOUT_AMOUNT, 0) else  0 end) NO_SEND_AMOUNT,"
				+ "(case when NVL(k.STOREOUT_AMOUNT, 0) > NVL(b.PAR_PAYMENT_AMOUNT, 0) + NVL(b.CUR_PAYMENT_AMOUNT, 0) then  NVL(k.STOREOUT_AMOUNT, 0) - NVL(b.PAR_PAYMENT_AMOUNT, 0) - NVL(b.CUR_PAYMENT_AMOUNT, 0) else  0 end) NO_PAYEDMENT,"
				+ "NVL(a.PAYABLE_AMOUNT, 0) - NVL(b.PAR_PAYMENT_AMOUNT, 0) - NVL(b.CUR_PAYMENT_AMOUNT, 0)- NVL(u.RETUN_AMOUNT,0)  LEFT_PAYEDMENT"
				+ " from DRP_ADVC_ORDER a "
				+ " left join (select sum(case when to_char(m.STATEMENT_DATE, 'yyyy-MM-DD') < '"
				+ SAEL_DATE_BENG
				+ "' then  decode(BILL_TYPE,'销售退款', NVL(m.STATEMENTS_AMOUNT, 0)*-1,NVL(m.STATEMENTS_AMOUNT, 0))  else 0 end) PAR_PAYMENT_AMOUNT,"
				+ "sum(case  when to_char(m.STATEMENT_DATE, 'yyyy-MM-DD') >= '"
				+ SAEL_DATE_BENG
				+ "' then decode(BILL_TYPE,'销售退款', NVL(m.STATEMENTS_AMOUNT, 0)*-1,NVL(m.STATEMENTS_AMOUNT, 0)) else  0 end) CUR_PAYMENT_AMOUNT,"
				+ "m.ADVC_ORDER_ID,"
				+ "sum(case when to_char(m.STATEMENT_DATE, 'yyyy-mm-dd') >='"
				+ SAEL_DATE_BENG
				+ "'"
				+ "and  to_char(m.STATEMENT_DATE, 'yyyy-mm-dd') <= '"
				+ SAEL_DATE_END
				+ "' then 1 else 0 end  )BUSS_FLAG"
				+ " from DRP_STATEMENTS m "
				+ " where m.STATE = '已结算' "
				+ " and m.DEL_FLAG = 0 "
				+ " and to_char(m.STATEMENT_DATE, 'yyyy-mm-dd') <= '"
				+ SAEL_DATE_END
				+ "'"
				+ " group by m.ADVC_ORDER_ID) b"
				+ " on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID"
				+ " left join (select sum((b.real_num * decode(d.prd_type, '赠品', 0, b.dect_price))) STOREOUT_AMOUNT,"
				+ " c.from_bill_id,"
				+ "sum(case when to_char(a.DEAL_TIME, 'yyyy-mm-dd') >= '"
				+ SAEL_DATE_BENG
				+ "' "
				+ "and  to_char(a.DEAL_TIME, 'yyyy-mm-dd') <= '"
				+ SAEL_DATE_END
				+ "' then 1 else 0 end  )BUSS_FLAG"
				+ " from DRP_STOREOUT          a,"
				+ "DRP_STOREOUT_DTL      b,"
				+ "DRP_ADVC_SEND_REQ     c,"
				+ "DRP_ADVC_SEND_REQ_DTL d"
				+ " where a.STOREOUT_ID = b.STOREOUT_ID"
				+ " and c.advc_send_req_id = d.advc_send_req_id"
				+ " and b.From_Bill_Dtl_Id = d.advc_send_req_dtl_id"
				+ " and a.Del_Flag = 0"
				+ " and b.del_flag = 0"
				+ " and c.del_flag = 0"
				+ " and d.del_flag = 0"
				+ " and a.deal_flag = 1"
				+ " and a.bill_type = '销售出库'"
				+ " group by c.from_bill_id) k"
				+ " on a.ADVC_ORDER_ID = k.FROM_BILL_ID"
				+ " left join  ("
				+ " select  decode(b.prd_type, '赠品', 0, b.payable_amount)RETUN_AMOUNT,a.from_bill_id "
				+ " from DRP_ADVC_ORDER a, DRP_ADVC_ORDER_DTL b "
				+ " where a.ADVC_ORDER_ID = b.ADVC_ORDER_ID and a.BILL_TYPE = '终端退货' and a.STATE in ('待退货', '已退货') "
				+ " and a.DEL_FLAG=0 and b.DEL_FLAG=0 )  u  on a.ADVC_ORDER_ID = u.FROM_BILL_ID "
				+ " where a.BILL_TYPE = '终端销售'"
				+ " and a.STATE in ('审核通过', '待确认', '已发货')"
				+ " and a.DEL_FLAG = 0"
				+ " and ((a.SALE_DATE >= to_date('"
				+ SAEL_DATE_BENG
				+ "', 'yyyy-MM-DD') or "
				+ "NVL(a.PAYABLE_AMOUNT, 0) - NVL(b.PAR_PAYMENT_AMOUNT, 0) -"
				+ "NVL(b.CUR_PAYMENT_AMOUNT, 0) > 0 or decode(a.BILL_TYPE, '终端销售',NVL(k.STOREOUT_AMOUNT, 0), NVL(u.RETURN_AMOUNT, 0)) < NVL(a.PAYABLE_AMOUNT, 0)  )"
				+ " and a.SALE_DATE <= to_date('"
				+ SAEL_DATE_END
				+ "', 'yyyy-MM-DD')" + " or b.BUSS_FLAG>0 or k.BUSS_FLAG>0 )";
		return sql;
	}

	/**
	 * 收款查询sql
	 * 
	 * @param userBean
	 * @param conSql
	 * @return
	 */
	public String getImportSql(String conSql) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select ")
				.append(
						" LEDGER_NAME,to_char(SALE_DATE,'yyyy-mm-dd') SALE_DATE,TERM_NO,TERM_NAME,ADVC_ORDER_NO,PAYABLE_AMOUNT,PROMOTE_NO, PROMOTE_NAME,STATEMENT_DATE,PAY_TYPE,sum(PAY_AMONT)PAY_AMONT,REMARK,CONTRACT_NO,STATEMENTS_NO,CUST_NAME,TEL ")
				.append("  from ( ")
				.append(
						" select a.LEDGER_NAME,a.SALE_DATE,a.TERM_NO,a.TERM_NAME,a.ADVC_ORDER_NO,a.PAYABLE_AMOUNT, a.PROMOTE_ID, a.PROMOTE_NO, a.PROMOTE_NAME,b.STATEMENT_DATE, ")
				.append(
						"c.PAY_TYPE,(case when b.BILL_TYPE ='销售退款' then c.PAY_AMONT * -1 else c.PAY_AMONT end) PAY_AMONT,a.REMARK,a.CONTRACT_NO ")
				.append(" ,b.STATEMENTS_NO,a.CUST_NAME,a.TEL ")
				.append(" from DRP_ADVC_ORDER a  ")
				.append(
						" left join DRP_STATEMENTS b  on a.ADVC_ORDER_ID=b.ADVC_ORDER_ID  ")
				.append(
						"  left join DRP_STATEMENTS_PAYMENT_DTL c on  b.STATEMENTS_ID=c.STATEMENTS_ID ")
				.append(
						" where  a.BILL_TYPE in ('终端销售','销售退款')   and b.STATE='已结算'  and a.STATE in ('审核通过','已结算','待结算','待确认','已发货','关闭') and a.DEL_FLAG=0 and b.DEL_FLAG=0 and c.DEL_FLAG=0 ")
				.append(conSql)
				.append("union all")
				.append(
						" select a.LEDGER_NAME,a.SALE_DATE,a.TERM_NO,a.TERM_NAME,a.ADVC_ORDER_NO,a.PAYABLE_AMOUNT,a.PROMOTE_ID, a.PROMOTE_NO, a.PROMOTE_NAME, b.STATEMENT_DATE,'退货退款' PAY_TYPE,c.DECT_AMOUNT*-1  PAY_AMONT,a.REMARK,a.CONTRACT_NO ")
				.append(
						" ,b.RETURN_STATEMENTS_NO STATEMENTS_NO,a.CUST_NAME,a.TEL ")
				.append(" from DRP_ADVC_ORDER a ")
				.append(
						" left join DRP_RETURN_STATEMENTS b  on a.ADVC_ORDER_NO=b.ADVC_ORDER_NO  ")
				.append(
						" left join DRP_RETURN_STATEMENTS_DTL c on  b.RETURN_STATEMENTS_ID=c.RETURN_STATEMENTS_ID ")
				.append(
						" where a.BILL_TYPE='终端退货' and b.STATE='已结算' and a.STATE in ('审核通过','已结算','待结算','待确认','已发货','关闭') and a.DEL_FLAG=0 and b.DEL_FLAG=0 and c.DEL_FLAG=0 ")
				.append(conSql)
				.append("union all")
				.append(
						" select a.LEDGER_NAME,a.RETURN_STATEMENT_DATE,a.TERM_NO,a.TERM_NAME,a.ADVC_ORDER_NO,a.PAYABLE_AMOUNT,a.PROMOTE_ID, a.PROMOTE_NO, a.PROMOTE_NAME,b.STATEMENT_DATE,c.PAY_TYPE,-1*c.PAY_AMONT PAY_AMONT,")
				.append(
						" a.REMARK,a.CONTRACT_NO,b.STATEMENTS_NO,a.CUST_NAME,a.TEL from DRP_ADVC_ORDER a ")
				.append(
						" left join DRP_STATEMENTS b on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID left join DRP_STATEMENTS_PAYMENT_DTL c on b.STATEMENTS_ID = c.STATEMENTS_ID")
				.append(
						"  where a.BILL_TYPE = '终端退货' and b.STATE = '已结算'  and a.STATE in ('待退货','已退货') and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 and c.DEL_FLAG = 0")
				.append(conSql)
				.append(" )all_date ")
				.append(
						" group by LEDGER_NAME,SALE_DATE,TERM_NO,TERM_NAME,ADVC_ORDER_NO,PAYABLE_AMOUNT,PROMOTE_NO, PROMOTE_NAME, STATEMENT_DATE,PAY_TYPE,REMARK,CONTRACT_NO,STATEMENTS_NO,CUST_NAME,TEL ")
				.append(" order by LEDGER_NAME,TERM_NO,ADVC_ORDER_NO ;");
		return sql.toString();

	}

	/**
	 * 收款查询总条数sql
	 * 
	 * @param userBean
	 * @param conSql
	 * @return
	 */
	public String getImportCountSql(String conSql) {
		StringBuffer sql = new StringBuffer();
		sql
				.append(" select count(1) NUM from (select ")
				.append(
						" LEDGER_NAME,to_char(SALE_DATE,'yyyy-mm-dd') SALE_DATE,TERM_NO,TERM_NAME,ADVC_ORDER_NO ")
				.append("  from ( ")
				.append(
						" select a.LEDGER_NAME,a.SALE_DATE,a.TERM_NO,a.TERM_NAME,a.ADVC_ORDER_NO,a.PAYABLE_AMOUNT, b.STATEMENT_DATE, ")
				.append(
						"c.PAY_TYPE,(case when b.BILL_TYPE ='销售退款' then c.PAY_AMONT * -1 else c.PAY_AMONT end) PAY_AMONT,a.REMARK,a.CONTRACT_NO ")
				.append(" ,b.STATEMENTS_NO ")
				.append(" from DRP_ADVC_ORDER a  ")
				.append(
						" left join DRP_STATEMENTS b  on a.ADVC_ORDER_ID=b.ADVC_ORDER_ID  ")
				.append(
						"  left join DRP_STATEMENTS_PAYMENT_DTL c on  b.STATEMENTS_ID=c.STATEMENTS_ID ")
				.append(
						" where  a.BILL_TYPE in ('终端销售','销售退款')   and b.STATE='已结算'  and a.STATE in ('审核通过','已结算','待结算','待确认','已发货','关闭') and a.DEL_FLAG=0 and b.DEL_FLAG=0 and c.DEL_FLAG=0 ")
				.append(conSql)
				.append("union all")
				.append(
						" select a.LEDGER_NAME,a.SALE_DATE,a.TERM_NO,a.TERM_NAME,a.ADVC_ORDER_NO,a.PAYABLE_AMOUNT, b.STATEMENT_DATE,'退货退款' PAY_TYPE,c.DECT_AMOUNT*-1  PAY_AMONT,a.REMARK,a.CONTRACT_NO ")
				.append(" ,b.RETURN_STATEMENTS_NO STATEMENTS_NO ")
				.append(" from DRP_ADVC_ORDER a ")
				.append(
						" left join DRP_RETURN_STATEMENTS b  on a.ADVC_ORDER_NO=b.ADVC_ORDER_NO  ")
				.append(
						" left join DRP_RETURN_STATEMENTS_DTL c on  b.RETURN_STATEMENTS_ID=c.RETURN_STATEMENTS_ID ")
				.append(
						" where a.BILL_TYPE='终端退货' and b.STATE='已结算' and a.STATE in ('审核通过','已结算','待结算','待确认','已发货','关闭') and a.DEL_FLAG=0 and b.DEL_FLAG=0 and c.DEL_FLAG=0 ")
				.append(conSql)
				.append("union all")
				.append(
						" select a.LEDGER_NAME,a.RETURN_STATEMENT_DATE,a.TERM_NO,a.TERM_NAME,a.ADVC_ORDER_NO,a.PAYABLE_AMOUNT,b.STATEMENT_DATE,c.PAY_TYPE,-1*c.PAY_AMONT PAY_AMONT,")
				.append(
						" a.REMARK,a.CONTRACT_NO,b.STATEMENTS_NO from DRP_ADVC_ORDER a ")
				.append(
						" left join DRP_STATEMENTS b on a.ADVC_ORDER_ID = b.ADVC_ORDER_ID left join DRP_STATEMENTS_PAYMENT_DTL c on b.STATEMENTS_ID = c.STATEMENTS_ID")
				.append(
						"  where a.BILL_TYPE = '终端退货' and b.STATE = '已结算'  and a.STATE in ('待退货','已退货') and a.DEL_FLAG = 0 and b.DEL_FLAG = 0 and c.DEL_FLAG = 0")
				.append(conSql)
				.append(" )all_date ")
				.append(
						" group by LEDGER_NAME,SALE_DATE,TERM_NO,TERM_NAME,ADVC_ORDER_NO,PAYABLE_AMOUNT,  STATEMENT_DATE,PAY_TYPE,REMARK,CONTRACT_NO,STATEMENTS_NO )");
		return sql.toString();

	}

	// 根据货品id，特殊工艺id，渠道id查询预订单信息，用于库存预订单对照备货查询报表弹出页面
	public List qeuryAdvcStoreAcct(Map<String, String> map) {
		return this.findList("Advcorder.qeuryAdvcStoreAcct", map);
	}

	/**
	 * 未处理的订货订单冻结信用
	 * 
	 * @param conSql
	 * @return
	 */
	public String getCreditFreezeSql(String conSql) {
		String sql = "select t.GOODS_ORDER_NO BILL_NO,"
				+ "t.BILL_TYPE BILL_TYPE,"
				+ "sum(NVL(d.CREDIT_FREEZE_PRICE, 0) * NVL(d.ORDER_NUM, 0)) CREDIT_FREEZE"
				+ " from DRP_GOODS_ORDER t, DRP_GOODS_ORDER_DTL d"
				+ " where t.GOODS_ORDER_ID = d.GOODS_ORDER_ID "
				+ conSql
				+ " and t.STATE in ('未处理')"
				+ " and t.DEL_FLAG = 0"
				+
				// " and t.BILL_TYPe in ('备货订货', '返利订货')"+
				" and t.BILL_TYPE not in ('赠品订货') "
				+ " and d.DEL_FLAG = 0"
				+ " and NVL(d.CREDIT_FREEZE_PRICE, 0) * NVL(d.ORDER_NUM, 0)>0 "
				+ " group by t.GOODS_ORDER_NO, t.BILL_TYPE"
				+ " union all "
				+
				// 销售订单冻结信用
				"select t.SALE_ORDER_NO BILL_NO,"
				+ "t.BILL_TYPE BILL_TYPE,"
				+ "sum(NVL(sd.CREDIT_FREEZE_PRICE, 0) *"
				+ "(NVL(sd.ORDER_NUM, 0) - NVL(sd.SENDED_NUM, 0))) CREDIT_FREEZE"
				+ " from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL sd"
				+ " where t.SALE_ORDER_ID = sd.SALE_ORDER_ID"
				+ conSql
				+ " and t.BILL_TYPE in ('标准','非标','返利订货')"
				+ " and t.DEL_FLAG = 0"
				+ " and sd.DEL_FLAG = 0"
				+ " and sd.STATE = '正常'"
				+ " and NVL(sd.CREDIT_FREEZE_PRICE, 0) * (NVL(sd.ORDER_NUM, 0) - NVL(sd.SENDED_NUM, 0))>0 "
				+ " group by t.SALE_ORDER_NO, t.BILL_TYPE"
				+ " union all "
				+ " select u.DELIVER_ORDER_NO, '手工冻结', t.FREEZE_AMOUNT from ERP_DELIVER_ORDER u, ERP_DELIVER_FREEZE_DTL t"
				+ " where u.DELIVER_ORDER_ID = t.DELIVER_ORDER_ID"
				+ " and u.DEL_FLAG = 0"
				+ " and u.STATE in ('已提交库房')"
				+ " and t.DEL_FLAG = 0 " + " and t.FREEZE_AMOUNT>0 " + conSql;

		return sql;
	}

	/**
	 * 返利订单扣除信用
	 * 
	 * @param conSql
	 * @return
	 */
	public String getRebateFreezeSql(String CHANN_ID) {
		String sql = " select t.GOODS_ORDER_NO BILL_NO,"
				+ " t.BILL_TYPE BILL_TYPE,"
				+ " sum(NVL(d.REBATE_PRICE, 0) * NVL(d.ORDER_NUM, 0)) REBATE_FREEZE"
				+ " from DRP_GOODS_ORDER t, DRP_GOODS_ORDER_DTL d"
				+ " where t.GOODS_ORDER_ID = d.GOODS_ORDER_ID"
				+ " and t.ORDER_CHANN_ID = '" + CHANN_ID + "'"
				+ " and t.STATE in ('未处理','已处理')" + " and t.DEL_FLAG = 0"
				+ " and t.BILL_TYPE ='返利订货' " + " and d.DEL_FLAG = 0 "
				+ " and NVL(d.cancel_flag,0)=0 "
				+ " group by t.GOODS_ORDER_NO, t.BILL_TYPE";

		return sql;
	}

	/**
	 * 分类销售统计
	 * 
	 * @param conSql
	 * @return
	 */
	public String getTop10ProductSql(String conSql, String RANKING) {
		String sql = "select row_.*, rownum rownum_"
				+ " from ( "
				+ " select temp.SHORT_NAME,"
				+ " sum(temp.SENDED_NUM) SENDED_NUM,"
				+ " sum(temp.SEND_AMOUNT) SEND_AMOUNT"
				+ " from (select d.PRD_NAME,"
				+ " substr("
				+ " d.PRD_NAME, 0,(case when instr(d.PRD_NAME, '-', 1, 1)=0 then length(d.PRD_NAME) else instr(d.PRD_NAME, '-', 1, 1)-1 end)"
				+ " ) SHORT_NAME,"
				+ "sum(NVL(d.SENDED_NUM, 0)) SENDED_NUM,"
				+ " sum(d.DECT_PRICE * NVL(d.SENDED_NUM, 0)) SEND_AMOUNT "
				+ " from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL d left join BASE_PRODUCT p on p.PRD_ID= d.PRD_ID "
				+ " where t.SALE_ORDER_ID = d.SALE_ORDER_ID"
				+ " and d.DEL_FLAG = 0" + " and NVL(d.CANCEL_FLAG, 0) = 0"
				+ " and NVL(d.sended_num, 0) > 0 " + conSql
				+ " group by d.PRD_NAME,d.SENDED_NUM, d.DECT_PRICE) temp "
				+ " group by temp.SHORT_NAME"
				+ " order by sum(temp.SENDED_NUM) DESC" + " )row_"
				+ " where rownum <= " + RANKING;

		return sql;
	}

	/**
	 * 销量统计比例
	 * 
	 * @param conSql
	 * @return
	 */
	public String getSaleStatisticsSql(String conSql) {
		// String sql = "select aa.PAR_PRD_NO,"+
		// "aa.PAR_PRD_NAME,"+
		// "aa.AREA_NAME_P,"+
		// "aa.SENDED_NUM,"+
		// "sum(aa.SENDED_NUM) over(partition by aa.PAR_PRD_NO) All_NUM,"+
		// "round(RATIO_TO_REPORT(aa.SENDED_NUM) OVER(partition by
		// aa.PAR_PRD_NO), 4) AS tt"+
		// " from (select p.PAR_PRD_ID, p.PAR_PRD_NO,
		// p.PAR_PRD_NAME,temp.AREA_NAME_P,sum(temp.SENDED_NUM) SENDED_NUM, " +
		// " sum(temp.SEND_AMOUNT) SEND_AMOUNT"+
		// " from (select d.PRD_ID, a.AREA_NAME_P,sum(NVL(d.SENDED_NUM, 0))
		// SENDED_NUM, " +
		// " d.dECT_PRICE * NVL(d.SENDED_NUM, 0) SEND_AMOUNT"+
		// " from ERP_SALE_ORDER_DTL d"+
		// " left join ERP_SALE_ORDER t on t.SALE_ORDER_ID = d.SALE_ORDER_ID"+
		// " left join BASE_AREA a on a.AREA_ID = t.AREA_ID"+
		// " where d.DEL_FLAG = 0 and NVL(d.CANCEL_FLAG, 0) = 0"+
		// " group by d.PRD_ID, d.SENDED_NUM, d.DECT_PRICE, a.AREA_NAME_P)
		// temp"+
		// " left join BASE_PRODUCT p on temp.PRD_ID = p.PRD_ID"+
		// " group by p.PAR_PRD_ID, p.PAR_PRD_NO, p.PAR_PRD_NAME,
		// temp.AREA_NAME_P"+
		// " having NVL(sum(temp.SENDED_NUM), 0) > 0) aa";
		String sql = " select p.par_prd_no,p.par_prd_name, a.area_name_p, sum(NVL(d.sended_num,0))sended_num"
				+ " from ERP_SALE_ORDER_DTL d left join BASE_PRODUCT p  on d.prd_id = p.prd_id"
				+ " left join ERP_SALE_ORDER t on t.sale_order_id = d.sale_order_id"
				+ " left join BASE_AREA a on a.area_id = t.area_id"
				+ " where d.del_flag = 0 and NVL(d.cancel_flag, 0) = 0"
				+ " and NVL(d.sended_num,0)>0 "
				+ conSql
				+ " group by p.par_prd_no,p.par_prd_name, a.area_name_p";
		return sql;
	}

	/**
	 * 待确认预订单
	 * 
	 * @param conSql
	 * @return
	 */
	public String getUncommAdvcSql(String conSql) {
		String sql = "select u.ADVC_ORDER_NO,"
				+ "u.TERM_NO,"
				+ "u.TERM_NAME,"
				+ "to_char(u.SALE_DATE, 'yyyy-MM-DD') SALE_DATE,"
				+ "u.SALE_PSON_NAME,"
				+ "u.CUST_NAME,"
				+ "u.TEL,"
				+ "u.RECV_ADDR,"
				+ "u.ADVC_AMOUNT,"
				+ "u.PAYABLE_AMOUNT,"
				+ "u.REMARK,"
				+ "to_char(u.PFMC_DATE, 'yyyy-MM-DD') PFMC_DATE,"
				+ "u.DEAL_FLAG,"
				+ "u.STTLE_FLAG,"
				+ "u.CRE_NAME,"
				+ "u.LEDGER_NAME,"
				+ "u.STATE,"
				+ "u.DEL_FLAG,"
				+ "u.CONTRACT_NO,"
				+ "u.TOTAL_AMOUNT,"
				+ "u.DISCOUNT_AMOUNT,"
				+ "to_char(u.ORDER_RECV_DATE, 'yyyy-mm-dd') ORDER_RECV_DATE,"
				+ "  (case when  t.DM_SPCL_TECH_NO is null or t.DM_SPCL_TECH_NO='' then d.PRD_NO"
				+ " else d.PRD_NO||'-'|| t.DM_SPCL_TECH_NO  end) PRD_NO,"
				+ "t.DM_SPCL_TECH_NO," + "t.SPCL_DTL_REMARK," + "d.PRD_NAME,"
				+ "d.PRD_SPEC," + "d.BRAND," + "d.STD_UNIT,"
				+ "p.PAR_PRD_NAME," + "d.PRICE," + "d.DECT_RATE,"
				+ "d.DECT_PRICE," + "d.ORDER_NUM,"
				+ "to_char(d.ORDER_RECV_DATE, 'yyyy-MM-DD') ORDER_RECV_DATE,"
				+ "d.PAYABLE_AMOUNT DTL_PAYABLE_AMOUNT,"
				+ "d.REMARK DTL_REMARK "
				+ "  from DRP_ADVC_ORDER u,DRP_ADVC_ORDER_DTL d "
				+ " left join BASE_PRODUCT p on p.PRD_ID=d.PRD_ID"
				+ " left join DRP_SPCL_TECH t on d.SPCL_TECH_ID=t.sPCL_TECH_ID"
				+ " where u.ADVC_ORDER_ID=d.ADVC_ORDER_ID"
				+ " and  u.BILL_TYPE = '终端销售'" + " and u.STATE = '待确认'"
				+ " and u.DEL_FLAG = 0" + " and d.DEL_FLAG=0 " + conSql;

		return sql;
	}

	/**
	 * 总部销售报表
	 * 
	 * @param conSql
	 * @return
	 */
	public String getErpSaleReportSql(String conSql) {
		String sql = "select a.AREA_NAME_P,"
				+ "b.PROV,"
				+ "b.AREA_NAME,"
				+ "b.CHANN_NO,"
				+ "b.CHANN_NAME,"
				+ "d.BRAND ,"
				+ "p.PAR_PRD_NAME ,"
				+ "d.PRD_NO,"
				+ "d.PRD_NAME,"
				+ "d.PRD_SPEC,"
				+ "NVL(d.ORDER_NUM, 0)ORDER_NUM,"
				+ "NVL(d.DECT_PRICE, 0) * NVL(d.ORDER_NUM, 0)ORDER_AMOUNT ,"
				+ "NVL(P.RET_PRICE_MIN, 0) * NVL(d.ORDER_NUM, 0)RET_ORDER_AMOUNT ,"
				+ "t.SHIP_POINT_NAME " + " from ERP_SALE_ORDER t"
				+ " left join ERP_SALE_ORDER_DTL d"
				+ " on t.SALE_ORDER_ID = d.SALE_ORDER_ID"
				+ " left join BASE_CHANN b"
				+ " on t.ORDER_CHANN_ID = b.CHANN_ID"
				+ " left join BASE_AREA a" + " on b.AREA_ID = a.AREA_ID"
				+ " left join BASE_PRODUCT p" + " on p.PRD_ID = d.PRD_ID"
				+ " where d.DEL_FLAG = 0" + " and t.DEL_FLAG = 0" + conSql;
		return sql;
	}

	/**
	 * 各战区季度有效门店达成数
	 * 
	 * @param conSql
	 * @return
	 */
	public String getWareaQuarterOpenNumSql(String conSql) {
		// String sql = "select AREA_NAME_P,PROV,AREA_NAME,AREA_MANAGE_NAME,
		// sum(Q1)Q1,"
		// +"sum(Q2)Q2,"
		// +"sum(Q3)Q3,"
		// +"sum(Q4)Q4 from("
		// +"select a.AREA_NAME_P,t.PROV,a.AREA_NAME,c.AREA_MANAGE_NAME, "
		// +"(case when to_char(t.BEG_SBUSS_DATE,'Q')=1 then sum(1) else 0 end
		// )Q1,"
		// +"(case when to_char(t.BEG_SBUSS_DATE,'Q')=2 then sum(1) else 0 end
		// )Q2,"
		// +"(case when to_char(t.BEG_SBUSS_DATE,'Q')=3 then sum(1) else 0 end
		// )Q3 ,"
		// +"(case when to_char(t.BEG_SBUSS_DATE,'Q')=4 then sum(1) else 0 end
		// )Q4"
		// +" from BASE_TERMINAL t left join BASE_AREA a on a.AREA_ID=t.AREA_ID"
		// +" left join BASE_CHANN c on c.CHANN_ID=t.CHANN_ID_P"
		// +" where t.DEL_FLAG=0" + conSql
		// +" group by a.AREA_NAME_P,t.PROV,a.AREA_NAME,c.AREA_MANAGE_NAME,"
		// +" to_char(t.BEG_SBUSS_DATE,'yyyy-Q'),to_char(t.BEG_SBUSS_DATE,'Q'))"
		// +" group by AREA_NAME_P,PROV,AREA_NAME,AREA_MANAGE_NAME";
		String sql = " select * from v_trem_quarter_report v  where 1=1 "
				+ conSql;
		return sql;
	}

	/**
	 * 门店销售出货统计sql 按导购员查询
	 * 
	 * @param conSql
	 * @param conSqloT
	 * @return
	 */
	public String getTermSaleSendBySalePsonNameSql(String conSql,
			String conSqloT) {
		String sql =
			    "SELECT t.TERM_NAME,t.SALE_PSON_NAME, t.ORDER_NUM,t.REAL_NUM, "
			    + "  t.REAL_AMOUNT,SUM(t.ORDER_NUM) over() TOTAL_ORDER_NUM,"
			    + "  SUM(t.REAL_NUM) over() TOTAL_REAL_NUM,SUM(t.REAL_AMOUNT) over() TOTAL_REAL_AMOUNT"
			    + "  FROM ("
			    + "select temp.TERM_NAME,temp.SALE_PSON_NAME,sum(NVL(temp.ORDER_NUM,0))ORDER_NUM,sum(NVL(temp.REAL_NUM,0))REAL_NUM,sum(NVL(temp.REAL_AMOUNT,0))REAL_AMOUNT "
				+ " from (select "
				+ "a.TERM_NAME,a.SALE_PSON_NAME,a.STOREOUT_NO IN_OUT_NO,'销售出库' BILL_TYPE,to_char(DEAL_TIME,'yyyy-mm-dd')IN_OUT_DATE,e.ADVC_ORDER_NO,"
				+ "e.TEL,e.RECV_ADDR,"
				+ "b.PRD_NO,b.PRD_NAME,b.PRD_SPEC,p.PAR_PRD_NO,p.PAR_PRD_NAME, t.SPCL_DTL_REMARK ,  f.ORDER_NUM , f.PRD_TYPE,b.REAL_NUM,"
				+ "(b.REAL_NUM*decode(f.PRD_TYPE,'赠品',0,b.DECT_PRICE)) REAL_AMOUNT,decode(f.PRD_TYPE,'赠品',0,b.DECT_PRICE)DECT_PRICE,"
				+ "e.PROMOTE_NO,e.PROMOTE_NAME,e.CONTRACT_NO, "
				+ "(select   sum(st.STATEMENTS_AMOUNT) STATEMENTS_AMOUNT "
				+ " from DRP_STATEMENTS st  where st.DEL_FLAG = 0 and st.STATE = '已结算' and st.BILL_TYPE = '销售退款' "
				+ " and st.ADVC_ORDER_ID=e.advc_order_id)STATEMENTS_AMOUNT,p.PRD_ID,t.SPCL_TECH_ID "
				+ " from "
				+ "DRP_STOREOUT a ,DRP_STOREOUT_DTL b  left join BASE_PRODUCT p  on b.PRD_ID=p.PRD_ID left join DRP_SPCL_TECH t on b.SPCL_TECH_ID = t.SPCL_TECH_ID ,"
				+ "DRP_ADVC_SEND_REQ c ,DRP_ADVC_SEND_REQ_DTL d ,"
				+ "DRP_ADVC_ORDER e,DRP_ADVC_ORDER_DTL f "
				+ " where "
				+ "a.STOREOUT_ID=b.STOREOUT_ID  "
				+ "and c.ADVC_SEND_REQ_ID=d.ADVC_SEND_REQ_ID "
				+ "and e.ADVC_ORDER_ID=f.ADVC_ORDER_ID "
				+ "and b.FROM_BILL_DTL_ID=d.ADVC_SEND_REQ_DTL_ID "
				+ "and d.FROM_BILL_DTL_ID=f.ADVC_ORDER_DTL_ID "
				+ "and a.DEL_FLAG=0 "
				+ "and b.DEL_FLAG=0 "
				+ "and c.DEL_FLAG=0 "
				+ "and d.DEL_FLAG=0 "
				+ "and e.DEL_FLAG=0 "
				+ "and f.DEL_FLAG=0 "
				+ "and a.DEAL_FLAG=1 " + "and a.BILL_TYPE='销售出库' "
				+ conSql
				+ " union all "
				+ "select "
				+ " e.TERM_NAME,e.SALE_PSON_NAME,a.STOREIN_NO IN_OUT_NO  ,'退货入库' BILL_TYPE,to_char(DEAL_TIME,'yyyy-mm-dd')IN_OUT_DATE,e.ADVC_ORDER_NO,"
				+ "e.TEL,e.RECV_ADDR,"
				+ " b.PRD_NO,b.PRD_NAME,b.PRD_SPEC,p.PAR_PRD_NO,p.PAR_PRD_NAME, t.SPCL_DTL_REMARK , b.NOTICE_NUM ORDER_NUM, f.PRD_TYPE,b.REAL_NUM*-1 REAL_NUM,"
				+ "  (decode(f.PRD_TYPE, '赠品', 0,b.DECT_AMOUNT)) * -1 REAL_AMOUNT, decode(f.PRD_TYPE, '赠品', 0, b.DECT_PRICE) DECT_PRICE,"
				+ "e.PROMOTE_NO,e.PROMOTE_NAME,e.CONTRACT_NO ,"
				+ "(select   sum(st.STATEMENTS_AMOUNT) STATEMENTS_AMOUNT "
				+ " from DRP_STATEMENTS st  where st.DEL_FLAG = 0 and st.STATE = '已结算' and st.BILL_TYPE = '销售退款' "
				+ " and st.ADVC_ORDER_ID=e.ADVC_ORDER_ID)STATEMENTS_AMOUNT,p.PRD_ID,t.SPCL_TECH_ID "
				+ " from "
				+ " DRP_STOREIN a ,DRP_STOREIN_DTL b  left join BASE_PRODUCT p  on b.PRD_ID=p.PRD_ID left join DRP_SPCL_TECH t on b.SPCL_TECH_ID = t.SPCL_TECH_ID,"
				+ " DRP_ADVC_ORDER e,DRP_ADVC_ORDER_DTL f "
				+ " where "
				+ " a.STOREIN_ID=b.STOREIN_ID "
				+ " and e.ADVC_ORDER_ID=f.ADVC_ORDER_ID"
				+ " and b.FROM_BILL_DTL_ID=f.ADVC_ORDER_DTL_id"
				+ " and a.DEL_FLAG=0"
				+ " and b.DEL_FLAG=0"
				+ " and e.DEL_FLAG=0"
				+ " and f.DEL_FLAG=0"
				+ " and a.DEAL_FLAG=1"
				+ " and e.BILL_TYPE='终端退货'"
				+ conSqloT
				+ ") temp " + " group by temp.TERM_NAME,temp.SALE_PSON_NAME  ) t ";

		return sql;
	}

	/**
	 * 战区推广费
	 * 
	 * @param conSql
	 * @return
	 */
	public String getWareExtensionFeeSql(String conSql, String YEAR,
			String QUARTER) {
		int intQUARTER = StringUtil.getStrQuarterToInt(QUARTER);
		int last_year = 0;
		int last_q = 0;
		if (intQUARTER == 1) {
			last_year = StringUtil.getInteger(YEAR) - 1;
			last_q = 4;
		} else {
			last_year = StringUtil.getInteger(YEAR);
			last_q = intQUARTER - 1;
		}

		StringBuffer sql = new StringBuffer("");
		sql
				.append("select ext.area_name_p WARE_NAME,ext.area_no_p,")
				.append(
						" round((ext.storeoutAmount_s + ext.quarter_amount)/10000,2) SALE_BUDGET,")
				.append(" round(ext.storeoutAmount/10000,2)storeoutAmount,")
				.append(
						" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.3)/1000,2) WARE_BUDGET,")
				.append(" round(ext.BUDGET_AMOUNT1/10000,2) WARE_PRE_GRANT,")
				.append(
						" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.3 - ext.BUDGET_AMOUNT1)/10000,2) WARE_BUDGET_DO ,")
				.append(" round((ext.storeoutAmount * 0.3)/10000,2) WARE_PLAN,")
				.append(" round(EXPENSE_AMOUNT1/10000,2) WARE_EXPENSE  ,")
				.append(
						" round((ext.storeoutAmount * 0.3 - EXPENSE_AMOUNT1)/10000,2) WARE_PLAN_CAN ,")
				.append(
						" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.2)/10000 ,2)  HQ_BUDET,")
				.append(" round(ext.BUDGET_AMOUNT2/10000,2) HQ_PRE_GRANT,")
				.append(
						" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.2 - ext.BUDGET_AMOUNT2)/10000,2)HQ_BUDGET_DO,")
				.append(" round(ext.storeoutAmount * 0.2/10000,2) HQ_PLAN,")
				.append(" round(EXPENSE_AMOUNT2/10000,2)  HQ_EXPENSE ,")
				.append(
						" round((ext.storeoutAmount * 0.2 - EXPENSE_AMOUNT2)/10000,2) HQ_PLAN_CAN")
				.append(" from (select a.area_name_p, a.area_no_p, ")
				.append(" max(f1.storeoutAmount_s) storeoutAmount_s,")
				.append(" sum(NVL(p.first_quarter_amount, 0)) quarter_amount, ")
				.append(" max(f2.storeoutAmount) storeoutAmount, ")
				.append(" max(NVL(c1.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT1,")
				.append(" max(NVL(c2.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT2,")
				.append(" max(NVL(x1.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT1, ")
				.append(" max(NVL(x2.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT2 ")
				.append(" from base_area a")
				.append(" left join (select a.area_id_p,")
				.append(" sum(NVL(temp.storeoutAmount_s, 0)) storeoutAmount_s")
				.append(" from (select s.order_chann_id,")
				.append(
						" sum(NVL(sd.dect_price, 0) *   NVL(sd.storeout_num, 0)) storeoutAmount_s ")
				.append(" from erp_storeout s, erp_storeout_prd_dtl sd")
				.append(" where s.storeout_id = sd.storeout_id")
				.append(" and TO_CHAR(s.storeout_time, 'Q') = '" + last_q + "'")
				// 上个季度
				.append(
						" and to_char(s.storeout_time, 'yyyy') = '" + last_year
								+ "'")
				.append(" and s.del_flag = 0")
				.append(" and sd.del_flag = 0")
				.append(" group by s.order_chann_id) temp")
				.append(" left join base_chann b")
				.append(" on temp.order_chann_id = b.chann_id")
				.append(" left join base_area a")
				.append(
						" on a.area_id = b.area_id and a.state in ('启用') and a.del_flag = 0")
				.append(" group by a.area_id_p) f1")
				.append(" on f1.area_id_p = a.area_id_p")
				.append(" left join ERP_AREA_SALE_PLAN p ")
				.append(" on p.PLAN_YEAR = '" + YEAR + "'")
				.append(" and p.warea_id = a.area_id_p")
				.append(" and p.del_flag = 0")
				.append(" left join (select a.area_id_p,")
				.append(" sum(NVL(temp.storeoutAmount, 0)) storeoutAmount")
				.append(" from (select s.order_chann_id,")
				.append(
						" sum(NVL(sd.dect_price, 0) * NVL(sd.storeout_num, 0)) storeoutAmount")
				.append(" from erp_storeout s, erp_storeout_prd_dtl sd")
				.append(" where s.storeout_id = sd.storeout_id")
				.append(
						" and TO_CHAR(s.storeout_time, 'Q') = '" + intQUARTER
								+ "'")
				.append(
						" and to_char(s.storeout_time, 'yyyy') = '" + YEAR
								+ "'")
				.append(" and s.del_flag = 0")
				.append(" and sd.del_flag = 0")
				.append(" group by s.order_chann_id) temp")
				.append(" left join base_chann b")
				.append(" on temp.order_chann_id = b.chann_id")
				.append(" left join base_area a")
				.append(
						" on a.area_id = b.area_id and a.state in ('启用') and a.del_flag = 0")
				.append(" group by a.area_id_p) f2")
				.append(" on f2.area_id_p = a.area_id_p")
				.append(" left join (select r.area_id,  ")
				.append(" SUM(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT")
				.append(" from ERP_PRMT_COST_REQ r")
				.append(" where r.state in ('审核通过', '提交')")
				.append(" and r.del_flag = 0")
				.append(" and to_char(r.REQ_DATE, 'yyyy') = '" + YEAR + "'")
				.append(" and TO_CHAR(r.REQ_DATE, 'Q') = '" + intQUARTER + "'")
				.append(" and r.cost_type = '战区费用'")
				.append(" group by r.area_id) c1")
				.append(" on c1.area_id = a.area_id_p")
				.append(" left join (select r.area_id, ")
				.append(" SUM(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT")
				.append(" from ERP_PRMT_COST_REQ r")
				.append(" where r.state in ('审核通过', '提交')")
				.append(" and r.del_flag = 0")
				.append(" and to_char(r.REQ_DATE, 'yyyy') = '" + YEAR + "'")
				.append(" and TO_CHAR(r.REQ_DATE, 'Q') = '" + intQUARTER + "'")
				.append(" and r.cost_type = '总部费用'")
				.append(" group by r.area_id) c2")
				.append(" on c2.area_id = a.area_id_p")
				.append(" left join (select r.area_id,")
				.append(" SUM(NVL(e.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT")
				.append(" from ERP_EXPENSE_ORDER e")
				.append(" left join ERP_PRMT_COST_REQ r")
				// .append(" on r.prmt_cost_req_no = e.relate_order_nos")
				.append(
						" on r.prmt_cost_req_no = SUBSTR(e.relate_order_nos||',',0, instr(e.relate_order_nos||',',',')-1) ")
				.append(" where e.state in ('审核通过', '提交')")
				.append(" and e.relate_order_nos is not null")
				.append(" and e.del_flag = 0")
				.append(" and r.cost_type = '战区费用'")
				.append(" and e.YEAR = '" + YEAR + "'")
				.append(" and e.quarter = '" + QUARTER + "'")
				.append(" group by r.area_id) x1")
				.append(" on x1.area_id = a.area_id_p")
				.append(" left join (select r.area_id,")
				.append(" SUM(NVL(e.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT")
				.append(" from ERP_EXPENSE_ORDER e")
				.append(" left join ERP_PRMT_COST_REQ r")
				.append(
						" on r.prmt_cost_req_no = SUBSTR(e.relate_order_nos||',',0, instr(e.relate_order_nos||',',',')-1)")
				.append(" where e.state in ('审核通过', '提交')")
				.append(
						" and e.relate_order_nos is not null and e.del_flag = 0")
				.append("and r.cost_type = '总部费用'")
				.append(" and e.YEAR = '" + YEAR + "'")
				.append(" and e.quarter = '" + QUARTER + "'")
				.append(" group by r.area_id) x2")
				.append(" on x2.area_id = a.area_id_p")
				.append("   where a.AREA_ID_P is not null")
				.append(
						" and a.state in ('启用') and a.del_flag = 0  group by a.area_name_p, a.area_no_p) ext  where 1=1 ")
				.append(conSql);

		return sql.toString();
	}

	
	
	
	/**
	 * 区域推广费
	 * @param conSql
	 * @return
	 */
	public String getAreaExtensionFeeSql(String conSql,String YEAR,String QUARTER){
		int intQUARTER = StringUtil.getStrQuarterToInt(QUARTER);
		int last_year = 0;
		int last_q = 0;
		if(intQUARTER == 1){
			last_year = StringUtil.getInteger(YEAR)-1;
			last_q = 4;
		}else{
			last_year = StringUtil.getInteger(YEAR);
			last_q = intQUARTER-1;
		}
		
		StringBuffer sql = new StringBuffer("");
		sql.append("select ext.area_name_p WARE_NAME,ext.area_no_p,ext.area_no, ext.area_name , ext.area_manage_name ,")
	    .append(" round((ext.storeoutAmount_s + ext.quarter_amount)/10000,2) SALE_BUDGET,")
	    .append(" round(ext.storeoutAmount/10000,2)storeoutAmount,")
	    .append(" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.3)/1000,2) WARE_BUDGET,")
	    .append(" round(ext.BUDGET_AMOUNT1/10000,2) WARE_PRE_GRANT,")
	    .append(" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.3 - ext.BUDGET_AMOUNT1)/10000,2) WARE_BUDGET_DO ,")
	    .append(" round((ext.storeoutAmount * 0.3)/10000,2) WARE_PLAN,")
	    .append(" round(EXPENSE_AMOUNT1/10000,2) WARE_EXPENSE  ,")
	    .append(" round((ext.storeoutAmount * 0.3 - EXPENSE_AMOUNT1)/10000,2) WARE_PLAN_CAN ,")
	    .append(" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.2)/10000 ,2)  HQ_BUDET,")
	    .append(" round(ext.BUDGET_AMOUNT2/10000,2) HQ_PRE_GRANT,")
	    .append(" round(((ext.storeoutAmount_s + ext.quarter_amount) * 0.2 - ext.BUDGET_AMOUNT2)/10000,2)HQ_BUDGET_DO,")
	    .append(" round(ext.storeoutAmount * 0.2/10000,2) HQ_PLAN,")
	    .append(" round(EXPENSE_AMOUNT2/10000,2)  HQ_EXPENSE ,")
	    .append(" round((ext.storeoutAmount * 0.2 - EXPENSE_AMOUNT2)/10000,2) HQ_PLAN_CAN")
	    .append(" from (select a.area_name_p, a.area_no_p,a.area_no,a.area_name,b.area_manage_name, ")
	    .append(" max(f1.storeoutAmount_s) storeoutAmount_s,")
	    .append(" sum(NVL(p.first_quarter_amount, 0)) quarter_amount, ")
	    .append(" max(f2.storeoutAmount) storeoutAmount, ")
	    .append(" max(NVL(c1.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT1,")
	    .append(" max(NVL(c2.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT2,")
	    .append(" max(NVL(x1.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT1, ")
	    .append(" max(NVL(x2.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT2 ")
	    .append(" from base_area a")
	    .append(" left join base_chann b on a.area_id = b.area_id and b.state in('启用') and b.del_flag =0 and b.area_manage_name is not null ")
	    .append(" left join (select b.area_id,b.area_manage_id,")
	    .append(" sum(NVL(temp.storeoutAmount_s, 0)) storeoutAmount_s")
	    .append(" from (select s.order_chann_id,")
	    .append(" sum(NVL(sd.dect_price, 0) *   NVL(sd.storeout_num, 0)) storeoutAmount_s ") 
	    .append(" from erp_storeout s, erp_storeout_prd_dtl sd")
	    .append(" where s.storeout_id = sd.storeout_id")
	    .append(" and TO_CHAR(s.storeout_time, 'Q') = '"+last_q+"'")//上个季度
	    .append(" and to_char(s.storeout_time, 'yyyy') = '"+last_year+"'")
	    .append(" and s.del_flag = 0")
	    .append(" and sd.del_flag = 0")
	    .append(" group by s.order_chann_id) temp")
	    .append(" left join base_chann b")
	    .append(" on temp.order_chann_id = b.chann_id")
//	    .append(" left join base_area a")
//	    .append(" on a.area_id = b.area_id and a.state in ('启用') and a.del_flag = 0")
	    .append(" group by b.area_id,b.area_manage_id) f1")
	    .append(" on f1.area_id = b.area_id and  NVL(f1.area_manage_id,'NONL') = NVL(b.area_manage_id,'NONL')")
	    .append(" left join ERP_AREA_SALE_PLAN p ")
	    .append(" on p.PLAN_YEAR = '"+YEAR+"'")
	    .append(" and p.area_id = a.area_id")
	    .append(" and p.del_flag = 0")
	    
	    .append(" left join (select b.area_id,b.area_manage_id,")
	    .append(" sum(NVL(temp.storeoutAmount, 0)) storeoutAmount")
	    .append(" from (select s.order_chann_id,")
	    .append(" sum(NVL(sd.dect_price, 0) * NVL(sd.storeout_num, 0)) storeoutAmount")
	    .append(" from erp_storeout s, erp_storeout_prd_dtl sd")
	    .append(" where s.storeout_id = sd.storeout_id")
	    .append(" and TO_CHAR(s.storeout_time, 'Q') = '"+intQUARTER+"'")
	    .append(" and to_char(s.storeout_time, 'yyyy') = '"+YEAR+"'")
	    .append(" and s.del_flag = 0")
	    .append(" and sd.del_flag = 0")
	    .append(" group by s.order_chann_id) temp")
	    .append(" left join base_chann b")
	    .append(" on temp.order_chann_id = b.chann_id")
		.append(" group by b.area_id,b.area_manage_id) f2")
		.append(" on f2.area_id = b.area_id   and NVL(f2.area_manage_id,'NONL') = NVL(b.area_manage_id,'NONL') ")
		
		.append(" left join (select b.area_id,b.area_manage_id,  ")
		.append(" SUM(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT")
		.append(" from ERP_PRMT_COST_REQ r left join base_chann b on r.chann_id = b.chann_id")
		.append(" where r.state in ('审核通过', '提交')")
		.append(" and r.del_flag = 0")
		.append(" and to_char(r.REQ_DATE, 'yyyy') = '"+YEAR+"'")
		.append(" and TO_CHAR(r.REQ_DATE, 'Q') = '"+intQUARTER+"'")
		.append(" and r.cost_type = '战区费用'")
		.append(" group by b.area_id,b.area_manage_id) c1")
		.append(" on c1.area_id = b.area_id and NVL(c1.area_manage_id,'NONL') = NVL(b.area_manage_id,'NONL') ")
		.append(" left join (select b.area_id, b.area_manage_id,")
		.append(" SUM(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT")
		.append(" from ERP_PRMT_COST_REQ r left join base_chann b on r.chann_id = b.chann_id ")
		.append(" where r.state in ('审核通过', '提交')")
		.append(" and r.del_flag = 0")
		.append(" and to_char(r.REQ_DATE, 'yyyy') = '"+YEAR+"'")
		.append(" and TO_CHAR(r.REQ_DATE, 'Q') = '"+intQUARTER+"'")
		.append(" and r.cost_type = '总部费用'")
		.append(" group by b.area_id,b.area_manage_id) c2")
		.append(" on c2.area_id = a.area_id and NVL(c2.area_manage_id,'NONL') = NVL(b.area_manage_id,'NONL')")
		.append(" left join (select b.area_id,b.area_manage_id,")
		.append(" SUM(NVL(e.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT")
		.append(" from ERP_EXPENSE_ORDER e")
		.append(" left join ERP_PRMT_COST_REQ r ")
//		.append(" on r.prmt_cost_req_no = e.relate_order_nos")
		.append(" on r.prmt_cost_req_no = SUBSTR(e.relate_order_nos||',',0, instr(e.relate_order_nos||',',',')-1) ")
		.append("  left join base_chann b on r.chann_id = b.chann_id where e.state in ('审核通过', '提交')")
		.append(" and e.relate_order_nos is not null")
		.append(" and e.del_flag = 0")
		.append(" and r.cost_type = '战区费用'")
		.append(" and e.YEAR = '"+YEAR+"'")
		.append(" and e.quarter = '"+QUARTER+"'")
		.append(" group by b.area_id,b.area_manage_id) x1")
		.append("  on x1.area_id = b.area_id and NVL(x1.area_manage_id,'NONL') = NVL(b.area_manage_id,'NONL')")
		
		.append(" left join (select b.area_id,b.area_manage_id,")
		.append(" SUM(NVL(e.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT")
		.append(" from ERP_EXPENSE_ORDER e")
		.append(" left join ERP_PRMT_COST_REQ r")
		.append(" on r.prmt_cost_req_no = SUBSTR(e.relate_order_nos||',',0, instr(e.relate_order_nos||',',',')-1)")
		.append(" left join base_chann b on r.chann_id = b.chann_id where e.state in ('审核通过', '提交')")
		.append(" and e.relate_order_nos is not null and e.del_flag = 0")
		.append( "and r.cost_type = '总部费用'")
		.append(" and e.YEAR = '"+YEAR+"'")
		.append( " and e.quarter = '"+QUARTER+"'")
		.append( " group by b.area_id,b.area_manage_id) x2")
		.append( " on x2.area_id = a.area_id and NVL(x2.area_manage_id,'NONL') = NVL(b.area_manage_id,'NONL')")
		.append( " where a.AREA_ID_P is not null")
		.append( " and a.state in ('启用') and a.del_flag = 0  group by a.area_no_p,a.area_name_p,a.area_name,a.area_no,b.area_manage_name) ext  where 1=1 ")
		.append(conSql);

		return sql.toString();
	}
	
	
	/**
	 * 每月售后投诉报表
	 * 
	 * @param YEAR_MONTH
	 * @return
	 */
	public String getAdviseMothStatisSql(String YEAR_MONTH,String lastDate) {
		
		StringBuffer sql = new StringBuffer();
		sql
		.append("select m.SHIP_POINT_ID,")
		.append("m.SHIP_POINT_NO,")
		.append("m.SHIP_POINT_NAME,")
		.append("n.PRD_NAME,")
		.append("NVL(ta.BILL_NUM, 0) BILL_NUM,")
		.append("NVL(tb.PRD_MUN, 0) PRD_MUN,")
		.append("NVL(tc.STOREOUT_NUM, 0) STOREOUT_NUM,")
		.append("(case when  NVL(tb.PRD_MUN, 0)=0 or NVL(tc.STOREOUT_NUM, 0)=0 then to_char('0.00','9999990.99') ")
		.append(" else to_char(round(NVL(tb.PRD_MUN, 0)/NVL(tc.STOREOUT_NUM, 0),4)*100,'9999990.99')")
		.append(" end) || '%'  PROP,")
		.append(" NVL(tao.BILL_NUM, 0) BILL_NUM_OLD,")
		.append(" NVL(tbo.PRD_MUN, 0) PRD_MUN_OLD,")
		.append(" NVL(tco.STOREOUT_NUM, 0) STOREOUT_NUM_OLD,")
		.append(" (case when  NVL(tbo.PRD_MUN, 0)=0 or NVL(tco.STOREOUT_NUM, 0)=0 then to_char('0.00','9999990.99') ")
		.append(" else to_char(round(NVL(tbo.PRD_MUN, 0)/NVL(tco.STOREOUT_NUM, 0),4)*100,'9999990.99')")
		.append(" end) || '%'  PROP_OLD")
		.append(" from BASE_SHIP_POINT m")
		.append(" left join (select PRD_NAME")
		.append(" from BASE_PRODUCT")
		.append(" where FINAL_NODE_FLAG = 0")
		.append(" and PRD_NAME in ('软床', '床垫', '床头柜')) n")
		.append(" on 1 = 1")
		.append(" left join (select td.PRD_TYPE, td.SHIP_POINT_ID, count(1) BILL_NUM")
		.append(" from (select a.PRD_TYPE, a.SHIP_POINT_ID, a.CMPL_ADVS_ID")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and to_char(b.RAISE_TIME, 'yyyy-MM') = '"+YEAR_MONTH+"'")
		.append(" group by a.PRD_TYPE, a.SHIP_POINT_ID, a.CMPL_ADVS_ID) td")
		.append(" group by td.PRD_TYPE, td.SHIP_POINT_ID) ta")
		.append(" on ta.PRD_TYPE = n.PRD_NAME")
		.append(" and ta.SHIP_POINT_ID = m.SHIP_POINT_ID")
		.append(" left join (select a.PRD_TYPE, a.SHIP_POINT_ID, count(1) PRD_MUN")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append("  and to_char(b.RAISE_TIME, 'yyyy-MM') = '"+YEAR_MONTH+"'")
		.append("  group by a.PRD_TYPE, a.SHIP_POINT_ID) tb")
		.append(" on tb.PRD_TYPE = n.PRD_NAME")
		.append("  and tb.SHIP_POINT_ID = m.SHIP_POINT_ID")
		.append(" left join")
		.append("(select f.SHIP_POINT_ID,")
		.append(" p.PAR_PRD_NAME,")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM') STOREOUT_TIME,")
		.append(" sum(e.STOREOUT_NUM) STOREOUT_NUM")
		.append(" from ERP_STOREOUT_PRD_DTL e")
		.append(" left join ERP_STOREOUT f")
		.append(" on e.STOREOUT_ID = f.STOREOUT_ID")
		.append(" left join BASE_PRODUCT p")
		.append(" on e.PRD_ID = p.PRD_ID")
		.append(" where e.DEL_FLAG = 0")
		.append(" and f.DEL_FLAG = 0")
		.append(" and f.STATE = '已处理'")
		.append("  and to_char(f.STOREOUT_TIME, 'yyyy-MM') = '"+YEAR_MONTH+"'")
		.append(" group by f.SHIP_POINT_ID,")
		.append(" p.PAR_PRD_NAME,")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM')) tc")
		.append(" on tc.PAR_PRD_NAME = n.PRD_NAME")
		.append(" and tc.SHIP_POINT_ID = m.SHIP_POINT_ID")
		.append(" left join (select td.PRD_TYPE, td.SHIP_POINT_ID, count(1) BILL_NUM")
		.append(" from (select a.PRD_TYPE, a.SHIP_POINT_ID, a.CMPL_ADVS_ID")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and to_char(b.RAISE_TIME, 'yyyy-MM') = '"+lastDate+"'")
		.append(" and b.STATE='已反馈'")
		.append(" group by a.PRD_TYPE, a.SHIP_POINT_ID, a.CMPL_ADVS_ID) td")
		.append(" group by td.PRD_TYPE, td.SHIP_POINT_ID) tao")
		.append(" on tao.PRD_TYPE = n.PRD_NAME")
		.append(" and tao.SHIP_POINT_ID = m.SHIP_POINT_ID")
		.append(" left join (select a.PRD_TYPE, a.SHIP_POINT_ID, count(1) PRD_MUN")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and to_char(b.RAISE_TIME, 'yyyy-MM') = '"+lastDate+"'")
		.append(" group by a.PRD_TYPE, a.SHIP_POINT_ID) tbo")
		.append(" on tbo.PRD_TYPE = n.PRD_NAME")
		.append(" and tbo.SHIP_POINT_ID = m.SHIP_POINT_ID")
		.append(" left join")
		.append(" (select f.SHIP_POINT_ID,")
		.append(" p.PAR_PRD_NAME,")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM') STOREOUT_TIME,")
		.append(" sum(e.STOREOUT_NUM) STOREOUT_NUM")
		.append("  from ERP_STOREOUT_PRD_DTL e")
		.append("  left join ERP_STOREOUT f")
		.append("    on e.STOREOUT_ID = f.STOREOUT_ID")
		.append("  left join BASE_PRODUCT p")
		.append("   on e.PRD_ID = p.PRD_ID")
		.append(" where e.DEL_FLAG = 0")
		.append("   and f.DEL_FLAG = 0")
		.append("   and f.STATE = '已处理'")
		.append("  and to_char(f.STOREOUT_TIME, 'yyyy-MM') = '"+lastDate+"'")
		.append("group by f.SHIP_POINT_ID,")
		.append("p.PAR_PRD_NAME,")
		.append("to_char(f.STOREOUT_TIME, 'yyyy-MM')) tco")
		.append(" on tco.PAR_PRD_NAME = n.PRD_NAME")
		.append(" and tco.SHIP_POINT_ID = m.SHIP_POINT_ID");

		return sql.toString();
	}
	
	
	/**
	 * 软床季/年 售后投诉报表
	 * 
	 * @param YEAR_MONTH
	 * @return
	 */
	public String getCDAdviseMothStatisSql(String CUUR_YEAR_MONTH_BEG,String CUUR_YEAR_MONTH_END,
			String LAST_YEAR_MONTH_BEG,String LAST_YEAR_MONTH_END) {

		StringBuffer sql = new StringBuffer();
		sql
		.append("select temp.SJXMC,")
		.append("NVL(ta.BILL_NUM, 0) BILL_NUM,")
		.append("NVL(tb.PRD_MUN, 0) PRD_MUN,")
		.append("NVL(tc.STOREOUT_NUM, 0) STOREOUT_NUM,")
		.append("(case when NVL(tb.PRD_MUN, 0) = 0 or NVL(tc.STOREOUT_NUM, 0) = 0 then  0 else round(NVL(tb.PRD_MUN, 0) / NVL(tc.STOREOUT_NUM, 0), 4) end) PROP, ")
		.append("NVL(tao.BILL_NUM, 0) BILL_NUM_OLD,")
		.append("NVL(tbo.PRD_MUN, 0) PRD_MUN_OLD,")
		.append("NVL(tco.STOREOUT_NUM, 0) STOREOUT_NUM_OLD,")
		.append("(case  when NVL(tbo.PRD_MUN, 0) = 0 or NVL(tco.STOREOUT_NUM, 0) = 0 then  0  else round(NVL(tbo.PRD_MUN, 0) / NVL(tco.STOREOUT_NUM, 0), 4)  end) PROP_OLD ")
		.append(" from (select a.SJXMC")
		.append(" from T_SYS_SJZD u")
		.append(" left join T_SYS_SJZDMX a")
		.append("  on u.SJZDID = a.SJZDID")
		.append(" where u.DELFLAG = 0")
		.append(" and a.DELFLAG = 0")
		.append(" and u.STATE = '启用'")
		.append(" and u.SJZDBH = 'DRP_CMPL_ADVS_PRD_PRO_TYPE') temp ")
		.append(" left join (select td.PRD_PROBLEM_TYPE, count(1) BILL_NUM")
		.append(" from (select a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+CUUR_YEAR_MONTH_BEG+"' and")
		.append(" to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+CUUR_YEAR_MONTH_END+"')")
		.append(" and a.PRD_TYPE = '床垫'")
		.append(" group by a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID) td")
		.append(" group by td.PRD_PROBLEM_TYPE) ta")
		.append(" on temp.SJXMC = ta.PRD_PROBLEM_TYPE")
		.append(" left join (select a.PRD_PROBLEM_TYPE, count(1) PRD_MUN")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and a.PRD_TYPE = '床垫'")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+CUUR_YEAR_MONTH_BEG+"' and")
		.append(" to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+CUUR_YEAR_MONTH_END+"')")
		.append(" group by a.PRD_PROBLEM_TYPE) tb")
		.append(" on temp.SJXMC = tb.PRD_PROBLEM_TYPE")
		.append(" left join (select sum(e.STOREOUT_NUM) STOREOUT_NUM")
		.append(" from ERP_STOREOUT_PRD_DTL e")
		.append(" left join ERP_STOREOUT f")
		.append(" on e.STOREOUT_ID = f.STOREOUT_ID")
		.append(" left join BASE_PRODUCT p")
		.append(" on e.PRD_ID = p.PRD_ID")
		.append(" where e.DEL_FLAG = 0")
		.append(" and f.DEL_FLAG = 0")
		.append(" and f.STATE = '已处理'")
		.append(" and (to_char(f.STOREOUT_TIME, 'yyyy-MM') >= '"+CUUR_YEAR_MONTH_BEG+"' and")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM') <= '"+CUUR_YEAR_MONTH_END+"')")
		.append(" and p.PAR_PRD_NAME = '床垫'")
		.append(" ) tc")
		.append(" on 1 = 1")
		.append(" left join (select td.PRD_PROBLEM_TYPE, count(1) BILL_NUM")
		.append(" from (select a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+LAST_YEAR_MONTH_BEG+"' and")
		.append(" to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+LAST_YEAR_MONTH_END+"')")
		.append(" and a.PRD_TYPE = '床垫'")
		.append(" group by a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID) td")
		.append(" group by td.PRD_PROBLEM_TYPE) tao")
		.append(" on temp.SJXMC = tao.PRD_PROBLEM_TYPE")
		.append(" left join (select a.PRD_PROBLEM_TYPE, count(1) PRD_MUN")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and a.PRD_TYPE = '床垫'")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+LAST_YEAR_MONTH_BEG+"' and")
		.append("  to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+LAST_YEAR_MONTH_END+"')")
		.append(" group by a.PRD_PROBLEM_TYPE) tbo")
		.append(" on temp.SJXMC = tbo.PRD_PROBLEM_TYPE")
		.append(" left join (select sum(e.STOREOUT_NUM) STOREOUT_NUM")
		.append(" from ERP_STOREOUT_PRD_DTL e")
		.append(" left join ERP_STOREOUT f")
		.append(" on e.STOREOUT_ID = f.STOREOUT_ID")
		.append(" left join BASE_PRODUCT p")
		.append(" on e.PRD_ID = p.PRD_ID")
		.append(" where e.DEL_FLAG = 0")
		.append(" and f.DEL_FLAG = 0")
		.append(" and f.STATE = '已处理'")
		.append(" and (to_char(f.STOREOUT_TIME, 'yyyy-MM') >= '"+LAST_YEAR_MONTH_BEG+"' and")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM') <= '"+LAST_YEAR_MONTH_END+"')")
		.append(" and p.PAR_PRD_NAME = '床垫'")
		.append(" ) tco")
		.append(" on 1 = 1");
		return sql.toString();
	}
	
	
	/**
	 * 软床季/年 售后投诉报表
	 * @param CUUR_YEAR_MONTH_BEG 查询开始时间
	 * @param CUUR_YEAR_MONTH_END 查询结束时间
	 * @param LAST_YEAR_MONTH_BEG 上年开始时间
	 * @param LAST_YEAR_MONTH_END 上年结束时间
	 * @param PRD_TYPE  货品类型 软床、床垫、床头柜
	 * @return
	 */
	public String getAdviseMothStatisSql(String CUUR_YEAR_MONTH_BEG,String CUUR_YEAR_MONTH_END,
			String LAST_YEAR_MONTH_BEG,String LAST_YEAR_MONTH_END,String PRD_TYPE) {

		StringBuffer sql = new StringBuffer();
		sql
		.append("select temp.SJXMC,")
		.append("NVL(ta.BILL_NUM, 0) BILL_NUM,")
		.append("NVL(tb.PRD_MUN, 0) PRD_MUN,")
		.append("NVL(tc.STOREOUT_NUM, 0) STOREOUT_NUM,")
		.append("(case when NVL(tb.PRD_MUN, 0) = 0 or NVL(tc.STOREOUT_NUM, 0) = 0 then  0 else round(NVL(tb.PRD_MUN, 0) / NVL(tc.STOREOUT_NUM, 0), 4) end) PROP, ")
		.append("NVL(tao.BILL_NUM, 0) BILL_NUM_OLD,")
		.append("NVL(tbo.PRD_MUN, 0) PRD_MUN_OLD,")
		.append("NVL(tco.STOREOUT_NUM, 0) STOREOUT_NUM_OLD,")
		.append("(case  when NVL(tbo.PRD_MUN, 0) = 0 or NVL(tco.STOREOUT_NUM, 0) = 0 then  0  else round(NVL(tbo.PRD_MUN, 0) / NVL(tco.STOREOUT_NUM, 0), 4)  end) PROP_OLD ")
		.append(" from (select a.SJXMC")
		.append(" from T_SYS_SJZD u")
		.append(" left join T_SYS_SJZDMX a")
		.append("  on u.SJZDID = a.SJZDID")
		.append(" where u.DELFLAG = 0")
		.append(" and a.DELFLAG = 0")
		.append(" and u.STATE = '启用'")
		.append(" and u.SJZDBH = 'DRP_CMPL_ADVS_PRD_PRO_TYPE') temp ")
		.append(" left join (select td.PRD_PROBLEM_TYPE, count(1) BILL_NUM")
		.append(" from (select a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+CUUR_YEAR_MONTH_BEG+"' and")
		.append(" to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+CUUR_YEAR_MONTH_END+"')")
		.append(" and a.PRD_TYPE = '"+PRD_TYPE+"'")
		.append(" group by a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID) td")
		.append(" group by td.PRD_PROBLEM_TYPE) ta")
		.append(" on temp.SJXMC = ta.PRD_PROBLEM_TYPE")
		.append(" left join (select a.PRD_PROBLEM_TYPE, count(1) PRD_MUN")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and a.PRD_TYPE = '"+PRD_TYPE+"'")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+CUUR_YEAR_MONTH_BEG+"' and")
		.append(" to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+CUUR_YEAR_MONTH_END+"')")
		.append(" group by a.PRD_PROBLEM_TYPE) tb")
		.append(" on temp.SJXMC = tb.PRD_PROBLEM_TYPE")
		.append(" left join (select sum(e.STOREOUT_NUM) STOREOUT_NUM")
		.append(" from ERP_STOREOUT_PRD_DTL e")
		.append(" left join ERP_STOREOUT f")
		.append(" on e.STOREOUT_ID = f.STOREOUT_ID")
		.append(" left join BASE_PRODUCT p")
		.append(" on e.PRD_ID = p.PRD_ID")
		.append(" where e.DEL_FLAG = 0")
		.append(" and f.DEL_FLAG = 0")
		.append(" and f.STATE = '已处理'")
		.append(" and (to_char(f.STOREOUT_TIME, 'yyyy-MM') >= '"+CUUR_YEAR_MONTH_BEG+"' and")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM') <= '"+CUUR_YEAR_MONTH_END+"')")
		.append(" and p.PAR_PRD_NAME = '"+PRD_TYPE+"'")
		.append(" ) tc")
		.append(" on 1 = 1")
		.append(" left join (select td.PRD_PROBLEM_TYPE, count(1) BILL_NUM")
		.append(" from (select a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+LAST_YEAR_MONTH_BEG+"' and")
		.append(" to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+LAST_YEAR_MONTH_END+"')")
		.append(" and a.PRD_TYPE = '"+PRD_TYPE+"'")
		.append(" group by a.PRD_PROBLEM_TYPE, a.CMPL_ADVS_ID) td")
		.append(" group by td.PRD_PROBLEM_TYPE) tao")
		.append(" on temp.SJXMC = tao.PRD_PROBLEM_TYPE")
		.append(" left join (select a.PRD_PROBLEM_TYPE, count(1) PRD_MUN")
		.append(" from DRP_CMPL_PRD_DTL a")
		.append(" left join DRP_CMPL_ADVS b")
		.append(" on a.CMPL_ADVS_ID = b.CMPL_ADVS_ID")
		.append(" where a.DEL_FLAG = 0")
		.append(" and b.CMPL_ADVS_TYPE = '产品投诉'")
		.append(" and b.DEL_FLAG = 0")
		.append(" and a.PRD_TYPE = '"+PRD_TYPE+"'")
		.append(" and (to_char(b.RAISE_TIME, 'yyyy-MM') >= '"+LAST_YEAR_MONTH_BEG+"' and")
		.append("  to_char(b.RAISE_TIME, 'yyyy-MM') <= '"+LAST_YEAR_MONTH_END+"')")
		.append(" group by a.PRD_PROBLEM_TYPE) tbo")
		.append(" on temp.SJXMC = tbo.PRD_PROBLEM_TYPE")
		.append(" left join (select sum(e.STOREOUT_NUM) STOREOUT_NUM")
		.append(" from ERP_STOREOUT_PRD_DTL e")
		.append(" left join ERP_STOREOUT f")
		.append(" on e.STOREOUT_ID = f.STOREOUT_ID")
		.append(" left join BASE_PRODUCT p")
		.append(" on e.PRD_ID = p.PRD_ID")
		.append(" where e.DEL_FLAG = 0")
		.append(" and f.DEL_FLAG = 0")
		.append(" and f.STATE = '已处理'")
		.append(" and (to_char(f.STOREOUT_TIME, 'yyyy-MM') >= '"+LAST_YEAR_MONTH_BEG+"' and")
		.append(" to_char(f.STOREOUT_TIME, 'yyyy-MM') <= '"+LAST_YEAR_MONTH_END+"')")
		.append(" and p.PAR_PRD_NAME = '"+PRD_TYPE+"'")
		.append(" ) tco")
		.append(" on 1 = 1");
		return sql.toString();
	}

	/**
	 * @param params
	 * @return
	 */
	public int getCountMkm(Map<String,String> params) {
		return queryForInt("mkmdayreport.getCountMkm",params);
	}
	
	/**
	 * @param params
	 * @return
	 */
	public List<MkmDayReportModel> queryMkmdayreport(Map<String,String> params){
		return this.findList("mkmdayreport.queryMkmdayreport",
				params);
	}
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> load(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("mkmdayreport.loadById",
				MKM_DAY_RPT_ID);
	}
   
	
	public Map<String, String> loadByChannVist(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("channvisitday.loadById",
				MKM_DAY_RPT_ID);
	}

	
	public Map<String, String> loadByProActv(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("Promotionactv.loadById",
				MKM_DAY_RPT_ID);
	}
	
	public Map<String, String> loadByDisVisit(String MKM_DAY_RPT_ID) {
		return (Map<String, String>) load("distributorvisit.loadById",
				MKM_DAY_RPT_ID);
	}
	 
	public Map<String, String> loadByShopTran(String MKM_DAY_RPT_ID){
		return (Map<String, String>) load("shoppGuideTran.loadById",
				MKM_DAY_RPT_ID);
	}
	
  
	public Map<String, String> loadByByTerm(String MKM_DAY_RPT_ID){
		return (Map<String, String>) load("terminalVisit.loadById",
				MKM_DAY_RPT_ID);
	} 
	
	/**
	 * @param MKM_DAY_RPT_ID
	 * @return
	 */
	public Map<String, String> loadByCooVisit(String MKM_DAY_RPT_ID){
		return (Map<String, String>) load("cooperativevisit.loadById",
				MKM_DAY_RPT_ID);
	}
	
	/**
	 * @param REPORT_DATE
	 * @param MKM_NAME
	 * @return
	 */
	public  String  queryReportInfo(String VST_DATE,String MKM_NAME){
		 Map<String, String> params = new HashMap<String, String>();
		 params.put("VST_DATE", VST_DATE);
		 params.put("MKM_NAME", MKM_NAME);
		 String str = (String)load("mkmdayreport.queryReportInfo", params);
		 return str;
	}
}
