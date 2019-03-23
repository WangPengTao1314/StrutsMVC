package com.hoperun.drp.oamg.channTermianl.model;

import com.hoperun.commons.model.BaseModel;

public class ChannTerminalQuotaModel  extends BaseModel {
    
	 private static final long serialVersionUID = 1L;
	 
	 /**门店指标ID**/
	 private   String  TERMINAL_QUOTA_ID;
	 /**门店指标编号**/
	 private   String  TERMINAL_QUOTA_NO;
	 /**战区ID**/
	 private   String  WAREA_ID;
	 /**战区编号**/
	 private   String  WAREA_NO;
	 /**战区名称**/
	 private   String  WAREA_NAME;
	 /**区域ID**/
	 private   String  AREA_ID;
	 /**区域编号**/
	 private   String  AREA_NO;
	 /**区域名称**/
	 private   String  AREA_NAME;
	 /**年份**/
	 private   String  YEAR;
	 /**月份**/
	 private   String  MONTH;
	 /**门店指标数量**/
	 private   String  QUOTA_NUM;
	 /**状态**/
	 private   String  STATE;
	 /**备注**/
	 private   String  REMARK;
	public String getTERMINAL_QUOTA_ID() {
		return TERMINAL_QUOTA_ID;
	}
	public void setTERMINAL_QUOTA_ID(String terminal_quota_id) {
		TERMINAL_QUOTA_ID = terminal_quota_id;
	}
	public String getTERMINAL_QUOTA_NO() {
		return TERMINAL_QUOTA_NO;
	}
	public void setTERMINAL_QUOTA_NO(String terminal_quota_no) {
		TERMINAL_QUOTA_NO = terminal_quota_no;
	}
	public String getWAREA_ID() {
		return WAREA_ID;
	}
	public void setWAREA_ID(String warea_id) {
		WAREA_ID = warea_id;
	}
	public String getWAREA_NO() {
		return WAREA_NO;
	}
	public void setWAREA_NO(String warea_no) {
		WAREA_NO = warea_no;
	}
	public String getWAREA_NAME() {
		return WAREA_NAME;
	}
	public void setWAREA_NAME(String warea_name) {
		WAREA_NAME = warea_name;
	}
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String area_id) {
		AREA_ID = area_id;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public void setAREA_NO(String area_no) {
		AREA_NO = area_no;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String area_name) {
		AREA_NAME = area_name;
	}
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String year) {
		YEAR = year;
	}
	public String getMONTH() {
		return MONTH;
	}
	public void setMONTH(String month) {
		MONTH = month;
	}
	public String getQUOTA_NUM() {
		return QUOTA_NUM;
	}
	public void setQUOTA_NUM(String quota_num) {
		QUOTA_NUM = quota_num;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
}
