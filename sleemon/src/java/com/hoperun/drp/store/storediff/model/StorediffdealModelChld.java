/**
 * prjName:喜临门营销平台
 * ucName:入库差异确认1
 * fileName:StorediffdealModelChld
*/
package com.hoperun.drp.store.storediff.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-30 14:17:25
 */
public class StorediffdealModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -3449632012892104475L;
/** 差异处理明细ID **/
   private String DIFF_DEAL_DTL_ID;
   /** 入库差异单明细ID **/
   private String STOREIN_DIFF_DTL_ID;
   /** 入库差异单ID **/
   private String STOREIN_DIFF_ID;
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
   /** 差异数量 **/
   private String DIFF_NUM;
   /** 差异原因 **/
   private String DIFF_RSON;
   /** 处理方式 **/
   private String DEAL_WAY;
   /** 差异金额 **/
   private String DIFF_AMOUNT;
   /** 差异附件 **/
   private String DIFF_ATT;
   /** 备注 **/
   private String REMARK;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 差异处理明细ID value
     * @return the DIFF_DEAL_DTL_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDIFF_DEAL_DTL_ID(){
        return DIFF_DEAL_DTL_ID;
    }
	/**
     * set 差异处理明细ID value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDIFF_DEAL_DTL_ID(String DIFF_DEAL_DTL_ID){
        this.DIFF_DEAL_DTL_ID=DIFF_DEAL_DTL_ID;
    }
     /**
     * get 入库差异单明细ID value
     * @return the STOREIN_DIFF_DTL_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getSTOREIN_DIFF_DTL_ID(){
        return STOREIN_DIFF_DTL_ID;
    }
	/**
     * set 入库差异单明细ID value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setSTOREIN_DIFF_DTL_ID(String STOREIN_DIFF_DTL_ID){
        this.STOREIN_DIFF_DTL_ID=STOREIN_DIFF_DTL_ID;
    }
     /**
     * get 入库差异单ID value
     * @return the STOREIN_DIFF_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getSTOREIN_DIFF_ID(){
        return STOREIN_DIFF_ID;
    }
	/**
     * set 入库差异单ID value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setSTOREIN_DIFF_ID(String STOREIN_DIFF_ID){
        this.STOREIN_DIFF_ID=STOREIN_DIFF_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 差异数量 value
     * @return the DIFF_NUM
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDIFF_NUM(){
        return DIFF_NUM;
    }
	/**
     * set 差异数量 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDIFF_NUM(String DIFF_NUM){
        this.DIFF_NUM=DIFF_NUM;
    }
     /**
     * get 差异原因 value
     * @return the DIFF_RSON
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDIFF_RSON(){
        return DIFF_RSON;
    }
	/**
     * set 差异原因 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDIFF_RSON(String DIFF_RSON){
        this.DIFF_RSON=DIFF_RSON;
    }
     /**
     * get 处理方式 value
     * @return the DEAL_WAY
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDEAL_WAY(){
        return DEAL_WAY;
    }
	/**
     * set 处理方式 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDEAL_WAY(String DEAL_WAY){
        this.DEAL_WAY=DEAL_WAY;
    }
     /**
     * get 差异金额 value
     * @return the DIFF_AMOUNT
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDIFF_AMOUNT(){
        return DIFF_AMOUNT;
    }
	/**
     * set 差异金额 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDIFF_AMOUNT(String DIFF_AMOUNT){
        this.DIFF_AMOUNT=DIFF_AMOUNT;
    }
     /**
     * get 差异附件 value
     * @return the DIFF_ATT
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDIFF_ATT(){
        return DIFF_ATT;
    }
	/**
     * set 差异附件 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDIFF_ATT(String DIFF_ATT){
        this.DIFF_ATT=DIFF_ATT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-30 14:17:25
     * @author wzg
	 * @createdate 2013-08-30 14:17:25
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}