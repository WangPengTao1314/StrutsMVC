/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：TerminalModel.java
 */
package com.hoperun.base.terminal.model;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandModel.
 * 
 * @module 系统管理
 * @func 终端信息
 * @version 1.1
 * @author 郭利伟
 */
public class TerminalModel {
	/**
	 * 终端信息ID
	 */
	private String TERM_ID;
	
	/**
	 * 终端编号
	 */
	private String TERM_NO;
	
	/**
	 * 终端名称
	 */
	private String TERM_NAME;
	
	/**
	 * 终端简称
	 */
	private String TERM_ABBR;
	
	/**
	 * 终端类型
	 */
	private String TERM_TYPE;
	
	/**
	 * 终端级别
	 */
	private String TERM_LVL;
	
	/**
	 * 上级渠道ID
	 */
	private String CHANN_ID_P;
	
	/**
	 * 上级渠道编号
	 */
	private String CHANN_NO_P;
	
	/**
	 * 上级渠道名称
	 */
	private String CHANN_NAME_P;
	
	/**
	 * 营业性质
	 */
	private String BUSS_NATRUE;
	
	/**
	 * 物流方式
	 */
	private String LOGIC_TYPE;
	
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
	 * 终端版本
	 */
	private String TERM_VERSION;
	
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
	 * 城市类型
	 */
	private String CITY_TYPE;
	
	/**
	 * 联系人
	 */
	private String PERSON_CON;
	
	/**
	 * 电话
	 */
	private String TEL;
	
	/**
	 * 手机
	 */
	private String MOBILE_PHONE;
	
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
	 * 电子邮件
	 */
	private String EMAIL;
	
	/**
	 * 网站
	 */
	private String WEB;
	
	/**
	 * 法人代表
	 */
	private String LEGAL_REPRE;
	
	/**
	 * 营业执照号
	 */
	private String BUSS_LIC;
	
	/**
	 * 税务登记号
	 */
	private String AX_BURDEN;
	
	/**
	 * 组织机构代码证
	 */
	private String ORG_CODE_CERT;
	
	/**
	 * 经营范围
	 */
	private String BUSS_SCOPE;
	
	/**
	 * 财务对照码
	 */
	private String FI_CMP_NO;
	
	/**
	 * 营业面积
	 */
	private String BUSS_AREA;
	
	/**
	 * 楼层数
	 */
	private String STOR_NO;
	
	/**
	 * 最后装潢时间
	 */
	private String LAST_DECOR_TIME;
	
	/**
	 * 状态
	 */
	private String STATE;
	
	/**
	 * 备注
	 */
	private String REMARK;
	
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
	 * 打款开户银行
	 */
	private String PLAY_BANK_NAME;
	/**
	 * 打款银行账号
	 */
	private String PLAY_BANK_ACCT;
	
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	/** 卖场ID **/
	private String SALE_STORE_ID;
	/** 卖场名称 **/
	private String SALE_STORE_NAME;
	/** 商场位置类别 **/
	private String LOCAL_TYPE;
	/** 开业时间 **/
	private String BEG_SBUSS_DATE;
    /** 营业状态 **/
	private String BUSS_STATE;
	/**开店类型**/
	private String BEG_BUSS_TYPE;
	/**门店分类**/
	private String TERM_CLASS;
	  /** 
	   * The order field.
	   */
    private String orderField;
	
    /**客户名称 **/
    private String CUST_NAME;
    
    public String getTERM_ID() {
		return TERM_ID;
	}

	public void setTERM_ID(String tERMID) {
		TERM_ID = tERMID;
	}

	public String getTERM_NO() {
		return TERM_NO;
	}

	public void setTERM_NO(String tERMNO) {
		TERM_NO = tERMNO;
	}

	public String getTERM_NAME() {
		return TERM_NAME;
	}

	public void setTERM_NAME(String tERMNAME) {
		TERM_NAME = tERMNAME;
	}

	public String getTERM_ABBR() {
		return TERM_ABBR;
	}

	public void setTERM_ABBR(String tERMABBR) {
		TERM_ABBR = tERMABBR;
	}

	public String getTERM_TYPE() {
		return TERM_TYPE;
	}

	public void setTERM_TYPE(String tERMTYPE) {
		TERM_TYPE = tERMTYPE;
	}

	public String getTERM_LVL() {
		return TERM_LVL;
	}

	public void setTERM_LVL(String tERMLVL) {
		TERM_LVL = tERMLVL;
	}

	public String getCHANN_ID_P() {
		return CHANN_ID_P;
	}

	public void setCHANN_ID_P(String cHANNIDP) {
		CHANN_ID_P = cHANNIDP;
	}

	public String getCHANN_NO_P() {
		return CHANN_NO_P;
	}

	public void setCHANN_NO_P(String cHANNNOP) {
		CHANN_NO_P = cHANNNOP;
	}

	public String getCHANN_NAME_P() {
		return CHANN_NAME_P;
	}

	public void setCHANN_NAME_P(String cHANNNAMEP) {
		CHANN_NAME_P = cHANNNAMEP;
	}

	public String getBUSS_NATRUE() {
		return BUSS_NATRUE;
	}

	public void setBUSS_NATRUE(String bUSSNATRUE) {
		BUSS_NATRUE = bUSSNATRUE;
	}

	public String getLOGIC_TYPE() {
		return LOGIC_TYPE;
	}

