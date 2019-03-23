/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProductModel.java
 */

package com.hoperun.drp.main.shopcar.model;

import com.hoperun.commons.model.BaseModel;

/**
 * The Class ProductModel.
 * 
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
public class ShopcarModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5872301494782550789L;
	/**
	 * 购物车ID
	 */
	private String SHOP_CART_ID;
	
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
	 * 特殊工艺标记
	 */
	private String SPCL_TECH_FLAG;
	
	/**
	 * 单价
	 */
	private String PRICE;
	
	/**
	 * 折扣率
	 */
	private String DECT_RATE;
	
	/**
	 * 折扣价
	 */
	private String DECT_PRICE;
	
	/**
	 * 订货数量
	 */
	private String ORDER_NUM;
	
	/**
	 * 订货金额
	 */
	private String ORDER_AMOUNT;
	
	/**
	 * 会话id
	 */
	private String SESSION_ID;
	
	/**
	 * 处理标记
	 */
	private String DEAL_FLAG;
	/**
	 * 体积
	 */
	private String VOLUME;
	/**
	 * 总体积
	 */
	private String TOTAL_VOLUME;
	
	/**
	 * 特殊工艺标记ID
	 */
	private String SPCL_TECH_ID;
	
	/**交货日期**/
	private String ORDER_RECV_DATE;
	/**特殊工艺加价倍数**/
	private String TECH_MULT;
	/**特殊工艺加价 金额**/
	private String TECH_AMOUNT;
	/**备注 **/
	private String  REMARK;

	
	
	
	/**
	 * @return the sHOP_CART_ID
	 */
	public String getSHOP_CART_ID() {
		return SHOP_CART_ID;
	}

	/**
	 * @param sHOPCARTID the sHOP_CART_ID to set
	 */
	public void setSHOP_CART_ID(String sHOPCARTID) {
		SHOP_CART_ID = sHOPCARTID;
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
	 * @return the oRDER_NUM
	 */
	public String getORDER_NUM() {
		return ORDER_NUM;
	}

	/**
	 * @param oRDERNUM the oRDER_NUM to set
	 */
	public void setORDER_NUM(String oRDERNUM) {
		ORDER_NUM = oRDERNUM;
	}

	/**
	 * @return the oRDER_AMOUNT
	 */
	public String getORDER_AMOUNT() {
		return ORDER_AMOUNT;
	}

	/**
	 * @param oRDERAMOUNT the oRDER_AMOUNT to set
	 */
	public void setORDER_AMOUNT(String oRDERAMOUNT) {
		ORDER_AMOUNT = oRDERAMOUNT;
	}

	/**
	 * @return the sESSION_ID
	 */
	public String getSESSION_ID() {
		return SESSION_ID;
	}

	/**
	 * @param sESSIONID the sESSION_ID to set
	 */
	public void setSESSION_ID(String sESSIONID) {
		SESSION_ID = sESSIONID;
	}

	/**
	 * @return the dEAL_FLAG
	 */
	public String getDEAL_FLAG() {
		return DEAL_FLAG;
	}

	/**
	 * @param dEALFLAG the dEAL_FLAG to set
	 */
	public void setDEAL_FLAG(String dEALFLAG) {
		DEAL_FLAG = dEALFLAG;
	}

	/**
	 * @return the vOLUME
	 */
	public String getVOLUME() {
		return VOLUME;
	}

	/**
	 * @param vOLUME the vOLUME to set
	 */
	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	/**
	 * @return the tOTAL_VOLUME
	 */
	public String getTOTAL_VOLUME() {
		return TOTAL_VOLUME;
	}

	/**
	 * @param tOTALVOLUME the tOTAL_VOLUME to set
	 */
	public void setTOTAL_VOLUME(String tOTALVOLUME) {
		TOTAL_VOLUME = tOTALVOLUME;
	}

	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String spcl_tech_id) {
		SPCL_TECH_ID = spcl_tech_id;
	}

	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}

	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
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

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	
	

}
