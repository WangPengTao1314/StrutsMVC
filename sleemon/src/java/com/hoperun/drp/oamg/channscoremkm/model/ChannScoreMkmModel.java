package com.hoperun.drp.oamg.channscoremkm.model;

import com.hoperun.commons.model.BaseModel;

public class ChannScoreMkmModel  extends BaseModel  {
    
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**加盟商营销经理评分ID**/
	 private  String  CHANN_SCORE_MKM_ID;
	 /**评分单编号**/
	 private  String  CHANN_SCORE_MKM_NO;
	 /**营销经理ID**/
	 private  String  MKM_ID;
	 /**营销经理名称**/
	 private  String  MKM_NAME;
	 /**评价人ID**/
	 private  String  SCORE_ID;
	 /**评价人名称**/
	 private  String  SCORE_NAME;
	 /**评价日期**/
	 private  String  SCORE_DATE;
	 /**得分**/
	 private  String  SCORE_TOTAL;
	public String getCHANN_SCORE_MKM_ID() {
		return CHANN_SCORE_MKM_ID;
	}
	public void setCHANN_SCORE_MKM_ID(String chann_score_mkm_id) {
		CHANN_SCORE_MKM_ID = chann_score_mkm_id;
	}
	public String getCHANN_SCORE_MKM_NO() {
		return CHANN_SCORE_MKM_NO;
	}
	public void setCHANN_SCORE_MKM_NO(String chann_score_mkm_no) {
		CHANN_SCORE_MKM_NO = chann_score_mkm_no;
	}
	public String getMKM_ID() {
		return MKM_ID;
	}
	public void setMKM_ID(String mkm_id) {
		MKM_ID = mkm_id;
	}
	public String getMKM_NAME() {
		return MKM_NAME;
	}
	public void setMKM_NAME(String mkm_name) {
		MKM_NAME = mkm_name;
	}
	public String getSCORE_ID() {
		return SCORE_ID;
	}
	public void setSCORE_ID(String score_id) {
		SCORE_ID = score_id;
	}
	public String getSCORE_NAME() {
		return SCORE_NAME;
	}
	public void setSCORE_NAME(String score_name) {
		SCORE_NAME = score_name;
	}
	public String getSCORE_DATE() {
		return SCORE_DATE;
	}
	public void setSCORE_DATE(String score_date) {
		SCORE_DATE = score_date;
	}
	public String getSCORE_TOTAL() {
		return SCORE_TOTAL;
	}
	public void setSCORE_TOTAL(String score_total) {
		SCORE_TOTAL = score_total;
	}
	  
}
