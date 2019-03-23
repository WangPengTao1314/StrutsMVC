/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：GrgzjhModelChld.java
 */
package com.hoperun.base.grgzjh.model;
import com.hoperun.commons.model.BaseModel;
/**
 * The Class GrgzjhModelChld.
 * 
 * @module 系统管理
 * @func 个人工作计划
 * @version 1.0
 * @author 吴军
 */
public class GrgzjhModelChld extends BaseModel{
    
	/** 个人工作计划明细行ID **/                            
	private String PER_WORK_PLAN_DTL_ID;

	/** 工作计划ID **/                                      
	private String PER_WORK_PLAN_ID;

	/** 个人工作计划内容 **/                                
	private String PLAN_CONTENT;

	/** 计划年份 **/                                        
	private String PLAN_YEAR;

	/** 计划月份 **/                                        
	private String PLAN_MONTH;

	/** 计划日 **/                                          
	private String PLAN_DAY;

	/** 是否重点工作 **/                                    
	private String IS_IMPT_FLAG;

	/** 删除标记 **/                                        
	private String DEL_FLAG;

	public String getPER_WORK_PLAN_DTL_ID() {
		return PER_WORK_PLAN_DTL_ID;
	}

	public void setPER_WORK_PLAN_DTL_ID(String per_work_plan_dtl_id) {
		PER_WORK_PLAN_DTL_ID = per_work_plan_dtl_id;
	}

	public String getPER_WORK_PLAN_ID() {
		return PER_WORK_PLAN_ID;
	}

	public void setPER_WORK_PLAN_ID(String per_work_plan_id) {
		PER_WORK_PLAN_ID = per_work_plan_id;
	}

	public String getPLAN_CONTENT() {
		return PLAN_CONTENT;
	}

	public void setPLAN_CONTENT(String plan_content) {
		PLAN_CONTENT = plan_content;
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

	public String getIS_IMPT_FLAG() {
		return IS_IMPT_FLAG;
	}

	public void setIS_IMPT_FLAG(String is_impt_flag) {
		IS_IMPT_FLAG = is_impt_flag;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}
}