package com.hoperun.report.store.service.impl;

import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.BaseRptService;
import com.hoperun.report.store.service.StoreRepertoryService;

public class StoreRepertoryServiceImpl extends BaseRptService implements
		StoreRepertoryService {

	/**
	 * 生成报表sql
	 * 
	 * @param whereSql
	 * @return
	 */
	public String createSql(String whereSql,String ZTXXID) {
		String sql = "select temp.STORE_NO,"
			    +"temp.STORE_NAME,"
			    +"(case when temp.DM_SPCL_TECH_NO is null or temp.DM_SPCL_TECH_NO='' then temp.PRD_NO " +
			    		" else temp.PRD_NO ||'-'|| temp.DM_SPCL_TECH_NO end )PRD_NO,"
			    +"temp.PRD_NAME,"
			    +"temp.PRD_SPEC,"
			    +"temp.SPCL_REMARK,"
			    +"temp.PAR_PRD_NAME,"
			    +"temp.STORE_NUM,"
			    +"NVL(advdtl.NOT_SEND_NUM,0)NOT_SEND_NUM,(temp.STORE_NUM-NVL(advdtl.NOT_SEND_NUM, 0)) USER_NUM from "
			    + "(select s.STORE_NO,s.STORE_NAME,a.PRD_ID,p.PRD_NO,"
				+ "p.PRD_NAME,p.PRD_SPEC," 
				+ "sum(NVL(a.STORE_NUM,0))STORE_NUM,"
				+ "sum(NVL(a.FREEZE_NUM,0))FREEZE_NUM,a.SPCL_TECH_ID,"
				+ "b.DM_SPCL_TECH_NO,b.SPCL_DTL_REMARK SPCL_REMARK,p.PAR_PRD_NAME "
				+ " from DRP_STORE_ACCT a left join DRP_STORE s"
				+ " on s.STORE_ID = a.STORE_ID left join BASE_PRODUCT p"
				+ " on p.PRD_ID = a.PRD_ID left join DRP_SPCL_TECH b on b.SPCL_TECH_ID=a.SPCL_TECH_ID" + whereSql
				+ " group by a.PRD_ID, s.STORE_NO,s.STORE_NAME,p.PRD_NO,"
				+ " p.PRD_NAME,p.PRD_SPEC,a.STORE_NUM,"
				+ " a.FREEZE_NUM,a.SPCL_TECH_ID,b.DM_SPCL_TECH_NO,b.SPCL_DTL_REMARK,p.PAR_PRD_NAME) temp "
				+" left join "
				+"(select d.PRD_ID,"
				+"d.PRD_NO,"
				+"sum(d.ORDER_NUM) ORDER_NUM,"
				+"sum(NVL(d.SEND_NUM, 0)) SEND_NUM,"
				+"(sum(d.ORDER_NUM) - sum(NVL(d.SEND_NUM, 0))) NOT_SEND_NUM,"
				+"d.SPCL_TECH_ID"
				+" from DRP_ADVC_ORDER t, DRP_ADVC_ORDER_DTL d"
				+" where t.ADVC_ORDER_ID = d.ADVC_ORDER_ID"
//				+" and (floor(CAST(d.order_recv_date as date) -to_date(to_char(sysdate, 'yyyy-MM-DD'),'yyyy-MM-DD')) >= 0 " 
//				       +" and "
//				       +"   floor(CAST(d.order_recv_date as date) - to_date(to_char(sysdate, 'yyyy-MM-DD'), 'yyyy-MM-DD')) <= 30" 
//				+     ")"
				+" and d.DEL_FLAG = 0"
				+" and t.DEL_FLAG = 0"
				+" and t.STATE in ('审核通过','部分发货','待确认')"
				+" and t.LEDGER_ID = '"+ZTXXID+"'"
				+" and d.STATE='正常' "
				+" and t.BILL_TYPE='终端销售' "
				+" group by d.PRD_ID, d.PRD_NO, d.SPCL_TECH_ID"
				+") advdtl"
				+" on temp.PRD_ID = advdtl.PRD_ID"
				+" and NVL(temp.SPCL_TECH_ID, 'NULL') = NVL(advdtl.SPCL_TECH_ID, 'NULL')";
				
				
		return sql;
	}
	
	
	public String createSql(String whereSql) {
		StringBuffer sql=new StringBuffer();//查询SQL
		StringBuffer commSql=new StringBuffer();//通用SQL
		StringBuffer sumSql=new StringBuffer();//合计SQL
		sql.append("rptSql=select s.STORE_NO,")
		   .append("s.STORE_NAME,")
		   .append("p.PRD_NO,")
		   .append("b.DM_SPCL_TECH_NO,")
		   .append("p.PRD_NAME,")
		   .append("p.PRD_SPEC,")
		   .append("a.STORE_NUM,")
		   .append("NVL(a.FREEZE_NUM,0)FREEZE_NUM,")
		   .append("a.SPCL_TECH_ID,")
		   .append("(NVL(a.STORE_NUM,0) - NVL(a.FREEZE_NUM,0)) USER_NUM,")
		   .append("b.SPCL_DTL_REMARK SPCL_REMARK,")
		   .append("p.PAR_PRD_NAME,")
		   .append("NVL(u.COST_PRICE,0)COST_PRICE,")
		   .append("(NVL(u.COST_PRICE,0)*(NVL(a.STORE_NUM,0) - NVL(a.FREEZE_NUM,0)))COST_AMOUNT ");
		commSql.append(" from DRP_STORE_ACCT a  ")
			   .append(" left join DRP_STORE s on s.STORE_ID = a.STORE_ID ")
			   .append(" left join BASE_PRODUCT p on p.PRD_ID = a.PRD_ID ")
			   .append(" left join DRP_SPCL_TECH b on b.SPCL_TECH_ID=a.SPCL_TECH_ID ")
			   .append(" left join BASE_CHANN c on c.CHANN_ID=a.LEDGER_ID and c.DEL_FLAG=0 ")
			   .append(" left join V_PRODUCT_PRVD_PRICE u on u.PRD_ID=a.PRD_ID and NVL(u.SPCL_TECH_ID,'NONE') = NVL(a.SPCL_TECH_ID,'NONE') ");
		sql.append(commSql)
		   .append(whereSql)
		   .append(" group by ")
		   .append("s.STORE_NO,")
		   .append("s.STORE_NAME,")
		   .append("p.PRD_NO,")
		   .append("p.PRD_NAME,")
		   .append("p.PRD_SPEC,")
		   .append("a.STORE_NUM,")
		   .append("a.FREEZE_NUM,")
		   .append("a.SPCL_TECH_ID,")
		   .append("b.SPCL_DTL_REMARK,")
		   .append("p.PAR_PRD_NAME,")
		   .append("b.DM_SPCL_TECH_NO,")
		   .append("u.COST_PRICE;");
		sumSql.append("sumSql=select sum(NVL(a.STORE_NUM,0))ALL_STORE_NUM,")
		      .append("sum(NVL(a.FREEZE_NUM, 0)) ALL_FREEZE_NUM,")
		      .append("sum(NVL(a.STORE_NUM, 0) - NVL(a.FREEZE_NUM, 0)) ALL_USER_NUM,")
		      .append("sum(NVL(u.COST_PRICE, 0)) ALL_COST_PRICE,")
		      .append("sum(NVL(u.COST_PRICE, 0) * (NVL(a.STORE_NUM, 0) - NVL(a.FREEZE_NUM, 0)))ALL_COST_AMOUNT ")
		      .append(commSql).append(whereSql);
		return sql.append(sumSql).toString();
	}
	public String createSql(String whereSql,StringBuffer str) {
		StringBuffer sql=new StringBuffer();//查询SQL
		StringBuffer commSql=new StringBuffer();//通用SQL
		StringBuffer sumSql=new StringBuffer();//合计SQL
		sql.append("rptSql=select ")
		   .append("sum(NVL(a.STORE_NUM,0))STORE_NUM,")
		   .append("sum(NVL(a.FREEZE_NUM,0))FREEZE_NUM,")
		   .append("sum(NVL(a.STORE_NUM,0) - NVL(a.FREEZE_NUM,0)) USER_NUM ");
		if(!StringUtil.isEmpty(str.toString())){
			sql.append(",").append(str.toString());
		}
		commSql.append(" from DRP_STORE_ACCT a  ")
			   .append(" left join DRP_STORE s on s.STORE_ID = a.STORE_ID ")
			   .append(" left join BASE_PRODUCT p on p.PRD_ID = a.PRD_ID ")
			   .append(" left join DRP_SPCL_TECH b on b.SPCL_TECH_ID=a.SPCL_TECH_ID ")
			   .append(" left join BASE_CHANN c on c.CHANN_ID=a.LEDGER_ID and c.DEL_FLAG=0 ");
		sql.append(commSql)
		   .append(whereSql);
		if(!StringUtil.isEmpty(str.toString())){
			sql.append(" group by ").append(str.toString());
		}
		sql.append(";");
		sumSql.append("sumSql=select sum(NVL(a.STORE_NUM,0))ALL_STORE_NUM,")
		      .append("sum(NVL(a.FREEZE_NUM, 0)) ALL_FREEZE_NUM,")
		      .append("sum(NVL(a.STORE_NUM, 0) - NVL(a.FREEZE_NUM, 0)) ALL_USER_NUM")
		      .append(commSql).append(whereSql);
		return sql.append(sumSql).toString();
	}
}
