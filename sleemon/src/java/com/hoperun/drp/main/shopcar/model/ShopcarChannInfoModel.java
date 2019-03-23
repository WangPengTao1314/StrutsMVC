package com.hoperun.drp.main.shopcar.model;

import com.hoperun.commons.model.BaseModel;

public class ShopcarChannInfoModel extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4389554420402174757L;
	/**
	 * 收货渠道ID
	 */
	private String CHANN_ID;
	/**
	 * 收货渠道编号
	 */
	private String CHANN_NO;
	/**
	 * 收货渠道名称
	 */
	private String CHANN_NAME;
	/**
	 * 订货渠道id
	 */
	private String ORDER_CHANN_ID;
	/**
	 * 订货渠道编号
	 */
	private String ORDER_CHANN_NO;
	/**
	 * 订货渠道名
	 */
	private String ORDER_CHANN_NAME;
	/**
	 * 联系人
	 */
	private String PERSON_CON;
	/**
	 * 电话
	 */
	private String TEL;
	/**
	 * 详细地址
	 */
	private String RECV_ADDR;
	/**
	 * 入库库房id
	 */
	private String STORE_ID;
	/**
	 * 入库库房编号
	 */
	private String STORE_NO;
	/**
	 * 入库库房名称
	 */
	private String STORE_NAME;
	/**
	 * 区域ID
	 */
	private String AREA_ID;
	/**
	 * 区域编号
	 */
	private String AREA_NO;
	/**
	 * 区域名称
	 */
	private String AREA_NAME;
	/**
	 * 收货地址编号
	 */
	private String RECV_ADDR_NO;
	
	/**交期 **/
	private String ORDER_RECV_DATE;
	
	/**
	 * @return the cHANN_ID
	 */
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	/**
	 * @param cHANNID the cHANN_ID to set
	 */
	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}
	/**
	 * @return the cHANN_NO
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	/**
	 * @param cHANNNO the cHANN_NO to set
	 */
	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}
	/**
	 * @return the cHANN_NAME
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	/**
	 * @param cHANNNAME the cHANN_NAME to set
	 */
	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
	}
	/**
	 * @return the oRDER_CHANN_ID
	 */
	public String getORDER_CHANN_ID() {
		return ORDER_CHANN_ID;
	}
	/**
	 * @param oRDERCHANNID the oRDER_CHANN_ID to set
	 */
	public void setORDER_CHANN_ID(String oRDERCHANNID) {
		ORDER_CHANN_ID = oRDERCHANNID;
	}
	/**
	 * @return the oRDER_CHANN_NO
	 */
	public String getORDER_CHANN_NO() {
		return ORDER_CHANN_NO;
	}
	/**
	 * @param oRDERCHANNNO the oRDER_CHANN_NO to set
	 */
	public void setORDER_CHANN_NO(String oRDERCHANNNO) {
		ORDER_CHANN_NO = oRDERCHANNNO;
	}
	/**
	 * @return the oRDER_CHANN_NAME
	 */
	public String getORDER_CHANN_NAME() {
		return ORDER_CHANN_NAME;
	}
	/**
	 * @param oRDERCHANNNAME the oRDER_CHANN_NAME to set
	 */
	public void setORDER_CHANN_NAME(String oRDERCHANNNAME) {
		ORDER_CHANN_NAME = oRDERCHANNNAME;
	}
	/**
	 * @return the pERSON_CON
	 */
	public String getPERSON_CON() {
		return PERSON_CON;
	}
	/**
	 * @param pERSONCON the pERSON_CON to set
	 */
	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}
	/**
	 * @return the tEL
	 */
	public String getTEL() {
		return TEL;
	}
	/**
	 * @param tEL the tEL to set
	 */
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	/**
	 * @return the sTORE_ID
	 */
	public String getSTORE_ID() {
		return STORE_ID;
	}
	/**
	 * @param sTOREID the sTORE_ID to set
	 */
	public void setSTORE_ID(String sTOREID) {
		STORE_ID = sTOREID;
	}
	/**
	 * @return the sTORE_NO
	 */
	public String getSTORE_NO() {
		return STORE_NO;
	}
	/**
	 * @param sTORENO the sTORE_NO to set
	 */
	public void setSTORE_NO(String sTORENO) {
		STORE_NO = sTORENO;
	}
	/**
	 * @return the sTORE_NAME
	 */
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	/**
	 * @param sTORENAME the sTORE_NAME to set
	 */
	public void setSTORE_NAME(String sTORENAME) {
		STORE_NAME = sTORENAME;
	}
	/**
	 * @return the aREA_ID
	 */
	public String getAREA_ID() {
		return AREA_ID;
	}
	/**
	 * @param aREAID the aREA_ID to set
	 */
	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}
	/**
	 * @return the aREA_NO
	 */
	public String getAREA_NO() {
		return AREA_NO;
	}
	/**
	 * @param aREANO the aREA_NO to set
	 */
	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}
	/**
	 * @return the aREA_NAME
	 */
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	/**
	 * @param aREANAME the aREA_NAME to set
	 */
	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}
	/**
	 * @return the rECV_ADDR_NO
	 */
	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}
	/**
	 * @param rECVADDRNO the rECV_ADDR_NO to set
	 */
	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}
	/**
	 * @return the rECV_ADDR
	 */
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	/**
	 * @param rECVADDR the rECV_ADDR to set
	 */
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
	
	
}
