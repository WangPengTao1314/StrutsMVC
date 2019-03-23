/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：LogicprvdModel.java
 */

package com.hoperun.base.logicprvd.model;

import com.hoperun.commons.model.BaseModel;

/**
 * The Class LogicprvdModel.
 * 
 * @module 系统管理
 * @func 物流供应商管理
 * @version 1.0
 * @author 王栋斌
 */
public class LogicprvdModel extends BaseModel {

	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = -1271571140859495562L;

	/** PRVD_ID . */
	private String PRVD_ID; // 供应商ID
	/** PRVD_NO . */
	private String PRVD_NO; // 供应商编号
	/** PRVD_NAME . */
	private String PRVD_NAME; // 供应商名称
	/** PRVD_TYPE . */
	private String PRVD_TYPE; // 供应商类别
	/** PRVD_LVL . */
	private String PRVD_LVL; // 供应商级别
	/** PRVD_NATRUE . */
	private String PRVD_NATRUE; // 供应商性质
	/** ZONE_ID . */
	private String ZONE_ID; // 行政区划ID
	/** NATION . */
	private String NATION; // 国家
	/** PROV . */
	private String PROV; // 省份
	/** CITY . */
	private String CITY; // 城市
	/** COUNTY . */
	private String COUNTY; // 区县
	/** PRVD_CY . */
	private String PRVD_CY; // 供货周期
	/** PRVD_CAP . */
	private String PRVD_CAP; // 供货能力
	/** PERSON_BUSS . */
	private String PERSON_BUSS; // 业务员
	/** PERSON_CON . */
	private String PERSON_CON; // 联系人
	/** TEL . */
	private String TEL; // 电话
	/** MOBILE_PHONE . */
	private String MOBILE; // 手机
	/** TAX . */
	private String TAX; // 传真
	/** POST . */
	private String POST; // 邮政编号
	/** ADDRESS . */
	private String ADDRESS; // 详细地址
	/** EMAIL . */
	private String EMAIL; // 电子邮件
	/** WEB . */
	private String WEB; // 网站
	/** LEGAL_REPRE . */
	private String LEGAL_REPRE; // 法人代表
	/** BUSS_LIC . */
	private String BUSS_LIC; // 营业执照号
	/** INVOICE_TI . */
	private String INVOICE_TI; // 发票抬头
	/** INVOICE_ADDR . */
	private String INVOICE_ADDR; // 发票地址
	/** OPENING_BANK . */
	private String BANK_NAME; // 开户行
	/** BANK_ACCT . */
	private String BANK_ACCT; // 银行账号
	/** FI_CMP_NO . */
	private String FI_CMP_NO; // 财务对照码
	/** STATE . */
	private String STATE; // 状态
	/** REMARK . */
	private String REMARK; // 备注
	/** CREATOR . */
	private String CREATOR; // 制单人ID
	/** CRE_NAME . */
	private String CRE_NAME; // 制单人名称
	/** CRE_TIME . */
	private String CRE_TIME; // 制单时间
	/** UPDATOR . */
	private String UPDATOR; // 更新人ID
	/** UPD_NAME . */
	private String UPD_NAME; // 更新人名称
	/** UPD_TIME . */
	private String UPD_TIME; // 更新时间
	/** DEPT_ID . */
	private String DEPT_ID; // 制单部门ID
	/** DEPT_NAME . */
	private String DEPT_NAME; // 制单部门名称
	/** ORG_ID . */
	private String ORG_ID; // 制单机构ID
	/** ORG_NAME . */
	private String ORG_NAME; // 制单机构名称
	/** LEDGER_ID . */
	private String LEDGER_ID; // 帐套ID
	/** LEDGER_NAME . */
	private String LEDGER_NAME; // 帐套名称
	/** TRUCK_ID . */
	private String TRUCK_ID; // 车型信息ID
	/** TRUCK_TYPE . */
	private String TRUCK_TYPE; // 车型
	/** VOLUME . */
	private String VOLUME; // 容积
	private String MIN_VOLUME; // 最小容积
	private String MAX_VOLUME; // 最大容积
	
