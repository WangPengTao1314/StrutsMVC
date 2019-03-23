package com.hoperun.commons.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.BaseService;

public class InitJournalCreditAcctServiceImpl  extends BaseService{
	
	public List getAllChann(){
		StringBuffer selSql =  new StringBuffer();
		selSql.append("select b.chann_id from base_chann b where b.del_flag = 0 and b.state = '启用'");
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		List resultList = selcomList(params);
		return resultList;
	}
	
/*****************************初使化冻结信用****************************************************/	
	public void trBatchInitChannFreeCredit(){
		List channList = getAllChann();
		for(int i=0;i<channList.size();i++){
			Map channMap = (Map)channList.get(i);
			String channId = (String)channMap.get("CHANN_ID");
			txNewInitChannFreeCredit(channId);
		}
	}
	
	public boolean txNewInitChannFreeCredit(String channId){
		StringBuffer instFreezeSql = new StringBuffer();
		instFreezeSql.append("  SELECT NVL(SUM(NVL(CREDIT_FREEZE,0)),0) CREDIT_FREEZE FROM (select t.GOODS_ORDER_NO BILL_NO,")
		.append("  t.BILL_TYPE BILL_TYPE,")
		.append("  sum(NVL(d.CREDIT_FREEZE_PRICE, 0) * NVL(d.ORDER_NUM, 0)) CREDIT_FREEZE")
		.append("  from DRP_GOODS_ORDER t, DRP_GOODS_ORDER_DTL d")
		.append(" where t.GOODS_ORDER_ID = d.GOODS_ORDER_ID")
		.append("   and t.ORDER_CHANN_ID = '"+channId+"'")
		.append("    and t.STATE in ('未处理')")
		.append("   and t.DEL_FLAG = 0")
		.append("   and t.BILL_TYPe not in ('赠品订货')")
		.append("   and d.DEL_FLAG = 0")
		.append("   and NVL(d.CREDIT_FREEZE_PRICE, 0) * NVL(d.ORDER_NUM, 0) > 0")
		.append("  group by t.GOODS_ORDER_NO, t.BILL_TYPE")
		.append(" union all")
		.append(" select t.SALE_ORDER_NO BILL_NO,")
		.append(" t.BILL_TYPE BILL_TYPE,")
		.append("  sum(NVL(sd.CREDIT_FREEZE_PRICE, 0) * (NVL(sd.ORDER_NUM, 0) - NVL(sd.SENDED_NUM, 0))) CREDIT_FREEZE")
		.append("  from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL sd")
		.append(" where t.SALE_ORDER_ID = sd.SALE_ORDER_ID")
		.append("   and t.ORDER_CHANN_ID = '"+channId+"'")
		.append(" and t.BILL_TYPE in ('标准', '非标', '返利订货')")
		.append("   and t.DEL_FLAG = 0  and sd.DEL_FLAG = 0 and sd.STATE = '正常'")
		.append("  and NVL(sd.CREDIT_FREEZE_PRICE, 0) * (NVL(sd.ORDER_NUM, 0) - NVL(sd.SENDED_NUM, 0)) > 0")
		.append(" group by t.SALE_ORDER_NO, t.BILL_TYPE")
		.append(" union all")
		.append(" select u.DELIVER_ORDER_NO, '手工冻结', t.FREEZE_AMOUNT")
		.append("   from ERP_DELIVER_ORDER u, ERP_DELIVER_FREEZE_DTL t")
		.append("  where u.DELIVER_ORDER_ID = t.DELIVER_ORDER_ID")
		.append("  and u.DEL_FLAG = 0")
		.append("  and u.STATE in ('已提交库房')")
		.append(" and t.DEL_FLAG = 0  and t.FREEZE_AMOUNT > 0  and t.ORDER_CHANN_ID = '"+channId+"')TEMP");
		Map params = new HashMap();
		params.put("SelSQL", instFreezeSql.toString());
		List resultList = selcomList(params);
		Map resultMap =  (Map)resultList.get(0);
		BigDecimal CREDIT_FREEZE = (BigDecimal)resultMap.get("CREDIT_FREEZE");

		StringBuffer updateBuf = new StringBuffer();
		updateBuf.append("update base_chann b set b.freeze_credit = "+CREDIT_FREEZE.doubleValue()+" where b.chann_id = '"+channId+"'");
		Map upParam = new HashMap();
		upParam.put("UpdSQL", updateBuf.toString());
		txUpdcom(upParam);
		return true;	
	}
	
