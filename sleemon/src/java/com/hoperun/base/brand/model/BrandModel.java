/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：BrandModel.java
 */
package com.hoperun.base.brand.model;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandModel.
 * 
 * @module 系统管理
 * @func 品牌信息
 * @version 1.1
 * @author 郭利伟
 */
public class BrandModel {

    /** 
     * 品牌主键
     */
    private String BRAND_ID;

    /**
     * 品牌
     */
    private String BRAND; 
    
    /**
     * 英文名称
     */
    private String BRAND_EN;
    
	/**
     * 状态
     */
    private String STATE; 
   
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
    private String DEL_FLAG;
   
    public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
    
    public String getBRAND_EN() {
		return BRAND_EN;
	}

	public void setBRAND_EN(String bRANDEN) {
		BRAND_EN = bRANDEN;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}

	/**
     * 备注
     */
    private String REMARK;
    
	public String getBRAND_ID() {
		return BRAND_ID;
	}

	public void setBRAND_ID(String bRANDID) {
		BRAND_ID = bRANDID;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
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

}
