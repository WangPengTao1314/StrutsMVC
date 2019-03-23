package com.hoperun.erp.sale.member.model;

public class MemberActModel {
	
	private String MEMBER_ID;
	private String MEMBER_ACT_DTL_ID;
	/** 营销活动ID **/
	private String MARKETING_ACT_ID;
	/** 营销活动编号 **/
	private String MARKETING_ACT_NO;
	/** 营销活动名称 **/
	private String MARKETING_ACT_NAME;
	/** 开始日期 **/
	private String START_DATE;
	/** 结束日期 **/
	private String END_DATE;
	/** 发起人名称 **/
	private String SPONSOR_NAME;
	private String DEL_FLAG;
	
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBERID) {
		MEMBER_ID = mEMBERID;
	}
	public String getMEMBER_ACT_DTL_ID() {
		return MEMBER_ACT_DTL_ID;
	}
	public void setMEMBER_ACT_DTL_ID(String mEMBERACTDTLID) {
		MEMBER_ACT_DTL_ID = mEMBERACTDTLID;
	}
	public String getMARKETING_ACT_ID() {
		return MARKETING_ACT_ID;
	}
	public void setMARKETING_ACT_ID(String mARKETINGACTID) {
		MARKETING_ACT_ID = mARKETINGACTID;
	}
	public String getMARKETING_ACT_NO() {
		return MARKETING_ACT_NO;
	}
	public void setMARKETING_ACT_NO(String mARKETINGACTNO) {
		MARKETING_ACT_NO = mARKETINGACTNO;
	}
	public String getMARKETING_ACT_NAME() {
		return MARKETING_ACT_NAME;
	}
	public void setMARKETING_ACT_NAME(String mARKETINGACTNAME) {
		MARKETING_ACT_NAME = mARKETINGACTNAME;
	}
	public String getSTART_DATE() {
		return START_DATE;
	}
	public void setSTART_DATE(String sTARTDATE) {
		START_DATE = sTARTDATE;
	}
	public String getEND_DATE() {
		return END_DATE;
	}
	public void setEND_DATE(String eNDDATE) {
		END_DATE = eNDDATE;
	}
	public String getSPONSOR_NAME() {
		return SPONSOR_NAME;
	}
	public void setSPONSOR_NAME(String sPONSORNAME) {
		SPONSOR_NAME = sPONSORNAME;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	

}
