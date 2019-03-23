/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvctoorderModelGchld
*/
package com.hoperun.drp.sale.saleordera.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:38:52
 */
public class SaleorderaModelChld extends BaseModel{
	private String SALE_ORDER_DTL_ID;
	private String SALE_ORDER_ID;
	private String PRD_ID;
	private String PRD_NO;
	private String PRD_NAME;
	private String PRD_SPEC;
	private String BRAND;
	private String STD_UNIT;
	private String IS_NO_STAND_FLAG;
	private String PRICE;
	private String DECT_RATE;
	private String DECT_PRICE;
	private String ORDER_NUM;
	private String ORDER_AMOUNT;
	private String SENDED_NUM;
	private String CANCLE_NUM;
	private String ADVC_SEND_DATE;
	private String REMARK;
	private String DEL_FLAG;
	private String FROM_BILL_DTL_ID;
	private String CANCEL_FLAG;
	private String SPCL_TECH_ID;
	private String YEAR_MONTH;
	private String PRD_TYPE;
	private String CUST_NAME;
	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}
	public void setSALE_ORDER_DTL_ID(String sALEORDERDTLID) {
		SALE_ORDER_DTL_ID = sALEORDERDTLID;
	}
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
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
	public String getBRAND() {
		return BRAND;
	}
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}
	public String getSTD_UNIT() {
		return STD_UNIT;
	}
	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}
	public String getIS_NO_STAND_FLAG() {
		return IS_NO_STAND_FLAG;
	}
	public void setIS_NO_STAND_FLAG(String iSNOSTANDFLAG) {
		IS_NO_STAND_FLAG = iSNOSTANDFLAG;
	}
	public String getPRICE() {
		return PRICE;
	}
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	public String getDECT_RATE() {
		return DECT_RATE;
	}
	public void setDECT_RATE(String dECTRATE) {
		DECT_RATE = dECTRATE;
	}
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}
	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}
	public String getORDER_NUM() {
		return ORDER_NUM;
	}
	public void setORDER_NUM(String oRDERNUM) {
		ORDER_NUM = oRDERNUM;
	}
	public String getORDER_AMOUNT() {
		return ORDER_AMOUNT;
	}
	public void setORDER_AMOUNT(String oRDERAMOUNT) {
		ORDER_AMOUNT = oRDERAMOUNT;
	}
	public String getSENDED_NUM() {
		return SENDED_NUM;
	}
	public void setSENDED_NUM(String sENDEDNUM) {
		SENDED_NUM = sENDEDNUM;
	}
	public String getCANCLE_NUM() {
		return CANCLE_NUM;
	}
	public void setCANCLE_NUM(String cANCLENUM) {
		CANCLE_NUM = cANCLENUM;
	}
	public String getADVC_SEND_DATE() {
		return ADVC_SEND_DATE;
	}
	public void setADVC_SEND_DATE(String aDVCSENDDATE) {
		ADVC_SEND_DATE = aDVCSENDDATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getFROM_BILL_DTL_ID() {
		return FROM_BILL_DTL_ID;
	}
	public void setFROM_BILL_DTL_ID(String fROMBILLDTLID) {
		FROM_BILL_DTL_ID = fROMBILLDTLID;
	}
	public String getCANCEL_FLAG() {
		return CANCEL_FLAG;
	}
	public void setCANCEL_FLAG(String cANCELFLAG) {
		CANCEL_FLAG = cANCELFLAG;
	}
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}
	public String getYEAR_MONTH() {
		return YEAR_MONTH;
	}
	public void setYEAR_MONTH(String yEARMONTH) {
		YEAR_MONTH = yEARMONTH;
	}
	public String getPRD_TYPE() {
		return PRD_TYPE;
	}
	public void setPRD_TYPE(String pRDTYPE) {
		PRD_TYPE = pRDTYPE;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}
	
}