	/*****************************初使化冻结信用****************************************************/	
		
	
	/**新的订货逻辑初使化流水账
	 * @param channId
	 * @return
	 */
	public boolean txNewInitChannAcct(String channId){
		boolean flg = deleteChannAcct(channId);
		instFreeCreditGoodsOrder(channId);
		txSaleOrderDtlClose(channId);
		instSaleOrderAcct(channId);
		return true;
	}
	
	/**销售订单行关闭产生流水账
	 * @param channId
	 * @return
	 */
	public boolean txSaleOrderDtlClose(String channId){
		StringBuffer instSql = new StringBuffer();
		instSql.append(
				"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
						+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
						+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
						+ "STD_UNIT,DECT_PRICE,PRD_NUM,DEAL_TIME,FREEZE_PRICE,"
						+ "YEAR_MONTH,DIRECTION)")
		.append(" select sys_guid(),")
		.append("  '销售订单行关闭',")
		.append(" t.sale_order_id,")
		.append(" t.sale_order_no,")
		.append(" d.sale_order_dtl_id,")
		.append("  t.ledger_id,")
		.append(" t.order_chann_id,")
		.append(" t.order_chann_no,")
		.append(" t.order_chann_name,")
		.append(" d.prd_id,")
		.append(" d.spcl_tech_id,")
		.append(" d.prd_no,")
		.append(" d.prd_name,")
		.append(" d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append("  d.dect_price,")
		.append("   NVL(d.OLD_ORDER_NUM,0)-NVL(d.SENDED_NUM,0),")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
		.append("  NVL(d.DECT_PRICE, 0)*NVL((select b.pay_rate from base_chann b where b.chann_id = '"+channId+"'),0.3),")
		.append(" to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
		.append("1")
		.append("  from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL d")
		.append(" where  t.SALE_ORDER_ID = d.SALE_ORDER_ID")
		.append(" and t.ORDER_CHANN_ID = '"+channId+"'")
		.append("  and t.DEL_FLAG = 0")
		.append(" and d.DEL_FLAG = 0")
		.append("  and nvl(d.IS_FREE_FLAG, 0) = 0")
		.append(" and d.STATE in ('关闭')");
		Map params = new HashMap();
		params.put("InsSQL", instSql.toString());
		return txInscom(params);
	}
	
	/**销售订单发货数量，扣减冻结信用    产生流水账
	 * @param channId
	 * @return
	 */
	private boolean instSaleOrderAcct(String channId){
		StringBuffer instSql = new StringBuffer();
		instSql.append(
				"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
						+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
						+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
						+ "STD_UNIT,DECT_PRICE,PRD_NUM,DEAL_TIME,FREEZE_PRICE,"
						+ "YEAR_MONTH,DIRECTION)")
		.append(" select sys_guid(),")
		.append("  '货品发运',")
		.append(" t.sale_order_id,")
		.append(" t.sale_order_no,")
		.append(" d.sale_order_dtl_id,")
		.append("  t.ledger_id,")
		.append(" t.order_chann_id,")
		.append(" t.order_chann_no,")
		.append(" t.order_chann_name,")
		.append(" d.prd_id,")
		.append(" d.spcl_tech_id,")
		.append(" d.prd_no,")
		.append(" d.prd_name,")
		.append(" d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append("  d.dect_price,")
		.append("   d.SENDED_NUM,")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
		.append("  NVL(d.DECT_PRICE, 0)*NVL((select b.pay_rate from base_chann b where b.chann_id = '"+channId+"'),0.3),")
		.append(" to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
		.append("1")
		.append("  from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL d")
		.append(" where  t.SALE_ORDER_ID = d.SALE_ORDER_ID")
		.append(" and NVL(d.SENDED_NUM, 0) > 0 ")
		.append(" and NVL(d.ORDER_NUM, 0) > 0 ")
		.append(" and t.order_chann_id = '"+channId+"'")
		.append("  and t.del_flag = 0")
		.append(" and d.del_flag = 0")
		.append("  and nvl(d.IS_FREE_FLAG, 0) = 0")
		.append(" and t.state in ('待发货', '已发货')");
		Map params = new HashMap();
		params.put("InsSQL", instSql.toString());
		return txInscom(params);
	}
	
	/****************************************************************/
	public boolean txInitChannAcct(String channId){
		boolean flg = deleteChannAcct(channId);
		instFreeCreditGoodsOrder(channId);
		instAllFreeCreditDeliverOrder(channId);
		instPdtDeliverAcct(channId);
		instDeliverCnfigAcct(channId);
		return true;
	}
	/**删除渠道所有流水账
	 * @param channId
	 * @return
	 */
	private boolean deleteChannAcct(String channId){
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete from BASE_JOURNAL_CREDIT_ACCT ac where ac.BUSS_TYPE in('货品发运','发运确认','整单冻结','取消预订','订货订单','订货订单退回','销售订单行关闭') and ac.chann_id = '")
		.append(channId).append("'");
		Map params = new HashMap();
		params.put("DelSQL", delSql.toString());
		return txDelcom(params);
	}
	
	/**货品发运，提交库房,部分发货，按计划发货数量    产生流水账
	 * @param channId
	 * @return
	 */
	private boolean instPdtDeliverAcct(String channId){
		StringBuffer instSql = new StringBuffer();
		instSql.append(
				"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
						+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
						+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
						+ "STD_UNIT,DECT_PRICE,PRD_NUM,PRD_AMOUNT,DEAL_TIME,FREEZE_PRICE,"
						+ "YEAR_MONTH,DIRECTION)")
		.append(" select sys_guid(),")
		.append("  '货品发运',")
		.append(" t.deliver_order_id,")
		.append(" t.deliver_order_no,")
		.append(" d.deliver_order_dtl_id,")
		.append("  t.ledger_id,")
		.append(" t.order_chann_id,")
		.append(" t.order_chann_no,")
		.append(" t.order_chann_name,")
		.append(" d.prd_id,")
		.append(" d.spcl_tech_id,")
		.append(" d.prd_no,")
		.append(" d.prd_name,")
		.append(" d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append("  d.dect_price,")
		.append("  d.plan_num,")
		.append("  (NVL(d.dect_price, 0) * NVL(d.plan_num, 0)) PRD_AMOUNT,")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
		.append("  NVL(d.dect_price, 0)*NVL((select b.pay_rate from base_chann b where b.chann_id = '"+channId+"'),0.3),")
		.append(" to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
		.append("1")
		.append("  from erp_deliver_order t, erp_deliver_order_dtl d")
		.append(" where   t.deliver_order_id = d.deliver_order_id")
		.append(" and NVL(d.plan_num,0) > 0 ")
		.append(" and t.order_chann_id = '"+channId+"'")
		.append("  and t.del_flag = 0")
		.append(" and d.del_flag = 0")
		.append("  and nvl(d.IS_FREE_FLAG, 0) = 0")
		.append(" and t.state in ('已提交库房','部分发货','已发货')");
		Map params = new HashMap();
		params.put("InsSQL", instSql.toString());
		return txInscom(params);
	}
	
	/**货品发运,待收货,已收货按实际发货数量    产生流水账
	 * @param channId
	 * @return
	 */
	private boolean instDeliverCnfigAcct(String channId){
		StringBuffer instSql = new StringBuffer();
		instSql.append(
				"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
						+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
						+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
						+ "STD_UNIT,DECT_PRICE,PRD_NUM,PRD_AMOUNT,DEAL_TIME,FREEZE_PRICE,"
						+ "YEAR_MONTH,DIRECTION)")
		.append(" select sys_guid(),")
		.append("  '货品发运',")
		.append(" t.deliver_order_id,")
		.append(" t.deliver_order_no,")
		.append(" d.deliver_order_dtl_id,")
		.append("  t.ledger_id,")
		.append(" t.order_chann_id,")
		.append(" t.order_chann_no,")
		.append(" t.order_chann_name,")
		.append(" d.prd_id,")
		.append(" d.spcl_tech_id,")
		.append("  d.prd_no,")
		.append(" d.prd_name,")
		.append("  d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append("  d.dect_price,")
		.append("  d.real_send_num,")
		.append("  (NVL(d.dect_price, 0) * NVL( d.real_send_num, 0)) PRD_AMOUNT,")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
		.append("  nvl(d.credit_freeze_price,0),")
		.append(" to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
		.append("1")
		.append("  from erp_deliver_order t, erp_deliver_order_dtl d")
		.append(" where   t.deliver_order_id = d.deliver_order_id")
		.append(" and t.order_chann_id = '"+channId+"'")
		.append("  and t.del_flag = 0")
		.append(" and d.del_flag = 0")
		.append("  and nvl(d.IS_FREE_FLAG, 0) = 0")
		.append(" and t.state in ('待收货','已收货')");
		Map params = new HashMap();
		params.put("InsSQL", instSql.toString());
		return txInscom(params);
	}
	
	/**订货订单的冻结信用
	 * @param channId
	 * @return
	 */
	private boolean instFreeCreditGoodsOrder(String channId){
		StringBuffer instFreezeSql = new StringBuffer();
		instFreezeSql.append(" insert into BASE_JOURNAL_CREDIT_ACCT ")
		.append(" (JOURNAL_CREDIT_ACCT_ID,")
		.append("  BUSS_TYPE,")
		.append("  BILL_ID,")
		.append("  BILL_NO,")
		.append("  BILL_DTL_ID,")
		.append(" LEDGER_ID,")
		.append("  CHANN_ID,")
		.append(" CHANN_NO,")
		.append(" CHANN_NAME,")
		.append(" PRD_ID,")
		.append(" PRD_NO,")
		.append(" PRD_NAME,")
		.append(" PRD_SPEC,")
		.append(" PRD_COLOR,")
		.append(" BRAND,")
		.append(" SPCL_TECH_ID,")
		.append(" STD_UNIT,")
		.append(" PRICE,")
		.append(" DECT_RATE,")
		.append(" DECT_PRICE,")
		.append(" PRD_NUM,")
		.append(" DEAL_TIME,")
		.append(" FREEZE_PRICE,")
		.append(" YEAR_MONTH,")
		.append("  DIRECTION)")
		.append(" SELECT sys_guid(),")
		.append(" '订货订单',")
		.append(" t.GOODS_ORDER_ID,")
		.append(" t.GOODS_ORDER_NO,")
		.append("  d.GOODS_ORDER_DTL_ID,")
		.append(" t.LEDGER_ID,")
		.append(" t.ORDER_CHANN_ID,")
		.append(" t.ORDER_CHANN_NO,")
		.append(" t.ORDER_CHANN_NAME,")
		.append("  d.PRD_ID,")
		.append(" d.PRD_NO,")
		.append(" d.PRD_NAME,")
		.append(" d.PRD_SPEC,")
		.append(" d.PRD_COLOR,")
		.append(" d.BRAND,")
		.append(" d.SPCL_TECH_ID,")
		.append(" d.STD_UNIT,")
		.append("  d.PRICE,")
		.append(" d.DECT_RATE,")
		.append("  d.DECT_PRICE,")
		.append("  d.ORDER_NUM,")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
		.append("  NVL(d.credit_freeze_price,0),")
		.append("  to_char(t.cre_time, 'yyyy/MM'),")
		.append("  0")
		.append(" from DRP_GOODS_ORDER t, DRP_GOODS_ORDER_DTL d ")
		.append(" where t.GOODS_ORDER_ID = d.GOODS_ORDER_ID")
		.append(" and t.order_chann_id = '"+channId+"'")
		.append(" and t.del_flag =0 and d.del_flag =0 ")
		.append("  and t.state in ('未处理','已处理')")
		.append("  and  t.bill_type != '赠品订货'");
		Map params = new HashMap();
		params.put("InsSQL", instFreezeSql.toString());
		return txInscom(params);
	}
	
	/**销售订单取消预订回信用
	 * @param channId
	 * @return
	 */
	private boolean instFreeCreditSaleOrder(String channId){
			StringBuffer instFreezeSql = new StringBuffer();
			instFreezeSql.append(
					"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
							+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
							+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
							+ "STD_UNIT,DECT_PRICE,PRD_NUM,DEAL_TIME,FREEZE_PRICE,"
							+ "YEAR_MONTH,DIRECTION)")
			.append(" select sys_guid(),")
			.append("  '取消预订',")
			.append("  t.sale_order_id,")
			.append(" t.sale_order_no,")
			.append("  d.sale_order_dtl_id,")
			.append("  t.ledger_id,")
			.append(" t.order_chann_id,")
			.append(" t.order_chann_no,")
			.append(" t.order_chann_name,")
			.append(" d.prd_id,")
			.append(" d.spcl_tech_id,")
			.append("  d.prd_no,")
			.append(" d.prd_name,")
			.append("  d.prd_spec,")
			.append(" d.prd_color,")
			.append("  d.brand,")
			.append("  d.std_unit,")
			.append("  d.dect_price,")
			.append("  (nvl(d.old_order_num, 0) - nvl(d.order_num, 0)),")
			.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
			.append("  NVL(d.credit_freeze_price,0),")
			.append(" to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
			.append("1")
			.append("  from erp_sale_order t, erp_sale_order_dtl d")
			.append(" where  t.sale_order_id = d.sale_order_id")
			.append(" and t.order_chann_id = '"+channId+"'")
			.append("  and t.del_flag = 0")
			.append(" and d.del_flag = 0")
			.append("  and nvl(d.IS_FREE_FLAG, 0) = 0")
			.append("  and d.is_canceled_flag = 1")
			.append("  and (nvl(d.old_order_num, 0) - nvl(d.order_num, 0)) > 0");
			Map params = new HashMap();
			params.put("InsSQL", instFreezeSql.toString());
			return txInscom(params);
	}

	/**发运单整单冻结
	 * @param channId
	 * @return
	 */
	private boolean instAllFreeCreditDeliverOrder(String channId){
		StringBuffer instSql = new StringBuffer();
		instSql.append(
				"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
						+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
						+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
						+ "STD_UNIT,DECT_PRICE,PRD_NUM,DEAL_TIME,FREEZE_PRICE,"
						+ "YEAR_MONTH,DIRECTION)")
		.append(" select sys_guid(),")
		.append("  '整单冻结',")
		.append(" t.deliver_order_id,")
		.append(" t.deliver_order_no,")
		.append(" d.deliver_order_dtl_id,")
		.append("  t.ledger_id,")
		.append(" t.order_chann_id,")
		.append(" t.order_chann_no,")
		.append(" t.order_chann_name,")
		.append(" d.prd_id,")
		.append(" d.spcl_tech_id,")
		.append(" d.prd_no,")
		.append(" d.prd_name,")
		.append(" d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append("  d.dect_price,")
		.append("  d.plan_num,")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS') DEAL_TIME,")
		.append("  NVL(d.dect_price, 0)*0.7,")
		.append(" to_char(t.cre_time, 'yyyy/MM') YEAR_MONTH,")
		.append("1")
		.append("  from erp_deliver_order t, erp_deliver_order_dtl d")
		.append(" where   t.deliver_order_id = d.deliver_order_id")
		.append(" and t.order_chann_id = '"+channId+"'")
		.append("  and t.del_flag = 0")
		.append(" and d.del_flag = 0")
		.append(" and nvl(d.IS_FREE_FLAG, 0) = 0")
		.append(" and  t.is_all_freeze_flag = 1")
		.append(" and t.state in ('已提交生产')");
		Map params = new HashMap();
		params.put("InsSQL", instSql.toString());
		return txInscom(params);
	}

	
	/**
	 * 获取列表信息
	 * 
	 * @param params
	 * @return
	 */
	public List selcomList(Map params) {
		return this.findList("sqlcom.query", params);
	}

	public boolean txUpdcom(Map params) {
		return update("sqlcom.update", params);
	}

	public boolean txInscom(Map params) {
		insert("sqlcom.insert", params);
		return true;
	}
	
	public boolean txDelcom(Map params) {
		return update("sqlcom.delete", params);
	}

}
