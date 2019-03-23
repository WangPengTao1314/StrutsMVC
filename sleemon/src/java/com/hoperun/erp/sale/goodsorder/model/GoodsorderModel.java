/**
 * prjName:喜临门营销平台
 * ucName:订货订单处理
 * fileName:GoodsorderModel
 */
package com.hoperun.erp.sale.goodsorder.model;

import com.hoperun.commons.model.BaseModel;

/**
 * *@module *@func *@version 1.1 *@author zzb *@createdate 2013-08-30 15:55:09
 */
public class GoodsorderModel extends BaseModel {
	/** 订货订单ID **/
	private String GOODS_ORDER_ID;
	/** 订货订单编号 **/
	private String GOODS_ORDER_NO;
	/** 订货方ID **/
	private String ORDER_CHANN_ID;
	/** 订货方编号 **/
	private String ORDER_CHANN_NO;
	/** 订货方名称 **/
	private String ORDER_CHANN_NAME;

	/** 发货点ID **/
	private String SHIP_POINT_ID;
	/** 发货点名称 **/
	private String SHIP_POINT_NAME;
	/** 发货方ID **/
	private String SEND_CHANN_ID;
	/** 发货方编号 **/
	private String SEND_CHANN_NO;
	/** 发货方名称 **/
	private String SEND_CHANN_NAME;
	/** 订单类型 **/
	private String BILL_TYPE;
	/** 是否使用返利 **/
	private String IS_USE_REBATE;
	/** 收货方ID **/
	private String RECV_CHANN_ID;
	/** 收货方编号 **/
	private String RECV_CHANN_NO;
	/** 收货方名称 **/
	private String RECV_CHANN_NAME;
	/** 联系人 **/
	private String PERSON_CON;
	/** 电话 **/
	private String TEL;
	/** 收货地址 **/
	private String RECV_ADDR;
	/** 入库库房ID **/
	private String RECV_STORE_ID;
	/** 入库库房编号 **/
	private String RECV_STORE_NO;
	/** 入库库房名称 **/
	private String RECV_STORE_NAME;
	/** 预付款金额 **/
	private String ADVC_AMOUNT;
	/** 付款凭证号 **/
	private String PAY_VOUCH_NO;
	/** 付款凭证 **/
	private String PAY_VOUCH_PATH;
	/** 订货总额 **/
	private String TOTL_AMOUNT;
	/** 备注 **/
	private String REMARK;
	/** 审核人ID **/
	private String AUDIT_ID;
	/** 审核人姓名 **/
	private String AUDIT_NAME;
	/** 审核时间 **/
	private String AUDIT_TIME;
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
	/** 帐套ID **/
	private String LEDGER_ID;
	/** 帐套名称 **/
	private String LEDGER_NAME;
	/** 状态 **/
	private String STATE;
	/** 删除标记 **/
	private String DEL_FLAG;
	
	/** 区域ID **/
	private String AREA_ID;
	/** 区域编号 **/
	private String AREA_NO;
	/** 区域名称 **/
	private String AREA_NAME;
	/**订货日期**/
	private String ORDER_DATE;
    /** 收货地址编号 **/
    private String RECV_ADDR_NO;
    
    /** 区域服务中心ID **/
    private String AREA_SER_ID;
    /** 区域服务中心编号 **/
    private String AREA_SER_NO;
    /** 区域服务中心名称 **/
    private String AREA_SER_NAME;
    /** 要求到货日期 **/
    private String ADVC_SEND_DATE;
    /**退回原因*/
    private String RETURN_RSON;
    
    /**冗余字段 明细是否含有非标货品**/
    private String IS_HAVE_SPECL;

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

