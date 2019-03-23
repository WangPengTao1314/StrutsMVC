package com.hoperun.drp.finance.profiiloss.model;

import com.hoperun.commons.model.BaseModel;

public class ProfiiLossModel  extends BaseModel{
	/**盈亏单ID */
	private String PROFIT_LOSS_ID;
	/**盈亏单编号 */
	private String PROFIT_LOSS_NO;
	/**盘点单ID */
	private String PRD_INV_ID;
	/** 盘点单编号*/
	private String PRD_INV_NO;
	/** 库房ID*/
	private String STORE_ID;
	/**库房编号 */
	private String STORE_NO;
	/**库房名称 */
	private String STORE_NAME;
	/**监盘人ID */
	private String INV_PSON_ID;
	/**监盘人编号 */
	private String INV_PSON_NO;
	/**监盘人名称 */
	private String INV_PSON_NAME;
	/**状态 */
	private String STATE;
	/**备注 */
	private String REMARK;
	public String getPROFIT_LOSS_ID() {
		return PROFIT_LOSS_ID;
	}
	public void setPROFIT_LOSS_ID(String pROFITLOSSID) {
		PROFIT_LOSS_ID = pROFITLOSSID;
	}
	public String getPROFIT_LOSS_NO() {
		return PROFIT_LOSS_NO;
	}
	public void setPROFIT_LOSS_NO(String pROFITLOSSNO) {
		PROFIT_LOSS_NO = pROFITLOSSNO;
	}
	public String getPRD_INV_ID() {
		return PRD_INV_ID;
	}
	public void setPRD_INV_ID(String pRDINVID) {
		PRD_INV_ID = pRDINVID;
	}
	public String getPRD_INV_NO() {
		return PRD_INV_NO;
	}
	public void setPRD_INV_NO(String pRDINVNO) {
		PRD_INV_NO = pRDINVNO;
	}
	public String getSTORE_ID() {
		return STORE_ID;
	}
	public void setSTORE_ID(String sTOREID) {
		STORE_ID = sTOREID;
	}
	public String getSTORE_NO() {
		return STORE_NO;
	}
	public void setSTORE_NO(String sTORENO) {
		STORE_NO = sTORENO;
	}
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	public void setSTORE_NAME(String sTORENAME) {
		STORE_NAME = sTORENAME;
	}
	public String getINV_PSON_ID() {
		return INV_PSON_ID;
	}
	public void setINV_PSON_ID(String iNVPSONID) {
		INV_PSON_ID = iNVPSONID;
	}
	public String getINV_PSON_NO() {
		return INV_PSON_NO;
	}
	public void setINV_PSON_NO(String iNVPSONNO) {
		INV_PSON_NO = iNVPSONNO;
	}
	public String getINV_PSON_NAME() {
		return INV_PSON_NAME;
	}
	public void setINV_PSON_NAME(String iNVPSONNAME) {
		INV_PSON_NAME = iNVPSONNAME;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
}
