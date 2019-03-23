package com.hoperun.drp.base.retalprice.model;

import com.hoperun.commons.model.BaseModel;

public class RetalpriceModel extends BaseModel{
	/**
	 * 货品零售价设置ID
	 */
	private String RETAL_PRICE_ID;
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
	 * 品牌
	 */
	private String BRAND;
	/**
	 * 出厂价
	 */
	private String FACT_PRICE;
	private String LEDGER_ID;
	private String LEDGER_NAME;
	/**
	 * @return the rETAL_PRICE_ID
	 */
	public String getRETAL_PRICE_ID() {
		return RETAL_PRICE_ID;
	}
	/**
	 * @param rETALPRICEID the rETAL_PRICE_ID to set
	 */
	public void setRETAL_PRICE_ID(String rETALPRICEID) {
		RETAL_PRICE_ID = rETALPRICEID;
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
	 * @return the fACT_PRICE
	 */
	public String getFACT_PRICE() {
		return FACT_PRICE;
	}
	/**
	 * @param fACTPRICE the fACT_PRICE to set
	 */
	public void setFACT_PRICE(String fACTPRICE) {
		FACT_PRICE = fACTPRICE;
	}
	/**
	 * @return the lEDGER_ID
	 */
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	/**
	 * @param lEDGERID the lEDGER_ID to set
	 */
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}
	/**
	 * @return the lEDGER_NAME
	 */
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	/**
	 * @param lEDGERNAME the lEDGER_NAME to set
	 */
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}
	
}
