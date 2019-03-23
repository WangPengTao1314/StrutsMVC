/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderModel
*/
package com.hoperun.erp.sale.techorderprice.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 16:47:06
 */
public class TechorderPriceModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1293155983558317420L;
/** 工艺单ID **/
   private String TECH_ORDER_ID;
   /** 工艺单编号 **/
   private String TECH_ORDER_NO;
   /** 来源单据ID **/
   private String FROM_BILL_ID;
   /** 来源单据NO **/
   private String FROM_BILL_NO;
   /** 订货方ID **/
   private String ORDER_CHANN_ID;
   /** 订货方编号 **/
   private String ORDER_CHANN_NO;
   /** 订货方名称 **/
   private String ORDER_CHANN_NAME;
   /** 订货日期 **/
   private String ORDER_DATE;
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
     /**
     * get 工艺单ID value
     * @return the TECH_ORDER_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getTECH_ORDER_ID(){
        return TECH_ORDER_ID;
    }
	/**
     * set 工艺单ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setTECH_ORDER_ID(String TECH_ORDER_ID){
        this.TECH_ORDER_ID=TECH_ORDER_ID;
    }
     /**
     * get 工艺单编号 value
     * @return the TECH_ORDER_NO
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getTECH_ORDER_NO(){
        return TECH_ORDER_NO;
    }
	/**
     * set 工艺单编号 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setTECH_ORDER_NO(String TECH_ORDER_NO){
        this.TECH_ORDER_NO=TECH_ORDER_NO;
    }
     /**
     * get 来源单据ID value
     * @return the FROM_BILL_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getFROM_BILL_ID(){
        return FROM_BILL_ID;
    }
	/**
     * set 来源单据ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setFROM_BILL_ID(String FROM_BILL_ID){
        this.FROM_BILL_ID=FROM_BILL_ID;
    }
     /**
     * get 来源单据NO value
     * @return the FROM_BILL_NO
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getFROM_BILL_NO(){
        return FROM_BILL_NO;
    }
	/**
     * set 来源单据NO value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setFROM_BILL_NO(String FROM_BILL_NO){
        this.FROM_BILL_NO=FROM_BILL_NO;
    }
     /**
     * get 订货方ID value
     * @return the ORDER_CHANN_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORDER_CHANN_ID(){
        return ORDER_CHANN_ID;
    }
	/**
     * set 订货方ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORDER_CHANN_ID(String ORDER_CHANN_ID){
        this.ORDER_CHANN_ID=ORDER_CHANN_ID;
    }
     /**
     * get 订货方编号 value
     * @return the ORDER_CHANN_NO
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORDER_CHANN_NO(){
        return ORDER_CHANN_NO;
    }
	/**
     * set 订货方编号 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORDER_CHANN_NO(String ORDER_CHANN_NO){
        this.ORDER_CHANN_NO=ORDER_CHANN_NO;
    }
     /**
     * get 订货方名称 value
     * @return the ORDER_CHANN_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORDER_CHANN_NAME(){
        return ORDER_CHANN_NAME;
    }
	/**
     * set 订货方名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORDER_CHANN_NAME(String ORDER_CHANN_NAME){
        this.ORDER_CHANN_NAME=ORDER_CHANN_NAME;
    }
     /**
     * get 订货日期 value
     * @return the ORDER_DATE
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORDER_DATE(){
        return ORDER_DATE;
    }
	/**
     * set 订货日期 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORDER_DATE(String ORDER_DATE){
        this.ORDER_DATE=ORDER_DATE;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDIT_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-10-12 16:47:06
     * @author lyg
	 * @createdate 2013-10-12 16:47:06
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}