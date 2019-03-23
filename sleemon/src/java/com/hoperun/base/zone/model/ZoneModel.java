/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ZoneModel.java
 */

package com.hoperun.base.zone.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ZoneModel.
 * 
 * @module 系统管理
 * @func 行政区划
 * @version 1.1
 * @author 张涛
 */
public class ZoneModel extends BaseModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4943417535815136771L;

	/** The ZONE_ID. */
	private String ZONE_ID; // 行政区划ID
	/** The NATION. */
	private String NATION; // 国家
	/** The PROV. */
	private String PROV; // 省份
	/** The CITY. */
	private String CITY; // 城市
	/** The COUNTY. */
	private String COUNTY; // 区县
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
	/** The DEL_FLAG. */
	private String DEL_FLAG; // 删除标记	
	
	/**
     * Gets the zONEID.
     * 
     * @return the zONEID
     */
	public String getZONE_ID() {
		return ZONE_ID;
	}
	
	
	/**
     * Sets the zONEID.
     * 
     * @param ZONE_ID the new zONEID
     */
	public void setZONE_ID(String zONEID) {
		ZONE_ID = zONEID;
	}
	
	
	/**
     * Gets the nATION.
     * 
     * @return the nATION
     */
	public String getNATION() {
		return NATION;
	}
	
	
	/**
     * Sets the nATION.
     * 
     * @param ZONE_ID the new nATION
     */
	public void setNATION(String nATION) {
		NATION = nATION;
	}
	
	
	/**
     * Gets the pROV.
     * 
     * @return the pROV
     */
	public String getPROV() {
		return PROV;
	}
	
	
	/**
     * Sets the pROV.
     * 
     * @param ZONE_ID the new pROV
     */
	public void setPROV(String pROV) {
		PROV = pROV;
	}
	
	
	/**
     * Gets the cITY.
     * 
     * @return the cITY
     */
	public String getCITY() {
		return CITY;
	}
	
	
	/**
     * Sets the cITY.
     * 
     * @param ZONE_ID the new cITY
     */
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	
	
	/**
     * Gets the cOUNTY.
     * 
     * @return the cOUNTY
     */
	public String getCOUNTY() {
		return COUNTY;
	}
	
	
	/**
     * Sets the cOUNTY.
     * 
     * @param ZONE_ID the new cOUNTY
     */
	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}
	
	
	/**
     * Gets the sTATE.
     * 
     * @return the sTATE
     */
	public String getSTATE() {
		return STATE;
	}
	
	
	/**
     * Sets the sTATE.
     * 
     * @param ZONE_ID the new sTATE
     */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	
	
	/**
     * Gets the cREATOR.
     * 
     * @return the cREATOR
     */
	public String getCREATOR() {
		return CREATOR;
	}
	
	
	/**
     * Sets the cREATOR.
     * 
     * @param ZONE_ID the new cREATOR
     */
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	
	
	/**
     * Gets the cRENAME.
     * 
     * @return the cRENAME
     */
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	
	
	/**
     * Sets the cRENAME.
     * 
     * @param ZONE_ID the new cRENAME
     */
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	
	
	/**
     * Gets the cRETIME.
     * 
     * @return the cRETIME
     */
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	
	
	/**
     * Sets the cRETIME.
     * 
     * @param ZONE_ID the new cRETIME
     */
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	
	
	/**
     * Gets the uPDATOR.
     * 
     * @return the uPDATOR
     */
	public String getUPDATOR() {
		return UPDATOR;
	}
	
	
	/**
     * Sets the uPDATOR.
     * 
     * @param ZONE_ID the new uPDATOR
     */
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}
	
	
	/**
     * Gets the uPDNAME.
     * 
     * @return the uPDNAME
     */
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	
	
	/**
     * Sets the uPDNAME.
     * 
     * @param ZONE_ID the new uPDNAME
     */
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}
	
	
	/**
     * Gets the uPDTIME.
     * 
     * @return the uPDTIME
     */
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	
	
	/**
     * Sets the uPDTIME.
     * 
     * @param ZONE_ID the new uPDTIME
     */
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}
	
	
	/**
     * Gets the dEPTID.
     * 
     * @return the dEPTID
     */
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	
	
	/**
     * Sets the dEPTID.
     * 
     * @param ZONE_ID the new dEPTID
     */
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}
	
	
	/**
     * Gets the dEPTNAME.
     * 
     * @return the dEPTNAME
     */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	
	
	/**
     * Sets the dEPTNAME.
     * 
     * @param ZONE_ID the new dEPTNAME
     */
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}
	
	
	/**
     * Gets the oRGID.
     * 
     * @return the oRGID
     */
	public String getORG_ID() {
		return ORG_ID;
	}
	
	
	/**
     * Sets the oRGID.
     * 
     * @param ZONE_ID the new oRGID
     */
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}
	
	
	/**
     * Gets the oRGNAME.
     * 
     * @return the oRGNAME
     */
	public String getORG_NAME() {
		return ORG_NAME;
	}
	
	
	/**
     * Sets the oRGNAME.
     * 
     * @param ZONE_ID the new oRGNAME
     */
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}
	
	
	/**
     * Gets the DEL_FLAG.
     * 
     * @return the DEL_FLAG
     */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	
	/**
     * Sets the DEL_FLAG.
     * 
     * @param ZONE_ID the new DEL_FLAG
     */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

}
