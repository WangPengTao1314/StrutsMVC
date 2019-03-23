/**
 * prjName:喜临门营销平台
 * ucName:付款凭证上传
 * fileName:PaymentupModel
*/
package com.hoperun.drp.sale.paymentup.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-23 10:25:58
 */
public class PaymentupModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 6577607452250698458L;
/** 付款凭证上传ID **/
   private String PAYMENT_UPLOAD_ID;
   /** 付款凭证编号 **/
   private String PAYMENT_UPLOAD_NO;
   /** 凭证号 **/
   private String PAYMENT_NO;
   /** 渠道ID **/
   private String CHANN_ID;
   /** 渠道编号 **/
   private String CHANN_NO;
   /** 所属渠道 **/
   private String CHANN_NAME;
   /** 金额 **/
   private String PAYMENT_AMOUNT;
   /** 区域ID **/
   private String AREA_ID;
   /** 区域编号 **/
   private String AREA_NO;
   /** 所属区域 **/
   private String AREA_NAME;
   /** 申请人ID **/
   private String REQ_PSON_ID;
   /** 申请人 **/
   private String REQ_PSON_NAME;
   /** 联系方式 **/
   private String TEL;
   /** 付款凭证路径 **/
   private String PAYMENT_PATH;
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
     * get 付款凭证上传ID value
     * @return the PAYMENT_UPLOAD_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getPAYMENT_UPLOAD_ID(){
        return PAYMENT_UPLOAD_ID;
    }
	/**
     * set 付款凭证上传ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setPAYMENT_UPLOAD_ID(String PAYMENT_UPLOAD_ID){
        this.PAYMENT_UPLOAD_ID=PAYMENT_UPLOAD_ID;
    }
     /**
     * get 付款凭证编号 value
     * @return the PAYMENT_UPLOAD_NO
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getPAYMENT_UPLOAD_NO(){
        return PAYMENT_UPLOAD_NO;
    }
	/**
     * set 付款凭证编号 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setPAYMENT_UPLOAD_NO(String PAYMENT_UPLOAD_NO){
        this.PAYMENT_UPLOAD_NO=PAYMENT_UPLOAD_NO;
    }
     /**
     * get 凭证号 value
     * @return the PAYMENT_NO
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getPAYMENT_NO(){
        return PAYMENT_NO;
    }
	/**
     * set 凭证号 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setPAYMENT_NO(String PAYMENT_NO){
        this.PAYMENT_NO=PAYMENT_NO;
    }
     /**
     * get 渠道ID value
     * @return the CHANN_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 渠道ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 渠道编号 value
     * @return the CHANN_NO
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 渠道编号 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 所属渠道 value
     * @return the CHANN_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 所属渠道 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 金额 value
     * @return the PAYMENT_AMOUNT
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getPAYMENT_AMOUNT(){
        return PAYMENT_AMOUNT;
    }
	/**
     * set 金额 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setPAYMENT_AMOUNT(String PAYMENT_AMOUNT){
        this.PAYMENT_AMOUNT=PAYMENT_AMOUNT;
    }
     /**
     * get 区域ID value
     * @return the AREA_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getAREA_ID(){
        return AREA_ID;
    }
	/**
     * set 区域ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setAREA_ID(String AREA_ID){
        this.AREA_ID=AREA_ID;
    }
     /**
     * get 区域编号 value
     * @return the AREA_NO
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getAREA_NO(){
        return AREA_NO;
    }
	/**
     * set 区域编号 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setAREA_NO(String AREA_NO){
        this.AREA_NO=AREA_NO;
    }
     /**
     * get 所属区域 value
     * @return the AREA_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getAREA_NAME(){
        return AREA_NAME;
    }
	/**
     * set 所属区域 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setAREA_NAME(String AREA_NAME){
        this.AREA_NAME=AREA_NAME;
    }
     /**
     * get 申请人ID value
     * @return the REQ_PSON_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getREQ_PSON_ID(){
        return REQ_PSON_ID;
    }
	/**
     * set 申请人ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setREQ_PSON_ID(String REQ_PSON_ID){
        this.REQ_PSON_ID=REQ_PSON_ID;
    }
     /**
     * get 申请人 value
     * @return the REQ_PSON_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getREQ_PSON_NAME(){
        return REQ_PSON_NAME;
    }
	/**
     * set 申请人 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setREQ_PSON_NAME(String REQ_PSON_NAME){
        this.REQ_PSON_NAME=REQ_PSON_NAME;
    }
     /**
     * get 联系方式 value
     * @return the TEL
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getTEL(){
        return TEL;
    }
	/**
     * set 联系方式 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setTEL(String TEL){
        this.TEL=TEL;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人 value
     * @return the AUDIT_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 账套 value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 账套 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 账套名称 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 账套名称 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-23 10:25:58
     * @author lyg
	 * @createdate 2013-08-23 10:25:58
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	/**
	 * @return the pAYMENT_PATH
	 */
	public String getPAYMENT_PATH() {
		return PAYMENT_PATH;
	}
	/**
	 * @param pAYMENTPATH the pAYMENT_PATH to set
	 */
	public void setPAYMENT_PATH(String pAYMENTPATH) {
		PAYMENT_PATH = pAYMENTPATH;
	}
    
}