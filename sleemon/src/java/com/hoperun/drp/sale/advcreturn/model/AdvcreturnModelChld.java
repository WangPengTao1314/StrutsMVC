/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnModelChld
*/
package com.hoperun.drp.sale.advcreturn.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class AdvcreturnModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -1320211990755703520L;
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
   /** 单价 **/
   private String PRICE;
   /** 折扣率 **/
   private String DECT_RATE;
   /** 退货单价 **/
   private String DECT_PRICE;
   /** 退货数量 **/
   private String ORDER_NUM;
   /** 应退金额 **/
   private String PAYABLE_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 会话ID **/
   private String SESSION_ID;
   /** 特殊工艺加价倍数 **/
   private String TECH_PRICE_MULT;
   /** 来源单据明细 **/
   private String FROM_BILL_DTL_ID;
   /**退货附件**/
   private String RETURN_ATT;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 订单特殊工艺ID**/
   private String SPCL_TECH_ID;
     /**
     * get 预订单明细ID value
     * @return the ADVC_ORDER_DTL_ID
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getADVC_ORDER_DTL_ID(){
        return ADVC_ORDER_DTL_ID;
    }
	/**
     * set 预订单明细ID value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setADVC_ORDER_DTL_ID(String ADVC_ORDER_DTL_ID){
        this.ADVC_ORDER_DTL_ID=ADVC_ORDER_DTL_ID;
    }
     /**
     * get 预订单ID value
     * @return the ADVC_ORDER_ID
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getADVC_ORDER_ID(){
        return ADVC_ORDER_ID;
    }
	/**
     * set 预订单ID value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setADVC_ORDER_ID(String ADVC_ORDER_ID){
        this.ADVC_ORDER_ID=ADVC_ORDER_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 退货单价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 退货单价 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 退货数量 value
     * @return the ORDER_NUM
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getORDER_NUM(){
        return ORDER_NUM;
    }
	/**
     * set 退货数量 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setORDER_NUM(String ORDER_NUM){
        this.ORDER_NUM=ORDER_NUM;
    }
     /**
     * get 应退金额 value
     * @return the PAYABLE_AMOUNT
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getPAYABLE_AMOUNT(){
        return PAYABLE_AMOUNT;
    }
	/**
     * set 应退金额 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setPAYABLE_AMOUNT(String PAYABLE_AMOUNT){
        this.PAYABLE_AMOUNT=PAYABLE_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 会话ID value
     * @return the SESSION_ID
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getSESSION_ID(){
        return SESSION_ID;
    }
	/**
     * set 会话ID value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setSESSION_ID(String SESSION_ID){
        this.SESSION_ID=SESSION_ID;
    }
     /**
     * get 特殊工艺加价倍数 value
     * @return the TECH_PRICE_MULT
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getTECH_PRICE_MULT(){
        return TECH_PRICE_MULT;
    }
	/**
     * set 特殊工艺加价倍数 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setTECH_PRICE_MULT(String TECH_PRICE_MULT){
        this.TECH_PRICE_MULT=TECH_PRICE_MULT;
    }
     /**
     * get 来源单据明细 value
     * @return the FROM_BILL_DTL_ID
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-19 14:35:52
     * @author lyg
	 * @createdate 2013-08-19 14:35:52
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	/**
	 * @return the rETURN_ATT
	 */
	public String getRETURN_ATT() {
		return RETURN_ATT;
	}
	/**
	 * @param rETURNATT the rETURN_ATT to set
	 */
	public void setRETURN_ATT(String rETURNATT) {
		RETURN_ATT = rETURNATT;
	}
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
    
}