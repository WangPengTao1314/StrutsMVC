/**
 * prjName:喜临门营销平台
 * ucName:推广费用申请单维护
 * fileName:PromoexpenModel
*/
package com.hoperun.drp.oamg.promoexpen.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-24 10:59:55
 */
public class PromoexpenModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/** 推广费用申请单ID **/
   private String PRMT_COST_REQ_ID;
   /** 推广费用申请单编号 **/
   private String PRMT_COST_REQ_NO;
   /** 申请人ID **/
   private String REQ_ID;
   /** 申请人姓名 **/
   private String REQ_NAME;
   /** 渠道ID **/
   private String CHANN_ID;
   /** 渠道编号 **/
   private String CHANN_NO;
   /** 渠道名称 **/
   private String CHANN_NAME;
   /** 申请时间 **/
   private String REQ_DATE;
   /**渠道联系人**/
   private String CHANN_PERSON_CON;
   /** 渠道电话 **/
   private String CHANN_TEL;
   /** 区域ID **/
   private String AREA_ID;
   /** 区域编号 **/
   private String AREA_NO;
   /** 区域名称 **/
   private String AREA_NAME;
   /** 区域经理ID **/
   private String AREA_MANAGE_ID;
   /** 区域经理名称 **/
   private String AREA_MANAGE_NAME;
   /** 区域经理电话 **/
   private String AREA_MANAGE_TEL;
   /** 申请原因 **/
   private String REQ_REMARK;
   /** 此次推广费用共计 **/
   private String TOTAL_PRMT_COST;
   /** 申请报销金额 **/
   private String REIT_REQ_AMOUNT;
   /** 费用类别 **/
   private String COST_TYPE;
   /** 加盟商本季度累计已发货 **/
   private String TOTAL_SEND_AMOUNT;
   /** 加盟商本季度累计预算 **/
   private String TOTAL_BUDGET_AMOUNT;
   /** 加盟商本季度累计已发货费用 **/
   private String TOTAL_SEND_COST_AMOUNT;
   /** 加盟商本季度累计发货预算费用 **/
   private String TOTAL_SEND_BUDGET_AMOUNT;
   /** 加盟商本季度申请费用 **/
   private String REQ_COST_AMOUNT;
   /** 加盟商本季度发货剩余费用 **/
   private String TOTAL_SEND_LEFT_AMOUNT;
   /** 加盟商本季度预算剩余费用 **/
   private String TOTAL_BUDGET_LEFT_AMOUNT;
   /** 加盟商本季度发货费效比 **/
   private String SEND_COST_RATE;
   /** 加盟商本季度预算费效比 **/
   private String SEND_BUDGET_RATE;
   /** 所属战区本季度累计已发货 **/
   private String TOTAL_AREA_SEND_AMOUNT;
   /** 所属战区本季度累计预算 **/
   private String TOTAL_AREA_BUDGET_AMOUNT;
   /** 所属战区本季度累计已发货费用 **/
   private String TOTAL_AREA_SEND_COST_AMOUNT;
   /** 所属战区本季度累计发货预算费用 **/
   private String TOTAL_AREA_SEND_BUDGET_AMOUNT;
   /** 所属战区本季度申请费用 **/
   private String AREA_REQ_COST_AMOUNT;
   /** 所属战区本季度发货剩余费用 **/
   private String TOTAL_AREA_SEND_LEFT_AMOUNT;
   /** 所属战区本季度预算剩余费用 **/
   private String TOTAL_AREA_BUDGET_LEFT_AMOUNT;
   /** 所属战区本季度发货费效比 **/
   private String AREA_SEND_COST_RATE;
   /** 所属战区本季度预算费效比 **/
   private String AREA_SEND_BUDGET_RATE;
   /** 备注 **/
   private String REMARK;
   /** 审核人ID **/
   private String AUDIT_ID;
   /** 审核人姓名 **/
   private String AUDIT_NAME;
   /** 审核时间 **/
   private String AUDIT_TIME;
   /** 制单人名称 **/
   private String CRE_NAME;
   /** 制单人ID **/
   private String CREATOR;
   /** 制单时间 **/
   private String CRE_TIME;
   /** 更新人名称 **/
   private String UPD_NAME;
   /** 更新人ID **/
   private String UPDATOR;
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
   /** 帐套ID **/
   private String LEDGER_ID;
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 行政区划ID **/
   private String ZONE_ID;
   /** 行政区划地址 **/
   private String ZONE_ADDR;
   /** 附件 **/
   private String STATENEBTS_ATT;
   private String  CHUHUO;
   private String  YUPI;
   private String  AREAYUSUAN;
   private String  AREAYUPI;
   private String  WAREYUSUAN;
   private String  WAREYUPI;
   
   private String TOTAL_REAL_AMOUNT;
   private String TOTAL_ADVC_AMOUNT;
   private String TOTAL_RATE;
   private String AREA_TOTAL_BUDGET_AMOUNT;
   private String AREA_TOTAL_PRE_AMOUNT;
   private String AREA_TOTAL_AVALIABLE_AMOUNT;
   private String WARE_TOTAL_BUDGET_AMOUNT;
   private String WARE_TOTAL_PRE_AMOUNT;
   private String WARE_TOTAL_AVALIABLE_AMOUNT;
   private String PRMT_PLAN_ID;
   private String PRMT_PLAN_NO;
   private String PRMT_PLAN_NAME;
   private String PRMT_TYPE;
   private String DGET_ITEM_ID;
   private String BUDGET_ITEM_ID;
   private String BUDGET_ITEM_NO;
   private String BUDGET_ITEM_NAME;
   private String BUDGET_ITEM_TYPE;
   private String CITY_NAME;
   private String CITY_LVL;
   private String REQ_MAKE;
   private String BUDGET_AMOUNT;
   private String BUDGET_QUOTA_ID;
   
   private String RETAIL_AMOUNT;
   private String BILL_AMOUNT;
   
   private String ACTION_PATH;
   private String BUDGET_PATH;
   private String AGREE_PATH;
   /** 是否制作VI画面 **/
   private String PRO_SCREEN;
   
   
