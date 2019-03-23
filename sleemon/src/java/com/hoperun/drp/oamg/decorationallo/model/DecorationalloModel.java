package com.hoperun.drp.oamg.decorationallo.model;

import com.hoperun.commons.model.BaseModel;

public class DecorationalloModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**装修补贴标准ID**/
	private String RNVTN_SUBST_STD_ID;
	/**装修补贴标准编号**/
	private String RNVTN_SUBST_STD_NO;
	/**品牌**/
	private String BRAND;
	/**最低面积**/
	private String MIN_AREA;
	/**标准面积**/
	private String STD_AREA;
	/**造价成本(元/平米)**/
	private String BUILD_COST;
	/**配饰费用**/
	private String DECORATE_COST;
	/**补贴标准版本**/
	private String RNVTN_SUBST_STD_VSION;
	/**状态**/
	private String STATE;
	/**备注**/
	private String REMARK;
	/**制单人ID**/
	private String CREATOR;
	/**制单人名称**/
	private String CRE_NAME;
	/**制单时间**/
	private String CRE_TIME;
	/**更新人ID**/
	private String UPDATOR;
	/**更新人名称**/
	private String UPD_NAME;
	/**更新时间**/
	private String UPD_TIME;
	/**审核人ID**/
	private String AUDIT_ID;
	/**审核人姓名**/
	private String AUDIT_NAME;
	/**审核时间**/
	private String AUDIT_TIME;
	/**制单部门ID**/
	private String DEPT_ID;
	/**制单部门名称**/
	private String DEPT_NAME;
	/**制单机构ID**/
	private String ORG_ID;
	/**制单机构名称**/
	private String ORG_NAME;
	/**帐套ID**/
	private String LEDGER_ID;
	/**帐套名称**/
	private String LEDGER_NAME;
	/**删除标记**/
	private String DEL_FLAG;
	
	private String BRAND_NAME;
	
	private String DATA_DTL_DES_1;
	private String DATA_DTL_DES_2;
	private String SUBST_AMOUNT;
	
	private String RNVTN_REIT_NO;
	
	private String RNVTN_SCALE;
	
	private String REIT_REMARK;
	
	public String getRNVTN_REIT_NO() {
		return RNVTN_REIT_NO;
	}

	public void setRNVTN_REIT_NO(String rnvtn_reit_no) {
		RNVTN_REIT_NO = rnvtn_reit_no;
	}

	public String getRNVTN_SCALE() {
		return RNVTN_SCALE;
	}

	public void setRNVTN_SCALE(String rnvtn_scale) {
		RNVTN_SCALE = rnvtn_scale;
	}

	public String getREIT_REMARK() {
		return REIT_REMARK;
	}

	public void setREIT_REMARK(String reit_remark) {
		REIT_REMARK = reit_remark;
	}

	public String getDATA_DTL_DES_1() {
		return DATA_DTL_DES_1;
	}

	public void setDATA_DTL_DES_1(String data_dtl_des_1) {
		DATA_DTL_DES_1 = data_dtl_des_1;
	}

	public String getDATA_DTL_DES_2() {
		return DATA_DTL_DES_2;
	}

	public void setDATA_DTL_DES_2(String data_dtl_des_2) {
		DATA_DTL_DES_2 = data_dtl_des_2;
	}

	public String getRNVTN_SUBST_STD_ID() {
		return RNVTN_SUBST_STD_ID;
	}

	public void setRNVTN_SUBST_STD_ID(String rnvtn_subst_std_id) {
		RNVTN_SUBST_STD_ID = rnvtn_subst_std_id;
	}

	public String getRNVTN_SUBST_STD_NO() {
		return RNVTN_SUBST_STD_NO;
	}

	public void setRNVTN_SUBST_STD_NO(String rnvtn_subst_std_no) {
		RNVTN_SUBST_STD_NO = rnvtn_subst_std_no;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String brand) {
		BRAND = brand;
	}

	public String getMIN_AREA() {
		return MIN_AREA;
	}

	public void setMIN_AREA(String min_area) {
		MIN_AREA = min_area;
	}

	public String getSTD_AREA() {
		return STD_AREA;
	}

	public void setSTD_AREA(String std_area) {
		STD_AREA = std_area;
	}

	public String getBUILD_COST() {
		return BUILD_COST;
	}

	public void setBUILD_COST(String build_cost) {
		BUILD_COST = build_cost;
	}

	public String getDECORATE_COST() {
		return DECORATE_COST;
	}

	public void setDECORATE_COST(String decorate_cost) {
		DECORATE_COST = decorate_cost;
	}

	public String getRNVTN_SUBST_STD_VSION() {
		return RNVTN_SUBST_STD_VSION;
	}

	public void setRNVTN_SUBST_STD_VSION(String rnvtn_subst_std_vsion) {
		RNVTN_SUBST_STD_VSION = rnvtn_subst_std_vsion;
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

	public String getAUDIT_ID() {
		return AUDIT_ID;
	}

	public void setAUDIT_ID(String audit_id) {
		AUDIT_ID = audit_id;
	}

	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}

	public void setAUDIT_NAME(String audit_name) {
		AUDIT_NAME = audit_name;
	}

	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}

	public void setAUDIT_TIME(String audit_time) {
		AUDIT_TIME = audit_time;
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

	public String getSUBST_AMOUNT() {
		return SUBST_AMOUNT;
	}

	public void setSUBST_AMOUNT(String subst_amount) {
		SUBST_AMOUNT = subst_amount;
	}

	public String getBRAND_NAME() {
		return BRAND_NAME;
	}

	public void setBRAND_NAME(String brand_name) {
		BRAND_NAME = brand_name;
	}

}
