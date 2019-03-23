package com.hoperun.drp.areaser.storeoutnotice.model;

import com.hoperun.commons.model.BaseModel;

public class StoreoutnoticeModel extends BaseModel{
	/**
	 * 销售出库通知单ID
	 */
	private String STOREOUT_NOTICE_ID;
	/**
	 * 销售出库通知单编号
	 */
	private String STOREOUT_NOTICE_NO;
	/**
	 * 来源单据ID
	 */
	private String FROM_BILL_ID;
	/**
	 * 来源单据NO
	 */
	private String FROM_BILL_NO;
	/**
	 * 发货方ID
	 */
	private String SEND_CHANN_ID;
	/**
	 * 发货方编号
	 */
	private String SEND_CHANN_NO;
	/**
	 * 发货方名称
	 */
	private String SEND_CHANN_NAME;
	/**
	 * 出库库房ID
	 */
	private String STOREOUT_STORE_ID;
	/**
	 * 出库库房编号
	 */
	private String STOREOUT_STORE_NO;
	/**
	 * 出库库房名称
	 */
	private String STOREOUT_STORE_NAME;
	/**
	 * 收货方ID
	 */
	private String RECV_CHANN_ID;
	/**
	 * 收货方编号
	 */
	private String RECV_CHANN_NO;
	/**
	 * 收货方名称
	 */
	private String RECV_CHANN_NAME;
	/**
	 * 销售日期
	 */
	private String SALE_DATE;
	/**
	 * 收货地址编号
	 */
	private String RECV_ADDR_NO;
	/**
	 * 收货地址
	 */
	private String RECV_ADDR;
	/**
	 * 出库总金额
	 */
	private String STOREOUT_AMOUNT;
	/**
	 * 状态
	 */
	private String STATE;
	/**
	 * 备注
	 */
	private String REMARK;
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
	 * 附加费
	 */
	private String OTHER_COST;
	/**
	 * @return the sTOREOUT_NOTICE_ID
	 */
	public String getSTOREOUT_NOTICE_ID() {
		return STOREOUT_NOTICE_ID;
	}
	/**
	 * @param sTOREOUTNOTICEID the sTOREOUT_NOTICE_ID to set
	 */
	public void setSTOREOUT_NOTICE_ID(String sTOREOUTNOTICEID) {
		STOREOUT_NOTICE_ID = sTOREOUTNOTICEID;
	}
	/**
	 * @return the sTOREOUT_NOTICE_NO
	 */
	public String getSTOREOUT_NOTICE_NO() {
		return STOREOUT_NOTICE_NO;
	}
	/**
	 * @param sTOREOUTNOTICENO the sTOREOUT_NOTICE_NO to set
	 */
	public void setSTOREOUT_NOTICE_NO(String sTOREOUTNOTICENO) {
		STOREOUT_NOTICE_NO = sTOREOUTNOTICENO;
	}
	/**
	 * @return the fROM_BILL_ID
	 */
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}
	/**
	 * @param fROMBILLID the fROM_BILL_ID to set
	 */
	public void setFROM_BILL_ID(String fROMBILLID) {
		FROM_BILL_ID = fROMBILLID;
	}
	/**
	 * @return the fROM_BILL_NO
	 */
	public String getFROM_BILL_NO() {
		return FROM_BILL_NO;
	}
	/**
	 * @param fROMBILLNO the fROM_BILL_NO to set
	 */
	public void setFROM_BILL_NO(String fROMBILLNO) {
		FROM_BILL_NO = fROMBILLNO;
	}
	/**
	 * @return the sEND_CHANN_ID
	 */
	public String getSEND_CHANN_ID() {
		return SEND_CHANN_ID;
	}
	/**
	 * @param sENDCHANNID the sEND_CHANN_ID to set
	 */
	public void setSEND_CHANN_ID(String sENDCHANNID) {
		SEND_CHANN_ID = sENDCHANNID;
	}
	/**
	 * @return the sEND_CHANN_NO
	 */
	public String getSEND_CHANN_NO() {
		return SEND_CHANN_NO;
	}
	/**
	 * @param sENDCHANNNO the sEND_CHANN_NO to set
	 */
	public void setSEND_CHANN_NO(String sENDCHANNNO) {
		SEND_CHANN_NO = sENDCHANNNO;
	}
	/**
	 * @return the sEND_CHANN_NAME
	 */
	public String getSEND_CHANN_NAME() {
		return SEND_CHANN_NAME;
	}
	/**
	 * @param sENDCHANNNAME the sEND_CHANN_NAME to set
	 */
	public void setSEND_CHANN_NAME(String sENDCHANNNAME) {
		SEND_CHANN_NAME = sENDCHANNNAME;
	}
	/**
	 * @return the sTOREOUT_STORE_ID
	 */
	public String getSTOREOUT_STORE_ID() {
		return STOREOUT_STORE_ID;
	}
	/**
	 * @param sTOREOUTSTOREID the sTOREOUT_STORE_ID to set
	 */
	public void setSTOREOUT_STORE_ID(String sTOREOUTSTOREID) {
		STOREOUT_STORE_ID = sTOREOUTSTOREID;
	}
	/**
	 * @return the sTOREOUT_STORE_NO
	 */
	public String getSTOREOUT_STORE_NO() {
		return STOREOUT_STORE_NO;
	}
	/**
	 * @param sTOREOUTSTORENO the sTOREOUT_STORE_NO to set
	 */
	public void setSTOREOUT_STORE_NO(String sTOREOUTSTORENO) {
		STOREOUT_STORE_NO = sTOREOUTSTORENO;
	}
	/**
	 * @return the sTOREOUT_STORE_NAME
	 */
	public String getSTOREOUT_STORE_NAME() {
		return STOREOUT_STORE_NAME;
	}
	/**
	 * @param sTOREOUTSTORENAME the sTOREOUT_STORE_NAME to set
	 */
	public void setSTOREOUT_STORE_NAME(String sTOREOUTSTORENAME) {
		STOREOUT_STORE_NAME = sTOREOUTSTORENAME;
	}
	/**
	 * @return the rECV_CHANN_ID
	 */
	public String getRECV_CHANN_ID() {
		return RECV_CHANN_ID;
	}
	/**
	 * @param rECVCHANNID the rECV_CHANN_ID to set
	 */
	public void setRECV_CHANN_ID(String rECVCHANNID) {
		RECV_CHANN_ID = rECVCHANNID;
	}
	/**
	 * @return the rECV_CHANN_NO
	 */
	public String getRECV_CHANN_NO() {
		return RECV_CHANN_NO;
	}
	/**
	 * @param rECVCHANNNO the rECV_CHANN_NO to set
	 */
	public void setRECV_CHANN_NO(String rECVCHANNNO) {
		RECV_CHANN_NO = rECVCHANNNO;
	}
	/**
	 * @return the rECV_CHANN_NAME
	 */
	public String getRECV_CHANN_NAME() {
		return RECV_CHANN_NAME;
	}
	/**
	 * @param rECVCHANNNAME the rECV_CHANN_NAME to set
	 */
	public void setRECV_CHANN_NAME(String rECVCHANNNAME) {
		RECV_CHANN_NAME = rECVCHANNNAME;
	}
	/**
	 * @return the sALE_DATE
	 */
	public String getSALE_DATE() {
		return SALE_DATE;
	}
	/**
	 * @param sALEDATE the sALE_DATE to set
	 */
	public void setSALE_DATE(String sALEDATE) {
		SALE_DATE = sALEDATE;
	}
	/**
	 * @return the rECV_ADDR_NO
	 */
	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}
	/**
	 * @param rECVADDRNO the rECV_ADDR_NO to set
	 */
	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}
	/**
	 * @return the rECV_ADDR
	 */
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	/**
	 * @param rECVADDR the rECV_ADDR to set
	 */
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}
	/**
	 * @return the sTOREOUT_AMOUNT
	 */
	public String getSTOREOUT_AMOUNT() {
		return STOREOUT_AMOUNT;
	}
	/**
	 * @param sTOREOUTAMOUNT the sTOREOUT_AMOUNT to set
	 */
	public void setSTOREOUT_AMOUNT(String sTOREOUTAMOUNT) {
		STOREOUT_AMOUNT = sTOREOUTAMOUNT;
	}
	/**
	 * @return the sTATE
	 */
	public String getSTATE() {
		return STATE;
	}
	/**
	 * @param sTATE the sTATE to set
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}
	/**
	 * @param rEMARK the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	/**
	 * @return the cREATOR
	 */
	public String getCREATOR() {
		return CREATOR;
	}
	/**
	 * @param cREATOR the cREATOR to set
	 */
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	/**
	 * @return the cRE_NAME
	 */
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	/**
	 * @param cRENAME the cRE_NAME to set
	 */
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	/**
	 * @return the cRE_TIME
	 */
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	/**
	 * @param cRETIME the cRE_TIME to set
	 */
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	/**
	 * @return the uPDATOR
	 */
	public String getUPDATOR() {
		return UPDATOR;
	}
	/**
	 * @param uPDATOR the uPDATOR to set
	 */
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}
	/**
	 * @return the uPD_NAME
	 */
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	/**
	 * @param uPDNAME the uPD_NAME to set
	 */
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}
	/**
	 * @return the uPD_TIME
	 */
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	/**
	 * @param uPDTIME the uPD_TIME to set
	 */
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}
	/**
	 * @return the dEPT_ID
	 */
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	/**
	 * @param dEPTID the dEPT_ID to set
	 */
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}
	/**
	 * @return the dEPT_NAME
	 */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	/**
	 * @param dEPTNAME the dEPT_NAME to set
	 */
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}
	/**
	 * @return the oRG_ID
	 */
	public String getORG_ID() {
		return ORG_ID;
	}
	/**
	 * @param oRGID the oRG_ID to set
	 */
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}
	/**
	 * @return the oRG_NAME
	 */
	public String getORG_NAME() {
		return ORG_NAME;
	}
	/**
	 * @param oRGNAME the oRG_NAME to set
	 */
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}
	/**
	 * @return the lEDGER_ID
	 */
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	/**
	 * @param lEDGERID the lEDGER_ID to set
	 */
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}
	/**
	 * @return the lEDGER_NAME
	 */
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	/**
	 * @param lEDGERNAME the lEDGER_NAME to set
	 */
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}
	/**
	 * @return the dEL_FLAG
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	/**
	 * @return the oTHER_COST
	 */
	public String getOTHER_COST() {
		return OTHER_COST;
	}
	/**
	 * @param oTHERCOST the oTHER_COST to set
	 */
	public void setOTHER_COST(String oTHERCOST) {
		OTHER_COST = oTHERCOST;
	}
	
}
