package com.hoperun.commons.model.inter;
/**  
 * @func  客户
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-1 上午10:31:05 
 */
public class InterChannModel extends FixedInterModel{
	/**
	 * 客户编码
	 */
	private String CUST_CODE;
	/**
	 * 客户名称
	 */
	private String CUST_NAME;
	/**
	 * 简称
	 */
	private String CUST_NAME_SIMPLE;
	/**
	 * 加盟时间
	 */
	private String ENTER_TIME;
	/**
	 * 市场类别(选项)
	 */
	private String CUST_MAKET_TYPE;
	/**
	 * 
	 */
	private String CUST_MAKET_TYPE_VAL;
	/**
	 * 国内外区分(选项)
	 */
	private String CUST_COUNTRY_INSIDE;
	/**
	 * 
	 */
	private String CUST_COUNTRY_INSIDE_VAL;
	/**
	 * 是否一次性客户
	 */
	private String IS_DISPOSABLE;
	/**
	 * 是否虚拟客户
	 */
	private String IS_VIRTUAL;
	/**
	 * 是否关联
	 */
	private String IS_RELATION;
	/**
	 * 组织机构代码
	 */
	private String ORG_CODE;
	/**
	 * 税务登记证号
	 */
	private String CUST_TAX_ID;
	/**
	 * 营业执照号
	 */
	private String CUST_BUSI_LICENSE_ID;
	/**
	 * 注册资金
	 */
	private String REG_CAPITAL;
	/**
	 * 法定代表人
	 */
	private String LEGAL_PERSON;
	/**
	 * 洲(选项)
	 */
	private String CONTINENT;
	/**
	 * 国家(选项)
	 */
	private String COUNTRY;
	/**
	 * 
	 */
	private String COUNTRY_VAL;
	/**
	 * 省份(选项)
	 */
	private String PROVINCE;
	/**
	 * 
	 */
	private String PROVINCE_VAL;
	/**
	 * 城市(选项)
	 */
	private String CITY;
	/**
	 * 
	 */
	private String CITY_VAL;
	/**
	 * 区县(选项)
	 */
	private String COUNTY;
	/**
	 * 
	 */
	private String COUNTY_VAL;
	/**
	 * 地址
	 */
	private String ADDRESS;
	/**
	 * 邮编
	 */
	private String POSTAL_CODE;
	/**
	 * 联系人
	 */
	private String CONTACT_PERSON;
	/**
	 * 电话
	 */
	private String PHONE;
	/**
	 * 传真
	 */
	private String FAX;
	/**
	 * 电子邮件
	 */
	private String EMAIL;
	/**
	 * 交易币种(选项)
	 */
	private String CURRENCY;
	/**
	 * 
	 */
	private String CURRENCY_VAL;
	/**
	 * 信用额度
	 */
	private String CREDIT_LINE;
	/**
	 * 开户行名称
	 */
	private String BANK;
	/**
	 * 银行账号
	 */
	private String BANK_ACCOUNT;
	/**
	 * 付款方式(选项)
	 */
	private String PAYMENT;
	/**
	 * 
	 */
	private String PAYMENT_VAL_VAL;
	/**
	 * 销售区域(选项)
	 */
	private String SALE_REGION;
	/**
	 * 
	 */
	private String SALE_REGION_VAL;
	/**
	 * 销售渠道(选项)
	 */
	private String SALE_CHANNEL;
	/**
	 * 
	 */
	private String SALE_CHANNEL_VAL;
	/**
	 * 客户类别(选项)
	 */
	private String CUST_TYPE;
	/**
	 * 
	 */
	private String CUST_TYPE_VAL;
	/**
	 * 	客户等级(选项)
	 */
	private String CUST_CLASS;
	/**
	 * 
	 */
	private String CUST_CLASS_VAL;
	/**
	 * 默认组织代码
	 */
	private String DEFAULT_ORG;
	/**
	 * 默认组织名称
	 */
	private String DEFAULT_ORG_NAME;
	
