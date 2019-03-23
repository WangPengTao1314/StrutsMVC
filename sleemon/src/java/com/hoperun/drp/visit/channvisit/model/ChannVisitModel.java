package com.hoperun.drp.visit.channvisit.model;

import com.hoperun.commons.model.BaseModel;

public class ChannVisitModel extends BaseModel {
 
	    private static final long serialVersionUID = 1L;
	    
		/**流程ID**/
	    private   String  CHANN_VISIT_ID;
		/**流程编号**/
	    private   String  CHANN_VISIT_NO;
		/**标题**/
	    private   String  TITLE;
	    /**紧急程度**/
	    private   String  EME_DEGREE;
		/**申请人**/
	    private   String  APPLY_PERSON;
		/**申请部门**/
	    private   String  APPLY_DEP;
		/**申请日期**/
	    private   String  APPLY_DATE;
		/**拜访人**/
	    private   String  VISIT_PEOPLE;
		/**拜访日期**/
	    private   String  VISIT_DATE;
		/**拜访形式**/
	    private   String  VISIT_TYPE;
	    /**渠道ID**/
	    private   String  CHANN_ID;
	    /**渠道编号**/
	    private   String  CHANN_NO;
	    /**加盟商名称**/
	    private   String  CHANN_NAME;
		/**软床**/
	    private   String  BED_STOCK;
	    /**床垫**/
	    private   String  MATTRESS_STOCK;
		/**床头柜**/
	    private   String  BEDSIDE_STOCK;
		/**床品**/
	    private   String  BEDDING_STOCK;
		/**其他**/
	    private   String  OTHER_STOCK;
		/**总计库存**/
	    private   String  TOTAL_STOCK;
		/**销售本月目标**/
	    private   String  MONTH_ORDER_NUM;
		/**销售本月实际达成**/
	    private   String  MONTH_ORDER_REALITY_RATE;
		/**销售本月达成率**/
	    private   String  MONTH_ORDER_RATE;
		/**销售本季目标**/
	    private   String  SEASON_ORDER_NUM;
	    /**销售本季实际达成**/
	    private   String  SEASON_ORDER_REALITY_RATE;
		/**销售本季达成率**/
	    private   String  SEASON_ORDER_RATE;
		/**销售改善计划**/
	    private   String  SALES_IMP_PLAN;
		/**门店本月目标**/
	    private   String  STORE_MONTH_ORDER_NUM;
		/**门店本月实际达成**/
	    private   String  STORE_MONTH_ORDER_REALITY_RATE;
		/**门店本月达成率**/
	    private   String  STORE_MONTH_ORDER_RATE;
		/**门店本季目标**/
	    private   String  STORE_SEASON_ORDER_NUM;
		/**门店本季实际达成**/
	    private   String  STORE_SEASON_REALITY_RATE;
		/**门店本季达成率**/
	    private   String  STORE_SEASON_ORDER_RATE;
	    /**门店改善计划**/
	    private   String  STORE_SALES_IMP_PLAN;
	    /**季度目标**/
	    private   String  SEASON_GOALS;
	    /**目前达成**/
	    private   String  CURRENT_REALITY_RATE;
	    /**达成率**/
	    private   String  CURRENT_RATE;
	    /**季度改善计划**/
	    private   String  SEASON_IMPROVE_PLAN;
	    /**加盟商问题**/
	    private   String  CHANN_QUESTION;
	    /**主要行动**/
	    private   String  MAIN_ACTION;
	    /**竞品信息**/
	    private   String  COMPETITION_INFO;
	    /**支持需求**/
	    private   String  SUPPORT_DEMAND;
	    /**相关流程**/
	    private   String  PROCESS;
	    /**相关文档**/
	    private   String  DOCUMENTS;
	    /**流程描述**/
	    private   String  REMARK;
		/**状态**/ 
	    private   String  STATE;
	    /**图片路径**/
	    private String    PIC_PATH; 
	    /**拜访人ID**/
	    private String    VISIT_PEOPLE_ID;
		