	/** LENGTH . */
	private String LENGTH; // 长度/全程
	/** WIDTH . */
	private String WIDTH; // 长度
	/** HEIGHT . */
	private String HEIGHT; // 高度
	/** WAY_MERGE_ID . */
	private String WAY_MERGE_ID; // 合并路线ID
	/** WAY_MERGE_NO . */
	private String WAY_MERGE_NO; // 合并路线编号
	/** WAY_MERGE_NAME . */
	private String WAY_MERGE_NAME; // 合并路线名称
	/** SHIP_POINT_ID . */
	private String SHIP_POINT_ID; // 发货点ID
	/** SHIP_POINT_NO . */
	private String SHIP_POINT_NO; // 发货点编号
	/** SHIP_POINT_NAME . */
	private String SHIP_POINT_NAME; // 发货点名称
	/** DELV_CITY . */
	private String DELV_CITY; // 发出城市
	/** ARRV_CITY . */
	private String ARRV_CITY; // 到达城市
	/** DAYS . */
	private String DAYS; // 天数
	/** WAY_MERGE_DTL_ID . */
	private String WAY_MERGE_DTL_ID; // 单位路线明细ID
	/** HAULWAY_ID . */
	private String HAULWAY_ID; // 标准路线ID
	/** HAULWAY_NO . */
	private String HAULWAY_NO; // 路线编号
	/** HAULWAY_NAME . */
	private String HAULWAY_NAME; // 路线名称
	/** CHANN_NO . */
	private String CHANN_NO; // 渠道编号
	/** CHANN_NAME . */
	private String CHANN_NAME; // 渠道名称

	/**
	 * Gets the PRVD_ID.
	 * 
	 * @return the PRVD_ID
	 */
	public String getPRVD_ID() {
		return PRVD_ID;
	}

	/**
	 * Sets the PRVD_ID.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_ID
	 */
	public void setPRVD_ID(String pRVDID) {
		PRVD_ID = pRVDID;
	}

	/**
	 * Gets the PRVD_NO.
	 * 
	 * @return the PRVD_NO
	 */
	public String getPRVD_NO() {
		return PRVD_NO;
	}

	/**
	 * Sets the PRVD_NO.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_NO
	 */
	public void setPRVD_NO(String pRVDNO) {
		PRVD_NO = pRVDNO;
	}

	/**
	 * Gets the PRVD_NAME.
	 * 
	 * @return the PRVD_NAME
	 */
	public String getPRVD_NAME() {
		return PRVD_NAME;
	}

	/**
	 * Sets the PRVD_NAME.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_NAME
	 */
	public void setPRVD_NAME(String pRVDNAME) {
		PRVD_NAME = pRVDNAME;
	}

	/**
	 * Gets the PRVD_TYPE.
	 * 
	 * @return the PRVD_TYPE
	 */
	public String getPRVD_TYPE() {
		return PRVD_TYPE;
	}

	/**
	 * Sets the PRVD_TYPE.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_TYPE
	 */
	public void setPRVD_TYPE(String pRVDTYPE) {
		PRVD_TYPE = pRVDTYPE;
	}

	/**
	 * Gets the PRVD_LVL.
	 * 
	 * @return the PRVD_LVL
	 */
	public String getPRVD_LVL() {
		return PRVD_LVL;
	}

	/**
	 * Sets the PRVD_LVL.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_LVL
	 */
	public void setPRVD_LVL(String pRVDLVL) {
		PRVD_LVL = pRVDLVL;
	}

	/**
	 * Gets the PRVD_NATRUE.
	 * 
	 * @return the PRVD_NATRUE
	 */
	public String getPRVD_NATRUE() {
		return PRVD_NATRUE;
	}

	/**
	 * Sets the PRVD_NATRUE.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_NATRUE
	 */
	public void setPRVD_NATRUE(String pRVDNATRUE) {
		PRVD_NATRUE = pRVDNATRUE;
	}

	/**
	 * Gets the ZONE_ID.
	 * 
	 * @return the ZONE_ID
	 */
	public String getZONE_ID() {
		return ZONE_ID;
	}

	/**
	 * Sets the ZONE_ID.
	 * 
	 * @param PRVD_ID
	 *            the new ZONE_ID
	 */
	public void setZONE_ID(String zONEID) {
		ZONE_ID = zONEID;
	}

	/**
	 * Gets the NATION.
	 * 
	 * @return the NATION
	 */
	public String getNATION() {
		return NATION;
	}

