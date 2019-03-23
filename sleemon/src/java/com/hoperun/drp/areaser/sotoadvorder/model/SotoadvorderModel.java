package com.hoperun.drp.areaser.sotoadvorder.model;

import com.hoperun.commons.model.BaseModel;

public class SotoadvorderModel extends BaseModel{
	/**
	 * 销售订单ID
	 */
	private String SALE_ORDER_ID;
	/**
	 * 销售订单编号
	 */
	private String SALE_ORDER_NO;
	/**
	 * 单据类型
	 */
	private String BILL_TYPE;
	/**
	 * 来源单据ID
	 */
	private String FROM_BILL_ID;
	/**
	 * 来源单据NO
	 */
	private String FROM_BILL_NO;
	/**
	 * 订货方ID
	 */
	private String ORDER_CHANN_ID;
	/**
	 * 订货方编号
	 */
	private String ORDER_CHANN_NO;
	/**
	 * 订货方名称
	 */
	private String ORDER_CHANN_NAME;
	/**
	 * 订货日期
	 */
	private String ORDER_DATE;
	/**
	 * 发货方ID
	 */
	private String SEND_CHANN_ID;
	/**
	 * 发货方编号
	 */
	private String SEND_CHANN_NO;
	/**
	 * 发货方名称
	 */
	private String SEND_CHANN_NAME;
	/**
	 * 是否使用返利
	 */
	private String IS_USE_REBATE;
	/**
	 * 收货方ID
	 */
	private String RECV_CHANN_ID;
	/**
	 * 收货方编号
	 */
	private String RECV_CHANN_NO;
	/**
	 * 收货方名称
	 */
	private String RECV_CHANN_NAME;
	/**
	 * 联系人
	 */
	private String PERSON_CON;
	/**
	 * 电话
	 */
	private String TEL;
	/**
	 * 收货地址
	 */
	private String RECV_ADDR;
	/**
	 * 发货点ID
	 */
	private String SHIP_POINT_ID;
	/**
	 * 发货点名称
	 */
	private String SHIP_POINT_NAME;
	/**
	 * 状态
	 */
	private String STATE;
	/**
	 * 备注
	 */
	private String REMARK;
	/**
	 * 区域ID
	 */
	private String AREA_ID;
	/**
	 * 总金额
	 */
	private String TOTL_AMOUNT;
	/**
	 * 区域编号
	 */
	private String AREA_NO;
	/**
	 * 区域名称
	 */
	private String AREA_NAME;
	/**
	 * 收货地址编号
	 */
	private String RECV_ADDR_NO;
	/**
	 * @return the sALE_ORDER_ID
	 */
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	/**
	 * @param sALEORDERID the sALE_ORDER_ID to set
	 */
	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
	}
	/**
	 * @return the sALE_ORDER_NO
	 */
	public String getSALE_ORDER_NO() {
		return SALE_ORDER_NO;
	}
	/**
	 * @param sALEORDERNO the sALE_ORDER_NO to set
	 */
	public void setSALE_ORDER_NO(String sALEORDERNO) {
		SALE_ORDER_NO = sALEORDERNO;
	}
	/**
	 * @return the bILL_TYPE
	 */
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	/**
	 * @param bILLTYPE the bILL_TYPE to set
	 */
	public void setBILL_TYPE(String bILLTYPE) {
		BILL_TYPE = bILLTYPE;
	}
	/**
	 * @return the fROM_BILL_ID
	 */
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}
	/**
	 * @param fROMBILLID the fROM_BILL_ID to set
	 */
	public void setFROM_BILL_ID(String fROMBILLID) {
		FROM_BILL_ID = fROMBILLID;
	}
	/**
	 * @return the fROM_BILL_NO
	 */
	public String getFROM_BILL_NO() {
		return FROM_BILL_NO;
	}
	/**
	 * @param fROMBILLNO the fROM_BILL_NO to set
	 */
	public void setFROM_BILL_NO(String fROMBILLNO) {
		FROM_BILL_NO = fROMBILLNO;
	}
	/**
	 * @return the oRDER_CHANN_ID
	 */
	public String getORDER_CHANN_ID() {
		return ORDER_CHANN_ID;
	}
	/**
	 * @param oRDERCHANNID the oRDER_CHANN_ID to set
	 */
	public void setORDER_CHANN_ID(String oRDERCHANNID) {
		ORDER_CHANN_ID = oRDERCHANNID;
	}
	/**
	 * @return the oRDER_CHANN_NO
	 */
	public String getORDER_CHANN_NO() {
		return ORDER_CHANN_NO;
	}
	/**
	 * @param oRDERCHANNNO the oRDER_CHANN_NO to set
	 */
	public void setORDER_CHANN_NO(String oRDERCHANNNO) {
		ORDER_CHANN_NO = oRDERCHANNNO;
	}
	/**
	 * @return the oRDER_CHANN_NAME
	 */
	public String getORDER_CHANN_NAME() {
		return ORDER_CHANN_NAME;
	}
	/**
	 * @param oRDERCHANNNAME the oRDER_CHANN_NAME to set
	 */
	public void setORDER_CHANN_NAME(String oRDERCHANNNAME) {
		ORDER_CHANN_NAME = oRDERCHANNNAME;
	}
	/**
	 * @return the oRDER_DATE
	 */
	public String getORDER_DATE() {
		return ORDER_DATE;
	}
	/**
	 * @param oRDERDATE the oRDER_DATE to set
	 */
	public void setORDER_DATE(String oRDERDATE) {
		ORDER_DATE = oRDERDATE;
	}
	/**
	 * @return the sEND_CHANN_ID
	 */
	public String getSEND_CHANN_ID() {
		return SEND_CHANN_ID;
	}
	/**
	 * @param sENDCHANNID the sEND_CHANN_ID to set
	 */
	public void setSEND_CHANN_ID(String sENDCHANNID) {
		SEND_CHANN_ID = sENDCHANNID;
	}
	/**
	 * @return the sEND_CHANN_NO
	 */
	public String getSEND_CHANN_NO() {
		return SEND_CHANN_NO;
	}
	/**
	 * @param sENDCHANNNO the sEND_CHANN_NO to set
	 */
	public void setSEND_CHANN_NO(String sENDCHANNNO) {
		SEND_CHANN_NO = sENDCHANNNO;
	}
	/**
	 * @return the sEND_CHANN_NAME
	 */
	public String getSEND_CHANN_NAME() {
		return SEND_CHANN_NAME;
	}
	/**
	 * @param sENDCHANNNAME the sEND_CHANN_NAME to set
	 */
	public void setSEND_CHANN_NAME(String sENDCHANNNAME) {
		SEND_CHANN_NAME = sENDCHANNNAME;
	}
	/**
	 * @return the iS_USE_REBATE
	 */
	public String getIS_USE_REBATE() {
		return IS_USE_REBATE;
	}
	/**
	 * @param iSUSEREBATE the iS_USE_REBATE to set
	 */
	public void setIS_USE_REBATE(String iSUSEREBATE) {
		IS_USE_REBATE = iSUSEREBATE;
	}
	/**
	 * @return the rECV_CHANN_ID
	 */
	public String getRECV_CHANN_ID() {
		return RECV_CHANN_ID;
	}
	/**
	 * @param rECVCHANNID the rECV_CHANN_ID to set
	 */
	public void setRECV_CHANN_ID(String rECVCHANNID) {
		RECV_CHANN_ID = rECVCHANNID;
	}
	/**
	 * @return the rECV_CHANN_NO
	 */
	public String getRECV_CHANN_NO() {
		return RECV_CHANN_NO;
	}
	/**
	 * @param rECVCHANNNO the rECV_CHANN_NO to set
	 */
	public void setRECV_CHANN_NO(String rECVCHANNNO) {
		RECV_CHANN_NO = rECVCHANNNO;
	}
	/**
	 * @return the rECV_CHANN_NAME
	 */
	public String getRECV_CHANN_NAME() {
		return RECV_CHANN_NAME;
	}
	/**
	 * @param rECVCHANNNAME the rECV_CHANN_NAME to set
	 */
	public void setRECV_CHANN_NAME(String rECVCHANNNAME) {
		RECV_CHANN_NAME = rECVCHANNNAME;
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
	 * @return the rECV_ADDR
	 */
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	/**
	 * @param rECVADDR the rECV_ADDR to set
	 */
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
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
	 * @return the rECV_ADDR_NO
	 */
	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}
	/**
	 * @param rECVADDRNO the rECV_ADDR_NO to set
	 */
	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}
	/**
	 * @return the tOTL_AMOUNT
	 */
	public String getTOTL_AMOUNT() {
		return TOTL_AMOUNT;
	}
	/**
	 * @param tOTLAMOUNT the tOTL_AMOUNT to set
	 */
	public void setTOTL_AMOUNT(String tOTLAMOUNT) {
		TOTL_AMOUNT = tOTLAMOUNT;
	}
	
	
}
