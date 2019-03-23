package com.hoperun.base.prdgroup.model;

import com.hoperun.commons.model.BaseModel;

/**
 * 货品组 明细
 * @author zhang_zhongbin
 *
 */
public class PrdGroupDtlModel extends BaseModel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//货品组明细ID
	private String PRD_GROUP_DTL_ID;
	//货品组ID
	private String PRD_GROUP_ID;
	//货品ID
	private String PRD_ID;
	//货品编号
	private String PRD_NO;
	//货品名称 
	private String PRD_NAME;
	//规格型号 
	private String PRD_SPEC;
	//颜色 
	private String PRD_COLOR;
	//品牌 
	private String BRAND;
	//标准单位 
	private String STD_UNIT;
	
	//删除标记
	private String DEL_FLAG;
	
	
	
 
	public String getPRD_GROUP_DTL_ID() {
		return PRD_GROUP_DTL_ID;
	}
	public void setPRD_GROUP_DTL_ID(String pRDGROUPDTLID) {
		PRD_GROUP_DTL_ID = pRDGROUPDTLID;
	}
	public String getPRD_GROUP_ID() {
		return PRD_GROUP_ID;
	}
	public void setPRD_GROUP_ID(String pRDGROUPID) {
		PRD_GROUP_ID = pRDGROUPID;
	}
	public String getPRD_ID() {
		return PRD_ID;
	}
	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}
	public String getPRD_NO() {
		return PRD_NO;
	}
	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}
	public String getPRD_NAME() {
		return PRD_NAME;
	}
	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}
	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}
	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
	}
	public String getBRAND() {
		return BRAND;
	}
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}
	public String getSTD_UNIT() {
		return STD_UNIT;
	}
	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	

}
