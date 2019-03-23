﻿/**
 * prjName:喜临门营销平台
 * ucName:我的退货单
 * fileName:MyretrunModelChld
*/
package com.hoperun.drp.sale.myretrun.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-25 09:38:48
 */
public class MyretrunModelChld extends BaseModel{
   /** 退货单明细ID **/
   private String PRD_RETURN_DTL_ID;
   /** 退货单ID **/
   private String PRD_RETURN_ID;
   /** 来源单据明细ID **/
   private String FROM_BILL_DTL_ID;
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
   /** 责任认定 **/
   private String DUTY_DECIDE;
   /** 核价价格 **/
   private String PRICE_DECIDE;
   /** 实际退货金额 **/
   private String REAL_RETURN_AMOUNT;
   /** 核价说明 **/
   private String REMARK_DECIDE;
   /** 处理工厂ID **/
   private String DEAL_FACTORY_ID;
   /** 处理工厂 **/
   private String DEAL_FACTORY_NAME;
   /** 核价人ID **/
   private String DECIDE_PSON_ID;
   /** 核价人名称 **/
   private String DECIDE_PSON_NAME;
   /** 核价时间 **/
   private String DECIDE_TIME;
   /** 核价标记 **/
   private String DECIDE_FLAG;
   /** 删除标记 **/
   private String DEL_FLAG;
   
	private String SPCL_TECH_FLAG;

	private String SPCL_TECH_ID;

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
     * get 退货单明细ID value
     * @return the PRD_RETURN_DTL_ID
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_RETURN_DTL_ID(){
        return PRD_RETURN_DTL_ID;
    }
	/**
     * set 退货单明细ID value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_RETURN_DTL_ID(String PRD_RETURN_DTL_ID){
        this.PRD_RETURN_DTL_ID=PRD_RETURN_DTL_ID;
    }
     /**
     * get 退货单ID value
     * @return the PRD_RETURN_ID
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_RETURN_ID(){
        return PRD_RETURN_ID;
    }
	/**
     * set 退货单ID value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_RETURN_ID(String PRD_RETURN_ID){
        this.PRD_RETURN_ID=PRD_RETURN_ID;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 退货单价 value
     * @return the RETURN_PRICE
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getRETURN_PRICE(){
        return RETURN_PRICE;
    }
	/**
     * set 退货单价 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setRETURN_PRICE(String RETURN_PRICE){
        this.RETURN_PRICE=RETURN_PRICE;
    }
     /**
     * get 退货数量 value
     * @return the RETURN_NUM
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getRETURN_NUM(){
        return RETURN_NUM;
    }
	/**
     * set 退货数量 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setRETURN_NUM(String RETURN_NUM){
        this.RETURN_NUM=RETURN_NUM;
    }
     /**
     * get 退货金额 value
     * @return the RETURN_AMOUNT
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getRETURN_AMOUNT(){
        return RETURN_AMOUNT;
    }
	/**
     * set 退货金额 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setRETURN_AMOUNT(String RETURN_AMOUNT){
        this.RETURN_AMOUNT=RETURN_AMOUNT;
    }
     /**
     * get 原因归类 value
     * @return the RETURN_RSON_TYPE
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getRETURN_RSON_TYPE(){
        return RETURN_RSON_TYPE;
    }
	/**
     * set 原因归类 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setRETURN_RSON_TYPE(String RETURN_RSON_TYPE){
        this.RETURN_RSON_TYPE=RETURN_RSON_TYPE;
    }
     /**
     * get 退货原因 value
     * @return the RETURN_RSON
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getRETURN_RSON(){
        return RETURN_RSON;
    }
	/**
     * set 退货原因 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setRETURN_RSON(String RETURN_RSON){
        this.RETURN_RSON=RETURN_RSON;
    }
     /**
     * get 退货附件 value
     * @return the RETURN_ATT
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getRETURN_ATT(){
        return RETURN_ATT;
    }
	/**
     * set 退货附件 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setRETURN_ATT(String RETURN_ATT){
        this.RETURN_ATT=RETURN_ATT;
    }
     /**
     * get 责任认定 value
     * @return the DUTY_DECIDE
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDUTY_DECIDE(){
        return DUTY_DECIDE;
    }
	/**
     * set 责任认定 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDUTY_DECIDE(String DUTY_DECIDE){
        this.DUTY_DECIDE=DUTY_DECIDE;
    }
     /**
     * get 核价价格 value
     * @return the PRICE_DECIDE
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getPRICE_DECIDE(){
        return PRICE_DECIDE;
    }
	/**
     * set 核价价格 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setPRICE_DECIDE(String PRICE_DECIDE){
        this.PRICE_DECIDE=PRICE_DECIDE;
    }
     /**
     * get 实际退货金额 value
     * @return the REAL_RETURN_AMOUNT
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getREAL_RETURN_AMOUNT(){
        return REAL_RETURN_AMOUNT;
    }
	/**
     * set 实际退货金额 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setREAL_RETURN_AMOUNT(String REAL_RETURN_AMOUNT){
        this.REAL_RETURN_AMOUNT=REAL_RETURN_AMOUNT;
    }
     /**
     * get 核价说明 value
     * @return the REMARK_DECIDE
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getREMARK_DECIDE(){
        return REMARK_DECIDE;
    }
	/**
     * set 核价说明 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setREMARK_DECIDE(String REMARK_DECIDE){
        this.REMARK_DECIDE=REMARK_DECIDE;
    }
     /**
     * get 处理工厂ID value
     * @return the DEAL_FACTORY_ID
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDEAL_FACTORY_ID(){
        return DEAL_FACTORY_ID;
    }
	/**
     * set 处理工厂ID value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDEAL_FACTORY_ID(String DEAL_FACTORY_ID){
        this.DEAL_FACTORY_ID=DEAL_FACTORY_ID;
    }
     /**
     * get 处理工厂 value
     * @return the DEAL_FACTORY_NAME
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDEAL_FACTORY_NAME(){
        return DEAL_FACTORY_NAME;
    }
	/**
     * set 处理工厂 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDEAL_FACTORY_NAME(String DEAL_FACTORY_NAME){
        this.DEAL_FACTORY_NAME=DEAL_FACTORY_NAME;
    }
     /**
     * get 核价人ID value
     * @return the DECIDE_PSON_ID
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDECIDE_PSON_ID(){
        return DECIDE_PSON_ID;
    }
	/**
     * set 核价人ID value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDECIDE_PSON_ID(String DECIDE_PSON_ID){
        this.DECIDE_PSON_ID=DECIDE_PSON_ID;
    }
     /**
     * get 核价人名称 value
     * @return the DECIDE_PSON_NAME
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDECIDE_PSON_NAME(){
        return DECIDE_PSON_NAME;
    }
	/**
     * set 核价人名称 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDECIDE_PSON_NAME(String DECIDE_PSON_NAME){
        this.DECIDE_PSON_NAME=DECIDE_PSON_NAME;
    }
     /**
     * get 核价时间 value
     * @return the DECIDE_TIME
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDECIDE_TIME(){
        return DECIDE_TIME;
    }
	/**
     * set 核价时间 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDECIDE_TIME(String DECIDE_TIME){
        this.DECIDE_TIME=DECIDE_TIME;
    }
     /**
     * get 核价标记 value
     * @return the DECIDE_FLAG
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDECIDE_FLAG(){
        return DECIDE_FLAG;
    }
	/**
     * set 核价标记 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDECIDE_FLAG(String DECIDE_FLAG){
        this.DECIDE_FLAG=DECIDE_FLAG;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-25 09:38:48
     * @author wzg
	 * @createdate 2013-08-25 09:38:48
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}