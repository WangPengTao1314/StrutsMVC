package com.hoperun.erp.oamg.storeclose.model;

import com.hoperun.commons.model.BaseModel;

public class StorecloseModel extends BaseModel {
	
	private static final long serialVersionUID = -3671598104654899486L;
	
	private String SPCL_STORE_CC_REQ_ID;  // 专卖店撤店及申请单ID   
	private String PCL_STORE_CC_REQ_NO;  //专卖店撤店及申请单编号     
	private String CHANN_ID ;  //渠道ID 
	private String CHANN_NO ;  //渠道编号 
	private String CHANN_NAME ;  //渠道名称 
	private String TERM_ID ;  //终端信息ID 
	private String TERM_NO ;  //终端编号 
	private String TERM_NAME ;  //终端名称 
	private String TERM_TYPE ;  //终端类型 
	private String RNVTN_REQ_ID ;  //装修申请人ID 
	private String RNVTN_REQ_NAME;  //装修申请人姓名 
	private String CHANN_PERSON_CON;  //渠道联系人 
	private String CHANN_TEL;  //渠道电话 
	private String AREA_ID ;  //区域ID 
	private String AREA_NO ;  //区域编号 
	private String AREA_NAME ;  //区域名称 	
	private String AREA_MANAGE_ID ;  //区域经理ID 
	private String AREA_MANAGE_NAME ;  //区域经理名称 
	private String AREA_MANAGE_TEL;  //区域经理电话  
	private String BEG_SBUSS_DATE;  //开业时间 
	private String PERSON_CON ;  //联系人 
	private String TEL;  //电话 
	private String MOBILE;  //手机 
	private String TAX;  //传真
	private String SALE_STORE_ID;  //卖场ID 
	private String SALE_STORE_NAME;  //卖场名称 
	private String LOCAL_TYPE; // 商场位置类别	
	private String ZONE_ID;  //行政区划ID 
	private String ZONE_ADDR;  //行政区划地址 
	private String BUSS_SCOPE;  //经营范围(经营品牌)
	private String CHARGE_AREA;  //负责区域
	private String BUSS_AREA; //营业面积 
	private String SPCL_STORE_CANCEL_DATE;  //专卖店撤店日期
	private String STORE_CANCEL_TYPE;  //撤店类型
	private String REIT_AMOUNT_PS;  //报销金额(元/平米) 
	private String ORG_REIT_AMOUNT;  //原报销金额 
	private String REITED_NUM;  //已报销次数
	private String REITED_AMOUNT; //已报销金额 
	private String ADDRESS;  //详细地址 
	private String REQ_RSON;  //申报理由 
	private String STATE;  //状态 	
	private String REMARK;  //备注
		
