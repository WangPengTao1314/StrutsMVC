package com.hoperun.drp.sale.goodsorderhd.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 订货订单 生命周期
 * @author zhang_zhongbin
 *
 */
public class GoodsorderhdModelTrace extends BaseModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3596656817031231267L;
	
	private String GOODS_ORDER_TRACE_ID;
    /** 订货订单ID **/
    private String GOODS_ORDER_ID;
    
    private String SALE_ORDER_ID;
    private String BILL_NO;
    private String BILL_TYPE;
    private String ACTION_NAME;
    private String TRACE_URL;
    private String DEAL_PSON_NAME;
    private String DEAL_TIME;
    private String STATE;
    
    
	public String getGOODS_ORDER_TRACE_ID() {
		return GOODS_ORDER_TRACE_ID;
	}
	public void setGOODS_ORDER_TRACE_ID(String gOODSORDERTRACEID) {
		GOODS_ORDER_TRACE_ID = gOODSORDERTRACEID;
	}
	public String getGOODS_ORDER_ID() {
		return GOODS_ORDER_ID;
	}
	public void setGOODS_ORDER_ID(String gOODSORDERID) {
		GOODS_ORDER_ID = gOODSORDERID;
	}
	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}
	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
	}
	public String getBILL_NO() {
		return BILL_NO;
	}
	public void setBILL_NO(String bILLNO) {
		BILL_NO = bILLNO;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILLTYPE) {
		BILL_TYPE = bILLTYPE;
	}
	public String getACTION_NAME() {
		return ACTION_NAME;
	}
	public void setACTION_NAME(String aCTIONNAME) {
		ACTION_NAME = aCTIONNAME;
	}
	public String getTRACE_URL() {
		return TRACE_URL;
	}
	public void setTRACE_URL(String tRACEURL) {
		TRACE_URL = tRACEURL;
	}
	public String getDEAL_PSON_NAME() {
		return DEAL_PSON_NAME;
	}
	public void setDEAL_PSON_NAME(String dEALPSONNAME) {
		DEAL_PSON_NAME = dEALPSONNAME;
	}
	public String getDEAL_TIME() {
		return DEAL_TIME;
	}
	public void setDEAL_TIME(String dEALTIME) {
		DEAL_TIME = dEALTIME;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
    
    
    
	   
	   

}
