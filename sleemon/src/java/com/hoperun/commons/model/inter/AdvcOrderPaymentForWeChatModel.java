package com.hoperun.commons.model.inter;
/**
 * 
*    
* 项目名称：sleemon   
* 类名称：AdvcOrderPaymentForWeChatModel.java
* 类描述：   预订单付款明细
* 创建人：liu_yg   
* 创建时间：2016-1-7 下午03:15:35   
* @version
 */
public class AdvcOrderPaymentForWeChatModel {
	/**
	 * 付款方式
	 */
	private String PAY_TYPE;
	/**
	 * 付款信息备注
	 */
	private String PAY_INFO;
	/**
	 * 付款金额
	 */
	private String PAY_AMONT;
	/**
	 * 付款单据号
	 */
	private String PAY_BILL_NO;
	/**
	 * 微信销付款明细ID
	 */
	private String FROM_BILL_ID;
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}
	public void setPAY_TYPE(String pAYTYPE) {
		PAY_TYPE = pAYTYPE;
	}
	public String getPAY_INFO() {
		return PAY_INFO;
	}
	public void setPAY_INFO(String pAYINFO) {
		PAY_INFO = pAYINFO;
	}
	public String getPAY_AMONT() {
		return PAY_AMONT;
	}
	public void setPAY_AMONT(String pAYAMONT) {
		PAY_AMONT = pAYAMONT;
	}
	public String getPAY_BILL_NO() {
		return PAY_BILL_NO;
	}
	public void setPAY_BILL_NO(String pAYBILLNO) {
		PAY_BILL_NO = pAYBILLNO;
	}
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}
	public void setFROM_BILL_ID(String fROMBILLID) {
		FROM_BILL_ID = fROMBILLID;
	}
	
}
