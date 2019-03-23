package com.hoperun.erp.sale.erpadvcorder.model;
/**
 * 付款明细
 * @author zhang_zhongbin
 *
 */
public class ErpPaymentDtlModel {
	
	/**付款明细ID **/
	private String PAYMENT_DTL_ID;
	/** 预订单ID**/
	private String ADVC_ORDER_ID;
	/** 付款方式**/
	private String PAY_TYPE;
	/** 付款单据号**/
	private String PAY_BILL_NO;
	/**付款金额 **/
	private String PAY_AMONT;
	/**付款信息 **/
	private String PAY_INFO;
	/** **/
	private String DEL_FLAG;
	
	public String getPAYMENT_DTL_ID() {
		return PAYMENT_DTL_ID;
	}
	public void setPAYMENT_DTL_ID(String pAYMENTDTLID) {
		PAYMENT_DTL_ID = pAYMENTDTLID;
	}
	public String getADVC_ORDER_ID() {
		return ADVC_ORDER_ID;
	}
	public void setADVC_ORDER_ID(String aDVCORDERID) {
		ADVC_ORDER_ID = aDVCORDERID;
	}
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}
	public void setPAY_TYPE(String pAYTYPE) {
		PAY_TYPE = pAYTYPE;
	}
	public String getPAY_BILL_NO() {
		return PAY_BILL_NO;
	}
	public void setPAY_BILL_NO(String pAYBILLNO) {
		PAY_BILL_NO = pAYBILLNO;
	}
	public String getPAY_AMONT() {
		return PAY_AMONT;
	}
	public void setPAY_AMONT(String pAYAMONT) {
		PAY_AMONT = pAYAMONT;
	}
	public String getPAY_INFO() {
		return PAY_INFO;
	}
	public void setPAY_INFO(String pAYINFO) {
		PAY_INFO = pAYINFO;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	

}
