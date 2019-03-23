package com.hoperun.drp.oamg.openterminal.model;

public class TerminalCommModel {
	
	/**终端申请明细ID**/
	private String OPEN_TERMINAL_REQ_DTL_ID;
	/**竞品ID**/
	private String COMMODITIES_ID;
	/**竞品名称**/
	private String COMMODITIES_NAME;
	/**序号**/
	private String SEQ_NO;
	private String DEL_FLAG;
	
	public String getOPEN_TERMINAL_REQ_DTL_ID() {
		return OPEN_TERMINAL_REQ_DTL_ID;
	}
	public void setOPEN_TERMINAL_REQ_DTL_ID(String oPENTERMINALREQDTLID) {
		OPEN_TERMINAL_REQ_DTL_ID = oPENTERMINALREQDTLID;
	}
	public String getCOMMODITIES_ID() {
		return COMMODITIES_ID;
	}
	public void setCOMMODITIES_ID(String cOMMODITIESID) {
		COMMODITIES_ID = cOMMODITIESID;
	}
	public String getCOMMODITIES_NAME() {
		return COMMODITIES_NAME;
	}
	public void setCOMMODITIES_NAME(String cOMMODITIESNAME) {
		COMMODITIES_NAME = cOMMODITIESNAME;
	}
	public String getSEQ_NO() {
		return SEQ_NO;
	}
	public void setSEQ_NO(String sEQNO) {
		SEQ_NO = sEQNO;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	
	

}
