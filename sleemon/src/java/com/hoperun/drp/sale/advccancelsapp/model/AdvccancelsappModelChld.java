/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappModelChld
 */
package com.hoperun.drp.sale.advccancelsapp.model;

import com.hoperun.commons.model.BaseModel;

/**
 *  
 */
public class AdvccancelsappModelChld extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2442196059147998173L;

	private String ADVC_SEND_CANCEL_DTL_ID;
	private String ADVC_SEND_CANCEL_ID;

	/** 来源明细ID **/
	private String FROM_BILL_DTL_ID;

	/** 货品ID **/
	private String PRD_ID;
	/** 货品编号 **/
	private String PRD_NO;
	/** 货品名称 **/
	private String PRD_NAME;
	/** 规格型号 **/
	private String PRD_SPEC;
	/** 颜色 **/
	private String PRD_COLOR;
	/** 品牌 **/
	private String BRAND;
	/** 标准单位 **/
	private String STD_UNIT;

	private String CANCEL_NUM;
	/** 单价 **/
	private String PRICE;
	/** 折扣率 **/
	private String DECT_RATE;
	/** 折扣价 **/
	private String DECT_PRICE;
	/** 折后金额 **/
	private String DECT_AMOUNT;
	/** 备注 **/
	private String CANCEL_RSON;

	/** 订单特殊工艺ID **/
	private String SPCL_TECH_ID;

	private String DEL_FLAG;

	public String getADVC_SEND_CANCEL_DTL_ID() {
		return ADVC_SEND_CANCEL_DTL_ID;
	}

	public void setADVC_SEND_CANCEL_DTL_ID(String aDVCSENDCANCELDTLID) {
		ADVC_SEND_CANCEL_DTL_ID = aDVCSENDCANCELDTLID;
	}

	public String getADVC_SEND_CANCEL_ID() {
		return ADVC_SEND_CANCEL_ID;
	}

	public void setADVC_SEND_CANCEL_ID(String aDVCSENDCANCELID) {
		ADVC_SEND_CANCEL_ID = aDVCSENDCANCELID;
	}

	public String getFROM_BILL_DTL_ID() {
		return FROM_BILL_DTL_ID;
	}

	public void setFROM_BILL_DTL_ID(String fROMBILLDTLID) {
		FROM_BILL_DTL_ID = fROMBILLDTLID;
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

	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
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

	public String getCANCEL_NUM() {
		return CANCEL_NUM;
	}

	public void setCANCEL_NUM(String cANCELNUM) {
		CANCEL_NUM = cANCELNUM;
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

	public String getDECT_AMOUNT() {
		return DECT_AMOUNT;
	}

	public void setDECT_AMOUNT(String dECTAMOUNT) {
		DECT_AMOUNT = dECTAMOUNT;
	}

	public String getCANCEL_RSON() {
		return CANCEL_RSON;
	}

	public void setCANCEL_RSON(String cANCELRSON) {
		CANCEL_RSON = cANCELRSON;
	}

	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	
}