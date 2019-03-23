package com.hoperun.erp.sale.storeout.model;

import com.hoperun.commons.model.BaseModel;

public class ErpStoreoutModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**出库单ID**/
	private String STOREOUT_ID;
	/**出库单编号**/
	private String STOREOUT_NO;
	/**单据类型**/
	private String BILL_TYPE;
	/**发运单ID**/
	private String DELIVER_ORDER_ID;
	/**发运单编号**/
	private String DELIVER_ORDER_NO;
	/**销售订单ID**/
    private String SALE_ORDER_ID;
    /**销售订单编号**/
    private String SALE_ORDER_NO;
	/**发货方ID**/
	private String SEND_CHANN_ID;
	/**发货方编号**/
	private String SEND_CHANN_NO;
	/**发货名称**/
	private String SEND_CHANN_NAME;
	/** 收货方ID **/
	private String RECV_CHANN_ID;
	/**收货方编号**/
	private String RECV_CHANN_NO;
	/**收货方名称**/
	private String RECV_CHANN_NAME;
	/**要求到货日期**/
	private String ORDER_RECV_DATE;
	/**出库时间**/
	private String STOREOUT_TIME;
	/**车牌号**/
	private String TRUCK_NO;
	/**状态**/
	private String STATE;
	/**备注**/
	private String REMARK;
	/**删除标记**/
	private String DEL_FLAG;
	/** U9出库单号 **/
	private String U9_SM_NO;
	
	private String CREATOR;
	private String CRE_NAME;
	private String CRE_TIME;
	private String DEPT_ID;
	private String DEPT_NAME;
	private String ORG_ID;
	private String ORG_NAME;
	private String LEDGER_ID;
	private String LEDGER_NAME;
	/** 订货方ID **/
	private String ORDER_CHANN_ID;
	/** 订货方编号 **/
	private String ORDER_CHANN_NO;
	/** 订货方名称 **/
	private String ORDER_CHANN_NAME;
	/** 收货地址编号 **/
	private String RECV_ADDR_NO;
	/** 收货地址 **/
	private String RECV_ADDR;
	
	public String getSTOREOUT_ID() {
		return STOREOUT_ID;
	}
	public void setSTOREOUT_ID(String sTOREOUTID) {
		STOREOUT_ID = sTOREOUTID;
	}
	public String getSTOREOUT_NO() {
		return STOREOUT_NO;
	}
	public void setSTOREOUT_NO(String sTOREOUTNO) {
		STOREOUT_NO = sTOREOUTNO;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILLTYPE) {
		BILL_TYPE = bILLTYPE;
	}
	public String getDELIVER_ORDER_ID() {
		return DELIVER_ORDER_ID;
	}
	public void setDELIVER_ORDER_ID(String dELIVERORDERID) {
		DELIVER_ORDER_ID = dELIVERORDERID;
	}
	public String getDELIVER_ORDER_NO() {
		return DELIVER_ORDER_NO;
	}
	public void setDELIVER_ORDER_NO(String dELIVERORDERNO) {
		DELIVER_ORDER_NO = dELIVERORDERNO;
	}
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
	}
	public String getSALE_ORDER_NO() {
		return SALE_ORDER_NO;
	}
	public void setSALE_ORDER_NO(String sALEORDERNO) {
		SALE_ORDER_NO = sALEORDERNO;
	}
	public String getSEND_CHANN_ID() {
		return SEND_CHANN_ID;
	}
	public void setSEND_CHANN_ID(String sENDCHANNID) {
		SEND_CHANN_ID = sENDCHANNID;
	}
	public String getSEND_CHANN_NO() {
		return SEND_CHANN_NO;
	}
	public void setSEND_CHANN_NO(String sENDCHANNNO) {
		SEND_CHANN_NO = sENDCHANNNO;
	}
	public String getSEND_CHANN_NAME() {
		return SEND_CHANN_NAME;
	}
	public void setSEND_CHANN_NAME(String sENDCHANNNAME) {
		SEND_CHANN_NAME = sENDCHANNNAME;
	}
	public String getRECV_CHANN_ID() {
		return RECV_CHANN_ID;
	}
	public void setRECV_CHANN_ID(String rECVCHANNID) {
		RECV_CHANN_ID = rECVCHANNID;
	}
	public String getRECV_CHANN_NO() {
		return RECV_CHANN_NO;
	}
	public void setRECV_CHANN_NO(String rECVCHANNNO) {
		RECV_CHANN_NO = rECVCHANNNO;
	}
	public String getRECV_CHANN_NAME() {
		return RECV_CHANN_NAME;
	}
	public void setRECV_CHANN_NAME(String rECVCHANNNAME) {
		RECV_CHANN_NAME = rECVCHANNNAME;
	}
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
	public String getSTOREOUT_TIME() {
		return STOREOUT_TIME;
	}
	public void setSTOREOUT_TIME(String sTOREOUTTIME) {
		STOREOUT_TIME = sTOREOUTTIME;
	}
	public String getTRUCK_NO() {
		return TRUCK_NO;
	}
	public void setTRUCK_NO(String tRUCKNO) {
		TRUCK_NO = tRUCKNO;
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
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
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
	public String getU9_SM_NO() {
		return U9_SM_NO;
	}
	public void setU9_SM_NO(String u9SMNO) {
		U9_SM_NO = u9SMNO;
	}
	public String getORDER_CHANN_ID() {
		return ORDER_CHANN_ID;
	}
	public void setORDER_CHANN_ID(String oRDERCHANNID) {
		ORDER_CHANN_ID = oRDERCHANNID;
	}
	public String getORDER_CHANN_NO() {
		return ORDER_CHANN_NO;
	}
	public void setORDER_CHANN_NO(String oRDERCHANNNO) {
		ORDER_CHANN_NO = oRDERCHANNNO;
	}
	public String getORDER_CHANN_NAME() {
		return ORDER_CHANN_NAME;
	}
	public void setORDER_CHANN_NAME(String oRDERCHANNNAME) {
		ORDER_CHANN_NAME = oRDERCHANNNAME;
	}
	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}
	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}
	
	
 
}
