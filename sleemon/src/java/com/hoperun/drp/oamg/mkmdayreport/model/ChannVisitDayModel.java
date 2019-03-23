package com.hoperun.drp.oamg.mkmdayreport.model;

import com.hoperun.commons.model.BaseModel;

public class ChannVisitDayModel extends BaseModel  {
      
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**加盟商拜访ID**/
	private  String  CHANN_VISIT_DAY_ID;
	/**营销经理日报表ID**/
	private  String  MKM_DAY_RPT_ID;
	/**渠道ID**/
	private  String  CHANN_ID;
	/**渠道编号**/
	private  String  CHANN_NO;
	/**渠道名称**/
	private  String  CHANN_NAME;
	/**床垫金额**/
	private  String  MATT_AMOUNT;
	/**软床金额**/
	private  String  SOFT_BED_AMOUNT;
	/**其他金额**/
	private  String  OTHER_AMOUNT;
	/**加盟商问题**/
	private  String  CHANN_PROBLEM;
	/**改善计划**/
	private  String  IMPRV_PLAN;
	/**竞品信息**/
	private  String  COMPET_PRODUCT;
	/**支持需求**/
	private  String  SUPPORT_REQ;
	
	private  String  STORE_ATT;
	
	public String getSTORE_ATT() {
		return STORE_ATT;
	}
	public void setSTORE_ATT(String store_att) {
		STORE_ATT = store_att;
	}
	public String getCHANN_VISIT_DAY_ID() {
		return CHANN_VISIT_DAY_ID;
	}
	public void setCHANN_VISIT_DAY_ID(String chann_visit_day_id) {
		CHANN_VISIT_DAY_ID = chann_visit_day_id;
	}
	public String getMKM_DAY_RPT_ID() {
		return MKM_DAY_RPT_ID;
	}
	public void setMKM_DAY_RPT_ID(String mkm_day_rpt_id) {
		MKM_DAY_RPT_ID = mkm_day_rpt_id;
	}
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	public void setCHANN_ID(String chann_id) {
		CHANN_ID = chann_id;
	}
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	public void setCHANN_NO(String chann_no) {
		CHANN_NO = chann_no;
	}
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	public String getMATT_AMOUNT() {
		return MATT_AMOUNT;
	}
	public void setMATT_AMOUNT(String matt_amount) {
		MATT_AMOUNT = matt_amount;
	}
	public String getSOFT_BED_AMOUNT() {
		return SOFT_BED_AMOUNT;
	}
	public void setSOFT_BED_AMOUNT(String soft_bed_amount) {
		SOFT_BED_AMOUNT = soft_bed_amount;
	}
	public String getOTHER_AMOUNT() {
		return OTHER_AMOUNT;
	}
	public void setOTHER_AMOUNT(String other_amount) {
		OTHER_AMOUNT = other_amount;
	}
	public String getCHANN_PROBLEM() {
		return CHANN_PROBLEM;
	}
	public void setCHANN_PROBLEM(String chann_problem) {
		CHANN_PROBLEM = chann_problem;
	}
	public String getIMPRV_PLAN() {
		return IMPRV_PLAN;
	}
	public void setIMPRV_PLAN(String imprv_plan) {
		IMPRV_PLAN = imprv_plan;
	}
	public String getCOMPET_PRODUCT() {
		return COMPET_PRODUCT;
	}
	public void setCOMPET_PRODUCT(String compet_product) {
		COMPET_PRODUCT = compet_product;
	}
	public String getSUPPORT_REQ() {
		return SUPPORT_REQ;
	}
	public void setSUPPORT_REQ(String support_req) {
		SUPPORT_REQ = support_req;
	}
}
