/**
 * prjName:喜临门营销平台
 * ucName:库房库位信息
 * fileName:StoreModelChld
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
public class StoreModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = -5379808694447639144L;
/** 库位信息ID **/
   private String STORG_ID;
   /** 库房ID **/
   private String STORE_ID;
   /** 库位信息编号 **/
   private String STORG_NO;
   /** 库位信息名称 **/
   private String STORG_NAME;
   /** 存放地点 **/
   private String LAY_ADDR;
   /** 楼层 **/
   private String FLOOR;
   /** 状态 **/
   private String STATE;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 库位信息ID value
     * @return the STORG_ID
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORG_ID(){
        return STORG_ID;
    }
	/**
     * set 库位信息ID value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORG_ID(String STORG_ID){
        this.STORG_ID=STORG_ID;
    }
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
     * get 库位信息编号 value
     * @return the STORG_NO
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORG_NO(){
        return STORG_NO;
    }
	/**
     * set 库位信息编号 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORG_NO(String STORG_NO){
        this.STORG_NO=STORG_NO;
    }
     /**
     * get 库位信息名称 value
     * @return the STORG_NAME
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getSTORG_NAME(){
        return STORG_NAME;
    }
	/**
     * set 库位信息名称 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setSTORG_NAME(String STORG_NAME){
        this.STORG_NAME=STORG_NAME;
    }
     /**
     * get 存放地点 value
     * @return the LAY_ADDR
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getLAY_ADDR(){
        return LAY_ADDR;
    }
	/**
     * set 存放地点 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setLAY_ADDR(String LAY_ADDR){
        this.LAY_ADDR=LAY_ADDR;
    }
     /**
     * get 楼层 value
     * @return the FLOOR
	 * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public String getFLOOR(){
        return FLOOR;
    }
	/**
     * set 楼层 value
     * @createdate 2013-08-13 14:01:22
     * @author wdb
	 * @createdate 2013-08-13 14:01:22
     */
    public void setFLOOR(String FLOOR){
        this.FLOOR=FLOOR;
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
}