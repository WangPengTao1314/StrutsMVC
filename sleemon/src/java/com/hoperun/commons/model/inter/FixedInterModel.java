package com.hoperun.commons.model.inter;
/**  
 * @func
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-1 上午10:24:41 
 */
public class FixedInterModel {
	/**
	 * 状态
	 */
	private String USE_STATUS;
	/**
	 * 系统名称（固定值-MDM）
	 */
	private String SysName;
	
	public String getUSE_STATUS() {
		return USE_STATUS;
	}
	public void setUSE_STATUS(String uSESTATUS) {
		USE_STATUS = uSESTATUS;
	}
	public String getSysName() {
		return SysName;
	}
	public void setSysName(String sysName) {
		SysName = sysName;
	}
	
}
