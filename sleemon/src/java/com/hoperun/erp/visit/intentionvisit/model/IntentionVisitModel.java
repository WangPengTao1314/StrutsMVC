package com.hoperun.erp.visit.intentionvisit.model;


public class IntentionVisitModel {

	private static final long serialVersionUID = 1L;
	/** 意向客户拜访ID **/
	private String INTE_CHANN_ID;
	/** 意向客户拜访NO **/
	private String INTE_CHANN_NO;
	/** 申请人ID **/
	private String APPLY_PERSON_ID;
	/** 申请人 **/
	private String APPLY_PERSON_NAME;
	/** 申请部门 **/
	private String APPLY_DEP;
	/** 申请日期 **/
	private String APPLY_DATE;
	/** 城市 **/
	private String CITY;
	/** 城市类型 **/
	private String CITY_TYPE;
	/** 区号 **/
	private String AREA_CODE;
	/** 城市级别 **/
	private String CITY_LEVEL;
	/** 意向加盟商开户名称 **/
	private String INTE_CHANN_NAME;
	/** 意向加盟商姓名 **/
	private String INTE_CUSTOMER_NAME;
	/** 电话 **/
	private String TEL;
	/** 性别 **/
	private String SEX;
	/** 年龄 **/
	private String AGE;
	/** 学历 **/
	private String EDUCATION;
	private String ADDRESS;
	/** 现有卖场名称 **/
	private String EXIST_STORE_NAME;
	/** 现有卖场地址 **/
	private String EXIST_STORE_ADDR;
	/** 现有卖场面积 **/
	private String EXIST_STORE_AREA;
	/** 档次排名 **/
	private String EXIST_STORE_RANGE;
	/** 进驻主竞品 **/
	private String EXIST_STORE_COMPETITION;
	/** 近期新开卖场名称 **/
	private String NEW_STORE_NAME;
	/** 近期新开卖场地址 **/
	private String NEW_STORE_ADDR;
	/** 近期新开卖场面积 **/
	private String NEW_STORE_AREA;
	/** 近期新开卖场档次排名 **/
	private String NEW_STORE_RANGE;
	private String NEW_STORE_DATE;
	/** 发货点ID **/
	private String SHIP_POINT_ID;
	/** 发货点名称 **/
	private String SHIP_POINT_NAME;
	/** 是否有区域服务中心 **/
	private String AREA_SER_FLG;
	/** 区域服务中心ID **/
	private String AREA_SER_ID;
	/** 区域服务中心编号 **/
	private String AREA_SER_NO;
	/** 区域服务中心名称 **/
	private String AREA_SER_NAME;
	/** 相关文档 **/
	private String DOCUMENTS;
	/** 拜访形式 **/
	private String VISIT_TYPE;
	/** 拜访说明 **/
	private String VISIT_REMARK;
	/** 意向经营品牌 **/
	private String BUSS_SCOPE;
	/** 意向进驻卖场 **/
	private String STORE_NAME;
	/** 计划开店版本 **/
	private String INTE_STORE_VSION;
	/** 计划开店面积 **/
	private String INTE_STORE_AREA;
	/** 计划开店时间 **/
	private String INTE_STORE_DATE;
	/** 是否签署合同 **/
	private String IS_CONTRACT;
	/** 是否缴纳意向金 **/
	private String IS_PAY_INTEAMOUNT;
	/** 是否已首次发货 **/
	private String IS_FIRST_SENTPDT;
	/** 加盟商异议 **/
	private String CHANN_REMARK;
	/** 解决方案 **/
	private String SOLUTION;
	/** 竞品信息 **/
	private String COMPETITION_INFO;
	/** 支持需求 **/
	private String SUPPORT_DEMAND;
	/** 现状及优势 **/
	private String ADVANTAGES;
	
	private String DOC_PATH;

	/** 状态 **/
	private String STATE;
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
	/** 删除标记 **/
	private String DEL_FLAG;
	
