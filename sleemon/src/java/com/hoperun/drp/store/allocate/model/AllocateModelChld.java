/**
 * prjName:喜临门营销平台
 * ucName:调拨申请维护
 * fileName:AllocateModelChld
*/
package com.hoperun.drp.store.allocate.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-05 13:29:12
 */
public class AllocateModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 2922953954003510310L;
/** 调拨单明细ID **/
   private String ALLOCATE_DTL_ID;
   /** 调拨单ID **/
   private String ALLOCATE_ID;
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
   /** 折后单价 **/
   private String DECT_PRICE;
   /** 调拨数量 **/
   private String ALLOC_NUM;
   /** 折后金额 **/
   private String DECT_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 删除标记 **/
   private String DEL_FLAG;
   /**订单特殊工艺ID*/
   private String SPCL_TECH_ID;
     /**
     * get 调拨单明细ID value
     * @return the ALLOCATE_DTL_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOCATE_DTL_ID(){
        return ALLOCATE_DTL_ID;
    }
	/**
     * set 调拨单明细ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOCATE_DTL_ID(String ALLOCATE_DTL_ID){
        this.ALLOCATE_DTL_ID=ALLOCATE_DTL_ID;
    }
     /**
     * get 调拨单ID value
     * @return the ALLOCATE_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOCATE_ID(){
        return ALLOCATE_ID;
    }
	/**
     * set 调拨单ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOCATE_ID(String ALLOCATE_ID){
        this.ALLOCATE_ID=ALLOCATE_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折后单价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折后单价 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 调拨数量 value
     * @return the ALLOC_NUM
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_NUM(){
        return ALLOC_NUM;
    }
	/**
     * set 调拨数量 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_NUM(String ALLOC_NUM){
        this.ALLOC_NUM=ALLOC_NUM;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
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