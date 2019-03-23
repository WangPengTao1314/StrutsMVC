/**
 * prjName:喜临门营销平台
 * ucName:广告投放申请单维护
 * fileName:AdvreqModel
*/
package com.hoperun.drp.adv.advreq.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-07-15
 */
public class AdvreqModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/** 广告投放申请单ID **/
   private String ERP_ADV_REQ_ID;
   /** 广告投放申请单号 **/
   private String ERP_ADV_REQ_NO;
   /** 广告投放类型 **/
   private String ADV_TYPE;
   /** 渠道ID **/
   private String CHANN_ID;
   /** 渠道编号 **/
   private String CHANN_NO;
   /** 渠道名称 **/
   private String CHANN_NAME;
   /** 区域ID **/
   private String AREA_ID;
   /** 区域编号 **/
   private String AREA_NO;
   /** 区域名称 **/
   private String AREA_NAME;
   /** 区域经理ID **/
   private String AREA_MANAGE_ID;
   private String AREA_MANAGE_NAME;   
   /** 渠道排名 **/
   private String CHANN_RANK;
   /** 广告公司名称 **/
   private String ADV_CO_NAME;
   /** 广告公司联系人 **/
   private String ADV_CO_CONTACT;
   /** 广告公司联系电话 **/
   private String ADV_CO_TEL;
   /** 广告投放内容 **/
   private String ADV_CONTENT;
   /** 广告投放地点 **/
   private String ADV_ADDR;
   /** 广告投放开始时间 **/
   private String ADV_START_DATE;
   /** 广告投放结束时间 **/
   private String ADV_END_DATE;
   /** 广告投放总预算金额 **/
   private String ADV_TOTAL_AMOUNT;
   /** 向总部申请支持费用 **/
   private String HEAD_SUP_AMOUNT;
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
   
   private String RANK_SCALE;
   
   private String PIC_PATH; //图片路径
   
   public String getPIC_PATH() {
		return PIC_PATH;
	}
	public void setPIC_PATH(String pICPATH) {
		PIC_PATH = pICPATH;
	}
	
	public String getRANK_SCALE() {
		return RANK_SCALE;
	}
	public void setRANK_SCALE(String rANKSCALE) {
		RANK_SCALE = rANKSCALE;
	}
   
     /**
     * get 广告投放申请单ID value
     * @return the ERP_ADV_REQ_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getERP_ADV_REQ_ID(){
        return ERP_ADV_REQ_ID;
    }
	/**
     * set 广告投放申请单ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setERP_ADV_REQ_ID(String ERP_ADV_REQ_ID){
        this.ERP_ADV_REQ_ID=ERP_ADV_REQ_ID;
    }
     /**
     * get 广告投放申请单号 value
     * @return the ERP_ADV_REQ_NO
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getERP_ADV_REQ_NO(){
        return ERP_ADV_REQ_NO;
    }
	/**
     * set 广告投放申请单号 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setERP_ADV_REQ_NO(String ERP_ADV_REQ_NO){
        this.ERP_ADV_REQ_NO=ERP_ADV_REQ_NO;
    }
     /**
     * get 广告投放类型 value
     * @return the ADV_TYPE
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_TYPE(){
        return ADV_TYPE;
    }
	/**
     * set 广告投放类型 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_TYPE(String ADV_TYPE){
        this.ADV_TYPE=ADV_TYPE;
    }
     /**
     * get 渠道ID value
     * @return the CHANN_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 渠道ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 渠道编号 value
     * @return the CHANN_NO
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 渠道编号 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 渠道名称 value
     * @return the CHANN_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 渠道名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 区域ID value
     * @return the AREA_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAREA_ID(){
        return AREA_ID;
    }
	/**
     * set 区域ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAREA_ID(String AREA_ID){
        this.AREA_ID=AREA_ID;
    }
     /**
     * get 区域编号 value
     * @return the AREA_NO
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAREA_NO(){
        return AREA_NO;
    }
	/**
     * set 区域编号 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAREA_NO(String AREA_NO){
        this.AREA_NO=AREA_NO;
    }
     /**
     * get 区域名称 value
     * @return the AREA_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAREA_NAME(){
        return AREA_NAME;
    }
	/**
     * set 区域名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAREA_NAME(String AREA_NAME){
        this.AREA_NAME=AREA_NAME;
    }
     /**
     * get 区域经理ID value
     * @return the AREA_MANAGE_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAREA_MANAGE_ID(){
        return AREA_MANAGE_ID;
    }
	/**
     * set 区域经理ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAREA_MANAGE_ID(String AREA_MANAGE_ID){
        this.AREA_MANAGE_ID=AREA_MANAGE_ID;
    }
     /**
     * get 渠道排名 value
     * @return the CHANN_RANK
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCHANN_RANK(){
        return CHANN_RANK;
    }
	/**
     * set 渠道排名 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCHANN_RANK(String CHANN_RANK){
        this.CHANN_RANK=CHANN_RANK;
    }
     /**
     * get 广告公司名称 value
     * @return the ADV_CO_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_CO_NAME(){
        return ADV_CO_NAME;
    }
	/**
     * set 广告公司名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_CO_NAME(String ADV_CO_NAME){
        this.ADV_CO_NAME=ADV_CO_NAME;
    }
     /**
     * get 广告公司联系人 value
     * @return the ADV_CO_CONTACT
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_CO_CONTACT(){
        return ADV_CO_CONTACT;
    }
	/**
     * set 广告公司联系人 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_CO_CONTACT(String ADV_CO_CONTACT){
        this.ADV_CO_CONTACT=ADV_CO_CONTACT;
    }
     /**
     * get 广告公司联系电话 value
     * @return the ADV_CO_TEL
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_CO_TEL(){
        return ADV_CO_TEL;
    }
	/**
     * set 广告公司联系电话 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_CO_TEL(String ADV_CO_TEL){
        this.ADV_CO_TEL=ADV_CO_TEL;
    }
     /**
     * get 广告投放内容 value
     * @return the ADV_CONTENT
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_CONTENT(){
        return ADV_CONTENT;
    }
	/**
     * set 广告投放内容 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_CONTENT(String ADV_CONTENT){
        this.ADV_CONTENT=ADV_CONTENT;
    }
     /**
     * get 广告投放地点 value
     * @return the ADV_ADDR
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_ADDR(){
        return ADV_ADDR;
    }
	/**
     * set 广告投放地点 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_ADDR(String ADV_ADDR){
        this.ADV_ADDR=ADV_ADDR;
    }
     /**
     * get 广告投放开始时间 value
     * @return the ADV_START_DATE
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_START_DATE(){
        return ADV_START_DATE;
    }
	/**
     * set 广告投放开始时间 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_START_DATE(String ADV_START_DATE){
        this.ADV_START_DATE=ADV_START_DATE;
    }
     /**
     * get 广告投放结束时间 value
     * @return the ADV_END_DATE
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_END_DATE(){
        return ADV_END_DATE;
    }
	/**
     * set 广告投放结束时间 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_END_DATE(String ADV_END_DATE){
        this.ADV_END_DATE=ADV_END_DATE;
    }
     /**
     * get 广告投放总预算金额 value
     * @return the ADV_TOTAL_AMOUNT
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getADV_TOTAL_AMOUNT(){
        return ADV_TOTAL_AMOUNT;
    }
	/**
     * set 广告投放总预算金额 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setADV_TOTAL_AMOUNT(String ADV_TOTAL_AMOUNT){
        this.ADV_TOTAL_AMOUNT=ADV_TOTAL_AMOUNT;
    }
     /**
     * get 向总部申请支持费用 value
     * @return the HEAD_SUP_AMOUNT
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getHEAD_SUP_AMOUNT(){
        return HEAD_SUP_AMOUNT;
    }
	/**
     * set 向总部申请支持费用 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setHEAD_SUP_AMOUNT(String HEAD_SUP_AMOUNT){
        this.HEAD_SUP_AMOUNT=HEAD_SUP_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDIT_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-03-07 10:19:11
     * @author wzg
	 * @createdate 2014-03-07 10:19:11
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
    public String getAREA_MANAGE_NAME() {
    	return AREA_MANAGE_NAME;
    }
    public void setAREA_MANAGE_NAME(String aREAMANAGENAME) {
    	AREA_MANAGE_NAME = aREAMANAGENAME;
    }
}