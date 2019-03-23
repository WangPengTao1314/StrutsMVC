package com.hoperun.drp.finance.profiiloss.model;

import com.hoperun.commons.model.BaseModel;

public class ProfiiLossModelChld  extends BaseModel{
	/** 盈亏单明细ID*/
	private String PROFIT_LOSS_DTL_ID;
	/** 盈亏单ID*/
	private String PROFIT_LOSS_ID;
	/** 货品ID*/
	private String PRD_ID;
	/** 货品编号*/
	private String PRD_NO;
	/** 货品名称*/
	private String PRD_NAME;
	/** 规格型号*/
	private String PRD_SPEC;
	/** 颜色*/
	private String PRD_COLOR;
	/** 品牌*/
	private String BRAND;
	/** 标准单位*/
	private String STD_UNIT;
	/** 盈亏数量*/
	private String PROFIT_LOSS_NUM;
	/** 删除标记*/
	private String DEL_FLAG;
	/** 订单特殊工艺ID*/
	private String SPCL_TECH_ID;
	/** 盈亏单价*/
	private String PROFIT_LOSS_PRICE;
	/** 盈亏金额*/
	private String PROFIT_LOSS_AMOUNT;
	public String getPROFIT_LOSS_DTL_ID() {
		return PROFIT_LOSS_DTL_ID;
	}
	public void setPROFIT_LOSS_DTL_ID(String pROFITLOSSDTLID) {
		PROFIT_LOSS_DTL_ID = pROFITLOSSDTLID;
	}
	public String getPROFIT_LOSS_ID() {
		return PROFIT_LOSS_ID;
	}
	public void setPROFIT_LOSS_ID(String pROFITLOSSID) {
		PROFIT_LOSS_ID = pROFITLOSSID;
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
	public String getPROFIT_LOSS_NUM() {
		return PROFIT_LOSS_NUM;
	}
	public void setPROFIT_LOSS_NUM(String pROFITLOSSNUM) {
		PROFIT_LOSS_NUM = pROFITLOSSNUM;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}
	public String getPROFIT_LOSS_PRICE() {
		return PROFIT_LOSS_PRICE;
	}
	public void setPROFIT_LOSS_PRICE(String pROFITLOSSPRICE) {
		PROFIT_LOSS_PRICE = pROFITLOSSPRICE;
	}
	public String getPROFIT_LOSS_AMOUNT() {
		return PROFIT_LOSS_AMOUNT;
	}
	public void setPROFIT_LOSS_AMOUNT(String pROFITLOSSAMOUNT) {
		PROFIT_LOSS_AMOUNT = pROFITLOSSAMOUNT;
	}
	
}
