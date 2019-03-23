/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProductModel.java
 */

package com.hoperun.base.pdtmenu.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductModel.
 * 
 * @module 系统管理
 * @func 货品分类
 * @version 1.1
 * @author 刘曰刚
 */
public class PdtmenuModel extends BaseModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4943417535815136771L;
	
	/**
	 * 货品ID
	 */
	private String PRD_ID;
	
	/**
	 * 货品编号
	 */
	private String PRD_NO;
	
	/**
	 * 货品名称
	 */
	private String PRD_NAME;
	
	/**
	 * 规格型号
	 */
	private String PRD_SPEC;

	/**
	 * 颜色
	 */
	private String PRD_COLOR;

	/**
	 * 品牌
	 */
	private String BRAND;

	/**
	 * 标准单位
	 */
	private String STD_UNIT;

	/**
	 * 默认计量单位
	 */
	private String MEAS_UNIT;

	/**
	 * 换算关系
	 */
	private double RATIO;

	/**
	 * 上级货品ID
	 */
	private String PAR_PRD_ID;

	/**
	 * 上级货品编号
	 */
	private String PAR_PRD_NO;

	/**
	 * 上级货品名称
	 */
	private String PAR_PRD_NAME;

	/**
	 * 货品类别
	 */
	private String PRD_TYPE;

	/**
	 * 体积
	 */
	private double VOLUME;

	/**
	 * 长度
	 */
	private double LENGTH;

	/**
	 * 宽度
	 */
	private double WIDTH;

	/**
	 * 高度
	 */
	private double HEIGHT;

	/**
	 * 出厂价
	 */
	private double FACT_PRICE;

	/**
	 * 最低零售价
	 */
	private double RET_PRICE_MIN;

	/**
	 * 开始生产日期
	 */
	private String BEG_DATE;

	/**
	 * 条码
	 */
	private String BAR_CODE;

	/**
	 * 老条码
	 */
	private String BAR_CODE_OLD;

	/**
	 * 财务对照码
	 */
	private String U_CMP_CODE;
	
	/**
	 * 货品套标记
	 */
	private Integer PRD_SUIT_FLAG;

	/**
	 * 图片路径
	 */
	private String PIC_PATH;

	/**
	 * 终结点标记
	 */
	private Integer FINAL_NODE_FLAG;
	
	/**
	 * 详细信息路径
	 */
	private String DTL_PATH;

	/**
	 * 状态
	 */
	private String STATE;

	/**
	 * 备注
	 */
	private String REMARK;

	/**
	 * 制单人ID
	 */
	private String CREATOR;

	/**
	 * 制单人名称
	 */
	private String CRE_NAME;

	/**
	 * 制单时间
	 */
	private String CRE_TIME;

	/**
	 * 更新人ID
	 */
	private String UPDATOR;

	/**
	 * 更新人名称
	 */
	private String UPD_NAME;

	/**
	 * 更新时间
	 */
	private String UPD_TIME;

	/**
	 * 制单部门ID
	 */
	private String DEPT_ID;

	/**
	 * 制单部门名称
	 */
	private String DEPT_NAME;

	/**
	 * 制单机构ID
	 */
	private String ORG_ID;

	/**
	 * 制单机构名称
	 */
	private String ORG_NAME;

	/**
	 * 删除标记
	 */
	private Integer DEL_FLAG;
	/**
	 * 材质
	 */
	private String PRD_MATL;
	/**
	 * 货品分类类型
	 */
	private String PRD_CLASS_TYPE;
	/**默认交期*/
	private String DEAFULT_ADVC_SEND_DATE;
	

	/**
	 * @return the pRD_ID
	 */
	public String getPRD_ID() {
		return PRD_ID;
	}

	/**
	 * @param pRDID the pRD_ID to set
	 */
	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}

	/**
	 * @return the pRD_NO
	 */
	public String getPRD_NO() {
		return PRD_NO;
	}

	/**
	 * @param pRDNO the pRD_NO to set
	 */
	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}

	/**
	 * @return the pRD_NAME
	 */
	public String getPRD_NAME() {
		return PRD_NAME;
	}

	/**
	 * @param pRDNAME the pRD_NAME to set
	 */
	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}

	/**
	 * @return the pRD_SPEC
	 */
	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	/**
	 * @param pRDSPEC the pRD_SPEC to set
	 */
	public void setPRD_SPEC(String pRDSPEC) {
		PRD_SPEC = pRDSPEC;
	}

	/**
	 * @return the pRD_COLOR
	 */
	public String getPRD_COLOR() {
		return PRD_COLOR;
	}

	/**
	 * @param pRDCOLOR the pRD_COLOR to set
	 */
	public void setPRD_COLOR(String pRDCOLOR) {
		PRD_COLOR = pRDCOLOR;
	}

	/**
	 * @return the bRAND
	 */
	public String getBRAND() {
		return BRAND;
	}

	/**
	 * @param bRAND the bRAND to set
	 */
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}

	/**
	 * @return the sTD_UNIT
	 */
	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	/**
	 * @param sTDUNIT the sTD_UNIT to set
	 */
	public void setSTD_UNIT(String sTDUNIT) {
		STD_UNIT = sTDUNIT;
	}

	/**
	 * @return the mEAS_UNIT
	 */
	public String getMEAS_UNIT() {
		return MEAS_UNIT;
	}

	/**
	 * @param mEASUNIT the mEAS_UNIT to set
	 */
	public void setMEAS_UNIT(String mEASUNIT) {
		MEAS_UNIT = mEASUNIT;
	}

	/**
	 * @return the rATIO
	 */
	public double getRATIO() {
		return RATIO;
	}

	/**
	 * @param rATIO the rATIO to set
	 */
	public void setRATIO(double rATIO) {
		RATIO = rATIO;
	}

	/**
	 * @return the pAR_PRD_ID
	 */
	public String getPAR_PRD_ID() {
		return PAR_PRD_ID;
	}

	/**
	 * @param pARPRDID the pAR_PRD_ID to set
	 */
	public void setPAR_PRD_ID(String pARPRDID) {
		PAR_PRD_ID = pARPRDID;
	}

	/**
	 * @return the pAR_PRD_NO
	 */
	public String getPAR_PRD_NO() {
		return PAR_PRD_NO;
	}

	/**
	 * @param pARPRDNO the pAR_PRD_NO to set
	 */
	public void setPAR_PRD_NO(String pARPRDNO) {
		PAR_PRD_NO = pARPRDNO;
	}

	/**
	 * @return the pAR_PRD_NAME
	 */
	public String getPAR_PRD_NAME() {
		return PAR_PRD_NAME;
	}

	/**
	 * @param pARPRDNAME the pAR_PRD_NAME to set
	 */
	public void setPAR_PRD_NAME(String pARPRDNAME) {
		PAR_PRD_NAME = pARPRDNAME;
	}

	/**
	 * @return the pRD_TYPE
	 */
	public String getPRD_TYPE() {
		return PRD_TYPE;
	}

	/**
	 * @param pRDTYPE the pRD_TYPE to set
	 */
	public void setPRD_TYPE(String pRDTYPE) {
		PRD_TYPE = pRDTYPE;
	}

	/**
	 * @return the vOLUME
	 */
	public double getVOLUME() {
		return VOLUME;
	}

	/**
	 * @param vOLUME the vOLUME to set
	 */
	public void setVOLUME(double vOLUME) {
		VOLUME = vOLUME;
	}

	/**
	 * @return the lENGTH
	 */
	public double getLENGTH() {
		return LENGTH;
	}

	/**
	 * @param lENGTH the lENGTH to set
	 */
	public void setLENGTH(double lENGTH) {
		LENGTH = lENGTH;
	}

	/**
	 * @return the wIDTH
	 */
	public double getWIDTH() {
		return WIDTH;
	}

	/**
	 * @param wIDTH the wIDTH to set
	 */
	public void setWIDTH(double wIDTH) {
		WIDTH = wIDTH;
	}

	/**
	 * @return the hEIGHT
	 */
	public double getHEIGHT() {
		return HEIGHT;
	}

	/**
	 * @param hEIGHT the hEIGHT to set
	 */
	public void setHEIGHT(double hEIGHT) {
		HEIGHT = hEIGHT;
	}

	/**
	 * @return the fACT_PRICE
	 */
	public double getFACT_PRICE() {
		return FACT_PRICE;
	}

	/**
	 * @param fACTPRICE the fACT_PRICE to set
	 */
	public void setFACT_PRICE(double fACTPRICE) {
		FACT_PRICE = fACTPRICE;
	}

	/**
	 * @return the rET_PRICE_MIN
	 */
	public double getRET_PRICE_MIN() {
		return RET_PRICE_MIN;
	}

	/**
	 * @param rETPRICEMIN the rET_PRICE_MIN to set
	 */
	public void setRET_PRICE_MIN(double rETPRICEMIN) {
		RET_PRICE_MIN = rETPRICEMIN;
	}

	/**
	 * @return the bEG_DATE
	 */
	public String getBEG_DATE() {
		return BEG_DATE;
	}

	/**
	 * @param bEGDATE the bEG_DATE to set
	 */
	public void setBEG_DATE(String bEGDATE) {
		BEG_DATE = bEGDATE;
	}

	/**
	 * @return the bAR_CODE
	 */
	public String getBAR_CODE() {
		return BAR_CODE;
	}

	/**
	 * @param bARCODE the bAR_CODE to set
	 */
	public void setBAR_CODE(String bARCODE) {
		BAR_CODE = bARCODE;
	}

	/**
	 * @return the bAR_CODE_OLD
	 */
	public String getBAR_CODE_OLD() {
		return BAR_CODE_OLD;
	}

	/**
	 * @param bARCODEOLD the bAR_CODE_OLD to set
	 */
	public void setBAR_CODE_OLD(String bARCODEOLD) {
		BAR_CODE_OLD = bARCODEOLD;
	}

	/**
	 * @return the u_CMP_CODE
	 */
	public String getU_CMP_CODE() {
		return U_CMP_CODE;
	}

	/**
	 * @param uCMPCODE the u_CMP_CODE to set
	 */
	public void setU_CMP_CODE(String uCMPCODE) {
		U_CMP_CODE = uCMPCODE;
	}

	/**
	 * @return the pRD_SUIT_FLAG
	 */
	public Integer getPRD_SUIT_FLAG() {
		return PRD_SUIT_FLAG;
	}

	/**
	 * @param pRDSUITFLAG the pRD_SUIT_FLAG to set
	 */
	public void setPRD_SUIT_FLAG(Integer pRDSUITFLAG) {
		PRD_SUIT_FLAG = pRDSUITFLAG;
	}

	/**
	 * @return the pIC_PATH
	 */
	public String getPIC_PATH() {
		return PIC_PATH;
	}

	/**
	 * @param pICPATH the pIC_PATH to set
	 */
	public void setPIC_PATH(String pICPATH) {
		PIC_PATH = pICPATH;
	}

	/**
	 * @return the fINAL_NODE_FLAG
	 */
	public Integer getFINAL_NODE_FLAG() {
		return FINAL_NODE_FLAG;
	}

	/**
	 * @param fINALNODEFLAG the fINAL_NODE_FLAG to set
	 */
	public void setFINAL_NODE_FLAG(Integer fINALNODEFLAG) {
		FINAL_NODE_FLAG = fINALNODEFLAG;
	}

	/**
	 * @return the sTATE
	 */
	public String getSTATE() {
		return STATE;
	}

	/**
	 * @param sTATE the sTATE to set
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
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
	 * @return the cREATOR
	 */
	public String getCREATOR() {
		return CREATOR;
	}

	/**
	 * @param cREATOR the cREATOR to set
	 */
	public void setCREATOR(String cREATOR) {
		CREATOR = cREATOR;
	}

	/**
	 * @return the cRE_NAME
	 */
	public String getCRE_NAME() {
		return CRE_NAME;
	}

	/**
	 * @param cRENAME the cRE_NAME to set
	 */
	public void setCRE_NAME(String cRENAME) {
		CRE_NAME = cRENAME;
	}

	/**
	 * @return the cRE_TIME
	 */
	public String getCRE_TIME() {
		return CRE_TIME;
	}

	/**
	 * @param cRETIME the cRE_TIME to set
	 */
	public void setCRE_TIME(String cRETIME) {
		CRE_TIME = cRETIME;
	}

	/**
	 * @return the uPDATOR
	 */
	public String getUPDATOR() {
		return UPDATOR;
	}

	/**
	 * @param uPDATOR the uPDATOR to set
	 */
	public void setUPDATOR(String uPDATOR) {
		UPDATOR = uPDATOR;
	}

	/**
	 * @return the uPD_NAME
	 */
	public String getUPD_NAME() {
		return UPD_NAME;
	}

	/**
	 * @param uPDNAME the uPD_NAME to set
	 */
	public void setUPD_NAME(String uPDNAME) {
		UPD_NAME = uPDNAME;
	}

	/**
	 * @return the uPD_TIME
	 */
	public String getUPD_TIME() {
		return UPD_TIME;
	}

	/**
	 * @param uPDTIME the uPD_TIME to set
	 */
	public void setUPD_TIME(String uPDTIME) {
		UPD_TIME = uPDTIME;
	}

	/**
	 * @return the dEPT_ID
	 */
	public String getDEPT_ID() {
		return DEPT_ID;
	}

	/**
	 * @param dEPTID the dEPT_ID to set
	 */
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}

	/**
	 * @return the dEPT_NAME
	 */
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	/**
	 * @param dEPTNAME the dEPT_NAME to set
	 */
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}

	/**
	 * @return the oRG_ID
	 */
	public String getORG_ID() {
		return ORG_ID;
	}

	/**
	 * @param oRGID the oRG_ID to set
	 */
	public void setORG_ID(String oRGID) {
		ORG_ID = oRGID;
	}

	/**
	 * @return the oRG_NAME
	 */
	public String getORG_NAME() {
		return ORG_NAME;
	}

	/**
	 * @param oRGNAME the oRG_NAME to set
	 */
	public void setORG_NAME(String oRGNAME) {
		ORG_NAME = oRGNAME;
	}

	/**
	 * @return the dEL_FLAG
	 */
	public Integer getDEL_FLAG() {
		return DEL_FLAG;
	}

	/**
	 * @param dELFLAG the dEL_FLAG to set
	 */
	public void setDEL_FLAG(Integer dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the dTL_PATH
	 */
	public String getDTL_PATH() {
		return DTL_PATH;
	}

	/**
	 * @param dTLPATH the dTL_PATH to set
	 */
	public void setDTL_PATH(String dTLPATH) {
		DTL_PATH = dTLPATH;
	}

	/**
	 * @return the pRD_MATL
	 */
	public String getPRD_MATL() {
		return PRD_MATL;
	}

	/**
	 * @param pRDMATL the pRD_MATL to set
	 */
	public void setPRD_MATL(String pRDMATL) {
		PRD_MATL = pRDMATL;
	}

	/**
	 * @return the pRD_CLASS_TYPE
	 */
	public String getPRD_CLASS_TYPE() {
		return PRD_CLASS_TYPE;
	}

	/**
	 * @param pRDCLASSTYPE the pRD_CLASS_TYPE to set
	 */
	public void setPRD_CLASS_TYPE(String pRDCLASSTYPE) {
		PRD_CLASS_TYPE = pRDCLASSTYPE;
	}

	public String getDEAFULT_ADVC_SEND_DATE() {
		return DEAFULT_ADVC_SEND_DATE;
	}

	public void setDEAFULT_ADVC_SEND_DATE(String dEAFULTADVCSENDDATE) {
		DEAFULT_ADVC_SEND_DATE = dEAFULTADVCSENDDATE;
	}

}
