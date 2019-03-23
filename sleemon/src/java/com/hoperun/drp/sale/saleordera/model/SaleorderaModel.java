/**

 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderModel
*/
package com.hoperun.drp.sale.saleordera.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class SaleorderaModel extends BaseModel{
	private String SALE_ORDER_ID;
	private String SALE_ORDER_NO;
	private String BILL_TYPE;
	private String FROM_BILL_ID;
	private String FROM_BILL_NO;
	private String ORDER_CHANN_ID;
	private String ORDER_CHANN_NO;
	private String ORDER_CHANN_NAME;
	private String ORDER_DATE;
	private String PERSON_CON;
	private String TEL;
	private String RECV_ADDR;
	private String STATE;
	private String REMARK;
	private String AREA_ID;
	private String AREA_NO;
	private String AREA_NAME;
	private String RECV_ADDR_NO;
	private String RSP_NAME;
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
	}
	public String getSALE_ORDER_NO() {
		return SALE_ORDER_NO;
	}
	public void setSALE_ORDER_NO(String sALEORDERNO) {
		SALE_ORDER_NO = sALEORDERNO;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILLTYPE) {
		BILL_TYPE = bILLTYPE;
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
	public String getORDER_CHANN_ID() {
		return ORDER_CHANN_ID;
	}
	public void setORDER_CHANN_ID(String oRDERCHANNID) {
		ORDER_CHANN_ID = oRDERCHANNID;
	}
	public String getORDER_CHANN_NO() {
		return ORDER_CHANN_NO;
	}
	public void setORDER_CHANN_NO(String oRDERCHANNNO) {
		ORDER_CHANN_NO = oRDERCHANNNO;
	}
	public String getORDER_CHANN_NAME() {
		return ORDER_CHANN_NAME;
	}
	public void setORDER_CHANN_NAME(String oRDERCHANNNAME) {
		ORDER_CHANN_NAME = oRDERCHANNNAME;
	}
	public String getORDER_DATE() {
		return ORDER_DATE;
	}
	public void setORDER_DATE(String oRDERDATE) {
		ORDER_DATE = oRDERDATE;
	}
	public String getPERSON_CON() {
		return PERSON_CON;
	}
	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
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
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}
	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}
	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}
	public String getRSP_NAME() {
		return RSP_NAME;
	}
	public void setRSP_NAME(String rSPNAME) {
		RSP_NAME = rSPNAME;
	}
	
}