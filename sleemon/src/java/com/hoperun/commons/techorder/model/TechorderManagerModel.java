package com.hoperun.commons.techorder.model;
/**
 * 订单特殊工艺主表
 * @author liu_yuegang
 *
 */
public class TechorderManagerModel {
	/**
	 * 订单特殊工艺ID
	 */
	private String SPCL_TECH_ID;
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
	 * 换算关系
	 */
	private String RATIO;
	/**
	 * 货品类别
	 */
	private String PRD_TYPE;
	/**
	 * 材质
	 */
	private String PRD_MATL;
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	/**
	 * 使用标记
	 */
	private String USE_FLAG;
	/**
	 * 单价
	 */
	private String PRICE;
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	/**
	 * 折后单价
	 */
	private String DECT_PRICE;
	/**
	 * 特殊工艺加价倍数
	 */
	private String TECH_MULT;
	/**
	 * 特殊工艺加价 金额
	 */
	private String TECH_AMOUNT;
	/**
	 * 特殊工艺核价
	 */
	private String TECH_CHECK_PRICE;
	/**
	 * 特殊工艺标记
	 */
	private String SPCL_TECH_FLAG;
	
	/**
	 * @return the sPCL_TECH_ID
	 */
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	/**
	 * @param sPCLTECHID the sPCL_TECH_ID to set
	 */
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
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
	 * @return the rATIO
	 */
	public String getRATIO() {
		return RATIO;
	}
	/**
	 * @param rATIO the rATIO to set
	 */
	public void setRATIO(String rATIO) {
		RATIO = rATIO;
	}
	
	/**
	 * @return the pRD_TYPE
	 */
	public String getPRD_TYPE() {
		return PRD_TYPE;
	}
	/**
	 * @param pRDTYPE the pRD_TYPE to set
	 */
	public void setPRD_TYPE(String pRDTYPE) {
		PRD_TYPE = pRDTYPE;
	}
	/**
	 * @return the pRD_MATL
	 */
	public String getPRD_MATL() {
		return PRD_MATL;
	}
	/**
	 * @param pRDMATL the pRD_MATL to set
	 */
	public void setPRD_MATL(String pRDMATL) {
		PRD_MATL = pRDMATL;
	}
	/**
	 * @return the dEL_FLAG
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	/**
	 * @return the uSE_FLAG
	 */
	public String getUSE_FLAG() {
		return USE_FLAG;
	}
	/**
	 * @param uSEFLAG the uSE_FLAG to set
	 */
	public void setUSE_FLAG(String uSEFLAG) {
		USE_FLAG = uSEFLAG;
	}
	/**
	 * @return the pRICE
	 */
	public String getPRICE() {
		return PRICE;
	}
	/**
	 * @param pRICE the pRICE to set
	 */
	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}
	/**
	 * @return the dECT_RATE
	 */
	public String getDECT_RATE() {
		return DECT_RATE;
	}
	/**
	 * @param dECTRATE the dECT_RATE to set
	 */
	public void setDECT_RATE(String dECTRATE) {
		DECT_RATE = dECTRATE;
	}
	/**
	 * @return the dECT_PRICE
	 */
	public String getDECT_PRICE() {
		return DECT_PRICE;
	}
	/**
	 * @param dECTPRICE the dECT_PRICE to set
	 */
	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}
	/**
	 * @return the tECH_MULT
	 */
	public String getTECH_MULT() {
		return TECH_MULT;
	}
	/**
	 * @param tECHMULT the tECH_MULT to set
	 */
	public void setTECH_MULT(String tECHMULT) {
		TECH_MULT = tECHMULT;
	}
	/**
	 * @return the tECH_AMOUNT
	 */
	public String getTECH_AMOUNT() {
		return TECH_AMOUNT;
	}
	/**
	 * @param tECHAMOUNT the tECH_AMOUNT to set
	 */
	public void setTECH_AMOUNT(String tECHAMOUNT) {
		TECH_AMOUNT = tECHAMOUNT;
	}
	/**
	 * @return the tECH_CHECK_PRICE
	 */
	public String getTECH_CHECK_PRICE() {
		return TECH_CHECK_PRICE;
	}
	/**
	 * @param tECHCHECKPRICE the tECH_CHECK_PRICE to set
	 */
	public void setTECH_CHECK_PRICE(String tECHCHECKPRICE) {
		TECH_CHECK_PRICE = tECHCHECKPRICE;
	}
	/**
	 * @return the sPCL_TECH_FLAG
	 */
	public String getSPCL_TECH_FLAG() {
		return SPCL_TECH_FLAG;
	}
	/**
	 * @param sPCLTECHFLAG the sPCL_TECH_FLAG to set
	 */
	public void setSPCL_TECH_FLAG(String sPCLTECHFLAG) {
		SPCL_TECH_FLAG = sPCLTECHFLAG;
	}
	
}
