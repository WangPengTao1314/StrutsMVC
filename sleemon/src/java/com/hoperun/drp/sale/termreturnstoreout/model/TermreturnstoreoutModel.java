/**
 * prjName:喜临门营销平台
 * ucName:门店-退货出库单
 * fileName:TermreturnstoreoutModel
*/
package com.hoperun.drp.sale.termreturnstoreout.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-31 10:42:20
 */
public class TermreturnstoreoutModel extends BaseModel{
   /** 退货出库单ID **/
   private String STOREOUT_ID;
   /** 退货出库通知单编号 **/
   private String STOREOUT_NO;
   /** 来源单据ID **/
   private String FROM_BILL_ID;
   /** 来源单据编号 **/
   private String FROM_BILL_NO;
   /** 出库库房ID **/
   private String STOREOUT_STORE_ID;
   /** 出库库房编号 **/
   private String STOREOUT_STORE_NO;
   /** 出库库房名称 **/
   private String STOREOUT_STORE_NAME;
   /** 销售日期 **/
   private String SALE_DATE;
   /** 收货地址 **/
   private String RECV_ADDR;
   /** 收货方ID **/
   private String RECV_CHANN_ID;
   /** 收货方编号 **/
   private String RECV_CHANN_NO;
   /** 收货方名称 **/
   private String RECV_CHANN_NAME;
   /** 出库总金额 **/
   private String STOREOUT_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 年份月份 **/
   private String YEAR_MONTH;
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
     * get 退货出库单ID value
     * @return the STOREOUT_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_ID(){
        return STOREOUT_ID;
    }
	/**
     * set 退货出库单ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_ID(String STOREOUT_ID){
        this.STOREOUT_ID=STOREOUT_ID;
    }
     /**
     * get 退货出库通知单编号 value
     * @return the STOREOUT_NO
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_NO(){
        return STOREOUT_NO;
    }
	/**
     * set 退货出库通知单编号 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_NO(String STOREOUT_NO){
        this.STOREOUT_NO=STOREOUT_NO;
    }
     /**
     * get 来源单据ID value
     * @return the FROM_BILL_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getFROM_BILL_ID(){
        return FROM_BILL_ID;
    }
	/**
     * set 来源单据ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setFROM_BILL_ID(String FROM_BILL_ID){
        this.FROM_BILL_ID=FROM_BILL_ID;
    }
     /**
     * get 来源单据编号 value
     * @return the FROM_BILL_NO
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getFROM_BILL_NO(){
        return FROM_BILL_NO;
    }
	/**
     * set 来源单据编号 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setFROM_BILL_NO(String FROM_BILL_NO){
        this.FROM_BILL_NO=FROM_BILL_NO;
    }
     /**
     * get 出库库房ID value
     * @return the STOREOUT_STORE_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_STORE_ID(){
        return STOREOUT_STORE_ID;
    }
	/**
     * set 出库库房ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_STORE_ID(String STOREOUT_STORE_ID){
        this.STOREOUT_STORE_ID=STOREOUT_STORE_ID;
    }
     /**
     * get 出库库房编号 value
     * @return the STOREOUT_STORE_NO
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_STORE_NO(){
        return STOREOUT_STORE_NO;
    }
	/**
     * set 出库库房编号 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_STORE_NO(String STOREOUT_STORE_NO){
        this.STOREOUT_STORE_NO=STOREOUT_STORE_NO;
    }
     /**
     * get 出库库房名称 value
     * @return the STOREOUT_STORE_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_STORE_NAME(){
        return STOREOUT_STORE_NAME;
    }
	/**
     * set 出库库房名称 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_STORE_NAME(String STOREOUT_STORE_NAME){
        this.STOREOUT_STORE_NAME=STOREOUT_STORE_NAME;
    }
     /**
     * get 销售日期 value
     * @return the SALE_DATE
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSALE_DATE(){
        return SALE_DATE;
    }
	/**
     * set 销售日期 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSALE_DATE(String SALE_DATE){
        this.SALE_DATE=SALE_DATE;
    }
     /**
     * get 收货地址 value
     * @return the RECV_ADDR
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getRECV_ADDR(){
        return RECV_ADDR;
    }
	/**
     * set 收货地址 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setRECV_ADDR(String RECV_ADDR){
        this.RECV_ADDR=RECV_ADDR;
    }
     /**
     * get 收货方ID value
     * @return the RECV_CHANN_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getRECV_CHANN_ID(){
        return RECV_CHANN_ID;
    }
	/**
     * set 收货方ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setRECV_CHANN_ID(String RECV_CHANN_ID){
        this.RECV_CHANN_ID=RECV_CHANN_ID;
    }
     /**
     * get 收货方编号 value
     * @return the RECV_CHANN_NO
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getRECV_CHANN_NO(){
        return RECV_CHANN_NO;
    }
	/**
     * set 收货方编号 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setRECV_CHANN_NO(String RECV_CHANN_NO){
        this.RECV_CHANN_NO=RECV_CHANN_NO;
    }
     /**
     * get 收货方名称 value
     * @return the RECV_CHANN_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getRECV_CHANN_NAME(){
        return RECV_CHANN_NAME;
    }
	/**
     * set 收货方名称 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setRECV_CHANN_NAME(String RECV_CHANN_NAME){
        this.RECV_CHANN_NAME=RECV_CHANN_NAME;
    }
     /**
     * get 出库总金额 value
     * @return the STOREOUT_AMOUNT
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTOREOUT_AMOUNT(){
        return STOREOUT_AMOUNT;
    }
	/**
     * set 出库总金额 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTOREOUT_AMOUNT(String STOREOUT_AMOUNT){
        this.STOREOUT_AMOUNT=STOREOUT_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 年份月份 value
     * @return the YEAR_MONTH
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getYEAR_MONTH(){
        return YEAR_MONTH;
    }
	/**
     * set 年份月份 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setYEAR_MONTH(String YEAR_MONTH){
        this.YEAR_MONTH=YEAR_MONTH;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-10-31 10:42:20
     * @author lyg
	 * @createdate 2014-10-31 10:42:20
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}