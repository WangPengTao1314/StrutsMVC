﻿/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderModelGchld
*/
package com.hoperun.drp.sale.advccheckprice.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:38:52
 */
public class AdvccheckpriceModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 8385962518424315652L;
/** 预订单明细ID **/
   private String ADVC_ORDER_DTL_ID;
   /** 预订单ID **/
   private String ADVC_ORDER_ID;
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
   /**特殊工艺**/
   private String SPCL_TECH;
   /** 单价 **/
   private double PRICE;
   /** 折扣率 **/
   private double DECT_RATE;
   /** 折后单价 **/
   private double DECT_PRICE;
   /** 订货数量 **/
   private Integer ORDER_NUM;
   /** 应收金额 **/
   private double PAYABLE_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 会话ID **/
   private String SESSION_ID;
   /** 特殊工艺加价倍数 **/
   private String TECH_PRICE_MULT;
   /**特殊工艺标记**/
   private String SPCL_TECH_FLAG;
   
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
     * get 预订单明细ID value
     * @return the ADVC_ORDER_DTL_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getADVC_ORDER_DTL_ID(){
        return ADVC_ORDER_DTL_ID;
    }
	/**
     * set 预订单明细ID value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setADVC_ORDER_DTL_ID(String ADVC_ORDER_DTL_ID){
        this.ADVC_ORDER_DTL_ID=ADVC_ORDER_DTL_ID;
    }
     /**
     * get 预订单ID value
     * @return the ADVC_ORDER_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getADVC_ORDER_ID(){
        return ADVC_ORDER_ID;
    }
	/**
     * set 预订单ID value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setADVC_ORDER_ID(String ADVC_ORDER_ID){
        this.ADVC_ORDER_ID=ADVC_ORDER_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public double getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPRICE(double PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public double getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setDECT_RATE(double DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折后单价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public double getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折后单价 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setDECT_PRICE(double DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 订货数量 value
     * @return the ORDER_NUM
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public Integer getORDER_NUM(){
        return ORDER_NUM;
    }
	/**
     * set 订货数量 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setORDER_NUM(Integer ORDER_NUM){
        this.ORDER_NUM=ORDER_NUM;
    }
     /**
     * get 应收金额 value
     * @return the PAYABLE_AMOUNT
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public double getPAYABLE_AMOUNT(){
        return PAYABLE_AMOUNT;
    }
	/**
     * set 应收金额 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setPAYABLE_AMOUNT(double PAYABLE_AMOUNT){
        this.PAYABLE_AMOUNT=PAYABLE_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-11 17:38:52
     * @author lyg
	 * @createdate 2013-08-11 17:38:52
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
    /**
     * get 会话id value
     * @return the SESSION_ID
	 * @author lyg
	 * @createdate 2013-12-25 19:19:52
     */
	public String getSESSION_ID() {
		return SESSION_ID;
	}
	/**
     * set 会话id value
     * @createdate 2013-12-25 19:19:52
     * @author lyg
	 * @createdate 2013-12-25 19:19:52
     */
	public void setSESSION_ID(String sESSIONID) {
		SESSION_ID = sESSIONID;
	}
	/**
     * get 特殊工艺加价倍数 value
     * @return the TECH_PRICE_MULT
	 * @author lyg
	 * @createdate 2013-12-25 19:19:52
     */
	public String getTECH_PRICE_MULT() {
		return TECH_PRICE_MULT;
	}
	/**
     * set 特殊工艺加价倍数 value
     * @createdate 2013-12-25 19:19:52
     * @author lyg
	 * @createdate 2013-12-25 19:19:52
     */
	public void setTECH_PRICE_MULT(String tECHPRICEMULT) {
		TECH_PRICE_MULT = tECHPRICEMULT;
	}
	/**
	 * @return the sPCL_TECH
	 */
	public String getSPCL_TECH() {
		return SPCL_TECH;
	}
	/**
	 * @param sPCLTECH the sPCL_TECH to set
	 */
	public void setSPCL_TECH(String sPCLTECH) {
		SPCL_TECH = sPCLTECH;
	}
    
}