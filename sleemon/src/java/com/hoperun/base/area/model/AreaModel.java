package com.hoperun.base.area.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 区域信息
 * @author zhang_zhongbin
 *
 */
public class AreaModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String AREA_ID;
	//区域编号
	private String AREA_NO;
	//区域名称
	private String AREA_NAME;
	//上级区ID
	private String AREA_ID_P;
	//上级区编码
	private String AREA_NO_P;
	//上级区名称
	private String AREA_NAME_P;
	//状态
	private String STATE;
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
	//帐套ID 
	private String LEDGER_ID;
	//帐套名称 
	private String LEDGER_NAME;
	
	
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
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
	public String getAREA_ID_P() {
		return AREA_ID_P;
	}
	public void setAREA_ID_P(String aREAIDP) {
		AREA_ID_P = aREAIDP;
	}
	public String getAREA_NO_P() {
		return AREA_NO_P;
	}
	public void setAREA_NO_P(String aREANOP) {
		AREA_NO_P = aREANOP;
	}
	public String getAREA_NAME_P() {
		return AREA_NAME_P;
	}
	public void setAREA_NAME_P(String aREANAMEP) {
		AREA_NAME_P = aREANAMEP;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
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
	
	
	 
	
	
	
	

}
