package com.hoperun.drp.oamg.decorationreit.model;

import com.hoperun.commons.model.BaseModel;

/**
 * *@module *@func *@version 1.1 *@author zhu_changxia *@createdate 2013-08-13
 * 14:01:22
 */

public class DecorationreitModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String RNVTN_REIT_REQ_ID;
	private String RNVTN_REIT_REQ_NO;
	private String CHANN_RNVTN_ID;
	private String CHANN_RNVTN_NO;
	private String RNVTN_PROP;
	private String CHANN_ID;
	private String CHANN_NO;
	private String CHANN_NAME;
	private String TERM_ID;
	private String TERM_NO;
	private String TERM_NAME;
	private String AREA_ID;
	private String AREA_NO;
	private String AREA_NAME;
	private String AREA_MANAGE_ID;
	private String AREA_MANAGE_NAME;
	private String AREA_MANAGE_TEL;
	private String REIT_AMOUNT_PS;
	private String REIT_AMOUNT;
	private String DRAW_AREA;
	private String DRAW_REIT_AMOUNT_PS;
	private String DRAW_FISH_DATE;
	private String RNVTN_FISH_DATE;
	private String PUNISH_REMARK;
	private String DEPOSIT;
	private String RNVTN_DAYS;
	private String DEPOSIT_RETURN_AMOUNT;
	private String REAL_REIT_AMOUNT;
	private String REIT_POLICY;
	private String TOTAL_REITED_NUM;
	private String REITED_NUM;
	private String REITED_RATE;
	private String CAN_RETURN_AMOUNT;
	private String TOTAL_RETURN_AMOUNT;
	private String LEFT_CAN_RETURN_AMOUNT;
	private String SALE_COMPACT_AMOUNT;
	private String REQ_ID;
	private String REQ_NAME;
	private String REQ_DATE;
	
	/**第几次报销**/
    private String COUNT_NUM;
	/** 附件路径 **/
	private String ATT_PATH;
	/** 附件ID **/
	private String ATT_ID;

	/** 状态 **/
	private String STATE;
	/** 备注 **/
	private String REMARK;
	/** 制单人ID **/
	private String CREATOR;
	/** 制单人名称 **/
	private String CRE_NAME;
	/** 制单时间 **/
	private String CRE_TIME;
	/** 更新人ID **/
	private String UPDATOR;
	/** 更新人名称 **/
	private String UPD_NAME;
	/** 更新时间 **/
	private String UPD_TIME;
	/** 审核人ID **/
	private String AUDIT_ID;
	/** 审核人姓名 **/
	private String AUDIT_NAME;
	/** 审核时间 **/
	private String AUDIT_TIME;
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
	/** 删除标记 **/
	private String DEL_FLAG;
	/**渠道联系人**/
    private String CHANN_PERSON_CON;
    /**渠道联系人电话**/
    private String CHANN_TEL;
    /**开店年份**/
    private String OPEN_SALE_YEAR;
    /**设计师**/
    private String DESIGN_PERSON;
    /**商场ID**/
    private String SALE_STORE_ID;
    /**商场名称**/
	private String SALE_STORE_NAME;
	/**地址**/
    private String ADDRESS;
    /**经营品牌**/
    private String BUSS_SCOPE;
    /**门店使用面积**/
    private String USE_AREA;
    /**商场位置类别**/
    private String LOCAL_TYPE;
    /**是否北方公司**/
    private String IS_NORTH;
    /**预算科目ID**/
    private String BUDGET_ITEM_ID;
    /**预算科目编号**/
    private String BUDGET_ITEM_NO;
    /**预算科目名称**/
    private String BUDGET_ITEM_NAME;
    /**照片**/
    private String PIC;
    /**装修申请附件**/
    private String ZHUANGXIUSQ;
    /**关联已报项附件**/
    private String YBXIANG;
    /**卖场验收表附件**/
    private String MCYSTAB;
    /**整改验收附件**/
    private String ZGYANSHOU;
    /**装修平面图**/
    private String ZXPIC;
    /**装修发票**/
    private String ZXFAPIAO;
    /**本年度至今发货金额**/
    private String YEAR_GOODS_AMOUNT;
    /**累计四个季度实际完成**/
    private String QUARTER_RATE;
    /**有无违反现象**/
    private String IS_VIOLATE_REMARK;
    
    private String BUDGET_QUOTA_ID;
    
    private String DESIGN_ID;
    /**金额说明**/
    private String AMOUNT_DESC;
    /**
     * 装修申请单标记  是否有DM申请单 
     */
    private String FROM_BILL_FLAG;
    
    private String FROM_BILL_FLAGT;
    
    private String FORM_BILL_FLAG_T;
    /**     * 老OA走的申请单附件
     */
    private String OLD_OA_ORDER_PIC;
    /**总报销金额**/
    private String TOTAL_REIT_AMOUNT;
    
    public String getTOTAL_REIT_AMOUNT() {
		return TOTAL_REIT_AMOUNT;
	}

	public void setTOTAL_REIT_AMOUNT(String total_reit_amount) {
		TOTAL_REIT_AMOUNT = total_reit_amount;
	}

	public String getDESIGN_ID() {
		return DESIGN_ID;
	}

	public void setDESIGN_ID(String design_id) {
		DESIGN_ID = design_id;
	}

	public String getBUDGET_QUOTA_ID() {
		return BUDGET_QUOTA_ID;
	}

	public void setBUDGET_QUOTA_ID(String budget_quota_id) {
		BUDGET_QUOTA_ID = budget_quota_id;
	}

	public String getYEAR_GOODS_AMOUNT() {
		return YEAR_GOODS_AMOUNT;
	}

	public void setYEAR_GOODS_AMOUNT(String year_goods_amount) {
		YEAR_GOODS_AMOUNT = year_goods_amount;
	}

	public String getPIC() {
		return PIC;
	}

	public void setPIC(String pic) {
		PIC = pic;
	}

	public String getZHUANGXIUSQ() {
		return ZHUANGXIUSQ;
	}

	public void setZHUANGXIUSQ(String zhuangxiusq) {
		ZHUANGXIUSQ = zhuangxiusq;
	}

	public String getYBXIANG() {
		return YBXIANG;
	}

	public void setYBXIANG(String ybxiang) {
		YBXIANG = ybxiang;
	}

	public String getMCYSTAB() {
		return MCYSTAB;
	}

	public void setMCYSTAB(String mcystab) {
		MCYSTAB = mcystab;
	}

	public String getZGYANSHOU() {
		return ZGYANSHOU;
	}

	public void setZGYANSHOU(String zgyanshou) {
		ZGYANSHOU = zgyanshou;
	}

	public String getZXPIC() {
		return ZXPIC;
	}

	public void setZXPIC(String zxpic) {
		ZXPIC = zxpic;
	}

	public String getZXFAPIAO() {
		return ZXFAPIAO;
	}

	public void setZXFAPIAO(String zxfapiao) {
		ZXFAPIAO = zxfapiao;
	}

	public String getOPEN_SALE_YEAR() {
		return OPEN_SALE_YEAR;
	}

	public void setOPEN_SALE_YEAR(String open_sale_year) {
		OPEN_SALE_YEAR = open_sale_year;
	}

	public String getDESIGN_PERSON() {
		return DESIGN_PERSON;
	}

	public void setDESIGN_PERSON(String design_person) {
		DESIGN_PERSON = design_person;
	}

	public String getSALE_STORE_ID() {
		return SALE_STORE_ID;
	}

	public void setSALE_STORE_ID(String sale_store_id) {
		SALE_STORE_ID = sale_store_id;
	}

	public String getSALE_STORE_NAME() {
		return SALE_STORE_NAME;
	}

	public void setSALE_STORE_NAME(String sale_store_name) {
		SALE_STORE_NAME = sale_store_name;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String address) {
		ADDRESS = address;
	}

	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}

	public void setBUSS_SCOPE(String buss_scope) {
		BUSS_SCOPE = buss_scope;
	}

	public String getUSE_AREA() {
		return USE_AREA;
	}

	public void setUSE_AREA(String use_area) {
		USE_AREA = use_area;
	}

	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}

	public void setLOCAL_TYPE(String local_type) {
		LOCAL_TYPE = local_type;
	}

	public String getIS_NORTH() {
		return IS_NORTH;
	}

	public void setIS_NORTH(String is_north) {
		IS_NORTH = is_north;
	}

	public String getBUDGET_ITEM_ID() {
		return BUDGET_ITEM_ID;
	}

	public void setBUDGET_ITEM_ID(String budget_item_id) {
		BUDGET_ITEM_ID = budget_item_id;
	}

	public String getBUDGET_ITEM_NO() {
		return BUDGET_ITEM_NO;
	}

	public void setBUDGET_ITEM_NO(String budget_item_no) {
		BUDGET_ITEM_NO = budget_item_no;
	}

	public String getBUDGET_ITEM_NAME() {
		return BUDGET_ITEM_NAME;
	}

	public void setBUDGET_ITEM_NAME(String budget_item_name) {
		BUDGET_ITEM_NAME = budget_item_name;
	}

	public String getRNVTN_REIT_REQ_ID() {
		return RNVTN_REIT_REQ_ID;
	}

	public void setRNVTN_REIT_REQ_ID(String rNVTNREITREQID) {
		RNVTN_REIT_REQ_ID = rNVTNREITREQID;
	}

	public String getRNVTN_REIT_REQ_NO() {
		return RNVTN_REIT_REQ_NO;
	}

	public void setRNVTN_REIT_REQ_NO(String rNVTNREITREQNO) {
		RNVTN_REIT_REQ_NO = rNVTNREITREQNO;
	}

	public String getCHANN_RNVTN_ID() {
		return CHANN_RNVTN_ID;
	}

	public void setCHANN_RNVTN_ID(String cHANNRNVTNID) {
		CHANN_RNVTN_ID = cHANNRNVTNID;
	}

	public String getCHANN_RNVTN_NO() {
		return CHANN_RNVTN_NO;
	}

	public void setCHANN_RNVTN_NO(String cHANNRNVTNNO) {
		CHANN_RNVTN_NO = cHANNRNVTNNO;
	}

	public String getRNVTN_PROP() {
		return RNVTN_PROP;
	}

	public void setRNVTN_PROP(String rNVTNPROP) {
		RNVTN_PROP = rNVTNPROP;
	}

	public String getCHANN_ID() {
		return CHANN_ID;
	}

	public void setCHANN_ID(String cHANNID) {
		CHANN_ID = cHANNID;
	}

	public String getCHANN_NO() {
		return CHANN_NO;
	}

	public void setCHANN_NO(String cHANNNO) {
		CHANN_NO = cHANNNO;
	}

	public String getCHANN_NAME() {
		return CHANN_NAME;
	}

	public void setCHANN_NAME(String cHANNNAME) {
		CHANN_NAME = cHANNNAME;
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

	public String getAREA_MANAGE_ID() {
		return AREA_MANAGE_ID;
	}

	public void setAREA_MANAGE_ID(String aREAMANAGEID) {
		AREA_MANAGE_ID = aREAMANAGEID;
	}

	public String getAREA_MANAGE_NAME() {
		return AREA_MANAGE_NAME;
	}

	public void setAREA_MANAGE_NAME(String aREAMANAGENAME) {
		AREA_MANAGE_NAME = aREAMANAGENAME;
	}

	public String getAREA_MANAGE_TEL() {
		return AREA_MANAGE_TEL;
	}

	public void setAREA_MANAGE_TEL(String aREAMANAGETEL) {
		AREA_MANAGE_TEL = aREAMANAGETEL;
	}

	public String getREIT_AMOUNT_PS() {
		return REIT_AMOUNT_PS;
	}

	public void setREIT_AMOUNT_PS(String rEITAMOUNTPS) {
		REIT_AMOUNT_PS = rEITAMOUNTPS;
	}

	public String getREIT_AMOUNT() {
		return REIT_AMOUNT;
	}

	public void setREIT_AMOUNT(String rEITAMOUNT) {
		REIT_AMOUNT = rEITAMOUNT;
	}

	public String getDRAW_AREA() {
		return DRAW_AREA;
	}

	public void setDRAW_AREA(String dRAWAREA) {
		DRAW_AREA = dRAWAREA;
	}

	public String getDRAW_REIT_AMOUNT_PS() {
		return DRAW_REIT_AMOUNT_PS;
	}

	public void setDRAW_REIT_AMOUNT_PS(String dRAWREITAMOUNTPS) {
		DRAW_REIT_AMOUNT_PS = dRAWREITAMOUNTPS;
	}

	public String getDRAW_FISH_DATE() {
		return DRAW_FISH_DATE;
	}

	public void setDRAW_FISH_DATE(String dRAWFISHDATE) {
		DRAW_FISH_DATE = dRAWFISHDATE;
	}

	public String getRNVTN_FISH_DATE() {
		return RNVTN_FISH_DATE;
	}

	public void setRNVTN_FISH_DATE(String rNVTNFISHDATE) {
		RNVTN_FISH_DATE = rNVTNFISHDATE;
	}

	public String getPUNISH_REMARK() {
		return PUNISH_REMARK;
	}

	public void setPUNISH_REMARK(String pUNISHREMARK) {
		PUNISH_REMARK = pUNISHREMARK;
	}

	public String getDEPOSIT() {
		return DEPOSIT;
	}

	public void setDEPOSIT(String dEPOSIT) {
		DEPOSIT = dEPOSIT;
	}

	public String getRNVTN_DAYS() {
		return RNVTN_DAYS;
	}

	public void setRNVTN_DAYS(String rNVTNDAYS) {
		RNVTN_DAYS = rNVTNDAYS;
	}

	public String getDEPOSIT_RETURN_AMOUNT() {
		return DEPOSIT_RETURN_AMOUNT;
	}

	public void setDEPOSIT_RETURN_AMOUNT(String dEPOSITRETURNAMOUNT) {
		DEPOSIT_RETURN_AMOUNT = dEPOSITRETURNAMOUNT;
	}

	public String getREAL_REIT_AMOUNT() {
		return REAL_REIT_AMOUNT;
	}

	public void setREAL_REIT_AMOUNT(String rEALREITAMOUNT) {
		REAL_REIT_AMOUNT = rEALREITAMOUNT;
	}

	public String getREIT_POLICY() {
		return REIT_POLICY;
	}

	public void setREIT_POLICY(String rEITPOLICY) {
		REIT_POLICY = rEITPOLICY;
	}

	public String getTOTAL_REITED_NUM() {
		return TOTAL_REITED_NUM;
	}

	public void setTOTAL_REITED_NUM(String tOTALREITEDNUM) {
		TOTAL_REITED_NUM = tOTALREITEDNUM;
	}

	public String getREITED_NUM() {
		return REITED_NUM;
	}

	public void setREITED_NUM(String rEITEDNUM) {
		REITED_NUM = rEITEDNUM;
	}

	public String getREITED_RATE() {
		return REITED_RATE;
	}

	public void setREITED_RATE(String rEITEDRATE) {
		REITED_RATE = rEITEDRATE;
	}

	public String getCAN_RETURN_AMOUNT() {
		return CAN_RETURN_AMOUNT;
	}

	public void setCAN_RETURN_AMOUNT(String cANRETURNAMOUNT) {
		CAN_RETURN_AMOUNT = cANRETURNAMOUNT;
	}

	public String getTOTAL_RETURN_AMOUNT() {
		return TOTAL_RETURN_AMOUNT;
	}

	public void setTOTAL_RETURN_AMOUNT(String tOTALRETURNAMOUNT) {
		TOTAL_RETURN_AMOUNT = tOTALRETURNAMOUNT;
	}

	public String getLEFT_CAN_RETURN_AMOUNT() {
		return LEFT_CAN_RETURN_AMOUNT;
	}

	public void setLEFT_CAN_RETURN_AMOUNT(String lEFTCANRETURNAMOUNT) {
		LEFT_CAN_RETURN_AMOUNT = lEFTCANRETURNAMOUNT;
	}

	public String getSALE_COMPACT_AMOUNT() {
		return SALE_COMPACT_AMOUNT;
	}

	public void setSALE_COMPACT_AMOUNT(String sALECOMPACTAMOUNT) {
		SALE_COMPACT_AMOUNT = sALECOMPACTAMOUNT;
	}

	public String getREQ_ID() {
		return REQ_ID;
	}

	public void setREQ_ID(String rEQID) {
		REQ_ID = rEQID;
	}

	public String getREQ_NAME() {
		return REQ_NAME;
	}

	public void setREQ_NAME(String rEQNAME) {
		REQ_NAME = rEQNAME;
	}

	public String getREQ_DATE() {
		return REQ_DATE;
	}

	public void setREQ_DATE(String rEQDATE) {
		REQ_DATE = rEQDATE;
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

	public String getUPDATOR() {
		return UPDATOR;
	}

	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}

	public String getUPD_NAME() {
		return UPD_NAME;
	}

	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}

	public String getUPD_TIME() {
		return UPD_TIME;
	}

	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}

	public String getAUDIT_ID() {
		return AUDIT_ID;
	}

	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}

	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}

	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}

	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}

	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
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

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	public String getATT_PATH() {
		return ATT_PATH;
	}

	public void setATT_PATH(String aTTPATH) {
		ATT_PATH = aTTPATH;
	}

	public String getATT_ID() {
		return ATT_ID;
	}

	public void setATT_ID(String aTTID) {
		ATT_ID = aTTID;
	}

	public String getCHANN_PERSON_CON() {
		return CHANN_PERSON_CON;
	}

	public void setCHANN_PERSON_CON(String chann_person_con) {
		CHANN_PERSON_CON = chann_person_con;
	}

	public String getCHANN_TEL() {
		return CHANN_TEL;
	}

	public void setCHANN_TEL(String chann_tel) {
		CHANN_TEL = chann_tel;
	}

	public String getCOUNT_NUM() {
		return COUNT_NUM;
	}

	public void setCOUNT_NUM(String count_num) {
		COUNT_NUM = count_num;
	}

	public String getQUARTER_RATE() {
		return QUARTER_RATE;
	}

	public void setQUARTER_RATE(String quarter_rate) {
		QUARTER_RATE = quarter_rate;
	}

	public String getIS_VIOLATE_REMARK() {
		return IS_VIOLATE_REMARK;
	}

	public void setIS_VIOLATE_REMARK(String is_violate_remark) {
		IS_VIOLATE_REMARK = is_violate_remark;
	}

	public String getOLD_OA_ORDER_PIC() {
		return OLD_OA_ORDER_PIC;
	}

	public void setOLD_OA_ORDER_PIC(String oLDOAORDERPIC) {
		OLD_OA_ORDER_PIC = oLDOAORDERPIC;
	}

	public String getFROM_BILL_FLAG() {
		return FROM_BILL_FLAG;
	}

	public void setFROM_BILL_FLAG(String fROMBILLFLAG) {
		FROM_BILL_FLAG = fROMBILLFLAG;
	}

	public String getAMOUNT_DESC() {
		return AMOUNT_DESC;
	}

	public void setAMOUNT_DESC(String amount_desc) {
		AMOUNT_DESC = amount_desc;
	}

	public String getFROM_BILL_FLAGT() {
		return FROM_BILL_FLAGT;
	}

	public void setFROM_BILL_FLAGT(String from_bill_flagt) {
		FROM_BILL_FLAGT = from_bill_flagt;
	}

	public String getFORM_BILL_FLAG_T() {
		return FORM_BILL_FLAG_T;
	}

	public void setFORM_BILL_FLAG_T(String form_bill_flag_t) {
		FORM_BILL_FLAG_T = form_bill_flag_t;
	}
	
	

}
