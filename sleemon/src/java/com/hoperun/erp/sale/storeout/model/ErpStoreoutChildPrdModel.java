package com.hoperun.erp.sale.storeout.model;

import com.hoperun.commons.model.BaseModel;

public class ErpStoreoutChildPrdModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/** 出库单货品明细ID **/
	private String STOREOUT_PRD_DTL_ID;
	/** 出库单ID **/
	private String STOREOUT_ID;
	/** 发运单明细ID **/
	private String DELIVER_ORDER_DTL_ID;
	/** 发运单ID **/
	private String DELIVER_ORDER_ID;
	/** 货品ID**/
	private String PRD_ID;
	/** 货品编号**/
	private String PRD_NO;
	/** 货品名称 **/
	private String PRD_NAME;
	/** 货品I规格型号 **/
	private String PRD_SPEC;
	/** 花号 **/
	private String PRD_COLOR;
	/** 品牌**/
	private String BRAND;
	/** 标准单位**/
	private String STD_UNIT;
	/** 出库数量**/
	private String STOREOUT_NUM;
	/** 单价 **/
	private String PRICE;
	/** 折扣率 **/
	private String DECT_RATE;
	/** 折后单价 **/
	private String DECT_PRICE;
	/** 折后金额 **/
	private String DECT_AMOUNT;
	/**总体积**/
	private String VOLUMES;
	/**销售订单明细ID**/
	private String SALE_ORDER_DTL_ID;
	/**U9订单标号**/
	private String U9_SALE_ORDER_NO;
	/**U9订单行编号**/
	private String U9_SALE_ORDER_DTL_NO;
	/**删除标记**/
	private String DEL_FLAG;
	/** U9出库单行ID **/
	private String U9_SM_DTL_ID;
	
	
 
	public String getDELIVER_ORDER_DTL_ID() {
		return DELIVER_ORDER_DTL_ID;
	}
	public void setDELIVER_ORDER_DTL_ID(String dELIVERORDERDTLID) {
		DELIVER_ORDER_DTL_ID = dELIVERORDERDTLID;
	}
	public String getDELIVER_ORDER_ID() {
		return DELIVER_ORDER_ID;
	}
	public void setDELIVER_ORDER_ID(String dELIVERORDERID) {
		DELIVER_ORDER_ID = dELIVERORDERID;
	}
	public String getPRD_ID() {
		return PRD_ID;
	}
	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}
	public String getPRD_NO() {
		return PRD_NO;
	}
	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}
	public String getPRD_NAME() {
		return PRD_NAME;
	}
	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}
	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}
	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
	}
	public String getBRAND() {
		return BRAND;
	}
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}
	public String getSTD_UNIT() {
		return STD_UNIT;
	}
	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}
 
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getDECT_RATE() {
		return DECT_RATE;
	}
	public void setDECT_RATE(String dECTRATE) {
		DECT_RATE = dECTRATE;
	}
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}
	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}
	public String getDECT_AMOUNT() {
		return DECT_AMOUNT;
	}
	public void setDECT_AMOUNT(String dECTAMOUNT) {
		DECT_AMOUNT = dECTAMOUNT;
	}
	public String getSTOREOUT_PRD_DTL_ID() {
		return STOREOUT_PRD_DTL_ID;
	}
	public void setSTOREOUT_PRD_DTL_ID(String sTOREOUTPRDDTLID) {
		STOREOUT_PRD_DTL_ID = sTOREOUTPRDDTLID;
	}
 
	public String getVOLUMES() {
		return VOLUMES;
	}
	public void setVOLUMES(String vOLUMES) {
		VOLUMES = vOLUMES;
	}
	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}
	public void setSALE_ORDER_DTL_ID(String sALEORDERDTLID) {
		SALE_ORDER_DTL_ID = sALEORDERDTLID;
	}
	public String getU9_SALE_ORDER_NO() {
		return U9_SALE_ORDER_NO;
	}
	public void setU9_SALE_ORDER_NO(String u9SALEORDERNO) {
		U9_SALE_ORDER_NO = u9SALEORDERNO;
	}
	public String getU9_SALE_ORDER_DTL_NO() {
		return U9_SALE_ORDER_DTL_NO;
	}
	public void setU9_SALE_ORDER_DTL_NO(String u9SALEORDERDTLNO) {
		U9_SALE_ORDER_DTL_NO = u9SALEORDERDTLNO;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getSTOREOUT_ID() {
		return STOREOUT_ID;
	}
	public void setSTOREOUT_ID(String sTOREOUTID) {
		STOREOUT_ID = sTOREOUTID;
	}
	public String getU9_SM_DTL_ID() {
		return U9_SM_DTL_ID;
	}
	public void setU9_SM_DTL_ID(String u9SMDTLID) {
		U9_SM_DTL_ID = u9SMDTLID;
	}
	public String getSTOREOUT_NUM() {
		return STOREOUT_NUM;
	}
	public void setSTOREOUT_NUM(String sTOREOUTNUM) {
		STOREOUT_NUM = sTOREOUTNUM;
	}
	 
	
	
	
	

}
