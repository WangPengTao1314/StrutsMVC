/**
 * prjName:喜临门营销平台
 * ucName:出库通知单处理
 * fileName:StoreoutModel
*/
package com.hoperun.drp.store.storeoutfewnotice.model;

import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author wujun
 * *@createdate 2014-12-10 14:59:50
 */

public class StoreoutfewnoticeModel extends BaseModel{
	
	/** 零星出库通知单ID **/          
	private String FEW_STOREOUT_REQ_ID;
	/** 零星出库通知单编号 **/        
	private String FEW_STOREOUT_REQ_NO;
	/** 单据类型 **/                  
	private String BILL_TYPE;          
	/** 出库库房ID **/                
	private String STOREOUT_STORE_ID;  
	/** 出库库房编号 **/              
	private String STOREOUT_STORE_NO;  
	/** 出库库房名称 **/              
	private String STOREOUT_STORE_NAME;
	/** 业务员ID **/                  
	private String BUSS_PSON_ID;       
	/** 业务员名称 **/                
	private String BUSS_PSON_NAME;     
	/** 业务部门ID **/                
	private String BUSS_DEPT_ID;       
	/** 业务部门名称 **/              
	private String BUSS_DEPT_NAME;     
	/** 状态 **/                      
	private String STATE;              
	/** 备注 **/                      
	private String REMARK;             
	/** 制单人ID **/                  
	private String CREATOR;            
	/** 制单人名称 **/                
	private String CRE_NAME;           
	/** 制单时间 **/                  
	private String CRE_TIME;           
	/** 更新人ID **/                  
	private String UPDATOR;            
	/** 更新人名称 **/                
	private String UPD_NAME;           
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
	/** 删除标记 **/                                          
	private String DEL_FLAG;
	
	public String getFEW_STOREOUT_REQ_ID() {
		return FEW_STOREOUT_REQ_ID;
	}
	public void setFEW_STOREOUT_REQ_ID(String few_storeout_req_id) {
		FEW_STOREOUT_REQ_ID = few_storeout_req_id;
	}
	public String getFEW_STOREOUT_REQ_NO() {
		return FEW_STOREOUT_REQ_NO;
	}
	public void setFEW_STOREOUT_REQ_NO(String few_storeout_req_no) {
		FEW_STOREOUT_REQ_NO = few_storeout_req_no;
	}
	public String getBILL_TYPE() {
		return BILL_TYPE;
	}
	public void setBILL_TYPE(String bill_type) {
		BILL_TYPE = bill_type;
	}
	public String getSTOREOUT_STORE_ID() {
		return STOREOUT_STORE_ID;
	}
	public void setSTOREOUT_STORE_ID(String storeout_store_id) {
		STOREOUT_STORE_ID = storeout_store_id;
	}
	public String getSTOREOUT_STORE_NO() {
		return STOREOUT_STORE_NO;
	}
	public void setSTOREOUT_STORE_NO(String storeout_store_no) {
		STOREOUT_STORE_NO = storeout_store_no;
	}
	public String getSTOREOUT_STORE_NAME() {
		return STOREOUT_STORE_NAME;
	}
	public void setSTOREOUT_STORE_NAME(String storeout_store_name) {
		STOREOUT_STORE_NAME = storeout_store_name;
	}
	public String getBUSS_PSON_ID() {
		return BUSS_PSON_ID;
	}
	public void setBUSS_PSON_ID(String buss_pson_id) {
		BUSS_PSON_ID = buss_pson_id;
	}
	public String getBUSS_PSON_NAME() {
		return BUSS_PSON_NAME;
	}
	public void setBUSS_PSON_NAME(String buss_pson_name) {
		BUSS_PSON_NAME = buss_pson_name;
	}
	public String getBUSS_DEPT_ID() {
		return BUSS_DEPT_ID;
	}
	public void setBUSS_DEPT_ID(String buss_dept_id) {
		BUSS_DEPT_ID = buss_dept_id;
	}
	public String getBUSS_DEPT_NAME() {
		return BUSS_DEPT_NAME;
	}
	public void setBUSS_DEPT_NAME(String buss_dept_name) {
		BUSS_DEPT_NAME = buss_dept_name;
	}
	public String getSTATE() {
		return STATE;
	}
	public void setSTATE(String state) {
		STATE = state;
	}
	public String getREMARK() {
		return REMARK;
	}
	public void setREMARK(String remark) {
		REMARK = remark;
	}
	public String getCREATOR() {
		return CREATOR;
	}
	public void setCREATOR(String creator) {
		CREATOR = creator;
	}
	public String getCRE_NAME() {
		return CRE_NAME;
	}
	public void setCRE_NAME(String cre_name) {
		CRE_NAME = cre_name;
	}
	public String getCRE_TIME() {
		return CRE_TIME;
	}
	public void setCRE_TIME(String cre_time) {
		CRE_TIME = cre_time;
	}
	public String getUPDATOR() {
		return UPDATOR;
	}
	public void setUPDATOR(String updator) {
		UPDATOR = updator;
	}
	public String getUPD_NAME() {
		return UPD_NAME;
	}
	public void setUPD_NAME(String upd_name) {
		UPD_NAME = upd_name;
	}
	public String getUPD_TIME() {
		return UPD_TIME;
	}
	public void setUPD_TIME(String upd_time) {
		UPD_TIME = upd_time;
	}
	public String getDEPT_ID() {
		return DEPT_ID;
	}
	public void setDEPT_ID(String dept_id) {
		DEPT_ID = dept_id;
	}
	public String getDEPT_NAME() {
		return DEPT_NAME;
	}
	public void setDEPT_NAME(String dept_name) {
		DEPT_NAME = dept_name;
	}
	public String getORG_ID() {
		return ORG_ID;
	}
	public void setORG_ID(String org_id) {
		ORG_ID = org_id;
	}
	public String getORG_NAME() {
		return ORG_NAME;
	}
	public void setORG_NAME(String org_name) {
		ORG_NAME = org_name;
	}
	public String getLEDGER_ID() {
		return LEDGER_ID;
	}
	public void setLEDGER_ID(String ledger_id) {
		LEDGER_ID = ledger_id;
	}
	public String getLEDGER_NAME() {
		return LEDGER_NAME;
	}
	public void setLEDGER_NAME(String ledger_name) {
		LEDGER_NAME = ledger_name;
	}
	public String getDEL_FLAG() {
		return DEL_FLAG;
	}
	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}
	
	
}
