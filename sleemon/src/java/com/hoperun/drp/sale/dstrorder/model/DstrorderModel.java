/**
 * prjName:喜临门营销平台
 * ucName:分发指令接收
 * fileName:DstrorderModel
*/
package com.hoperun.drp.sale.dstrorder.model;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-16 10:31:37
 */
public class DstrorderModel{
   /** 分发指令接收ID **/
   private String DSTR_ORDER_ID;
   /** 分发指令接收编号 **/
   private String DSTR_ORDER_NO;
   /** 来源单据ID **/
   private String FROM_BILL_ID;
   /** 来源单据编号 **/
   private String FROM_BILL_NO;
   /** 发货方ID **/
   private String SEND_CHANN_ID;
   /** 发货方编号 **/
   private String SEND_CHANN_NO;
   /** 发货方名称 **/
   private String SEND_CHANN_NAME;
   /** 订货方ID **/
   private String ORDER_CHANN_ID;
   /** 订货方编号 **/
   private String ORDER_CHANN_NO;
   /** 订货方名称 **/
   private String ORDER_CHANN_NAME;
   /** 收货方ID **/
   private String RECV_CHANN_ID;
   /** 收货方编号 **/
   private String RECV_CHANN_NO;
   /** 收货方名称 **/
   private String RECV_CHANN_NAME;
   /** 联系人 **/
   private String PERSON_CON;
   /** 电话 **/
   private String TEL;
   /** 收货地址 **/
   private String RECV_ADDR;
   /** 入库库房ID **/
   private String RECV_STORE_ID;
   /** 入库库房编号 **/
   private String RECV_STORE_NO;
   /** 入库库房名称 **/
   private String RECV_STORE_NAME;
   /** 分发方ID **/
   private String DSTR_CHANN_ID;
   /** 分发方编号 **/
   private String DSTR_CHANN_NO;
   /** 分发方名称 **/
   private String DSTR_CHANN_NAME;
   /** 接收人ID **/
   private String RECV_PSON_ID;
   /** 接收人名称 **/
   private String RECV_PSON_NAME;
   /** 接收时间 **/
   private String RECV_TIME;
   /** 制单人名称 **/
   private String CRE_NAME;
   /** 制单人ID **/
   private String CREATOR;
   /** 制单时间 **/
   private String CRE_TIME;
   /** 帐套ID **/
   private String LEDGER_ID;
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 分发指令接收ID value
     * @return the DSTR_ORDER_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getDSTR_ORDER_ID(){
        return DSTR_ORDER_ID;
    }
	/**
     * set 分发指令接收ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setDSTR_ORDER_ID(String DSTR_ORDER_ID){
        this.DSTR_ORDER_ID=DSTR_ORDER_ID;
    }
     /**
     * get 分发指令接收编号 value
     * @return the DSTR_ORDER_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getDSTR_ORDER_NO(){
        return DSTR_ORDER_NO;
    }
	/**
     * set 分发指令接收编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setDSTR_ORDER_NO(String DSTR_ORDER_NO){
        this.DSTR_ORDER_NO=DSTR_ORDER_NO;
    }
     /**
     * get 来源单据ID value
     * @return the FROM_BILL_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getFROM_BILL_ID(){
        return FROM_BILL_ID;
    }
	/**
     * set 来源单据ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setFROM_BILL_ID(String FROM_BILL_ID){
        this.FROM_BILL_ID=FROM_BILL_ID;
    }
     /**
     * get 来源单据编号 value
     * @return the FROM_BILL_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getFROM_BILL_NO(){
        return FROM_BILL_NO;
    }
	/**
     * set 来源单据编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setFROM_BILL_NO(String FROM_BILL_NO){
        this.FROM_BILL_NO=FROM_BILL_NO;
    }
     /**
     * get 发货方ID value
     * @return the SEND_CHANN_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getSEND_CHANN_ID(){
        return SEND_CHANN_ID;
    }
	/**
     * set 发货方ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setSEND_CHANN_ID(String SEND_CHANN_ID){
        this.SEND_CHANN_ID=SEND_CHANN_ID;
    }
     /**
     * get 发货方编号 value
     * @return the SEND_CHANN_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getSEND_CHANN_NO(){
        return SEND_CHANN_NO;
    }
	/**
     * set 发货方编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setSEND_CHANN_NO(String SEND_CHANN_NO){
        this.SEND_CHANN_NO=SEND_CHANN_NO;
    }
     /**
     * get 发货方名称 value
     * @return the SEND_CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getSEND_CHANN_NAME(){
        return SEND_CHANN_NAME;
    }
	/**
     * set 发货方名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setSEND_CHANN_NAME(String SEND_CHANN_NAME){
        this.SEND_CHANN_NAME=SEND_CHANN_NAME;
    }
     /**
     * get 订货方ID value
     * @return the ORDER_CHANN_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getORDER_CHANN_ID(){
        return ORDER_CHANN_ID;
    }
	/**
     * set 订货方ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setORDER_CHANN_ID(String ORDER_CHANN_ID){
        this.ORDER_CHANN_ID=ORDER_CHANN_ID;
    }
     /**
     * get 订货方编号 value
     * @return the ORDER_CHANN_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getORDER_CHANN_NO(){
        return ORDER_CHANN_NO;
    }
	/**
     * set 订货方编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setORDER_CHANN_NO(String ORDER_CHANN_NO){
        this.ORDER_CHANN_NO=ORDER_CHANN_NO;
    }
     /**
     * get 订货方名称 value
     * @return the ORDER_CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getORDER_CHANN_NAME(){
        return ORDER_CHANN_NAME;
    }
	/**
     * set 订货方名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setORDER_CHANN_NAME(String ORDER_CHANN_NAME){
        this.ORDER_CHANN_NAME=ORDER_CHANN_NAME;
    }
     /**
     * get 收货方ID value
     * @return the RECV_CHANN_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_CHANN_ID(){
        return RECV_CHANN_ID;
    }
	/**
     * set 收货方ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_CHANN_ID(String RECV_CHANN_ID){
        this.RECV_CHANN_ID=RECV_CHANN_ID;
    }
     /**
     * get 收货方编号 value
     * @return the RECV_CHANN_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_CHANN_NO(){
        return RECV_CHANN_NO;
    }
	/**
     * set 收货方编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_CHANN_NO(String RECV_CHANN_NO){
        this.RECV_CHANN_NO=RECV_CHANN_NO;
    }
     /**
     * get 收货方名称 value
     * @return the RECV_CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_CHANN_NAME(){
        return RECV_CHANN_NAME;
    }
	/**
     * set 收货方名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_CHANN_NAME(String RECV_CHANN_NAME){
        this.RECV_CHANN_NAME=RECV_CHANN_NAME;
    }
     /**
     * get 联系人 value
     * @return the PERSON_CON
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getPERSON_CON(){
        return PERSON_CON;
    }
	/**
     * set 联系人 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setPERSON_CON(String PERSON_CON){
        this.PERSON_CON=PERSON_CON;
    }
     /**
     * get 电话 value
     * @return the TEL
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getTEL(){
        return TEL;
    }
	/**
     * set 电话 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setTEL(String TEL){
        this.TEL=TEL;
    }
     /**
     * get 收货地址 value
     * @return the RECV_ADDR
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_ADDR(){
        return RECV_ADDR;
    }
	/**
     * set 收货地址 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_ADDR(String RECV_ADDR){
        this.RECV_ADDR=RECV_ADDR;
    }
     /**
     * get 入库库房ID value
     * @return the RECV_STORE_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_STORE_ID(){
        return RECV_STORE_ID;
    }
	/**
     * set 入库库房ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_STORE_ID(String RECV_STORE_ID){
        this.RECV_STORE_ID=RECV_STORE_ID;
    }
     /**
     * get 入库库房编号 value
     * @return the RECV_STORE_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_STORE_NO(){
        return RECV_STORE_NO;
    }
	/**
     * set 入库库房编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_STORE_NO(String RECV_STORE_NO){
        this.RECV_STORE_NO=RECV_STORE_NO;
    }
     /**
     * get 入库库房名称 value
     * @return the RECV_STORE_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_STORE_NAME(){
        return RECV_STORE_NAME;
    }
	/**
     * set 入库库房名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_STORE_NAME(String RECV_STORE_NAME){
        this.RECV_STORE_NAME=RECV_STORE_NAME;
    }
     /**
     * get 分发方ID value
     * @return the DSTR_CHANN_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getDSTR_CHANN_ID(){
        return DSTR_CHANN_ID;
    }
	/**
     * set 分发方ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setDSTR_CHANN_ID(String DSTR_CHANN_ID){
        this.DSTR_CHANN_ID=DSTR_CHANN_ID;
    }
     /**
     * get 分发方编号 value
     * @return the DSTR_CHANN_NO
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getDSTR_CHANN_NO(){
        return DSTR_CHANN_NO;
    }
	/**
     * set 分发方编号 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setDSTR_CHANN_NO(String DSTR_CHANN_NO){
        this.DSTR_CHANN_NO=DSTR_CHANN_NO;
    }
     /**
     * get 分发方名称 value
     * @return the DSTR_CHANN_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getDSTR_CHANN_NAME(){
        return DSTR_CHANN_NAME;
    }
	/**
     * set 分发方名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setDSTR_CHANN_NAME(String DSTR_CHANN_NAME){
        this.DSTR_CHANN_NAME=DSTR_CHANN_NAME;
    }
     /**
     * get 接收人ID value
     * @return the RECV_PSON_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_PSON_ID(){
        return RECV_PSON_ID;
    }
	/**
     * set 接收人ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_PSON_ID(String RECV_PSON_ID){
        this.RECV_PSON_ID=RECV_PSON_ID;
    }
     /**
     * get 接收人名称 value
     * @return the RECV_PSON_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_PSON_NAME(){
        return RECV_PSON_NAME;
    }
	/**
     * set 接收人名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_PSON_NAME(String RECV_PSON_NAME){
        this.RECV_PSON_NAME=RECV_PSON_NAME;
    }
     /**
     * get 接收时间 value
     * @return the RECV_TIME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getRECV_TIME(){
        return RECV_TIME;
    }
	/**
     * set 接收时间 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setRECV_TIME(String RECV_TIME){
        this.RECV_TIME=RECV_TIME;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-16 10:31:37
     * @author glw
	 * @createdate 2013-08-16 10:31:37
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}