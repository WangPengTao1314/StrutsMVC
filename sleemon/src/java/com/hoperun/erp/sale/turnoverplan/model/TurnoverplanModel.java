package com.hoperun.erp.sale.turnoverplan.model;

import com.hoperun.commons.model.BaseModel;

public class TurnoverplanModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**发运单ID**/
	private String DELIVER_ORDER_ID;
	/**发运单编号**/
	private String DELIVER_ORDER_NO;
	/**发运方式**/
	private String DELIVER_TYPE;
	/**发货方类型**/
	private String CHANN_TYPE;
	/**供应商ID**/
	private String PRVD_ID;
	/**供应商编号**/
	private String PRVD_NO;
	/**供应商名称**/
	private String PRVD_NAME;
	/**车型**/
	private String TRUCK_TYPE;
	/**区域服务中心ID**/
	private String AREA_SER_ID;
	/**区域服务中心编号**/
	private String AREA_SER_NO;
	/**区域服务中心名称**/
	private String AREA_SER_NAME;
	/**发货方ID**/
	private String SEND_CHANN_ID;
	/**发货方编号**/
	private String SEND_CHANN_NO;
	/**发货名称**/
	private String SEND_CHANN_NAME;
	/**预计发货日期**/
	private String ADVC_SEND_DATE;
	/**总容积**/
	private String TOTAL_VOLUME;
	/**状态**/
	private String STATE;
	/**备注**/
	private String REMARK;
	/**删除标记**/
	private String DEL_FLAG;
	/**发货点ID**/
	private String SHIP_POINT_ID;
	/**发货点名称**/
	private String SHIP_POINT_NAME;
	/**进场时间**/
	private String APPCH_TIME;
	/**单据类型**/
	private String BILL_TYPE;
	/**收货地址编号*/
	private String RECV_ADDR_NO;
	/**收货地址*/
	private String RECV_ADDR;
	
	private String CREATOR;
	private String CRE_NAME;
	private String CRE_TIME;
	private String DEPT_ID;
	private String DEPT_NAME;
	private String ORG_ID;
	private String ORG_NAME;
	private String LEDGER_ID;
	private String LEDGER_NAME;
	
	//收货方ID
	private String RECV_CHANN_ID;
	//收货方编号
	private String RECV_CHANN_NO;
	//收货方名称
	private String RECV_CHANN_NAME;
	//托运单号
	private String CONSAR_NO;
	//司机手机号
	private String DRV_MOB_NO;
	//合并发运单号
	private String JOIN_DELIVER_ORDER_NO;
	/**U9订单编号 **/
	private String U9_SALE_ORDER_NO;
	/**U9订单行编号 **/
	private String U9_SALE_ORDER_DTL_NO;
	/** 来源发运单ID **/
	private String FROM_BILL_ID;
	
	public String getDELIVER_ORDER_ID() {
		return DELIVER_ORDER_ID;
	}
	public void setDELIVER_ORDER_ID(String dELIVERORDERID) {
		DELIVER_ORDER_ID = dELIVERORDERID;
	}
	public String getDELIVER_ORDER_NO() {
		return DELIVER_ORDER_NO;
	}
	public void setDELIVER_ORDER_NO(String dELIVERORDERNO) {
		DELIVER_ORDER_NO = dELIVERORDERNO;
	}
	public String getDELIVER_TYPE() {
		return DELIVER_TYPE;
	}
	public void setDELIVER_TYPE(String dELIVERTYPE) {
		DELIVER_TYPE = dELIVERTYPE;
	}
	public String getTRUCK_TYPE() {
		return TRUCK_TYPE;
	}
	public void setTRUCK_TYPE(String tRUCKTYPE) {
		TRUCK_TYPE = tRUCKTYPE;
	}
	public String getAREA_SER_ID() {
		return AREA_SER_ID;
	}
	public void setAREA_SER_ID(String aREASERID) {
		AREA_SER_ID = aREASERID;
	}
	public String getAREA_SER_NO() {
		return AREA_SER_NO;
	}
	public void setAREA_SER_NO(String aREASERNO) {
		AREA_SER_NO = aREASERNO;
	}
	public String getAREA_SER_NAME() {
		return AREA_SER_NAME;
	}
	public void setAREA_SER_NAME(String aREASERNAME) {
		AREA_SER_NAME = aREASERNAME;
	}
	public String getSEND_CHANN_ID() {
		return SEND_CHANN_ID;
	}
	public void setSEND_CHANN_ID(String sENDCHANNID) {
		SEND_CHANN_ID = sENDCHANNID;
	}
	public String getSEND_CHANN_NO() {
		return SEND_CHANN_NO;
	}
	public void setSEND_CHANN_NO(String sENDCHANNNO) {
		SEND_CHANN_NO = sENDCHANNNO;
	}
	public String getSEND_CHANN_NAME() {
		return SEND_CHANN_NAME;
	}
	public void setSEND_CHANN_NAME(String sENDCHANNNAME) {
		SEND_CHANN_NAME = sENDCHANNNAME;
	}
	public String getADVC_SEND_DATE() {
		return ADVC_SEND_DATE;
	}
	public void setADVC_SEND_DATE(String aDVCSENDDATE) {
		ADVC_SEND_DATE = aDVCSENDDATE;
	}
	public String getTOTAL_VOLUME() {
		return TOTAL_VOLUME;
	}
	public void setTOTAL_VOLUME(String tOTALVOLUME) {
		TOTAL_VOLUME = tOTALVOLUME;
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
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getSHIP_POINT_ID() {
		return SHIP_POINT_ID;
	}
	public void setSHIP_POINT_ID(String sHIPPOINTID) {
		SHIP_POINT_ID = sHIPPOINTID;
	}
	public String getSHIP_POINT_NAME() {
		return SHIP_POINT_NAME;
	}
	public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
		SHIP_POINT_NAME = sHIPPOINTNAME;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
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
 
	public String getAPPCH_TIME() {
		return APPCH_TIME;
	}
	public void setAPPCH_TIME(String aPPCHTIME) {
		APPCH_TIME = aPPCHTIME;
	}
	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}
	public void setCHANN_TYPE(String cHANNTYPE) {
		CHANN_TYPE = cHANNTYPE;
	}
	public String getPRVD_ID() {
		return PRVD_ID;
	}
	public void setPRVD_ID(String pRVDID) {
		PRVD_ID = pRVDID;
	}
	public String getPRVD_NO() {
		return PRVD_NO;
	}
	public void setPRVD_NO(String pRVDNO) {
		PRVD_NO = pRVDNO;
	}
	public String getPRVD_NAME() {
		return PRVD_NAME;
	}
	public void setPRVD_NAME(String pRVDNAME) {
		PRVD_NAME = pRVDNAME;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bILLTYPE) {
		BILL_TYPE = bILLTYPE;
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
	public String getRECV_CHANN_ID() {
		return RECV_CHANN_ID;
	}
	public void setRECV_CHANN_ID(String rECVCHANNID) {
		RECV_CHANN_ID = rECVCHANNID;
	}
	public String getRECV_CHANN_NO() {
		return RECV_CHANN_NO;
	}
	public void setRECV_CHANN_NO(String rECVCHANNNO) {
		RECV_CHANN_NO = rECVCHANNNO;
	}
	public String getRECV_CHANN_NAME() {
		return RECV_CHANN_NAME;
	}
	public void setRECV_CHANN_NAME(String rECVCHANNNAME) {
		RECV_CHANN_NAME = rECVCHANNNAME;
	}
	public String getCONSAR_NO() {
		return CONSAR_NO;
	}
	public void setCONSAR_NO(String cONSARNO) {
		CONSAR_NO = cONSARNO;
	}
	public String getDRV_MOB_NO() {
		return DRV_MOB_NO;
	}
	public void setDRV_MOB_NO(String dRVMOBNO) {
		DRV_MOB_NO = dRVMOBNO;
	}
	public String getJOIN_DELIVER_ORDER_NO() {
		return JOIN_DELIVER_ORDER_NO;
	}
	public void setJOIN_DELIVER_ORDER_NO(String jOINDELIVERORDERNO) {
		JOIN_DELIVER_ORDER_NO = jOINDELIVERORDERNO;
	}
	public String getU9_SALE_ORDER_NO() {
		return U9_SALE_ORDER_NO;
	}
	
	public void setU9_SALE_ORDER_NO(String u9SALEORDERNO) {
		U9_SALE_ORDER_NO = u9SALEORDERNO;
	}
	public String getU9_SALE_ORDER_DTL_NO() {
		return U9_SALE_ORDER_DTL_NO;
	}
	public void setU9_SALE_ORDER_DTL_NO(String u9SALEORDERDTLNO) {
		U9_SALE_ORDER_DTL_NO = u9SALEORDERDTLNO;
	}
	public String getFROM_BILL_ID() {
		return FROM_BILL_ID;
	}
	public void setFROM_BILL_ID(String fROMBILLID) {
		FROM_BILL_ID = fROMBILLID;
	}
	
	
	
	

}