public String getACTION_PATH() {
	return ACTION_PATH;
}
public void setACTION_PATH(String action_path) {
	ACTION_PATH = action_path;
}
public String getPRO_SCREEN() {
	return PRO_SCREEN;
}
public void setPRO_SCREEN(String pro_screen) {
	PRO_SCREEN = pro_screen;
}
public String getBUDGET_PATH() {
	return BUDGET_PATH;
}
public void setBUDGET_PATH(String budget_path) {
	BUDGET_PATH = budget_path;
}
public String getAGREE_PATH() {
	return AGREE_PATH;
}
public void setAGREE_PATH(String agree_path) {
	AGREE_PATH = agree_path;
}
public String getRETAIL_AMOUNT() {
	return RETAIL_AMOUNT;
}
public void setRETAIL_AMOUNT(String retail_amount) {
	RETAIL_AMOUNT = retail_amount;
}
public String getBILL_AMOUNT() {
	return BILL_AMOUNT;
}
public void setBILL_AMOUNT(String bill_amount) {
	BILL_AMOUNT = bill_amount;
}
	public String getTOTAL_REAL_AMOUNT() {
	return TOTAL_REAL_AMOUNT;
}
public void setTOTAL_REAL_AMOUNT(String total_real_amount) {
	TOTAL_REAL_AMOUNT = total_real_amount;
}
public String getTOTAL_ADVC_AMOUNT() {
	return TOTAL_ADVC_AMOUNT;
}
public void setTOTAL_ADVC_AMOUNT(String total_advc_amount) {
	TOTAL_ADVC_AMOUNT = total_advc_amount;
}
public String getTOTAL_RATE() {
	return TOTAL_RATE;
}
public void setTOTAL_RATE(String total_rate) {
	TOTAL_RATE = total_rate;
}
public String getAREA_TOTAL_BUDGET_AMOUNT() {
	return AREA_TOTAL_BUDGET_AMOUNT;
}
public void setAREA_TOTAL_BUDGET_AMOUNT(String area_total_budget_amount) {
	AREA_TOTAL_BUDGET_AMOUNT = area_total_budget_amount;
}
public String getAREA_TOTAL_PRE_AMOUNT() {
	return AREA_TOTAL_PRE_AMOUNT;
}
public void setAREA_TOTAL_PRE_AMOUNT(String area_total_pre_amount) {
	AREA_TOTAL_PRE_AMOUNT = area_total_pre_amount;
}
public String getAREA_TOTAL_AVALIABLE_AMOUNT() {
	return AREA_TOTAL_AVALIABLE_AMOUNT;
}
public void setAREA_TOTAL_AVALIABLE_AMOUNT(String area_total_avaliable_amount) {
	AREA_TOTAL_AVALIABLE_AMOUNT = area_total_avaliable_amount;
}
public String getWARE_TOTAL_BUDGET_AMOUNT() {
	return WARE_TOTAL_BUDGET_AMOUNT;
}
public void setWARE_TOTAL_BUDGET_AMOUNT(String ware_total_budget_amount) {
	WARE_TOTAL_BUDGET_AMOUNT = ware_total_budget_amount;
}
public String getWARE_TOTAL_PRE_AMOUNT() {
	return WARE_TOTAL_PRE_AMOUNT;
}
public void setWARE_TOTAL_PRE_AMOUNT(String ware_total_pre_amount) {
	WARE_TOTAL_PRE_AMOUNT = ware_total_pre_amount;
}
public String getWARE_TOTAL_AVALIABLE_AMOUNT() {
	return WARE_TOTAL_AVALIABLE_AMOUNT;
}
public void setWARE_TOTAL_AVALIABLE_AMOUNT(String ware_total_avaliable_amount) {
	WARE_TOTAL_AVALIABLE_AMOUNT = ware_total_avaliable_amount;
}
public String getPRMT_PLAN_ID() {
	return PRMT_PLAN_ID;
}
public void setPRMT_PLAN_ID(String prmt_plan_id) {
	PRMT_PLAN_ID = prmt_plan_id;
}
public String getPRMT_PLAN_NO() {
	return PRMT_PLAN_NO;
}
public void setPRMT_PLAN_NO(String prmt_plan_no) {
	PRMT_PLAN_NO = prmt_plan_no;
}
public String getPRMT_PLAN_NAME() {
	return PRMT_PLAN_NAME;
}
public void setPRMT_PLAN_NAME(String prmt_plan_name) {
	PRMT_PLAN_NAME = prmt_plan_name;
}
public String getPRMT_TYPE() {
	return PRMT_TYPE;
}
public void setPRMT_TYPE(String prmt_type) {
	PRMT_TYPE = prmt_type;
}
public String getDGET_ITEM_ID() {
	return DGET_ITEM_ID;
}
public void setDGET_ITEM_ID(String dget_item_id) {
	DGET_ITEM_ID = dget_item_id;
}
public String getBUDGET_ITEM_NO() {
	return BUDGET_ITEM_NO;
}
public void setBUDGET_ITEM_NO(String budget_item_no) {
	BUDGET_ITEM_NO = budget_item_no;
}
public String getBUDGET_ITEM_NAME() {
	return BUDGET_ITEM_NAME;
}
public void setBUDGET_ITEM_NAME(String budget_item_name) {
	BUDGET_ITEM_NAME = budget_item_name;
}
public String getBUDGET_ITEM_TYPE() {
	return BUDGET_ITEM_TYPE;
}
public void setBUDGET_ITEM_TYPE(String budget_item_type) {
	BUDGET_ITEM_TYPE = budget_item_type;
}
public String getCITY_NAME() {
	return CITY_NAME;
}
public void setCITY_NAME(String city_name) {
	CITY_NAME = city_name;
}
public String getCITY_LVL() {
	return CITY_LVL;
}
public void setCITY_LVL(String city_lvl) {
	CITY_LVL = city_lvl;
}
public String getREQ_MAKE() {
	return REQ_MAKE;
}
public void setREQ_MAKE(String req_make) {
	REQ_MAKE = req_make;
}
public String getBUDGET_AMOUNT() {
	return BUDGET_AMOUNT;
}
public void setBUDGET_AMOUNT(String budget_amount) {
	BUDGET_AMOUNT = budget_amount;
}
	/**
     * get 推广费用申请单ID value
     * @return the PRMT_COST_REQ_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getPRMT_COST_REQ_ID(){
        return PRMT_COST_REQ_ID;
    }
	/**
     * set 推广费用申请单ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setPRMT_COST_REQ_ID(String PRMT_COST_REQ_ID){
        this.PRMT_COST_REQ_ID=PRMT_COST_REQ_ID;
    }
     /**
     * get 推广费用申请单编号 value
     * @return the PRMT_COST_REQ_NO
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getPRMT_COST_REQ_NO(){
        return PRMT_COST_REQ_NO;
    }
	/**
     * set 推广费用申请单编号 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setPRMT_COST_REQ_NO(String PRMT_COST_REQ_NO){
        this.PRMT_COST_REQ_NO=PRMT_COST_REQ_NO;
    }
     /**
     * get 申请人ID value
     * @return the REQ_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREQ_ID(){
        return REQ_ID;
    }
	/**
     * set 申请人ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREQ_ID(String REQ_ID){
        this.REQ_ID=REQ_ID;
    }
     /**
     * get 申请人姓名 value
     * @return the REQ_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREQ_NAME(){
        return REQ_NAME;
    }
	/**
     * set 申请人姓名 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREQ_NAME(String REQ_NAME){
        this.REQ_NAME=REQ_NAME;
    }
     /**
     * get 渠道ID value
     * @return the CHANN_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 渠道ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 渠道编号 value
     * @return the CHANN_NO
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 渠道编号 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 渠道名称 value
     * @return the CHANN_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 渠道名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 申请时间 value
     * @return the REQ_DATE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREQ_DATE(){
        return REQ_DATE;
    }
	/**
     * set 申请时间 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREQ_DATE(String REQ_DATE){
        this.REQ_DATE=REQ_DATE;
    }
     /**
     * get 渠道电话 value
     * @return the CHANN_TEL
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCHANN_TEL(){
        return CHANN_TEL;
    }
	/**
     * set 渠道电话 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCHANN_TEL(String CHANN_TEL){
        this.CHANN_TEL=CHANN_TEL;
    }
     /**
     * get 区域ID value
     * @return the AREA_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_ID(){
        return AREA_ID;
    }
	/**
     * set 区域ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_ID(String AREA_ID){
        this.AREA_ID=AREA_ID;
    }
     /**
     * get 区域编号 value
     * @return the AREA_NO
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_NO(){
        return AREA_NO;
    }
	/**
     * set 区域编号 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_NO(String AREA_NO){
        this.AREA_NO=AREA_NO;
    }
     /**
     * get 区域名称 value
     * @return the AREA_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_NAME(){
        return AREA_NAME;
    }
	/**
     * set 区域名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_NAME(String AREA_NAME){
        this.AREA_NAME=AREA_NAME;
    }
     /**
     * get 区域经理ID value
     * @return the AREA_MANAGE_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_MANAGE_ID(){
        return AREA_MANAGE_ID;
    }
	/**
     * set 区域经理ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_MANAGE_ID(String AREA_MANAGE_ID){
        this.AREA_MANAGE_ID=AREA_MANAGE_ID;
    }
     /**
     * get 区域经理名称 value
     * @return the AREA_MANAGE_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_MANAGE_NAME(){
        return AREA_MANAGE_NAME;
    }
	/**
     * set 区域经理名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_MANAGE_NAME(String AREA_MANAGE_NAME){
        this.AREA_MANAGE_NAME=AREA_MANAGE_NAME;
    }
     /**
     * get 区域经理电话 value
     * @return the AREA_MANAGE_TEL
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_MANAGE_TEL(){
        return AREA_MANAGE_TEL;
    }
	/**
     * set 区域经理电话 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_MANAGE_TEL(String AREA_MANAGE_TEL){
        this.AREA_MANAGE_TEL=AREA_MANAGE_TEL;
    }
     /**
     * get 申请原因 value
     * @return the REQ_REMARK
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREQ_REMARK(){
        return REQ_REMARK;
    }
	/**
     * set 申请原因 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREQ_REMARK(String REQ_REMARK){
        this.REQ_REMARK=REQ_REMARK;
    }
     /**
     * get 此次推广费用共计 value
     * @return the TOTAL_PRMT_COST
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_PRMT_COST(){
        return TOTAL_PRMT_COST;
    }
	/**
     * set 此次推广费用共计 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_PRMT_COST(String TOTAL_PRMT_COST){
        this.TOTAL_PRMT_COST=TOTAL_PRMT_COST;
    }
     /**
     * get 申请报销金额 value
     * @return the REIT_REQ_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREIT_REQ_AMOUNT(){
        return REIT_REQ_AMOUNT;
    }
	/**
     * set 申请报销金额 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREIT_REQ_AMOUNT(String REIT_REQ_AMOUNT){
        this.REIT_REQ_AMOUNT=REIT_REQ_AMOUNT;
    }
     /**
     * get 费用类别 value
     * @return the COST_TYPE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCOST_TYPE(){
        return COST_TYPE;
    }
	/**
     * set 费用类别 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCOST_TYPE(String COST_TYPE){
        this.COST_TYPE=COST_TYPE;
    }
     /**
     * get 加盟商本季度累计已发货 value
     * @return the TOTAL_SEND_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_SEND_AMOUNT(){
        return TOTAL_SEND_AMOUNT;
    }
	/**
     * set 加盟商本季度累计已发货 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_SEND_AMOUNT(String TOTAL_SEND_AMOUNT){
        this.TOTAL_SEND_AMOUNT=TOTAL_SEND_AMOUNT;
    }
     /**
     * get 加盟商本季度累计预算 value
     * @return the TOTAL_BUDGET_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_BUDGET_AMOUNT(){
        return TOTAL_BUDGET_AMOUNT;
    }
	/**
     * set 加盟商本季度累计预算 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_BUDGET_AMOUNT(String TOTAL_BUDGET_AMOUNT){
        this.TOTAL_BUDGET_AMOUNT=TOTAL_BUDGET_AMOUNT;
    }
     /**
     * get 加盟商本季度累计已发货费用 value
     * @return the TOTAL_SEND_COST_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_SEND_COST_AMOUNT(){
        return TOTAL_SEND_COST_AMOUNT;
    }
	/**
     * set 加盟商本季度累计已发货费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_SEND_COST_AMOUNT(String TOTAL_SEND_COST_AMOUNT){
        this.TOTAL_SEND_COST_AMOUNT=TOTAL_SEND_COST_AMOUNT;
    }
     /**
     * get 加盟商本季度累计发货预算费用 value
     * @return the TOTAL_SEND_BUDGET_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_SEND_BUDGET_AMOUNT(){
        return TOTAL_SEND_BUDGET_AMOUNT;
    }
	/**
     * set 加盟商本季度累计发货预算费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_SEND_BUDGET_AMOUNT(String TOTAL_SEND_BUDGET_AMOUNT){
        this.TOTAL_SEND_BUDGET_AMOUNT=TOTAL_SEND_BUDGET_AMOUNT;
    }
     /**
     * get 加盟商本季度申请费用 value
     * @return the REQ_COST_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREQ_COST_AMOUNT(){
        return REQ_COST_AMOUNT;
    }
	/**
     * set 加盟商本季度申请费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREQ_COST_AMOUNT(String REQ_COST_AMOUNT){
        this.REQ_COST_AMOUNT=REQ_COST_AMOUNT;
    }
     /**
     * get 加盟商本季度发货剩余费用 value
     * @return the TOTAL_SEND_LEFT_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_SEND_LEFT_AMOUNT(){
        return TOTAL_SEND_LEFT_AMOUNT;
    }
	/**
     * set 加盟商本季度发货剩余费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_SEND_LEFT_AMOUNT(String TOTAL_SEND_LEFT_AMOUNT){
        this.TOTAL_SEND_LEFT_AMOUNT=TOTAL_SEND_LEFT_AMOUNT;
    }
     /**
     * get 加盟商本季度预算剩余费用 value
     * @return the TOTAL_BUDGET_LEFT_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_BUDGET_LEFT_AMOUNT(){
        return TOTAL_BUDGET_LEFT_AMOUNT;
    }
	/**
     * set 加盟商本季度预算剩余费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_BUDGET_LEFT_AMOUNT(String TOTAL_BUDGET_LEFT_AMOUNT){
        this.TOTAL_BUDGET_LEFT_AMOUNT=TOTAL_BUDGET_LEFT_AMOUNT;
    }
     /**
     * get 加盟商本季度发货费效比 value
     * @return the SEND_COST_RATE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getSEND_COST_RATE(){
        return SEND_COST_RATE;
    }
	/**
     * set 加盟商本季度发货费效比 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setSEND_COST_RATE(String SEND_COST_RATE){
        this.SEND_COST_RATE=SEND_COST_RATE;
    }
     /**
     * get 加盟商本季度预算费效比 value
     * @return the SEND_BUDGET_RATE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getSEND_BUDGET_RATE(){
        return SEND_BUDGET_RATE;
    }
	/**
     * set 加盟商本季度预算费效比 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setSEND_BUDGET_RATE(String SEND_BUDGET_RATE){
        this.SEND_BUDGET_RATE=SEND_BUDGET_RATE;
    }
     /**
     * get 所属战区本季度累计已发货 value
     * @return the TOTAL_AREA_SEND_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_AREA_SEND_AMOUNT(){
        return TOTAL_AREA_SEND_AMOUNT;
    }
	/**
     * set 所属战区本季度累计已发货 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_AREA_SEND_AMOUNT(String TOTAL_AREA_SEND_AMOUNT){
        this.TOTAL_AREA_SEND_AMOUNT=TOTAL_AREA_SEND_AMOUNT;
    }
     /**
     * get 所属战区本季度累计预算 value
     * @return the TOTAL_AREA_BUDGET_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_AREA_BUDGET_AMOUNT(){
        return TOTAL_AREA_BUDGET_AMOUNT;
    }
	/**
     * set 所属战区本季度累计预算 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_AREA_BUDGET_AMOUNT(String TOTAL_AREA_BUDGET_AMOUNT){
        this.TOTAL_AREA_BUDGET_AMOUNT=TOTAL_AREA_BUDGET_AMOUNT;
    }
     /**
     * get 所属战区本季度累计已发货费用 value
     * @return the TOTAL_AREA_SEND_COST_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_AREA_SEND_COST_AMOUNT(){
        return TOTAL_AREA_SEND_COST_AMOUNT;
    }
	/**
     * set 所属战区本季度累计已发货费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_AREA_SEND_COST_AMOUNT(String TOTAL_AREA_SEND_COST_AMOUNT){
        this.TOTAL_AREA_SEND_COST_AMOUNT=TOTAL_AREA_SEND_COST_AMOUNT;
    }
     /**
     * get 所属战区本季度累计发货预算费用 value
     * @return the TOTAL_AREA_SEND_BUDGET_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_AREA_SEND_BUDGET_AMOUNT(){
        return TOTAL_AREA_SEND_BUDGET_AMOUNT;
    }
	/**
     * set 所属战区本季度累计发货预算费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_AREA_SEND_BUDGET_AMOUNT(String TOTAL_AREA_SEND_BUDGET_AMOUNT){
        this.TOTAL_AREA_SEND_BUDGET_AMOUNT=TOTAL_AREA_SEND_BUDGET_AMOUNT;
    }
     /**
     * get 所属战区本季度申请费用 value
     * @return the AREA_REQ_COST_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_REQ_COST_AMOUNT(){
        return AREA_REQ_COST_AMOUNT;
    }
	/**
     * set 所属战区本季度申请费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_REQ_COST_AMOUNT(String AREA_REQ_COST_AMOUNT){
        this.AREA_REQ_COST_AMOUNT=AREA_REQ_COST_AMOUNT;
    }
     /**
     * get 所属战区本季度发货剩余费用 value
     * @return the TOTAL_AREA_SEND_LEFT_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_AREA_SEND_LEFT_AMOUNT(){
        return TOTAL_AREA_SEND_LEFT_AMOUNT;
    }
	/**
     * set 所属战区本季度发货剩余费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_AREA_SEND_LEFT_AMOUNT(String TOTAL_AREA_SEND_LEFT_AMOUNT){
        this.TOTAL_AREA_SEND_LEFT_AMOUNT=TOTAL_AREA_SEND_LEFT_AMOUNT;
    }
     /**
     * get 所属战区本季度预算剩余费用 value
     * @return the TOTAL_AREA_BUDGET_LEFT_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getTOTAL_AREA_BUDGET_LEFT_AMOUNT(){
        return TOTAL_AREA_BUDGET_LEFT_AMOUNT;
    }
	/**
     * set 所属战区本季度预算剩余费用 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setTOTAL_AREA_BUDGET_LEFT_AMOUNT(String TOTAL_AREA_BUDGET_LEFT_AMOUNT){
        this.TOTAL_AREA_BUDGET_LEFT_AMOUNT=TOTAL_AREA_BUDGET_LEFT_AMOUNT;
    }
     /**
     * get 所属战区本季度发货费效比 value
     * @return the AREA_SEND_COST_RATE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_SEND_COST_RATE(){
        return AREA_SEND_COST_RATE;
    }
	/**
     * set 所属战区本季度发货费效比 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_SEND_COST_RATE(String AREA_SEND_COST_RATE){
        this.AREA_SEND_COST_RATE=AREA_SEND_COST_RATE;
    }
     /**
     * get 所属战区本季度预算费效比 value
     * @return the AREA_SEND_BUDGET_RATE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAREA_SEND_BUDGET_RATE(){
        return AREA_SEND_BUDGET_RATE;
    }
	/**
     * set 所属战区本季度预算费效比 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAREA_SEND_BUDGET_RATE(String AREA_SEND_BUDGET_RATE){
        this.AREA_SEND_BUDGET_RATE=AREA_SEND_BUDGET_RATE;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDIT_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-01-24 10:59:55
     * @author chenj
	 * @createdate 2014-01-24 10:59:55
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getZONE_ID() {
		return ZONE_ID;
	}
	public void setZONE_ID(String zone_id) {
		ZONE_ID = zone_id;
	}
	public String getZONE_ADDR() {
		return ZONE_ADDR;
	}
	public void setZONE_ADDR(String zone_addr) {
		ZONE_ADDR = zone_addr;
	}
	public String getCHANN_PERSON_CON() {
		return CHANN_PERSON_CON;
	}
	public void setCHANN_PERSON_CON(String chann_person_con) {
		CHANN_PERSON_CON = chann_person_con;
	}
	public String getSTATENEBTS_ATT() {
		return STATENEBTS_ATT;
	}
	public void setSTATENEBTS_ATT(String statenebts_att) {
		STATENEBTS_ATT = statenebts_att;
	}
	public String getBUDGET_ITEM_ID() {
		return BUDGET_ITEM_ID;
	}
	public void setBUDGET_ITEM_ID(String budget_item_id) {
		BUDGET_ITEM_ID = budget_item_id;
	}
	public String getBUDGET_QUOTA_ID() {
		return BUDGET_QUOTA_ID;
	}
	public void setBUDGET_QUOTA_ID(String budget_quota_id) {
		BUDGET_QUOTA_ID = budget_quota_id;
	}
	public String getCHUHUO() {
		return CHUHUO;
	}
	public void setCHUHUO(String chuhuo) {
		CHUHUO = chuhuo;
	}
	public String getYUPI() {
		return YUPI;
	}
	public void setYUPI(String yupi) {
		YUPI = yupi;
	}
	public String getAREAYUSUAN() {
		return AREAYUSUAN;
	}
	public void setAREAYUSUAN(String areayusuan) {
		AREAYUSUAN = areayusuan;
	}
	public String getAREAYUPI() {
		return AREAYUPI;
	}
	public void setAREAYUPI(String areayupi) {
		AREAYUPI = areayupi;
	}
	public String getWAREYUSUAN() {
		return WAREYUSUAN;
	}
	public void setWAREYUSUAN(String wareyusuan) {
		WAREYUSUAN = wareyusuan;
	}
	public String getWAREYUPI() {
		return WAREYUPI;
	}
	public void setWAREYUPI(String wareyupi) {
		WAREYUPI = wareyupi;
	}
}