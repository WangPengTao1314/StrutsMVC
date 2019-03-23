/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappModel
*/
package com.hoperun.drp.sale.advcgoodsapp.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-11-02 18:55:53
 */
public class AdvcgoodsappModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1030197827801852843L;
/** 预订单发货申请ID **/
   private String ADVC_SEND_REQ_ID;
   /** 发货申请单编号 **/
   private String ADVC_SEND_REQ_NO;
   /** 单据类型 **/
   private String BILL_TYPE;
   /** 发货方ID **/
   private String SEND_CHANN_ID;
   /** 发货方编号 **/
   private String SEND_CHANN_NO;
   /** 发货方名称 **/
   private String SEND_CHANN_NAME;
   /** 出库库房ID **/
   private String STOREOUT_STORE_ID;
   /** 出库库房编号 **/
   private String STOREOUT_STORE_NO;
   /** 出库库房名称 **/
   private String STOREOUT_STORE_NAME;
   /** 库位管理标记 **/
   private String STORAGE_FLAG;
   /** 来源单据ID **/
   private String FROM_BILL_ID;
   /** 来源单据编号 **/
   private String FROM_BILL_NO;
   /** 终端信息ID **/
   private String TERM_ID;
   /** 终端编号 **/
   private String TERM_NO;
   /** 终端名称 **/
   private String TERM_NAME;
   /** 销售日期 **/
   private String SALE_DATE;
   /** 业务员ID **/
   private String SALE_PSON_ID;
   /** 业务员 **/
   private String SALE_PSON_NAME;
   /** 客户姓名 **/
   private String CUST_NAME;
   /** 电话 **/
   private String TEL;
   /** 收货地址 **/
   private String RECV_ADDR;
   /** 要求到货日期 **/
   private String ORDER_RECV_DATE;
   /** 出库总金额 **/
   private String STOREOUT_AMOUNT;
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
   /** 帐套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 发货类型 **/
   private String SEND_TYPE;
   /** 订金金额 **/
   private String ADVC_AMOUNT;
   /** 应收总额 **/
   private String PAYABLE_AMOUNT;
   /** 已付款金额 **/
   private String PAYED_TOTAL_AMOUNT;
   /** 合同号 **/
   private String CONTRACT_NO;
   
   
     /**
     * get 预订单发货申请ID value
     * @return the ADVC_SEND_REQ_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getADVC_SEND_REQ_ID(){
        return ADVC_SEND_REQ_ID;
    }
	/**
     * set 预订单发货申请ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setADVC_SEND_REQ_ID(String ADVC_SEND_REQ_ID){
        this.ADVC_SEND_REQ_ID=ADVC_SEND_REQ_ID;
    }
     /**
     * get 发货申请单编号 value
     * @return the ADVC_SEND_REQ_NO
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getADVC_SEND_REQ_NO(){
        return ADVC_SEND_REQ_NO;
    }
	/**
     * set 发货申请单编号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setADVC_SEND_REQ_NO(String ADVC_SEND_REQ_NO){
        this.ADVC_SEND_REQ_NO=ADVC_SEND_REQ_NO;
    }
     /**
     * get 单据类型 value
     * @return the BILL_TYPE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getBILL_TYPE(){
        return BILL_TYPE;
    }
	/**
     * set 单据类型 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setBILL_TYPE(String BILL_TYPE){
        this.BILL_TYPE=BILL_TYPE;
    }
     /**
     * get 发货方ID value
     * @return the SEND_CHANN_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSEND_CHANN_ID(){
        return SEND_CHANN_ID;
    }
	/**
     * set 发货方ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSEND_CHANN_ID(String SEND_CHANN_ID){
        this.SEND_CHANN_ID=SEND_CHANN_ID;
    }
     /**
     * get 发货方编号 value
     * @return the SEND_CHANN_NO
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSEND_CHANN_NO(){
        return SEND_CHANN_NO;
    }
	/**
     * set 发货方编号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSEND_CHANN_NO(String SEND_CHANN_NO){
        this.SEND_CHANN_NO=SEND_CHANN_NO;
    }
     /**
     * get 发货方名称 value
     * @return the SEND_CHANN_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSEND_CHANN_NAME(){
        return SEND_CHANN_NAME;
    }
	/**
     * set 发货方名称 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSEND_CHANN_NAME(String SEND_CHANN_NAME){
        this.SEND_CHANN_NAME=SEND_CHANN_NAME;
    }
     /**
     * get 出库库房ID value
     * @return the STOREOUT_STORE_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTOREOUT_STORE_ID(){
        return STOREOUT_STORE_ID;
    }
	/**
     * set 出库库房ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTOREOUT_STORE_ID(String STOREOUT_STORE_ID){
        this.STOREOUT_STORE_ID=STOREOUT_STORE_ID;
    }
     /**
     * get 出库库房编号 value
     * @return the STOREOUT_STORE_NO
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTOREOUT_STORE_NO(){
        return STOREOUT_STORE_NO;
    }
	/**
     * set 出库库房编号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTOREOUT_STORE_NO(String STOREOUT_STORE_NO){
        this.STOREOUT_STORE_NO=STOREOUT_STORE_NO;
    }
     /**
     * get 出库库房名称 value
     * @return the STOREOUT_STORE_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTOREOUT_STORE_NAME(){
        return STOREOUT_STORE_NAME;
    }
	/**
     * set 出库库房名称 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTOREOUT_STORE_NAME(String STOREOUT_STORE_NAME){
        this.STOREOUT_STORE_NAME=STOREOUT_STORE_NAME;
    }
     /**
     * get 库位管理标记 value
     * @return the STORAGE_FLAG
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTORAGE_FLAG(){
        return STORAGE_FLAG;
    }
	/**
     * set 库位管理标记 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTORAGE_FLAG(String STORAGE_FLAG){
        this.STORAGE_FLAG=STORAGE_FLAG;
    }
     /**
     * get 来源单据ID value
     * @return the FROM_BILL_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getFROM_BILL_ID(){
        return FROM_BILL_ID;
    }
	/**
     * set 来源单据ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setFROM_BILL_ID(String FROM_BILL_ID){
        this.FROM_BILL_ID=FROM_BILL_ID;
    }
     /**
     * get 来源单据编号 value
     * @return the FROM_BILL_NO
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getFROM_BILL_NO(){
        return FROM_BILL_NO;
    }
	/**
     * set 来源单据编号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setFROM_BILL_NO(String FROM_BILL_NO){
        this.FROM_BILL_NO=FROM_BILL_NO;
    }
     /**
     * get 终端信息ID value
     * @return the TERM_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getTERM_ID(){
        return TERM_ID;
    }
	/**
     * set 终端信息ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setTERM_ID(String TERM_ID){
        this.TERM_ID=TERM_ID;
    }
     /**
     * get 终端编号 value
     * @return the TERM_NO
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getTERM_NO(){
        return TERM_NO;
    }
	/**
     * set 终端编号 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setTERM_NO(String TERM_NO){
        this.TERM_NO=TERM_NO;
    }
     /**
     * get 终端名称 value
     * @return the TERM_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getTERM_NAME(){
        return TERM_NAME;
    }
	/**
     * set 终端名称 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setTERM_NAME(String TERM_NAME){
        this.TERM_NAME=TERM_NAME;
    }
     /**
     * get 销售日期 value
     * @return the SALE_DATE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSALE_DATE(){
        return SALE_DATE;
    }
	/**
     * set 销售日期 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSALE_DATE(String SALE_DATE){
        this.SALE_DATE=SALE_DATE;
    }
     /**
     * get 业务员ID value
     * @return the SALE_PSON_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSALE_PSON_ID(){
        return SALE_PSON_ID;
    }
	/**
     * set 业务员ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSALE_PSON_ID(String SALE_PSON_ID){
        this.SALE_PSON_ID=SALE_PSON_ID;
    }
     /**
     * get 业务员 value
     * @return the SALE_PSON_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSALE_PSON_NAME(){
        return SALE_PSON_NAME;
    }
	/**
     * set 业务员 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSALE_PSON_NAME(String SALE_PSON_NAME){
        this.SALE_PSON_NAME=SALE_PSON_NAME;
    }
     /**
     * get 客户姓名 value
     * @return the CUST_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getCUST_NAME(){
        return CUST_NAME;
    }
	/**
     * set 客户姓名 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setCUST_NAME(String CUST_NAME){
        this.CUST_NAME=CUST_NAME;
    }
     /**
     * get 电话 value
     * @return the TEL
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getTEL(){
        return TEL;
    }
	/**
     * set 电话 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setTEL(String TEL){
        this.TEL=TEL;
    }
     /**
     * get 收货地址 value
     * @return the RECV_ADDR
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getRECV_ADDR(){
        return RECV_ADDR;
    }
	/**
     * set 收货地址 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setRECV_ADDR(String RECV_ADDR){
        this.RECV_ADDR=RECV_ADDR;
    }
     /**
     * get 要求到货日期 value
     * @return the ORDER_RECV_DATE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getORDER_RECV_DATE(){
        return ORDER_RECV_DATE;
    }
	/**
     * set 要求到货日期 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setORDER_RECV_DATE(String ORDER_RECV_DATE){
        this.ORDER_RECV_DATE=ORDER_RECV_DATE;
    }
     /**
     * get 出库总金额 value
     * @return the STOREOUT_AMOUNT
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTOREOUT_AMOUNT(){
        return STOREOUT_AMOUNT;
    }
	/**
     * set 出库总金额 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTOREOUT_AMOUNT(String STOREOUT_AMOUNT){
        this.STOREOUT_AMOUNT=STOREOUT_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-11-02 18:55:53
     * @author lyg
	 * @createdate 2013-11-02 18:55:53
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	/**
	 * @return the sEND_TYPE
	 */
	public String getSEND_TYPE() {
		return SEND_TYPE;
	}
	/**
	 * @param sENDTYPE the sEND_TYPE to set
	 */
	public void setSEND_TYPE(String sENDTYPE) {
		SEND_TYPE = sENDTYPE;
	}
	/**
	 * @return the aDVC_AMOUNT
	 */
	public String getADVC_AMOUNT() {
		return ADVC_AMOUNT;
	}
	/**
	 * @param aDVCAMOUNT the aDVC_AMOUNT to set
	 */
	public void setADVC_AMOUNT(String aDVCAMOUNT) {
		ADVC_AMOUNT = aDVCAMOUNT;
	}
	/**
	 * @return the pAYABLE_AMOUNT
	 */
	public String getPAYABLE_AMOUNT() {
		return PAYABLE_AMOUNT;
	}
	/**
	 * @param pAYABLEAMOUNT the pAYABLE_AMOUNT to set
	 */
	public void setPAYABLE_AMOUNT(String pAYABLEAMOUNT) {
		PAYABLE_AMOUNT = pAYABLEAMOUNT;
	}
	/**
	 * @return the pAYED_TOTAL_AMOUNT
	 */
	public String getPAYED_TOTAL_AMOUNT() {
		return PAYED_TOTAL_AMOUNT;
	}
	/**
	 * @param pAYEDTOTALAMOUNT the pAYED_TOTAL_AMOUNT to set
	 */
	public void setPAYED_TOTAL_AMOUNT(String pAYEDTOTALAMOUNT) {
		PAYED_TOTAL_AMOUNT = pAYEDTOTALAMOUNT;
	}
	public String getCONTRACT_NO() {
		return CONTRACT_NO;
	}
	public void setCONTRACT_NO(String cONTRACTNO) {
		CONTRACT_NO = cONTRACTNO;
	}
    
}