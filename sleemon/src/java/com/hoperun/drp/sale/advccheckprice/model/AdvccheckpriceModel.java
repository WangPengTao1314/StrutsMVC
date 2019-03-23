/**

 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderModel
*/
package com.hoperun.drp.sale.advccheckprice.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvccheckpriceModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 3567702961516331864L;
/** 预订单ID **/
   private String ADVC_ORDER_ID;
   /** 订货订单ID **/
   private String GOODS_ORDER_ID;
   /** 终端预订单编号 **/
   private String ADVC_ORDER_NO;
   /** 终端信息ID **/
   private String TERM_ID;
   /** 终端编号 **/
   private String TERM_NO;
   /** 终端名称 **/
   private String TERM_NAME;
   /** 销售日期 **/
   private String SALE_DATE;
   /** 业务员ID **/
   private String SALE_PSON_ID;
   /** 业务员名称 **/
   private String SALE_PSON_NAME;
   /** 客户名称 **/
   private String CUST_NAME;
   /** 电话 **/
   private String TEL;
   /** 收货地址 **/
   private String RECV_ADDR;
   /** 要求到货日期 **/
   private String ORDER_RECV_DATE;
   /** 订金金额 **/
   private double ADVC_AMOUNT;
   /** 应收总额 **/
   private double PAYABLE_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 业绩日期 **/
   private String PFMC_DATE;
   /** 处理标记 **/
   private Integer DEAL_FLAG;
   /** 结算标记 **/
   private Integer STTLE_FLAG;
   /** 制单人名称 **/
   private String CRE_NAME;
   /** 制单人ID **/
   private String CREATOR;
   /** 制单时间 **/
   private String CRE_TIME;
   /** 更新人名称 **/
   private String UPD_NAME;
   /** 更新人ID **/
   private String UPDATOR;
   /** 更新时间 **/
   private String UPD_TIME;
   /** 制单部门ID **/
   private String DEPT_ID;
   /** 制单部门名称 **/
   private String DEPT_NAME;
   /** 制单机构ID **/
   private String ORG_ID;
   /** 制单机构名称 **/
   private String ORG_NAME;
   /** 帐套ID **/
   private String LEDGER_ID;
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private Integer DEL_FLAG;
   /** 单据类型 **/
   private String BILL_TYPE;
   
     /**
 * @return the bILL_TYPE
 */
public String getBILL_TYPE() {
	return BILL_TYPE;
}
/**
 * @param bILLTYPE the bILL_TYPE to set
 */
