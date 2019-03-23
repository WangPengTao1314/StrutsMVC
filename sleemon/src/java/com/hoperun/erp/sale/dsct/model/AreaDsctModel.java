/**
 * 项目名称：喜临门
 * 系统名：区域折扣信息
 * 文件名：AreaDsctModel.java
 */
package com.hoperun.erp.sale.dsct.model;

/**
 * The Class AreaDsctModel.
 * 
 * @module 总部->销售管理->价格管理
 * @func 区域折扣信息
 * @version 1.0
 * @author 王志格
 */
public class AreaDsctModel {
	
	/*** 区域折扣ID*/
	private String AREA_DSCT_ID;
	/*** 区域ID*/
	private String AREA_ID;
	/*** 区域编号*/
	private String AREA_NO;
	/*** 区域名称*/
	private String AREA_NAME;
	/**货品ID**/
	private String PRD_ID;
	/**货品编号**/
	private String PRD_NO;
	/**货品名称**/
	private String PRD_NAME;
	/**规格**/
	private String PRD_SPEC;
	/** * 折扣类型 */
	private String DECT_TYPE;
	/** * 折扣率*/
	private String DECT_RATE;
	/**基准价**/
	private String BASE_PRICE;
	/**折扣价**/
	private String DECT_PRICE;
	
	public String getAREA_DSCT_ID() {
		return AREA_DSCT_ID;
	}
	public void setAREA_DSCT_ID(String aREADSCTID) {
		AREA_DSCT_ID = aREADSCTID;
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
	public String getDECT_TYPE() {
		return DECT_TYPE;
	}
	public void setDECT_TYPE(String dECTTYPE) {
		DECT_TYPE = dECTTYPE;
	}
	public String getDECT_RATE() {
		return DECT_RATE;
	}
	public void setDECT_RATE(String dECTRATE) {
		DECT_RATE = dECTRATE;
	}
	public String getBASE_PRICE() {
		return BASE_PRICE;
	}
	public void setBASE_PRICE(String bASEPRICE) {
		BASE_PRICE = bASEPRICE;
	}
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}
	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}
	
	
	

	 
}
