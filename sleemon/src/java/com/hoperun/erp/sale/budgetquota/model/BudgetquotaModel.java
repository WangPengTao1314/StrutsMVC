package com.hoperun.erp.sale.budgetquota.model;

public class BudgetquotaModel {
	
	/** 预算额度分配ID**/
	private String BUDGET_QUOTA_ID;
	/** 预算科目ID**/
	private String BUDGET_ITEM_ID;
	/** 预算科目编号**/
	private String BUDGET_ITEM_NO;
	/** 预算科目名称**/
	private String BUDGET_ITEM_NAME;
	/** 预算科目类型 **/
	private String BUDGET_ITEM_TYPE;
	/** 上级预算科目ID**/
	private String PAR_BUDGET_ITEM_ID;
	/** 上级预算科目编号**/
	private String PAR_BUDGET_ITEM_NO;
	/** 上级预算科目名称**/
	private String PAR_BUDGET_ITEM_NAME;
	/** 月份**/
	private String MONTH;
	/** 年份**/
	private String YEAR;
	/** 季度**/
	private String QUARTER;
	/**预算额度**/
	private String BUDGET_QUOTA;
	/** 冻预算结额度**/
	private String FREEZE_BUDGET_QUOTA;
	/**使用预算额度**/
	private String USE_BUDGET_QUOTA;
	/** 预算部门ID**/
	private String BUDGET_DEPT_ID;
	/**预算部门编号**/
	private String BUDGET_DEPT_NO;
	/** 预算部门名称 **/
	private String BUDGET_DEPT_NAME;
	/** 关联区域ID **/
	private String RELATE_AREA_ID;
	/**关联区域编号 **/
	private String RELATE_AREA_NO;
	/** 关联区域名称 **/
	private String RELATE_AREA_NAME;
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
	
	private String REAL_REIT_AMOUNT;
	
	private String BUDGET_AMOUNT;
	
	public String getBUDGET_AMOUNT() {
		return BUDGET_AMOUNT;
	}
	public void setBUDGET_AMOUNT(String budget_amount) {
		BUDGET_AMOUNT = budget_amount;
	}
	public String getBUDGET_QUOTA_ID() {
		return BUDGET_QUOTA_ID;
	}
	public void setBUDGET_QUOTA_ID(String bUDGETQUOTAID) {
		BUDGET_QUOTA_ID = bUDGETQUOTAID;
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
	public String getPAR_BUDGET_ITEM_ID() {
		return PAR_BUDGET_ITEM_ID;
	}
	public void setPAR_BUDGET_ITEM_ID(String pARBUDGETITEMID) {
		PAR_BUDGET_ITEM_ID = pARBUDGETITEMID;
	}
	public String getPAR_BUDGET_ITEM_NO() {
		return PAR_BUDGET_ITEM_NO;
	}
	public void setPAR_BUDGET_ITEM_NO(String pARBUDGETITEMNO) {
		PAR_BUDGET_ITEM_NO = pARBUDGETITEMNO;
	}
	public String getPAR_BUDGET_ITEM_NAME() {
		return PAR_BUDGET_ITEM_NAME;
	}
	public void setPAR_BUDGET_ITEM_NAME(String pARBUDGETITEMNAME) {
		PAR_BUDGET_ITEM_NAME = pARBUDGETITEMNAME;
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
	public String getBUDGET_QUOTA() {
		return BUDGET_QUOTA;
	}
	public void setBUDGET_QUOTA(String bUDGETQUOTA) {
		BUDGET_QUOTA = bUDGETQUOTA;
	}
	public String getFREEZE_BUDGET_QUOTA() {
		return FREEZE_BUDGET_QUOTA;
	}
	public void setFREEZE_BUDGET_QUOTA(String fREEZEBUDGETQUOTA) {
		FREEZE_BUDGET_QUOTA = fREEZEBUDGETQUOTA;
	}
	public String getUSE_BUDGET_QUOTA() {
		return USE_BUDGET_QUOTA;
	}
	public void setUSE_BUDGET_QUOTA(String uSEBUDGETQUOTA) {
		USE_BUDGET_QUOTA = uSEBUDGETQUOTA;
	}
	public String getBUDGET_DEPT_ID() {
		return BUDGET_DEPT_ID;
	}
	public void setBUDGET_DEPT_ID(String bUDGETDEPTID) {
		BUDGET_DEPT_ID = bUDGETDEPTID;
	}
	public String getBUDGET_DEPT_NO() {
		return BUDGET_DEPT_NO;
	}
	public void setBUDGET_DEPT_NO(String bUDGETDEPTNO) {
		BUDGET_DEPT_NO = bUDGETDEPTNO;
	}
	public String getBUDGET_DEPT_NAME() {
		return BUDGET_DEPT_NAME;
	}
	public void setBUDGET_DEPT_NAME(String bUDGETDEPTNAME) {
		BUDGET_DEPT_NAME = bUDGETDEPTNAME;
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
	public String getRELATE_AREA_ID() {
		return RELATE_AREA_ID;
	}
	public void setRELATE_AREA_ID(String rELATEAREAID) {
		RELATE_AREA_ID = rELATEAREAID;
	}
	public String getRELATE_AREA_NO() {
		return RELATE_AREA_NO;
	}
	public void setRELATE_AREA_NO(String rELATEAREANO) {
		RELATE_AREA_NO = rELATEAREANO;
	}
	public String getRELATE_AREA_NAME() {
		return RELATE_AREA_NAME;
	}
	public void setRELATE_AREA_NAME(String rELATEAREANAME) {
		RELATE_AREA_NAME = rELATEAREANAME;
	}
	public String getREAL_REIT_AMOUNT() {
		return REAL_REIT_AMOUNT;
	}
	public void setREAL_REIT_AMOUNT(String real_reit_amount) {
		REAL_REIT_AMOUNT = real_reit_amount;
	}
	
	
	 
	
	
	
	

}
