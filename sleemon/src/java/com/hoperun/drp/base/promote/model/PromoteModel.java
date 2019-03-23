package com.hoperun.drp.base.promote.model;

import com.hoperun.commons.model.BaseModel;

/***
 * 活动
 * 
 * @author zhang_zhongbin
 * 
 */
public class PromoteModel extends BaseModel {
	// 活动id
	private String PROMOTE_ID;
	// 活动编号
	private String PROMOTE_NO;
	// 活动名称
	private String PROMOTE_NAME;
	// 活动描述
	private String PROMOTE_DESC;
	// 活动开始日期
	private String BEG_DATE;
	// 活动结束日期
	private String END_DATE;
	// 状态
	private String STATE;
	// 备注
	private String REMARK;
	// 删除标记
	private String DEL_FLAG;
	// 制单人ID
	private String CREATOR;
	// 制单人姓名
	private String CRE_NAME;
	// 制单时间
	private String CRE_TIME;
	// 更新人ID
	private String UPDATOR;
	// 更新人姓名
	private String UPD_NAME;
	// 更新时间
	private String UPD_TIME;
	// 制单部门ID
	private String DEPT_ID;
	// 制单部门名称
	private String DEPT_NAME;
	// 制单机构ID
	private String ORG_ID;
	// 制单机构名称
	private String ORG_NAME;
	// 帐套ID
	private String LEDGER_ID;
	// 帐套名称
	private String LEDGER_NAME;

	public String getPROMOTE_ID() {
		return PROMOTE_ID;
	}

	public void setPROMOTE_ID(String pROMOTEID) {
		PROMOTE_ID = pROMOTEID;
	}

	public String getPROMOTE_NO() {
		return PROMOTE_NO;
	}

	public void setPROMOTE_NO(String pROMOTENO) {
		PROMOTE_NO = pROMOTENO;
	}

	public String getPROMOTE_NAME() {
		return PROMOTE_NAME;
	}

	public void setPROMOTE_NAME(String pROMOTENAME) {
		PROMOTE_NAME = pROMOTENAME;
	}

	public String getPROMOTE_DESC() {
		return PROMOTE_DESC;
	}

	public void setPROMOTE_DESC(String pROMOTEDESC) {
		PROMOTE_DESC = pROMOTEDESC;
	}

	public String getBEG_DATE() {
		return BEG_DATE;
	}

	public void setBEG_DATE(String bEGDATE) {
		BEG_DATE = bEGDATE;
	}

	public String getEND_DATE() {
		return END_DATE;
	}

	public void setEND_DATE(String eNDDATE) {
		END_DATE = eNDDATE;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	public String getCREATOR() {
		return CREATOR;
	}

	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	public String getCRE_NAME() {
		return CRE_NAME;
	}

	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}

	public String getCRE_TIME() {
		return CRE_TIME;
	}

	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}

	public String getUPDATOR() {
		return UPDATOR;
	}

	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}

	public String getUPD_NAME() {
		return UPD_NAME;
	}

	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
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

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

}