	public String getCUST_CODE() {
		return CUST_CODE;
	}
	public void setCUST_CODE(String cUSTCODE) {
		CUST_CODE = cUSTCODE;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}
	public String getCUST_NAME_SIMPLE() {
		return CUST_NAME_SIMPLE;
	}
	public void setCUST_NAME_SIMPLE(String cUSTNAMESIMPLE) {
		CUST_NAME_SIMPLE = cUSTNAMESIMPLE;
	}
	public String getENTER_TIME() {
		return ENTER_TIME;
	}
	public void setENTER_TIME(String eNTERTIME) {
		ENTER_TIME = eNTERTIME;
	}
	public String getCUST_MAKET_TYPE() {
		return CUST_MAKET_TYPE;
	}
	public void setCUST_MAKET_TYPE(String cUSTMAKETTYPE) {
		CUST_MAKET_TYPE = cUSTMAKETTYPE;
	}
	public String getCUST_MAKET_TYPE_VAL() {
		return CUST_MAKET_TYPE_VAL;
	}
	public void setCUST_MAKET_TYPE_VAL(String cUSTMAKETTYPEVAL) {
		CUST_MAKET_TYPE_VAL = cUSTMAKETTYPEVAL;
	}
	public String getCUST_COUNTRY_INSIDE() {
		return CUST_COUNTRY_INSIDE;
	}
	public void setCUST_COUNTRY_INSIDE(String cUSTCOUNTRYINSIDE) {
		CUST_COUNTRY_INSIDE = cUSTCOUNTRYINSIDE;
	}
	public String getCUST_COUNTRY_INSIDE_VAL() {
		return CUST_COUNTRY_INSIDE_VAL;
	}
	public void setCUST_COUNTRY_INSIDE_VAL(String cUSTCOUNTRYINSIDEVAL) {
		CUST_COUNTRY_INSIDE_VAL = cUSTCOUNTRYINSIDEVAL;
	}
	public String getIS_DISPOSABLE() {
		return IS_DISPOSABLE;
	}
	public void setIS_DISPOSABLE(String iSDISPOSABLE) {
		IS_DISPOSABLE = iSDISPOSABLE;
	}
	public String getIS_VIRTUAL() {
		return IS_VIRTUAL;
	}
	public void setIS_VIRTUAL(String iSVIRTUAL) {
		IS_VIRTUAL = iSVIRTUAL;
	}
	public String getIS_RELATION() {
		return IS_RELATION;
	}
	public void setIS_RELATION(String iSRELATION) {
		IS_RELATION = iSRELATION;
	}
	public String getORG_CODE() {
		return ORG_CODE;
	}
	public void setORG_CODE(String oRGCODE) {
		ORG_CODE = oRGCODE;
	}
	public String getCUST_TAX_ID() {
		return CUST_TAX_ID;
	}
	public void setCUST_TAX_ID(String cUSTTAXID) {
		CUST_TAX_ID = cUSTTAXID;
	}
	public String getCUST_BUSI_LICENSE_ID() {
		return CUST_BUSI_LICENSE_ID;
	}
	public void setCUST_BUSI_LICENSE_ID(String cUSTBUSILICENSEID) {
		CUST_BUSI_LICENSE_ID = cUSTBUSILICENSEID;
	}
	public String getREG_CAPITAL() {
		return REG_CAPITAL;
	}
	public void setREG_CAPITAL(String rEGCAPITAL) {
		REG_CAPITAL = rEGCAPITAL;
	}
	public String getLEGAL_PERSON() {
		return LEGAL_PERSON;
	}
	public void setLEGAL_PERSON(String lEGALPERSON) {
		LEGAL_PERSON = lEGALPERSON;
	}
	public String getCONTINENT() {
		return CONTINENT;
	}
	public void setCONTINENT(String cONTINENT) {
		CONTINENT = cONTINENT;
	}
	public String getCOUNTRY() {
		return COUNTRY;
	}
	public void setCOUNTRY(String cOUNTRY) {
		COUNTRY = cOUNTRY;
	}
	public String getCOUNTRY_VAL() {
		return COUNTRY_VAL;
	}
	public void setCOUNTRY_VAL(String cOUNTRYVAL) {
		COUNTRY_VAL = cOUNTRYVAL;
	}
	public String getPROVINCE() {
		return PROVINCE;
	}
	public void setPROVINCE(String pROVINCE) {
		PROVINCE = pROVINCE;
	}
	public String getPROVINCE_VAL() {
		return PROVINCE_VAL;
	}
	public void setPROVINCE_VAL(String pROVINCEVAL) {
		PROVINCE_VAL = pROVINCEVAL;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getCITY_VAL() {
		return CITY_VAL;
	}
	public void setCITY_VAL(String cITYVAL) {
		CITY_VAL = cITYVAL;
	}
	public String getCOUNTY() {
		return COUNTY;
	}
	public void setCOUNTY(String cOUNTY) {
		COUNTY = cOUNTY;
	}
	public String getCOUNTY_VAL() {
		return COUNTY_VAL;
	}
	public void setCOUNTY_VAL(String cOUNTYVAL) {
		COUNTY_VAL = cOUNTYVAL;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}
	public void setPOSTAL_CODE(String pOSTALCODE) {
		POSTAL_CODE = pOSTALCODE;
	}
	public String getCONTACT_PERSON() {
		return CONTACT_PERSON;
	}
	public void setCONTACT_PERSON(String cONTACTPERSON) {
		CONTACT_PERSON = cONTACTPERSON;
	}
	public String getPHONE() {
		return PHONE;
	}
	public void setPHONE(String pHONE) {
		PHONE = pHONE;
	}
	public String getFAX() {
		return FAX;
	}
	public void setFAX(String fAX) {
		FAX = fAX;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getCURRENCY() {
		return CURRENCY;
	}
	public void setCURRENCY(String cURRENCY) {
		CURRENCY = cURRENCY;
	}
	public String getCURRENCY_VAL() {
		return CURRENCY_VAL;
	}
	public void setCURRENCY_VAL(String cURRENCYVAL) {
		CURRENCY_VAL = cURRENCYVAL;
	}
	public String getCREDIT_LINE() {
		return CREDIT_LINE;
	}
	public void setCREDIT_LINE(String cREDITLINE) {
		CREDIT_LINE = cREDITLINE;
	}
	public String getBANK() {
		return BANK;
	}
	public void setBANK(String bANK) {
		BANK = bANK;
	}
	public String getBANK_ACCOUNT() {
		return BANK_ACCOUNT;
	}
	public void setBANK_ACCOUNT(String bANKACCOUNT) {
		BANK_ACCOUNT = bANKACCOUNT;
	}
	public String getPAYMENT() {
		return PAYMENT;
	}
	public void setPAYMENT(String pAYMENT) {
		PAYMENT = pAYMENT;
	}
	public String getPAYMENT_VAL_VAL() {
		return PAYMENT_VAL_VAL;
	}
	public void setPAYMENT_VAL_VAL(String pAYMENTVALVAL) {
		PAYMENT_VAL_VAL = pAYMENTVALVAL;
	}
	public String getSALE_REGION() {
		return SALE_REGION;
	}
	public void setSALE_REGION(String sALEREGION) {
		SALE_REGION = sALEREGION;
	}
	public String getSALE_REGION_VAL() {
		return SALE_REGION_VAL;
	}
	public void setSALE_REGION_VAL(String sALEREGIONVAL) {
		SALE_REGION_VAL = sALEREGIONVAL;
	}
	public String getSALE_CHANNEL() {
		return SALE_CHANNEL;
	}
	public void setSALE_CHANNEL(String sALECHANNEL) {
		SALE_CHANNEL = sALECHANNEL;
	}
	public String getSALE_CHANNEL_VAL() {
		return SALE_CHANNEL_VAL;
	}
	public void setSALE_CHANNEL_VAL(String sALECHANNELVAL) {
		SALE_CHANNEL_VAL = sALECHANNELVAL;
	}
	public String getCUST_TYPE() {
		return CUST_TYPE;
	}
	public void setCUST_TYPE(String cUSTTYPE) {
		CUST_TYPE = cUSTTYPE;
	}
	public String getCUST_TYPE_VAL() {
		return CUST_TYPE_VAL;
	}
	public void setCUST_TYPE_VAL(String cUSTTYPEVAL) {
		CUST_TYPE_VAL = cUSTTYPEVAL;
	}
	public String getCUST_CLASS() {
		return CUST_CLASS;
	}
	public void setCUST_CLASS(String cUSTCLASS) {
		CUST_CLASS = cUSTCLASS;
	}
	public String getCUST_CLASS_VAL() {
		return CUST_CLASS_VAL;
	}
	public void setCUST_CLASS_VAL(String cUSTCLASSVAL) {
		CUST_CLASS_VAL = cUSTCLASSVAL;
	}
	public String getDEFAULT_ORG() {
		return DEFAULT_ORG;
	}
	public void setDEFAULT_ORG(String dEFAULTORG) {
		DEFAULT_ORG = dEFAULTORG;
	}
	public String getDEFAULT_ORG_NAME() {
		return DEFAULT_ORG_NAME;
	}
	public void setDEFAULT_ORG_NAME(String dEFAULTORGNAME) {
		DEFAULT_ORG_NAME = dEFAULTORGNAME;
	}
	
	
}
