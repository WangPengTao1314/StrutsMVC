/**
 * prjName:喜临门营销平台
 * ucName:调拨申请维护
 * fileName:AllocateModel
*/
package com.hoperun.drp.store.allocate.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-05 13:29:12
 */
public class AllocateModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -5413357853862644884L;
/** 调拨单ID **/
   private String ALLOCATE_ID;
   /** 调拨单编号 **/
   private String ALLOCATE_NO;
   /** 单据类型 **/
   private String BILL_TYPE;
   /** 调出方ID **/
   private String ALLOC_OUT_CHANN_ID;
   /** 调出方编号 **/
   private String ALLOC_OUT_CHANN_NO;
   /** 调出方名称 **/
   private String ALLOC_OUT_CHANN_NAME;
   /** 调入方ID **/
   private String ALLOC_IN_CHANN_ID;
   /** 调入方编号 **/
   private String ALLOC_IN_CHANN_NO;
   /** 调入方名称 **/
   private String ALLOC_IN_CHANN_NAME;
   /** 调出方库房ID **/
   private String ALLOC_OUT_STORE_ID;
   /** 调出库房编号 **/
   private String ALLOC_OUT_STORE_NO;
   /** 调出库房名称 **/
   private String ALLOC_OUT_STORE_NAME;
   /** 库位管理标记 **/
   private String STORAGE_FLAG;
   /** 备注 **/
   private String REMARK;
   /** 制单人 **/
   private String CRE_NAME;
   /** 制单人ID **/
   private String CREATOR;
   /** 制单时间 **/
   private String CRE_TIME;
   /** 更新人 **/
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
   /** 帐套 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 调拨单ID value
     * @return the ALLOCATE_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOCATE_ID(){
        return ALLOCATE_ID;
    }
	/**
     * set 调拨单ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOCATE_ID(String ALLOCATE_ID){
        this.ALLOCATE_ID=ALLOCATE_ID;
    }
     /**
     * get 调拨单编号 value
     * @return the ALLOCATE_NO
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOCATE_NO(){
        return ALLOCATE_NO;
    }
	/**
     * set 调拨单编号 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOCATE_NO(String ALLOCATE_NO){
        this.ALLOCATE_NO=ALLOCATE_NO;
    }
     /**
     * get 单据类型 value
     * @return the BILL_TYPE
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getBILL_TYPE(){
        return BILL_TYPE;
    }
	/**
     * set 单据类型 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setBILL_TYPE(String BILL_TYPE){
        this.BILL_TYPE=BILL_TYPE;
    }
     /**
     * get 调出方ID value
     * @return the ALLOC_OUT_CHANN_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_OUT_CHANN_ID(){
        return ALLOC_OUT_CHANN_ID;
    }
	/**
     * set 调出方ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_OUT_CHANN_ID(String ALLOC_OUT_CHANN_ID){
        this.ALLOC_OUT_CHANN_ID=ALLOC_OUT_CHANN_ID;
    }
     /**
     * get 调出方编号 value
     * @return the ALLOC_OUT_CHANN_NO
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_OUT_CHANN_NO(){
        return ALLOC_OUT_CHANN_NO;
    }
	/**
     * set 调出方编号 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_OUT_CHANN_NO(String ALLOC_OUT_CHANN_NO){
        this.ALLOC_OUT_CHANN_NO=ALLOC_OUT_CHANN_NO;
    }
     /**
     * get 调出方名称 value
     * @return the ALLOC_OUT_CHANN_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_OUT_CHANN_NAME(){
        return ALLOC_OUT_CHANN_NAME;
    }
	/**
     * set 调出方名称 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_OUT_CHANN_NAME(String ALLOC_OUT_CHANN_NAME){
        this.ALLOC_OUT_CHANN_NAME=ALLOC_OUT_CHANN_NAME;
    }
     /**
     * get 调入方ID value
     * @return the ALLOC_IN_CHANN_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_IN_CHANN_ID(){
        return ALLOC_IN_CHANN_ID;
    }
	/**
     * set 调入方ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_IN_CHANN_ID(String ALLOC_IN_CHANN_ID){
        this.ALLOC_IN_CHANN_ID=ALLOC_IN_CHANN_ID;
    }
     /**
     * get 调入方编号 value
     * @return the ALLOC_IN_CHANN_NO
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_IN_CHANN_NO(){
        return ALLOC_IN_CHANN_NO;
    }
	/**
     * set 调入方编号 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_IN_CHANN_NO(String ALLOC_IN_CHANN_NO){
        this.ALLOC_IN_CHANN_NO=ALLOC_IN_CHANN_NO;
    }
     /**
     * get 调入方名称 value
     * @return the ALLOC_IN_CHANN_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_IN_CHANN_NAME(){
        return ALLOC_IN_CHANN_NAME;
    }
	/**
     * set 调入方名称 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_IN_CHANN_NAME(String ALLOC_IN_CHANN_NAME){
        this.ALLOC_IN_CHANN_NAME=ALLOC_IN_CHANN_NAME;
    }
     /**
     * get 调出方库房ID value
     * @return the ALLOC_OUT_STORE_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_OUT_STORE_ID(){
        return ALLOC_OUT_STORE_ID;
    }
	/**
     * set 调出方库房ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_OUT_STORE_ID(String ALLOC_OUT_STORE_ID){
        this.ALLOC_OUT_STORE_ID=ALLOC_OUT_STORE_ID;
    }
     /**
     * get 调出库房编号 value
     * @return the ALLOC_OUT_STORE_NO
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_OUT_STORE_NO(){
        return ALLOC_OUT_STORE_NO;
    }
	/**
     * set 调出库房编号 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_OUT_STORE_NO(String ALLOC_OUT_STORE_NO){
        this.ALLOC_OUT_STORE_NO=ALLOC_OUT_STORE_NO;
    }
     /**
     * get 调出库房名称 value
     * @return the ALLOC_OUT_STORE_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getALLOC_OUT_STORE_NAME(){
        return ALLOC_OUT_STORE_NAME;
    }
	/**
     * set 调出库房名称 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setALLOC_OUT_STORE_NAME(String ALLOC_OUT_STORE_NAME){
        this.ALLOC_OUT_STORE_NAME=ALLOC_OUT_STORE_NAME;
    }
     /**
     * get 库位管理标记 value
     * @return the STORAGE_FLAG
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getSTORAGE_FLAG(){
        return STORAGE_FLAG;
    }
	/**
     * set 库位管理标记 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setSTORAGE_FLAG(String STORAGE_FLAG){
        this.STORAGE_FLAG=STORAGE_FLAG;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-05 13:29:12
     * @author lyg
	 * @createdate 2013-09-05 13:29:12
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}