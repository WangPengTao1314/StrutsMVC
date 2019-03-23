package com.hoperun.commons.model;

import java.util.List;

public class InterReturnMsg {
	
	
	private String FLAG;
	private String KEY;
	private String APPCODE;
	private List list;
	public String getAPPCODE() {
		return APPCODE;
	}
	public void setAPPCODE(String aPPCODE) {
		APPCODE = aPPCODE;
	}
	public String getFLAG() {
		return FLAG;
	}
	public void setFLAG(String fLAG) {
		FLAG = fLAG;
	}
	public String getMESSAGE() {
		return MESSAGE;
	}
	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}
	private String MESSAGE;
	
	
	public String getKEY() {
		return KEY;
	}
	public void setKEY(String kEY) {
		KEY = kEY;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	
}