	/**
	 * Sets the NATION.
	 * 
	 * @param PRVD_ID
	 *            the new NATION
	 */
	public void setNATION(String nATION) {
		NATION = nATION;
	}

	/**
	 * Gets the PROV.
	 * 
	 * @return the PROV
	 */
	public String getPROV() {
		return PROV;
	}

	/**
	 * Sets the PROV.
	 * 
	 * @param PRVD_ID
	 *            the new PROV
	 */
	public void setPROV(String pROV) {
		PROV = pROV;
	}

	/**
	 * Gets the CITY.
	 * 
	 * @return the CITY
	 */
	public String getCITY() {
		return CITY;
	}

	/**
	 * Sets the CITY.
	 * 
	 * @param PRVD_ID
	 *            the new CITY
	 */
	public void setCITY(String cITY) {
		CITY = cITY;
	}

	/**
	 * Gets the COUNTY.
	 * 
	 * @return the COUNTY
	 */
	public String getCOUNTY() {
		return COUNTY;
	}

	/**
	 * Sets the COUNTY.
	 * 
	 * @param PRVD_ID
	 *            the new COUNTY
	 */
	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}

	/**
	 * Gets the PRVD_CY.
	 * 
	 * @return the PRVD_CY
	 */
	public String getPRVD_CY() {
		return PRVD_CY;
	}

	/**
	 * Sets the PRVD_CY.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_CY
	 */
	public void setPRVD_CY(String pRVDCY) {
		PRVD_CY = pRVDCY;
	}

	/**
	 * Gets the PRVD_CAP.
	 * 
	 * @return the PRVD_CAP
	 */
	public String getPRVD_CAP() {
		return PRVD_CAP;
	}

	/**
	 * Sets the PRVD_CAP.
	 * 
	 * @param PRVD_ID
	 *            the new PRVD_CAP
	 */
	public void setPRVD_CAP(String pRVDCAP) {
		PRVD_CAP = pRVDCAP;
	}

	/**
	 * Gets the PERSON_BUSS.
	 * 
	 * @return the PERSON_BUSS
	 */
	public String getPERSON_BUSS() {
		return PERSON_BUSS;
	}

	/**
	 * Sets the PERSON_BUSS.
	 * 
	 * @param PRVD_ID
	 *            the new PERSON_BUSS
	 */
	public void setPERSON_BUSS(String pERSONBUSS) {
		PERSON_BUSS = pERSONBUSS;
	}

	/**
	 * Gets the PERSON_CON.
	 * 
	 * @return the PERSON_CON
	 */
	public String getPERSON_CON() {
		return PERSON_CON;
	}

	/**
	 * Sets the PERSON_CON.
	 * 
	 * @param PRVD_ID
	 *            the new PERSON_CON
	 */
	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}

	/**
	 * Gets the TEL.
	 * 
	 * @return the TEL
	 */
	public String getTEL() {
		return TEL;
	}

	/**
	 * Sets the TEL.
	 * 
	 * @param PRVD_ID
	 *            the new TEL
	 */
	public void setTEL(String tEL) {
		TEL = tEL;
	}
 
	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	/**
	 * Gets the TAX.
	 * 
	 * @return the TAX
	 */
	public String getTAX() {
		return TAX;
	}

	/**
	 * Sets the TAX.
	 * 
	 * @param PRVD_ID
	 *            the new TAX
	 */
	public void setTAX(String tAX) {
		TAX = tAX;
	}

	/**
	 * Gets the POST.
	 * 
	 * @return the POST
	 */
	public String getPOST() {
		return POST;
	}

	/**
	 * Sets the POST.
	 * 
	 * @param PRVD_ID
	 *            the new POST
	 */
	public void setPOST(String pOST) {
		POST = pOST;
	}

	/**
	 * Gets the ADDRESS.
	 * 
	 * @return the ADDRESS
	 */
	public String getADDRESS() {
		return ADDRESS;
	}

	/**
	 * Sets the ADDRESS.
	 * 
	 * @param PRVD_ID
	 *            the new ADDRESS
	 */
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	/**
	 * Gets the EMAIL.
	 * 
	 * @return the EMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}

	/**
	 * Sets the EMAIL.
	 * 
	 * @param PRVD_ID
	 *            the new EMAIL
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	/**
	 * Gets the WEB.
	 * 
	 * @return the WEB
	 */
	public String getWEB() {
		return WEB;
	}

	/**
	 * Sets the WEB.
	 * 
	 * @param PRVD_ID
	 *            the new WEB
	 */
	public void setWEB(String wEB) {
		WEB = wEB;
	}

	/**
	 * Gets the LEGAL_REPRE.
	 * 
	 * @return the LEGAL_REPRE
	 */
	public String getLEGAL_REPRE() {
		return LEGAL_REPRE;
	}

	/**
	 * Sets the LEGAL_REPRE.
	 * 
	 * @param PRVD_ID
	 *            the new LEGAL_REPRE
	 */
	public void setLEGAL_REPRE(String lEGALREPRE) {
		LEGAL_REPRE = lEGALREPRE;
	}

	/**
	 * Gets the BUSS_LIC.
	 * 
	 * @return the BUSS_LIC
	 */
	public String getBUSS_LIC() {
		return BUSS_LIC;
	}

	/**
	 * Sets the BUSS_LIC.
	 * 
	 * @param PRVD_ID
	 *            the new BUSS_LIC
	 */
	public void setBUSS_LIC(String bUSSLIC) {
		BUSS_LIC = bUSSLIC;
	}

	/**
	 * Gets the INVOICE_TI.
	 * 
	 * @return the INVOICE_TI
	 */
	public String getINVOICE_TI() {
		return INVOICE_TI;
	}

	/**
	 * Sets the INVOICE_TI.
	 * 
	 * @param PRVD_ID
	 *            the new INVOICE_TI
	 */
	public void setINVOICE_TI(String iNVOICETI) {
		INVOICE_TI = iNVOICETI;
	}

	/**
	 * Gets the INVOICE_ADDR.
	 * 
	 * @return the INVOICE_ADDR
	 */
	public String getINVOICE_ADDR() {
		return INVOICE_ADDR;
	}

	/**
	 * Sets the INVOICE_ADDR.
	 * 
	 * @param PRVD_ID
	 *            the new INVOICE_ADDR
	 */
	public void setINVOICE_ADDR(String iNVOICEADDR) {
		INVOICE_ADDR = iNVOICEADDR;
	}

    

	public String getBANK_NAME() {
		return BANK_NAME;
	}

	public void setBANK_NAME(String bANKNAME) {
		BANK_NAME = bANKNAME;
	}

	/**
	 * Gets the BANK_ACCT.
	 * 
	 * @return the BANK_ACCT
	 */
	public String getBANK_ACCT() {
		return BANK_ACCT;
	}

	/**
	 * Sets the BANK_ACCT.
	 * 
	 * @param PRVD_ID
	 *            the new BANK_ACCT
	 */
	public void setBANK_ACCT(String bANKACCT) {
		BANK_ACCT = bANKACCT;
	}

	/**
	 * Gets the FI_CMP_NO.
	 * 
	 * @return the FI_CMP_NO
	 */
	public String getFI_CMP_NO() {
		return FI_CMP_NO;
	}

	/**
	 * Sets the FI_CMP_NO.
	 * 
	 * @param PRVD_ID
	 *            the new FI_CMP_NO
	 */
	public void setFI_CMP_NO(String fICMPNO) {
		FI_CMP_NO = fICMPNO;
	}

	/**
	 * Gets the STATE.
	 * 
	 * @return the STATE
	 */
	public String getSTATE() {
		return STATE;
	}

	/**
	 * Sets the STATE.
	 * 
	 * @param PRVD_ID
	 *            the new STATE
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	/**
	 * Gets the REMARK.
	 * 
	 * @return the REMARK
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * Sets the REMARK.
	 * 
	 * @param PRVD_ID
	 *            the new REMARK
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	/**
	 * Gets the CREATOR.
	 * 
	 * @return the CREATOR
	 */
	public String getCREATOR() {
		return CREATOR;
	}

	/**
	 * Sets the CREATOR.
	 * 
	 * @param PRVD_ID
	 *            the new CREATOR
	 */
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	/**
	 * Gets the CRE_NAME.
	 * 
	 * @return the CRE_NAME
	 */
	public String getCRE_NAME() {
		return CRE_NAME;
	}

	/**
	 * Sets the CRE_NAME.
	 * 
	 * @param PRVD_ID
	 *            the new CRE_NAME
	 */
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}

	/**
	 * Gets the CRE_TIME.
	 * 
	 * @return the CRE_TIME
	 */
	public String getCRE_TIME() {
		return CRE_TIME;
	}

	/**
	 * Sets the CRE_TIME.
	 * 
	 * @param PRVD_ID
	 *            the new CRE_TIME
	 */
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}

	/**
	 * Gets the UPDATOR.
	 * 
	 * @return the UPDATOR
	 */
	public String getUPDATOR() {
		return UPDATOR;
	}

	/**
	 * Sets the UPDATOR.
	 * 
	 * @param PRVD_ID
	 *            the new UPDATOR
	 */
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}

	/**
	 * Gets the UPD_NAME.
	 * 
	 * @return the UPD_NAME
	 */
	public String getUPD_NAME() {
		return UPD_NAME;
	}

	/**
	 * Sets the UPD_NAME.
	 * 
	 * @param PRVD_ID
	 *            the new UPD_NAME
	 */
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}

	/**
	 * Gets the UPD_TIME.
	 * 
	 * @return the UPD_TIME
	 */
	public String getUPD_TIME() {
		return UPD_TIME;
	}

	/**
	 * Sets the UPD_TIME.
	 * 
	 * @param PRVD_ID
	 *            the new UPD_TIME
	 */
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}

	/**
	 * Gets the DEPT_ID.
	 * 
	 * @return the DEPT_ID
	 */
	public String getDEPT_ID() {
		return DEPT_ID;
	}

	/**
	 * Sets the DEPT_ID.
	 * 
	 * @param PRVD_ID
	 *            the new DEPT_ID
	 */
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}

	/**
	 * Gets the DEPT_NAME.
	 * 
	 * @return the DEPT_NAME
	 */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	/**
	 * Sets the DEPT_NAME.
	 * 
	 * @param PRVD_ID
	 *            the new DEPT_NAME
	 */
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}

	/**
	 * Gets the ORG_ID.
	 * 
	 * @return the ORG_ID
	 */
	public String getORG_ID() {
		return ORG_ID;
	}

	/**
	 * Sets the ORG_ID.
	 * 
	 * @param PRVD_ID
	 *            the new ORG_ID
	 */
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}

	/**
	 * Gets the ORG_NAME.
	 * 
	 * @return the ORG_NAME
	 */
	public String getORG_NAME() {
		return ORG_NAME;
	}

	/**
	 * Sets the ORG_NAME.
	 * 
	 * @param PRVD_ID
	 *            the new ORG_NAME
	 */
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}

	/**
	 * Gets the LEDGER_ID.
	 * 
	 * @return the LEDGER_ID
	 */
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}

	/**
	 * Sets the LEDGER_ID.
	 * 
	 * @param PRVD_ID
	 *            the new LEDGER_ID
	 */
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}

	/**
	 * Gets the LEDGER_NAME.
	 * 
	 * @return the LEDGER_NAME
	 */
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}

	/**
	 * Sets the LEDGER_NAME.
	 * 
	 * @param PRVD_ID
	 *            the new LEDGER_NAME
	 */
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}

	/**
	 * Gets the TRUCK_ID.
	 * 
	 * @return the TRUCK_ID
	 */
	public String getTRUCK_ID() {
		return TRUCK_ID;
	}

	/**
	 * Sets the TRUCK_ID.
	 * 
	 * @param tRUCKID
	 *            the new tRUCKID
	 */
	public void setTRUCK_ID(String tRUCKID) {
		TRUCK_ID = tRUCKID;
	}

	/**
	 * Gets the TRUCK_TYPE.
	 * 
	 * @return the TRUCK_TYPE
	 */
	public String getTRUCK_TYPE() {
		return TRUCK_TYPE;
	}

	/**
	 * Sets the TRUCK_TYPE.
	 * 
	 * @param tRUCKTYPE
	 *            the new tRUCKTYPE
	 */
	public void setTRUCK_TYPE(String tRUCKTYPE) {
		TRUCK_TYPE = tRUCKTYPE;
	}

	/**
	 * Gets the VOLUME.
	 * 
	 * @return the VOLUME
	 */
	public String getVOLUME() {
		return VOLUME;
	}

	/**
	 * Sets the VOLUME.
	 * 
	 * @param vOLUME
	 *            the new vOLUME
	 */
	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	/**
	 * Gets the LENGTH.
	 * 
	 * @return the LENGTH
	 */
	public String getLENGTH() {
		return LENGTH;
	}

	/**
	 * Sets the LENGTH.
	 * 
	 * @param lENGTH
	 *            the new lENGTH
	 */
	public void setLENGTH(String lENGTH) {
		LENGTH = lENGTH;
	}

	/**
	 * Gets the WIDTH.
	 * 
	 * @return the WIDTH
	 */
	public String getWIDTH() {
		return WIDTH;
	}

	/**
	 * Sets the WIDTH.
	 * 
	 * @param wIDTH
	 *            the new wIDTH
	 */
	public void setWIDTH(String wIDTH) {
		WIDTH = wIDTH;
	}

	/**
	 * Gets the HEIGHT.
	 * 
	 * @return the HEIGHT
	 */
	public String getHEIGHT() {
		return HEIGHT;
	}

	/**
	 * Sets the HEIGHT.
	 * 
	 * @param hEIGHT
	 *            the new hEIGHT
	 */
	public void setHEIGHT(String hEIGHT) {
		HEIGHT = hEIGHT;
	}

	/**
	 * Gets the WAY_MERGE_ID.
	 * 
	 * @return the WAY_MERGE_ID
	 */
	public String getWAY_MERGE_ID() {
		return WAY_MERGE_ID;
	}

	/**
	 * Sets the WAY_MERGE_ID.
	 * 
	 * @param wAYMERGEID
	 *            the new wAYMERGEID
	 */
	public void setWAY_MERGE_ID(String wAYMERGEID) {
		WAY_MERGE_ID = wAYMERGEID;
	}

	/**
	 * Gets the WAY_MERGE_NO.
	 * 
	 * @return the WAY_MERGE_NO
	 */
	public String getWAY_MERGE_NO() {
		return WAY_MERGE_NO;
	}

	/**
	 * Sets the WAY_MERGE_NO.
	 * 
	 * @param wAYMERGENO
	 *            the new wAYMERGENO
	 */
	public void setWAY_MERGE_NO(String wAYMERGENO) {
		WAY_MERGE_NO = wAYMERGENO;
	}

	/**
	 * Gets the WAY_MERGE_NAME.
	 * 
	 * @return the WAY_MERGE_NAME
	 */
	public String getWAY_MERGE_NAME() {
		return WAY_MERGE_NAME;
	}

	/**
	 * Sets the WAY_MERGE_NAME.
	 * 
	 * @param wAYMERGENAME
	 *            the new wAYMERGENAME
	 */
	public void setWAY_MERGE_NAME(String wAYMERGENAME) {
		WAY_MERGE_NAME = wAYMERGENAME;
	}

	/**
	 * Get the SHIP_POINT_ID.
	 * @return SHIP_POINT_ID
	 */
	public String getSHIP_POINT_ID() {
		return SHIP_POINT_ID;
	}

	/**
	 * Sets the SHIP_POINT_ID.
	 * 
	 * @param sHIPPOINTID
	 *            the new sHIPPOINTID
	 */
	public void setSHIP_POINT_ID(String sHIPPOINTID) {
		SHIP_POINT_ID = sHIPPOINTID;
	}

	/**
	 * Get the SHIP_POINT_NO.
	 * @return SHIP_POINT_NO
	 */
	public String getSHIP_POINT_NO() {
		return SHIP_POINT_NO;
	}

	/**
	 * Sets the SHIP_POINT_NO.
	 * 
	 * @param sHIPPOINTNO
	 *            the new sHIPPOINTNO
	 */
	public void setSHIP_POINT_NO(String sHIPPOINTNO) {
		SHIP_POINT_NO = sHIPPOINTNO;
	}

	/**
	 * Get the SHIP_POINT_NAME.
	 * @return SHIP_POINT_NAME
	 */
	public String getSHIP_POINT_NAME() {
		return SHIP_POINT_NAME;
	}

	/**
	 * Sets the SHIP_POINT_NAME.
	 * 
	 * @param sHIPPOINTNAME
	 *            the new sHIPPOINTNAME
	 */
	public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
		SHIP_POINT_NAME = sHIPPOINTNAME;
	}

	/**
	 * Gets the DELV_CITY.
	 * 
	 * @return the DELV_CITY
	 */
	public String getDELV_CITY() {
		return DELV_CITY;
	}

	/**
	 * Sets the DELV_CITY.
	 * 
	 * @param dELVCITY
	 *            the new dELVCITY
	 */
	public void setDELV_CITY(String dELVCITY) {
		DELV_CITY = dELVCITY;
	}

	/**
	 * Gets the ARRV_CITY.
	 * 
	 * @return the ARRV_CITY
	 */
	public String getARRV_CITY() {
		return ARRV_CITY;
	}

	/**
	 * Sets the ARRV_CITY.
	 * 
	 * @param aRRVCITY
	 *            the new aRRVCITY
	 */
	public void setARRV_CITY(String aRRVCITY) {
		ARRV_CITY = aRRVCITY;
	}

	/**
	 * Gets the DAYS.
	 * 
	 * @return the DAYS
	 */
	public String getDAYS() {
		return DAYS;
	}

	/**
	 * Sets the DAYS.
	 * 
	 * @param dAYS
	 *            the new dAYS
	 */
	public void setDAYS(String dAYS) {
		DAYS = dAYS;
	}

	/**
	 * Gets the WAY_MERGE_DTL_ID.
	 * 
	 * @return the WAY_MERGE_DTL_ID
	 */
	public String getWAY_MERGE_DTL_ID() {
		return WAY_MERGE_DTL_ID;
	}

	/**
	 * Sets the WAY_MERGE_DTL_ID.
	 * 
	 * @param wAYMERGEDTLID
	 *            the new wAYMERGEDTLID
	 */
	public void setWAY_MERGE_DTL_ID(String wAYMERGEDTLID) {
		WAY_MERGE_DTL_ID = wAYMERGEDTLID;
	}

	/**
	 * Gets the HAULWAY_ID.
	 * 
	 * @return the HAULWAY_ID
	 */
	public String getHAULWAY_ID() {
		return HAULWAY_ID;
	}

	/**
	 * Sets the HAULWAY_ID.
	 * 
	 * @param hAULWAYID
	 *            the new hAULWAYID
	 */
	public void setHAULWAY_ID(String hAULWAYID) {
		HAULWAY_ID = hAULWAYID;
	}

	/**
	 * Gets the HAULWAY_NO.
	 * 
	 * @return the HAULWAY_NO
	 */
	public String getHAULWAY_NO() {
		return HAULWAY_NO;
	}

	/**
	 * Sets the HAULWAY_NO.
	 * 
	 * @param hAULWAYNO
	 *            the new hAULWAYNO
	 */
	public void setHAULWAY_NO(String hAULWAYNO) {
		HAULWAY_NO = hAULWAYNO;
	}

	/**
	 * Gets the HAULWAY_NAME.
	 * 
	 * @return the HAULWAY_NAME
	 */
	public String getHAULWAY_NAME() {
		return HAULWAY_NAME;
	}

	/**
	 * Sets the HAULWAY_NAME.
	 * 
	 * @param hAULWAYNAME
	 *            the new hAULWAYNAME
	 */
	public void setHAULWAY_NAME(String hAULWAYNAME) {
		HAULWAY_NAME = hAULWAYNAME;
	}

	/**
	 * Gets the CHANN_NO.
	 * 
	 * @return the CHANN_NO
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}

	/**
	 * Sets the CHANN_NO.
	 * 
	 * @param cHANNNO
	 *            the new cHANNNO
	 */
	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}

	/**
	 * Gets the CHANN_NAME.
	 * 
	 * @return the CHANN_NAME
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}

	/**
	 * Sets the CHANN_NAME.
	 * 
	 * @param cHANNNAME
	 *            the new cHANNNAME
	 */
	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}

	public String getMIN_VOLUME() {
		return MIN_VOLUME;
	}

	public void setMIN_VOLUME(String mINVOLUME) {
		MIN_VOLUME = mINVOLUME;
	}

	public String getMAX_VOLUME() {
		return MAX_VOLUME;
	}

	public void setMAX_VOLUME(String mAXVOLUME) {
		MAX_VOLUME = mAXVOLUME;
	}
	
	
}