	public String getINTE_CHANN_ID() {
		return INTE_CHANN_ID;
	}
	public void setINTE_CHANN_ID(String iNTECHANNID) {
		INTE_CHANN_ID = iNTECHANNID;
	}
	public String getINTE_CHANN_NO() {
		return INTE_CHANN_NO;
	}
	public void setINTE_CHANN_NO(String iNTECHANNNO) {
		INTE_CHANN_NO = iNTECHANNNO;
	}
	public String getAPPLY_PERSON_ID() {
		return APPLY_PERSON_ID;
	}
	public void setAPPLY_PERSON_ID(String aPPLYPERSONID) {
		APPLY_PERSON_ID = aPPLYPERSONID;
	}
	public String getAPPLY_PERSON_NAME() {
		return APPLY_PERSON_NAME;
	}
	public void setAPPLY_PERSON_NAME(String aPPLYPERSONNAME) {
		APPLY_PERSON_NAME = aPPLYPERSONNAME;
	}
	public String getAPPLY_DEP() {
		return APPLY_DEP;
	}
	public void setAPPLY_DEP(String aPPLYDEP) {
		APPLY_DEP = aPPLYDEP;
	}
	public String getAPPLY_DATE() {
		return APPLY_DATE;
	}
	public void setAPPLY_DATE(String aPPLYDATE) {
		APPLY_DATE = aPPLYDATE;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getCITY_TYPE() {
		return CITY_TYPE;
	}
	public void setCITY_TYPE(String cITYTYPE) {
		CITY_TYPE = cITYTYPE;
	}
	public String getAREA_CODE() {
		return AREA_CODE;
	}
	public void setAREA_CODE(String aREACODE) {
		AREA_CODE = aREACODE;
	}
	public String getCITY_LEVEL() {
		return CITY_LEVEL;
	}
	public void setCITY_LEVEL(String cITYLEVEL) {
		CITY_LEVEL = cITYLEVEL;
	}
	public String getINTE_CHANN_NAME() {
		return INTE_CHANN_NAME;
	}
	public void setINTE_CHANN_NAME(String iNTECHANNNAME) {
		INTE_CHANN_NAME = iNTECHANNNAME;
	}
	public String getINTE_CUSTOMER_NAME() {
		return INTE_CUSTOMER_NAME;
	}
	public void setINTE_CUSTOMER_NAME(String iNTECUSTOMERNAME) {
		INTE_CUSTOMER_NAME = iNTECUSTOMERNAME;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getSEX() {
		return SEX;
	}
	public void setSEX(String sEX) {
		SEX = sEX;
	}
	public String getAGE() {
		return AGE;
	}
	public void setAGE(String aGE) {
		AGE = aGE;
	}
	public String getEDUCATION() {
		return EDUCATION;
	}
	public void setEDUCATION(String eDUCATION) {
		EDUCATION = eDUCATION;
	}
	public String getEXIST_STORE_NAME() {
		return EXIST_STORE_NAME;
	}
	public void setEXIST_STORE_NAME(String eXISTSTORENAME) {
		EXIST_STORE_NAME = eXISTSTORENAME;
	}
	public String getEXIST_STORE_ADDR() {
		return EXIST_STORE_ADDR;
	}
	public void setEXIST_STORE_ADDR(String eXISTSTOREADDR) {
		EXIST_STORE_ADDR = eXISTSTOREADDR;
	}
	public String getEXIST_STORE_AREA() {
		return EXIST_STORE_AREA;
	}
	public void setEXIST_STORE_AREA(String eXISTSTOREAREA) {
		EXIST_STORE_AREA = eXISTSTOREAREA;
	}
	public String getEXIST_STORE_RANGE() {
		return EXIST_STORE_RANGE;
	}
	public void setEXIST_STORE_RANGE(String eXISTSTORERANGE) {
		EXIST_STORE_RANGE = eXISTSTORERANGE;
	}
	public String getEXIST_STORE_COMPETITION() {
		return EXIST_STORE_COMPETITION;
	}
	public void setEXIST_STORE_COMPETITION(String eXISTSTORECOMPETITION) {
		EXIST_STORE_COMPETITION = eXISTSTORECOMPETITION;
	}
	public String getNEW_STORE_NAME() {
		return NEW_STORE_NAME;
	}
	public void setNEW_STORE_NAME(String nEWSTORENAME) {
		NEW_STORE_NAME = nEWSTORENAME;
	}
	public String getNEW_STORE_ADDR() {
		return NEW_STORE_ADDR;
	}
	public void setNEW_STORE_ADDR(String nEWSTOREADDR) {
		NEW_STORE_ADDR = nEWSTOREADDR;
	}
	public String getNEW_STORE_AREA() {
		return NEW_STORE_AREA;
	}
	public void setNEW_STORE_AREA(String nEWSTOREAREA) {
		NEW_STORE_AREA = nEWSTOREAREA;
	}
	public String getNEW_STORE_RANGE() {
		return NEW_STORE_RANGE;
	}
	public void setNEW_STORE_RANGE(String nEWSTORERANGE) {
		NEW_STORE_RANGE = nEWSTORERANGE;
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
	public String getAREA_SER_FLG() {
		return AREA_SER_FLG;
	}
	public void setAREA_SER_FLG(String aREASERFLG) {
		AREA_SER_FLG = aREASERFLG;
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
	public String getDOCUMENTS() {
		return DOCUMENTS;
	}
	public void setDOCUMENTS(String dOCUMENTS) {
		DOCUMENTS = dOCUMENTS;
	}
	public String getVISIT_TYPE() {
		return VISIT_TYPE;
	}
	public void setVISIT_TYPE(String vISITTYPE) {
		VISIT_TYPE = vISITTYPE;
	}
	public String getVISIT_REMARK() {
		return VISIT_REMARK;
	}
	public void setVISIT_REMARK(String vISITREMARK) {
		VISIT_REMARK = vISITREMARK;
	}
	public String getBUSS_SCOPE() {
		return BUSS_SCOPE;
	}
	public void setBUSS_SCOPE(String bUSSSCOPE) {
		BUSS_SCOPE = bUSSSCOPE;
	}
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	public void setSTORE_NAME(String sTORENAME) {
		STORE_NAME = sTORENAME;
	}
	public String getINTE_STORE_VSION() {
		return INTE_STORE_VSION;
	}
	public void setINTE_STORE_VSION(String iNTESTOREVSION) {
		INTE_STORE_VSION = iNTESTOREVSION;
	}
	public String getINTE_STORE_AREA() {
		return INTE_STORE_AREA;
	}
	public void setINTE_STORE_AREA(String iNTESTOREAREA) {
		INTE_STORE_AREA = iNTESTOREAREA;
	}
	public String getINTE_STORE_DATE() {
		return INTE_STORE_DATE;
	}
	public void setINTE_STORE_DATE(String iNTESTOREDATE) {
		INTE_STORE_DATE = iNTESTOREDATE;
	}
	public String getIS_CONTRACT() {
		return IS_CONTRACT;
	}
	public void setIS_CONTRACT(String iSCONTRACT) {
		IS_CONTRACT = iSCONTRACT;
	}
	public String getIS_PAY_INTEAMOUNT() {
		return IS_PAY_INTEAMOUNT;
	}
	public void setIS_PAY_INTEAMOUNT(String iSPAYINTEAMOUNT) {
		IS_PAY_INTEAMOUNT = iSPAYINTEAMOUNT;
	}
	public String getIS_FIRST_SENTPDT() {
		return IS_FIRST_SENTPDT;
	}
	public void setIS_FIRST_SENTPDT(String iSFIRSTSENTPDT) {
		IS_FIRST_SENTPDT = iSFIRSTSENTPDT;
	}
	public String getCHANN_REMARK() {
		return CHANN_REMARK;
	}
	public void setCHANN_REMARK(String cHANNREMARK) {
		CHANN_REMARK = cHANNREMARK;
	}
	public String getSOLUTION() {
		return SOLUTION;
	}
	public void setSOLUTION(String sOLUTION) {
		SOLUTION = sOLUTION;
	}
	public String getCOMPETITION_INFO() {
		return COMPETITION_INFO;
	}
	public void setCOMPETITION_INFO(String cOMPETITIONINFO) {
		COMPETITION_INFO = cOMPETITIONINFO;
	}
	public String getSUPPORT_DEMAND() {
		return SUPPORT_DEMAND;
	}
	public void setSUPPORT_DEMAND(String sUPPORTDEMAND) {
		SUPPORT_DEMAND = sUPPORTDEMAND;
	}
	public String getADVANTAGES() {
		return ADVANTAGES;
	}
	public void setADVANTAGES(String aDVANTAGES) {
		ADVANTAGES = aDVANTAGES;
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
	public String getDOC_PATH() {
		return DOC_PATH;
	}
	public void setDOC_PATH(String dOCPATH) {
		DOC_PATH = dOCPATH;
	}
	public String getNEW_STORE_DATE() {
		return NEW_STORE_DATE;
	}
	public void setNEW_STORE_DATE(String nEWSTOREDATE) {
		NEW_STORE_DATE = nEWSTOREDATE;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	
	
	

}
