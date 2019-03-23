/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：PrdSpclTechModel.java
 */

package com.hoperun.base.product.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductModel.
 * 
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 黄如
 */
public class PrdSpclTechModel extends BaseModel {

	/** The Constant serialVersionUID. */
	 
	private static final long serialVersionUID = 4390042562445844883L;
	
	/**
	 * 
	 * 货品特殊工艺维护ID
	 */
	private String PRD_SPCL_TECH_ID;
	

	/**
	 * 货品ID
	 */
	private String PRD_ID;
	
	/**
	 * 特殊工艺维护名称
	 */
	private String SPCL_TECH_NAME;
	
	/**
	 * 特殊工艺维护类型
	 */
	private String SPCL_TECH_TYPE;
	
	/**
	 * 当前值
	 */
	private String CURRT_VAL;

	/**
	 * 调整范围从
	 */
	private String CURRT_VAL_TURN_BEG;

	/**
	 * 调整范围到
	 */
	private String CURRT_VAL_TURN_END;

	
	/**
	 * 调价类型
	 */
	private String PRICE_TURN_TYPE;

	/**
	 * 调价值
	 */
	private String PRICE_TURN_VAL;

	/**
	 * 调整后值
	 */
	private String TUENED_VALS;

	/**
	 * 删除标记
	 */
	private Integer DEL_FLAG;
	/**
	 * 高度填充物说明
	 */
	private String REMARK;
	
	/**调整类型**/
	private String TURN_TYPE;
	
	/**
	 * @return the pRD_ID
	 */
	public String getPRD_ID() {
		return PRD_ID;
	}

	/**
	 * @param pRDID the pRD_ID to set
	 */
	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}



	/**
	 * @return the dEL_FLAG
	 */
	public Integer getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(Integer dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getPRD_SPCL_TECH_ID() {
		return PRD_SPCL_TECH_ID;
	}

	public void setPRD_SPCL_TECH_ID(String prd_spcl_tech_id) {
		PRD_SPCL_TECH_ID = prd_spcl_tech_id;
	}

	public String getSPCL_TECH_NAME() {
		return SPCL_TECH_NAME;
	}

	public void setSPCL_TECH_NAME(String spcl_tech_name) {
		SPCL_TECH_NAME = spcl_tech_name;
	}

	public String getSPCL_TECH_TYPE() {
		return SPCL_TECH_TYPE;
	}

	public void setSPCL_TECH_TYPE(String spcl_tech_type) {
		SPCL_TECH_TYPE = spcl_tech_type;
	}

	public String getCURRT_VAL() {
		return CURRT_VAL;
	}

	public void setCURRT_VAL(String currt_val) {
		CURRT_VAL = currt_val;
	}

	public String getPRICE_TURN_TYPE() {
		return PRICE_TURN_TYPE;
	}

	public void setPRICE_TURN_TYPE(String price_turn_type) {
		PRICE_TURN_TYPE = price_turn_type;
	}

	public String getPRICE_TURN_VAL() {
		return PRICE_TURN_VAL;
	}

	public void setPRICE_TURN_VAL(String price_turn_val) {
		PRICE_TURN_VAL = price_turn_val;
	}

	public String getTUENED_VALS() {
		return TUENED_VALS;
	}

	public void setTUENED_VALS(String tuened_vals) {
		TUENED_VALS = tuened_vals;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getCURRT_VAL_TURN_BEG() {
		return CURRT_VAL_TURN_BEG;
	}

	public void setCURRT_VAL_TURN_BEG(String currt_val_turn_beg) {
		CURRT_VAL_TURN_BEG = currt_val_turn_beg;
	}

	public String getCURRT_VAL_TURN_END() {
		return CURRT_VAL_TURN_END;
	}

	public void setCURRT_VAL_TURN_END(String currt_val_turn_end) {
		CURRT_VAL_TURN_END = currt_val_turn_end;
	}

	public String getTURN_TYPE() {
		return TURN_TYPE;
	}

	public void setTURN_TYPE(String tURNTYPE) {
		TURN_TYPE = tURNTYPE;
	}
	
	

	
}
