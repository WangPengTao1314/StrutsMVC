package com.hoperun.erp.sale.saleplan.model;

import com.hoperun.commons.model.BaseModel;

public class SaleplanModel extends BaseModel  {
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**销售计划ID**/
	private   String   SALE_PLAN_ID;
	/**销售计划编号**/
	private   String   SALE_PLAN_NO;
	/**渠道ID**/
	private   String   CHANN_ID;
	/**渠道编号**/
	private   String   CHANN_NO;
	/**渠道名称**/
	private   String   CHANN_NAME;
	/**渠道简称**/
	private   String   CHANN_ABBR;
	/**渠道类型**/
	private   String   CHANN_TYPE;
	/**渠道级别**/
	private   String   CHAA_LVL;
	/**区域ID**/
	private   String   AREA_ID;
	/**区域编号**/
	private   String   AREA_NO;
	/**区域名称**/
	private   String   AREA_NAME;
	/**区域经理ID**/
	private   String   AREA_MANAGE_ID;
	/**区域经理名称**/
	private   String   AREA_MANAGE_NAME;
	/**计划年份**/
	private   String   PLAN_YEAR;
	/**计划发货总额**/
	private   String   PLAN_SALE_AMOUNT_TOTAL;
	/**加盟商零售总额**/
	private   String   CHANN_SALE_AMOUNT_TOTAL;
	/**状态**/
	private   String   STATE;
	/**备注**/
	private   String   REMARK;
	private   String   YEAR_PLAN_AMOUNT;
	private   String   FIRST_QUARTER_AMOUNT;
	private   String   SECOND_QUARTER_AMOUNT;
	private   String   THIRD_QUARTER_AMOUNT;
	private   String   FOURTH_QUARTER_AMOUNT;
	private   String   JAN_AMOUNT;
	private   String   FEB_AMOUNT;
	private   String   MAR_AMOUNT;
	private   String   APR_AMOUNT;
	private   String   MAY_AMOUNT;
	private   String   JUN_AMOUNT;
	private   String   JUL_AMOUNT;
	private   String   AUG_AMOUNT;
	private   String   SEP_AMOUNT;
	private   String   OCT_AMOUNT;
	private   String   NOV_AMOUNT;
	private   String   DEC_AMOUNT;
	private   String   PLAN_MONTH;
	
