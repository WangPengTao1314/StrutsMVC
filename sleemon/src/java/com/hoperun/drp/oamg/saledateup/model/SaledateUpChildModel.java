package com.hoperun.drp.oamg.saledateup.model;

import com.hoperun.commons.model.BaseModel;

public class SaledateUpChildModel extends BaseModel{
	
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**销售数据上报明细ID**/
	private String SALE_DATE_UP_DTL_ID;
	/**销售数据上报ID**/
	private String SALE_DATE_UP_ID;
	/**货品类别**/
	private String PRD_TYPE;
	/**销售数量**/
	private String TOTAL_NUM;
	/**销售金额**/
	private String TOTAL_AMOUNT;
	private String DEL_FLAG;
	
	
	public String getSALE_DATE_UP_DTL_ID() {
		return SALE_DATE_UP_DTL_ID;
	}
	public void setSALE_DATE_UP_DTL_ID(String sALEDATEUPDTLID) {
		SALE_DATE_UP_DTL_ID = sALEDATEUPDTLID;
	}
	public String getSALE_DATE_UP_ID() {
		return SALE_DATE_UP_ID;
	}
	public void setSALE_DATE_UP_ID(String sALEDATEUPID) {
		SALE_DATE_UP_ID = sALEDATEUPID;
	}
	public String getPRD_TYPE() {
		return PRD_TYPE;
	}
	public void setPRD_TYPE(String pRDTYPE) {
		PRD_TYPE = pRDTYPE;
	}
	public String getTOTAL_NUM() {
		return TOTAL_NUM;
	}
	public void setTOTAL_NUM(String tOTALNUM) {
		TOTAL_NUM = tOTALNUM;
	}
	public String getTOTAL_AMOUNT() {
		return TOTAL_AMOUNT;
	}
	public void setTOTAL_AMOUNT(String tOTALAMOUNT) {
		TOTAL_AMOUNT = tOTALAMOUNT;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	
	

}
