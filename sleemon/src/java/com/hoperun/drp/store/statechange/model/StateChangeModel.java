package com.hoperun.drp.store.statechange.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 形态转换
 * @author zhang_zhongbin
 *
 */
public class StateChangeModel extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	/** 形态转换ID **/
	private String STATE_CHANGE_ID;

	/** 形态转换编号 **/
	private String STATE_CHANGE_NO;

	/** 状态 **/
	private String STATE;

	/** 备注 **/
	private String REMARK;

	/** 制单人 **/
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
	/** 制单部门ID **/
	private String DEPT_ID;
	/** 制单部门 **/
	private String DEPT_NAME;
	/** 制单机构ID **/
	private String ORG_ID;
	/** 制单机构 **/
	private String ORG_NAME;
	/** 帐套ID **/
	private String LEDGER_ID;
	/** 帐套名称 **/
	private String LEDGER_NAME;
	/** 删除标记 **/
	private String DEL_FLAG;
	/** 审核人姓名人 **/
	private String AUDIT_NAME;
	/** 审核人ID **/
	private String AUDIT_ID;
	/** 审核时间 **/
	private String AUDIT_TIME;
	
	
	public String getSTATE_CHANGE_ID() {
		return STATE_CHANGE_ID;
	}
	public void setSTATE_CHANGE_ID(String sTATECHANGEID) {
		STATE_CHANGE_ID = sTATECHANGEID;
	}
	public String getSTATE_CHANGE_NO() {
		return STATE_CHANGE_NO;
	}
	public void setSTATE_CHANGE_NO(String sTATECHANGENO) {
		STATE_CHANGE_NO = sTATECHANGENO;
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
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
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
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
	}
	
	
	

}
