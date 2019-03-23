package com.hoperun.commons.util.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.InterReturnMsg;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.BeanHelper;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.InterUtil;
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.JesonOutUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.impl.model.JsonLonanBean;
import com.hoperun.commons.util.security.MD5Encrypt;
import com.hoperun.drp.report.querypaymentrep.model.PayMentDtl;
import com.hoperun.sys.model.UserBean;
/**
 * The Class FlowServiceImpl.
 * 
 * @module 共通模块
 * @func 审批流程
 * @version 1.1
 * @author zhuxw
 */
public class LogicUtilServiceImpl extends BaseService {

	/** The logger. */
	private Logger logger = Logger.getLogger(LogicUtilServiceImpl.class);

	private String INSERT_OP = "insert";

	private String MSG_START = "对不起，";
	private String MSG_END = "可用库存不足！";

	/**
	 * 记库存实时账
	 * 
	 * @param bussID
	 * @param keyValue
	 */
	public boolean txDoStoreAcct(String bussID, String keyValue) {
		boolean flag = false;
		Map confMap = loadAcctConf(bussID);
		String tempSql = queryStoreAcct(confMap, keyValue, null,true);

		// 更新语句
		StringBuffer updateSql = new StringBuffer();
		updateSql
				.append(
						"update DRP_STORE_ACCT a set a.STORE_NUM=NVL(a.STORE_NUM,0)+(select NVL(STORE_NUM,0) from (")
				.append(tempSql)
				.append(
						") temp1 where a.PRD_ID = temp1.PRD_ID and a.STORE_ID = temp1.STORE_ID and ")
				.append("NVL(a.SPCL_TECH_ID,'null') = NVL(temp1.SPCL_TECH_ID,'null')")
				.append("),a.FREEZE_NUM=a.FREEZE_NUM+(select FREEZE_NUM from (")
				.append(tempSql)
				.append(
						") temp2 where a.PRD_ID = temp2.PRD_ID and a.STORE_ID = temp2.STORE_ID and ")
				.append("NVL(a.SPCL_TECH_ID,'null') = NVL(temp2.SPCL_TECH_ID,'null')")
				.append(") ")
				.append(
						" where (a.PRD_ID,a.STORE_ID,a.LEDGER_ID) in (select PRD_ID,STORE_ID,LEDGER_ID from (")
				.append(tempSql)
				.append("))")
				.append(" and EXISTS (select 1 from (")
				.append(tempSql)
				.append(
						") tmp where tmp.STORE_ID=a.STORE_ID and tmp.PRD_ID=a.PRD_ID and tmp.LEDGER_ID=a.LEDGER_ID and NVL(tmp.SPCL_TECH_ID,'null') = NVL(a.SPCL_TECH_ID,'null'))");

		// 插入语句
		StringBuffer insertSql = new StringBuffer();
		String insertTempSql = queryStoreAcct(confMap, keyValue, INSERT_OP,true);

		insertSql
				.append(
						" insert into DRP_STORE_ACCT (STORE_ACCT_ID,STORE_ID,SPCL_TECH_ID,PRD_ID,STORE_NUM,FREEZE_NUM,LEDGER_ID,STATE) ")
				.append(" select ")
				.append("sys_guid() ")
				.append(
						",STORE_ID,SPCL_TECH_ID,PRD_ID,STORE_NUM,FREEZE_NUM,LEDGER_ID,'")
				.append(BusinessConsts.INTEGER_0).append("' from (").append(
						insertTempSql).append(")");

		// 更新
		Map params = new HashMap();
		params.put("UpdSQL", updateSql.toString());
		InterUtil.txUpdcom(params);
		params.put("InsSQL", insertSql.toString());
		InterUtil.txInscom(params);
		return flag;
	}
	
	
	/**
	 * 记形态转换库存实时账（不需要转换冻结数量）
	 * 
	 * @param bussID
	 * @param keyValue
	 */
	public boolean txStateChangeStoreAcct(String bussID, String keyValue) {
		boolean flag = false;
		Map confMap = loadAcctConf(bussID);
		String tempSql = queryStoreAcct(confMap, keyValue, null,true);

		// 更新语句
		StringBuffer updateSql = new StringBuffer();
		updateSql
				.append(
						"update DRP_STORE_ACCT a set a.STORE_NUM=NVL(a.STORE_NUM,0)+(select NVL(STORE_NUM,0) from (")
				.append(tempSql)
				.append(
						") temp1 where a.PRD_ID = temp1.PRD_ID and a.STORE_ID = temp1.STORE_ID and ")
				.append("NVL(a.SPCL_TECH_ID,'null') = NVL(temp1.SPCL_TECH_ID,'null')")
				.append(")")
				.append(" where (a.PRD_ID,a.STORE_ID,a.LEDGER_ID) in (select PRD_ID,STORE_ID,LEDGER_ID from (")
				.append(tempSql)
				.append("))")
				.append(" and EXISTS (select 1 from (")
				.append(tempSql)
				.append(
						") tmp where tmp.STORE_ID=a.STORE_ID and tmp.PRD_ID=a.PRD_ID and tmp.LEDGER_ID=a.LEDGER_ID and NVL(tmp.SPCL_TECH_ID,'null') = NVL(a.SPCL_TECH_ID,'null'))");

		// 插入语句
		StringBuffer insertSql = new StringBuffer();
		String insertTempSql = queryStoreAcct(confMap, keyValue, INSERT_OP,true);

		insertSql
				.append(
						" insert into DRP_STORE_ACCT (STORE_ACCT_ID,STORE_ID,SPCL_TECH_ID,PRD_ID,STORE_NUM,FREEZE_NUM,LEDGER_ID,STATE) ")
				.append(" select ")
				.append("sys_guid() ")
				.append(
						",STORE_ID,SPCL_TECH_ID,PRD_ID,STORE_NUM,FREEZE_NUM,LEDGER_ID,'")
				.append(BusinessConsts.INTEGER_0).append("' from (").append(
						insertTempSql).append(")");

		// 更新
		Map params = new HashMap();
		params.put("UpdSQL", updateSql.toString());
		InterUtil.txUpdcom(params);
		params.put("InsSQL", insertSql.toString());
		InterUtil.txInscom(params);
		return flag;
	}
	

	/**
	 * 记库存冻结
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public boolean txDoStoreFreeze(String bussID, String keyValue) {
		Map confMap = loadAcctConf(bussID);
		String tempSql = null;
		if (BusinessConsts.ADVC_ORDER_SEQ_CONF_ID.equals(bussID)) {
			tempSql = queryStoreAcctForSend(confMap, keyValue, null);
		} else {
			tempSql = queryStoreAcct(confMap, keyValue, null,true);
		}
		// 更新语句
		StringBuffer updateSql = new StringBuffer();
		updateSql
				.append(
						"update DRP_STORE_ACCT a set a.FREEZE_NUM= NVL(a.FREEZE_NUM,0)+(select NVL(FREEZE_NUM,0) from (")
				.append(tempSql)
				.append(") bus where bus.PRD_ID = a.PRD_ID and NVL(bus.SPCL_TECH_ID,'null') = NVL(a.SPCL_TECH_ID,'null') ")
				.append(
						") where (a.PRD_ID,a.STORE_ID,a.LEDGER_ID,NVL(a.SPCL_TECH_ID, 'null')) in (select PRD_ID,STORE_ID,LEDGER_ID,NVL(SPCL_TECH_ID, 'null') SPCL_TECH_ID from (")
				.append(tempSql)
				.append(")")
				.append(") and EXISTS (select 1 from (")
				.append(tempSql)
				.append(
						") tmp where tmp.STORE_ID=a.STORE_ID and tmp.PRD_ID=a.PRD_ID  and NVL(tmp.SPCL_TECH_ID, 'null') = NVL(a.SPCL_TECH_ID, 'null') and tmp.LEDGER_ID=a.LEDGER_ID)");
		// 更新
		Map params = new HashMap();
		params.put("UpdSQL", updateSql.toString());
		return InterUtil.txUpdcom(params);
	}

	/**
	 * 记库存流水帐
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public boolean txDoJournalAcct(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadAcctConf(bussID);

		String tempSql = queryJournalAcct(bussID,confMap, keyValue);

		StringBuffer insertSql = new StringBuffer();
		insertSql
				.append(
						"insert into DRP_JOURNAL_ACCT(JOURNAL_ACCT_ID,IN_OUT_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,STORE_ID,STORE_NO,STORE_NAME,SPCL_TECH_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,IN_OUT_NUM,IN_OUT_AMOUNT,FREEZE_NUM,DEAL_TIME,DEAL_DATE,YEAR_MONTH,DIRECTION,TAX_RATE,NO_TAX_DECT_PRICE,NO_TAX_DECT_AMOUNT,TAX_AMOUNT)")
				.append(tempSql);

		Map params = new HashMap();
		params.put("InsSQL", insertSql.toString());
		return InterUtil.txInscom(params);
	}
	/**
	 * 记库存流水账（带特殊工艺管控）
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public boolean txDoJournalAcct (String bussID,String keyValue,String CHANN_ID)throws Exception{
		Map confMap = loadAcctConf(bussID);
		String IS_SPEC_TECH_ENABLE=getChannSpecEnableByChannId(CHANN_ID);
		String tempSql = queryJournalAcct(bussID,confMap, keyValue,IS_SPEC_TECH_ENABLE);

		StringBuffer insertSql = new StringBuffer();
		insertSql
				.append(
						"insert into DRP_JOURNAL_ACCT(JOURNAL_ACCT_ID,IN_OUT_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,STORE_ID,STORE_NO,STORE_NAME,SPCL_TECH_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,IN_OUT_NUM,IN_OUT_AMOUNT,FREEZE_NUM,DEAL_TIME,DEAL_DATE,YEAR_MONTH,DIRECTION,TAX_RATE,NO_TAX_DECT_PRICE,NO_TAX_DECT_AMOUNT,TAX_AMOUNT,IS_SPEC_TECH_ENABLE)")
				.append(tempSql);

		Map params = new HashMap();
		params.put("InsSQL", insertSql.toString());
		return InterUtil.txInscom(params);
	}
	/**
	 * 库存流水帐查询数据
	 * 
	 * @return
	 */
	private String queryJournalAcct(String bussId,Map confMap, String keyValue,String IS_SPEC_TECH_ENABLE) throws Exception{
		String aname = (String) confMap.get("DTL_TAB_ANNAME");
		StringBuffer sqlBuffer = new StringBuffer();
		String billNoCol = (String) confMap.get("BILL_NO_COL");
		String channIdCol = (String) confMap.get("CHANN_ID_COL");
		String strPrice = (String) confMap.get("PRICE_COL");
		String strRateCol = (String) confMap.get("DECT_RATE_COL");
		String strDectPrice = (String) confMap.get("DECT_PRICE_COL");
		String strAcctAmountCol = (String) confMap.get("ACCT_AMOUNT_COL");
		String strFreezeNumCol = (String) confMap.get("FREEZE_NUM_COL");
		String dtlKeyCol = confMap.get("DTL_KEY_COL").toString();
		String PRD_SPEC_COL = (String)confMap.get("PRD_SPEC_COL");
		String PRD_COLOR_COL = (String)confMap.get("PRD_COLOR_COL");
		String BRAND_COL =  (String)confMap.get("BRAND_COL");
		String STD_UNIT_COL =  (String)confMap.get("STD_UNIT_COL");
		String STORE_ID = (String)confMap.get("STORE_ID_COL");
		String STORE_NO = (String)confMap.get("STORE_NO_COL");
		String STORE_NAME = (String)confMap.get("STORE_NAME_COL");
		String acctNumCol = (String)confMap.get("ACCT_NUM_COL");
		Map dumpChannMap = null;
		if(BusinessConsts.DUMP_OUT_CONF_ID.equals(bussId)){
			dtlKeyCol = "'ZC_'||"+dtlKeyCol;
			dumpChannMap = getChannMapByLendger(keyValue);
		}else if(BusinessConsts.DUMP_IN_CONF_ID.equals(bussId)){
			dtlKeyCol = "'ZR_'||"+dtlKeyCol;
			dumpChannMap = getChannMapByLendger(keyValue);
		}else if(BusinessConsts.CONV_PDT_CONF_1ID.equals(bussId)){
			dtlKeyCol = "'ZHC_'||"+dtlKeyCol;
		}else if(BusinessConsts.CONV_PDT_CONF_2ID.equals(bussId)){
			dtlKeyCol = "'ZHR_'||"+dtlKeyCol;
		}
		String inOutType = "'"+confMap.get("IN_OUT_TYPE").toString()+"' IN_OUT_TYPE";
		if(BusinessConsts.INVEN_CONF_ID.equals(bussId)){
			inOutType = " CASE  WHEN  d.DIFF_NUM >= 0 THEN '盘盈'  ELSE  '盘亏'  END IN_OUT_TYPE";
		}
		sqlBuffer.append("select ").append(dtlKeyCol).append(
				" JOURNAL_ACCT_ID,").append(
				 inOutType).append(
				",")// 出入库类型
				.append("'" + keyValue + "'").append(" BILL_ID,");// 单据ID
		if (billNoCol != null) {
			sqlBuffer.append(billNoCol).append(" BILL_NO, ");// 单据编号
		} else {
			sqlBuffer.append("'',");
		}
		sqlBuffer.append(confMap.get("DTL_KEY_COL").toString()).append(
				" BILL_DTL_ID, ").append(
				confMap.get("LEDGER_ID_COL").toString()).append(" LEDGER_ID, ");
		if (channIdCol != null) {
			sqlBuffer.append(confMap.get("CHANN_ID_COL").toString()).append(
					" CHANN_ID, ").append(
					confMap.get("CHANN_NO_COL").toString()).append(
					" CHANN_NO, ").append(
					confMap.get("CHANN_NAME_COL").toString()).append(
					" CHANN_NAME, ");
		} else if(dumpChannMap!=null){
			sqlBuffer.append("'"+dumpChannMap.get("CHANN_ID")+"'").append(
			" CHANN_ID, ").append("'"+dumpChannMap.get("CHANN_NO")+"'").append(
			" CHANN_NO, ").append("'"+dumpChannMap.get("CHANN_NAME")+"'").append(
			" CHANN_NAME, ");
		}else {
			sqlBuffer.append("'',").append("'',").append("'',");
		}
		if(STORE_ID!=null){
			sqlBuffer.append(STORE_ID).append(
			" STORE_ID, ").append(STORE_NO)
			.append(" STORE_NO, ")
			.append(STORE_NAME).append(" STORE_NAME, ");			
		}else {
			sqlBuffer.append("'',").append("'',").append("'',");
		}
		sqlBuffer.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(
						" SPCL_TECH_ID, ").append(
						confMap.get("PRD_ID_COL").toString()).append(
						" PRD_ID, ").append(
						confMap.get("PRD_NO_COL").toString()).append(
						" PRD_NO, ")
						.append(confMap.get("PRD_NAME_COL").toString())
						.append(" PRD_NAME, ");
		if(PRD_SPEC_COL!=null){
			sqlBuffer.append(PRD_SPEC_COL).append(" PRD_SPEC, ");
		}else{
			sqlBuffer.append("'',");
		}
		if(PRD_COLOR_COL!=null){
			sqlBuffer.append(PRD_COLOR_COL).append(" PRD_COLOR, ");
		}else{
			sqlBuffer.append("'',");
		}
	    if(BRAND_COL!=null){
	    	sqlBuffer.append(BRAND_COL).append(" BRAND, ");
	    }else{
	    	sqlBuffer.append("'',");
	    }
	    if(STD_UNIT_COL!=null){
	    	sqlBuffer.append(STD_UNIT_COL).append(" STD_UNIT, ");
	    }else{
	    	sqlBuffer.append("'',");
	    }
		if (strPrice != null) {
			sqlBuffer.append(confMap.get("PRICE_COL").toString()).append(
					" PRICE, ");
		} else {
			sqlBuffer.append("'',");
		}
		if (strRateCol != null) {
			sqlBuffer.append(confMap.get("DECT_RATE_COL").toString()).append(
					" DECT_RATE, ");
		} else {
			sqlBuffer.append("'',");
		}
		if (strDectPrice != null) {
			sqlBuffer.append(confMap.get("DECT_PRICE_COL").toString()).append(
					" DECT_PRICE, ");
		} else {
			sqlBuffer.append("'',");
		}

		if(acctNumCol!=null){
			if(BusinessConsts.INVEN_CONF_ID.equals(bussId)){
				sqlBuffer.append("abs("+acctNumCol).append(
				") IN_OUT_NUM, ");	
			}else{
				sqlBuffer.append(acctNumCol).append(
				" IN_OUT_NUM, ");				
			}

		}else{
			sqlBuffer.append("'',");
		}

		if (strAcctAmountCol != null) {
			sqlBuffer.append(confMap.get("ACCT_AMOUNT_COL").toString()).append(
					" IN_OUT_AMOUNT, ");
		} else {
			sqlBuffer.append("'',");
		}
		if (strFreezeNumCol != null) {
			sqlBuffer.append(confMap.get("FREEZE_NUM_COL").toString()).append(
					" FREEZE_NUM, ");
		} else {
			sqlBuffer.append("'',");
		}
		if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
			sqlBuffer.append(" t.year||'-'||t.month||'-01 00:00:01',");
		}else{
			sqlBuffer.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'), ");
		}
		
