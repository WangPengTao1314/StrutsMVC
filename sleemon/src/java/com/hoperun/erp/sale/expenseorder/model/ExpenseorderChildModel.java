package com.hoperun.erp.sale.expenseorder.model;

public class ExpenseorderChildModel {
	
	/** 费用报销单明细ID**/
	private String EXPENSE_ORDER_DTL_ID;
	/** 费用报销单ID**/
	private String EXPENSE_ORDER_ID;
	/** 报销明细类型**/
	private String EXPENSE_TYPE;
	/** 报销事项**/
	private String EXPENSE_REMARK;
	/** 业务发生日期**/
	private String BUSS_DATE;
	/** 报销金额**/
	private String EXPENSE_AMOUNT;
	/** 其它说明**/
	private String OTHER_REMARK;
	/** 删除标记**/
	private String DEL_FLAG;
	/**附件 **/
	private String EXPENSE_ATT;
	
	public String getEXPENSE_ORDER_DTL_ID() {
		return EXPENSE_ORDER_DTL_ID;
	}
	public void setEXPENSE_ORDER_DTL_ID(String eXPENSEORDERDTLID) {
		EXPENSE_ORDER_DTL_ID = eXPENSEORDERDTLID;
	}
	public String getEXPENSE_ORDER_ID() {
		return EXPENSE_ORDER_ID;
	}
	public void setEXPENSE_ORDER_ID(String eXPENSEORDERID) {
		EXPENSE_ORDER_ID = eXPENSEORDERID;
	}
	public String getEXPENSE_TYPE() {
		return EXPENSE_TYPE;
	}
	public void setEXPENSE_TYPE(String eXPENSETYPE) {
		EXPENSE_TYPE = eXPENSETYPE;
	}
	public String getEXPENSE_REMARK() {
		return EXPENSE_REMARK;
	}
	public void setEXPENSE_REMARK(String eXPENSEREMARK) {
		EXPENSE_REMARK = eXPENSEREMARK;
	}
	public String getBUSS_DATE() {
		return BUSS_DATE;
	}
	public void setBUSS_DATE(String bUSSDATE) {
		BUSS_DATE = bUSSDATE;
	}
	public String getEXPENSE_AMOUNT() {
		return EXPENSE_AMOUNT;
	}
	public void setEXPENSE_AMOUNT(String eXPENSEAMOUNT) {
		EXPENSE_AMOUNT = eXPENSEAMOUNT;
	}
	public String getOTHER_REMARK() {
		return OTHER_REMARK;
	}
	public void setOTHER_REMARK(String oTHERREMARK) {
		OTHER_REMARK = oTHERREMARK;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getEXPENSE_ATT() {
		return EXPENSE_ATT;
	}
	public void setEXPENSE_ATT(String eXPENSEATT) {
		EXPENSE_ATT = eXPENSEATT;
	}
	
	
	

}
