package com.hoperun.erp.sale.turnoverplan.model;

import com.hoperun.commons.model.BaseModel;

public class TurnoverplanChildModel extends BaseModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 发运单明细ID **/
	private String DELIVER_ORDER_DTL_ID;
	/** 发运单ID **/
	private String DELIVER_ORDER_ID;
	/** 销售订单ID **/
	private String SALE_ORDER_ID;
	/** 销售订单编号 **/
	private String SALE_ORDER_NO;
	/** 订货方ID **/
	private String ORDER_CHANN_ID;
	/** 订货方编号 **/
	private String ORDER_CHANN_NO;
	/** 订货方名称 **/
	private String ORDER_CHANN_NAME;
	/** 收货方ID **/
	private String RECV_CHANN_ID;
	/** 收货方编号 **/
	private String RECV_CHANN_NO;
	/** 收货方名称 **/
	private String RECV_CHANN_NAME;
	/** 收货地址 **/
	private String RECV_ADDR;
	/** 货品ID **/
	private String PRD_ID;
	/** 货品编号 **/
	private String PRD_NO;
	/** 货品名称 **/
	private String PRD_NAME;
	/** 规格型号 **/
	private String PRD_SPEC;
	/** 颜色 **/
	private String PRD_COLOR;
	/** 品牌 **/
	private String BRAND;
	/** 标准单位 **/
	private String STD_UNIT;
	/** 是否是备货 **/
	private String IS_BACKUP_FLAG;
	/** 特殊工艺ID **/
	private String SPCL_TECH_ID;
	/** 备注 **/
	private String REMARK;
	/** 预排排车数量 **/
	private String ADVC_PLAN_NUM;
	/** 计划排车数量 **/
	private String PLAN_NUM;
	/** 实际发货数量 **/
	private String REAL_SEND_NUM;
	/** 未发货数量 **/
	private String NO_SEND_NUM;
	/** 未发货品处理方式 **/
	private String NO_SEND_DEAL_TYPE;
	/** 删除标记 **/
	private String DEL_FLAG;

	/** 订单订单明细ID **/
	private String SALE_ORDER_DTL_ID;
	/** 最后一次 已排车的数量 **/
	private String LAST_PLANED_NUM;
	
	/**U9货品维度编号**/
	private String U_CODE;
	/**订货订单编号**/
	private String GOODS_ORDER_NO;
	/**折后单价**/
	private String DECT_PRICE;
	/**信用冻结单价**/
	private String CREDIT_FREEZE_PRICE;
	/** 收货地址编号**/
	private String RECV_ADDR_NO;

	private String STOREOUT_TIME;     //出库时间
	private String TRUCK_NO;           //车牌号码
	private String SN;                //货品码
	/**货品体积 **/
	private String VOLUME;
	/** 是否赠品标记 **/
	private String IS_FREE_FLAG;
	/** 新规格 **/
	private String NEW_SPEC;
	/** 新花号**/
	private String NEW_COLOR;
	/** 历史实发数量**/
	private String REAL_SEND_NUM_OLD;
	/** 行状态 0->未处理 2->已处理  3-> 关闭**/
	private String IS_SEND_FIN;
	
	public String getSTOREOUT_TIME() {
		return STOREOUT_TIME;
	}

	public void setSTOREOUT_TIME(String sTOREOUTTIME) {
		STOREOUT_TIME = sTOREOUTTIME;
	}

	public String getTRUCK_NO() {
		return TRUCK_NO;
	}

	public void setTRUCK_NO(String tRUCKNO) {
		TRUCK_NO = tRUCKNO;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}


	


	public String getDELIVER_ORDER_DTL_ID() {
		return DELIVER_ORDER_DTL_ID;
	}

	public void setDELIVER_ORDER_DTL_ID(String dELIVERORDERDTLID) {
		DELIVER_ORDER_DTL_ID = dELIVERORDERDTLID;
	}

	public String getDELIVER_ORDER_ID() {
		return DELIVER_ORDER_ID;
	}

	public void setDELIVER_ORDER_ID(String dELIVERORDERID) {
		DELIVER_ORDER_ID = dELIVERORDERID;
	}

	public String getSALE_ORDER_ID() {
		return SALE_ORDER_ID;
	}

	public void setSALE_ORDER_ID(String sALEORDERID) {
		SALE_ORDER_ID = sALEORDERID;
	}

	public String getSALE_ORDER_NO() {
		return SALE_ORDER_NO;
	}

	public void setSALE_ORDER_NO(String sALEORDERNO) {
		SALE_ORDER_NO = sALEORDERNO;
	}

	public String getORDER_CHANN_ID() {
		return ORDER_CHANN_ID;
	}

	public void setORDER_CHANN_ID(String oRDERCHANNID) {
		ORDER_CHANN_ID = oRDERCHANNID;
	}

	public String getORDER_CHANN_NO() {
		return ORDER_CHANN_NO;
	}

	public void setORDER_CHANN_NO(String oRDERCHANNNO) {
		ORDER_CHANN_NO = oRDERCHANNNO;
	}

	public String getORDER_CHANN_NAME() {
		return ORDER_CHANN_NAME;
	}

	public void setORDER_CHANN_NAME(String oRDERCHANNNAME) {
		ORDER_CHANN_NAME = oRDERCHANNNAME;
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

	public String getRECV_ADDR() {
		return RECV_ADDR;
	}

	public void setRECV_ADDR(String rECVADDR) {
		RECV_ADDR = rECVADDR;
	}

	public String getPRD_ID() {
		return PRD_ID;
	}

	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}

	public String getPRD_NO() {
		return PRD_NO;
	}

	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}

	public String getPRD_NAME() {
		return PRD_NAME;
	}

	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}

	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}

	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}

	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}

	public String getIS_BACKUP_FLAG() {
		return IS_BACKUP_FLAG;
	}

	public void setIS_BACKUP_FLAG(String iSBACKUPFLAG) {
		IS_BACKUP_FLAG = iSBACKUPFLAG;
	}
	
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	public String getADVC_PLAN_NUM() {
		return ADVC_PLAN_NUM;
	}

	public void setADVC_PLAN_NUM(String aDVCPLANNUM) {
		ADVC_PLAN_NUM = aDVCPLANNUM;
	}

	public String getPLAN_NUM() {
		return PLAN_NUM;
	}

	public void setPLAN_NUM(String pLANNUM) {
		PLAN_NUM = pLANNUM;
	}

	public String getREAL_SEND_NUM() {
		return REAL_SEND_NUM;
	}

	public void setREAL_SEND_NUM(String rEALSENDNUM) {
		REAL_SEND_NUM = rEALSENDNUM;
	}

	public String getNO_SEND_NUM() {
		return NO_SEND_NUM;
	}

	public void setNO_SEND_NUM(String nOSENDNUM) {
		NO_SEND_NUM = nOSENDNUM;
	}

	public String getNO_SEND_DEAL_TYPE() {
		return NO_SEND_DEAL_TYPE;
	}

	public void setNO_SEND_DEAL_TYPE(String nOSENDDEALTYPE) {
		NO_SEND_DEAL_TYPE = nOSENDDEALTYPE;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	public String getSALE_ORDER_DTL_ID() {
		return SALE_ORDER_DTL_ID;
	}

	public void setSALE_ORDER_DTL_ID(String sALEORDERDTLID) {
		SALE_ORDER_DTL_ID = sALEORDERDTLID;
	}

	public String getLAST_PLANED_NUM() {
		return LAST_PLANED_NUM;
	}

	public void setLAST_PLANED_NUM(String lASTPLANEDNUM) {
		LAST_PLANED_NUM = lASTPLANEDNUM;
	}

	public String getU_CODE() {
		return U_CODE;
	}

	public void setU_CODE(String uCODE) {
		U_CODE = uCODE;
	}

	public String getGOODS_ORDER_NO() {
		return GOODS_ORDER_NO;
	}

	public void setGOODS_ORDER_NO(String gOODSORDERNO) {
		GOODS_ORDER_NO = gOODSORDERNO;
	}

	public String getDECT_PRICE() {
		return DECT_PRICE;
	}

	public void setDECT_PRICE(String dECTPRICE) {
		DECT_PRICE = dECTPRICE;
	}

	public String getCREDIT_FREEZE_PRICE() {
		return CREDIT_FREEZE_PRICE;
	}

	public void setCREDIT_FREEZE_PRICE(String cREDITFREEZEPRICE) {
		CREDIT_FREEZE_PRICE = cREDITFREEZEPRICE;
	}

	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}

	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}

	public String getVOLUME() {
		return VOLUME;
	}

	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}

	public String getIS_FREE_FLAG() {
		return IS_FREE_FLAG;
	}

	public void setIS_FREE_FLAG(String iSFREEFLAG) {
		IS_FREE_FLAG = iSFREEFLAG;
	}

	public String getNEW_SPEC() {
		return NEW_SPEC;
	}

	public void setNEW_SPEC(String nEWSPEC) {
		NEW_SPEC = nEWSPEC;
	}

	public String getNEW_COLOR() {
		return NEW_COLOR;
	}

	public void setNEW_COLOR(String nEWCOLOR) {
		NEW_COLOR = nEWCOLOR;
	}

	public String getREAL_SEND_NUM_OLD() {
		return REAL_SEND_NUM_OLD;
	}

	public void setREAL_SEND_NUM_OLD(String rEALSENDNUMOLD) {
		REAL_SEND_NUM_OLD = rEALSENDNUMOLD;
	}

	public String getIS_SEND_FIN() {
		return IS_SEND_FIN;
	}

	public void setIS_SEND_FIN(String iSSENDFIN) {
		IS_SEND_FIN = iSSENDFIN;
	}
	
	
	
	
	

}
