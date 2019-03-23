package com.hoperun.drp.oamg.rnvtnreform.model;

import com.hoperun.commons.model.BaseModel;

public class RnvtnreformChildModel extends BaseModel {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**装修整改单明细ID**/
	private   String   RNVTN_REFORM_DTL_ID;
	/**装修整改单ID**/
	private   String   RNVTN_REFORM_ID;
	/**装修验收单明细ID**/
	private   String   RNVTN_CHECK_DTL_ID;
	/**验收项目类型**/
	private   String   PRJ_TYPE;
	/**验收项目名称**/
	private   String   PRJ_NAME;
	/**项目分值**/
	private   String   PRJ_SCORE;
	/**验收得分**/
	private   String   CHECK_SCORE;
	/**验收意见**/
	private   String   CHECK_REMARK;
	/**删除标记**/
	private   String   DEL_FLAG;
	public String getRNVTN_REFORM_DTL_ID() {
		return RNVTN_REFORM_DTL_ID;
	}
	public void setRNVTN_REFORM_DTL_ID(String rnvtn_reform_dtl_id) {
		RNVTN_REFORM_DTL_ID = rnvtn_reform_dtl_id;
	}
	public String getRNVTN_REFORM_ID() {
		return RNVTN_REFORM_ID;
	}
	public void setRNVTN_REFORM_ID(String rnvtn_reform_id) {
		RNVTN_REFORM_ID = rnvtn_reform_id;
	}
	public String getRNVTN_CHECK_DTL_ID() {
		return RNVTN_CHECK_DTL_ID;
	}
	public void setRNVTN_CHECK_DTL_ID(String rnvtn_check_dtl_id) {
		RNVTN_CHECK_DTL_ID = rnvtn_check_dtl_id;
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
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}
  
}
