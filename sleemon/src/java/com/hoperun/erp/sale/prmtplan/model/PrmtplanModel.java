/**
 * prjName:喜临门营销平台
 * ucName:推广促销方案维护
 * fileName:PrmtplanModel
*/
package com.hoperun.erp.sale.prmtplan.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func 促销
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-08-23 16:04:28
 */
public class PrmtplanModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
/** 促销方案ID **/
   private String PRMT_PLAN_ID;
   /** 促销方案编号 **/
   private String PRMT_PLAN_NO;
   /** 促销方案名称 **/
   private String PRMT_PLAN_NAME;
   /** 促销类型 **/
   private String PRMT_TYPE;
   /** 生效日期从 **/
   private String EFFCT_DATE_BEG;
   /** 生效日期到 **/
   private String EFFCT_DATE_END;
   /** 备注 **/
   private String REMARK;
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
   /** 账套 **/
   private String LEDGER_ID;
   /** 账套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
   
   private String AUDIT_ID;
   private String AUDIT_NAME;
   private String AUDIT_TIME;
  
   /**
     * get 促销方案ID value
     * @return the PRMT_PLAN_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRMT_PLAN_ID(){
        return PRMT_PLAN_ID;
    }
	/**
     * set 促销方案ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRMT_PLAN_ID(String PRMT_PLAN_ID){
        this.PRMT_PLAN_ID=PRMT_PLAN_ID;
    }
     /**
     * get 促销方案编号 value
     * @return the PRMT_PLAN_NO
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRMT_PLAN_NO(){
        return PRMT_PLAN_NO;
    }
	/**
     * set 促销方案编号 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRMT_PLAN_NO(String PRMT_PLAN_NO){
        this.PRMT_PLAN_NO=PRMT_PLAN_NO;
    }
     /**
     * get 促销方案名称 value
     * @return the PRMT_PLAN_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRMT_PLAN_NAME(){
        return PRMT_PLAN_NAME;
    }
	/**
     * set 促销方案名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRMT_PLAN_NAME(String PRMT_PLAN_NAME){
        this.PRMT_PLAN_NAME=PRMT_PLAN_NAME;
    }
     /**
     * get 促销类型 value
     * @return the PRMT_TYPE
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRMT_TYPE(){
        return PRMT_TYPE;
    }
	/**
     * set 促销类型 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRMT_TYPE(String PRMT_TYPE){
        this.PRMT_TYPE=PRMT_TYPE;
    }
     /**
     * get 生效日期从 value
     * @return the EFFCT_DATE_BEG
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getEFFCT_DATE_BEG(){
        return EFFCT_DATE_BEG;
    }
	/**
     * set 生效日期从 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setEFFCT_DATE_BEG(String EFFCT_DATE_BEG){
        this.EFFCT_DATE_BEG=EFFCT_DATE_BEG;
    }
     /**
     * get 生效日期到 value
     * @return the EFFCT_DATE_END
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getEFFCT_DATE_END(){
        return EFFCT_DATE_END;
    }
	/**
     * set 生效日期到 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setEFFCT_DATE_END(String EFFCT_DATE_END){
        this.EFFCT_DATE_END=EFFCT_DATE_END;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 账套 value
     * @return the LEDGER_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 账套 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 账套名称 value
     * @return the LEDGER_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 账套名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
	}
    
    
}