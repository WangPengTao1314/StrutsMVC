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
public class AdvcorderModelTrace extends BaseModel{
	private String ADVC_ORDER_TRACE_ID;
	private String ADVC_ORDER_ID;
	private String ACTION;
	private String ACTOR;
	private String ACT_TIME;
	private String BILL_NO;
	private String REMARK;
	/**
	 * @return the aDVC_ORDER_TRACE_ID
	 */
	public String getADVC_ORDER_TRACE_ID() {
		return ADVC_ORDER_TRACE_ID;
	}
	/**
	 * @param aDVCORDERTRACEID the aDVC_ORDER_TRACE_ID to set
	 */
	public void setADVC_ORDER_TRACE_ID(String aDVCORDERTRACEID) {
		ADVC_ORDER_TRACE_ID = aDVCORDERTRACEID;
	}
	/**
	 * @return the aDVC_ORDER_ID
	 */
	public String getADVC_ORDER_ID() {
		return ADVC_ORDER_ID;
	}
	/**
	 * @param aDVCORDERID the aDVC_ORDER_ID to set
	 */
	public void setADVC_ORDER_ID(String aDVCORDERID) {
		ADVC_ORDER_ID = aDVCORDERID;
	}
	/**
	 * @return the aCTION
	 */
	public String getACTION() {
		return ACTION;
	}
	/**
	 * @param aCTION the aCTION to set
	 */
	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}
	/**
	 * @return the aCTOR
	 */
	public String getACTOR() {
		return ACTOR;
	}
	/**
	 * @param aCTOR the aCTOR to set
	 */
	public void setACTOR(String aCTOR) {
		ACTOR = aCTOR;
	}
	/**
	 * @return the aCT_TIME
	 */
	public String getACT_TIME() {
		return ACT_TIME;
	}
	/**
	 * @param aCTTIME the aCT_TIME to set
	 */
	public void setACT_TIME(String aCTTIME) {
		ACT_TIME = aCTTIME;
	}
	/**
	 * @return the bILL_NO
	 */
	public String getBILL_NO() {
		return BILL_NO;
	}
	/**
	 * @param bILLNO the bILL_NO to set
	 */
	public void setBILL_NO(String bILLNO) {
		BILL_NO = bILLNO;
	}
	/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}
	/**
	 * @param rEMARK the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
}