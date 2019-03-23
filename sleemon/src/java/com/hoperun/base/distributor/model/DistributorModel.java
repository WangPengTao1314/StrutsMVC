package com.hoperun.base.distributor.model;

/**  
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.0
 * @author gu_hongxiu
 */
public class DistributorModel {
	
	
	/**
	 * 分销商ID
	 */
	private String DISTRIBUTOR_ID;
	
	/**
	 * 分销商编号
	 */
	private String DISTRIBUTOR_NO;
	
	/**
	 * 分销商名称
	 */
	private String DISTRIBUTOR_NAME;

	/**
	 * 分销商类型
	 */
	private String DISTRIBUTOR_TYPE;

	/**
	 * 公司电话
	 */
	private String TEL;

	/**
	 * 渠道ID
	 */
	private String CHANN_ID;

	/**
	 * 渠道编号
	 */
	private String CHANN_NO;

	/**
	 * 渠道名称
	 */
	private String CHANN_NAME;

	/**
	 * 区域ID
	 */
	private String AREA_ID;

	/**
	 * 区域编号
	 */
	private String AREA_NO;

	/**
	 * 区域名称
	 */
	private String AREA_NAME;

	/**
	 * 行政区划ID
	 */
	private String ZONE_ID;

	
	/**
	 * 国家
	 */
	private String NATION;

	/**
	 * 省份
	 */
	private String PROV;

	/**
	 * 城市
	 */
	private String CITY;

	/**
	 * 区县
	 */
	private String COUNTY;
	
	/**
	 * 区域经理ID
	 */
	private String AREA_MANAGE_ID;
	
	/**
	 * 区域经理名称
	 */
	private String AREA_MANAGE_NAME;

	/**
	 * 区域经理电话
	 */
	private String AREA_MANAGE_TEL;

	/**
	 * 详细地址
	 */
	private String ADDRESS;

	/**
	 * 传真
	 */
	private String TAX;

	/**
	 * 电子邮件
	 */
	private String EMAIL;

	/**
	 * 联系人
	 */
	private String PERSON_CON;

	/**
	 * 联系人电话
	 */
	private String MOBILE;

	/**
	 * 商场名称
	 */
	private String SALE_STORE_NAME;

	/**
	 * 商场位置
	 */
	private String SALE_STORE_LOCAL;

	/**
	 * 经营品牌
	 */
	private String BUSS_BRAND;

	/**
	 * 主营分类
	 */
	private String BUSS_CLASS;

	/**
	 * 合作日期
	 */
	private String COOPER_DATE;

	/**
	 * 合作方案附件
	 */
	private String COOPER_PLAN_ATT;

	/**
	 * 附件
	 */
	private String ATT;

	/**
	 * 状态
	 */
	private String STATE;

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
	
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	

	/**
	 * 备注
	 */
	private String REMARK;


	public String getDISTRIBUTOR_ID() {
		return DISTRIBUTOR_ID;
	}


	public void setDISTRIBUTOR_ID(String dISTRIBUTORID) {
		DISTRIBUTOR_ID = dISTRIBUTORID;
	}


	public String getDISTRIBUTOR_NO() {
		return DISTRIBUTOR_NO;
	}


	public void setDISTRIBUTOR_NO(String dISTRIBUTORNO) {
		DISTRIBUTOR_NO = dISTRIBUTORNO;
	}


	public String getDISTRIBUTOR_NAME() {
		return DISTRIBUTOR_NAME;
	}


	public void setDISTRIBUTOR_NAME(String dISTRIBUTORNAME) {
		DISTRIBUTOR_NAME = dISTRIBUTORNAME;
	}


	public String getDISTRIBUTOR_TYPE() {
		return DISTRIBUTOR_TYPE;
	}


	public void setDISTRIBUTOR_TYPE(String dISTRIBUTORTYPE) {
		DISTRIBUTOR_TYPE = dISTRIBUTORTYPE;
	}


	public String getTEL() {
		return TEL;
	}


	public void setTEL(String tEL) {
		TEL = tEL;
	}


	public String getCHANN_ID() {
		return CHANN_ID;
	}


	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}


	public String getCHANN_NO() {
		return CHANN_NO;
	}


	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}


	public String getCHANN_NAME() {
		return CHANN_NAME;
	}


	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}


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


	public String getZONE_ID() {
		return ZONE_ID;
	}


	public void setZONE_ID(String zONEID) {
		ZONE_ID = zONEID;
	}


	public String getNATION() {
		return NATION;
	}


	public void setNATION(String nATION) {
		NATION = nATION;
	}


	public String getPROV() {
		return PROV;
	}


	public void setPROV(String pROV) {
		PROV = pROV;
	}


	public String getCITY() {
		return CITY;
	}


	public void setCITY(String cITY) {
		CITY = cITY;
	}


	public String getCOUNTY() {
		return COUNTY;
	}


	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}


	public String getAREA_MANAGE_ID() {
		return AREA_MANAGE_ID;
	}


	public void setAREA_MANAGE_ID(String aREAMANAGEID) {
		AREA_MANAGE_ID = aREAMANAGEID;
	}


	public String getAREA_MANAGE_NAME() {
		return AREA_MANAGE_NAME;
	}


	public void setAREA_MANAGE_NAME(String aREAMANAGENAME) {
		AREA_MANAGE_NAME = aREAMANAGENAME;
	}


	public String getAREA_MANAGE_TEL() {
		return AREA_MANAGE_TEL;
	}


	public void setAREA_MANAGE_TEL(String aREAMANAGETEL) {
		AREA_MANAGE_TEL = aREAMANAGETEL;
	}


	public String getADDRESS() {
		return ADDRESS;
	}


	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}


	public String getTAX() {
		return TAX;
	}


	public void setTAX(String tAX) {
		TAX = tAX;
	}


	public String getEMAIL() {
		return EMAIL;
	}


	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}


	public String getPERSON_CON() {
		return PERSON_CON;
	}


	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}


	public String getMOBILE() {
		return MOBILE;
	}


	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}


	public String getSALE_STORE_NAME() {
		return SALE_STORE_NAME;
	}


	public void setSALE_STORE_NAME(String sALESTORENAME) {
		SALE_STORE_NAME = sALESTORENAME;
	}


	public String getSALE_STORE_LOCAL() {
		return SALE_STORE_LOCAL;
	}


	public void setSALE_STORE_LOCAL(String sALESTORELOCAL) {
		SALE_STORE_LOCAL = sALESTORELOCAL;
	}


	public String getBUSS_BRAND() {
		return BUSS_BRAND;
	}


	public void setBUSS_BRAND(String bUSSBRAND) {
		BUSS_BRAND = bUSSBRAND;
	}


	public String getBUSS_CLASS() {
		return BUSS_CLASS;
	}


	public void setBUSS_CLASS(String bUSSCLASS) {
		BUSS_CLASS = bUSSCLASS;
	}


	public String getCOOPER_DATE() {
		return COOPER_DATE;
	}


	public void setCOOPER_DATE(String cOOPERDATE) {
		COOPER_DATE = cOOPERDATE;
	}


	public String getCOOPER_PLAN_ATT() {
		return COOPER_PLAN_ATT;
	}


	public void setCOOPER_PLAN_ATT(String cOOPERPLANATT) {
		COOPER_PLAN_ATT = cOOPERPLANATT;
	}


	public String getATT() {
		return ATT;
	}


	public void setATT(String aTT) {
		ATT = aTT;
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


	public String getDEL_FLAG() {
		return DEL_FLAG;
	}


	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}


	public String getREMARK() {
		return REMARK;
	}


	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

		
			
}