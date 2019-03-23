/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：QDXXModel.java
 */
package com.hoperun.erp.sale.channpunish.model;


/**
 * The Class ChannModel.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public class ChannpunishModel {
	
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
	 * 渠道简称
	 */
	private String CHANN_ABBR;

	/**
	 * 渠道类型
	 */
	private String CHANN_TYPE;

	/**
	 * 渠道级别
	 */
	private String CHAA_LVL;

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
	private String MOBILE;

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
	 * 经营性质
	 */
	private String BUSS_NATRUE;

	/**
	 * 经营范围
	 */
	private String BUSS_SCOPE;

	/**
	 * 增值税号
	 */
	private String VAT_NO;

	/**
	 * 发票抬头
	 */
	private String INVOICE_TI;

	/**
	 * 发票地址
	 */
	private String INVOICE_ADDR;

	/**
	 * 开户银行
	 */
	private String BANK_NAME;

	/**
	 * 银行账号
	 */
	private String BANK_ACCT;

	/**
	 * 财务对照码
	 */
	private String FI_CMP_NO;

	/**
	 * 保证金
	 */
	private double DEPOSIT;

	/**
	 * 欠款期限
	 */
	private Integer DEBT_DEF;

	/**
	 * 价格条款
	 */
	private String PRICE_CLAUSE;

	/**
	 * 营业执照附件
	 */
	private String BUSS_LIC_ATT;

	/**
	 * 税务登记附件
	 */
	private String TAX_BURDEN_ATT;

	/**
	 * 组织机构代码证附件
	 */
	private String ORG_CERT_ATT;

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
	 * 返利总额
	 */
	private double REBATE_TOTAL;
	
	/**
	 * 返利年份
	 */
	private String REBATE_YEAR;
	
	/**
	 * 当前返利
	 */
	private double REBATE_CURRT;
	
	/**
	 * 已使用返利
	 */
	private double REBATE_USED;
	
	/**
	 * 冻结返利
	 */
	private double REBATE_FREEZE;
	
	/**
	 * 信用总额
	 */
	private double CREDIT_TOTAL;
	
	/**
	 * 当前信用
	 */
	private double CREDIT_CURRT;
	
	/**
	 * 已使用信用
	 */
	private double CREDIT_USED;
	
	/**
	 * 冻结信用
	 */
	private double CREDIT_FREEZE;
	
	/**
	 * 临时信用
	 */
	private double TEMP_CREDIT;
	
	/**
	 * 临时信用有效期
	 */
	private String TEMP_CREDIT_VALDT;
	
	/**
	 * 删除标记
	 */
	private Integer DEL_FLAG;
	
	/**
	 * 付款比例
	 */
	private double PAY_RATE;
	
	/**
	 * 信用修改人
	 */
	private String CREDIT_CRE_NAME;
	
	/**
	 * 信用修改时间
	 */
	private String CREDIT_CRE_TIME;
	
	/**
	 * 返利修改人
	 */
	private String REBATE_UPD_NAME;
	
	/**
	 * 返利修改时间
	 */
	private String REBATE_UPD_TIME;
	/**
	 * 发货点ID
	 */
	private String SHIP_POINT_ID;
	/**
	 * 发货点编号
	 */
	private String SHIP_POINT_NO;
	/**
	 * 发货点名称
	 */
	private String SHIP_POINT_NAME;
	/**
	 * 区域服务中心ID
	 */
	private String AREA_SER_ID;
	/**
	 * 区域服务中心编号
	 */
	private String AREA_SER_NO;
	/**
	 * 区域服务中心名称
	 */
	private String AREA_SER_NAME;
	
	/**
	 * 终端销售折扣控制标记
	 */
	private String TERM_DECT_CTR_FLAG;

	/**
	 * @return the aREA_SER_ID
	 */
	public String getAREA_SER_ID() {
		return AREA_SER_ID;
	}

	/**
	 * @param aREASERID the aREA_SER_ID to set
	 */
	public void setAREA_SER_ID(String aREASERID) {
		AREA_SER_ID = aREASERID;
	}

	/**
	 * @return the aREA_SER_NO
	 */
	public String getAREA_SER_NO() {
		return AREA_SER_NO;
	}

	/**
	 * @param aREASERNO the aREA_SER_NO to set
	 */
	public void setAREA_SER_NO(String aREASERNO) {
		AREA_SER_NO = aREASERNO;
	}

	/**
	 * @return the aREA_SER_NAME
	 */
	public String getAREA_SER_NAME() {
		return AREA_SER_NAME;
	}

	/**
	 * @param aREASERNAME the aREA_SER_NAME to set
	 */
	public void setAREA_SER_NAME(String aREASERNAME) {
		AREA_SER_NAME = aREASERNAME;
	}

	/**
	 * @return the sHIP_POINT_ID
	 */
	public String getSHIP_POINT_ID() {
		return SHIP_POINT_ID;
	}

	/**
	 * @param sHIPPOINTID the sHIP_POINT_ID to set
	 */
	public void setSHIP_POINT_ID(String sHIPPOINTID) {
		SHIP_POINT_ID = sHIPPOINTID;
	}

	/**
	 * @return the sHIP_POINT_NO
	 */
	public String getSHIP_POINT_NO() {
		return SHIP_POINT_NO;
	}

	/**
	 * @param sHIPPOINTNO the sHIP_POINT_NO to set
	 */
	public void setSHIP_POINT_NO(String sHIPPOINTNO) {
		SHIP_POINT_NO = sHIPPOINTNO;
	}

	/**
	 * @return the sHIP_POINT_NAME
	 */
	public String getSHIP_POINT_NAME() {
		return SHIP_POINT_NAME;
	}

	/**
	 * @param sHIPPOINTNAME the sHIP_POINT_NAME to set
	 */
	public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
		SHIP_POINT_NAME = sHIPPOINTNAME;
	}

	/**
	 * @return the cHANN_ID
	 */
	public String getCHANN_ID() {
		return CHANN_ID;
	}

	/**
	 * @param cHANNID the cHANN_ID to set
	 */
	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}

	/**
	 * @return the cHANN_NO
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}

	/**
	 * @param cHANNNO the cHANN_NO to set
	 */
	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}

	/**
	 * @return the cHANN_NAME
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}

	/**
	 * @param cHANNNAME the cHANN_NAME to set
	 */
	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}

	/**
	 * @return the cHANN_ABBR
	 */
	public String getCHANN_ABBR() {
		return CHANN_ABBR;
	}

	/**
	 * @param cHANNABBR the cHANN_ABBR to set
	 */
	public void setCHANN_ABBR(String cHANNABBR) {
		CHANN_ABBR = cHANNABBR;
	}

	/**
	 * @return the cHANN_TYPE
	 */
	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}

	/**
	 * @param cHANNTYPE the cHANN_TYPE to set
	 */
	public void setCHANN_TYPE(String cHANNTYPE) {
		CHANN_TYPE = cHANNTYPE;
	}

	/**
	 * @return the cHAA_LVL
	 */
	public String getCHAA_LVL() {
		return CHAA_LVL;
	}

	/**
	 * @param cHAALVL the cHAA_LVL to set
	 */
	public void setCHAA_LVL(String cHAALVL) {
		CHAA_LVL = cHAALVL;
	}

	/**
	 * @return the cHANN_ID_P
	 */
	public String getCHANN_ID_P() {
		return CHANN_ID_P;
	}

	/**
	 * @param cHANNIDP the cHANN_ID_P to set
	 */
	public void setCHANN_ID_P(String cHANNIDP) {
		CHANN_ID_P = cHANNIDP;
	}

	/**
	 * @return the cHANN_NO_P
	 */
	public String getCHANN_NO_P() {
		return CHANN_NO_P;
	}

	/**
	 * @param cHANNNOP the cHANN_NO_P to set
	 */
	public void setCHANN_NO_P(String cHANNNOP) {
		CHANN_NO_P = cHANNNOP;
	}

	/**
	 * @return the cHANN_NAME_P
	 */
	public String getCHANN_NAME_P() {
		return CHANN_NAME_P;
	}

	/**
	 * @param cHANNNAMEP the cHANN_NAME_P to set
	 */
	public void setCHANN_NAME_P(String cHANNNAMEP) {
		CHANN_NAME_P = cHANNNAMEP;
	}

	/**
	 * @return the aREA_ID
	 */
	public String getAREA_ID() {
		return AREA_ID;
	}

	/**
	 * @param aREAID the aREA_ID to set
	 */
	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}

	/**
	 * @return the aREA_NO
	 */
	public String getAREA_NO() {
		return AREA_NO;
	}

	/**
	 * @param aREANO the aREA_NO to set
	 */
	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}

	/**
	 * @return the aREA_NAME
	 */
	public String getAREA_NAME() {
		return AREA_NAME;
	}

	/**
	 * @param aREANAME the aREA_NAME to set
	 */
	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}

	/**
	 * @return the zONE_ID
	 */
	public String getZONE_ID() {
		return ZONE_ID;
	}

	/**
	 * @param zONEID the zONE_ID to set
	 */
	public void setZONE_ID(String zONEID) {
		ZONE_ID = zONEID;
	}

	/**
	 * @return the nATION
	 */
	public String getNATION() {
		return NATION;
	}

	/**
	 * @param nATION the nATION to set
	 */
	public void setNATION(String nATION) {
		NATION = nATION;
	}

	/**
	 * @return the pROV
	 */
	public String getPROV() {
		return PROV;
	}

	/**
	 * @param pROV the pROV to set
	 */
	public void setPROV(String pROV) {
		PROV = pROV;
	}

	/**
	 * @return the cITY
	 */
	public String getCITY() {
		return CITY;
	}

	/**
	 * @param cITY the cITY to set
	 */
	public void setCITY(String cITY) {
		CITY = cITY;
	}

	/**
	 * @return the cOUNTY
	 */
	public String getCOUNTY() {
		return COUNTY;
	}

	/**
	 * @param cOUNTY the cOUNTY to set
	 */
	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}

	/**
	 * @return the cITY_TYPE
	 */
	public String getCITY_TYPE() {
		return CITY_TYPE;
	}

	/**
	 * @param cITYTYPE the cITY_TYPE to set
	 */
	public void setCITY_TYPE(String cITYTYPE) {
		CITY_TYPE = cITYTYPE;
	}

	/**
	 * @return the pERSON_CON
	 */
	public String getPERSON_CON() {
		return PERSON_CON;
	}

	/**
	 * @param pERSONCON the pERSON_CON to set
	 */
	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}

	/**
	 * @return the tEL
	 */
	public String getTEL() {
		return TEL;
	}

	/**
	 * @param tEL the tEL to set
	 */
	public void setTEL(String tEL) {
		TEL = tEL;
	}

	/**
	 * @return the mOBILE
	 */
	public String getMOBILE() {
		return MOBILE;
	}

	/**
	 * @param mOBILE the mOBILE to set
	 */
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	/**
	 * @return the tAX
	 */
	public String getTAX() {
		return TAX;
	}

	/**
	 * @param tAX the tAX to set
	 */
	public void setTAX(String tAX) {
		TAX = tAX;
	}

	/**
	 * @return the pOST
	 */
	public String getPOST() {
		return POST;
	}

	/**
	 * @param pOST the pOST to set
	 */
	public void setPOST(String pOST) {
		POST = pOST;
	}

	/**
	 * @return the aDDRESS
	 */
	public String getADDRESS() {
		return ADDRESS;
	}

	/**
	 * @param aDDRESS the aDDRESS to set
	 */
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	/**
	 * @return the eMAIL
	 */
	public String getEMAIL() {
		return EMAIL;
	}

	/**
	 * @param eMAIL the eMAIL to set
	 */
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	/**
	 * @return the wEB
	 */
	public String getWEB() {
		return WEB;
	}

	/**
	 * @param wEB the wEB to set
	 */
	public void setWEB(String wEB) {
		WEB = wEB;
	}

	/**
	 * @return the lEGAL_REPRE
	 */
	public String getLEGAL_REPRE() {
		return LEGAL_REPRE;
	}

	/**
	 * @param lEGALREPRE the lEGAL_REPRE to set
	 */
	public void setLEGAL_REPRE(String lEGALREPRE) {
		LEGAL_REPRE = lEGALREPRE;
	}

	/**
	 * @return the bUSS_LIC
	 */
	public String getBUSS_LIC() {
		return BUSS_LIC;
	}

	/**
	 * @param bUSSLIC the bUSS_LIC to set
	 */
	public void setBUSS_LIC(String bUSSLIC) {
		BUSS_LIC = bUSSLIC;
	}

	/**
	 * @return the aX_BURDEN
	 */
	public String getAX_BURDEN() {
		return AX_BURDEN;
	}

	/**
	 * @param aXBURDEN the aX_BURDEN to set
	 */
	public void setAX_BURDEN(String aXBURDEN) {
		AX_BURDEN = aXBURDEN;
	}

	/**
	 * @return the oRG_CODE_CERT
	 */
	public String getORG_CODE_CERT() {
		return ORG_CODE_CERT;
	}

	/**
	 * @param oRGCODECERT the oRG_CODE_CERT to set
	 */
	public void setORG_CODE_CERT(String oRGCODECERT) {
		ORG_CODE_CERT = oRGCODECERT;
	}

	/**
	 * @return the bUSS_NATRUE
	 */
	public String getBUSS_NATRUE() {
		return BUSS_NATRUE;
	}

	/**
	 * @param bUSSNATRUE the bUSS_NATRUE to set
	 */
	public void setBUSS_NATRUE(String bUSSNATRUE) {
		BUSS_NATRUE = bUSSNATRUE;
	}

	/**
	 * @return the bUSS_SCOPE
	 */
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}

	/**
	 * @param bUSSSCOPE the bUSS_SCOPE to set
	 */
	public void setBUSS_SCOPE(String bUSSSCOPE) {
		BUSS_SCOPE = bUSSSCOPE;
	}

	/**
	 * @return the vAT_NO
	 */
	public String getVAT_NO() {
		return VAT_NO;
	}

	/**
	 * @param vATNO the vAT_NO to set
	 */
	public void setVAT_NO(String vATNO) {
		VAT_NO = vATNO;
	}

	/**
	 * @return the iNVOICE_TI
	 */
	public String getINVOICE_TI() {
		return INVOICE_TI;
	}

	/**
	 * @param iNVOICETI the iNVOICE_TI to set
	 */
	public void setINVOICE_TI(String iNVOICETI) {
		INVOICE_TI = iNVOICETI;
	}

	/**
	 * @return the iNVOICE_ADDR
	 */
	public String getINVOICE_ADDR() {
		return INVOICE_ADDR;
	}

	/**
	 * @param iNVOICEADDR the iNVOICE_ADDR to set
	 */
	public void setINVOICE_ADDR(String iNVOICEADDR) {
		INVOICE_ADDR = iNVOICEADDR;
	}

	/**
	 * @return the bANK_NAME
	 */
	public String getBANK_NAME() {
		return BANK_NAME;
	}

	/**
	 * @param bANKNAME the bANK_NAME to set
	 */
	public void setBANK_NAME(String bANKNAME) {
		BANK_NAME = bANKNAME;
	}

	/**
	 * @return the bANK_ACCT
	 */
	public String getBANK_ACCT() {
		return BANK_ACCT;
	}

	/**
	 * @param bANKACCT the bANK_ACCT to set
	 */
	public void setBANK_ACCT(String bANKACCT) {
		BANK_ACCT = bANKACCT;
	}

	/**
	 * @return the fI_CMP_NO
	 */
	public String getFI_CMP_NO() {
		return FI_CMP_NO;
	}

	/**
	 * @param fICMPNO the fI_CMP_NO to set
	 */
	public void setFI_CMP_NO(String fICMPNO) {
		FI_CMP_NO = fICMPNO;
	}

	/**
	 * @return the dEPOSIT
	 */
	public double getDEPOSIT() {
		return DEPOSIT;
	}

	/**
	 * @param dEPOSIT the dEPOSIT to set
	 */
	public void setDEPOSIT(double dEPOSIT) {
		DEPOSIT = dEPOSIT;
	}

	/**
	 * @return the dEBT_DEF
	 */
	public Integer getDEBT_DEF() {
		return DEBT_DEF;
	}

	/**
	 * @param dEBTDEF the dEBT_DEF to set
	 */
	public void setDEBT_DEF(Integer dEBTDEF) {
		DEBT_DEF = dEBTDEF;
	}

	/**
	 * @return the pRICE_CLAUSE
	 */
	public String getPRICE_CLAUSE() {
		return PRICE_CLAUSE;
	}

	/**
	 * @param pRICECLAUSE the pRICE_CLAUSE to set
	 */
	public void setPRICE_CLAUSE(String pRICECLAUSE) {
		PRICE_CLAUSE = pRICECLAUSE;
	}

	/**
	 * @return the bUSS_LIC_ATT
	 */
	public String getBUSS_LIC_ATT() {
		return BUSS_LIC_ATT;
	}

	/**
	 * @param bUSSLICATT the bUSS_LIC_ATT to set
	 */
	public void setBUSS_LIC_ATT(String bUSSLICATT) {
		BUSS_LIC_ATT = bUSSLICATT;
	}

	/**
	 * @return the tAX_BURDEN_ATT
	 */
	public String getTAX_BURDEN_ATT() {
		return TAX_BURDEN_ATT;
	}

	/**
	 * @param tAXBURDENATT the tAX_BURDEN_ATT to set
	 */
	public void setTAX_BURDEN_ATT(String tAXBURDENATT) {
		TAX_BURDEN_ATT = tAXBURDENATT;
	}

	/**
	 * @return the oRG_CERT_ATT
	 */
	public String getORG_CERT_ATT() {
		return ORG_CERT_ATT;
	}

	/**
	 * @param oRGCERTATT the oRG_CERT_ATT to set
	 */
	public void setORG_CERT_ATT(String oRGCERTATT) {
		ORG_CERT_ATT = oRGCERTATT;
	}

	/**
	 * @return the sTATE
	 */
	public String getSTATE() {
		return STATE;
	}

	/**
	 * @param sTATE the sTATE to set
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * @param rEMARK the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	/**
	 * @return the cREATOR
	 */
	public String getCREATOR() {
		return CREATOR;
	}

	/**
	 * @param cREATOR the cREATOR to set
	 */
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	/**
	 * @return the cRE_NAME
	 */
	public String getCRE_NAME() {
		return CRE_NAME;
	}

	/**
	 * @param cRENAME the cRE_NAME to set
	 */
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}

	/**
	 * @return the cRE_TIME
	 */
	public String getCRE_TIME() {
		return CRE_TIME;
	}

	/**
	 * @param cRETIME the cRE_TIME to set
	 */
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}

	/**
	 * @return the uPDATOR
	 */
	public String getUPDATOR() {
		return UPDATOR;
	}

	/**
	 * @param uPDATOR the uPDATOR to set
	 */
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}

	/**
	 * @return the uPD_NAME
	 */
	public String getUPD_NAME() {
		return UPD_NAME;
	}

	/**
	 * @param uPDNAME the uPD_NAME to set
	 */
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}

	/**
	 * @return the uPD_TIME
	 */
	public String getUPD_TIME() {
		return UPD_TIME;
	}

	/**
	 * @param uPDTIME the uPD_TIME to set
	 */
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}

	/**
	 * @return the dEPT_ID
	 */
	public String getDEPT_ID() {
		return DEPT_ID;
	}

	/**
	 * @param dEPTID the dEPT_ID to set
	 */
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}

	/**
	 * @return the dEPT_NAME
	 */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	/**
	 * @param dEPTNAME the dEPT_NAME to set
	 */
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}

	/**
	 * @return the oRG_ID
	 */
	public String getORG_ID() {
		return ORG_ID;
	}

	/**
	 * @param oRGID the oRG_ID to set
	 */
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}

	/**
	 * @return the oRG_NAME
	 */
	public String getORG_NAME() {
		return ORG_NAME;
	}

	/**
	 * @param oRGNAME the oRG_NAME to set
	 */
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}

	/**
	 * @return the lEDGER_ID
	 */
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}

	/**
	 * @param lEDGERID the lEDGER_ID to set
	 */
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}

	/**
	 * @return the lEDGER_NAME
	 */
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}

	/**
	 * @param lEDGERNAME the lEDGER_NAME to set
	 */
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}

	/**
	 * @return the rEBATE_TOTAL
	 */
	public double getREBATE_TOTAL() {
		return REBATE_TOTAL;
	}

	/**
	 * @param rEBATETOTAL the rEBATE_TOTAL to set
	 */
	public void setREBATE_TOTAL(double rEBATETOTAL) {
		REBATE_TOTAL = rEBATETOTAL;
	}

	/**
	 * @return the rEBATE_YEAR
	 */
	public String getREBATE_YEAR() {
		return REBATE_YEAR;
	}

	/**
	 * @param rEBATEYEAR the rEBATE_YEAR to set
	 */
	public void setREBATE_YEAR(String rEBATEYEAR) {
		REBATE_YEAR = rEBATEYEAR;
	}

	/**
	 * @return the rEBATE_CURRT
	 */
	public double getREBATE_CURRT() {
		return REBATE_CURRT;
	}

	/**
	 * @param rEBATECURRT the rEBATE_CURRT to set
	 */
	public void setREBATE_CURRT(double rEBATECURRT) {
		REBATE_CURRT = rEBATECURRT;
	}

	/**
	 * @return the rEBATE_USED
	 */
	public double getREBATE_USED() {
		return REBATE_USED;
	}

	/**
	 * @param rEBATEUSED the rEBATE_USED to set
	 */
	public void setREBATE_USED(double rEBATEUSED) {
		REBATE_USED = rEBATEUSED;
	}

	/**
	 * @return the rEBATE_FREEZE
	 */
	public double getREBATE_FREEZE() {
		return REBATE_FREEZE;
	}

	/**
	 * @param rEBATEFREEZE the rEBATE_FREEZE to set
	 */
	public void setREBATE_FREEZE(double rEBATEFREEZE) {
		REBATE_FREEZE = rEBATEFREEZE;
	}

	/**
	 * @return the cREDIT_TOTAL
	 */
	public double getCREDIT_TOTAL() {
		return CREDIT_TOTAL;
	}

	/**
	 * @param cREDITTOTAL the cREDIT_TOTAL to set
	 */
	public void setCREDIT_TOTAL(double cREDITTOTAL) {
		CREDIT_TOTAL = cREDITTOTAL;
	}

	/**
	 * @return the cREDIT_CURRT
	 */
	public double getCREDIT_CURRT() {
		return CREDIT_CURRT;
	}

	/**
	 * @param cREDITCURRT the cREDIT_CURRT to set
	 */
	public void setCREDIT_CURRT(double cREDITCURRT) {
		CREDIT_CURRT = cREDITCURRT;
	}

	/**
	 * @return the cREDIT_USED
	 */
	public double getCREDIT_USED() {
		return CREDIT_USED;
	}

	/**
	 * @param cREDITUSED the cREDIT_USED to set
	 */
	public void setCREDIT_USED(double cREDITUSED) {
		CREDIT_USED = cREDITUSED;
	}

	/**
	 * @return the cREDIT_FREEZE
	 */
	public double getCREDIT_FREEZE() {
		return CREDIT_FREEZE;
	}

	/**
	 * @param cREDITFREEZE the cREDIT_FREEZE to set
	 */
	public void setCREDIT_FREEZE(double cREDITFREEZE) {
		CREDIT_FREEZE = cREDITFREEZE;
	}

	/**
	 * @return the tEMP_CREDIT
	 */
	public double getTEMP_CREDIT() {
		return TEMP_CREDIT;
	}

	/**
	 * @param tEMPCREDIT the tEMP_CREDIT to set
	 */
	public void setTEMP_CREDIT(double tEMPCREDIT) {
		TEMP_CREDIT = tEMPCREDIT;
	}

	/**
	 * @return the tEMP_CREDIT_VALDT
	 */
	public String getTEMP_CREDIT_VALDT() {
		return TEMP_CREDIT_VALDT;
	}

	/**
	 * @param tEMPCREDITVALDT the tEMP_CREDIT_VALDT to set
	 */
	public void setTEMP_CREDIT_VALDT(String tEMPCREDITVALDT) {
		TEMP_CREDIT_VALDT = tEMPCREDITVALDT;
	}

	/**
	 * @return the dEL_FLAG
	 */
	public Integer getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(Integer dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	/**
	 * @return the pAY_RATE
	 */
	public double getPAY_RATE() {
		return PAY_RATE;
	}

	/**
	 * @param pAYRATE the pAY_RATE to set
	 */
	public void setPAY_RATE(double pAYRATE) {
		PAY_RATE = pAYRATE;
	}

	/**
	 * @return the cREDIT_CRE_NAME
	 */
	public String getCREDIT_CRE_NAME() {
		return CREDIT_CRE_NAME;
	}

	/**
	 * @param cREDITCRENAME the cREDIT_CRE_NAME to set
	 */
	public void setCREDIT_CRE_NAME(String cREDITCRENAME) {
		CREDIT_CRE_NAME = cREDITCRENAME;
	}

	/**
	 * @return the cREDIT_CRE_TIME
	 */
	public String getCREDIT_CRE_TIME() {
		return CREDIT_CRE_TIME;
	}

	/**
	 * @param cREDITCRETIME the cREDIT_CRE_TIME to set
	 */
	public void setCREDIT_CRE_TIME(String cREDITCRETIME) {
		CREDIT_CRE_TIME = cREDITCRETIME;
	}

	/**
	 * @return the rEBATE_UPD_NAME
	 */
	public String getREBATE_UPD_NAME() {
		return REBATE_UPD_NAME;
	}

	/**
	 * @param rEBATEUPDNAME the rEBATE_UPD_NAME to set
	 */
	public void setREBATE_UPD_NAME(String rEBATEUPDNAME) {
		REBATE_UPD_NAME = rEBATEUPDNAME;
	}

	/**
	 * @return the rEBATE_UPD_TIME
	 */
	public String getREBATE_UPD_TIME() {
		return REBATE_UPD_TIME;
	}

	/**
	 * @param rEBATEUPDTIME the rEBATE_UPD_TIME to set
	 */
	public void setREBATE_UPD_TIME(String rEBATEUPDTIME) {
		REBATE_UPD_TIME = rEBATEUPDTIME;
	}

	/**
	 * @return the tERM_DECT_CTR_FLAG
	 */
	public String getTERM_DECT_CTR_FLAG() {
		return TERM_DECT_CTR_FLAG;
	}

	/**
	 * @param tERMDECTCTRFLAG the tERM_DECT_CTR_FLAG to set
	 */
	public void setTERM_DECT_CTR_FLAG(String tERMDECTCTRFLAG) {
		TERM_DECT_CTR_FLAG = tERMDECTCTRFLAG;
	}

}