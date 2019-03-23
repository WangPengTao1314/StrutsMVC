/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProSuitModel.java
 */
package com.hoperun.base.product.model;

import com.hoperun.commons.model.BaseModel;

/**
 * The Class ProductModel.
 * 
 * @module 系统管理
 * @func 货品套
 * @version 1.1
 * @author 刘曰刚
 */
public class PrdSuitModel  extends BaseModel{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7073220445600393784L;

	/**
	 * 货品套明细ID
	 */
	private String PRD_UNIT_ID;
	
	/**
	 * 货品ID
	 */
	private String PRD_ID;

	/**
	 * 货品编号
	 */
	private String PRD_NO;

	/**
	 * 货品名称
	 */
	private String PRD_NAME;

	/**
	 * 规格型号
	 */
	private String PRD_SPEC;

	/**
	 * 颜色
	 */
	private String PRD_COLOR;

	/**
	 * 品牌
	 */
	private String BRAND;

	/**
	 * 标准单位
	 */
	private String STD_UNIT;
	
	/**
	 * 组成数量
	 */
	private String COMP_NUM;
	private String MAIN_PRD_ID;
	private String PRVD_PRICE;
	private String MAIN_BOM_FLAG;

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
	 * @return the pRD_NO
	 */
	public String getPRD_NO() {
		return PRD_NO;
	}

	/**
	 * @param pRDNO the pRD_NO to set
	 */
	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}

	/**
	 * @return the pRD_NAME
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}

	/**
	 * @param pRDNAME the pRD_NAME to set
	 */
	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}

	/**
	 * @return the pRD_SPEC
	 */
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	/**
	 * @param pRDSPEC the pRD_SPEC to set
	 */
	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}

	/**
	 * @return the pRD_COLOR
	 */
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	/**
	 * @param pRDCOLOR the pRD_COLOR to set
	 */
	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
	}

	/**
	 * @return the bRAND
	 */
	public String getBRAND() {
		return BRAND;
	}

	/**
	 * @param bRAND the bRAND to set
	 */
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}

	/**
	 * @return the sTD_UNIT
	 */
	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	/**
	 * @param sTDUNIT the sTD_UNIT to set
	 */
	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}


	/**
	 * @return the cOMP_NUM
	 */
	public String getCOMP_NUM() {
		return COMP_NUM;
	}

	/**
	 * @param cOMPNUM the cOMP_NUM to set
	 */
	public void setCOMP_NUM(String cOMPNUM) {
		COMP_NUM = cOMPNUM;
	}

	/**
	 * @return the mAIN_BOM_FLAG
	 */
	public String getMAIN_BOM_FLAG() {
		return MAIN_BOM_FLAG;
	}

	/**
	 * @param mAINBOMFLAG the mAIN_BOM_FLAG to set
	 */
	public void setMAIN_BOM_FLAG(String mAINBOMFLAG) {
		MAIN_BOM_FLAG = mAINBOMFLAG;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the mAIN_PRD_ID
	 */
	public String getMAIN_PRD_ID() {
		return MAIN_PRD_ID;
	}

	/**
	 * @param mAINPRDID the mAIN_PRD_ID to set
	 */
	public void setMAIN_PRD_ID(String mAINPRDID) {
		MAIN_PRD_ID = mAINPRDID;
	}

	/**
	 * @return the pRVD_PRICE
	 */
	public String getPRVD_PRICE() {
		return PRVD_PRICE;
	}

	/**
	 * @param pRVDPRICE the pRVD_PRICE to set
	 */
	public void setPRVD_PRICE(String pRVDPRICE) {
		PRVD_PRICE = pRVDPRICE;
	}

	
}