	    public String getCHANN_VISIT_ID() {
			return CHANN_VISIT_ID;
		}
		public void setCHANN_VISIT_ID(String chann_visit_id) {
			CHANN_VISIT_ID = chann_visit_id;
		}
		public String getCHANN_VISIT_NO() {
			return CHANN_VISIT_NO;
		}
		public void setCHANN_VISIT_NO(String chann_visit_no) {
			CHANN_VISIT_NO = chann_visit_no;
		}
		public String getTITLE() {
			return TITLE;
		}
		public void setTITLE(String title) {
			TITLE = title;
		}
		public String getEME_DEGREE() {
			return EME_DEGREE;
		}
		public void setEME_DEGREE(String eme_degree) {
			EME_DEGREE = eme_degree;
		}
		public String getAPPLY_PERSON() {
			return APPLY_PERSON;
		}
		public void setAPPLY_PERSON(String apply_person) {
			APPLY_PERSON = apply_person;
		}
		public String getAPPLY_DEP() {
			return APPLY_DEP;
		}
		public void setAPPLY_DEP(String apply_dep) {
			APPLY_DEP = apply_dep;
		}
		public String getAPPLY_DATE() {
			return APPLY_DATE;
		}
		public void setAPPLY_DATE(String apply_date) {
			APPLY_DATE = apply_date;
		}
		public String getVISIT_PEOPLE() {
			return VISIT_PEOPLE;
		}
		public void setVISIT_PEOPLE(String visit_people) {
			VISIT_PEOPLE = visit_people;
		}
		public String getVISIT_DATE() {
			return VISIT_DATE;
		}
		public void setVISIT_DATE(String visit_date) {
			VISIT_DATE = visit_date;
		}
		public String getVISIT_TYPE() {
			return VISIT_TYPE;
		}
		public void setVISIT_TYPE(String visit_type) {
			VISIT_TYPE = visit_type;
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
		public String getBED_STOCK() {
			return BED_STOCK;
		}
		public void setBED_STOCK(String bed_stock) {
			BED_STOCK = bed_stock;
		}
		public String getMATTRESS_STOCK() {
			return MATTRESS_STOCK;
		}
		public void setMATTRESS_STOCK(String mattress_stock) {
			MATTRESS_STOCK = mattress_stock;
		}
		public String getBEDSIDE_STOCK() {
			return BEDSIDE_STOCK;
		}
		public void setBEDSIDE_STOCK(String bedside_stock) {
			BEDSIDE_STOCK = bedside_stock;
		}
		public String getBEDDING_STOCK() {
			return BEDDING_STOCK;
		}
		public void setBEDDING_STOCK(String bedding_stock) {
			BEDDING_STOCK = bedding_stock;
		}
		public String getOTHER_STOCK() {
			return OTHER_STOCK;
		}
		public void setOTHER_STOCK(String other_stock) {
			OTHER_STOCK = other_stock;
		}
		public String getTOTAL_STOCK() {
			return TOTAL_STOCK;
		}
		public void setTOTAL_STOCK(String total_stock) {
			TOTAL_STOCK = total_stock;
		}
		public String getMONTH_ORDER_NUM() {
			return MONTH_ORDER_NUM;
		}
		public void setMONTH_ORDER_NUM(String month_order_num) {
			MONTH_ORDER_NUM = month_order_num;
		}
		public String getMONTH_ORDER_REALITY_RATE() {
			return MONTH_ORDER_REALITY_RATE;
		}
		public void setMONTH_ORDER_REALITY_RATE(String month_order_reality_rate) {
			MONTH_ORDER_REALITY_RATE = month_order_reality_rate;
		}
		public String getMONTH_ORDER_RATE() {
			return MONTH_ORDER_RATE;
		}
		public void setMONTH_ORDER_RATE(String month_order_rate) {
			MONTH_ORDER_RATE = month_order_rate;
		}
		public String getSEASON_ORDER_NUM() {
			return SEASON_ORDER_NUM;
		}
		public void setSEASON_ORDER_NUM(String season_order_num) {
			SEASON_ORDER_NUM = season_order_num;
		}
		public String getSEASON_ORDER_REALITY_RATE() {
			return SEASON_ORDER_REALITY_RATE;
		}
		public void setSEASON_ORDER_REALITY_RATE(String season_order_reality_rate) {
			SEASON_ORDER_REALITY_RATE = season_order_reality_rate;
		}
		public String getSEASON_ORDER_RATE() {
			return SEASON_ORDER_RATE;
		}
		public void setSEASON_ORDER_RATE(String season_order_rate) {
			SEASON_ORDER_RATE = season_order_rate;
		}
		public String getSALES_IMP_PLAN() {
			return SALES_IMP_PLAN;
		}
		public void setSALES_IMP_PLAN(String sales_imp_plan) {
			SALES_IMP_PLAN = sales_imp_plan;
		}
		public String getSTORE_MONTH_ORDER_NUM() {
			return STORE_MONTH_ORDER_NUM;
		}
		public void setSTORE_MONTH_ORDER_NUM(String store_month_order_num) {
			STORE_MONTH_ORDER_NUM = store_month_order_num;
		}
		public String getSTORE_MONTH_ORDER_REALITY_RATE() {
			return STORE_MONTH_ORDER_REALITY_RATE;
		}
		public void setSTORE_MONTH_ORDER_REALITY_RATE(
				String store_month_order_reality_rate) {
			STORE_MONTH_ORDER_REALITY_RATE = store_month_order_reality_rate;
		}
		public String getSTORE_MONTH_ORDER_RATE() {
			return STORE_MONTH_ORDER_RATE;
		}
		public void setSTORE_MONTH_ORDER_RATE(String store_month_order_rate) {
			STORE_MONTH_ORDER_RATE = store_month_order_rate;
		}
		public String getSTORE_SEASON_ORDER_NUM() {
			return STORE_SEASON_ORDER_NUM;
		}
		public void setSTORE_SEASON_ORDER_NUM(String store_season_order_num) {
			STORE_SEASON_ORDER_NUM = store_season_order_num;
		}
		public String getSTORE_SEASON_REALITY_RATE() {
			return STORE_SEASON_REALITY_RATE;
		}
		public void setSTORE_SEASON_REALITY_RATE(String store_season_reality_rate) {
			STORE_SEASON_REALITY_RATE = store_season_reality_rate;
		}
		public String getSTORE_SEASON_ORDER_RATE() {
			return STORE_SEASON_ORDER_RATE;
		}
		public void setSTORE_SEASON_ORDER_RATE(String store_season_order_rate) {
			STORE_SEASON_ORDER_RATE = store_season_order_rate;
		}
		public String getSTORE_SALES_IMP_PLAN() {
			return STORE_SALES_IMP_PLAN;
		}
		public void setSTORE_SALES_IMP_PLAN(String store_sales_imp_plan) {
			STORE_SALES_IMP_PLAN = store_sales_imp_plan;
		}
		public String getSEASON_GOALS() {
			return SEASON_GOALS;
		}
		public void setSEASON_GOALS(String season_goals) {
			SEASON_GOALS = season_goals;
		}
		public String getCURRENT_REALITY_RATE() {
			return CURRENT_REALITY_RATE;
		}
		public void setCURRENT_REALITY_RATE(String current_reality_rate) {
			CURRENT_REALITY_RATE = current_reality_rate;
		}
		public String getCURRENT_RATE() {
			return CURRENT_RATE;
		}
		public void setCURRENT_RATE(String current_rate) {
			CURRENT_RATE = current_rate;
		}
		public String getSEASON_IMPROVE_PLAN() {
			return SEASON_IMPROVE_PLAN;
		}
		public void setSEASON_IMPROVE_PLAN(String season_improve_plan) {
			SEASON_IMPROVE_PLAN = season_improve_plan;
		}
		public String getCHANN_QUESTION() {
			return CHANN_QUESTION;
		}
		public void setCHANN_QUESTION(String chann_question) {
			CHANN_QUESTION = chann_question;
		}
		public String getMAIN_ACTION() {
			return MAIN_ACTION;
		}
		public void setMAIN_ACTION(String main_action) {
			MAIN_ACTION = main_action;
		}
		public String getCOMPETITION_INFO() {
			return COMPETITION_INFO;
		}
		public void setCOMPETITION_INFO(String competition_info) {
			COMPETITION_INFO = competition_info;
		}
		public String getSUPPORT_DEMAND() {
			return SUPPORT_DEMAND;
		}
		public void setSUPPORT_DEMAND(String support_demand) {
			SUPPORT_DEMAND = support_demand;
		}
		public String getPROCESS() {
			return PROCESS;
		}
		public void setPROCESS(String process) {
			PROCESS = process;
		}
		public String getDOCUMENTS() {
			return DOCUMENTS;
		}
		public void setDOCUMENTS(String documents) {
			DOCUMENTS = documents;
		}
		public String getREMARK() {
			return REMARK;
		}
		public void setREMARK(String remark) {
			REMARK = remark;
		}
		public String getSTATE() {
			return STATE;
		}
		public void setSTATE(String state) {
			STATE = state;
		}
		public String getPIC_PATH() {
			return PIC_PATH;
		}
		public void setPIC_PATH(String pic_path) {
			PIC_PATH = pic_path;
		}
		public String getVISIT_PEOPLE_ID() {
			return VISIT_PEOPLE_ID;
		}
		public void setVISIT_PEOPLE_ID(String visit_people_id) {
			VISIT_PEOPLE_ID = visit_people_id;
		}
	
}