	/**
	 * get 订货订单ID value
	 * 
	 * @return the GOODS_ORDER_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getGOODS_ORDER_ID() {
		return GOODS_ORDER_ID;
	}

	/**
	 * set 订货订单ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setGOODS_ORDER_ID(String GOODS_ORDER_ID) {
		this.GOODS_ORDER_ID = GOODS_ORDER_ID;
	}

	/**
	 * get 订货订单编号 value
	 * 
	 * @return the GOODS_ORDER_NO
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getGOODS_ORDER_NO() {
		return GOODS_ORDER_NO;
	}

	/**
	 * set 订货订单编号 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setGOODS_ORDER_NO(String GOODS_ORDER_NO) {
		this.GOODS_ORDER_NO = GOODS_ORDER_NO;
	}

	/**
	 * get 订货方ID value
	 * 
	 * @return the ORDER_CHANN_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getORDER_CHANN_ID() {
		return ORDER_CHANN_ID;
	}

	/**
	 * set 订货方ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setORDER_CHANN_ID(String ORDER_CHANN_ID) {
		this.ORDER_CHANN_ID = ORDER_CHANN_ID;
	}

	/**
	 * get 订货方编号 value
	 * 
	 * @return the ORDER_CHANN_NO
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getORDER_CHANN_NO() {
		return ORDER_CHANN_NO;
	}

	/**
	 * set 订货方编号 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setORDER_CHANN_NO(String ORDER_CHANN_NO) {
		this.ORDER_CHANN_NO = ORDER_CHANN_NO;
	}

	/**
	 * get 订货方名称 value
	 * 
	 * @return the ORDER_CHANN_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getORDER_CHANN_NAME() {
		return ORDER_CHANN_NAME;
	}

	/**
	 * set 订货方名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setORDER_CHANN_NAME(String ORDER_CHANN_NAME) {
		this.ORDER_CHANN_NAME = ORDER_CHANN_NAME;
	}

	/**
	 * get 发货方ID value
	 * 
	 * @return the SEND_CHANN_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getSEND_CHANN_ID() {
		return SEND_CHANN_ID;
	}

	/**
	 * set 发货方ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setSEND_CHANN_ID(String SEND_CHANN_ID) {
		this.SEND_CHANN_ID = SEND_CHANN_ID;
	}

	/**
	 * get 发货方编号 value
	 * 
	 * @return the SEND_CHANN_NO
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getSEND_CHANN_NO() {
		return SEND_CHANN_NO;
	}

	/**
	 * set 发货方编号 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setSEND_CHANN_NO(String SEND_CHANN_NO) {
		this.SEND_CHANN_NO = SEND_CHANN_NO;
	}

	/**
	 * get 发货方名称 value
	 * 
	 * @return the SEND_CHANN_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getSEND_CHANN_NAME() {
		return SEND_CHANN_NAME;
	}

	/**
	 * set 发货方名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setSEND_CHANN_NAME(String SEND_CHANN_NAME) {
		this.SEND_CHANN_NAME = SEND_CHANN_NAME;
	}

	/**
	 * get 订单类型 value
	 * 
	 * @return the BILL_TYPE
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}

	/**
	 * set 订单类型 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setBILL_TYPE(String BILL_TYPE) {
		this.BILL_TYPE = BILL_TYPE;
	}

	/**
	 * get 是否使用返利 value
	 * 
	 * @return the IS_USE_REBATE
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getIS_USE_REBATE() {
		return IS_USE_REBATE;
	}

	/**
	 * set 是否使用返利 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setIS_USE_REBATE(String IS_USE_REBATE) {
		this.IS_USE_REBATE = IS_USE_REBATE;
	}

	/**
	 * get 收货方ID value
	 * 
	 * @return the RECV_CHANN_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_CHANN_ID() {
		return RECV_CHANN_ID;
	}

	/**
	 * set 收货方ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_CHANN_ID(String RECV_CHANN_ID) {
		this.RECV_CHANN_ID = RECV_CHANN_ID;
	}

	/**
	 * get 收货方编号 value
	 * 
	 * @return the RECV_CHANN_NO
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_CHANN_NO() {
		return RECV_CHANN_NO;
	}

	/**
	 * set 收货方编号 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_CHANN_NO(String RECV_CHANN_NO) {
		this.RECV_CHANN_NO = RECV_CHANN_NO;
	}

	/**
	 * get 收货方名称 value
	 * 
	 * @return the RECV_CHANN_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_CHANN_NAME() {
		return RECV_CHANN_NAME;
	}

	/**
	 * set 收货方名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_CHANN_NAME(String RECV_CHANN_NAME) {
		this.RECV_CHANN_NAME = RECV_CHANN_NAME;
	}

	/**
	 * get 联系人 value
	 * 
	 * @return the PERSON_CON
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getPERSON_CON() {
		return PERSON_CON;
	}

	/**
	 * set 联系人 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setPERSON_CON(String PERSON_CON) {
		this.PERSON_CON = PERSON_CON;
	}

	/**
	 * get 电话 value
	 * 
	 * @return the TEL
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getTEL() {
		return TEL;
	}

	/**
	 * set 电话 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setTEL(String TEL) {
		this.TEL = TEL;
	}

	/**
	 * get 收货地址 value
	 * 
	 * @return the RECV_ADDR
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_ADDR() {
		return RECV_ADDR;
	}

	/**
	 * set 收货地址 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_ADDR(String RECV_ADDR) {
		this.RECV_ADDR = RECV_ADDR;
	}

	/**
	 * get 入库库房ID value
	 * 
	 * @return the RECV_STORE_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_STORE_ID() {
		return RECV_STORE_ID;
	}

	/**
	 * set 入库库房ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_STORE_ID(String RECV_STORE_ID) {
		this.RECV_STORE_ID = RECV_STORE_ID;
	}

	/**
	 * get 入库库房编号 value
	 * 
	 * @return the RECV_STORE_NO
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_STORE_NO() {
		return RECV_STORE_NO;
	}

	/**
	 * set 入库库房编号 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_STORE_NO(String RECV_STORE_NO) {
		this.RECV_STORE_NO = RECV_STORE_NO;
	}

	/**
	 * get 入库库房名称 value
	 * 
	 * @return the RECV_STORE_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getRECV_STORE_NAME() {
		return RECV_STORE_NAME;
	}

	/**
	 * set 入库库房名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setRECV_STORE_NAME(String RECV_STORE_NAME) {
		this.RECV_STORE_NAME = RECV_STORE_NAME;
	}

	/**
	 * get 预付款金额 value
	 * 
	 * @return the ADVC_AMOUNT
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getADVC_AMOUNT() {
		return ADVC_AMOUNT;
	}

	/**
	 * set 预付款金额 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setADVC_AMOUNT(String ADVC_AMOUNT) {
		this.ADVC_AMOUNT = ADVC_AMOUNT;
	}

	/**
	 * get 付款凭证号 value
	 * 
	 * @return the PAY_VOUCH_NO
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getPAY_VOUCH_NO() {
		return PAY_VOUCH_NO;
	}

	/**
	 * set 付款凭证号 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setPAY_VOUCH_NO(String PAY_VOUCH_NO) {
		this.PAY_VOUCH_NO = PAY_VOUCH_NO;
	}

	/**
	 * get 付款凭证 value
	 * 
	 * @return the PAY_VOUCH_PATH
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getPAY_VOUCH_PATH() {
		return PAY_VOUCH_PATH;
	}

	/**
	 * set 付款凭证 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setPAY_VOUCH_PATH(String PAY_VOUCH_PATH) {
		this.PAY_VOUCH_PATH = PAY_VOUCH_PATH;
	}

	/**
	 * get 订货总额 value
	 * 
	 * @return the TOTL_AMOUNT
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getTOTL_AMOUNT() {
		return TOTL_AMOUNT;
	}

	/**
	 * set 订货总额 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setTOTL_AMOUNT(String TOTL_AMOUNT) {
		this.TOTL_AMOUNT = TOTL_AMOUNT;
	}

	/**
	 * get 备注 value
	 * 
	 * @return the REMARK
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getREMARK() {
		return REMARK;
	}

	/**
	 * set 备注 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setREMARK(String REMARK) {
		this.REMARK = REMARK;
	}

	/**
	 * get 审核人ID value
	 * 
	 * @return the AUDIT_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}

	/**
	 * set 审核人ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setAUDIT_ID(String AUDIT_ID) {
		this.AUDIT_ID = AUDIT_ID;
	}

	/**
	 * get 审核人姓名 value
	 * 
	 * @return the AUDIT_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}

	/**
	 * set 审核人姓名 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setAUDIT_NAME(String AUDIT_NAME) {
		this.AUDIT_NAME = AUDIT_NAME;
	}

	/**
	 * get 审核时间 value
	 * 
	 * @return the AUDIT_TIME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}

	/**
	 * set 审核时间 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setAUDIT_TIME(String AUDIT_TIME) {
		this.AUDIT_TIME = AUDIT_TIME;
	}

	/**
	 * get 制单人名称 value
	 * 
	 * @return the CRE_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getCRE_NAME() {
		return CRE_NAME;
	}

	/**
	 * set 制单人名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setCRE_NAME(String CRE_NAME) {
		this.CRE_NAME = CRE_NAME;
	}

	/**
	 * get 制单人ID value
	 * 
	 * @return the CREATOR
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getCREATOR() {
		return CREATOR;
	}

	/**
	 * set 制单人ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setCREATOR(String CREATOR) {
		this.CREATOR = CREATOR;
	}

	/**
	 * get 制单时间 value
	 * 
	 * @return the CRE_TIME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getCRE_TIME() {
		return CRE_TIME;
	}

	/**
	 * set 制单时间 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setCRE_TIME(String CRE_TIME) {
		this.CRE_TIME = CRE_TIME;
	}

	/**
	 * get 更新人名称 value
	 * 
	 * @return the UPD_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getUPD_NAME() {
		return UPD_NAME;
	}

	/**
	 * set 更新人名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setUPD_NAME(String UPD_NAME) {
		this.UPD_NAME = UPD_NAME;
	}

	/**
	 * get 更新人ID value
	 * 
	 * @return the UPDATOR
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getUPDATOR() {
		return UPDATOR;
	}

	/**
	 * set 更新人ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setUPDATOR(String UPDATOR) {
		this.UPDATOR = UPDATOR;
	}

	/**
	 * get 更新时间 value
	 * 
	 * @return the UPD_TIME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getUPD_TIME() {
		return UPD_TIME;
	}

	/**
	 * set 更新时间 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setUPD_TIME(String UPD_TIME) {
		this.UPD_TIME = UPD_TIME;
	}

	/**
	 * get 制单部门ID value
	 * 
	 * @return the DEPT_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getDEPT_ID() {
		return DEPT_ID;
	}

	/**
	 * set 制单部门ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setDEPT_ID(String DEPT_ID) {
		this.DEPT_ID = DEPT_ID;
	}

	/**
	 * get 制单部门名称 value
	 * 
	 * @return the DEPT_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	/**
	 * set 制单部门名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setDEPT_NAME(String DEPT_NAME) {
		this.DEPT_NAME = DEPT_NAME;
	}

	/**
	 * get 制单机构ID value
	 * 
	 * @return the ORG_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getORG_ID() {
		return ORG_ID;
	}

	/**
	 * set 制单机构ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setORG_ID(String ORG_ID) {
		this.ORG_ID = ORG_ID;
	}

	/**
	 * get 制单机构名称 value
	 * 
	 * @return the ORG_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getORG_NAME() {
		return ORG_NAME;
	}

	/**
	 * set 制单机构名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setORG_NAME(String ORG_NAME) {
		this.ORG_NAME = ORG_NAME;
	}

	/**
	 * get 帐套ID value
	 * 
	 * @return the LEDGER_ID
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}

	/**
	 * set 帐套ID value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setLEDGER_ID(String LEDGER_ID) {
		this.LEDGER_ID = LEDGER_ID;
	}

	/**
	 * get 帐套名称 value
	 * 
	 * @return the LEDGER_NAME
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}

	/**
	 * set 帐套名称 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setLEDGER_NAME(String LEDGER_NAME) {
		this.LEDGER_NAME = LEDGER_NAME;
	}

	/**
	 * get 状态 value
	 * 
	 * @return the STATE
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getSTATE() {
		return STATE;
	}

	/**
	 * set 状态 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setSTATE(String STATE) {
		this.STATE = STATE;
	}

	/**
	 * get 删除标记 value
	 * 
	 * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * set 删除标记 value
	 * 
	 * @createdate 2013-08-30 15:55:09
	 * @author zzb
	 * @createdate 2013-08-30 15:55:09
	 */
	public void setDEL_FLAG(String DEL_FLAG) {
		this.DEL_FLAG = DEL_FLAG;
	}

	public String getAREA_ID() {
		return AREA_ID;
	}

	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}

	public String getAREA_NO() {
		return AREA_NO;
	}

	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}

	public String getAREA_NAME() {
		return AREA_NAME;
	}

	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}

	public String getORDER_DATE() {
		return ORDER_DATE;
	}

	public void setORDER_DATE(String oRDERDATE) {
		ORDER_DATE = oRDERDATE;
	}

	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}

	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
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

	public String getADVC_SEND_DATE() {
		return ADVC_SEND_DATE;
	}

	public void setADVC_SEND_DATE(String aDVCSENDDATE) {
		ADVC_SEND_DATE = aDVCSENDDATE;
	}

	public String getIS_HAVE_SPECL() {
		return IS_HAVE_SPECL;
	}

	public void setIS_HAVE_SPECL(String iSHAVESPECL) {
		IS_HAVE_SPECL = iSHAVESPECL;
	}

	/**
	 * @return the rETURN_RSON
	 */
	public String getRETURN_RSON() {
		return RETURN_RSON;
	}

	/**
	 * @param rETURNRSON the rETURN_RSON to set
	 */
	public void setRETURN_RSON(String rETURNRSON) {
		RETURN_RSON = rETURNRSON;
	}
	
	
	
	
}