package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * 小数位模型.
 * 
 * @author zhuxw
 */
public class XswModel extends BaseModel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -6821840041362189511L;
	
	/** ---- 表字段 ----. */
	//数字类型
	private String NUMTYPE;
	//小数位
	/** The DECIMALS. */
	private String DECIMALS;
	//进位类型
	/** The ROUNDTYPE. */
	private String ROUNDTYPE;
	//状态
	/** The STATE. */
	private String STATE;
	
	/**
	 * Gets the nUMTYPE.
	 * 
	 * @return the nUMTYPE
	 */
	public String getNUMTYPE() {
		return NUMTYPE;
	}
	
	/**
	 * Sets the nUMTYPE.
	 * 
	 * @param numtype the new nUMTYPE
	 */
	public void setNUMTYPE(String numtype) {
		NUMTYPE = numtype;
	}
	
	/**
	 * Gets the dECIMALS.
	 * 
	 * @return the dECIMALS
	 */
	public String getDECIMALS() {
		return DECIMALS;
	}
	
	/**
	 * Sets the dECIMALS.
	 * 
	 * @param decimals the new dECIMALS
	 */
	public void setDECIMALS(String decimals) {
		DECIMALS = decimals;
	}
	
	/**
	 * Gets the rOUNDTYPE.
	 * 
	 * @return the rOUNDTYPE
	 */
	public String getROUNDTYPE() {
		return ROUNDTYPE;
	}
	
	/**
	 * Sets the rOUNDTYPE.
	 * 
	 * @param roundtype the new rOUNDTYPE
	 */
	public void setROUNDTYPE(String roundtype) {
		ROUNDTYPE = roundtype;
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
	 * @param state the new sTATE
	 */
	public void setSTATE(String state) {
		STATE = state;
	}
	
	/**
	 * Gets the serial version uid.
	 * 
	 * @return the serial version uid
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	
}
