package com.hoperun.drp.oamg.saledateup.model;

import com.hoperun.commons.model.BaseModel;

public class SaledateUpModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**销售数据上报ID**/
	private String SALE_DATE_UP_ID;
	/**销售数据上报编号**/
	private String SALE_DATE_UP_NO;
	/**战区ID**/
	private String WAREA_ID;
	/**战区编号**/
	private String WAREA_NO;
	/**战区名称**/
	private String WAREA_NAME;
	/**区域ID**/
	private String AREA_ID;
	/**区域编号**/
	private String AREA_NO;
	/**区域名称**/
	private String AREA_NAME;
	/**终端ID **/
	private String TERM_ID;
	/**终端NO **/
	private String TERM_NO;
	/**终端名称 **/
	private String TERM_NAME;
	/**渠道ID**/
	private String CHANN_ID;
	/**渠道编号**/
	private String CHANN_NO;
	/**渠道名称**/
	private String CHANN_NAME;
	/**年**/
	private String YEAR;
	/**月**/
	private String MONTH;
	/**总数量**/
	private String TOTAL_NUM;
	/**总金额**/
	private String TOTAL_AMOUNT;
	/**状态**/
	private String      STATE;
	/**备注**/
	private String      REMARK;
	/**制单人ID**/
	private String      CREATOR;
	/**制单人名称**/
	private String      CRE_NAME;
	/**更新人ID**/
	private String      UPDATOR;
	/**更新人名称**/
	private String      UPD_NAME;
	/**更新时间**/
	private String      UPD_TIME;
	/**审核人ID**/
	private String      AUDIT_ID;
	/**审核人姓名**/
	private String      AUDIT_NAME;
	/**审核时间**/
	private String      AUDIT_TIME;
	/**制单部门ID**/
	private String      DEPT_ID;
	/**制单部门名称**/
	private String      DEPT_NAME;
	/**制单机构ID**/
	private String      ORG_ID;
	/**制单机构名称**/
	private String      ORG_NAME;
	/**帐套ID**/
	private String      LEDGER_ID;
	/**帐套名称**/
	private String      LEDGER_NAME;
	/**删除标记**/
	private String     DEL_FLAG;
	
	
	public String getSALE_DATE_UP_ID() {
		return SALE_DATE_UP_ID;
	}
	public void setSALE_DATE_UP_ID(String sALEDATEUPID) {
		SALE_DATE_UP_ID = sALEDATEUPID;
	}
	public String getSALE_DATE_UP_NO() {
		return SALE_DATE_UP_NO;
	}
	public void setSALE_DATE_UP_NO(String sALEDATEUPNO) {
		SALE_DATE_UP_NO = sALEDATEUPNO;
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
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	public String getTOTAL_NUM() {
		return TOTAL_NUM;
	}
	public void setTOTAL_NUM(String tOTALNUM) {
		TOTAL_NUM = tOTALNUM;
	}
	public String getTOTAL_AMOUNT() {
		return TOTAL_AMOUNT;
	}
	public void setTOTAL_AMOUNT(String tOTALAMOUNT) {
		TOTAL_AMOUNT = tOTALAMOUNT;
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
	
	
	
	
	
	

}
