package com.hoperun.drp.sale.adviseinit.model;

import com.hoperun.commons.model.BaseModel;

@SuppressWarnings("serial")
public class AdviseinitModel extends BaseModel {
	   /** 投诉与建议ID **/
	   private String CMPL_ADVS_ID;
	   /** 编号 **/
	   private String CMPL_ADVS_NO;
	   /** 标题 **/
	   private String CMPL_ADVS_TITLE;
	   /** 类型 **/
	   private String CMPL_ADVS_TYPE;
	   /** 渠道ID **/
	   private String CHANN_ID;
	   /** 渠道编号 **/
	   private String CHANN_NO;
	   /** 渠道名称 **/
	   private String CHANN_NAME;
	   /** 区域ID **/
	   private String AREA_ID;
	   /** 区域编号 **/
	   private String AREA_NO;
	   /** 区域 **/
	   private String AREA_NAME;
	   /** 区域经理ID **/
	   private String AREA_MANAGE_ID;
	   /** 区域经理 **/
	   private String AREA_MANAGE_NAME;
	   /** 区域总监ID **/
	   private String AREA_CHIEF_ID;
	   /** 总监 **/
	   private String AREA_CHIEF_NAME;
	   /** 提出人ID **/
	   private String RAISE_PSON_ID;
	   /** 提出人 **/
	   private String RAISE_PSON_NAME;
	   /** 时间 **/
	   private String RAISE_TIME;
	   /** 联系电话 **/
	   private String TEL;
	   /** 投诉对象 **/
	   private String CMPL_OBJ;
	   /** 投诉的人 **/
	   private String CMPL_TO_PSON;
	   /** 建议类型 **/
	   private String ADVS_TYPE;
	   /** 建议概述 **/
	   private String ADVS_SMMRY;
	   /** 投诉与建议内容 **/
	   private String CMPL_ADVS_CONTENT;
	   /** 处理人ID **/
	   private String DEAL_PSON_ID;
	   /** 处理人 **/
	   private String DEAL_PSON_NAME;
	   /** 处理时间 **/
	   private String DEAL_TIME;
	   /** 回馈信息 **/
	   private String FEEDBACK_INFO;
	   /** 指派处理人 **/
	   private String APPOINT_PSON_ID;
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
	   /** 账套ID **/
	   private String LEDGER_ID;
	   /** 账套名称 **/
	   private String LEDGER_NAME;
	   /** 状态 **/
	   private String STATE;
	   /** 删除标记 **/
	   private String DEL_FLAG;
	   /** 投诉货品明细ID **/
	   private String CMPL_PRD_DTL_ID;
	   /** 货品ID **/
	   private String PRD_ID;
	   /** 货品编号 **/
	   private String PRD_NO;
	   /** 货品名称 **/
	   private String PRD_NAME;
	   /** 规格型号 **/
	   private String PRD_SPEC;
	   /** 产品问题类型 **/
	   private String PRD_PROBLEM_TYPE;
	   /** 备注说明 **/
	   private String REMARK;
	   /** 附件 **/
	   private String PRD_ATT;
	   /**紧急程度**/
	   private String EMG_LVL;
	   /**生产基地id**/
	   private String SHIP_POINT_ID;
	   /**生产基地编号**/
	   private String SHIP_POINT_NO;
	   /**生产基地名称**/
	   private String SHIP_POINT_NAME;
	   /**省份**/
	   private String PROV;
	   /**使用时间**/
	   private String USE_TIME;
	   /**生产日期**/
	   private String PRDC_DATE;
	   
	   private String ADVS_SOURCE;
	   private String CUSTOMER_TEL;
	   private String CUSTOMER_NAME;
	   private String BUY_DATE;
	   private String PRD_TYPE;
	   
