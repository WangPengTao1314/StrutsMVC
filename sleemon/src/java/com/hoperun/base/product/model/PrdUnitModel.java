/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProUnitModel.java
 */
package com.hoperun.base.product.model;

import com.hoperun.commons.model.BaseModel;
/**
 * The Class ProductModel.
 * 
 * @module 系统管理
 * @func 货品计量单位
 * @version 1.1
 * @author 刘曰刚
 */
public class PrdUnitModel extends BaseModel{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7073220445600393784L;
	/**
	 * 货品计量单位ID
	 */
	private String PRD_UNIT_ID;
	
	/**
	 * 货品ID
	 */
	private String PRD_ID;
	
	/**
	 * 计量单位ID
	 */
	private String MEAS_UNIT_ID;
	
	/**
	 * 计量单位编号
	 */
	private String MEAS_UNIT_NO;
	
	/**
	 * 计量单位名称
	 */
	private String MEAS_UNIT_NAME;
	
	/**
	 * 单位类型
	 */
	private String MEAS_UNIT_TYPE;
	
	/**
	 * 换算关系
	 */
	private double RATIO;

	/**
	 * @return the pRD_UNIT_ID
	 */
	public String getPRD_UNIT_ID() {
		return PRD_UNIT_ID;
	}

	/**
	 * @param pRDUNITID the pRD_UNIT_ID to set
	 */
	public void setPRD_UNIT_ID(String pRDUNITID) {
		PRD_UNIT_ID = pRDUNITID;
	}

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
	 * @return the mEAS_UNIT_ID
	 */
	public String getMEAS_UNIT_ID() {
		return MEAS_UNIT_ID;
	}

	/**
	 * @param mEASUNITID the mEAS_UNIT_ID to set
	 */
	public void setMEAS_UNIT_ID(String mEASUNITID) {
		MEAS_UNIT_ID = mEASUNITID;
	}

	/**
	 * @return the mEAS_UNIT_NO
	 */
	public String getMEAS_UNIT_NO() {
		return MEAS_UNIT_NO;
	}

	/**
	 * @param mEASUNITNO the mEAS_UNIT_NO to set
	 */
	public void setMEAS_UNIT_NO(String mEASUNITNO) {
		MEAS_UNIT_NO = mEASUNITNO;
	}

	/**
	 * @return the mEAS_UNIT_NAME
	 */
	public String getMEAS_UNIT_NAME() {
		return MEAS_UNIT_NAME;
	}

	/**
	 * @param mEASUNITNAME the mEAS_UNIT_NAME to set
	 */
	public void setMEAS_UNIT_NAME(String mEASUNITNAME) {
		MEAS_UNIT_NAME = mEASUNITNAME;
	}

	/**
	 * @return the mEAS_UNIT_TYPE
	 */
	public String getMEAS_UNIT_TYPE() {
		return MEAS_UNIT_TYPE;
	}

	/**
	 * @param mEASUNITTYPE the mEAS_UNIT_TYPE to set
	 */
	public void setMEAS_UNIT_TYPE(String mEASUNITTYPE) {
		MEAS_UNIT_TYPE = mEASUNITTYPE;
	}

	/**
	 * @return the rATIO
	 */
	public double getRATIO() {
		return RATIO;
	}

	/**
	 * @param rATIO the rATIO to set
	 */
	public void setRATIO(double rATIO) {
		RATIO = rATIO;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
