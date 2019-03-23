/**
 * prjName:喜临门营销平台
 * ucName:分发指令接收
 * fileName:DstrorderModelChld
*/
package com.hoperun.drp.sale.dstrorder.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-11 18:59:32
 */
public class DstrorderModelChld extends BaseModel{
   private static final long serialVersionUID = 376895945106255687L;
   /** 分发指令接收明细ID **/
   private String DSTR_ORDER_DTL_ID;
   /** 分发指令接收ID **/
   private String DSTR_ORDER_ID;
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
   /** 单价 **/
   private String PRICE;
   /** 折扣率 **/
   private String DECT_RATE;
   /** 折扣价 **/
   private String DECT_PRICE;
   /** 订货数量 **/
   private String ORDER_NUM;
   /** 分发数量 **/
   private String DSTR_NUM;
   /** 折后金额 **/
   private String DECT_AMOUNT;
   /** 删除标记 **/
   private String DEL_FLAG;
   
   /** 特殊工艺标记 **/
   private String SPCL_TECH_FLAG;
   
   /** 订单特殊工艺 **/
   private String SPCL_TECH_ID;
   
   /** 销售订单ID **/
   private String SALE_ORDER_ID;
   
   /** 销售订单编号 **/
   private String SALE_ORDER_NO;
   
   /** 销售订单明细ID **/
   private String SALE_ORDER_DTL_ID;
   
   /** 订货订单编号 **/
   private String GOODS_ORDER_NO;
   
   
     /**
     * get 分发指令接收明细ID value
     * @return the DSTR_ORDER_DTL_ID
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDSTR_ORDER_DTL_ID(){
        return DSTR_ORDER_DTL_ID;
    }
	/**
     * set 分发指令接收明细ID value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDSTR_ORDER_DTL_ID(String DSTR_ORDER_DTL_ID){
        this.DSTR_ORDER_DTL_ID=DSTR_ORDER_DTL_ID;
    }
     /**
     * get 分发指令接收ID value
     * @return the DSTR_ORDER_ID
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDSTR_ORDER_ID(){
        return DSTR_ORDER_ID;
    }
	/**
     * set 分发指令接收ID value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDSTR_ORDER_ID(String DSTR_ORDER_ID){
        this.DSTR_ORDER_ID=DSTR_ORDER_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 订货数量 value
     * @return the ORDER_NUM
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getORDER_NUM(){
        return ORDER_NUM;
    }
	/**
     * set 订货数量 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setORDER_NUM(String ORDER_NUM){
        this.ORDER_NUM=ORDER_NUM;
    }
     /**
     * get 分发数量 value
     * @return the DSTR_NUM
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDSTR_NUM(){
        return DSTR_NUM;
    }
	/**
     * set 分发数量 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDSTR_NUM(String DSTR_NUM){
        this.DSTR_NUM=DSTR_NUM;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-11 18:59:32
     * @author glw
	 * @createdate 2013-08-11 18:59:32
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
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
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	public void setSALE_ORDER_ID(String sale_order_id) {
		SALE_ORDER_ID = sale_order_id;
	}
	public String getSALE_ORDER_NO() {
		return SALE_ORDER_NO;
	}
	public void setSALE_ORDER_NO(String sale_order_no) {
		SALE_ORDER_NO = sale_order_no;
	}
	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}
	public void setSALE_ORDER_DTL_ID(String sale_order_dtl_id) {
		SALE_ORDER_DTL_ID = sale_order_dtl_id;
	}
	public String getGOODS_ORDER_NO() {
		return GOODS_ORDER_NO;
	}
	public void setGOODS_ORDER_NO(String goods_order_no) {
		GOODS_ORDER_NO = goods_order_no;
	}
}