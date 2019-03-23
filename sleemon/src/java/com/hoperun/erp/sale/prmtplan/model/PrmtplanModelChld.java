/**
 * prjName:喜临门营销平台
 * ucName:推广促销方案维护
 * fileName:PrmtplanModelChld
*/
package com.hoperun.erp.sale.prmtplan.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-08-23 16:04:28
 */
public class PrmtplanModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
   /** 促销货品组明细ID **/
   private String PRMT_PRD_GROUP_ID;
   /** 促销方案ID **/
   private String PRMT_PLAN_ID;
   /** 货品组ID **/
   private String PRD_GROUP_ID;
   /** 货品组编号 **/
   private String PRD_GROUP_NO;
   /** 货品组名称 **/
   private String PRD_GROUP_NAME;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 促销货品组明细ID value
     * @return the PRMT_PRD_GROUP_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRMT_PRD_GROUP_ID(){
        return PRMT_PRD_GROUP_ID;
    }
	/**
     * set 促销货品组明细ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRMT_PRD_GROUP_ID(String PRMT_PRD_GROUP_ID){
        this.PRMT_PRD_GROUP_ID=PRMT_PRD_GROUP_ID;
    }
     /**
     * get 促销方案ID value
     * @return the PRMT_PLAN_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRMT_PLAN_ID(){
        return PRMT_PLAN_ID;
    }
	/**
     * set 促销方案ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRMT_PLAN_ID(String PRMT_PLAN_ID){
        this.PRMT_PLAN_ID=PRMT_PLAN_ID;
    }
     /**
     * get 货品组ID value
     * @return the PRD_GROUP_ID
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRD_GROUP_ID(){
        return PRD_GROUP_ID;
    }
	/**
     * set 货品组ID value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRD_GROUP_ID(String PRD_GROUP_ID){
        this.PRD_GROUP_ID=PRD_GROUP_ID;
    }
     /**
     * get 货品组编号 value
     * @return the PRD_GROUP_NO
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRD_GROUP_NO(){
        return PRD_GROUP_NO;
    }
	/**
     * set 货品组编号 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRD_GROUP_NO(String PRD_GROUP_NO){
        this.PRD_GROUP_NO=PRD_GROUP_NO;
    }
     /**
     * get 货品组名称 value
     * @return the PRD_GROUP_NAME
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getPRD_GROUP_NAME(){
        return PRD_GROUP_NAME;
    }
	/**
     * set 货品组名称 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setPRD_GROUP_NAME(String PRD_GROUP_NAME){
        this.PRD_GROUP_NAME=PRD_GROUP_NAME;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-08-23 16:04:28
     * @author zzb
	 * @createdate 2013-08-23 16:04:28
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}