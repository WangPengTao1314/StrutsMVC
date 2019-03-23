package com.hoperun.drp.visit.channvisit.model;

import com.hoperun.commons.model.BaseModel;

public class ChannVisitDtlModel extends BaseModel  {

	   private static final long serialVersionUID = 1L;
	   
	   /**加盟商拜访明细ID**/
	   private   String  CHANN_VISIT_DTL_ID;
	   /**加盟商拜访ID**/
	   private   String  CHANN_VISIT_ID;
	   /**拜访日期**/
	   private   String  VISIT_DATE;
	   /**项目名称**/
	   private   String  PRO_NAME;
	   /**主类别**/
	   private   String  MAIN_TYPE;
	   /**副类别**/
	   private   String  SUB_TYPE;
	   /**目前情况分析**/
	   private   String  SIT_ANALYSIS;
	   /**行动方案**/
	   private   String  ACTION_PLAN;
	   /**预计完成时间**/
	   private   String  COMPLETE_TIME;
	   /**其他**/
	   private   String  OTHER_INFO;
	   
	   
	   public String getCHANN_VISIT_DTL_ID() {
			return CHANN_VISIT_DTL_ID;
		}
	   public void setCHANN_VISIT_DTL_ID(String chann_visit_dtl_id) {
			CHANN_VISIT_DTL_ID = chann_visit_dtl_id;
		}
	   public String getCHANN_VISIT_ID() {
			return CHANN_VISIT_ID;
		}
	   public void setCHANN_VISIT_ID(String chann_visit_id) {
			CHANN_VISIT_ID = chann_visit_id;
		}
	   public String getVISIT_DATE() {
			return VISIT_DATE;
		}
	   public void setVISIT_DATE(String visit_date) {
			VISIT_DATE = visit_date;
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
	   public String getSUB_TYPE() {
			return SUB_TYPE;
		}
	   public void setSUB_TYPE(String sub_type) {
			SUB_TYPE = sub_type;
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
