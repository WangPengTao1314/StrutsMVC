/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：QDXXModel.java
 */
package com.hoperun.erp.sale.erpprmtprice.model;


/**
 * The Class ChannModel.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public class ErpprmtpriceModel {
	/**促销价格设定ID*/
	private String PRMT_PRICE_ID;
	/**促销方案ID*/
	private String PRMT_PLAN_ID;
	/**促销方案编号*/
	private String PRMT_PLAN_NO;
	/**促销方案名称*/
	private String PRMT_PLAN_NAME;
	/**促销类型*/
	private String PRMT_TYPE;
	/**生效日期从*/
	private String EFFCT_DATE_BEG;
	/**生效日期到*/
	private String EFFCT_DATE_END;
	/**货品ID*/
	private String PRD_ID;
	/**货品编号*/
	private String PRD_NO;
	/**货品名称*/
	private String PRD_NAME;
	/**规格型号*/
	private String PRD_SPEC;
	/**颜色*/
	private String PRD_COLOR;
	/**品牌*/
	private String BRAND;
	/**供货价格*/
	private String PRVD_PRICE;
	/**促销供货价格*/
	private String PRMT_PRVD_PRICE;
	/**状态*/
	private String STATE;
	/**备注*/
	private String REMARK;
	public String getPRMT_PRICE_ID() {
		return PRMT_PRICE_ID;
	}
	public void setPRMT_PRICE_ID(String pRMTPRICEID) {
		PRMT_PRICE_ID = pRMTPRICEID;
	}
	public String getPRMT_PLAN_ID() {
		return PRMT_PLAN_ID;
	}
	public void setPRMT_PLAN_ID(String pRMTPLANID) {
		PRMT_PLAN_ID = pRMTPLANID;
	}
	public String getPRMT_PLAN_NO() {
		return PRMT_PLAN_NO;
	}
	public void setPRMT_PLAN_NO(String pRMTPLANNO) {
		PRMT_PLAN_NO = pRMTPLANNO;
	}
	public String getPRMT_PLAN_NAME() {
		return PRMT_PLAN_NAME;
	}
	public void setPRMT_PLAN_NAME(String pRMTPLANNAME) {
		PRMT_PLAN_NAME = pRMTPLANNAME;
	}
	public String getPRMT_TYPE() {
		return PRMT_TYPE;
	}
	public void setPRMT_TYPE(String pRMTTYPE) {
		PRMT_TYPE = pRMTTYPE;
	}
	public String getEFFCT_DATE_BEG() {
		return EFFCT_DATE_BEG;
	}
	public void setEFFCT_DATE_BEG(String eFFCTDATEBEG) {
		EFFCT_DATE_BEG = eFFCTDATEBEG;
	}
	public String getEFFCT_DATE_END() {
		return EFFCT_DATE_END;
	}
	public void setEFFCT_DATE_END(String eFFCTDATEEND) {
		EFFCT_DATE_END = eFFCTDATEEND;
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
	public String getPRVD_PRICE() {
		return PRVD_PRICE;
	}
	public void setPRVD_PRICE(String pRVDPRICE) {
		PRVD_PRICE = pRVDPRICE;
	}
	public String getPRMT_PRVD_PRICE() {
		return PRMT_PRVD_PRICE;
	}
	public void setPRMT_PRVD_PRICE(String pRMTPRVDPRICE) {
		PRMT_PRVD_PRICE = pRMTPRVDPRICE;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
}