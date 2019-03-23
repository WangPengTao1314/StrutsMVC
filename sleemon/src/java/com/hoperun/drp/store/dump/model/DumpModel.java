/**
 * prjName:喜临门营销平台
 * ucName:转储单
 * fileName:DumpModel
*/
package com.hoperun.drp.store.dump.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-05 14:00:34
 */
public class DumpModel extends BaseModel{
   /** 转储单ID **/
   private String DUMP_ID;
   /** 转储单编号 **/
   private String DUMP_NO;
   /** 单据类型 **/
   private String BILL_TYPE;
   /** 转出库房ID **/
   private String DUMP_OUT_STORE_ID;
   /** 转出库房编号 **/
   private String DUMP_OUT_STORE_NO;
   /** 转出库房名称 **/
   private String DUMP_OUT_STORE_NAME;
   /** 转出库位管理标记 **/
   private String DUMP_OUT_STORAGE_FLAG;
   /** 转入库房ID **/
   private String DUMP_IN_STORE_ID;
   /** 转入库房编号 **/
   private String DUMP_IN_STORE_NO;
   /** 转入库房名称 **/
   private String DUMP_IN_STORE_NAME;
   /** 转入库位管理标记 **/
   private String STORAGE_FLAG2;
   /** 备注 **/
   private String REMARK;
   /** 制单人 **/
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
   /** 制单机构 **/
   private String ORG_NAME;
   /** 帐套ID **/
   private String LEDGER_ID;
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 审核人姓名人 **/
   private String AUDIT_NAME;
   /** 审核人ID **/
   private String AUDIT_ID;
   /** 审核时间 **/
   private String AUDIT_TIME;
   /**转储日期**/
   private String DUMP_DATE;
   /**关联单据号**/
   private String RELT_BILL_NO;
   
     /**
     * get 转储单ID value
     * @return the DUMP_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_ID(){
        return DUMP_ID;
    }
	/**
     * set 转储单ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_ID(String DUMP_ID){
        this.DUMP_ID=DUMP_ID;
    }
     /**
     * get 转储单编号 value
     * @return the DUMP_NO
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_NO(){
        return DUMP_NO;
    }
	/**
     * set 转储单编号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_NO(String DUMP_NO){
        this.DUMP_NO=DUMP_NO;
    }
     /**
     * get 单据类型 value
     * @return the BILL_TYPE
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getBILL_TYPE(){
        return BILL_TYPE;
    }
	/**
     * set 单据类型 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setBILL_TYPE(String BILL_TYPE){
        this.BILL_TYPE=BILL_TYPE;
    }
     /**
     * get 转出库房ID value
     * @return the DUMP_OUT_STORE_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORE_ID(){
        return DUMP_OUT_STORE_ID;
    }
	/**
     * set 转出库房ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORE_ID(String DUMP_OUT_STORE_ID){
        this.DUMP_OUT_STORE_ID=DUMP_OUT_STORE_ID;
    }
     /**
     * get 转出库房编号 value
     * @return the DUMP_OUT_STORE_NO
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORE_NO(){
        return DUMP_OUT_STORE_NO;
    }
	/**
     * set 转出库房编号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORE_NO(String DUMP_OUT_STORE_NO){
        this.DUMP_OUT_STORE_NO=DUMP_OUT_STORE_NO;
    }
     /**
     * get 转出库房名称 value
     * @return the DUMP_OUT_STORE_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORE_NAME(){
        return DUMP_OUT_STORE_NAME;
    }
	/**
     * set 转出库房名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORE_NAME(String DUMP_OUT_STORE_NAME){
        this.DUMP_OUT_STORE_NAME=DUMP_OUT_STORE_NAME;
    }
     /**
     * get 转出库位管理标记 value
     * @return the DUMP_OUT_STORAGE_FLAG
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORAGE_FLAG(){
        return DUMP_OUT_STORAGE_FLAG;
    }
	/**
     * set 转出库位管理标记 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORAGE_FLAG(String DUMP_OUT_STORAGE_FLAG){
        this.DUMP_OUT_STORAGE_FLAG=DUMP_OUT_STORAGE_FLAG;
    }
     /**
     * get 转入库房ID value
     * @return the DUMP_IN_STORE_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_IN_STORE_ID(){
        return DUMP_IN_STORE_ID;
    }
	/**
     * set 转入库房ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_IN_STORE_ID(String DUMP_IN_STORE_ID){
        this.DUMP_IN_STORE_ID=DUMP_IN_STORE_ID;
    }
     /**
     * get 转入库房编号 value
     * @return the DUMP_IN_STORE_NO
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_IN_STORE_NO(){
        return DUMP_IN_STORE_NO;
    }
	/**
     * set 转入库房编号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_IN_STORE_NO(String DUMP_IN_STORE_NO){
        this.DUMP_IN_STORE_NO=DUMP_IN_STORE_NO;
    }
     /**
     * get 转入库房名称 value
     * @return the DUMP_IN_STORE_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_IN_STORE_NAME(){
        return DUMP_IN_STORE_NAME;
    }
	/**
     * set 转入库房名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_IN_STORE_NAME(String DUMP_IN_STORE_NAME){
        this.DUMP_IN_STORE_NAME=DUMP_IN_STORE_NAME;
    }
     /**
     * get 转入库位管理标记 value
     * @return the STORAGE_FLAG2
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getSTORAGE_FLAG2(){
        return STORAGE_FLAG2;
    }
	/**
     * set 转入库位管理标记 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setSTORAGE_FLAG2(String STORAGE_FLAG2){
        this.STORAGE_FLAG2=STORAGE_FLAG2;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getAUDIT_NAME() {
		return AUDIT_NAME;
	}
	public void setAUDIT_NAME(String aUDITNAME) {
		AUDIT_NAME = aUDITNAME;
	}
	public String getAUDIT_ID() {
		return AUDIT_ID;
	}
	public void setAUDIT_ID(String aUDITID) {
		AUDIT_ID = aUDITID;
	}
	public String getAUDIT_TIME() {
		return AUDIT_TIME;
	}
	public void setAUDIT_TIME(String aUDITTIME) {
		AUDIT_TIME = aUDITTIME;
	}
	/**
	 * @return the dUMP_DATE
	 */
	public String getDUMP_DATE() {
		return DUMP_DATE;
	}
	/**
	 * @param dUMPDATE the dUMP_DATE to set
	 */
	public void setDUMP_DATE(String dUMPDATE) {
		DUMP_DATE = dUMPDATE;
	}
	/**
	 * @return the rELT_BILL_NO
	 */
	public String getRELT_BILL_NO() {
		return RELT_BILL_NO;
	}
	/**
	 * @param rELTBILLNO the rELT_BILL_NO to set
	 */
	public void setRELT_BILL_NO(String rELTBILLNO) {
		RELT_BILL_NO = rELTBILLNO;
	}
    
    
}