/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：FaulwayModel.java
 */
package com.hoperun.base.haulway.model;

import com.hoperun.commons.model.BaseModel;

/**
 * The Class DWWHModel.
 * 
 * @module 系统管理
 * @func 基础信息
 * @version 1.0
 * @author 王栋斌
 */
public class HaulwayModel extends BaseModel {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -8579356516978097420L;

	/** The HAULWAY_ID. */
	private String HAULWAY_ID; // 标准路线ID

	/** The HAULWAY_NO. */
	private String HAULWAY_NO; // 路线编号

	/** The HAULWAY_NAME. */
	private String HAULWAY_NAME; // 路线名称

	/** The DELV_CITY. */
	private String DELV_CITY; // 发出城市

	/** The SHIP_POIT_ID. */
	private String SHIP_POIT_ID; // 发货点ID
	
	/** The SHIP_POIT_NO. */
	private String SHIP_POIT_NO; // 发货点编号
	
	/** The SHIP_POIT_NAME. */
	private String SHIP_POIT_NAME; // 发货点名称
	
	/** The ARRV_CITY. */
	private String ARRV_CITY; // 到达城市
	
	/** The CHANN_ID. */
	private String CHANN_ID; // 渠道ID
	
	/** The CHANN_NO. */
	private String CHANN_NO; // 渠道编号
	
	/** The CHANN_NAME. */
	private String CHANN_NAME; // 渠道名称
	
	/** The LENGTH. */
	private String LENGTH; // 全程
	
	/** The DAYS. */
	private String DAYS; // 天数

	/** The STATE. */
	private String STATE; // 状态
	
	/** The REMARK. */
	private String REMARK; // 备注

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
	 * get the HAULWAY_ID
	 * 
	 * @return HAULWAY_ID
	 */
	public String getHAULWAY_ID() {
		return HAULWAY_ID;
	}

	/**
	 * set the HAULWAY_ID
	 * 
	 * @param hAULWAYID
	 */
	public void setHAULWAY_ID(String hAULWAYID) {
		HAULWAY_ID = hAULWAYID;
	}

	/**
	 * get the HAULWAY_NO
	 * 
	 * @return HAULWAY_NO
	 */
	public String getHAULWAY_NO() {
		return HAULWAY_NO;
	}

	/**
	 * set the HAULWAY_NO
	 * 
	 * @param hAULWAYNO
	 */
	public void setHAULWAY_NO(String hAULWAYNO) {
		HAULWAY_NO = hAULWAYNO;
	}

	/**
	 * get the HAULWAY_NAME
	 * 
	 * @return HAULWAY_NAME
	 */
	public String getHAULWAY_NAME() {
		return HAULWAY_NAME;
	}

	/**
	 * set the HAULWAY_NAME
	 * 
	 * @param hAULWAYNAME
	 */
	public void setHAULWAY_NAME(String hAULWAYNAME) {
		HAULWAY_NAME = hAULWAYNAME;
	}

	/**
	 * get the DELV_CITY
	 * 
	 * @return DELV_CITY
	 */
	public String getDELV_CITY() {
		return DELV_CITY;
	}

	/**
	 * set the DELV_CITY
	 * 
	 * @param dELVCITY
	 */
	public void setDELV_CITY(String dELVCITY) {
		DELV_CITY = dELVCITY;
	}

	/**
	 * get the SHIP_POIT_ID
	 * 
	 * @return SHIP_POIT_ID
	 */
	public String getSHIP_POIT_ID() {
		return SHIP_POIT_ID;
	}

	/**
	 * set the SHIP_POIT_ID
	 * 
	 * @param sHIPPOITID
	 */
	public void setSHIP_POIT_ID(String sHIPPOITID) {
		SHIP_POIT_ID = sHIPPOITID;
	}

	/**
	 * get the SHIP_POIT_NO
	 * 
	 * @return SHIP_POIT_NO
	 */
	public String getSHIP_POIT_NO() {
		return SHIP_POIT_NO;
	}

	/**
	 * set the SHIP_POIT_NO
	 * 
	 * @param sHIPPOITNO
	 */
	public void setSHIP_POIT_NO(String sHIPPOITNO) {
		SHIP_POIT_NO = sHIPPOITNO;
	}

	/**
	 * get the SHIP_POIT_NAME
	 * 
	 * @return SHIP_POIT_NAME
	 */
	public String getSHIP_POIT_NAME() {
		return SHIP_POIT_NAME;
	}

	/**
	 * set the SHIP_POIT_NAME
	 * 
	 * @param sHIPPOITNAME
	 */
	public void setSHIP_POIT_NAME(String sHIPPOITNAME) {
		SHIP_POIT_NAME = sHIPPOITNAME;
	}

	/**
	 * get the ARRV_CITY
	 * 
	 * @return ARRV_CITY
	 */
	public String getARRV_CITY() {
		return ARRV_CITY;
	}

	/**
	 * set the ARRV_CITY
	 * 
	 * @param aRRVCITY
	 */
	public void setARRV_CITY(String aRRVCITY) {
		ARRV_CITY = aRRVCITY;
	}

	/**
	 * get the CHANN_ID
	 * 
	 * @return CHANN_ID
	 */
	public String getCHANN_ID() {
		return CHANN_ID;
	}

	/**
	 * set the CHANN_ID
	 * 
	 * @param cHANNID
	 */
	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}

	/**
	 * get the CHANN_NO
	 * 
	 * @return CHANN_NO
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}

	/**
	 * set the CHANN_NO
	 * 
	 * @param cHANNNO
	 */
	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}

	/**
	 * get the CHANN_NAME
	 * 
	 * @return CHANN_NAME
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}

	/**
	 * set the CHANN_NAME
	 * 
	 * @param cHANNNAME
	 */
	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}

	/**
	 * get the LENGTH
	 * 
	 * @return LENGTH
	 */
	public String getLENGTH() {
		return LENGTH;
	}

	/**
	 * set the LENGTH
	 * 
	 * @param lENGTH
	 */
	public void setLENGTH(String lENGTH) {
		LENGTH = lENGTH;
	}

	/**
	 * get the DAYS
	 * 
	 * @return DAYS
	 */
	public String getDAYS() {
		return DAYS;
	}

	/**
	 * set the DAYS
	 * 
	 * @param dAYS
	 */
	public void setDAYS(String dAYS) {
		DAYS = dAYS;
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
	 * get the REMARK
	 * 
	 * @return REMARK
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * set the REMARK
	 * 
	 * @param rEMARK
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
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
