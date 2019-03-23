package com.hoperun.drp.base.drpsaleprice.model;

import com.hoperun.commons.model.BaseModel;

public class DrpsalepriceModel extends BaseModel{
	/**
	 * 渠道销售价格ID
	 */
	private String SALE_PRICE_ID;
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
	 * 出厂价
	 */
	private String FACT_PRICE;
	/**
	 * 折扣类型
	 */
	private String DECT_TYPE;
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	/**
	 * 折扣价
	 */
	private String DECT_PRICE;
	/**
	 * 供货价格
	 */
	private String PRVD_PRICE;
	public String getSALE_PRICE_ID() {
		return SALE_PRICE_ID;
	}
	public void setSALE_PRICE_ID(String sALEPRICEID) {
		SALE_PRICE_ID = sALEPRICEID;
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
	public String getFACT_PRICE() {
		return FACT_PRICE;
	}
	public void setFACT_PRICE(String fACTPRICE) {
		FACT_PRICE = fACTPRICE;
	}
	public String getDECT_TYPE() {
		return DECT_TYPE;
	}
	public void setDECT_TYPE(String dECTTYPE) {
		DECT_TYPE = dECTTYPE;
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
	public String getPRVD_PRICE() {
		return PRVD_PRICE;
	}
	public void setPRVD_PRICE(String pRVDPRICE) {
		PRVD_PRICE = pRVDPRICE;
	}
	
}
