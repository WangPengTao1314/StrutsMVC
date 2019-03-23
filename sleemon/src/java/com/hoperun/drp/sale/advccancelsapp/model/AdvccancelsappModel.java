/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappModel
 */
package com.hoperun.drp.sale.advccancelsapp.model;

import com.hoperun.commons.model.BaseModel;

/**
 *  
 */
public class AdvccancelsappModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1030197827801852843L;

	/**预订单发货取消ID**/
	private String ADVC_SEND_CANCEL_ID;
	/**预订单发货取消NO**/
	private String ADVC_SEND_CANCEL_NO;
	/** 预订单发货申请ID **/
	private String ADVC_SEND_REQ_ID;
	/** 发货申请单编号 **/
	private String ADVC_SEND_REQ_NO;
	/** 出库库房ID **/
	private String STOREOUT_STORE_ID;
	/** 出库库房编号 **/
	private String STOREOUT_STORE_NO;
	/** 出库库房名称 **/
	private String STOREOUT_STORE_NAME;
	/** 终端信息ID **/
	private String TERM_ID;
	/** 终端编号 **/
	private String TERM_NO;
	/** 终端名称 **/
	private String TERM_NAME;
	/** 销售日期 **/
	private String SALE_DATE;
	/** 业务员ID **/
	private String SALE_PSON_ID;
	/** 业务员 **/
	private String SALE_PSON_NAME;
	/** 客户姓名 **/
	private String CUST_NAME;
	/** 电话 **/
	private String TEL;
	/** 备注 **/
	private String REMARK;
	/** 制单人 **/
	private String CRE_NAME;
	/** 制单人ID **/
	private String CREATOR;
	/** 制单时间 **/
	private String CRE_TIME;
	/** 更新人 **/
	private String UPD_NAME;
	/** 更新人ID **/
	private String UPDATOR;
	/** 更新时间 **/
	private String UPD_TIME;
	/** 制单部门ID **/
	private String DEPT_ID;
	/** 制单部门 **/
	private String DEPT_NAME;
	/** 制单机构ID **/
	private String ORG_ID;
	/** 制单机构 **/
	private String ORG_NAME;
	/** 帐套ID **/
	private String LEDGER_ID;
	/** 帐套名称 **/
	private String LEDGER_NAME;
	/** 状态 **/
	private String STATE;
	/** 删除标记 **/
	private String DEL_FLAG;
	
	
	public String getADVC_SEND_CANCEL_ID() {
		return ADVC_SEND_CANCEL_ID;
	}
	public void setADVC_SEND_CANCEL_ID(String aDVCSENDCANCELID) {
		ADVC_SEND_CANCEL_ID = aDVCSENDCANCELID;
	}
	public String getADVC_SEND_CANCEL_NO() {
		return ADVC_SEND_CANCEL_NO;
	}
	public void setADVC_SEND_CANCEL_NO(String aDVCSENDCANCELNO) {
		ADVC_SEND_CANCEL_NO = aDVCSENDCANCELNO;
	}
	public String getADVC_SEND_REQ_ID() {
		return ADVC_SEND_REQ_ID;
	}
	public void setADVC_SEND_REQ_ID(String aDVCSENDREQID) {
		ADVC_SEND_REQ_ID = aDVCSENDREQID;
	}
	public String getADVC_SEND_REQ_NO() {
		return ADVC_SEND_REQ_NO;
	}
	public void setADVC_SEND_REQ_NO(String aDVCSENDREQNO) {
		ADVC_SEND_REQ_NO = aDVCSENDREQNO;
	}
	public String getSTOREOUT_STORE_ID() {
		return STOREOUT_STORE_ID;
	}
	public void setSTOREOUT_STORE_ID(String sTOREOUTSTOREID) {
		STOREOUT_STORE_ID = sTOREOUTSTOREID;
	}
	public String getSTOREOUT_STORE_NO() {
		return STOREOUT_STORE_NO;
	}
	public void setSTOREOUT_STORE_NO(String sTOREOUTSTORENO) {
		STOREOUT_STORE_NO = sTOREOUTSTORENO;
	}
	public String getSTOREOUT_STORE_NAME() {
		return STOREOUT_STORE_NAME;
	}
	public void setSTOREOUT_STORE_NAME(String sTOREOUTSTORENAME) {
		STOREOUT_STORE_NAME = sTOREOUTSTORENAME;
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
	public String getSALE_DATE() {
		return SALE_DATE;
	}
	public void setSALE_DATE(String sALEDATE) {
		SALE_DATE = sALEDATE;
	}
	public String getSALE_PSON_ID() {
		return SALE_PSON_ID;
	}
	public void setSALE_PSON_ID(String sALEPSONID) {
		SALE_PSON_ID = sALEPSONID;
	}
	public String getSALE_PSON_NAME() {
		return SALE_PSON_NAME;
	}
	public void setSALE_PSON_NAME(String sALEPSONNAME) {
		SALE_PSON_NAME = sALEPSONNAME;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
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
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
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