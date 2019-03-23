/**
 * prjName:喜临门营销平台
 * ucName:入库通知单维护
 * fileName:StoreinnoticeModel
*/
package com.hoperun.drp.store.storeinnotice.model;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-17 17:07:03
 */
public class StoreinnoticeModel{
   /** 入库通知单ID **/
   private String STOREIN_NOTICE_ID;
   /** 入库通知单编号 **/
   private String STOREIN_NOTICE_NO;
   /** 单据类型 **/
   private String BILL_TYPE;
   /** 来源单据ID **/
   private String FROM_BILL_ID;
   /** 来源单据编号 **/
   private String FROM_BILL_NO;
   /** 发货方ID **/
   private String SEND_CHANN_ID;
   /** 发货方编号 **/
   private String SEND_CHANN_NO;
   /** 发货方名称 **/
   private String SEND_CHANN_NAME;
   /** 收货方ID **/
   private String RECV_CHANN_ID;
   /** 收货方编号 **/
   private String RECV_CHANN_NO;
   /** 收货方名称 **/
   private String RECV_CHANN_NAME;
   /** 默认入库库房ID **/
   private String DEF_STORE_ID;
   /** 默认入库库房编号 **/
   private String DEF_STORE_NO;
   /** 默认入库库房名称 **/
   private String DEF_STORE_NAME;
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
   /** 制单部门 **/
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
   /** 来源类型 **/
   private String FROM_TYPE;
   /**入库时间**/
   private String STOREIN_TIME;
     /**
     * get 入库通知单ID value
     * @return the STOREIN_NOTICE_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_NOTICE_ID(){
        return STOREIN_NOTICE_ID;
    }
	/**
     * set 入库通知单ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_NOTICE_ID(String STOREIN_NOTICE_ID){
        this.STOREIN_NOTICE_ID=STOREIN_NOTICE_ID;
    }
     /**
     * get 入库通知单编号 value
     * @return the STOREIN_NOTICE_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTOREIN_NOTICE_NO(){
        return STOREIN_NOTICE_NO;
    }
	/**
     * set 入库通知单编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTOREIN_NOTICE_NO(String STOREIN_NOTICE_NO){
        this.STOREIN_NOTICE_NO=STOREIN_NOTICE_NO;
    }
     /**
     * get 单据类型 value
     * @return the BILL_TYPE
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getBILL_TYPE(){
        return BILL_TYPE;
    }
	/**
     * set 单据类型 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setBILL_TYPE(String BILL_TYPE){
        this.BILL_TYPE=BILL_TYPE;
    }
     /**
     * get 来源单据ID value
     * @return the FROM_BILL_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getFROM_BILL_ID(){
        return FROM_BILL_ID;
    }
	/**
     * set 来源单据ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setFROM_BILL_ID(String FROM_BILL_ID){
        this.FROM_BILL_ID=FROM_BILL_ID;
    }
     /**
     * get 来源单据编号 value
     * @return the FROM_BILL_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getFROM_BILL_NO(){
        return FROM_BILL_NO;
    }
	/**
     * set 来源单据编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setFROM_BILL_NO(String FROM_BILL_NO){
        this.FROM_BILL_NO=FROM_BILL_NO;
    }
     /**
     * get 发货方ID value
     * @return the SEND_CHANN_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSEND_CHANN_ID(){
        return SEND_CHANN_ID;
    }
	/**
     * set 发货方ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSEND_CHANN_ID(String SEND_CHANN_ID){
        this.SEND_CHANN_ID=SEND_CHANN_ID;
    }
     /**
     * get 发货方编号 value
     * @return the SEND_CHANN_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSEND_CHANN_NO(){
        return SEND_CHANN_NO;
    }
	/**
     * set 发货方编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSEND_CHANN_NO(String SEND_CHANN_NO){
        this.SEND_CHANN_NO=SEND_CHANN_NO;
    }
     /**
     * get 发货方名称 value
     * @return the SEND_CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSEND_CHANN_NAME(){
        return SEND_CHANN_NAME;
    }
	/**
     * set 发货方名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSEND_CHANN_NAME(String SEND_CHANN_NAME){
        this.SEND_CHANN_NAME=SEND_CHANN_NAME;
    }
     /**
     * get 收货方ID value
     * @return the RECV_CHANN_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getRECV_CHANN_ID(){
        return RECV_CHANN_ID;
    }
	/**
     * set 收货方ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setRECV_CHANN_ID(String RECV_CHANN_ID){
        this.RECV_CHANN_ID=RECV_CHANN_ID;
    }
     /**
     * get 收货方编号 value
     * @return the RECV_CHANN_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getRECV_CHANN_NO(){
        return RECV_CHANN_NO;
    }
	/**
     * set 收货方编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setRECV_CHANN_NO(String RECV_CHANN_NO){
        this.RECV_CHANN_NO=RECV_CHANN_NO;
    }
     /**
     * get 收货方名称 value
     * @return the RECV_CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getRECV_CHANN_NAME(){
        return RECV_CHANN_NAME;
    }
	/**
     * set 收货方名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setRECV_CHANN_NAME(String RECV_CHANN_NAME){
        this.RECV_CHANN_NAME=RECV_CHANN_NAME;
    }
     /**
     * get 默认入库库房ID value
     * @return the DEF_STORE_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEF_STORE_ID(){
        return DEF_STORE_ID;
    }
	/**
     * set 默认入库库房ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEF_STORE_ID(String DEF_STORE_ID){
        this.DEF_STORE_ID=DEF_STORE_ID;
    }
     /**
     * get 默认入库库房编号 value
     * @return the DEF_STORE_NO
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEF_STORE_NO(){
        return DEF_STORE_NO;
    }
	/**
     * set 默认入库库房编号 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEF_STORE_NO(String DEF_STORE_NO){
        this.DEF_STORE_NO=DEF_STORE_NO;
    }
     /**
     * get 默认入库库房名称 value
     * @return the DEF_STORE_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEF_STORE_NAME(){
        return DEF_STORE_NAME;
    }
	/**
     * set 默认入库库房名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEF_STORE_NAME(String DEF_STORE_NAME){
        this.DEF_STORE_NAME=DEF_STORE_NAME;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDIT_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-17 17:07:03
     * @author glw
	 * @createdate 2013-08-17 17:07:03
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getFROM_TYPE() {
		return FROM_TYPE;
	}
	public void setFROM_TYPE(String fROMTYPE) {
		FROM_TYPE = fROMTYPE;
	}
	public String getSTOREIN_TIME() {
		return STOREIN_TIME;
	}
	public void setSTOREIN_TIME(String sTOREINTIME) {
		STOREIN_TIME = sTOREINTIME;
	}
    
    
    
    
}