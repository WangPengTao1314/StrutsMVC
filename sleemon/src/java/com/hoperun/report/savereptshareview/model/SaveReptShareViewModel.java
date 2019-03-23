package com.hoperun.report.savereptshareview.model;

import com.hoperun.commons.model.BaseModel;

public class SaveReptShareViewModel extends BaseModel{
	private String SHARED_PSON_ID;
	private String SHARED_PSON_NAME;
	/**
	 * @return the sHARED_PSON_ID
	 */
	public String getSHARED_PSON_ID() {
		return SHARED_PSON_ID;
	}
	/**
	 * @param sHAREDPSONID the sHARED_PSON_ID to set
	 */
	public void setSHARED_PSON_ID(String sHAREDPSONID) {
		SHARED_PSON_ID = sHAREDPSONID;
	}
	/**
	 * @return the sHARED_PSON_NAME
	 */
	public String getSHARED_PSON_NAME() {
		return SHARED_PSON_NAME;
	}
	/**
	 * @param sHAREDPSONNAME the sHARED_PSON_NAME to set
	 */
	public void setSHARED_PSON_NAME(String sHAREDPSONNAME) {
		SHARED_PSON_NAME = sHAREDPSONNAME;
	}
	
}
