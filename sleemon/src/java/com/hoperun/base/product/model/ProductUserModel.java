/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProductModel.java
 */

package com.hoperun.base.product.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductModel.
 * 
 * @module 系统管理
 * @func 货品信息
 * @version 1.1
 * @author 刘曰刚
 */
public class ProductUserModel extends BaseModel {

	private String USER_CHARG_PRD_ID;
	private String XTYHID;
	private String PRD_ID;
	private String PRD_NO;
	private String PRD_NAME;
	/**
	 * @return the uSER_CHARG_PRD_ID
	 */
	public String getUSER_CHARG_PRD_ID() {
		return USER_CHARG_PRD_ID;
	}
	/**
	 * @param uSERCHARGPRDID the uSER_CHARG_PRD_ID to set
	 */
	public void setUSER_CHARG_PRD_ID(String uSERCHARGPRDID) {
		USER_CHARG_PRD_ID = uSERCHARGPRDID;
	}
	/**
	 * @return the xTYHID
	 */
	public String getXTYHID() {
		return XTYHID;
	}
	/**
	 * @param xTYHID the xTYHID to set
	 */
	public void setXTYHID(String xTYHID) {
		XTYHID = xTYHID;
	}
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
	
}
