/**
 * prjName:喜临门营销平台
 * ucName:推广促销方案维护
 * fileName:PrmtplanModelChld
 */
package com.hoperun.erp.sale.prmtplan.model;

import com.hoperun.commons.model.BaseModel;

/**
 * 促销发布生效区域
 * *@module *@func *@version 1.1 *@author zzb *@createdate 2013-08-23 16:04:28
 */
public class PrmtplaAreaModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 促销货品组明细ID **/
	private String PRMT_EFFCT_AREA_ID;
	/** 促销方案ID **/
	private String PRMT_PLAN_ID;
	/** 货品组ID **/
	private String AREA_ID;
	/** 货品组编号 **/
	private String AREA_NO;
	/** 货品组名称 **/
	private String AREA_NAME;
	/** 删除标记 **/
	private String DEL_FLAG;
	

	public String getPRMT_EFFCT_AREA_ID() {
		return PRMT_EFFCT_AREA_ID;
	}

	public void setPRMT_EFFCT_AREA_ID(String pRMTEFFCTAREAID) {
		PRMT_EFFCT_AREA_ID = pRMTEFFCTAREAID;
	}

	public String getPRMT_PLAN_ID() {
		return PRMT_PLAN_ID;
	}

	public void setPRMT_PLAN_ID(String pRMTPLANID) {
		PRMT_PLAN_ID = pRMTPLANID;
	}

	public String getAREA_ID() {
		return AREA_ID;
	}

	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}

	public String getAREA_NO() {
		return AREA_NO;
	}

	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}

	public String getAREA_NAME() {
		return AREA_NAME;
	}

	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

}