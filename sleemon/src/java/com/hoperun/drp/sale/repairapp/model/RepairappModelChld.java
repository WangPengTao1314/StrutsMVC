/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairappModelChld
 */
package com.hoperun.drp.sale.repairapp.model;

import com.hoperun.commons.model.BaseModel;

/**
 * 返修明细
 * *@module 
 * *@func 
 * *@version 1.1 
 * *@author chenj 
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairappModelChld extends BaseModel {
	
	/** 返修单明细ID **/
	private String REPAIR_ORDER_DTL_ID;
	/** 返修单ID **/
	private String ERP_REPAIR_ORDER_ID;
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
	/** 返修数量 **/
	private String REPAIR_NUM;
	/** 返修单价 **/
	private String REPAIR_PRICE;
	/** 返修金额 **/
	private String REPAIR_AMOUNT;
	/** 返修原因 **/
	private String REPAIR_RESON;
	/** 返修附件 **/
	private String REPAIR_ATT;
	/** 删除标记 **/
	private String DEL_FLAG;

	private String SPCL_TECH_FLAG;

	private String SPCL_TECH_ID;

	private String REMARK;
	private String SAFE_NUM;

	// 体积
	private String VOLUME;
	// 总体积
	private String TOTAL_VOLUME;

	/**
	 * get 返修单明细ID value
	 * 
	 * @return the REPAIR_ORDER_DTL_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getREPAIR_ORDER_DTL_ID() {
		return REPAIR_ORDER_DTL_ID;
	}

	/**
	 * set 返修单明细ID value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setREPAIR_ORDER_DTL_ID(String REPAIR_ORDER_DTL_ID) {
		this.REPAIR_ORDER_DTL_ID = REPAIR_ORDER_DTL_ID;
	}

	/**
	 * get 返修单ID value
	 * 
	 * @return the ERP_REPAIR_ORDER_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getERP_REPAIR_ORDER_ID() {
		return ERP_REPAIR_ORDER_ID;
	}

	/**
	 * set 返修单ID value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setERP_REPAIR_ORDER_ID(String ERP_REPAIR_ORDER_ID) {
		this.ERP_REPAIR_ORDER_ID = ERP_REPAIR_ORDER_ID;
	}

	/**
	 * get 货品ID value
	 * 
	 * @return the PRD_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getPRD_ID() {
		return PRD_ID;
	}

	/**
	 * set 货品ID value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setPRD_ID(String PRD_ID) {
		this.PRD_ID = PRD_ID;
	}

	/**
	 * get 货品编号 value
	 * 
	 * @return the PRD_NO
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getPRD_NO() {
		return PRD_NO;
	}

	/**
	 * set 货品编号 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setPRD_NO(String PRD_NO) {
		this.PRD_NO = PRD_NO;
	}

	/**
	 * get 货品名称 value
	 * 
	 * @return the PRD_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}

	/**
	 * set 货品名称 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setPRD_NAME(String PRD_NAME) {
		this.PRD_NAME = PRD_NAME;
	}

	/**
	 * get 规格型号 value
	 * 
	 * @return the PRD_SPEC
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	/**
	 * set 规格型号 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setPRD_SPEC(String PRD_SPEC) {
		this.PRD_SPEC = PRD_SPEC;
	}

	/**
	 * get 颜色 value
	 * 
	 * @return the PRD_COLOR
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	/**
	 * set 颜色 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setPRD_COLOR(String PRD_COLOR) {
		this.PRD_COLOR = PRD_COLOR;
	}

	/**
	 * get 品牌 value
	 * 
	 * @return the BRAND
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getBRAND() {
		return BRAND;
	}

	/**
	 * set 品牌 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setBRAND(String BRAND) {
		this.BRAND = BRAND;
	}

	/**
	 * get 标准单位 value
	 * 
	 * @return the STD_UNIT
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	/**
	 * set 标准单位 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setSTD_UNIT(String STD_UNIT) {
		this.STD_UNIT = STD_UNIT;
	}

	/**
	 * get 返修数量 value
	 * 
	 * @return the REPAIR_NUM
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getREPAIR_NUM() {
		return REPAIR_NUM;
	}

	/**
	 * set 返修数量 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setREPAIR_NUM(String REPAIR_NUM) {
		this.REPAIR_NUM = REPAIR_NUM;
	}

	/**
	 * get 返修单价 value
	 * 
	 * @return the REPAIR_PRICE
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getREPAIR_PRICE() {
		return REPAIR_PRICE;
	}

	/**
	 * set 返修单价 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setREPAIR_PRICE(String REPAIR_PRICE) {
		this.REPAIR_PRICE = REPAIR_PRICE;
	}

	/**
	 * get 返修金额 value
	 * 
	 * @return the REPAIR_AMOUNT
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getREPAIR_AMOUNT() {
		return REPAIR_AMOUNT;
	}

	/**
	 * set 返修金额 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setREPAIR_AMOUNT(String REPAIR_AMOUNT) {
		this.REPAIR_AMOUNT = REPAIR_AMOUNT;
	}

	/**
	 * get 返修原因 value
	 * 
	 * @return the REPAIR_RESON
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getREPAIR_RESON() {
		return REPAIR_RESON;
	}

	/**
	 * set 返修原因 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setREPAIR_RESON(String REPAIR_RESON) {
		this.REPAIR_RESON = REPAIR_RESON;
	}

	/**
	 * get 返修附件 value
	 * 
	 * @return the REPAIR_ATT
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getREPAIR_ATT() {
		return REPAIR_ATT;
	}

	/**
	 * set 返修附件 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setREPAIR_ATT(String REPAIR_ATT) {
		this.REPAIR_ATT = REPAIR_ATT;
	}

	/**
	 * get 删除标记 value
	 * 
	 * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * set 删除标记 value
	 * 
	 * @createdate 2013-11-03 16:25:12
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
	 */
	public void setDEL_FLAG(String DEL_FLAG) {
		this.DEL_FLAG = DEL_FLAG;
	}

	public String getSPCL_TECH_FLAG() {
		return SPCL_TECH_FLAG;
	}

	public void setSPCL_TECH_FLAG(String spcl_tech_flag) {
		SPCL_TECH_FLAG = spcl_tech_flag;
	}

	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String spcl_tech_id) {
		SPCL_TECH_ID = spcl_tech_id;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	/**
	 * @return the sAFE_NUM
	 */
	public String getSAFE_NUM() {
		return SAFE_NUM;
	}

	/**
	 * @param sAFENUM
	 *            the sAFE_NUM to set
	 */
	public void setSAFE_NUM(String sAFENUM) {
		SAFE_NUM = sAFENUM;
	}

	public String getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	public String getTOTAL_VOLUME() {
		return TOTAL_VOLUME;
	}

	public void setTOTAL_VOLUME(String tOTALVOLUME) {
		TOTAL_VOLUME = tOTALVOLUME;
	}

}