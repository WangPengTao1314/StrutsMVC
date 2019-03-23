package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;
/**
 * 公告生效部门(T_SYS_NOTC_DEPT)
 * @author zhang_zhongbin
 *
 */
public class NotcDeptModel extends BaseModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//id
	private String NOTC_DEPT_ID;
	//公告ID
	private String NOTICE_ID;
	//部门ID
	private String DEPT_ID;
	//部门名称
	private String DEPT_NAME;
	//删除标记
	private String DEL_FLAG;
	
	public String getNOTC_DEPT_ID() {
		return NOTC_DEPT_ID;
	}
	public void setNOTC_DEPT_ID(String nOTCDEPTID) {
		NOTC_DEPT_ID = nOTCDEPTID;
	}
	public String getNOTICE_ID() {
		return NOTICE_ID;
	}
	public void setNOTICE_ID(String nOTICEID) {
		NOTICE_ID = nOTICEID;
	}
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dEPTID) {
		DEPT_ID = dEPTID;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dEPTNAME) {
		DEPT_NAME = dEPTNAME;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}
	
	 
	
	
	

}
