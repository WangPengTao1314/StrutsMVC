/**
  *@module 系统管理
  *@fuc 数据字典bean
  *@version 1.1
  *@author 张羽
*/

package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;


// TODO: Auto-generated Javadoc
/**
 * 数据字典模型.
 * 
 * @author zhang_yu
 */
public class SJZDModel extends BaseModel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6681766681233801853L;
	
	/** The SJZDID. */
	private String SJZDID;
	
	/** The SJZDBH. */
	private String  SJZDBH;
	
	/** The SJZDMC. */
	private String SJZDMC;
	
	/** The STATE. */
	private String STATE	;
	
	/** The REMARK. */
	private String REMARK	;
	
	/** The order field. */
	private String orderField; 
	
	
	
	/**
	 * Gets the sJZDID.
	 * 
	 * @return the sJZDID
	 */
	public String getSJZDID() {
		return SJZDID;
	}
	
	/**
	 * Sets the sJZDID.
	 * 
	 * @param sJZDID the new sJZDID
	 */
	public void setSJZDID(String sJZDID) {
		SJZDID = sJZDID;
	}
	
	/**
	 * Gets the order field.
	 * 
	 * @return the order field
	 */
	public String getOrderField() {
		return orderField;
	}
	
	/**
	 * Sets the order field.
	 * 
	 * @param orderField the new order field
	 */
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	
	/**
	 * Gets the sJZDBH.
	 * 
	 * @return the sJZDBH
	 */
	public String getSJZDBH() {
		return SJZDBH;
	}
	
	/**
	 * Sets the sJZDBH.
	 * 
	 * @param sJZDBH the new sJZDBH
	 */
	public void setSJZDBH(String sJZDBH) {
		SJZDBH = sJZDBH;
	}
	
	/**
	 * Gets the sJZDMC.
	 * 
	 * @return the sJZDMC
	 */
	public String getSJZDMC() {
		return SJZDMC;
	}
	
	/**
	 * Sets the sJZDMC.
	 * 
	 * @param sJZDMC the new sJZDMC
	 */
	public void setSJZDMC(String sJZDMC) {
		SJZDMC = sJZDMC;
	}
	
	/**
	 * Gets the sTATE.
	 * 
	 * @return the sTATE
	 */
	public String getSTATE() {
		return STATE;
	}
	
	/**
	 * Sets the sTATE.
	 * 
	 * @param sTATE the new sTATE
	 */
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	
	/**
	 * Gets the rEMARK.
	 * 
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}
	
	/**
	 * Sets the rEMARK.
	 * 
	 * @param rEMARK the new rEMARK
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	
	/**
	 * Gets the serialversionuid.
	 * 
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
