package com.hoperun.drp.oamg.termrefinecheck.model;

import com.hoperun.commons.model.BaseModel;

public class ChanncheckplanModel  extends BaseModel{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**渠道化检查方案ID**/
	 private   String   CHANN_CHECK_PLAN_ID;
     /**渠道化检查方案编号**/ 
	 private   String   CHANN_CHECK_PLAN_NO;
	 /**渠道化检查方案名称**/
	 private   String   CHANN_CHECK_PLAN_NAME;
	 /**方案类型**/
	 private   String   PLAN_TYPE;
	 /**检查开始日期**/
	 private   String   CHECK_DATE_BEG;
	 /**检查结束日期**/
	 private   String   CHECK_DATE_END;
	 /**状态**/
	 private   String   STATE;
	 /**备注**/
	 private   String   REMARK;
	 /**附件文件**/
	 private   String   PIC_PATH;
	 
	public String getCHANN_CHECK_PLAN_ID() {
		return CHANN_CHECK_PLAN_ID;
	}
	public void setCHANN_CHECK_PLAN_ID(String chann_check_plan_id) {
		CHANN_CHECK_PLAN_ID = chann_check_plan_id;
	}
	public String getCHANN_CHECK_PLAN_NO() {
		return CHANN_CHECK_PLAN_NO;
	}
	public void setCHANN_CHECK_PLAN_NO(String chann_check_plan_no) {
		CHANN_CHECK_PLAN_NO = chann_check_plan_no;
	}
	public String getCHANN_CHECK_PLAN_NAME() {
		return CHANN_CHECK_PLAN_NAME;
	}
	public void setCHANN_CHECK_PLAN_NAME(String chann_check_plan_name) {
		CHANN_CHECK_PLAN_NAME = chann_check_plan_name;
	}
	public String getPLAN_TYPE() {
		return PLAN_TYPE;
	}
	public void setPLAN_TYPE(String plan_type) {
		PLAN_TYPE = plan_type;
	}
	public String getCHECK_DATE_BEG() {
		return CHECK_DATE_BEG;
	}
	public void setCHECK_DATE_BEG(String check_date_beg) {
		CHECK_DATE_BEG = check_date_beg;
	}
	public String getCHECK_DATE_END() {
		return CHECK_DATE_END;
	}
	public void setCHECK_DATE_END(String check_date_end) {
		CHECK_DATE_END = check_date_end;
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
	public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pic_path) {
		PIC_PATH = pic_path;
	}
}