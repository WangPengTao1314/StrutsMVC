package com.hoperun.commons.util.impl.model;

import java.util.List;

public class DeliverOrderImplModel {
	private String DELIVER_ORDER_NO;
	private String STOREOUT_TIME;
	private String TRUCK_NO;
	private List<PdtSNImplModel> pdtModel;
	private List<DeliverOrderDtlImplModel> dtlModel;
	public List<DeliverOrderDtlImplModel> getDtlModel() {
		return dtlModel;
	}
	public void setDtlModel(List<DeliverOrderDtlImplModel> dtlModel) {
		this.dtlModel = dtlModel;
	}
	public List<PdtSNImplModel> getPdtModel() {
		return pdtModel;
	}
	public void setPdtModel(List<PdtSNImplModel> pdtModel) {
		this.pdtModel = pdtModel;
	}
	

	public String getDELIVER_ORDER_NO() {
		return DELIVER_ORDER_NO;
	}
	public void setDELIVER_ORDER_NO(String dELIVERORDERNO) {
		DELIVER_ORDER_NO = dELIVERORDERNO;
	}
	public String getSTOREOUT_TIME() {
		return STOREOUT_TIME;
	}
	public void setSTOREOUT_TIME(String sTOREOUTTIME) {
		STOREOUT_TIME = sTOREOUTTIME;
	}
	public String getTRUCK_NO() {
		return TRUCK_NO;
	}
	public void setTRUCK_NO(String tRUCKNO) {
		TRUCK_NO = tRUCKNO;
	}

	
}
