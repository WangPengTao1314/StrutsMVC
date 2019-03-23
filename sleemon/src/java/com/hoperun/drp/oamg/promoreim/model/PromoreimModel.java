/**
 * prjName:喜临门营销平台
 * ucName:推广费用报销单维护
 * fileName:PromoreimModel
*/
package com.hoperun.drp.oamg.promoreim.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-25 19:49:05
 */
public class PromoreimModel extends BaseModel{
   /** 加盟商推广费用报销单ID **/
   private String PRMT_COST_REIT_ID;
   /** 加盟商推广费用报销单编号 **/
   private String PRMT_COST_REIT_NO;
   /** 推广费用申请单ID **/
   private String PRMT_COST_REQ_ID;
   /** 推广费用申请单编号 **/
   private String PRMT_COST_REQ_NO;
   /** 渠道ID **/
   private String CHANN_ID;
   /** 渠道编号 **/
   private String CHANN_NO;
   /** 渠道名称 **/
   private String CHANN_NAME;
   /** 渠道联系人 **/
   private String CHANN_PERSON_CON;
   /** 渠道电话 **/
   private String CHANN_TEL;
   /** 行政区划ID **/
   private String ZONE_ID;
   /** 行政区划地址 **/
   private String ZONE_ADDR;
   /** 区域经理ID **/
   private String AREA_MANAGE_ID;
   /** 区域经理名称 **/
   private String AREA_MANAGE_NAME;
   /** 区域经理电话 **/
   private String AREA_MANAGE_TEL;
   /** 申请人ID **/
   private String REQ_ID;
   /** 申请人姓名 **/
   private String REQ_NAME;
   /** 申请时间 **/
   private String REQ_DATE;
   /** 执行总结 **/
   private String EXCT_SMRZ;
   /** 此次推广费用共计 **/
   private String TOTAL_PRMT_COST;
   /** 申请报销金额 **/
   private String REIT_REQ_AMOUNT;
   /** 费用类别 **/
   private String COST_TYPE;
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
   
