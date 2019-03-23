/**
 * prjName:喜临门营销平台
 * ucName:渠道培训申请单维护
 * fileName:TrainreqModel
*/
package com.hoperun.drp.oamg.trainreq.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author ghx
 * *@createdate 2014-02-28 14:01:04
 */
public class TrainreqModel extends BaseModel{
   /** 培训申请单ID **/
   private String TRAIN_REQ_ID;
   /** 培训申请单编号 **/
   private String TRAIN_REQ_NO;
   /** 申请加盟商ID **/
   private String CHANN_ID;
   /** 申请加盟商编号 **/
   private String CHANN_NO;
   /** 申请加盟商名称 **/
   private String CHANN_NAME;
   /** 培训课程编号 **/
   private String TRAIN_COURSE_NO;
   /** 培训课程名称 **/
   private String TRAIN_COURSE_NAME;
   /** 培训类型 **/
   private String TRAIN_TYPE;
   /** 培训时间从 **/
   private String TRAIN_TIME_BEG;
   /** 培训时间到 **/
   private String TRAIN_TIME_END;
   /** 培训地点 **/
   private String TRAIN_ADDR;
   /** 申请参加人数 **/
   private String REQ_JOIN_NUM;
   /** 额定人数 **/
   private String FIXED_PSON_NUM;   
   /** 培训目的 **/
   private String REQ_REASON;
   /** 培训课程ID **/
   private String TRAIN_COURSE_ID;
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
   /** 账套 **/
   private String LEDGER_ID;
   /** 账套名称 **/
   private String LEDGER_NAME;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 培训申请单ID value
     * @return the TRAIN_REQ_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_REQ_ID(){
        return TRAIN_REQ_ID;
    }
	/**
     * set 培训申请单ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_REQ_ID(String TRAIN_REQ_ID){
        this.TRAIN_REQ_ID=TRAIN_REQ_ID;
    }
     /**
     * get 培训申请单编号 value
     * @return the TRAIN_REQ_NO
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_REQ_NO(){
        return TRAIN_REQ_NO;
    }
	/**
     * set 培训申请单编号 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_REQ_NO(String TRAIN_REQ_NO){
        this.TRAIN_REQ_NO=TRAIN_REQ_NO;
    }
     /**
     * get 申请加盟商ID value
     * @return the CHANN_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getCHANN_ID(){
        return CHANN_ID;
    }
	/**
     * set 申请加盟商ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setCHANN_ID(String CHANN_ID){
        this.CHANN_ID=CHANN_ID;
    }
     /**
     * get 申请加盟商编号 value
     * @return the CHANN_NO
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getCHANN_NO(){
        return CHANN_NO;
    }
	/**
     * set 申请加盟商编号 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setCHANN_NO(String CHANN_NO){
        this.CHANN_NO=CHANN_NO;
    }
     /**
     * get 申请加盟商名称 value
     * @return the CHANN_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getCHANN_NAME(){
        return CHANN_NAME;
    }
	/**
     * set 申请加盟商名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setCHANN_NAME(String CHANN_NAME){
        this.CHANN_NAME=CHANN_NAME;
    }
     /**
     * get 培训课程编号 value
     * @return the TRAIN_COURSE_NO
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_COURSE_NO(){
        return TRAIN_COURSE_NO;
    }
	/**
     * set 培训课程编号 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_COURSE_NO(String TRAIN_COURSE_NO){
        this.TRAIN_COURSE_NO=TRAIN_COURSE_NO;
    }
     /**
     * get 培训课程名称 value
     * @return the TRAIN_COURSE_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_COURSE_NAME(){
        return TRAIN_COURSE_NAME;
    }
	/**
     * set 培训课程名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_COURSE_NAME(String TRAIN_COURSE_NAME){
        this.TRAIN_COURSE_NAME=TRAIN_COURSE_NAME;
    }
     /**
     * get 培训类型 value
     * @return the TRAIN_TYPE
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_TYPE(){
        return TRAIN_TYPE;
    }
	/**
     * set 培训类型 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_TYPE(String TRAIN_TYPE){
        this.TRAIN_TYPE=TRAIN_TYPE;
    }
     /**
     * get 培训时间从 value
     * @return the TRAIN_TIME_BEG
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_TIME_BEG(){
        return TRAIN_TIME_BEG;
    }
	/**
     * set 培训时间从 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_TIME_BEG(String TRAIN_TIME_BEG){
        this.TRAIN_TIME_BEG=TRAIN_TIME_BEG;
    }
     /**
     * get 培训时间到 value
     * @return the TRAIN_TIME_END
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_TIME_END(){
        return TRAIN_TIME_END;
    }
	/**
     * set 培训时间到 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_TIME_END(String TRAIN_TIME_END){
        this.TRAIN_TIME_END=TRAIN_TIME_END;
    }
     /**
     * get 培训地点 value
     * @return the TRAIN_ADDR
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_ADDR(){
        return TRAIN_ADDR;
    }
	/**
     * set 培训地点 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_ADDR(String TRAIN_ADDR){
        this.TRAIN_ADDR=TRAIN_ADDR;
    }
     /**
     * get 申请参加人数 value
     * @return the REQ_JOIN_NUM
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getREQ_JOIN_NUM(){
        return REQ_JOIN_NUM;
    }
	/**
     * set 申请参加人数 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setREQ_JOIN_NUM(String REQ_JOIN_NUM){
        this.REQ_JOIN_NUM=REQ_JOIN_NUM;
    }
     /**
     * get 培训目的 value
     * @return the REQ_REASON
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getREQ_REASON(){
        return REQ_REASON;
    }
	/**
     * set 培训目的 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setREQ_REASON(String REQ_REASON){
        this.REQ_REASON=REQ_REASON;
    }
     /**
     * get 培训课程ID value
     * @return the TRAIN_COURSE_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_COURSE_ID(){
        return TRAIN_COURSE_ID;
    }
	/**
     * set 培训课程ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_COURSE_ID(String TRAIN_COURSE_ID){
        this.TRAIN_COURSE_ID=TRAIN_COURSE_ID;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 更新人名称 value
     * @return the UPD_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getUPD_NAME(){
        return UPD_NAME;
    }
	/**
     * set 更新人名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setUPD_NAME(String UPD_NAME){
        this.UPD_NAME=UPD_NAME;
    }
     /**
     * get 更新人ID value
     * @return the UPDATOR
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getUPDATOR(){
        return UPDATOR;
    }
	/**
     * set 更新人ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setUPDATOR(String UPDATOR){
        this.UPDATOR=UPDATOR;
    }
     /**
     * get 更新时间 value
     * @return the UPD_TIME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getUPD_TIME(){
        return UPD_TIME;
    }
	/**
     * set 更新时间 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setUPD_TIME(String UPD_TIME){
        this.UPD_TIME=UPD_TIME;
    }
     /**
     * get 制单部门ID value
     * @return the DEPT_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getDEPT_ID(){
        return DEPT_ID;
    }
	/**
     * set 制单部门ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setDEPT_ID(String DEPT_ID){
        this.DEPT_ID=DEPT_ID;
    }
     /**
     * get 制单部门名称 value
     * @return the DEPT_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getDEPT_NAME(){
        return DEPT_NAME;
    }
	/**
     * set 制单部门名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setDEPT_NAME(String DEPT_NAME){
        this.DEPT_NAME=DEPT_NAME;
    }
     /**
     * get 制单机构ID value
     * @return the ORG_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getORG_ID(){
        return ORG_ID;
    }
	/**
     * set 制单机构ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setORG_ID(String ORG_ID){
        this.ORG_ID=ORG_ID;
    }
     /**
     * get 制单机构名称 value
     * @return the ORG_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getORG_NAME(){
        return ORG_NAME;
    }
	/**
     * set 制单机构名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setORG_NAME(String ORG_NAME){
        this.ORG_NAME=ORG_NAME;
    }
     /**
     * get 账套 value
     * @return the LEDGER_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getLEDGER_ID(){
        return LEDGER_ID;
    }
	/**
     * set 账套 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setLEDGER_ID(String LEDGER_ID){
        this.LEDGER_ID=LEDGER_ID;
    }
     /**
     * get 账套名称 value
     * @return the LEDGER_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getLEDGER_NAME(){
        return LEDGER_NAME;
    }
	/**
     * set 账套名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setLEDGER_NAME(String LEDGER_NAME){
        this.LEDGER_NAME=LEDGER_NAME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
    
    public String getFIXED_PSON_NUM() {
    	return FIXED_PSON_NUM;
    }
    public void setFIXED_PSON_NUM(String fIXEDPSONNUM) {
    	FIXED_PSON_NUM = fIXEDPSONNUM;
    }
}