	public void setLOGIC_TYPE(String lOGICTYPE) {
		LOGIC_TYPE = lOGICTYPE;
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

	public String getCITY_TYPE() {
		return CITY_TYPE;
	}

	public void setCITY_TYPE(String cITYTYPE) {
		CITY_TYPE = cITYTYPE;
	}

	public String getPERSON_CON() {
		return PERSON_CON;
	}

	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
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

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getWEB() {
		return WEB;
	}

	public void setWEB(String wEB) {
		WEB = wEB;
	}

	public String getLEGAL_REPRE() {
		return LEGAL_REPRE;
	}

	public void setLEGAL_REPRE(String lEGALREPRE) {
		LEGAL_REPRE = lEGALREPRE;
	}

	public String getBUSS_LIC() {
		return BUSS_LIC;
	}

	public void setBUSS_LIC(String bUSSLIC) {
		BUSS_LIC = bUSSLIC;
	}

	public String getAX_BURDEN() {
		return AX_BURDEN;
	}

	public void setAX_BURDEN(String aXBURDEN) {
		AX_BURDEN = aXBURDEN;
	}

	public String getORG_CODE_CERT() {
		return ORG_CODE_CERT;
	}

	public void setORG_CODE_CERT(String oRGCODECERT) {
		ORG_CODE_CERT = oRGCODECERT;
	}

	public String getMOBILE_PHONE() {
		return MOBILE_PHONE;
	}

	public void setMOBILE_PHONE(String mOBILEPHONE) {
		MOBILE_PHONE = mOBILEPHONE;
	}

	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}

	public void setBUSS_SCOPE(String bUSSSCOPE) {
		BUSS_SCOPE = bUSSSCOPE;
	}

	public String getFI_CMP_NO() {
		return FI_CMP_NO;
	}

	public void setFI_CMP_NO(String fICMPNO) {
		FI_CMP_NO = fICMPNO;
	}

	public String getBUSS_AREA() {
		return BUSS_AREA;
	}

	public void setBUSS_AREA(String bUSSAREA) {
		BUSS_AREA = bUSSAREA;
	}

	public String getSTOR_NO() {
		return STOR_NO;
	}

	public void setSTOR_NO(String sTORNO) {
		STOR_NO = sTORNO;
	}

	public String getLAST_DECOR_TIME() {
		return LAST_DECOR_TIME;
	}

	public void setLAST_DECOR_TIME(String lASTDECORTIME) {
		LAST_DECOR_TIME = lASTDECORTIME;
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

	public String getOrderField() {
		return orderField;
	}

	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}

	public String getSALE_STORE_ID() {
		return SALE_STORE_ID;
	}

	public void setSALE_STORE_ID(String sALESTOREID) {
		SALE_STORE_ID = sALESTOREID;
	}

	public String getSALE_STORE_NAME() {
		return SALE_STORE_NAME;
	}

	public void setSALE_STORE_NAME(String sALESTORENAME) {
		SALE_STORE_NAME = sALESTORENAME;
	}

	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}

	public void setLOCAL_TYPE(String lOCALTYPE) {
		LOCAL_TYPE = lOCALTYPE;
	}

	public String getBEG_SBUSS_DATE() {
		return BEG_SBUSS_DATE;
	}

	public void setBEG_SBUSS_DATE(String bEGSBUSSDATE) {
		BEG_SBUSS_DATE = bEGSBUSSDATE;
	}

	/**
	 * @return the pLAY_BANK_NAME
	 */
	public String getPLAY_BANK_NAME() {
		return PLAY_BANK_NAME;
	}

	/**
	 * @param pLAYBANKNAME the pLAY_BANK_NAME to set
	 */
	public void setPLAY_BANK_NAME(String pLAYBANKNAME) {
		PLAY_BANK_NAME = pLAYBANKNAME;
	}

	/**
	 * @return the pLAY_BANK_ACCT
	 */
	public String getPLAY_BANK_ACCT() {
		return PLAY_BANK_ACCT;
	}

	/**
	 * @param pLAYBANKACCT the pLAY_BANK_ACCT to set
	 */
	public void setPLAY_BANK_ACCT(String pLAYBANKACCT) {
		PLAY_BANK_ACCT = pLAYBANKACCT;
	}

	public String getBUSS_STATE() {
		return BUSS_STATE;
	}

	public void setBUSS_STATE(String buss_state) {
		BUSS_STATE = buss_state;
	}

	/**
	 * @return the tERM_VERSION
	 */
	public String getTERM_VERSION() {
		return TERM_VERSION;
	}

	/**
	 * @param tERMVERSION the tERM_VERSION to set
	 */
	public void setTERM_VERSION(String tERMVERSION) {
		TERM_VERSION = tERMVERSION;
	}

	/**
	 * @return the bEG_BUSS_TYPE
	 */
	public String getBEG_BUSS_TYPE() {
		return BEG_BUSS_TYPE;
	}

	/**
	 * @param bEGBUSSTYPE the bEG_BUSS_TYPE to set
	 */
	public void setBEG_BUSS_TYPE(String bEGBUSSTYPE) {
		BEG_BUSS_TYPE = bEGBUSSTYPE;
	}

	public String getTERM_CLASS() {
		return TERM_CLASS;
	}

	public void setTERM_CLASS(String tERMCLASS) {
		TERM_CLASS = tERMCLASS;
	}

	public String getCUST_NAME() {
		return CUST_NAME;
	}

	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}
	
	
	
}
