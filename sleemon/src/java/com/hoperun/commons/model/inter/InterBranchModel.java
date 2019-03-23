package com.hoperun.commons.model.inter;
/**  组织
 * @func
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-2 上午09:49:54 
 */
public class InterBranchModel extends FixedInterModel {
	/**
	 * 组织编码
	 */
	private String ORG_CODE;
	/**
	 * 组织名称
	 */
	private String ORG_NAME;
	/**
	 * 英文名
	 */
	private String ENGLISH_NAME;
	/**
	 * 上级组织编码(选项)
	 */
	private String PARENT_CODE;
	/**
	 * 
	 */
	private String PARENT_CODE_VAL;
	/**
	 * 简称
	 */
	private String ABBREV_NAME;
	/**
	 * 组织级别(选项)
	 */
	private String ORG_LEVEL;
	/**
	 * 
	 */
	private String ORG_LEVEL_VAL;
	/**
	 * 组织类型(选项)
	 */
	private String ORG_TYPE;
	/**
	 * 
	 */
	private String ORG_TYPE_VAL;
	/**
	 * 组织机构代码
	 */
	private String ORGANIZATION_CODE;
	/**
	 * 法定代表人
	 */
	private String LEGAL_MAN;
	/**
	 * 营业执照号
	 */
	private String LICENSE_NO;
	/**
	 * 税务登记证号
	 */
	private String REGISTRY_NO;
	/**
	 * 主页
	 */
	private String URL;
	/**
	 * 国家(选项)
	 */
	private String NATION;
	/**
	 * 
	 */
	private String NATION_VAL;
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
	private String DISTRICT;
	/**
	 * 
	 */
	private String DISTRICT_VAL;
	/**
	 * 地址
	 */
	private String ADDRESS;
	/**
	 * 邮编
	 */
	private String POST_CODE;
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
	public String getORG_CODE() {
		return ORG_CODE;
	}
	public void setORG_CODE(String oRGCODE) {
		ORG_CODE = oRGCODE;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}
	public String getENGLISH_NAME() {
		return ENGLISH_NAME;
	}
	public void setENGLISH_NAME(String eNGLISHNAME) {
		ENGLISH_NAME = eNGLISHNAME;
	}
	public String getPARENT_CODE() {
		return PARENT_CODE;
	}
	public void setPARENT_CODE(String pARENTCODE) {
		PARENT_CODE = pARENTCODE;
	}
	public String getPARENT_CODE_VAL() {
		return PARENT_CODE_VAL;
	}
	public void setPARENT_CODE_VAL(String pARENTCODEVAL) {
		PARENT_CODE_VAL = pARENTCODEVAL;
	}
	public String getABBREV_NAME() {
		return ABBREV_NAME;
	}
	public void setABBREV_NAME(String aBBREVNAME) {
		ABBREV_NAME = aBBREVNAME;
	}
	public String getORG_LEVEL() {
		return ORG_LEVEL;
	}
	public void setORG_LEVEL(String oRGLEVEL) {
		ORG_LEVEL = oRGLEVEL;
	}
	public String getORG_LEVEL_VAL() {
		return ORG_LEVEL_VAL;
	}
	public void setORG_LEVEL_VAL(String oRGLEVELVAL) {
		ORG_LEVEL_VAL = oRGLEVELVAL;
	}
	public String getORG_TYPE() {
		return ORG_TYPE;
	}
	public void setORG_TYPE(String oRGTYPE) {
		ORG_TYPE = oRGTYPE;
	}
	public String getORG_TYPE_VAL() {
		return ORG_TYPE_VAL;
	}
	public void setORG_TYPE_VAL(String oRGTYPEVAL) {
		ORG_TYPE_VAL = oRGTYPEVAL;
	}
	public String getORGANIZATION_CODE() {
		return ORGANIZATION_CODE;
	}
	public void setORGANIZATION_CODE(String oRGANIZATIONCODE) {
		ORGANIZATION_CODE = oRGANIZATIONCODE;
	}
	public String getLEGAL_MAN() {
		return LEGAL_MAN;
	}
	public void setLEGAL_MAN(String lEGALMAN) {
		LEGAL_MAN = lEGALMAN;
	}
	public String getLICENSE_NO() {
		return LICENSE_NO;
	}
	public void setLICENSE_NO(String lICENSENO) {
		LICENSE_NO = lICENSENO;
	}
	public String getREGISTRY_NO() {
		return REGISTRY_NO;
	}
	public void setREGISTRY_NO(String rEGISTRYNO) {
		REGISTRY_NO = rEGISTRYNO;
	}
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getNATION() {
		return NATION;
	}
	public void setNATION(String nATION) {
		NATION = nATION;
	}
	public String getNATION_VAL() {
		return NATION_VAL;
	}
	public void setNATION_VAL(String nATIONVAL) {
		NATION_VAL = nATIONVAL;
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
	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public String getDISTRICT_VAL() {
		return DISTRICT_VAL;
	}
	public void setDISTRICT_VAL(String dISTRICTVAL) {
		DISTRICT_VAL = dISTRICTVAL;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getPOST_CODE() {
		return POST_CODE;
	}
	public void setPOST_CODE(String pOSTCODE) {
		POST_CODE = pOSTCODE;
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
	
	
}
