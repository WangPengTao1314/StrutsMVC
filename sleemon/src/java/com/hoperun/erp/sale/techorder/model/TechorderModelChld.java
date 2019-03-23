/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderModelChld
*/
package com.hoperun.erp.sale.techorder.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 16:47:06
 */
public class TechorderModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 2972195187631505779L;
/** 工艺单明细ID **/
   private String TECH_ORDER_DTL_ID;
   /** 工艺单ID **/
   private String TECH_ORDER_ID;
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
   /** 备注 **/
   private String REMARK;
   /** 是否可生产 **/
   private String IS_CAN_PRD_FLAG;
   /** 核价价格 **/
   private String PRICE_DECIDE;
   /** 新货品ID **/
   private String NEW_PRD_ID;
   /** 新货品编号 **/
   private String NEW_PRD_NO;
   /** 新货品名称 **/
   private String NEW_PRD_NAME;
   /** 规格型号 **/
   private String NEW_PRD_SPEC;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 附件 **/
   private String TECH_ATT;
     /**
     * get 工艺单明细ID value
     * @return the TECH_ORDER_DTL_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getTECH_ORDER_DTL_ID(){
        return TECH_ORDER_DTL_ID;
    }
	/**
     * set 工艺单明细ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setTECH_ORDER_DTL_ID(String TECH_ORDER_DTL_ID){
        this.TECH_ORDER_DTL_ID=TECH_ORDER_DTL_ID;
    }
     /**
     * get 工艺单ID value
     * @return the TECH_ORDER_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getTECH_ORDER_ID(){
        return TECH_ORDER_ID;
    }
	/**
     * set 工艺单ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setTECH_ORDER_ID(String TECH_ORDER_ID){
        this.TECH_ORDER_ID=TECH_ORDER_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 特殊工艺标记 value
     * @return the SPCL_TECH_FLAG
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getSPCL_TECH_FLAG(){
        return SPCL_TECH_FLAG;
    }
	/**
     * set 特殊工艺标记 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setSPCL_TECH_FLAG(String SPCL_TECH_FLAG){
        this.SPCL_TECH_FLAG=SPCL_TECH_FLAG;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折后单价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折后单价 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 订货数量 value
     * @return the ORDER_NUM
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORDER_NUM(){
        return ORDER_NUM;
    }
	/**
     * set 订货数量 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORDER_NUM(String ORDER_NUM){
        this.ORDER_NUM=ORDER_NUM;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 是否可生产 value
     * @return the IS_CAN_PRD_FLAG
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getIS_CAN_PRD_FLAG(){
        return IS_CAN_PRD_FLAG;
    }
	/**
     * set 是否可生产 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setIS_CAN_PRD_FLAG(String IS_CAN_PRD_FLAG){
        this.IS_CAN_PRD_FLAG=IS_CAN_PRD_FLAG;
    }
     /**
     * get 核价价格 value
     * @return the PRICE_DECIDE
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getPRICE_DECIDE(){
        return PRICE_DECIDE;
    }
	/**
     * set 核价价格 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setPRICE_DECIDE(String PRICE_DECIDE){
        this.PRICE_DECIDE=PRICE_DECIDE;
    }
     /**
     * get 新货品ID value
     * @return the NEW_PRD_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getNEW_PRD_ID(){
        return NEW_PRD_ID;
    }
	/**
     * set 新货品ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setNEW_PRD_ID(String NEW_PRD_ID){
        this.NEW_PRD_ID=NEW_PRD_ID;
    }
     /**
     * get 新货品编号 value
     * @return the NEW_PRD_NO
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getNEW_PRD_NO(){
        return NEW_PRD_NO;
    }
	/**
     * set 新货品编号 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setNEW_PRD_NO(String NEW_PRD_NO){
        this.NEW_PRD_NO=NEW_PRD_NO;
    }
     /**
     * get 新货品名称 value
     * @return the NEW_PRD_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getNEW_PRD_NAME(){
        return NEW_PRD_NAME;
    }
	/**
     * set 新货品名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setNEW_PRD_NAME(String NEW_PRD_NAME){
        this.NEW_PRD_NAME=NEW_PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the NEW_PRD_SPEC
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getNEW_PRD_SPEC(){
        return NEW_PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setNEW_PRD_SPEC(String NEW_PRD_SPEC){
        this.NEW_PRD_SPEC=NEW_PRD_SPEC;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getTECH_ATT() {
		return TECH_ATT;
	}
	public void setTECH_ATT(String tECHATT) {
		TECH_ATT = tECHATT;
	}
    
    
}