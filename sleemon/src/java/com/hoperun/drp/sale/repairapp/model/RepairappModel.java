/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairappModel
*/
package com.hoperun.drp.sale.repairapp.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairappModel extends BaseModel{
   /** 返修单ID **/
   private String ERP_REPAIR_ORDER_ID;
   /** 返修单编号 **/
   private String ERP_REPAIR_ORDER_NO;
   /** 返修货方ID **/
   private String REPAIR_CHANN_ID;
   /** 返修方编号 **/
   private String REPAIR_CHANN_NO;
   /** 返修方名称 **/
   private String REPAIR_CHANN_NAME;
   /** 区域ID **/
   private String AREA_ID;
   /** 区域编号 **/
   private String AREA_NO;
   /** 区域名称 **/
   private String AREA_NAME;
   /** 要求完成日期 **/
   private String REQ_FINISH_DATE;
   /** 出库库房ID **/
   private String STOREOUT_STORE_ID;
   /** 出库库房编号 **/
   private String STOREOUT_STORE_NO;
   /** 出库库房名称 **/
   private String STOREOUT_STORE_NAME;
   /** 库位管理标记 **/
   private String STORAGE_FLAG;
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
   
   /**返修方收货地址ID **/
   private String DELIVER_ADDR_ID;
   /**返修方收货地址编号 **/
   private String DELIVER_ADDR_NO;
   /**返修方收货地址 **/
   private String DELIVER_DTL_ADDR;
   
     /**
     * get 返修单ID value
     * @return the ERP_REPAIR_ORDER_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getERP_REPAIR_ORDER_ID(){
        return ERP_REPAIR_ORDER_ID;
    }
	/**
     * set 返修单ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setERP_REPAIR_ORDER_ID(String ERP_REPAIR_ORDER_ID){
        this.ERP_REPAIR_ORDER_ID=ERP_REPAIR_ORDER_ID;
    }
     /**
     * get 返修单编号 value
     * @return the ERP_REPAIR_ORDER_NO
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getERP_REPAIR_ORDER_NO(){
        return ERP_REPAIR_ORDER_NO;
    }
	/**
     * set 返修单编号 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setERP_REPAIR_ORDER_NO(String ERP_REPAIR_ORDER_NO){
        this.ERP_REPAIR_ORDER_NO=ERP_REPAIR_ORDER_NO;
    }
     /**
     * get 返修货方ID value
     * @return the REPAIR_CHANN_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getREPAIR_CHANN_ID(){
        return REPAIR_CHANN_ID;
    }
	/**
     * set 返修货方ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setREPAIR_CHANN_ID(String REPAIR_CHANN_ID){
        this.REPAIR_CHANN_ID=REPAIR_CHANN_ID;
    }
     /**
     * get 返修方编号 value
     * @return the REPAIR_CHANN_NO
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getREPAIR_CHANN_NO(){
        return REPAIR_CHANN_NO;
    }
	/**
     * set 返修方编号 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setREPAIR_CHANN_NO(String REPAIR_CHANN_NO){
        this.REPAIR_CHANN_NO=REPAIR_CHANN_NO;
    }
     /**
     * get 返修方名称 value
     * @return the REPAIR_CHANN_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getREPAIR_CHANN_NAME(){
        return REPAIR_CHANN_NAME;
    }
	/**
     * set 返修方名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setREPAIR_CHANN_NAME(String REPAIR_CHANN_NAME){
        this.REPAIR_CHANN_NAME=REPAIR_CHANN_NAME;
    }
     /**
     * get 区域ID value
     * @return the AREA_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getAREA_ID(){
        return AREA_ID;
    }
	/**
     * set 区域ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setAREA_ID(String AREA_ID){
        this.AREA_ID=AREA_ID;
    }
     /**
     * get 区域编号 value
     * @return the AREA_NO
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getAREA_NO(){
        return AREA_NO;
    }
	/**
     * set 区域编号 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setAREA_NO(String AREA_NO){
        this.AREA_NO=AREA_NO;
    }
     /**
     * get 区域名称 value
     * @return the AREA_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getAREA_NAME(){
        return AREA_NAME;
    }
	/**
     * set 区域名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setAREA_NAME(String AREA_NAME){
        this.AREA_NAME=AREA_NAME;
    }
     /**
     * get 要求完成日期 value
     * @return the REQ_FINISH_DATE
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getREQ_FINISH_DATE(){
        return REQ_FINISH_DATE;
    }
	/**
     * set 要求完成日期 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setREQ_FINISH_DATE(String REQ_FINISH_DATE){
        this.REQ_FINISH_DATE=REQ_FINISH_DATE;
    }
     /**
     * get 出库库房ID value
     * @return the STOREOUT_STORE_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getSTOREOUT_STORE_ID(){
        return STOREOUT_STORE_ID;
    }
	/**
     * set 出库库房ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setSTOREOUT_STORE_ID(String STOREOUT_STORE_ID){
        this.STOREOUT_STORE_ID=STOREOUT_STORE_ID;
    }
     /**
     * get 出库库房编号 value
     * @return the STOREOUT_STORE_NO
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getSTOREOUT_STORE_NO(){
        return STOREOUT_STORE_NO;
    }
	/**
     * set 出库库房编号 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setSTOREOUT_STORE_NO(String STOREOUT_STORE_NO){
        this.STOREOUT_STORE_NO=STOREOUT_STORE_NO;
    }
     /**
     * get 出库库房名称 value
     * @return the STOREOUT_STORE_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getSTOREOUT_STORE_NAME(){
        return STOREOUT_STORE_NAME;
    }
	/**
     * set 出库库房名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setSTOREOUT_STORE_NAME(String STOREOUT_STORE_NAME){
        this.STOREOUT_STORE_NAME=STOREOUT_STORE_NAME;
    }
     /**
     * get 库位管理标记 value
     * @return the STORAGE_FLAG
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getSTORAGE_FLAG(){
        return STORAGE_FLAG;
    }
	/**
     * set 库位管理标记 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setSTORAGE_FLAG(String STORAGE_FLAG){
        this.STORAGE_FLAG=STORAGE_FLAG;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-11-03 16:25:12
     * @author chenj
	 * @createdate 2013-11-03 16:25:12
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getDELIVER_ADDR_ID() {
		return DELIVER_ADDR_ID;
	}
	public void setDELIVER_ADDR_ID(String dELIVERADDRID) {
		DELIVER_ADDR_ID = dELIVERADDRID;
	}
	public String getDELIVER_ADDR_NO() {
		return DELIVER_ADDR_NO;
	}
	public void setDELIVER_ADDR_NO(String dELIVERADDRNO) {
		DELIVER_ADDR_NO = dELIVERADDRNO;
	}
	public String getDELIVER_DTL_ADDR() {
		return DELIVER_DTL_ADDR;
	}
	public void setDELIVER_DTL_ADDR(String dELIVERDTLADDR) {
		DELIVER_DTL_ADDR = dELIVERDTLADDR;
	}
    
    
}