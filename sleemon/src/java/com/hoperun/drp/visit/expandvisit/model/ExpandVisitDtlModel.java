package com.hoperun.drp.visit.expandvisit.model;

import com.hoperun.commons.model.BaseModel;

public class ExpandVisitDtlModel extends BaseModel {
   
	    private static final long serialVersionUID = 1L;
	    /**拓展拜访明细ID**/
	    private  String  EXPAND_VISIT_DTL_ID;
	    /**拓展拜访ID**/
	    private  String  EXPAND_VISIT_ID;
	    /**商场名称**/
	    private  String  STORE_NAME;
	    /**软体层次**/
	    private  String  SOFTWARE_LAYER;
	    /**定位**/
	    private  String  LOCATION;
	    /**知名度**/
        private  String  POPULARITY;		 
	    /**开业时间**/
	    private  String  OPENNING_TIME;
	    /**招商时间**/
	    private  String  INVESTMENT_TIME;
	    /**其他信息**/
	    private  String  OTHER_INFO;
	    /**商场经理姓名**/
	    private  String  STORE_MNG_NAME;
	    /**商场经理联系方式**/
	    private  String  STORE_MNG_TEL;
	    /**商场经理联系邮箱**/
	    private  String  STORE_MNG_EMAIL;
	    /**客户姓名**/
	    private  String  CUST_NAME;
	    /**客户现状**/
	    private  String  CUST_STATE;
	    /**客户资金**/
	    private  String  CUST_FUNDS;
	    /**经营理念**/
	    private  String  BUSINESS_PHI;
	    /**客户其他信息**/
	    private  String  CUST_OTHER_INFO;
	    /**客户意向**/
	    private  String  CUST_INTENTION;
	    /**跟进方式**/
	    private  String  FOLLOW_WAY;
		/**删除标记**/
	    private  String  DEL_FLAG;
	    /**招商经理姓名**/
	    private  String  INVESTMENT_MNG_NAME;
	    /**招商经理联系方式**/
	    private  String  INVESTMENT_MNG_TEL;
	    /**招商经理联系邮箱**/
	    private  String  INVESTMENT_MNG_EMAIL;
	   
		public String getINVESTMENT_MNG_NAME() {
			return INVESTMENT_MNG_NAME;
		}
		public void setINVESTMENT_MNG_NAME(String investment_mng_name) {
			INVESTMENT_MNG_NAME = investment_mng_name;
		}
		public String getINVESTMENT_MNG_TEL() {
			return INVESTMENT_MNG_TEL;
		}
		public void setINVESTMENT_MNG_TEL(String investment_mng_tel) {
			INVESTMENT_MNG_TEL = investment_mng_tel;
		}
		public String getINVESTMENT_MNG_EMAIL() {
			return INVESTMENT_MNG_EMAIL;
		}
		public void setINVESTMENT_MNG_EMAIL(String investment_mng_email) {
			INVESTMENT_MNG_EMAIL = investment_mng_email;
		}
		public String getEXPAND_VISIT_DTL_ID() {
			return EXPAND_VISIT_DTL_ID;
		}
		public void setEXPAND_VISIT_DTL_ID(String expand_visit_dtl_id) {
			EXPAND_VISIT_DTL_ID = expand_visit_dtl_id;
		}
		public String getEXPAND_VISIT_ID() {
			return EXPAND_VISIT_ID;
		}
		public void setEXPAND_VISIT_ID(String expand_visit_id) {
			EXPAND_VISIT_ID = expand_visit_id;
		}
		public String getSTORE_NAME() {
			return STORE_NAME;
		}
		public void setSTORE_NAME(String store_name) {
			STORE_NAME = store_name;
		}
		public String getSOFTWARE_LAYER() {
			return SOFTWARE_LAYER;
		}
		public void setSOFTWARE_LAYER(String software_layer) {
			SOFTWARE_LAYER = software_layer;
		}
		public String getLOCATION() {
			return LOCATION;
		}
		public void setLOCATION(String location) {
			LOCATION = location;
		}
		public String getPOPULARITY() {
			return POPULARITY;
		}
		public void setPOPULARITY(String popularity) {
			POPULARITY = popularity;
		}
		public String getOPENNING_TIME() {
			return OPENNING_TIME;
		}
		public void setOPENNING_TIME(String openning_time) {
			OPENNING_TIME = openning_time;
		}
		public String getINVESTMENT_TIME() {
			return INVESTMENT_TIME;
		}
		public void setINVESTMENT_TIME(String investment_time) {
			INVESTMENT_TIME = investment_time;
		}
		public String getOTHER_INFO() {
			return OTHER_INFO;
		}
		public void setOTHER_INFO(String other_info) {
			OTHER_INFO = other_info;
		}
		public String getSTORE_MNG_NAME() {
			return STORE_MNG_NAME;
		}
		public void setSTORE_MNG_NAME(String store_mng_name) {
			STORE_MNG_NAME = store_mng_name;
		}
		public String getSTORE_MNG_TEL() {
			return STORE_MNG_TEL;
		}
		public void setSTORE_MNG_TEL(String store_mng_tel) {
			STORE_MNG_TEL = store_mng_tel;
		}
		public String getSTORE_MNG_EMAIL() {
			return STORE_MNG_EMAIL;
		}
		public void setSTORE_MNG_EMAIL(String store_mng_email) {
			STORE_MNG_EMAIL = store_mng_email;
		}
		public String getCUST_NAME() {
			return CUST_NAME;
		}
		public void setCUST_NAME(String cust_name) {
			CUST_NAME = cust_name;
		}
		public String getCUST_STATE() {
			return CUST_STATE;
		}
		public void setCUST_STATE(String cust_state) {
			CUST_STATE = cust_state;
		}
		public String getCUST_FUNDS() {
			return CUST_FUNDS;
		}
		public void setCUST_FUNDS(String cust_funds) {
			CUST_FUNDS = cust_funds;
		}
		public String getBUSINESS_PHI() {
			return BUSINESS_PHI;
		}
		public void setBUSINESS_PHI(String business_phi) {
			BUSINESS_PHI = business_phi;
		}
		public String getCUST_OTHER_INFO() {
			return CUST_OTHER_INFO;
		}
		public void setCUST_OTHER_INFO(String cust_other_info) {
			CUST_OTHER_INFO = cust_other_info;
		}
		public String getCUST_INTENTION() {
			return CUST_INTENTION;
		}
		public void setCUST_INTENTION(String cust_intention) {
			CUST_INTENTION = cust_intention;
		}
		public String getFOLLOW_WAY() {
			return FOLLOW_WAY;
		}
		public void setFOLLOW_WAY(String follow_way) {
			FOLLOW_WAY = follow_way;
		}
		public String getDEL_FLAG() {
			return DEL_FLAG;
		}
		public void setDEL_FLAG(String del_flag) {
			DEL_FLAG = del_flag;
		}
	}
