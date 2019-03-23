package com.hoperun.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.BaseService;
/* 初使货进销存账*/
public class InitJournalInvPdtAcctServiceImpl extends BaseService{
	private final static String tableName = "DRP_INVOC_ACCT";
	
	public boolean txInitInvPdtAcct(String channId){
		List invPdtList = getInvPdtData(channId);
		for(int i=0;i<invPdtList.size();i++){
			HashMap invPdtMap = (HashMap)invPdtList.get(i);
			String storeId = (String)invPdtMap.get("STORE_ID");
			String prd_inv_id = (String)invPdtMap.get("PRD_INV_ID");
			delInvocAcct(channId,storeId);
			instInvcAcct(prd_inv_id,channId,storeId);
		}
		return true;
	}
	
	/**得初始化盘点数据
	 * @param channId
	 * @return
	 */
	public List getInvPdtData(String channId){
		StringBuffer selSql =  new StringBuffer();
		selSql.append("select inv.prd_inv_id,inv.store_id,inv.ledger_id,inv.cre_time ")
		.append("    from DRP_PRD_INV inv ")
		.append("  where inv.inv_type = '初始化盘点'")
		.append("  and inv.del_flag = 0")
		.append("  and inv.ledger_id = '"+channId+"'")
		.append("  order by inv.cre_time asc");
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		List resultList = selcomList(params);
		return resultList;		
	}
	
	/**删除进销存账
	 * @param channId
	 * @param storeId
	 */
	public void delInvocAcct(String channId,String storeId){
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete from "+tableName+" ac where ac.ledger_id = '")
		.append(channId).append("' and ").append(" STORE_ID = '").append(storeId).append("'");
		Map params = new HashMap();
		params.put("DelSQL", delSql.toString());
		txDelcom(params);		
	}
	
	public boolean instInvcAcct(String prd_inv_id,String channId,String storeId){
		StringBuffer instSql = new StringBuffer();
		instSql.append(
				"INSERT INTO "+tableName+" ")
		.append(" (INVOC_ACCT_ID,")
		.append(" PRD_ID,")
		.append(" SPCL_TECH_ID,")
		.append(" CUR_END_NUM,")
		.append(" STORE_ID,")
		.append(" CUR_END_PRICE,")
		.append(" CUR_END_AMOUNT,")
		.append(" MONTH,")
		.append(" YEAR,")
		.append(" LEDGER_ID)")
		.append(" SELECT rawtohex(sys_guid()) as UUID,")
		.append(" d.PRD_ID,")
		.append(" d.spcl_tech_id,")
		.append(" d.inv_num,")
		.append(" t.store_id,")
		.append(" (select p.prvd_price from base_product p where p.prd_id = d.prd_id),")
		.append("  (select p.prvd_price from base_product p where p.prd_id = d.prd_id) * NVL(d.inv_num, 0),")
		.append("   to_char(add_months(trunc(t.cre_time), -1), 'mm') MONTH,")
		.append("  to_char(t.cre_time, 'yyyy') YEAR,")
		.append("  LEDGER_ID")
		.append("   from DRP_PRD_INV t, DRP_PRD_INV_DTL d ")
		.append("  where t.prd_inv_id = d.prd_inv_id ")
		.append("  and t.prd_inv_id = '"+prd_inv_id+"'")
		.append("  and t.ledger_id = '"+channId+"'")
		.append("   and t.store_id =  '"+storeId+"'");
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
