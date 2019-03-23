package com.hoperun.drp.report.queryReutrnRep.model;

import java.io.Serializable;
import java.util.Date;


public class ReutrnRep implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2594214662958367244L;
	
	private String  Return_no; //	退货单号
	public String getReturn_no() {
		return Return_no;
	}
	public void setReturn_no(String returnNo) {
		Return_no = returnNo;
	}
	public String getCust_no() {
		return Cust_no;
	}
	public void setCust_no(String custNo) {
		Cust_no = custNo;
	}
	public String getCust_name() {
		return Cust_name;
	}
	public void setCust_name(String custName) {
		Cust_name = custName;
	}
	public String getPrd_sn() {
		return Prd_sn;
	}
	public void setPrd_sn(String prdSn) {
		Prd_sn = prdSn;
	}
	public String getPrd_no() {
		return Prd_no;
	}
	public void setPrd_no(String prdNo) {
		Prd_no = prdNo;
	}
	public String getPrd_name() {
		return Prd_name;
	}
	public void setPrd_name(String prdName) {
		Prd_name = prdName;
	}
	public String getPrd_spec() {
		return Prd_spec;
	}
	public void setPrd_spec(String prdSpec) {
		Prd_spec = prdSpec;
	}
	public Float getStore_in_num() {
		return Store_in_num;
	}
	public void setStore_in_num(Float storeInNum) {
		Store_in_num = storeInNum;
	}
	public Float getReturn_price() {
		return Return_price;
	}
	public void setReturn_price(Float returnPrice) {
		Return_price = returnPrice;
	}
	public Float getReturn_amount() {
		return Return_amount;
	}
	public void setReturn_amount(Float returnAmount) {
		Return_amount = returnAmount;
	}
	public String getStore_in_time() {
		return Store_in_time;
	}
	public void setStore_in_time(String storeInTime) {
		Store_in_time = storeInTime;
	}
	public String getStore_in_username() {
		return Store_in_username;
	}
	public void setStore_in_username(String storeInUsername) {
		Store_in_username = storeInUsername;
	}
	public String getFnc_audit_time() {
		return Fnc_audit_time;
	}
	public void setFnc_audit_time(String fncAuditTime) {
		Fnc_audit_time = fncAuditTime;
	}
	public String getFnc_audit_username() {
		return Fnc_audit_username;
	}
	public void setFnc_audit_username(String fncAuditUsername) {
		Fnc_audit_username = fncAuditUsername;
	}
	private String  Cust_no; //	客户编号
	private String  Cust_name; //	客户名称		
	private String  Prd_sn; //	商品序列号		
	private String  Prd_no; //	商品编号		
	private String  Prd_name; //	商品名称		
	private String  Prd_spec; //	规格型号		
	private Float  Store_in_num; //	入库数量		
	private Float  Return_price; //	退货单价		
	private Float  Return_amount; //	退货金额		
	private String  Store_in_time; //	入库时间		时间	　
	private String  Store_in_username; //	入库处理人姓名		
	private String  Fnc_audit_time; //	财务审核时间		时间	　
	private String  Fnc_audit_username; //	财务审核人姓名
	
	private String Status;	
	private String Desc;
	private String Code;
	
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
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
	
			

}
