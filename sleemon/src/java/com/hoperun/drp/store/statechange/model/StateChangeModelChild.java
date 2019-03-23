package com.hoperun.drp.store.statechange.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 形态转换
 * @author zhang_zhongbin
 *
 */
public class StateChangeModelChild extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 形态转换明细ID **/
	private String STATE_CHANGE_DTL_ID;
	/** 形态转换ID **/
	private String STATE_CHANGE_ID;
	/** 库房ID **/
	private String STORE_ID;
	/** 库房编号 **/
	private String STORE_NO;
	/** 库房名称 **/
	private String STORE_NAME;
	/** 转换前货品ID **/
	private String PAR_CHANGE_PRD_ID;
	/** 转换前货品编号 **/
	private String PAR_CHANGE_PRD_NO;
	/** 转换前货品名称 **/
	private String PAR_CHANGE_PRD_NAME;
	/** 转换前货品特殊工艺 **/
	private String PAR_CHANGE_SPCL_TECH_ID;
	/** 转换后前货品ID **/
	private String CHANGED_PRD_ID;
	/** 转换后货品编号 **/
	private String CHANGED_PRD_NO;
	/** 转换后货品名称 **/
	private String CHANGED_PRD_NAME;
	/** 转换后货品特殊工艺 **/
	private String CHANGED_SPCL_TECH_ID;
	/** 转换后货品特殊工艺 **/
	private String CHANGE_NUM;
	
	private String DEL_FLAG;
	
	private String SAFE_NUM;

	public String getSTATE_CHANGE_DTL_ID() {
		return STATE_CHANGE_DTL_ID;
	}

	public void setSTATE_CHANGE_DTL_ID(String sTATECHANGEDTLID) {
		STATE_CHANGE_DTL_ID = sTATECHANGEDTLID;
	}

	public String getSTATE_CHANGE_ID() {
		return STATE_CHANGE_ID;
	}

	public void setSTATE_CHANGE_ID(String sTATECHANGEID) {
		STATE_CHANGE_ID = sTATECHANGEID;
	}

	public String getSTORE_ID() {
		return STORE_ID;
	}

	public void setSTORE_ID(String sTOREID) {
		STORE_ID = sTOREID;
	}

	public String getSTORE_NO() {
		return STORE_NO;
	}

	public void setSTORE_NO(String sTORENO) {
		STORE_NO = sTORENO;
	}

	public String getSTORE_NAME() {
		return STORE_NAME;
	}

	public void setSTORE_NAME(String sTORENAME) {
		STORE_NAME = sTORENAME;
	}

	public String getPAR_CHANGE_PRD_ID() {
		return PAR_CHANGE_PRD_ID;
	}

	public void setPAR_CHANGE_PRD_ID(String pARCHANGEPRDID) {
		PAR_CHANGE_PRD_ID = pARCHANGEPRDID;
	}

	public String getPAR_CHANGE_PRD_NO() {
		return PAR_CHANGE_PRD_NO;
	}

	public void setPAR_CHANGE_PRD_NO(String pARCHANGEPRDNO) {
		PAR_CHANGE_PRD_NO = pARCHANGEPRDNO;
	}

	public String getPAR_CHANGE_PRD_NAME() {
		return PAR_CHANGE_PRD_NAME;
	}

	public void setPAR_CHANGE_PRD_NAME(String pARCHANGEPRDNAME) {
		PAR_CHANGE_PRD_NAME = pARCHANGEPRDNAME;
	}

	public String getPAR_CHANGE_SPCL_TECH_ID() {
		return PAR_CHANGE_SPCL_TECH_ID;
	}

	public void setPAR_CHANGE_SPCL_TECH_ID(String pARCHANGESPCLTECHID) {
		PAR_CHANGE_SPCL_TECH_ID = pARCHANGESPCLTECHID;
	}

	public String getCHANGED_PRD_ID() {
		return CHANGED_PRD_ID;
	}

	public void setCHANGED_PRD_ID(String cHANGEDPRDID) {
		CHANGED_PRD_ID = cHANGEDPRDID;
	}

	public String getCHANGED_PRD_NO() {
		return CHANGED_PRD_NO;
	}

	public void setCHANGED_PRD_NO(String cHANGEDPRDNO) {
		CHANGED_PRD_NO = cHANGEDPRDNO;
	}

	public String getCHANGED_PRD_NAME() {
		return CHANGED_PRD_NAME;
	}

	public void setCHANGED_PRD_NAME(String cHANGEDPRDNAME) {
		CHANGED_PRD_NAME = cHANGEDPRDNAME;
	}

	public String getCHANGED_SPCL_TECH_ID() {
		return CHANGED_SPCL_TECH_ID;
	}

	public void setCHANGED_SPCL_TECH_ID(String cHANGEDSPCLTECHID) {
		CHANGED_SPCL_TECH_ID = cHANGEDSPCLTECHID;
	}

	public String getCHANGE_NUM() {
		return CHANGE_NUM;
	}

	public void setCHANGE_NUM(String cHANGENUM) {
		CHANGE_NUM = cHANGENUM;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	public String getSAFE_NUM() {
		return SAFE_NUM;
	}

	public void setSAFE_NUM(String sAFENUM) {
		SAFE_NUM = sAFENUM;
	}
	
	
	

}
