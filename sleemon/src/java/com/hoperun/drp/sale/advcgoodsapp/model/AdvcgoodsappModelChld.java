/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappModelChld
*/
package com.hoperun.drp.sale.advcgoodsapp.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-11-02 18:55:53
 */
public class AdvcgoodsappModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -2442196059147998173L;
/** 预订单发货申请明细ID **/
   private String ADVC_SEND_REQ_DTL_ID;
   /** 预订单发货申请ID **/
   private String ADVC_SEND_REQ_ID;
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
   /** 实际出库数量 **/
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
   /** 货品类型 **/
   private String PRD_TYPE;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 订单特殊工艺ID**/
   private String SPCL_TECH_ID;
   /** 冻结数量*/
   private String FREEZE_NUM;
   /**
    * 交货日期
    */
   private String ORDER_RECV_DATE;
   
   private String SPCL_SPEC_REMARK;
     /**
     * get 预订单发货申请明细ID value
     * @return the ADVC_SEND_REQ_DTL_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getADVC_SEND_REQ_DTL_ID(){
        return ADVC_SEND_REQ_DTL_ID;
    }
	/**
     * set 预订单发货申请明细ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setADVC_SEND_REQ_DTL_ID(String ADVC_SEND_REQ_DTL_ID){
        this.ADVC_SEND_REQ_DTL_ID=ADVC_SEND_REQ_DTL_ID;
    }
     /**
     * get 预订单发货申请ID value
     * @return the ADVC_SEND_REQ_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getADVC_SEND_REQ_ID(){
        return ADVC_SEND_REQ_ID;
    }
	/**
     * set 预订单发货申请ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setADVC_SEND_REQ_ID(String ADVC_SEND_REQ_ID){
        this.ADVC_SEND_REQ_ID=ADVC_SEND_REQ_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 通知出库数量 value
     * @return the NOTICE_NUM
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getNOTICE_NUM(){
        return NOTICE_NUM;
    }
	/**
     * set 通知出库数量 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setNOTICE_NUM(String NOTICE_NUM){
        this.NOTICE_NUM=NOTICE_NUM;
    }
     /**
     * get 实际出库数量 value
     * @return the REAL_NUM
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getREAL_NUM(){
        return REAL_NUM;
    }
	/**
     * set 实际出库数量 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setREAL_NUM(String REAL_NUM){
        this.REAL_NUM=REAL_NUM;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 折后金额 value
     * @return the DECT_AMOUNT
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDECT_AMOUNT(){
        return DECT_AMOUNT;
    }
	/**
     * set 折后金额 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setDECT_AMOUNT(String DECT_AMOUNT){
        this.DECT_AMOUNT=DECT_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 来源单据明细ID value
     * @return the FROM_BILL_DTL_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getFROM_BILL_DTL_ID(){
        return FROM_BILL_DTL_ID;
    }
	/**
     * set 来源单据明细ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setFROM_BILL_DTL_ID(String FROM_BILL_DTL_ID){
        this.FROM_BILL_DTL_ID=FROM_BILL_DTL_ID;
    }
     /**
     * get 货品类型 value
     * @return the PRD_TYPE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getPRD_TYPE(){
        return PRD_TYPE;
    }
	/**
     * set 货品类型 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setPRD_TYPE(String PRD_TYPE){
        this.PRD_TYPE=PRD_TYPE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
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
	/**
	 * @return the fREEZE_NUM
	 */
	public String getFREEZE_NUM() {
		return FREEZE_NUM;
	}
	/**
	 * @param fREEZENUM the fREEZE_NUM to set
	 */
	public void setFREEZE_NUM(String fREEZENUM) {
		FREEZE_NUM = fREEZENUM;
	}
	/**
	 * @return the oRDER_RECV_DATE
	 */
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	/**
	 * @param oRDERRECVDATE the oRDER_RECV_DATE to set
	 */
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
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