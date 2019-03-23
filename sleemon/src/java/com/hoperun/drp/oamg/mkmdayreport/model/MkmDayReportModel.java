package com.hoperun.drp.oamg.mkmdayreport.model;

import com.hoperun.commons.model.BaseModel;

public class MkmDayReportModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**营销经理日报表ID**/
	private String MKM_DAY_RPT_ID;
	/**营销经理日报表编号**/
	private String MKM_DAY_RPT_NO;
	/**营销经理ID**/
	private String MKM_ID;
	/**营销经理名称**/
	private String MKM_NAME;
	/**战区ID**/
	private String WAREA_ID;
	/**战区编号**/
	private String WAREA_NO;
	/**战区名称**/
	private String WAREA_NAME;
	/**上报日期**/
	private String REPORT_DATE;
	/**拜访日期**/
	private String VST_DATE;
	/**出差途中**/
	private String ON_TRIP;
	/**公司总部公务处理**/
	private String CORP_BUSS_DEAL;
	/**调休**/
	private String WAKE_OFF;
	/**状态**/
	private String STATE;
	/**备注**/
	private String REMARK;
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getMKM_DAY_RPT_ID() {
		return MKM_DAY_RPT_ID;
	}
	public void setMKM_DAY_RPT_ID(String mkm_day_rpt_id) {
		MKM_DAY_RPT_ID = mkm_day_rpt_id;
	}
	public String getMKM_DAY_RPT_NO() {
		return MKM_DAY_RPT_NO;
	}
	public void setMKM_DAY_RPT_NO(String mkm_day_rpt_no) {
		MKM_DAY_RPT_NO = mkm_day_rpt_no;
	}
	public String getMKM_ID() {
		return MKM_ID;
	}
	public void setMKM_ID(String mkm_id) {
		MKM_ID = mkm_id;
	}
	public String getMKM_NAME() {
		return MKM_NAME;
	}
	public void setMKM_NAME(String mkm_name) {
		MKM_NAME = mkm_name;
	}
	public String getWAREA_ID() {
		return WAREA_ID;
	}
	public void setWAREA_ID(String warea_id) {
		WAREA_ID = warea_id;
	}
	public String getWAREA_NO() {
		return WAREA_NO;
	}
	public void setWAREA_NO(String warea_no) {
		WAREA_NO = warea_no;
	}
	public String getWAREA_NAME() {
		return WAREA_NAME;
	}
	public void setWAREA_NAME(String warea_name) {
		WAREA_NAME = warea_name;
	}
	public String getREPORT_DATE() {
		return REPORT_DATE;
	}
	public void setREPORT_DATE(String report_date) {
		REPORT_DATE = report_date;
	}
	public String getVST_DATE() {
		return VST_DATE;
	}
	public void setVST_DATE(String vst_date) {
		VST_DATE = vst_date;
	}
	public String getON_TRIP() {
		return ON_TRIP;
	}
	public void setON_TRIP(String on_trip) {
		ON_TRIP = on_trip;
	}
	public String getCORP_BUSS_DEAL() {
		return CORP_BUSS_DEAL;
	}
	public void setCORP_BUSS_DEAL(String corp_buss_deal) {
		CORP_BUSS_DEAL = corp_buss_deal;
	}
	public String getWAKE_OFF() {
		return WAKE_OFF;
	}
	public void setWAKE_OFF(String wake_off) {
		WAKE_OFF = wake_off;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
}
