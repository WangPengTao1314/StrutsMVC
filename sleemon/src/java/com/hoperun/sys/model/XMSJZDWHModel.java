/**
 * prjName:喜临门营销平台
 * ucName:项目数据字典维护
 * fileName:SjzdwhModel
*/
package com.hoperun.sys.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-30 10:18:20
 */
public class XMSJZDWHModel extends BaseModel{
   /** 数据ID **/
   private String DATA_ID;
   /** 数据编号 **/
   private String DATA_CODE;
   /** 数据名称 **/
   private String DATA_NAME;
   /** 删除标记 **/
   private String DELFLAG;
   /** 备注 **/
   private String DATA_REMARK;
   /** 制单人名称 **/
   private String CRE_NAME;
   /** 制单人ID **/
   private String CREATOR;
   /** 制单时间 **/
   private String CRE_TIME;
   /** 状态 **/
   private String STATE;
     /**
     * get 数据ID value
     * @return the DATA_ID
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_ID(){
        return DATA_ID;
    }
	/**
     * set 数据ID value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_ID(String DATA_ID){
        this.DATA_ID=DATA_ID;
    }
     /**
     * get 数据编号 value
     * @return the DATA_CODE
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_CODE(){
        return DATA_CODE;
    }
	/**
     * set 数据编号 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_CODE(String DATA_CODE){
        this.DATA_CODE=DATA_CODE;
    }
     /**
     * get 数据名称 value
     * @return the DATA_NAME
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_NAME(){
        return DATA_NAME;
    }
	/**
     * set 数据名称 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_NAME(String DATA_NAME){
        this.DATA_NAME=DATA_NAME;
    }
     /**
     * get 删除标记 value
     * @return the DELFLAG
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDELFLAG(){
        return DELFLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDELFLAG(String DELFLAG){
        this.DELFLAG=DELFLAG;
    }
     /**
     * get 备注 value
     * @return the DATA_REMARK
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_REMARK(){
        return DATA_REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_REMARK(String DATA_REMARK){
        this.DATA_REMARK=DATA_REMARK;
    }
     /**
     * get 制单人名称 value
     * @return the CRE_NAME
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getCRE_NAME(){
        return CRE_NAME;
    }
	/**
     * set 制单人名称 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setCRE_NAME(String CRE_NAME){
        this.CRE_NAME=CRE_NAME;
    }
     /**
     * get 制单人ID value
     * @return the CREATOR
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getCREATOR(){
        return CREATOR;
    }
	/**
     * set 制单人ID value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setCREATOR(String CREATOR){
        this.CREATOR=CREATOR;
    }
     /**
     * get 制单时间 value
     * @return the CRE_TIME
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getCRE_TIME(){
        return CRE_TIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setCRE_TIME(String CRE_TIME){
        this.CRE_TIME=CRE_TIME;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
    }
}