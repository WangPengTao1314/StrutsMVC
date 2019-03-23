/**
 * prjName:喜临门营销平台
 * ucName:退货申请单维护
 * fileName:PrdreturnModelChld
 */
package com.hoperun.drp.sale.prdreturnreq.model;

import com.hoperun.commons.model.BaseModel;

/**
 * *@module *@func *@version 1.1 *@author wzg *@createdate 2013-08-15 10:17:13
 */
public class PrdreturnreqModelChld extends BaseModel {
	/** 退货申请单明细ID **/
	private String PRD_RETURN_DTL_REQ_ID;
	/** 退货申请单ID **/
	private String PRD_RETURN_REQ_ID;
	/** 来源单据ID **/
	private String FROM_BILL_ID;
	/** 来源单据NO **/
	private String FROM_BILL_NO;
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
	/** 退货单价 **/
	private String RETURN_PRICE;
	/** 退货数量 **/
	private String RETURN_NUM;
	/** 退货金额 **/
	private String RETURN_AMOUNT;
	/** 原因归类 **/
	private String RETURN_RSON_TYPE;
	/** 退货原因 **/
	private String RETURN_RSON;
	/** 退货附件 **/
	private String RETURN_ATT;
	/** 删除标记 **/
	private String DEL_FLAG;
	private String STORE_ID;
	private String MAX_RETURN_NUM;
	private String SPCL_TECH_FLAG;
	private String USE_TIME;
	private String SHIP_POINT_ID;
	private String SHIP_POINT_NO;
	private String SHIP_POINT_NAME;
	private String PRDC_DATE;
	private String SPCL_SPEC_REMARK;
	private String SPCL_TECH_ID;
	//货品分类
	private String PAR_PRD_NAME;

	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}

	public String getSPCL_TECH_FLAG() {
		return SPCL_TECH_FLAG;
	}

	public void setSPCL_TECH_FLAG(String sPCLTECHFLAG) {
		SPCL_TECH_FLAG = sPCLTECHFLAG;
	}

	/**
	 * get 退货申请单明细ID value
	 * 
	 * @return the PRD_RETURN_DTL_REQ_ID
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_RETURN_DTL_REQ_ID() {
		return PRD_RETURN_DTL_REQ_ID;
	}

	/**
	 * set 退货申请单明细ID value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_RETURN_DTL_REQ_ID(String PRD_RETURN_DTL_REQ_ID) {
		this.PRD_RETURN_DTL_REQ_ID = PRD_RETURN_DTL_REQ_ID;
	}

	/**
	 * get 退货申请单ID value
	 * 
	 * @return the PRD_RETURN_REQ_ID
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_RETURN_REQ_ID() {
		return PRD_RETURN_REQ_ID;
	}

	/**
	 * set 退货申请单ID value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_RETURN_REQ_ID(String PRD_RETURN_REQ_ID) {
		this.PRD_RETURN_REQ_ID = PRD_RETURN_REQ_ID;
	}

	/**
	 * get 来源单据ID value
	 * 
	 * @return the FROM_BILL_ID
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}

	/**
	 * set 来源单据ID value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setFROM_BILL_ID(String FROM_BILL_ID) {
		this.FROM_BILL_ID = FROM_BILL_ID;
	}

	/**
	 * get 来源单据NO value
	 * 
	 * @return the FROM_BILL_NO
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getFROM_BILL_NO() {
		return FROM_BILL_NO;
	}

	/**
	 * set 来源单据NO value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setFROM_BILL_NO(String FROM_BILL_NO) {
		this.FROM_BILL_NO = FROM_BILL_NO;
	}

	/**
	 * get 货品ID value
	 * 
	 * @return the PRD_ID
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_ID() {
		return PRD_ID;
	}

	/**
	 * set 货品ID value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_ID(String PRD_ID) {
		this.PRD_ID = PRD_ID;
	}

	/**
	 * get 货品编号 value
	 * 
	 * @return the PRD_NO
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_NO() {
		return PRD_NO;
	}

	/**
	 * set 货品编号 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_NO(String PRD_NO) {
		this.PRD_NO = PRD_NO;
	}

	/**
	 * get 货品名称 value
	 * 
	 * @return the PRD_NAME
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}

	/**
	 * set 货品名称 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_NAME(String PRD_NAME) {
		this.PRD_NAME = PRD_NAME;
	}

	/**
	 * get 规格型号 value
	 * 
	 * @return the PRD_SPEC
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	/**
	 * set 规格型号 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_SPEC(String PRD_SPEC) {
		this.PRD_SPEC = PRD_SPEC;
	}

	/**
	 * get 颜色 value
	 * 
	 * @return the PRD_COLOR
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	/**
	 * set 颜色 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setPRD_COLOR(String PRD_COLOR) {
		this.PRD_COLOR = PRD_COLOR;
	}

	/**
	 * get 品牌 value
	 * 
	 * @return the BRAND
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getBRAND() {
		return BRAND;
	}

	/**
	 * set 品牌 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setBRAND(String BRAND) {
		this.BRAND = BRAND;
	}

	/**
	 * get 标准单位 value
	 * 
	 * @return the STD_UNIT
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	/**
	 * set 标准单位 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setSTD_UNIT(String STD_UNIT) {
		this.STD_UNIT = STD_UNIT;
	}

	/**
	 * get 退货单价 value
	 * 
	 * @return the RETURN_PRICE
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getRETURN_PRICE() {
		return RETURN_PRICE;
	}

	/**
	 * set 退货单价 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setRETURN_PRICE(String RETURN_PRICE) {
		this.RETURN_PRICE = RETURN_PRICE;
	}

	/**
	 * get 退货数量 value
	 * 
	 * @return the RETURN_NUM
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getRETURN_NUM() {
		return RETURN_NUM;
	}

	/**
	 * set 退货数量 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setRETURN_NUM(String RETURN_NUM) {
		this.RETURN_NUM = RETURN_NUM;
	}

	/**
	 * get 退货金额 value
	 * 
	 * @return the RETURN_AMOUNT
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getRETURN_AMOUNT() {
		return RETURN_AMOUNT;
	}

	/**
	 * set 退货金额 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setRETURN_AMOUNT(String RETURN_AMOUNT) {
		this.RETURN_AMOUNT = RETURN_AMOUNT;
	}

	/**
	 * get 原因归类 value
	 * 
	 * @return the RETURN_RSON_TYPE
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getRETURN_RSON_TYPE() {
		return RETURN_RSON_TYPE;
	}

	/**
	 * set 原因归类 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setRETURN_RSON_TYPE(String RETURN_RSON_TYPE) {
		this.RETURN_RSON_TYPE = RETURN_RSON_TYPE;
	}

	/**
	 * get 退货原因 value
	 * 
	 * @return the RETURN_RSON
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getRETURN_RSON() {
		return RETURN_RSON;
	}

	/**
	 * set 退货原因 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setRETURN_RSON(String RETURN_RSON) {
		this.RETURN_RSON = RETURN_RSON;
	}

	/**
	 * get 退货附件 value
	 * 
	 * @return the RETURN_ATT
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getRETURN_ATT() {
		return RETURN_ATT;
	}

	/**
	 * set 退货附件 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setRETURN_ATT(String RETURN_ATT) {
		this.RETURN_ATT = RETURN_ATT;
	}

	/**
	 * get 删除标记 value
	 * 
	 * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * set 删除标记 value
	 * 
	 * @createdate 2013-08-15 10:17:13
	 * @author wzg
	 * @createdate 2013-08-15 10:17:13
	 */
	public void setDEL_FLAG(String DEL_FLAG) {
		this.DEL_FLAG = DEL_FLAG;
	}

	/**
	 * @return the sTORE_ID
	 */
	public String getSTORE_ID() {
		return STORE_ID;
	}

	/**
	 * @param sTOREID the sTORE_ID to set
	 */
	public void setSTORE_ID(String sTOREID) {
		STORE_ID = sTOREID;
	}

	/**
	 * @return the mAX_RETURN_NUM
	 */
	public String getMAX_RETURN_NUM() {
		return MAX_RETURN_NUM;
	}

	/**
	 * @param mAXRETURNNUM the mAX_RETURN_NUM to set
	 */
	public void setMAX_RETURN_NUM(String mAXRETURNNUM) {
		MAX_RETURN_NUM = mAXRETURNNUM;
	}

	/**
	 * @return the uSE_TIME
	 */
	public String getUSE_TIME() {
		return USE_TIME;
	}

	/**
	 * @param uSETIME the uSE_TIME to set
	 */
	public void setUSE_TIME(String uSETIME) {
		USE_TIME = uSETIME;
	}

	/**
	 * @return the sHIP_POINT_ID
	 */
	public String getSHIP_POINT_ID() {
		return SHIP_POINT_ID;
	}

	/**
	 * @param sHIPPOINTID the sHIP_POINT_ID to set
	 */
	public void setSHIP_POINT_ID(String sHIPPOINTID) {
		SHIP_POINT_ID = sHIPPOINTID;
	}

	/**
	 * @return the sHIP_POINT_NO
	 */
	public String getSHIP_POINT_NO() {
		return SHIP_POINT_NO;
	}

	/**
	 * @param sHIPPOINTNO the sHIP_POINT_NO to set
	 */
	public void setSHIP_POINT_NO(String sHIPPOINTNO) {
		SHIP_POINT_NO = sHIPPOINTNO;
	}

	/**
	 * @return the sHIP_POINT_NAME
	 */
	public String getSHIP_POINT_NAME() {
		return SHIP_POINT_NAME;
	}

	/**
	 * @param sHIPPOINTNAME the sHIP_POINT_NAME to set
	 */
	public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
		SHIP_POINT_NAME = sHIPPOINTNAME;
	}

	/**
	 * @return the pRDC_DATE
	 */
	public String getPRDC_DATE() {
		return PRDC_DATE;
	}

	/**
	 * @param pRDCDATE the pRDC_DATE to set
	 */
	public void setPRDC_DATE(String pRDCDATE) {
		PRDC_DATE = pRDCDATE;
	}

	/**
	 * @return the sPCL_SPEC_REMARK
	 */
	public String getSPCL_SPEC_REMARK() {
		return SPCL_SPEC_REMARK;
	}

	/**
	 * @param sPCLSPECREMARK the sPCL_SPEC_REMARK to set
	 */
	public void setSPCL_SPEC_REMARK(String sPCLSPECREMARK) {
		SPCL_SPEC_REMARK = sPCLSPECREMARK;
	}

	public String getPAR_PRD_NAME() {
		return PAR_PRD_NAME;
	}

	public void setPAR_PRD_NAME(String pARPRDNAME) {
		PAR_PRD_NAME = pARPRDNAME;
	}
	
	
	
}