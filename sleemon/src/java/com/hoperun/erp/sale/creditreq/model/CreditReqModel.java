package com.hoperun.erp.sale.creditreq.model;

import com.hoperun.commons.model.BaseModel;

/**
 * 信用额度变更申请
 * @author zhang_zhongbin
 *
 */
public class CreditReqModel extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3850628596647210830L;

	//信用额度变更申请ID
	private String CREDIT_REQ_ID;
	
	//渠道ID
	private String CHANN_ID;
	//渠道编号
	private String CHANN_NO;
	//渠道名称
	private String CHANN_NAME;
	//区域编号
	private String AREA_NO;
	//区域名称
	private String AREA_NAME;
	//原信用额度
	private String OLD_CREDIT_TOTAL;
	//变更后信用额度
	private String NEW_CREDIT_TOTAL;
	//申请人ID
	private String REQ_PSON_ID;
	//申请人名称
	private String REQ_PSON_NAME;
	//状态
	private String STATE;
	//备注
	private String REMARK;
	//制单人ID
	private String CREATOR;
	//制单人名称
	private String CRE_NAME;
	//制单时间
	private String CRE_TIME;
	//更新人ID
	private String UPDATOR;
	//更新人名称
	private String UPD_NAME;
	//更新人时间
	private String UPD_TIME;
	//审核人ID
	private String AUDIT_ID;
	//审核人姓名
	private String AUDIT_NAME;
	//审核时间
	private String AUDIT_TIME;	
	//制单部门ID
	private String  DEPT_ID;
	//制单部门名称
	private String  DEPT_NAME;
	//制单机构ID
	private String  ORG_ID;
	//制单机构名称
	private String  ORG_NAME;
	//帐套ID
	private String  LEDGER_ID;
	//帐套名称
	private String  LEDGER_NAME;
	//删除标记
	private String  DEL_FLAG;
	
	
	
	public String getCREDIT_REQ_ID() {
		return CREDIT_REQ_ID;
	}
	public void setCREDIT_REQ_ID(String cREDITREQID) {
		CREDIT_REQ_ID = cREDITREQID;
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
	public String getOLD_CREDIT_TOTAL() {
		return OLD_CREDIT_TOTAL;
	}
	public void setOLD_CREDIT_TOTAL(String oLDCREDITTOTAL) {
		OLD_CREDIT_TOTAL = oLDCREDITTOTAL;
	}
	public String getNEW_CREDIT_TOTAL() {
		return NEW_CREDIT_TOTAL;
	}
	public void setNEW_CREDIT_TOTAL(String nEWCREDITTOTAL) {
		NEW_CREDIT_TOTAL = nEWCREDITTOTAL;
	}
	public String getREQ_PSON_ID() {
		return REQ_PSON_ID;
	}
	public void setREQ_PSON_ID(String rEQPSONID) {
		REQ_PSON_ID = rEQPSONID;
	}
	public String getREQ_PSON_NAME() {
		return REQ_PSON_NAME;
	}
	public void setREQ_PSON_NAME(String rEQPSONNAME) {
		REQ_PSON_NAME = rEQPSONNAME;
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
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
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
	
	
	
	
	
	
	
	
	
	
}
