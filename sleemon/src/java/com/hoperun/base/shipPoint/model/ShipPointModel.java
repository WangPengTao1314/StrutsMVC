/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：ShipPoitModel.java
 */
package com.hoperun.base.shipPoint.model;

/**
 * The Class ShipPoitModel.
 * 
 * @module 系统管理->基础数据
 * @func 生产基地维护
 * @version 1.0
 * @author 王志格
 */
public class ShipPointModel {
	
	/**
	 * 生产基地ID
	 */
	private String SHIP_POINT_ID;
	
	/**
	 * 生产基地编号
	 */
	private String SHIP_POINT_NO;

	/**
	 * 生产基地名称
	 */
	private String SHIP_POINT_NAME;
	
	
	/**
	 * 详细地址
	 */
	private String ADDRESS;
	
	/**
	 * 状态
	 */
	private String STATE;

	/**
	 * 删除标记
	 */
	private int DEL_FLAG;
	

	/**
	 * 制单人ID
	 */
	private String CREATOR;
	
	/**
	 * 制单人名称
	 */
	private String CRE_NAME;
	
	/**
	 * 制单时间
	 */
	private String CRE_TIME;

	/**
	 * 更新人ID
	 */
	private String UPDATOR;
	
	/**
	 * 更新人名称
	 */
	private String UPD_NAME;
	
	/**
	 * 更新时间
	 */
	private String UPD_TIME;

	/**
	 * 制单部门ID
	 */
	private String DEPT_ID;

	/**
	 * 制单部门名称
	 */
	private String DEPT_NAME;

	/**
	 * 制单机构ID
	 */
	private String ORG_ID;

	/**
	 * 制单机构名称
	 */
	private String ORG_NAME;

	/**
	 * 帐套ID
	 */
	private String LEDGER_ID;

	/**
	 * 帐套名称
	 */
	private String LEDGER_NAME;

	

	public String getSHIP_POINT_ID() {
		return SHIP_POINT_ID;
	}

	public void setSHIP_POINT_ID(String sHIPPOINTID) {
		SHIP_POINT_ID = sHIPPOINTID;
	}

	public String getSHIP_POINT_NO() {
		return SHIP_POINT_NO;
	}

	public void setSHIP_POINT_NO(String sHIPPOINTNO) {
		SHIP_POINT_NO = sHIPPOINTNO;
	}

	public String getSHIP_POINT_NAME() {
		return SHIP_POINT_NAME;
	}

	public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
		SHIP_POINT_NAME = sHIPPOINTNAME;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public int getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(int dELFLAG) {
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
	

}