		if(BusinessConsts.STOREIN_ORDER_CONF5_ID.equals(bussId)){
			sqlBuffer.append(" t.STATEMENUT_DATE, ");
		}else if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
			sqlBuffer.append("to_date(t.year||'-'||t.month||'-01', 'yyyy-MM-DD'), ");
		}else{
			sqlBuffer.append(" to_date(to_char(sysdate, 'yyyy-MM-DD'), 'yyyy-MM-DD'), ");
		}
		if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
			sqlBuffer.append("t.year||'/'||t.month, ");
		}else{
			sqlBuffer.append("to_char(sysdate,'YYYY/MM'), ");
		}
		
		if(BusinessConsts.INVEN_CONF_ID.equals(bussId)){
			inOutType = " CASE  WHEN  d.DIFF_NUM >= 0 THEN 0  ELSE  1  END DIRECTION,";
			sqlBuffer.append(inOutType);
		}else{
			sqlBuffer.append(confMap.get("DIRECTION").toString())
			.append(" DIRECTION,");
		}
		
	    if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
	    	sqlBuffer.append("0 TAX_RATE,")
            .append(strDectPrice+" NO_TAX_DECT_PRICE,")
            .append("0 NO_TAX_DECT_AMOUNT,")
            .append("0 TAX_AMOUNT");
	    	
	    }else{
	    	sqlBuffer.append(" NVL(b.tax_rate,0.17) TAX_RATE,")
            .append(" CAST(NVL("+strDectPrice+",0)/(1+NVL(b.tax_rate,0.17)) as decimal(38, 2))  NO_TAX_DECT_PRICE,")
            .append(" CAST((NVL("+strDectPrice+",0)/(1+NVL(b.tax_rate,0.17)))*NVL("+acctNumCol+",0) as decimal(38, 2))  NO_TAX_DECT_AMOUNT,")
            .append(" CAST((NVL("+strDectPrice+",0)-  NVL("+strDectPrice+",0)/(1+NVL(b.tax_rate,0.17)))*NVL("+acctNumCol+",0)as decimal(38, 2)) TAX_AMOUNT");
	    }
	    sqlBuffer.append(",'").append(IS_SPEC_TECH_ENABLE).append("' ");
	    sqlBuffer.append(" from ")
				.append(confMap.get("MAIN_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(" left join ")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" on ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap))
				.append(" left join base_chann b on "+channIdCol+" = b.chann_id")
				.append(" where ").append(aname)
				.append(".DEL_FLAG = 0 ").append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'");
      if(!BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
    	  sqlBuffer.append(" and NVL("+acctNumCol+",0)!=0");
      }			
		return sqlBuffer.toString();
	}
	/**
	 * 删库存流水帐
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public boolean txDelJournalAcct(String keyValue,String BillType)
			throws Exception {
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete from DRP_JOURNAL_ACCT ac where ")
		.append("ac.bill_id = '").append(keyValue).append("'")
		.append(" and ac.bill_dtl_id in (select d.storeout_dtl_id from drp_storeout_dtl d where d.del_flag =0 and d.storeout_id = '")
		.append(keyValue).append("')")
		.append("and ac.in_out_type = '").append(BillType)
		.append("'");
		Map params = new HashMap();
		params.put("DelSQL", delSql.toString());
		return InterUtil.txDelcom(params);
	}
	
	/**
	 * 返入库时删库存流水帐
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public boolean txDelStoreInJournalAcct(String keyValue,String BillType)
			throws Exception {
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete from DRP_JOURNAL_ACCT ac where ")
		.append("ac.bill_id = '").append(keyValue).append("'")
		.append(" and ac.bill_dtl_id in (select d.storein_dtl_id from drp_storein_dtl d where d.del_flag =0 and storein_id = '")
		.append(keyValue).append("')")
		.append("and ac.in_out_type = '").append(BillType)
		.append("'");
		Map params = new HashMap();
		params.put("DelSQL", delSql.toString());
		return InterUtil.txDelcom(params);
	}
	
	/**检查是否存在流水
	 * @param keyValue
	 * @param BillType
	 * @return
	 */
	public boolean checkJournalAcct(String keyValue,String BillType){
		StringBuffer selSql = new StringBuffer();
		selSql.append("select count(*) JOURNUM from DRP_JOURNAL_ACCT ac")
		.append(" where ac.in_out_type = '").append(BillType).append("'")
		.append(" and ac.bill_id = '").append(keyValue).append("'");
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		Map checkMap = InterUtil.selcom(params);
		BigDecimal JOURNUM = (BigDecimal)checkMap.get("JOURNUM");
		int i_JOURNUM = JOURNUM.intValue();
		if(i_JOURNUM>0){
			return true;
		}
		return false;
	}
	
	
	
	public boolean txDeleteInitInventoryJournalAcct(String STORE_NO,String CHANN_ID){
		StringBuffer deleteSql = new StringBuffer();
		deleteSql
				.append("DELETE FROM DRP_JOURNAL_ACCT ac where  ac.STORE_NO = '"+STORE_NO+"'");
		Map params = new HashMap();
		params.put("DelSQL", deleteSql.toString());
		return InterUtil.txDelcom(params);
	}
	
	
	/**
	 * 初记库存流水帐
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 */
	public boolean txInitInventoryJournalAcct(String PRD_INV_ID,String STORE_NO,String CHANN_ID)
			throws Exception {
		txDeleteInitInventoryJournalAcct(STORE_NO,CHANN_ID);
		StringBuffer insertSql = new StringBuffer();
		insertSql
				.append(
						"insert into DRP_JOURNAL_ACCT(JOURNAL_ACCT_ID,IN_OUT_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,STORE_ID,STORE_NO,STORE_NAME,SPCL_TECH_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,IN_OUT_NUM,DEAL_TIME,DEAL_DATE,YEAR_MONTH,DIRECTION,DECT_PRICE,TAX_RATE,NO_TAX_DECT_PRICE,NO_TAX_DECT_AMOUNT,TAX_AMOUNT)")
		.append(" SELECT d.PRD_INV_DTL_ID, ")	
		.append(" '初始化盘点',")
		.append(" t.PRD_INV_ID,")
		.append(" t.PRD_INV_NO,")
		.append(" d.PRD_INV_DTL_ID,")
		.append(" t.LEDGER_ID,")
		.append("'").append(CHANN_ID).append("',")
		.append(" (select CHANN_NO FROM BASE_CHANN b where b.CHANN_ID = '").append(CHANN_ID).append("'),")
		.append(" (select CHANN_NAME FROM BASE_CHANN b where b.CHANN_ID = '").append(CHANN_ID).append("'),")
		.append(" t.STORE_ID,")
		.append(" t.STORE_NO,")
		.append(" t.STORE_NAME,")
		.append(" d.SPCL_TECH_ID,")
		.append(" d.PRD_ID,")
		.append(" d.PRD_NO,")
		.append(" d.PRD_NAME,")
		.append(" d.PRD_SPEC,")
		.append(" d.PRD_COLOR,")
		.append(" d.BRAND,")
		.append(" d.STD_UNIT,")
		.append(" d.INV_NUM,")
		.append(" to_char(t.cre_time, 'yyyy-MM-DD HH24:MI:SS'), ")
		.append(" to_date(to_char(t.cre_time, 'yyyy-MM-DD'), 'yyyy-MM-DD'), ")
		.append(" to_char(t.cre_time,'YYYY/MM'), ")
		.append(" '0', ")
		.append(" d.DECT_PRICE,")
		.append(" NVL(b.tax_rate,0.17) TAX_RATE,")
        .append(" CAST(NVL(d.DECT_PRICE,0)/(1+NVL(b.tax_rate,0.17)) as decimal(38, 2))  NO_TAX_DECT_PRICE,")
        .append(" CAST((NVL(d.DECT_PRICE,0)/(1+NVL(b.tax_rate,0.17)))*NVL(d.INV_NUM,0) as decimal(38, 2))  NO_TAX_DECT_AMOUNT,")
        .append(" CAST((NVL(d.DECT_PRICE,0)-  NVL(d.DECT_PRICE,0)/(1+NVL(b.tax_rate,0.17)))*NVL(d.INV_NUM,0)as decimal(38, 2)) TAX_AMOUNT")
		.append("  from DRP_PRD_INV t left join  DRP_PRD_INV_DTL d  on  t.PRD_INV_ID = d.PRD_INV_ID")
		.append(" left join base_chann  b on b.chann_id = '"+CHANN_ID+"' where ")
		.append(" t.STORE_NO = '").append(STORE_NO).append("'")
		.append("  and t.PRD_INV_ID = '"+PRD_INV_ID+"'");
		Map params = new HashMap();
		params.put("InsSQL", insertSql.toString());
		return InterUtil.txInscom(params);
	}

	/**
	 * 判断可用库存是否够用的判断
	 * 
	 * @param bussID
	 * @param keyValue
	 * @param chkType
	 * @return
	 */
	public String txDoChkCanUseStoreNum(String bussID, String keyValue,
			String chkType) {

		List resultList = null;
		String num_col = "";// 库存数量/冻结数量 列名
		Gson mGson = new Gson();
		MsgInfo msg = null;

		if (BusinessConsts.STORE_DESC.equals(chkType)) {
			num_col = "STORE_NUM";
		} else if (BusinessConsts.FREEZE_DESC.equals(chkType)) {
			num_col = "FREEZE_NUM";
		}

		Map confMap = loadAcctConf(bussID);
		String tempSql = null;
		if (BusinessConsts.ADVC_ORDER_SEQ_CONF_ID.equals(bussID)) {
			tempSql = queryStoreAcctForSend(confMap, keyValue, null);
		} else {
			tempSql = queryStoreAcct(confMap, keyValue, null,false);
		}

		StringBuffer selSql = new StringBuffer();
		selSql
				.append("select c.PRD_NAME,b.")
				.append(num_col)
				.append(" NUM,")
				.append("(nvl(a.STORE_NUM,0)-nvl(a.FREEZE_NUM,0)) USE_STORE ")
				.append(" from (")
				.append(tempSql)
				.append(") b ")
				.append(
						" left join DRP_STORE_ACCT a on b.PRD_ID=a.PRD_ID and b.STORE_ID=a.STORE_ID and b.LEDGER_ID=a.LEDGER_ID and NVL(a.SPCL_TECH_ID,'NULL') = NVL(b.SPCL_TECH_ID,'NULL') ")
				.append(
						" left join BASE_PRODUCT c on b.PRD_ID=c.PRD_ID ");

		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		resultList = InterUtil.selcomList(params);
		// 如果查询到数据，则要继续校验
		if (resultList != null && resultList.size() > 0) {
			if(BusinessConsts.STOREOUT_ORDER_CONF6_ID.equals(bussID)){  //零星出库允许0出库
				msg = new MsgInfo();
				msg.setFLAG(true);
			}else{
				msg = getUseStoreMsg(resultList);
			}
			
			if (msg.isFLAG()) {
				selSql = new StringBuffer();
				selSql
						.append(
								"select b.PRD_NAME, (nvl(a.STORE_NUM, 0) - nvl(a.FREEZE_NUM, 0)) USE_STORE  from  ")
					    .append(" ( ")
						.append(tempSql)
						.append(" ) tmp")
						.append("  left join  DRP_STORE_ACCT a  on tmp.PRD_ID = a.PRD_ID ")
						.append("  and tmp.STORE_ID = a.STORE_ID  ")
						.append("   and tmp.LEDGER_ID = a.LEDGER_ID   and NVL(a.SPCL_TECH_ID, 'NONE') = NVL(tmp.SPCL_TECH_ID, 'NONE') ")
						.append(
								" left join BASE_PRODUCT b on a.PRD_ID=b.PRD_ID ")
						.append(" where  NVL(tmp.STORE_NUM,0) > (nvl(a.STORE_NUM, 0) - nvl(a.FREEZE_NUM, 0))");
				
				params.put("SelSQL", selSql.toString());
				resultList = InterUtil.selcomList(params);

				msg = new MsgInfo();
				// 如果查询不到，则返回true，
				if (resultList == null || resultList.size() == 0) {
					msg.setFLAG(true);
				} else {
					// 查询出结果为false
					msg.setFLAG(false);
					StringBuffer strBuf = new StringBuffer();
					for(int i=0;i<resultList.size();i++){
						Map map = (Map) resultList.get(i);
						strBuf.append(map.get("PRD_NAME")).append(",");
					}
					msg.setMESS(MSG_START +strBuf.toString() + MSG_END);
				}
			}
		} else {
			// 查不到数据，说明明细里货品不用冻结
			msg = new MsgInfo();
			msg.setFLAG(false);
			msg.setMESS("0");
		}
		return mGson.toJson(msg);
	}
	
	private MsgInfo getUseStoreMsg(List storeAcctList) {
		MsgInfo msg = new MsgInfo();
		msg.setFLAG(true);
		String strPdtList = "";
		boolean isFlg = true;
		for (int i = 0; i < storeAcctList.size(); i++) {
			Map storeAcctMap = (Map) storeAcctList.get(i);
			String strUseStore = String.valueOf(storeAcctMap.get("USE_STORE"));
			if ("0".equals(strUseStore)) {
				isFlg = false;
				strPdtList += storeAcctMap.get("PRD_NAME")+",";
			}
		}
		if(!isFlg){
			msg.setFLAG(false);
			msg.setMESS(MSG_START + strPdtList+ MSG_END);
		}
		return msg;
	}
	
	
	/**检查出库处理之后库存数量不能为负数
	 * @param STOREOUT_ID
	 * @return
	 */
	public String txDoChkStoreNumByStoreOut(String STOREOUT_ID){
		String message = "true";
		StringBuffer selSql = new StringBuffer();
		selSql.append("select temp.PRD_NO, temp.PRD_NAME,temp.STORE_NUM")
		.append(" from (select p.PRD_NO, p.PRD_NAME, ac.STORE_NUM")
		.append("  from DRP_STORE_ACCT ac")
		.append(" left join BASE_PRODUCT p on ac.PRD_ID = p.PRD_ID")
		.append(" where (ac.STORE_ID, ac.PRD_ID, NVL(ac.SPCL_TECH_ID, 'NONL')) in ")
		.append(" (select t.STOREOUT_STORE_ID,d.PRD_ID, NVL(d.SPCL_TECH_ID, 'NONL')")
		.append(" from DRP_STOREOUT t, DRP_STOREOUT_DTL d")
		.append(" where t.STOREOUT_ID = d.STOREOUT_ID")
		.append(" and d.DEL_FLAG = 0")
		.append("  and t.STOREOUT_ID = '"+STOREOUT_ID+"')) temp")
		.append(" where NVL(temp.store_num, 0) < 0");
		
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		List resultList = InterUtil.selcomList(params);
		StringBuffer msgBuf = new StringBuffer();
		if(resultList!=null && resultList.size()>0){
			for(int i =0;i<resultList.size();i++){
				Map returnMap = (Map)resultList.get(i);
				String PRD_NAME = (String)returnMap.get("PRD_NAME");
				msgBuf.append(PRD_NAME).append(",");
			}
			msgBuf.append("库存数量不足，不能出库!");
		}
		if(msgBuf.length()>0){
			message = msgBuf.toString();
		}
		return message;
	}
	


	/**
	 * 查询库存配置信息
	 */
	private Map loadAcctConf(String bussID) {
		String sql = "select * from DRP_STORE_ACCT_CONF where STORE_ACCT_CONF_ID='"
				+ bussID + "'";
		Map params = new HashMap();
		params.put("SelSQL", sql);
		return InterUtil.selcom(params);
	}

	/**
	 * 查询信用配置信息
	 */
	private Map loadCreditConf(String bussID) {
		String sql = "select * from BASE_JOURNAL_CREDIT_ACCT_CONF where JOURNAL_CREDIT_ACCT_CONF_ID='"
				+ bussID + "'";
		Map params = new HashMap();
		params.put("SelSQL", sql);
		return InterUtil.selcom(params);
	}

	/**
	 * 库存实时帐查询临时数据
	 * 
	 * @param keyValue是业务表的主键值
	 * @return
	 */
	private String queryStoreAcct(Map confMap, String keyValue, String op,boolean isDirection) {
		String direction = String.valueOf(confMap.get("DIRECTION"));
		if(isDirection){
			if ("0".equals(direction)) {
				direction = "1";
			} else {
				direction = "-1";
			}
		}else{
			direction = "1";
		}
		
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer
				.append(" select ")
				.append(" sum(")
				.append(confMap.get("ACCT_NUM_COL"))
				.append(") * " + direction + " STORE_NUM, ")
				// 库存数量
				.append(" sum(")
				.append("NVL(" + confMap.get("FREEZE_NUM_COL") + ",0)")
				.append(") * " + direction + " FREEZE_NUM, ")
				// 冻结数量
				.append(confMap.get("PRD_ID_COL").toString() +" PRD_ID ")
				.append(",")
				// 货品
				.append(confMap.get("STORE_ID_COL").toString())
				.append(" as STORE_ID ")
				.append(",")
				// 库房
				.append(confMap.get("LEDGER_ID_COL").toString())
				.append(",")
				// 帐套
				.append(confMap.get("SPCL_TECH_ID_COL").toString() +" SPCL_TECH_ID ")
				// 帐套
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(" and ").append(
						confMap.get("STORE_ID_COL").toString()).append(
						" is not null")
						.append("  and ").append(confMap.get("DTL_TAB_ANNAME").toString()).append(".del_flag = 0")
						.append(" and ").append(confMap.get("MAIN_KEY_COL").toString()).append("=").append("'").append(keyValue).append("'");

		if (INSERT_OP.equals(op)) {
			sqlBuffer.append(
					" and not EXISTS (select 1 from DRP_STORE_ACCT xx ")
					.append(" where xx.STORE_ID=").append(
							confMap.get("STORE_ID_COL").toString()).append(
							" and xx.PRD_ID=").append(
							confMap.get("PRD_ID_COL").toString()).append(
							" and xx.LEDGER_ID=").append(
							confMap.get("LEDGER_ID_COL").toString())
							.append(" and NVL(xx.SPCL_TECH_ID,0) = NVL("+confMap.get("SPCL_TECH_ID_COL").toString()+",0)")
					.append(")");
		}
		sqlBuffer.append(" group by ").append(
				confMap.get("PRD_ID_COL").toString()).append(",").append(
				confMap.get("STORE_ID_COL").toString()).append(",").append(
				confMap.get("LEDGER_ID_COL").toString()).append(",").append(
				confMap.get("SPCL_TECH_ID_COL").toString());
		return sqlBuffer.toString();
	}

	/**
	 * 库存实时帐查询临时数据(预订单发货申请要查预订单未冻结的货品)
	 * 
	 * @param keyValue是业务表的主键值
	 * @return
	 */
	private String queryStoreAcctForSend(Map confMap, String keyValue, String op) {
		String direction = String.valueOf(confMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer
				.append(" select ")
				.append(" sum(")
				.append(confMap.get("ACCT_NUM_COL"))
				.append(") * " + direction + " STORE_NUM, ")
				// 库存数量
				.append(" sum(")
				.append("NVL(" + confMap.get("FREEZE_NUM_COL") + ",0)")
				.append(") * " + direction + " FREEZE_NUM, ")
				// 冻结数量
				.append(confMap.get("PRD_ID_COL").toString())
				.append(",")
				// 货品
				.append(confMap.get("STORE_ID_COL").toString())
				.append(" as STORE_ID ")
				.append(",")
				// 库房
				.append(confMap.get("LEDGER_ID_COL").toString())
				.append(",")
				// 帐套
				.append(confMap.get("SPCL_TECH_ID_COL").toString())
				// 帐套
				.append(" from ")
				.append(confMap.get("MAIN_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ")
				.append(confMap.get("MAIN_KEY_COL").toString())
				.append("=")
				.append(getFKeyColName(confMap))
				.append(" and ")
				.append(confMap.get("STORE_ID_COL").toString())
				.append(" is not null")
				.append("  and ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(".del_flag = 0")
				.append(" and NVL(d.cancel_flag,0) = 0")
				.append(
						" and d.from_bill_dtl_id in (select advc_order_dtl_id from drp_advc_order_dtl  where advc_order_id =t.from_bill_id and NVL(is_freeze_flag,0)=0)")
				.append(" and ").append(confMap.get("MAIN_KEY_COL").toString())
				.append("=").append("'").append(keyValue).append("'");

		if (INSERT_OP.equals(op)) {
			sqlBuffer.append(
					" and not EXISTS (select 1 from DRP_STORE_ACCT xx ")
					.append(" where xx.STORE_ID=").append(
							confMap.get("STORE_ID_COL").toString()).append(
							" and xx.PRD_ID=").append(
							confMap.get("PRD_ID_COL").toString()).append(
							" and xx.LEDGER_ID=").append(
							confMap.get("LEDGER_ID_COL").toString())
							.append(" and NVL(xx.SPCL_TECH_ID,0) = NVL(d.SPCL_TECH_ID,0)")
					.append(")");
		}
		sqlBuffer.append(" group by ").append(
				confMap.get("PRD_ID_COL").toString()).append(",").append(
				confMap.get("STORE_ID_COL").toString()).append(",").append(
				confMap.get("LEDGER_ID_COL").toString()).append(",").append(
				confMap.get("SPCL_TECH_ID_COL").toString());
		return sqlBuffer.toString();
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
	 * 库存流水帐查询数据
	 * 
	 * @return
	 */
	private String queryJournalAcct(String bussId,Map confMap, String keyValue) throws Exception{
		String aname = (String) confMap.get("DTL_TAB_ANNAME");
		StringBuffer sqlBuffer = new StringBuffer();
		String billNoCol = (String) confMap.get("BILL_NO_COL");
		String channIdCol = (String) confMap.get("CHANN_ID_COL");
		String strPrice = (String) confMap.get("PRICE_COL");
		String strRateCol = (String) confMap.get("DECT_RATE_COL");
		String strDectPrice = (String) confMap.get("DECT_PRICE_COL");
		String strAcctAmountCol = (String) confMap.get("ACCT_AMOUNT_COL");
		String strFreezeNumCol = (String) confMap.get("FREEZE_NUM_COL");
		String dtlKeyCol = confMap.get("DTL_KEY_COL").toString();
		String PRD_SPEC_COL = (String)confMap.get("PRD_SPEC_COL");
		String PRD_COLOR_COL = (String)confMap.get("PRD_COLOR_COL");
		String BRAND_COL =  (String)confMap.get("BRAND_COL");
		String STD_UNIT_COL =  (String)confMap.get("STD_UNIT_COL");
		String STORE_ID = (String)confMap.get("STORE_ID_COL");
		String STORE_NO = (String)confMap.get("STORE_NO_COL");
		String STORE_NAME = (String)confMap.get("STORE_NAME_COL");
		String acctNumCol = (String)confMap.get("ACCT_NUM_COL");
		Map dumpChannMap = null;
		if(BusinessConsts.DUMP_OUT_CONF_ID.equals(bussId)){
			dtlKeyCol = "'ZC_'||"+dtlKeyCol;
			dumpChannMap = getChannMapByLendger(keyValue);
		}else if(BusinessConsts.DUMP_IN_CONF_ID.equals(bussId)){
			dtlKeyCol = "'ZR_'||"+dtlKeyCol;
			dumpChannMap = getChannMapByLendger(keyValue);
		}else if(BusinessConsts.CONV_PDT_CONF_1ID.equals(bussId)){
			dtlKeyCol = "'ZHC_'||"+dtlKeyCol;
		}else if(BusinessConsts.CONV_PDT_CONF_2ID.equals(bussId)){
			dtlKeyCol = "'ZHR_'||"+dtlKeyCol;
		}
		String inOutType = "'"+confMap.get("IN_OUT_TYPE").toString()+"' IN_OUT_TYPE";
		if(BusinessConsts.INVEN_CONF_ID.equals(bussId)){
			inOutType = " CASE  WHEN  d.DIFF_NUM >= 0 THEN '盘盈'  ELSE  '盘亏'  END IN_OUT_TYPE";
		}
		sqlBuffer.append("select ").append(dtlKeyCol).append(
				" JOURNAL_ACCT_ID,").append(
				 inOutType).append(
				",")// 出入库类型
				.append("'" + keyValue + "'").append(" BILL_ID,");// 单据ID
		if (billNoCol != null) {
			sqlBuffer.append(billNoCol).append(" BILL_NO, ");// 单据编号
		} else {
			sqlBuffer.append("'',");
		}
		sqlBuffer.append(confMap.get("DTL_KEY_COL").toString()).append(
				" BILL_DTL_ID, ").append(
				confMap.get("LEDGER_ID_COL").toString()).append(" LEDGER_ID, ");
		if (channIdCol != null) {
			sqlBuffer.append(confMap.get("CHANN_ID_COL").toString()).append(
					" CHANN_ID, ").append(
					confMap.get("CHANN_NO_COL").toString()).append(
					" CHANN_NO, ").append(
					confMap.get("CHANN_NAME_COL").toString()).append(
					" CHANN_NAME, ");
		} else if(dumpChannMap!=null){
			sqlBuffer.append("'"+dumpChannMap.get("CHANN_ID")+"'").append(
			" CHANN_ID, ").append("'"+dumpChannMap.get("CHANN_NO")+"'").append(
			" CHANN_NO, ").append("'"+dumpChannMap.get("CHANN_NAME")+"'").append(
			" CHANN_NAME, ");
		}else {
			sqlBuffer.append("'',").append("'',").append("'',");
		}
		if(STORE_ID!=null){
			sqlBuffer.append(STORE_ID).append(
			" STORE_ID, ").append(STORE_NO)
			.append(" STORE_NO, ")
			.append(STORE_NAME).append(" STORE_NAME, ");			
		}else {
			sqlBuffer.append("'',").append("'',").append("'',");
		}
		sqlBuffer.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(
						" SPCL_TECH_ID, ").append(
						confMap.get("PRD_ID_COL").toString()).append(
						" PRD_ID, ").append(
						confMap.get("PRD_NO_COL").toString()).append(
						" PRD_NO, ")
						.append(confMap.get("PRD_NAME_COL").toString())
						.append(" PRD_NAME, ");
		if(PRD_SPEC_COL!=null){
			sqlBuffer.append(PRD_SPEC_COL).append(" PRD_SPEC, ");
		}else{
			sqlBuffer.append("'',");
		}
		if(PRD_COLOR_COL!=null){
			sqlBuffer.append(PRD_COLOR_COL).append(" PRD_COLOR, ");
		}else{
			sqlBuffer.append("'',");
		}
	    if(BRAND_COL!=null){
	    	sqlBuffer.append(BRAND_COL).append(" BRAND, ");
	    }else{
	    	sqlBuffer.append("'',");
	    }
	    if(STD_UNIT_COL!=null){
	    	sqlBuffer.append(STD_UNIT_COL).append(" STD_UNIT, ");
	    }else{
	    	sqlBuffer.append("'',");
	    }
		if (strPrice != null) {
			sqlBuffer.append(confMap.get("PRICE_COL").toString()).append(
					" PRICE, ");
		} else {
			sqlBuffer.append("'',");
		}
		if (strRateCol != null) {
			sqlBuffer.append(confMap.get("DECT_RATE_COL").toString()).append(
					" DECT_RATE, ");
		} else {
			sqlBuffer.append("'',");
		}
		if (strDectPrice != null) {
			sqlBuffer.append(confMap.get("DECT_PRICE_COL").toString()).append(
					" DECT_PRICE, ");
		} else {
			sqlBuffer.append("'',");
		}

		if(acctNumCol!=null){
			if(BusinessConsts.INVEN_CONF_ID.equals(bussId)){
				sqlBuffer.append("abs("+acctNumCol).append(
				") IN_OUT_NUM, ");	
			}else{
				sqlBuffer.append(acctNumCol).append(
				" IN_OUT_NUM, ");				
			}

		}else{
			sqlBuffer.append("'',");
		}

		if (strAcctAmountCol != null) {
			sqlBuffer.append(confMap.get("ACCT_AMOUNT_COL").toString()).append(
					" IN_OUT_AMOUNT, ");
		} else {
			sqlBuffer.append("'',");
		}
		if (strFreezeNumCol != null) {
			sqlBuffer.append(confMap.get("FREEZE_NUM_COL").toString()).append(
					" FREEZE_NUM, ");
		} else {
			sqlBuffer.append("'',");
		}
		if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
			sqlBuffer.append(" t.year||'-'||t.month||'-01 00:00:01',");
		}else{
			sqlBuffer.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'), ");
		}
		
		if(BusinessConsts.STOREIN_ORDER_CONF5_ID.equals(bussId)){
			sqlBuffer.append(" t.STATEMENUT_DATE, ");
		}else if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
			sqlBuffer.append("to_date(t.year||'-'||t.month||'-01', 'yyyy-MM-DD'), ");
		}else{
			sqlBuffer.append(" to_date(to_char(sysdate, 'yyyy-MM-DD'), 'yyyy-MM-DD'), ");
		}
		if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
			sqlBuffer.append("t.year||'/'||t.month, ");
		}else{
			sqlBuffer.append("to_char(sysdate,'YYYY/MM'), ");
		}
		
		if(BusinessConsts.INVEN_CONF_ID.equals(bussId)){
			inOutType = " CASE  WHEN  d.DIFF_NUM >= 0 THEN 0  ELSE  1  END DIRECTION,";
			sqlBuffer.append(inOutType);
		}else{
			sqlBuffer.append(confMap.get("DIRECTION").toString())
			.append(" DIRECTION,");
		}
		
	    if(BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
	    	sqlBuffer.append("0 TAX_RATE,")
            .append(strDectPrice+" NO_TAX_DECT_PRICE,")
            .append("0 NO_TAX_DECT_AMOUNT,")
            .append("0 TAX_AMOUNT");
	    	
	    }else{
	    	sqlBuffer.append(" NVL(b.tax_rate,0.17) TAX_RATE,")
            .append(" CAST(NVL("+strDectPrice+",0)/(1+NVL(b.tax_rate,0.17)) as decimal(38, 2))  NO_TAX_DECT_PRICE,")
            .append(" CAST((NVL("+strDectPrice+",0)/(1+NVL(b.tax_rate,0.17)))*NVL("+acctNumCol+",0) as decimal(38, 2))  NO_TAX_DECT_AMOUNT,")
            .append(" CAST((NVL("+strDectPrice+",0)-  NVL("+strDectPrice+",0)/(1+NVL(b.tax_rate,0.17)))*NVL("+acctNumCol+",0)as decimal(38, 2)) TAX_AMOUNT");
	    }
	    sqlBuffer.append(" from ")
				.append(confMap.get("MAIN_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(" left join ")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" on ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap))
				.append(" left join base_chann b on "+channIdCol+" = b.chann_id")
				.append(" where ").append(aname)
				.append(".DEL_FLAG = 0 ").append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'");
      if(!BusinessConsts.COSTAD_CONF_ID.equals(bussId)){
    	  sqlBuffer.append(" and NVL("+acctNumCol+",0)!=0");
      }			
		return sqlBuffer.toString();
	}

	/**
	 * @param bussID业务类型
	 *            ，是用来获得配置信息用的。
	 * @param keyValue
	 *            记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats
	 *            业务状态
	 * @return
	 */
	public boolean txChkCanUseCredit(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String strTempCredite = getTempCredite(keyValue, confMap);
		String strBusCredite = null;
		if(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID.equals(bussID)){
			strBusCredite = getBusCrediteByGoodsOrder(keyValue, confMap);
		}else{
			strBusCredite = getBusCredite(keyValue, confMap);
		}
		
		StringBuffer checkBuf = new StringBuffer();
		checkBuf
				.append("Select ")
				.append(
						"(NVL(b.CREDIT_CURRT,0)+NVL(b.PAYMENT_CREDIT,0)-NVL(b.FREEZE_CREDIT,0)")
				.append("+NVL(("+strTempCredite+") ,0)+NVL(b.REBATE_CURRT,0)) USER_CREDITE").append(
						", NVL(bus.BUS_CREDITE,0) BUS_CREDITE").append(
						" from BASE_CHANN b").append(
						",(" + strBusCredite + ") bus").append(
						" where b.CHANN_ID = bus.CHANN_ID");

		Map params = new HashMap();
		params.put("SelSQL", checkBuf.toString());
		List resultList = InterUtil.selcomList(params);
		if (resultList != null && resultList.size() > 0) {
			Map resutMap = (Map) resultList.get(0);
			BigDecimal userCredite = (BigDecimal) resutMap.get("USER_CREDITE"); // 可用信用
			BigDecimal busCredite = (BigDecimal) resutMap.get("BUS_CREDITE"); // 当前信用
			String strUserCredite = userCredite.toString();
			if (!"0".equals(strUserCredite)
					&& userCredite.compareTo(busCredite) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 在订货订单模块，返利订货时判断总的信用够不够
	 * @param bussID业务类型
	 *            ，是用来获得配置信息用的。
	 * @param keyValue
	 *            记账业务的主键值,是用来获得记账所需要的参数值。
	 * @param chkType
	 * @param busStats
	 *            业务状态
	 * @return
	 */
	public boolean txChkAllCanUseCredit(String bussID, String keyValue)
			throws Exception {
		String channNo =  CreditCtrlUtil.getChannNoByGoodOrderId(keyValue);
		String CREDIT_CURRT = CreditCtrlUtil.getU9CurrCredit(channNo);
		StringBuffer checkBuf = new StringBuffer();
		checkBuf.append("Select NVL(b.ini_credit,0)+NVL(b.REBATE_CURRT, 0) + NVL(b.PAYMENT_CREDIT, 0) + ")
	    .append(" (NVL("+CREDIT_CURRT+", 0) - NVL(b.FREEZE_CREDIT, 0) +")
	   .append(" NVL((select sum(TEMP_CREDIT_REQ) TEMP_CREDITE")
		.append(" from ERP_TEMP_CREDIT_REQ req, DRP_GOODS_ORDER t")
		 .append(" where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd')")
		.append(" and req.STATE = '审核通过'")
		 .append(" and req.DEL_FLAG = 0")
		 .append(" and req.CHANN_ID = t.ORDER_CHANN_ID")
		 .append(" and t.GOODS_ORDER_ID = '"+keyValue+"'),")
		.append("0)) USER_CREDITE,")
		.append(" (NVL(bus.CURR_REBATE, 0) + NVL(bus.ALL_REBATE, 0)) CURR_REBATE")
		.append(" from BASE_CHANN b,")
	       .append("(select t.ORDER_CHANN_ID CHANN_ID,")
	      .append( "sum(NVL(d.REBATE_PRICE, 0) * d.ORDER_NUM) CURR_REBATE,")
	    .append("  sum((NVL(d.dect_price, 0)*")
	     .append(" NVL(d.order_num, 0) * 0.3)) ALL_REBATE")
	        .append("  from DRP_GOODS_ORDER t, DRP_GOODS_ORDER_DTL d")
	        .append(" where t.GOODS_ORDER_ID = d.GOODS_ORDER_ID")
	        .append("   and t.GOODS_ORDER_ID = '"+keyValue+"'")
	         .append("  and d.del_flag = 0 ")
	       .append("  group by t.ORDER_CHANN_ID) bus")
	.append(" where b.CHANN_ID = bus.CHANN_ID");
		Map params = new HashMap();
		params.put("SelSQL", checkBuf.toString());
		List resultList = InterUtil.selcomList(params);
		if (resultList != null && resultList.size() > 0) {
			Map resutMap = (Map) resultList.get(0);
			BigDecimal userCredite = (BigDecimal) resutMap.get("USER_CREDITE"); // 订货要用的信用
			BigDecimal busRebate = (BigDecimal) resutMap.get("CURR_REBATE"); // 总的返利额
			String strUserCredite = userCredite.toString();
			if (!"0".equals(strUserCredite)
					&& userCredite.compareTo(busRebate) >= 0) {
				return true;
			}
		}
		return false;
	}

	
	/**返利变更申请更新返利
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txRebateChange(String keyValue,int isCancle)throws Exception{
		int dection = 1;
		StringBuffer selBuf = new StringBuffer();
		selBuf.append(" select CHANGE_REBATE,CHANN_ID,BILL_TYPE from ERP_REBATE_CHANGE_REQ t where t.rebate_change_req_id = '")
		.append(keyValue).append("'");
		Map params = new HashMap();
		params.put("SelSQL", selBuf.toString());
		Map selMap = InterUtil.selcom(params);
		BigDecimal CHANGE_REBATE = (BigDecimal)selMap.get("CHANGE_REBATE");
		String BILL_TYPE = (String)selMap.get("BILL_TYPE");
		String CHANN_ID = (String)selMap.get("CHANN_ID");
		if(StringUtil.isEmpty(CHANN_ID)){
			throw new ServiceException("渠道信用不能为空 !");
		}
		if(StringUtil.isEmpty(BILL_TYPE)){
			throw new ServiceException("单据类型不能为空 !");
		}
		if(CHANGE_REBATE==null){
			throw new ServiceException("变更金额不能为空 !");
		}
		if("增加返利".equals(BILL_TYPE)){
			dection = 1;
		}else if("减少返利".equals(BILL_TYPE)){
			dection = -1;
		}else {
			throw new ServiceException("单据类型不正确!");
		}
		dection = dection*isCancle;
		StringBuffer rebateBuf = null;
		if(isCancle!=-1){
			rebateBuf = new StringBuffer();
			rebateBuf.append(" update ERP_REBATE_CHANGE_REQ req set ")
			.append("req.change_rebate_old = (select NVL(b.REBATE_CURRT, 0)")
			.append(" from BASE_CHANN b")
			.append(" where b.chann_id = req.chann_id),")
			.append(" req.CHANGE_REBATE_NEW = (select NVL(b.REBATE_CURRT, 0) from BASE_CHANN b where b.chann_id = req.chann_id) + NVL(req.CHANGE_REBATE,0)*"+dection)
			.append(" WHERE req.rebate_change_req_id = '")
			.append(keyValue).append("'");
			 params = new HashMap();
			params.put("UpdSQL", rebateBuf.toString());
			InterUtil.txUpdcom(params);			
		}

		
		rebateBuf = new StringBuffer();
		rebateBuf.append("update BASE_CHANN b ")
		.append("  SET b.REBATE_CURRT = NVL(b.REBATE_CURRT, 0) +")
		.append("  NVL((select NVL(t.CHANGE_REBATE, 0)")
		.append("  from ERP_REBATE_CHANGE_REQ t")
		.append("  where t.rebate_change_req_id = '")
		.append(keyValue).append("'),0)*"+dection)
		.append(",")
		.append(" b.REBATE_TOTAL = NVL(b.REBATE_TOTAL, 0) +")
		.append("  NVL((select NVL(t.CHANGE_REBATE, 0)")
		.append("  from ERP_REBATE_CHANGE_REQ t")
		.append("  where t.rebate_change_req_id = '")
		.append(keyValue).append("'),0)*"+dection)
		.append("  where b.CHANN_ID =")
		.append(" (select t.CHANN_ID")
		.append(" from ERP_REBATE_CHANGE_REQ t")
		.append("   where t.REBATE_CHANGE_REQ_ID = '")
		.append(keyValue).append("')");
		params = new HashMap();
		params.put("UpdSQL", rebateBuf.toString());
		InterUtil.txUpdcom(params);
		if(isCancle==-1){  //取消返利变更时,要删除流水
			delRebateChangeJournal(keyValue);
		}else{
			insRebateChangeJournal(keyValue);
		}
		
		return true;
	}
	
	
	public boolean insRebateChangeJournal(String keyValue)
	throws Exception {
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_REBATE_ACCT (JOURNAL_REBATE_ACCT_ID,"
								+ "BUSS_TYPE,BILL_ID,BILL_NO,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,"
								+ "PRD_AMOUNT,DEAL_TIME,REBATE_PRICE,"
								+ "YEAR_MONTH,DIRECTION,REBATE_TYPE)").append(
						" SELECT  sys_guid(),").append(
						" BILL_TYPE,")
				.append("REBATE_CHANGE_REQ_ID,REBATE_CHANGE_REQ_NO,")
				.append("LEDGER_ID,CHANN_ID,CHANN_NO,CHANN_NAME,")
				.append("CHANGE_REBATE,to_char(sysdate,'yyyy-MM-DD HH24:MI:SS'),CHANGE_REBATE,to_char(sysdate,'yyyy-MM') ")
				.append(",decode(t.bill_type , '增加返利',0,1),t.REBATE_TYPE")
				.append(" from ERP_REBATE_CHANGE_REQ t where t.REBATE_CHANGE_REQ_ID='")
				.append(keyValue).append("'");
		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}
	
	public boolean delRebateChangeJournal(String keyValue)
	throws Exception {
		StringBuffer delBuffer = new StringBuffer();
		delBuffer
				.append(
						"delete from BASE_JOURNAL_REBATE_ACCT ac ").append(" where  ac.bill_id='")
				.append(keyValue).append("'");
		Map params = new HashMap();
		params.put("DelSQL", delBuffer.toString());
		return InterUtil.txDelcom(params);
	}
	/**
	 * 跟据明细来做信用CHECK ，（目前主要是发运单的CHECK）
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
	public String chkCanUseCreditForSend(String bussID, String deliverOrderId)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String strTempCredite = getTempCredite2(deliverOrderId, confMap);
		String strBusCredite = getBusCrediteForDeliverOrder(deliverOrderId, confMap);
		StringBuffer checkBuf = new StringBuffer();
		checkBuf.append("select ").append(
				"(NVL(b.CREDIT_CURRT,0)-NVL(b.FREEZE_CREDIT,0)").append(
				"+NVL(temp.TEMP_CREDITE ,0)+NVL(b.REBATE_CURRT,0)) USER_CREDITE").append(
				", NVL(bus.BUS_CREDITE,0) BUS_CREDITE").append(
				" from BASE_CHANN b").append(",(" + strBusCredite + ") bus")
				.append(",(" + strTempCredite + ") temp").append(
						" where b.CHANN_ID = bus.CHANN_ID");

		Map params = new HashMap();
		params.put("SelSQL", checkBuf.toString());
		List resultList = InterUtil.selcomList(params);
		if (resultList != null && resultList.size() > 0) {
			Map resutMap = (Map) resultList.get(0);
			BigDecimal userCredite = (BigDecimal) resutMap.get("USER_CREDITE"); // 可用信用
			BigDecimal busCredite = (BigDecimal) resutMap.get("BUS_CREDITE"); // 当前信用
			String strUserCredite = userCredite.toString();
			if (!"0".equals(strUserCredite)
					&& userCredite.compareTo(busCredite) >= 0) {
				return "true";
			}else{
				float mustCredite =  busCredite.floatValue() - userCredite.floatValue() ;
				return "信用额度不够!还需支付"+mustCredite;
			}
		}
		return "true";
	}

	/**
	 * 更新付款凭证信用
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdPaymentCredit(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String direction = String.valueOf(confMap.get("DIRECTION"));
		String operation = "-";
		if ("0".equals(direction)) {
			operation = "+";
		} else {
			operation = "-";
		}
		StringBuffer updateBuf = new StringBuffer();
		updateBuf.append("UPDATE ").append("BASE_CHANN b ").append(
				"set b.PAYMENT_CREDIT = NVL(b.PAYMENT_CREDIT,0)").append(
				operation).append(" NVL((select ").append(
				confMap.get("PRD_AMOUNT_COL").toString()).append(" from ")
				.append(confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("='")
				.append(keyValue).append("'),0)").append(
						" where  b.CHANN_ID = (select ").append(
						confMap.get("CHANN_ID_COL").toString())
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("='")
				.append(keyValue).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", updateBuf.toString());
		return InterUtil.txUpdcom(params);
	}

	/**
	 * 更新冻结信用
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdFreezeCredit(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String direction = String.valueOf(confMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}

		StringBuffer updateBuf = new StringBuffer();
		updateBuf.append("UPDATE ").append("BASE_CHANN b ").append(
				"set b.FREEZE_CREDIT = NVL(b.FREEZE_CREDIT,0)+").append(
				" NVL((select ").append(
				"sum(NVL(" + confMap.get("FREEZE_PRICE_COL").toString())
				.append(",0)* NVL(").append(
						confMap.get("PRD_NUM_COL").toString()).append(", 0))")
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap))
				.append(" and d.DEL_FLAG = 0")
				.append(
						" and t.DEL_FLAG = 0 and ")
				.append(
						confMap.get("MAIN_KEY_COL").toString()).append(" = '")
				.append(keyValue).append("'").append(
						"),0) * " + direction + " WHERE b.CHANN_ID = (select ")
				.append(confMap.get("CHANN_ID_COL").toString())
				.append(" FROM ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append(" = '")
				.append(keyValue).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", updateBuf.toString());
		return InterUtil.txUpdcom(params);
	}

	/**
	 * 更新冻结信用--跟据明细ID冻结
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdFreezeCredit(String bussID, String mainKey,
			String dtlKeys) throws Exception {
		Map confMap = loadCreditConf(bussID);
		String direction = String.valueOf(confMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}

		StringBuffer updateBuf = new StringBuffer();
		updateBuf.append("UPDATE ").append("BASE_CHANN b ").append(
				"set b.FREEZE_CREDIT = b.FREEZE_CREDIT+").append(
				" NVL((select ").append(
				"sum(NVL(" + confMap.get("FREEZE_PRICE_COL").toString())
				.append(",0)* NVL(").append(
						confMap.get("PRD_NUM_COL").toString()).append(", 0))")
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(
						" and t.DEL_FLAG = 0 ")
			     .append(
						" and d.DEL_FLAG = 0 and ")
				 .append(" NVL(d.IS_FREE_FLAG,0) = 0 and ")
				.append(
						confMap.get("DTL_KEY_COL").toString()).append(" IN (")
				.append(dtlKeys).append(")").append(
						"),0) * " + direction + " WHERE b.CHANN_ID = (select ")
				.append(confMap.get("CHANN_ID_COL").toString())
				.append(" FROM ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append(" = '")
				.append(mainKey).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", updateBuf.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**
	 * 更新冻结信用--取消预订 按新的订货数量回冻结信用
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdFreezeCreditByCanleOrder(String bussID, String mainKey,
			String dtlKeys,String newOrderNum) throws Exception {
		Map confMap = loadCreditConf(bussID);
		String direction = String.valueOf(confMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}

		StringBuffer updateBuf = new StringBuffer();
		updateBuf.append("UPDATE ").append("BASE_CHANN b ").append(
				"set b.FREEZE_CREDIT = b.FREEZE_CREDIT+").append(
				" NVL((select ").append(
				"sum(NVL(" + confMap.get("FREEZE_PRICE_COL").toString())
				.append(",0)* (NVL("+newOrderNum+",0)))")
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(
						" and t.DEL_FLAG = 0 ")
			     .append(
						" and d.DEL_FLAG = 0 and ")
				 .append(" NVL(d.IS_FREE_FLAG,0) = 0 and ")
				.append(
						confMap.get("DTL_KEY_COL").toString()).append(" IN (")
				.append(dtlKeys).append(")").append(
						"),0) * " + direction + " WHERE b.CHANN_ID = (select ")
				.append(confMap.get("CHANN_ID_COL").toString())
				.append(" FROM ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append(" = '")
				.append(mainKey).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", updateBuf.toString());
		return InterUtil.txUpdcom(params);
	}

	/**
	 * 更新冻结信用--跟据明细ID冻结,现在中有‘货品发运’在用
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdFreezeCreditByDeliverOrder(String mainKey,
			String dtlKey) throws Exception {
		
		StringBuffer updateBuf = new StringBuffer();
		updateBuf.append("UPDATE BASE_CHANN b")
		.append("  set b.FREEZE_CREDIT = b.FREEZE_CREDIT -")
		.append("  NVL((select sum(NVL(d.CREDIT_FREEZE_PRICE, 0) *   NVL(d.PLAN_NUM, 0))")
		.append(" from ERP_DELIVER_ORDER     t,ERP_DELIVER_ORDER_DTL d")
		.append("  WHERE t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append("  and NVL(d.is_free_flag, 0) = 0")
		.append(" and d.DELIVER_ORDER_DTL_ID IN (")
		.append(dtlKey).append(")),").append(" 0) ")
		.append("  WHERE b.CHANN_ID = (select t.ORDER_CHANN_ID")
		.append("  FROM ERP_DELIVER_ORDER t")
		.append("  WHERE t.DELIVER_ORDER_ID = '").append(mainKey).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", updateBuf.toString());
		return InterUtil.txUpdcom(params);
	}

	/**
	 * 更新信用释放冻结信用
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdCanUseCredit(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String direction = String.valueOf(confMap.get("DIRECTION"));
		String operation = "-";
		if ("0".equals(direction)) {
			operation = "+";
		} else {
			operation = "-";
		}
		String freezePriceCol = (String) confMap.get("FREEZE_PRICE_COL");
		String currPriceCol = (String) confMap.get("DECT_PRICE_COL");
		if (freezePriceCol == null && currPriceCol == null) {
			throw new Exception("配置文件：" + bussID + "出错!");
		}

		StringBuffer currCreditBuf = new StringBuffer();
		if (currPriceCol != null) {
			currCreditBuf.append("select ").append(" NVL(SUM(NVL(").append(
					confMap.get("DECT_PRICE_COL").toString()).append(
					",0)* NVL(").append(confMap.get("PRD_NUM_COL").toString())
					.append(",0)),0)").append(" FROM ").append(
							confMap.get("MAIN_TAB_NAME").toString())
					.append(" ").append(
							confMap.get("MAIN_TAB_ANNAME").toString()).append(
							" ").append(",").append(
							confMap.get("DTL_TAB_NAME").toString()).append(" ")
					.append(confMap.get("DTL_TAB_ANNAME").toString()).append(
							" ").append(" WHERE ").append(
							confMap.get("MAIN_KEY_COL").toString()).append("=")
					.append(getFKeyColName(confMap)).append(
							" and t.DEL_FLAG = 0 and ").append(
							confMap.get("MAIN_KEY_COL").toString()).append(
							" = '").append(keyValue).append("'");
		} else {
			currCreditBuf.append("0");
		}

		StringBuffer freezeCreditBuf = new StringBuffer();
		if (freezePriceCol != null) {
			freezeCreditBuf.append("select ").append("NVL(SUM(NVL(").append(
					confMap.get("FREEZE_PRICE_COL").toString()).append(
					",0)* NVL(").append(confMap.get("PRD_NUM_COL").toString())
					.append(",0)),0)").append(" FROM ").append(
							confMap.get("MAIN_TAB_NAME").toString())
					.append(" ").append(
							confMap.get("MAIN_TAB_ANNAME").toString()).append(
							" ").append(",").append(
							confMap.get("DTL_TAB_NAME").toString()).append(" ")
					.append(confMap.get("DTL_TAB_ANNAME").toString()).append(
							" ").append(" WHERE ").append(
							confMap.get("MAIN_KEY_COL").toString()).append("=")
					.append(getFKeyColName(confMap)).append(
							" and t.DEL_FLAG = 0 and ").append(
							confMap.get("MAIN_KEY_COL").toString()).append(
							" = '").append(keyValue).append("'");
		} else {
			freezeCreditBuf.append("0");
		}

		StringBuffer updateSql = new StringBuffer();
		updateSql.append(
				"UPDATE BASE_CHANN b set b.CREDIT_CURRT = (b.CREDIT_CURRT")
				.append(operation).append("(").append(currCreditBuf.toString())
				.append("))").append(",").append(
						"b.FREEZE_CREDIT = (b.FREEZE_CREDIT ")
				.append(operation).append(" (").append(
						freezeCreditBuf.toString()).append("))").append(
						" WHERE ").append(" b.CHANN_ID = (select ").append(
						confMap.get("CHANN_ID_COL").toString())
				.append(" FROM ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append(" = '")
				.append(keyValue).append("')");

		Map params = new HashMap();
		params.put("UpdSQL", updateSql.toString());
		return InterUtil.txUpdcom(params);
	}

	/**
	 * 更新信用释放冻结信用
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txUpdCanUseCredit(String bussID, String keyValue,
			String dtlKey) throws Exception {
		Map confMap = loadCreditConf(bussID);
		StringBuffer currCreditBuf = new StringBuffer();
		currCreditBuf.append("select ").append("SUM(NVL(").append(
				confMap.get("DECT_PRICE_COL").toString()).append(",0)* NVL(")
				.append(confMap.get("PRD_NUM_COL").toString()).append(",0))")

				.append(" FROM ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(
						" and t.DEL_FLAG = 0 and ").append(
						confMap.get("DTL_KEY_COL").toString()).append(" = '")
				.append(dtlKey).append("'");

		StringBuffer freezeCreditBuf = new StringBuffer();
		freezeCreditBuf.append("select ").append("SUM(NVL(").append(
				confMap.get("DECT_PRICE_COL").toString()).append(",0) * NVL(")
				.append(confMap.get("PRD_NUM_COL").toString()).append(",0))")
				.append(" FROM ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" WHERE ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(
						" and t.DEL_FLAG = 0 and ").append(
						confMap.get("DTL_KEY_COL").toString()).append(" = '")
				.append(dtlKey).append("'");

		StringBuffer updateSql = new StringBuffer();
		updateSql.append(
				"UPDATE BASE_CHANN b set b.CREDIT_CURRT = (b.CREDIT_CURRT-(")
				.append(currCreditBuf.toString()).append("))").append(",")
				.append("b.FREEZE_CREDIT = (b.FREEZE_CREDIT - (").append(
						freezeCreditBuf.toString()).append("))").append(
						" WHERE ").append(" b.CHANN_ID = (select ").append(
						confMap.get("CHANN_ID_COL").toString())
				.append(" FROM ")
				.append(confMap.get("DTL_TAB_NAME").toString()).append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString()).append(" ")
				.append(" WHERE ")
				.append(confMap.get("DTL_KEY_COL").toString()).append(" = '")
				.append(dtlKey).append("')");

		Map params = new HashMap();
		params.put("UpdSQL", updateSql.toString());
		return InterUtil.txUpdcom(params);
	}


	
	/**
	 * 
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getBusCrediteForDeliverOrder(String bussID, Map confMap) throws Exception {
		StringBuffer busBuf = new StringBuffer();
		busBuf.append("select ").append(
				"sum((NVL((" + confMap.get("DECT_PRICE_COL").toString()
						+ "),0)-NVL(("
						+ confMap.get("FREEZE_PRICE_COL").toString()
						+ "),0))* NVL(" + confMap.get("PRD_NUM_COL").toString()
						+ ",0)) BUS_CREDITE ").append(
				" , t.ORDER_CHANN_ID CHANN_ID  ")
				.append(" from ").append(
						confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString()).append(" ")
				.append(",").append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ").append(confMap.get("DTL_TAB_ANNAME").toString())
				.append(" ").append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap))
				.append(" and t.DEL_FLAG = 0 ")
				.append(" and d.DEL_FLAG = 0 ")
				.append(" and NVL(d.IS_FREE_FLAG,0) = 0 ")
				.append(
						" and " + confMap.get("MAIN_KEY_COL").toString())
				.append("= '").append(bussID).append("'").append(
						" group by  t.ORDER_CHANN_ID ");
		return busBuf.toString();
	}
	
	/**
	 * 得本次信用 (按70%的信用算)
	 * )
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getBusCredite(String bussID, Map confMap) throws Exception {
		StringBuffer busBuf = new StringBuffer();
		busBuf.append("select ").append(
				"sum((NVL((" + confMap.get("DECT_PRICE_COL").toString()
						+ "),0)-NVL(("
						+ confMap.get("FREEZE_PRICE_COL").toString()
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
				.append(getFKeyColName(confMap))
				.append(" and d.del_flag = 0 ")
				.append(
						" and " + confMap.get("MAIN_KEY_COL").toString())
				.append("= '").append(bussID).append("'").append(
						" group by " + confMap.get("CHANN_ID_COL"));
		return busBuf.toString();
	}
	
	/**
	 * 得本次要用的信用 (按30%的信用算)  订货订单CHECK 30%
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getBusCrediteByGoodsOrder(String bussID, Map confMap) throws Exception {
		StringBuffer busBuf = new StringBuffer();
		busBuf.append("select ").append(
				"sum((NVL(("
						+ confMap.get("FREEZE_PRICE_COL").toString()
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
				.append(getFKeyColName(confMap))
				.append(" and d.del_flag = 0")
				.append(
						" and " + confMap.get("MAIN_KEY_COL").toString())
				.append("= '").append(bussID).append("'").append(
						" group by " + confMap.get("CHANN_ID_COL"));
		return busBuf.toString();
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
	 * 得临时信用
	 * 
	 * @param bussID
	 * @param confMap
	 * @return
	 * @throws Exception
	 */
	private String getTempCredite2(String deliverOrderId, Map confMap) throws Exception {
		StringBuffer creditBuf = new StringBuffer();
		creditBuf
				.append("select ")
				.append("sum(NVL(req.TEMP_CREDIT_REQ,0)) TEMP_CREDITE ")
				.append("from ERP_TEMP_CREDIT_REQ req ")
				.append(
						"where req.TEMP_CREDIT_VALDT >= to_date(to_char(sysdate,'yyyy-mm-dd'),'yyyy-mm-dd') and req.STATE = '审核通过' ")
				.append(" and req.CHANN_ID in (select t.ORDER_CHANN_ID CHANN_ID ")
				.append(" from ERP_DELIVER_ORDER t")
				.append(" where t.DELIVER_ORDER_ID = '"+deliverOrderId+"')");
		return creditBuf.toString();
	}

	/**
	 * 判断返利是否符合要求
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean txChkCanUseRebate(String bussID, String keyValue)
			throws Exception {
		Map configMap = loadRebateConf(bussID);
		StringBuffer checkBuf = new StringBuffer();
		String rebateTemp = getRebateTemp(keyValue, configMap);
		checkBuf.append(
				" SELECT NVL(b.REBATE_CURRT,0) USE_REBATE,a.CURR_REBATE")
				.append(" FROM BASE_CHANN b,").append("(").append(rebateTemp)
				.append(") a").append(" WHERE b.CHANN_ID = a.CHANN_ID").append(
						" for update of b.CHANN_ID");
		Map params = new HashMap();
		params.put("SelSQL", checkBuf.toString());
		List resultList = InterUtil.selcomList(params);
		if (resultList != null && resultList.size() > 0) {
			Map resutMap = (Map) resultList.get(0);
			BigDecimal useRebate = (BigDecimal) resutMap.get("USE_REBATE"); // 可用返利
			BigDecimal currRebate = (BigDecimal) resutMap.get("CURR_REBATE"); // 当前返利
			String strUseRate = useRebate.toString();
			if (!"0".equals(strUseRate) && useRebate.compareTo(currRebate) >= 0) {
				return true;
			}
		}
		return false;
	}

	
	
	public boolean txUpdateRebate(String bussID, String keyValue,String dtlKeys)
	throws Exception {
		Map configMap = loadRebateConf(bussID);
		String direction = String.valueOf(configMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}
		StringBuffer tempBuf = new StringBuffer();
		tempBuf.append(" (SELECT SUM(NVL(").append(
				configMap.get("REBATE_PRICE_COL")).append(",0)*")
		.append("NVL(").append(configMap.get("PRD_NUM_COL")).append(
				",0))").append(" FROM ").append(
				configMap.get("MAIN_TAB_NAME").toString()).append(" ")
		.append(configMap.get("MAIN_TAB_ANNAME").toString())
		.append(" ").append(",").append(
				configMap.get("DTL_TAB_NAME").toString()).append(" ")
		.append(configMap.get("DTL_TAB_ANNAME").toString()).append(" ")
		.append(" where ").append(
				configMap.get("MAIN_KEY_COL").toString()).append("=")
		.append(getFKeyColName(configMap))
		.append(" and d.del_flag = 0 ")
		.append(" and NVL(d.is_free_flag,0) = 0  ")
		.append(" and ").append(
				configMap.get("DTL_KEY_COL").toString()).append(" IN (")
		.append(dtlKeys).append(")")
		.append(
				" and " + configMap.get("MAIN_KEY_COL").toString())
		.append(" = '").append(keyValue).append("') * ").append(
				direction);		
		
		StringBuffer rebateBuf = new StringBuffer();
		rebateBuf
				.append(
						" UPDATE BASE_CHANN b SET b.REBATE_CURRT = NVL(b.REBATE_CURRT,0)+")
		        .append(tempBuf.toString()).append(",")			
			    .append(" b.REBATE_USED = NVL(b.REBATE_USED,0) - ")
			     .append(tempBuf.toString())
			    .append(" where  b.CHANN_ID = (select ")
				.append(configMap.get("CHANN_ID_COL").toString()).append(
						" from ").append(
						configMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(configMap.get("MAIN_TAB_ANNAME").toString())
				.append(" ").append(" where ").append(
						configMap.get("MAIN_KEY_COL").toString()).append("='")
				.append(keyValue).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", rebateBuf.toString());
		return InterUtil.txUpdcom(params);
}

	
	
	public boolean txUpdateRebateByNum(String bussID, String keyValue,String dtlKeys,String CANCEL_NUM)
	throws Exception {
		Map configMap = loadRebateConf(bussID);
		String direction = String.valueOf(configMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}
		StringBuffer tempBuf = new StringBuffer();
		tempBuf.append(" (SELECT NVL(SUM(NVL(").append(
				configMap.get("REBATE_PRICE_COL")).append(",0)*")
		.append("NVL(").append(CANCEL_NUM).append(
				",0)),0)").append(" FROM ").append(
				configMap.get("MAIN_TAB_NAME").toString()).append(" ")
		.append(configMap.get("MAIN_TAB_ANNAME").toString())
		.append(" ").append(",").append(
				configMap.get("DTL_TAB_NAME").toString()).append(" ")
		.append(configMap.get("DTL_TAB_ANNAME").toString()).append(" ")
		.append(" where ").append(
				configMap.get("MAIN_KEY_COL").toString()).append("=")
		.append(getFKeyColName(configMap))
		.append(" and d.del_flag = 0 and t.IS_USE_REBATE = 1 ")
		.append(" and NVL(d.is_free_flag,0) = 0  ")
		.append(" and ").append(
				configMap.get("DTL_KEY_COL").toString()).append(" IN (")
		.append(dtlKeys).append(")")
		.append(
				" and " + configMap.get("MAIN_KEY_COL").toString())
		.append(" = '").append(keyValue).append("') * ").append(
				direction);		
		
		StringBuffer rebateBuf = new StringBuffer();
		rebateBuf
				.append(
						" UPDATE BASE_CHANN b SET b.REBATE_CURRT = NVL(b.REBATE_CURRT,0)+")
		        .append(tempBuf.toString()).append(",")			
			    .append(" b.REBATE_USED = NVL(b.REBATE_USED,0) - ")
			     .append(tempBuf.toString())
			    .append(" where  b.CHANN_ID = (select ")
				.append(configMap.get("CHANN_ID_COL").toString()).append(
						" from ").append(
						configMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(configMap.get("MAIN_TAB_ANNAME").toString())
				.append(" ").append(" where ").append(
						configMap.get("MAIN_KEY_COL").toString()).append("='")
				.append(keyValue).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", rebateBuf.toString());
		return InterUtil.txUpdcom(params);
}
	
	public boolean txUpdateRebate(String bussID, String keyValue)
	throws Exception {
		Map configMap = loadRebateConf(bussID);
		String direction = String.valueOf(configMap.get("DIRECTION"));
		if ("0".equals(direction)) {
			direction = "1";
		} else {
			direction = "-1";
		}
		StringBuffer tempBuf = new StringBuffer();
		tempBuf.append(" (SELECT SUM(NVL(").append(
				configMap.get("REBATE_PRICE_COL")).append(",0)*")
		.append("NVL(").append(configMap.get("PRD_NUM_COL")).append(
				",0))").append(" FROM ").append(
				configMap.get("MAIN_TAB_NAME").toString()).append(" ")
		.append(configMap.get("MAIN_TAB_ANNAME").toString())
		.append(" ").append(",").append(
				configMap.get("DTL_TAB_NAME").toString()).append(" ")
		.append(configMap.get("DTL_TAB_ANNAME").toString()).append(" ")
		.append(" where ").append(
				configMap.get("MAIN_KEY_COL").toString()).append("=")
		.append(getFKeyColName(configMap)).append(
				" and " + configMap.get("MAIN_KEY_COL").toString())
		.append(" = '").append(keyValue).append("'")
		.append(" AND D.DEL_FLAG = 0 ")
		.append(") * ")
		.append(
				direction);
		
		StringBuffer rebateBuf = new StringBuffer();
		rebateBuf
				.append(
						" UPDATE BASE_CHANN b SET b.REBATE_CURRT = NVL(b.REBATE_CURRT,0)+")
			    .append(tempBuf.toString()).append(",")
			    .append(" b.REBATE_USED = NVL(b.REBATE_USED,0) - ")
			     .append(tempBuf.toString())
        .append(" where  b.CHANN_ID = (select ")
				.append(configMap.get("CHANN_ID_COL").toString()).append(
						" from ").append(
						configMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(configMap.get("MAIN_TAB_ANNAME").toString())
				.append(" ").append(" where ").append(
						configMap.get("MAIN_KEY_COL").toString()).append("='")
				.append(keyValue).append("')");
		Map params = new HashMap();
		params.put("UpdSQL", rebateBuf.toString());
		return InterUtil.txUpdcom(params);
	}
	

	/**返利流水账
	 * @param bussID
	 * @param keyValue
	 * @param dtlKeys
	 * @return
	 * @throws Exception
	 */
	public boolean insRebateJournal(String bussID, String keyValue,String dtlKeys)
			throws Exception {
		Map confMap = loadRebateConf(bussID);
		String priceCol = (String) confMap.get("PRICE_COL");
		String dectRateCol = (String) confMap.get("DECT_RATE_COL");
		String prdAmountCol = (String) confMap.get("PRD_AMOUNT_COL");
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_REBATE_ACCT (JOURNAL_REBATE_ACCT_ID,"
								+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
								+ "STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,PRD_NUM,PRD_AMOUNT,DEAL_TIME,REBATE_PRICE,"
								+ "YEAR_MONTH,DIRECTION)").append(
						" SELECT  sys_guid(),").append(
						"'" + confMap.get("BUSS_TYPE").toString()).append("',")
				.append(confMap.get("MAIN_KEY_COL").toString()).append(",")
				.append(confMap.get("BILL_NO_COL").toString()).append(",")
				.append(confMap.get("DTL_KEY_COL").toString()).append(",")
				.append(confMap.get("LEDGER_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_NO_COL").toString()).append(",")
				.append(confMap.get("CHANN_NAME_COL").toString()).append(",")
				.append(confMap.get("PRD_ID_COL").toString()).append(",")
				.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(",")
				.append(confMap.get("PRD_NO_COL").toString()).append(",")
				.append(confMap.get("PRD_NAME_COL").toString()).append(",")
				.append(confMap.get("PRD_SPEC_COL").toString()).append(",")
				.append(confMap.get("PRD_COLOR_COL").toString()).append(",")
				.append(confMap.get("BRAND_COL").toString()).append(",")
				.append(confMap.get("STD_UNIT_COL").toString()).append(",");
		if (priceCol != null) {
			instBuffer.append(priceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectRateCol != null) {
			instBuffer.append(dectRateCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		instBuffer.append(confMap.get("DECT_PRICE_COL").toString()).append(",")
				.append(confMap.get("PRD_NUM_COL").toString()).append(",");
		instBuffer.append("NVL("+confMap.get("PRD_NUM_COL").toString()+",0)*NVL("+confMap.get("REBATE_PRICE_COL").toString()+",0)").append(",");
		instBuffer.append(" to_char(sysdate,'yyyy-MM-DD HH24:MI:SS') ").append(",").append(
				confMap.get("REBATE_PRICE_COL").toString()).append(",").append(
				" to_char(sysdate,'yyyy/MM') ").append(",").append(
				confMap.get("DIRECTION").toString()).append(" from ").append(
				confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'")
				.append(" and NVL("+confMap.get("REBATE_PRICE_COL").toString()+",0)>0")
				.append(" and ").append(
						confMap.get("DTL_KEY_COL").toString()).append(" IN (")
				.append(dtlKeys).append(")");

		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}
	
	/**返利流水账
	 * @param bussID
	 * @param keyValue
	 * @param dtlKeys
	 * @return
	 * @throws Exception
	 */
	public boolean insRebateJournal(String bussID, String keyValue,String dtlKeys,String ORDER_NUM)
			throws Exception {
		Map confMap = loadRebateConf(bussID);
		String priceCol = (String) confMap.get("PRICE_COL");
		String dectRateCol = (String) confMap.get("DECT_RATE_COL");
		String prdAmountCol = (String) confMap.get("PRD_AMOUNT_COL");
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_REBATE_ACCT (JOURNAL_REBATE_ACCT_ID,"
								+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
								+ "STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,PRD_NUM,PRD_AMOUNT,DEAL_TIME,REBATE_PRICE,"
								+ "YEAR_MONTH,DIRECTION)").append(
						" SELECT  sys_guid(),").append(
						"'" + confMap.get("BUSS_TYPE").toString()).append("',")
				.append(confMap.get("MAIN_KEY_COL").toString()).append(",")
				.append(confMap.get("BILL_NO_COL").toString()).append(",")
				.append(confMap.get("DTL_KEY_COL").toString()).append(",")
				.append(confMap.get("LEDGER_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_NO_COL").toString()).append(",")
				.append(confMap.get("CHANN_NAME_COL").toString()).append(",")
				.append(confMap.get("PRD_ID_COL").toString()).append(",")
				.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(",")
				.append(confMap.get("PRD_NO_COL").toString()).append(",")
				.append(confMap.get("PRD_NAME_COL").toString()).append(",")
				.append(confMap.get("PRD_SPEC_COL").toString()).append(",")
				.append(confMap.get("PRD_COLOR_COL").toString()).append(",")
				.append(confMap.get("BRAND_COL").toString()).append(",")
				.append(confMap.get("STD_UNIT_COL").toString()).append(",");
		if (priceCol != null) {
			instBuffer.append(priceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectRateCol != null) {
			instBuffer.append(dectRateCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		instBuffer.append(confMap.get("DECT_PRICE_COL").toString()).append(",")
				.append(ORDER_NUM).append(",");
		instBuffer.append("NVL("+ORDER_NUM+",0)*NVL("+confMap.get("REBATE_PRICE_COL").toString()+",0)").append(",");
		instBuffer.append(" to_char(sysdate,'yyyy-MM-DD HH24:MI:SS') ").append(",").append(
				confMap.get("REBATE_PRICE_COL").toString()).append(",").append(
				" to_char(sysdate,'yyyy/MM') ").append(",").append(
				confMap.get("DIRECTION").toString()).append(" from ").append(
				confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'")
				.append(" and t.IS_USE_REBATE = 1 ")
				.append(" and ").append(
						confMap.get("DTL_KEY_COL").toString()).append(" IN (")
				.append(dtlKeys).append(")");

		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}
	
	/**返利登记流水账
	 * @param bussID
	 * @param keyValue
	 * @param dtlKeys
	 * @return
	 * @throws Exception
	 */
	public boolean insRebateRegistJournal(String BUSS_TYPE,String LEDGER_ID,String PRD_AMOUNT,String CHANN_IDS,String DIRECTION)
			throws Exception {
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_REBATE_ACCT (JOURNAL_REBATE_ACCT_ID,"
								+ "BUSS_TYPE,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,"
								+ "PRD_AMOUNT,DEAL_TIME,"
								+ "YEAR_MONTH,DIRECTION)").append(
						" SELECT sys_guid(),").append(
						"'" + BUSS_TYPE).append("',")
				.append(LEDGER_ID).append(",")
				.append("CHANN_ID").append(",")
				.append("CHANN_NO").append(",")
				.append("CHANN_NAME").append(",")
				.append(PRD_AMOUNT).append(",")
				.append(" to_char(sysdate,'yyyy-MM-DD HH24:MI:SS') ").append(",")
				.append(" to_char(sysdate,'yyyy/MM'), ")
				.append(DIRECTION).append("")
				.append(" from BASE_CHANN WHERE CHANN_ID IN (")
				.append(CHANN_IDS).append(")");
		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}
	
	
	
	public boolean insRebateJournal(String bussID, String keyValue)
	throws Exception {
		Map confMap = loadRebateConf(bussID);
		String priceCol = (String) confMap.get("PRICE_COL");
		String dectRateCol = (String) confMap.get("DECT_RATE_COL");
		String prdAmountCol = (String) confMap.get("PRD_AMOUNT_COL");
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_REBATE_ACCT (JOURNAL_REBATE_ACCT_ID,"
								+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
								+ "STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,PRD_NUM,PRD_AMOUNT,DEAL_TIME,REBATE_PRICE,"
								+ "YEAR_MONTH,DIRECTION)").append(
						" SELECT  sys_guid(),").append(
						"'" + confMap.get("BUSS_TYPE").toString()).append("',")
				.append(confMap.get("MAIN_KEY_COL").toString()).append(",")
				.append(confMap.get("BILL_NO_COL").toString()).append(",")
				.append(confMap.get("DTL_KEY_COL").toString()).append(",")
				.append(confMap.get("LEDGER_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_NO_COL").toString()).append(",")
				.append(confMap.get("CHANN_NAME_COL").toString()).append(",")
				.append(confMap.get("PRD_ID_COL").toString()).append(",")
				.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(",")
				.append(confMap.get("PRD_NO_COL").toString()).append(",")
				.append(confMap.get("PRD_NAME_COL").toString()).append(",")
				.append(confMap.get("PRD_SPEC_COL").toString()).append(",")
				.append(confMap.get("PRD_COLOR_COL").toString()).append(",")
				.append(confMap.get("BRAND_COL").toString()).append(",")
				.append(confMap.get("STD_UNIT_COL").toString()).append(",");
		if (priceCol != null) {
			instBuffer.append(priceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectRateCol != null) {
			instBuffer.append(dectRateCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		instBuffer.append(confMap.get("DECT_PRICE_COL").toString()).append(",")
				.append(confMap.get("PRD_NUM_COL").toString()).append(",");
		if (prdAmountCol != null) {
			instBuffer.append(prdAmountCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		instBuffer.append("  to_char(sysdate,'yyyy-MM-DD HH24:MI:SS') ").append(",").append(
				confMap.get("REBATE_PRICE_COL").toString()).append(",").append(
				"  to_char(sysdate,'yyyy-MM') ").append(",").append(
				confMap.get("DIRECTION").toString()).append(" from ").append(
				confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'")
				.append(" and d.del_flag=0");
		
		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}

	private String getRebateTemp(String valeKey, Map configMap)
			throws Exception {
		StringBuffer tempBuf = new StringBuffer();
		tempBuf.append(" SELECT sum(NVL(").append(
				configMap.get("REBATE_PRICE_COL").toString()).append(",0)")
				.append("*").append(configMap.get("PRD_NUM_COL")).append(
						") CURR_REBATE ,")
				.append(configMap.get("CHANN_ID_COL")).append(" CHANN_ID")
				.append(" from ").append(configMap.get("MAIN_TAB_NAME"))
				.append(" ").append(configMap.get("MAIN_TAB_ANNAME")).append(
						",").append(configMap.get("DTL_TAB_NAME")).append(" ")
				.append(configMap.get("DTL_TAB_ANNAME")).append(" WHERE ")
				.append(configMap.get("MAIN_KEY_COL")).append("=").append(
						getFKeyColName(configMap)).append(" and ").append(
						configMap.get("DTL_TAB_ANNAME")).append(".DEL_FLAG=0")
				.append(" and " + configMap.get("MAIN_KEY_COL").toString())
				.append("= '").append(valeKey).append("'").append(" group by ")
				.append(configMap.get("CHANN_ID_COL"));
		return tempBuf.toString();
	}

	/**
	 * 查询返利配置信息
	 */
	private Map loadRebateConf(String bussID) {
		String sql = "select * from BASE_REBATE_ACCT_CONF where REBATE_ACCT_CONF_ID='"
				+ bussID + "'";
		Map params = new HashMap();
		params.put("SelSQL", sql);
		return InterUtil.selcom(params);
	}


	private boolean instRecivePayment(String payMentId, String channId,
			String paymentPrice, String date, String reMark) throws Exception {
		Map cheannMap = getChannMap(channId);
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						" INSERT INTO ERP_PAYMENT (PAYMENT_ID,PAYMENT_NO,"
								+ "CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,"
								+ "AREA_NO,AREA_NAME,PAY_AMONT,PAY_DATE,STATE,REMARK,DEL_FLAG)VALUES(")
				.append("'").append(payMentId).append("',").append("'").append(
						LogicUtil.getBmgz("ERP_PAYMENT_NO")).append("',")
				.append("'").append(cheannMap.get("CHANN_ID")).append("',")
				.append("'").append(cheannMap.get("CHANN_NO")).append("',")
				.append("'").append(cheannMap.get("CHANN_NAME")).append("',")
				.append("'").append(cheannMap.get("CHANN_ABBR")).append("',")
				.append("'").append(cheannMap.get("CHANN_TYPE")).append("',")
				.append("'").append(cheannMap.get("CHAA_LVL")).append("',")
				.append("'").append(cheannMap.get("AREA_NO")).append("',")
				.append("'").append(cheannMap.get("AREA_NAME")).append("',")
				.append("'").append(paymentPrice).append("',").append(
						"to_date('" + date + "','yyyy-MM-dd')").append(",")
				.append("'").append("已记账").append("',").append("'").append(
						reMark).append("',").append("'").append(0).append("'")
				.append(")");
		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}

	/**
	 * 跟据渠道ID得相关渠道信息
	 * 
	 * @param channNo
	 * @return
	 */
	private Map getChannMap(String channNo) throws Exception {
		StringBuffer chanBuf = new StringBuffer();
		chanBuf
				.append(
						" SELECT CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,AREA_NO,AREA_NAME FROM BASE_CHANN WHERE CHANN_NO = '")
				.append(channNo).append("'");
		Map params = new HashMap();
		params.put("SelSQL", chanBuf.toString());
		return InterUtil.selcom(params);
	}
	
	/**
	 * 跟据渠道NO得相关渠道信息
	 * 
	 * @param channId
	 * @return
	 */
	private Map getChannMapByNo(String channNo) throws Exception {
		StringBuffer chanBuf = new StringBuffer();
		chanBuf
				.append(
						" SELECT CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,AREA_NO,AREA_NAME,STATE FROM BASE_CHANN WHERE CHANN_NO = '")
				.append(channNo).append("'");
		Map params = new HashMap();
		params.put("SelSQL", chanBuf.toString());
		return InterUtil.selcom(params);
	}

	/**
	 * U9接口 发运单确认时U9导入实出数量,序列号
	 * 
	 * @param jsonDeliver
	 * @param jsonPdt
	 * @param jsonStoreOut
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txUpdateDeliverOrder(String jsonDeliver) {
		InterReturnMsg msg = new InterReturnMsg();
		if (jsonDeliver == null || jsonDeliver.trim().length() == 0) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		ArrayList dtlList = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonDeliver);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			dtlList = jsonUtil.getMbDtlList();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonDeliver;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String deliverOrderNo = (String) bodyMap.get("DELIVER_ORDER_NO");
		String TRUCK_NO = (String) bodyMap.get("TRUCK_NO");
		String STOREOUT_TIME = (String) bodyMap.get("STOREOUT_TIME");
		if(!checkisDeliverStats(deliverOrderNo)){
			String errorInfo = "不能对已发货,已收货，待收货的发运单更新实发数量!";
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			msg.setKEY(deliverOrderNo);
			msg.setAPPCODE(APPCODE);
			return msg;
		}
		
		String[] deliverOrderDtlIds = new String[dtlList.size()];
		for (int j = 0; j < dtlList.size(); j++) {
			HashMap dtlMap = (HashMap) dtlList.get(j);
			String deliverOrderdtlId = (String) dtlMap
					.get("DELIVER_ORDER_DTL_ID");
			deliverOrderDtlIds[j] = deliverOrderdtlId;
			try {
				flg = updateDeliverOrderDtl(dtlMap,APPCODE);
			} catch (Exception ex) {
				String errorInfo = "更新发运单明细信息出错!" + InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(errorInfo);
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("更新发运单明细信息出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			
			try {
				String strPrdSns = (String) dtlMap.get("PRD_SN");
				if(strPrdSns!=null && strPrdSns.trim().length()>0){
					flg = instErpSN(deliverOrderdtlId, strPrdSns);
				}
			} catch (Exception ex) {
				String errorInfo = "更新出库货品序列号出错!" + InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("更新出库货品序列号出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("更新出库货品序列号出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
		}
		boolean IS_SEND_FIN = checkDeliverDtlStat(deliverOrderNo);
		try {
			flg = updateDeliverOrder(deliverOrderNo,TRUCK_NO,STOREOUT_TIME,IS_SEND_FIN);
		} catch (Exception ex) {
			String errorInfo = "更新发运单主表信息出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			msg.setKEY(deliverOrderNo);
			msg.setAPPCODE(APPCODE);
			return msg;
		}
		if (!flg) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("更新发运单主表信息出错!");
			msg.setKEY(deliverOrderNo);
			msg.setAPPCODE(APPCODE);
			return msg;
		}
		
		if(IS_SEND_FIN){
			Map deliverOrderMap = null;
			List childList = null;
			try{
				deliverOrderMap = getDeliverOrder(deliverOrderNo);
				childList = getDeliverOrderDtl(deliverOrderDtlIds);
			}catch(Exception ex){
				String errorInfo = "跟据发运单号不能找到发运单信息!" + InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(errorInfo);
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			ArrayList storeinDtl = new ArrayList();
			ArrayList basedtalList = new ArrayList();
			try {
				setDtlList(deliverOrderMap,childList,storeinDtl,basedtalList);
			} catch (Exception ex) {
				String errorInfo = InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("区分总部直发出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			try {
				flg = sendNotice(deliverOrderMap, childList, msg);
			} catch (Exception ex) {
				String errorInfo = "发送消息给订单员或导购员出错!" + InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("发送消息给订单员或导购员出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("发送消息给订单员或导购员出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			try {
				flg = genStoreInAndDstr(deliverOrderMap, storeinDtl, msg);
			} catch (Exception ex) {
				String errorInfo = "产生入库通知单出错!" + InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("产生入库通知单出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("产生入库通知单出错!");
				msg.setKEY(deliverOrderNo);
				msg.setAPPCODE(APPCODE);
				return msg;
			}
			try {
				if(basedtalList.size()>0){
					flg =genBaseDeliverNotice(deliverOrderMap,basedtalList);
				}
			} catch (Exception ex) {
				String errorInfo = "产生直发通知单出错!" + InterUtil.getErrorInfo(ex);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("产生入库通知单出错!"+errorInfo);
				msg.setAPPCODE(APPCODE);
				msg.setFLAG(BusinessConsts.SUCCESS);
				return msg;
			}			
		}
		msg.setKEY(deliverOrderNo);
		msg.setAPPCODE(APPCODE);
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**检查是否可以更新实发数量(已发货，待收货，已收货不能推)
	 * @return
	 */
	private boolean checkisDeliverStats(String deliverOrderNo){
		String [] strStats = {"已收货","已发货","待收货"};
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT t.STATE")
	    .append("  FROM ERP_DELIVER_ORDER t")
		.append(" ")
		.append(" where ")
		.append("  t.DELIVER_ORDER_NO = '"+deliverOrderNo+"'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map checkMap = InterUtil.selcom(map);
		String STATE = (String)checkMap.get("STATE");
		if(StringUtil.isContain(strStats,STATE)){
			return false;
		}
		return true;
	}
	
	/**
	 * 查询发运单主表
	 * 
	 * @param deliverOrderId
	 * @return
	 */
	private Map getDeliverOrder(String deliverOrderId) {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						"select u.DELIVER_ORDER_ID,u.DELIVER_ORDER_ID,u.DELIVER_ORDER_NO,")
				.append(
						"u.DELIVER_TYPE,u.DELIVER_TYPE,u.BILL_TYPE,u.TRUCK_TYPE,u.CHANN_TYPE,u.PRVD_ID,u.PRVD_NO,")
				.append(
						"u.PRVD_NAME,u.AREA_SER_ID,u.AREA_SER_NO,u.AREA_SER_NAME,u.SEND_CHANN_ID,u.RECV_CHANN_ID,u.RECV_CHANN_NO,u.RECV_CHANN_NAME,")
				.append(
						"u.SEND_CHANN_NO,u.ADVC_SEND_DATE,u.TOTAL_VOLUME,u.STATE,u.REMARK,u.SHIP_POINT_ID,")
				.append(
						"u.SHIP_POINT_NAME,to_char(u.APPCH_TIME,'yyyy-MM-DD HH24:MI:SS') APPCH_TIME,")
				.append(
						"u.CRE_NAME,u.CRE_NAME,u.CREATOR,to_char(u.CRE_TIME,'yyyy-MM-DD HH24:MI:SS') CRE_TIME,")
				.append(
						"u.UPD_NAME,u.UPDATOR,to_char(u.UPD_TIME,'yyyy-MM-DD HH24:MI:SS') UPD_TIME,u.DEPT_ID,")
				.append(
						"u.DEPT_NAME,u.ORG_ID,u.ORG_NAME,u.LEDGER_ID,u.LEDGER_NAME,u.DEL_FLAG,u.BILL_TYPE")
				.append(
						" from ERP_DELIVER_ORDER u where u.DELIVER_ORDER_NO = '")
				.append(deliverOrderId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		return InterUtil.selcom(params);
	}

	/**
	 * 查询发运单明细
	 * 
	 * @param deliverOrderDtlIds
	 * @return
	 */
	private List getDeliverOrderDtl(String[] deliverOrderDtlIds) {
		StringBuffer dtlIds = new StringBuffer();
		for (int i = 0; i < deliverOrderDtlIds.length; i++) {
			dtlIds.append("'" + deliverOrderDtlIds[i] + "',");
		}
		String ids = dtlIds.toString();
		if (!StringUtil.isEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);
		}
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						"select DELIVER_ORDER_DTL_ID,DELIVER_ORDER_ID,SALE_ORDER_ID,SALE_ORDER_NO,SALE_ORDER_DTL_ID,ORDER_CHANN_ID,")
				.append(
						"ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,")
				.append(
						"PRD_COLOR,BRAND,STD_UNIT,VOLUME,IS_NO_STAND_FLAG,SPCL_TECH_ID,ADVC_PLAN_NUM,PLAN_NUM,REAL_SEND_NUM,NO_SEND_NUM,")
				.append(
						"NO_SEND_DEAL_TYPE,DEL_FLAG,U_CODE,GOODS_ORDER_NO from ERP_DELIVER_ORDER_DTL where DELIVER_ORDER_DTL_ID in("
								+ ids + ")");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		List resultList = InterUtil.selcomList(params);
		return resultList;
	}

	/**
	 * 把有特殊工艺的货品 给对应的 通知
	 * 
	 * @author zhang_zhongbin
	 */
	public boolean sendNotice(Map deliverOrderMap, List childList,
			InterReturnMsg returnMsg) {
		try {
			if(!checkSendNotice(childList)){
				return true;
			}
			String RECV_CHANN_ID = (String) deliverOrderMap.get("RECV_CHANN_ID");
			String RECV_CHANN_NAME = (String) deliverOrderMap.get("RECV_CHANN_NAME");
			StringBuffer content = new StringBuffer();
			Map result = new HashMap();
			List newList = null;
			for (int i = 0; i < childList.size(); i++) {
				HashMap child = (HashMap) childList.get(i);
				String PRD_NAME = (String)child.get("PRD_NAME");
				String SPCL_TECH_ID = (String)child.get("SPCL_TECH_ID");
				if(SPCL_TECH_ID!=null && SPCL_TECH_ID.trim().length()>0 ){
					sendNoiceToDGRY(SPCL_TECH_ID,PRD_NAME);
				}
				content.append("[");
				content.append(child.get("PRD_NO"));
				content.append("|");
				content.append(child.get("PRD_NAME"));
				content.append("]");
			}
			sendNoiceToOrderRY(RECV_CHANN_ID,RECV_CHANN_NAME,content.toString());
			return true;
		} catch (Exception ex) {
			logger.info(ex);
			returnMsg.setFLAG(BusinessConsts.FLASE);
			returnMsg.setMESSAGE("发送消息到区域服务中心出错!");
			return false;
		}
	}

	/**判断是否要发消息通知，有特殊工艺的才要通知，没有特殊工艺的不通知
	 * @param childList
	 * @return
	 */
	private boolean checkSendNotice(List childList){
		for (int i = 0; i < childList.size(); i++) {
			HashMap child = (HashMap) childList.get(i);
			String SPCL_TECH_ID = (String)child.get("SPCL_TECH_ID");
			if(SPCL_TECH_ID!=null && SPCL_TECH_ID.trim().length()>0 ){
				return true;
			}
		}
		return false;
	}
	
	/**发送消息给订单员
	 * @param SPCL_TECH_ID
	 * @param PRD_NAME
	 */
	private void sendNoiceToOrderRY(String RECV_CHANN_ID,String RECV_CHANN_NAME,String content){
		StringBuffer bufSql = new StringBuffer();
		bufSql.append("select c.XTYHID")
		.append(" from T_SYS_JSQX a, T_SYS_XTYHJS b, T_SYS_XTYH c, T_SYS_RYXX e")
		.append(" where a.XTJSID = b.XTJSID")
		.append(" and b.XTYHID = c.XTYHID")
		.append(" and c.RYXXID = e.RYXXID")
		.append(" and a. XTMKID = 'FX0020401'")
		.append("and c.YHZT = '启用'")
		.append("and c.ZTXXID = '"+RECV_CHANN_ID+"'");
		
		Map params = new HashMap();
		params.put("SelSQL", bufSql.toString());
		List ryList  = InterUtil.selcomList(params);
		StringBuffer xmBuf = new StringBuffer();
		for(int i=0;i<ryList.size();i++){
			HashMap ryMap = (HashMap)ryList.get(i);
			String XTYHID = (String)ryMap.get("XTYHID");
			xmBuf.append(XTYHID);
			if(i!=ryList.size()-1){
				xmBuf.append(",");
			}
		}
		
		String msg = "特殊工艺货品 " + content + " 送至[" + RECV_CHANN_NAME + "]";
		Map<String,String> sendMap = new TreeMap();
		sendMap.put("YHBH","U9");
		sendMap.put("SENDID", StringUtil.uuid32len());
		sendMap.put("SENDER", "U9");
		sendMap.put("MSGINFO", msg);
		sendMap.put("XTYHID", "'"+xmBuf.toString()+"'");
		sendMap.put("BMXXID", "''");
		sendMap.put("JGXXID",  "''");
		this.insert("MESSAGE.insertMessage", sendMap);
	}
	
	/**发送消息给导够人员
	 * @param SPCL_TECH_ID
	 * @param PRD_NAME
	 */
	private void sendNoiceToDGRY(String SPCL_TECH_ID,String PRD_NAME){
		StringBuffer bufSql = new StringBuffer();
		bufSql.append("SELECT T.TERM_ID,T.TERM_NAME  FROM DRP_ADVC_ORDER T")
		.append(" LEFT JOIN DRP_ADVC_ORDER_DTL D  ON T.ADVC_ORDER_ID = D.ADVC_ORDER_ID ")
		.append(" WHERE D.SPCL_TECH_ID = '").append(SPCL_TECH_ID).append("'")
		.append("AND T.TERM_ID IS NOT NULL AND ROWNUM = 1");
		Map params = new HashMap();
		params.put("SelSQL", bufSql.toString());
		Map map = InterUtil.selcom(params);
		if(map==null){   //如果找不到预订单，说明不是导购员下的单
			return;
		}
		String TERM_ID = (String)map.get("TERM_ID");
		String TERM_NAME = (String)map.get("TERM_NAME");
		String msg = "货品 " + PRD_NAME + " 送至[" + TERM_NAME + "]";
		Map<String,String> sendMap = new TreeMap();
		sendMap.put("YHBH","U9");
		sendMap.put("SENDID", StringUtil.uuid32len());
		sendMap.put("SENDER", "U9");
		sendMap.put("MSGINFO", msg);
		sendMap.put("XTYHID", "''");
		sendMap.put("BMXXID", "'"+TERM_ID+"'");
		sendMap.put("JGXXID", "''");
		this.insert("MESSAGE.insertMessage", sendMap);
	}
	
	/**订货方是区服中心，收货方是加盟商的生成直发通知单，其它的都生成入库通知单
	 * @param deliverOrderMap
	 * @param childList
	 * @param storeinDtl
	 * @param basedtalList
	 * @throws Exception
	 */
	private void setDtlList(Map deliverOrderMap,List childList,ArrayList storeinDtl,ArrayList basedtalList)throws Exception{
		String RECV_CHANN_NO = (String)deliverOrderMap.get("RECV_CHANN_NO");
		Map recvChannMap = getChannMap(RECV_CHANN_NO);
		String recvChannType = (String)recvChannMap.get("CHANN_TYPE");
		if("加盟商".equals(recvChannType)){
			for (int i = 0; i < childList.size(); i++) {
				HashMap dtlMap = (HashMap) childList.get(i);
				String ORDER_CHANN_NO = (String)dtlMap.get("ORDER_CHANN_NO");
				Map orderChannMap = getChannMap(ORDER_CHANN_NO);
				String orderChannType = (String)orderChannMap.get("CHANN_TYPE");
				if("区域服务中心".equals(orderChannType)){
					basedtalList.add(dtlMap);
				}else{
					storeinDtl.add(dtlMap);
				}
			}
		}else{
			storeinDtl.addAll(childList);
		}
	}

	/**订货方是区域服务中心，收货方是加盟商的才要产生总部直发通知单
	 * @param deliverOrderMap
	 * @param childList
	 * @return
	 * @throws Exception
	 */
	private boolean genBaseDeliverNotice(Map deliverOrderMap,List basedtalList)throws Exception {
			HashMap params = new HashMap();
			String BASE_DELIVER_NOTICE_ID = StringUtil.uuid32len();
			params.put("BASE_DELIVER_NOTICE_ID",  BASE_DELIVER_NOTICE_ID);
			params.put("BASE_DELIVER_NOTICE_NO",   LogicUtil.getBmgz("ERP_BASE_DELIVER_NOTICE_NO"));
			params.put("FROM_BILL_ID",  deliverOrderMap.get("DELIVER_ORDER_ID"));
			params.put("FROM_BILL_NO",  deliverOrderMap.get("DELIVER_ORDER_NO"));
			params.put("DELIVER_TYPE",  deliverOrderMap.get("DELIVER_TYPE"));
			params.put("CHANN_TYPE",  deliverOrderMap.get("CHANN_TYPE"));
			params.put("PRVD_ID",  deliverOrderMap.get("PRVD_ID"));
			params.put("PRVD_NO",  deliverOrderMap.get("PRVD_NO"));
			params.put("PRVD_NAME",  deliverOrderMap.get("PRVD_NAME"));
			params.put("TRUCK_TYPE",  deliverOrderMap.get("TRUCK_TYPE"));
			params.put("RECV_CHANN_ID",  deliverOrderMap.get("RECV_CHANN_ID"));
			params.put("RECV_CHANN_NO",  deliverOrderMap.get("RECV_CHANN_NO"));
			params.put("RECV_CHANN_NAME",  deliverOrderMap.get("RECV_CHANN_NAME"));
			params.put("AREA_SER_ID",  deliverOrderMap.get("AREA_SER_ID"));
			params.put("AREA_SER_NO",  deliverOrderMap.get("AREA_SER_NO"));
			params.put("AREA_SER_NAME",  deliverOrderMap.get("AREA_SER_NAME"));
			params.put("ADVC_SEND_DATE",  deliverOrderMap.get("ADVC_SEND_DATE"));
			params.put("APPCH_TIME",  deliverOrderMap.get("APPCH_TIME"));
			params.put("TOTAL_VOLUME",  deliverOrderMap.get("TOTAL_VOLUME"));
			params.put("TRUCK_NO",  deliverOrderMap.get("TRUCK_NO"));
			params.put("STATE",  "已处理");
			params.put("REMARK",  deliverOrderMap.get("REMARK"));
			params.put("CREATOR",   deliverOrderMap.get("AREA_SER_ID"));
			params.put("CRE_NAME",  deliverOrderMap.get("AREA_SER_NAME"));
			params.put("DEPT_ID",   deliverOrderMap.get("AREA_SER_ID"));
			params.put("DEPT_NAME",   deliverOrderMap.get("AREA_SER_NAME"));
			params.put("ORG_ID",   deliverOrderMap.get("AREA_SER_ID"));
			params.put("ORG_NAME",   deliverOrderMap.get("AREA_SER_NAME"));
			params.put("LEDGER_ID",   deliverOrderMap.get("AREA_SER_ID"));
			params.put("DEL_FLAG",  0);
			ArrayList baseDeliverDtlList = new ArrayList();
			boolean isDeliverNotiecFlg =false;  //直发通知单FLAG
				for (int i = 0; i < basedtalList.size(); i++) {
					HashMap dtlMap = (HashMap) basedtalList.get(i);
					String ORDER_CHANN_NO = (String)dtlMap.get("ORDER_CHANN_NO");
					Map orderChannMap = getChannMap(ORDER_CHANN_NO);
					String orderChannType = (String)orderChannMap.get("CHANN_TYPE");
					if("区域服务中心".equals(orderChannType)){
						String RECV_CHANN_ID = (String)dtlMap.get("RECV_CHANN_ID");
						String PRD_ID = (String)dtlMap.get("PRD_ID");
//						Map saleparams = new HashMap();
//						saleparams.put("RECV_CHANN_ID",RECV_CHANN_ID);
//						saleparams.put("PRD_ID", PRD_ID);
//						saleparams.put("STATE", BusinessConsts.PASS);
//						saleparams.put("SPCL_TECH_ID", dtlMap.get("SPCL_TECH_ID"));
//						List drpSaleOrderList =this.findList("Senddirectnotice.queryDrpSaleOrderById", saleparams);
						BigDecimal REAL_SEND_NUM = (BigDecimal)dtlMap.get("REAL_SEND_NUM");
//						for(int j=0; j<drpSaleOrderList.size();j++){
//							Map drpSaleMap = (Map)drpSaleOrderList.get(j);
							HashMap baseDeliverMap = new HashMap();
							baseDeliverMap.put("BASE_DELIVER_NOTICE_DTL_ID",  StringUtil.uuid32len());
							baseDeliverMap.put("BASE_DELIVER_NOTICE_ID",BASE_DELIVER_NOTICE_ID);
							baseDeliverMap.put("FROM_BILL_DTL_ID", dtlMap.get("DELIVER_ORDER_DTL_ID"));
							baseDeliverMap.put("SALE_ORDER_ID",dtlMap.get("SALE_ORDER_ID"));
							baseDeliverMap.put("SALE_ORDER_DTL_ID",dtlMap.get("SALE_ORDER_DTL_ID"));
							baseDeliverMap.put("SALE_ORDER_NO",dtlMap.get("SALE_ORDER_NO"));
							baseDeliverMap.put("ORDER_CHANN_ID",dtlMap.get("ORDER_CHANN_ID"));
							baseDeliverMap.put("ORDER_CHANN_NO",dtlMap.get("ORDER_CHANN_NO"));
							baseDeliverMap.put("ORDER_CHANN_NAME",dtlMap.get("ORDER_CHANN_NAME"));
							baseDeliverMap.put("RECV_CHANN_ID",dtlMap.get("RECV_CHANN_ID"));
							baseDeliverMap.put("RECV_CHANN_NO",dtlMap.get("RECV_CHANN_NO"));
							baseDeliverMap.put("RECV_CHANN_NAME",dtlMap.get("RECV_CHANN_NAME"));
							baseDeliverMap.put("PRD_ID",dtlMap.get("PRD_ID"));
							baseDeliverMap.put("PRD_NO",dtlMap.get("PRD_NO"));
							baseDeliverMap.put("PRD_NAME",dtlMap.get("PRD_NAME"));
							baseDeliverMap.put("PRD_SPEC",dtlMap.get("PRD_SPEC"));
							baseDeliverMap.put("PRD_COLOR",dtlMap.get("PRD_COLOR"));
							baseDeliverMap.put("BRAND",dtlMap.get("BRAND"));
							baseDeliverMap.put("STD_UNIT",dtlMap.get("STD_UNIT"));
							baseDeliverMap.put("VOLUME",dtlMap.get("VOLUME"));
							baseDeliverMap.put("IS_NO_STAND_FLAG",dtlMap.get("IS_NO_STAND_FLAG"));
							baseDeliverMap.put("SPCL_TECH_ID",dtlMap.get("SPCL_TECH_ID"));
							baseDeliverMap.put("REMARK",dtlMap.get("REMARK"));
							baseDeliverMap.put("REAL_SEND_NUM",REAL_SEND_NUM);
							baseDeliverMap.put("DEL_FLAG",0);
							baseDeliverMap.put("RECV_ADDR_NO",dtlMap.get("RECV_ADDR_NO"));
							baseDeliverMap.put("RECV_ADDR",dtlMap.get("RECV_ADDR"));
							baseDeliverDtlList.add(baseDeliverMap);
//						}
					}
				}

				insert("Senddirectnotice.insertDeliverNotice", params);
				this.batch4Update("Senddirectnotice.insertDeliverNoticeDtl", baseDeliverDtlList);
				InterReturnMsg msg = new InterReturnMsg();
				genStoreInAndDstr(deliverOrderMap, basedtalList, msg);
		return true;
	}

	/**
	 * 产生入库通知单，如果是区域代发产生分发指令单
	 * 
	 * @param deliverOrderId
	 * @param deliverOrderDtlIds
	 * @throws Exception
	 */
	private boolean genStoreInAndDstr(Map deliverOrderMap,
			List childList, InterReturnMsg msg) throws Exception {
		try {
			String billType = (String)deliverOrderMap.get("BILL_TYPE");
			if("返修发运".equals(billType)){
				billType = "返修入库";
			}else{
				billType = "订货入库";
			}
			HashMap dtlResultMap = new HashMap();
			List newList = null;
			// 按照 明细里的 收货方 分类 同一个收货方的明细放在一起，有几个不同的收货方 就生成几张入库通知单
			for (int i = 0; i < childList.size(); i++) {
				HashMap child = (HashMap) childList.get(i);
				String RECV_CHANN_ID = (String) child.get("RECV_CHANN_ID");
				newList = (List) dtlResultMap.get(RECV_CHANN_ID);
				if (null == newList) {
					newList = new ArrayList();
				}
				newList.add(child);
				dtlResultMap.put(RECV_CHANN_ID, newList);
			}
			boolean flag = inserStoreinOrder(billType,dtlResultMap, msg);
			if (!flag) {
				return flag;
			}
			String CHANN_TYPE = (String) deliverOrderMap.get("CHANN_TYPE");
			if ("区域代发".equals(CHANN_TYPE)&&!"true".equals(Consts.IS_OLD_FLOW)) {
				flag = insertDstrOrder(deliverOrderMap, dtlResultMap, msg);
				if (!flag) {
					return flag;
				}
			}
		} catch (Exception ex) {
			logger.info(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("生成 分发指令单出错!");
			return false;
		}
		return true;
	}

	/**
	 * 生成 入库通知单
	 */
	public boolean inserStoreinOrder(String billType,HashMap resultMap, InterReturnMsg returnMsg) {
		try {
			Set<String> keySet = resultMap.keySet();
			for (String RECV_CHANN_ID : keySet) {
				List dtlList = (List) resultMap.get(RECV_CHANN_ID);
				dtlList = filterDtlList(dtlList);
				if(dtlList.size()==0){
					continue;
				}
				HashMap baseParams = new HashMap();
				baseParams.put("CHANN_ID", RECV_CHANN_ID);
				Map channlMap = (Map) load("chann.loadById", baseParams);
	 			String AREA_SER_ID = (String)channlMap.get("AREA_SER_ID");
	 			String AREA_SER_NO =  null;
	 			String AREA_SER_NAME =  null;
	 			if(AREA_SER_ID!=null){
		 			 AREA_SER_NO =  (String)channlMap.get("AREA_SER_NO");
		 			 AREA_SER_NAME =  (String)channlMap.get("AREA_SER_NAME");
	 			}
	 			
				
				HashMap dtlMap = (HashMap) dtlList.get(0);
				String STOREIN_NOTICE_ID = StringUtil.uuid32len();
				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf
						.append(
								"insert into DRP_STOREIN_NOTICE(STOREIN_NOTICE_ID,STOREIN_NOTICE_NO,")
						.append(
								"BILL_TYPE,FROM_BILL_ID,FROM_BILL_NO,ORDER_CHANN_ID,ORDER_CHANN_NO,")
						.append(
								"ORDER_CHANN_NAME,RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME,SEND_CHANN_ID,")
						.append(
								"SEND_CHANN_NO,SEND_CHANN_NAME,")
						.append(
								"CRE_NAME,CREATOR,CRE_TIME,DEPT_ID,DEPT_NAME,ORG_ID,ORG_NAME,")
						.append("LEDGER_ID,LEDGER_NAME,STATE,DEL_FLAG")
						.append(" ) select ")
						.append("'" + STOREIN_NOTICE_ID + "'")
						.append(",")
						.append(
								"'"
										+ LogicUtil
												.getBmgz("DRP_STOREIN_NOTICE_NO")
										+ "'")
						.append(",")
						.append("'"+billType+"'")
						.append(",")
						.append(
								"t.DELIVER_ORDER_ID,t.DELIVER_ORDER_NO,d.ORDER_CHANN_ID,")
						.append(
								"d.ORDER_CHANN_NO,d.ORDER_CHANN_NAME,d.RECV_CHANN_ID,")
						.append(
								"d.RECV_CHANN_NO,d.RECV_CHANN_NAME,");
					    if(AREA_SER_ID!=null){
					    	sqlBuf.append("'"+AREA_SER_ID+"','"+AREA_SER_NO+"','"+AREA_SER_NAME+"'");
					    }else{
					    	sqlBuf.append("t.SEND_CHANN_ID,t.SEND_CHANN_NO,t.SEND_CHANN_NAME");
					    }
						
						sqlBuf.append(",'U9',d.RECV_CHANN_ID,")
						.append("sysdate")
						.append(",")
						.append(
								"d.RECV_CHANN_ID,d.RECV_CHANN_NAME,d.RECV_CHANN_ID,")
						.append(
								"d.RECV_CHANN_NAME,d.RECV_CHANN_ID,d.RECV_CHANN_NAME,")
						.append("'未提交',")
						.append(BusinessConsts.DEL_FLAG_COMMON)
						.append(
								" from ERP_DELIVER_ORDER t left join ERP_DELIVER_ORDER_DTL  d ")
						.append("on t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
						.append(" where  d.DELIVER_ORDER_DTL_ID = '").append(
								(String) dtlMap.get("DELIVER_ORDER_DTL_ID"))
						.append("' and t.DELIVER_ORDER_ID = '").append(
								(String) dtlMap.get("DELIVER_ORDER_ID"))
						.append("'");

				Map params = new HashMap();
				params.put("InsSQL", sqlBuf.toString());
				boolean flg = InterUtil.txInscom(params);
				if (!flg) {
					returnMsg.setFLAG(BusinessConsts.FLASE);
					returnMsg.setMESSAGE("生成 入库通知单主表出错!");
					return false;
				}
				//add by zzb 2014-8-22 添加税率
				//不含税折后单价=round(DECT_PRICE/1+税率,2)
				//不含税折后金额=不含税折后单价*数量
				//税额=折后金额-不含税折后金额
				for (int i = 0; i < dtlList.size(); i++) {
					dtlMap = (HashMap) dtlList.get(i);
					StringBuffer dtlSqlBuf = new StringBuffer();
					dtlSqlBuf
							.append("insert into DRP_STOREIN_NOTICE_DTL(")
							.append(
									"STOREIN_NOTICE_DTL_ID,STOREIN_NOTICE_ID,STOREIN_STORE_ID,")
							.append(
									"STOREIN_STORE_NO,STOREIN_STORE_NAME,PRD_ID,PRD_NO,")
							.append(
									"PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,")
							.append(
									"DECT_RATE,DECT_PRICE,NOTICE_NUM,DECT_AMOUNT,")
							.append(
									"SALE_ORDER_ID,SALE_ORDER_NO,SALE_ORDER_DTL_ID,")
							.append("GOODS_ORDER_NO,SPCL_TECH_ID,DEL_FLAG,")
							.append("TAX_RATE,NO_TAX_DECT_PRICE,NO_TAX_DECT_AMOUNT ,TAX_AMOUNT ")
							.append(")select rawtohex(sys_guid()) as UUID")
							.append(",'")
							.append(STOREIN_NOTICE_ID)
							.append("',")
							.append("g.STORE_ID,g.STORE_NO,g.STORE_NAME,")
							.append("d.PRD_ID,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,")
							.append("d.PRD_COLOR,d.BRAND,d.STD_UNIT,s.PRICE,")
							.append("s.DECT_RATE,d.DECT_PRICE,d.REAL_SEND_NUM,")
							.append(
									"(d.REAL_SEND_NUM*d.DECT_PRICE),d.SALE_ORDER_ID,")
							.append(
									"d.SALE_ORDER_NO,d.SALE_ORDER_DTL_ID,d.GOODS_ORDER_NO,")
							.append("d.SPCL_TECH_ID,")
							.append(BusinessConsts.DEL_FLAG_COMMON)
							.append(",c.TAX_RATE,")
                            .append("round(d.DECT_PRICE/(1+c.TAX_RATE),2),")
                            .append("round( d.REAL_SEND_NUM *(d.DECT_PRICE/(1+c.TAX_RATE)),2),")
                            .append("round( (d.REAL_SEND_NUM * d.DECT_PRICE) - ( d.REAL_SEND_NUM *(d.DECT_PRICE/(1+c.TAX_RATE))),2)")
							.append(" from ERP_DELIVER_ORDER_DTL d ")
							.append(" left join BASE_CHANN c on d.ORDER_CHANN_ID=c.CHANN_ID")
							.append(
									" left join DRP_GOODS_ORDER g on g.GOODS_ORDER_NO = d.GOODS_ORDER_NO")
							.append(
									" left join ERP_SALE_ORDER_DTL s on s.SALE_ORDER_DTL_ID = d.SALE_ORDER_DTL_ID")
							.append(" where d.DELIVER_ORDER_DTL_ID = '")
							.append((String) dtlMap.get("DELIVER_ORDER_DTL_ID"))
							.append("'");

					Map dtlParams = new HashMap();
					dtlParams.put("InsSQL", dtlSqlBuf.toString());
					flg = InterUtil.txInscom(dtlParams);
					if (!flg) {
						returnMsg.setFLAG(BusinessConsts.FLASE);
						returnMsg.setMESSAGE("生成 入库通知单明细出错!");
						return false;
					}
				}
			}
		} catch (Exception ex) {
			logger.info(ex);
			returnMsg.setFLAG(BusinessConsts.FLASE);
			returnMsg.setMESSAGE("处理入库通知单出错!");
			return false;
		}

		return true;
	}
	
	/**
	 * 过滤掉所有入库数量为0的明细
	 */
	private List filterDtlList(List dtlList){
		ArrayList newDtlList = new ArrayList();
		for (int i = 0; i < dtlList.size(); i++) {
			HashMap dtlMap = (HashMap) dtlList.get(i);
			BigDecimal REAL_SEND_NUM = (BigDecimal)dtlMap.get("REAL_SEND_NUM");
			if(REAL_SEND_NUM!=null&&!"0".equals(REAL_SEND_NUM.toString())){
				newDtlList.add(dtlMap);
			}
			
		}
		return newDtlList;
		
	}
	
	

	/**
	 * 生成分发指令单
	 * 
	 */
	private boolean insertDstrOrder(Map deliverOrderMap, HashMap resultMap,
			InterReturnMsg returnMsg) {
		try {
			Set<String> keySet = resultMap.keySet();
			for (String ORDER_CHANN_ID : keySet) {
				List dtlList = (List) resultMap.get(ORDER_CHANN_ID);
				HashMap dtlMap = (HashMap) dtlList.get(0);
				String dstrOrderId = StringUtil.uuid32len();
				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf
						.append(" insert into ERP_DSTR_ORDER(")
						.append(
								"DSTR_ORDER_ID,DSTR_ORDER_NO,FROM_BILL_ID,FROM_BILL_NO,SEND_CHANN_ID,SEND_CHANN_NO,SEND_CHANN_NAME,")
						.append(
								"ORDER_CHANN_ID,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME,")
						.append(
								"PERSON_CON,TEL,RECV_ADDR,RECV_STORE_ID,RECV_STORE_NO,RECV_STORE_NAME,DSTR_CHANN_ID,")
						.append(
								"DSTR_CHANN_NO,DSTR_CHANN_NAME,CRE_NAME,CREATOR,CRE_TIME,LEDGER_ID,LEDGER_NAME,")
						.append("STATE,DEL_FLAG)select '")
						.append(dstrOrderId)
						.append("','")
						.append(LogicUtil.getBmgz("ERP_DSTR_ORDER_NO"))
						.append("',")
						.append(
								"t.DELIVER_ORDER_ID,t.DELIVER_ORDER_NO,t.SEND_CHANN_ID,t.SEND_CHANN_NO,")
						.append(
								"t.SEND_CHANN_NAME,d.ORDER_CHANN_ID,d.ORDER_CHANN_NO,d.ORDER_CHANN_NAME,'")
						.append((String) dtlMap.get("RECV_CHANN_ID"))
						.append("','")
						.append((String) dtlMap.get("RECV_CHANN_NO"))
						.append("','")
						.append((String) dtlMap.get("RECV_CHANN_NAME"))
						.append("',")
						.append(
								"g.PERSON_CON,g.TEL,g.RECV_ADDR,g.STORE_ID,g.STORE_NO,g.STORE_NAME,t.AREA_SER_ID,")
						.append("t.AREA_SER_NO,t.AREA_SER_NAME,")
						.append("'U9','U9',sysdate,'")
						.append((String) deliverOrderMap.get("AREA_SER_NO"))
						.append("','")
						.append((String) deliverOrderMap.get("AREA_SER_NAME"))
						.append("',")
						.append("'未接收','")
						.append(BusinessConsts.DEL_FLAG_COMMON)
						.append("'")
						.append(
								" FROM ERP_DELIVER_ORDER t left join ERP_DELIVER_ORDER_DTL d on t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
						.append(
								" left join DRP_GOODS_ORDER g on g.GOODS_ORDER_NO = d.GOODS_ORDER_NO")
						.append(" where d.DELIVER_ORDER_DTL_ID = '").append(
								(String) dtlMap.get("DELIVER_ORDER_DTL_ID"))
						.append("'");
				Map params = new HashMap();
				params.put("InsSQL", sqlBuf.toString());
				boolean flg = InterUtil.txInscom(params);
				if (!flg) {
					returnMsg.setFLAG(BusinessConsts.FLASE);
					returnMsg.setMESSAGE("生成 分发指令单主表出错!");
					return false;
				}
				for (int i = 0; i < dtlList.size(); i++) {
					dtlMap = (HashMap) dtlList.get(i);
					StringBuffer dtlSqlBuf = new StringBuffer();
					dtlSqlBuf
							.append(
									"insert into ERP_DSTR_ORDER_DTL(DSTR_ORDER_DTL_ID,")
							.append(
									"DSTR_ORDER_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,")
							.append(
									"STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM,DSTR_NUM,DECT_AMOUNT,")
							.append(
									"DEL_FLAG,REAL_DSTR_NUM,SALE_ORDER_ID,SALE_ORDER_NO,SALE_ORDER_DTL_ID,")
							.append("GOODS_ORDER_NO,SPCL_TECH_ID )select '")
							.append(StringUtil.uuid32len())
							.append("','")
							.append(dstrOrderId)
							.append("',")
							.append(
									"d.PRD_ID,p.PRD_NO,p.PRD_NAME,p.PRD_SPEC,p.PRD_COLOR,p.BRAND,")
							.append(
									"p.STD_UNIT,s.PRICE,s.DECT_RATE,d.DECT_PRICE,s.ORDER_NUM,d.REAL_SEND_NUM,")
							.append("(d.REAL_SEND_NUM*d.DECT_PRICE),'")
							.append(BusinessConsts.DEL_FLAG_COMMON)
							.append("',")
							.append(
									"d.REAL_SEND_NUM,d.SALE_ORDER_ID,d.SALE_ORDER_NO,d.SALE_ORDER_DTL_ID,")
							.append(
									"d.GOODS_ORDER_NO,d.SPCL_TECH_ID from ERP_DELIVER_ORDER_DTL d ")
							.append(
									"left join DRP_GOODS_ORDER g on g.GOODS_ORDER_NO = d.GOODS_ORDER_NO")
							.append(
									" left join ERP_SALE_ORDER_DTL s on s.SALE_ORDER_DTL_ID = d.SALE_ORDER_DTL_ID")
							.append(
									" left join BASE_PRODUCT p on p.PRD_ID = d.PRD_ID")
							.append(" where d.DELIVER_ORDER_DTL_ID = '")
							.append((String) dtlMap.get("DELIVER_ORDER_DTL_ID"))
							.append("'");

					Map dtlParams = new HashMap();
					dtlParams.put("InsSQL", dtlSqlBuf.toString());
					flg = InterUtil.txInscom(dtlParams);
					if (!flg) {
						returnMsg.setFLAG(BusinessConsts.FLASE);
						returnMsg.setMESSAGE("生成 分发指令单明细出错!");
						return false;
					}
				}
			}
		} catch (Exception ex) {
			logger.info(ex);
			returnMsg.setFLAG(BusinessConsts.FLASE);
			returnMsg.setMESSAGE("生成 分发指令单出错!");
			return false;
		}
		return true;

	}
	
	public String returnMsg(InterReturnMsg msg) {
		String jsonResult = new Gson().toJson(msg);
		return jsonResult;
	}

	/**
	 * 更新发运单主表相关信息
	 * 
	 * @param deliverList
	 * @return
	 * @throws Exception
	 */
	public boolean updateDeliverOrder(String deliverOderNo,String TRUCK_NO,String STOREOUT_TIME,boolean IS_SEND_FIN) throws Exception {
		String state = "部分发货";
		if(IS_SEND_FIN){
			state = "已发货";
		}
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" UPDATE ERP_DELIVER_ORDER t SET t.STATE = '").append(
				state).append("'");
				if(TRUCK_NO!=null){
					sqlBuf.append(",t.TRUCK_NO ='").append(TRUCK_NO).append("'");
				}
				sqlBuf.append(",t.STOREOUT_TIME = ").append("sysdate")
				.append(",t.UPDATOR='U9系统',t.UPD_NAME='U9系统',UPD_TIME=").append(
				BusinessConsts.UPDATE_TIME).append("").append(
				" WHERE t.DELIVER_ORDER_NO = '").append(deliverOderNo).append("'")
				.append(" and t.STATE in('部分发货', '已提交生产','已提交库房')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuf.toString());
		return InterUtil.txUpdcom(params);
	}
	
	public boolean checkDeliverDtlStat(String deliverOderNo){
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT count(d.DELIVER_ORDER_DTL_ID) IS_SEND_FIN")
	    .append("  FROM ERP_DELIVER_ORDER t")
		.append("   left join ERP_DELIVER_ORDER_DTL d")
		.append(" on t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append(" where d.DEL_FLAG = 0 and NVL(d.IS_SEND_FIN, 0) = 0")
		.append("  and t.DELIVER_ORDER_NO = '"+deliverOderNo+"'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map checkMap = InterUtil.selcom(map);
		BigDecimal IS_SEND_FIN = (BigDecimal)checkMap.get("IS_SEND_FIN");
		int allNum = IS_SEND_FIN.intValue();
		if(0==allNum){
			return true;
		}
		return false;
	}
	
	public boolean checkDeliverDtlStatById(String deliverOderId){
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT count(d.DELIVER_ORDER_DTL_ID) IS_SEND_FIN")
	    .append("  FROM ERP_DELIVER_ORDER t")
		.append("   left join ERP_DELIVER_ORDER_DTL d")
		.append(" on t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append(" where d.DEL_FLAG = 0 and NVL(d.IS_SEND_FIN, 0) = 0")
		.append("  and t.DELIVER_ORDER_ID = '"+deliverOderId+"'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map checkMap = InterUtil.selcom(map);
		BigDecimal IS_SEND_FIN = (BigDecimal)checkMap.get("IS_SEND_FIN");
		int allNum = IS_SEND_FIN.intValue();
		if(0==allNum){
			return true;
		}
		return false;
	}
	

	/**
	 * 更新发运单明细货品实出数量 测试用要删
	 * 
	 * @param pdtList
	 * @return
	 */
	public boolean updateDeliverOrderDtl(String dtlId, String realStoreNum)
			throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						" UPDATE ERP_DELIVER_ORDER_DTL d SET d.REAL_SEND_NUM = '")
				.append(realStoreNum).append("'").append(
						" WHERE d.DELIVER_ORDER_DTL_ID = '").append(dtlId)
				.append("'");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuf.toString());
		return InterUtil.txUpdcom(params);
	}
	

	/**
	 * 更新发运单明细货品实出数量
	 * 
	 * @param pdtList
	 * @return
	 */
	private boolean updateDeliverOrderDtl(HashMap dtlMap,String APPCODE) throws Exception {
		String dtlId = (String) dtlMap.get("DELIVER_ORDER_DTL_ID");
		String realStoreNum = (String) dtlMap.get("REAL_STOREOUT_NUM");
		StringBuffer sqlBuf = new StringBuffer();
		if("UA".equals(APPCODE)){  //更新
			if("0.000000000".equals(realStoreNum)){
				sqlBuf
				.append(
						" UPDATE ERP_DELIVER_ORDER_DTL d SET d.IS_SEND_FIN = 1,d.REAL_SEND_NUM = ")
				.append(realStoreNum).append(
						" ,d.NO_SEND_NUM =  (NVL(d.PLAN_NUM,0) - NVL("+realStoreNum+",0)) WHERE d.DELIVER_ORDER_DTL_ID = '").append(dtlId)
				.append("'");
				Map params = new HashMap();
				params.put("UpdSQL", sqlBuf.toString());
				InterUtil.txUpdcom(params);
			}else{
				sqlBuf
				.append(
						" UPDATE ERP_DELIVER_ORDER_DTL d SET d.UA_SEND_NUM = ")
				.append(realStoreNum).append(
						"  WHERE d.DELIVER_ORDER_DTL_ID = '").append(dtlId)
				.append("'");
				Map params = new HashMap();
				params.put("UpdSQL", sqlBuf.toString());
				InterUtil.txUpdcom(params);
				
				sqlBuf = new StringBuffer();
				sqlBuf.append(" UPDATE ERP_DELIVER_ORDER_DTL d set d.IS_SEND_FIN = 1 WHERE "+realStoreNum+" = NVL(d.REAL_SEND_NUM,0) and NVL(d.REAL_SEND_NUM,0)>0 and d.DELIVER_ORDER_DTL_ID = '"+dtlId+"'");
				params = new HashMap();
				params.put("UpdSQL", sqlBuf.toString());
				InterUtil.txUpdcom(params);
				
			}

		}else{   //叠加更新
			sqlBuf
			.append(
					" UPDATE ERP_DELIVER_ORDER_DTL d SET d.REAL_SEND_NUM = NVL(d.REAL_SEND_NUM,0)+")
			.append(realStoreNum).append(
					" ,d.NO_SEND_NUM =  (NVL(d.PLAN_NUM,0) -NVL(d.REAL_SEND_NUM,0)- NVL("+realStoreNum+",0)) WHERE d.DELIVER_ORDER_DTL_ID = '").append(dtlId)
			.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			InterUtil.txUpdcom(params);
			
			String UA_SEND_NUM = getUASendNum(dtlId);
			if(UA_SEND_NUM!=null){  //如果UA回传了参考实发数量,那么U9只要等于UA的数发数量就认为是已处理
				sqlBuf = new StringBuffer();
				sqlBuf.append(" UPDATE ERP_DELIVER_ORDER_DTL d set d.IS_SEND_FIN = 1 WHERE "+UA_SEND_NUM+" = NVL(d.REAL_SEND_NUM,0) and NVL(d.REAL_SEND_NUM,0)>0 and d.DELIVER_ORDER_DTL_ID = '"+dtlId+"'");
				params = new HashMap();
				params.put("UpdSQL", sqlBuf.toString());
				InterUtil.txUpdcom(params);
			}else{ 
				sqlBuf = new StringBuffer();
				sqlBuf.append(" UPDATE ERP_DELIVER_ORDER_DTL d set d.IS_SEND_FIN = 1 WHERE NVL(d.PLAN_NUM,0) = NVL(d.REAL_SEND_NUM,0) and NVL(d.REAL_SEND_NUM,0)>0 and d.DELIVER_ORDER_DTL_ID = '"+dtlId+"'");
				params = new HashMap();
				params.put("UpdSQL", sqlBuf.toString());
				InterUtil.txUpdcom(params);
			}
		}
		return true;
//		/***************************测试完下掉***********************/
//		sqlBuf
//		.append(
//				" UPDATE ERP_DELIVER_ORDER_DTL d SET d.IS_SEND_FIN = 1,d.REAL_SEND_NUM = NVL(d.REAL_SEND_NUM,0)+")
//		.append(realStoreNum).append(
//				" ,d.NO_SEND_NUM =  (NVL(d.PLAN_NUM,0) - NVL("+realStoreNum+",0)) WHERE d.DELIVER_ORDER_DTL_ID = '").append(dtlId)
//		.append("'");
//		Map params = new HashMap();
//		params.put("UpdSQL", sqlBuf.toString());
//		return txUpdcom(params);
//		/***************************测试完下掉***********************/
		
	}
	
	private String getUASendNum(String deliverOderId){
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT d.UA_SEND_NUM")
		.append(" from ERP_DELIVER_ORDER_DTL d")
		.append("  where d.deliver_order_dtl_id = '"+deliverOderId+"'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map checkMap = InterUtil.selcom(map);
		BigDecimal UA_SEND_NUM = (BigDecimal)checkMap.get("UA_SEND_NUM");
		if(UA_SEND_NUM==null){
			return null;
		}
		return UA_SEND_NUM.toString();
	}
	
	private boolean checkIsUpdateSum(String deliverOrderNo,HashMap dtlMap){
		String dtlId = (String) dtlMap.get("DELIVER_ORDER_DTL_ID");
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" SELECT count(t.DELIVER_ORDER_ID) CHECK_NUM ")
		.append(" from ERP_DELIVER_ORDER t")
		.append("  left join ERP_DELIVER_ORDER_DTL d")
		.append(" on t.deliver_order_id = d.deliver_order_id")
		.append("  where t.deliver_order_no = '").append(deliverOrderNo).append("'")
		.append("   and d.deliver_order_dtl_id = '").append(dtlId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map checkMap = InterUtil.selcom(params);
		BigDecimal CHECK_NUM = (BigDecimal)checkMap.get("CHECK_NUM");
		int allNum = CHECK_NUM.intValue();
		if(allNum>0){
			return true;
		}
		return false;
	}

	/**
	 * 更新ERP_STOREOUT_DTL表的货品信息
	 * 
	 * @param storeOutList
	 * @return
	 * @throws Exception
	 */
	private boolean instErpSN(String dtlId, String strPrdSns) throws Exception {
		strPrdSns = DecompressSnList(strPrdSns);
		boolean flg = true;
		String[] pdSnls = strPrdSns.split(",");
		for (int i = 0; i < pdSnls.length; i++) {
			HashMap params = new HashMap();
			String prdSn = pdSnls[i];
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							" INSERT INTO ERP_STOREOUT_DTL (STOREOUT_DTL_ID,DELIVER_ORDER_ID,DELIVER_ORDER_DTL_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,REAL_NUM,PRICE,DECT_RATE,DECT_PRICE,DECT_AMOUNT,YEAR_MONTH,SN) SELECT  ")
					.append(
							" sys_guid(),DELIVER_ORDER_ID,DELIVER_ORDER_DTL_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,REAL_SEND_NUM,0,0,DECT_PRICE,0, to_char(sysdate,'YYYY/MM'),'")
					.append(prdSn)
					.append(
							"' FROM ERP_DELIVER_ORDER_DTL d WHERE d.DELIVER_ORDER_DTL_ID = '")
					.append(dtlId).append("'");
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				return flg;
			}
		}
		return flg;
	}

	
	public static String DecompressSnList(String snList)
    {
        if (StringUtil.isEmpty(snList))
        {
            return "";
        }
        else
        {
            StringBuilder sb = new StringBuilder();
            String[] snArray = snList.split(";");
            for (int i = 0; i < snArray.length; i+=2)
            {
            	String prefix = snArray[i];  //前缀
            	String[] snTails = snArray[i+1].split(",");  //后缀清单
                for (String snt : snTails) {
                	   sb.append(prefix).append(snt).append(',');
				}
            }
            //移除尾巴的","。
            if (sb.length() > 0)
            {
            	sb.substring(0, sb.length()-1);
            }
            return sb.toString();
        }
    }

	/**
	 * 新增账套信息
	 * 
	 * @param jsonLedgerData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreateLedger(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("账套信息管理", "新增账套信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("账套信息管理", "新增账套信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String[] mustFld = { "LEDGER_NO", "LEDGER_NAME", "STATE" };
		try {

			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("账套信息管理", "新增账套信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String LEDGER_NO_P = (String) bodyMap.get("LEDGER_NO_P");
			String STATE = (String) bodyMap.get("STATE");
			String LEDGER_NO = (String) bodyMap.get("LEDGER_NO");
			if (!InterUtil.checkPrimarykey("ZTBH", LEDGER_NO, "T_SYS_ZTXX")) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("账套已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"insert into T_SYS_ZTXX(ZTXXID,ZTBH,ZTMC,SJZT,STATE,CREATER, CRETIME,DELFLAG,CRENAME)")
					.append("values('").append(LEDGER_NO).append("',").append(
							"'").append(LEDGER_NO).append("',").append("'")
					.append((String) bodyMap.get("LEDGER_NAME")).append("',");
			if (!StringUtil.isEmpty(LEDGER_NO_P)) {
				sqlBuf.append("'").append(LEDGER_NO_P).append("',");
			} else {
				sqlBuf.append("'',");
			}
			sqlBuf.append("'").append(InterUtil.coveState(STATE))
					.append("',").append("'ESB',").append("sysdate,").append(
							BusinessConsts.DEL_FLAG_COMMON).append(",").append(
							"'ESB')");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("账套信息管理", "新增账套信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_NULL_ERR, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增账套信息!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增账套信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("账套信息管理", "新增账套信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增账套信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改账套信息
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateLedger(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("账套信息管理", "修改账套信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}

		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("账套信息管理", "修改账套信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String[] mustFld = { "LEDGER_NO" };
		try {
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("账套信息管理", "修改账套信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_ZTXX set ");
			String LEDGER_NO = (String) bodyMap.get("LEDGER_NO");
			String LEDGER_NAME = (String) bodyMap.get("LEDGER_NAME");
			String LEDGER_NO_P = (String) bodyMap.get("LEDGER_NO_P");
			if (!StringUtil.isEmpty(LEDGER_NO_P)) {
				sqlBuf.append("SJZT = '").append(LEDGER_NO_P).append("',");
			}
			if (!StringUtil.isEmpty(LEDGER_NAME)) {
				sqlBuf.append("ZTMC = '").append(LEDGER_NAME).append("',");
			}
			String STATE = (String) bodyMap.get("STATE");
			if (STATE != null) {
				sqlBuf.append("STATE = '").append(
						InterUtil.coveState(STATE)).append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where ZTXXID = '").append(LEDGER_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("账套信息管理", "修改账套信息", "U9系统", "失败", "修改账套信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改账套信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改账套信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("账套信息管理", "修改账套信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改账套信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除账套信息
	 * 
	 * @param jsonLedgerData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteLedger(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("账套信息管理", "删除账套信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("账套信息管理", "删除账套信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String[] mustFld = { "LEDGER_NO" };
		try {
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("账套信息管理", "删除账套信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String LEDGER_NO = (String) bodyMap.get("LEDGER_NO");
			boolean flg = true;
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_ZTXX set DELFLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(" where ZTXXID = ")
					.append("'").append(LEDGER_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("账套信息管理", "删除账套信息", "U9系统", "失败", "删除账套信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除账套信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除账套信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("账套信息管理", "删除账套信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除账套信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}



	/**
	 * 新增品牌
	 * 
	 * @param jsonBrandData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreateBrand(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("品牌信息管理", "新增品牌信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("品牌信息管理", "新增品牌信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String[] mustFld = { "Code", "Name", "EnglishName", "UseStatus" };
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			String BRAND_NO = (String) bodyMap.get("Code");
			String BRAND_NAME = (String) bodyMap.get("Name");
			String BRAND_NAME_EN = (String) bodyMap.get("EnglishName");
			String MARK_TYPE = (String) bodyMap.get("MarketType");// 市场类别，数据库里无该字段
			String STATE = (String) bodyMap.get("UseStatus");
			if (!InterUtil.checkPrimarykey("BRAND_ID", BRAND_NO, "BASE_BRAND")) {
				LogicUtil.actLog("品牌信息管理", "新增品牌信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("品牌已经存在!");
				return msg;
			}
			sqlBuf
					.append(
							"insert into BASE_BRAND(BRAND_ID,BRAND,BRAND_EN,STATE,CREATOR,CRE_NAME,CRE_TIME,DEL_FLAG)")
					.append("values('").append(BRAND_NO).append("',").append(
							"'").append(BRAND_NAME).append("',").append("'")
					.append(BRAND_NAME_EN).append("',").append("'").append(
							InterUtil.coveState(STATE)).append("',")
					.append("'ESB',").append("'ESB',").append("sysdate,")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("品牌信息管理", "新增品牌信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增品牌信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增品牌信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("品牌信息管理", "新增品牌信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增品牌信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改品牌
	 * 
	 * @param jsonBrandData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txUpdateBrand(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("品牌信息管理", "修改品牌信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("品牌信息管理", "修改品牌信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String[] mustFld = { "BRAND_NO" };
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("品牌信息管理", "修改品牌信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String BRAND_NO = (String) bodyMap.get("Code");
			String BRAND_NAME = (String) bodyMap.get("Name");
			String BRAND_NAME_EN = (String) bodyMap.get("EnglishName");
			String MARK_TYPE = (String) bodyMap.get("MarketType");// 市场类别，数据库里无该字段
			String STATE = (String) bodyMap.get("UseStatus");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_BRAND set ");
			// if (!StringUtil.isEmpty(MARK_TYPE)) {
			// sqlBuf.append("MARK_TYPE = '").append(MARK_TYPE).append("',");
			// }
			sqlBuf.append("UPDATOR = 'ESB',UPD_NAME='ESB',UPD_TIME=sysdate,");
			if (!StringUtil.isEmpty(BRAND_NAME)) {
				sqlBuf.append("BRAND = '").append(BRAND_NAME).append("',");
			}
			if (!StringUtil.isEmpty(BRAND_NAME_EN)) {
				sqlBuf.append("BRAND_EN = '").append(BRAND_NAME_EN)
						.append("',");
			}
			if (STATE != null) {
				sqlBuf.append("STATE = '").append(
						InterUtil.coveState(STATE)).append("',");
			}
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where BRAND_ID = '").append(BRAND_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("品牌信息管理", "修改品牌信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改品牌信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改品牌信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("品牌信息管理", "修改品牌信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改品牌信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除品牌
	 * 
	 * @param jsonBrandData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteBrand(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("品牌信息管理", "删除品牌信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("品牌信息管理", "删除品牌信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String[] mustFld = { "BRAND_NO" };
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			boolean flg = true;
			String BRAND_ID = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_BRAND set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(" where BRAND_ID = ");
			sqlBuf.append("'");
			sqlBuf.append(BRAND_ID);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
		} catch (Exception ex) {
			String errorInfo = "删除品牌信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("品牌信息管理", "删除品牌信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除品牌信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增货品分类
	 * 
	 * @param jsonPdtMenuData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreatePdtMenu(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PRD_MENU_NO", "PRD_MENU_NAME", "BRAND_NO",
					"PAR_PRD_MENU_NO", "STATE" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String PRD_MENU_NO = (String) bodyMap.get("PRD_MENU_NO");
			String PRD_MENU_NAME = (String) bodyMap.get("PRD_MENU_NAME");
			String BRAND_NO = (String) bodyMap.get("BRAND_NO");
			String PAR_PRD_MENU_NO = InterUtil.paseNullString((String) bodyMap
					.get("PAR_PRD_MENU_NO"));
			String REMARK = InterUtil.paseNullString((String) bodyMap.get("REMARK"));
			String STATE = (String) bodyMap.get("STATE");
			if (!InterUtil.checkPrimarykey("PRD_ID", PRD_MENU_NO, "BASE_PRODUCT")) {
				LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败", "货品分类已经存在",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("货品分类已经存在");
				return msg;
			}
			Map brandMap = getBrand(BRAND_NO);
			if (brandMap == null) {
				String ErrorInfo = "未找到品牌编号为" + BRAND_NO + "的品牌信息";
				LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败", ErrorInfo,
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(ErrorInfo);
				return msg;
			}
			String BRAND = (String) brandMap.get("BRAND");
			// 验证上级货品分类是否在数据库可以查找到数据
			String PAR_PRD_ID = "";
			String PAR_PRD_NO = "";
			String PAR_PRD_NAME = "";
			if (!StringUtil.isEmpty(PAR_PRD_MENU_NO)) {
				Map PdtMenuMap = getParPrdId(PAR_PRD_MENU_NO);
				if (PdtMenuMap == null) {
					String errorInfo = "未找到货品分类编号为" + PAR_PRD_MENU_NO
							+ "的货品分类信息";
					LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
							errorInfo, "", "", "");
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE(errorInfo);
					return msg;
				}
				PAR_PRD_ID = (String) PdtMenuMap.get("PRD_ID");
				PAR_PRD_NO = (String) PdtMenuMap.get("PRD_NO");
				PAR_PRD_NAME = (String) PdtMenuMap.get("PAR_PRD_NAME");
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append("insert into BASE_PRODUCT(PRD_ID,PRD_NO,PRD_NAME,BRAND,PAR_PRD_ID,PAR_PRD_NO,PAR_PRD_NAME,STATE,REMARK,CREATOR,CRE_NAME,CRE_TIME,DEL_FLAG,FINAL_NODE_FLAG,PRD_CLASS_TYP)");
			sqlBuf.append("values('").append(StringUtil.uuid32len()).append(
					"',").append("'").append(PRD_MENU_NO).append("',").append(
					"'").append(PRD_MENU_NAME).append("',").append("'").append(
					BRAND).append("',").append("'").append(PAR_PRD_ID).append(
					"',").append("'").append(PAR_PRD_NO).append("',").append(
					"'").append(PAR_PRD_NAME).append("',").append("'").append(
					STATE).append("',").append("'").append(REMARK).append("',")
					.append("'U9系统',").append("'U9系统',").append("sysdate,")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(",").append(
							BusinessConsts.YJLBJ_FLAG_FALSE).append(",")
					.append(BusinessConsts.YJLBJ_FLAG_FALSE).append(")");

			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
						"新增货品分类信息出错!", "", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增货品分类信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增货品分类信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增货品分类信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	private Map getBrand(String BRAND_NO) throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		// 验证品牌编号和上级货品分类是否可以查到数据
		sqlBuf.append("select BRAND from BASE_BRAND where BRAND_ID='").append(
				BRAND_NO).append("'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map brandMap = InterUtil.selcom(map);
		return brandMap;
	}

	/**
	 * 修改货品分类信息
	 * 
	 * @param jsonPdtMenuData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txUpdatePdtMenu(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("货品分类管理", "修改货品分类信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("货品分类管理", "修改货品分类", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PRD_MENU_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("货品分类管理", "修改货品分类", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String PRD_NO = (String) bodyMap.get("PRD_MENU_NO");
			String PRD_NAME = (String) bodyMap.get("PRD_MENU_NAME");
			String BRAND_ID = (String) bodyMap.get("BRAND_NO");
			String PAR_PRD_NO = (String) bodyMap.get("PAR_PRD_MENU_NO");
			String REMARK = (String) bodyMap.get("REMARK");
			String STATE = (String) bodyMap.get("STATE");
			boolean flg = true;
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_PRODUCT set ");
			if (!StringUtil.isEmpty(PRD_NAME)) {
				sqlBuf.append("PRD_NAME = '").append(PRD_NAME).append("',");
			}
			if (!StringUtil.isEmpty(BRAND_ID)) {
				Map brandMap = getBrand(BRAND_ID);
				if (brandMap == null) {
					String ErrorInfo = "未找到品牌编号为" + BRAND_ID + "的品牌信息";
					LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
							ErrorInfo, APPCODE, APPID, OPRCODE);
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE(ErrorInfo);
					return msg;
				}
				String BRAND = (String) brandMap.get("BRAND");
				sqlBuf.append(",'").append(BRAND).append("',");
			}
			if (!StringUtil.isEmpty(PAR_PRD_NO)) {
				Map PdtMenuMap = getParPrdId(PAR_PRD_NO);
				if (PdtMenuMap == null) {
					String errorInfo = "未找到货品分类编号为" + PAR_PRD_NO + "的货品分类信息";
					LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
							errorInfo, "", "", "");
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE(errorInfo);
					return msg;
				}
				String PAR_PRD_ID = (String) PdtMenuMap.get("PRD_ID");
				String PAR_PRD_NAME = (String) PdtMenuMap.get("PRD_NAME");
				sqlBuf.append("PAR_PRD_ID = '").append(PAR_PRD_ID).append("',")
						.append("PAR_PRD_NO = '").append(PAR_PRD_NO).append(
								"',").append("PAR_PRD_NAME = '").append(
								PAR_PRD_NAME).append("',");
			}
			if (!StringUtil.isEmpty(REMARK)) {
				sqlBuf.append("REMARK = '").append(REMARK).append("',");
			}
			if (STATE != null) {
				sqlBuf.append("STATE = '").append(STATE).append("',");
			}
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where PRD_NO = '").append(PRD_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败",
						"修改货品分类信息出错!", "", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改货品分类信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改货品分类信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("货品分类管理", "新增货品分类", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改货品分类信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除货品分类
	 * 
	 * @param jsonPdtMenuData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeletePdtMenu(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("货品分类管理", "删除货品分类", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("货品分类管理", "删除货品分类", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PRD_MENU_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("货品分类管理", "删除货品分类", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String PRD_ID = (String) bodyMap.get("PRD_MENU_NO");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_PRODUCT set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(" where PRD_ID = ")
					.append("'").append(PRD_ID).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("货品分类管理", "删除货品分类", "U9系统", "失败",
						"删除品分类信息出错!", "", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除货品分类信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除货品分类信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("货品分类管理", "删除货品分类", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除货品分类信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增货品单位
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txCreateUnitMenu(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("货品单位管理", "新增货品单位", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("货品单位管理", "新增货品单位", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PRD_UNIT_NO", "PRD_UNIT_NAME", "STATE" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("货品单位管理", "新增货品单位", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String PRD_UNIT_NO = (String) bodyMap.get("PRD_UNIT_NO");
			String PRD_UNIT_NAME = (String) bodyMap.get("PRD_UNIT_NAME");
			String PRD_UNIT_NAME_EN = (String) bodyMap.get("PRD_UNIT_NAME_EN");
			String STATE = (String) bodyMap.get("STATE");
			String REMARK = (String) bodyMap.get("REMARK");
			if (!InterUtil.checkPrimarykey("MEAS_UNIT_NO", PRD_UNIT_NO, "BASE_UNIT")) {
				LogicUtil.actLog("货品单位管理", "新增货品单位", "U9系统", "失败", "货品单位已经存在!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("货品单位已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append("insert into BASE_UNIT(MEAS_UNIT_ID,MEAS_UNIT_NO,MEAS_UNIT_NAME,UNIT_NAME_EN,STATE,CREATOR,CRE_NAME,CRE_TIME,DEL_FLAG)");
			sqlBuf.append("values('").append(StringUtil.uuid32len()).append(
					"','").append(PRD_UNIT_NO).append("','").append(
					PRD_UNIT_NAME).append("','").append(
							InterUtil.paseNullString(PRD_UNIT_NAME_EN)).append("','").append(
									InterUtil.coveState(STATE)).append("',").append(
					"'U9系统','U9系统',sysdate,").append(
					BusinessConsts.DEL_FLAG_COMMON).append(")");

			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("货品单位管理", "新增货品单位", "U9系统", "失败", "货品单位已经存在!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增货品单位出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增货品单位出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("货品单位管理", "新增货品单位", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增货品单位出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改货品单位
	 * 
	 * @param jsonBrandData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txUpdateUnitMenu(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("货品单位管理", "修改货品单位", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("货品单位管理", "修改货品单位", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PRD_UNIT_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("货品单位管理", "修改货品单位", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String PRD_UNIT_NO = (String) bodyMap.get("PRD_UNIT_NO");
			String PRD_UNIT_NAME = (String) bodyMap.get("PRD_UNIT_NAME");
			String PRD_UNIT_NAME_EN = (String) bodyMap.get("PRD_UNIT_NAME_EN");
			String STATE = (String) bodyMap.get("STATE");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_UNIT set ");
			sqlBuf.append("UPDATOR = 'U9系统',UPD_NAME='U9系统',UPD_TIME=sysdate,");
			if (!StringUtil.isEmpty(PRD_UNIT_NAME)) {
				sqlBuf.append("MEAS_UNIT_NAME = '").append(PRD_UNIT_NAME)
						.append("',");
			}
			if (!StringUtil.isEmpty(PRD_UNIT_NAME_EN)) {
				sqlBuf.append("UNIT_NAME_EN = '").append(PRD_UNIT_NAME_EN)
						.append("',");
			}
			if (!StringUtil.isEmpty(STATE)) {
				sqlBuf.append("STATE = '").append(InterUtil.coveState(STATE))
						.append("',");
			}
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where MEAS_UNIT_NO = '").append(PRD_UNIT_NO)
					.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("货品单位管理", "修改货品单位", "U9系统", "失败", "修改货品单位出错!",
						"", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改货品单位出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改货品单位出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("货品单位管理", "修改货品单位", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改货品单位出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除货品单位
	 * 
	 * @param jsonPdtMenuData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteUnitMenu(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("货品单位管理", "删除货品单位", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("货品单位管理", "删除货品单位", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PRD_UNIT_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("货品单位管理", "删除货品单位", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String PRD_UNIT_NO = (String) bodyMap.get("PRD_UNIT_NO");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_UNIT set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(
					" where MEAS_UNIT_NO = ").append("'").append(PRD_UNIT_NO)
					.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("货品单位管理", "删除货品单位", "U9系统", "失败", "删除货品单位出错!",
						"", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除货品单位出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除货品单位出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("货品单位管理", "删除货品单位", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除货品单位出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增机构信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreateOrganization(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("机构信息管理", "新增机构信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("机构信息管理", "新增机构信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;	
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		
		try {
			String[] mustFld = { "Code", "Name"};
			
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("机构信息管理", "新增机构信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String ORG_NO = (String) bodyMap.get("Code");
			String ORG_NAME = (String) bodyMap.get("Name");
			String ORG_ABBR = (String) bodyMap.get("Abbreviation");
			String ORG_NO_P = (String) bodyMap.get("ParentCode");
			String LEGAL_REPRE = (String) bodyMap.get("LegalRepresentative");
			String TEL = (String) bodyMap.get("TelephoneNumber");
			String TAX = (String) bodyMap.get("FaxNumber");
			String URL_ADDR = (String) bodyMap.get("URL");
			String POST = (String) bodyMap.get("PostCode");
			String ORG_NAME_ENG = (String) bodyMap.get("EnglishName");
			String DTL_ADDR = (String) bodyMap.get("Address");
			String STATE = (String) bodyMap.get("UseStatus");
			if (!InterUtil.checkPrimarykey("JGXXID", ORG_NO, "T_SYS_JGXX")) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("机构已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							" insert into T_SYS_JGXX(JGXXID,DZXXID,ZTXXID,JGBH,JGMC,JGJC,FRDB,DH,CZ,ZYDZ,"
									+ "SJJG,JGZT,QTSM,XXDZ,YZBM,JGYWMC,JGYWJC,JGYWXXDZ,XH,CREATER,CRENAME,CRETIME,DELFLAG)")
					.append("values('")
					.append(InterUtil.paseNullString(ORG_NO))
					.append("',")
					.append("'")
					.append("")
					.append("',")
					// 地址信息ID DZXXID
					.append("'").append(ORG_NO).append("',").append("'")
					.append(ORG_NO).append("',").append("'").append(ORG_NAME)
					.append("',").append("'").append(InterUtil.paseNullString(ORG_ABBR))
					.append("',").append("'").append(
							InterUtil.paseNullString(LEGAL_REPRE)).append("',").append(
							"'").append(InterUtil.paseNullString(TEL)).append("',")
					.append("'").append(InterUtil.paseNullString(TAX)).append("',")
					.append("'")
					.append(InterUtil.paseNullString(URL_ADDR))
					.append("',")
					// 主页地址ZYDZ
					.append("'").append(InterUtil.paseNullString(ORG_NO_P)).append("',")
					.append("'").append(InterUtil.coveState(STATE))
					.append("',").append("'").append("")
					.append("',")
					// 其它说明 QTSM
					.append("'").append(InterUtil.paseNullString(DTL_ADDR)).append("',")
					.append("'").append(InterUtil.paseNullString(POST)).append("',")
					// 邮编 YZBM
					.append("'").append(InterUtil.paseNullString(ORG_NAME_ENG)).append(
							"',")
					// 机构英文名称 JGYWMC
					.append("'").append(InterUtil.paseNullString(ORG_NAME_ENG)).append(
							"',")
					.append("'").append("").append("',")
					// 序号 XH
					.append("'U9系统',").append("'U9系统',").append("sysdate,")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("机构信息管理", "新增机构信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_NULL_ERR, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增机构信息!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增机构出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("机构信息管理", "新增机构信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增机构出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改机构信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateOrganization(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("机构信息管理", "修改机构信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("机构信息管理", "修改机构信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String[] mustFld = { "Code"};
		try {
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("机构信息管理", "修改机构信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String ORG_NO = (String) bodyMap.get("Code");
			String ORG_NAME = (String) bodyMap.get("Name");
			String ORG_ABBR = (String) bodyMap.get("Abbreviation");
			String ORG_NO_P = (String) bodyMap.get("ParentCode");
			String LEGAL_REPRE = (String) bodyMap.get("LegalRepresentative");
			String TEL = (String) bodyMap.get("TelephoneNumber");
			String TAX = (String) bodyMap.get("FaxNumber");
			String URL_ADDR = (String) bodyMap.get("URL");
			String POST = (String) bodyMap.get("PostCode");
			String ORG_NAME_ENG = (String) bodyMap.get("EnglishName");
			String DTL_ADDR = (String) bodyMap.get("Address");
			String STATE = (String) bodyMap.get("UseStatus");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_JGXX set ");
			if (!StringUtil.isEmpty(ORG_NAME)) {
				sqlBuf.append("JGMC = '").append(ORG_NAME).append("',");
			}
			if (!StringUtil.isEmpty(ORG_ABBR)) {
				sqlBuf.append("JGJC = '").append(ORG_ABBR).append("',");
			}
			if (!StringUtil.isEmpty(ORG_NO_P)) {
				sqlBuf.append("SJJG = '").append(ORG_NO_P).append("',");
			}
			if (!StringUtil.isEmpty(LEGAL_REPRE)) {
				sqlBuf.append("FRDB = '").append(LEGAL_REPRE).append("',");
			}
			if (!StringUtil.isEmpty(TEL)) {
				sqlBuf.append("DH = '").append(TEL).append("',");
			}
			if (!StringUtil.isEmpty(TAX)) {
				sqlBuf.append("CZ = '").append(TAX).append("',");
			}

			if (!StringUtil.isEmpty(URL_ADDR)) {
				sqlBuf.append("ZYDZ = '").append(URL_ADDR).append("',");
			}
			if (!StringUtil.isEmpty(POST)) {
				sqlBuf.append("YZBM = '").append(POST).append("',");
			}
			if (!StringUtil.isEmpty(ORG_NAME_ENG)) {
				sqlBuf.append("JGYWMC = '").append(ORG_NAME_ENG).append("',");
				sqlBuf.append("JGYWJC = '").append(ORG_NAME_ENG).append("',");
			}

			if (!StringUtil.isEmpty(DTL_ADDR)) {
				sqlBuf.append("XXDZ = '").append(DTL_ADDR).append("',");
			}

			if (StringUtil.isEmpty(STATE)) {
				sqlBuf.append("STATE = '").append(
						InterUtil.coveState(STATE)).append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where JGXXID = '").append(ORG_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("机构信息管理", "修改机构信息", "U9系统", "失败", "修改机构信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改机构信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改机构信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("机构信息管理", "修改机构信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改机构信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除机构信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteOrganization(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("机构信息管理", "删除机构信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("机构信息管理", "删除机构信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("机构信息管理", "删除机构信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String ORG_NO = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_JGXX set ");
			sqlBuf.append(" DELFLAG=");
			sqlBuf.append(BusinessConsts.DEL_FLAG_DROP);
			sqlBuf.append(" where JGBH = ");
			sqlBuf.append("'");
			sqlBuf.append(ORG_NO);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("机构信息管理", "删除机构信息", "U9系统", "失败", "删除机构信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除机构信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除机构信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("机构信息管理", "删除机构信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除机构信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增货品
	 * 
	 * @param jsonProductData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreateProduct(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String strSql = "";
		try {
			String STATE = (String) bodyMap.get("UseStatus");
			String[] mustFld = { "Code", "Name",
					"BrandCode", "InventoryType",
					"ReferenceCost", "UseStatus",
					"Volume","PrvdPrice"};
			mustFld = StringUtil.deleteByIndex(mustFld,2);
			if("0".equals(STATE)){
				mustFld = StringUtil.deleteByIndex(mustFld,2);
				mustFld = StringUtil.deleteByIndex(mustFld,3);
			}
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
            
			String IS_NO_STAND_FLAG = "0";
			String PRD_NO = (String) bodyMap.get("Code");
			if(PRD_NO.indexOf("-")!=-1){
				IS_NO_STAND_FLAG = "1";
			}
			String PRD_NAME = (String) bodyMap.get("Name");
			String BrandCode = (String) bodyMap.get("BrandCode");
			String BRAND = getBrandName(BrandCode);
			if("".equals(BRAND)){
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品品牌在DM系统不存在!");
				return msg;
			}
			String PAR_PRD_ID ="";
			String PAR_PRD_NAME = "";
			String PAR_PRD_NO = (String) bodyMap.get("InventoryType");
			if(PAR_PRD_NO!=null&&PAR_PRD_NO.trim().length()>0){
				Map PdtMenuMap = getParPrdId(PAR_PRD_NO);
				if(PdtMenuMap==null){
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品分类在DM系统不存在!");
					return msg;
				}
				PAR_PRD_ID = InterUtil.paseNullString((String)PdtMenuMap.get("PRD_ID"));
				PAR_PRD_NAME = InterUtil.paseNullString((String)PdtMenuMap.get("PRD_NAME"));
			}else{
				PAR_PRD_NO = "";
			}
			
			String STD_UNIT_NO = InterUtil.paseNullString((String) bodyMap
					.get("STD_UNIT_NO"));
			String MEAS_UNIT_NO = InterUtil.paseNullString((String) bodyMap
					.get("MainUnit"));
			String MEAS_UNIT_NAME = "";
			if (!StringUtil.isEmpty(MEAS_UNIT_NO)) {
				Map untiMap = getBaseUnit(MEAS_UNIT_NO);
				if(untiMap!=null){
					MEAS_UNIT_NAME = (String)untiMap.get("MEAS_UNIT_NAME");
				}else{
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品标准单位在DM系统不存在!");
					return msg;
				}
			}
			String RATIO = InterUtil.paseNullString((String) bodyMap.get("RATIO"));
			String LENGTH = (String) bodyMap.get("Length");
			String WIDTH = (String) bodyMap.get("Width");
			String HEIGHT = (String) bodyMap.get("Height");
			String VOLUME = (String) bodyMap.get("Volume");
			String PRD_SPEC = (String) bodyMap.get("PRD_SPEC");
			String BAR_CODE = (String) bodyMap.get("BarCode");
			String FACT_PRICE = (String) bodyMap.get("ReferenceCost");
			String PRVD_PRICE = (String) bodyMap.get("PrvdPrice");
			String IsDiKu  =  (String) bodyMap.get("IsDiKu");
			String AUTO_BACKUP_FLG = "0";
			if("1".equals(IsDiKu)){
				AUTO_BACKUP_FLG = "1";
			}
			
			String RET_PRICE_MIN = InterUtil.paseNullString((String) bodyMap
					.get("RetailPrice"));
			String BEG_DATE = InterUtil.paseNullString((String) bodyMap.get("LaunchDate"));
			String REMARK = InterUtil.paseNullString((String) bodyMap.get("REMARK"));
			String PRD_COLOR = (String) bodyMap.get("PRD_COLOR");
			String LEDGER_NO = InterUtil.paseNullString((String) bodyMap.get("LEDGER_NO"));
			if (!InterUtil.checkPrimarykey("PRD_NO", PRD_NO, "BASE_PRODUCT")) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品已经存在!PRD_NO:"+PRD_NO);
				return msg;
			}
			String PRD_ID = StringUtil.uuid32len();
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("insert into BASE_PRODUCT(PRD_ID,PRD_NO,PRD_SUIT_FLAG,AUTO_BACKUP_FLG,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,MEAS_UNIT,RATIO,PAR_PRD_ID,PAR_PRD_NO,PAR_PRD_NAME,VOLUME,FACT_PRICE,PRVD_PRICE,RET_PRICE_MIN,STATE,REMARK,CREATOR,CRE_NAME,CRE_TIME,DEL_FLAG,FINAL_NODE_FLAG,COMM_FLAG,BEG_DATE,LEDGER_ID,ORG_ID,IS_NO_STAND_FLAG)");
			sqlBuf.append("values( '").append(PRD_ID).append(
					"',")
					.append("'").append(PRD_NO).append("',0,")
					.append(AUTO_BACKUP_FLG).append(",")
					.append("'").append(PRD_NAME).append("',");
					if(!StringUtil.isEmpty(PRD_SPEC)){
						sqlBuf.append("'").append(PRD_SPEC).append("',");
					}else{
						sqlBuf.append("'通用规格',");
					}
					if(!StringUtil.isEmpty(PRD_COLOR)){
						sqlBuf.append("'").append(PRD_COLOR).append("',");
					}else{
						sqlBuf.append("'',");
					}
					sqlBuf.append("'").append(BRAND).append("',")
					.append("'").append(MEAS_UNIT_NAME).append("',")
					.append("'").append(MEAS_UNIT_NAME).append("',")
					.append("'").append(RATIO).append("',")
					.append("'").append(PAR_PRD_ID).append("',")
					.append("'").append(PAR_PRD_NO).append("',")
					.append("'").append(PAR_PRD_NAME).append("',")
					.append("'").append(VOLUME).append("',")
					.append(FACT_PRICE).append(",")
					.append(PRVD_PRICE).append(",")
					.append("'").append(RET_PRICE_MIN).append("',")
					.append("'").append(InterUtil.coveState(STATE)).append("',")
					.append("'").append(REMARK).append("',")
					.append("'U9系统',").append("'U9系统',")
					.append("sysdate,")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(",")
					.append(BusinessConsts.YJLBJ_FLAG_TRUE).append(",")
					.append(BusinessConsts.DEL_FLAG_DROP).append(",")
					.append("to_date('").append(BEG_DATE).append("','yyyy-MM-DD')").append(",")
					.append("'00'").append(",")
					.append("'00'").append(",")
					.append(IS_NO_STAND_FLAG)
					.append(")");
			Map params = new HashMap();
			strSql = sqlBuf.toString();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"新增货品信息出错!SQL:"+sqlBuf.toString());
				return msg;
			}
			insertAreaDsct (PRD_ID, PRD_NO, PRD_NAME, PRD_SPEC, FACT_PRICE);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			String errorInfo = "新增货品信息出错!SQL:" +strSql+"==="+ InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("UId:"+APPID+"错误信息:"+errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}
	
	/**插入区域价格表(测试用，要删掉)
	 * @return
	 */
	private boolean insertAreaDsct(String PRD_ID,String PRD_NO,String PRD_NAME,String PRD_SPEC,String FACT_PRICE){
		StringBuffer selSqlBuf = new StringBuffer();
		selSqlBuf.append("select * from base_area a where a.del_flag = 0 and a.state ='启用'");
		Map map = new HashMap();
		map.put("SelSQL", selSqlBuf.toString());
		List baseChannList = InterUtil.selcomList(map);
		for(int i =0 ;i<baseChannList.size();i++){
			Map channMap = (Map)baseChannList.get(i);
			String AREA_ID = (String)channMap.get("AREA_ID");
			String AREA_NO = (String)channMap.get("AREA_NO");
			String AREA_NAME = (String)channMap.get("AREA_NAME");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("insert into BASE_AREA_DSCT_FLAT (AREA_DSCT_FLAT_ID,AREA_ID,AREA_NO," +
					"AREA_NAME,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC" +
					",BASE_PRICE,DECT_RATE,DECT_PRICE)" )
		   .append(" VALUES (")
		   .append("'").append(StringUtil.uuid32len()).append("',")
		   .append("'").append(AREA_ID).append("',")
		   .append("'").append(AREA_NO).append("',")
		   .append("'").append(AREA_NAME).append("',")
		   .append("'").append(PRD_ID).append("',")
		   .append("'").append(PRD_NO).append("',")
		   .append("'").append(PRD_NAME).append("',")
		   .append("'").append(PRD_SPEC).append("',")
		   .append("'").append(FACT_PRICE).append("',")
		   .append("1,")
		   .append("'").append(FACT_PRICE).append("'")
		   .append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			InterUtil.txInscom(params);
		}
		
		return true;
	}
	
	private String getBrandName(String BAR_CODE){
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						"  select BRAND from base_brand where BRAND_ID = '")
				.append(BAR_CODE).append("'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map BrandMap = InterUtil.selcom(map);
		if(BrandMap!=null){
			return (String)BrandMap.get("BRAND");
		}
		return "";
	}


	/**
	 * 修改货品
	 * 
	 * @param jsonProductData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txUpdateProduct(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}

		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			boolean flg = true;
			String PRD_NO = (String) bodyMap.get("Code");
			String PRD_NAME = (String) bodyMap.get("Name");
			String PRD_COLOR = (String) bodyMap.get("PRD_COLOR");
			String BrandCode = (String) bodyMap.get("BrandCode");
			String BRAND = getBrandName(BrandCode);
			if("".equals(BRAND)){
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品品牌在DM系统不存在!");
				return msg;
			}
			String PRD_MENU_NO = (String) bodyMap.get("InventoryType");
			String STD_UNIT_NO = (String) bodyMap.get("STD_UNIT_NO");
			
			String MEAS_UNIT_NO = InterUtil.paseNullString((String) bodyMap
					.get("MainUnit"));
			String MEAS_UNIT_NAME = "";
			if (!StringUtil.isEmpty(MEAS_UNIT_NO)) {
				Map untiMap = getBaseUnit(MEAS_UNIT_NO);
				if(untiMap!=null){
					MEAS_UNIT_NAME = (String)untiMap.get("MEAS_UNIT_NAME");
				}else{
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品标准单位在DM系统不存在!");
					return msg;
				}
			}
			String STD_UNIT_NAME = "";
			if (!StringUtil.isEmpty(STD_UNIT_NO)) {
				Map untiMap = getBaseUnit(STD_UNIT_NO);
				if(untiMap!=null){
					STD_UNIT_NAME = (String)untiMap.get("MEAS_UNIT_NAME");
				}else{
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE("UId:"+APPID+"错误信息:"+"货品标准单位在DM系统不存在!");
					return msg;
				}
			}

			String RATIO = (String) bodyMap.get("RATIO");
			String LENGTH = (String) bodyMap.get("Length");
			String WIDTH = (String) bodyMap.get("Width");
			String HEIGHT = (String) bodyMap.get("Height");
			String VOLUME = (String) bodyMap.get("Volume");
			String PRD_SPEC = (String) bodyMap.get("PRD_SPEC");
			String FACT_PRICE = (String) bodyMap.get("ReferenceCost");
			String PRVD_PRICE = (String) bodyMap.get("PrvdPrice");
			String RET_PRICE_MIN = (String) bodyMap.get("RetailPrice");
			String BEG_DATE = (String) bodyMap.get("LaunchDate");
			String REMARK = (String) bodyMap.get("REMARK");
			String STATE = (String) bodyMap.get("UseStatus");
			
			String IsDiKu  =  (String) bodyMap.get("IsDiKu");
			String AUTO_BACKUP_FLG = null;
			if("1".equals(IsDiKu)){
				AUTO_BACKUP_FLG = "1";
			}else if("2".equals(IsDiKu)){
				AUTO_BACKUP_FLG = "0";
			}
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_PRODUCT set ");
			if (!StringUtil.isEmpty(PRD_NAME)) {
				sqlBuf.append("PRD_NAME = '").append(PRD_NAME).append("',");
			}
			if (!StringUtil.isEmpty(AUTO_BACKUP_FLG)) {
				sqlBuf.append("AUTO_BACKUP_FLG = '").append(AUTO_BACKUP_FLG).append("',");
			}			
			if (!StringUtil.isEmpty(PRD_SPEC)) {
				sqlBuf.append("PRD_SPEC = '").append(PRD_SPEC).append("',");
			}
			if (!StringUtil.isEmpty(PRD_COLOR)) {
				sqlBuf.append("PRD_COLOR = '").append(PRD_COLOR).append("',");
			}
			if (!StringUtil.isEmpty(BRAND)) {
				sqlBuf.append("BRAND='").append(BRAND).append("',");
			}
			if (!StringUtil.isEmpty(STD_UNIT_NAME)) {
				sqlBuf.append("STD_UNIT = '").append(STD_UNIT_NAME).append("',");
			}
			if (!StringUtil.isEmpty(MEAS_UNIT_NAME)) {
				sqlBuf.append("MEAS_UNIT = '").append(MEAS_UNIT_NAME).append("',");
			}
			if (!StringUtil.isEmpty(RATIO)) {
				sqlBuf.append("RATIO = '").append(RATIO).append("',");
			}
			if (!StringUtil.isEmpty(PRD_MENU_NO)) {
				Map PdtMenuMap = getParPrdId(PRD_MENU_NO);
				if (PdtMenuMap == null) {
					String errorInfo = "UId:"+APPID+"  错误信息:"+" 未找到上级货品编号为" + PRD_MENU_NO + "的货品分类信息";
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE(errorInfo);
					return msg;
				}
				String PAR_PRD_ID = (String) PdtMenuMap.get("PRD_ID");
				String PAR_PRD_NO = (String) PdtMenuMap.get("PRD_NO");
				String PAR_PRD_NAME = (String) PdtMenuMap.get("PRD_NAME");
				sqlBuf.append("PAR_PRD_ID = '").append(PAR_PRD_ID).append("',")
						.append("PAR_PRD_NO = '").append(PAR_PRD_NO).append(
								"',").append("PAR_PRD_NAME = '").append(
								PAR_PRD_NAME).append("',");
			}

			if (!StringUtil.isEmpty(BEG_DATE)) {
				sqlBuf.append("BEG_DATE = to_date('").append(BEG_DATE).append(
						"','yyyy-MM-DD'),");
			}

			if (!StringUtil.isEmpty(VOLUME)) {
				sqlBuf.append("VOLUME = ").append(VOLUME).append(",");
			}
			if (!StringUtil.isEmpty(FACT_PRICE)) {
				sqlBuf.append("FACT_PRICE = '").append(FACT_PRICE).append("',");
			}
			if (!StringUtil.isEmpty(PRVD_PRICE)) {
				sqlBuf.append("PRVD_PRICE = '").append(PRVD_PRICE).append("',");
			}
			if (!StringUtil.isEmpty(RET_PRICE_MIN)) {
				sqlBuf.append("RET_PRICE_MIN = '").append(RET_PRICE_MIN)
						.append("',");
			}
			if (!StringUtil.isEmpty(REMARK)) {
				sqlBuf.append("REMARK = '").append(REMARK).append("',");
			}
			if (STATE != null&&STATE.trim().length()>0) {
				sqlBuf.append("STATE = '").append(
						InterUtil.coveState(STATE)).append("',");
			}
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where PRD_NO = '").append(PRD_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"  错误信息:"+"货品编号不存在!SQL:"+sqlBuf.toString());
				return msg;
			}
			if("1".equals(STATE)){
				Map pdtMap = getPdtMustFld(PRD_NO);
				if(!checkPdtMustFld(pdtMap,msg)){
					LogicUtil.actLog("货品管理", "修改货品", "U9系统", "失败",msg.getMESSAGE(), "", "", "");
					throw new ServiceException(msg.getMESSAGE());
				}
			}
		} catch (Exception ex) {
			String errorInfo = "UId:"+APPID+"  错误信息:"+"修改货品信息出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}
	
	private Map getPdtMustFld(String pdtNo){
		StringBuffer pdtBuf = new StringBuffer();
		pdtBuf.append("SELECT BRAND,PAR_PRD_ID,PAR_PRD_NO FROM BASE_PRODUCT WHERE PRD_NO = '")
				.append(pdtNo).append("'");
		Map params = new HashMap();
		params.put("SelSQL", pdtBuf.toString());
		return InterUtil.selcom(params);
	}
	
	
	private boolean checkPdtMustFld(Map pdtMap,InterReturnMsg msg){
		Iterator iter = pdtMap.entrySet().iterator(); 
		while(iter.hasNext()){ 
			Map.Entry entry = (Map.Entry) iter.next();
			String fldName = (String)entry.getKey();
			String fldValue = (String)entry.getValue();
			if (StringUtil.isEmpty(fldValue)) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(fldName+":必填字段为空");
				return false;
			}
		}
		return true;
	}
	

	/**
	 * 删除货品
	 * 
	 * @param jsonProductData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteProduct(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;

		try {
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			boolean flg = true;
			String PRD_NO = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_PRODUCT set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(" where PRD_NO = ")
					.append("'").append(PRD_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"  错误信息:"+"删除货品信息出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除货品信息出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("UId:"+APPID+"  错误信息:"+"删除货品信息出错!"+errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}


	/**
	 * 新增部门信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreateDepartMent(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("部门信息管理", "新增部门信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("部门信息管理", "新增部门信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "DEPT_NO", "DEPT_NAME", "DEPT_ABBR", "ORG_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("部门信息管理", "新增部门信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String DEPT_NO = (String) bodyMap.get("DEPT_NO");
			String DEPT_NAME = (String) bodyMap.get("DEPT_NAME");
			String ORG_NO = (String) bodyMap.get("ORG_NO");
			String REMARK = (String) bodyMap.get("REMARK");
			String DEPT_NO_P = (String) bodyMap.get("DEPT_NO_P");
			
			String DEPT_ABBR = (String) bodyMap.get("DEPT_ABBR");
			String TEL = (String) bodyMap.get("TEL");
			String TAX = (String) bodyMap.get("TAX");
			String EMAIL = (String) bodyMap.get("EMAIL");
			String URL_ADDR = (String) bodyMap.get("URL_ADDR");
			String POST = (String) bodyMap.get("POST");
			
			String STATE = (String) bodyMap.get("STATE");
			
			String DTL_ADDR = (String) bodyMap.get("DTL_ADDR");
			
			if (!InterUtil.checkPrimarykey("BMBH", DEPT_NO, "T_SYS_BMXX")) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("部门已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"insert into T_SYS_BMXX"
									+ "(BMXXID,BMBH,BMMC,BMJC,DH,CZ,DZYJ,ZYDZ,YZBM,SJBM,BMZT,QTSM,XXDZ,XH,JGXXID,ZTXXID,DELFLAG)")
					.append("values('").append(DEPT_NO).append("',")
					.append("'").append(InterUtil.paseNullString(DEPT_NO)).append("',")
					.append("'").append(InterUtil.paseNullString(DEPT_NAME)).append("',")
					.append("'").append(InterUtil.paseNullString(DEPT_ABBR)).append("',")
					.append("'").append(InterUtil.paseNullString(TEL)).append("',")
					.append("'")
					.append(InterUtil.paseNullString(TAX))
					.append("',")
					.append("'")
					.append(InterUtil.paseNullString(EMAIL))
					.append("',")
					.append("'")
					.append(InterUtil.paseNullString(URL_ADDR))
					.append("',")
					// 主页地址ZYDZ
					.append("'")
					.append(InterUtil.paseNullString(POST))
					.append("',")
					// 邮编 YZBM
					.append("'").append(InterUtil.paseNullString(DEPT_NO_P)).append("',")
					.append("'").append(InterUtil.coveState(STATE))
					.append("',").append("'")
					.append(InterUtil.paseNullString(REMARK))
					.append("',")
					// 其它说明 QTSM
					.append("'").append(InterUtil.paseNullString(DTL_ADDR)).append("',")
					.append("'").append("").append("',")
					// 序号 XH
					.append("'").append(InterUtil.paseNullString(ORG_NO)).append("',")
					.append("'").append("").append("',")// 帐套
					.append(BusinessConsts.DEL_FLAG_COMMON).append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("部门信息管理", "新增部门信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_NULL_ERR, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增部门信息!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增部门信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("部门信息管理", "新增部门信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增部门信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改部门信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateDepartMent(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("部门信息管理", "修改部门信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}

		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("部门信息管理", "修改部门信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		
		try {
			boolean flg = true;
			String[] mustFld = { "DEPT_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("部门信息管理", "修改部门信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String DEPT_NO = (String) bodyMap.get("DEPT_NO");
			String DEPT_NAME = (String) bodyMap.get("DEPT_NAME");
			String DEPT_ABBR = (String) bodyMap.get("DEPT_ABBR");
			String TEL = (String) bodyMap.get("TEL");
			String TAX = (String) bodyMap.get("TAX");
			String EMAIL = (String) bodyMap.get("EMAIL");
			String URL_ADDR = (String) bodyMap.get("URL_ADDR");
			String POST = (String) bodyMap.get("POST");
			String DEPT_NO_P = (String) bodyMap.get("DEPT_NO_P");
			String STATE = (String) bodyMap.get("STATE");
			String REMARK = (String) bodyMap.get("REMARK");
			String DTL_ADDR = (String) bodyMap.get("DTL_ADDR");
			String ORG_NO = (String) bodyMap.get("ORG_NO");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_BMXX set ");

			if (!StringUtil.isEmpty(DEPT_NAME)) {
				sqlBuf.append("BMMC = '").append(DEPT_NAME).append("',");
			}
			if (!StringUtil.isEmpty(DEPT_ABBR)) {
				sqlBuf.append("BMJC = '").append(DEPT_ABBR).append("',");
			}
			if (!StringUtil.isEmpty(DEPT_NO_P)) {
				sqlBuf.append("SJBM = '").append(DEPT_NO_P).append("',");
			}
			if (!StringUtil.isEmpty(ORG_NO)) {
				sqlBuf.append("JGXXID = '").append(ORG_NO).append("',");
			}
			if (!StringUtil.isEmpty(TEL)) {
				sqlBuf.append("DH = '").append(TEL).append("',");
			}
			if (!StringUtil.isEmpty(TAX)) {
				sqlBuf.append("CZ = '").append(TAX).append("',");
			}
			if (!StringUtil.isEmpty(EMAIL)) {
				sqlBuf.append("DZYJ = '").append(EMAIL).append("',");
			}
			if (!StringUtil.isEmpty(URL_ADDR)) {
				sqlBuf.append("ZYDZ = '").append(URL_ADDR).append("',");
			}
			if (!StringUtil.isEmpty(POST)) {
				sqlBuf.append("YZBM = '").append(POST).append("',");
			}
			if (!StringUtil.isEmpty(DTL_ADDR)) {
				sqlBuf.append("XXDZ = '").append(DTL_ADDR).append("',");
			}
			if (!StringUtil.isEmpty(REMARK)) {
				sqlBuf.append("QTSM = '").append(REMARK).append("',");
			}
			if (STATE != null) {
				sqlBuf.append("BMZT = '").append(InterUtil.coveState(STATE)).append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where BMXXID = '").append(DEPT_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("部门信息管理", "修改部门信息", "U9系统", "失败", "修改部门信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改部门信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改部门信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("部门信息管理", "修改部门信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改部门信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除部门信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteDepartMent(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("部门信息管理", "删除部门信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
			+ jsonData;
			LogicUtil.actLog("部门信息管理", "删除部门信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "DEPT_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("部门信息管理", "删除部门信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String BMXXID = (String) bodyMap.get("DEPT_NO");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_BMXX  set ");
			sqlBuf.append(" DELFLAG=");
			sqlBuf.append(BusinessConsts.DEL_FLAG_DROP);
			sqlBuf.append(" where BMXXID = ");
			sqlBuf.append("'");
			sqlBuf.append(BMXXID);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("部门信息管理", "删除部门信息", "U9系统", "失败", "删除部门信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除部门信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除部门信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("部门信息管理", "删除部门信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除部门信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增人员信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txCreateEmployee(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("人员信息管理", "新增人员信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap<String, String> bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			Map m = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("人员信息管理", "新增人员信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code", "Name", "Sex", "IDNumber",
					"PostName","OfficePhoneNumber","PhoneNumber","Email","PositionType","UseStatus" ,"OrganizationCode"};
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("人员信息管理", "新增人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String EMP_NO = (String) bodyMap.get("Code");
			String EMP_NAME = (String) bodyMap.get("Name");
			String SEX = (String) bodyMap.get("Sex");
			String ID_CARD = (String) bodyMap.get("IDNumber");
			String ZW = (String) bodyMap.get("PostName");
			String TEL = (String) bodyMap.get("OfficePhoneNumber");
			String MOBILE = (String) bodyMap.get("PhoneNumber");
			String EMAIL = (String) bodyMap.get("Email");
			String EMP_LEVEL = (String) bodyMap.get("PositionType");
			String ORG_NO = (String) bodyMap.get("OrganizationCode");
			String EMP_TYPE = (String) bodyMap.get("PositionType");
			String STATE = (String) bodyMap.get("UseStatus");
			if (!InterUtil.checkPrimarykey("RYBH", EMP_NO, "T_SYS_RYXX")) {
				String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
						+ jsonData;
				LogicUtil.actLog("人员信息管理", "新增人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("人员已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"insert into T_SYS_RYXX"
									+ "(RYXXID,RYBH,XM,XB,SFZH,ZW,GSDH,SJ,DZYJ,RYZT,RYJB,BMXXID,JGXXID,RYLB,DELFLAG)")
					.append("values('").append(EMP_NO).append("',").append("'")
					.append(EMP_NO).append("',").append("'").append(EMP_NAME)
					.append("',").append("'").append(SEX).append("',").append(
							"'").append(InterUtil.paseNullString(ID_CARD)).append("',")
					.append("'").append(InterUtil.paseNullString(ZW)).append("',")
					.append("'").append(InterUtil.paseNullString(TEL)).append("',")
					.append("'").append(InterUtil.paseNullString(MOBILE)).append("',")
					.append("'").append(InterUtil.paseNullString(EMAIL)).append("',")
					.append("'")
					.append(InterUtil.coveState(STATE)).append(
							"',").append("'").append(InterUtil.paseNullString(EMP_LEVEL))
					.append("',").append("'").append(ORG_NO).append("',")
					.append("'").append(ORG_NO).append("',").append("'")
					.append(InterUtil.paseNullString(EMP_TYPE)).append("',").append(
							BusinessConsts.DEL_FLAG_COMMON).append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
						+ jsonData;
				LogicUtil.actLog("人员信息管理", "新增人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增人员信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("人员信息管理", "新增员信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增人员信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改人员信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateEmployee(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("人员信息管理", "修改人员信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("人员信息管理", "修改人员信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "EMP_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("人员信息管理", "修改人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String EMP_NO = (String) bodyMap.get("Code");
			String EMP_NAME = (String) bodyMap.get("Name");
			String SEX = (String) bodyMap.get("Sex");
			String ID_CARD = (String) bodyMap.get("IDNumber");
			String ZW = (String) bodyMap.get("PostName");
			String TEL = (String) bodyMap.get("OfficePhoneNumber");
			String MOBILE = (String) bodyMap.get("PhoneNumber");
			String EMAIL = (String) bodyMap.get("Email");
			String EMP_LEVEL = (String) bodyMap.get("PositionType");
			String ORG_NO = (String) bodyMap.get("OrganizationCode");
			String EMP_TYPE = (String) bodyMap.get("PositionType");
			String STATE = (String) bodyMap.get("UseStatus");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_RYXX set ");
			if (!StringUtil.isEmpty(EMP_NAME)) {
				sqlBuf.append("XM = '").append(EMP_NAME).append("',");
			}
			
			if (!StringUtil.isEmpty(ORG_NO)) {
				sqlBuf.append("BMXXID = '").append(ORG_NO).append("',");
				sqlBuf.append("JGXXID = '").append(ORG_NO).append("',");
			}
			if (!StringUtil.isEmpty(SEX)) {
				sqlBuf.append("XB = '").append(SEX).append("',");
			}
			if (!StringUtil.isEmpty(EMP_LEVEL)) {
				sqlBuf.append("RYJB = '").append(EMP_LEVEL).append("',");
			}
			if (!StringUtil.isEmpty(ID_CARD)) {
				sqlBuf.append("SFZH = '").append(ID_CARD).append("',");
			}
			if (!StringUtil.isEmpty(ZW)) {
				sqlBuf.append("ZW = '").append(ZW).append("',");
			}
			if (!StringUtil.isEmpty(EMP_TYPE)) {
				sqlBuf.append("RYLB = '").append(EMP_TYPE).append("',");
			}
			if (!StringUtil.isEmpty(TEL)) {
				sqlBuf.append("GSDH = '").append(TEL).append("',");
			}
			if (!StringUtil.isEmpty(MOBILE)) {
				sqlBuf.append("SJ = '").append(MOBILE).append("',");
			}
			if (!StringUtil.isEmpty(EMAIL)) {
				sqlBuf.append("DZYJ = '").append(EMAIL).append("',");
			}

			if (!StringUtil.isEmpty(STATE)) {
				sqlBuf.append("RYZT = '").append(
						InterUtil.coveState(STATE)).append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);

			sqlBuf.append(" where RYXXID = '").append(EMP_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("人员信息管理", "修改人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改人员信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改人员信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("人员信息管理", "修改人员信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改人员信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除人员信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteEmployee(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("人员信息管理", "删除人员信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("人员信息管理", "删除人员信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("人员信息管理", "删除人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String EMP_NO = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_RYXX ");
			sqlBuf.append("set DELFLAG=");
			sqlBuf.append("'");
			sqlBuf.append(BusinessConsts.DEL_FLAG_DROP);
			sqlBuf.append("'");
			sqlBuf.append(" where RYXXID = ");
			sqlBuf.append("'");
			sqlBuf.append(EMP_NO);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("人员信息管理", "删除人员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除人员信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除人员信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("人员信息管理", "删除人员信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除人员信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增用户信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txCreateUserInfo(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("用户信息管理", "新增用户信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		boolean flg = true;
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("用户信息管理", "新增用户信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "ID", "OrganizationCode", "EmployeeCode"};
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("用户信息管理", "新增用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String XTYHID = (String) bodyMap.get("ID");
			String EMP_NO = (String) bodyMap.get("EmployeeCode");
			String ORG_NO = (String) bodyMap.get("OrganizationCode");
			String STATE = (String) bodyMap.get("STATE");
			StringBuffer ztBuf = new StringBuffer();
			ztBuf
					.append(
							"SELECT JGXXID,BMXXID,RYXXID,XM from T_SYS_RYXX t where  t.RYBH = '")
					.append(EMP_NO).append("'");
			Map params = new HashMap();
			params.put("SelSQL", ztBuf.toString());
			Map ztMap = InterUtil.selcom(params);
			String JGXXID = (String) ztMap.get("JGXXID");
			String BMXXID = (String) ztMap.get("BMXXID");
			String RYXXID = (String) ztMap.get("RYXXID");
			String XM = (String) ztMap.get("XM");
			if (!InterUtil.checkPrimarykey("YHBH", XTYHID, "T_SYS_XTYH")) {
				LogicUtil.actLog("用户信息管理", "新增用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("用户已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"INSERT INTO T_SYS_XTYH"
									+ "(XTYHID,ZTXXID,JGXXID,BMXXID,RYXXID,YHBH,YHM,YHZT,DELFLAG,IS_DRP_LEDGER)")
					.append("values('").append(XTYHID).append("',").append("'")
					.append(JGXXID).append("',").append("'").append(JGXXID)
					.append("',").append("'").append(BMXXID).append("',")
					.append("'").append(InterUtil.paseNullString(RYXXID)).append("',")
					.append("'").append(InterUtil.paseNullString(EMP_NO)).append("',")
					.append("'").append(InterUtil.paseNullString(XM)).append("',")
					.append("'")
					.append(InterUtil.coveState(STATE)).append(
							"',").append(
							BusinessConsts.DEL_FLAG_COMMON).append("0,0").append(")");
			params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("用户信息管理", "新增用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增人员信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增人员信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("用户信息管理", "新增用户信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增人员信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	private Map getEmployeeMap(String EMP_NO) {
		StringBuffer userBuf = new StringBuffer();
		userBuf.append("SELECT RYXXID,XM from T_SYS_RYXX where  RYBH = '")
				.append(EMP_NO).append("'");
		HashMap params = new HashMap();
		params.put("SelSQL", userBuf.toString());
		Map userMap = InterUtil.selcom(params);
		return userMap;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateUserInfo(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("用户信息管理", "修改用户信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("用户信息管理", "修改用户信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "ID" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("用户信息管理", "修改用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String XTYHID = (String) bodyMap.get("ID");
			String EMP_NO = (String) bodyMap.get("EmployeeCode");
			
			String STATE = (String) bodyMap.get("STATE");
			
			
			
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_XTYH set ");
			if (!StringUtil.isEmpty(EMP_NO)) {
				StringBuffer ztBuf = new StringBuffer();
				ztBuf
						.append(
								"SELECT JGXXID,BMXXID,RYXXID,XM from T_SYS_RYXX t where  t.RYBH = '")
						.append(EMP_NO).append("'");
				Map params = new HashMap();
				params.put("SelSQL", ztBuf.toString());
				Map ztMap = InterUtil.selcom(params);
				String JGXXID = (String) ztMap.get("JGXXID");
				String BMXXID = (String) ztMap.get("BMXXID");
				String XM = (String) ztMap.get("XM");
				String RYXXID = (String) ztMap.get("RYXXID");
				
				sqlBuf.append("YHBH = '").append(EMP_NO).append("',");
				sqlBuf.append("RYXXID = '").append(RYXXID).append("',");
				sqlBuf.append("YHM = '").append(XM).append("',");
			}
			if (!StringUtil.isEmpty(STATE)) {
				sqlBuf.append("YHZT = '").append(
						InterUtil.coveState(STATE)).append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);

			sqlBuf.append(" where XTYHID = '").append(XTYHID).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("用户信息管理", "修改用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改用户信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改用户信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("用户信息管理", "修改用户信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改用户信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除用户信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteUserInfo(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("用户信息管理", "删除用户信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
			headMap = jsonUtil.getMbHead();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("用户信息管理", "删除用户信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "ID" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("用户信息管理", "删除用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String XTYHID = (String) bodyMap.get("ID");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update T_SYS_XTYH ");
			sqlBuf.append("set DELFLAG=");
			sqlBuf.append("'");
			sqlBuf.append(BusinessConsts.DEL_FLAG_DROP);
			sqlBuf.append("'");
			sqlBuf.append(" where XTYHID = ");
			sqlBuf.append("'");
			sqlBuf.append(XTYHID);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("用户信息管理", "删除用户信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除用户信息出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除用户信息出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("用户信息管理", "删除用户信息", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除用户信息出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 验证货品传来的上级货品分类是否存在
	 * 
	 * @param PAR_PRD_MENU_NO
	 * @return
	 */
	public Map getParPrdId(String PAR_PRD_MENU_NO) throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						"select PRD_ID,PRD_NO,PRD_NAME from BASE_PRODUCT where PRD_NO='")
				.append(PAR_PRD_MENU_NO).append("'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map pdtMenuMap = InterUtil.selcom(map);
		return pdtMenuMap;
	}
	
	public Map getBaseUnit(String MEAS_UNIT_NO){
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						"select MEAS_UNIT_NO,MEAS_UNIT_NAME from BASE_UNIT where MEAS_UNIT_NO='")
				.append(MEAS_UNIT_NO).append("'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map untiMap = InterUtil.selcom(map);
		return untiMap;
		
	}

	/**
	 * 新增销售区域
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txCreateSaleArea(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("销售区域管理", "新增销售区域", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("销售区域管理", "新增销售区域", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "AREA_NO", "AREA_NAME", "STATE" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("销售区域管理", "新增销售区域", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			String AREA_ID = StringUtil.uuid32len();
			String AREA_NO = InterUtil.paseNullString((String) bodyMap.get("AREA_NO"));
			String AREA_NAME = InterUtil.paseNullString((String) bodyMap.get("AREA_NAME"));
			String AREA_NO_P = InterUtil.paseNullString((String) bodyMap.get("AREA_NO_P"));
			String AREA_NAME_P = InterUtil.paseNullString((String) bodyMap
					.get("AREA_NAME_P"));
			String STATE = InterUtil.paseNullString((String) bodyMap.get("STATE"));
			String AREA_ID_P = "";
			if (AREA_NO_P.length() > 0) {
				StringBuffer querySql = new StringBuffer();
				querySql.append(
						"SELECT AREA_ID FROM BASE_AREA WHERE AREA_NO = '")
						.append(AREA_NO_P).append("'");
				Map selParams = new HashMap();
				selParams.put("SelSQL", querySql.toString());
				Map AreaMap = InterUtil.selcom(selParams);
				AREA_ID_P = (String) AreaMap.get("AREA_ID");
			}

			if (!InterUtil.checkPrimarykey("AREA_NO", AREA_NO, "BASE_AREA")) {
				LogicUtil.actLog("销售区域管理", "新增销售区域", "U9系统", "失败", "销售区域已经存在!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("销售区域已经存在!");
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"insert into BASE_AREA"
									+ "(AREA_ID,AREA_NO,AREA_NAME,AREA_ID_P,AREA_NO_P,AREA_NAME_P,STATE,CREATOR,CRE_NAME,CRE_TIME,DEL_FLAG)")
					.append("values('").append(AREA_ID).append("',")
					.append("'").append(AREA_NO).append("',").append("'")
					.append(AREA_NAME).append("',").append("'").append(
							AREA_ID_P).append("',").append("'").append(
							AREA_NO_P).append("',").append("'").append(
							AREA_NAME_P).append("',").append("'").append(
									InterUtil.coveState(STATE)).append("',").append(
							"'U9系统',").append("'U9系统',").append("sysdate,")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				LogicUtil.actLog("销售区域管理", "新增销售区域", "U9系统", "失败", "新增销售区域出错",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增销售区域!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增销售区域出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("销售区域管理", "新增销售区域", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增销售区域出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改销售区域
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg trxUpdateSaleArea(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("销售区域管理", "修改销售区域", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("销售区域管理", "修改销售区域", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "AREA_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("销售区域管理", "修改销售区域", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String AREA_NO = (String) bodyMap.get("AREA_NO");
			String AREA_NAME = (String) bodyMap.get("AREA_NAME");
			String AREA_NO_P = (String) bodyMap.get("AREA_NO_P");
			String AREA_NAME_P = (String) bodyMap.get("AREA_NAME_P");
			String STATE = (String) bodyMap.get("STATE");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_AREA set ");
			if (!StringUtil.isEmpty(AREA_NAME)) {
				sqlBuf.append("AREA_NAME = '").append(AREA_NAME).append("',");
			}
			if (!StringUtil.isEmpty(AREA_NO_P)) {
				StringBuffer querySql = new StringBuffer();
				querySql.append(
						"SELECT AREA_ID FROM BASE_AREA WHERE AREA_NO = '")
						.append(AREA_NO_P).append("'");
				Map selParams = new HashMap();
				selParams.put("SelSQL", querySql.toString());
				Map AreaMap = InterUtil.selcom(selParams);
				String AREA_ID_P = (String) AreaMap.get("AREA_ID");
				sqlBuf.append("AREA_NO_P = '").append(AREA_NO_P).append("',")
						.append("AREA_NAME_P = '").append(AREA_NAME_P).append(
								"',").append("AREA_ID_P = '").append(AREA_ID_P)
						.append("',");
			}
			if (!StringUtil.isEmpty(STATE)) {
				sqlBuf.append("STATE = '").append(InterUtil.coveState(STATE))
						.append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			sqlBuf.append(" where AREA_NO = '").append(AREA_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("销售区域管理", "修改销售区域", "U9系统", "失败", "修改销售区域出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("修改销售区域出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改销售区域出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("销售区域管理", "修改销售区域", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("修改销售区域出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除销售区域信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txdeleteSaleArea(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("销售区域管理", "删除销售区域", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("销售区域管理", "删除销售区域", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_FORMAT_ERR);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "AREA_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				LogicUtil.actLog("销售区域管理", "删除销售区域", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, APPCODE, APPID,
						OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}

			boolean flg = true;
			String AREA_NO = (String) bodyMap.get("AREA_NO");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_AREA set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(" where AREA_NO = ");
			sqlBuf.append("'");
			sqlBuf.append(AREA_NO);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("销售区域管理", "删除销售区域", "U9系统", "失败", "删除销售区域出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("删除销售区域出错!");
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除销售区域出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("销售区域管理", "删除销售区域", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("删除销售区域出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增渠道信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txCreateBaseChann(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code", "Name", "MarketType","CHAA_LVL","SHIP_POINT_NO","SHIP_POINT_NAME"};
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}

			String CHANN_NO = InterUtil.paseNullString((String) bodyMap.get("Code"));
			String CHANN_NAME = InterUtil.paseNullString((String) bodyMap
					.get("Name"));
			String CHANN_ABBR = InterUtil.paseNullString((String) bodyMap
					.get("Abbreviation"));
			String CHANN_TYPE = InterUtil.paseNullString((String) bodyMap
					.get("MarketType"));
			CHANN_TYPE = convCHANN_TYPE(CHANN_TYPE);
			String CHAA_LVL = InterUtil.paseNullString((String) bodyMap.get("CHAA_LVL"));
			CHAA_LVL = convChannLevel(CHAA_LVL);
			String NATION = InterUtil.paseNullString((String) bodyMap.get("Nation"));
			String PROV = InterUtil.paseNullString((String) bodyMap.get("Province"));
			String CITY = InterUtil.paseNullString((String) bodyMap.get("City"));
			String COUNTY = InterUtil.paseNullString((String) bodyMap.get("District"));
			String PERSON_CON = InterUtil.paseNullString((String) bodyMap
					.get("ContactPerson"));
			String TEL = InterUtil.paseNullString((String) bodyMap.get("TelephoneNumber"));
			String POST = InterUtil.paseNullString((String) bodyMap.get("PostCode"));
			String ADDRESS = InterUtil.paseNullString((String) bodyMap.get("Address"));
			String AX_BURDEN = InterUtil.paseNullString((String) bodyMap.get("TaxRegistryNumber"));
			String BANK_NAME = InterUtil.paseNullString((String) bodyMap.get("AccountBank"));
			String BANK_ACCT = InterUtil.paseNullString((String) bodyMap.get("AccountNumber"));
			String SHIP_POINT_NO = InterUtil.paseNullString((String) bodyMap.get("SHIP_POINT_NO"));
			String SHIP_POINT_NAME = InterUtil.paseNullString((String) bodyMap.get("SHIP_POINT_NAME"));
			
			String STATE = InterUtil.paseNullString((String) bodyMap.get("UseStatus"));
			if("1".equals(STATE)){
				STATE = "0";
			}
			String JOIN_DATE = InterUtil.paseNullString((String) bodyMap.get("EnterDate"));
			if (!InterUtil.checkPrimarykey("CHANN_NO", CHANN_NO, "BASE_CHANN")) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"渠道已经存在!CHANN_NO:"+CHANN_NO);
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"insert into BASE_CHANN"
									+ "(CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,NATION,PROV,CITY,COUNTY,"
									+ "PERSON_CON,TEL,POST,ADDRESS,"
									+ "AX_BURDEN,"
									+ "BANK_NAME,BANK_ACCT,STATE,JOIN_DATE,DEL_FLAG,SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME,CREATOR,CRE_NAME,CRE_TIME,DEPT_ID,DEPT_NAME)")
					.append("values('").append(StringUtil.uuid32len()).append("',")
					.append("'").append(CHANN_NO).append("',")
					.append("'").append(CHANN_NAME).append("',")
					.append("'").append(CHANN_ABBR).append("',")
					.append("'").append(CHANN_TYPE).append("',")
					.append("'").append(CHAA_LVL).append("',")
					.append("'").append(NATION).append("',")
					.append("'").append(PROV).append("',")
					.append("'").append(CITY).append("',")
					.append("'").append(COUNTY).append("',")
					.append("'").append(PERSON_CON).append("',")
					.append("'").append(TEL).append("',")
					.append("'").append(POST).append("',")
					.append("'").append(ADDRESS).append("',")
					.append("'").append(AX_BURDEN).append("',")
					.append("'").append(BANK_NAME).append("',")
					.append("'").append(BANK_ACCT).append("',")
					.append("'").append(InterUtil.coveState(STATE)).append("',")
					.append("to_date(substr('" + JOIN_DATE + "',1,10),'yyyy-MM-dd')").append(",")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(",")
					.append("'").append(SHIP_POINT_NO).append("',")
					.append("'").append(SHIP_POINT_NO).append("',")
					.append("'").append(SHIP_POINT_NAME).append("',")
					.append("'").append("U9").append("',")
					.append("'").append("U9").append("',")
					.append("sysdate").append(",")
					.append("'").append("12").append("',")
					.append("'").append("信息中心")
					.append("')");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			//同时生成生产工厂
			flg = InterUtil.txInscom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"新增渠道信息出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "新增渠道信息出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}
	
	private String convChannLevel(String CHANN_TYPE){
		if("01".equals(CHANN_TYPE)){
			return "A";
		}else if("02".equals(CHANN_TYPE)){
			return "B";
		}else if("03".equals(CHANN_TYPE)){
			return "C";
		}
		return CHANN_TYPE;
	}
	
	private String convCHANN_TYPE(String CHANN_TYPE){
		if("1".equals(CHANN_TYPE)){
			return "加盟商";
		}else if("2".equals(CHANN_TYPE)){
			return "直营办"; 
		}else if("3".equals(CHANN_TYPE)){
			return "区域服务中心"; 
		}else{
			return CHANN_TYPE;
		}
	}
	
//	/**新增渠道时插入发货点
//	 * @param SHIP_POINT_NO
//	 * @param SHIP_POINT_NAME
//	 * @return
//	 * @throws Exception
//	 */
//	private boolean createShipPoint(String SHIP_POINT_NO,String SHIP_POINT_NAME)throws Exception{
//		StringBuffer instBuf = new StringBuffer();
//		instBuf.append("insert into BASE_SHIP_POINT(SHIP_POINT_ID,SHIP_POINT_NO,SHIP_POINT_NAME,STATE,CREATOR,CRE_NAME,CRE_TIME,CRE_TIME,DEPT_ID,DEPT_NAME,ORG_ID,ORG_NAME,LEDGER_ID,LEDGER_NAME,DEL_FLAG)")
//			   .append("values(")
//			   .append(StringUtil.uuid32len()).append("',")
//			   .append("'").append(SHIP_POINT_NO).append("',")
//			   .append("'").append(SHIP_POINT_NAME).append("',")
//			   .append("'").append("制作").append("',")
//			   .append("'").append("制作").append("',")
//		Map params = new HashMap();
//		params.put("InsSQL", instBuf.toString());
//		return txInscom(params);
//	}
//	
//	private boolean updateShipPoint(String SHIP_POINT_NO,String SHIP_POINT_NAME)throws Exception{
//		
//	}

	/**
	 * 修改渠道信息
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateBaseChann(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		Map<String,String> channMap =new HashMap<String, String>();
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}

			boolean flg = true;
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_CHANN set ");
			String CHANN_NO = (String) bodyMap.get("Code");
			Map ChannMap = getChannMapByNo(CHANN_NO);
			if(ChannMap==null){
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"客户编号不存在!CHANN_NO:"+CHANN_NO);
				return msg;
			}
			String strStats = (String)ChannMap.get("STATE");
			channMap.put("STATE", strStats);
			channMap.put("CHANN_ID", load("chann.getChannId", CHANN_NO).toString());
			String CHANN_NAME = (String) bodyMap.get("Name");
			if (!StringUtil.isEmpty(CHANN_NAME)) {
				sqlBuf.append("CHANN_NAME = '").append(CHANN_NAME).append("',");
				channMap.put("CHANN_NAME", CHANN_NAME);
			}
			String CHANN_ABBR = (String) bodyMap.get("Abbreviation");
			if (!StringUtil.isEmpty(CHANN_ABBR)) {
				sqlBuf.append("CHANN_ABBR = '").append(CHANN_ABBR).append("',");
			}
			String CHANN_TYPE = (String) bodyMap.get("MarketType");
			if (!StringUtil.isEmpty(CHANN_TYPE)) {
				CHANN_TYPE = convCHANN_TYPE(CHANN_TYPE);
				sqlBuf.append("CHANN_TYPE = '").append(CHANN_TYPE).append("',");
			}
			
			String CHAA_LVL = (String) bodyMap.get("CHAA_LVL");
			if (!StringUtil.isEmpty(CHAA_LVL)) {
				CHAA_LVL = convChannLevel(CHAA_LVL);
				sqlBuf.append("CHAA_LVL = '").append(CHAA_LVL).append("',");
			}		
			
			String NATION = (String) bodyMap.get("Nation");
			if (!StringUtil.isEmpty(NATION)) {
				sqlBuf.append("NATION = '").append(NATION).append("',");
			}
			String PROV = (String) bodyMap.get("Province");
			if (!StringUtil.isEmpty(PROV)) {
				sqlBuf.append("PROV = '").append(PROV).append("',");
			}
			String CITY = (String) bodyMap.get("City");
			if (!StringUtil.isEmpty(CITY)) {
				sqlBuf.append("CITY = '").append(CITY).append("',");
			}
			String COUNTY = (String) bodyMap.get("District");
			if (!StringUtil.isEmpty(COUNTY)) {
				sqlBuf.append("COUNTY = '").append(COUNTY).append("',");
			}
			String PERSON_CON = (String) bodyMap.get("ContactPerson");
			if (!StringUtil.isEmpty(PERSON_CON)) {
				sqlBuf.append("PERSON_CON = '").append(PERSON_CON).append("',");
			}
			String TEL = (String) bodyMap.get("TelephoneNumber");
			if (!StringUtil.isEmpty(TEL)) {
				sqlBuf.append("TEL = '").append(TEL).append("',");
			}
			String POST = (String) bodyMap.get("PostCode");
			if (!StringUtil.isEmpty(POST)) {
				sqlBuf.append("POST = '").append(POST).append("',");
			}
			String ADDRESS = (String) bodyMap.get("Address");
			if (!StringUtil.isEmpty(ADDRESS)) {
				sqlBuf.append("ADDRESS = '").append(ADDRESS).append("',");
			}

			String AX_BURDEN = (String) bodyMap.get("TaxRegistryNumbe");
			if (!StringUtil.isEmpty(AX_BURDEN)) {
				sqlBuf.append("AX_BURDEN = '").append(AX_BURDEN).append("',");
			}

			String BANK_NAME = (String) bodyMap.get("AccountBank");
			if (!StringUtil.isEmpty(BANK_NAME)) {
				sqlBuf.append("BANK_NAME = '").append(BANK_NAME).append("',");
			}
			String BANK_ACCT = (String) bodyMap.get("AccountNumber");
			if (!StringUtil.isEmpty(BANK_ACCT)) {
				sqlBuf.append("BANK_ACCT = '").append(BANK_ACCT).append("',");
			}

			String STATE = (String) bodyMap.get("UseStatus");
			if (STATE != null) {
				if("制定".equals(strStats)&&"1".equals(STATE)){
					STATE = "0";
				}
				sqlBuf.append("STATE = '").append(InterUtil.coveState(STATE))
						.append("',");
			}

			String JOIN_DATE = (String) bodyMap.get("EnterDate");
			if (JOIN_DATE != null) {
				sqlBuf.append("JOIN_DATE = ").append("to_date(substr('" + JOIN_DATE + "',1,10),'yyyy-MM-dd')").append(",");
			}
			String SHIP_POINT_NO = InterUtil.paseNullString((String) bodyMap.get("SHIP_POINT_NO"));
			if (SHIP_POINT_NO != null) {
				sqlBuf.append("SHIP_POINT_ID = '").append(SHIP_POINT_NO).append("',");
				sqlBuf.append("SHIP_POINT_NO = '").append(SHIP_POINT_NO).append("',");
				//修改发货点
			}
			String SHIP_POINT_NAME = InterUtil.paseNullString((String) bodyMap.get("SHIP_POINT_NAME"));
			if (SHIP_POINT_NAME != null) {
				sqlBuf.append("SHIP_POINT_NAME = '").append(SHIP_POINT_NAME).append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);

			sqlBuf.append(" where CHANN_NO = '").append(CHANN_NO).append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			System.out.println("***********************"+sqlBuf.toString());
			
			
			update("chann.upZTXX", channMap);
			update("chann.upJGXX", channMap);
			update("chann.upBMXX", channMap);
			update("chann.upTerminal",channMap);
			
			
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"修改渠道信息出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改渠道信息出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("UId:"+APPID+"错误信息:"+errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除渠道信息
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteBaseChann(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			boolean flg = true;
			String CHANN_NO = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_CHANN set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(" where CHANN_ID = ");
			sqlBuf.append("'");
			sqlBuf.append(CHANN_NO);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"删除渠道信息出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除渠道信息出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("UId:"+APPID+"错误信息:"+errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 新增送货地址
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txCreateDeliverAddr(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "Code", "CustomerCode",
					"Address", "UseStatus" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}

			String DELIVER_ADDR_NO = (String) bodyMap.get("Code");
			String CHANN_NO = (String) bodyMap.get("CustomerCode");
			String DELIVER_ADDR_DTL = (String) bodyMap.get("Address");
			String STATE = (String) bodyMap.get("UseStatus");
			if (!InterUtil.checkPrimarykey("DELIVER_ADDR_NO", DELIVER_ADDR_NO,
					"BASE_DELIVER_ADDR")) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"送货地址已经存在!DELIVER_ADDR_NO:"+DELIVER_ADDR_NO);
				return msg;
			}
			Map ChannMap = getChannMapByNo(CHANN_NO);
			if(ChannMap==null){
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"送货地址对应的客户编号不存在!CHANN_NO:"+CHANN_NO);
				return msg;
				
			}
			String chanId = (String)ChannMap.get("CHANN_ID");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"insert into BASE_DELIVER_ADDR"
									+ "(DELIVER_ADDR_ID,DELIVER_ADDR_NO,CHANN_ID,DELIVER_DTL_ADDR,STATE,DEL_FLAG)")
					.append("values('").append(DELIVER_ADDR_NO).append("',")
					.append("'").append(DELIVER_ADDR_NO).append("',").append(
							"'").append(chanId).append("',").append("'")
					.append(DELIVER_ADDR_DTL).append("',");
			sqlBuf.append("'").append(InterUtil.coveState(STATE)).append("',")
					.append(BusinessConsts.DEL_FLAG_COMMON).append(")");
			Map params = new HashMap();
			params.put("InsSQL", sqlBuf.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"插入数据库新增送货地址出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "UId:"+APPID+"错误信息:"+"新增送货地址出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 修改送货地址
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUpdateDeliverAddr(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap bodyMap = null;
		HashMap headMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			boolean flg = true;
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			String DELIVER_ADDR_NO = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_DELIVER_ADDR set ");
			String CHANN_NO = (String) bodyMap.get("CHANN_NO");
			if (!StringUtil.isEmpty(CHANN_NO)) {
				Map ChannMap = getChannMapByNo(CHANN_NO);
				if(ChannMap==null){
					msg.setFLAG(BusinessConsts.FLASE);
					msg.setMESSAGE("UId:"+APPID+"错误信息:"+"送货地址对应的客户编号不存在!CHANN_NO:"+CHANN_NO);
					return msg;
				}
				String chanId = (String)ChannMap.get("CHANN_ID");
				sqlBuf.append("CHANN_ID = '").append(chanId).append("',");
			}
			String DELIVER_ADDR_DTL = (String) bodyMap.get("Address");
			if (!StringUtil.isEmpty(DELIVER_ADDR_DTL)) {
				sqlBuf.append("DELIVER_DTL_ADDR = '").append(DELIVER_ADDR_DTL)
						.append("',");
			}

			String STATE = (String) bodyMap.get("UseStatus");
			if (STATE != null) {
				sqlBuf.append("STATE = '").append(InterUtil.coveState(STATE))
						.append("',");
			}
			// 去掉sql中可能出现的逗号
			sqlBuf = InterUtil.replaceUpSql(sqlBuf);

			sqlBuf.append(" where DELIVER_ADDR_ID = '").append(DELIVER_ADDR_NO)
					.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"修改送货地址出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "修改送货地址出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("UId:"+APPID+"错误信息:"+errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 删除送货地址
	 * 
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txDeleteDeliverAddr(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("送货地址管理", "删除送货地址", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("送货地址管理", "删除送货地址", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			boolean flg = true;
			String[] mustFld = { "Code" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			String DELIVER_ADDR_NO = (String) bodyMap.get("Code");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("update BASE_DELIVER_ADDR set DEL_FLAG= ").append(
					BusinessConsts.DEL_FLAG_DROP).append(
					" where DELIVER_ADDR_ID = ");
			sqlBuf.append("'");
			sqlBuf.append(DELIVER_ADDR_NO);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("UId:"+APPID+"错误信息:"+"删除送货地址出错!SQL:"+sqlBuf.toString());
				return msg;
			}
		} catch (Exception ex) {
			String errorInfo = "删除送货地址出错!" + InterUtil.getErrorInfo(ex);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("UId:"+APPID+"错误信息:"+errorInfo);
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}
	
	/**
	 * 客户付款单 推送过来后 发消息
	 * @param msg
	 * @param CHANN_ID
	 */
	public void payMentSendMessage(String msg,String CHANN_ID){
		try{
			String XTYHIDS = "";
			Map params = new HashMap();
			String sql = "select distinct c.USER_ID from BASE_USER_CHANN_CHRG c,T_SYS_XTYHJS js " +
					"where c.USER_ID=js.XTYHID " +
					"and c.CHANN_ID = '"+CHANN_ID+"' " +
					"and js.XTJSID = 'XTJS0002'";
			params.put("SelSQL", sql);
			List<Map<String,String>> list = InterUtil.selcomList(params);
			if (null != list) {
				for (Map<String, String> map : list) {
					XTYHIDS += "'"+map.get("USER_ID") + "',";
				}
			}
			if (!"".equals(XTYHIDS)) {
				XTYHIDS = XTYHIDS.substring(0, XTYHIDS.length() - 1);
			}
			
			Map<String, String> sendMap = new HashMap<String, String>();
			sendMap.put("YHBH", "U9");
			sendMap.put("SENDID", StringUtil.uuid32len());
			sendMap.put("SENDER", "U9");
			sendMap.put("MSGINFO", msg);
			sendMap.put("XTYHID", XTYHIDS);
			sendMap.put("BMXXID", "''");
			sendMap.put("JGXXID", "''");

			this.insert("MESSAGE.insertMessage", sendMap);
			
		}catch (Exception e) {
			logger.error("客户付款单发消息失败"+e.getMessage());
		}
	}

	/**
	 * 新增客户付款单
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txCreatePayMent(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(BusinessConsts.IMPL_PARAM_NULL_ERR);
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(errorInfo);
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PAYMENT_NO", "CHANN_NO", "PAY_AMONT",
					"PAY_DATE", "LEDGER_NO" ,"DocumentType","FundsDirect"};
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}

			boolean flg = true;
			String PAYMENT_ID = StringUtil.uuid32len();
			String PAYMENT_NO = (String) bodyMap.get("PAYMENT_NO");
			String CHANN_NO = (String) bodyMap.get("CHANN_NO");
			String PAY_AMONT = (String) bodyMap.get("PAY_AMONT");
			String PAY_DATE = (String) bodyMap.get("PAY_DATE");
			String REMARK = (String) bodyMap.get("REMARK");
			String LEDGER_NO = (String) bodyMap.get("LEDGER_NO");
			String DocumentType = (String) bodyMap.get("DocumentType");
			String FundsDirect = (String) bodyMap.get("FundsDirect");
			
			Map cheannMap = getChannMap(CHANN_NO);
			String CHANN_ID = InterUtil.paseNullString((String)cheannMap.get("CHANN_ID"));
			String CHANN_NAME = InterUtil.paseNullString((String)cheannMap.get("CHANN_NAME"));
			StringBuffer instBuffer = new StringBuffer();
			instBuffer
					.append(
							"INSERT INTO ERP_PAYMENT (PAYMENT_ID,PAYMENT_NO,"
									+ "CHANN_ID,CHANN_NO,CHANN_NAME,CHANN_ABBR,CHANN_TYPE,CHAA_LVL,"
									+ "AREA_NO,AREA_NAME,PAY_AMONT,PAY_DATE,STATE,REMARK,DEL_FLAG,LEDGER_ID,CREATOR,CRE_NAME,CRE_TIME,BILL_TYPE,BRRW_DRCT)VALUES(")
					.append("'").append(PAYMENT_ID).append("',").append("'")
					.append(PAYMENT_NO).append("',").append("'").append(
							InterUtil.paseNullString((String)cheannMap.get("CHANN_ID"))).append("',").append("'")
					.append(InterUtil.paseNullString((String)cheannMap.get("CHANN_NO"))).append("',").append("'")
					.append(InterUtil.paseNullString((String)cheannMap.get("CHANN_NAME"))).append("',").append(
							"'").append(InterUtil.paseNullString((String)cheannMap.get("CHANN_ABBR"))).append(
							"',").append("'").append(
									InterUtil.paseNullString((String)cheannMap.get("CHANN_TYPE"))).append("',").append(
							"'").append(InterUtil.paseNullString((String)cheannMap.get("CHAA_LVL"))).append("',")
					.append("'").append(InterUtil.paseNullString((String)cheannMap.get("AREA_NO"))).append("',")
					.append("'").append(InterUtil.paseNullString((String)cheannMap.get("AREA_NAME")))
					.append("',").append(PAY_AMONT).append(",").append(
							"to_date(substr('" + PAY_DATE + "',1,10),'yyyy-MM-dd')").append(
							",").append("'").append("已记账").append("',").append(
							"'").append(REMARK).append("',").append(0).append(
							",'").append(LEDGER_NO).append("'").append(
							",'U9系统','U9系统'").append(",sysdate,")
					.append("'").append(DocumentType).append("',")
					.append("'").append(FundsDirect).append("'")
							.append(")");
			Map params = new HashMap();
			params.put("InsSQL", instBuffer.toString());
			flg = InterUtil.txInscom(params);
			if (!flg) {
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增客户付款单出错!SQL:"+instBuffer.toString());
				return msg;
			}
			Float payment  = Float.valueOf(PAY_AMONT);
			if("1".equals(FundsDirect)){
				payment = -1*Math.abs(payment);
				
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"UPDATE BASE_CHANN set CREDIT_CURRT = NVL(CREDIT_CURRT ,0) + ")
					.append(payment).append(" where CHANN_ID = ");
			sqlBuf.append("'");
			sqlBuf.append(CHANN_ID);
			sqlBuf.append("'");
			params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("信用管理", "新增客户付款单", "U9系统", "失败", "新增信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("新增信息出错!");
				return msg;
			}
			flg = insertJOURNAL_CREDIT(PAYMENT_ID,DocumentType,FundsDirect);
			if (!flg) {
				LogicUtil.actLog("信用管理", "新增客户付款单", "U9系统", "失败", "记信用流水账出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("记信用流水账出错!");
				return msg;
			}
			
			//add by zzb 2014-8-20 发消息
			String sendMsg = "您辖属加盟商 ["+CHANN_NAME+"]["+CHANN_NO+"],有单号为 ["+PAYMENT_NO+"] 的客户"+DocumentType+"已记账，记账金额："+PAY_AMONT;
			payMentSendMessage(sendMsg,CHANN_ID);
			
		} catch (Exception ex) {
			String errorInfo = "新增客户付款单出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("信用管理", "新增客户付款单", "U9系统", "失败", errorInfo,
					APPCODE, APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("新增客户付款单出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}
	
	

	/**
	 * 弃审付款单
	 * 
	 * @param jsonData
	 * @return
	 */
	public InterReturnMsg txUnAuditPayment(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败", errorInfo, "", "",
					"");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
		String APPCODE = (String) headMap.get("AppCode");
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		try {
			String[] mustFld = { "PAYMENT_NO", "LEDGER_NO" };
			if (!InterUtil.checkMustFld(mustFld, bodyMap, msg)) {
				return msg;
			}
			boolean flg = true;
			String PAYMENT_NO = (String) bodyMap.get("PAYMENT_NO");
			if (!checkExsitPrimarykey("PAYMENT_NO", PAYMENT_NO, "ERP_PAYMENT")) {
				LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败", "付款单不存在!"+jsonData,
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("付款单不存在!");
				return msg;
			}
			Map payMentMap = getPayMentMap(PAYMENT_NO);
			String PAYMENT_ID = (String)payMentMap.get("PAYMENT_ID");
			BigDecimal BigPAY_AMONT = (BigDecimal) payMentMap.get("PAY_AMONT");
			String PAY_AMONT = BigPAY_AMONT.toString();
			String CHANN_ID = (String) payMentMap.get("CHANN_ID");
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							"UPDATE BASE_CHANN set CREDIT_CURRT = NVL(CREDIT_CURRT ,0) - ")
					.append(PAY_AMONT).append(" where CHANN_ID = ");
			sqlBuf.append("'");
			sqlBuf.append(CHANN_ID);
			sqlBuf.append("'");
			Map params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败", "扣减信息出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("扣减信息出错!");
				return msg;
			}
			
			sqlBuf = new StringBuffer();
			sqlBuf.append("UPDATE ERP_PAYMENT set STATE = '弃审',DEL_FLAG = 1 ")
					.append(" where PAYMENT_NO = ");
			sqlBuf.append("'");
			sqlBuf.append(PAYMENT_NO);
			sqlBuf.append("'");
			params = new HashMap();
			params.put("UpdSQL", sqlBuf.toString());
			flg = InterUtil.txUpdcom(params);
			if (!flg) {
				LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败", "更新付款单出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("更新付款单出错!");
				return msg;
			}
			flg = insertJOURNAL_CREDIT(PAYMENT_ID,"弃审付款单","1");
			if (!flg) {
				LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败", "记信用流水账出错!",
						APPCODE, APPID, OPRCODE);
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE("记信用流水账出错!");
				return msg;
			}
			
			String CHANN_NO = (String) payMentMap.get("CHANN_NO");
			String CHANN_NAME = (String) payMentMap.get("CHANN_NAME");
			
			//add by zzb 2014-8-20 发消息
			String sendMsg = "您辖属加盟商 ["+CHANN_NAME+"]["+CHANN_NO+"],有单号为 ["+PAYMENT_NO+"] 的客户付款单已弃审，弃审金额："+PAY_AMONT;
			payMentSendMessage(sendMsg,CHANN_ID);
			
		} catch (Exception ex) {
			String errorInfo = "弃审付款单出错!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("信用管理", "弃审付款单", "U9系统", "失败", errorInfo, APPCODE,
					APPID, OPRCODE);
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("弃审付款单出错!");
			return msg;
		}
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	
	
	/**
	 * 交付计划生成销售订单
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String createSO(String BusinessId,String Is_Abort_Flag,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE)throws Exception {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", ServiceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf
				.append(
						"SELECT BILL_TYPE,STATE,RECV_ADDR_NO as AddressCode,to_char(t.CRE_TIME,'yyyy-mm-dd') as BusinessDate,t.DELIVER_ORDER_NO as PlanDocNo,'01' as DocType,(select SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode,t.REMARK as Memo,to_char(t.ADVC_SEND_DATE,'yyyy-mm-dd') as RequireDate FROM ERP_DELIVER_ORDER t WHERE t.DELIVER_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = InterUtil.selcom(params);
		String ADDRESSCODE = (String)deliverMap.get("ADDRESSCODE");
		if(ADDRESSCODE==null||ADDRESSCODE.trim().length()==0){
			throw new ServiceException("主表收货地址为空,不能提交!");
		}
		String BILL_TYPE = (String)deliverMap.get("BILL_TYPE");
		if("返修发运".equals(BILL_TYPE)){
			deliverMap.put("Is_Maintenance_Flag", "1");
		}else{
			deliverMap.put("Is_Maintenance_Flag", "0");
		}
		StringBuffer deliverDtlBuf = new StringBuffer();
		// 工艺单特殊工艺的说明，要把订单工艺所有记录并装，加上备注信息
		deliverDtlBuf.append("SELECT PRD_SPEC as Spec,NEW_COLOR as FlowerNo, ORDER_CHANN_NO as CustomerCode,PRD_NO as ItemMaster,SPCL_TECH_ID as SpeTech_No,'' as SpeTech_Description, DELIVER_ORDER_DTL_ID as PlanDocLineID,PLAN_NUM as Qty,DECT_PRICE as Price ,REMARK as Memo , IS_BACKUP_FLAG,NVL(IS_FREE_FLAG,0)IS_FREE_FLAG FROM ERP_DELIVER_ORDER_DTL WHERE DELIVER_ORDER_ID = '");
		if(Is_Abort_Flag!=null){
			deliverDtlBuf.append(BusinessId).append("'");
		}else{
			deliverDtlBuf.append(BusinessId).append("' and DEL_FLAG = 0 ");
		}
		
		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List deliverDtlList = InterUtil.selcomList(params);
		setSOBodyMap(Is_Abort_Flag,bodyMap,deliverMap,deliverDtlList);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	
	
	private void setSOBodyMap(String Is_Abort_Flag,LinkedHashMap bodyMap,Map deliverMap,List deliverDtlList)throws Exception{
		HashMap mainMap = new HashMap();
		ArrayList dtlList = new ArrayList();
		mainMap.put("BusinessDate", deliverMap.get("BUSINESSDATE"));
		mainMap.put("PlanDocNo", deliverMap.get("PLANDOCNO"));
		mainMap.put("DocType", deliverMap.get("DOCTYPE"));
		mainMap.put("OrgCode", deliverMap.get("ORGCODE"));
		mainMap.put("Memo", deliverMap.get("MEMO"));
		mainMap.put("RequireDate", deliverMap.get("REQUIREDATE"));
		mainMap.put("Is_Maintenance_Flag", deliverMap.get("Is_Maintenance_Flag"));
		if(Is_Abort_Flag!=null){
			mainMap.put("Is_Abort_Flag", Is_Abort_Flag);
		}
		bodyMap.putAll(mainMap);
		
		for(int i=0;i<deliverDtlList.size();i++){
			HashMap dtlMap = (HashMap)deliverDtlList.get(i);
			String spetechId = (String)dtlMap.get("SPETECH_NO");
			String pdtNo =  (String)dtlMap.get("ITEMMASTER");
			String spetechNo = null;
			String spetchDesc = null;
			if(spetechId!=null){
				spetechNo = LogicUtil.getSpecTechNO(spetechId,pdtNo);
				spetchDesc = LogicUtil.getSpclInfoById(spetechId);
			}
			HashMap tempMap = new HashMap();
			tempMap.put("Spec", dtlMap.get("SPEC"));
			tempMap.put("FlowerNo", dtlMap.get("FLOWERNO"));
			tempMap.put("CustomerCode", dtlMap.get("CUSTOMERCODE"));
			tempMap.put("AddressCode", deliverMap.get("ADDRESSCODE"));
			tempMap.put("ItemMaster", pdtNo);
			tempMap.put("SpeTech_No", spetechNo);   
			tempMap.put("SpeTech_Description", spetchDesc);
			tempMap.put("PlanDocLineID", dtlMap.get("PLANDOCLINEID"));
			tempMap.put("Qty", dtlMap.get("QTY"));
			tempMap.put("Price", dtlMap.get("PRICE"));
			tempMap.put("Memo", dtlMap.get("MEMO"));
			BigDecimal IS_BACKUP_FLAG =  (BigDecimal)dtlMap.get("IS_BACKUP_FLAG");
			if(IS_BACKUP_FLAG == null){
				IS_BACKUP_FLAG = new BigDecimal("0");
			}
			tempMap.put("IS_BACKUP_FLAG", IS_BACKUP_FLAG.toString());
			BigDecimal IS_FREE_FLAG =  (BigDecimal)dtlMap.get("IS_FREE_FLAG");
			tempMap.put("IS_FREE_FLAG", IS_FREE_FLAG.toString());
			dtlList.add(tempMap);
			
		}
		bodyMap.put("DETAIL", dtlList)	;
	}

	/**
	 * 修改交期传给u9
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String modifyRequireDate(String BusinessId,String TEMP_DATE,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", ServiceCode);
		headMap.put("ServiceCode",ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId",UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf.append(
						"SELECT DELIVER_ORDER_NO as PlanDocNo,'"+TEMP_DATE+"' as RequireDate FROM ERP_DELIVER_ORDER WHERE DELIVER_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		HashMap deliverMap = (HashMap)InterUtil.selcom(params);
//		bodyMap.putAll(deliverMap);
		setRequireDateMap(bodyMap,deliverMap);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	
	
	private void setRequireDateMap(LinkedHashMap bodyMap,Map deliverMap){
		HashMap mainMap = new HashMap();
		ArrayList dtlList = new ArrayList();
		mainMap.put("PlanDocNo", deliverMap.get("PLANDOCNO"));
		mainMap.put("RequireDate", deliverMap.get("REQUIREDATE"));
		bodyMap.putAll(mainMap);
	}
	
	

	/**
	 * 创建出货单
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String createShipPlan(String BusinessId,String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", InterfaceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation",Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf.append(
						"SELECT t.ORDER_CHANN_NO as CustomerCode,t.RECV_ADDR_NO as CustomerSiteCode,t.DELIVER_ORDER_ID as ShipPlanID,t.DELIVER_ORDER_NO as ShipPlanDocNo,to_char(t.APPCH_TIME,'yyyy-mm-dd') as ShipPlanDate,t.JOIN_DELIVER_ORDER_NO as ShipPlanNO,(select p.SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode ,REMARK as MEMO FROM ERP_DELIVER_ORDER t WHERE t.DELIVER_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = InterUtil.selcom(params);
		StringBuffer deliverDtlBuf = new StringBuffer();
		deliverDtlBuf.append("SELECT 1 Direction,d.DELIVER_ORDER_DTL_ID as DeliverPlanLineID,d.U_CODE as PlanDocLineID,d.PRD_NO as ItemMasterCode, (select SPCL_TECH_NO FROM DRP_SPCL_TECH s WHERE s.spcl_tech_id =d.spcl_tech_id) SpeTech_No ,d.PLAN_NUM as ShipQty,0 as ShipType,d.DECT_PRICE as Price,d.REMARK as Memo FROM ERP_DELIVER_ORDER_DTL d WHERE d.DELIVER_ORDER_ID = '")
		.append(BusinessId).append("'   and d.DEL_FLAG =0 and NVL(d.PLAN_NUM,0) > 0");
		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List deliverDtlList = InterUtil.selcomList(params);
		String CustomerCode = (String)deliverMap.get("CUSTOMERCODE");
		String CustomerSiteCode = (String)deliverMap.get("CUSTOMERSITECODE");
		for(int i=0;i<deliverDtlList.size();i++){
			HashMap deliverDtl = (HashMap)deliverDtlList.get(i);
			deliverDtl.put("CUSTOMERCODE", CustomerCode);
			deliverMap.remove("CUSTOMERCODE");
			deliverDtl.put("CUSTOMERSITECODE", CustomerSiteCode);
			deliverMap.remove("CUSTOMERSITECODE");
		}
		bodyMap.putAll(deliverMap);
		bodyMap.put("DETAIL", deliverDtlList);
		String strJsonData = JesonImplUtil.getImplJson(headMap, bodyMap);
		return strJsonData;
	}

	
	/**
	 * 创建出货单按行  为了(货品发运的关闭)
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String createShipPlanByClose(String BusinessId,String DELIVER_ORDER_DTL_IDS,String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", InterfaceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation",Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf.append(
						"SELECT '-1' Direction,t.ORDER_CHANN_NO as CustomerCode,t.RECV_ADDR_NO as CustomerSiteCode,t.DELIVER_ORDER_ID as ShipPlanID,t.DELIVER_ORDER_NO as ShipPlanDocNo,to_char(t.ADVC_SEND_DATE,'yyyy-mm-dd') as ShipPlanDate,t.JOIN_DELIVER_ORDER_NO as ShipPlanNO,(select p.SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode ,REMARK as MEMO FROM ERP_DELIVER_ORDER t WHERE t.DELIVER_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = InterUtil.selcom(params);
		StringBuffer deliverDtlBuf = new StringBuffer();
		deliverDtlBuf.append("SELECT d.DELIVER_ORDER_DTL_ID as DeliverPlanLineID,d.U_CODE as PlanDocLineID,d.PRD_NO as ItemMasterCode, (select SPCL_TECH_NO FROM DRP_SPCL_TECH s WHERE s.spcl_tech_id =d.spcl_tech_id) SpeTech_No ,d.PLAN_NUM as ShipQty,0 as ShipType,d.DECT_PRICE as Price,d.REMARK as Memo FROM ERP_DELIVER_ORDER_DTL d WHERE d.DELIVER_ORDER_ID = '")
		.append(BusinessId).append("'   and d.DEL_FLAG =0 and DELIVER_ORDER_DTL_ID in ("+DELIVER_ORDER_DTL_IDS+")");
		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List deliverDtlList = InterUtil.selcomList(params);
		String CustomerCode = (String)deliverMap.get("CUSTOMERCODE");
		String CustomerSiteCode = (String)deliverMap.get("CUSTOMERSITECODE");
		for(int i=0;i<deliverDtlList.size();i++){
			HashMap deliverDtl = (HashMap)deliverDtlList.get(i);
			deliverDtl.put("CUSTOMERCODE", CustomerCode);
			deliverMap.remove("CUSTOMERCODE");
			deliverDtl.put("CUSTOMERSITECODE", CustomerSiteCode);
			deliverMap.remove("CUSTOMERSITECODE");
		}
		bodyMap.putAll(deliverMap);
		bodyMap.put("DETAIL", deliverDtlList);
		String strJsonData = JesonImplUtil.getImplJson(headMap, bodyMap);
		return strJsonData;
	}
	
	/**
	 * 创建退货单
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String createRMA(String BusinessId,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode",ServiceCode);
		headMap.put("ServiceCode",ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode",AppCode);
		headMap.put("DestCode",DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer rtBuf = new StringBuffer();
		rtBuf
				.append("SELECT to_char(t.EXPECT_RETURNDATE,'yyyy-mm-dd') as CurrentDate,t.PRD_RETURN_NO as ReturnDocNo, ( select b.SHIP_POINT_NO from BASE_CHANN b WHERE b.CHANN_ID  = t.return_chann_id) as OrgCode , t.RETURN_CHANN_NO, t.LEDGER_ID FROM ERP_PRD_RETURN t WHERE t.PRD_RETURN_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", rtBuf.toString());
		Map rtPdtMap = InterUtil.selcom(params);
		rtPdtMap.put("Is_REPAIR_FLAG", "0");
		StringBuffer dtlBuf = new StringBuffer();
		dtlBuf.append("SELECT RETURN_NUM as RMAQty,PRD_RETURN_DTL_ID as ReturnDocLineID,PRD_NO as ItemMasterCode,SPCL_TECH_ID as SpeTech_No,SN as Serial_No,RETURN_RSON as RMAMemo,PRICE_DECIDE as Price FROM ERP_PRD_RETURN_DTL WHERE PRD_RETURN_ID = '")
		.append(BusinessId).append("'");
		params = new HashMap();
		params.put("SelSQL", dtlBuf.toString());
		List rtPdtDtlList = InterUtil.selcomList(params);
		setRMAMap(bodyMap,rtPdtMap,rtPdtDtlList);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	
	
	/**
	 * 创建返修单
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String createRepair_RMA(String BusinessId,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode",ServiceCode);
		headMap.put("ServiceCode",ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode",AppCode);
		headMap.put("DestCode",DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer rtBuf = new StringBuffer();
		rtBuf
				.append("SELECT to_char(t.REQ_FINISH_DATE,'yyyy-mm-dd') as CurrentDate,t.ERP_REPAIR_ORDER_NO as ReturnDocNo, ( select b.SHIP_POINT_NO from BASE_CHANN b WHERE b.CHANN_ID  = t.REPAIR_CHANN_ID) as OrgCode , t.REPAIR_CHANN_NO, t.LEDGER_ID FROM ERP_REPAIR_ORDER t WHERE t.ERP_REPAIR_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", rtBuf.toString());
		Map rtPdtMap = InterUtil.selcom(params);
		rtPdtMap.put("Is_REPAIR_FLAG", "1");

		StringBuffer dtlBuf = new StringBuffer();
		dtlBuf.append("SELECT REPAIR_NUM as RMAQty,REPAIR_ORDER_DTL_ID as ReturnDocLineID,PRD_NO as ItemMasterCode,SPCL_TECH_ID as SpeTech_No,REPAIR_RESON as RMAMemo,REPAIR_PRICE as Price FROM ERP_REPAIR_ORDER_DTL WHERE ERP_REPAIR_ORDER_ID = '")
		.append(BusinessId).append("'");
		params = new HashMap();
		params.put("SelSQL", dtlBuf.toString());
		List rtPdtDtlList = InterUtil.selcomList(params);
		setRMARepairMap(bodyMap,rtPdtMap,rtPdtDtlList);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	private void setRMARepairMap(LinkedHashMap bodyMap,Map rtPdtMap,List rtPdtDtlList ){
		HashMap mainMap = new HashMap();
		ArrayList dtlList = new ArrayList();
		mainMap.put("CurrentDate", rtPdtMap.get("CURRENTDATE"));
		mainMap.put("ReturnDocNo", rtPdtMap.get("RETURNDOCNO"));
		mainMap.put("OrgCode", rtPdtMap.get("ORGCODE"));
		mainMap.put("Is_REPAIR_FLAG", rtPdtMap.get("Is_REPAIR_FLAG"));
		bodyMap.putAll(mainMap);
		
		for(int i=0;i<rtPdtDtlList.size();i++){
			HashMap dtlMap = (HashMap)rtPdtDtlList.get(i);
			String ReturnDocLineID = (String)dtlMap.get("RETURNDOCLINEID");
			HashMap tempMap = new HashMap();
			tempMap.put("RMAQty", dtlMap.get("RMAQTY"));
			tempMap.put("CustomerCode", rtPdtMap.get("REPAIR_CHANN_NO"));
			tempMap.put("ReturnDocLineID",ReturnDocLineID);
			tempMap.put("ItemMasterCode", dtlMap.get("ITEMMASTERCODE"));
			tempMap.put("SpeTech_No", dtlMap.get("SPETECH_NO"));
			tempMap.put("Serial_No", dtlMap.get("SERIAL_NO"));
			tempMap.put("RMAMemo", dtlMap.get("RMAMMEMO"));
			String DeliverOrderNo = getDeliveNoByRtPdtId(ReturnDocLineID);
			tempMap.put("DeliverOrderNo", DeliverOrderNo);
			tempMap.put("Price", dtlMap.get("PRICE"));
//			tempMap.put("Memo", "");
			dtlList.add(tempMap);
		}
		bodyMap.put("DETAIL", dtlList)	;
	}
	
	private void setRMAMap(LinkedHashMap bodyMap,Map rtPdtMap,List rtPdtDtlList ){
		HashMap mainMap = new HashMap();
		ArrayList dtlList = new ArrayList();
		mainMap.put("CurrentDate", rtPdtMap.get("CURRENTDATE"));
		mainMap.put("ReturnDocNo", rtPdtMap.get("RETURNDOCNO"));
		mainMap.put("OrgCode", rtPdtMap.get("ORGCODE"));
		mainMap.put("Is_REPAIR_FLAG", rtPdtMap.get("Is_REPAIR_FLAG"));
		bodyMap.putAll(mainMap);
		
		for(int i=0;i<rtPdtDtlList.size();i++){
			HashMap dtlMap = (HashMap)rtPdtDtlList.get(i);
			String ReturnDocLineID = (String)dtlMap.get("RETURNDOCLINEID");
			HashMap tempMap = new HashMap();
			tempMap.put("RMAQty", dtlMap.get("RMAQTY"));
			tempMap.put("CustomerCode", rtPdtMap.get("RETURN_CHANN_NO"));
			tempMap.put("ReturnDocLineID",ReturnDocLineID);
			tempMap.put("ItemMasterCode", dtlMap.get("ITEMMASTERCODE"));
			tempMap.put("SpeTech_No", dtlMap.get("SPETECH_NO"));
			tempMap.put("Serial_No", dtlMap.get("SERIAL_NO"));
			tempMap.put("RMAMemo", dtlMap.get("RMAMMEMO"));
			String DeliverOrderNo = getDeliveNoByRtPdtId(ReturnDocLineID);
			tempMap.put("DeliverOrderNo", DeliverOrderNo);
			tempMap.put("Price", dtlMap.get("PRICE"));
//			tempMap.put("Memo", "");
			dtlList.add(tempMap);
		}
		bodyMap.put("DETAIL", dtlList)	;
	}
	
	private String getDeliveNoByRtPdtId(String PRD_RETURN_DTL_ID){
		StringBuffer dtlBuf = new StringBuffer();
		dtlBuf.append("select FROM_BILL_NO from DRP_STOREIN_NOTICE where STOREIN_NOTICE_ID in(select FROM_BILL_ID from DRP_PRD_RETURN_REQ_DTL where FROM_BILL_ID in (select FROM_BILL_DTL_ID from ERP_PRD_RETURN_DTL  where PRD_RETURN_DTL_ID = '")
		.append(PRD_RETURN_DTL_ID).append("'))");
		HashMap params = new HashMap();
		params.put("SelSQL", dtlBuf.toString());
		Map retPdtMap = InterUtil.selcom(params);
		String deliveNo = "";
		if(retPdtMap!=null){
			deliveNo = (String)retPdtMap.get("FROM_BILL_NO");
		}
		return deliveNo;																	
	}
	
	/**应收、余额查询
	 * @param CHANN_NO
	 * @param LEDGER_ID
	 * @return
	 */
	public String txQueryPayMent(String CHANN_NO,String LEDGER_ID,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", ServiceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation",Operation);
		headMap.put("AppCode",AppCode);
		headMap.put("DestCode",DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		bodyMap.put("CustNo", CHANN_NO);
		bodyMap.put("OrgCode", LEDGER_ID);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	
	/**库存查询
	 * @param CHANN_NO
	 * @param PDT_ID
	 * @param LEDGER_ID
	 * @return
	 */
	public String txQueryStoreAcct(String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String CHANN_NO,String PDT_ID,String LEDGER_ID) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", InterfaceCode);
		headMap.put("ServiceCode",ServiceCode);
		headMap.put("Operation",Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode",DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId",UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		bodyMap.put("CustNo", CHANN_NO);
		bodyMap.put("PrdNo", PDT_ID);
		bodyMap.put("OrgCode", LEDGER_ID);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	
	public String txQueryProStatus(String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String BusinessId) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode",InterfaceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);

		LinkedHashMap bodyMap = new LinkedHashMap();
		String DeliverPlanNo = getBusinessOrder(BusinessId);
		bodyMap.put("DeliverPlanNo", DeliverPlanNo);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}
	
	private String getBusinessOrder(String BusinessId){
		if(BusinessId.startsWith("SO")){
			StringBuffer dtlBuf = new StringBuffer();
			dtlBuf.append("select tt.REMARK from erp_sale_order tt where tt.sale_order_id = '")
			.append(BusinessId).append("'");
			HashMap params = new HashMap();
			params.put("SelSQL", dtlBuf.toString());
			Map retPdtMap = InterUtil.selcom(params);
			String DeliverPlanNo = (String)retPdtMap.get("REMARK");
			return DeliverPlanNo;
		}else{
			return BusinessId;
		}
		
	}
	
	public String txQueryReturnTrace(String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String RETURN_NO) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode",InterfaceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation",Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		
		LinkedHashMap bodyMap = new LinkedHashMap();
		bodyMap.put("RETURN_NO", RETURN_NO);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}


	private Map getPayMentMap(String PAYMENT_NO) throws Exception {
		StringBuffer payMentBuf = new StringBuffer();
		payMentBuf.append("SELECT * FROM ERP_PAYMENT WHERE DEL_FLAG = 0 and PAYMENT_NO = '")
				.append(PAYMENT_NO).append("'");
		Map params = new HashMap();
		params.put("SelSQL", payMentBuf.toString());
		return InterUtil.selcom(params);
	}

	/**
	 * 检查是否存在弃审单
	 * 
	 * @param mainKey
	 * @return
	 * @throws Exception
	 */
	private boolean checkExsitPrimarykey(String keyName, String keyValue,
			String tableName) throws Exception {
		StringBuffer payMentBuf = new StringBuffer();
		payMentBuf.append("SELECT ").append(keyName).append(" FROM ").append(
				tableName).append(" WHERE DEL_FLAG = 0 and ").append(keyName).append(" = '")
				.append(keyValue).append("'");
		Map params = new HashMap();
		params.put("SelSQL", payMentBuf.toString());
		Map map = InterUtil.selcom(params);
		if (map != null && map.size() > 0) {
			return true;
		}
		return false;
	}


	
	/**还冻结信用,扣减余额：
	 * @param DELIVER_ORDER_DTL_ID
	 * @return
	 */
	public boolean updatePdtDeliverFree(String DELIVER_ORDER_DTL_ID,String DELIVER_ORDER_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b")
		.append(" set (b.CREDIT_CURRT, b.FREEZE_CREDIT) = (select NVL(b.CREDIT_CURRT, 0) -")
		.append("  NVL(SUM(NVL(d.DECT_PRICE, 0) *")
		.append(" NVL(d.PLAN_NUM, 0)),0),")
		.append("  NVL(b.FREEZE_CREDIT, 0) -")
		.append("  NVL(SUM(NVL(d.CREDIT_FREEZE_PRICE,")
		.append("  0) *")
		.append(" NVL(d.PLAN_NUM, 0)),0)")
		.append(" FROM ERP_DELIVER_ORDER     t,")
		.append("  ERP_DELIVER_ORDER_DTL d")
		.append(" WHERE t.DELIVER_ORDER_ID =")
		.append(" d.DELIVER_ORDER_ID")
		.append(" and t.DEL_FLAG = 0")
		.append(" and NVL(d.IS_FREE_FLAG,0) = 0")
		.append(" and d.DELIVER_ORDER_DTL_ID = '"+DELIVER_ORDER_DTL_ID+"')")
		.append("  WHERE b.CHANN_ID =")
		.append(" (select t.ORDER_CHANN_ID")
		.append("  FROM ERP_DELIVER_ORDER t")
		.append("  WHERE t.DELIVER_ORDER_ID = '"+DELIVER_ORDER_ID+"')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**扣减过的冻结单价清0
	 * @param DELIVER_ORDER_DTL_ID
	 * @return
	 */
	public boolean clearPdtDeliverFreePrice(String DELIVER_ORDER_DTL_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update ERP_DELIVER_ORDER_DTL d set CREDIT_FREEZE_PRICE=0 where d.DELIVER_ORDER_DTL_ID = '"+DELIVER_ORDER_DTL_ID+"' and NVL(d.IS_FREE_FLAG,0) = 0");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	

	
	

	
	/**按新产生的发运单冻结的信用 (请注意 是差异数量)
	 * @param DELIVER_ORDER_DTL_ID
	 * @return
	 */
	public boolean updateDeliverConfigFree(String NewsDELIVER_ORDER_DTL_ID,String deliverOrderId){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b")
		.append(" set b.FREEZE_CREDIT = NVL(b.FREEZE_CREDIT, 0) +")
		.append("  NVL((select NVL(d.DECT_PRICE, 0) *")
		.append(" NVL(d.PLAN_NUM, 0)*0.3")
		.append("   from ERP_DELIVER_ORDER  t,")
		.append(" ERP_DELIVER_ORDER_DTL d")
		.append("  WHERE t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append(" and t.DEL_FLAG = 0")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0")
		.append(" and d.DELIVER_ORDER_DTL_ID =")
		.append("  '"+NewsDELIVER_ORDER_DTL_ID+"'),")
		.append("  0) * 1")
		.append("  WHERE b.CHANN_ID =")
		.append("  (select t.ORDER_CHANN_ID")
		.append("  FROM ERP_DELIVER_ORDER t")
		.append(" WHERE t.DELIVER_ORDER_ID = '"+deliverOrderId+"')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**更新产生的货品的冻结单价
	 * @param NewsDELIVER_ORDER_DTL_ID
	 * @return
	 */	
	public boolean updateDeliverConfigFreePrice(String NewsDELIVER_ORDER_DTL_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update  ERP_DELIVER_ORDER_DTL d set CREDIT_FREEZE_PRICE=NVL(d.DECT_PRICE, 0) *0.3   where  NVL(d.IS_FREE_FLAG,0) = 0 AND d.DELIVER_ORDER_DTL_ID = '"+NewsDELIVER_ORDER_DTL_ID+"'");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	
	/**按新产生的发运单回当前信用 (请注意 是差异数量)
	 * @param DELIVER_ORDER_DTL_ID
	 * @return
	 */
	public boolean updateDeliverConfigCurrtCredit(String NewsDELIVER_ORDER_DTL_ID,String deliverOrderId){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b")
		.append("   set b.CREDIT_CURRT=NVL(b.CREDIT_CURRT,0)+")
		.append("  (select")
		.append("   NVL(SUM(NVL(d.DECT_PRICE, 0) * NVL(d.PLAN_NUM, 0)),0)  ")
		.append("   FROM ERP_DELIVER_ORDER t, ERP_DELIVER_ORDER_DTL d")
		.append(" WHERE t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0")
		.append("  and t.DEL_FLAG = 0")
		.append(" and d.DELIVER_ORDER_DTL_ID = '"+NewsDELIVER_ORDER_DTL_ID+"'")
		.append("  )*1")
		.append("  WHERE b.CHANN_ID =")
		.append("  (select t.ORDER_CHANN_ID")
		.append("   FROM ERP_DELIVER_ORDER t")
		.append("   WHERE t.DELIVER_ORDER_ID = '"+deliverOrderId+"')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/*初使信用额度设定时  ,记流水账
	 * @return
	 */
	public boolean insertJOURNAL_InitCREDIT(String channId,String diffCredit ,String DIRECTION){
		StringBuffer instBuffer = new StringBuffer();
		instBuffer.append("insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,")
           .append("BUSS_TYPE,")
		   .append("LEDGER_ID,")
		   .append("CHANN_ID,")
		   .append("CHANN_NO,")
		   .append("CHANN_NAME,")
		   .append("PRD_AMOUNT,")
		   .append("DEAL_TIME,")
		   .append("YEAR_MONTH,")
		   .append("DIRECTION)")
		  .append("select sys_guid(),")
		   .append("'信用额度设定',")
		   .append("LEDGER_ID,")
		    .append("CHANN_ID,")
		      .append("CHANN_NO,")
		      .append("CHANN_NAME,")
		     .append(diffCredit+",")
		      .append("to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
		     .append(" to_char(sysdate, 'yyyy/MM'),")
		   .append(""+DIRECTION+"")
		  .append("  from BASE_CHANN t")
		   .append(" where t.CHANN_ID = '"+channId+"'");
		   Map params = new HashMap();
			params.put("InsSQL", instBuffer.toString());
			boolean flg = InterUtil.txInscom(params);
			return flg;
	}
	
	public Map getOrderNumByDeliverId(String DELIVER_ORDER_DTL_ID){
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("select d.Order_Num ADVC_PLAN_NUM, d.IS_BACKUP_FLAG from ERP_SALE_ORDER_DTL d WHERE d.u_code = '"+DELIVER_ORDER_DTL_ID+"'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuffer.toString());
		Map map = InterUtil.selcom(params);
		return map;
	}
	
	/**整单冻结
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	public boolean txAllDeliverOrderFreeze(String DELIVER_ORDER_ID){
		boolean flg = updateOldFreezePrice(DELIVER_ORDER_ID);
		if(!flg){
			throw new ServiceException("更新明细冻结单价出错!");
		}
		flg = updateFreezeCredit(DELIVER_ORDER_ID);
		if(!flg){
			throw new ServiceException("更新客户70%冻结信用出错!");
		}
		inertFreezeCreditJournal("整单冻结",DELIVER_ORDER_ID,"0");
		flg = updateDeliverDtlFreezePrice(DELIVER_ORDER_ID);
		if(!flg){
			throw new ServiceException("更新发运单明细冻结单价出错!");
		}
		flg = updateDeliverFreezeFlg(DELIVER_ORDER_ID,"1");
		if(!flg){
			throw new ServiceException("更新发运单主表冻结标记出错!");
		}
		return true;
	}
	
	/**1、	更新发运单明细上的 原冻结单价=单价单价
	 * @return
	 */
	private boolean updateOldFreezePrice(String DELIVER_ORDER_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update ERP_DELIVER_ORDER_DTL d")
		.append("     set d.OLD_FREEZE_PRICE = d.CREDIT_FREEZE_PRICE")
		.append("  where d.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID).append("'")
		.append("     and d.DEL_FLAG = 0")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**冻结渠道70%的信用
	 * @return
	 */
	private boolean updateFreezeCredit(String DELIVER_ORDER_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b")
		.append("   set b.FREEZE_CREDIT=NVL(b.FREEZE_CREDIT,0)+")
		.append("  (select")
		.append("  SUM((NVL(d.DECT_PRICE, 0)-NVL(d.CREDIT_FREEZE_PRICE, 0)) * NVL(d.PLAN_NUM, 0))")
		.append("   FROM ERP_DELIVER_ORDER t, ERP_DELIVER_ORDER_DTL d")
		.append(" WHERE t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0")
		.append("  and d.DEL_FLAG = 0")
		.append(" and t.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID).append("'")
		.append("  )")
		.append("  WHERE b.CHANN_ID =")
		.append("  (select t.ORDER_CHANN_ID")
		.append("   FROM ERP_DELIVER_ORDER t")
		.append("   WHERE t.DELIVER_ORDER_ID = '"+DELIVER_ORDER_ID+"')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**更新发运单明细的冻结单价
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	private boolean updateDeliverDtlFreezePrice(String DELIVER_ORDER_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update ERP_DELIVER_ORDER_DTL d")
		.append("     set d.CREDIT_FREEZE_PRICE = d.DECT_PRICE")
		.append("  where d.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID).append("'")
		.append("     and d.DEL_FLAG = 0")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**更新整单冻结标记
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	private boolean updateDeliverFreezeFlg(String DELIVER_ORDER_ID,String IS_ALL_FREEZE_FLAG){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update ERP_DELIVER_ORDER t")
		.append("     set t.IS_ALL_FREEZE_FLAG = ").append(IS_ALL_FREEZE_FLAG)
		.append("  where t.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID).append("'");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**整单解冻
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	public boolean txAllDeliverOrderunFreeze(String DELIVER_ORDER_ID){
		boolean flg = robackFreezeCredit(DELIVER_ORDER_ID);
		if(!flg){
			throw new ServiceException("解除客户70%冻结信用出错!");
		}
		flg = robackDeliverDtlFreezePrice(DELIVER_ORDER_ID);
		if(!flg){
			throw new ServiceException("更新发运单明细冻结单价出错!");
		}
		inertFreezeCreditJournal("整单解冻",DELIVER_ORDER_ID,"1");
		flg = updateDeliverFreezeFlg(DELIVER_ORDER_ID,"0");
		if(!flg){
			throw new ServiceException("更新发运单主表冻结标记出错!");
		}
		return true;
	}
	
	/**解除冻结时减70%冻结信用
	 * @return
	 */
	private boolean robackFreezeCredit(String DELIVER_ORDER_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("UPDATE BASE_CHANN b")
		.append("   set b.FREEZE_CREDIT=NVL(b.FREEZE_CREDIT,0)-")
		.append("  (select")
		.append("  SUM((NVL(d.DECT_PRICE, 0)-NVL(d.OLD_FREEZE_PRICE, 0)) * NVL(d.PLAN_NUM, 0))")
		.append("   FROM ERP_DELIVER_ORDER t, ERP_DELIVER_ORDER_DTL d")
		.append(" WHERE t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0")
		.append("  and d.DEL_FLAG = 0")
		.append(" and t.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID).append("'")
		.append("  )")
		.append("  WHERE b.CHANN_ID =")
		.append("  (select t.ORDER_CHANN_ID")
		.append("   FROM ERP_DELIVER_ORDER t")
		.append("   WHERE t.DELIVER_ORDER_ID = '"+DELIVER_ORDER_ID+"')");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/**更新冻结单价=原冻结单价
	 * @param DELIVER_ORDER_ID
	 * @return
	 */
	private boolean robackDeliverDtlFreezePrice(String DELIVER_ORDER_ID){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("update ERP_DELIVER_ORDER_DTL d")
		.append("     set d.CREDIT_FREEZE_PRICE = d.OLD_FREEZE_PRICE")
		.append("  where d.DELIVER_ORDER_ID = '").append(DELIVER_ORDER_ID).append("'")
		.append("     and d.DEL_FLAG = 0")
		.append("  and NVL(d.IS_FREE_FLAG,0) = 0");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuff.toString());
		return InterUtil.txUpdcom(params);
	}
	
	/******************************信用流水账 stat************************/
	/**
	 * 记流水账
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean inertCreditJournal(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String priceCol = (String) confMap.get("PRICE_COL");
		String dectRateCol = (String) confMap.get("DECT_RATE_COL");
		String prdAmountCol = (String) confMap.get("PRD_AMOUNT_COL");
		String strPdtIdCol = (String) confMap.get("PRD_ID_COL");
		String dectPriceCol = (String) confMap.get("DECT_PRICE_COL");
		String freezePriceCol = (String) confMap.get("FREEZE_PRICE_COL");
		String pdtNumCol = (String) confMap.get("PRD_NUM_COL");
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
								+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,PRD_ID,SPCL_TECH_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,"
								+ "STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,PRD_NUM,PRD_AMOUNT,DEAL_TIME,FREEZE_PRICE,"
								+ "YEAR_MONTH,DIRECTION)").append(
						" SELECT  sys_guid(),").append(
						"'" + confMap.get("BUSS_TYPE").toString()).append("',")
				.append(confMap.get("MAIN_KEY_COL").toString()).append(",")
				.append(confMap.get("BILL_NO_COL").toString()).append(",")
				.append(confMap.get("DTL_KEY_COL").toString()).append(",")
				.append(confMap.get("LEDGER_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_NO_COL").toString()).append(",")
				.append(confMap.get("CHANN_NAME_COL").toString()).append(",");
		if (strPdtIdCol != null) {
			instBuffer.append(strPdtIdCol).append(",").append(
					confMap.get("PRD_NO_COL").toString()).append(",").append(
					confMap.get("PRD_NAME_COL").toString()).append(",").append(
					confMap.get("PRD_SPEC_COL").toString()).append(",").append(
					confMap.get("PRD_COLOR_COL").toString()).append(",")
					.append(confMap.get("BRAND_COL").toString()).append(",")
					.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(
							",").append(confMap.get("STD_UNIT_COL").toString())
					.append(",");
		} else {
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
		}
		if (priceCol != null) {
			instBuffer.append(priceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectRateCol != null) {
			instBuffer.append(dectRateCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectPriceCol != null) {
			instBuffer.append(dectPriceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (pdtNumCol != null) {
			instBuffer.append(pdtNumCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (prdAmountCol != null) {
			instBuffer.append(prdAmountCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		instBuffer.append(" to_char(sysdate,'yyyy-MM-DD HH24:MI:SS')  ").append(",");
		if (freezePriceCol != null) {
			instBuffer.append(freezePriceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}

		instBuffer.append(" to_char(sysdate,'yyyy/MM') ").append(",").append(
				confMap.get("DIRECTION").toString()).append(" from ").append(
				confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap)).append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'");

		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}

	
	/**
	 * 订货订单冻结信用记流水账
	 * 
	 * @param bussID
	 * @param keyValue
	 * @return
	 * @throws Exception
	 */
	public boolean inertOrderCreditJournal(String bussID, String keyValue)
			throws Exception {
		Map confMap = loadCreditConf(bussID);
		String priceCol = (String) confMap.get("PRICE_COL");
		String dectRateCol = (String) confMap.get("DECT_RATE_COL");
		String prdAmountCol = (String) confMap.get("PRD_AMOUNT_COL");
		String strPdtIdCol = (String) confMap.get("PRD_ID_COL");
		String dectPriceCol = (String) confMap.get("DECT_PRICE_COL");
		String freezePriceCol = (String) confMap.get("FREEZE_PRICE_COL");
		String pdtNumCol = (String) confMap.get("PRD_NUM_COL");
		StringBuffer instBuffer = new StringBuffer();
		instBuffer
				.append(
						"insert into BASE_JOURNAL_CREDIT_ACCT (JOURNAL_CREDIT_ACCT_ID,"
								+ "BUSS_TYPE,BILL_ID,BILL_NO,BILL_DTL_ID,LEDGER_ID,CHANN_ID,CHANN_NO,"
								+ "CHANN_NAME,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,SPCL_TECH_ID,"
								+ "STD_UNIT,PRICE,DECT_RATE,DECT_PRICE,PRD_NUM,DEAL_TIME,FREEZE_PRICE,"
								+ "YEAR_MONTH,DIRECTION)").append(
						" SELECT  sys_guid(),").append(
						"'" + confMap.get("BUSS_TYPE").toString()).append("',")
				.append(confMap.get("MAIN_KEY_COL").toString()).append(",")
				.append(confMap.get("BILL_NO_COL").toString()).append(",")
				.append(confMap.get("DTL_KEY_COL").toString()).append(",")
				.append(confMap.get("LEDGER_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_ID_COL").toString()).append(",")
				.append(confMap.get("CHANN_NO_COL").toString()).append(",")
				.append(confMap.get("CHANN_NAME_COL").toString()).append(",");
		if (strPdtIdCol != null) {
			instBuffer.append(strPdtIdCol).append(",").append(
					confMap.get("PRD_NO_COL").toString()).append(",").append(
					confMap.get("PRD_NAME_COL").toString()).append(",").append(
					confMap.get("PRD_SPEC_COL").toString()).append(",").append(
					confMap.get("PRD_COLOR_COL").toString()).append(",")
					.append(confMap.get("BRAND_COL").toString()).append(",")
					.append(confMap.get("SPCL_TECH_ID_COL").toString()).append(
							",").append(confMap.get("STD_UNIT_COL").toString())
					.append(",");
		} else {
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
			instBuffer.append("''").append(",");
		}
		if (priceCol != null) {
			instBuffer.append(priceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectRateCol != null) {
			instBuffer.append(dectRateCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (dectPriceCol != null) {
			instBuffer.append(dectPriceCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		if (pdtNumCol != null) {
			instBuffer.append(pdtNumCol).append(",");
		} else {
			instBuffer.append("''").append(",");
		}
		instBuffer.append(" to_char(sysdate,'yyyy-MM-DD HH24:MI:SS')  ").append(",");
		if (freezePriceCol != null) {
			instBuffer.append("NVL("+freezePriceCol+",0)").append(",");
		} else {
			instBuffer.append("''").append(",");
		}

		instBuffer.append(" to_char(sysdate,'yyyy/MM') ").append(",").append(
				confMap.get("DIRECTION").toString()).append(" from ").append(
				confMap.get("MAIN_TAB_NAME").toString()).append(" ")
				.append(confMap.get("MAIN_TAB_ANNAME").toString())
				.append(",")
				// 单据主表
				.append(confMap.get("DTL_TAB_NAME").toString())
				.append(" ")
				.append(confMap.get("DTL_TAB_ANNAME").toString())
				// 单据明细
				.append(" where ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append(getFKeyColName(confMap))
				.append(" and d.DEL_FLAG = 0 ")
				.append(" and ").append(
						confMap.get("MAIN_KEY_COL").toString()).append("=")
				.append("'").append(keyValue).append("'");

		Map params = new HashMap();
		params.put("InsSQL", instBuffer.toString());
		return InterUtil.txInscom(params);
	}
	
	/*记付款单流水账
	 * @return
	 */
	public boolean insertJOURNAL_CREDIT(String PAYMENT_ID,String actionName,String DIRECTION){
		StringBuffer instBuffer = new StringBuffer();
		instBuffer.append("insert into BASE_JOURNAL_CREDIT_ACCT(JOURNAL_CREDIT_ACCT_ID,")
           .append("BUSS_TYPE,")
		  .append(" BILL_ID,")
		   .append("BILL_NO,")
		   .append("LEDGER_ID,")
		   .append("CHANN_ID,")
		   .append("CHANN_NO,")
		   .append("CHANN_NAME,")
		   .append("PRD_AMOUNT,")
		   .append("DEAL_TIME,")
		   .append("YEAR_MONTH,")
		   .append("DIRECTION)")
		   .append("select sys_guid(),")
		   .append("'"+actionName+"',")
		   .append(" PAYMENT_ID,")
		   .append("PAYMENT_NO,")
		   .append("LEDGER_ID,")
		   .append("CHANN_ID,")
		   .append("CHANN_NO,")
		   .append("CHANN_NAME,")
		   .append(" PAY_AMONT,")
		   .append("to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
		   .append(" to_char(sysdate, 'yyyy/MM'),")
		   .append(""+DIRECTION+"")
		   .append("  from ERP_PAYMENT t")
		   .append(" where t.PAYMENT_ID = '"+PAYMENT_ID+"'");
		    Map params = new HashMap();
			params.put("InsSQL", instBuffer.toString());
			boolean flg = InterUtil.txInscom(params);
			return flg;
	}

	
	/**记销售订单流水账(冻结信用)
	 * @return
	 */
	public boolean inertSaleCancelCreditJournal(String erpSaleOrderDtlIds,String DIRECTION,String ORDER_NUM ){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("insert into BASE_JOURNAL_CREDIT_ACCT")
		.append("   (JOURNAL_CREDIT_ACCT_ID,")
		.append(" BUSS_TYPE, BILL_ID,BILL_NO, BILL_DTL_ID,CHANN_ID,CHANN_NO, CHANN_NAME,PRD_ID, SPCL_TECH_ID, PRD_NO,")
		.append(" PRD_NAME, PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT, PRD_NUM, DEAL_TIME,FREEZE_PRICE, YEAR_MONTH,")
		.append("   DIRECTION)")
		.append(" select rawtohex(sys_guid()) as UUID,")
		.append(" '取消预订',")
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
		.append("  d.std_unit,");
		if("1".equals(DIRECTION)){
			sqlBuff.append(ORDER_NUM+",");
		}else{
			sqlBuff.append("NVL(d.ORDER_NUM,0),");
		}
		sqlBuff.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
		.append("  NVL(d.CREDIT_FREEZE_PRICE,0),")
		.append("  to_char(sysdate, 'yyyy/MM'),")
		.append(DIRECTION)
		.append(" from erp_sale_order t,erp_sale_order_dtl d")
		.append("  where t.sale_order_id = d.sale_order_id and NVL(d.is_free_flag,0) = 0  and   d.sale_order_dtl_id in (").append(erpSaleOrderDtlIds).append(")");
		 Map params = new HashMap();
	     params.put("InsSQL", sqlBuff.toString());
		boolean flg = InterUtil.txInscom(params);
		return flg;
	}
	
	/**记发运单流水账
	 * @return
	 */
	public boolean inertPdtDeliverCreditJournal(String BillType,String DELIVER_ORDER_DTL_ID,String DIRECTION){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("insert into BASE_JOURNAL_CREDIT_ACCT")
		.append("   (JOURNAL_CREDIT_ACCT_ID,")
		.append(" BUSS_TYPE, BILL_ID,BILL_NO,BILL_DTL_ID,CHANN_ID,CHANN_NO, CHANN_NAME,PRD_ID, SPCL_TECH_ID, PRD_NO,")
		.append(" PRD_NAME, PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,DECT_PRICE,PRD_AMOUNT, PRD_NUM, DEAL_TIME,FREEZE_PRICE, YEAR_MONTH,")
		.append("   DIRECTION)")
		.append(" select rawtohex(sys_guid()) as UUID,")
		.append(" '"+BillType+"',")
		.append("  d.deliver_order_id,")
		.append("  t.deliver_order_no,")
		.append(" d.deliver_order_dtl_id,")
		.append("  t.order_chann_id,")
		.append("  t.order_chann_no,")
		.append("  t.order_chann_name,")
		.append("  d.prd_id,")
		.append("  d.spcl_tech_id,")
		.append(" d.prd_no,")
		.append(" d.prd_name,")
		.append(" d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append(" d.DECT_PRICE,")
		.append(" NVL(d.DECT_PRICE,0)*NVL(d.PLAN_NUM,0),")
		.append(" d.PLAN_NUM,")
		.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
		.append("  d.CREDIT_FREEZE_PRICE,")
		.append("  to_char(sysdate, 'yyyy/MM'),")
		.append(DIRECTION)
		.append(" from erp_deliver_order_dtl d left join erp_deliver_order t on t.deliver_order_id = d.deliver_order_id ")
		.append("  where d.deliver_order_dtl_id = '").append(DELIVER_ORDER_DTL_ID).append("'")
		.append(" and NVL(d.IS_FREE_FLAG, 0) = 0");
		Map params = new HashMap();
	    params.put("InsSQL", sqlBuff.toString());
		boolean flg = InterUtil.txInscom(params);
		return flg;
	}
	
	/**记发运单流水账--<整单冻结/整单解冻>
	 * @return
	 */
	public boolean inertFreezeCreditJournal(String BillType,String DELIVER_ORDER_ID,String DIRECTION){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("insert into BASE_JOURNAL_CREDIT_ACCT")
		.append("   (JOURNAL_CREDIT_ACCT_ID,")
		.append(" BUSS_TYPE, BILL_ID, BILL_NO,BILL_DTL_ID,CHANN_ID,CHANN_NO, CHANN_NAME,PRD_ID, SPCL_TECH_ID, PRD_NO,")
		.append(" PRD_NAME, PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,DECT_PRICE, PRD_NUM, DEAL_TIME,FREEZE_PRICE, YEAR_MONTH,")
		.append("   DIRECTION)")
		.append(" select rawtohex(sys_guid()) as UUID,")
		.append(" '"+BillType+"',")
		.append("  d.deliver_order_id,")
		.append("  t.deliver_order_no,")
		.append(" d.deliver_order_dtl_id,")
		.append("  t.order_chann_id,")
		.append("  t.order_chann_no,")
		.append("  t.order_chann_name,")
		.append("  d.prd_id,")
		.append("  d.spcl_tech_id,")
		.append(" d.prd_no,")
		.append(" d.prd_name,")
		.append(" d.prd_spec,")
		.append(" d.prd_color,")
		.append("  d.brand,")
		.append("  d.std_unit,")
		.append(" d.DECT_PRICE,")
		.append(" d.PLAN_NUM,")
		.append(" to_char(sysdate, 'yyyy-MM-DD HH24:MI:SS'),")
		.append("  NVL(d.DECT_PRICE, 0)-NVL(d.CREDIT_FREEZE_PRICE, 0),")
		.append("  to_char(sysdate, 'yyyy/MM'),")
		.append(DIRECTION)
		.append(" from erp_deliver_order_dtl d left join erp_deliver_order t on t.deliver_order_id = d.deliver_order_id ")
		.append("  where d.deliver_order_id = '").append(DELIVER_ORDER_ID).append("'")
		.append(" and NVL(d.IS_FREE_FLAG, 0) = 0");
		 Map params = new HashMap();
	     params.put("InsSQL", sqlBuff.toString());
		boolean flg = InterUtil.txInscom(params);
			return flg;
	}
	
	public boolean instSpechNoRemark(String sale_order_no,String sale_order_dtl_id,String prd_no,String spcl_tech_id,String spcl_tech_no,String spetchDesc){
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append("insert into DRP_SPCL_TECH_REMARK")
		.append("   (SPCL_TECH_REMARK_ID,SALE_ORDER_NO,")
		.append(" SALE_ORDER_DTL_ID, PRD_NO, SPCL_TECH_ID,SPCL_TECH_NO,SPCL_SPEC_REMARK,DEAL_DATE)")
		.append(" VALUES(rawtohex(sys_guid()),")
		.append(" '"+sale_order_no+"',")
		.append(" '"+ sale_order_dtl_id+"',")
		.append(" '"+ prd_no+"',")
		.append(" '"+ spcl_tech_id+"',")
		.append(" '"+ spcl_tech_no+"',")
		.append(" '"+ spetchDesc+"',")
		.append("  sysdate)");
		 Map params = new HashMap();
	     params.put("InsSQL", sqlBuff.toString());
		boolean flg = InterUtil.txInscom(params);
		return flg;
	}
	
	/******************************信用流水账 end************************/
	
	/**
	 * 跟据渠道ID得相关渠道信息
	 * 
	 * @param channNo
	 * @return
	 */
	private Map getChannMapByLendger(String dumpId) throws Exception {
		StringBuffer chanBuf = new StringBuffer();
		chanBuf.append("select b.chann_id,b.chann_no,b.chann_name from base_chann b where b.chann_id =(select d.ledger_id from DRP_DUMP d where d.dump_id = '"+dumpId+"')");
		Map params = new HashMap();
		params.put("SelSQL", chanBuf.toString());
		return InterUtil.selcom(params);
	}
	public Map getMonthAcc(String ledgerId,String bussDate) {
		StringBuffer chanBuf = new StringBuffer();
		chanBuf.append("select  count(*)NUM  from DRP_MONTH_ACCT_NOTE where STATE='已月结' and LEDGER_ID ='"+ledgerId+"' and YEAR=substr('"+bussDate+"',1,4) and MONTH =substr('"+bussDate+"',6,2)");
		Map params = new HashMap();
		params.put("SelSQL", chanBuf.toString());
		return InterUtil.selcom(params);
	}
	
	/**生成盘盈盘亏单
	 * @param invenToryId
	 */
	public void genProfitLossOrder(String invenToryId){
		genOverageOrder(invenToryId);
		genShortageOrder(invenToryId);
	}
	
	/**生成盘盈单
	 * 
	 */
	public void genOverageOrder(String invenToryId){
		String PROFIT_LOSS_ID = StringUtil.uuid32len();
		String PROFIT_LOSS_NO = LogicUtil.getBmgz("DRP_PROFIT_LOSS_NO");
		StringBuffer overageBuf = new StringBuffer();
		overageBuf.append(" insert into DRP_PROFIT_LOSS(PROFIT_LOSS_ID,PROFIT_LOSS_NO,PRD_INV_ID,PRD_INV_NO,")
		.append(" STORE_ID,STORE_NO,STORE_NAME,INV_PSON_ID,INV_PSON_NO,INV_PSON_NAME,")
		.append(" STATE,REMARK,CREATOR,CRE_NAME,CRE_TIME,AUDIT_ID,AUDIT_NAME,AUDIT_TIME,DEPT_ID,")
		.append(" DEPT_NAME,ORG_ID,ORG_NAME,LEDGER_ID,LEDGER_NAME,DEL_FLAG,BILL_TYPE)")
		.append(" select '"+PROFIT_LOSS_ID+"',")
		.append("'").append(PROFIT_LOSS_NO).append("',")
		.append("t.prd_inv_id,t.prd_inv_no,t.store_id,t.store_no,t.store_name,t.inv_pson_id,")
		.append(" t.inv_pson_no,t.inv_pson_name,'审核通过',t.remark,t.creator,t.cre_name,")
		.append(" sysdate,t.audit_id,t.audit_name,sysdate,t.dept_id,t.dept_name,")
		.append(" t.org_id,t.org_name,t.ledger_id,t.ledger_name,0,'盘盈'")
		.append(" from drp_prd_inv t where t.prd_inv_id = '").append(invenToryId)
		.append("'");
		
		 
		 StringBuffer overageDtlBuf = new StringBuffer();
		 overageDtlBuf.append("insert into DRP_PROFIT_LOSS_DTL(")
		 .append(" PROFIT_LOSS_DTL_ID,PROFIT_LOSS_ID,PRD_ID,")
		 .append(" PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,")
		 .append(" STD_UNIT,PROFIT_LOSS_NUM,DEL_FLAG,SPCL_TECH_ID,")
		 .append(" PROFIT_LOSS_PRICE,PROFIT_LOSS_AMOUNT)")
		 .append(" select PROFIT_LOSS_DTL_ID,")
		 .append(" PROFIT_LOSS_ID,")
		 .append(" PRD_ID,PRD_NO, PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND, STD_UNIT, DIFF_NUM, DEL_FLAG, SPCL_TECH_ID,")
		 .append(" (CASE")
		 .append("  WHEN temp.cost_price = '-1' then")
		 .append("  temp.PRVD_PRICE")
		 .append(" else")
		 .append("  temp.cost_price")
		 .append(" end) PROFIT_LOSS_PRICE,")
		 .append(" ((CASE")
		 .append(" WHEN temp.cost_price = '-1' then")
		 .append(" temp.PRVD_PRICE")
		 .append(" else")
		 .append("  temp.cost_price")
		 .append(" end)  * DIFF_NUM )PROFIT_LOSS_AMOUNT")
		 .append(" from (select rawtohex(sys_guid()) PROFIT_LOSS_DTL_ID,")
		 .append(" '").append(PROFIT_LOSS_ID).append("' PROFIT_LOSS_ID,")
		 .append(" d.prd_id,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,d.PRD_COLOR,d.BRAND,d.STD_UNIT,d.DIFF_NUM,")
		 .append(" 0 DEL_FLAG,d.SPCL_TECH_ID,NVL((SELECT NVL(c.cost_price, 0) FROM DRP_COST_PRICE c")
		 .append(" where c.month = to_char(trunc(add_months(sysdate, -1)), 'MM')")
		 .append(" and c.year = to_char(sysdate, 'YYYY')")
		 .append(" and c.prd_id = d.prd_id  and NVL(c.spcl_tech_id, 'NONL') = NVL(d.spcl_tech_id, 'NONL')")
		 .append("  and c.ledger_id = t.ledger_id), '-1') cost_price,")
		 .append(" (select p.PRVD_PRICE   from base_product p where p.prd_id = d.prd_id) PRVD_PRICE")
		 .append(" from drp_prd_inv_dtl d, drp_prd_inv t  where d.prd_inv_id = t.prd_inv_id")
		 .append(" and t.prd_inv_id = '"+invenToryId+"'  and d.del_flag = 0  and NVL(d.diff_num, 0) > 0 ) temp");
		 Map dtlParams = new HashMap();
		 dtlParams.put("InsSQL", overageDtlBuf.toString());
	     InterUtil.txInscom(dtlParams); 
	     if(checkExsitDtl(PROFIT_LOSS_ID)){
			 Map params = new HashMap();
		     params.put("InsSQL", overageBuf.toString());
			 boolean flg = InterUtil.txInscom(params);
	     }
	     
	}
	
	private boolean checkExsitDtl(String PROFIT_LOSS_ID){
		 StringBuffer overageDtlBuf = new StringBuffer();
		 overageDtlBuf.append(" SELECT COUNT(*) NUM FROM DRP_PROFIT_LOSS_DTL d")
		 .append(" WHERE d.PROFIT_LOSS_ID = '").append(PROFIT_LOSS_ID).append("'");
		 
		Map map = new HashMap();
		map.put("SelSQL", overageDtlBuf.toString());
		Map checkMap = InterUtil.selcom(map);
		BigDecimal NUMFld = (BigDecimal)checkMap.get("NUM");
		if(NUMFld.intValue()==0){
			return false;
		}
		return true;
		 
	}
	
	/**生成盘亏单
	 * @param invenToryId
	 */
	public void genShortageOrder(String invenToryId){
		String PROFIT_LOSS_ID = StringUtil.uuid32len();
		String PROFIT_LOSS_NO = LogicUtil.getBmgz("DRP_PROFIT_LOSS_NO");
		StringBuffer overageBuf = new StringBuffer();
		overageBuf.append(" insert into DRP_PROFIT_LOSS(PROFIT_LOSS_ID,PROFIT_LOSS_NO,PRD_INV_ID,PRD_INV_NO,")
		.append(" STORE_ID,STORE_NO,STORE_NAME,INV_PSON_ID,INV_PSON_NO,INV_PSON_NAME,")
		.append(" STATE,REMARK,CREATOR,CRE_NAME,CRE_TIME,AUDIT_ID,AUDIT_NAME,AUDIT_TIME,DEPT_ID,")
		.append(" DEPT_NAME,ORG_ID,ORG_NAME,LEDGER_ID,LEDGER_NAME,DEL_FLAG,BILL_TYPE)")
		.append(" select '"+PROFIT_LOSS_ID+"',")
		.append("'").append(PROFIT_LOSS_NO).append("',")
		.append("t.prd_inv_id,t.prd_inv_no,t.store_id,t.store_no,t.store_name,t.inv_pson_id,")
		.append(" t.inv_pson_no,t.inv_pson_name,'审核通过',t.remark,t.creator,t.cre_name,")
		.append(" sysdate,t.audit_id,t.audit_name,sysdate,t.dept_id,t.dept_name,")
		.append(" t.org_id,t.org_name,t.ledger_id,t.ledger_name,0,'盘亏'")
		.append(" from drp_prd_inv t where t.prd_inv_id = '").append(invenToryId)
		.append("'");
		 
		 StringBuffer overageDtlBuf = new StringBuffer();
		 overageDtlBuf.append("insert into DRP_PROFIT_LOSS_DTL(")
		 .append(" PROFIT_LOSS_DTL_ID,PROFIT_LOSS_ID,PRD_ID,")
		 .append(" PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,")
		 .append(" STD_UNIT,PROFIT_LOSS_NUM,DEL_FLAG,SPCL_TECH_ID,")
		 .append(" PROFIT_LOSS_PRICE,PROFIT_LOSS_AMOUNT)")
		 .append(" select PROFIT_LOSS_DTL_ID,")
		 .append(" PROFIT_LOSS_ID,")
		 .append(" PRD_ID,PRD_NO, PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND, STD_UNIT, DIFF_NUM, DEL_FLAG, SPCL_TECH_ID,")
		 .append(" (CASE")
		 .append("  WHEN temp.cost_price = '-1' then")
		 .append("  temp.PRVD_PRICE")
		 .append(" else")
		 .append("  temp.cost_price")
		 .append(" end) PROFIT_LOSS_PRICE,")
		 .append(" ((CASE")
		 .append(" WHEN temp.cost_price = '-1' then")
		 .append(" temp.PRVD_PRICE")
		 .append(" else")
		 .append("  temp.cost_price")
		 .append(" end)  * DIFF_NUM )PROFIT_LOSS_AMOUNT")
		 .append(" from (select rawtohex(sys_guid()) PROFIT_LOSS_DTL_ID,")
		 .append(" '").append(PROFIT_LOSS_ID).append("' PROFIT_LOSS_ID,")
		 .append(" d.prd_id,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,d.PRD_COLOR,d.BRAND,d.STD_UNIT,abs(d.DIFF_NUM)DIFF_NUM,")
		 .append(" 0 DEL_FLAG,d.SPCL_TECH_ID,NVL((SELECT NVL(c.cost_price, 0) FROM DRP_COST_PRICE c")
		 .append(" where c.month = to_char(trunc(add_months(sysdate, -1)), 'MM')")
		 .append(" and c.year = to_char(sysdate, 'YYYY')")
		 .append(" and c.prd_id = d.prd_id  and NVL(c.spcl_tech_id, 'NONL') = NVL(d.spcl_tech_id, 'NONL')")
		 .append("  and c.ledger_id = t.ledger_id), '-1') cost_price,")
		 .append(" (select p.PRVD_PRICE   from base_product p where p.prd_id = d.prd_id) PRVD_PRICE")
		 .append(" from drp_prd_inv_dtl d, drp_prd_inv t  where d.prd_inv_id = t.prd_inv_id")
		 .append(" and t.prd_inv_id = '"+invenToryId+"'  and d.del_flag = 0  and NVL(d.diff_num, 0) < 0 ) temp");
		 Map dtlParams = new HashMap();
		 dtlParams.put("InsSQL", overageDtlBuf.toString());
	     InterUtil.txInscom(dtlParams); 
	     
	     if(checkExsitDtl(PROFIT_LOSS_ID)){
			 Map params = new HashMap();
		     params.put("InsSQL", overageBuf.toString());
			 InterUtil.txInscom(params);
	     }
	}
	
	
	/**
	 * 更新对账单
	 * @param jsonData
	 * @return
	 */
	public String txUpdatePayMentDetail(String CHANN_NO,String MonthStartDate,String jsonData) {
        String sql = " delete from ERP_PAYMENT_STATEMENT  where CHANN_NO='"+CHANN_NO+"'";
        Map<String,String> params = new HashMap<String,String>();
		params.put("DelSQL", sql);
		InterUtil.txDelcom(params);
        
		HashMap headMap = null;
		HashMap bodyMap = null;
		try {
			JesonOutUtil jsonUtil = new JesonOutUtil(jsonData);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap)jsonUtil.getMbBody().get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			return "接口调用出错";
		}
		 
		String IsOK = (String) bodyMap.get("IsOK");
		String Msg = (String) bodyMap.get("Msg");
		String Detail = (String) bodyMap.get("Detail");
//		if(!"0".equals(IsOK)){
//			return Msg;
//		}
		try {
			
				List<PayMentDtl> list = new Gson().fromJson(Detail, 
						new TypeToken<ArrayList<PayMentDtl>>(){}.getType());
				
				 for(PayMentDtl model : list){
					 String Memo = model.getMemo();
					 String AccrueDate = model.getAccrueDate();
					 String AccountPeriod = model.getAccountPeriod();
					 if("期初余额".equals(Memo)){
						 Date initDate = DateUtil.parseDate(MonthStartDate,"yyyy-MM-dd");
						 Calendar calendar = Calendar.getInstance();
						 calendar.setTime(initDate);
						 int minday = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);//得到当前月份的最小日期
						 calendar.set(Calendar.DAY_OF_MONTH, minday);
						  //DateUtil.format(DateUtil.parseDate(MonthStartDate),"yyyy-MM-dd HH:mm:ss");
						 AccrueDate = DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
						 AccountPeriod = DateUtil.format(calendar.getTime(), "yyyy-M");
					 }

					 if("期末余额".equals(Memo)){
						 Date endDate = DateUtil.parseDate(MonthStartDate,"yyyy-MM-dd");
						 Calendar calendar = Calendar.getInstance();
						 calendar.setTime(endDate);
						 int maxday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//得到当前月份的最大日期
						 calendar.set(Calendar.DAY_OF_MONTH, maxday);
						 calendar.set(Calendar.HOUR_OF_DAY, 23);
						 calendar.set(Calendar.MINUTE, 59);//23:59:00
						 AccrueDate = DateUtil.format(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
						 AccountPeriod = DateUtil.format(calendar.getTime(), "yyyy-M");
					 }
					 StringBuffer sqlBuf = new StringBuffer();
					 sqlBuf.append(" insert into ERP_PAYMENT_STATEMENT(CHANN_NO,CHANN_NAME,ACCT_DATE,ACCT_TIME,DOC_NO," +
								"CUST_NAME,MEMO,BILL_TYPE,BILL_NO,CUR_PAYMENT,CUR_REV_PAYMENT,CUR_LEFT_PAYMENT,LEDGER_NAME) values(");
					 sqlBuf.append("'").append(model.getPayCustCode()).append("',");
					 sqlBuf.append("'").append(model.getPayCustName()).append("',");	
					 sqlBuf.append("'").append(AccrueDate).append("',");	
					 sqlBuf.append("'").append(AccountPeriod).append("',");	//记账期间
					 sqlBuf.append("'").append(model.getVoucherDisplayCode()).append("',");	
					 sqlBuf.append("'").append(model.getAccrueCustName()).append("',");	
					 sqlBuf.append("'").append(model.getMemo()).append("',");	
					 sqlBuf.append("'").append(model.getCatName()).append("',");
					 sqlBuf.append("'").append(model.getDocNo()).append("',");
					 sqlBuf.append("'").append(model.getQty()).append("',");
					 sqlBuf.append("'").append(model.getCustSiteCode()).append("',");
					 sqlBuf.append("'").append(model.getBalance()).append("',")//本期余额
					 .append("'").append(model.getOrgName()).append("'")
					 .append(")");
					 // 去掉sql中可能出现的逗号
					 sqlBuf = InterUtil.replaceUpSql(sqlBuf);
			 
					 params.clear();
					 params.put("InsSQL", sqlBuf.toString());
					 InterUtil.txInscom(params);
				 }
				 
				 //最后执行一个update
				 sql = " update ERP_PAYMENT_STATEMENT t set t.CHANN_NAME=(select c.CHANN_NAME from BASE_CHANN c where c.Del_Flag=0 and c.CHANN_NO=t.CHANN_NO)" +
				 		" where t.CHANN_NAME is null and t.CHANN_NO='"+CHANN_NO+"' ";
				 params.clear();
				 params.put("UpdSQL", sql);
				 InterUtil.txUpdcom(params);
			 
		} catch (Exception ex) {
			 
		}
 
		return "";
	}
	
	

	/**
	 * 查询门店导购员信息
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg queryTermSales(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog("查询门店导购员信息", "查询门店导购员信息", "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		Map<String,String> jsonMap = null;
		try {
			jsonMap = new Gson().fromJson(jsonData,  
		                new TypeToken<Map<String, String>>(){}.getType());  
			 
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog("查询门店导购员信息", "查询门店导购员信息", "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
 
		try {
			String[] mustFld = { "TERM_NO","APP_KEY" };
			if (!InterUtil.checkMustFld(mustFld, jsonMap, msg)) {
				LogicUtil.actLog("查询门店导购员信息", "查询门店导购员信息", "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "","");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			boolean flg = true;
			String TERM_NO =  jsonMap.get("TERM_NO");
			String APP_KEY =  jsonMap.get("APP_KEY");
			//两次日期的MD5
			String myKey = MD5Encrypt.MD5(DateUtil.format(new Date(), "yyyy-MM-dd"));
			myKey = MD5Encrypt.MD5(myKey);
			//"APP_KEY不匹配
			if(!myKey.equals(APP_KEY)){
				LogicUtil.actLog("查询门店导购员信息", "查询门店导购员信息", "U9系统", "失败",
						BusinessConsts.APP_KEY_ERROR, "", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.APP_KEY_ERROR);
				return msg;
			}
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("select t.RYBH SALES_NO, t.XM SALES_NAME from T_SYS_RYXX t")
			.append(" where t.RYJB = '门店_导购员'")
            .append(" and t.RYZT = '启用' ")
            .append(" and t.BMXXID = '").append(TERM_NO).append("' ")
            .append(" and DELFLAG = 0");
			 
			Map params = new HashMap();
			params.put("SelSQL", sqlBuf.toString());
			List list = InterUtil.selcomList(params);
			 
			msg.setFLAG(BusinessConsts.SUCCESS);
			msg.setMESSAGE("");
			msg.setList(list);
			return msg;
			 
		} catch (Exception ex) {
			String errorInfo = "查询门店导购员信息!" + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog("查询门店导购员信息", "查询门店导购员信息", "U9系统", "失败", errorInfo,"", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("内部出错");
			throw new ServiceException("内部出错");
		}
	}
	
	
	/**
	 * 订单接入接口
	 * @param jsonData
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txInsAdvcOrder(String jsonData) {
		InterReturnMsg msg = new InterReturnMsg();
		String actionName = "订单接入";
		if (StringUtil.isEmpty(jsonData)) {
			LogicUtil.actLog(actionName,actionName, "U9系统", "失败",
					BusinessConsts.IMPL_PARAM_NULL_ERR, "", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数为空!");
			return msg;
		}
		JsonLonanBean jsonBean = null;
		try {
			jsonBean = new Gson().fromJson(jsonData,  
		                new TypeToken<JsonLonanBean>(){}.getType());  
			 
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonData;
			LogicUtil.actLog(actionName, actionName, "U9系统", "失败", errorInfo, "",
					"", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("参数格式不正确!");
			return msg;
		}
 
		try {
			Map<String,Object>jsonMap = BeanHelper.convertBeanToMap(jsonBean);
			//必填校验
			String[] mustFld = {"ORDER_NO","TERM_NO","APP_KEY","SALES_NO","CUST_NAME","TEL"};
			if (!InterUtil.checkMustFld(mustFld, jsonMap, msg)) {
				LogicUtil.actLog(actionName,actionName, "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "","");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			//必填明细校验 zzb
			mustFld = new String[]{"PRD_NO","PRICE","ORDER_NUM","ORDER_RECV_DATE"};
			List<JsonLonanBean.LonanPrd> DTL_DATAS =  jsonBean.getDTL_DATAS();
			int count = 0;
			for(JsonLonanBean.LonanPrd lonanPrd: DTL_DATAS){
				Map<String,Object> model = BeanHelper.convertBeanToMap(lonanPrd);
				if (!InterUtil.checkMustFld(mustFld, model, msg)){
					count++;
				}
			}
			if(count>0){
				LogicUtil.actLog(actionName,actionName, "U9系统", "失败",
						BusinessConsts.IMPL_PARAM_MUST_FLD, "", "","");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.IMPL_PARAM_MUST_FLD);
				return msg;
			}
			
			String APP_KEY =  StringUtil.nullToSring(jsonMap.get("APP_KEY"));
			//两次日期的MD5
			String myKey = MD5Encrypt.MD5(DateUtil.format(new Date(), "yyyy-MM-dd"));
			myKey = MD5Encrypt.MD5(myKey);
			//"APP_KEY不匹配
			if(!myKey.equals(APP_KEY)){
				LogicUtil.actLog(actionName, actionName, "U9系统", "失败",
						BusinessConsts.APP_KEY_ERROR, "", "", "");
				msg.setFLAG(BusinessConsts.FLASE);
				msg.setMESSAGE(BusinessConsts.APP_KEY_ERROR);
				return msg;
			}
			
			boolean flg = true;
			String ORDER_NO =  StringUtil.nullToSring(jsonMap.get("ORDER_NO"));
			String CUST_NAME =  StringUtil.nullToSring(jsonMap.get("CUST_NAME"));
			String TERM_NO =  StringUtil.nullToSring(jsonMap.get("TERM_NO"));
			String TEL =  StringUtil.nullToSring(jsonMap.get("TEL"));
			String SALES_NO =  StringUtil.nullToSring(jsonMap.get("SALES_NO"));
			Double ADVC_AMOUNT =  StringUtil.getDouble(jsonMap.get("ADVC_AMOUNT"));
			String RECV_ADDR =  StringUtil.nullToSring(jsonMap.get("RECV_ADDR"));
			
			Map params = new HashMap();
			params.put("SelSQL", "select t.TERM_ID,t.TERM_NO, t.TERM_NAME,t.CHANN_ID_P,t.CHANN_NO_P,t.CHANN_NAME_P,t.LEDGER_ID,t.LEDGER_NAME from BASE_TERMINAL t where t.TERM_NO='"+TERM_NO+"'");
			List<Map<String,String>> list = InterUtil.selcomList(params);
			if(null == list || list.size()==0){
				throw new ServiceException("没有查到门店信息！编号："+TERM_NO);
			}
			Map<String,String> termMap  = list.get(0);
			 
			params.clear();
			params.put("SelSQL", "select t.RYXXID, t.RYBH, t.XM ,t.BMXXID from T_SYS_RYXX t where  t.rybh='"+SALES_NO+"'");
			List<Map<String,String>> ryList = InterUtil.selcomList(params);
			if(null == ryList || ryList.size()==0){
				throw new ServiceException("没有查到门店导购员信息！编号："+SALES_NO);
			}
			Map<String,String> ryMap  = ryList.get(0);
			
			String prefix = termMap.get("CHANN_NO_P");//预订单 单头
			int length = BusinessConsts.ADVC_ORDER_NO_LENGTH;//预订单编号段长
			UserBean userBean = new UserBean(null,null,null);
			userBean.setLoginZTXXID(termMap.get("CHANN_NO_P"));
			String ADVC_ORDER_NO = LogicUtil.getBillNo("ADVC_ORDER_NO","DRP_ADVC_ORDER",prefix,length,userBean);
			
			String ADVC_ORDER_ID = StringUtil.uuid32len();
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf.append("insert into DRP_ADVC_ORDER(ADVC_ORDER_ID,ADVC_ORDER_NO,SALE_PSON_ID,SALE_PSON_NAME,TERM_ID,TERM_NO,TERM_NAME,")
			.append("CUST_NAME, TEL,ADVC_AMOUNT, PAYABLE_AMOUNT,RECV_ADDR,REMARK,STATE,FROM_BILL_NO,CREATOR,CRE_NAME,DEL_FLAG,BILL_TYPE,LEDGER_ID,LEDGER_NAME,ORG_ID,ORG_NAME)")
			.append("values(")
			.append("'").append(ADVC_ORDER_ID).append("',")
			.append("'").append(ADVC_ORDER_NO).append("',")
			.append("'").append(ryMap.get("RYXXID")).append("',")
			.append("'").append(ryMap.get("XM")).append("',")
			.append("'").append(termMap.get("TERM_ID")).append("',")
			.append("'").append(termMap.get("TERM_NO")).append("',")
			.append("'").append(termMap.get("TERM_NAME")).append("',")
			.append("'").append(CUST_NAME).append("',")
			.append("'").append(TEL).append("',")
			.append("").append(ADVC_AMOUNT).append(",")
			.append("").append(0).append(",")
			.append("'").append(RECV_ADDR).append("',")
			.append("'").append("龙安订单编号：").append(ORDER_NO).append("',")
			.append("'").append("待完善").append("',")
			.append("'").append(ORDER_NO).append("',")
			.append("'").append("LA").append("',")
			.append("'").append("LA").append("',")
			.append("").append(0).append(",")
			.append("'").append("终端销售").append("',")
			.append("'").append(termMap.get("CHANN_ID_P")).append("',")
			.append("'").append(termMap.get("CHANN_NAME_P")).append("',")
			.append("'").append(termMap.get("CHANN_ID_P")).append("',")
			.append("'").append(termMap.get("CHANN_NAME_P")).append("')") ;
		    
			params.clear();
			params.put("InsSQL", sqlBuf.toString());
			InterUtil.txInscom(params);
			
			for(JsonLonanBean.LonanPrd model: DTL_DATAS){
				String ORDER_RECV_DATE = model.getORDER_RECV_DATE();
				String PRICE = model.getPRICE();
				String PRD_NO = model.getPRD_NO();
				String PRD_DESC = model.getPRD_DESC();//描述
				Double ORDER_NUM = StringUtil.getDouble(model.getORDER_NUM());
				params.clear();
//				params.put("SelSQL", "select p.PRD_ID,p.PRD_NO,p.PRD_NAME,p.PRD_SPEC,p.STD_UNIT,p.PRD_COLOR,p.BRAND,p.FACT_PRICE from BASE_PRODUCT p where p.PRD_NO='"+PRD_NO+"' ");
//				List<Map<String,String>> prdList = InterUtil.selcomList(params);
//				if(null == prdList || prdList.size()==0){
//					throw new ServiceException("没有查到货品信息！编号："+PRD_NO);
//				}
//				Map<String,String> prdMap  = prdList.get(0);
				
//				Double FACT_PRICE = StringUtil.getDouble(prdMap.get("FACT_PRICE"));
				Double DECT_PRICE = StringUtil.getDouble(PRICE);
//				Double DECT_RATE = (double)(Math.round(DECT_PRICE/FACT_PRICE*100)/100.0);
				Double PAYABLE_AMOUNT = ORDER_NUM*DECT_PRICE;
				 
				sqlBuf = new StringBuffer();
				sqlBuf.append("insert into DRP_ADVC_ORDER_DTL(ADVC_ORDER_DTL_ID, ADVC_ORDER_ID,")
				.append("DECT_PRICE,ORDER_NUM,PAYABLE_AMOUNT,REMARK,DEL_FLAG,ORDER_RECV_DATE,STATE")
				.append(")values(")
				.append("'").append(StringUtil.uuid32len()).append("',")
				.append("'").append(ADVC_ORDER_ID).append("',")
//				.append("'").append(prdMap.get("PRD_ID")).append("',")
//				.append("'").append(prdMap.get("PRD_NO")).append("',")
//				.append("'").append(prdMap.get("PRD_NAME")).append("',")
//				.append("'").append(prdMap.get("PRD_SPEC")).append("',")
//				.append("'").append(prdMap.get("PRD_COLOR")).append("',")
//				.append("'").append(prdMap.get("BRAND")).append("',")
//				.append("'").append(prdMap.get("STD_UNIT")).append("',")
//				.append(FACT_PRICE).append(",")
//				.append(DECT_RATE).append(",")
				.append(PRICE).append(",")
				.append(ORDER_NUM).append(",")
				.append(PAYABLE_AMOUNT).append(",")
				.append("'").append(PRD_DESC).append("',")
				.append(0).append(",")
				.append(" to_date('").append(ORDER_RECV_DATE).append("','yyyy-mm-dd'),")
				.append("'").append("正常").append("')")
				;
				
				params.clear();
				params.put("InsSQL", sqlBuf.toString());
				InterUtil.txInscom(params);
			}
			 
			msg.setFLAG(BusinessConsts.SUCCESS);
			msg.setMESSAGE(ADVC_ORDER_NO);
			return msg;
			 
		}catch(ServiceException ex){
			String errorInfo = "jsonData: " +jsonData+"    "+actionName + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog(actionName, actionName, "U9系统", "失败", errorInfo,"", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE(ex.getMessage());
			throw new ServiceException(ex.getMessage());
		} catch (Exception ex) {
			String errorInfo = "jsonData: " +jsonData+"    "+actionName + InterUtil.getErrorInfo(ex);
			LogicUtil.actLog(actionName, actionName, "U9系统", "失败", errorInfo,"", "", "");
			msg.setFLAG(BusinessConsts.FLASE);
			msg.setMESSAGE("内部出错");
			throw new ServiceException("内部出错");
			
		}
	}
	private String getChannSpecEnableByChannId(String CHANN_ID){
		String sql="select NVL(IS_SPEC_TECH_ENABLE,0)IS_SPEC_TECH_ENABLE from BASE_CHANN where CHANN_ID='"+CHANN_ID+"' and DEL_FLAG=0";
		Map<String,String> params=new HashMap<String, String>();
		params.put("SelSQL", sql);
		Map<String,String> map=InterUtil.selcom(params);
		return String.valueOf(map.get("IS_SPEC_TECH_ENABLE"));
	}
}
