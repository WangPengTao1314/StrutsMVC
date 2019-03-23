/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：UnitModel.java
 */
package com.hoperun.base.unit.model;

import com.hoperun.commons.model.BaseModel;

/**
 * The Class DWWHModel.
 * 
 * @module 系统管理
 * @func 基础信息
 * @version 1.0
 * @author 王栋斌
 */
public class UnitModel extends BaseModel {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4943417535815136771L;

	/** The MEAS_UNIT_ID. */
	private String MEAS_UNIT_ID; // 单位ID

	/** The MEAS_UNIT_NAME. */
	private String MEAS_UNIT_NAME; // 单位名称

	/** The MEAS_UNIT_NO. */
	private String MEAS_UNIT_NO; // 单位编号

	/** The UNIT_NAME_EN. */
	private String UNIT_NAME_EN; // 单位英文名称

	/** The UNIT_TYPE. */
	private String UNIT_TYPE; // 单位类型

	/** The STATE. */
	private String STATE; // 状态

	/** The CREATOR. */
	private String CREATOR; // 制单人ID

	/** The CRE_NAME. */
	private String CRE_NAME; // 制单人名称

	/** The CRE_TIME. */
	private String CRE_TIME; // 制单时间

	/** The UPDATOR. */
	private String UPDATOR; // 更新人ID

	/** The UPD_NAME. */
	private String UPD_NAME; // 更新人名称

	/** The UPD_TIME. */
	private String UPD_TIME; // 更新时间

	/** The DEPT_ID. */
	private String DEPT_ID; // 制单部门ID

	/** The DEPT_NAME. */
	private String DEPT_NAME; // 制单部门名称

	/** The ORG_ID. */
	private String ORG_ID; // 制单机构ID

	/** The ORG_NAME. */
	private String ORG_NAME; // 制单机构名称

	/** The CRE_TIME_FROM. */
	private String CRE_TIME_FROM; // 制单时间从

	/** The CRE_TIME_TO. */
	private String CRE_TIME_TO; // 制单时间到

	/**
	 * get the MEAS_UNIT_ID
	 * 
	 * @return MEAS_UNIT_ID
	 */
	public String getMEAS_UNIT_ID() {
		return MEAS_UNIT_ID;
	}

	/**
	 * set the MEAS_UNIT_ID
	 * 
	 * @param mEASUNITID
	 */
	public void setMEAS_UNIT_ID(String mEASUNITID) {
		MEAS_UNIT_ID = mEASUNITID;
	}

	/**
	 * get the MEAS_UNIT_NAME
	 * 
	 * @return MEAS_UNIT_NAME
	 */
	public String getMEAS_UNIT_NAME() {
		return MEAS_UNIT_NAME;
	}

	/**
	 * set the MEAS_UNIT_NAME
	 * 
	 * @param mEASUNITNAME
	 */
	public void setMEAS_UNIT_NAME(String mEASUNITNAME) {
		MEAS_UNIT_NAME = mEASUNITNAME;
	}

	/**
	 * get the MEAS_UNIT_NO
	 * 
	 * @return MEAS_UNIT_NO
	 */
	public String getMEAS_UNIT_NO() {
		return MEAS_UNIT_NO;
	}

	/**
	 * set the MEAS_UNIT_NO
	 * 
	 * @param mEASUNITNO
	 */
	public void setMEAS_UNIT_NO(String mEASUNITNO) {
		MEAS_UNIT_NO = mEASUNITNO;
	}

	/**
	 * get the UNIT_NAME_EN
	 * 
	 * @return UNIT_NAME_EN
	 */
	public String getUNIT_NAME_EN() {
		return UNIT_NAME_EN;
	}

	/**
	 * set the UNIT_NAME_EN
	 * 
	 * @param uNITNAMEEN
	 */
	public void setUNIT_NAME_EN(String uNITNAMEEN) {
		UNIT_NAME_EN = uNITNAMEEN;
	}

