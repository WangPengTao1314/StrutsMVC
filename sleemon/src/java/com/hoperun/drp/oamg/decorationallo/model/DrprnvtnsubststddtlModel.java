package com.hoperun.drp.oamg.decorationallo.model;

import com.hoperun.commons.model.BaseModel;

public class DrprnvtnsubststddtlModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 装修补贴标准明细ID* */
	private String RNVTN_SUBST_STD_DTL_ID;
	/** 装修补贴标准ID* */
	private String RNVTN_SUBST_STD_ID;
	/** 商场位置类别* */
	private String LOCAL_TYPE;
	/** 补贴金额* */
	private String SUBST_AMOUNT;
	/** 备注* */
	private String REMARK;
	/** 删除标记* */
	private String DEL_FLAG;

	private String DLX;
	
	private String BC;
	
	private String CSZ;
	
	public String getDLX() {
		return DLX;
	}

	public void setDLX(String dlx) {
		DLX = dlx;
	}

	public String getBC() {
		return BC;
	}

	public void setBC(String bc) {
		BC = bc;
	}

	public String getCSZ() {
		return CSZ;
	}

	public void setCSZ(String csz) {
		CSZ = csz;
	}

	public String getRNVTN_SUBST_STD_DTL_ID() {
		return RNVTN_SUBST_STD_DTL_ID;
	}

	public void setRNVTN_SUBST_STD_DTL_ID(String rnvtn_subst_std_dtl_id) {
		RNVTN_SUBST_STD_DTL_ID = rnvtn_subst_std_dtl_id;
	}

	public String getRNVTN_SUBST_STD_ID() {
		return RNVTN_SUBST_STD_ID;
	}

	public void setRNVTN_SUBST_STD_ID(String rnvtn_subst_std_id) {
		RNVTN_SUBST_STD_ID = rnvtn_subst_std_id;
	}

	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}

	public void setLOCAL_TYPE(String local_type) {
		LOCAL_TYPE = local_type;
	}

	public String getSUBST_AMOUNT() {
		return SUBST_AMOUNT;
	}

	public void setSUBST_AMOUNT(String subst_amount) {
		SUBST_AMOUNT = subst_amount;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}
}
