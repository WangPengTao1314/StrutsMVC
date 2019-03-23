package com.hoperun.drp.report.querypaymentrep.model;

public class PayMentDtl {

	// 序列号
	private String Id;
	// 查询期间
	private String QueryPeriod;
	// 付款客户编号
	private String PayCustCode;
	// 付款客户
	private String PayCustName;
	// 审核日期
	private String ApprovedOn;
	// 凭证号
	private String VoucherDisplayCode;
	// 凭证类型
	private String VoucherCatName;
	// 立账日期
	private String AccrueDate;
	// 记账期间
	private String AccountPeriod;
	// 立账客户
	private String AccrueCustName;
	// 摘要
	private String Memo;
	// 单据类型
	private String CatName;
	// 单号
	private String DocNo;
	// 组织
	private String OrgName;
	// 本期应收
	private String Qty;
	// 本期收回
	private String CustSiteCode;
	//余额
	private String Balance;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getQueryPeriod() {
		return QueryPeriod;
	}
	public void setQueryPeriod(String queryPeriod) {
		QueryPeriod = queryPeriod;
	}
	public String getPayCustCode() {
		return PayCustCode;
	}
	public void setPayCustCode(String payCustCode) {
		PayCustCode = payCustCode;
	}
	public String getPayCustName() {
		return PayCustName;
	}
	public void setPayCustName(String payCustName) {
		PayCustName = payCustName;
	}
	public String getApprovedOn() {
		return ApprovedOn;
	}
	public void setApprovedOn(String approvedOn) {
		ApprovedOn = approvedOn;
	}
	public String getVoucherDisplayCode() {
		return VoucherDisplayCode;
	}
	public void setVoucherDisplayCode(String voucherDisplayCode) {
		VoucherDisplayCode = voucherDisplayCode;
	}
	public String getVoucherCatName() {
		return VoucherCatName;
	}
	public void setVoucherCatName(String voucherCatName) {
		VoucherCatName = voucherCatName;
	}
	public String getAccrueDate() {
		return AccrueDate;
	}
	public void setAccrueDate(String accrueDate) {
		AccrueDate = accrueDate;
	}
	public String getAccountPeriod() {
		return AccountPeriod;
	}
	public void setAccountPeriod(String accountPeriod) {
		AccountPeriod = accountPeriod;
	}
	public String getAccrueCustName() {
		return AccrueCustName;
	}
	public void setAccrueCustName(String accrueCustName) {
		AccrueCustName = accrueCustName;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	public String getCatName() {
		return CatName;
	}
	public void setCatName(String catName) {
		CatName = catName;
	}
	public String getDocNo() {
		return DocNo;
	}
	public void setDocNo(String docNo) {
		DocNo = docNo;
	}
	public String getOrgName() {
		return OrgName;
	}
	public void setOrgName(String orgName) {
		OrgName = orgName;
	}
	public String getQty() {
		return Qty;
	}
	public void setQty(String qty) {
		Qty = qty;
	}
	public String getCustSiteCode() {
		return CustSiteCode;
	}
	public void setCustSiteCode(String custSiteCode) {
		CustSiteCode = custSiteCode;
	}
	public String getBalance() {
		return Balance;
	}
	public void setBalance(String balance) {
		Balance = balance;
	}
	
	

}
