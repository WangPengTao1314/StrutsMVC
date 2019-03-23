/**
 * prjName:喜临门营销平台
 * ucName:库存储备信息
 * fileName:ResvstoreModel
*/
package com.hoperun.drp.base.resvstore.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func 库存储备
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-07 14:13:12
 */
public class ResvstoreModel extends BaseModel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
   /** 库存储备信息ID **/
   private String RESV_STOCK_ID;
   /** 货品ID **/
   private String PRD_ID;
   /** 货品编号 **/
   private String PRD_NO;
   /** 货品名称 **/
   private String PRD_NAME;
   /** 规格型号 **/
   private String PRD_SPEC;
   /** 颜色 **/
   private String PRD_COLOR;
   /** 品牌 **/
   private String BRAND;
   /** 标准单位 **/
   private String STD_UNIT;
   /** 安全库存数量 **/
   private String SAFE_STORE_NUM;
   /** 最低库存数量 **/
   private String MIN_STORE_NUM;
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
     * get 库存储备信息ID value
     * @return the RESV_STOCK_ID
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getRESV_STOCK_ID(){
        return RESV_STOCK_ID;
    }
	/**
     * set 库存储备信息ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setRESV_STOCK_ID(String RESV_STOCK_ID){
        this.RESV_STOCK_ID=RESV_STOCK_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 安全库存数量 value
     * @return the SAFE_STORE_NUM
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getSAFE_STORE_NUM(){
        return SAFE_STORE_NUM;
    }
	/**
     * set 安全库存数量 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setSAFE_STORE_NUM(String SAFE_STORE_NUM){
        this.SAFE_STORE_NUM=SAFE_STORE_NUM;
    }
     /**
     * get 最低库存数量 value
     * @return the MIN_STORE_NUM
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getMIN_STORE_NUM(){
        return MIN_STORE_NUM;
    }
	/**
     * set 最低库存数量 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setMIN_STORE_NUM(String MIN_STORE_NUM){
        this.MIN_STORE_NUM=MIN_STORE_NUM;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套 value
     * @return the LEDGER_NAME
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-07 14:13:12
     * @author zzb
	 * @createdate 2013-09-07 14:13:12
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}