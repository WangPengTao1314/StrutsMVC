package com.hoperun.drp.oamg.rnvtncheck.model;

import com.hoperun.commons.model.BaseModel;

public class RnvtncheckModel extends BaseModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**装修验收单ID**/
	private  String   RNVTN_CHECK_ID;
	/**装修验收单编号**/
	private  String   RNVTN_CHECK_NO;
	/**装修申请单ID**/
	private  String   CHANN_RNVTN_ID;
	/**装修申请单编号**/
	private  String   CHANN_RNVTN_NO;
	/**终端信息ID**/
	private  String   TERM_ID;
	/**终端编号**/
	private  String   TERM_NO;
	/**终端名称**/
	private  String   TERM_NAME;
	/**装修性质**/
	private  String   RNVTN_PROP;
	/**计划开业时间**/
	private  String   PLAN_SBUSS_DATE;
	/**区域ID**/
	private  String   AREA_ID;
	/**区域编号**/
	private  String   AREA_NO;
	/**区域名称**/
	private  String   AREA_NAME;
	/**区域经理ID**/
	private  String   AREA_MANAGE_ID;
	/**区域经理名称**/
	private  String   AREA_MANAGE_NAME;
	/**区域经理电话**/
	private  String   AREA_MANAGE_TEL;
	/**卖场ID**/
	private  String   SALE_STORE_ID;
	/**卖场名称**/
	private  String   SALE_STORE_NAME;
	/**行政区划ID**/
	private  String   ZONE_ID;
	/**行政区划地址**/
	private  String   ZONE_ADDR;
	/**商场位置类别**/
	private  String   LOCAL_TYPE;
	/**经营范围(经营品牌)**/
	private  String   BUSS_SCOPE;
	/**专卖店类型**/
	private  String   SPCL_STORE_TYPE;
	/**验收得分**/
	private  String   CHECK_SCORE;
	/**验收意见**/
	private  String   CHECK_REMARK;
	/**处罚意见**/
	private  String   PUNISH_REMARK;
	/**状态**/
	private  String   STATE;
	/**备注**/
	private  String   REMARK;
	/**制单人ID**/
	private  String   CREATOR;
	/**制单人名称**/
	private  String   CRE_NAME;
	/**制单时间**/
	private  String   CRE_TIME;
	/**更新人ID**/
	private  String   UPDATOR;
	/**更新人名称**/
	private  String   UPD_NAME;
	/**更新时间**/
	private  String   UPD_TIME;
	/**制单部门ID**/
	private  String   DEPT_ID;
	/**制单部门名称**/
	private  String   DEPT_NAME;
	/**制单机构ID**/
	private  String   ORG_ID;
	/**制单机构名称**/
	private  String   ORG_NAME;
	/**帐套ID**/
	private  String   LEDGER_ID;
	/**帐套名称**/
	private  String   LEDGER_NAME;
	/**删除标记**/
	private  String   DEL_FLAG;
	/**验收负责人**/
	private  String   RNVTN_PRINCIPAL;
	/**验收时间**/
	private  String   RNVTN_CHECK_DATE;
	
	private  String   ATTACHMENT;
	
	private  String   PRJ_TYPE;
	
	private  String   PRJ_NAME;
	
	private  String   PRJ_SCORE;
	
	private  String   IS_REFORM_FLAG;
	
	 /**图片路径**/
    private String PIC_PATH; 
	
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pic_path) {
		PIC_PATH = pic_path;
	}
	public String getPRJ_TYPE() {
		return PRJ_TYPE;
	}
	public void setPRJ_TYPE(String prj_type) {
		PRJ_TYPE = prj_type;
	}
	public String getPRJ_NAME() {
		return PRJ_NAME;
	}
	public void setPRJ_NAME(String prj_name) {
		PRJ_NAME = prj_name;
	}
	public String getPRJ_SCORE() {
		return PRJ_SCORE;
	}
	public void setPRJ_SCORE(String prj_score) {
		PRJ_SCORE = prj_score;
	}
	public String getIS_REFORM_FLAG() {
		return IS_REFORM_FLAG;
	}
	public void setIS_REFORM_FLAG(String is_reform_flag) {
		IS_REFORM_FLAG = is_reform_flag;
	}
	public String getATTACHMENT() {
		return ATTACHMENT;
	}
	public void setATTACHMENT(String attachment) {
		ATTACHMENT = attachment;
	}
	public String getRNVTN_PRINCIPAL() {
		return RNVTN_PRINCIPAL;
	}
	public void setRNVTN_PRINCIPAL(String rnvtn_principal) {
		RNVTN_PRINCIPAL = rnvtn_principal;
	}
	public String getRNVTN_CHECK_DATE() {
		return RNVTN_CHECK_DATE;
	}
	public void setRNVTN_CHECK_DATE(String rnvtn_check_date) {
		RNVTN_CHECK_DATE = rnvtn_check_date;
	}
	public String getRNVTN_CHECK_ID() {
		return RNVTN_CHECK_ID;
	}
	public void setRNVTN_CHECK_ID(String rnvtn_check_id) {
		RNVTN_CHECK_ID = rnvtn_check_id;
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
	public String getAREA_MANAGE_NAME() {
		return AREA_MANAGE_NAME;
	}
	public void setAREA_MANAGE_NAME(String area_manage_name) {
		AREA_MANAGE_NAME = area_manage_name;
	}
	public String getAREA_MANAGE_TEL() {
		return AREA_MANAGE_TEL;
	}
	public void setAREA_MANAGE_TEL(String area_manage_tel) {
		AREA_MANAGE_TEL = area_manage_tel;
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
	public String getZONE_ID() {
		return ZONE_ID;
	}
	public void setZONE_ID(String zone_id) {
		ZONE_ID = zone_id;
	}
	public String getZONE_ADDR() {
		return ZONE_ADDR;
	}
	public void setZONE_ADDR(String zone_addr) {
		ZONE_ADDR = zone_addr;
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
	public String getCHECK_SCORE() {
		return CHECK_SCORE;
	}
	public void setCHECK_SCORE(String check_score) {
		CHECK_SCORE = check_score;
	}
	public String getCHECK_REMARK() {
		return CHECK_REMARK;
	}
	public void setCHECK_REMARK(String check_remark) {
		CHECK_REMARK = check_remark;
	}
	public String getPUNISH_REMARK() {
		return PUNISH_REMARK;
	}
	public void setPUNISH_REMARK(String punish_remark) {
		PUNISH_REMARK = punish_remark;
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
	
	
}

