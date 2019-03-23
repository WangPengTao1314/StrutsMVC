package com.hoperun.erp.sale.marketing.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 营销活动主表
 * @author zhang_zhongbin
 *
 */
public class MarketActivitiyModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3775732891945337175L;
	
	/** 营销活动ID **/
	private String MARKETING_ACT_ID;
	/** 营销活动编号 **/
	private String MARKETING_ACT_NO;
	/** 营销活动名称 **/
	private String MARKETING_ACT_NAME;
	/** 开始日期 **/
	private String START_DATE;
	/** 结束日期 **/
	private String END_DATE;
	/** 发起人名称 **/
	private String SPONSOR_NAME;
	/** 提成比例 **/
	private String COMMISSION_PERCENTAGE;
	/** 活动内容 **/
	private String REMARK;
	/** 状态 **/
	private String STATE;
	/** 制单人名称 **/
	private String CRE_NAME;
	/** 制单人ID **/
	private String CREATOR;
	/** 制单时间 **/
	private String CRE_TIME;
	/** 更新人名称 **/
	private String UPD_NAME;
	/** 更新人ID **/
	private String UPDATOR;
	/** 更新时间 **/
	private String UPD_TIME;
	/** 审核人ID **/
	private String AUDIT_ID;
	/** 审核人姓名 **/
	private String AUDIT_NAME;
	/** 审核时间 **/
	private String AUDIT_TIME;
	/** 帐套ID **/
	private String LEDGER_ID;
	/** 帐套名称 **/
	private String LEDGER_NAME;
	/** 删除标记 **/
	private String DEL_FLAG;
	
	
	public String getMARKETING_ACT_ID() {
		return MARKETING_ACT_ID;
	}
	public void setMARKETING_ACT_ID(String mARKETINGACTID) {
		MARKETING_ACT_ID = mARKETINGACTID;
	}
	public String getMARKETING_ACT_NO() {
		return MARKETING_ACT_NO;
	}
	public void setMARKETING_ACT_NO(String mARKETINGACTNO) {
		MARKETING_ACT_NO = mARKETINGACTNO;
	}
	public String getMARKETING_ACT_NAME() {
		return MARKETING_ACT_NAME;
	}
	public void setMARKETING_ACT_NAME(String mARKETINGACTNAME) {
		MARKETING_ACT_NAME = mARKETINGACTNAME;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTARTDATE) {
		START_DATE = sTARTDATE;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String eNDDATE) {
		END_DATE = eNDDATE;
	}
	public String getSPONSOR_NAME() {
		return SPONSOR_NAME;
	}
	public void setSPONSOR_NAME(String sPONSORNAME) {
		SPONSOR_NAME = sPONSORNAME;
	}
	public String getCOMMISSION_PERCENTAGE() {
		return COMMISSION_PERCENTAGE;
	}
	public void setCOMMISSION_PERCENTAGE(String cOMMISSIONPERCENTAGE) {
		COMMISSION_PERCENTAGE = cOMMISSIONPERCENTAGE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	

}
