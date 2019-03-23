package com.hoperun.drp.oamg.mkmdayreport.model;

import com.hoperun.commons.model.BaseModel;

public class ShoppGuideTranModel   extends BaseModel  {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**导购员培训ID**/
	 private  String  SHOPP_GUIDE_TRAN_ID;
	 /**营销经理日报表ID**/
	 private  String  MKM_DAY_RPT_ID;
	 /**培训人数**/
	 private  String  TRAN_NUM;
	 /**培训主题**/
	 private  String  TRAN_TITLE;
	 /**培训形式**/
	 private  String  TRAN_TYPE;
	 /**培训时间**/
	 private  String  TRAN_DATE;
	 /**培训照片**/
	 private  String  TRAN_PIC;
	public String getSHOPP_GUIDE_TRAN_ID() {
		return SHOPP_GUIDE_TRAN_ID;
	}
	public void setSHOPP_GUIDE_TRAN_ID(String shopp_guide_tran_id) {
		SHOPP_GUIDE_TRAN_ID = shopp_guide_tran_id;
	}
	public String getMKM_DAY_RPT_ID() {
		return MKM_DAY_RPT_ID;
	}
	public void setMKM_DAY_RPT_ID(String mkm_day_rpt_id) {
		MKM_DAY_RPT_ID = mkm_day_rpt_id;
	}
	public String getTRAN_NUM() {
		return TRAN_NUM;
	}
	public void setTRAN_NUM(String tran_num) {
		TRAN_NUM = tran_num;
	}
	public String getTRAN_TITLE() {
		return TRAN_TITLE;
	}
	public void setTRAN_TITLE(String tran_title) {
		TRAN_TITLE = tran_title;
	}
	public String getTRAN_TYPE() {
		return TRAN_TYPE;
	}
	public void setTRAN_TYPE(String tran_type) {
		TRAN_TYPE = tran_type;
	}
	public String getTRAN_DATE() {
		return TRAN_DATE;
	}
	public void setTRAN_DATE(String tran_date) {
		TRAN_DATE = tran_date;
	}
	public String getTRAN_PIC() {
		return TRAN_PIC;
	}
	public void setTRAN_PIC(String tran_pic) {
		TRAN_PIC = tran_pic;
	}

}
