/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：GrgzjhModel.java
 */
package com.hoperun.base.grgzjh.model;
import com.hoperun.commons.model.BaseModel;
/**
 * The Class GrgzjhModel.
 * 
 * @module 系统管理
 * @func 个人工作计划
 * @version 1.0
 * @author 吴军
 */
public class GrgzjhModel extends BaseModel{
   
	/** 工作计划ID **/                                  
	private String PER_WORK_PLAN_ID;

	/** 工作计划编号 **/                               
	private String PER_WORK_PLAN_NO;

	/** 计划人员ID **/                                  
	private String PLAN_PSON_ID;                  

	/** 计划人员编号 **/                                
	private String PLAN_PSON_NO;

	/** 计划人员姓名 **/                                
	private String PLAN_PSON_NAME;

	/** 计划年份 **/                                    
	private String PLAN_YEAR;

	/** 计划月份 **/                                    
	private String PLAN_MONTH;

	/** 计划日 **/                                      
	private String PLAN_DAY;

	/** 状态 **/                                        
	private String STATE;

	/** 备注 **/                                        
	private String REMARK;

	/** 制单人ID **/                                    
	private String CREATOR;

	/** 制单人名称 **/                                  
	private String CRE_NAME;

	/** 制单时间 **/                                    
	private String CRE_TIME;

	/** 更新人ID **/                                    
	private String UPDATOR;

	/** 更新人名称 **/                                  
	private String UPD_NAME;

	/** 更新时间 **/                                    
	private String UPD_TIME;

	/** 制单部门ID **/                                  
	private String DEPT_ID;

	/** 制单部门名称 **/                                
	private String DEPT_NAME;

	/** 制单机构ID **/                                  
	private String ORG_ID;

	/** 制单机构名称 **/                                
	private String ORG_NAME;

	/** 账套 **/                                        
	private String LEDGER_ID;

	/** 账套名称 **/                                    
	private String LEDGER_NAME;                        

	/** 删除标记 **/                                    
	private String DEL_FLAG;

	/** 审核人ID **/                                    
	private String AUDIT_ID;                         

	/** 审核人姓名 **/                                  
	private String AUDIT_NAME;

	/** 审核时间 **/                                    
	private String AUDIT_TIME;

	public String getPER_WORK_PLAN_ID() {
		return PER_WORK_PLAN_ID;
	}

	public void setPER_WORK_PLAN_ID(String per_work_plan_id) {
		PER_WORK_PLAN_ID = per_work_plan_id;
	}

	public String getPER_WORK_PLAN_NO() {
		return PER_WORK_PLAN_NO;
	}

	public void setPER_WORK_PLAN_NO(String per_work_plan_no) {
		PER_WORK_PLAN_NO = per_work_plan_no;
	}

	public String getPLAN_PSON_ID() {
		return PLAN_PSON_ID;
	}

	public void setPLAN_PSON_ID(String plan_pson_id) {
		PLAN_PSON_ID = plan_pson_id;
	}

	public String getPLAN_PSON_NO() {
		return PLAN_PSON_NO;
	}

	public void setPLAN_PSON_NO(String plan_pson_no) {
		PLAN_PSON_NO = plan_pson_no;
	}

	public String getPLAN_PSON_NAME() {
		return PLAN_PSON_NAME;
	}

	public void setPLAN_PSON_NAME(String plan_pson_name) {
		PLAN_PSON_NAME = plan_pson_name;
	}

	public String getPLAN_YEAR() {
		return PLAN_YEAR;
	}

	public void setPLAN_YEAR(String plan_year) {
		PLAN_YEAR = plan_year;
	}

	public String getPLAN_MONTH() {
		return PLAN_MONTH;
	}

	public void setPLAN_MONTH(String plan_month) {
		PLAN_MONTH = plan_month;
	}

	public String getPLAN_DAY() {
		return PLAN_DAY;
	}

	public void setPLAN_DAY(String plan_day) {
		PLAN_DAY = plan_day;
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

	public String getCREATOR() {
		return CREATOR;
	}

	public void setCREATOR(String creator) {
		CREATOR = creator;
	}

	public String getCRE_NAME() {
		return CRE_NAME;
	}

	public void setCRE_NAME(String cre_name) {
		CRE_NAME = cre_name;
	}

	public String getCRE_TIME() {
		return CRE_TIME;
	}

	public void setCRE_TIME(String cre_time) {
		CRE_TIME = cre_time;
	}

	public String getUPDATOR() {
		return UPDATOR;
	}

	public void setUPDATOR(String updator) {
		UPDATOR = updator;
	}

	public String getUPD_NAME() {
		return UPD_NAME;
	}

	public void setUPD_NAME(String upd_name) {
		UPD_NAME = upd_name;
	}

	public String getUPD_TIME() {
		return UPD_TIME;
	}

	public void setUPD_TIME(String upd_time) {
		UPD_TIME = upd_time;
	}

	public String getDEPT_ID() {
		return DEPT_ID;
	}

	public void setDEPT_ID(String dept_id) {
		DEPT_ID = dept_id;
	}

	public String getDEPT_NAME() {
		return DEPT_NAME;
	}

	public void setDEPT_NAME(String dept_name) {
		DEPT_NAME = dept_name;
	}

	public String getORG_ID() {
		return ORG_ID;
	}

	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}

	public String getORG_NAME() {
		return ORG_NAME;
	}

	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
	}

	public String getLEDGER_ID() {
		return LEDGER_ID;
	}

	public void setLEDGER_ID(String ledger_id) {
		LEDGER_ID = ledger_id;
	}

	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}

	public void setLEDGER_NAME(String ledger_name) {
		LEDGER_NAME = ledger_name;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}

	public String getAUDIT_ID() {
		return AUDIT_ID;
	}

	public void setAUDIT_ID(String audit_id) {
		AUDIT_ID = audit_id;
	}

	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}

	public void setAUDIT_NAME(String audit_name) {
		AUDIT_NAME = audit_name;
	}

	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}

	public void setAUDIT_TIME(String audit_time) {
		AUDIT_TIME = audit_time;
	}
	
}