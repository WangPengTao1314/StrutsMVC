package com.hoperun.erp.sale.expenseorder.model;

public class ExpenseorderModel {
	/** 费用报销单ID**/
	private String EXPENSE_ORDER_ID;
	/** 费用报销单编号**/
	private String EXPENSE_ORDER_NO;
	/** 费用报销类型**/
	private String EXPENSE_TYPE;
	/** 预算科目ID**/
	private String BUDGET_ITEM_ID;
	/** 预算科目编号**/
	private String BUDGET_ITEM_NO;
	/** 预算科目名称**/
	private String BUDGET_ITEM_NAME;
	/** 预算科目类型 **/
	private String BUDGET_ITEM_TYPE;
	/** 月份**/
	private String MONTH;
	/** 年份**/
	private String YEAR;
	/** 季度**/
	private String QUARTER;
	/** 报销人ID**/
	private String EXPENSE_PSON_ID;
	/** 报销人姓名**/
	private String EXPENSE_PSON_NAME;
	/** 报销部门ID**/
	private String EXPENSE_DEPT_ID;
	/** 报销部门编号**/
	private String EXPENSE_DEPT_NO;
	/** 报销部门名称**/
	private String EXPENSE_DEPT_NAME;
	/** 报销金额**/
	private String EXPENSE_AMOUNT;
	/** 报销日期**/
	private String EXPENSE_DATE;
	/**关联单据编号**/
	private String RELATE_ORDER_NOS;
	/**申请金额**/
	private String RELATE_AMOUNT_REQ;
	/** 备注**/
	private String REMARK;
	/** 状态 **/
	private String STATE;
	/** 制单人名称 **/
	private String CRE_NAME;
	/** 制单人ID **/
	private String CREATOR;
	/** 制单时间 **/
	private String CRE_TIME;
	/** 更新人名称 **/
	private String UPD_NAME;
	/** 更新人ID **/
	private String UPDATOR;
	/** 更新时间 **/
	private String UPD_TIME;
	/** 帐套ID **/
	private String LEDGER_ID;
	/** 帐套名称 **/
	private String LEDGER_NAME;
	/** 删除标记 **/
	private String DEL_FLAG;
	/** 主表附件 **/
	private String EXPENSE_ATT;
	/**预算额度分配ID **/
	private String BUDGET_QUOTA_ID;
	
	private String CHANN_ID;
	
	private String CHANN_NO;
	
	private String CHANN_NAME;
	
	/**效果图**/
	private String EXPENSE_ATT_PIC;
	
	public String getEXPENSE_ATT_PIC() {
		return EXPENSE_ATT_PIC;
	}
	public void setEXPENSE_ATT_PIC(String expense_att_pic) {
		EXPENSE_ATT_PIC = expense_att_pic;
	}
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	public void setCHANN_ID(String chann_id) {
		CHANN_ID = chann_id;
	}
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	public void setCHANN_NO(String chann_no) {
		CHANN_NO = chann_no;
	}
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	public String getEXPENSE_ORDER_ID() {
		return EXPENSE_ORDER_ID;
	}
	public void setEXPENSE_ORDER_ID(String eXPENSEORDERID) {
		EXPENSE_ORDER_ID = eXPENSEORDERID;
	}
	public String getEXPENSE_ORDER_NO() {
		return EXPENSE_ORDER_NO;
	}
	public void setEXPENSE_ORDER_NO(String eXPENSEORDERNO) {
		EXPENSE_ORDER_NO = eXPENSEORDERNO;
	}
	public String getEXPENSE_TYPE() {
		return EXPENSE_TYPE;
	}
	public void setEXPENSE_TYPE(String eXPENSETYPE) {
		EXPENSE_TYPE = eXPENSETYPE;
	}
	public String getBUDGET_ITEM_ID() {
		return BUDGET_ITEM_ID;
	}
	public void setBUDGET_ITEM_ID(String bUDGETITEMID) {
		BUDGET_ITEM_ID = bUDGETITEMID;
	}
	public String getBUDGET_ITEM_NO() {
		return BUDGET_ITEM_NO;
	}
	public void setBUDGET_ITEM_NO(String bUDGETITEMNO) {
		BUDGET_ITEM_NO = bUDGETITEMNO;
	}
	public String getBUDGET_ITEM_NAME() {
		return BUDGET_ITEM_NAME;
	}
	public void setBUDGET_ITEM_NAME(String bUDGETITEMNAME) {
		BUDGET_ITEM_NAME = bUDGETITEMNAME;
	}
	public String getBUDGET_ITEM_TYPE() {
		return BUDGET_ITEM_TYPE;
	}
	public void setBUDGET_ITEM_TYPE(String bUDGETITEMTYPE) {
		BUDGET_ITEM_TYPE = bUDGETITEMTYPE;
	}
	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	public String getQUARTER() {
		return QUARTER;
	}
	public void setQUARTER(String qUARTER) {
		QUARTER = qUARTER;
	}
	public String getEXPENSE_PSON_ID() {
		return EXPENSE_PSON_ID;
	}
	public void setEXPENSE_PSON_ID(String eXPENSEPSONID) {
		EXPENSE_PSON_ID = eXPENSEPSONID;
	}
	public String getEXPENSE_PSON_NAME() {
		return EXPENSE_PSON_NAME;
	}
	public void setEXPENSE_PSON_NAME(String eXPENSEPSONNAME) {
		EXPENSE_PSON_NAME = eXPENSEPSONNAME;
	}
	public String getEXPENSE_DEPT_ID() {
		return EXPENSE_DEPT_ID;
	}
	public void setEXPENSE_DEPT_ID(String eXPENSEDEPTID) {
		EXPENSE_DEPT_ID = eXPENSEDEPTID;
	}
	public String getEXPENSE_DEPT_NO() {
		return EXPENSE_DEPT_NO;
	}
	public void setEXPENSE_DEPT_NO(String eXPENSEDEPTNO) {
		EXPENSE_DEPT_NO = eXPENSEDEPTNO;
	}
	public String getEXPENSE_DEPT_NAME() {
		return EXPENSE_DEPT_NAME;
	}
	public void setEXPENSE_DEPT_NAME(String eXPENSEDEPTNAME) {
		EXPENSE_DEPT_NAME = eXPENSEDEPTNAME;
	}
	public String getEXPENSE_AMOUNT() {
		return EXPENSE_AMOUNT;
	}
	public void setEXPENSE_AMOUNT(String eXPENSEAMOUNT) {
		EXPENSE_AMOUNT = eXPENSEAMOUNT;
	}
	public String getEXPENSE_DATE() {
		return EXPENSE_DATE;
	}
	public void setEXPENSE_DATE(String eXPENSEDATE) {
		EXPENSE_DATE = eXPENSEDATE;
	}
	public String getRELATE_ORDER_NOS() {
		return RELATE_ORDER_NOS;
	}
	public void setRELATE_ORDER_NOS(String rELATEORDERNOS) {
		RELATE_ORDER_NOS = rELATEORDERNOS;
	}
	public String getRELATE_AMOUNT_REQ() {
		return RELATE_AMOUNT_REQ;
	}
	public void setRELATE_AMOUNT_REQ(String rELATEAMOUNTREQ) {
		RELATE_AMOUNT_REQ = rELATEAMOUNTREQ;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
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
	public String getBUDGET_QUOTA_ID() {
		return BUDGET_QUOTA_ID;
	}
	public void setBUDGET_QUOTA_ID(String bUDGETQUOTAID) {
		BUDGET_QUOTA_ID = bUDGETQUOTAID;
	}
	
	
	

}
