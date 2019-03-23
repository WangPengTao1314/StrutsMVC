/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：FactoryModel.java
 */
package com.hoperun.base.factory.model;

/**
 * The Class FactoryModel.
 * 
 * @module 系统管理
 * @func 生产工厂维护
 * @version 1.0
 * @author 王志格
 */
public class FactoryModel {
	
	/**
	 * 生产基地ID
	 */
	private String FACTORY_ID;
	
	/**
	 * 生产基地编号
	 */
	private String FACTORY_NO;

	/**
	 * 生产基地名称
	 */
	private String FACTORY_NAME;
	/**
	 * 联系人
	 */
	private String PERSON_CON;
	
	/**
	 * 手机号码
	 */
	private String MOBILE_NO;
	
	/**
	 * 电话
	 */
	private String TEL;
	
	/**
	 * 传真
	 */
	private String TAX;
	

	/**
	 * 邮政编号
	 */
	private String POST;
	
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
	
	
	public String getFACTORY_ID() {
		return FACTORY_ID;
	}
	public void setFACTORY_ID(String fACTORYID) {
		FACTORY_ID = fACTORYID;
	}

	public String getFACTORY_NO() {
		return FACTORY_NO;
	}

	public void setFACTORY_NO(String fACTORYNO) {
		FACTORY_NO = fACTORYNO;
	}

	public String getFACTORY_NAME() {
		return FACTORY_NAME;
	}

	public void setFACTORY_NAME(String fACTORYNAME) {
		FACTORY_NAME = fACTORYNAME;
	}

	public String getPERSON_CON() {
		return PERSON_CON;
	}

	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}

	public String getMOBILE_NO() {
		return MOBILE_NO;
	}

	public void setMOBILE_NO(String mOBILENO) {
		MOBILE_NO = mOBILENO;
	}

	public String getTEL() {
		return TEL;
	}

	public void setTEL(String tEL) {
		TEL = tEL;
	}

	public String getTAX() {
		return TAX;
	}

	public void setTAX(String tAX) {
		TAX = tAX;
	}

	public String getPOST() {
		return POST;
	}

	public void setPOST(String pOST) {
		POST = pOST;
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

	public String getCREATOR() {
		return CREATOR;
	}

	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	public int getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(int dELFLAG) {
		DEL_FLAG = dELFLAG;
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
