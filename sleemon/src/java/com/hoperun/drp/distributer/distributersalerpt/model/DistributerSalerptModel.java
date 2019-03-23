package com.hoperun.drp.distributer.distributersalerpt.model;

import java.util.List;

import com.hoperun.commons.model.BaseModel;

/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 */
public class DistributerSalerptModel extends BaseModel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1823250430507403934L;

	/**
	 * 分销商数据上报ID
	 */
	private String DISTRIBUTOR_SALE_RPT_ID;
	
	/**
	 * 分销商数据上报编号
	 */
	private String DISTRIBUTOR_SALE_RPT_NO;	
	
	/**
	 * 渠道ID
	 */
	private String CHANN_ID;

	/**
	 * 渠道编号
	 */
	private String CHANN_NO;

	/**
	 * 渠道名称
	 */
	private String CHANN_NAME;
	
	/**
	 * 月份
	 */
	private String MONTH;
	
	/**
	 * 年份
	 */
	private String YEAR;

	/**
	 * 状态
	 */
	private String STATE;

	/**
	 * 制单人ID
	 */
	private String CREATOR;

	/**
	 * 制单人名称
	 */
	private String CRE_NAME;

	/**
	 * 制单时间
	 */
	private String CRE_TIME;

	/**
	 * 更新人ID
	 */
	private String UPDATOR;

	/**
	 * 更新人名称
	 */
	private String UPD_NAME;

	/**
	 * 更新时间
	 */
	private String UPD_TIME;

	/**
	 * 制单部门ID
	 */
	private String DEPT_ID;

	/**
	 * 制单部门名称
	 */
	private String DEPT_NAME;

	/**
	 * 制单机构ID
	 */
	private String ORG_ID;

	/**
	 * 制单机构名称
	 */
	private String ORG_NAME;

	/**
	 * 帐套ID
	 */
	private String LEDGER_ID;

	/**
	 * 帐套名称
	 */
	private String LEDGER_NAME;
	
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	

	/**
	 * 备注
	 */
	private String REMARK;
	
	private List<DistributerSalerptChildModel> childList;

	public List<DistributerSalerptChildModel> getChildList() {
		return childList;
	}


	public void setChildList(List<DistributerSalerptChildModel> childList) {
		this.childList = childList;
	}


	public String getCHANN_ID() {
		return CHANN_ID;
	}


	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}


	public String getCHANN_NO() {
		return CHANN_NO;
	}


	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}


	public String getCHANN_NAME() {
		return CHANN_NAME;
	}


	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}

	public String getSTATE() {
		return STATE;
	}


	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}


	public String getCREATOR() {
		return CREATOR;
	}


	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}


	public String getCRE_NAME() {
		return CRE_NAME;
	}


	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}


	public String getCRE_TIME() {
		return CRE_TIME;
	}


	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}


	public String getUPDATOR() {
		return UPDATOR;
	}


	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}


	public String getUPD_NAME() {
		return UPD_NAME;
	}


	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}


	public String getUPD_TIME() {
		return UPD_TIME;
	}


	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}


	public String getDEPT_ID() {
		return DEPT_ID;
	}


	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}


	public String getDEPT_NAME() {
		return DEPT_NAME;
	}


	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}


	public String getORG_ID() {
		return ORG_ID;
	}


	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}


	public String getORG_NAME() {
		return ORG_NAME;
	}


	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
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


	public String getREMARK() {
		return REMARK;
	}


	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	public String getDISTRIBUTOR_SALE_RPT_ID() {
		return DISTRIBUTOR_SALE_RPT_ID;
	}


	public void setDISTRIBUTOR_SALE_RPT_ID(String dISTRIBUTORSALERPTID) {
		DISTRIBUTOR_SALE_RPT_ID = dISTRIBUTORSALERPTID;
	}


	public String getDISTRIBUTOR_SALE_RPT_NO() {
		return DISTRIBUTOR_SALE_RPT_NO;
	}


	public void setDISTRIBUTOR_SALE_RPT_NO(String dISTRIBUTORSALERPTNO) {
		DISTRIBUTOR_SALE_RPT_NO = dISTRIBUTORSALERPTNO;
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
	
}
