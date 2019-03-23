package com.hoperun.drp.oamg.workplanmage.model;

import com.hoperun.commons.model.BaseModel;

public class WorkplanmagedtlModel extends BaseModel  {
     
	 
	    private static final long serialVersionUID = 1L;
		/**工作计划明细ID**/
	    private    String    WORK_PLAN_DTL_ID;
	    /**工作计划ID**/
	    private    String    WORK_PLAN_ID;
	    /**人员信息ID**/
	    private    String    RYXXID;
	    /**人员编号**/
	    private    String    RYBH;
	    /**姓名**/
	    private    String    RYMC;
	    /**上报份数**/
	    private    String    UP_REPORT_NUM;
	    
		public String getRYMC() {
			return RYMC;
		}
		public void setRYMC(String rymc) {
			RYMC = rymc;
		}
		public String getUP_REPORT_NUM() {
			return UP_REPORT_NUM;
		}
		public void setUP_REPORT_NUM(String up_report_num) {
			UP_REPORT_NUM = up_report_num;
		}
		public String getWORK_PLAN_DTL_ID() {
			return WORK_PLAN_DTL_ID;
		}
		public void setWORK_PLAN_DTL_ID(String work_plan_dtl_id) {
			WORK_PLAN_DTL_ID = work_plan_dtl_id;
		}
		public String getWORK_PLAN_ID() {
			return WORK_PLAN_ID;
		}
		public void setWORK_PLAN_ID(String work_plan_id) {
			WORK_PLAN_ID = work_plan_id;
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
		 
}

