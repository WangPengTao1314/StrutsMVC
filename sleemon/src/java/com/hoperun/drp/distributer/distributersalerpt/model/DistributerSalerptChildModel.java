package com.hoperun.drp.distributer.distributersalerpt.model;

import com.hoperun.commons.model.BaseModel;

public class DistributerSalerptChildModel extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3346828869862496415L;

	/**
	 * 分销商数据上报明细ID
	 */
	private String DISTRIBUTOR_SALE_RPT_DTL_ID;
	
	/**
	 * 分销商数据上报ID
	 */
	private String DISTRIBUTOR_SALE_RPT_ID;
	
	/**
	 * 分销商ID
	 */
	private String DISTRIBUTOR_ID;


	/**
	 * 分销商编号
	 */
	private String DISTRIBUTOR_NO;

	/**
	 * 分销商名称
	 */
	private String DISTRIBUTOR_NAME;

	/**
	 * 分销商类型
	 */
	private String DISTRIBUTOR_TYPE;

	/**
	 * 货品ID
	 */
	private String PRD_ID;

	/**
	 * 货品编号
	 */
	private String PRD_NO;

	/**
	 * 货品名称
	 */
	private String PRD_NAME;

	/**
	 * 规格型号
	 */
	private String PRD_SPEC;

	/**
	 * 提货数量
	 */
	private String STOREOUT_NUM;

	/**
	 * 提货金额
	 */
	private String STOREOUT_AMOUNT;

	/**
	 * 删除标记
	 */
	private String DEL_FLAG;

	public String getDISTRIBUTOR_SALE_RPT_DTL_ID() {
		return DISTRIBUTOR_SALE_RPT_DTL_ID;
	}

	public void setDISTRIBUTOR_SALE_RPT_DTL_ID(String dISTRIBUTORSALERPTDTLID) {
		DISTRIBUTOR_SALE_RPT_DTL_ID = dISTRIBUTORSALERPTDTLID;
	}

	public String getDISTRIBUTOR_SALE_RPT_ID() {
		return DISTRIBUTOR_SALE_RPT_ID;
	}

	public void setDISTRIBUTOR_SALE_RPT_ID(String dISTRIBUTORSALERPTID) {
		DISTRIBUTOR_SALE_RPT_ID = dISTRIBUTORSALERPTID;
	}

	public String getDISTRIBUTOR_ID() {
		return DISTRIBUTOR_ID;
	}

	public void setDISTRIBUTOR_ID(String dISTRIBUTORID) {
		DISTRIBUTOR_ID = dISTRIBUTORID;
	}

	public String getDISTRIBUTOR_NO() {
		return DISTRIBUTOR_NO;
	}

	public void setDISTRIBUTOR_NO(String dISTRIBUTORNO) {
		DISTRIBUTOR_NO = dISTRIBUTORNO;
	}

	public String getDISTRIBUTOR_NAME() {
		return DISTRIBUTOR_NAME;
	}

	public void setDISTRIBUTOR_NAME(String dISTRIBUTORNAME) {
		DISTRIBUTOR_NAME = dISTRIBUTORNAME;
	}

	public String getDISTRIBUTOR_TYPE() {
		return DISTRIBUTOR_TYPE;
	}

	public void setDISTRIBUTOR_TYPE(String dISTRIBUTORTYPE) {
		DISTRIBUTOR_TYPE = dISTRIBUTORTYPE;
	}

	public String getPRD_ID() {
		return PRD_ID;
	}

	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}

	public String getPRD_NO() {
		return PRD_NO;
	}

	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}

	public String getPRD_NAME() {
		return PRD_NAME;
	}

	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}

	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}

	public String getSTOREOUT_NUM() {
		return STOREOUT_NUM;
	}

	public void setSTOREOUT_NUM(String sTOREOUTNUM) {
		STOREOUT_NUM = sTOREOUTNUM;
	}

	public String getSTOREOUT_AMOUNT() {
		return STOREOUT_AMOUNT;
	}

	public void setSTOREOUT_AMOUNT(String sTOREOUTAMOUNT) {
		STOREOUT_AMOUNT = sTOREOUTAMOUNT;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

}
