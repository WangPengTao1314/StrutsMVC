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
public class StoreoutfewnoticeModelChld extends BaseModel{
	
	/** 零星出库通知单明细ID **/         
	private String FEW_STOREOUT_REQ_DTL_ID;

	/** 零星出库通知单ID **/             
	private String FEW_STOREOUT_REQ_ID;  
	 
	/** 货品ID **/                       
	private String PRD_ID;              
	   
	/** 货品编号 **/                     
	private String PRD_NO;         
	        
	/** 货品名称 **/                     
	private String PRD_NAME;          
	     
	/** 规格型号 **/                     
	private String PRD_SPEC;            
	   
	/** 品牌 **/                         
	private String BRAND;                
	 
	/** 标准单位 **/                     
	private String STD_UNIT;       
	       
	/** 通知出库数量 **/                 
	private String NOTICE_NUM;        
	   
	/** 订单特殊工艺ID **/               
	private String SPCL_TECH_ID;         
	 
	/** 备注 **/                         
	private String REMARK;              
	   
	/** 删除标记 **/                     
	private String DEL_FLAG;

	public String getFEW_STOREOUT_REQ_DTL_ID() {
		return FEW_STOREOUT_REQ_DTL_ID;
	}

	public void setFEW_STOREOUT_REQ_DTL_ID(String few_storeout_req_dtl_id) {
		FEW_STOREOUT_REQ_DTL_ID = few_storeout_req_dtl_id;
	}

	public String getFEW_STOREOUT_REQ_ID() {
		return FEW_STOREOUT_REQ_ID;
	}

	public void setFEW_STOREOUT_REQ_ID(String few_storeout_req_id) {
		FEW_STOREOUT_REQ_ID = few_storeout_req_id;
	}

	public String getPRD_ID() {
		return PRD_ID;
	}

	public void setPRD_ID(String prd_id) {
		PRD_ID = prd_id;
	}

	public String getPRD_NO() {
		return PRD_NO;
	}

	public void setPRD_NO(String prd_no) {
		PRD_NO = prd_no;
	}

	public String getPRD_NAME() {
		return PRD_NAME;
	}

	public void setPRD_NAME(String prd_name) {
		PRD_NAME = prd_name;
	}

	public String getPRD_SPEC() {
		return PRD_SPEC;
	}

	public void setPRD_SPEC(String prd_spec) {
		PRD_SPEC = prd_spec;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String brand) {
		BRAND = brand;
	}

	public String getSTD_UNIT() {
		return STD_UNIT;
	}

	public void setSTD_UNIT(String std_unit) {
		STD_UNIT = std_unit;
	}

	public String getNOTICE_NUM() {
		return NOTICE_NUM;
	}

	public void setNOTICE_NUM(String notice_num) {
		NOTICE_NUM = notice_num;
	}

	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}

	public void setSPCL_TECH_ID(String spcl_tech_id) {
		SPCL_TECH_ID = spcl_tech_id;
	}

	public String getREMARK() {
		return REMARK;
	}

	public void setREMARK(String remark) {
		REMARK = remark;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String del_flag) {
		DEL_FLAG = del_flag;
	}    

	          
	           
}
