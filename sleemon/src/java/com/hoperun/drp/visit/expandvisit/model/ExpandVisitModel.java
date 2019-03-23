package com.hoperun.drp.visit.expandvisit.model;

import com.hoperun.commons.model.BaseModel;

public class ExpandVisitModel extends BaseModel{
     
	private static final long serialVersionUID = 1L;
	/**流程ID**/
	 private   String  EXPAND_VISIT_ID;
	 /**流程编号**/
	 private   String  EXPAND_VISIT_NO;
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
	 /**现有卖场名称**/
	 private   String  EXIST_STORE_NAME;
	 /**现有卖场地址**/
	 private   String  EXIST_STORE_ADDR;
	 /**现有卖场面积**/
	 private   String  EXIST_STORE_AREA;
	 /**档次排名**/
	 private   String  EXIST_STORE_RANGE;
	 /**进驻主竞品**/
	 private   String  EXIST_STORE_COMPETITION;
	 /**近期新开卖场名称**/
	 private   String  NEW_STORE_NAME;
	 /**近期新开卖场地址**/
	 private   String  NEW_STORE_ADDR;
	 /**近期新开卖场面积**/
	 private   String  NEW_STORE_AREA;
	 /**近期新开卖场档次排名**/
	 private   String  NEW_STORE_RANGE;
	 /**开业时间**/
	 private   String  NEW_STORE_DATE;
	 /**客户姓名**/
	 private   String  CHANN_NAME;
	 /**经营品牌**/
	 private   String  BUSS_SCOPE;
	 /**进驻卖场**/
	 private   String  STORE_NAME;
	 /**意向门店**/
	 private   String  PURPOSE_STORE_NAME;
	 /**联系电话**/
	 private   String  TEL;
	 /**加盟商异议**/
	 private   String  CHANN_REMARK;
	 /**解决方案**/
	 private   String  SOLUTION;
	 /**竞品信息**/
	 private   String  COMPETITION_INFO;
	 /**支持需求**/
	 private   String  SUPPORT_DEMAND;
	 /**相关流程**/
	 private   String  PROCESS;
	 /**流程描述**/
	 private   String  REMARK;
	 
	 /**图片路径**/
	 private   String  PIC_PATH;
	 
	 private   String  VISIT_TYPE;
	 
	 private   String  ADVANTAGES;
	  /**拜访人ID**/
	 private String    VISIT_PEOPLE_ID;
	 
	public String getADVANTAGES() {
		return ADVANTAGES;
	}
	public void setADVANTAGES(String aDVANTAGES) {
		ADVANTAGES = aDVANTAGES;
	}
	public String getVISIT_TYPE() {
		return VISIT_TYPE;
	}
	public void setVISIT_TYPE(String vISITTYPE) {
		VISIT_TYPE = vISITTYPE;
	}
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pic_path) {
		PIC_PATH = pic_path;
	}
	public String getEXPAND_VISIT_ID() {
		return EXPAND_VISIT_ID;
	}
	public void setEXPAND_VISIT_ID(String expand_visit_id) {
		EXPAND_VISIT_ID = expand_visit_id;
	}
	public String getEXPAND_VISIT_NO() {
		return EXPAND_VISIT_NO;
	}
	public void setEXPAND_VISIT_NO(String expand_visit_no) {
		EXPAND_VISIT_NO = expand_visit_no;
	}
	public String getVISIT_DATE() {
		return VISIT_DATE;
	}
	public void setVISIT_DATE(String visit_date) {
		VISIT_DATE = visit_date;
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
	public String getEXIST_STORE_NAME() {
		return EXIST_STORE_NAME;
	}
	public void setEXIST_STORE_NAME(String exist_store_name) {
		EXIST_STORE_NAME = exist_store_name;
	}
	public String getEXIST_STORE_ADDR() {
		return EXIST_STORE_ADDR;
	}
	public void setEXIST_STORE_ADDR(String exist_store_addr) {
		EXIST_STORE_ADDR = exist_store_addr;
	}
	public String getEXIST_STORE_AREA() {
		return EXIST_STORE_AREA;
	}
	public void setEXIST_STORE_AREA(String exist_store_area) {
		EXIST_STORE_AREA = exist_store_area;
	}
	public String getEXIST_STORE_RANGE() {
		return EXIST_STORE_RANGE;
	}
	public void setEXIST_STORE_RANGE(String exist_store_range) {
		EXIST_STORE_RANGE = exist_store_range;
	}
	public String getEXIST_STORE_COMPETITION() {
		return EXIST_STORE_COMPETITION;
	}
	public void setEXIST_STORE_COMPETITION(String exist_store_competition) {
		EXIST_STORE_COMPETITION = exist_store_competition;
	}
	public String getNEW_STORE_NAME() {
		return NEW_STORE_NAME;
	}
	public void setNEW_STORE_NAME(String new_store_name) {
		NEW_STORE_NAME = new_store_name;
	}
	public String getNEW_STORE_ADDR() {
		return NEW_STORE_ADDR;
	}
	public void setNEW_STORE_ADDR(String new_store_addr) {
		NEW_STORE_ADDR = new_store_addr;
	}
	public String getNEW_STORE_AREA() {
		return NEW_STORE_AREA;
	}
	public void setNEW_STORE_AREA(String new_store_area) {
		NEW_STORE_AREA = new_store_area;
	}
	public String getNEW_STORE_RANGE() {
		return NEW_STORE_RANGE;
	}
	public void setNEW_STORE_RANGE(String new_store_range) {
		NEW_STORE_RANGE = new_store_range;
	}
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}
	public void setBUSS_SCOPE(String buss_scope) {
		BUSS_SCOPE = buss_scope;
	}
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	public void setSTORE_NAME(String store_name) {
		STORE_NAME = store_name;
	}
	public String getPURPOSE_STORE_NAME() {
		return PURPOSE_STORE_NAME;
	}
	public void setPURPOSE_STORE_NAME(String purpose_store_name) {
		PURPOSE_STORE_NAME = purpose_store_name;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tel) {
		TEL = tel;
	}
	public String getCHANN_REMARK() {
		return CHANN_REMARK;
	}
	public void setCHANN_REMARK(String chann_remark) {
		CHANN_REMARK = chann_remark;
	}
	public String getSOLUTION() {
		return SOLUTION;
	}
	public void setSOLUTION(String solution) {
		SOLUTION = solution;
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
	public String getNEW_STORE_DATE() {
		return NEW_STORE_DATE;
	}
	public void setNEW_STORE_DATE(String new_store_date) {
		NEW_STORE_DATE = new_store_date;
	}
	public String getVISIT_PEOPLE_ID() {
		return VISIT_PEOPLE_ID;
	}
	public void setVISIT_PEOPLE_ID(String visit_people_id) {
		VISIT_PEOPLE_ID = visit_people_id;
	}
	 
}
