/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ZoneModel.java
 */

package com.hoperun.base.provider.model;

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
public class ProviderModel extends BaseModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4943417535815136771L;

	/** PRVD_ID .*/
	private String PRVD_ID; //供应商ID
	/** PRVD_NO .*/
	private String PRVD_NO; //供应商编号
	/** PRVD_NAME .*/
	private String PRVD_NAME; //供应商名称
	/** PRVD_TYPE .*/
	private String PRVD_TYPE; //供应商类别
	/** PRVD_LVL .*/
	private String PRVD_LVL; //供应商级别
	/** PRVD_NATRUE .*/
	private String PRVD_NATRUE; //供应商性质
	/** ZONE_ID .*/
	private String ZONE_ID; //行政区划ID
	/** NATION .*/
	private String NATION; //国家
	/** PROV .*/
	private String PROV; //省份
	/** CITY .*/
	private String CITY; //城市
	/** COUNTY .*/
	private String COUNTY; //区县
	/** PRVD_CY .*/
	private String PRVD_CY; //供货周期
	/** PRVD_CAP .*/
	private String PRVD_CAP; //供货能力
	/** PERSON_BUSS .*/
	private String PERSON_BUSS; //业务员
	/** PERSON_CON .*/
	private String PERSON_CON; //联系人
	/** TEL .*/
	private String TEL; //电话
	/** MOBILE_PHONE .*/
	private String MOBILE_PHONE; //手机
	/** TAX .*/
	private String TAX; //传真
	/** POST .*/
	private String POST; //邮政编号
	/** ADDRESS .*/
	private String ADDRESS; //详细地址
	/** EMAIL .*/
	private String EMAIL; //电子邮件
	/** WEB .*/
	private String WEB; //网站
	/** LEGAL_REPRE .*/
	private String LEGAL_REPRE; //法人代表
	/** BUSS_LIC .*/
	private String BUSS_LIC; //营业执照号
	/** INVOICE_TI .*/
	private String INVOICE_TI; //发票抬头
	/** INVOICE_ADDR .*/
	private String INVOICE_ADDR; //发票地址
	/** OPENING_BANK .*/
	private String OPENING_BANK; //开户行
	/** BANK_ACCT .*/
	private String BANK_ACCT; //银行账号
	/** FI_CMP_NO .*/
	private String FI_CMP_NO; //财务对照码
	/** STATE .*/
	private String STATE; //状态
	/** REMARK .*/
	private String REMARK; //备注
	/** CREATOR .*/
	private String CREATOR; //制单人ID
	/** CRE_NAME .*/
	private String CRE_NAME; //制单人名称
	/** CRE_TIME .*/
	private String CRE_TIME; //制单时间
	/** UPDATOR .*/
	private String UPDATOR; //更新人ID
	/** UPD_NAME .*/
	private String UPD_NAME; //更新人名称
	/** UPD_TIME .*/
	private String UPD_TIME; //更新时间
	/** DEPT_ID .*/
	private String DEPT_ID; //制单部门ID
	/** DEPT_NAME .*/
	private String DEPT_NAME; //制单部门名称
	/** ORG_ID .*/
	private String ORG_ID; //制单机构ID
	/** ORG_NAME .*/
	private String ORG_NAME; //制单机构名称
	/** LEDGER_ID .*/
	private String LEDGER_ID; //帐套ID
	/** LEDGER_NAME .*/
	private String LEDGER_NAME; //帐套名称
	/** DEL_FLAG .*/
	private String DEL_FLAG; //删除标记
	
	private String DEFAULT_FLAG;//默认标记
	
	
	/**
	 * @return the dEFAULT_FLAG
	 */
	public String getDEFAULT_FLAG() {
		return DEFAULT_FLAG;
	}


	/**
	 * @param dEFAULTFLAG the dEFAULT_FLAG to set
	 */
	public void setDEFAULT_FLAG(String dEFAULTFLAG) {
		DEFAULT_FLAG = dEFAULTFLAG;
	}


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
     * @param PRVD_ID the new PRVD_ID
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
     * @param PRVD_ID the new PRVD_NO
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
     * @param PRVD_ID the new PRVD_NAME
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
     * @param PRVD_ID the new PRVD_TYPE
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
     * @param PRVD_ID the new PRVD_LVL
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
     * @param PRVD_ID the new PRVD_NATRUE
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
     * @param PRVD_ID the new ZONE_ID
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
     * @param PRVD_ID the new NATION
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
     * @param PRVD_ID the new PROV
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
     * @param PRVD_ID the new CITY
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
     * @param PRVD_ID the new COUNTY
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
     * @param PRVD_ID the new PRVD_CY
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
     * @param PRVD_ID the new PRVD_CAP
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
     * @param PRVD_ID the new PERSON_BUSS
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
     * @param PRVD_ID the new PERSON_CON
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
     * @param PRVD_ID the new TEL
     */
	public void setTEL(String tEL) {
		TEL = tEL;
	}


	/**
     * Gets the MOBILE_PHONE.
     * 
     * @return the MOBILE_PHONE
     */
	public String getMOBILE_PHONE() {
		return MOBILE_PHONE;
	}


	/**
     * Sets the MOBILE_PHONE.
     * 
     * @param PRVD_ID the new MOBILE_PHONE
     */
	public void setMOBILE_PHONE(String mOBILEPHONE) {
		MOBILE_PHONE = mOBILEPHONE;
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
     * @param PRVD_ID the new TAX
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
     * @param PRVD_ID the new POST
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
     * @param PRVD_ID the new ADDRESS
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
     * @param PRVD_ID the new EMAIL
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
     * @param PRVD_ID the new WEB
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
     * @param PRVD_ID the new LEGAL_REPRE
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
     * @param PRVD_ID the new BUSS_LIC
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
     * @param PRVD_ID the new INVOICE_TI
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
     * @param PRVD_ID the new INVOICE_ADDR
     */
	public void setINVOICE_ADDR(String iNVOICEADDR) {
		INVOICE_ADDR = iNVOICEADDR;
	}


	/**
     * Gets the OPENING_BANK.
     * 
     * @return the OPENING_BANK
     */
	public String getOPENING_BANK() {
		return OPENING_BANK;
	}


	/**
     * Sets the OPENING_BANK.
     * 
     * @param PRVD_ID the new OPENING_BANK
     */
	public void setOPENING_BANK(String oPENINGBANK) {
		OPENING_BANK = oPENINGBANK;
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
     * @param PRVD_ID the new BANK_ACCT
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
     * @param PRVD_ID the new FI_CMP_NO
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
     * @param PRVD_ID the new STATE
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
     * @param PRVD_ID the new REMARK
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
     * @param PRVD_ID the new CREATOR
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
     * @param PRVD_ID the new CRE_NAME
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
     * @param PRVD_ID the new CRE_TIME
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
     * @param PRVD_ID the new UPDATOR
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
     * @param PRVD_ID the new UPD_NAME
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
     * @param PRVD_ID the new UPD_TIME
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
     * @param PRVD_ID the new DEPT_ID
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
     * @param PRVD_ID the new DEPT_NAME
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
     * @param PRVD_ID the new ORG_ID
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
     * @param PRVD_ID the new ORG_NAME
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
     * @param PRVD_ID the new LEDGER_ID
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
     * @param PRVD_ID the new LEDGER_NAME
     */
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
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
     * @param PRVD_ID the new DEL_FLAG
     */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

}
