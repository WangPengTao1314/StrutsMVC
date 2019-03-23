package com.hoperun.drp.distributer.distributerendreq.model;

import com.hoperun.commons.model.BaseModel;

/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */
public class DistributerEndReqModel extends BaseModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6496046403684914254L;

	/**
	 * 加盟商终止合作申请单ID
	 */
	private String CHANN_END_REQ_ID;
	
	/**
	 * 加盟商终止合作申请单编号
	 */
	private String CHANN_END_REQ_NO;
	
	/**
	 * 申请时间
	 */
	private String REQ_DATE;
	
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
	 * 渠道类型
	 */
	private String CHANN_TYPE;
	
	/**
	 * 加盟日期
	 */
	private String JOIN_DATE;
	
	/**
	 * 渠道联系人
	 */
	private String CHANN_PERSON_CON;
	
	/**
	 * 渠道联系人电话
	 */
	private String CHANN_MOBILE;	
	
	/**
	 * 联系人
	 */
	private String PERSON_CON;	
	
	/**
	 * 公司电话(座机)
	 */
	private String TEL;
	
	/**
	 * 联系人电话(手机)
	 */
	private String MOBILE;
	
	/**
	 * 战区ID
	 */
	private String WAREA_ID;
	
	/**
	 * 战区编号
	 */
	private String WAREA_NO;
	
	/**
	 * 战区名称
	 */
	private String WAREA_NAME;
	
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
	 * 终端信息
	 */
	private String TERM_NAMES;
	
	/**
	 * 保证金
	 */
	private String DEPOSIT;
	
	/**
	 * 保证金_财务
	 */
	private String DEPOSIT_CONFIRM;
	
	/**
	 * 账面余额
	 */
	private String LEFT_ACCT_AMOUNT;
	
	/**
	 * 账面余额_财务
	 */
	private String LEFT_ACCT_AMOUNT_CONFIRM;
	
	/**
	 * 退货金额
	 */
	private String RETURN_AMOUNT;
	
	/**
	 * 退货金额_财务
	 */
	private String RETURN_AMOUNT_CONFIRM;	
	
	/**
	 * 退货附件
	 */
	private String RETURN_ATT;
	
	/**
	 * 终止合作原因
	 */
	private String END_RESON;
	
	/**
	 * 传真
	 */
	private String TAX;

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


	public String getCHANN_END_REQ_ID() {
		return CHANN_END_REQ_ID;
	}


	public void setCHANN_END_REQ_ID(String cHANNENDREQID) {
		CHANN_END_REQ_ID = cHANNENDREQID;
	}


	public String getCHANN_END_REQ_NO() {
		return CHANN_END_REQ_NO;
	}


	public void setCHANN_END_REQ_NO(String cHANNENDREQNO) {
		CHANN_END_REQ_NO = cHANNENDREQNO;
	}


	public String getREQ_DATE() {
		return REQ_DATE;
	}


	public void setREQ_DATE(String rEQDATE) {
		REQ_DATE = rEQDATE;
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


	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}


	public void setCHANN_TYPE(String cHANNTYPE) {
		CHANN_TYPE = cHANNTYPE;
	}


	public String getJOIN_DATE() {
		return JOIN_DATE;
	}


	public void setJOIN_DATE(String jOINDATE) {
		JOIN_DATE = jOINDATE;
	}


	public String getCHANN_PERSON_CON() {
		return CHANN_PERSON_CON;
	}


	public void setCHANN_PERSON_CON(String cHANNPERSONCON) {
		CHANN_PERSON_CON = cHANNPERSONCON;
	}


	public String getCHANN_MOBILE() {
		return CHANN_MOBILE;
	}


	public void setCHANN_MOBILE(String cHANNMOBILE) {
		CHANN_MOBILE = cHANNMOBILE;
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


	public String getMOBILE() {
		return MOBILE;
	}


	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}


	public String getWAREA_ID() {
		return WAREA_ID;
	}


	public void setWAREA_ID(String wAREAID) {
		WAREA_ID = wAREAID;
	}


	public String getWAREA_NO() {
		return WAREA_NO;
	}


	public void setWAREA_NO(String wAREANO) {
		WAREA_NO = wAREANO;
	}


	public String getWAREA_NAME() {
		return WAREA_NAME;
	}


	public void setWAREA_NAME(String wAREANAME) {
		WAREA_NAME = wAREANAME;
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


	public String getTERM_NAMES() {
		return TERM_NAMES;
	}


	public void setTERM_NAMES(String tERMNAMES) {
		TERM_NAMES = tERMNAMES;
	}


	public String getDEPOSIT() {
		return DEPOSIT;
	}


	public void setDEPOSIT(String dEPOSIT) {
		DEPOSIT = dEPOSIT;
	}


	public String getDEPOSIT_CONFIRM() {
		return DEPOSIT_CONFIRM;
	}


	public void setDEPOSIT_CONFIRM(String dEPOSITCONFIRM) {
		DEPOSIT_CONFIRM = dEPOSITCONFIRM;
	}


	public String getLEFT_ACCT_AMOUNT() {
		return LEFT_ACCT_AMOUNT;
	}


	public void setLEFT_ACCT_AMOUNT(String lEFTACCTAMOUNT) {
		LEFT_ACCT_AMOUNT = lEFTACCTAMOUNT;
	}


	public String getLEFT_ACCT_AMOUNT_CONFIRM() {
		return LEFT_ACCT_AMOUNT_CONFIRM;
	}


	public void setLEFT_ACCT_AMOUNT_CONFIRM(String lEFTACCTAMOUNTCONFIRM) {
		LEFT_ACCT_AMOUNT_CONFIRM = lEFTACCTAMOUNTCONFIRM;
	}


	public String getRETURN_AMOUNT() {
		return RETURN_AMOUNT;
	}


	public void setRETURN_AMOUNT(String rETURNAMOUNT) {
		RETURN_AMOUNT = rETURNAMOUNT;
	}


	public String getRETURN_AMOUNT_CONFIRM() {
		return RETURN_AMOUNT_CONFIRM;
	}


	public void setRETURN_AMOUNT_CONFIRM(String rETURNAMOUNTCONFIRM) {
		RETURN_AMOUNT_CONFIRM = rETURNAMOUNTCONFIRM;
	}


	public String getRETURN_ATT() {
		return RETURN_ATT;
	}


	public void setRETURN_ATT(String rETURNATT) {
		RETURN_ATT = rETURNATT;
	}


	public String getEND_RESON() {
		return END_RESON;
	}


	public void setEND_RESON(String eNDRESON) {
		END_RESON = eNDRESON;
	}


	public String getTAX() {
		return TAX;
	}


	public void setTAX(String tAX) {
		TAX = tAX;
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
