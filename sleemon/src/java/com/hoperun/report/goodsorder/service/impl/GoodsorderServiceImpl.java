package com.hoperun.report.goodsorder.service.impl;

import com.hoperun.report.BaseRptService;
import com.hoperun.report.goodsorder.service.GoodsorderService;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public class GoodsorderServiceImpl  extends BaseRptService implements GoodsorderService {

	/**
	 * 生成报表sql
	 * @param whereSql 条件语句
	 * @return
	 */	
	public String createSql(String whereSql) {
//		String queryParam = " a.GOODS_ORDER_NO,b.AREA_NAME,a.ORDER_CHANN_NO,a.ORDER_CHANN_NAME,b.AREA_MANAGE_NAME,      to_char(a.CRE_TIME,'yyyy-mm-dd')BUSS_DATE,d.PAR_PRD_NAME,c.PRD_NO,c.PRD_NAME,c.BRAND,      c.PRD_SPEC,NVL( c.ORDER_NUM,0) ORDER_NUM, c.DECT_PRICE, NVL(c.ORDER_AMOUNT,0.0)ORDER_AMOUNT,decode(c.IS_NO_STAND_FLAG,1,'有','无')SPCL_TECH,      NVL(e.REMARK,'')REMARK  ";
//		String sumParam = " '合计' GOODS_ORDER_NO,  '' AREA_NAME,  '' ORDER_CHANN_NO, '' ORDER_CHANN_NAME, '' AREA_MANAGE_NAME, ''  BUSS_DATE,  '' PAR_PRD_NAME,  '' PRD_NO, '' PRD_NAME,   '' BRAND,  '' PRD_SPEC,  sum(NVL(c.ORDER_NUM, 0)) ORDER_NUM,       null  DECT_PRICE,        sum(NVL(c.ORDER_AMOUNT, 0.0)) ORDER_AMOUNT,        ''  SPCL_TECH,        ''  REMARK   ";
		String queryParam = " a.GOODS_ORDER_NO,b.AREA_NAME,a.ORDER_CHANN_NO,a.ORDER_CHANN_NAME,b.AREA_MANAGE_NAME,      to_char(a.CRE_TIME,'yyyy-mm-dd')BUSS_DATE,d.PAR_PRD_NAME,c.PRD_NO,c.PRD_NAME,c.BRAND,      c.PRD_SPEC,NVL( c.ORDER_NUM,0) ORDER_NUM, c.DECT_PRICE, NVL(c.ORDER_AMOUNT,0.0)ORDER_AMOUNT,e.SPCL_DTL_REMARK SPCL_TECH,a.STATE";
		String sumParam = " '合计' GOODS_ORDER_NO,  '' AREA_NAME,  '' ORDER_CHANN_NO, '' ORDER_CHANN_NAME, '' AREA_MANAGE_NAME, ''  BUSS_DATE,  '' PAR_PRD_NAME,  '' PRD_NO, '' PRD_NAME,   '' BRAND,  '' PRD_SPEC,  sum(NVL(c.ORDER_NUM, 0)) ORDER_NUM,       null  DECT_PRICE,        sum(NVL(c.ORDER_AMOUNT, 0.0)) ORDER_AMOUNT,        ''  SPCL_TECH,a.STATE";
		String fromSql = " from      DRP_GOODS_ORDER a           left join BASE_CHANN b on a.ORDER_CHANN_ID =b.CHANN_ID    left join  BASE_AREA area on b.AREA_ID=area.AREA_ID       left join DRP_GOODS_ORDER_DTL c on a.GOODS_ORDER_ID=c.GOODS_ORDER_ID           left join BASE_PRODUCT d on c.PRD_ID=d.PRD_ID           left join DRP_SPCL_TECH e on c.SPCL_TECH_ID=e.SPCL_TECH_ID  ";
		
		String orderParam = " BUSS_DATE asc ";
				
		return formatSql(queryParam,sumParam,orderParam,fromSql,whereSql);
	}

}
