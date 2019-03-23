/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentModel
*/
package com.hoperun.drp.finance.custrefundments.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public class CustRefundmentModel extends BaseModel{
   /** 结算单ID **/
   private String STATEMENTS_ID;
   /** 结算单编号 **/
   private String STATEMENTS_NO;
   /** 预订单ID **/
   private String ADVC_ORDER_ID;
   /** 预订单编号 **/
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
   /** 要求到货日期 **/
   private String ORDER_RECV_DATE;
   /** 订金金额 **/
   private String ADVC_AMOUNT;
   /** 已付款金额 **/
   private String PAYED_TOTAL_AMOUNT;
   /** 已扣款金额 **/
   private String DEDUCTED_TOTAL_AMOUNT;
   /** 本次结算金额 **/
   private String STATEMENTS_AMOUNT;
   /** 本次扣款金额 **/
   private String DEDUCT_AMOUNT;
   /** 结算附件 **/
   private String STATENEBTS_ATT;
   /** 追加定金 **/
   private String ADD_ADVC_AMOUNT;
   /** 应收总额 **/
   private String PAYABLE_AMOUNT;
   /** 备注 **/
   private String REMARK;
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
   /** 账套 **/
   private String LEDGER_ID;
   /** 账套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
   /**结算日期 **/
   private String STATEMENT_DATE;
     /**
     * get 结算单ID value
     * @return the STATEMENTS_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSTATEMENTS_ID(){
        return STATEMENTS_ID;
    }
	/**
     * set 结算单ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSTATEMENTS_ID(String STATEMENTS_ID){
        this.STATEMENTS_ID=STATEMENTS_ID;
    }
     /**
     * get 结算单编号 value
     * @return the STATEMENTS_NO
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSTATEMENTS_NO(){
        return STATEMENTS_NO;
    }
	/**
     * set 结算单编号 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSTATEMENTS_NO(String STATEMENTS_NO){
        this.STATEMENTS_NO=STATEMENTS_NO;
    }
     /**
     * get 预订单ID value
     * @return the ADVC_ORDER_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getADVC_ORDER_ID(){
        return ADVC_ORDER_ID;
    }
	/**
     * set 预订单ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setADVC_ORDER_ID(String ADVC_ORDER_ID){
        this.ADVC_ORDER_ID=ADVC_ORDER_ID;
    }
     /**
     * get 预订单编号 value
     * @return the ADVC_ORDER_NO
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getADVC_ORDER_NO(){
        return ADVC_ORDER_NO;
    }
	/**
     * set 预订单编号 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setADVC_ORDER_NO(String ADVC_ORDER_NO){
        this.ADVC_ORDER_NO=ADVC_ORDER_NO;
    }
     /**
     * get 终端信息ID value
     * @return the TERM_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getTERM_ID(){
        return TERM_ID;
    }
	/**
     * set 终端信息ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setTERM_ID(String TERM_ID){
        this.TERM_ID=TERM_ID;
    }
     /**
     * get 终端编号 value
     * @return the TERM_NO
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getTERM_NO(){
        return TERM_NO;
    }
	/**
     * set 终端编号 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setTERM_NO(String TERM_NO){
        this.TERM_NO=TERM_NO;
    }
     /**
     * get 终端名称 value
     * @return the TERM_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getTERM_NAME(){
        return TERM_NAME;
    }
	/**
     * set 终端名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setTERM_NAME(String TERM_NAME){
        this.TERM_NAME=TERM_NAME;
    }
     /**
     * get 销售日期 value
     * @return the SALE_DATE
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSALE_DATE(){
        return SALE_DATE;
    }
	/**
     * set 销售日期 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSALE_DATE(String SALE_DATE){
        this.SALE_DATE=SALE_DATE;
    }
     /**
     * get 业务员ID value
     * @return the SALE_PSON_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSALE_PSON_ID(){
        return SALE_PSON_ID;
    }
	/**
     * set 业务员ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSALE_PSON_ID(String SALE_PSON_ID){
        this.SALE_PSON_ID=SALE_PSON_ID;
    }
     /**
     * get 业务员名称 value
     * @return the SALE_PSON_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSALE_PSON_NAME(){
        return SALE_PSON_NAME;
    }
	/**
     * set 业务员名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSALE_PSON_NAME(String SALE_PSON_NAME){
        this.SALE_PSON_NAME=SALE_PSON_NAME;
    }
     /**
     * get 客户名称 value
     * @return the CUST_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getCUST_NAME(){
        return CUST_NAME;
    }
	/**
     * set 客户名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setCUST_NAME(String CUST_NAME){
        this.CUST_NAME=CUST_NAME;
    }
     /**
     * get 电话 value
     * @return the TEL
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getTEL(){
        return TEL;
    }
	/**
     * set 电话 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setTEL(String TEL){
        this.TEL=TEL;
    }
     /**
     * get 要求到货日期 value
     * @return the ORDER_RECV_DATE
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getORDER_RECV_DATE(){
        return ORDER_RECV_DATE;
    }
	/**
     * set 要求到货日期 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setORDER_RECV_DATE(String ORDER_RECV_DATE){
        this.ORDER_RECV_DATE=ORDER_RECV_DATE;
    }
     /**
     * get 订金金额 value
     * @return the ADVC_AMOUNT
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getADVC_AMOUNT(){
        return ADVC_AMOUNT;
    }
	/**
     * set 订金金额 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setADVC_AMOUNT(String ADVC_AMOUNT){
        this.ADVC_AMOUNT=ADVC_AMOUNT;
    }
     /**
     * get 追加定金 value
     * @return the ADD_ADVC_AMOUNT
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getADD_ADVC_AMOUNT(){
        return ADD_ADVC_AMOUNT;
    }
	/**
     * set 追加定金 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setADD_ADVC_AMOUNT(String ADD_ADVC_AMOUNT){
        this.ADD_ADVC_AMOUNT=ADD_ADVC_AMOUNT;
    }
     /**
     * get 应收总额 value
     * @return the PAYABLE_AMOUNT
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getPAYABLE_AMOUNT(){
        return PAYABLE_AMOUNT;
    }
	/**
     * set 应收总额 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setPAYABLE_AMOUNT(String PAYABLE_AMOUNT){
        this.PAYABLE_AMOUNT=PAYABLE_AMOUNT;
    }
     /**
     * get 结算金额 value
     * @return the STATEMENTS_AMOUNT
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSTATEMENTS_AMOUNT(){
        return STATEMENTS_AMOUNT;
    }
	/**
     * set 结算金额 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSTATEMENTS_AMOUNT(String STATEMENTS_AMOUNT){
        this.STATEMENTS_AMOUNT=STATEMENTS_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 账套 value
     * @return the LEDGER_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 账套 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 账套名称 value
     * @return the LEDGER_NAME
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 账套名称 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getPAYED_TOTAL_AMOUNT() {
		return PAYED_TOTAL_AMOUNT;
	}
	public void setPAYED_TOTAL_AMOUNT(String payed_total_amount) {
		PAYED_TOTAL_AMOUNT = payed_total_amount;
	}
	public String getDEDUCTED_TOTAL_AMOUNT() {
		return DEDUCTED_TOTAL_AMOUNT;
	}
	public void setDEDUCTED_TOTAL_AMOUNT(String deducted_total_amount) {
		DEDUCTED_TOTAL_AMOUNT = deducted_total_amount;
	}
	public String getDEDUCT_AMOUNT() {
		return DEDUCT_AMOUNT;
	}
	public void setDEDUCT_AMOUNT(String deduct_amount) {
		DEDUCT_AMOUNT = deduct_amount;
	}
	public String getSTATENEBTS_ATT() {
		return STATENEBTS_ATT;
	}
	public void setSTATENEBTS_ATT(String statenebts_att) {
		STATENEBTS_ATT = statenebts_att;
	}
	public String getSTATEMENT_DATE() {
		return STATEMENT_DATE;
	}
	public void setSTATEMENT_DATE(String sTATEMENTDATE) {
		STATEMENT_DATE = sTATEMENTDATE;
	}
	
}