package com.hoperun.drp.oamg.advreit.model;

import com.hoperun.commons.model.BaseModel;

public class AdvreitModel extends BaseModel {
     
		private static final long serialVersionUID = 1L;
		  
		/**广告投放报销申请单ID**/
		private   String  ERP_ADV_REIT_ID;
		/**广告投放报销申请单号**/
		private   String  ERP_ADV_REIT_NO;
		/**广告投放申请单ID**/
		private   String  ERP_ADV_REQ_ID;
		/**广告投放申请单号**/
		private   String  ERP_ADV_REQ_NO;
		/**渠道ID**/
		private   String  CHANN_ID;
		/**渠道编号**/
		private   String  CHANN_NO;
		/**渠道名称**/
		private   String  CHANN_NAME;
		/**区域ID**/
		private   String  AREA_ID;
		/**区域编号**/
		private   String  AREA_NO;
		/**区域名称**/
		private   String  AREA_NAME;
		/**区域经理ID**/
		private   String  AREA_MANAGE_ID;
		/**渠道排名**/
		private   String  CHANN_RANK;
		/**广告公司名称**/
		private   String  ADV_CO_NAME;
		/**广告公司联系人**/
		private   String  ADV_CO_CONTACT;
		/**广告公司联系电话**/
		private   String  ADV_CO_TEL;
		/**广告投放内容**/
		private   String  ADV_CONTENT;
		/**广告投放地点**/
		private   String  ADV_ADDR;
		/**广告投放开始时间**/
		private   String  ADV_START_DATE;
		/**广告投放结束时间**/
	    private   String  ADV_END_DATE;
		/**广告投放总预算金额**/
		private   String  ADV_TOTAL_AMOUNT;
		/**已报销金额**/
	    private   String  HAS_REIM_AMOUNT;
		/**状态**/
		private   String  STATE;
		/**区域经理名称**/
		private   String  AREA_MANAGE_NAME;
		/**申请报销原因**/
		private   String  REIT_REASON;
		/**申请报销金额**/
		private   String  REIT_AMOUNT;
		/**申请人**/
	    private   String  RNVTN_REQ_NAME;
		/**申请时间**/
	    private   String  RNVTN_REQ_DATE;
	    /**投放申请单附件**/
	    private   String  REQPATH;
	    /**申请报销附件**/
	    private   String  REQREITPATH;
	    
