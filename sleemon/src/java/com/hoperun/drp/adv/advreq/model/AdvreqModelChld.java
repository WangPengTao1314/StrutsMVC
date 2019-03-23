/**
 * prjName:喜临门营销平台
 * ucName:广告投放申请单维护
 * fileName:AdvreqModelChld
*/
package com.hoperun.drp.adv.advreq.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-15
 */
public class AdvreqModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/** 广告投放申请单付款明细ID **/
   private String ERP_ADV_REQ_DTL_ID;
   /** 广告投放申请单ID **/
   private String ERP_ADV_REQ_ID;
   /** 付款批次 **/
   private String PAY_BATCH;
   /** 付款比例 **/
   private String PAY_PERCENT;
   /** 付款金额 **/
   private String PAY_AMOUNT;
   /** 付款条件 **/
   private String PAY_COND;
   /** 付款方式 **/
   private String PAY_TYPE;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 广告投放申请单付款明细ID value
     * @return the ERP_ADV_REQ_DTL_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getERP_ADV_REQ_DTL_ID(){
        return ERP_ADV_REQ_DTL_ID;
    }
	/**
     * set 广告投放申请单付款明细ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setERP_ADV_REQ_DTL_ID(String ERP_ADV_REQ_DTL_ID){
        this.ERP_ADV_REQ_DTL_ID=ERP_ADV_REQ_DTL_ID;
    }
     /**
     * get 广告投放申请单ID value
     * @return the ERP_ADV_REQ_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getERP_ADV_REQ_ID(){
        return ERP_ADV_REQ_ID;
    }
	/**
     * set 广告投放申请单ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setERP_ADV_REQ_ID(String ERP_ADV_REQ_ID){
        this.ERP_ADV_REQ_ID=ERP_ADV_REQ_ID;
    }
     /**
     * get 付款批次 value
     * @return the PAY_BATCH
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getPAY_BATCH(){
        return PAY_BATCH;
    }
	/**
     * set 付款批次 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setPAY_BATCH(String PAY_BATCH){
        this.PAY_BATCH=PAY_BATCH;
    }
     /**
     * get 付款比例 value
     * @return the PAY_PERCENT
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getPAY_PERCENT(){
        return PAY_PERCENT;
    }
	/**
     * set 付款比例 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setPAY_PERCENT(String PAY_PERCENT){
        this.PAY_PERCENT=PAY_PERCENT;
    }
     /**
     * get 付款金额 value
     * @return the PAY_AMOUNT
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getPAY_AMOUNT(){
        return PAY_AMOUNT;
    }
	/**
     * set 付款金额 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setPAY_AMOUNT(String PAY_AMOUNT){
        this.PAY_AMOUNT=PAY_AMOUNT;
    }
     /**
     * get 付款条件 value
     * @return the PAY_COND
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getPAY_COND(){
        return PAY_COND;
    }
	/**
     * set 付款条件 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setPAY_COND(String PAY_COND){
        this.PAY_COND=PAY_COND;
    }
     /**
     * get 付款方式 value
     * @return the PAY_TYPE
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getPAY_TYPE(){
        return PAY_TYPE;
    }
	/**
     * set 付款方式 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setPAY_TYPE(String PAY_TYPE){
        this.PAY_TYPE=PAY_TYPE;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}