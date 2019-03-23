/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderModelChld
*/
package com.hoperun.drp.sale.advcorder.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-11 17:17:29
 */
public class AdvcorderModelGchld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -8273768190565979840L;
	/** 付款明细ID **/
   private String PAYMENT_DTL_ID;
   /** 预订单ID **/
   private String ADVC_ORDER_ID;
   /** 付款方式 **/
   private String PAY_TYPE;
   /** 付款信息 **/
   private String PAY_INFO;
   /** 付款金额 **/
   private String PAY_AMONT;
   /**付款单据编号**/
   private String PAY_BILL_NO;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 付款明细ID value
     * @return the PAYMENT_DTL_ID
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getPAYMENT_DTL_ID(){
        return PAYMENT_DTL_ID;
    }
	/**
     * set 付款明细ID value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setPAYMENT_DTL_ID(String PAYMENT_DTL_ID){
        this.PAYMENT_DTL_ID=PAYMENT_DTL_ID;
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
     * get 付款方式 value
     * @return the PAY_TYPE
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getPAY_TYPE(){
        return PAY_TYPE;
    }
	/**
     * set 付款方式 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setPAY_TYPE(String PAY_TYPE){
        this.PAY_TYPE=PAY_TYPE;
    }
     /**
     * get 付款信息 value
     * @return the PAY_INFO
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getPAY_INFO(){
        return PAY_INFO;
    }
	/**
     * set 付款信息 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setPAY_INFO(String PAY_INFO){
        this.PAY_INFO=PAY_INFO;
    }
     /**
     * get 付款金额 value
     * @return the PAY_AMONT
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getPAY_AMONT(){
        return PAY_AMONT;
    }
	/**
     * set 付款金额 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setPAY_AMONT(String PAY_AMONT){
        this.PAY_AMONT=PAY_AMONT;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-11 17:17:29
     * @author lyg
	 * @createdate 2013-08-11 17:17:29
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	/**
	 * @return the pAY_BILL_NO
	 */
	public String getPAY_BILL_NO() {
		return PAY_BILL_NO;
	}
	/**
	 * @param pAYBILLNO the pAY_BILL_NO to set
	 */
	public void setPAY_BILL_NO(String pAYBILLNO) {
		PAY_BILL_NO = pAYBILLNO;
	}
    
}