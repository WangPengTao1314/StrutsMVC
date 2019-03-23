package com.hoperun.erp.sale.deliveryhd.model;

import com.hoperun.commons.model.BaseModel;

public class DeliveryhdGchldModel  extends BaseModel{
	private String DELIVER_FREEZE_DTL_ID;
	private String DELIVER_ORDER_ID;
	private String ORDER_CHANN_ID;
	private String ORDER_CHANN_NO;
	private String ORDER_CHANN_NAME;
	private String FREEZE_AMOUNT;
	/**
	 * @return the dELIVER_FREEZE_DTL_ID
	 */
	public String getDELIVER_FREEZE_DTL_ID() {
		return DELIVER_FREEZE_DTL_ID;
	}
	/**
	 * @param dELIVERFREEZEDTLID the dELIVER_FREEZE_DTL_ID to set
	 */
	public void setDELIVER_FREEZE_DTL_ID(String dELIVERFREEZEDTLID) {
		DELIVER_FREEZE_DTL_ID = dELIVERFREEZEDTLID;
	}
	/**
	 * @return the dELIVER_ORDER_ID
	 */
	public String getDELIVER_ORDER_ID() {
		return DELIVER_ORDER_ID;
	}
	/**
	 * @param dELIVERORDERID the dELIVER_ORDER_ID to set
	 */
	public void setDELIVER_ORDER_ID(String dELIVERORDERID) {
		DELIVER_ORDER_ID = dELIVERORDERID;
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
	 * @return the fREEZE_AMOUNT
	 */
	public String getFREEZE_AMOUNT() {
		return FREEZE_AMOUNT;
	}
	/**
	 * @param fREEZEAMOUNT the fREEZE_AMOUNT to set
	 */
	public void setFREEZE_AMOUNT(String fREEZEAMOUNT) {
		FREEZE_AMOUNT = fREEZEAMOUNT;
	}
	
}
