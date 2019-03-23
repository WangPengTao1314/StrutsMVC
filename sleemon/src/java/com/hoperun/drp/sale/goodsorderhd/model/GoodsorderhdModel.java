/**
 * prjName:喜临门营销平台
 * ucName:订货订单维护
 * fileName:GoodsorderhdModel
*/
package com.hoperun.drp.sale.goodsorderhd.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-15 10:35:10
 */
public class GoodsorderhdModel extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 7034955609629647179L;
	
/** 订货订单ID **/
   private String GOODS_ORDER_ID;
   /** 订货订单编号 **/
   private String GOODS_ORDER_NO;
   /** 订货方ID **/
   private String ORDER_CHANN_ID;
   /** 订货方编号 **/
   private String ORDER_CHANN_NO;
   /** 订货方名称 **/
   private String ORDER_CHANN_NAME;
   /** 发货方ID **/
   private String SEND_CHANN_ID;
   /** 发货方编号 **/
   private String SEND_CHANN_NO;
   /** 发货方名称 **/
   private String SEND_CHANN_NAME;
   /** 订单类型 **/
   private String BILL_TYPE;
   /** 是否使用返利 **/
   private String IS_USE_REBATE;
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
   /** 备注 **/
   private String REMARK;
   /** 审核人ID **/
   private String AUDIT_ID;
   /** 审核人姓名 **/
   private String AUDIT_NAME;
   /** 审核时间 **/
   private String AUDIT_TIME;
   /** 发货点ID **/
   private String SHIP_POINT_ID;
   /** 发货点名称 **/
   private String SHIP_POINT_NAME;
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
   /** 区域ID **/
   private String AREA_ID;
   /** 区域编号 **/
   private String AREA_NO;
   /** 区域名称 **/
   private String AREA_NAME;
   /** 库房ID**/
   private String STORE_ID;
   /** 库房编号**/
   private String STORE_NO;
   /** 库房名称**/
   private String STORE_NAME;
   /** 收货地址编号 **/
   private String RECV_ADDR_NO;
   /** 要求到货日期 **/
   private String ORDER_RECV_DATE;
     /**
     * get 订货订单ID value
     * @return the GOODS_ORDER_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getGOODS_ORDER_ID(){
        return GOODS_ORDER_ID;
    }
	/**
     * set 订货订单ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setGOODS_ORDER_ID(String GOODS_ORDER_ID){
        this.GOODS_ORDER_ID=GOODS_ORDER_ID;
    }
     /**
     * get 订货订单编号 value
     * @return the GOODS_ORDER_NO
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getGOODS_ORDER_NO(){
        return GOODS_ORDER_NO;
    }
	/**
     * set 订货订单编号 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setGOODS_ORDER_NO(String GOODS_ORDER_NO){
        this.GOODS_ORDER_NO=GOODS_ORDER_NO;
    }
     /**
     * get 订货方ID value
     * @return the ORDER_CHANN_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORDER_CHANN_ID(){
        return ORDER_CHANN_ID;
    }
	/**
     * set 订货方ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORDER_CHANN_ID(String ORDER_CHANN_ID){
        this.ORDER_CHANN_ID=ORDER_CHANN_ID;
    }
     /**
     * get 订货方编号 value
     * @return the ORDER_CHANN_NO
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORDER_CHANN_NO(){
        return ORDER_CHANN_NO;
    }
	/**
     * set 订货方编号 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORDER_CHANN_NO(String ORDER_CHANN_NO){
        this.ORDER_CHANN_NO=ORDER_CHANN_NO;
    }
     /**
     * get 订货方名称 value
     * @return the ORDER_CHANN_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORDER_CHANN_NAME(){
        return ORDER_CHANN_NAME;
    }
	/**
     * set 订货方名称 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORDER_CHANN_NAME(String ORDER_CHANN_NAME){
        this.ORDER_CHANN_NAME=ORDER_CHANN_NAME;
    }
     /**
     * get 发货方ID value
     * @return the SEND_CHANN_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSEND_CHANN_ID(){
        return SEND_CHANN_ID;
    }
	/**
     * set 发货方ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSEND_CHANN_ID(String SEND_CHANN_ID){
        this.SEND_CHANN_ID=SEND_CHANN_ID;
    }
     /**
     * get 发货方编号 value
     * @return the SEND_CHANN_NO
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSEND_CHANN_NO(){
        return SEND_CHANN_NO;
    }
	/**
     * set 发货方编号 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSEND_CHANN_NO(String SEND_CHANN_NO){
        this.SEND_CHANN_NO=SEND_CHANN_NO;
    }
     /**
     * get 发货方名称 value
     * @return the SEND_CHANN_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSEND_CHANN_NAME(){
        return SEND_CHANN_NAME;
    }
	/**
     * set 发货方名称 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSEND_CHANN_NAME(String SEND_CHANN_NAME){
        this.SEND_CHANN_NAME=SEND_CHANN_NAME;
    }
     /**
     * get 订单类型 value
     * @return the BILL_TYPE
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getBILL_TYPE(){
        return BILL_TYPE;
    }
	/**
     * set 订单类型 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setBILL_TYPE(String BILL_TYPE){
        this.BILL_TYPE=BILL_TYPE;
    }
     /**
     * get 是否使用返利 value
     * @return the IS_USE_REBATE
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getIS_USE_REBATE(){
        return IS_USE_REBATE;
    }
	/**
     * set 是否使用返利 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setIS_USE_REBATE(String IS_USE_REBATE){
        this.IS_USE_REBATE=IS_USE_REBATE;
    }
     /**
     * get 收货方ID value
     * @return the RECV_CHANN_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getRECV_CHANN_ID(){
        return RECV_CHANN_ID;
    }
	/**
     * set 收货方ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setRECV_CHANN_ID(String RECV_CHANN_ID){
        this.RECV_CHANN_ID=RECV_CHANN_ID;
    }
     /**
     * get 收货方编号 value
     * @return the RECV_CHANN_NO
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getRECV_CHANN_NO(){
        return RECV_CHANN_NO;
    }
	/**
     * set 收货方编号 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setRECV_CHANN_NO(String RECV_CHANN_NO){
        this.RECV_CHANN_NO=RECV_CHANN_NO;
    }
     /**
     * get 收货方名称 value
     * @return the RECV_CHANN_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getRECV_CHANN_NAME(){
        return RECV_CHANN_NAME;
    }
	/**
     * set 收货方名称 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setRECV_CHANN_NAME(String RECV_CHANN_NAME){
        this.RECV_CHANN_NAME=RECV_CHANN_NAME;
    }
     /**
     * get 联系人 value
     * @return the PERSON_CON
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPERSON_CON(){
        return PERSON_CON;
    }
	/**
     * set 联系人 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPERSON_CON(String PERSON_CON){
        this.PERSON_CON=PERSON_CON;
    }
     /**
     * get 电话 value
     * @return the TEL
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getTEL(){
        return TEL;
    }
	/**
     * set 电话 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setTEL(String TEL){
        this.TEL=TEL;
    }
     /**
     * get 收货地址 value
     * @return the RECV_ADDR
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getRECV_ADDR(){
        return RECV_ADDR;
    }
	/**
     * set 收货地址 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setRECV_ADDR(String RECV_ADDR){
        this.RECV_ADDR=RECV_ADDR;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 审核人ID value
     * @return the AUDIT_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getAUDIT_ID(){
        return AUDIT_ID;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setAUDIT_ID(String AUDIT_ID){
        this.AUDIT_ID=AUDIT_ID;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDIT_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getAUDIT_NAME(){
        return AUDIT_NAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setAUDIT_NAME(String AUDIT_NAME){
        this.AUDIT_NAME=AUDIT_NAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDIT_TIME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getAUDIT_TIME(){
        return AUDIT_TIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setAUDIT_TIME(String AUDIT_TIME){
        this.AUDIT_TIME=AUDIT_TIME;
    }
     /**
     * get 发货点ID value
     * @return the SHIP_POINT_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSHIP_POINT_ID(){
        return SHIP_POINT_ID;
    }
	/**
     * set 发货点ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSHIP_POINT_ID(String SHIP_POINT_ID){
        this.SHIP_POINT_ID=SHIP_POINT_ID;
    }
     /**
     * get 发货点名称 value
     * @return the SHIP_POINT_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSHIP_POINT_NAME(){
        return SHIP_POINT_NAME;
    }
	/**
     * set 发货点名称 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSHIP_POINT_NAME(String SHIP_POINT_NAME){
        this.SHIP_POINT_NAME=SHIP_POINT_NAME;
    }
     /**
     * get 制单人 value
     * @return the CRE_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人 value
     * @return the UPD_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门 value
     * @return the DEPT_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构 value
     * @return the ORG_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
    
	public String getAREA_ID() {
		return AREA_ID;
	}
	public void setAREA_ID(String aREAID) {
		AREA_ID = aREAID;
	}
	public String getAREA_NO() {
		return AREA_NO;
	}
	public void setAREA_NO(String aREANO) {
		AREA_NO = aREANO;
	}
	public String getAREA_NAME() {
		return AREA_NAME;
	}
	public void setAREA_NAME(String aREANAME) {
		AREA_NAME = aREANAME;
	}
	public String getSTORE_ID() {
		return STORE_ID;
	}
	public void setSTORE_ID(String sTOREID) {
		STORE_ID = sTOREID;
	}
	public String getSTORE_NO() {
		return STORE_NO;
	}
	public void setSTORE_NO(String sTORENO) {
		STORE_NO = sTORENO;
	}
	public String getSTORE_NAME() {
		return STORE_NAME;
	}
	public void setSTORE_NAME(String sTORENAME) {
		STORE_NAME = sTORENAME;
	}
	public String getRECV_ADDR_NO() {
		return RECV_ADDR_NO;
	}
	public void setRECV_ADDR_NO(String rECVADDRNO) {
		RECV_ADDR_NO = rECVADDRNO;
	}
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
    
	
    
    
}