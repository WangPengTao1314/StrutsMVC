package com.hoperun.erp.sale.channdiscount.model;

import com.hoperun.commons.model.BaseModel;

public class ChannDiscountModel  extends BaseModel{
	/**
	 * 渠道折扣ID
	 */
	private String CHANN_DSCT_ID;
	/**
	 * 渠道ID
	 */
	private String CHANN_ID;
	/**
	 * 渠道编号
	 */
	private String CHANN_NO;
	/**
	 * 渠道名称
	 */
	private String CHANN_NAME;
	/**
	 * 渠道类型
	 */
	private String CHANN_TYPE;
	/**
	 * 渠道级别
	 */
	private String CHAA_LVL;
	/**
	 * 折扣类型
	 */
	private String DECT_TYPE;
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	/**
	 * @return the cHANN_DSCT_ID
	 */
	public String getCHANN_DSCT_ID() {
		return CHANN_DSCT_ID;
	}
	/**
	 * @param cHANNDSCTID the cHANN_DSCT_ID to set
	 */
	public void setCHANN_DSCT_ID(String cHANNDSCTID) {
		CHANN_DSCT_ID = cHANNDSCTID;
	}
	/**
	 * @return the cHANN_ID
	 */
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	/**
	 * @param cHANNID the cHANN_ID to set
	 */
	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}
	/**
	 * @return the cHANN_NO
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	/**
	 * @param cHANNNO the cHANN_NO to set
	 */
	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}
	/**
	 * @return the cHANN_NAME
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	/**
	 * @param cHANNNAME the cHANN_NAME to set
	 */
	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}
	/**
	 * @return the cHANN_TYPE
	 */
	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}
	/**
	 * @param cHANNTYPE the cHANN_TYPE to set
	 */
	public void setCHANN_TYPE(String cHANNTYPE) {
		CHANN_TYPE = cHANNTYPE;
	}
	/**
	 * @return the cHAA_LVL
	 */
	public String getCHAA_LVL() {
		return CHAA_LVL;
	}
	/**
	 * @param cHAALVL the cHAA_LVL to set
	 */
	public void setCHAA_LVL(String cHAALVL) {
		CHAA_LVL = cHAALVL;
	}
	/**
	 * @return the dECT_TYPE
	 */
	public String getDECT_TYPE() {
		return DECT_TYPE;
	}
	/**
	 * @param dECTTYPE the dECT_TYPE to set
	 */
	public void setDECT_TYPE(String dECTTYPE) {
		DECT_TYPE = dECTTYPE;
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
}
