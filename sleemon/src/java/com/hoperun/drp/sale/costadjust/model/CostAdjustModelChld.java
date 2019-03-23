/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnModelChld
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
public class CostAdjustModelChld extends BaseModel{
	/**成本调整单明细ID*/
	private String COST_ADJUST_DTL_ID;
	/**调整单ID*/
	private String COST_ADJUST_ID;
	/**货品ID*/
	private String PRD_ID;
	/**货品编号*/
	private String PRD_NO;
	/**货品名称*/
	private String PRD_NAME;
	/**规格型号*/
	private String PRD_SPEC;
	/**标准单位*/
	private String STD_UNIT;
	/**订单特殊工艺ID*/
	private String SPCL_TECH_ID;
	/**调整金额*/
	private String ADJUST_AMOUNT;
	/**备注*/
	private String REMARK;
	/**当前成本单价**/
	private String CUR_COST_PRICE;
	/**调整后成本单价**/
	private String NEW_COST_PRICE;
	/**
	 * @return the cOST_ADJUST_DTL_ID
	 */
	public String getCOST_ADJUST_DTL_ID() {
		return COST_ADJUST_DTL_ID;
	}
	/**
	 * @param cOSTADJUSTDTLID the cOST_ADJUST_DTL_ID to set
	 */
	public void setCOST_ADJUST_DTL_ID(String cOSTADJUSTDTLID) {
		COST_ADJUST_DTL_ID = cOSTADJUSTDTLID;
	}
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
	 * @return the aDJUST_AMOUNT
	 */
	public String getADJUST_AMOUNT() {
		return ADJUST_AMOUNT;
	}
	/**
	 * @param aDJUSTAMOUNT the aDJUST_AMOUNT to set
	 */
	public void setADJUST_AMOUNT(String aDJUSTAMOUNT) {
		ADJUST_AMOUNT = aDJUSTAMOUNT;
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
	public String getCUR_COST_PRICE() {
		return CUR_COST_PRICE;
	}
	public void setCUR_COST_PRICE(String cURCOSTPRICE) {
		CUR_COST_PRICE = cURCOSTPRICE;
	}
	public String getNEW_COST_PRICE() {
		return NEW_COST_PRICE;
	}
	public void setNEW_COST_PRICE(String nEWCOSTPRICE) {
		NEW_COST_PRICE = nEWCOSTPRICE;
	}
	
}