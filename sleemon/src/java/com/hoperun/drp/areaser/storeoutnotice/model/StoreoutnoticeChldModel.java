package com.hoperun.drp.areaser.storeoutnotice.model;

import com.hoperun.commons.model.BaseModel;

public class StoreoutnoticeChldModel extends BaseModel{
	/**
	 * 销售出库通知单ID
	 */
	private String STOREOUT_NOTICE_DTL_ID;
	/**
	 * 销售出库通知单ID
	 */
	private String STOREOUT_NOTICE_ID;
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
	 * 通知出库数量
	 */
	private String NOTICE_NUM;
	/**
	 * 单价
	 */
	private String PRICE;
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	/**
	 * 折扣价
	 */
	private String DECT_PRICE;
	/**
	 * 折后金额
	 */
	private String DECT_AMOUNT;
	/**
	 * 备注
	 */
	private String REMARK;
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	/**
	 * 来源单据明细ID
	 */
	private String FROM_BILL_DTL_ID;
	/**
	 * 订单特殊工艺ID
	 */
	private String SPCL_TECH_ID;
	/**
	 * 实际出库数量
	 */
	private String REAL_NUM;
	
	/**
	 * @return the sTOREOUT_NOTICE_DTL_ID
	 */
	public String getSTOREOUT_NOTICE_DTL_ID() {
		return STOREOUT_NOTICE_DTL_ID;
	}
	/**
	 * @param sTOREOUTNOTICEDTLID the sTOREOUT_NOTICE_DTL_ID to set
	 */
	public void setSTOREOUT_NOTICE_DTL_ID(String sTOREOUTNOTICEDTLID) {
		STOREOUT_NOTICE_DTL_ID = sTOREOUTNOTICEDTLID;
	}
	/**
	 * @return the sTOREOUT_NOTICE_ID
	 */
	public String getSTOREOUT_NOTICE_ID() {
		return STOREOUT_NOTICE_ID;
	}
	/**
	 * @param sTOREOUTNOTICEID the sTOREOUT_NOTICE_ID to set
	 */
	public void setSTOREOUT_NOTICE_ID(String sTOREOUTNOTICEID) {
		STOREOUT_NOTICE_ID = sTOREOUTNOTICEID;
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
	 * @return the nOTICE_NUM
	 */
	public String getNOTICE_NUM() {
		return NOTICE_NUM;
	}
	/**
	 * @param nOTICENUM the nOTICE_NUM to set
	 */
	public void setNOTICE_NUM(String nOTICENUM) {
		NOTICE_NUM = nOTICENUM;
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
	 * @return the rEAL_NUM
	 */
	public String getREAL_NUM() {
		return REAL_NUM;
	}
	/**
	 * @param rEALNUM the rEAL_NUM to set
	 */
	public void setREAL_NUM(String rEALNUM) {
		REAL_NUM = rEALNUM;
	}
	
	
}
