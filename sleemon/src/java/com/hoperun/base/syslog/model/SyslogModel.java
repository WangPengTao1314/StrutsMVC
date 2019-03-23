package com.hoperun.base.syslog.model;

import com.hoperun.commons.model.BaseModel;

public class SyslogModel extends BaseModel {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -1198266156050990849L;
	
	/** 系统日志id **/
	private String SYSLOG_ID;	
	/** 模块名称 **/
	private String UC_NAME;
	/** 操作名称 **/
	private String ACT_NAME;
	/** 调用/被调用 **/
	private String OPRATOR;
	/** 操作时间 **/
	private String ACT_TIME;
	/** 状态  **/
	private String STATE;
	/** 备注   **/
	private String REMARK;
	/** 调用方appcode  **/
	private String APPCODE;
	/** uid **/
	private String APPID;
	/** 服务码+操作码  **/
	private String OPRCODE;
	
	
	public String getSYSLOGID() {
		return SYSLOG_ID;
	}
	public void setSYSLOGID(String sYSLOGID) {
		SYSLOG_ID = sYSLOGID;
	}
	public String getUCNAME() {
		return UC_NAME;
	}
	public void setUCNAME(String uCNAME) {
		UC_NAME = uCNAME;
	}
	public String getACTNAME() {
		return ACT_NAME;
	}
	public void setACTNAME(String aCTNAME) {
		ACT_NAME = aCTNAME;
	}
	public String getOPRATOR() {
		return OPRATOR;
	}
	public void setOPRATOR(String oPRATOR) {
		OPRATOR = oPRATOR;
	}
	public String getACTTIME() {
		return ACT_TIME;
	}
	public void setACTTIME(String aCTTIME) {
		ACT_TIME = aCTTIME;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
	public String getAPPCODE() {
		return APPCODE;
	}
	public void setAPPCODE(String aPPCODE) {
		APPCODE = aPPCODE;
	}
	public String getAPPID() {
		return APPID;
	}
	public void setAPPID(String aPPID) {
		APPID = aPPID;
	}
	public String getOPRCODE() {
		return OPRCODE;
	}
	public void setOPRCODE(String oPRCODE) {
		OPRCODE = oPRCODE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
