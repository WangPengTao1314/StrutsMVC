package com.hoperun.drp.report.queryProStatus.model;

import java.io.Serializable;

public class ProStatus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 543126740738196140L;
		
	private String DeliverOrderNo; //发运单号
	private String SaleOrderNo; //销售订单编号
	private String CustNo; //客户编号
	private String CustName; //客户名称
	private String PrdNo; //商品编号
	private String PrdName; //商品名称
	private String PrdSpec; //规格型号	
	private Integer StoreInNum; //已入库数量
	private Integer StoreNum; //抵库数量
	
	private Integer PLAN_NUM; //排车数量	
	private Integer OrderNum; //订货数量
	private String DeliverOrderDetailID;//发运单明细id
	private String Status;	
	private String Desc;
	
	private String Ledger_Name;
	public String getLEDGER_NAME() {
		return Ledger_Name;
	}
	public void setLEDGER_NAME(String lEDGERNAME) {
		Ledger_Name = lEDGERNAME;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getDesc() {
		return Desc;
	}
	public void setDesc(String desc) {
		Desc = desc;
	}
	
	public String getDeliverOrderNo() {
		return DeliverOrderNo;
	}
	public void setDeliverOrderNo(String deliverOrderNo) {
		DeliverOrderNo = deliverOrderNo;
	}
	public String getSaleOrderNo() {
		return SaleOrderNo;
	}
	public void setSaleOrderNo(String saleOrderNo) {
		SaleOrderNo = saleOrderNo;
	}
	public String getCustNo() {
		return CustNo;
	}
	public void setCustNo(String custNo) {
		CustNo = custNo;
	}
	public String getCustName() {
		return CustName;
	}
	public void setCustName(String custName) {
		CustName = custName;
	}
	public String getPrdNo() {
		return PrdNo;
	}
	public void setPrdNo(String prdNo) {
		PrdNo = prdNo;
	}
	public String getPrdName() {
		return PrdName;
	}
	public void setPrdName(String prdName) {
		PrdName = prdName;
	}
	public String getPrdSpec() {
		return PrdSpec;
	}
	public void setPrdSpec(String prdSpec) {
		PrdSpec = prdSpec;
	}
	public Float getPLAN_NUM() {
		return (PLAN_NUM==null ? 0.0f : PLAN_NUM);
	}
	public void setPLAN_NUM(Integer pLANNUM) {
		PLAN_NUM = (pLANNUM==null ? 0 : pLANNUM);
	}
	public Float getStroeInNum() {
		return (StoreInNum == null ? 0.0f :  StoreInNum); 
	}
	public void setStroeInNum(Integer stroeInNum) {
		StoreInNum = (stroeInNum == null ? 0:  stroeInNum);
	}
	public Integer getStoreNum() {
		return (StoreNum == null ? 0 : StoreNum);
	}
	public void setStoreNum(Integer storeNum) {
		StoreNum = (storeNum == null ? 0: storeNum);
	}
	
	public Integer getOrderNum() {
		return (OrderNum == null ? 0 : OrderNum);
	}
	public void setOrderNum(Integer orderNum) {
		OrderNum = (orderNum == null ? 0: orderNum);
	}
	/**
	 * @return the dELIVER_ORDER_DTL_ID
	 */
	public String getDELIVER_ORDER_DTL_ID() {
		return DeliverOrderDetailID;
	}
	/**
	 * @param dELIVERORDERDTLID the dELIVER_ORDER_DTL_ID to set
	 */
	public void setDELIVER_ORDER_DTL_ID(String dELIVERORDERDTLID) {
		DeliverOrderDetailID = dELIVERORDERDTLID;
	}
	
}
