/**
 * prjName:喜临门营销平台
 * ucName:转储单
 * fileName:DumpModelChld
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
public class DumpModelChld extends BaseModel{
   /** 转储单明细ID **/
   private String DUMP_DTL_ID;
   /** 转储单ID **/
   private String DUMP_ID;
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
   /** 转出库位ID **/
   private String DUMP_OUT_STORG_ID;
   /** 转出库位编号 **/
   private String DUMP_OUT_STORG_NO;
   /** 转出库位名称 **/
   private String DUMP_OUT_STORG_NAME;
   /** 转入库位ID **/
   private String DUMP_IN_STORG_ID;
   /** 转入库位编号 **/
   private String DUMP_IN_STORG_NO;
   /** 转入库位名称 **/
   private String DUMP_IN_STORG_NAME;
   /** 转储数量 **/
   private String DUMP_NUM;
   /** 备注 **/
   private String REMARK;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 特殊工艺ID **/
   private String SPCL_TECH_ID;
   /**关联明细ID **/
   private String RELT_BILL_DTL_ID;
     /**
     * get 转储单明细ID value
     * @return the DUMP_DTL_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_DTL_ID(){
        return DUMP_DTL_ID;
    }
	/**
     * set 转储单明细ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_DTL_ID(String DUMP_DTL_ID){
        this.DUMP_DTL_ID=DUMP_DTL_ID;
    }
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
     * get 货品ID value
     * @return the PRD_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 转出库位ID value
     * @return the DUMP_OUT_STORG_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORG_ID(){
        return DUMP_OUT_STORG_ID;
    }
	/**
     * set 转出库位ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORG_ID(String DUMP_OUT_STORG_ID){
        this.DUMP_OUT_STORG_ID=DUMP_OUT_STORG_ID;
    }
     /**
     * get 转出库位编号 value
     * @return the DUMP_OUT_STORG_NO
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORG_NO(){
        return DUMP_OUT_STORG_NO;
    }
	/**
     * set 转出库位编号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORG_NO(String DUMP_OUT_STORG_NO){
        this.DUMP_OUT_STORG_NO=DUMP_OUT_STORG_NO;
    }
     /**
     * get 转出库位名称 value
     * @return the DUMP_OUT_STORG_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_OUT_STORG_NAME(){
        return DUMP_OUT_STORG_NAME;
    }
	/**
     * set 转出库位名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_OUT_STORG_NAME(String DUMP_OUT_STORG_NAME){
        this.DUMP_OUT_STORG_NAME=DUMP_OUT_STORG_NAME;
    }
     /**
     * get 转入库位ID value
     * @return the DUMP_IN_STORG_ID
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_IN_STORG_ID(){
        return DUMP_IN_STORG_ID;
    }
	/**
     * set 转入库位ID value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_IN_STORG_ID(String DUMP_IN_STORG_ID){
        this.DUMP_IN_STORG_ID=DUMP_IN_STORG_ID;
    }
     /**
     * get 转入库位编号 value
     * @return the DUMP_IN_STORG_NO
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_IN_STORG_NO(){
        return DUMP_IN_STORG_NO;
    }
	/**
     * set 转入库位编号 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_IN_STORG_NO(String DUMP_IN_STORG_NO){
        this.DUMP_IN_STORG_NO=DUMP_IN_STORG_NO;
    }
     /**
     * get 转入库位名称 value
     * @return the DUMP_IN_STORG_NAME
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_IN_STORG_NAME(){
        return DUMP_IN_STORG_NAME;
    }
	/**
     * set 转入库位名称 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_IN_STORG_NAME(String DUMP_IN_STORG_NAME){
        this.DUMP_IN_STORG_NAME=DUMP_IN_STORG_NAME;
    }
     /**
     * get 转储数量 value
     * @return the DUMP_NUM
	 * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public String getDUMP_NUM(){
        return DUMP_NUM;
    }
	/**
     * set 转储数量 value
     * @createdate 2013-09-05 14:00:34
     * @author zzb
	 * @createdate 2013-09-05 14:00:34
     */
    public void setDUMP_NUM(String DUMP_NUM){
        this.DUMP_NUM=DUMP_NUM;
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
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	public void setSPCL_TECH_ID(String sPCLTECHID) {
		SPCL_TECH_ID = sPCLTECHID;
	}
	public String getRELT_BILL_DTL_ID() {
		return RELT_BILL_DTL_ID;
	}
	public void setRELT_BILL_DTL_ID(String rELTBILLDTLID) {
		RELT_BILL_DTL_ID = rELTBILLDTLID;
	}
    
}