   /** 附件 **/
   private String STATENEBTS_ATT;
     /**
     * get 加盟商推广费用报销单ID value
     * @return the PRMT_COST_REIT_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getPRMT_COST_REIT_ID(){
        return PRMT_COST_REIT_ID;
    }
	/**
     * set 加盟商推广费用报销单ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setPRMT_COST_REIT_ID(String PRMT_COST_REIT_ID){
        this.PRMT_COST_REIT_ID=PRMT_COST_REIT_ID;
    }
     /**
     * get 加盟商推广费用报销单编号 value
     * @return the PRMT_COST_REIT_NO
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getPRMT_COST_REIT_NO(){
        return PRMT_COST_REIT_NO;
    }
	/**
     * set 加盟商推广费用报销单编号 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setPRMT_COST_REIT_NO(String PRMT_COST_REIT_NO){
        this.PRMT_COST_REIT_NO=PRMT_COST_REIT_NO;
    }
     /**
     * get 推广费用申请单ID value
     * @return the PRMT_COST_REQ_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getPRMT_COST_REQ_ID(){
        return PRMT_COST_REQ_ID;
    }
	/**
     * set 推广费用申请单ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setPRMT_COST_REQ_ID(String PRMT_COST_REQ_ID){
        this.PRMT_COST_REQ_ID=PRMT_COST_REQ_ID;
    }
     /**
     * get 推广费用申请单编号 value
     * @return the PRMT_COST_REQ_NO
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getPRMT_COST_REQ_NO(){
        return PRMT_COST_REQ_NO;
    }
	/**
     * set 推广费用申请单编号 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setPRMT_COST_REQ_NO(String PRMT_COST_REQ_NO){
        this.PRMT_COST_REQ_NO=PRMT_COST_REQ_NO;
    }
     /**
     * get 渠道ID value
     * @return the CHANN_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 渠道ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 渠道编号 value
     * @return the CHANN_NO
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 渠道编号 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 渠道名称 value
     * @return the CHANN_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 渠道名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 渠道联系人 value
     * @return the CHANN_PERSON_CON
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCHANN_PERSON_CON(){
        return CHANN_PERSON_CON;
    }
	/**
     * set 渠道联系人 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCHANN_PERSON_CON(String CHANN_PERSON_CON){
        this.CHANN_PERSON_CON=CHANN_PERSON_CON;
    }
     /**
     * get 渠道电话 value
     * @return the CHANN_TEL
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCHANN_TEL(){
        return CHANN_TEL;
    }
	/**
     * set 渠道电话 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCHANN_TEL(String CHANN_TEL){
        this.CHANN_TEL=CHANN_TEL;
    }
     /**
     * get 行政区划ID value
     * @return the ZONE_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getZONE_ID(){
        return ZONE_ID;
    }
	/**
     * set 行政区划ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setZONE_ID(String ZONE_ID){
        this.ZONE_ID=ZONE_ID;
    }
     /**
     * get 行政区划地址 value
     * @return the ZONE_ADDR
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getZONE_ADDR(){
        return ZONE_ADDR;
    }
	/**
     * set 行政区划地址 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setZONE_ADDR(String ZONE_ADDR){
        this.ZONE_ADDR=ZONE_ADDR;
    }
     /**
     * get 区域经理ID value
     * @return the AREA_MANAGE_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getAREA_MANAGE_ID(){
        return AREA_MANAGE_ID;
    }
	/**
     * set 区域经理ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setAREA_MANAGE_ID(String AREA_MANAGE_ID){
        this.AREA_MANAGE_ID=AREA_MANAGE_ID;
    }
     /**
     * get 区域经理名称 value
     * @return the AREA_MANAGE_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getAREA_MANAGE_NAME(){
        return AREA_MANAGE_NAME;
    }
	/**
     * set 区域经理名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setAREA_MANAGE_NAME(String AREA_MANAGE_NAME){
        this.AREA_MANAGE_NAME=AREA_MANAGE_NAME;
    }
     /**
     * get 区域经理电话 value
     * @return the AREA_MANAGE_TEL
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getAREA_MANAGE_TEL(){
        return AREA_MANAGE_TEL;
    }
	/**
     * set 区域经理电话 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setAREA_MANAGE_TEL(String AREA_MANAGE_TEL){
        this.AREA_MANAGE_TEL=AREA_MANAGE_TEL;
    }
     /**
     * get 申请人ID value
     * @return the REQ_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getREQ_ID(){
        return REQ_ID;
    }
	/**
     * set 申请人ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setREQ_ID(String REQ_ID){
        this.REQ_ID=REQ_ID;
    }
     /**
     * get 申请人姓名 value
     * @return the REQ_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getREQ_NAME(){
        return REQ_NAME;
    }
	/**
     * set 申请人姓名 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setREQ_NAME(String REQ_NAME){
        this.REQ_NAME=REQ_NAME;
    }
     /**
     * get 申请时间 value
     * @return the REQ_DATE
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getREQ_DATE(){
        return REQ_DATE;
    }
	/**
     * set 申请时间 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setREQ_DATE(String REQ_DATE){
        this.REQ_DATE=REQ_DATE;
    }
     /**
     * get 执行总结 value
     * @return the EXCT_SMRZ
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getEXCT_SMRZ(){
        return EXCT_SMRZ;
    }
	/**
     * set 执行总结 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setEXCT_SMRZ(String EXCT_SMRZ){
        this.EXCT_SMRZ=EXCT_SMRZ;
    }
     /**
     * get 此次推广费用共计 value
     * @return the TOTAL_PRMT_COST
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getTOTAL_PRMT_COST(){
        return TOTAL_PRMT_COST;
    }
	/**
     * set 此次推广费用共计 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setTOTAL_PRMT_COST(String TOTAL_PRMT_COST){
        this.TOTAL_PRMT_COST=TOTAL_PRMT_COST;
    }
     /**
     * get 申请报销金额 value
     * @return the REIT_REQ_AMOUNT
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getREIT_REQ_AMOUNT(){
        return REIT_REQ_AMOUNT;
    }
	/**
     * set 申请报销金额 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setREIT_REQ_AMOUNT(String REIT_REQ_AMOUNT){
        this.REIT_REQ_AMOUNT=REIT_REQ_AMOUNT;
    }
     /**
     * get 费用类别 value
     * @return the COST_TYPE
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCOST_TYPE(){
        return COST_TYPE;
    }
	/**
     * set 费用类别 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCOST_TYPE(String COST_TYPE){
        this.COST_TYPE=COST_TYPE;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDIT_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-01-25 19:49:05
     * @author chenj
	 * @createdate 2014-01-25 19:49:05
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getSTATENEBTS_ATT() {
		return STATENEBTS_ATT;
	}
	public void setSTATENEBTS_ATT(String statenebts_att) {
		STATENEBTS_ATT = statenebts_att;
	}
}