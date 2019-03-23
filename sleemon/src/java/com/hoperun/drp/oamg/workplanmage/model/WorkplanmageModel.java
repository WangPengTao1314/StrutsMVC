package com.hoperun.drp.oamg.workplanmage.model;

import com.hoperun.commons.model.BaseModel;

public class WorkplanmageModel extends BaseModel  {
  
	    private static final long serialVersionUID = 1L;
		/**工作计划ID**/
	    private   String  WORK_PLAN_ID; 
	    /**工作计划NO**/
	    private   String  WORK_PLAN_NO;
	    /**战区ID**/
	    private   String  WAREA_ID;
	    /**战区编号**/
	    private   String  WAREA_NO;
	    /**战区名称**/
	    private   String  WAREA_NAME; 
	    /**月份**/
	    private   String  PLAN_MONTH;
	    /**年份**/
	    private   String  PLAN_YEAR;
	    /**总上报份数**/
	    private   String  TOTAL_UP_REPORT_NUM;
	    /**状态**/
	    private   String  STATE; 
	    /**备注**/
	    private   String  REMARK;
	   
		public String getWORK_PLAN_ID() {
			return WORK_PLAN_ID;
		}
		public void setWORK_PLAN_ID(String work_plan_id) {
			WORK_PLAN_ID = work_plan_id;
		}
		public String getWORK_PLAN_NO() {
			return WORK_PLAN_NO;
		}
		public void setWORK_PLAN_NO(String work_plan_no) {
			WORK_PLAN_NO = work_plan_no;
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
		public String getPLAN_MONTH() {
			return PLAN_MONTH;
		}
		public void setPLAN_MONTH(String plan_month) {
			PLAN_MONTH = plan_month;
		}
		public String getPLAN_YEAR() {
			return PLAN_YEAR;
		}
		public void setPLAN_YEAR(String plan_year) {
			PLAN_YEAR = plan_year;
		}
		public String getTOTAL_UP_REPORT_NUM() {
			return TOTAL_UP_REPORT_NUM;
		}
		public void setTOTAL_UP_REPORT_NUM(String total_up_report_num) {
			TOTAL_UP_REPORT_NUM = total_up_report_num;
		} 
	   
}
