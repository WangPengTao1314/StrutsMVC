package com.hoperun.drp.report.querypaymentrep.model;

/**
 * *@module
 * *@func 应收余额查询
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2014-05-14 14:13:12
 */

public class TestPojo {
    
	private  String   CustNo;
	
	private  String   CustName;
	
	private  Float    PayableAmount;

	private  String    AcctAmount;
	
	private String    Status;	
	
	private String    Desc;

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

	public String getCustNo() {
		return CustNo;
	}

	public void setCustNo(String custNo) {
		this.CustNo = custNo;
	}

	public String getCustName() {
		return CustName;
	}

	public void setCustName(String custName) {
		this.CustName = custName;
	}

	public Float getPayableAmount() {
		return PayableAmount;
	}

	public void setPayableAmount(Float payableAmount) {
		this.PayableAmount = payableAmount;
	}

	public String getAcctAmount() {
		return AcctAmount;
	}

	public void setAcctAmount(String acctAmount) {
		this.AcctAmount = acctAmount;
	}
	
}
