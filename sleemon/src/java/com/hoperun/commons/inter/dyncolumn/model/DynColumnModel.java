package com.hoperun.commons.inter.dyncolumn.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class DynColumnModel.
 * 
 * @module 共通模块
 * @func 动态列
 * @version 1.1
 * @author zhuxw
 */
public class DynColumnModel extends BaseModel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5364056473522970557L;
	
	/** The COLPHYSICSNAME. */
	private String COLPHYSICSNAME;
	
	/** The COLLOGICNAME. */
	private String COLLOGICNAME;
	
	/** The ISDISPLAY. */
	private String ISDISPLAY;
	
	/** The ISEDIT. */
	private String ISEDIT;
	
	/** The COLORDER. */
	private String COLORDER;
	
	/** The ALIGN. */
	private String ALIGN;
	
	/** The TABNAME. */
	private String TABNAME;
	
	//--用户配置表信息
	/** The USERCOLSHOWID. */
	private String USERCOLSHOWID;
	
	/** The XTYHID. */
	private String XTYHID;
	
	/** The TABNO. */
	private String TABNO;

	/**
	 * Gets the tABNO.
	 * 
	 * @return the tABNO
	 */
	public String getTABNO() {
		return TABNO;
	}

	/**
	 * Sets the tABNO.
	 * 
	 * @param tabno the new tABNO
	 */
	public void setTABNO(String tabno) {
		TABNO = tabno;
	}

	/**
	 * Gets the uSERCOLSHOWID.
	 * 
	 * @return the uSERCOLSHOWID
	 */
	public String getUSERCOLSHOWID() {
		return USERCOLSHOWID;
	}

	/**
	 * Sets the uSERCOLSHOWID.
	 * 
	 * @param usercolshowid the new uSERCOLSHOWID
	 */
	public void setUSERCOLSHOWID(String usercolshowid) {
		USERCOLSHOWID = usercolshowid;
	}

	/**
	 * Gets the xTYHID.
	 * 
	 * @return the xTYHID
	 */
	public String getXTYHID() {
		return XTYHID;
	}

	/**
	 * Sets the xTYHID.
	 * 
	 * @param xtyhid the new xTYHID
	 */
	public void setXTYHID(String xtyhid) {
		XTYHID = xtyhid;
	}

	/**
	 * Gets the tABNAME.
	 * 
	 * @return the tABNAME
	 */
	public String getTABNAME() {
		return TABNAME;
	}

	/**
	 * Sets the tABNAME.
	 * 
	 * @param tabname the new tABNAME
	 */
	public void setTABNAME(String tabname) {
		TABNAME = tabname;
	}

	/**
	 * Gets the cOLPHYSICSNAME.
	 * 
	 * @return the cOLPHYSICSNAME
	 */
	public String getCOLPHYSICSNAME() {
		return COLPHYSICSNAME;
	}

	/**
	 * Sets the cOLPHYSICSNAME.
	 * 
	 * @param colphysicsname the new cOLPHYSICSNAME
	 */
	public void setCOLPHYSICSNAME(String colphysicsname) {
		COLPHYSICSNAME = colphysicsname;
	}

	/**
	 * Gets the cOLLOGICNAME.
	 * 
	 * @return the cOLLOGICNAME
	 */
	public String getCOLLOGICNAME() {
		return COLLOGICNAME;
	}

	/**
	 * Sets the cOLLOGICNAME.
	 * 
	 * @param collogicname the new cOLLOGICNAME
	 */
	public void setCOLLOGICNAME(String collogicname) {
		COLLOGICNAME = collogicname;
	}

	/**
	 * Gets the iSDISPLAY.
	 * 
	 * @return the iSDISPLAY
	 */
	public String getISDISPLAY() {
		return ISDISPLAY;
	}

	/**
	 * Sets the iSDISPLAY.
	 * 
	 * @param isdisplay the new iSDISPLAY
	 */
	public void setISDISPLAY(String isdisplay) {
		ISDISPLAY = isdisplay;
	}

	/**
	 * Gets the iSEDIT.
	 * 
	 * @return the iSEDIT
	 */
	public String getISEDIT() {
		return ISEDIT;
	}

	/**
	 * Sets the iSEDIT.
	 * 
	 * @param isedit the new iSEDIT
	 */
	public void setISEDIT(String isedit) {
		ISEDIT = isedit;
	}

	/**
	 * Gets the cOLORDER.
	 * 
	 * @return the cOLORDER
	 */
	public String getCOLORDER() {
		return COLORDER;
	}

	/**
	 * Sets the cOLORDER.
	 * 
	 * @param colorder the new cOLORDER
	 */
	public void setCOLORDER(String colorder) {
		COLORDER = colorder;
	}

	/**
	 * Gets the aLIGN.
	 * 
	 * @return the aLIGN
	 */
	public String getALIGN() {
		return ALIGN;
	}

	/**
	 * Sets the aLIGN.
	 * 
	 * @param align the new aLIGN
	 */
	public void setALIGN(String align) {
		ALIGN = align;
	}

}
