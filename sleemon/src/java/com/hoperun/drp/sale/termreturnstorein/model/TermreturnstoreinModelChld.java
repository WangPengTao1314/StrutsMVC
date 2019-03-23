/**
 * prjName:喜临门营销平台
 * ucName:门店退货入库单
 * fileName:TermreturnstoreinModelChld
*/
package com.hoperun.drp.sale.termreturnstorein.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-27 15:46:33
 */
public class TermreturnstoreinModelChld extends BaseModel{
   /** 入库单明细ID **/
   private String STOREIN_DTL_ID;
   /** 入库单ID **/
   private String STOREIN_ID;
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
   /** 品牌 **/
   private String BRAND;
   /** 标准单位 **/
   private String STD_UNIT;
   /** 通知入库数量 **/
   private String NOTICE_NUM;
   /** 实际入库数量 **/
   private String REAL_NUM;
   /** 备注 **/
   private String REMARK;
   /** 单价 **/
   private String PRICE;
   /** 折扣率 **/
   private String DECT_RATE;
   /** 折扣价 **/
   private String DECT_PRICE;
   /** 折后金额 **/
   private String DECT_AMOUNT;
   /** 订单特殊工艺ID **/
   private String SPCL_TECH_ID;
   /** 税率 **/
   private String TAX_RATE;
   /** 不含税折后单价 **/
   private String NO_TAX_DECT_PRICE;
   /** 不含税折后金额 **/
   private String NO_TAX_DECT_AMOUNT;
   /** 税额 **/
   private String TAX_AMOUNT;
   /** 年份月份 **/
   private String YEAR_MONTH;
   /** 货品类型 **/
   private String PRD_TYPE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 入库单明细ID value
     * @return the STOREIN_DTL_ID
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getSTOREIN_DTL_ID(){
        return STOREIN_DTL_ID;
    }
	/**
     * set 入库单明细ID value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setSTOREIN_DTL_ID(String STOREIN_DTL_ID){
        this.STOREIN_DTL_ID=STOREIN_DTL_ID;
    }
     /**
     * get 入库单ID value
     * @return the STOREIN_ID
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getSTOREIN_ID(){
        return STOREIN_ID;
    }
	/**
     * set 入库单ID value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setSTOREIN_ID(String STOREIN_ID){
        this.STOREIN_ID=STOREIN_ID;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 通知入库数量 value
     * @return the NOTICE_NUM
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getNOTICE_NUM(){
        return NOTICE_NUM;
    }
	/**
     * set 通知入库数量 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setNOTICE_NUM(String NOTICE_NUM){
        this.NOTICE_NUM=NOTICE_NUM;
    }
     /**
     * get 实际入库数量 value
     * @return the REAL_NUM
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getREAL_NUM(){
        return REAL_NUM;
    }
	/**
     * set 实际入库数量 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setREAL_NUM(String REAL_NUM){
        this.REAL_NUM=REAL_NUM;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 订单特殊工艺ID value
     * @return the SPCL_TECH_ID
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getSPCL_TECH_ID(){
        return SPCL_TECH_ID;
    }
	/**
     * set 订单特殊工艺ID value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setSPCL_TECH_ID(String SPCL_TECH_ID){
        this.SPCL_TECH_ID=SPCL_TECH_ID;
    }
     /**
     * get 税率 value
     * @return the TAX_RATE
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getTAX_RATE(){
        return TAX_RATE;
    }
	/**
     * set 税率 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setTAX_RATE(String TAX_RATE){
        this.TAX_RATE=TAX_RATE;
    }
     /**
     * get 不含税折后单价 value
     * @return the NO_TAX_DECT_PRICE
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getNO_TAX_DECT_PRICE(){
        return NO_TAX_DECT_PRICE;
    }
	/**
     * set 不含税折后单价 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setNO_TAX_DECT_PRICE(String NO_TAX_DECT_PRICE){
        this.NO_TAX_DECT_PRICE=NO_TAX_DECT_PRICE;
    }
     /**
     * get 不含税折后金额 value
     * @return the NO_TAX_DECT_AMOUNT
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getNO_TAX_DECT_AMOUNT(){
        return NO_TAX_DECT_AMOUNT;
    }
	/**
     * set 不含税折后金额 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setNO_TAX_DECT_AMOUNT(String NO_TAX_DECT_AMOUNT){
        this.NO_TAX_DECT_AMOUNT=NO_TAX_DECT_AMOUNT;
    }
     /**
     * get 税额 value
     * @return the TAX_AMOUNT
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getTAX_AMOUNT(){
        return TAX_AMOUNT;
    }
	/**
     * set 税额 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setTAX_AMOUNT(String TAX_AMOUNT){
        this.TAX_AMOUNT=TAX_AMOUNT;
    }
     /**
     * get 年份月份 value
     * @return the YEAR_MONTH
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getYEAR_MONTH(){
        return YEAR_MONTH;
    }
	/**
     * set 年份月份 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setYEAR_MONTH(String YEAR_MONTH){
        this.YEAR_MONTH=YEAR_MONTH;
    }
     /**
     * get 货品类型 value
     * @return the PRD_TYPE
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getPRD_TYPE(){
        return PRD_TYPE;
    }
	/**
     * set 货品类型 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setPRD_TYPE(String PRD_TYPE){
        this.PRD_TYPE=PRD_TYPE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-10-27 15:46:33
     * @author lyg
	 * @createdate 2014-10-27 15:46:33
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}