	/**
	 * get the UNIT_TYPE
	 * 
	 * @return UNIT_TYPE
	 */
	public String getUNIT_TYPE() {
		return UNIT_TYPE;
	}

	/**
	 * set the UNIT_TYPE
	 * 
	 * @param uNITTYPE
	 */
	public void setUNIT_TYPE(String uNITTYPE) {
		UNIT_TYPE = uNITTYPE;
	}

	/**
	 * get the STATE
	 * 
	 * @return STATE
	 */
	public String getSTATE() {
		return STATE;
	}

	/**
	 * set the STATE
	 * 
	 * @param sTATE
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	/**
	 * get the CREATOR
	 * 
	 * @return CREATOR
	 */
	public String getCREATOR() {
		return CREATOR;
	}

	/**
	 * set the CREATOR
	 * 
	 * @param cREATOR
	 */
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	/**
	 * get the CRE_NAME
	 * 
	 * @return CRE_NAME
	 */
	public String getCRE_NAME() {
		return CRE_NAME;
	}

	/**
	 * set the CRE_NAME
	 * 
	 * @param cRENAME
	 */
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}

	/**
	 * get the CRE_TIME
	 * 
	 * @return CRE_TIME
	 */
	public String getCRE_TIME() {
		return CRE_TIME;
	}

	/**
	 * set the CRE_TIME
	 * 
	 * @param cRETIME
	 */
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}

	/**
	 * get the UPDATOR
	 * 
	 * @return UPDATOR
	 */
	public String getUPDATOR() {
		return UPDATOR;
	}

	/**
	 * set the UPDATOR
	 * 
	 * @param uPDATOR
	 */
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}

	/**
	 * get the UPD_NAME
	 * 
	 * @return UPD_NAME
	 */
	public String getUPD_NAME() {
		return UPD_NAME;
	}

	/**
	 * set the UPD_NAME
	 * 
	 * @param uPDNAME
	 */
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}

	/**
	 * get the UPD_TIME
	 * 
	 * @return UPD_TIME
	 */
	public String getUPD_TIME() {
		return UPD_TIME;
	}

	/**
	 * set the UPD_TIME
	 * 
	 * @param uPDTIME
	 */
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}

	/**
	 * get the DEPT_ID
	 * 
	 * @return DEPT_ID
	 */
	public String getDEPT_ID() {
		return DEPT_ID;
	}

	/**
	 * set the DEPT_ID
	 * 
	 * @param dEPTID
	 */
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}

	/**
	 * get the DEPT_NAME
	 * 
	 * @return DEPT_NAME
	 */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	/**
	 * set the DEPT_NAME
	 * 
	 * @param dEPTNAME
	 */
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}

	/**
	 * get the ORG_ID
	 * 
	 * @return ORG_ID
	 */
	public String getORG_ID() {
		return ORG_ID;
	}

	/**
	 * set the ORG_ID
	 * 
	 * @param oRGID
	 */
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}

	/**
	 * get the ORG_NAME
	 * 
	 * @return ORG_NAME
	 */
	public String getORG_NAME() {
		return ORG_NAME;
	}

	/**
	 * set the ORG_NAME
	 * 
	 * @param oRGNAME
	 */
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}

	/**
	 * get the CRE_TIME_FROM
	 * 
	 * @return CRE_TIME_FROM
	 */
	public String getCRE_TIME_FROM() {
		return CRE_TIME_FROM;
	}

	/**
	 * set the CRE_TIME_FROM
	 * 
	 * @param cRETIMEFROM
	 */
	public void setCRE_TIME_FROM(String cRETIMEFROM) {
		CRE_TIME_FROM = cRETIMEFROM;
	}

	/**
	 * get the CRE_TIME_TO
	 * 
	 * @return CRE_TIME_TO
	 */
	public String getCRE_TIME_TO() {
		return CRE_TIME_TO;
	}

	/**
	 * set the CRE_TIME_TO
	 * 
	 * @param cRETIMETO
	 */
	public void setCRE_TIME_TO(String cRETIMETO) {
		CRE_TIME_TO = cRETIMETO;
	}
}
