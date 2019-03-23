/**
 * prjName:喜临门营销平台
 * ucName:促销品发放
 * fileName:PrmtexitModelChld
*/
package com.hoperun.erp.sale.prmtexit.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-19 16:54:28
 */
public class PrmtexitModelChld extends BaseModel{
   /** 促销品发放明细ID **/
   private String PRMT_GOODS_EXTD_DTL_ID;
   /** 促销品发放ID **/
   private String PRMT_GOODS_EXTD_ID;
   /** 货品ID **/
   private String PRD_ID;
   /** 货品编号 **/
   private String PRD_NO;
   /** 货品名称 **/
   private String PRD_NAME;
   /** 规格型号 **/
   private String PRD_SPEC;
   /** 颜色 **/
   private String PRD_COLOR;
   /** 品牌 **/
   private String BRAND;
   /** 标准单位 **/
   private String STD_UNIT;
   /** 发放数量 **/
   private String EXTD_NUM;
   /** 备注 **/
   private String REMARK;
   /** 删除标记 **/
   private String DEL_FLAG;
     /**
     * get 促销品发放明细ID value
     * @return the PRMT_GOODS_EXTD_DTL_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_GOODS_EXTD_DTL_ID(){
        return PRMT_GOODS_EXTD_DTL_ID;
    }
	/**
     * set 促销品发放明细ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_GOODS_EXTD_DTL_ID(String PRMT_GOODS_EXTD_DTL_ID){
        this.PRMT_GOODS_EXTD_DTL_ID=PRMT_GOODS_EXTD_DTL_ID;
    }
     /**
     * get 促销品发放ID value
     * @return the PRMT_GOODS_EXTD_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRMT_GOODS_EXTD_ID(){
        return PRMT_GOODS_EXTD_ID;
    }
	/**
     * set 促销品发放ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRMT_GOODS_EXTD_ID(String PRMT_GOODS_EXTD_ID){
        this.PRMT_GOODS_EXTD_ID=PRMT_GOODS_EXTD_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 发放数量 value
     * @return the EXTD_NUM
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getEXTD_NUM(){
        return EXTD_NUM;
    }
	/**
     * set 发放数量 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setEXTD_NUM(String EXTD_NUM){
        this.EXTD_NUM=EXTD_NUM;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-10-19 16:54:28
     * @author chenj
	 * @createdate 2013-10-19 16:54:28
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
}