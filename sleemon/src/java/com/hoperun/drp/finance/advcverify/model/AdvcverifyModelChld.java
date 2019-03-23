/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentModelChld
*/
package com.hoperun.drp.finance.advcverify.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public class AdvcverifyModelChld extends BaseModel{
   /** 结算单付款明细ID **/
   private String STATEMENTS_PAYMENT_DTL_ID;
   /** 结算单ID **/
   private String STATEMENTS_ID;
   /** 付款方式 **/
   private String PAY_TYPE;
   /** 付款单据号 **/
   private String PAY_BILL_NO;
   /** 付款金额 **/
   private String PAY_AMONT;
   /** 付款信息 **/
   private String PAY_INFO;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 结算单付款明细ID value
     * @return the STATEMENTS_PAYMENT_DTL_ID
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getSTATEMENTS_PAYMENT_DTL_ID(){
        return STATEMENTS_PAYMENT_DTL_ID;
    }
	/**
     * set 结算单付款明细ID value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setSTATEMENTS_PAYMENT_DTL_ID(String STATEMENTS_PAYMENT_DTL_ID){
        this.STATEMENTS_PAYMENT_DTL_ID=STATEMENTS_PAYMENT_DTL_ID;
    }
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
     * get 付款方式 value
     * @return the PAY_TYPE
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getPAY_TYPE(){
        return PAY_TYPE;
    }
	/**
     * set 付款方式 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setPAY_TYPE(String PAY_TYPE){
        this.PAY_TYPE=PAY_TYPE;
    }
     /**
     * get 付款单据号 value
     * @return the PAY_BILL_NO
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getPAY_BILL_NO(){
        return PAY_BILL_NO;
    }
	/**
     * set 付款单据号 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setPAY_BILL_NO(String PAY_BILL_NO){
        this.PAY_BILL_NO=PAY_BILL_NO;
    }
     /**
     * get 付款金额 value
     * @return the PAY_AMONT
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getPAY_AMONT(){
        return PAY_AMONT;
    }
	/**
     * set 付款金额 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setPAY_AMONT(String PAY_AMONT){
        this.PAY_AMONT=PAY_AMONT;
    }
     /**
     * get 付款信息 value
     * @return the PAY_INFO
	 * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public String getPAY_INFO(){
        return PAY_INFO;
    }
	/**
     * set 付款信息 value
     * @createdate 2013-10-20 18:57:47
     * @author chenj
	 * @createdate 2013-10-20 18:57:47
     */
    public void setPAY_INFO(String PAY_INFO){
        this.PAY_INFO=PAY_INFO;
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
}