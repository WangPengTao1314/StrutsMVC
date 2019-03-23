/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseModel.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class BaseModel implements Serializable {

	/**
	 * Instantiates a new base model.
	 */
	public BaseModel() {
		super();
	}

	/** 制单人ID. */
	private String CREATER;
	
	/** 制单人姓名. */
	private String CRENAME;
	
	/** 更新人. */
	private String UPDATER;
	
	/** 更新时间. */
	private String UPDTIME;
	
	/** 制单时间. */
	private String CRETIME;
	
	/** 删除标记. */
	private String DELFLAG;

	/** serial Version ID. */
	private static final long serialVersionUID = 9163744604740313446L;

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		// Field[] fields = getClass().getFields();
		// List<String> excludeFileds = new ArrayList<String>();
		// for (Field f : fields) {
		// if (!f.isAnnotationPresent(Column.class))
		// excludeFileds.add(f.getName());
		// }
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * Gets the cREATER.
	 * 
	 * @return the cREATER
	 */
	public String getCREATER() {
		return CREATER;
	}

	/**
	 * Sets the cREATER.
	 * 
	 * @param creater the new cREATER
	 */
	public void setCREATER(String creater) {
		CREATER = creater;
	}

	/**
	 * Gets the cRENAME.
	 * 
	 * @return the cRENAME
	 */
	public String getCRENAME() {
		return CRENAME;
	}

	/**
	 * Sets the cRENAME.
	 * 
	 * @param crename the new cRENAME
	 */
	public void setCRENAME(String crename) {
		CRENAME = crename;
	}

	/**
	 * Gets the uPDATER.
	 * 
	 * @return the uPDATER
	 */
	public String getUPDATER() {
		return UPDATER;
	}

	/**
	 * Sets the uPDATER.
	 * 
	 * @param updater the new uPDATER
	 */
	public void setUPDATER(String updater) {
		UPDATER = updater;
	}

	/**
	 * Gets the uPDTIME.
	 * 
	 * @return the uPDTIME
	 */
	public String getUPDTIME() {
		return UPDTIME;
	}

	/**
	 * Sets the uPDTIME.
	 * 
	 * @param updtime the new uPDTIME
	 */
	public void setUPDTIME(String updtime) {
		UPDTIME = updtime;
	}

	/**
	 * Gets the cRETIME.
	 * 
	 * @return the cRETIME
	 */
	public String getCRETIME() {
		return CRETIME;
	}

	/**
	 * Sets the cRETIME.
	 * 
	 * @param cretime the new cRETIME
	 */
	public void setCRETIME(String cretime) {
		CRETIME = cretime;
	}

	/**
	 * Gets the dELFLAG.
	 * 
	 * @return the dELFLAG
	 */
	public String getDELFLAG() {
		return DELFLAG;
	}

	/**
	 * Sets the dELFLAG.
	 * 
	 * @param delflag the new dELFLAG
	 */
	public void setDELFLAG(String delflag) {
		DELFLAG = delflag;
	}

}
