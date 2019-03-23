/**
 * prjName:喜临门营销平台
 * ucName:门店下级退货单
 * fileName:DrpreturnaModelChld
*/
package com.hoperun.drp.sale.drpreturna.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-26 15:41:36
 */
public class DrpreturnaModelChld extends BaseModel{
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
   /** 退货原因 **/
   private String RETURN_RSON;
   /** 年份月份 **/
   private String YEAR_MONTH;
   /** 货品类型 **/
   private String PRD_TYPE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 退货单明细ID value
     * @return the PRD_RETURN_DTL_ID
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_RETURN_DTL_ID(){
        return PRD_RETURN_DTL_ID;
    }
	/**
     * set 退货单明细ID value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_RETURN_DTL_ID(String PRD_RETURN_DTL_ID){
        this.PRD_RETURN_DTL_ID=PRD_RETURN_DTL_ID;
    }
     /**
     * get 退货单ID value
     * @return the PRD_RETURN_ID
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_RETURN_ID(){
        return PRD_RETURN_ID;
    }
	/**
     * set 退货单ID value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_RETURN_ID(String PRD_RETURN_ID){
        this.PRD_RETURN_ID=PRD_RETURN_ID;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 退货单价 value
     * @return the RETURN_PRICE
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getRETURN_PRICE(){
        return RETURN_PRICE;
    }
	/**
     * set 退货单价 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setRETURN_PRICE(String RETURN_PRICE){
        this.RETURN_PRICE=RETURN_PRICE;
    }
     /**
     * get 退货数量 value
     * @return the RETURN_NUM
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getRETURN_NUM(){
        return RETURN_NUM;
    }
	/**
     * set 退货数量 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setRETURN_NUM(String RETURN_NUM){
        this.RETURN_NUM=RETURN_NUM;
    }
     /**
     * get 退货金额 value
     * @return the RETURN_AMOUNT
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getRETURN_AMOUNT(){
        return RETURN_AMOUNT;
    }
	/**
     * set 退货金额 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setRETURN_AMOUNT(String RETURN_AMOUNT){
        this.RETURN_AMOUNT=RETURN_AMOUNT;
    }
     /**
     * get 退货原因 value
     * @return the RETURN_RSON
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getRETURN_RSON(){
        return RETURN_RSON;
    }
	/**
     * set 退货原因 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setRETURN_RSON(String RETURN_RSON){
        this.RETURN_RSON=RETURN_RSON;
    }
     /**
     * get 年份月份 value
     * @return the YEAR_MONTH
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getYEAR_MONTH(){
        return YEAR_MONTH;
    }
	/**
     * set 年份月份 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setYEAR_MONTH(String YEAR_MONTH){
        this.YEAR_MONTH=YEAR_MONTH;
    }
     /**
     * get 货品类型 value
     * @return the PRD_TYPE
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getPRD_TYPE(){
        return PRD_TYPE;
    }
	/**
     * set 货品类型 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setPRD_TYPE(String PRD_TYPE){
        this.PRD_TYPE=PRD_TYPE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-10-26 15:41:36
     * @author lyg
	 * @createdate 2014-10-26 15:41:36
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}