package com.hoperun.drp.report.querystock.model;

/**
 * *@module
 * *@func 库存查询
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2014-05-14 14:13:12
 */

public class StoreModel {
   
	private String PrdNo;            //商品编号
	
	private String PrdName;          //商品名称
	
	private String PrdSpec;          //规格型号
	
	private String StoreNo;          //库房编号	
	
	private String StoreName;        //库房名称
	
	private Float  StorePrice;       //库存单价
	
	private Float  StoreNum;         //库存数量
	
	private String StoreAmount;      //库存金额
	
	private String CustNo;           //客户编号
	
	private String CustName;         //客户名称
	
	private String DeliverAddr;      //送货详细地址
	
	private Float  SalePrice;        //销售价格
	
	private Float  SaleAmount;       //销售金额
	
	private String Remark;            //描述
	
	private String LedgerName;       //帐套名称
	
	private Float  PrvdPrice;        //供货价
	
	private Float  Unit_Volume;      //单位体积
	
	private Float  Total_Volume;     //总体积
	
	private String Status;	
	private String Desc;

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

	public String getStoreNo() {
		return StoreNo;
	}

	public void setStoreNo(String storeNo) {
		StoreNo = storeNo;
	}

	public String getStoreName() {
		return StoreName;
	}

	public void setStoreName(String storeName) {
		StoreName = storeName;
	}

	public Float getStorePrice() {
		return StorePrice;
	}

	public void setStorePrice(Float storePrice) {
		StorePrice = storePrice;
	}

	public Float getStoreNum() {
		return StoreNum;
	}

	public void setStoreNum(Float storeNum) {
		StoreNum = storeNum;
	}

	public String getStoreAmount() {
		return StoreAmount;
	}

	public void setStoreAmount(String storeAmount) {
		StoreAmount = storeAmount;
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

	public String getDeliverAddr() {
		return DeliverAddr;
	}

	public void setDeliverAddr(String deliverAddr) {
		DeliverAddr = deliverAddr;
	}

	public Float getSalePrice() {
		return SalePrice;
	}

	public void setSalePrice(Float salePrice) {
		SalePrice = salePrice;
	}

	public Float getSaleAmount() {
		return SaleAmount;
	}

	public void setSaleAmount(Float saleAmount) {
		SaleAmount = saleAmount;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public String getLedgerName() {
		return LedgerName;
	}

	public void setLedgerName(String ledgerName) {
		LedgerName = ledgerName;
	}

	public Float getPrvdPrice() {
		return PrvdPrice;
	}

	public void setPrvdPrice(Float prvdPrice) {
		PrvdPrice = prvdPrice;
	}

	public Float getUnit_Volume() {
		return Unit_Volume;
	}

	public void setUnit_Volume(Float unit_Volume) {
		Unit_Volume = unit_Volume;
	}

	public Float getTotal_Volume() {
		return Total_Volume;
	}

	public void setTotal_Volume(Float total_Volume) {
		Total_Volume = total_Volume;
	}

	 
}
