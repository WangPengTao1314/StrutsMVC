/**
 * prjName:喜临门营销平台
 * ucName:渠道培训申请单维护
 * fileName:TrainreqModelChld
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
public class TrainreqModelChld extends BaseModel{
   /** 培训申请单明细ID **/
   private String TRAIN_REQ_DTL_ID;
   /** 培训申请单ID **/
   private String TRAIN_REQ_ID;
   /** 培训类型 **/
   private String TRAIN_TYPE;
   /** 培训对象ID **/
   private String TRAIN_OBJECT_ID;
   /** 培训对象编号 **/
   private String TRAIN_OBJECT_NO;
   /** 培训对象名称 **/
   private String TRAIN_OBJECT_NAME;
   /** 备注 **/
   private String REMARK;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 培训申请单明细ID value
     * @return the TRAIN_REQ_DTL_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_REQ_DTL_ID(){
        return TRAIN_REQ_DTL_ID;
    }
	/**
     * set 培训申请单明细ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_REQ_DTL_ID(String TRAIN_REQ_DTL_ID){
        this.TRAIN_REQ_DTL_ID=TRAIN_REQ_DTL_ID;
    }
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
     * get 培训对象ID value
     * @return the TRAIN_OBJECT_ID
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_OBJECT_ID(){
        return TRAIN_OBJECT_ID;
    }
	/**
     * set 培训对象ID value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_OBJECT_ID(String TRAIN_OBJECT_ID){
        this.TRAIN_OBJECT_ID=TRAIN_OBJECT_ID;
    }
     /**
     * get 培训对象编号 value
     * @return the TRAIN_OBJECT_NO
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_OBJECT_NO(){
        return TRAIN_OBJECT_NO;
    }
	/**
     * set 培训对象编号 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_OBJECT_NO(String TRAIN_OBJECT_NO){
        this.TRAIN_OBJECT_NO=TRAIN_OBJECT_NO;
    }
     /**
     * get 培训对象名称 value
     * @return the TRAIN_OBJECT_NAME
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getTRAIN_OBJECT_NAME(){
        return TRAIN_OBJECT_NAME;
    }
	/**
     * set 培训对象名称 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setTRAIN_OBJECT_NAME(String TRAIN_OBJECT_NAME){
        this.TRAIN_OBJECT_NAME=TRAIN_OBJECT_NAME;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-02-28 14:01:04
     * @author wzg
	 * @createdate 2014-02-28 14:01:04
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
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
}