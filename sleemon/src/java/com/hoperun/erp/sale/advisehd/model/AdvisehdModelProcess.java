package com.hoperun.erp.sale.advisehd.model;

public class AdvisehdModelProcess {
	
	   /** 投诉与建议处理跟踪表ID **/
	   private String DRP_CMPL_ADVS_TRACE_ID;
	   /** 投诉与建议ID**/
	   private String CMPL_ADVS_ID;
	   /** 处理人ID **/
	   private String DEAL_PSON_ID;
	   /** 处理人名称 **/
	   private String DEAL_PSON_NAME;
	   /** 处理时间 **/
	   private String DEAL_TIME;
	   /** 回馈信息 **/
	   private String FEEDBACK_INFO;
	   /** 状态 **/
	   private String STATE;
	public String getDRP_CMPL_ADVS_TRACE_ID() {
		return DRP_CMPL_ADVS_TRACE_ID;
	}
	public void setDRP_CMPL_ADVS_TRACE_ID(String drp_cmpl_advs_trace_id) {
		DRP_CMPL_ADVS_TRACE_ID = drp_cmpl_advs_trace_id;
	}
	public String getCMPL_ADVS_ID() {
		return CMPL_ADVS_ID;
	}
	public void setCMPL_ADVS_ID(String cmpl_advs_id) {
		CMPL_ADVS_ID = cmpl_advs_id;
	}
	public String getDEAL_PSON_ID() {
		return DEAL_PSON_ID;
	}
	public void setDEAL_PSON_ID(String deal_pson_id) {
		DEAL_PSON_ID = deal_pson_id;
	}
	public String getDEAL_PSON_NAME() {
		return DEAL_PSON_NAME;
	}
	public void setDEAL_PSON_NAME(String deal_pson_name) {
		DEAL_PSON_NAME = deal_pson_name;
	}
	public String getDEAL_TIME() {
		return DEAL_TIME;
	}
	public void setDEAL_TIME(String deal_time) {
		DEAL_TIME = deal_time;
	}
	public String getFEEDBACK_INFO() {
		return FEEDBACK_INFO;
	}
	public void setFEEDBACK_INFO(String feedback_info) {
		FEEDBACK_INFO = feedback_info;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
}
