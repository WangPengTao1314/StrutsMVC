/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqModel.java
 */
package com.hoperun.erp.sale.tempcreditreq.model;

import com.hoperun.commons.model.BaseModel;
/**
 * The Class ProductModel.
 * 
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
public class TempCreditReqModel extends BaseModel {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4943417535815136771L;
	/**
	 * 临时信用申请ID
	 */
	private String TEMP_CREDIT_REQ_ID;
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
	 * 区域编号
	 */
	private String AREA_NO;
	/**
	 * 区域名称
	 */
	private String AREA_NAME;
	/**
	 * 申请临时信用
	 */
	private String TEMP_CREDIT_REQ;
	/**
	 * 临时信用有效期
	 */
	private String TEMP_CREDIT_VALDT;
	/**
	 * 申请人ID
	 */
	private String REQ_PSON_ID;
	/**
	 * 申请人名称
	 */
	private String REQ_PSON_NAME;
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
	 * 审核人ID
	 */
	private String AUDIT_ID;
	/**
	 * 审核人姓名
	 */
	private String AUDIT_NAME;
	/**
	 * 审核时间
	 */
	private String AUDIT_TIME;
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
	 * @return the tEMP_CREDIT_REQ_ID
	 */
	public String getTEMP_CREDIT_REQ_ID() {
		return TEMP_CREDIT_REQ_ID;
	}
	/**
	 * @param tEMPCREDITREQID the tEMP_CREDIT_REQ_ID to set
	 */
	public void setTEMP_CREDIT_REQ_ID(String tEMPCREDITREQID) {
		TEMP_CREDIT_REQ_ID = tEMPCREDITREQID;
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
	 * @return the tEMP_CREDIT_REQ
	 */
	public String getTEMP_CREDIT_REQ() {
		return TEMP_CREDIT_REQ;
	}
	/**
	 * @param tEMPCREDITREQ the tEMP_CREDIT_REQ to set
	 */
	public void setTEMP_CREDIT_REQ(String tEMPCREDITREQ) {
		TEMP_CREDIT_REQ = tEMPCREDITREQ;
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
	 * @return the rEQ_PSON_ID
	 */
	public String getREQ_PSON_ID() {
		return REQ_PSON_ID;
	}
	/**
	 * @param rEQPSONID the rEQ_PSON_ID to set
	 */
	public void setREQ_PSON_ID(String rEQPSONID) {
		REQ_PSON_ID = rEQPSONID;
	}
	/**
	 * @return the rEQ_PSON_NAME
	 */
	public String getREQ_PSON_NAME() {
		return REQ_PSON_NAME;
	}
	/**
	 * @param rEQPSONNAME the rEQ_PSON_NAME to set
	 */
	public void setREQ_PSON_NAME(String rEQPSONNAME) {
		REQ_PSON_NAME = rEQPSONNAME;
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
	 * @return the aUDIT_ID
	 */
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	/**
	 * @param aUDITID the aUDIT_ID to set
	 */
	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}
	/**
	 * @return the aUDIT_NAME
	 */
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	/**
	 * @param aUDITNAME the aUDIT_NAME to set
	 */
	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}
	/**
	 * @return the aUDIT_TIME
	 */
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	/**
	 * @param aUDITTIME the aUDIT_TIME to set
	 */
	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
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
	 * @return the dEL_FLAG
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
