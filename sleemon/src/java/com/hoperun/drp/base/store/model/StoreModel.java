/**
 * prjName:喜临门营销平台
 * ucName:库房库位信息
 * fileName:StoreModel
*/
package com.hoperun.drp.base.store.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-13 14:01:22
 */
@SuppressWarnings("serial")
public class StoreModel extends BaseModel{
   /** 库房ID **/
   private String STORE_ID;
   /** 库房编号 **/
   private String STORE_NO;
   /** 库房名称 **/
   private String STORE_NAME;
   /** 库房类型 **/
   private String STORE_TYPE;
   /** 上级库房ID **/
   private String PAR_STORE_ID;
   /** 上级库房编号 **/
   private String PAR_STORE_NO;
   /** 上级库房名称 **/
   private String PAR_STORE_NAME;
   /** 是否终端库房 **/
   private String TERM_STROE_FLAG;
   /** 所属单位 **/
   private String BEL_UNIT_ID;
   /** 所属单位名称 **/
   private String BEL_UNIT_NAME;
   /** 所属渠道ID **/
   private String BEL_CHANN_ID;
   /** 所属渠道编号 **/
   private String BEL_CHANN_NO;
   /** 所属渠道名称 **/
   private String BEL_CHANN_NAME;
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
   
   /** 详细地址 **/
   private String DTL_ADDR;
   
   /**库房总容积**/
   private String TOTAL_VOLUME;
   
     /**
     * get 库房ID value
     * @return the STORE_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORE_ID(){
        return STORE_ID;
    }
	/**
     * set 库房ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORE_ID(String STORE_ID){
        this.STORE_ID=STORE_ID;
    }
     /**
     * get 库房编号 value
     * @return the STORE_NO
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORE_NO(){
        return STORE_NO;
    }
	/**
     * set 库房编号 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORE_NO(String STORE_NO){
        this.STORE_NO=STORE_NO;
    }
     /**
     * get 库房名称 value
     * @return the STORE_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORE_NAME(){
        return STORE_NAME;
    }
	/**
     * set 库房名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORE_NAME(String STORE_NAME){
        this.STORE_NAME=STORE_NAME;
    }
     /**
     * get 库房类型 value
     * @return the STORE_TYPE
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORE_TYPE(){
        return STORE_TYPE;
    }
	/**
     * set 库房类型 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORE_TYPE(String STORE_TYPE){
        this.STORE_TYPE=STORE_TYPE;
    }
     /**
     * get 上级库房ID value
     * @return the PAR_STORE_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getPAR_STORE_ID(){
        return PAR_STORE_ID;
    }
	/**
     * set 上级库房ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setPAR_STORE_ID(String PAR_STORE_ID){
        this.PAR_STORE_ID=PAR_STORE_ID;
    }
     /**
     * get 是否终端库房 value
     * @return the TERM_STROE_FLAG
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getTERM_STROE_FLAG(){
        return TERM_STROE_FLAG;
    }
	/**
     * set 是否终端库房 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setTERM_STROE_FLAG(String TERM_STROE_FLAG){
        this.TERM_STROE_FLAG=TERM_STROE_FLAG;
    }
     /**
     * get 所属单位 value
     * @return the BEL_UNIT_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getBEL_UNIT_ID(){
        return BEL_UNIT_ID;
    }
	/**
     * set 所属单位 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setBEL_UNIT_ID(String BEL_UNIT_ID){
        this.BEL_UNIT_ID=BEL_UNIT_ID;
    }
     /**
     * get 所属单位名称 value
     * @return the BEL_UNIT_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getBEL_UNIT_NAME(){
        return BEL_UNIT_NAME;
    }
	/**
     * set 所属单位名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setBEL_UNIT_NAME(String BEL_UNIT_NAME){
        this.BEL_UNIT_NAME=BEL_UNIT_NAME;
    }
     /**
     * get 库位管理标记 value
     * @return the STORAGE_FLAG
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORAGE_FLAG(){
        return STORAGE_FLAG;
    }
	/**
     * set 库位管理标记 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORAGE_FLAG(String STORAGE_FLAG){
        this.STORAGE_FLAG=STORAGE_FLAG;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 帐套ID value
     * @return the LEDGER_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 帐套ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 帐套名称 value
     * @return the LEDGER_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 帐套名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
    /**
     * get 上级库房名称 value
     * @return the PAR_STORE_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public String getPAR_STORE_NAME() {
		return PAR_STORE_NAME;
	}
	/**
     * set 上级库房名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public void setPAR_STORE_NAME(String pAR_STORE_NAME) {
		PAR_STORE_NAME = pAR_STORE_NAME;
	}
	
	/**
     * set 上级库房编号 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public void setPAR_STORE_NO(String pAR_STORE_NO) {
		PAR_STORE_NO = pAR_STORE_NO;
	}
	
	/**
     * get 上级库房编号 value
     * @return the PAR_STORE_NO
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public String getPAR_STORE_NO() {
		return PAR_STORE_NO;
	}
	
	/**
     * get 上级渠道ID value
     * @return the BEL_CHANN_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public String getBEL_CHANN_ID() {
		return BEL_CHANN_ID;
	}
	
	/**
     * set 上级渠道ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public void setBEL_CHANN_ID(String bELCHANNID) {
		BEL_CHANN_ID = bELCHANNID;
	}
	
	/**
     * get 上级渠道编号  value
     * @return the BEL_CHANN_NO
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public String getBEL_CHANN_NO() {
		return BEL_CHANN_NO;
	}
	
	/**
     * set 上级渠道编号 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public void setBEL_CHANN_NO(String bELCHANNNO) {
		BEL_CHANN_NO = bELCHANNNO;
	}
	
	/**
     * get 上级渠道名称 value
     * @return the BEL_CHANN_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public String getBEL_CHANN_NAME() {
		return BEL_CHANN_NAME;
	}
	
	/**
     * set 上级渠道名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
	public void setBEL_CHANN_NAME(String bELCHANNNAME) {
		BEL_CHANN_NAME = bELCHANNNAME;
	}
	public String getDTL_ADDR() {
		return DTL_ADDR;
	}
	public void setDTL_ADDR(String dTLADDR) {
		DTL_ADDR = dTLADDR;
	}
	public String getTOTAL_VOLUME() {
		return TOTAL_VOLUME;
	}
	public void setTOTAL_VOLUME(String tOTALVOLUME) {
		TOTAL_VOLUME = tOTALVOLUME;
	}
	
	
	
}