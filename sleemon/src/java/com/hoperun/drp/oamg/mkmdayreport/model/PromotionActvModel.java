package com.hoperun.drp.oamg.mkmdayreport.model;

import com.hoperun.commons.model.BaseModel;

public class PromotionActvModel extends BaseModel  {
   
	/**推广活动推进ID**/;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  String  PROMOTION_ACTV_ID;
	/**营销经理日报表ID**/
	private  String  MKM_DAY_RPT_ID;
	/**活动形式**/
	private  String  ACTV_STYLE; 
	/**活动主题**/
	private  String  ACTV_TITLE;
	/**启动时间**/
	private  String  BEG_DATE;
	/**落地时间**/
	private  String  AGREE_DATE;
	/**落地地点**/
	private  String  AGREE_ADDR;
	/**活动预算费用**/
	private  String  ACTV_PRMT_COST;
	/**预计成交单数**/
	private  String  ADVC_DEAL_BILL_NUM;
	/**预计成交金额**/
	private  String  ADVC_DEAL_BILL_AMOUNT;
	/**活动、推广方案附件**/
	private  String  ACTV_PRMT_ATT;
	/**推进日期**/
	private  String  FORWORD_DATE;
	/**售卡数**/
	private  String  SALE_CARD_NUM;
	/**其他**/
	private  String  OTHER_REMARK;
	/**活动结果售卡数**/
    private  String  SALE_CARD_NUM_END;
    /**签到卡数**/
    private  String  CHECK_CARD_NUM;
    /**预计成交金额**/
    private  String  ADVC_DEAL_AMOUNT;
    /**实际投入费用**/
    private  String  COST_AMOUNT;
    /**成交单数**/
    private  String  DEAL_BILL_NUM;
    
	public String getDEAL_BILL_NUM() {
		return DEAL_BILL_NUM;
	}
	public void setDEAL_BILL_NUM(String deal_bill_num) {
		DEAL_BILL_NUM = deal_bill_num;
	}
	public String getPROMOTION_ACTV_ID() {
		return PROMOTION_ACTV_ID;
	}
	public void setPROMOTION_ACTV_ID(String promotion_actv_id) {
		PROMOTION_ACTV_ID = promotion_actv_id;
	}
	public String getMKM_DAY_RPT_ID() {
		return MKM_DAY_RPT_ID;
	}
	public void setMKM_DAY_RPT_ID(String mkm_day_rpt_id) {
		MKM_DAY_RPT_ID = mkm_day_rpt_id;
	}
	public String getACTV_STYLE() {
		return ACTV_STYLE;
	}
	public void setACTV_STYLE(String actv_style) {
		ACTV_STYLE = actv_style;
	}
	public String getACTV_TITLE() {
		return ACTV_TITLE;
	}
	public void setACTV_TITLE(String actv_title) {
		ACTV_TITLE = actv_title;
	}
	public String getBEG_DATE() {
		return BEG_DATE;
	}
	public void setBEG_DATE(String beg_date) {
		BEG_DATE = beg_date;
	}
	public String getAGREE_DATE() {
		return AGREE_DATE;
	}
	public void setAGREE_DATE(String agree_date) {
		AGREE_DATE = agree_date;
	}
	public String getAGREE_ADDR() {
		return AGREE_ADDR;
	}
	public void setAGREE_ADDR(String agree_addr) {
		AGREE_ADDR = agree_addr;
	}
	public String getACTV_PRMT_COST() {
		return ACTV_PRMT_COST;
	}
	public void setACTV_PRMT_COST(String actv_prmt_cost) {
		ACTV_PRMT_COST = actv_prmt_cost;
	}
	public String getADVC_DEAL_BILL_NUM() {
		return ADVC_DEAL_BILL_NUM;
	}
	public void setADVC_DEAL_BILL_NUM(String advc_deal_bill_num) {
		ADVC_DEAL_BILL_NUM = advc_deal_bill_num;
	}
	public String getADVC_DEAL_BILL_AMOUNT() {
		return ADVC_DEAL_BILL_AMOUNT;
	}
	public void setADVC_DEAL_BILL_AMOUNT(String advc_deal_bill_amount) {
		ADVC_DEAL_BILL_AMOUNT = advc_deal_bill_amount;
	}
	public String getACTV_PRMT_ATT() {
		return ACTV_PRMT_ATT;
	}
	public void setACTV_PRMT_ATT(String actv_prmt_att) {
		ACTV_PRMT_ATT = actv_prmt_att;
	}
	public String getFORWORD_DATE() {
		return FORWORD_DATE;
	}
	public void setFORWORD_DATE(String forword_date) {
		FORWORD_DATE = forword_date;
	}
	public String getSALE_CARD_NUM() {
		return SALE_CARD_NUM;
	}
	public void setSALE_CARD_NUM(String sale_card_num) {
		SALE_CARD_NUM = sale_card_num;
	}
	public String getOTHER_REMARK() {
		return OTHER_REMARK;
	}
	public void setOTHER_REMARK(String other_remark) {
		OTHER_REMARK = other_remark;
	}
	public String getSALE_CARD_NUM_END() {
		return SALE_CARD_NUM_END;
	}
	public void setSALE_CARD_NUM_END(String sale_card_num_end) {
		SALE_CARD_NUM_END = sale_card_num_end;
	}
	public String getCHECK_CARD_NUM() {
		return CHECK_CARD_NUM;
	}
	public void setCHECK_CARD_NUM(String check_card_num) {
		CHECK_CARD_NUM = check_card_num;
	}
	public String getADVC_DEAL_AMOUNT() {
		return ADVC_DEAL_AMOUNT;
	}
	public void setADVC_DEAL_AMOUNT(String advc_deal_amount) {
		ADVC_DEAL_AMOUNT = advc_deal_amount;
	}
	public String getCOST_AMOUNT() {
		return COST_AMOUNT;
	}
	public void setCOST_AMOUNT(String cost_amount) {
		COST_AMOUNT = cost_amount;
	}
}
