/**
 * prjName:喜临门营销平台
 * ucName:促销品发放
 * fileName:PrmtexitModel
*/
package com.hoperun.erp.sale.prmtexit.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-19 16:54:28
 */
public class PrmtexitModel extends BaseModel{
   /** 促销品发放ID **/
   private String PRMT_GOODS_EXTD_ID;
   /** 促销品发放编号 **/
   private String PRMT_GOODS_EXTD_NO;
   /** 促销方案ID **/
   private String PRMT_PLAN_ID;
   /** 促销方案编号 **/
   private String PRMT_PLAN_NO;
   /** 促销方案名称 **/
   private String PRMT_PLAN_NAME;
   /** 渠道ID **/
   private String CHANN_ID;
   /** 渠道编号 **/
   private String CHANN_NO;
   /** 渠道名称 **/
   private String CHANN_NAME;
   /** 统计日期从 **/
   private String COUNT_DATE_BEG;
   /** 统计日期到 **/
   private String COUNT_DATE_END;
   /** 销售金额 **/
   private String SALE_AMOUNT;
   /** 发放人ID **/
   private String EXTD_PSON_ID;
   /** 发放人名称 **/
   private String EXTD_PSON_NAME;
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
     /**
     * get 促销品发放ID value
     * @return the PRMT_GOODS_EXTD_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_GOODS_EXTD_ID(){
        return PRMT_GOODS_EXTD_ID;
    }
	/**
     * set 促销品发放ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_GOODS_EXTD_ID(String PRMT_GOODS_EXTD_ID){
        this.PRMT_GOODS_EXTD_ID=PRMT_GOODS_EXTD_ID;
    }
     /**
     * get 促销品发放编号 value
     * @return the PRMT_GOODS_EXTD_NO
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_GOODS_EXTD_NO(){
        return PRMT_GOODS_EXTD_NO;
    }
	/**
     * set 促销品发放编号 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_GOODS_EXTD_NO(String PRMT_GOODS_EXTD_NO){
        this.PRMT_GOODS_EXTD_NO=PRMT_GOODS_EXTD_NO;
    }
     /**
     * get 促销方案ID value
     * @return the PRMT_PLAN_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_PLAN_ID(){
        return PRMT_PLAN_ID;
    }
	/**
     * set 促销方案ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_PLAN_ID(String PRMT_PLAN_ID){
        this.PRMT_PLAN_ID=PRMT_PLAN_ID;
    }
     /**
     * get 促销方案编号 value
     * @return the PRMT_PLAN_NO
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_PLAN_NO(){
        return PRMT_PLAN_NO;
    }
	/**
     * set 促销方案编号 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_PLAN_NO(String PRMT_PLAN_NO){
        this.PRMT_PLAN_NO=PRMT_PLAN_NO;
    }
     /**
     * get 促销方案名称 value
     * @return the PRMT_PLAN_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_PLAN_NAME(){
        return PRMT_PLAN_NAME;
    }
	/**
     * set 促销方案名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_PLAN_NAME(String PRMT_PLAN_NAME){
        this.PRMT_PLAN_NAME=PRMT_PLAN_NAME;
    }
     /**
     * get 渠道ID value
     * @return the CHANN_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 渠道ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 渠道编号 value
     * @return the CHANN_NO
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 渠道编号 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 渠道名称 value
     * @return the CHANN_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 渠道名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 统计日期从 value
     * @return the COUNT_DATE_BEG
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCOUNT_DATE_BEG(){
        return COUNT_DATE_BEG;
    }
	/**
     * set 统计日期从 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCOUNT_DATE_BEG(String COUNT_DATE_BEG){
        this.COUNT_DATE_BEG=COUNT_DATE_BEG;
    }
     /**
     * get 统计日期到 value
     * @return the COUNT_DATE_END
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCOUNT_DATE_END(){
        return COUNT_DATE_END;
    }
	/**
     * set 统计日期到 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCOUNT_DATE_END(String COUNT_DATE_END){
        this.COUNT_DATE_END=COUNT_DATE_END;
    }
     /**
     * get 销售金额 value
     * @return the SALE_AMOUNT
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getSALE_AMOUNT(){
        return SALE_AMOUNT;
    }
	/**
     * set 销售金额 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setSALE_AMOUNT(String SALE_AMOUNT){
        this.SALE_AMOUNT=SALE_AMOUNT;
    }
     /**
     * get 发放人ID value
     * @return the EXTD_PSON_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getEXTD_PSON_ID(){
        return EXTD_PSON_ID;
    }
	/**
     * set 发放人ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setEXTD_PSON_ID(String EXTD_PSON_ID){
        this.EXTD_PSON_ID=EXTD_PSON_ID;
    }
     /**
     * get 发放人名称 value
     * @return the EXTD_PSON_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getEXTD_PSON_NAME(){
        return EXTD_PSON_NAME;
    }
	/**
     * set 发放人名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setEXTD_PSON_NAME(String EXTD_PSON_NAME){
        this.EXTD_PSON_NAME=EXTD_PSON_NAME;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 账套 value
     * @return the LEDGER_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 账套 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 账套名称 value
     * @return the LEDGER_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 账套名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}