public void setBILL_TYPE(String bILLTYPE) {
	BILL_TYPE = bILLTYPE;
}
	/**
     * get 预订单ID value
     * @return the ADVC_ORDER_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getADVC_ORDER_ID(){
        return ADVC_ORDER_ID;
    }
	/**
     * set 预订单ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setADVC_ORDER_ID(String ADVC_ORDER_ID){
        this.ADVC_ORDER_ID=ADVC_ORDER_ID;
    }
     /**
     * get 订货订单ID value
     * @return the GOODS_ORDER_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getGOODS_ORDER_ID(){
        return GOODS_ORDER_ID;
    }
	/**
     * set 订货订单ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setGOODS_ORDER_ID(String GOODS_ORDER_ID){
        this.GOODS_ORDER_ID=GOODS_ORDER_ID;
    }
     /**
     * get 终端预订单编号 value
     * @return the ADVC_ORDER_NO
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getADVC_ORDER_NO(){
        return ADVC_ORDER_NO;
    }
	/**
     * set 终端预订单编号 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setADVC_ORDER_NO(String ADVC_ORDER_NO){
        this.ADVC_ORDER_NO=ADVC_ORDER_NO;
    }
     /**
     * get 终端信息ID value
     * @return the TERM_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getTERM_ID(){
        return TERM_ID;
    }
	/**
     * set 终端信息ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setTERM_ID(String TERM_ID){
        this.TERM_ID=TERM_ID;
    }
     /**
     * get 终端编号 value
     * @return the TERM_NO
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getTERM_NO(){
        return TERM_NO;
    }
	/**
     * set 终端编号 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setTERM_NO(String TERM_NO){
        this.TERM_NO=TERM_NO;
    }
     /**
     * get 终端名称 value
     * @return the TERM_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getTERM_NAME(){
        return TERM_NAME;
    }
	/**
     * set 终端名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setTERM_NAME(String TERM_NAME){
        this.TERM_NAME=TERM_NAME;
    }
     /**
     * get 销售日期 value
     * @return the SALE_DATE
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getSALE_DATE(){
        return SALE_DATE;
    }
	/**
     * set 销售日期 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setSALE_DATE(String SALE_DATE){
        this.SALE_DATE=SALE_DATE;
    }
     /**
     * get 业务员ID value
     * @return the SALE_PSON_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getSALE_PSON_ID(){
        return SALE_PSON_ID;
    }
	/**
     * set 业务员ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setSALE_PSON_ID(String SALE_PSON_ID){
        this.SALE_PSON_ID=SALE_PSON_ID;
    }
     /**
     * get 业务员名称 value
     * @return the SALE_PSON_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getSALE_PSON_NAME(){
        return SALE_PSON_NAME;
    }
	/**
     * set 业务员名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setSALE_PSON_NAME(String SALE_PSON_NAME){
        this.SALE_PSON_NAME=SALE_PSON_NAME;
    }
     /**
     * get 客户名称 value
     * @return the CUST_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getCUST_NAME(){
        return CUST_NAME;
    }
	/**
     * set 客户名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setCUST_NAME(String CUST_NAME){
        this.CUST_NAME=CUST_NAME;
    }
     /**
     * get 电话 value
     * @return the TEL
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getTEL(){
        return TEL;
    }
	/**
     * set 电话 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setTEL(String TEL){
        this.TEL=TEL;
    }
     /**
     * get 收货地址 value
     * @return the RECV_ADDR
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getRECV_ADDR(){
        return RECV_ADDR;
    }
	/**
     * set 收货地址 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setRECV_ADDR(String RECV_ADDR){
        this.RECV_ADDR=RECV_ADDR;
    }
     /**
     * get 要求到货日期 value
     * @return the ORDER_RECV_DATE
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getORDER_RECV_DATE(){
        return ORDER_RECV_DATE;
    }
	/**
     * set 要求到货日期 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setORDER_RECV_DATE(String ORDER_RECV_DATE){
        this.ORDER_RECV_DATE=ORDER_RECV_DATE;
    }
     /**
     * get 订金金额 value
     * @return the ADVC_AMOUNT
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public double getADVC_AMOUNT(){
        return ADVC_AMOUNT;
    }
	/**
     * set 订金金额 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setADVC_AMOUNT(double ADVC_AMOUNT){
        this.ADVC_AMOUNT=ADVC_AMOUNT;
    }
     /**
     * get 应收总额 value
     * @return the PAYABLE_AMOUNT
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public double getPAYABLE_AMOUNT(){
        return PAYABLE_AMOUNT;
    }
	/**
     * set 应收总额 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setPAYABLE_AMOUNT(double PAYABLE_AMOUNT){
        this.PAYABLE_AMOUNT=PAYABLE_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 业绩日期 value
     * @return the PFMC_DATE
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getPFMC_DATE(){
        return PFMC_DATE;
    }
	/**
     * set 业绩日期 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setPFMC_DATE(String PFMC_DATE){
        this.PFMC_DATE=PFMC_DATE;
    }
   
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
	/**
	 * @return the dEAL_FLAG
	 */
	public Integer getDEAL_FLAG() {
		return DEAL_FLAG;
	}
	/**
	 * @param dEALFLAG the dEAL_FLAG to set
	 */
	public void setDEAL_FLAG(Integer dEALFLAG) {
		DEAL_FLAG = dEALFLAG;
	}
	/**
	 * @return the sTTLE_FLAG
	 */
	public Integer getSTTLE_FLAG() {
		return STTLE_FLAG;
	}
	/**
	 * @param sTTLEFLAG the sTTLE_FLAG to set
	 */
	public void setSTTLE_FLAG(Integer sTTLEFLAG) {
		STTLE_FLAG = sTTLEFLAG;
	}
	/**
	 * @return the dEL_FLAG
	 */
	public Integer getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(Integer dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
    
}