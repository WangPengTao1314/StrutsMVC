/**
 * prjName:喜临门营销平台
 * ucName:项目数据字典维护
 * fileName:SjzdwhModelChld
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
public class XMSJZDWHModelChld extends BaseModel{
   /** 数据明细ID **/
   private String DATA_DTL_ID;
   /** 数据ID **/
   private String DATA_ID;
   /** 数据明细编号 **/
   private String DATA_DTL_CODE;
   /** 数据明细名称 **/
   private String DATA_DTL_NAME;
   /** 上级数据明细ID **/
   private String PAR_DATA_DTL_ID;
   /** 上级数据明细编号 **/
   private String PAR_DATA_DTL_CODE;
   /** 上级数据明细名称 **/
   private String PAR_DATA_DTL_NAME;
   /** 数据明细描述1 **/
   private String DATA_DTL_DES_1;
   /** 数据明细描述2 **/
   private String DATA_DTL_DES_2;
   /** 备注 **/
   private String REMARK;
   /** 删除标记 **/
   private String DELFLAG;
     /**
     * get 数据明细ID value
     * @return the DATA_DTL_ID
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_DTL_ID(){
        return DATA_DTL_ID;
    }
	/**
     * set 数据明细ID value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_DTL_ID(String DATA_DTL_ID){
        this.DATA_DTL_ID=DATA_DTL_ID;
    }
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
     * get 数据明细编号 value
     * @return the DATA_DTL_CODE
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_DTL_CODE(){
        return DATA_DTL_CODE;
    }
	/**
     * set 数据明细编号 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_DTL_CODE(String DATA_DTL_CODE){
        this.DATA_DTL_CODE=DATA_DTL_CODE;
    }
     /**
     * get 数据明细名称 value
     * @return the DATA_DTL_NAME
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_DTL_NAME(){
        return DATA_DTL_NAME;
    }
	/**
     * set 数据明细名称 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_DTL_NAME(String DATA_DTL_NAME){
        this.DATA_DTL_NAME=DATA_DTL_NAME;
    }
     /**
     * get 上级数据明细ID value
     * @return the PAR_DATA_DTL_ID
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getPAR_DATA_DTL_ID(){
        return PAR_DATA_DTL_ID;
    }
	/**
     * set 上级数据明细ID value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setPAR_DATA_DTL_ID(String PAR_DATA_DTL_ID){
        this.PAR_DATA_DTL_ID=PAR_DATA_DTL_ID;
    }
     /**
     * get 上级数据明细编号 value
     * @return the PAR_DATA_DTL_CODE
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getPAR_DATA_DTL_CODE(){
        return PAR_DATA_DTL_CODE;
    }
	/**
     * set 上级数据明细编号 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setPAR_DATA_DTL_CODE(String PAR_DATA_DTL_CODE){
        this.PAR_DATA_DTL_CODE=PAR_DATA_DTL_CODE;
    }
     /**
     * get 上级数据明细名称 value
     * @return the PAR_DATA_DTL_NAME
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getPAR_DATA_DTL_NAME(){
        return PAR_DATA_DTL_NAME;
    }
	/**
     * set 上级数据明细名称 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setPAR_DATA_DTL_NAME(String PAR_DATA_DTL_NAME){
        this.PAR_DATA_DTL_NAME=PAR_DATA_DTL_NAME;
    }
     /**
     * get 数据明细描述1 value
     * @return the DATA_DTL_DES_1
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_DTL_DES_1(){
        return DATA_DTL_DES_1;
    }
	/**
     * set 数据明细描述1 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_DTL_DES_1(String DATA_DTL_DES_1){
        this.DATA_DTL_DES_1=DATA_DTL_DES_1;
    }
     /**
     * get 数据明细描述2 value
     * @return the DATA_DTL_DES_2
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getDATA_DTL_DES_2(){
        return DATA_DTL_DES_2;
    }
	/**
     * set 数据明细描述2 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setDATA_DTL_DES_2(String DATA_DTL_DES_2){
        this.DATA_DTL_DES_2=DATA_DTL_DES_2;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2014-01-30 10:18:20
     * @author chenj
	 * @createdate 2014-01-30 10:18:20
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
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
}