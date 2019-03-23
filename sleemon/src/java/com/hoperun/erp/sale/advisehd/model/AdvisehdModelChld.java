/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议查询
 * fileName:AdviseModelChld
*/
package com.hoperun.erp.sale.advisehd.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public class AdvisehdModelChld extends BaseModel{
   /** 投诉货品明细ID **/
   private String CMPL_PRD_DTL_ID;
   /** 投诉与建议ID **/
   private String CMPL_ADVS_ID;
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
   /** 删除标记 **/
   private String DEL_FLAG;
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
}