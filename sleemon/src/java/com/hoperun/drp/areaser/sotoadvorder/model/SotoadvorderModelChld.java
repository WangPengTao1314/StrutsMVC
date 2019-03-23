package com.hoperun.drp.areaser.sotoadvorder.model;

import com.hoperun.commons.model.BaseModel;

public class SotoadvorderModelChld extends BaseModel{
	/**
	 * 销售订单明细ID
	 */
	private String SALE_ORDER_DTL_ID;
	/**
	 * 销售订单ID
	 */
	private String SALE_ORDER_ID;
	/**
	 * 货品ID
	 */
	private String PRD_ID;
	/**
	 * 货品编号
	 */
	private String PRD_NO;
	/**
	 * 货品名称
	 */
	private String PRD_NAME;
	/**
	 * 规格型号
	 */
	private String PRD_SPEC;
	/**
	 * 颜色
	 */
	private String PRD_COLOR;
	/**
	 * 品牌
	 */
	private String BRAND;
	/**
	 * 标准单位
	 */
	private String STD_UNIT;
	/**
	 * 是否非标
	 */
	private String IS_NO_STAND_FLAG;
	/**
	 * 单价
	 */
	private String PRICE;
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	/**
	 * 折后单价
	 */
	private String DECT_PRICE;
	/**
	 * 订货数量
	 */
	private String ORDER_NUM;
	/**
	 * 订货金额
	 */
	private String ORDER_AMOUNT;
	/**
	 * 预计发货日期
	 */
	private String ADVC_SEND_DATE;
	/**
	 * 备注
	 */
	private String REMARK;
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	/**
	 * 会话ID
	 */
	private String SESSION_ID;
	/**
	 * 特殊工艺加价倍数
	 */
	private String TECH_PRICE_MULT;
	/**
	 * 抵库数量
	 */
	private String PLE_REP;
	/**
	 * 来源单据明细ID
	 */
	private String FROM_BILL_DTL_ID;
	/**
	 * 取消标记
	 */
	private String CANCEL_FLAG;
	/**
	 * 已转订货数量
	 */
	private String CHANGE_NUM;
	/**
	 * 已实际发货数量
	 */
	private String SENDED_NUM;
	/**
	 * 抵库数量
	 */
	private String STORED_NUM;
	/**
	 * 体积
	 */
	private String VOLUME;
	/**
	 * 订单特殊工艺ID
	 */
	private String SPCL_TECH_ID;
	/**
	 * 订货订单编号
	 */
	private String GOODS_ORDER_NO;
	/**
	 * @return the sALE_ORDER_DTL_ID
	 */
	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}
	/**
	 * @param sALEORDERDTLID the sALE_ORDER_DTL_ID to set
	 */
	public void setSALE_ORDER_DTL_ID(String sALEORDERDTLID) {
		SALE_ORDER_DTL_ID = sALEORDERDTLID;
	}
	/**
	 * @return the sALE_ORDER_ID
	 */
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	/**
	 * @param sALEORDERID the sALE_ORDER_ID to set
	 */
	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
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
	 * @return the iS_NO_STAND_FLAG
	 */
	public String getIS_NO_STAND_FLAG() {
		return IS_NO_STAND_FLAG;
	}
	/**
	 * @param iSNOSTANDFLAG the iS_NO_STAND_FLAG to set
	 */
	public void setIS_NO_STAND_FLAG(String iSNOSTANDFLAG) {
		IS_NO_STAND_FLAG = iSNOSTANDFLAG;
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
	 * @return the oRDER_AMOUNT
	 */
	public String getORDER_AMOUNT() {
		return ORDER_AMOUNT;
	}
	/**
	 * @param oRDERAMOUNT the oRDER_AMOUNT to set
	 */
	public void setORDER_AMOUNT(String oRDERAMOUNT) {
		ORDER_AMOUNT = oRDERAMOUNT;
	}
	/**
	 * @return the aDVC_SEND_DATE
	 */
	public String getADVC_SEND_DATE() {
		return ADVC_SEND_DATE;
	}
	/**
	 * @param aDVCSENDDATE the aDVC_SEND_DATE to set
	 */
	public void setADVC_SEND_DATE(String aDVCSENDDATE) {
		ADVC_SEND_DATE = aDVCSENDDATE;
	}
	/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}
	/**
	 * @param rEMARK the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
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
	 * @return the tECH_PRICE_MULT
	 */
	public String getTECH_PRICE_MULT() {
		return TECH_PRICE_MULT;
	}
	/**
	 * @param tECHPRICEMULT the tECH_PRICE_MULT to set
	 */
	public void setTECH_PRICE_MULT(String tECHPRICEMULT) {
		TECH_PRICE_MULT = tECHPRICEMULT;
	}
	/**
	 * @return the fROM_BILL_DTL_ID
	 */
	public String getFROM_BILL_DTL_ID() {
		return FROM_BILL_DTL_ID;
	}
	/**
	 * @param fROMBILLDTLID the fROM_BILL_DTL_ID to set
	 */
	public void setFROM_BILL_DTL_ID(String fROMBILLDTLID) {
		FROM_BILL_DTL_ID = fROMBILLDTLID;
	}
	/**
	 * @return the cANCEL_FLAG
	 */
	public String getCANCEL_FLAG() {
		return CANCEL_FLAG;
	}
	/**
	 * @param cANCELFLAG the cANCEL_FLAG to set
	 */
	public void setCANCEL_FLAG(String cANCELFLAG) {
		CANCEL_FLAG = cANCELFLAG;
	}
	/**
	 * @return the cHANGE_NUM
	 */
	public String getCHANGE_NUM() {
		return CHANGE_NUM;
	}
	/**
	 * @param cHANGENUM the cHANGE_NUM to set
	 */
	public void setCHANGE_NUM(String cHANGENUM) {
		CHANGE_NUM = cHANGENUM;
	}
	/**
	 * @return the sENDED_NUM
	 */
	public String getSENDED_NUM() {
		return SENDED_NUM;
	}
	/**
	 * @param sENDEDNUM the sENDED_NUM to set
	 */
	public void setSENDED_NUM(String sENDEDNUM) {
		SENDED_NUM = sENDEDNUM;
	}
	/**
	 * @return the sTORED_NUM
	 */
	public String getSTORED_NUM() {
		return STORED_NUM;
	}
	/**
	 * @param sTOREDNUM the sTORED_NUM to set
	 */
	public void setSTORED_NUM(String sTOREDNUM) {
		STORED_NUM = sTOREDNUM;
	}
	/**
	 * @return the vOLUME
	 */
	public String getVOLUME() {
		return VOLUME;
	}
	/**
	 * @param vOLUME the vOLUME to set
	 */
	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
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
	/**
	 * @return the gOODS_ORDER_NO
	 */
	public String getGOODS_ORDER_NO() {
		return GOODS_ORDER_NO;
	}
	/**
	 * @param gOODSORDERNO the gOODS_ORDER_NO to set
	 */
	public void setGOODS_ORDER_NO(String gOODSORDERNO) {
		GOODS_ORDER_NO = gOODSORDERNO;
	}
	/**
	 * @return the pLE_REP
	 */
	public String getPLE_REP() {
		return PLE_REP;
	}
	/**
	 * @param pLEREP the pLE_REP to set
	 */
	public void setPLE_REP(String pLEREP) {
		PLE_REP = pLEREP;
	}
	
}
