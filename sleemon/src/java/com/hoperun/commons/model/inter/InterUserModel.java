package com.hoperun.commons.model.inter;
/**  
 * @func 用户
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-6 上午09:05:30 
 */
public class InterUserModel extends FixedInterModel {
	/**
	 * 用户编码
	 */
	private String ACCOUNT_NO;
	/**
	 * 用户名
	 */
	private String MDM_USER_NAME;
	/**
	 * 姓名
	 */
	private String NAME;
	/**
	 * 用户密码
	 */
	private String MDM_USER_PWD;
	/**
	 * 用户类型(选项)
	 */
	private String MDM_USER_TYPE;
	/**
	 * 
	 */
	private String MDM_USER_TYPE_VAL;
	/**
	 * 默认语言(选项)
	 */
	private String DEFAULT_LANGUAGE;
	/**
	 * 
	 */
	private String DEFAULT_LANGUAGE_VAL;
	/**
	 * 员工编号
	 */
	private String MDM_USER_NO;
	/**
	 * 所属组织(选项)
	 */
	private String ORG_ID;
	/**
	 * 
	 */
	private String ORG_ID_VAL;
	/**
	 * 生效日期
	 */
	private String VALID_DATE;
	/**
	 * 失效日期
	 */
	private String INVALID_DATE;
	public String getACCOUNT_NO() {
		return ACCOUNT_NO;
	}
	public void setACCOUNT_NO(String aCCOUNTNO) {
		ACCOUNT_NO = aCCOUNTNO;
	}
	public String getMDM_USER_NAME() {
		return MDM_USER_NAME;
	}
	public void setMDM_USER_NAME(String mDMUSERNAME) {
		MDM_USER_NAME = mDMUSERNAME;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getMDM_USER_PWD() {
		return MDM_USER_PWD;
	}
	public void setMDM_USER_PWD(String mDMUSERPWD) {
		MDM_USER_PWD = mDMUSERPWD;
	}
	public String getMDM_USER_TYPE() {
		return MDM_USER_TYPE;
	}
	public void setMDM_USER_TYPE(String mDMUSERTYPE) {
		MDM_USER_TYPE = mDMUSERTYPE;
	}
	public String getMDM_USER_TYPE_VAL() {
		return MDM_USER_TYPE_VAL;
	}
	public void setMDM_USER_TYPE_VAL(String mDMUSERTYPEVAL) {
		MDM_USER_TYPE_VAL = mDMUSERTYPEVAL;
	}
	public String getDEFAULT_LANGUAGE() {
		return DEFAULT_LANGUAGE;
	}
	public void setDEFAULT_LANGUAGE(String dEFAULTLANGUAGE) {
		DEFAULT_LANGUAGE = dEFAULTLANGUAGE;
	}
	public String getDEFAULT_LANGUAGE_VAL() {
		return DEFAULT_LANGUAGE_VAL;
	}
	public void setDEFAULT_LANGUAGE_VAL(String dEFAULTLANGUAGEVAL) {
		DEFAULT_LANGUAGE_VAL = dEFAULTLANGUAGEVAL;
	}
	public String getMDM_USER_NO() {
		return MDM_USER_NO;
	}
	public void setMDM_USER_NO(String mDMUSERNO) {
		MDM_USER_NO = mDMUSERNO;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}
	public String getORG_ID_VAL() {
		return ORG_ID_VAL;
	}
	public void setORG_ID_VAL(String oRGIDVAL) {
		ORG_ID_VAL = oRGIDVAL;
	}
	public String getVALID_DATE() {
		return VALID_DATE;
	}
	public void setVALID_DATE(String vALIDDATE) {
		VALID_DATE = vALIDDATE;
	}
	public String getINVALID_DATE() {
		return INVALID_DATE;
	}
	public void setINVALID_DATE(String iNVALIDDATE) {
		INVALID_DATE = iNVALIDDATE;
	}
	
	
}
