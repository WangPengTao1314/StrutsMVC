package com.hoperun.drp.oamg.termrefinecheck.model;

import com.hoperun.commons.model.BaseModel;

public class ChanncheckplanModelChild extends BaseModel{
	
		/**
	     * 
	     */
	    private static final long serialVersionUID = 1L;
		/**渠道检查方案明细ID**/
	    private   String   CHANN_CHECK_PLAN_DTL_ID;
		/**渠道化检查方案ID**/
	    private   String   CHANN_CHECK_PLAN_ID;
		/**检查项目类型**/
	    private   String   PRJ_TYPE;
		/**检查项目编号**/
	    private   String   PRJ_CODE;
		/**检查项目名称**/
	    private   String   PRJ_NAME;
		/**项目分值**/
	    private   String   PRJ_SCORE;
		public String getCHANN_CHECK_PLAN_DTL_ID() {
			return CHANN_CHECK_PLAN_DTL_ID;
		}
		public void setCHANN_CHECK_PLAN_DTL_ID(String chann_check_plan_dtl_id) {
			CHANN_CHECK_PLAN_DTL_ID = chann_check_plan_dtl_id;
		}
		public String getCHANN_CHECK_PLAN_ID() {
			return CHANN_CHECK_PLAN_ID;
		}
		public void setCHANN_CHECK_PLAN_ID(String chann_check_plan_id) {
			CHANN_CHECK_PLAN_ID = chann_check_plan_id;
		}
		public String getPRJ_TYPE() {
			return PRJ_TYPE;
		}
		public void setPRJ_TYPE(String prj_type) {
			PRJ_TYPE = prj_type;
		}
		public String getPRJ_CODE() {
			return PRJ_CODE;
		}
		public void setPRJ_CODE(String prj_code) {
			PRJ_CODE = prj_code;
		}
		public String getPRJ_NAME() {
			return PRJ_NAME;
		}
		public void setPRJ_NAME(String prj_name) {
			PRJ_NAME = prj_name;
		}
		public String getPRJ_SCORE() {
			return PRJ_SCORE;
		}
		public void setPRJ_SCORE(String prj_score) {
			PRJ_SCORE = prj_score;
		}
	

}
