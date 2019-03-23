package com.hoperun.report.saleordersendout.service.impl;

import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.BaseRptService;
import com.hoperun.report.saleordersendout.service.SaleorderSendoutService;

public class SaleorderSendoutServiceImpl extends BaseRptService implements SaleorderSendoutService{
	
	/**
	 * 销售订单出货明细
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {
		String queryParam = "rptSql=select  a.SALE_ORDER_NO, a.AREA_NAME,"+
		 "a.WAREA_NAME AREA_NAME_P,"+
		 "g.AREA_MANAGE_NAME CRE_NAME, a.ORDER_CHANN_NO,"+
         "a.ORDER_CHANN_NAME,"+
         "to_char(temp.ADVC_SEND_DATE, 'yyyy-mm-dd') ADVC_SEND_DATE,"+
		 "to_char(a.ORDER_DATE,'yyyy-mm-dd')ORDER_DATE,"+
		 "to_char(a.CRE_TIME,'yyyy-mm-dd')AUDIT_DATE,"+
		 "b.PRD_NO,"+
		 "b.PRD_NAME,"+
		 "b.PRD_SPEC,"+
		 "b.BRAND,"+
		 "b.ORDER_NUM,"+
		 "NVL(temp.REAL_SEND_NUM,0) SENDED_NUM,"+
		 "(NVL(b.ORDER_NUM,0)-NVL(temp.REAL_SEND_NUM,0))NO_SEND_NUM,"+
		 "decode(b.IS_FREE_FLAG,1,0,b.DECT_PRICE) PRICE,"+
		 "decode(b.IS_FREE_FLAG,1,0,b.DECT_PRICE)*(NVL(b.ORDER_NUM,0)-NVL(temp.REAL_SEND_NUM,0)) NO_SEND_AMOUNT,"+
		 "decode(b.IS_FREE_FLAG,1,0,b.DECT_PRICE)*NVL(temp.REAL_SEND_NUM, 0) SENDED_AMOUNT,"+
//		 
//		 "b.ORDER_AMOUNT,"+
		 
		 "decode(b.IS_FREE_FLAG,1,0,b.DECT_PRICE)*NVL(b.ORDER_NUM,0) ORDER_AMOUNT ,"+
		 "b.VOLUME,"+
		 "a.RECV_ADDR_NO,"+
//		 "d.DELIVER_DTL_ADDR,"+
		 "a.RECV_ADDR DELIVER_DTL_ADDR,"+
		 "f.PRD_NAME AS  PRD_TYPE_NAME,"+
		 "a.STATE,"+
		 "c.SPCL_DTL_REMARK SPCL_REMARK, "+
		 "a.SHIP_POINT_NAME, "+
		 "rownum"+
		 " from "+
		 " ERP_SALE_ORDER a "+
//         " left join BASE_AREA area on a.AREA_ID=area.AREA_ID"+
		 " left join ERP_SALE_ORDER_DTL b on a.SALE_ORDER_ID=b.SALE_ORDER_ID and b.DEL_FLAG=0 "+
		 " left join DRP_SPCL_TECH c on b.SPCL_TECH_ID=c.SPCL_TECH_ID "+
//		 " left join BASE_DELIVER_ADDR d on a.RECV_ADDR_NO =d.DELIVER_ADDR_NO and a.ORDER_CHANN_ID=d.CHANN_ID and d.STATE='启用'  and d.DEL_FLAG =0 "+
		 " left join BASE_CHANN g on g.CHANN_ID =a.ORDER_CHANN_ID and g.DEL_FLAG=0 "+
		 " left join BASE_PRODUCT e on b.PRD_ID=e.PRD_ID "+
		 " left join BASE_PRODUCT f on e.PAR_PRD_ID=f.PRD_ID"+
		 " left join "+  
		 " ( "+
		 "      select n.SALE_ORDER_DTL_ID, "+
		 "      MAX(m.ADVC_SEND_DATE) ADVC_SEND_DATE,sum((case "+
		 "                                  when m.STATE in ('已提交库房', "+
		 "                                                   '已发货', "+
		 "                                                   '已收货', "+
		 "                                                   '待收货',"+
		 "                                                  '部分发货') then "+
		 "                                   NVL(REAL_SEND_NUM, 0) "+
		 "                                 else "+
		 "                                   0 "+
		  "                               end)) REAL_SEND_NUM "+
		 "                       from ERP_DELIVER_ORDER m, ERP_DELIVER_ORDER_DTL n "+
		 "                      where m.DELIVER_ORDER_ID = n.DELIVER_ORDER_ID "+
		  "                       and m.DEL_FLAG = 0 "+
		  "                       and n.DEL_FLAG = 0 "+
		 "                      group by n.SALE_ORDER_DTL_ID "+
		"  ) temp  on  b.SALE_ORDER_DTL_ID=temp.SALE_ORDER_DTL_ID "
		 ;
		 
		return queryParam + whereSql;
	}
	
	/**
	 * 生成新发运单报表
	 * @param whereSql
	 * @return
	 */
	public String createNewSql(String whereSql){
		StringBuffer sql=new StringBuffer();//查询SQL
		StringBuffer commSql=new StringBuffer();//通用表连接
		StringBuffer sumSql=new StringBuffer();//合计SQL
		commSql.append(" from ERP_SALE_ORDER a ")
//		   .append(" left join BASE_AREA area on a.AREA_ID = area.AREA_ID ")
		   .append(" left join ERP_SALE_ORDER_DTL b on a.SALE_ORDER_ID = b.SALE_ORDER_ID and b.DEL_FLAG = 0 ")
		   .append("  left join DRP_SPCL_TECH c on b.SPCL_TECH_ID = c.SPCL_TECH_ID ")
//		   .append(" left join BASE_DELIVER_ADDR d on a.RECV_ADDR_NO = d.DELIVER_ADDR_NO and d.STATE='启用'  and a.ORDER_CHANN_ID = d.CHANN_ID  and d.DEL_FLAG =0  ")
		   .append(" left join BASE_CHANN g on g.CHANN_ID = a.ORDER_CHANN_ID  and g.DEL_FLAG = 0 ")
		   .append(" left join BASE_PRODUCT e on b.PRD_ID = e.PRD_ID ")
		   .append(" left join BASE_PRODUCT f on e.PAR_PRD_ID = f.PRD_ID ");
		sql.append("rptSql= select a.SALE_ORDER_NO, ")
		   .append("a.AREA_NAME,")
//		   .append("(case when area.AREA_NAME_P is null then a.AREA_NAME else area.AREA_NAME_P  end) AREA_NAME_P,")
		   .append("a.WAREA_NAME AREA_NAME_P,")
		   .append("g.AREA_MANAGE_NAME CRE_NAME,")
		   .append("g.PROV,")
		   .append("a.ORDER_CHANN_NO,")
		   .append("a.ORDER_CHANN_NAME,")
		   .append("to_char(b.ADVC_SEND_DATE, 'yyyy-mm-dd') ADVC_SEND_DATE,")
		   .append("to_char(a.ORDER_DATE, 'yyyy-mm-dd') ORDER_DATE,")
		   .append("to_char(a.CRE_TIME, 'yyyy-mm-dd') AUDIT_DATE,")
		   .append("b.PRD_NO,")
		   .append(" b.PRD_NAME,")
		   .append(" b.PRD_SPEC,")
		   .append(" b.BRAND,")
		   .append("b.ORDER_NUM,")
		   .append(" NVL(b.SENDED_NUM, 0) SENDED_NUM,")
		   .append("(NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0)) NO_SEND_NUM,")
		   .append("decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) PRICE,")
		   .append("decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) *(NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0)) NO_SEND_AMOUNT,")
		   .append("decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * NVL(b.SENDED_NUM, 0) SENDED_AMOUNT,")
		   .append("decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * NVL(b.ORDER_NUM, 0) ORDER_AMOUNT,")
		   .append("b.VOLUME,")
		   .append("a.RECV_ADDR_NO,")
		   .append("a.RECV_ADDR DELIVER_DTL_ADDR,")
		   .append("f.PRD_NAME AS PRD_TYPE_NAME,")
		   .append("a.STATE,")
		   .append("c.SPCL_DTL_REMARK SPCL_REMARK,")
		   .append("a.SHIP_POINT_NAME,")
		   .append("decode(b.IS_TOSS_FLAG, 1, b.PRDC_POINT_NAME, a.SHIP_POINT_NAME) PRDC_POINT_NAME,")
		   .append("(case when b.IS_BACKUP_FLAG = 1 then '是' else '否' end )IS_BACKUP_FLAG,")
		   .append("a.BILL_TYPE,")
		   .append("rownum ")
		   .append(commSql).append(whereSql)
		   .append(" union all ")
		   .append(" select '合计','','','','','','','','','','','','','', ")
		   .append("sum(NVL(b.ORDER_NUM, 0)) ORDER_NUM,")
		   .append("sum(NVL(b.SENDED_NUM, 0)) SENDED_NUM,")
		   .append("sum((NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0))) NO_SEND_NUM,null,")
		   .append("sum(decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * (NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0))) NO_SEND_AMOUNT,")
		   .append("sum(decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) *  NVL(b.SENDED_NUM, 0)) SENDED_AMOUNT,")
		   .append("sum(decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * NVL(b.ORDER_NUM, 0)) ORDER_AMOUNT,")
		   .append("null,'','','','','','','','','',null  ")
		   .append(commSql).append(whereSql);
//		   
//		sumSql.append("select sum(NVL(b.ORDER_NUM,0)) ALL_ORDER_NUM,")
//		      .append("sum(NVL(b.SENDED_NUM, 0)) ALL_SENDED_NUM,")
//		      .append("sum((NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0))) ALL_NO_SEND_NUM,")
//		      .append("sum(decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * (NVL(b.ORDER_NUM, 0) - NVL(b.SENDED_NUM, 0))) ALL_NO_SEND_AMOUNT,")
//		      .append("sum(decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * NVL(b.SENDED_NUM, 0)) ALL_SENDED_AMOUNT,")
//		      .append("sum(decode(b.IS_FREE_FLAG, 1, 0, b.DECT_PRICE) * NVL(b.ORDER_NUM, 0)) ALL_ORDER_AMOUNT ");
		
		return sql.toString();
		   
	}
}
