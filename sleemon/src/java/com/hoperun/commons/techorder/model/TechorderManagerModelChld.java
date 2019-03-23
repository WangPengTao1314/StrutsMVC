package com.hoperun.commons.techorder.model;
/**
 * 订单特殊工艺明细
 * @author liu_yuegang
 *
 */
public class TechorderManagerModelChld {
	/**
	 * 订单特殊工艺明细ID
	 */
	private String SPCL_TECH_DTL_ID;
	/**
	 * 订单特殊工艺ID
	 */
	private String SPCL_TECH_ID;
	/**
	 * 货品特殊工艺维护ID
	 */
	private String PRD_SPCL_TECH_ID;
	/**
	 * 特殊工艺维护名称
	 */
	private String SPCL_TECH_NAME;
	/**
	 * 特殊工艺类型
	 */
	private String SPCL_TECH_TYPE;
	/**
	 * 当前值
	 */
	private String CURRT_VAL;
	/**
	 * 调整范围从
	 */
	private String CURRT_VAL_TURN_BEG;
	/**
	 * 调整范围到
	 */
	private String CURRT_VAL_TURN_END;
	/**
	 * 调价类型
	 */
	private String PRICE_TURN_TYPE;
	/**
	 * 调价值
	 */
	private String PRICE_TURN_VAL;
	/**
	 * 可调整值
	 */
	private String TUENED_VALS;
	/**
	 * 调整后值
	 */
	private String CUST_TURN_VAL;
	/**
	 * 备注
	 */
	private String REMARK;
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;
	/**
	 * @return the sPCL_TECH_DTL_ID
	 */
	public String getSPCL_TECH_DTL_ID() {
		return SPCL_TECH_DTL_ID;
	}
	/**
	 * @param sPCLTECHDTLID the sPCL_TECH_DTL_ID to set
	 */
	public void setSPCL_TECH_DTL_ID(String sPCLTECHDTLID) {
		SPCL_TECH_DTL_ID = sPCLTECHDTLID;
	}
	/**
	 * @return the sPCL_TECH_ID
	 */
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	/**
	 * @param sPCLTECHID the sPCL_TECH_ID to set
	 */
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}
	/**
	 * @return the pRD_SPCL_TECH_ID
	 */
	public String getPRD_SPCL_TECH_ID() {
		return PRD_SPCL_TECH_ID;
	}
	/**
	 * @param pRDSPCLTECHID the pRD_SPCL_TECH_ID to set
	 */
	public void setPRD_SPCL_TECH_ID(String pRDSPCLTECHID) {
		PRD_SPCL_TECH_ID = pRDSPCLTECHID;
	}
	/**
	 * @return the sPCL_TECH_NAME
	 */
	public String getSPCL_TECH_NAME() {
		return SPCL_TECH_NAME;
	}
	/**
	 * @param sPCLTECHNAME the sPCL_TECH_NAME to set
	 */
	public void setSPCL_TECH_NAME(String sPCLTECHNAME) {
		SPCL_TECH_NAME = sPCLTECHNAME;
	}
	/**
	 * @return the sPCL_TECH_TYPE
	 */
	public String getSPCL_TECH_TYPE() {
		return SPCL_TECH_TYPE;
	}
	/**
	 * @param sPCLTECHTYPE the sPCL_TECH_TYPE to set
	 */
	public void setSPCL_TECH_TYPE(String sPCLTECHTYPE) {
		SPCL_TECH_TYPE = sPCLTECHTYPE;
	}
	/**
	 * @return the cURRT_VAL
	 */
	public String getCURRT_VAL() {
		return CURRT_VAL;
	}
	/**
	 * @param cURRTVAL the cURRT_VAL to set
	 */
	public void setCURRT_VAL(String cURRTVAL) {
		CURRT_VAL = cURRTVAL;
	}
	/**
	 * @return the cURRT_VAL_TURN_BEG
	 */
	public String getCURRT_VAL_TURN_BEG() {
		return CURRT_VAL_TURN_BEG;
	}
	/**
	 * @param cURRTVALTURNBEG the cURRT_VAL_TURN_BEG to set
	 */
	public void setCURRT_VAL_TURN_BEG(String cURRTVALTURNBEG) {
		CURRT_VAL_TURN_BEG = cURRTVALTURNBEG;
	}
	/**
	 * @return the cURRT_VAL_TURN_END
	 */
	public String getCURRT_VAL_TURN_END() {
		return CURRT_VAL_TURN_END;
	}
	/**
	 * @param cURRTVALTURNEND the cURRT_VAL_TURN_END to set
	 */
	public void setCURRT_VAL_TURN_END(String cURRTVALTURNEND) {
		CURRT_VAL_TURN_END = cURRTVALTURNEND;
	}
	/**
	 * @return the pRICE_TURN_TYPE
	 */
	public String getPRICE_TURN_TYPE() {
		return PRICE_TURN_TYPE;
	}
	/**
	 * @param pRICETURNTYPE the pRICE_TURN_TYPE to set
	 */
	public void setPRICE_TURN_TYPE(String pRICETURNTYPE) {
		PRICE_TURN_TYPE = pRICETURNTYPE;
	}
	/**
	 * @return the pRICE_TURN_VAL
	 */
	public String getPRICE_TURN_VAL() {
		return PRICE_TURN_VAL;
	}
	/**
	 * @param pRICETURNVAL the pRICE_TURN_VAL to set
	 */
	public void setPRICE_TURN_VAL(String pRICETURNVAL) {
		PRICE_TURN_VAL = pRICETURNVAL;
	}
	/**
	 * @return the tUENED_VALS
	 */
	public String getTUENED_VALS() {
		return TUENED_VALS;
	}
	/**
	 * @param tUENEDVALS the tUENED_VALS to set
	 */
	public void setTUENED_VALS(String tUENEDVALS) {
		TUENED_VALS = tUENEDVALS;
	}
	/**
	 * @return the cUST_TURN_VAL
	 */
	public String getCUST_TURN_VAL() {
		return CUST_TURN_VAL;
	}
	/**
	 * @param cUSTTURNVAL the cUST_TURN_VAL to set
	 */
	public void setCUST_TURN_VAL(String cUSTTURNVAL) {
		CUST_TURN_VAL = cUSTTURNVAL;
	}
	/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}
	/**
	 * @param rEMARK the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	/**
	 * @return the dEL_FLAG
	 */
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
}
