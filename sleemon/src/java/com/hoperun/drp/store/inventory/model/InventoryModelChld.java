/**
 * prjName:喜临门营销平台
 * ucName:盘点单维护
 * fileName:InventoryModelChld
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
public class InventoryModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 283506342072499452L;
/** 盘点单明细ID **/
   private String PRD_INV_DTL_ID;
   /** 盘点单ID **/
   private String PRD_INV_ID;
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
   /** 规格型号 **/
   private String PRD_SPEC;
   /** 颜色 **/
   private String PRD_COLOR;
   /** 品牌 **/
   private String BRAND;
   /** 标准单位 **/
   private String STD_UNIT;
   /** 盘点数量 **/
   private String INV_NUM;
   /** 账面数量 **/
   private String ACCT_NUM;
   /** 差异数量 **/
   private String DIFF_NUM;
   /** 手工新增标记 **/
   private String INS_FLAG;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 特殊工艺id*/
   private String SPCL_TECH_ID;
     /**
     * get 盘点单明细ID value
     * @return the PRD_INV_DTL_ID
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_INV_DTL_ID(){
        return PRD_INV_DTL_ID;
    }
	/**
     * set 盘点单明细ID value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_INV_DTL_ID(String PRD_INV_DTL_ID){
        this.PRD_INV_DTL_ID=PRD_INV_DTL_ID;
    }
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
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 盘点数量 value
     * @return the INV_NUM
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINV_NUM(){
        return INV_NUM;
    }
	/**
     * set 盘点数量 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINV_NUM(String INV_NUM){
        this.INV_NUM=INV_NUM;
    }
     /**
     * get 账面数量 value
     * @return the ACCT_NUM
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getACCT_NUM(){
        return ACCT_NUM;
    }
	/**
     * set 账面数量 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setACCT_NUM(String ACCT_NUM){
        this.ACCT_NUM=ACCT_NUM;
    }
     /**
     * get 差异数量 value
     * @return the DIFF_NUM
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getDIFF_NUM(){
        return DIFF_NUM;
    }
	/**
     * set 差异数量 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setDIFF_NUM(String DIFF_NUM){
        this.DIFF_NUM=DIFF_NUM;
    }
     /**
     * get 手工新增标记 value
     * @return the INS_FLAG
	 * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public String getINS_FLAG(){
        return INS_FLAG;
    }
	/**
     * set 手工新增标记 value
     * @createdate 2013-09-07 09:54:59
     * @author lyg
	 * @createdate 2013-09-07 09:54:59
     */
    public void setINS_FLAG(String INS_FLAG){
        this.INS_FLAG=INS_FLAG;
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
	/**
	 * @return the sPCL_TECH_ID
	 */
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	/**
	 * @param sPCLTECHID the sPCL_TECH_ID to set
	 */
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}
    
}