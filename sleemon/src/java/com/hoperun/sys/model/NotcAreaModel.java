package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 公告生效区域(T_SYS_NOTC_AREA)
 * @author zhang_zhongbin
 *
 */
public class NotcAreaModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//id
	private String NOTC_AREA_ID;
	//公告ID
	private String NOTICE_ID;
	//区域ID
	private String AREA_ID;
	//区域编号
	private String AREA_NO;
	//区域名称
	private String AREA_NAME;
	//删除标记
	private String DEL_FLAG;
	
	
	public String getNOTC_AREA_ID() {
		return NOTC_AREA_ID;
	}
	public void setNOTC_AREA_ID(String nOTCAREAID) {
		NOTC_AREA_ID = nOTCAREAID;
	}
	public String getNOTICE_ID() {
		return NOTICE_ID;
	}
	public void setNOTICE_ID(String nOTICEID) {
		NOTICE_ID = nOTICEID;
	}
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}
	
	
	
	

}