	   private String PRD_NUM;
	     /**
	     * get 投诉与建议ID value
	     * @return the CMPL_ADVS_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_ADVS_ID(){
	        return CMPL_ADVS_ID;
	    }
		/**
	     * set 投诉与建议ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_ADVS_ID(String CMPL_ADVS_ID){
	        this.CMPL_ADVS_ID=CMPL_ADVS_ID;
	    }
	     /**
	     * get 编号 value
	     * @return the CMPL_ADVS_NO
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_ADVS_NO(){
	        return CMPL_ADVS_NO;
	    }
		/**
	     * set 编号 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_ADVS_NO(String CMPL_ADVS_NO){
	        this.CMPL_ADVS_NO=CMPL_ADVS_NO;
	    }
	     /**
	     * get 标题 value
	     * @return the CMPL_ADVS_TITLE
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_ADVS_TITLE(){
	        return CMPL_ADVS_TITLE;
	    }
		/**
	     * set 标题 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_ADVS_TITLE(String CMPL_ADVS_TITLE){
	        this.CMPL_ADVS_TITLE=CMPL_ADVS_TITLE;
	    }
	     /**
	     * get 类型 value
	     * @return the CMPL_ADVS_TYPE
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_ADVS_TYPE(){
	        return CMPL_ADVS_TYPE;
	    }
		/**
	     * set 类型 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_ADVS_TYPE(String CMPL_ADVS_TYPE){
	        this.CMPL_ADVS_TYPE=CMPL_ADVS_TYPE;
	    }
	     /**
	     * get 渠道ID value
	     * @return the CHANN_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCHANN_ID(){
	        return CHANN_ID;
	    }
		/**
	     * set 渠道ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCHANN_ID(String CHANN_ID){
	        this.CHANN_ID=CHANN_ID;
	    }
	     /**
	     * get 渠道编号 value
	     * @return the CHANN_NO
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCHANN_NO(){
	        return CHANN_NO;
	    }
		/**
	     * set 渠道编号 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCHANN_NO(String CHANN_NO){
	        this.CHANN_NO=CHANN_NO;
	    }
	     /**
	     * get 渠道名称 value
	     * @return the CHANN_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCHANN_NAME(){
	        return CHANN_NAME;
	    }
		/**
	     * set 渠道名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCHANN_NAME(String CHANN_NAME){
	        this.CHANN_NAME=CHANN_NAME;
	    }
	     /**
	     * get 区域编号 value
	     * @return the AREA_NO
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAREA_NO(){
	        return AREA_NO;
	    }
		/**
	     * set 区域编号 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAREA_NO(String AREA_NO){
	        this.AREA_NO=AREA_NO;
	    }
	     /**
	     * get 区域 value
	     * @return the AREA_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAREA_NAME(){
	        return AREA_NAME;
	    }
		/**
	     * set 区域 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAREA_NAME(String AREA_NAME){
	        this.AREA_NAME=AREA_NAME;
	    }
	     /**
	     * get 区域经理ID value
	     * @return the AREA_MANAGE_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAREA_MANAGE_ID(){
	        return AREA_MANAGE_ID;
	    }
		/**
	     * set 区域经理ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAREA_MANAGE_ID(String AREA_MANAGE_ID){
	        this.AREA_MANAGE_ID=AREA_MANAGE_ID;
	    }
	     /**
	     * get 区域经理 value
	     * @return the AREA_MANAGE_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAREA_MANAGE_NAME(){
	        return AREA_MANAGE_NAME;
	    }
		/**
	     * set 区域经理 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAREA_MANAGE_NAME(String AREA_MANAGE_NAME){
	        this.AREA_MANAGE_NAME=AREA_MANAGE_NAME;
	    }
	     /**
	     * get 区域总监ID value
	     * @return the AREA_CHIEF_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAREA_CHIEF_ID(){
	        return AREA_CHIEF_ID;
	    }
		/**
	     * set 区域总监ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAREA_CHIEF_ID(String AREA_CHIEF_ID){
	        this.AREA_CHIEF_ID=AREA_CHIEF_ID;
	    }
	     /**
	     * get 总监 value
	     * @return the AREA_CHIEF_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAREA_CHIEF_NAME(){
	        return AREA_CHIEF_NAME;
	    }
		/**
	     * set 总监 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAREA_CHIEF_NAME(String AREA_CHIEF_NAME){
	        this.AREA_CHIEF_NAME=AREA_CHIEF_NAME;
	    }
	     /**
	     * get 提出人ID value
	     * @return the RAISE_PSON_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getRAISE_PSON_ID(){
	        return RAISE_PSON_ID;
	    }
		/**
	     * set 提出人ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setRAISE_PSON_ID(String RAISE_PSON_ID){
	        this.RAISE_PSON_ID=RAISE_PSON_ID;
	    }
	     /**
	     * get 提出人 value
	     * @return the RAISE_PSON_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getRAISE_PSON_NAME(){
	        return RAISE_PSON_NAME;
	    }
		/**
	     * set 提出人 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setRAISE_PSON_NAME(String RAISE_PSON_NAME){
	        this.RAISE_PSON_NAME=RAISE_PSON_NAME;
	    }
	     /**
	     * get 时间 value
	     * @return the RAISE_TIME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getRAISE_TIME(){
	        return RAISE_TIME;
	    }
		/**
	     * set 时间 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setRAISE_TIME(String RAISE_TIME){
	        this.RAISE_TIME=RAISE_TIME;
	    }
	     /**
	     * get 联系电话 value
	     * @return the TEL
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getTEL(){
	        return TEL;
	    }
		/**
	     * set 联系电话 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setTEL(String TEL){
	        this.TEL=TEL;
	    }
	     /**
	     * get 投诉对象 value
	     * @return the CMPL_OBJ
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_OBJ(){
	        return CMPL_OBJ;
	    }
		/**
	     * set 投诉对象 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_OBJ(String CMPL_OBJ){
	        this.CMPL_OBJ=CMPL_OBJ;
	    }
	     /**
	     * get 投诉的人 value
	     * @return the CMPL_TO_PSON
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_TO_PSON(){
	        return CMPL_TO_PSON;
	    }
		/**
	     * set 投诉的人 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_TO_PSON(String CMPL_TO_PSON){
	        this.CMPL_TO_PSON=CMPL_TO_PSON;
	    }
	     /**
	     * get 建议类型 value
	     * @return the ADVS_TYPE
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getADVS_TYPE(){
	        return ADVS_TYPE;
	    }
		/**
	     * set 建议类型 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setADVS_TYPE(String ADVS_TYPE){
	        this.ADVS_TYPE=ADVS_TYPE;
	    }
	     /**
	     * get 建议概述 value
	     * @return the ADVS_SMMRY
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getADVS_SMMRY(){
	        return ADVS_SMMRY;
	    }
		/**
	     * set 建议概述 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setADVS_SMMRY(String ADVS_SMMRY){
	        this.ADVS_SMMRY=ADVS_SMMRY;
	    }
	     /**
	     * get 投诉与建议内容 value
	     * @return the CMPL_ADVS_CONTENT
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_ADVS_CONTENT(){
	        return CMPL_ADVS_CONTENT;
	    }
		/**
	     * set 投诉与建议内容 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_ADVS_CONTENT(String CMPL_ADVS_CONTENT){
	        this.CMPL_ADVS_CONTENT=CMPL_ADVS_CONTENT;
	    }
	     /**
	     * get 处理人ID value
	     * @return the DEAL_PSON_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getDEAL_PSON_ID(){
	        return DEAL_PSON_ID;
	    }
		/**
	     * set 处理人ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setDEAL_PSON_ID(String DEAL_PSON_ID){
	        this.DEAL_PSON_ID=DEAL_PSON_ID;
	    }
	     /**
	     * get 处理人 value
	     * @return the DEAL_PSON_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getDEAL_PSON_NAME(){
	        return DEAL_PSON_NAME;
	    }
		/**
	     * set 处理人 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setDEAL_PSON_NAME(String DEAL_PSON_NAME){
	        this.DEAL_PSON_NAME=DEAL_PSON_NAME;
	    }
	     /**
	     * get 处理时间 value
	     * @return the DEAL_TIME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getDEAL_TIME(){
	        return DEAL_TIME;
	    }
		/**
	     * set 处理时间 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setDEAL_TIME(String DEAL_TIME){
	        this.DEAL_TIME=DEAL_TIME;
	    }
	     /**
	     * get 回馈信息 value
	     * @return the FEEDBACK_INFO
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getFEEDBACK_INFO(){
	        return FEEDBACK_INFO;
	    }
		/**
	     * set 回馈信息 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setFEEDBACK_INFO(String FEEDBACK_INFO){
	        this.FEEDBACK_INFO=FEEDBACK_INFO;
	    }
	     /**
	     * get 指派处理人 value
	     * @return the APPOINT_PSON_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getAPPOINT_PSON_ID(){
	        return APPOINT_PSON_ID;
	    }
		/**
	     * set 指派处理人 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setAPPOINT_PSON_ID(String APPOINT_PSON_ID){
	        this.APPOINT_PSON_ID=APPOINT_PSON_ID;
	    }
	     /**
	     * get 制单人名称 value
	     * @return the CRE_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCRE_NAME(){
	        return CRE_NAME;
	    }
		/**
	     * set 制单人名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCRE_NAME(String CRE_NAME){
	        this.CRE_NAME=CRE_NAME;
	    }
	     /**
	     * get 制单人ID value
	     * @return the CREATOR
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCREATOR(){
	        return CREATOR;
	    }
		/**
	     * set 制单人ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCREATOR(String CREATOR){
	        this.CREATOR=CREATOR;
	    }
	     /**
	     * get 制单时间 value
	     * @return the CRE_TIME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCRE_TIME(){
	        return CRE_TIME;
	    }
		/**
	     * set 制单时间 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCRE_TIME(String CRE_TIME){
	        this.CRE_TIME=CRE_TIME;
	    }
	     /**
	     * get 更新人名称 value
	     * @return the UPD_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getUPD_NAME(){
	        return UPD_NAME;
	    }
		/**
	     * set 更新人名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setUPD_NAME(String UPD_NAME){
	        this.UPD_NAME=UPD_NAME;
	    }
	     /**
	     * get 更新人ID value
	     * @return the UPDATOR
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getUPDATOR(){
	        return UPDATOR;
	    }
		/**
	     * set 更新人ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setUPDATOR(String UPDATOR){
	        this.UPDATOR=UPDATOR;
	    }
	     /**
	     * get 更新时间 value
	     * @return the UPD_TIME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getUPD_TIME(){
	        return UPD_TIME;
	    }
		/**
	     * set 更新时间 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setUPD_TIME(String UPD_TIME){
	        this.UPD_TIME=UPD_TIME;
	    }
	     /**
	     * get 制单部门ID value
	     * @return the DEPT_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getDEPT_ID(){
	        return DEPT_ID;
	    }
		/**
	     * set 制单部门ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setDEPT_ID(String DEPT_ID){
	        this.DEPT_ID=DEPT_ID;
	    }
	     /**
	     * get 制单部门名称 value
	     * @return the DEPT_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getDEPT_NAME(){
	        return DEPT_NAME;
	    }
		/**
	     * set 制单部门名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setDEPT_NAME(String DEPT_NAME){
	        this.DEPT_NAME=DEPT_NAME;
	    }
	     /**
	     * get 制单机构ID value
	     * @return the ORG_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getORG_ID(){
	        return ORG_ID;
	    }
		/**
	     * set 制单机构ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setORG_ID(String ORG_ID){
	        this.ORG_ID=ORG_ID;
	    }
	     /**
	     * get 制单机构名称 value
	     * @return the ORG_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getORG_NAME(){
	        return ORG_NAME;
	    }
		/**
	     * set 制单机构名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setORG_NAME(String ORG_NAME){
	        this.ORG_NAME=ORG_NAME;
	    }
	     /**
	     * get 账套ID value
	     * @return the LEDGER_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getLEDGER_ID(){
	        return LEDGER_ID;
	    }
		/**
	     * set 账套ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setLEDGER_ID(String LEDGER_ID){
	        this.LEDGER_ID=LEDGER_ID;
	    }
	     /**
	     * get 账套名称 value
	     * @return the LEDGER_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getLEDGER_NAME(){
	        return LEDGER_NAME;
	    }
		/**
	     * set 账套名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setLEDGER_NAME(String LEDGER_NAME){
	        this.LEDGER_NAME=LEDGER_NAME;
	    }
	     /**
	     * get 状态 value
	     * @return the STATE
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getSTATE(){
	        return STATE;
	    }
		/**
	     * set 状态 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setSTATE(String STATE){
	        this.STATE=STATE;
	    }
	     /**
	     * get 删除标记 value
	     * @return the DEL_FLAG
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getDEL_FLAG(){
	        return DEL_FLAG;
	    }
		/**
	     * set 删除标记 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setDEL_FLAG(String DEL_FLAG){
	        this.DEL_FLAG=DEL_FLAG;
	    }
	    /**
	     * get 投诉货品明细ID value
	     * @return the CMPL_PRD_DTL_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getCMPL_PRD_DTL_ID(){
	        return CMPL_PRD_DTL_ID;
	    }
		/**
	     * set 投诉货品明细ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setCMPL_PRD_DTL_ID(String CMPL_PRD_DTL_ID){
	        this.CMPL_PRD_DTL_ID=CMPL_PRD_DTL_ID;
	    }
	     /**
	     * get 货品ID value
	     * @return the PRD_ID
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getPRD_ID(){
	        return PRD_ID;
	    }
		/**
	     * set 货品ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setPRD_ID(String PRD_ID){
	        this.PRD_ID=PRD_ID;
	    }
	     /**
	     * get 货品编号 value
	     * @return the PRD_NO
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getPRD_NO(){
	        return PRD_NO;
	    }
		/**
	     * set 货品编号 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setPRD_NO(String PRD_NO){
	        this.PRD_NO=PRD_NO;
	    }
	     /**
	     * get 货品名称 value
	     * @return the PRD_NAME
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getPRD_NAME(){
	        return PRD_NAME;
	    }
		/**
	     * set 货品名称 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setPRD_NAME(String PRD_NAME){
	        this.PRD_NAME=PRD_NAME;
	    }
	     /**
	     * get 规格型号 value
	     * @return the PRD_SPEC
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getPRD_SPEC(){
	        return PRD_SPEC;
	    }
		/**
	     * set 规格型号 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setPRD_SPEC(String PRD_SPEC){
	        this.PRD_SPEC=PRD_SPEC;
	    }
	     /**
	     * get 产品问题类型 value
	     * @return the PRD_PROBLEM_TYPE
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getPRD_PROBLEM_TYPE(){
	        return PRD_PROBLEM_TYPE;
	    }
		/**
	     * set 产品问题类型 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setPRD_PROBLEM_TYPE(String PRD_PROBLEM_TYPE){
	        this.PRD_PROBLEM_TYPE=PRD_PROBLEM_TYPE;
	    }
	     /**
	     * get 备注说明 value
	     * @return the REMARK
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getREMARK(){
	        return REMARK;
	    }
		/**
	     * set 备注说明 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setREMARK(String REMARK){
	        this.REMARK=REMARK;
	    }
	     /**
	     * get 附件 value
	     * @return the PRD_ATT
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public String getPRD_ATT(){
	        return PRD_ATT;
	    }
		/**
	     * set 附件 value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
	    public void setPRD_ATT(String PRD_ATT){
	        this.PRD_ATT=PRD_ATT;
	    }
	    /**
	     * get 区域ID value
	     * @return the PRD_ATT
		 * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
		public String getAREA_ID() {
			return AREA_ID;
		}
		/**
	     * set 区域ID value
	     * @createdate 2013-08-17 10:29:35
	     * @author wdb
		 * @createdate 2013-08-17 10:29:35
	     */
		public void setAREA_ID(String aREAID) {
			AREA_ID = aREAID;
		}
		public String getEMG_LVL() {
			return EMG_LVL;
		}
		public void setEMG_LVL(String eMGLVL) {
			EMG_LVL = eMGLVL;
		}
		/**
		 * @return the sHIP_POINT_ID
		 */
		public String getSHIP_POINT_ID() {
			return SHIP_POINT_ID;
		}
		/**
		 * @param sHIPPOINTID the sHIP_POINT_ID to set
		 */
		public void setSHIP_POINT_ID(String sHIPPOINTID) {
			SHIP_POINT_ID = sHIPPOINTID;
		}
		/**
		 * @return the sHIP_POINT_NO
		 */
		public String getSHIP_POINT_NO() {
			return SHIP_POINT_NO;
		}
		/**
		 * @param sHIPPOINTNO the sHIP_POINT_NO to set
		 */
		public void setSHIP_POINT_NO(String sHIPPOINTNO) {
			SHIP_POINT_NO = sHIPPOINTNO;
		}
		/**
		 * @return the sHIP_POINT_NAME
		 */
		public String getSHIP_POINT_NAME() {
			return SHIP_POINT_NAME;
		}
		/**
		 * @param sHIPPOINTNAME the sHIP_POINT_NAME to set
		 */
		public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
			SHIP_POINT_NAME = sHIPPOINTNAME;
		}
		/**
		 * @return the pROV
		 */
		public String getPROV() {
			return PROV;
		}
		/**
		 * @param pROV the pROV to set
		 */
		public void setPROV(String pROV) {
			PROV = pROV;
		}
		/**
		 * @return the uSE_TIME
		 */
		public String getUSE_TIME() {
			return USE_TIME;
		}
		/**
		 * @param uSETIME the uSE_TIME to set
		 */
		public void setUSE_TIME(String uSETIME) {
			USE_TIME = uSETIME;
		}
		/**
		 * @return the pRDC_DATE
		 */
		public String getPRDC_DATE() {
			return PRDC_DATE;
		}
		/**
		 * @param pRDCDATE the pRDC_DATE to set
		 */
		public void setPRDC_DATE(String pRDCDATE) {
			PRDC_DATE = pRDCDATE;
		}
		public String getADVS_SOURCE() {
			return ADVS_SOURCE;
		}
		public void setADVS_SOURCE(String aDVSSOURCE) {
			ADVS_SOURCE = aDVSSOURCE;
		}
		public String getCUSTOMER_TEL() {
			return CUSTOMER_TEL;
		}
		public void setCUSTOMER_TEL(String cUSTOMERTEL) {
			CUSTOMER_TEL = cUSTOMERTEL;
		}
		public String getCUSTOMER_NAME() {
			return CUSTOMER_NAME;
		}
		public void setCUSTOMER_NAME(String cUSTOMERNAME) {
			CUSTOMER_NAME = cUSTOMERNAME;
		}
		public String getBUY_DATE() {
			return BUY_DATE;
		}
		public void setBUY_DATE(String bUYDATE) {
			BUY_DATE = bUYDATE;
		}
		public String getPRD_TYPE() {
			return PRD_TYPE;
		}
		public void setPRD_TYPE(String pRDTYPE) {
			PRD_TYPE = pRDTYPE;
		}
		public String getPRD_NUM() {
			return PRD_NUM;
		}
		public void setPRD_NUM(String pRDNUM) {
			PRD_NUM = pRDNUM;
		}
	    
}
