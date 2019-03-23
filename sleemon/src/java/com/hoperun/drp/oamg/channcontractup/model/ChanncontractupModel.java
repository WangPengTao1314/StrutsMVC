package com.hoperun.drp.oamg.channcontractup.model;

import com.hoperun.commons.model.BaseModel;

public class ChanncontractupModel  extends BaseModel {
	/**
	 * 渠道合同ID
	 */
	private String CHANN_CONTRACT_ID;
	/**
	 * 渠道合同编号
	 */
	private String CHANN_CONTRACT_NO;
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
	 * 合同附件
	 */
	private String CONTRACT_ATT;
	/**
	 * 年份
	 */
	private String YEAR;
	/**
	 * 状态
	 */
	private String STATE;
	/**
	 * 备注
	 */
	private String REMARK;
	
	/**
	 * 年度指标
	 */
	private String YEAR_PLAN_AMOUNT;
	
	/**
	 * 一季度指标
	 */
	private String FIRST_QUARTER_AMOUNT;
	/**
	 * 二季度指标
	 */
	private String SECOND_QUARTER_AMOUNT;
	/**
	 * 三季度指标
	 */
	private String THIRD_QUARTER_AMOUNT;
	/**
	 * 四季度指标
	 */
	private String FOURTH_QUARTER_AMOUNT;
	
	public String getCHANN_CONTRACT_ID() {
		return CHANN_CONTRACT_ID;
	}
	public void setCHANN_CONTRACT_ID(String cHANNCONTRACTID) {
		CHANN_CONTRACT_ID = cHANNCONTRACTID;
	}
	public String getCHANN_CONTRACT_NO() {
		return CHANN_CONTRACT_NO;
	}
	public void setCHANN_CONTRACT_NO(String cHANNCONTRACTNO) {
		CHANN_CONTRACT_NO = cHANNCONTRACTNO;
	}
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
	public String getCONTRACT_ATT() {
		return CONTRACT_ATT;
	}
	public void setCONTRACT_ATT(String cONTRACTATT) {
		CONTRACT_ATT = cONTRACTATT;
	}
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getYEAR_PLAN_AMOUNT() {
		return YEAR_PLAN_AMOUNT;
	}
	public void setYEAR_PLAN_AMOUNT(String yEARPLANAMOUNT) {
		YEAR_PLAN_AMOUNT = yEARPLANAMOUNT;
	}
	public String getFIRST_QUARTER_AMOUNT() {
		return FIRST_QUARTER_AMOUNT;
	}
	public void setFIRST_QUARTER_AMOUNT(String fIRSTQUARTERAMOUNT) {
		FIRST_QUARTER_AMOUNT = fIRSTQUARTERAMOUNT;
	}
	public String getSECOND_QUARTER_AMOUNT() {
		return SECOND_QUARTER_AMOUNT;
	}
	public void setSECOND_QUARTER_AMOUNT(String sECONDQUARTERAMOUNT) {
		SECOND_QUARTER_AMOUNT = sECONDQUARTERAMOUNT;
	}
	public String getTHIRD_QUARTER_AMOUNT() {
		return THIRD_QUARTER_AMOUNT;
	}
	public void setTHIRD_QUARTER_AMOUNT(String tHIRDQUARTERAMOUNT) {
		THIRD_QUARTER_AMOUNT = tHIRDQUARTERAMOUNT;
	}
	public String getFOURTH_QUARTER_AMOUNT() {
		return FOURTH_QUARTER_AMOUNT;
	}
	public void setFOURTH_QUARTER_AMOUNT(String fOURTHQUARTERAMOUNT) {
		FOURTH_QUARTER_AMOUNT = fOURTHQUARTERAMOUNT;
	}
	
}
