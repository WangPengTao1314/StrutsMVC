/**

 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderModel
*/
package com.hoperun.drp.store.storeouta.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class StoreoutaModel extends BaseModel{
	private String STOREOUT_ID;
	private String STOREOUT_NO;
	private String FROM_BILL_ID;
	private String FROM_BILL_NO;
	private String STOREOUT_STORE_ID;
	private String STOREOUT_STORE_NO;
	private String STOREOUT_STORE_NAME;
	private String RECV_CHANN_ID;
	private String RECV_CHANN_NO;
	private String RECV_CHANN_NAME;
	private String SALE_DATE;
	private String RECV_ADDR;
	private String STOREOUT_AMOUNT;
	private String STATE;
	private String REMARK;
	private String RSP_NAME;
	public String getSTOREOUT_ID() {
		return STOREOUT_ID;
	}
	public void setSTOREOUT_ID(String sTOREOUTID) {
		STOREOUT_ID = sTOREOUTID;
	}
	public String getSTOREOUT_NO() {
		return STOREOUT_NO;
	}
	public void setSTOREOUT_NO(String sTOREOUTNO) {
		STOREOUT_NO = sTOREOUTNO;
	}
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}
	public void setFROM_BILL_ID(String fROMBILLID) {
		FROM_BILL_ID = fROMBILLID;
	}
	public String getFROM_BILL_NO() {
		return FROM_BILL_NO;
	}
	public void setFROM_BILL_NO(String fROMBILLNO) {
		FROM_BILL_NO = fROMBILLNO;
	}
	public String getSTOREOUT_STORE_ID() {
		return STOREOUT_STORE_ID;
	}
	public void setSTOREOUT_STORE_ID(String sTOREOUTSTOREID) {
		STOREOUT_STORE_ID = sTOREOUTSTOREID;
	}
	public String getSTOREOUT_STORE_NO() {
		return STOREOUT_STORE_NO;
	}
	public void setSTOREOUT_STORE_NO(String sTOREOUTSTORENO) {
		STOREOUT_STORE_NO = sTOREOUTSTORENO;
	}
	public String getSTOREOUT_STORE_NAME() {
		return STOREOUT_STORE_NAME;
	}
	public void setSTOREOUT_STORE_NAME(String sTOREOUTSTORENAME) {
		STOREOUT_STORE_NAME = sTOREOUTSTORENAME;
	}
	public String getRECV_CHANN_ID() {
		return RECV_CHANN_ID;
	}
	public void setRECV_CHANN_ID(String rECVCHANNID) {
		RECV_CHANN_ID = rECVCHANNID;
	}
	public String getRECV_CHANN_NO() {
		return RECV_CHANN_NO;
	}
	public void setRECV_CHANN_NO(String rECVCHANNNO) {
		RECV_CHANN_NO = rECVCHANNNO;
	}
	public String getRECV_CHANN_NAME() {
		return RECV_CHANN_NAME;
	}
	public void setRECV_CHANN_NAME(String rECVCHANNNAME) {
		RECV_CHANN_NAME = rECVCHANNNAME;
	}
	public String getSALE_DATE() {
		return SALE_DATE;
	}
	public void setSALE_DATE(String sALEDATE) {
		SALE_DATE = sALEDATE;
	}
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}
	public String getSTOREOUT_AMOUNT() {
		return STOREOUT_AMOUNT;
	}
	public void setSTOREOUT_AMOUNT(String sTOREOUTAMOUNT) {
		STOREOUT_AMOUNT = sTOREOUTAMOUNT;
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
	public String getRSP_NAME() {
		return RSP_NAME;
	}
	public void setRSP_NAME(String rSPNAME) {
		RSP_NAME = rSPNAME;
	}
	
}