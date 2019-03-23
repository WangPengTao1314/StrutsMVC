package com.hoperun.erp.sale.member.model;

public class MemberPointModel {
	/**会员信息ID **/
	private String MEMBER_ID;
	/** 会员积分信息明细ID**/
	private String MEMBER_POINT_DTL_ID;
	/**业务类型**/
	private String BUS_TYPE;
	/** 业务编号**/
	private String BUS_ORDER_NO;
	/**变更积分 **/
	private String CURR_POINT;
	/**变更后积分 **/
	private String NEW_POINT;
	/**积分变更时间 **/
	private String POINT_EDIT_TIME;
	
	public String getMEMBER_ID() {
		return MEMBER_ID;
	}
	public void setMEMBER_ID(String mEMBERID) {
		MEMBER_ID = mEMBERID;
	}
	public String getMEMBER_POINT_DTL_ID() {
		return MEMBER_POINT_DTL_ID;
	}
	public void setMEMBER_POINT_DTL_ID(String mEMBERPOINTDTLID) {
		MEMBER_POINT_DTL_ID = mEMBERPOINTDTLID;
	}
	public String getBUS_TYPE() {
		return BUS_TYPE;
	}
	public void setBUS_TYPE(String bUSTYPE) {
		BUS_TYPE = bUSTYPE;
	}
	public String getBUS_ORDER_NO() {
		return BUS_ORDER_NO;
	}
	public void setBUS_ORDER_NO(String bUSORDERNO) {
		BUS_ORDER_NO = bUSORDERNO;
	}
	public String getCURR_POINT() {
		return CURR_POINT;
	}
	public void setCURR_POINT(String cURRPOINT) {
		CURR_POINT = cURRPOINT;
	}
	public String getNEW_POINT() {
		return NEW_POINT;
	}
	public void setNEW_POINT(String nEWPOINT) {
		NEW_POINT = nEWPOINT;
	}
	public String getPOINT_EDIT_TIME() {
		return POINT_EDIT_TIME;
	}
	public void setPOINT_EDIT_TIME(String pOINTEDITTIME) {
		POINT_EDIT_TIME = pOINTEDITTIME;
	}
	
	
	

}
