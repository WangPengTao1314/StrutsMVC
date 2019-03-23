package com.hoperun.erp.oamg.monthVisit.model;

import com.hoperun.commons.model.BaseModel;

public class MonthvisitModel extends BaseModel {
	 
	    private static final long serialVersionUID = 1L;
		/**月度拜访计划ID**/
		private   String   MONTH_VISIT_PLAN_ID;
	    /**月度拜访计划编号**/
		private   String   MONTH_VISIT_PLAN_NO;
		/**人员信息ID**/
		private   String   RYXXID;
		/**人员信息编号**/
		private   String   RYBH;
		/**姓名**/
		private   String   RYMC;
		/**计划年份**/
		private   String   PLAN_YEAR; 
		/**计划月份**/
		private   String   PLAN_MONTH;
		/**计划拜访次数**/
		private   String   PLAN_VISIT_NUM_TOTAL;
		/**状态**/
		private   String   STATE;
		/**备注**/
        private   String   REMARK;		
        
		public String getREMARK() {
			return REMARK;
		}
		public void setREMARK(String remark) {
			REMARK = remark;
		}
		public String getMONTH_VISIT_PLAN_ID() {
			return MONTH_VISIT_PLAN_ID;
		}
		public void setMONTH_VISIT_PLAN_ID(String month_visit_plan_id) {
			MONTH_VISIT_PLAN_ID = month_visit_plan_id;
		}
		public String getMONTH_VISIT_PLAN_NO() {
			return MONTH_VISIT_PLAN_NO;
		}
		public void setMONTH_VISIT_PLAN_NO(String month_visit_plan_no) {
			MONTH_VISIT_PLAN_NO = month_visit_plan_no;
		}
		public String getRYXXID() {
			return RYXXID;
		}
		public void setRYXXID(String ryxxid) {
			RYXXID = ryxxid;
		}
		public String getRYBH() {
			return RYBH;
		}
		public void setRYBH(String rybh) {
			RYBH = rybh;
		}
		public String getRYMC() {
			return RYMC;
		}
		public void setRYMC(String rymc) {
			RYMC = rymc;
		}
		public String getPLAN_YEAR() {
			return PLAN_YEAR;
		}
		public void setPLAN_YEAR(String plan_year) {
			PLAN_YEAR = plan_year;
		}
		public String getPLAN_MONTH() {
			return PLAN_MONTH;
		}
		public void setPLAN_MONTH(String plan_month) {
			PLAN_MONTH = plan_month;
		}
		public String getPLAN_VISIT_NUM_TOTAL() {
			return PLAN_VISIT_NUM_TOTAL;
		}
		public void setPLAN_VISIT_NUM_TOTAL(String plan_visit_num_total) {
			PLAN_VISIT_NUM_TOTAL = plan_visit_num_total;
		}
		public String getSTATE() {
			return STATE;
		}
		public void setSTATE(String state) {
			STATE = state;
		}
	 

}
