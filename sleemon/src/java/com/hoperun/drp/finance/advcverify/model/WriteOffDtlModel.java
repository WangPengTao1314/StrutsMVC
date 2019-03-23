package com.hoperun.drp.finance.advcverify.model;

import com.hoperun.commons.model.BaseModel;

public class WriteOffDtlModel extends BaseModel{
	
	/** 核销明细ID**/
	private String WRITE_OFF_DTL_ID;
	/** 结算单付款明细ID**/
	private String STATEMENTS_PAYMENT_DTL_ID;
	/** 付款方式**/
	private String PAY_TYPE;
	/** 付款单据号**/
	private String PAY_BILL_NO;
	/** 删除标记 **/
	private String DEL_FLAG;
	/** 核销人ID **/
	private String WRITE_OFF_PSON_ID;
	/** 核销人名称 **/
	private String WRITE_OFF_PSON_NAME;
	/** 核销时间 **/
	private String WRITE_OFF_PSON_TIME;
	/** 核销金额 **/
	private String WRITE_OFF_AMONT;
	public String getWRITE_OFF_DTL_ID() {
		return WRITE_OFF_DTL_ID;
	}
	public void setWRITE_OFF_DTL_ID(String wRITEOFFDTLID) {
		WRITE_OFF_DTL_ID = wRITEOFFDTLID;
	}
	public String getSTATEMENTS_PAYMENT_DTL_ID() {
		return STATEMENTS_PAYMENT_DTL_ID;
	}
	public void setSTATEMENTS_PAYMENT_DTL_ID(String sTATEMENTSPAYMENTDTLID) {
		STATEMENTS_PAYMENT_DTL_ID = sTATEMENTSPAYMENTDTLID;
	}
	public String getPAY_TYPE() {
		return PAY_TYPE;
	}
	public void setPAY_TYPE(String pAYTYPE) {
		PAY_TYPE = pAYTYPE;
	}
	public String getPAY_BILL_NO() {
		return PAY_BILL_NO;
	}
	public void setPAY_BILL_NO(String pAYBILLNO) {
		PAY_BILL_NO = pAYBILLNO;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getWRITE_OFF_PSON_ID() {
		return WRITE_OFF_PSON_ID;
	}
	public void setWRITE_OFF_PSON_ID(String wRITEOFFPSONID) {
		WRITE_OFF_PSON_ID = wRITEOFFPSONID;
	}
	public String getWRITE_OFF_PSON_NAME() {
		return WRITE_OFF_PSON_NAME;
	}
	public void setWRITE_OFF_PSON_NAME(String wRITEOFFPSONNAME) {
		WRITE_OFF_PSON_NAME = wRITEOFFPSONNAME;
	}
	public String getWRITE_OFF_PSON_TIME() {
		return WRITE_OFF_PSON_TIME;
	}
	public void setWRITE_OFF_PSON_TIME(String wRITEOFFPSONTIME) {
		WRITE_OFF_PSON_TIME = wRITEOFFPSONTIME;
	}
	public String getWRITE_OFF_AMONT() {
		return WRITE_OFF_AMONT;
	}
	public void setWRITE_OFF_AMONT(String wRITEOFFAMONT) {
		WRITE_OFF_AMONT = wRITEOFFAMONT;
	}
	
	
	
	

}
