package com.hoperun.drp.visit.storevisit.model;

import com.hoperun.commons.model.BaseModel;

public class StoreVisitModel extends BaseModel {
  
	 private static final long serialVersionUID = 1L;
	 
	 private   String  VISIT_TYPE;
	 public String getVISIT_TYPE() {
		return VISIT_TYPE;
	}
	public void setVISIT_TYPE(String vISITTYPE) {
		VISIT_TYPE = vISITTYPE;
	}
	/**流程ID**/
	 private   String  STORE_VISIT_ID;
	 /**流程编号**/
	 private   String  STORE_VISIT_NO;
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
	 /**市**/
	 private   String  CITY;
	 /**加盟商名称**/ 
	 private   String  CHANN_NAME; 
	 /**门店名称**/
	 private   String  STORE_NAME;
	 /**店招**/
	 private   String  STORE_STROKES;
	 /**店内灯箱**/
	 private   String  LIGHT_BOX;
	 /**地面**/
	 private   String  GROUND;
	 /**产品**/
	 private   String  PRODUCTS;
	 /**道具**/
	 private   String  PROPERTIES;
	 /**软饰**/
	 private   String  SOFT_DECORATION;
	 /**块毯**/
	 private   String  BLANKETS;
	 /**电视**/
	 private   String  TELEVISION;
	 /**灯光**/
	 private   String  LIGHT_LAMP;
	 /**物料**/
	 private   String  MATERIALS;
	 /**饮水机**/
	 private   String  WATER_MACHINE;
	 /**导购形象**/
	 private   String  FIGURE  ;
	 /**导购态度**/
	 private   String  MANNER;
	 /**导购技能**/ 
	 private   String  TECHNICAL;
	 /**执行活动主题**/
	 private   String  EXECUTE_ACTION_TOPIC;
	 /**执行活动时间**/
	 private   String  EXECUTE_ACTION_DATE;
	 /**执行活动地点**/ 
	 private   String  EXECUTE_ACTION_ADDR;
	 /**活动目标**/
	 private   String  ACTION_PLAN;
	 /**实际达成**/
	 private   String  ACTION_REALITY_RATE;
	 /**达成率**/
	 private   String  ACTION_RATE;
	 /**活动总结亮点**/
	 private   String  ACTION_RIGHT;
	 /**活动总结不足**/
	 private   String  ACTION_BAD;
	 /**本月计划**/
	 private   String  MONTH_ORDER_NUM;
	 /**实际达成**/ 
	 private   String  MONTH_REALITY_RATE;
	 /**达成率**/
	 private   String  MONTH_RATE;
	 /**本季目标**/
	 private   String  SEASON_ORDER_NUM;
	 /**实际达成**/
	 private   String  SEASON_REALITY_RATE;
	 /**达成率**/
	 private   String  SEASON_RATE;
	 /**月度评效**/
	 private   String  EVALUATION_MONTH;
	 /**季度评效**/
	 private   String  EVALUATION_SEASON;
	 /**活动计划主题**/
	 private   String  ACTION_PLAN_TOPIC;
	 /**活动计划时间**/
	 private   String  ACTION_PLAN_DATE;
	 /**活动计划地点**/ 
	 private   String  ACTION_PLAN_ADDR;
	 /**预计达成目标**/
	 private   String  EXPECTED_GOAL;
	 /**竞品信息**/
	 private   String  COMPETITION_INFO;
	 /**支持需求**/
	 private   String  SUPPORT_DEMAND;
	 /**相关流程**/
	 private   String  PROCESS;
	 /**流程描述**/
	 private   String  REMARK;
	 /**渠道ID**/
	 private   String  CHANN_ID;
	 /**渠道编号**/
	 private   String  CHANN_NO;
     /**图片路径**/
     private String    PIC_PATH; 
	 /**终端ID**/
     private String    TERM_ID;
     /**终端编号**/
     private String    TERM_NO;
     
     private String    ACTUAL_INVESTMENT;
     
