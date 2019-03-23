/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderModelChld
*/
package com.hoperun.erp.sale.saleorder.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleorderModelChld extends BaseModel{
   /** 销售订单明细ID **/
   private String SALE_ORDER_DTL_ID;
   /** 销售订单ID **/
   private String SALE_ORDER_ID;
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
   /** 是否是备货 **/
   private String IS_BACKUP_FLAG;
   /** 特殊工艺标记 **/
   private String SPCL_TECH_FLAG;
   /** 单价 **/
   private String PRICE;
   /** 折扣率 **/
   private String DECT_RATE;
   /** 折后单价 **/
   private String DECT_PRICE;
   /** 订货数量 **/
   private String ORDER_NUM;
   /** 订货金额 **/
   private String ORDER_AMOUNT;
   /** 预计发货日期 **/
   private String ADVC_SEND_DATE;
   /**体积**/
   private String VOLUME;
   /** 备注 **/
   private String REMARK;
   /** 会话ID **/
   private String SESSION_ID;
   /** 特殊工艺加价倍数 **/
   private String TECH_PRICE_MULT;
   /** 来源单据明细ID **/
   private String FROM_BILL_DTL_ID;
   /** 取消标记 **/
   private String CANCEL_FLAG;
   /** 删除标记 **/
   private String DEL_FLAG;
   /**是否非标*/
   private String IS_NO_STAND_FLAG;
   /**特殊工艺id*/
   private String SPCL_TECH_ID;
     /**
     * get 销售订单明细ID value
     * @return the SALE_ORDER_DTL_ID
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getSALE_ORDER_DTL_ID(){
        return SALE_ORDER_DTL_ID;
    }
	/**
     * set 销售订单明细ID value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setSALE_ORDER_DTL_ID(String SALE_ORDER_DTL_ID){
        this.SALE_ORDER_DTL_ID=SALE_ORDER_DTL_ID;
    }
     /**
     * get 销售订单ID value
     * @return the SALE_ORDER_ID
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getSALE_ORDER_ID(){
        return SALE_ORDER_ID;
    }
	/**
     * set 销售订单ID value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setSALE_ORDER_ID(String SALE_ORDER_ID){
        this.SALE_ORDER_ID=SALE_ORDER_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 是否是备货 value
     * @return the IS_BACKUP_FLAG
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getIS_BACKUP_FLAG(){
        return IS_BACKUP_FLAG;
    }
	/**
     * set 是否是备货 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setIS_BACKUP_FLAG(String IS_BACKUP_FLAG){
        this.IS_BACKUP_FLAG=IS_BACKUP_FLAG;
    }
     /**
     * get 特殊工艺标记 value
     * @return the SPCL_TECH_FLAG
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getSPCL_TECH_FLAG(){
        return SPCL_TECH_FLAG;
    }
	/**
     * set 特殊工艺标记 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setSPCL_TECH_FLAG(String SPCL_TECH_FLAG){
        this.SPCL_TECH_FLAG=SPCL_TECH_FLAG;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折后单价 value
     * @return the DECT_PRICE
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折后单价 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 订货数量 value
     * @return the ORDER_NUM
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getORDER_NUM(){
        return ORDER_NUM;
    }
	/**
     * set 订货数量 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setORDER_NUM(String ORDER_NUM){
        this.ORDER_NUM=ORDER_NUM;
    }
     /**
     * get 订货金额 value
     * @return the ORDER_AMOUNT
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getORDER_AMOUNT(){
        return ORDER_AMOUNT;
    }
	/**
     * set 订货金额 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setORDER_AMOUNT(String ORDER_AMOUNT){
        this.ORDER_AMOUNT=ORDER_AMOUNT;
    }
     /**
     * get 预计发货日期 value
     * @return the ADVC_SEND_DATE
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getADVC_SEND_DATE(){
        return ADVC_SEND_DATE;
    }
	/**
     * set 预计发货日期 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setADVC_SEND_DATE(String ADVC_SEND_DATE){
        this.ADVC_SEND_DATE=ADVC_SEND_DATE;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 会话ID value
     * @return the SESSION_ID
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getSESSION_ID(){
        return SESSION_ID;
    }
	/**
     * set 会话ID value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setSESSION_ID(String SESSION_ID){
        this.SESSION_ID=SESSION_ID;
    }
     /**
     * get 特殊工艺加价倍数 value
     * @return the TECH_PRICE_MULT
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getTECH_PRICE_MULT(){
        return TECH_PRICE_MULT;
    }
	/**
     * set 特殊工艺加价倍数 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setTECH_PRICE_MULT(String TECH_PRICE_MULT){
        this.TECH_PRICE_MULT=TECH_PRICE_MULT;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 取消标记 value
     * @return the CANCEL_FLAG
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getCANCEL_FLAG(){
        return CANCEL_FLAG;
    }
	/**
     * set 取消标记 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setCANCEL_FLAG(String CANCEL_FLAG){
        this.CANCEL_FLAG=CANCEL_FLAG;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-10-12 13:52:19
     * @author zzb
	 * @createdate 2013-10-12 13:52:19
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
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
	 * @return the iS_NO_STAND_FLAG
	 */
	public String getIS_NO_STAND_FLAG() {
		return IS_NO_STAND_FLAG;
	}
	/**
	 * @param iSNOSTANDFLAG the iS_NO_STAND_FLAG to set
	 */
	public void setIS_NO_STAND_FLAG(String iSNOSTANDFLAG) {
		IS_NO_STAND_FLAG = iSNOSTANDFLAG;
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