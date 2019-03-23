/**
 * prjName:喜临门营销平台
 * ucName:预订单转订货订单
 * fileName:AdvctoorderModelGoods
*/
package com.hoperun.drp.sale.advctoorder.model;

import com.hoperun.commons.model.BaseModel;

public class AdvctoorderModelGoodChlds extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7902076360646664677L;
	/** 货品ID */
	private String PRD_ID;
	/** 货品编号 */
	private String PRD_NO;
	/** 货品名称 */
	private String PRD_NAME;
	/** 规格型号 */
	private String PRD_SPEC;
	/** 颜色 */
	private String PRD_COLOR;
	/** 品牌 */
	private String BRAND;
	/** 标准单位 */
	private String STD_UNIT;
	/** 特殊工艺 */
	private String SPCL_TECH_FLAG;
	/** 单价 */
	private String PRICE;
	/** 折扣率 */
	private String DECT_RATE;
	/** 折扣价 */
	private String DECT_PRICE;
	/** 订货数量 */
	private String ORDER_NUM;
	/** 折后金额 */
	private String DECT_AMOUNT;
	/** 删除标记 */
	private String DEL_FLAG;
	/**预订单明细ID**/
	private String ADVC_ORDER_DTL_ID;
	/** 预订单id */
	private String ADVC_ORDER_ID;
	/** 会话id */
	private String SESSION_ID;
	/**订单特殊工艺ID**/
	private String SPCL_TECH_ID;
	/**抵库数量*/
	private String PLE_REP;
	/**
	 * 交货日期
	 */
	private String ORDER_RECV_DATE;
	
	/**
	 * @return the oRDER_RECV_DATE
	 */
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	/**
	 * @param oRDERRECVDATE the oRDER_RECV_DATE to set
	 */
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
	/**
	 * @return the sESSION_ID
	 */
	public String getSESSION_ID() {
		return SESSION_ID;
	}
	/**
	 * @param sESSIONID the sESSION_ID to set
	 */
	public void setSESSION_ID(String sESSIONID) {
		SESSION_ID = sESSIONID;
	}
	/**
	 * @return the aDVC_ORDER_ID
	 */
	public String getADVC_ORDER_ID() {
		return ADVC_ORDER_ID;
	}
	/**
	 * @param aDVCORDERID the aDVC_ORDER_ID to set
	 */
	public void setADVC_ORDER_ID(String aDVCORDERID) {
		ADVC_ORDER_ID = aDVCORDERID;
	}
	/**
	 * @return the pRD_ID
	 */
	public String getPRD_ID() {
		return PRD_ID;
	}
	/**
	 * @param pRDID the pRD_ID to set
	 */
	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}
	/**
	 * @return the pRD_NO
	 */
	public String getPRD_NO() {
		return PRD_NO;
	}
	/**
	 * @param pRDNO the pRD_NO to set
	 */
	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}
	/**
	 * @return the pRD_NAME
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}
	/**
	 * @param pRDNAME the pRD_NAME to set
	 */
	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}
	/**
	 * @return the pRD_SPEC
	 */
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}
	/**
	 * @param pRDSPEC the pRD_SPEC to set
	 */
	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}
	/**
	 * @return the pRD_COLOR
	 */
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}
	/**
	 * @param pRDCOLOR the pRD_COLOR to set
	 */
	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
	}
	/**
	 * @return the bRAND
	 */
	public String getBRAND() {
		return BRAND;
	}
	/**
	 * @param bRAND the bRAND to set
	 */
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}
	/**
	 * @return the sTD_UNIT
	 */
	public String getSTD_UNIT() {
		return STD_UNIT;
	}
	/**
	 * @param sTDUNIT the sTD_UNIT to set
	 */
	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}

	/**
	 * @return the sPCL_TECH_FLAG
	 */
	public String getSPCL_TECH_FLAG() {
		return SPCL_TECH_FLAG;
	}
	/**
	 * @param sPCLTECHFLAG the sPCL_TECH_FLAG to set
	 */
	public void setSPCL_TECH_FLAG(String sPCLTECHFLAG) {
		SPCL_TECH_FLAG = sPCLTECHFLAG;
	}
	/**
	 * @return the pRICE
	 */
	public String getPRICE() {
		return PRICE;
	}
	/**
	 * @param pRICE the pRICE to set
	 */
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	/**
	 * @return the dECT_RATE
	 */
	public String getDECT_RATE() {
		return DECT_RATE;
	}
	/**
	 * @param dECTRATE the dECT_RATE to set
	 */
	public void setDECT_RATE(String dECTRATE) {
		DECT_RATE = dECTRATE;
	}
	/**
	 * @return the dECT_PRICE
	 */
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}
	/**
	 * @param dECTPRICE the dECT_PRICE to set
	 */
	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}
	/**
	 * @return the oRDER_NUM
	 */
	public String getORDER_NUM() {
		return ORDER_NUM;
	}
	/**
	 * @param oRDERNUM the oRDER_NUM to set
	 */
	public void setORDER_NUM(String oRDERNUM) {
		ORDER_NUM = oRDERNUM;
	}
	/**
	 * @return the dECT_AMOUNT
	 */
	public String getDECT_AMOUNT() {
		return DECT_AMOUNT;
	}
	/**
	 * @param dECTAMOUNT the dECT_AMOUNT to set
	 */
	public void setDECT_AMOUNT(String dECTAMOUNT) {
		DECT_AMOUNT = dECTAMOUNT;
	}
	/**
	 * @return the dEL_FLAG
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	/**
	 * @return the aDVC_ORDER_DTL_ID
	 */
	public String getADVC_ORDER_DTL_ID() {
		return ADVC_ORDER_DTL_ID;
	}
	/**
	 * @param aDVCORDERDTLID the aDVC_ORDER_DTL_ID to set
	 */
	public void setADVC_ORDER_DTL_ID(String aDVCORDERDTLID) {
		ADVC_ORDER_DTL_ID = aDVCORDERDTLID;
	}
	/**
	 * @return the sPCL_TECH_ID
	 */
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	/**
	 * @param sPCLTECHID the sPCL_TECH_ID to set
	 */
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}
	public String getPLE_REP() {
		return PLE_REP;
	}
	public void setPLE_REP(String pLEREP) {
		PLE_REP = pLEREP;
	}
	

}
