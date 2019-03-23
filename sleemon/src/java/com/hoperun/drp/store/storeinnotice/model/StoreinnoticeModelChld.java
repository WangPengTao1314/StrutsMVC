/**
 * prjName:喜临门营销平台
 * ucName:入库通知单维护
 * fileName:StoreinnoticeModelChld
*/
package com.hoperun.drp.store.storeinnotice.model;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-17 17:07:03
 */
public class StoreinnoticeModelChld{
   /** 入库通知单明细ID **/
   private String STOREIN_NOTICE_DTL_ID;
   /** 入库通知单ID **/
   private String STOREIN_NOTICE_ID;
   /** 入库库房ID **/
   private String STOREIN_STORE_ID;
   /** 入库库房编号 **/
   private String STOREIN_STORE_NO;
   /** 入库库房名称 **/
   private String STOREIN_STORE_NAME;
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
   /** 通知入库数量 **/
   private String NOTICE_NUM;
   /** 折后金额 **/
   private String DECT_AMOUNT;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 备注 **/
   private String REMARK;
   
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
     * get 入库通知单明细ID value
     * @return the STOREIN_NOTICE_DTL_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_NOTICE_DTL_ID(){
        return STOREIN_NOTICE_DTL_ID;
    }
	/**
     * set 入库通知单明细ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_NOTICE_DTL_ID(String STOREIN_NOTICE_DTL_ID){
        this.STOREIN_NOTICE_DTL_ID=STOREIN_NOTICE_DTL_ID;
    }
     /**
     * get 入库通知单ID value
     * @return the STOREIN_NOTICE_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_NOTICE_ID(){
        return STOREIN_NOTICE_ID;
    }
	/**
     * set 入库通知单ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_NOTICE_ID(String STOREIN_NOTICE_ID){
        this.STOREIN_NOTICE_ID=STOREIN_NOTICE_ID;
    }
     /**
     * get 入库库房ID value
     * @return the STOREIN_STORE_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_STORE_ID(){
        return STOREIN_STORE_ID;
    }
	/**
     * set 入库库房ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_STORE_ID(String STOREIN_STORE_ID){
        this.STOREIN_STORE_ID=STOREIN_STORE_ID;
    }
     /**
     * get 入库库房编号 value
     * @return the STOREIN_STORE_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_STORE_NO(){
        return STOREIN_STORE_NO;
    }
	/**
     * set 入库库房编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_STORE_NO(String STOREIN_STORE_NO){
        this.STOREIN_STORE_NO=STOREIN_STORE_NO;
    }
     /**
     * get 入库库房名称 value
     * @return the STOREIN_STORE_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_STORE_NAME(){
        return STOREIN_STORE_NAME;
    }
	/**
     * set 入库库房名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_STORE_NAME(String STOREIN_STORE_NAME){
        this.STOREIN_STORE_NAME=STOREIN_STORE_NAME;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 通知入库数量 value
     * @return the NOTICE_NUM
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getNOTICE_NUM(){
        return NOTICE_NUM;
    }
	/**
     * set 通知入库数量 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setNOTICE_NUM(String NOTICE_NUM){
        this.NOTICE_NUM=NOTICE_NUM;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getREMARK() {
		return REMARK;
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
	public String getGOODS_ORDER_NO() {
		return GOODS_ORDER_NO;
	}
	public void setGOODS_ORDER_NO(String goods_order_no) {
		GOODS_ORDER_NO = goods_order_no;
	}
	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}
	public void setSALE_ORDER_DTL_ID(String sale_order_dtl_id) {
		SALE_ORDER_DTL_ID = sale_order_dtl_id;
	}
}