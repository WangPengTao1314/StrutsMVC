/**
 * 项目名称：平台
 * 系统名：信用额度设定
 * 文件名：CreditModel.java
 */
package com.hoperun.erp.sale.credit.model;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandModel.
 * 
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
 */
public class CreditModel {
	
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
	 * 渠道简称
	 */
	private String CHANN_ABBR;
	
	/**
	 * 渠道类型
	 */
	private String CHANN_TYPE;
	
	/**
	 * 渠道级别
	 */
	private String CHAA_LVL;
	
	/**
	 * 区域编号
	 */
	private String AREA_NO;
	
	/**
	 * 区域名称
	 */
	private String AREA_NAME;
	/**
	 * CREDIT_CURRT
	 */
	private String CREDIT_CURRT;
	/**
	 * 初始信用
	 */
	private String INI_CREDIT;
	
	
	/**
	 * 信用修改人
	 */
	private String CREDIT_CRE_NAME;
	
	/**
	 * 信用修改时间
	 */
	private String CREDIT_CRE_TIME;
	
	/**
	 * 帐套名称
	 */
	private String LEDGER_NAME;
	
	/** 上次调整的初始信用 **/
	private String INI_CREDIT_LAST;
	
	

	public String getCHANN_ID() {
		return CHANN_ID;
	}

	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}

	public String getCHANN_NO() {
		return CHANN_NO;
	}

	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}

	public String getCHANN_NAME() {
		return CHANN_NAME;
	}

	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}

	public String getCHANN_ABBR() {
		return CHANN_ABBR;
	}

	public void setCHANN_ABBR(String cHANNABBR) {
		CHANN_ABBR = cHANNABBR;
	}

	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}

	public void setCHANN_TYPE(String cHANNTYPE) {
		CHANN_TYPE = cHANNTYPE;
	}

	public String getCHAA_LVL() {
		return CHAA_LVL;
	}

	public void setCHAA_LVL(String cHAALVL) {
		CHAA_LVL = cHAALVL;
	}


	public String getCREDIT_CRE_NAME() {
		return CREDIT_CRE_NAME;
	}

	public void setCREDIT_CRE_NAME(String cREDITCRENAME) {
		CREDIT_CRE_NAME = cREDITCRENAME;
	}

	public String getCREDIT_CRE_TIME() {
		return CREDIT_CRE_TIME;
	}

	public void setCREDIT_CRE_TIME(String cREDITCRETIME) {
		CREDIT_CRE_TIME = cREDITCRETIME;
	}

	public String getAREA_NO() {
		return AREA_NO;
	}

	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}

	public String getAREA_NAME() {
		return AREA_NAME;
	}

	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}

	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}

	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}

	/**
	 * @return the cREDIT_CURRT
	 */
	public String getCREDIT_CURRT() {
		return CREDIT_CURRT;
	}

	/**
	 * @param cREDITCURRT the cREDIT_CURRT to set
	 */
	public void setCREDIT_CURRT(String cREDITCURRT) {
		CREDIT_CURRT = cREDITCURRT;
	}

	/**
	 * @return the iNI_CREDIT
	 */
	public String getINI_CREDIT() {
		return INI_CREDIT;
	}

	/**
	 * @param iNICREDIT the iNI_CREDIT to set
	 */
	public void setINI_CREDIT(String iNICREDIT) {
		INI_CREDIT = iNICREDIT;
	}

	public String getINI_CREDIT_LAST() {
		return INI_CREDIT_LAST;
	}

	public void setINI_CREDIT_LAST(String iNICREDITLAST) {
		INI_CREDIT_LAST = iNICREDITLAST;
	}
	
	
	
}
