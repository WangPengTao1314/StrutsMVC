/**
 * prjName:喜临门营销平台
 * ucName:客户付款单
 * fileName:PaymentModel
*/
package com.hoperun.erp.sale.payment.model;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-15 09:31:13
 */
public class PaymentModel{
   /** 付款单ID **/
   private String PAYMENT_ID;
   /** 付款单编号 **/
   private String PAYMENT_NO;
   /** 渠道ID **/
   private String CHANN_ID;
   /** 渠道编号 **/
   private String CHANN_NO;
   /** 渠道名称 **/
   private String CHANN_NAME;
   /** 渠道简称 **/
   private String CHANN_ABBR;
   /** 渠道类型 **/
   private String CHANN_TYPE;
   /** 渠道级别 **/
   private String CHAA_LVL;
   /** 区域编号 **/
   private String AREA_NO;
   /** 区域名称 **/
   private String AREA_NAME;
   /** 付款金额 **/
   private String PAY_AMONT;
   /** 付款日期 **/
   private String PAY_DATE;
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
   /** 帐套ID **/
   private String LEDGER_ID;
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 付款单ID value
     * @return the PAYMENT_ID
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getPAYMENT_ID(){
        return PAYMENT_ID;
    }
	/**
     * set 付款单ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setPAYMENT_ID(String PAYMENT_ID){
        this.PAYMENT_ID=PAYMENT_ID;
    }
     /**
     * get 付款单编号 value
     * @return the PAYMENT_NO
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getPAYMENT_NO(){
        return PAYMENT_NO;
    }
	/**
     * set 付款单编号 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setPAYMENT_NO(String PAYMENT_NO){
        this.PAYMENT_NO=PAYMENT_NO;
    }
     /**
     * get 渠道ID value
     * @return the CHANN_ID
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 渠道ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 渠道编号 value
     * @return the CHANN_NO
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 渠道编号 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 渠道名称 value
     * @return the CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 渠道名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 渠道简称 value
     * @return the CHANN_ABBR
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCHANN_ABBR(){
        return CHANN_ABBR;
    }
	/**
     * set 渠道简称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCHANN_ABBR(String CHANN_ABBR){
        this.CHANN_ABBR=CHANN_ABBR;
    }
     /**
     * get 渠道类型 value
     * @return the CHANN_TYPE
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCHANN_TYPE(){
        return CHANN_TYPE;
    }
	/**
     * set 渠道类型 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCHANN_TYPE(String CHANN_TYPE){
        this.CHANN_TYPE=CHANN_TYPE;
    }
     /**
     * get 渠道级别 value
     * @return the CHAA_LVL
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCHAA_LVL(){
        return CHAA_LVL;
    }
	/**
     * set 渠道级别 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCHAA_LVL(String CHAA_LVL){
        this.CHAA_LVL=CHAA_LVL;
    }
     /**
     * get 区域编号 value
     * @return the AREA_NO
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getAREA_NO(){
        return AREA_NO;
    }
	/**
     * set 区域编号 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setAREA_NO(String AREA_NO){
        this.AREA_NO=AREA_NO;
    }
     /**
     * get 区域名称 value
     * @return the AREA_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getAREA_NAME(){
        return AREA_NAME;
    }
	/**
     * set 区域名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setAREA_NAME(String AREA_NAME){
        this.AREA_NAME=AREA_NAME;
    }
     /**
     * get 付款金额 value
     * @return the PAY_AMONT
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getPAY_AMONT(){
        return PAY_AMONT;
    }
	/**
     * set 付款金额 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setPAY_AMONT(String PAY_AMONT){
        this.PAY_AMONT=PAY_AMONT;
    }
     /**
     * get 付款日期 value
     * @return the PAY_DATE
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getPAY_DATE(){
        return PAY_DATE;
    }
	/**
     * set 付款日期 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setPAY_DATE(String PAY_DATE){
        this.PAY_DATE=PAY_DATE;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-15 09:31:13
     * @author glw
	 * @createdate 2013-08-15 09:31:13
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}