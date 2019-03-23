package com.hoperun.commons.model.inter;

import java.util.List;

/**
 * 
*    
* 项目名称：sleemon   
* 类名称：AdvcOrderForWeChatModel.java
* 类描述：   微信预订单接口
* 创建人：liu_yg   
* 创建时间：2016-1-7 上午11:12:39   
* @version
 */
public class AdvcOrderForWeChatModel {
	/**
	 * 终端编号
	 */
	private String TERM_NO;
	/**
	 * 销售日期
	 */
	private String SALE_DATE;
	/**
	 * 业务员编号
	 */
	private String SALE_PSON_NO;
	/**
	 * 客户名称
	 */
	private String CUST_NAME;
	/**
	 * 电话
	 */
	private String TEL;
	/**
	 * 收货地址
	 */
	private String RECV_ADDR;
	/**
	 * 订金金额
	 */
	private String ADVC_AMOUNT;
	/**
	 * 应收总额
	 */
	private String PAYABLE_AMOUNT;
	/**
	 * 备注
	 */
	private String REMARK;
	/**
	 * 制单人编号
	 */
	private String CRE_NO;
	/**
	 * 制单时间
	 */
	private String CRE_TIME;
	/**
	 * 微信销售订单号
	 */
	private String FROM_BILL_NO;
	/**
	 * 总金额
	 */
	private String TOTAL_AMOUNT;
	
	/**
	 * 订单明细
	 */
	private List<AdvcOrderDtlForWeChatModel> dtlList;
	/**
	 * 订单付款明细
	 */
	private List<AdvcOrderPaymentForWeChatModel> paymentList;
	public List<AdvcOrderDtlForWeChatModel> getDtlList() {
		return dtlList;
	}
	public void setDtlList(List<AdvcOrderDtlForWeChatModel> dtlList) {
		this.dtlList = dtlList;
	}
	public List<AdvcOrderPaymentForWeChatModel> getPaymentList() {
		return paymentList;
	}
	public void setPaymentList(List<AdvcOrderPaymentForWeChatModel> paymentList) {
		this.paymentList = paymentList;
	}
	public String getTERM_NO() {
		return TERM_NO;
	}
	public void setTERM_NO(String tERMNO) {
		TERM_NO = tERMNO;
	}
	public String getSALE_DATE() {
		return SALE_DATE;
	}
	public void setSALE_DATE(String sALEDATE) {
		SALE_DATE = sALEDATE;
	}
	public String getSALE_PSON_NO() {
		return SALE_PSON_NO;
	}
	public void setSALE_PSON_NO(String sALEPSONNO) {
		SALE_PSON_NO = sALEPSONNO;
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
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}
	public String getADVC_AMOUNT() {
		return ADVC_AMOUNT;
	}
	public void setADVC_AMOUNT(String aDVCAMOUNT) {
		ADVC_AMOUNT = aDVCAMOUNT;
	}
	public String getPAYABLE_AMOUNT() {
		return PAYABLE_AMOUNT;
	}
	public void setPAYABLE_AMOUNT(String pAYABLEAMOUNT) {
		PAYABLE_AMOUNT = pAYABLEAMOUNT;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getCRE_NO() {
		return CRE_NO;
	}
	public void setCRE_NO(String cRENO) {
		CRE_NO = cRENO;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	public String getFROM_BILL_NO() {
		return FROM_BILL_NO;
	}
	public void setFROM_BILL_NO(String fROMBILLNO) {
		FROM_BILL_NO = fROMBILLNO;
	}
	public String getTOTAL_AMOUNT() {
		return TOTAL_AMOUNT;
	}
	public void setTOTAL_AMOUNT(String tOTALAMOUNT) {
		TOTAL_AMOUNT = tOTALAMOUNT;
	}
	
}
