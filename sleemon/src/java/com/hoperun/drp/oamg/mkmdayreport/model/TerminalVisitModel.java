package com.hoperun.drp.oamg.mkmdayreport.model;

import com.hoperun.commons.model.BaseModel;

public class TerminalVisitModel    extends BaseModel  {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**门店拜访表ID**/
	 private  String  TERMINAL_VISIT_ID;
	 /**营销经理日报表ID**/
	 private  String  MKM_DAY_RPT_ID;
	 /**终端信息ID**/
	 private  String  TERM_ID;
	 /**终端编号**/
	 private  String  TERM_NO;
	 /**终端名称**/
	 private  String  TERM_NAME;
	 /**渠道ID**/
	 private  String  CHANN_ID_T;
	 /**渠道编号**/
	 private  String  CHANN_NO_T;
	 /**渠道名称**/
	 private  String  CHANN_NAME_T;
	 /**硬装**/
	 private  String  HARD_DECORATE;
	 /**软装**/
	 private  String  SOFT_DECORATE;
     /**导购员**/
	 private  String  SHOPP_GUIDE;
	 /**主要问题**/
	 private  String  MAIN_PROBLEM_TERM;
	 /**解决方案**/
	 private  String  SOLUTION_TERM;
	public String getTERMINAL_VISIT_ID() {
		return TERMINAL_VISIT_ID;
	}
	public void setTERMINAL_VISIT_ID(String terminal_visit_id) {
		TERMINAL_VISIT_ID = terminal_visit_id;
	}
	public String getMKM_DAY_RPT_ID() {
		return MKM_DAY_RPT_ID;
	}
	public void setMKM_DAY_RPT_ID(String mkm_day_rpt_id) {
		MKM_DAY_RPT_ID = mkm_day_rpt_id;
	}
	public String getTERM_ID() {
		return TERM_ID;
	}
	public void setTERM_ID(String term_id) {
		TERM_ID = term_id;
	}
	public String getTERM_NO() {
		return TERM_NO;
	}
	public void setTERM_NO(String term_no) {
		TERM_NO = term_no;
	}
	public String getTERM_NAME() {
		return TERM_NAME;
	}
	public void setTERM_NAME(String term_name) {
		TERM_NAME = term_name;
	}
	public String getHARD_DECORATE() {
		return HARD_DECORATE;
	}
	public void setHARD_DECORATE(String hard_decorate) {
		HARD_DECORATE = hard_decorate;
	}
	public String getSOFT_DECORATE() {
		return SOFT_DECORATE;
	}
	public void setSOFT_DECORATE(String soft_decorate) {
		SOFT_DECORATE = soft_decorate;
	}
	public String getSHOPP_GUIDE() {
		return SHOPP_GUIDE;
	}
	public void setSHOPP_GUIDE(String shopp_guide) {
		SHOPP_GUIDE = shopp_guide;
	}
	public String getMAIN_PROBLEM_TERM() {
		return MAIN_PROBLEM_TERM;
	}
	public void setMAIN_PROBLEM_TERM(String main_problem_term) {
		MAIN_PROBLEM_TERM = main_problem_term;
	}
	public String getSOLUTION_TERM() {
		return SOLUTION_TERM;
	}
	public void setSOLUTION_TERM(String solution_term) {
		SOLUTION_TERM = solution_term;
	}
	public String getCHANN_ID_T() {
		return CHANN_ID_T;
	}
	public void setCHANN_ID_T(String chann_id_t) {
		CHANN_ID_T = chann_id_t;
	}
	public String getCHANN_NO_T() {
		return CHANN_NO_T;
	}
	public void setCHANN_NO_T(String chann_no_t) {
		CHANN_NO_T = chann_no_t;
	}
	public String getCHANN_NAME_T() {
		return CHANN_NAME_T;
	}
	public void setCHANN_NAME_T(String chann_name_t) {
		CHANN_NAME_T = chann_name_t;
	}
}
