/**
 * prjName:喜临门营销平台
 * ucName:盘点单维护
 * fileName:InventoryModel
*/
package com.hoperun.drp.store.inventory.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-07 09:54:59
 */
public class InventoryModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -2458817588980484134L;
/** 盘点单ID **/
   private String PRD_INV_ID;
   /** 盘点单编号 **/
   private String PRD_INV_NO;
   /** 盘点类型 **/
   private String INV_TYPE;
   /** 库房ID **/
   private String STORE_ID;
   /** 库房编号 **/
   private String STORE_NO;
   /** 库房名称 **/
   private String STORE_NAME;
   /** 库位管理标记 **/
   private String STORAGE_FLAG;
   /** 盘点范围 **/
   private String INV_RANGE;
   /** 库位信息ID **/
   private String STORG_ID;
   /** 库位编号 **/
   private String STORG_NO;
   /** 库位名称 **/
   private String STORG_NAME;
   /** 货品ID **/
   private String PRD_ID;
   /** 货品编号 **/
   private String PRD_NO;
   /** 货品名称 **/
   private String PRD_NAME;
   /** 监盘人ID **/
   private String INV_PSON_ID;
   /** 监盘人编号 **/
   private String INV_PSON_NO;
   /** 监盘人 **/
   private String INV_PSON_NAME;
   /** 备注 **/
   private String REMARK;
   /** 审核人ID **/
   private String AUDIT_ID;
   /** 审核人 **/
   private String AUDIT_NAME;
   /** 审核时间 **/
   private String AUDIT_TIME;
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
     * get 盘点单ID value
     * @return the PRD_INV_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_INV_ID(){
        return PRD_INV_ID;
    }
	/**
     * set 盘点单ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_INV_ID(String PRD_INV_ID){
        this.PRD_INV_ID=PRD_INV_ID;
    }
     /**
     * get 盘点单编号 value
     * @return the PRD_INV_NO
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_INV_NO(){
        return PRD_INV_NO;
    }
	/**
     * set 盘点单编号 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_INV_NO(String PRD_INV_NO){
        this.PRD_INV_NO=PRD_INV_NO;
    }
     /**
     * get 盘点类型 value
     * @return the INV_TYPE
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINV_TYPE(){
        return INV_TYPE;
    }
	/**
     * set 盘点类型 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINV_TYPE(String INV_TYPE){
        this.INV_TYPE=INV_TYPE;
    }
     /**
     * get 库房ID value
     * @return the STORE_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORE_ID(){
        return STORE_ID;
    }
	/**
     * set 库房ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORE_ID(String STORE_ID){
        this.STORE_ID=STORE_ID;
    }
     /**
     * get 库房编号 value
     * @return the STORE_NO
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORE_NO(){
        return STORE_NO;
    }
	/**
     * set 库房编号 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORE_NO(String STORE_NO){
        this.STORE_NO=STORE_NO;
    }
     /**
     * get 库房名称 value
     * @return the STORE_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORE_NAME(){
        return STORE_NAME;
    }
	/**
     * set 库房名称 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORE_NAME(String STORE_NAME){
        this.STORE_NAME=STORE_NAME;
    }
     /**
     * get 库位管理标记 value
     * @return the STORAGE_FLAG
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORAGE_FLAG(){
        return STORAGE_FLAG;
    }
	/**
     * set 库位管理标记 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORAGE_FLAG(String STORAGE_FLAG){
        this.STORAGE_FLAG=STORAGE_FLAG;
    }
     /**
     * get 盘点范围 value
     * @return the INV_RANGE
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINV_RANGE(){
        return INV_RANGE;
    }
	/**
     * set 盘点范围 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINV_RANGE(String INV_RANGE){
        this.INV_RANGE=INV_RANGE;
    }
     /**
     * get 库位信息ID value
     * @return the STORG_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORG_ID(){
        return STORG_ID;
    }
	/**
     * set 库位信息ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORG_ID(String STORG_ID){
        this.STORG_ID=STORG_ID;
    }
     /**
     * get 库位编号 value
     * @return the STORG_NO
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORG_NO(){
        return STORG_NO;
    }
	/**
     * set 库位编号 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORG_NO(String STORG_NO){
        this.STORG_NO=STORG_NO;
    }
     /**
     * get 库位名称 value
     * @return the STORG_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTORG_NAME(){
        return STORG_NAME;
    }
	/**
     * set 库位名称 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTORG_NAME(String STORG_NAME){
        this.STORG_NAME=STORG_NAME;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 监盘人ID value
     * @return the INV_PSON_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINV_PSON_ID(){
        return INV_PSON_ID;
    }
	/**
     * set 监盘人ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINV_PSON_ID(String INV_PSON_ID){
        this.INV_PSON_ID=INV_PSON_ID;
    }
     /**
     * get 监盘人编号 value
     * @return the INV_PSON_NO
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINV_PSON_NO(){
        return INV_PSON_NO;
    }
	/**
     * set 监盘人编号 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINV_PSON_NO(String INV_PSON_NO){
        this.INV_PSON_NO=INV_PSON_NO;
    }
     /**
     * get 监盘人 value
     * @return the INV_PSON_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINV_PSON_NAME(){
        return INV_PSON_NAME;
    }
	/**
     * set 监盘人 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINV_PSON_NAME(String INV_PSON_NAME){
        this.INV_PSON_NAME=INV_PSON_NAME;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人 value
     * @return the AUDIT_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}