	private String PIC_PATH; //图片路径
	
	
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pICPATH) {
		PIC_PATH = pICPATH;
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
	public String getTERM_TYPE() {
		return TERM_TYPE;
	}
	public void setTERM_TYPE(String tERMTYPE) {
		TERM_TYPE = tERMTYPE;
	}
	public String getRNVTN_REQ_ID() {
		return RNVTN_REQ_ID;
	}
	public void setRNVTN_REQ_ID(String rNVTNREQID) {
		RNVTN_REQ_ID = rNVTNREQID;
	}
	public String getRNVTN_REQ_NAME() {
		return RNVTN_REQ_NAME;
	}
	public void setRNVTN_REQ_NAME(String rNVTNREQNAME) {
		RNVTN_REQ_NAME = rNVTNREQNAME;
	}
	public String getCHANN_PERSON_CON() {
		return CHANN_PERSON_CON;
	}
	public void setCHANN_PERSON_CON(String cHANNPERSONCON) {
		CHANN_PERSON_CON = cHANNPERSONCON;
	}
	public String getCHANN_TEL() {
		return CHANN_TEL;
	}
	public void setCHANN_TEL(String cHANNTEL) {
		CHANN_TEL = cHANNTEL;
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
	public String getBEG_SBUSS_DATE() {
		return BEG_SBUSS_DATE;
	}
	public void setBEG_SBUSS_DATE(String bEGSBUSSDATE) {
		BEG_SBUSS_DATE = bEGSBUSSDATE;
	}
	public String getPERSON_CON() {
		return PERSON_CON;
	}
	public void setPERSON_CON(String pERSONCON) {
		PERSON_CON = pERSONCON;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getMOBILE() {
		return MOBILE;
	}
	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}
	public String getSALE_STORE_ID() {
		return SALE_STORE_ID;
	}
	public void setSALE_STORE_ID(String sALESTOREID) {
		SALE_STORE_ID = sALESTOREID;
	}
	public String getSALE_STORE_NAME() {
		return SALE_STORE_NAME;
	}
	public void setSALE_STORE_NAME(String sALESTORENAME) {
		SALE_STORE_NAME = sALESTORENAME;
	}
	public String getZONE_ID() {
		return ZONE_ID;
	}
	public void setZONE_ID(String zONEID) {
		ZONE_ID = zONEID;
	}
	public String getZONE_ADDR() {
		return ZONE_ADDR;
	}
	public void setZONE_ADDR(String zONEADDR) {
		ZONE_ADDR = zONEADDR;
	}
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}
	public void setBUSS_SCOPE(String bUSSSCOPE) {
		BUSS_SCOPE = bUSSSCOPE;
	}
		
	public String getREIT_AMOUNT_PS() {
		return REIT_AMOUNT_PS;
	}
	public void setREIT_AMOUNT_PS(String rEITAMOUNTPS) {
		REIT_AMOUNT_PS = rEITAMOUNTPS;
	}
	public String getORG_REIT_AMOUNT() {
		return ORG_REIT_AMOUNT;
	}
	public void setORG_REIT_AMOUNT(String oRGREITAMOUNT) {
		ORG_REIT_AMOUNT = oRGREITAMOUNT;
	}
	public String getREITED_AMOUNT() {
		return REITED_AMOUNT;
	}
	public void setREITED_AMOUNT(String rEITEDAMOUNT) {
		REITED_AMOUNT = rEITEDAMOUNT;
	}	
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREQ_RSON() {
		return REQ_RSON;
	}
	public void setREQ_RSON(String rEQRSON) {
		REQ_RSON = rEQRSON;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	public String getBUSS_AREA() {
		return BUSS_AREA;
	}
	public void setBUSS_AREA(String bUSSAREA) {
		BUSS_AREA = bUSSAREA;
	}
	
	public String getSPCL_STORE_CC_REQ_ID() {
		return SPCL_STORE_CC_REQ_ID;
	}
	public void setSPCL_STORE_CC_REQ_ID(String sPCLSTORECCREQID) {
		SPCL_STORE_CC_REQ_ID = sPCLSTORECCREQID;
	}
	public String getPCL_STORE_CC_REQ_NO() {
		return PCL_STORE_CC_REQ_NO;
	}
	public void setPCL_STORE_CC_REQ_NO(String pCLSTORECCREQNO) {
		PCL_STORE_CC_REQ_NO = pCLSTORECCREQNO;
	}
	public String getTAX() {
		return TAX;
	}
	public void setTAX(String tAX) {
		TAX = tAX;
	}
	public String getCHARGE_AREA() {
		return CHARGE_AREA;
	}
	public void setCHARGE_AREA(String cHARGEAREA) {
		CHARGE_AREA = cHARGEAREA;
	}
	public String getSPCL_STORE_CANCEL_DATE() {
		return SPCL_STORE_CANCEL_DATE;
	}
	public void setSPCL_STORE_CANCEL_DATE(String sPCLSTORECANCELDATE) {
		SPCL_STORE_CANCEL_DATE = sPCLSTORECANCELDATE;
	}
	public String getSTORE_CANCEL_TYPE() {
		return STORE_CANCEL_TYPE;
	}
	public void setSTORE_CANCEL_TYPE(String sTORECANCELTYPE) {
		STORE_CANCEL_TYPE = sTORECANCELTYPE;
	}
	public String getREITED_NUM() {
		return REITED_NUM;
	}
	public void setREITED_NUM(String rEITEDNUM) {
		REITED_NUM = rEITEDNUM;
	}
	public String getLOCAL_TYPE() {
		return LOCAL_TYPE;
	}
	public void setLOCAL_TYPE(String lOCALTYPE) {
		LOCAL_TYPE = lOCALTYPE;
	}

}
