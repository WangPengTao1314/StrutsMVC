package com.hoperun.erp.oamg.monthVisit.model;

import com.hoperun.commons.model.BaseModel;

public class MonthvisitdtlModel  extends BaseModel {
       
	 
	        private static final long serialVersionUID = 1L;
			/**月度拜访计划明细ID**/
	        private   String  MONTH_VISIT_PLAN_DTL_ID;
		    /**月度拜访计划ID**/
	        private   String  MONTH_VISIT_PLAN_ID;
		    /**拜访对象类型**/
	        private   String  VISIT_OBJ_TYPE;
		    /**拜访对象ID**/
	        private   String  VISIT_OBJ_ID;
		    /**拜访对象编号**/
	        private   String  VISIT_OBJ_NO;
		    /**拜访对象名称**/
	        private   String  VISIT_OBJ_NAME;
		    /**计划拜访次数**/
	        private   String  PLAN_VISIT_NUM;
	        private String VISIT_TYPE;
	        
			public String getVISIT_TYPE() {
				return VISIT_TYPE;
			}
			public void setVISIT_TYPE(String vISITTYPE) {
				VISIT_TYPE = vISITTYPE;
			}
			public String getMONTH_VISIT_PLAN_DTL_ID() {
				return MONTH_VISIT_PLAN_DTL_ID;
			}
			public void setMONTH_VISIT_PLAN_DTL_ID(String month_visit_plan_dtl_id) {
				MONTH_VISIT_PLAN_DTL_ID = month_visit_plan_dtl_id;
			}
			public String getMONTH_VISIT_PLAN_ID() {
				return MONTH_VISIT_PLAN_ID;
			}
			public void setMONTH_VISIT_PLAN_ID(String month_visit_plan_id) {
				MONTH_VISIT_PLAN_ID = month_visit_plan_id;
			}
			public String getVISIT_OBJ_TYPE() {
				return VISIT_OBJ_TYPE;
			}
			public void setVISIT_OBJ_TYPE(String visit_obj_type) {
				VISIT_OBJ_TYPE = visit_obj_type;
			}
			public String getVISIT_OBJ_ID() {
				return VISIT_OBJ_ID;
			}
			public void setVISIT_OBJ_ID(String visit_obj_id) {
				VISIT_OBJ_ID = visit_obj_id;
			}
			public String getVISIT_OBJ_NO() {
				return VISIT_OBJ_NO;
			}
			public void setVISIT_OBJ_NO(String visit_obj_no) {
				VISIT_OBJ_NO = visit_obj_no;
			}
			public String getVISIT_OBJ_NAME() {
				return VISIT_OBJ_NAME;
			}
			public void setVISIT_OBJ_NAME(String visit_obj_name) {
				VISIT_OBJ_NAME = visit_obj_name;
			}
			public String getPLAN_VISIT_NUM() {
				return PLAN_VISIT_NUM;
			}
			public void setPLAN_VISIT_NUM(String plan_visit_num) {
				PLAN_VISIT_NUM = plan_visit_num;
			}
}
