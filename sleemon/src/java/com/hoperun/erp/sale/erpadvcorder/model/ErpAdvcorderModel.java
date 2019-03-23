package com.hoperun.erp.sale.erpadvcorder.model;
/**
 * 总部预订单
 * @author zhang_zhongbin
 *
 */
public class ErpAdvcorderModel {
	
	
	/** 预订单ID **/
	private String ADVC_ORDER_ID;
	/** 订货订单ID **/
	private String GOODS_ORDER_ID;
	/** 预订单编号 **/
	private String ADVC_ORDER_NO;
	/** 单据类型 **/
	private String BILL_TYPE;
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
	private String ADVC_AMOUNT;
	/** 应收总额 **/
	private String PAYABLE_AMOUNT;
	/** 状态 **/
	private String STATE;
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
	/** 帐套ID **/
	private String LEDGER_ID;
	/** 帐套名称 **/
	private String LEDGER_NAME;
	/** 删除标记 **/
	private String DEL_FLAG;
	/**结算标记**/
	private String STTLE_FLAG;
	/**营销活动ID**/
	private String MARKETING_ACT_ID;
	/**营销活动编号**/
	private String MARKETING_ACT_NO;
	/**营销活动名称**/
	private String MARKETING_ACT_NAME;
	/**卡券ID**/
	private String MARKETING_CARD_ID;
	/**卡券编号**/
	private String MARKETING_CARD_NO;
	/**终端ID**/
	private String TERM_ID;
	/**终端编号**/
	private String TERM_NO;
	/**终端名称**/
	private String TERM_NAME;
	
	public String getADVC_ORDER_ID() {
		return ADVC_ORDER_ID;
	}
	public void setADVC_ORDER_ID(String aDVCORDERID) {
		ADVC_ORDER_ID = aDVCORDERID;
	}
	public String getGOODS_ORDER_ID() {
		return GOODS_ORDER_ID;
	}
	public void setGOODS_ORDER_ID(String gOODSORDERID) {
		GOODS_ORDER_ID = gOODSORDERID;
	}
	public String getADVC_ORDER_NO() {
		return ADVC_ORDER_NO;
	}
	public void setADVC_ORDER_NO(String aDVCORDERNO) {
		ADVC_ORDER_NO = aDVCORDERNO;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILLTYPE) {
		BILL_TYPE = bILLTYPE;
	}
	public String getSALE_DATE() {
		return SALE_DATE;
	}
	public void setSALE_DATE(String sALEDATE) {
		SALE_DATE = sALEDATE;
	}
	public String getSALE_PSON_ID() {
		return SALE_PSON_ID;
	}
	public void setSALE_PSON_ID(String sALEPSONID) {
		SALE_PSON_ID = sALEPSONID;
	}
	public String getSALE_PSON_NAME() {
		return SALE_PSON_NAME;
	}
	public void setSALE_PSON_NAME(String sALEPSONNAME) {
		SALE_PSON_NAME = sALEPSONNAME;
	}
	public String getCUST_NAME() {
		return CUST_NAME;
	}
	public void setCUST_NAME(String cUSTNAME) {
		CUST_NAME = cUSTNAME;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}
	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
	public String getADVC_AMOUNT() {
		return ADVC_AMOUNT;
	}
	public void setADVC_AMOUNT(String aDVCAMOUNT) {
		ADVC_AMOUNT = aDVCAMOUNT;
	}
	public String getPAYABLE_AMOUNT() {
		return PAYABLE_AMOUNT;
	}
	public void setPAYABLE_AMOUNT(String pAYABLEAMOUNT) {
		PAYABLE_AMOUNT = pAYABLEAMOUNT;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String lEDGERID) {
		LEDGER_ID = lEDGERID;
	}
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	public void setLEDGER_NAME(String lEDGERNAME) {
		LEDGER_NAME = lEDGERNAME;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getSTTLE_FLAG() {
		return STTLE_FLAG;
	}
	public void setSTTLE_FLAG(String sTTLEFLAG) {
		STTLE_FLAG = sTTLEFLAG;
	}
	public String getMARKETING_ACT_ID() {
		return MARKETING_ACT_ID;
	}
	public void setMARKETING_ACT_ID(String mARKETINGACTID) {
		MARKETING_ACT_ID = mARKETINGACTID;
	}
	public String getMARKETING_ACT_NO() {
		return MARKETING_ACT_NO;
	}
	public void setMARKETING_ACT_NO(String mARKETINGACTNO) {
		MARKETING_ACT_NO = mARKETINGACTNO;
	}
	public String getMARKETING_ACT_NAME() {
		return MARKETING_ACT_NAME;
	}
	public void setMARKETING_ACT_NAME(String mARKETINGACTNAME) {
		MARKETING_ACT_NAME = mARKETINGACTNAME;
	}
	public String getMARKETING_CARD_ID() {
		return MARKETING_CARD_ID;
	}
	public void setMARKETING_CARD_ID(String mARKETINGCARDID) {
		MARKETING_CARD_ID = mARKETINGCARDID;
	}
	public String getMARKETING_CARD_NO() {
		return MARKETING_CARD_NO;
	}
	public void setMARKETING_CARD_NO(String mARKETINGCARDNO) {
		MARKETING_CARD_NO = mARKETINGCARDNO;
	}
	public String getTERM_ID() {
		return TERM_ID;
	}
	public void setTERM_ID(String tERMID) {
		TERM_ID = tERMID;
	}
	public String getTERM_NO() {
		return TERM_NO;
	}
	public void setTERM_NO(String tERMNO) {
		TERM_NO = tERMNO;
	}
	public String getTERM_NAME() {
		return TERM_NAME;
	}
	public void setTERM_NAME(String tERMNAME) {
		TERM_NAME = tERMNAME;
	}
	
	
	

}
