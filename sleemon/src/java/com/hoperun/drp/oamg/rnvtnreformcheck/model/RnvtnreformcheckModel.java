package com.hoperun.drp.oamg.rnvtnreformcheck.model;

import com.hoperun.commons.model.BaseModel;

public class RnvtnreformcheckModel extends BaseModel {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 装修整改单ID* */
	private String RNVTN_REFORM_ID;
	/** 装修验收单编号* */
	private String RNVTN_CHECK_NO;
	/** 装修申请单ID* */
	private String CHANN_RNVTN_ID;
	/** 装修申请单编号* */
	private String CHANN_RNVTN_NO;
	/** 施工负责人* */
	private String PROCESS_CHARGE;
	/** 验收负责人* */
	private String CHECK_CHARGE;
	/** 验收时间* */
	private String CHECK_TIME;
	/** 终端信息ID* */
	private String TERM_ID;
	/** 终端编号* */
	private String TERM_NO;
	/** 终端名称* */
	private String TERM_NAME;
	/** 装修性质* */
	private String RNVTN_PROP;
	/** 计划开业时间* */
	private String PLAN_SBUSS_DATE;
	/** 卖场ID* */
	private String SALE_STORE_ID;
	/** 卖场名称* */
	private String SALE_STORE_NAME;
	/** 卖场面积* */
	private String SALE_STORE_AREA;
	/** 商场位置类别* */
	private String LOCAL_TYPE;
	/** 经营范围(经营品牌)* */
	private String BUSS_SCOPE;
	/** 专卖店类型* */
	private String SPCL_STORE_TYPE;
	/** 状态* */
	private String STATE;
	/** 备注* */
	private String REMARK;
	/** 制单人ID* */
	private String CREATOR;
	/** 制单人名称* */
	private String CRE_NAME;
	/** 制单时间* */
	private String CRE_TIME;
	/** 更新人ID* */
	private String UPDATOR;
	/** 更新人名称* */
	private String UPD_NAME;
	/** 更新时间* */
	private String UPD_TIME;
	/** 制单部门ID* */
	private String DEPT_ID;
	/** 制单部门名称* */
	private String DEPT_NAME;
	/** 制单机构ID* */
	private String ORG_ID;
	/** 制单机构名称* */
	private String ORG_NAME;
	/** 帐套ID* */
	private String LEDGER_ID;
	/** 帐套名称* */
	private String LEDGER_NAME;
	/** 删除标记* */
	private String DEL_FLAG;
	/**设计师**/
	private String DESIGNER;
	/**整改验收意见**/
	private String RNVTN_REFORM_REMARK;
	/**处罚意见**/
	private String PUNISH_REMARK;
	/**图片路径**/
	private String PIC_PATH;
	/**整改后图片**/
	private String ATTMEMT_PATH;
	
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pic_path) {
		PIC_PATH = pic_path;
	}
	public String getDESIGNER() {
		return DESIGNER;
	}
	public void setDESIGNER(String designer) {
		DESIGNER = designer;
	}
	public String getRNVTN_REFORM_REMARK() {
		return RNVTN_REFORM_REMARK;
	}
	public void setRNVTN_REFORM_REMARK(String rnvtn_reform_remark) {
		RNVTN_REFORM_REMARK = rnvtn_reform_remark;
	}
	public String getPUNISH_REMARK() {
		return PUNISH_REMARK;
	}
	public void setPUNISH_REMARK(String punish_remark) {
		PUNISH_REMARK = punish_remark;
	}
	public String getRNVTN_REFORM_ID() {
		return RNVTN_REFORM_ID;
	}
	public void setRNVTN_REFORM_ID(String rnvtn_reform_id) {
		RNVTN_REFORM_ID = rnvtn_reform_id;
	}
	public String getRNVTN_CHECK_NO() {
		return RNVTN_CHECK_NO;
	}
	public void setRNVTN_CHECK_NO(String rnvtn_check_no) {
		RNVTN_CHECK_NO = rnvtn_check_no;
	}
	public String getCHANN_RNVTN_ID() {
		return CHANN_RNVTN_ID;
	}
	public void setCHANN_RNVTN_ID(String chann_rnvtn_id) {
		CHANN_RNVTN_ID = chann_rnvtn_id;
	}
	public String getCHANN_RNVTN_NO() {
		return CHANN_RNVTN_NO;
	}
	public void setCHANN_RNVTN_NO(String chann_rnvtn_no) {
		CHANN_RNVTN_NO = chann_rnvtn_no;
	}
	public String getPROCESS_CHARGE() {
		return PROCESS_CHARGE;
	}
	public void setPROCESS_CHARGE(String process_charge) {
		PROCESS_CHARGE = process_charge;
	}
	public String getCHECK_CHARGE() {
		return CHECK_CHARGE;
	}
	public void setCHECK_CHARGE(String check_charge) {
		CHECK_CHARGE = check_charge;
	}
	public String getCHECK_TIME() {
		return CHECK_TIME;
	}
	public void setCHECK_TIME(String check_time) {
		CHECK_TIME = check_time;
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
	public String getRNVTN_PROP() {
		return RNVTN_PROP;
	}
	public void setRNVTN_PROP(String rnvtn_prop) {
		RNVTN_PROP = rnvtn_prop;
	}
	public String getPLAN_SBUSS_DATE() {
		return PLAN_SBUSS_DATE;
	}
	public void setPLAN_SBUSS_DATE(String plan_sbuss_date) {
		PLAN_SBUSS_DATE = plan_sbuss_date;
	}
	public String getSALE_STORE_ID() {
		return SALE_STORE_ID;
	}
	public void setSALE_STORE_ID(String sale_store_id) {
		SALE_STORE_ID = sale_store_id;
	}
	public String getSALE_STORE_NAME() {
		return SALE_STORE_NAME;
	}
	public void setSALE_STORE_NAME(String sale_store_name) {
		SALE_STORE_NAME = sale_store_name;
	}
	public String getSALE_STORE_AREA() {
		return SALE_STORE_AREA;
	}
	public void setSALE_STORE_AREA(String sale_store_area) {
		SALE_STORE_AREA = sale_store_area;
	}
	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}
	public void setLOCAL_TYPE(String local_type) {
		LOCAL_TYPE = local_type;
	}
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}
	public void setBUSS_SCOPE(String buss_scope) {
		BUSS_SCOPE = buss_scope;
	}
	public String getSPCL_STORE_TYPE() {
		return SPCL_STORE_TYPE;
	}
	public void setSPCL_STORE_TYPE(String spcl_store_type) {
		SPCL_STORE_TYPE = spcl_store_type;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String creator) {
		CREATOR = creator;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cre_name) {
		CRE_NAME = cre_name;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cre_time) {
		CRE_TIME = cre_time;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String updator) {
		UPDATOR = updator;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String upd_name) {
		UPD_NAME = upd_name;
	}
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	public void setUPD_TIME(String upd_time) {
		UPD_TIME = upd_time;
	}
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dept_id) {
		DEPT_ID = dept_id;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dept_name) {
		DEPT_NAME = dept_name;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String ledger_id) {
		LEDGER_ID = ledger_id;
	}
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	public void setLEDGER_NAME(String ledger_name) {
		LEDGER_NAME = ledger_name;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}
	public String getATTMEMT_PATH() {
		return ATTMEMT_PATH;
	}
	public void setATTMEMT_PATH(String attmemt_path) {
		ATTMEMT_PATH = attmemt_path;
	}
}
