/**
 * prjName:喜临门营销平台
 * ucName:人员信息
 * fileName:EmployeeModelChld
*/
package com.hoperun.drp.base.employee.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class EmployeeModelChld extends BaseModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4355974652152826191L;
	/** 系统角色id*/
	private String XTJSID;
	/** 系统用户角色id*/
	private String XTYHJSID;
	/** 角色编号*/
	private String JSBH;
	/** 角色名称*/
	private String JSMC;
	/** 角色说明*/
	private String JSSM;
	/**
	 * @return the xTJSID
	 */
	public String getXTJSID() {
		return XTJSID;
	}
	/**
	 * @param xTJSID the xTJSID to set
	 */
	public void setXTJSID(String xTJSID) {
		XTJSID = xTJSID;
	}
	/**
	 * @return the xTYHJSID
	 */
	public String getXTYHJSID() {
		return XTYHJSID;
	}
	/**
	 * @param xTYHJSID the xTYHJSID to set
	 */
	public void setXTYHJSID(String xTYHJSID) {
		XTYHJSID = xTYHJSID;
	}
	/**
	 * @return the jSBH
	 */
	public String getJSBH() {
		return JSBH;
	}
	/**
	 * @param jSBH the jSBH to set
	 */
	public void setJSBH(String jSBH) {
		JSBH = jSBH;
	}
	/**
	 * @return the jSMC
	 */
	public String getJSMC() {
		return JSMC;
	}
	/**
	 * @param jSMC the jSMC to set
	 */
	public void setJSMC(String jSMC) {
		JSMC = jSMC;
	}
	/**
	 * @return the jSSM
	 */
	public String getJSSM() {
		return JSSM;
	}
	/**
	 * @param jSSM the jSSM to set
	 */
	public void setJSSM(String jSSM) {
		JSSM = jSSM;
	}
	
	
}