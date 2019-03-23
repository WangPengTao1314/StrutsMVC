package com.hoperun.commons.service.impl;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.SpringContextUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.querypaymentrep.model.TestPojo;

public class CreditCtrlServiceImpl extends BaseService {

	/**
	 * 取U9当前信用
	 * 
	 * @param channId
	 * @return
	 * @throws Exception
	 */
	public static String getU9CurrCredit(String CHANN_NO)
			throws Exception {
		String jsonStr = LogicUtil.queryPayMent(CHANN_NO, null);
		List<TestPojo> list = new Gson().fromJson(jsonStr,
				new TypeToken<ArrayList<TestPojo>>() {
				}.getType());
		float AcctAmount = 0;
		if (list != null && list.size() > 0) {
			for (TestPojo model : list) {
				String status = model.getStatus();
				if (!"false".equals(status)) {
					AcctAmount = AcctAmount + Float.parseFloat(model.getAcctAmount());
				}else{
					return null;
				}
			}
		}else{
			return null;
		}
		if(AcctAmount!=0.0){
			AcctAmount = AcctAmount*-1;
		}
		return String.format("%.2f", AcctAmount);
	}

	/**
	 * 销售订单关闭时回冻结信用
	 * 
	 * @return
	 */
	public boolean updateFreezeCreditForSaleClose(String DELIVER_ORDER_ID,
			String DELIVER_ORDER_Dtl_IDS) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b").append(
				"   set b.freeze_credit = NVL(b.freeze_credit, 0) -").append(
				"  (select NVL(SUM((NVL(d.ORDER_NUM, 0) -").append(
				"   NVL(d.SENDED_NUM, 0)) *  ").append(
				"   NVL(d.credit_freeze_price, 0)), 0) ").append(
				"  FROM ERP_SALE_ORDER_DTL d").append(
				"  WHERE NVL(d.IS_FREE_FLAG, 0) = 0").append(
				" and d.DEL_FLAG = 0").append(
				"  and d.SALE_ORDER_DTL_ID in (" + DELIVER_ORDER_Dtl_IDS + ")")
				.append("  )").append("  WHERE b.CHANN_ID =").append(
						"  (select t.ORDER_CHANN_ID").append(
						"   FROM ERP_SALE_ORDER t").append(
						"   WHERE t.SALE_ORDER_ID = '" + DELIVER_ORDER_ID
								+ "')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return txUpdcom(params);
	}

	/**
	 * 销售订单关闭时回冻结信用流水账
	 * 
	 * @return
	 */
	public boolean inertCreditJournalSaleClose(String erpSaleOrderDtlIds,
			String DIRECTION) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff
				.append("insert into BASE_JOURNAL_CREDIT_ACCT")
				.append("   (JOURNAL_CREDIT_ACCT_ID,")
				.append(
						" BUSS_TYPE, BILL_ID,BILL_NO, BILL_DTL_ID,CHANN_ID,CHANN_NO, CHANN_NAME,PRD_ID, SPCL_TECH_ID, PRD_NO,")
				.append(
						" PRD_NAME, PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT, PRD_NUM, DEAL_TIME,FREEZE_PRICE, YEAR_MONTH,")
				.append("   DIRECTION)")
				.append(" select rawtohex(sys_guid()) as UUID,")
				.append(" '销售订单行关闭',")
				.append("  d.sale_order_id,")
				.append("  t.sale_order_no,")
				.append(" d.sale_order_dtl_id,")
				.append("  t.order_chann_id,")
				.append(" t.order_chann_no,")
				.append("  t.order_chann_name,")
				.append("  d.prd_id,")
				.append("  d.spcl_tech_id,")
				.append(" d.prd_no,")
				.append(" d.prd_name,")
				.append(" d.prd_spec,")
				.append(" d.prd_color,")
				.append("  d.brand,")
				.append("  d.std_unit,")
				.append("NVL(d.ORDER_NUM, 0) -NVL(d.SENDED_NUM, 0),")
				.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
				.append("  NVL(d.CREDIT_FREEZE_PRICE,0),")
				.append("  to_char(sysdate, 'yyyy/MM'),")
				.append(DIRECTION)
				.append(" from erp_sale_order t,erp_sale_order_dtl d")
				.append(
						"  where t.sale_order_id = d.sale_order_id and NVL(d.is_free_flag,0) = 0  and   d.sale_order_dtl_id in (")
				.append(erpSaleOrderDtlIds).append(")");
		Map params = new HashMap();
		params.put("InsSQL", sqlBuff.toString());
		boolean flg = txInscom(params);
		return flg;
	}
	
	/**是否是赠品订货
	 * @param SALE_ORDER_DTL_ID
	 * @return
	 */
	public boolean checkIsFreeOrder(String SALE_ORDER_DTL_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select t.BILL_TYPE from erp_sale_order t where t.sale_order_id in ")
		.append(" (select d.sale_order_id from erp_sale_order_dtl d  where d.sale_order_dtl_id = '"+SALE_ORDER_DTL_ID+"')");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuff.toString());
		Map freeOrderMap = selcom(params);	
		String BILL_TYPE = (String)freeOrderMap.get("BILL_TYPE");
		if("赠品订货".equals(BILL_TYPE)){
			return true;
		}
		return false;
	}

	/**
	 * 发运确认回填实发数量时减冻结信用
	 * 
	 * @param SALE_ORDER_DTL_ID
	 * @return
	 */
	public boolean updateFreezeCredit(String SALE_ORDER_DTL_ID,
			String SENDED_NUM) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b").append(
				"   set b.FREEZE_CREDIT = NVL(b.FREEZE_CREDIT, 0) -").append(
				"  NVL((select NVL(d.CREDIT_FREEZE_PRICE,0)*NVL(" + SENDED_NUM + ", 0) ").append(
				"  FROM ERP_SALE_ORDER_DTL d").append(
				"  WHERE NVL(d.IS_FREE_FLAG, 0) = 0").append(
				" and d.DEL_FLAG = 0").append(
				"  and d.SALE_ORDER_DTL_ID = '" + SALE_ORDER_DTL_ID + "'")
				.append("  ),0)").append("  WHERE b.CHANN_ID =").append(
						"  (select t.ORDER_CHANN_ID").append(
						"   FROM ERP_SALE_ORDER t").append(
						"   WHERE t.SALE_ORDER_ID = (select d.sale_order_id from ERP_SALE_ORDER_DTL d where d.sale_order_dtl_id = '"+SALE_ORDER_DTL_ID+"') " 
								+ ")");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return txUpdcom(params);
	}

	/**
	 * 发运确认时扣减冻结信用流水账
	 * 
	 * @return
	 */
	public boolean inertCreditJournal(String erpSaleOrderDtlId,
			String realStoreNum, String DIRECTION) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff
				.append("insert into BASE_JOURNAL_CREDIT_ACCT")
				.append("   (JOURNAL_CREDIT_ACCT_ID,")
				.append(
						" BUSS_TYPE, BILL_ID,BILL_NO, BILL_DTL_ID,CHANN_ID,CHANN_NO, CHANN_NAME,PRD_ID, SPCL_TECH_ID, PRD_NO,")
				.append(
						" PRD_NAME, PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT, PRD_NUM, DEAL_TIME,FREEZE_PRICE, YEAR_MONTH,")
				.append("   DIRECTION)")
				.append(" select rawtohex(sys_guid()) as UUID,")
				.append(" '货品发运',")
				.append("  d.sale_order_id,")
				.append("  t.sale_order_no,")
				.append(" d.sale_order_dtl_id,")
				.append("  t.order_chann_id,")
				.append(" t.order_chann_no,")
				.append("  t.order_chann_name,")
				.append("  d.prd_id,")
				.append("  d.spcl_tech_id,")
				.append(" d.prd_no,")
				.append(" d.prd_name,")
				.append(" d.prd_spec,")
				.append(" d.prd_color,")
				.append("  d.brand,")
				.append("  d.std_unit,")
				.append(realStoreNum + ",")
				.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
				.append("  NVL(d.CREDIT_FREEZE_PRICE,0),")
				.append("  to_char(sysdate, 'yyyy/MM'),")
				.append(DIRECTION)
				.append(" from erp_sale_order t,erp_sale_order_dtl d")
				.append(
						"  where t.sale_order_id = d.sale_order_id and NVL(d.is_free_flag,0) = 0  and   d.sale_order_dtl_id = '")
				.append(erpSaleOrderDtlId).append("'");
		Map params = new HashMap();
		params.put("InsSQL", sqlBuff.toString());
		boolean flg = txInscom(params);
		return flg;
	}

	/**
	 * 得本次要用的信用 (按30%的信用算) 订货订单CHECK 30%
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getBusCrediteByGoodsOrder(String bussID, Map confMap)
			throws Exception {
		StringBuffer busBuf = new StringBuffer();
		busBuf.append("select ").append(
				"sum((NVL((" + confMap.get("FREEZE_PRICE_COL").toString()
						+ "),0))* NVL(" + confMap.get("PRD_NUM_COL").toString()
						+ ",0)) BUS_CREDITE ").append(
				" , " + confMap.get("CHANN_ID_COL").toString() + " CHANN_ID  ")
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(" and d.del_flag = 0")
				.append(" and " + confMap.get("MAIN_KEY_COL").toString())
				.append("= '").append(bussID).append("'").append(
						" group by " + confMap.get("CHANN_ID_COL"));
		return busBuf.toString();
	}

	private String getFKeyColName(Map confMap) {
		StringBuffer tabBuf = new StringBuffer();
		String aname = (String) confMap.get("DTL_TAB_ANNAME");
		String mainKeyCol = (String) confMap.get("MAIN_KEY_COL");
		if (mainKeyCol.indexOf(".") != -1) {
			tabBuf.append(aname).append(".");
			String[] dtlTable = mainKeyCol.split("\\.");
			tabBuf.append(dtlTable[1]);
		} else {
			return mainKeyCol;
		}
		return tabBuf.toString();
	}

	/**
	 * @param bussID业务类型
	 *            ，是用来获得配置信息用的。
	 * @param GOODS_ORDER_ID
	 *            记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats
	 *            业务状态
	 * @return
	 */
	public boolean txChkCanUseCredit(String bussID, String GOODS_ORDER_ID)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String strTempCredite = getTempCredite(GOODS_ORDER_ID, confMap);
		String strBusCredite = getBusCrediteByGoodsOrder(GOODS_ORDER_ID,
				confMap);
		Map goodsOrderMap = getGoodsOrderMap(GOODS_ORDER_ID);
		String channNo = (String) goodsOrderMap.get("ORDER_CHANN_NO");
		String CREDIT_CURRT = CreditCtrlUtil.getU9CurrCredit(channNo);
		if (StringUtil.isEmpty(CREDIT_CURRT)) {
			throw new ServiceException("获取账户余额失败!");
		}
		StringBuffer checkBuf = new StringBuffer();
		checkBuf.append("Select ").append(
				"(NVL(" + CREDIT_CURRT
						+ ",0)+NVL(b.INI_CREDIT,0)+NVL(b.PAYMENT_CREDIT,0)-NVL(b.FREEZE_CREDIT,0)")
				.append(
						"+NVL((" + strTempCredite
								+ ") ,0)) USER_CREDITE")
				.append(", NVL(bus.BUS_CREDITE,0) BUS_CREDITE").append(
						" from BASE_CHANN b").append(
						",(" + strBusCredite + ") bus").append(
						" where b.CHANN_ID = bus.CHANN_ID");

		Map params = new HashMap();
		params.put("SelSQL", checkBuf.toString());
		List resultList = selcomList(params);
		if (resultList != null && resultList.size() > 0) {
			Map resutMap = (Map) resultList.get(0);
			BigDecimal userCredite = (BigDecimal) resutMap.get("USER_CREDITE"); // 可用信用
			BigDecimal busCredite = (BigDecimal) resutMap.get("BUS_CREDITE"); // 订单扣除信用
			String strUserCredite = userCredite.toString();
			if (!"0".equals(strUserCredite)
					&& userCredite.compareTo(busCredite) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 得临时信用
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getTempCredite(String bussID, Map confMap) throws Exception {
		StringBuffer creditBuf = new StringBuffer();
		creditBuf
				.append("select ")
				.append("sum(TEMP_CREDIT_REQ) TEMP_CREDITE ")
				.append("from ERP_TEMP_CREDIT_REQ req,")
				.append(confMap.get("MAIN_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(" ")
				.append(
						"where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and req.STATE = '审核通过' and req.DEL_FLAG =0 ")
				.append(" and req.CHANN_ID = ").append(
						confMap.get("CHANN_ID_COL").toString()).append(
						" and " + confMap.get("MAIN_KEY_COL").toString())
				.append(" = ").append("'").append(bussID).append("'");
		return creditBuf.toString();
	}

	/**
	 * 查询信用配置信息
	 */
	public Map getGoodsOrderMap(String GOODS_ORDER_ID) {
		String sql = "select t.ORDER_CHANN_NO, b.ship_point_id   from drp_goods_order t, base_chann b  where t.order_chann_id = b.chann_id    and t.goods_order_id = '"
				+ GOODS_ORDER_ID + "'";
		Map params = new HashMap();
		params.put("SelSQL", sql);
		return selcom(params);
	}

	/**
	 * 查询信用配置信息
	 */
	private Map loadCreditConf(String bussID) {
		String sql = "select * from BASE_JOURNAL_CREDIT_ACCT_CONF where JOURNAL_CREDIT_ACCT_CONF_ID='"
				+ bussID + "'";
		Map params = new HashMap();
		params.put("SelSQL", sql);
		return selcom(params);
	}
	
	public String queryUserCredit(String channId) throws Exception{
		CreditCtrlServiceImpl creditCtrlImpl = (CreditCtrlServiceImpl) SpringContextUtil
		.getBean("CreditCtrlService");
		String currCredit = getU9CurrCredit(channId);
		StringBuffer userCreditBuf = new StringBuffer();
		userCreditBuf.append(" select (NVL("+currCredit+", 0) + NVL(b.PAYMENT_CREDIT, 0) -")
		.append(" NVL(b.FREEZE_CREDIT, 0) +")
		.append(" NVL((select sum(NVL(TEMP_CREDIT_REQ,0)) TEMP_CREDITE")
		.append("  from ERP_TEMP_CREDIT_REQ req")
		.append(" where req.TEMP_CREDIT_VALDT >=")
		.append(" to_date(to_char(sysdate, 'yyyy-mm-dd'), 'yyyy-mm-dd')")
		.append("   and req.STATE = '审核通过'")
		.append("  and req.DEL_FLAG = 0")
		.append(" and req.CHANN_ID = '").append(channId).append("'")
		.append("  ),")
		.append("  0) + NVL(b.REBATE_CURRT, 0)) USER_CREDITE")
		.append(" from BASE_CHANN b")
		.append("  where b.CHANN_ID ='").append(channId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", userCreditBuf.toString());
		Map userCreditMap =  selcom(params);
		String USER_CREDITE = String.format("%.2f", userCreditMap.get("USER_CREDITE"));
		return USER_CREDITE;
	}

	/**未提交状态还没冻结信用，不用扣减，其它的提交之后的所有状态都在扣减
	 * @param DELIVER_ORDER_ID
	 * @return
	 * @throws Exception
	 */
	public boolean checkIsUpdateFreeCredit(String DELIVER_ORDER_ID)throws Exception{
		String sql = "select t.STATE from erp_deliver_order t where t.deliver_order_id='"
			+ DELIVER_ORDER_ID + "'";
		Map params = new HashMap();
		params.put("SelSQL", sql);
		Map delverMap = selcom(params);
		String STATE = (String)delverMap.get("STATE");
		if("未提交".equals(STATE)){
			return false;
		}
		return true;
	}
	

	/**更新出货计划手工输入的冻结金额
	 * @param DELIVER_ORDER_ID 发运单ID
	 * @param CHANN_ID  渠道ID
	 * @param direction  0是加,1是减
	 * @return
	 */
	public boolean updateFreeCreditForDeliverOrder(String DELIVER_ORDER_ID,String CHANN_ID,String direction)throws Exception{
		String character = "+";
		if("1".equals(direction)){
			character = "-";
		}
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update base_chann b ")
		.append(" set b.FREEZE_CREDIT = NVL(b.FREEZE_CREDIT, 0) ").append(character)
		.append(" NVL((select NVL(fd.FREEZE_AMOUNT, 0)")
		.append(" from ERP_DELIVER_FREEZE_DTL fd")
		.append("  where fd.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID)
		.append("'  and fd.del_flag = 0 ")
		.append(" and fd.ORDER_CHANN_ID = '").append(CHANN_ID).append("'),0)")
		.append("  WHERE b.CHANN_ID = '").append(CHANN_ID).append("'");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return txUpdcom(params);
	}
	
	/**手工冻结信用
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	public String getFreeAmount(String DELIVER_ORDER_ID,String ORDER_CHANN_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("select d.FREEZE_AMOUNT from ERP_DELIVER_FREEZE_DTL d ")
		.append(" where d.DEL_FLAG =0 and d.DELIVER_ORDER_ID = '")
		.append(DELIVER_ORDER_ID).append("' and d.ORDER_CHANN_ID = '")
		.append(ORDER_CHANN_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuff.toString());
		Map resultMap = selcom(params);	
		if(resultMap!=null){
			BigDecimal FREEZE_AMOUNT =  (BigDecimal)resultMap.get("FREEZE_AMOUNT");
			return FREEZE_AMOUNT.toString();
		}else {
			return "0";
		}

	}
	
	/**出货计划撤销时更新ERP_DELIVER_FREEZE_DTL冻结金额为0
	 * @param DELIVER_ORDER_ID
	 * @param CHANN_ID
	 * @return
	 */
	public boolean updateDeliverFreezeAmount(String DELIVER_ORDER_ID,String CHANN_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" update ERP_DELIVER_FREEZE_DTL fd set fd.FREEZE_AMOUNT = 0  ")
		.append("  where fd.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID)
		.append("' and fd.ORDER_CHANN_ID = '").append(CHANN_ID).append("'");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return txUpdcom(params);
	}

	/**
	 * 出货计划提交库房时冻结信用流水账
	 * 
	 * @return
	 */
	public boolean inertDeliverOrderCreditJournal(String DELIVER_ORDER_ID,String CHANN_ID,String direction)throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff
				.append("insert into BASE_JOURNAL_CREDIT_ACCT")
				.append("   (JOURNAL_CREDIT_ACCT_ID,")
				.append(
						" BUSS_TYPE, BILL_ID,CHANN_ID,CHANN_NO, CHANN_NAME,")
				.append(
						" PRD_NUM, DEAL_TIME,FREEZE_PRICE, YEAR_MONTH,")
				.append("   DIRECTION)")
				.append(" select rawtohex(sys_guid()) as UUID,")
				.append(" '人工冻结信用',")
				.append(" d.deliver_freeze_dtl_id,")
				.append(" d.ORDER_CHANN_ID,")
				.append(" d.ORDER_CHANN_NO,")
				.append("  d.ORDER_CHANN_NAME,")
				.append(" 1,")
				.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
				.append(" d.FREEZE_AMOUNT,")
				.append("  to_char(sysdate, 'yyyy/MM'),")
				.append(direction)
				.append(" from ERP_DELIVER_FREEZE_DTL d")
				.append(
						"  where d.DELIVER_ORDER_ID = '"+DELIVER_ORDER_ID+"' and d.ORDER_CHANN_ID = '")
				.append(CHANN_ID).append("'");
		Map params = new HashMap();
		params.put("InsSQL", sqlBuff.toString());
		boolean flg = txInscom(params);
		return flg;
	}	
	
	/**
	 * 跟据出货计划做信用CHECK 
	 * 
	 * @param bussID业务类型
	 *            ，是用来获得配置信息用的。
	 * @param deliverOrderId
	 *            记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats
	 *            业务状态
	 * @return
	 */
	public String chkUseCreditForDeliverOrder( String deliverOrderId,String CHANN_ID,String CHANN_NO)
			throws Exception {
		String CREDIT_CURRT = CreditCtrlUtil.getU9CurrCredit(CHANN_NO);
		String strTempCredite = getTempCredite2(deliverOrderId,CHANN_ID);
		String strBusCredite = getBusCrediteForDeliverOrder(deliverOrderId,CHANN_ID);
		String FREEZE_AMOUNT = getFreeAmount(deliverOrderId,CHANN_ID); 
		StringBuffer checkBuf = new StringBuffer();
		checkBuf.append("select ").append(
				"(NVL("+CREDIT_CURRT+",0)-NVL(b.FREEZE_CREDIT,0)").append(
				"+NVL(temp.TEMP_CREDITE ,0)+NVL(b.INI_CREDIT,0)) USER_CREDITE").append(
				", NVL(bus.BUS_CREDITE,0) BUS_CREDITE").append(
				" from BASE_CHANN b").append(",(" + strBusCredite + ") bus")
				.append(",(" + strTempCredite + ") temp").append(
						" where b.CHANN_ID = bus.CHANN_ID");

		Map params = new HashMap();
		params.put("SelSQL", checkBuf.toString());
		List resultList = selcomList(params);
		if (resultList != null && resultList.size() > 0) {
			Map resutMap = (Map) resultList.get(0);
			BigDecimal userCredite = (BigDecimal) resutMap.get("USER_CREDITE"); // 可用信用
			BigDecimal busCredite = (BigDecimal) resutMap.get("BUS_CREDITE"); // 当前信用
			float totalCredit = userCredite.floatValue();
			float f_FREEZE_AMOUNT = Float.parseFloat(FREEZE_AMOUNT);
			if (totalCredit-f_FREEZE_AMOUNT>= 0) {
				return "true";
			}else{
				float mustCredite = totalCredit -f_FREEZE_AMOUNT ;
				return "信用额度不够!还需支付"+mustCredite;
			}
		}
		return "true";
	}	
	
	/**
	 * 得临时信用
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getTempCredite2(String deliverOrderId,String CHANN_ID) throws Exception {
		StringBuffer creditBuf = new StringBuffer();
		creditBuf
				.append("select ")
				.append("sum(NVL(req.TEMP_CREDIT_REQ,0)) TEMP_CREDITE ")
				.append("from ERP_TEMP_CREDIT_REQ req ")
				.append(
						"where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and req.STATE = '审核通过' ")
				.append(" and req.CHANN_ID = '").append(CHANN_ID).append("'");
		return creditBuf.toString();
	}
	
	/**
	 * 
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	public String getBusCrediteForDeliverOrder(String deliverOrderId,String CHANN_ID) throws Exception {
		StringBuffer busBuf = new StringBuffer();
		busBuf.append("select ").append(
				"sum((NVL(d.CREDIT_FREEZE_PRICE,0))* NVL(d.PLAN_NUM,0)) BUS_CREDITE ").append(
				" , d.ORDER_CHANN_ID CHANN_ID  ")
				.append(" from ERP_DELIVER_ORDER t")
				.append(",").append(" ERP_DELIVER_ORDER_DTL d")
				.append(" ").append(" where ").append("t.DELIVER_ORDER_ID=d.DELIVER_ORDER_ID")
				.append(" and t.DEL_FLAG = 0 ")
				.append(" and d.DEL_FLAG = 0 ")
				.append(" and NVL(d.IS_FREE_FLAG,0) = 0 ")
				.append(" and d.order_chann_id = '").append(CHANN_ID).append("' ")
				.append(
						" and t.DELIVER_ORDER_ID= '").append(deliverOrderId).append("'").append(
						" group by  d.ORDER_CHANN_ID ");
		return busBuf.toString();
	}	
	
	
	public void updateFreeAmountByChannId(String CHANN_ID){
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select sum(NVL(CREDIT_FREEZE,0)) CREDIT_FREEZE from (")
		.append("select  sum(NVL(d.CREDIT_FREEZE_PRICE, 0) * NVL(d.ORDER_NUM, 0)) CREDIT_FREEZE")
		.append(" from DRP_GOODS_ORDER t, DRP_GOODS_ORDER_DTL d")
		.append("  where t.GOODS_ORDER_ID = d.GOODS_ORDER_ID")
		.append("  and t.ORDER_CHANN_NO = '"+CHANN_ID+"'")
		.append("  and t.STATE in ('未处理')")
		.append("  and t.DEL_FLAG = 0")
		.append("  and t.BILL_TYPe in ('备货订货', '返利订货')")
		.append("  and d.DEL_FLAG = 0")
		.append(" and NVL(d.CREDIT_FREEZE_PRICE, 0) * NVL(d.ORDER_NUM, 0) > 0")
		.append(" union all")
		.append(" select  sum(NVL(sd.CREDIT_FREEZE_PRICE, 0) *  (NVL(sd.ORDER_NUM, 0) - NVL(sd.SENDED_NUM, 0))) CREDIT_FREEZE")
		.append(" from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL sd")
		.append("  where t.SALE_ORDER_ID = sd.SALE_ORDER_ID")
		.append(" and t.ORDER_CHANN_NO = '"+CHANN_ID+"'")
		.append("  and t.BILL_TYPE in ('标准', '非标', '返利订货')")
		.append("  and t.DEL_FLAG = 0 and sd.DEL_FLAG = 0 and sd.STATE = '正常'")
		.append(" and NVL(sd.CREDIT_FREEZE_PRICE, 0) * (NVL(sd.ORDER_NUM, 0) - NVL(sd.SENDED_NUM, 0)) > 0")
		.append(" union all")
		.append(" select  t.FREEZE_AMOUNT")
		.append(" from ERP_DELIVER_ORDER u, ERP_DELIVER_FREEZE_DTL t")
		.append(" where u.DELIVER_ORDER_ID = t.DELIVER_ORDER_ID")
		.append("  and u.DEL_FLAG = 0")
		.append(" and u.STATE in ('已提交库房')")
		.append(" and t.DEL_FLAG = 0 and t.FREEZE_AMOUNT > 0  and t.ORDER_CHANN_NO = '"+CHANN_ID+"'")
		.append(") temp ");
	}
	
	/**推广费冻结预批金额
	 * @param PRMT_COST_REQ_ID
	 * @throws Exception
	 */
	public void txUpdateFreeBudget(String PRMT_COST_REQ_ID) throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select r.BUDGET_QUOTA_ID,r.BUDGET_AMOUNT from  ERP_PRMT_COST_REQ r")
		.append(" where  r.DEL_FLAG=0 and r.PRMT_COST_REQ_ID='").append(PRMT_COST_REQ_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map freeBudgetMap = selcom(params);	
		String BUDGET_QUOTA_ID = (String)freeBudgetMap.get("BUDGET_QUOTA_ID");
		BigDecimal BUDGET_AMOUNT = (BigDecimal)freeBudgetMap.get("BUDGET_AMOUNT");
		if(StringUtil.isEmpty(BUDGET_QUOTA_ID)){
			throw new ServiceException("未设置预算科目!");
		}
		if(BUDGET_AMOUNT==null){
			throw new ServiceException("未设置预批金额!");
		}
		if(BUDGET_AMOUNT.floatValue()<0){
			throw new ServiceException("预批金额不能为负数!");
		}
		//检查额度分配表的数据是否可用
		sqlBuf = new StringBuffer();
		sqlBuf.append(" select count(t.BUDGET_QUOTA_ID) NUM from ERP_BUDGET_QUOTA t where t.del_flag = 0 and t.state = '启用'")
		.append(" and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map budgetQuotaMap = selcom(params);
		BigDecimal num= (BigDecimal)budgetQuotaMap.get("NUM");
//		if(num.intValue()!=1){
//			throw new ServerException("额度分配信用出现异常(停用或有重复数据)!");
//		}
		
		//更新额度分配表的冻结金额
		StringBuffer upSqlBuf = new StringBuffer();
		upSqlBuf.append("update ERP_BUDGET_QUOTA t  set t.FREEZE_BUDGET_QUOTA = NVL(t.FREEZE_BUDGET_QUOTA,0)+")
		.append(BUDGET_AMOUNT.floatValue())
		.append(" WHERE t.BUDGET_QUOTA_ID = '")
		.append(BUDGET_QUOTA_ID)
		.append("'");
		params = new HashMap();
		params.put("UpdSQL", upSqlBuf.toString());
		txUpdcom(params);
		
		instBudgetCreditJournal(PRMT_COST_REQ_ID,BUDGET_QUOTA_ID,"冻结单据","推广费用申请单",0);
		checkFreeAmountByCost(BUDGET_QUOTA_ID);
	}
	
	/**检查推广费用申请单冻结金额与科目冻结金额是否匹配
	 * @param BUDGET_QUOTA_ID
	 * @throws Exception
	 */
	private void checkFreeAmountByCost(String BUDGET_QUOTA_ID)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select t.BUDGET_QUOTA_ID,")
		.append("sum(NVL(r.BUDGET_AMOUNT, 0)) BUDGET_AMOUNT,")
	    .append(" Max(NVL(t.FREEZE_BUDGET_QUOTA, 0)) FREEZE_BUDGET_QUOTA")
		.append(" from ERP_PRMT_COST_REQ r left join ERP_BUDGET_QUOTA t")
		.append(" on r.BUDGET_QUOTA_ID = t.BUDGET_QUOTA_ID")
		.append(" where r.del_flag = 0")
		.append(" and r.state = '审核通过'")
		.append("  and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'")
		.append(" group by t.BUDGET_QUOTA_ID");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map checkMap = selcom(params);
		BigDecimal newBUDGET_AMOUNT =  (BigDecimal)checkMap.get("BUDGET_AMOUNT");
		BigDecimal newFREEZE_BUDGET_QUOTA =  (BigDecimal)checkMap.get("FREEZE_BUDGET_QUOTA");
		
		sqlBuf = new StringBuffer();
		sqlBuf.append("select NVL(SUM(NVL(r.EXPENSE_AMOUNT, 0)),0) EXPENSE_AMOUNT")
		.append("  from ERP_EXPENSE_ORDER r")
		.append("  where r.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'")
		.append("  and r.del_flag = 0")
		.append("  and r.state = '审核通过'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map costReitMap = selcom(params);
		BigDecimal EXPENSE_AMOUNT= (BigDecimal)costReitMap.get("EXPENSE_AMOUNT");
		float newFreezeAmount = newBUDGET_AMOUNT.subtract(EXPENSE_AMOUNT).floatValue();
		if(newFreezeAmount<0){
			throw new ServiceException("报销金额超过了预批金额!");
		}
		if(newFreezeAmount!=newFREEZE_BUDGET_QUOTA.floatValue()){
			throw new ServiceException("冻结金额与单据预批金额不匹配!");
		}		
	}
	
	/**推广费用-预算流水
	 * @param PRMT_COST_REQ_ID
	 * @param JOURNAL_TYPE
	 * @param BUSS_TYPE
	 * @param DIRECTION
	 * @throws Exception
	 */
	public void instBudgetCreditJournal(String PRMT_COST_REQ_ID,String BUDGET_QUOTA_ID,String JOURNAL_TYPE,String BUSS_TYPE,int DIRECTION)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" insert into BASE_JOURNAL_BUDGET_ACCT(JOURNAL_BUDGET_ACCT_ID,JOURNAL_TYPE,BUSS_TYPE,")
		.append("BILL_ID,BILL_NO,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_AMOUNT,DEAL_TIME,YEAR_MONTH,DIRECTION,BUDGET_QUOTA_ID)")
		.append("select rawtohex(sys_guid()) as UUID,")
		.append("'").append(JOURNAL_TYPE).append("',")
		.append("'").append(BUSS_TYPE).append("',")
		.append("PRMT_COST_REQ_ID,PRMT_COST_REQ_NO,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,BUDGET_AMOUNT,")
		.append("to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),to_char(sysdate, 'yyyy/MM'),")
		.append(DIRECTION).append(",")
		.append("'").append(BUDGET_QUOTA_ID).append("'")
		.append(" from ERP_PRMT_COST_REQ where PRMT_COST_REQ_ID = '").append(PRMT_COST_REQ_ID).append("'");
		
		Map params = new HashMap();
		params.put("InsSQL", sqlBuf.toString());
		txInscom(params);
	}
	
	/**释放冻结预批金额,增加预批金额
	 * @param PRMT_COST_REQ_ID
	 * @throws Exception
	 */
	public void txUpdateBudgetFreeAndAmount(String EXPENSE_ORDER_ID)throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select t.RELATE_ORDER_NOS,t.EXPENSE_AMOUNT,t.BUDGET_QUOTA_ID from  ERP_EXPENSE_ORDER t")
		.append(" where  t.DEL_FLAG=0 and t.EXPENSE_ORDER_ID = '").append(EXPENSE_ORDER_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map costBudgetMap = selcom(params);	
		if(costBudgetMap.get("EXPENSE_AMOUNT")==null){
			throw new ServiceException("申请报销金额不能为空!");
		}
		BigDecimal EXPENSE_AMOUNT = (BigDecimal)costBudgetMap.get("EXPENSE_AMOUNT");
		String RELATE_ORDER_NOS = StringUtil.nullToSring(costBudgetMap.get("RELATE_ORDER_NOS"));
		if(StringUtil.isEmpty(RELATE_ORDER_NOS)){
			throw new ServiceException("未填写的推广费用申请单!");
		}
		
		String BUDGET_QUOTA_ID = StringUtil.nullToSring(costBudgetMap.get("BUDGET_QUOTA_ID"));
		if(StringUtil.isEmpty(BUDGET_QUOTA_ID)){
			throw new ServiceException("报销单未填写预算科目!");
		}
		String [] PRMT_COST_REQ_NO_ARR = RELATE_ORDER_NOS.split(",");
		for(int i=0;i<PRMT_COST_REQ_NO_ARR.length;i++){
			String PRMT_COST_REQ_NO = PRMT_COST_REQ_NO_ARR[i];
			sqlBuf = new StringBuffer();
			sqlBuf.append("select r.BUDGET_QUOTA_ID,r.BUDGET_AMOUNT from  ERP_PRMT_COST_REQ r")
			.append(" where  r.DEL_FLAG=0 and r.STATE = '审核通过' and r.PRMT_COST_REQ_NO='").append(PRMT_COST_REQ_NO).append("'");
			params = new HashMap();
			params.put("SelSQL", sqlBuf.toString());
			Map freeBudgetMap = selcom(params);	
			if(freeBudgetMap==null){
				throw new ServiceException("无关联的推广费用申请单!");
			}
			String BUDGET_QUOTA_ID_Y = StringUtil.nullToSring(freeBudgetMap.get("BUDGET_QUOTA_ID"));
			if(StringUtil.isEmpty(BUDGET_QUOTA_ID_Y)){
				throw new ServiceException("推广费用申请单未设置预算科目!");
			}
			if(!BUDGET_QUOTA_ID.equals(BUDGET_QUOTA_ID_Y)){
				throw new ServiceException("报销单与推广费用申请单的预算科目不一致!");
			}
			if(freeBudgetMap.get("BUDGET_AMOUNT")==null){
				throw new ServiceException("未设置预批金额!");
			}
			BigDecimal BUDGET_AMOUNT = (BigDecimal)freeBudgetMap.get("BUDGET_AMOUNT");
			if(BUDGET_AMOUNT.floatValue()<0){
				throw new ServiceException("预批金额不能为负数!");
			}
		}

		
		//检查额度分配表的数据是否可用
		sqlBuf = new StringBuffer();
		sqlBuf.append(" select count(t.BUDGET_QUOTA_ID) NUM from ERP_BUDGET_QUOTA t where t.del_flag = 0 and t.state = '启用'")
		.append(" and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map budgetQuotaMap = selcom(params);
		BigDecimal num= (BigDecimal)budgetQuotaMap.get("NUM");
		if(num.intValue()!=1){
			throw new ServiceException("额度分配信用出现异常(停用或有重复数据)!");
		}
		
		//更新额度分配表的冻结金额
		StringBuffer upSqlBuf = new StringBuffer();
		upSqlBuf.append("update ERP_BUDGET_QUOTA t  set t.FREEZE_BUDGET_QUOTA = NVL(t.FREEZE_BUDGET_QUOTA,0)-")
		.append(EXPENSE_AMOUNT.floatValue())
		.append(",USE_BUDGET_QUOTA = NVL(USE_BUDGET_QUOTA,0)+")
		.append(EXPENSE_AMOUNT.floatValue())
		.append(" WHERE t.BUDGET_QUOTA_ID = '")
		.append(BUDGET_QUOTA_ID)
		.append("'");
		params = new HashMap();
		params.put("UpdSQL", upSqlBuf.toString());
		txUpdcom(params);
		checkFreeAmountByCost(BUDGET_QUOTA_ID);
		chekcBudgetAmountByExpenseId(BUDGET_QUOTA_ID);
		instBudgetFreeAndAmountCreditJournal(EXPENSE_ORDER_ID,"使用单据",0);
		
	}
	

	/**检查报销单金额与科目单金额是否批配
	 * @param BUDGET_QUOTA_ID
	 */
	private void chekcBudgetAmountByExpenseId(String BUDGET_QUOTA_ID)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select SUM(NVL(t.EXPENSE_AMOUNT, 0)) EXPENSE_AMOUNT,")
		.append(" MAX(NVL(f.USE_BUDGET_QUOTA, 0))USE_BUDGET_QUOTA,")
		.append(" MAX(NVL(f.BUDGET_QUOTA, 0))BUDGET_QUOTA")
		.append("  from ERP_EXPENSE_ORDER t left join ERP_BUDGET_QUOTA f")
		.append("  on t.BUDGET_QUOTA_ID = f.BUDGET_QUOTA_ID")
		.append(" where t.state = '审核通过'")
		.append(" and t.del_flag = 0")
		.append(" and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map checkMap = selcom(params);
		BigDecimal EXPENSE_AMOUNT =  (BigDecimal)checkMap.get("EXPENSE_AMOUNT");  //报销单报销总金额
		BigDecimal USE_BUDGET_QUOTA =  (BigDecimal)checkMap.get("USE_BUDGET_QUOTA");  //使用预算额度
		BigDecimal BUDGET_QUOTA =  (BigDecimal)checkMap.get("BUDGET_QUOTA");     //预算额度
		if(EXPENSE_AMOUNT.floatValue()!=USE_BUDGET_QUOTA.floatValue()){
			throw new ServiceException("报销金额与使用预算额度不批匹配!");
		}
		if(USE_BUDGET_QUOTA.floatValue()>BUDGET_QUOTA.floatValue()){
			throw new ServiceException("使用预算额度超过了预算额度!");
		}
	  }
	
	
	/**推广费用-预算流水
	 * @param PRMT_COST_REQ_ID
	 * @param JOURNAL_TYPE
	 * @param BUSS_TYPE
	 * @param DIRECTION
	 * @throws Exception
	 */
	public void instBudgetFreeAndAmountCreditJournal(String EXPENSE_ORDER_ID,String BUSS_TYPE,int DIRECTION)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" insert into BASE_JOURNAL_BUDGET_ACCT(JOURNAL_BUDGET_ACCT_ID,JOURNAL_TYPE,BUSS_TYPE,")
		.append("BILL_ID,BILL_NO,LEDGER_ID,BUSS_AMOUNT,DEAL_TIME,YEAR_MONTH,DIRECTION)")
		.append("select rawtohex(sys_guid()) as UUID,")
		.append("'").append(BUSS_TYPE).append("',")
		.append("EXPENSE_TYPE,")
		.append("EXPENSE_ORDER_ID,EXPENSE_ORDER_NO,LEDGER_ID,EXPENSE_AMOUNT,")
		.append("to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),to_char(sysdate, 'yyyy/MM'),")
		.append(DIRECTION)
		.append(" from ERP_EXPENSE_ORDER where EXPENSE_ORDER_ID = '").append(EXPENSE_ORDER_ID).append("'");
		
		Map params = new HashMap();
		params.put("InsSQL", sqlBuf.toString());
		txInscom(params);
	}
	
	
	/**装修申请，冻结预批金额              add by zhuxw beg
	 * @param CHANN_RNVTN_ID
	 * @param DIRECTION               0表示冻结,1表示释放
	 * @throws Exception
	 */
	public void txRnvtnReqAcct(String CHANN_RNVTN_ID,String DIRECTION) throws ServiceException {

		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select r.BUDGET_QUOTA_ID,r.REIT_AMOUNT BUDGET_AMOUNT from  DRP_CHANN_RNVTN r")
		.append(" where  r.DEL_FLAG=0 and r.CHANN_RNVTN_ID='").append(CHANN_RNVTN_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map freeBudgetMap = selcom(params);	
		String BUDGET_QUOTA_ID = (String)freeBudgetMap.get("BUDGET_QUOTA_ID");
		
		BigDecimal BUDGET_AMOUNT = (BigDecimal)freeBudgetMap.get("BUDGET_AMOUNT");
		if(BUDGET_AMOUNT==null){
			BUDGET_AMOUNT =  new BigDecimal(0);
		}
		if(StringUtil.isEmpty(BUDGET_QUOTA_ID)){
			throw new ServiceException("未设置:预算科目!");
		}
		
		if(BUDGET_AMOUNT.floatValue()<0){
			throw new ServiceException("装修申请金额不能为负数!");
		}
		//检查额度分配表的数据是否正确
		sqlBuf = new StringBuffer();
		sqlBuf.append(" select count(t.BUDGET_QUOTA_ID) NUM from ERP_BUDGET_QUOTA t where t.del_flag = 0 and t.state = '启用'")
		.append(" and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map budgetQuotaMap = selcom(params);
		BigDecimal num= (BigDecimal)budgetQuotaMap.get("NUM");
		if(num.intValue()!=1){
			throw new ServiceException("对不起！对应的预算科目被停用或存在重复数据)!");
		}
		
		String operator = null;
		//更新额度分配表的冻结金额
//		if("0".equals(DIRECTION)){
//			operator ="+";
//		}else if("1".equals(DIRECTION)){
//			operator ="-";
//		}
		StringBuffer upSqlBuf = new StringBuffer();
		upSqlBuf.append("update ERP_BUDGET_QUOTA t  set t.FREEZE_BUDGET_QUOTA = NVL(t.FREEZE_BUDGET_QUOTA,0)  ")
		.append(operator).append(" ")
		.append(BUDGET_AMOUNT.floatValue())
		.append(" WHERE t.BUDGET_QUOTA_ID = '")
		.append(BUDGET_QUOTA_ID)
		.append("'");
		params = new HashMap();
		params.put("UpdSQL", upSqlBuf.toString());
		boolean flag = txUpdcom(params);
		//插入流水账
		/*
		if("0".equals(DIRECTION)){
			StringBuffer insertSql = new StringBuffer();
			insertSql.append(" insert into BASE_JOURNAL_BUDGET_ACCT(JOURNAL_BUDGET_ACCT_ID,JOURNAL_TYPE,BUSS_TYPE,")
			.append("BILL_ID,BILL_NO,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_AMOUNT,DEAL_TIME,YEAR_MONTH,DIRECTION,BUDGET_QUOTA_ID)")
			.append("select u.CHANN_RNVTN_ID,")
			.append("'冻结单据',")
			.append("'装修费用申请单',")
			.append(" u.CHANN_RNVTN_ID ,u.CHANN_RNVTN_NO,u.LEDGER_ID,u.CHANN_ID,u.CHANN_NO,u.CHANN_NAME,NVL(u.REIT_AMOUNT,0),")
			.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),to_char(sysdate, 'yyyy/MM'),").append(DIRECTION).append(",'")
			.append( BUDGET_QUOTA_ID)
			.append("' from DRP_CHANN_RNVTN u where u.CHANN_RNVTN_ID = '").append(CHANN_RNVTN_ID).append("'");
			 Map paramsT = new HashMap();
		     paramsT.put("InsSQL", insertSql.toString());
			 boolean flg = txInscom(paramsT);
		}else if("1".equals(DIRECTION)) {   //否决时删除流水账
			StringBuffer delSql = new StringBuffer();
			delSql.append(" delete from BASE_JOURNAL_BUDGET_ACCT ac where ac.JOURNAL_BUDGET_ACCT_ID ='")
			.append(CHANN_RNVTN_ID)
			.append("'");
			Map delParams = new HashMap();
			params.put("DelSQL", delSql.toString());
			txDelcom(params);
		}*/
		//校验是否满足条件
		checkRnvtnFreeze(BUDGET_QUOTA_ID,CHANN_RNVTN_ID,String.valueOf(BUDGET_AMOUNT));
	}
	
	/**检查装修费用申请单冻结金额与科目冻结金额是否匹配
	 * @param BUDGET_QUOTA_ID
	 * @throws Exception
	 */
	private void checkRnvtnFreeze(String BUDGET_QUOTA_ID,String CHANN_RNVTN_ID,String REQ_AMOUNT)throws ServiceException{
		//获得当前额度，和已冻结额度，可使用额度
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select t.BUDGET_QUOTA_ID,")
		.append(" NVL(t.BUDGET_QUOTA, 0)   BUDGET_AMOUNT,")
		.append(" NVL(t.FREEZE_BUDGET_QUOTA, 0) FREEZE_BUDGET_QUOTA,")
		.append(" NVL(t.BUDGET_QUOTA, 0)- NVL(t.USE_BUDGET_QUOTA, 0)  CAN_USE_BUDGET, ")
		.append(" NVL(t.BUDGET_QUOTA, 0)- NVL(t.USE_BUDGET_QUOTA, 0)-NVL(t.FREEZE_BUDGET_QUOTA, 0)  IS_CAN_USE_BUDGET ")
		.append(" from ERP_BUDGET_QUOTA t")
		.append(" where  t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map checkMap = selcom(params);
		String canUseBudget = StringUtil.nullToSring(checkMap.get("CAN_USE_BUDGET"));
		Double isCanUseBudget =  Double.parseDouble(StringUtil.nullToSring(checkMap.get("IS_CAN_USE_BUDGET")));
		BigDecimal freezeBudgetQuota =  (BigDecimal)checkMap.get("FREEZE_BUDGET_QUOTA");
		
		//获得冻结流水账总额
		sqlBuf = new StringBuffer();
		sqlBuf.append("select NVL(sum(decode(b.DIRECTION ,0,1,1,-1)*b.BUSS_AMOUNT),0) TOTAL_FREEZE_AMOUNT ");
		sqlBuf.append("from  BASE_JOURNAL_BUDGET_ACCT b where b.Journal_Type='冻结单据' and b.BUDGET_QUOTA_ID='");
		sqlBuf.append(BUDGET_QUOTA_ID).append("'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map costReitMap = selcom(params);
		BigDecimal totalFreezeAmount= (BigDecimal)costReitMap.get("TOTAL_FREEZE_AMOUNT");
		if(isCanUseBudget<0){
			throw new ServiceException("对不起！本次预申请金额：【"+REQ_AMOUNT+"】超过最大可用金额 ：【"+canUseBudget+"】");
		}
 		if(freezeBudgetQuota.compareTo(totalFreezeAmount)!=0){
			throw new ServiceException("对不起！冻结流水金额：【"+totalFreezeAmount+"】与冻结总账金额【"+freezeBudgetQuota+"】不匹配，请联系管理员！");
		}	
	}

	/**装修费用报销记账
	 * @param RNVTN_REIT_REQ_ID
	 * @throws Exception
	 */
	public void txUpdRnvtnReitAcct(String RNVTN_REIT_REQ_ID)throws Exception {
		//验证装修报销单信息
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" select t.CHANN_RNVTN_NO,NVL(t.REIT_AMOUNT,0)REIT_AMOUNT,NVL(t.REAL_REIT_AMOUNT,0)REAL_REIT_AMOUNT,t.BUDGET_QUOTA_ID from  DRP_RNVTN_REIT_REQ t")
		.append(" where  t.DEL_FLAG=0 and t.RNVTN_REIT_REQ_ID = '").append(RNVTN_REIT_REQ_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map costBudgetMap = selcom(params);	
		Double reitAount  = Double.parseDouble(StringUtil.nullToSring(costBudgetMap.get("REIT_AMOUNT")));
		Double reitAountT = Double.parseDouble(StringUtil.nullToSring(costBudgetMap.get("REAL_REIT_AMOUNT")));
		if(reitAount==0){
			throw new ServiceException("对不起！申请报销金额不能为空!");
		}
		if(reitAountT==0){
			throw new ServiceException("对不起！实际报销金额不能为空!");
		}
		//String channRnvtnNo = StringUtil.nullToSring(costBudgetMap.get("CHANN_RNVTN_NO"));

		String BUDGET_QUOTA_ID = StringUtil.nullToSring(costBudgetMap.get("BUDGET_QUOTA_ID"));
		if(StringUtil.isEmpty(BUDGET_QUOTA_ID)){
			throw new ServiceException("对不起！装修报销单的预算科目不能为空!");
		}
		//验证装修申请单信息
		String channRnvtnId = "";
//		if(!StringUtil.isEmpty(channRnvtnNo)) {
//			sqlBuf = new StringBuffer();
//			sqlBuf.append("select r.BUDGET_QUOTA_ID,r.REIT_AMOUNT BUDGET_AMOUNT,r.CHANN_RNVTN_ID from  DRP_CHANN_RNVTN r")
//			.append(" where  r.DEL_FLAG=0 and r.CHANN_RNVTN_NO='").append(channRnvtnNo).append("'");
//			params = new HashMap();
//			params.put("SelSQL", sqlBuf.toString());
//		    Map freeBudgetMap = selcom(params);	
//			if(freeBudgetMap==null){
//				throw new ServiceException("对不起！找不到对应的装修申请单!");
//			}
//			String BUDGET_QUOTA_ID_Y = StringUtil.nullToSring(freeBudgetMap.get("BUDGET_QUOTA_ID"));
//			if(StringUtil.isEmpty(BUDGET_QUOTA_ID_Y)){
//				throw new ServiceException("对不起！装修申请单未设置预算科目!");
//			}
//			if(!BUDGET_QUOTA_ID.equals(BUDGET_QUOTA_ID_Y)){
//				throw new ServiceException("对不起！装修报销单与装修申请单的预算科目不一致!");
//			}
//			channRnvtnId= StringUtil.nullToSring(freeBudgetMap.get("CHANN_RNVTN_ID"));
//			BigDecimal BUDGET_AMOUNT = (BigDecimal)freeBudgetMap.get("BUDGET_AMOUNT");
//		  }
			
		//检查额度分配表的数据是否可用
		sqlBuf = new StringBuffer();
		sqlBuf.append(" select count(t.BUDGET_QUOTA_ID) NUM from ERP_BUDGET_QUOTA t where t.del_flag = 0 and t.state = '启用'")
		.append(" and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map budgetQuotaMap = selcom(params);
		BigDecimal num= (BigDecimal)budgetQuotaMap.get("NUM");
		if(num.intValue()!=1){
			throw new ServiceException("对不起！对应的预算科目被停用或存在重复数据)!");
		}
		
		//更新额度分配表的冻结金额和报销金额
		sqlBuf = new StringBuffer();
		sqlBuf.append("update ERP_BUDGET_QUOTA t  set t.FREEZE_BUDGET_QUOTA = NVL(t.FREEZE_BUDGET_QUOTA,0)-")
		.append(reitAount)
		.append(",USE_BUDGET_QUOTA = NVL(USE_BUDGET_QUOTA,0)+")
		//.append(reitAount)
		.append(reitAountT)
		.append(" WHERE t.BUDGET_QUOTA_ID = '")
		.append(BUDGET_QUOTA_ID)
		.append("'");
		params = new HashMap();
		params.put("UpdSQL", sqlBuf.toString());
		txUpdcom(params);	
		
		//插入解冻流水账
		sqlBuf = new StringBuffer();
		sqlBuf.append(" insert into BASE_JOURNAL_BUDGET_ACCT(JOURNAL_BUDGET_ACCT_ID,JOURNAL_TYPE,BUSS_TYPE,")
		.append("BILL_ID,BILL_NO,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_AMOUNT,DEAL_TIME,YEAR_MONTH,DIRECTION,BUDGET_QUOTA_ID)")
		.append("select rawtohex(sys_guid()) as UUID,")
		.append("'冻结单据',")
		.append("'装修费用报销单',")
		.append(" u.RNVTN_REIT_REQ_ID ,u.RNVTN_REIT_REQ_NO,u.LEDGER_ID,u.CHANN_ID,u.CHANN_NO,u.CHANN_NAME,NVL(u.REIT_AMOUNT,0),")
		.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),to_char(sysdate, 'yyyy/MM'),1, '")
		.append(BUDGET_QUOTA_ID)
		.append("'  from DRP_RNVTN_REIT_REQ u where u.RNVTN_REIT_REQ_ID = '").append(RNVTN_REIT_REQ_ID).append("'");
		 Map paramsT = new HashMap();
		 paramsT.put("InsSQL", sqlBuf.toString());
		 boolean flg = txInscom(paramsT);


		//插入实际使用流水账
		sqlBuf = new StringBuffer();
		sqlBuf.append(" insert into BASE_JOURNAL_BUDGET_ACCT(JOURNAL_BUDGET_ACCT_ID,JOURNAL_TYPE,BUSS_TYPE,")
		.append("BILL_ID,BILL_NO,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_AMOUNT,DEAL_TIME,YEAR_MONTH,DIRECTION,BUDGET_QUOTA_ID)")
		.append("select rawtohex(sys_guid()) as UUID,")
		.append("'使用单据',")
		.append("'装修费用报销单',")
		.append(" u.RNVTN_REIT_REQ_ID ,u.RNVTN_REIT_REQ_NO,u.LEDGER_ID,u.CHANN_ID,u.CHANN_NO,u.CHANN_NAME,NVL(u.REAL_REIT_AMOUNT,0),")
		.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),to_char(sysdate, 'yyyy/MM'),0,'")
		.append(BUDGET_QUOTA_ID)
		.append("' from DRP_RNVTN_REIT_REQ u where u.RNVTN_REIT_REQ_ID = '").append(RNVTN_REIT_REQ_ID).append("'");
		paramsT = new HashMap();
		paramsT.put("InsSQL", sqlBuf.toString());
		boolean flag = txInscom(paramsT);

		//校验实际报销金额不能超过申请金额
		if(!StringUtil.isEmpty(channRnvtnId)) {
		  checkInfactAndReq(channRnvtnId,reitAount,reitAountT);
		}
		//校验已报销金额，冻结金额与流水匹配
		checkCommReit(BUDGET_QUOTA_ID);
		
	}
	
	
	/**装修费用报销提交时冻结/释放金额,无来源单据才要冻结/释放
	 * @param RNVTN_REIT_REQ_ID
	 * @throws Exception
	 */
	public void txUpdRnvtnReitFreeezeAcct(String RNVTN_REIT_REQ_ID,String DIRECTION)throws Exception {
		//验证装修报销单信息
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" select t.CHANN_RNVTN_NO,NVL(t.REIT_AMOUNT,0)REIT_AMOUNT,t.BUDGET_QUOTA_ID from  DRP_RNVTN_REIT_REQ t")
		.append(" where  t.DEL_FLAG=0 and t.RNVTN_REIT_REQ_ID = '").append(RNVTN_REIT_REQ_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map costBudgetMap = selcom(params);	
		Double reitAount = Double.parseDouble(StringUtil.nullToSring(costBudgetMap.get("REIT_AMOUNT")));
		if(reitAount==0){
			throw new ServiceException("对不起！申请报销金额不能为空!");
		}
		
//		String channRnvtnNo = StringUtil.nullToSring(costBudgetMap.get("CHANN_RNVTN_NO"));
//		if(!StringUtil.isEmpty(channRnvtnNo)){
//			return;
//		}
		
		String BUDGET_QUOTA_ID = StringUtil.nullToSring(costBudgetMap.get("BUDGET_QUOTA_ID"));
		if(StringUtil.isEmpty(BUDGET_QUOTA_ID)){
			throw new ServiceException("对不起！装修报销单的预算科目不能为空!");
		}
			
		//检查额度分配表的数据是否可用
		sqlBuf = new StringBuffer();
		sqlBuf.append(" select count(t.BUDGET_QUOTA_ID) NUM from ERP_BUDGET_QUOTA t where t.del_flag = 0 and t.state = '启用'")
		.append(" and t.BUDGET_QUOTA_ID = '").append(BUDGET_QUOTA_ID).append("'");
		params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map budgetQuotaMap = selcom(params);
		BigDecimal num= (BigDecimal)budgetQuotaMap.get("NUM");
		if(num.intValue()!=1){
			throw new ServiceException("对不起！对应的预算科目被停用或存在重复数据)!");
		}
		
		String operator = null;
		//更新额度分配表的冻结金额
		if("0".equals(DIRECTION)){
			operator ="+";
		}else if("1".equals(DIRECTION)){
			operator ="-";
		}
		//更新额度分配表的冻结金额
		sqlBuf = new StringBuffer();
		sqlBuf.append("update ERP_BUDGET_QUOTA t set ")
		.append(" FREEZE_BUDGET_QUOTA = NVL(FREEZE_BUDGET_QUOTA,0)").append(operator).append(" ")
		.append(reitAount)
		.append(" WHERE t.BUDGET_QUOTA_ID = '")
		.append(BUDGET_QUOTA_ID)
		.append("'");
		params = new HashMap();
		params.put("UpdSQL", sqlBuf.toString());
		txUpdcom(params);
			
		//插入冻结流水账
		sqlBuf = new StringBuffer();
		sqlBuf.append(" insert into BASE_JOURNAL_BUDGET_ACCT(JOURNAL_BUDGET_ACCT_ID,JOURNAL_TYPE,BUSS_TYPE,")
		.append("BILL_ID,BILL_NO,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,BUSS_AMOUNT,DEAL_TIME,YEAR_MONTH,DIRECTION,BUDGET_QUOTA_ID)")
		.append("select rawtohex(sys_guid()) as UUID,")
		.append("'冻结单据',")
		.append("'装修费用报销单',")
		.append(" u.RNVTN_REIT_REQ_ID ,u.RNVTN_REIT_REQ_NO,u.LEDGER_ID,u.CHANN_ID,u.CHANN_NO,u.CHANN_NAME,NVL(u.REIT_AMOUNT,0),")
		.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),to_char(sysdate, 'yyyy/MM'),").append(DIRECTION).append(",'")
		.append(BUDGET_QUOTA_ID)
		.append("' from DRP_RNVTN_REIT_REQ u where u.RNVTN_REIT_REQ_ID = '").append(RNVTN_REIT_REQ_ID).append("'");
		HashMap paramsT = new HashMap();
		paramsT.put("InsSQL", sqlBuf.toString());
		boolean flag = txInscom(paramsT);

		//校验已报销金额，冻结金额与流水匹配
		checkCommReit(BUDGET_QUOTA_ID);
		
	}
	
	/**通用报销check
	 * @param BUDGET_QUOTA_ID
	 */
	private void checkCommReit(String BUDGET_QUOTA_ID)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select NVL(u.BUDGET_QUOTA, 0)BUDGET_AMOUNT ,");
		sqlBuf.append("   NVL(u.USE_BUDGET_QUOTA, 0)USE_BUDGET_QUOTA,");
		sqlBuf.append("   NVL(u.FREEZE_BUDGET_QUOTA, 0) FREEZE_BUDGET_QUOTA,");
		sqlBuf.append("   NVL((select sum(decode(b.DIRECTION ,0,1,1,-1)*NVL(b.BUSS_AMOUNT,0)) ");
		sqlBuf.append("       from  BASE_JOURNAL_BUDGET_ACCT b where b.Journal_Type='使用单据' and b.BUDGET_QUOTA_ID=u.BUDGET_QUOTA_ID),0)BILL_USED_AMOUNT,");
	    sqlBuf.append("   NVL((select sum(decode(b.DIRECTION ,0,1,1,-1)*NVL(b.BUSS_AMOUNT,0))");
	    sqlBuf.append("        from  BASE_JOURNAL_BUDGET_ACCT b where b.Journal_Type='冻结单据' and b.BUDGET_QUOTA_ID=u.BUDGET_QUOTA_ID),0)BILL_FREEZE_AMOUNT");
	    sqlBuf.append(" from ");
	    sqlBuf.append(" ERP_BUDGET_QUOTA u   ");     
	    sqlBuf.append(" where");
	    sqlBuf.append(" u.BUDGET_QUOTA_ID='").append(BUDGET_QUOTA_ID).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map checkMap = selcom(params);
		Double budgetAmount=Double.parseDouble(StringUtil.nullToSring(checkMap.get("BUDGET_AMOUNT")));
		Double useBudgetQuota=Double.parseDouble(StringUtil.nullToSring(checkMap.get("USE_BUDGET_QUOTA")));
		Double feeezeBuggetQuota=Double.parseDouble(StringUtil.nullToSring(checkMap.get("FREEZE_BUDGET_QUOTA")));
		Double billUsedAmount=Double.parseDouble(StringUtil.nullToSring(checkMap.get("BILL_USED_AMOUNT")));
		Double billFreezeAmount=Double.parseDouble(StringUtil.nullToSring(checkMap.get("BILL_FREEZE_AMOUNT")));
		if(budgetAmount-useBudgetQuota-feeezeBuggetQuota<0){
			throw new ServiceException("对不起！当前报销的金额 超出 最大可报销金额！");
		}
 		if(!useBudgetQuota.equals(billUsedAmount)){
			throw new ServiceException("对不起！实际报销流水金额：【"+billUsedAmount+"】与已报销金额总账【"+useBudgetQuota+"】不匹配，请联系管理员！");
		}
		if(!feeezeBuggetQuota.equals(billFreezeAmount)){
			throw new ServiceException("对不起！冻结流水金额：【"+billFreezeAmount+"】与冻结总账金额【"+feeezeBuggetQuota+"】不匹配，请联系管理员！");
		}
	}
	
	public void checkInfactAndReq(String channRnvtnId,Double reitAount,Double reitAountT)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("	select ");
		sqlBuf.append("   u.REIT_AMOUNT REQ_REIT_AMOUNT,");
		sqlBuf.append("  NVL(( ");
		sqlBuf.append("   select sum(NVL(a.REIT_AMOUNT,0))");
		sqlBuf.append("   from DRP_RNVTN_REIT_REQ a ,BASE_JOURNAL_BUDGET_ACCT b");
		sqlBuf.append("   where a.RNVTN_REIT_REQ_ID=b.BILL_ID");
		sqlBuf.append("  and b.Journal_Type='使用单据'");
	    sqlBuf.append("  and a.CHANN_RNVTN_ID=u.CHANN_RNVTN_ID");
		sqlBuf.append(" ),0) USED_REIT_AMOUNT");
		sqlBuf.append(" from  DRP_CHANN_RNVTN u  ");
		sqlBuf.append("where  u.DEL_FLAG=0 and u.CHANN_RNVTN_ID='").append(channRnvtnId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map checkMap = selcom(params);
		Double reqReitAmount=Double.parseDouble(StringUtil.nullToSring(checkMap.get("REQ_REIT_AMOUNT")));
		Double usedReitAmount=Double.parseDouble(StringUtil.nullToSring(checkMap.get("USED_REIT_AMOUNT")));
		if(reqReitAmount.compareTo(usedReitAmount) <0 ){
			throw new ServiceException("对不起！本次实际报销金额：【"+reitAountT+"】超出最大可报销金额【"+(reqReitAmount+reitAountT-usedReitAmount)+"】！");
		}
	}
// end by zhuxw	
	
	/***成本调整金额
	 * @param COST_ADJUST_ID
	 * @throws Exception
	 */
	public void trUpdateCostAdJust(String COST_ADJUST_ID)throws Exception{
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select t.YEAR, t.MONTH, d.PRD_ID, d.SPCL_TECH_ID,d.CUR_COST_PRICE,d.NEW_COST_PRICE,t.LEDGER_ID,d.COST_ADJUST_DTL_ID ")
		.append(" from DRP_COST_ADJUST t, DRP_COST_ADJUST_DTL d")
		.append("  where t.COST_ADJUST_ID = d.COST_ADJUST_ID")
		.append(" and t.DEL_FLAG = 0  and d.DEL_FLAG = 0 ")
		.append(" and t.COST_ADJUST_ID = '")
		.append(COST_ADJUST_ID)
		.append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		List resultList = selcomList(params);
		for(int i=0 ;i<resultList.size();i++){
			Map costAdjustMap = (Map)resultList.get(i);
			String YEAR = (String)costAdjustMap.get("YEAR");
			String MONTH = (String)costAdjustMap.get("MONTH");
			String PRD_ID = (String)costAdjustMap.get("PRD_ID");
			String SPCL_TECH_ID = (String)costAdjustMap.get("SPCL_TECH_ID");
			String LEDGER_ID = (String)costAdjustMap.get("LEDGER_ID");
			BigDecimal CUR_COST_PRICE = (BigDecimal)costAdjustMap.get("CUR_COST_PRICE");
			BigDecimal NEW_COST_PRICE = (BigDecimal)costAdjustMap.get("NEW_COST_PRICE");
			String COST_ADJUST_DTL_ID = (String)costAdjustMap.get("COST_ADJUST_DTL_ID");
			if(StringUtil.isEmpty(YEAR)||StringUtil.isEmpty(MONTH)||StringUtil.isEmpty(PRD_ID)||StringUtil.isEmpty(LEDGER_ID)){
				throw new ServiceException("年份,月份,货品,账套不能为空!");
			}
			sqlBuf = new StringBuffer();
			sqlBuf.append("select p.COST_PRICE from DRP_COST_PRICE p where p.YEAR = '")
			.append(YEAR).append("'")
			.append(" and p.MONTH = '").append(MONTH).append("'")
			.append(" and p.LEDGER_ID = '").append(LEDGER_ID).append("'")
			.append(" and p.PRD_ID = '").append(PRD_ID).append("'");
			if(!StringUtil.isEmpty(SPCL_TECH_ID)){
				sqlBuf.append(" and SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'");
			}else {
				sqlBuf.append(" and (p.SPCL_TECH_ID is null or p.SPCL_TECH_ID = 'NONE' )");
			}
			
			params = new HashMap();
			params.put("SelSQL", sqlBuf.toString());
			Map costPriceMap = null;
			try{
				 costPriceMap = selcom(params);	
			}catch(Exception ex){
				throw new ServiceException("原成本数据出错,请联系管理员!");
			}
			
			BigDecimal oldCOST_PRICE = (BigDecimal)costPriceMap.get("COST_PRICE");
			if(oldCOST_PRICE.floatValue() != CUR_COST_PRICE.floatValue()){
				throw new ServiceException("当前成本已经发生变化，不能进行成本调整!");
			}
			
			StringBuffer upSqlBuf = new StringBuffer();
			upSqlBuf.append(" update DRP_COST_PRICE p  set p.COST_PRICE = ")
			.append(NEW_COST_PRICE)
			.append(" where p.YEAR = '").append(YEAR).append("'")
			.append(" and p.MONTH = '").append(MONTH).append("'")
			.append(" and p.LEDGER_ID = '").append(LEDGER_ID).append("'")
			.append(" and p.PRD_ID = '").append(PRD_ID).append("'");
			if(!StringUtil.isEmpty(SPCL_TECH_ID)){
				upSqlBuf.append(" and SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'");
			}else {
				upSqlBuf.append(" and (p.SPCL_TECH_ID is null or p.SPCL_TECH_ID = 'NONE' )");
			}
			
			
			params = new HashMap();
			params.put("UpdSQL", upSqlBuf.toString());
			txUpdcom(params);
			
			upSqlBuf = new StringBuffer();
			upSqlBuf.append("update DRP_INVOC_ACCT u")
			.append(" set CUR_END_PRICE=NVL((select COST_PRICE from DRP_COST_PRICE b where ")
			.append(" u.LEDGER_ID=b.LEDGER_ID ")
			.append(" and  u.MONTH=b.MONTH and u.YEAR=b.YEAR  and  u.PRD_ID=b.PRD_ID")
			.append(" and NVL(u.SPCL_TECH_ID,'NONE')=NVL(b.SPCL_TECH_ID,'NONE') ),0)")
			.append("  where ").append("u.YEAR='").append(YEAR).append("'")
			.append("  and u.MONTH='").append(MONTH).append("'")
			.append(" and u.LEDGER_ID='").append(LEDGER_ID).append("'")
			.append(" and u.PRD_ID='").append(PRD_ID).append("'");
			if(!StringUtil.isEmpty(SPCL_TECH_ID)){
				upSqlBuf.append(" and SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'");
			}else {
				upSqlBuf.append(" and (u.SPCL_TECH_ID is null or u.SPCL_TECH_ID = 'NONE' )");
			}
			
			params = new HashMap();
			params.put("UpdSQL", upSqlBuf.toString());
			txUpdcom(params);
			
			upSqlBuf = new StringBuffer();
			upSqlBuf.append("update DRP_INVOC_ACCT u set")
			.append(" CUR_DUMP_IN_PRICE=CUR_END_PRICE,")
			.append(" CUR_DUMP_IN_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_DUMP_IN_NUM,0),")
			.append("  CUR_TERM_SALE_PRICE=CUR_END_PRICE,")
			.append(" CUR_TERM_SALE_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_TERM_SALE_NUM,0),")
			.append("  CUR_OTHER_SALE_PRICE=CUR_END_PRICE,")
			.append(" CUR_OTHER_REAL_SALE_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_OTHER_SALE_NUM,0),")
			.append(" CUR_RETUN_PRICE=CUR_END_PRICE,")
			.append(" CUR_REAL_RETUN_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_RETUN_NUM,0),")
			.append(" CUR_REPAIR_PRICE=CUR_END_PRICE,")
			.append(" CUR_REPAIR_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_REPAIR_NUM,0),")
			.append(" CUR_DUMP_OUT_PRICE=CUR_END_PRICE,")
			.append(" CUR_DUMP_OUT_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_DUMP_OUT_NUM,0),")
			.append(" CUR_INV_ADD_PRICE=CUR_END_PRICE,")
			.append(" CUR_INV_ADD_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_INV_ADD_NUM,0),")
			.append(" CUR_INV_REDUCE_PRICE=CUR_END_PRICE,")
			.append(" CUR_INV_REDUCE_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_INV_REDUCE_NUM,0),")
			.append(" CUR_FEW_STOREOUT_PRICE=CUR_END_PRICE,")
			.append(" CUR_FEW_STOREOUT_AMOUNT=NVL(u.CUR_END_PRICE,0)*NVL(u.CUR_FEW_STOREOUT_NUM,0),")
			.append(" CUR_END_AMOUNT=CUR_END_PRICE*NVL(u.CUR_END_NUM,0)")
			.append(" where  u.YEAR='").append(YEAR).append("'")
			.append("  and u.MONTH='").append(MONTH).append("'")
			.append(" and u.LEDGER_ID='").append(LEDGER_ID).append("'")
			.append(" and u.PRD_ID='").append(PRD_ID).append("'");
			if(!StringUtil.isEmpty(SPCL_TECH_ID)){
				upSqlBuf.append(" and SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'");
			}else {
				upSqlBuf.append(" and (u.SPCL_TECH_ID is null or u.SPCL_TECH_ID = 'NONE' )");
			}
			params = new HashMap();
			params.put("UpdSQL", upSqlBuf.toString());
			txUpdcom(params);
			
			upSqlBuf = new StringBuffer();
			upSqlBuf.append("update DRP_INVOC_ACCT u set")
			.append(" CUR_COST_ADJUST_AMOUNT=NVL(CUR_END_AMOUNT,0)-(NVL(u.PER_LEFT_AMOUNT,0)+NVL(u.CUR_PUR_AMOUNT,0) ")
			.append(" +NVL(u.CUR_CUST_RETURN_AMOUNT,0)+NVL(u.CUR_OTHER_RETURN_AMOUNT,0) ")
			.append("  +NVL(u.CUR_REPAIR_BACK_AMOUNT,0) -NVL(u.CUR_TERM_SALE_AMOUNT,0)  -")
			.append(" NVL(u.CUR_OTHER_SALE_AMOUNT,0) +NVL(u.CUR_DUMP_IN_AMOUNT,0)-")
			.append(" NVL(u.CUR_RETUN_AMOUNT,0)-NVL(u.CUR_REPAIR_AMOUNT,0)-")
			.append(" NVL(u.CUR_DUMP_OUT_AMOUNT,0)+NVL(u.CUR_INV_ADD_AMOUNT,0)-NVL(u.CUR_INV_REDUCE_AMOUNT,0)")
			.append(" -NVL(u.CUR_QUO_AMOUNT,0)+  NVL(u.CUR_COST_ADJUST_AMOUNT,0))")
			.append(" where  u.YEAR='").append(YEAR).append("'")
			.append("  and u.MONTH='").append(MONTH).append("'")
			.append(" and u.LEDGER_ID='").append(LEDGER_ID).append("'")
			.append(" and u.PRD_ID='").append(PRD_ID).append("'");
			if(!StringUtil.isEmpty(SPCL_TECH_ID)){
				upSqlBuf.append(" and SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'");
			}else {
				upSqlBuf.append(" and (u.SPCL_TECH_ID is null or u.SPCL_TECH_ID = 'NONE' )");
			}
			
			params = new HashMap();
			params.put("UpdSQL", upSqlBuf.toString());
			txUpdcom(params);
			
			sqlBuf = new StringBuffer();
			sqlBuf.append("select p.STORE_ID, s.STORE_NO,s.STORE_NAME,p.CUR_COST_ADJUST_AMOUNT from DRP_INVOC_ACCT p left join DRP_STORE s on p.STORE_ID = s.STORE_ID where p.YEAR = '")
			.append(YEAR).append("'")
			.append("  and p.MONTH='").append(MONTH).append("'")
			.append(" and p.LEDGER_ID='").append(LEDGER_ID).append("'")
			.append(" and p.PRD_ID='").append(PRD_ID).append("'");
			if(!StringUtil.isEmpty(SPCL_TECH_ID)){
				sqlBuf.append(" and p.SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'");
			}else {
				sqlBuf.append(" and (p.SPCL_TECH_ID is null or p.SPCL_TECH_ID = 'NONE' )");
			}
			
			params = new HashMap();
			params.put("SelSQL", sqlBuf.toString());
			List costAmountList = selcomList(params);
			if(costAmountList==null || costAmountList.size()==0){
				throw new ServiceException("进销存数据不存在!");
			}
			for(int num = 0;num<costAmountList.size();num++){
				Map costAmountMap = (Map)costAmountList.get(num);
				String STORE_ID = (String)costAmountMap.get("STORE_ID");
				String STORE_NO = (String)costAmountMap.get("STORE_NO");
				String STORE_NAME = (String)costAmountMap.get("STORE_NAME");
				BigDecimal CUR_COST_ADJUST_AMOUNT = (BigDecimal)costAmountMap.get("CUR_COST_ADJUST_AMOUNT");
				float CUR_COST_ADJUST_AMOUNT_VALUE = 0;
				if(CUR_COST_ADJUST_AMOUNT!=null){
					CUR_COST_ADJUST_AMOUNT_VALUE = CUR_COST_ADJUST_AMOUNT.floatValue();
				}
				instInvocAcctJournalAcct(COST_ADJUST_DTL_ID,YEAR,MONTH,LEDGER_ID,PRD_ID,STORE_ID,STORE_NO,STORE_NAME,SPCL_TECH_ID,CUR_COST_ADJUST_AMOUNT_VALUE);
				
			}
		}

	}
	
	private void instInvocAcctJournalAcct(String COST_ADJUST_DTL_ID,String YEAR,String MONTH,String LEDGER_ID,String PRD_ID,String STORE_ID,String STORE_NO,String STORE_NAME,String SPCL_TECH_ID,float CUR_COST_ADJUST_AMOUNT)throws Exception{
		StringBuffer insertSql = new StringBuffer();
		insertSql.append("insert into DRP_JOURNAL_ACCT(JOURNAL_ACCT_ID,IN_OUT_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,STORE_ID,STORE_NO,STORE_NAME,SPCL_TECH_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,STD_UNIT,DECT_PRICE,IN_OUT_AMOUNT,DEAL_TIME,DEAL_DATE,YEAR_MONTH,DIRECTION,NO_TAX_DECT_PRICE)")
		.append("select rawtohex(sys_guid()) as UUID,'成本调整',t.COST_ADJUST_ID,t.COST_ADJUST_NO,d.COST_ADJUST_DTL_ID,t.LEDGER_ID,'"+STORE_ID+"','"+STORE_NO+"','"+STORE_NAME+"',d.SPCL_TECH_ID,d.PRD_ID,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,d.STD_UNIT,")
		.append(CUR_COST_ADJUST_AMOUNT).append(",").append(CUR_COST_ADJUST_AMOUNT)
		.append(" ,t.year||'-'||t.month||'-01 00:00:01',")
		.append("to_date(t.year||'-'||t.month||'-01', 'yyyy-MM-DD'), ")
		.append("t.year||'/'||t.month, ").append("0,")
		.append(CUR_COST_ADJUST_AMOUNT)
		.append(" from DRP_COST_ADJUST t,DRP_COST_ADJUST_DTL d")
		.append(" where t.COST_ADJUST_ID=d.COST_ADJUST_ID")
		.append(" and d.COST_ADJUST_DTL_ID = '").append(COST_ADJUST_DTL_ID).append("'");
		Map params = new HashMap();
		params.put("InsSQL", insertSql.toString());
		boolean flg = txInscom(params);
	}
	
	/**
	 * 查询个人信息
	 */
	public Map selcom(Map params) {
		return (Map) load("sqlcom.query", params);
	}

	/**
	 * 获取列表信息
	 * @param params
	 * @return
	 */
	public List selcomList(Map params) {
		return this.findList("sqlcom.query", params);
	}

	public boolean txUpdcom(Map params) {
		update("sqlcom.update", params);
		return true;
	}

	public boolean txInscom(Map params) {
		insert("sqlcom.insert", params);
		return true;
	}
	
	public boolean txDelcom(Map params) {
		return update("sqlcom.delete", params);
	}	
}
