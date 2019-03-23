package com.hoperun.commons.model.inter;
/**
 * 
*    
* 项目名称：sleemon   
* 类名称：AdvcOrderDtlForWeChatModel.java
* 类描述：    微信预订单明细接口
* 创建人：liu_yg   
* 创建时间：2016-1-7 上午11:20:32   
* @version
 */
public class AdvcOrderDtlForWeChatModel {
	/**
	 * 货品编号
	 */
	private String PRD_NO;
	/**
	 * 单价
	 */
	private String PRICE;
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	/**
	 * 折后单价
	 */
	private String DECT_PRICE;
	/**
	 * 订货数量
	 */
	private String ORDER_NUM;
	/**
	 * 应收金额
	 */
	private String PAYABLE_AMOUNT;
	/**
	 * 备注
	 */
	private String REMARK;
	/**
	 * 特殊工艺标记
	 */
	private String SPCL_TECH_FLAG;
	/**
	 * 特殊工艺备注
	 */
	private String SPCL_TECH_REMARK;
	/**
	 * 交货日期
	 */
	private String ORDER_RECV_DATE;
	/**
	 * 微信销售订单明细ID
	 */
	private String FROM_BILL_ID;
	/**
	 * 货品类型
	 */
	private String PRD_TYPE;
	public String getPRD_NO() {
		return PRD_NO;
	}
	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getDECT_RATE() {
		return DECT_RATE;
	}
	public void setDECT_RATE(String dECTRATE) {
		DECT_RATE = dECTRATE;
	}
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}
	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}
	public String getORDER_NUM() {
		return ORDER_NUM;
	}
	public void setORDER_NUM(String oRDERNUM) {
		ORDER_NUM = oRDERNUM;
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
	public String getSPCL_TECH_FLAG() {
		return SPCL_TECH_FLAG;
	}
	public void setSPCL_TECH_FLAG(String sPCLTECHFLAG) {
		SPCL_TECH_FLAG = sPCLTECHFLAG;
	}
	public String getSPCL_TECH_REMARK() {
		return SPCL_TECH_REMARK;
	}
	public void setSPCL_TECH_REMARK(String sPCLTECHREMARK) {
		SPCL_TECH_REMARK = sPCLTECHREMARK;
	}
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}
	public void setFROM_BILL_ID(String fROMBILLID) {
		FROM_BILL_ID = fROMBILLID;
	}
	public String getPRD_TYPE() {
		return PRD_TYPE;
	}
	public void setPRD_TYPE(String pRDTYPE) {
		PRD_TYPE = pRDTYPE;
	}
	
}
