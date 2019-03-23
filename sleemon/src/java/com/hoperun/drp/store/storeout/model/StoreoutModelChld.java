/**
 * prjName:喜临门营销平台
 * ucName:出库单处理
 * fileName:StoreoutModelChld
*/
package com.hoperun.drp.store.storeout.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-09-15 14:59:50
 */
public class StoreoutModelChld extends BaseModel{
   /** 出库单明细ID **/
   private String STOREOUT_DTL_ID;
   /** 出库单ID **/
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
   /** 通知入库数量 **/
   private String NOTICE_NUM;
   /** 实际入库数量 **/
   private String REAL_NUM;
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
   /** 年份月份 **/
   private String YEAR_MONTH;
   /** 删除标记 **/
   private String DEL_FLAG;
   private String SPCL_SPEC_REMARK;
   private String SN;
   
   /** 特殊工艺标记 **/
   private String SPCL_TECH_FLAG;
   
   /** 订单特殊工艺 **/
   private String SPCL_TECH_ID;
   
   /** 销售订单ID **/
   private String SALE_ORDER_ID;
   
   /** 销售订单编号 **/
   private String SALE_ORDER_NO;
   /**货品分类**/
   private String PRD_TYPE;
   
   /** 销售订单明细ID **/
   private String SALE_ORDER_DTL_ID;
   
   /** 订货订单编号 **/
   private String GOODS_ORDER_NO;
   //U9特殊工艺编号
   private String DM_SPCL_TECH_NO;

	/**
     * get 出库单明细ID value
     * @return the STOREOUT_DTL_ID
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getSTOREOUT_DTL_ID(){
        return STOREOUT_DTL_ID;
    }
	/**
     * set 出库单明细ID value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setSTOREOUT_DTL_ID(String STOREOUT_DTL_ID){
        this.STOREOUT_DTL_ID=STOREOUT_DTL_ID;
    }
     /**
     * get 出库单ID value
     * @return the STOREOUT_ID
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getSTOREOUT_ID(){
        return STOREOUT_ID;
    }
	/**
     * set 出库单ID value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setSTOREOUT_ID(String STOREOUT_ID){
        this.STOREOUT_ID=STOREOUT_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 通知入库数量 value
     * @return the NOTICE_NUM
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getNOTICE_NUM(){
        return NOTICE_NUM;
    }
	/**
     * set 通知入库数量 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setNOTICE_NUM(String NOTICE_NUM){
        this.NOTICE_NUM=NOTICE_NUM;
    }
     /**
     * get 实际入库数量 value
     * @return the REAL_NUM
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getREAL_NUM(){
        return REAL_NUM;
    }
	/**
     * set 实际入库数量 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setREAL_NUM(String REAL_NUM){
        this.REAL_NUM=REAL_NUM;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 年份月份 value
     * @return the 年份月份(YEAR_MONTH)
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getYEAR_MONTH(){
        return YEAR_MONTH;
    }
	/**
     * set 年份月份 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setYEAR_MONTH(String YEAR_MONTH){
        this.YEAR_MONTH=YEAR_MONTH;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-15 14:59:50
     * @author chenj
	 * @createdate 2013-09-15 14:59:50
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
    
    /**扫码序列号
     * @return
     */
    public String getSN() {
    	return SN;
    }
    public void setSN(String sN) {
    	SN = sN;
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
	/**
	 * @return the pRD_TYPE
	 */
	public String getPRD_TYPE() {
		return PRD_TYPE;
	}
	/**
	 * @param pRDTYPE the pRD_TYPE to set
	 */
	public void setPRD_TYPE(String pRDTYPE) {
		PRD_TYPE = pRDTYPE;
	}
	/**
	 * @return the dM_SPCL_TECH_NO
	 */
	public String getDM_SPCL_TECH_NO() {
		return DM_SPCL_TECH_NO;
	}
	/**
	 * @param dMSPCLTECHNO the dM_SPCL_TECH_NO to set
	 */
	public void setDM_SPCL_TECH_NO(String dMSPCLTECHNO) {
		DM_SPCL_TECH_NO = dMSPCLTECHNO;
	}
	/**
	 * @return the sPCL_SPEC_REMARK
	 */
	public String getSPCL_SPEC_REMARK() {
		return SPCL_SPEC_REMARK;
	}
	/**
	 * @param sPCLSPECREMARK the sPCL_SPEC_REMARK to set
	 */
	public void setSPCL_SPEC_REMARK(String sPCLSPECREMARK) {
		SPCL_SPEC_REMARK = sPCLSPECREMARK;
	}
	
}