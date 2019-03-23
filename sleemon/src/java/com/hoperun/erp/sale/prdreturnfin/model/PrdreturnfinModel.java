/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnModel
*/
package com.hoperun.erp.sale.prdreturnfin.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public class PrdreturnfinModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 3766527965598410168L;
/** 退货单ID **/
   private String PRD_RETURN_ID;
   /** 退货单编号 **/
   private String PRD_RETURN_NO;
   /** 单据类型 **/
   private String BILL_TYPE;
   /** 来源单据ID **/
   private String FROM_BILL_ID;
   /** 来源单据编号 **/
   private String FROM_BILL_NO;
   /** 退货方ID **/
   private String RETURN_CHANN_ID;
   /** 退货方编号 **/
   private String RETURN_CHANN_NO;
   /** 退货方名称 **/
   private String RETURN_CHANN_NAME;
   /** 收货方ID **/
   private String RECV_CHANN_ID;
   /** 收货方编号 **/
   private String RECV_CHANN_NO;
   /** 收货方名称 **/
   private String RECV_CHANN_NAME;
   /** 预计退货日期 **/
   private String EXPECT_RETURNDATE;
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
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 退货单ID value
     * @return the PRD_RETURN_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getPRD_RETURN_ID(){
        return PRD_RETURN_ID;
    }
	/**
     * set 退货单ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setPRD_RETURN_ID(String PRD_RETURN_ID){
        this.PRD_RETURN_ID=PRD_RETURN_ID;
    }
     /**
     * get 退货单编号 value
     * @return the PRD_RETURN_NO
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getPRD_RETURN_NO(){
        return PRD_RETURN_NO;
    }
	/**
     * set 退货单编号 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setPRD_RETURN_NO(String PRD_RETURN_NO){
        this.PRD_RETURN_NO=PRD_RETURN_NO;
    }
     /**
     * get 单据类型 value
     * @return the BILL_TYPE
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getBILL_TYPE(){
        return BILL_TYPE;
    }
	/**
     * set 单据类型 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setBILL_TYPE(String BILL_TYPE){
        this.BILL_TYPE=BILL_TYPE;
    }
     /**
     * get 来源单据ID value
     * @return the FROM_BILL_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getFROM_BILL_ID(){
        return FROM_BILL_ID;
    }
	/**
     * set 来源单据ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setFROM_BILL_ID(String FROM_BILL_ID){
        this.FROM_BILL_ID=FROM_BILL_ID;
    }
     /**
     * get 来源单据编号 value
     * @return the FROM_BILL_NO
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getFROM_BILL_NO(){
        return FROM_BILL_NO;
    }
	/**
     * set 来源单据编号 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setFROM_BILL_NO(String FROM_BILL_NO){
        this.FROM_BILL_NO=FROM_BILL_NO;
    }
     /**
     * get 退货方ID value
     * @return the RETURN_CHANN_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getRETURN_CHANN_ID(){
        return RETURN_CHANN_ID;
    }
	/**
     * set 退货方ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setRETURN_CHANN_ID(String RETURN_CHANN_ID){
        this.RETURN_CHANN_ID=RETURN_CHANN_ID;
    }
     /**
     * get 退货方编号 value
     * @return the RETURN_CHANN_NO
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getRETURN_CHANN_NO(){
        return RETURN_CHANN_NO;
    }
	/**
     * set 退货方编号 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setRETURN_CHANN_NO(String RETURN_CHANN_NO){
        this.RETURN_CHANN_NO=RETURN_CHANN_NO;
    }
     /**
     * get 退货方名称 value
     * @return the RETURN_CHANN_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getRETURN_CHANN_NAME(){
        return RETURN_CHANN_NAME;
    }
	/**
     * set 退货方名称 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setRETURN_CHANN_NAME(String RETURN_CHANN_NAME){
        this.RETURN_CHANN_NAME=RETURN_CHANN_NAME;
    }
     /**
     * get 收货方ID value
     * @return the RECV_CHANN_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getRECV_CHANN_ID(){
        return RECV_CHANN_ID;
    }
	/**
     * set 收货方ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setRECV_CHANN_ID(String RECV_CHANN_ID){
        this.RECV_CHANN_ID=RECV_CHANN_ID;
    }
     /**
     * get 收货方编号 value
     * @return the RECV_CHANN_NO
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getRECV_CHANN_NO(){
        return RECV_CHANN_NO;
    }
	/**
     * set 收货方编号 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setRECV_CHANN_NO(String RECV_CHANN_NO){
        this.RECV_CHANN_NO=RECV_CHANN_NO;
    }
     /**
     * get 收货方名称 value
     * @return the RECV_CHANN_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getRECV_CHANN_NAME(){
        return RECV_CHANN_NAME;
    }
	/**
     * set 收货方名称 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setRECV_CHANN_NAME(String RECV_CHANN_NAME){
        this.RECV_CHANN_NAME=RECV_CHANN_NAME;
    }
     /**
     * get 预计退货日期 value
     * @return the EXPECT_RETURNDATE
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getEXPECT_RETURNDATE(){
        return EXPECT_RETURNDATE;
    }
	/**
     * set 预计退货日期 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setEXPECT_RETURNDATE(String EXPECT_RETURNDATE){
        this.EXPECT_RETURNDATE=EXPECT_RETURNDATE;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人 value
     * @return the AUDIT_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-19 15:33:31
     * @author wzg
	 * @createdate 2013-08-19 15:33:31
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}