package com.hoperun.drp.visit.storevisit.model;

import com.hoperun.commons.model.BaseModel;

public class StoreVisitDtlModel extends BaseModel  {

	 
	    private static final long serialVersionUID = 1L;
	    
		/**门店拜访明细ID**/
        private   String  STORE_VISIT_DTL_ID;
	    /**门店拜访ID**/
        private   String  STORE_VISIT_ID;
        /**项目名称**/
        private   String  PRO_NAME;
        /**主类型**/
        private   String  MAIN_TYPE;
        /**目前情况分析**/
        private   String  SIT_ANALYSIS;
        /**行动方案**/
        private   String  ACTION_PLAN;
        /**预计完成时间**/
        private   String  COMPLETE_TIME;
        /**其他**/
        private   String  OTHER_INFO;
       
		public String getSTORE_VISIT_DTL_ID() {
			return STORE_VISIT_DTL_ID;
		}
		public void setSTORE_VISIT_DTL_ID(String store_visit_dtl_id) {
			STORE_VISIT_DTL_ID = store_visit_dtl_id;
		}
		public String getSTORE_VISIT_ID() {
			return STORE_VISIT_ID;
		}
		public void setSTORE_VISIT_ID(String store_visit_id) {
			STORE_VISIT_ID = store_visit_id;
		}
		public String getPRO_NAME() {
			return PRO_NAME;
		}
		public void setPRO_NAME(String pro_name) {
			PRO_NAME = pro_name;
		}
		public String getMAIN_TYPE() {
			return MAIN_TYPE;
		}
		public void setMAIN_TYPE(String main_type) {
			MAIN_TYPE = main_type;
		}
		public String getSIT_ANALYSIS() {
			return SIT_ANALYSIS;
		}
		public void setSIT_ANALYSIS(String sit_analysis) {
			SIT_ANALYSIS = sit_analysis;
		}
		public String getACTION_PLAN() {
			return ACTION_PLAN;
		}
		public void setACTION_PLAN(String action_plan) {
			ACTION_PLAN = action_plan;
		}
		public String getCOMPLETE_TIME() {
			return COMPLETE_TIME;
		}
		public void setCOMPLETE_TIME(String complete_time) {
			COMPLETE_TIME = complete_time;
		}
		public String getOTHER_INFO() {
			return OTHER_INFO;
		}
		public void setOTHER_INFO(String other_info) {
			OTHER_INFO = other_info;
		}
	       
} 
