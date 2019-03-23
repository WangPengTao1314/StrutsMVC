/**
 * prjName:喜临门营销平台
 * ucName:门店-退货出库单
 * fileName:TermreturnstoreoutModelChld
*/
package com.hoperun.drp.sale.termreturnstoreout.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-31 10:42:20
 */
public class TermreturnstoreoutModelChld extends BaseModel{
   /** 退货出库单明细ID **/
   private String STOREOUT_DTL_ID;
   /** 退货出库单ID **/
   private String STOREOUT_ID;
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
   /** 通知出库数量 **/
   private String NOTICE_NUM;
   /** 单价 **/
   private String PRICE;
   /** 折扣率 **/
   private String DECT_RATE;
   /** 折扣价 **/
   private String DECT_PRICE;
   /** 折后金额 **/
   private String DECT_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 来源单据明细ID **/
   private String FROM_BILL_DTL_ID;
   /** 订单特殊工艺ID **/
   private String SPCL_TECH_ID;
   /** 实际出库数量 **/
   private String REAL_NUM;
   /** 年份月份 **/
   private String YEAR_MONTH;
   /** 货品类型 **/
   private String PRD_TYPE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 退货出库单明细ID value
     * @return the STOREOUT_DTL_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_DTL_ID(){
        return STOREOUT_DTL_ID;
    }
	/**
     * set 退货出库单明细ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_DTL_ID(String STOREOUT_DTL_ID){
        this.STOREOUT_DTL_ID=STOREOUT_DTL_ID;
    }
     /**
     * get 退货出库单ID value
     * @return the STOREOUT_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_ID(){
        return STOREOUT_ID;
    }
	/**
     * set 退货出库单ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_ID(String STOREOUT_ID){
        this.STOREOUT_ID=STOREOUT_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 通知出库数量 value
     * @return the NOTICE_NUM
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getNOTICE_NUM(){
        return NOTICE_NUM;
    }
	/**
     * set 通知出库数量 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setNOTICE_NUM(String NOTICE_NUM){
        this.NOTICE_NUM=NOTICE_NUM;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 订单特殊工艺ID value
     * @return the SPCL_TECH_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSPCL_TECH_ID(){
        return SPCL_TECH_ID;
    }
	/**
     * set 订单特殊工艺ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSPCL_TECH_ID(String SPCL_TECH_ID){
        this.SPCL_TECH_ID=SPCL_TECH_ID;
    }
     /**
     * get 实际出库数量 value
     * @return the REAL_NUM
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getREAL_NUM(){
        return REAL_NUM;
    }
	/**
     * set 实际出库数量 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setREAL_NUM(String REAL_NUM){
        this.REAL_NUM=REAL_NUM;
    }
     /**
     * get 年份月份 value
     * @return the YEAR_MONTH
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getYEAR_MONTH(){
        return YEAR_MONTH;
    }
	/**
     * set 年份月份 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setYEAR_MONTH(String YEAR_MONTH){
        this.YEAR_MONTH=YEAR_MONTH;
    }
     /**
     * get 货品类型 value
     * @return the PRD_TYPE
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getPRD_TYPE(){
        return PRD_TYPE;
    }
	/**
     * set 货品类型 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setPRD_TYPE(String PRD_TYPE){
        this.PRD_TYPE=PRD_TYPE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}