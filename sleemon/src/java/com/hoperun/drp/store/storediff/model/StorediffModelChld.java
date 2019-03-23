/**
 * prjName:喜临门营销平台
 * ucName:入库差异确认
 * fileName:StorediffModelChld
 */
package com.hoperun.drp.store.storediff.model;

import com.hoperun.commons.model.BaseModel;

/**
 * *@module *@func *@version 1.1 *@author wzg *@createdate 2013-08-30 14:03:21
 */
public class StorediffModelChld extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2583502558462801845L;
	/** 入库差异单明细ID **/
	private String STOREIN_DIFF_DTL_ID;
	/** 入库差异单ID **/
	private String STOREIN_DIFF_ID;
	/** 货品ID **/
	private String PRD_ID;
	/** 货品编号 **/
	private String PRD_NO;
	/** 货品名称 **/
	private String PRD_NAME;
	/** 规格型号 **/
	private String PRD_SPEC;
	/** 颜色 **/
	private String PRD_COLOR;
	/** 品牌 **/
	private String BRAND;
	/** 标准单位 **/
	private String STD_UNIT;
	/** 单价 **/
	private String PRICE;
	/** 折扣率 **/
	private String DECT_RATE;
	/** 折扣价 **/
	private String DECT_PRICE;
	/** 通知入库数量 **/
	private String NOTICE_NUM;
	/** 实际入库数量 **/
	private String REAL_NUM;
	/** 差异数量 **/
	private String DIFF_NUM;
	/** 差异金额 **/
	private String DIFF_AMOUNT;
	/** 来源单据明细ID **/
	private String FROM_BILL_DTL_ID;
	/** 删除标记 **/
	private String DEL_FLAG;
	/** 特殊工艺标记 **/
    private String SPCL_TECH_FLAG;
   
    /** 订单特殊工艺 **/
    private String SPCL_TECH_ID;
   
    /** 销售订单ID **/
    private String SALE_ORDER_ID;
   
    /** 销售订单编号 **/
    private String SALE_ORDER_NO;
   
    /** 销售订单明细ID **/
    private String SALE_ORDER_DTL_ID;
   
    /** 订货订单编号 **/
    private String GOODS_ORDER_NO;

	/**
	 * get 入库差异单明细ID value
	 * 
	 * @return the STOREIN_DIFF_DTL_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getSTOREIN_DIFF_DTL_ID() {
		return STOREIN_DIFF_DTL_ID;
	}

	/**
	 * set 入库差异单明细ID value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setSTOREIN_DIFF_DTL_ID(String STOREIN_DIFF_DTL_ID) {
		this.STOREIN_DIFF_DTL_ID = STOREIN_DIFF_DTL_ID;
	}

	/**
	 * get 入库差异单ID value
	 * 
	 * @return the STOREIN_DIFF_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getSTOREIN_DIFF_ID() {
		return STOREIN_DIFF_ID;
	}

	/**
	 * set 入库差异单ID value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setSTOREIN_DIFF_ID(String STOREIN_DIFF_ID) {
		this.STOREIN_DIFF_ID = STOREIN_DIFF_ID;
	}

	/**
	 * get 货品ID value
	 * 
	 * @return the PRD_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getPRD_ID() {
		return PRD_ID;
	}

	/**
	 * set 货品ID value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setPRD_ID(String PRD_ID) {
		this.PRD_ID = PRD_ID;
	}

	/**
	 * get 货品编号 value
	 * 
	 * @return the PRD_NO
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getPRD_NO() {
		return PRD_NO;
	}

	/**
	 * set 货品编号 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setPRD_NO(String PRD_NO) {
		this.PRD_NO = PRD_NO;
	}

	/**
	 * get 货品名称 value
	 * 
	 * @return the PRD_NAME
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}

	/**
	 * set 货品名称 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setPRD_NAME(String PRD_NAME) {
		this.PRD_NAME = PRD_NAME;
	}

	/**
	 * get 规格型号 value
	 * 
	 * @return the PRD_SPEC
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	/**
	 * set 规格型号 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setPRD_SPEC(String PRD_SPEC) {
		this.PRD_SPEC = PRD_SPEC;
	}

	/**
	 * get 颜色 value
	 * 
	 * @return the PRD_COLOR
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	/**
	 * set 颜色 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setPRD_COLOR(String PRD_COLOR) {
		this.PRD_COLOR = PRD_COLOR;
	}

	/**
	 * get 品牌 value
	 * 
	 * @return the BRAND
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getBRAND() {
		return BRAND;
	}

	/**
	 * set 品牌 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setBRAND(String BRAND) {
		this.BRAND = BRAND;
	}

	/**
	 * get 标准单位 value
	 * 
	 * @return the STD_UNIT
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	/**
	 * set 标准单位 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setSTD_UNIT(String STD_UNIT) {
		this.STD_UNIT = STD_UNIT;
	}

	/**
	 * get 单价 value
	 * 
	 * @return the PRICE
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getPRICE() {
		return PRICE;
	}

	/**
	 * set 单价 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setPRICE(String PRICE) {
		this.PRICE = PRICE;
	}

	/**
	 * get 折扣率 value
	 * 
	 * @return the DECT_RATE
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getDECT_RATE() {
		return DECT_RATE;
	}

	/**
	 * set 折扣率 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setDECT_RATE(String DECT_RATE) {
		this.DECT_RATE = DECT_RATE;
	}

	/**
	 * get 折扣价 value
	 * 
	 * @return the DECT_PRICE
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}

	/**
	 * set 折扣价 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setDECT_PRICE(String DECT_PRICE) {
		this.DECT_PRICE = DECT_PRICE;
	}

	/**
	 * get 通知入库数量 value
	 * 
	 * @return the NOTICE_NUM
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getNOTICE_NUM() {
		return NOTICE_NUM;
	}

	/**
	 * set 通知入库数量 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setNOTICE_NUM(String NOTICE_NUM) {
		this.NOTICE_NUM = NOTICE_NUM;
	}

	/**
	 * get 实际入库数量 value
	 * 
	 * @return the REAL_NUM
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getREAL_NUM() {
		return REAL_NUM;
	}

	/**
	 * set 实际入库数量 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setREAL_NUM(String REAL_NUM) {
		this.REAL_NUM = REAL_NUM;
	}

	/**
	 * get 差异数量 value
	 * 
	 * @return the DIFF_NUM
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getDIFF_NUM() {
		return DIFF_NUM;
	}

	/**
	 * set 差异数量 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setDIFF_NUM(String DIFF_NUM) {
		this.DIFF_NUM = DIFF_NUM;
	}

	/**
	 * get 差异金额 value
	 * 
	 * @return the DIFF_AMOUNT
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getDIFF_AMOUNT() {
		return DIFF_AMOUNT;
	}

	/**
	 * set 差异金额 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setDIFF_AMOUNT(String DIFF_AMOUNT) {
		this.DIFF_AMOUNT = DIFF_AMOUNT;
	}

	/**
	 * get 来源单据明细ID value
	 * 
	 * @return the FROM_BILL_DTL_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getFROM_BILL_DTL_ID() {
		return FROM_BILL_DTL_ID;
	}

	/**
	 * set 来源单据明细ID value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID) {
		this.FROM_BILL_DTL_ID = FROM_BILL_DTL_ID;
	}

	/**
	 * get 删除标记 value
	 * 
	 * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * set 删除标记 value
	 * 
	 * @createdate 2013-08-30 14:03:21
	 * @author wzg
	 * @createdate 2013-08-30 14:03:21
	 */
	public void setDEL_FLAG(String DEL_FLAG) {
		this.DEL_FLAG = DEL_FLAG;
	}

	public String getGOODS_ORDER_NO() {
		return GOODS_ORDER_NO;
	}

	public void setGOODS_ORDER_NO(String gOODSORDERNO) {
		GOODS_ORDER_NO = gOODSORDERNO;
	}

	public String getSPCL_TECH_FLAG() {
		return SPCL_TECH_FLAG;
	}

	public void setSPCL_TECH_FLAG(String sPCLTECHFLAG) {
		SPCL_TECH_FLAG = sPCLTECHFLAG;
	}

	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}

	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}

	public void setSALE_ORDER_ID(String sale_order_id) {
		SALE_ORDER_ID = sale_order_id;
	}

	public String getSALE_ORDER_NO() {
		return SALE_ORDER_NO;
	}

	public void setSALE_ORDER_NO(String sale_order_no) {
		SALE_ORDER_NO = sale_order_no;
	}

	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}

	public void setSALE_ORDER_DTL_ID(String sale_order_dtl_id) {
		SALE_ORDER_DTL_ID = sale_order_dtl_id;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}