		public String getREQPATH() {
			return REQPATH;
		}
		public void setREQPATH(String reqpath) {
			REQPATH = reqpath;
		}
		public String getREQREITPATH() {
			return REQREITPATH;
		}
		public void setREQREITPATH(String reqreitpath) {
			REQREITPATH = reqreitpath;
		}
		public String getERP_ADV_REIT_ID() {
			return ERP_ADV_REIT_ID;
		}
		public void setERP_ADV_REIT_ID(String erp_adv_reit_id) {
			ERP_ADV_REIT_ID = erp_adv_reit_id;
		}
		public String getERP_ADV_REIT_NO() {
			return ERP_ADV_REIT_NO;
		}
		public void setERP_ADV_REIT_NO(String erp_adv_reit_no) {
			ERP_ADV_REIT_NO = erp_adv_reit_no;
		}
		public String getERP_ADV_REQ_ID() {
			return ERP_ADV_REQ_ID;
		}
		public void setERP_ADV_REQ_ID(String erp_adv_req_id) {
			ERP_ADV_REQ_ID = erp_adv_req_id;
		}
		public String getERP_ADV_REQ_NO() {
			return ERP_ADV_REQ_NO;
		}
		public void setERP_ADV_REQ_NO(String erp_adv_req_no) {
			ERP_ADV_REQ_NO = erp_adv_req_no;
		}
		public String getCHANN_ID() {
			return CHANN_ID;
		}
		public void setCHANN_ID(String chann_id) {
			CHANN_ID = chann_id;
		}
		public String getCHANN_NO() {
			return CHANN_NO;
		}
		public void setCHANN_NO(String chann_no) {
			CHANN_NO = chann_no;
		}
		public String getCHANN_NAME() {
			return CHANN_NAME;
		}
		public void setCHANN_NAME(String chann_name) {
			CHANN_NAME = chann_name;
		}
		public String getAREA_ID() {
			return AREA_ID;
		}
		public void setAREA_ID(String area_id) {
			AREA_ID = area_id;
		}
		public String getAREA_NO() {
			return AREA_NO;
		}
		public void setAREA_NO(String area_no) {
			AREA_NO = area_no;
		}
		public String getAREA_NAME() {
			return AREA_NAME;
		}
		public void setAREA_NAME(String area_name) {
			AREA_NAME = area_name;
		}
		public String getAREA_MANAGE_ID() {
			return AREA_MANAGE_ID;
		}
		public void setAREA_MANAGE_ID(String area_manage_id) {
			AREA_MANAGE_ID = area_manage_id;
		}
		public String getCHANN_RANK() {
			return CHANN_RANK;
		}
		public void setCHANN_RANK(String chann_rank) {
			CHANN_RANK = chann_rank;
		}
		public String getADV_CO_NAME() {
			return ADV_CO_NAME;
		}
		public void setADV_CO_NAME(String adv_co_name) {
			ADV_CO_NAME = adv_co_name;
		}
		public String getADV_CO_CONTACT() {
			return ADV_CO_CONTACT;
		}
		public void setADV_CO_CONTACT(String adv_co_contact) {
			ADV_CO_CONTACT = adv_co_contact;
		}
		public String getADV_CO_TEL() {
			return ADV_CO_TEL;
		}
		public void setADV_CO_TEL(String adv_co_tel) {
			ADV_CO_TEL = adv_co_tel;
		}
		public String getADV_CONTENT() {
			return ADV_CONTENT;
		}
		public void setADV_CONTENT(String adv_content) {
			ADV_CONTENT = adv_content;
		}
		public String getADV_ADDR() {
			return ADV_ADDR;
		}
		public void setADV_ADDR(String adv_addr) {
			ADV_ADDR = adv_addr;
		}
		public String getADV_START_DATE() {
			return ADV_START_DATE;
		}
		public void setADV_START_DATE(String adv_start_date) {
			ADV_START_DATE = adv_start_date;
		}
		public String getADV_END_DATE() {
			return ADV_END_DATE;
		}
		public void setADV_END_DATE(String adv_end_date) {
			ADV_END_DATE = adv_end_date;
		}
		public String getADV_TOTAL_AMOUNT() {
			return ADV_TOTAL_AMOUNT;
		}
		public void setADV_TOTAL_AMOUNT(String adv_total_amount) {
			ADV_TOTAL_AMOUNT = adv_total_amount;
		}
		public String getHAS_REIM_AMOUNT() {
			return HAS_REIM_AMOUNT;
		}
		public void setHAS_REIM_AMOUNT(String has_reim_amount) {
			HAS_REIM_AMOUNT = has_reim_amount;
		}
		public String getSTATE() {
			return STATE;
		}
		public void setSTATE(String state) {
			STATE = state;
		}
		public String getAREA_MANAGE_NAME() {
			return AREA_MANAGE_NAME;
		}
		public void setAREA_MANAGE_NAME(String area_manage_name) {
			AREA_MANAGE_NAME = area_manage_name;
		}
		public String getREIT_REASON() {
			return REIT_REASON;
		}
		public void setREIT_REASON(String reit_reason) {
			REIT_REASON = reit_reason;
		}
		public String getREIT_AMOUNT() {
			return REIT_AMOUNT;
		}
		public void setREIT_AMOUNT(String reit_amount) {
			REIT_AMOUNT = reit_amount;
		}
		public String getRNVTN_REQ_NAME() {
			return RNVTN_REQ_NAME;
		}
		public void setRNVTN_REQ_NAME(String rnvtn_req_name) {
			RNVTN_REQ_NAME = rnvtn_req_name;
		}
		public String getRNVTN_REQ_DATE() {
			return RNVTN_REQ_DATE;
		}
		public void setRNVTN_REQ_DATE(String rnvtn_req_date) {
			RNVTN_REQ_DATE = rnvtn_req_date;
		}
}