	private   String   SJXZ;
	private   String   SJGL;
	public String getSJXZ() {
		return SJXZ;
	}
	public void setSJXZ(String sjxz) {
		SJXZ = sjxz;
	}
	public String getSJGL() {
		return SJGL;
	}
	public void setSJGL(String sjgl) {
		SJGL = sjgl;
	}
	public String getPLAN_MONTH() {
		return PLAN_MONTH;
	}
	public void setPLAN_MONTH(String plan_month) {
		PLAN_MONTH = plan_month;
	}
	public String getSALE_PLAN_ID() {
		return SALE_PLAN_ID;
	}
	public void setSALE_PLAN_ID(String sale_plan_id) {
		SALE_PLAN_ID = sale_plan_id;
	}
	public String getSALE_PLAN_NO() {
		return SALE_PLAN_NO;
	}
	public void setSALE_PLAN_NO(String sale_plan_no) {
		SALE_PLAN_NO = sale_plan_no;
	}
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	public void setCHANN_ID(String chann_id) {
		CHANN_ID = chann_id;
	}
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	public void setCHANN_NO(String chann_no) {
		CHANN_NO = chann_no;
	}
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	public String getCHANN_ABBR() {
		return CHANN_ABBR;
	}
	public void setCHANN_ABBR(String chann_abbr) {
		CHANN_ABBR = chann_abbr;
	}
	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}
	public void setCHANN_TYPE(String chann_type) {
		CHANN_TYPE = chann_type;
	}
	public String getCHAA_LVL() {
		return CHAA_LVL;
	}
	public void setCHAA_LVL(String chaa_lvl) {
		CHAA_LVL = chaa_lvl;
	}
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String area_id) {
		AREA_ID = area_id;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public void setAREA_NO(String area_no) {
		AREA_NO = area_no;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String area_name) {
		AREA_NAME = area_name;
	}
	public String getAREA_MANAGE_ID() {
		return AREA_MANAGE_ID;
	}
	public void setAREA_MANAGE_ID(String area_manage_id) {
		AREA_MANAGE_ID = area_manage_id;
	}
	public String getAREA_MANAGE_NAME() {
		return AREA_MANAGE_NAME;
	}
	public void setAREA_MANAGE_NAME(String area_manage_name) {
		AREA_MANAGE_NAME = area_manage_name;
	}
	public String getPLAN_YEAR() {
		return PLAN_YEAR;
	}
	public void setPLAN_YEAR(String plan_year) {
		PLAN_YEAR = plan_year;
	}
	public String getPLAN_SALE_AMOUNT_TOTAL() {
		return PLAN_SALE_AMOUNT_TOTAL;
	}
	public void setPLAN_SALE_AMOUNT_TOTAL(String plan_sale_amount_total) {
		PLAN_SALE_AMOUNT_TOTAL = plan_sale_amount_total;
	}
	public String getCHANN_SALE_AMOUNT_TOTAL() {
		return CHANN_SALE_AMOUNT_TOTAL;
	}
	public void setCHANN_SALE_AMOUNT_TOTAL(String chann_sale_amount_total) {
		CHANN_SALE_AMOUNT_TOTAL = chann_sale_amount_total;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getYEAR_PLAN_AMOUNT() {
		return YEAR_PLAN_AMOUNT;
	}
	public void setYEAR_PLAN_AMOUNT(String year_plan_amount) {
		YEAR_PLAN_AMOUNT = year_plan_amount;
	}
	public String getFIRST_QUARTER_AMOUNT() {
		return FIRST_QUARTER_AMOUNT;
	}
	public void setFIRST_QUARTER_AMOUNT(String first_quarter_amount) {
		FIRST_QUARTER_AMOUNT = first_quarter_amount;
	}
	public String getSECOND_QUARTER_AMOUNT() {
		return SECOND_QUARTER_AMOUNT;
	}
	public void setSECOND_QUARTER_AMOUNT(String second_quarter_amount) {
		SECOND_QUARTER_AMOUNT = second_quarter_amount;
	}
	public String getTHIRD_QUARTER_AMOUNT() {
		return THIRD_QUARTER_AMOUNT;
	}
	public void setTHIRD_QUARTER_AMOUNT(String third_quarter_amount) {
		THIRD_QUARTER_AMOUNT = third_quarter_amount;
	}
	public String getFOURTH_QUARTER_AMOUNT() {
		return FOURTH_QUARTER_AMOUNT;
	}
	public void setFOURTH_QUARTER_AMOUNT(String fourth_quarter_amount) {
		FOURTH_QUARTER_AMOUNT = fourth_quarter_amount;
	}
	public String getJAN_AMOUNT() {
		return JAN_AMOUNT;
	}
	public void setJAN_AMOUNT(String jan_amount) {
		JAN_AMOUNT = jan_amount;
	}
	public String getFEB_AMOUNT() {
		return FEB_AMOUNT;
	}
	public void setFEB_AMOUNT(String feb_amount) {
		FEB_AMOUNT = feb_amount;
	}
	public String getMAR_AMOUNT() {
		return MAR_AMOUNT;
	}
	public void setMAR_AMOUNT(String mar_amount) {
		MAR_AMOUNT = mar_amount;
	}
	public String getAPR_AMOUNT() {
		return APR_AMOUNT;
	}
	public void setAPR_AMOUNT(String apr_amount) {
		APR_AMOUNT = apr_amount;
	}
	public String getMAY_AMOUNT() {
		return MAY_AMOUNT;
	}
	public void setMAY_AMOUNT(String may_amount) {
		MAY_AMOUNT = may_amount;
	}
	public String getJUN_AMOUNT() {
		return JUN_AMOUNT;
	}
	public void setJUN_AMOUNT(String jun_amount) {
		JUN_AMOUNT = jun_amount;
	}
	public String getJUL_AMOUNT() {
		return JUL_AMOUNT;
	}
	public void setJUL_AMOUNT(String jul_amount) {
		JUL_AMOUNT = jul_amount;
	}
	public String getAUG_AMOUNT() {
		return AUG_AMOUNT;
	}
	public void setAUG_AMOUNT(String aug_amount) {
		AUG_AMOUNT = aug_amount;
	}
	public String getSEP_AMOUNT() {
		return SEP_AMOUNT;
	}
	public void setSEP_AMOUNT(String sep_amount) {
		SEP_AMOUNT = sep_amount;
	}
	public String getOCT_AMOUNT() {
		return OCT_AMOUNT;
	}
	public void setOCT_AMOUNT(String oct_amount) {
		OCT_AMOUNT = oct_amount;
	}
	public String getNOV_AMOUNT() {
		return NOV_AMOUNT;
	}
	public void setNOV_AMOUNT(String nov_amount) {
		NOV_AMOUNT = nov_amount;
	}
	public String getDEC_AMOUNT() {
		return DEC_AMOUNT;
	}
	public void setDEC_AMOUNT(String dec_amount) {
		DEC_AMOUNT = dec_amount;
	}
}