     private String    FORECAST_INVESTMENT;
     /**拜访人ID**/
	 private String    VISIT_PEOPLE_ID;
	 
	public String getACTUAL_INVESTMENT() {
		return ACTUAL_INVESTMENT;
	}
	public void setACTUAL_INVESTMENT(String aCTUALINVESTMENT) {
		ACTUAL_INVESTMENT = aCTUALINVESTMENT;
	}
	public String getFORECAST_INVESTMENT() {
		return FORECAST_INVESTMENT;
	}
	public void setFORECAST_INVESTMENT(String fORECASTINVESTMENT) {
		FORECAST_INVESTMENT = fORECASTINVESTMENT;
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
	public String getSTORE_VISIT_ID() {
		return STORE_VISIT_ID;
	}
	public void setSTORE_VISIT_ID(String store_visit_id) {
		STORE_VISIT_ID = store_visit_id;
	}
	public String getSTORE_VISIT_NO() {
		return STORE_VISIT_NO;
	}
	public void setSTORE_VISIT_NO(String store_visit_no) {
		STORE_VISIT_NO = store_visit_no;
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
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String city) {
		CITY = city;
	}
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	public void setSTORE_NAME(String store_name) {
		STORE_NAME = store_name;
	}
	public String getSTORE_STROKES() {
		return STORE_STROKES;
	}
	public void setSTORE_STROKES(String store_strokes) {
		STORE_STROKES = store_strokes;
	}
	public String getLIGHT_BOX() {
		return LIGHT_BOX;
	}
	public void setLIGHT_BOX(String light_box) {
		LIGHT_BOX = light_box;
	}
	public String getGROUND() {
		return GROUND;
	}
	public void setGROUND(String ground) {
		GROUND = ground;
	}
	public String getPRODUCTS() {
		return PRODUCTS;
	}
	public void setPRODUCTS(String products) {
		PRODUCTS = products;
	}
	public String getPROPERTIES() {
		return PROPERTIES;
	}
	public void setPROPERTIES(String properties) {
		PROPERTIES = properties;
	}
	public String getSOFT_DECORATION() {
		return SOFT_DECORATION;
	}
	public void setSOFT_DECORATION(String soft_decoration) {
		SOFT_DECORATION = soft_decoration;
	}
	public String getBLANKETS() {
		return BLANKETS;
	}
	public void setBLANKETS(String blankets) {
		BLANKETS = blankets;
	}
	public String getTELEVISION() {
		return TELEVISION;
	}
	public void setTELEVISION(String television) {
		TELEVISION = television;
	}
	public String getLIGHT_LAMP() {
		return LIGHT_LAMP;
	}
	public void setLIGHT_LAMP(String light_lamp) {
		LIGHT_LAMP = light_lamp;
	}
	public String getMATERIALS() {
		return MATERIALS;
	}
	public void setMATERIALS(String materials) {
		MATERIALS = materials;
	}
	public String getWATER_MACHINE() {
		return WATER_MACHINE;
	}
	public void setWATER_MACHINE(String water_machine) {
		WATER_MACHINE = water_machine;
	}
	public String getFIGURE() {
		return FIGURE;
	}
	public void setFIGURE(String figure) {
		FIGURE = figure;
	}
	public String getMANNER() {
		return MANNER;
	}
	public void setMANNER(String manner) {
		MANNER = manner;
	}
	public String getTECHNICAL() {
		return TECHNICAL;
	}
	public void setTECHNICAL(String technical) {
		TECHNICAL = technical;
	}
	public String getEXECUTE_ACTION_TOPIC() {
		return EXECUTE_ACTION_TOPIC;
	}
	public void setEXECUTE_ACTION_TOPIC(String execute_action_topic) {
		EXECUTE_ACTION_TOPIC = execute_action_topic;
	}
	public String getEXECUTE_ACTION_DATE() {
		return EXECUTE_ACTION_DATE;
	}
	public void setEXECUTE_ACTION_DATE(String execute_action_date) {
		EXECUTE_ACTION_DATE = execute_action_date;
	}
	public String getEXECUTE_ACTION_ADDR() {
		return EXECUTE_ACTION_ADDR;
	}
	public void setEXECUTE_ACTION_ADDR(String execute_action_addr) {
		EXECUTE_ACTION_ADDR = execute_action_addr;
	}
	public String getACTION_PLAN() {
		return ACTION_PLAN;
	}
	public void setACTION_PLAN(String action_plan) {
		ACTION_PLAN = action_plan;
	}
	public String getACTION_REALITY_RATE() {
		return ACTION_REALITY_RATE;
	}
	public void setACTION_REALITY_RATE(String action_reality_rate) {
		ACTION_REALITY_RATE = action_reality_rate;
	}
	public String getACTION_RATE() {
		return ACTION_RATE;
	}
	public void setACTION_RATE(String action_rate) {
		ACTION_RATE = action_rate;
	}
	public String getACTION_RIGHT() {
		return ACTION_RIGHT;
	}
	public void setACTION_RIGHT(String action_right) {
		ACTION_RIGHT = action_right;
	}
	public String getACTION_BAD() {
		return ACTION_BAD;
	}
	public void setACTION_BAD(String action_bad) {
		ACTION_BAD = action_bad;
	}
	public String getMONTH_ORDER_NUM() {
		return MONTH_ORDER_NUM;
	}
	public void setMONTH_ORDER_NUM(String month_order_num) {
		MONTH_ORDER_NUM = month_order_num;
	}
	public String getMONTH_REALITY_RATE() {
		return MONTH_REALITY_RATE;
	}
	public void setMONTH_REALITY_RATE(String month_reality_rate) {
		MONTH_REALITY_RATE = month_reality_rate;
	}
	public String getMONTH_RATE() {
		return MONTH_RATE;
	}
	public void setMONTH_RATE(String month_rate) {
		MONTH_RATE = month_rate;
	}
	public String getSEASON_ORDER_NUM() {
		return SEASON_ORDER_NUM;
	}
	public void setSEASON_ORDER_NUM(String season_order_num) {
		SEASON_ORDER_NUM = season_order_num;
	}
	public String getSEASON_REALITY_RATE() {
		return SEASON_REALITY_RATE;
	}
	public void setSEASON_REALITY_RATE(String season_reality_rate) {
		SEASON_REALITY_RATE = season_reality_rate;
	}
	public String getSEASON_RATE() {
		return SEASON_RATE;
	}
	public void setSEASON_RATE(String season_rate) {
		SEASON_RATE = season_rate;
	}
	public String getEVALUATION_MONTH() {
		return EVALUATION_MONTH;
	}
	public void setEVALUATION_MONTH(String evaluation_month) {
		EVALUATION_MONTH = evaluation_month;
	}
	public String getEVALUATION_SEASON() {
		return EVALUATION_SEASON;
	}
	public void setEVALUATION_SEASON(String evaluation_season) {
		EVALUATION_SEASON = evaluation_season;
	}
	public String getACTION_PLAN_TOPIC() {
		return ACTION_PLAN_TOPIC;
	}
	public void setACTION_PLAN_TOPIC(String action_plan_topic) {
		ACTION_PLAN_TOPIC = action_plan_topic;
	}
	public String getACTION_PLAN_DATE() {
		return ACTION_PLAN_DATE;
	}
	public void setACTION_PLAN_DATE(String action_plan_date) {
		ACTION_PLAN_DATE = action_plan_date;
	}
	public String getACTION_PLAN_ADDR() {
		return ACTION_PLAN_ADDR;
	}
	public void setACTION_PLAN_ADDR(String action_plan_addr) {
		ACTION_PLAN_ADDR = action_plan_addr;
	}
	public String getEXPECTED_GOAL() {
		return EXPECTED_GOAL;
	}
	public void setEXPECTED_GOAL(String expected_goal) {
		EXPECTED_GOAL = expected_goal;
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
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
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
