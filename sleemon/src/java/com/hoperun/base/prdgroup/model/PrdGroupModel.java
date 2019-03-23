package com.hoperun.base.prdgroup.model;

import com.hoperun.commons.model.BaseModel;

public class PrdGroupModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//货品组ID
	private String PRD_GROUP_ID;
	//货品组编号
	private String PRD_GROUP_NO;
	//货品组名称
	private String PRD_GROUP_NAME;
	//货品组状态
	private String STATE;
	//备注
	private String REMARK;
	
	//制单人ID
	private String CREATOR;
	//制单人姓名
	private String CRE_NAME;
	//制单时间
	private String CRE_TIME;
	//更新人ID
	private String UPDATOR;
	//更新人姓名
	private String UPD_NAME;
	//更新时间
	private String UPD_TIME;
	//制单部门ID
	private String DEPT_ID;
	//制单部门名称
	private String DEPT_NAME;
	//制单机构ID
	private String ORG_ID;
	//制单机构名称
	private String ORG_NAME;

	//删除标记
	private String DEL_FLAG;
	
	
	
	 
 
	public String getPRD_GROUP_ID() {
		return PRD_GROUP_ID;
	}
	public void setPRD_GROUP_ID(String pRDGROUPID) {
		PRD_GROUP_ID = pRDGROUPID;
	}
	public String getPRD_GROUP_NO() {
		return PRD_GROUP_NO;
	}
	public void setPRD_GROUP_NO(String pRDGROUPNO) {
		PRD_GROUP_NO = pRDGROUPNO;
	}
	public String getPRD_GROUP_NAME() {
		return PRD_GROUP_NAME;
	}
	public void setPRD_GROUP_NAME(String pRDGROUPNAME) {
		PRD_GROUP_NAME = pRDGROUPNAME;
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
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	
	 

}
