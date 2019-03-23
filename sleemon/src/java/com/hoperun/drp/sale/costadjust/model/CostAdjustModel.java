/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnModel
*/
package com.hoperun.drp.sale.costadjust.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public class CostAdjustModel extends BaseModel{
	/**调整单ID*/
	private String COST_ADJUST_ID;
	/**成本调整单编号*/
	private String COST_ADJUST_NO;
	/**月份*/
	private String MONTH;
	/**年份*/
	private String YEAR;
	/**备注*/
	private String REMARK;
	/**库房ID*/
	private String STORE_ID;
	/**库房NO*/
	private String STORE_NO;
	/**库房名称*/
	private String STORE_NAME;
	/**
	 * @return the cOST_ADJUST_ID
	 */
	public String getCOST_ADJUST_ID() {
		return COST_ADJUST_ID;
	}
	/**
	 * @param cOSTADJUSTID the cOST_ADJUST_ID to set
	 */
	public void setCOST_ADJUST_ID(String cOSTADJUSTID) {
		COST_ADJUST_ID = cOSTADJUSTID;
	}
	/**
	 * @return the cOST_ADJUST_NO
	 */
	public String getCOST_ADJUST_NO() {
		return COST_ADJUST_NO;
	}
	/**
	 * @param cOSTADJUSTNO the cOST_ADJUST_NO to set
	 */
	public void setCOST_ADJUST_NO(String cOSTADJUSTNO) {
		COST_ADJUST_NO = cOSTADJUSTNO;
	}
	/**
	 * @return the mONTH
	 */
	public String getMONTH() {
		return MONTH;
	}
	/**
	 * @param mONTH the mONTH to set
	 */
	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}
	/**
	 * @return the yEAR
	 */
	public String getYEAR() {
		return YEAR;
	}
	/**
	 * @param yEAR the yEAR to set
	 */
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
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
	 * @return the sTORE_ID
	 */
	public String getSTORE_ID() {
		return STORE_ID;
	}
	/**
	 * @param sTOREID the sTORE_ID to set
	 */
	public void setSTORE_ID(String sTOREID) {
		STORE_ID = sTOREID;
	}
	/**
	 * @return the sTORE_NO
	 */
	public String getSTORE_NO() {
		return STORE_NO;
	}
	/**
	 * @param sTORENO the sTORE_NO to set
	 */
	public void setSTORE_NO(String sTORENO) {
		STORE_NO = sTORENO;
	}
	/**
	 * @return the sTORE_NAME
	 */
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	/**
	 * @param sTORENAME the sTORE_NAME to set
	 */
	public void setSTORE_NAME(String sTORENAME) {
		STORE_NAME = sTORENAME;
	}
	
}