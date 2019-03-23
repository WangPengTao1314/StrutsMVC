/**
 * prjName:喜临门营销平台
 * ucName:订货订单维护
 * fileName:GoodsorderhdModelChld
*/
package com.hoperun.drp.sale.goodsorderhd.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func 订货订单明细
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-15 10:35:10
 */
public class GoodsorderhdModelChld extends BaseModel{
   /**
	 * 
	 */
	private static final long serialVersionUID = 1929584300934316770L;
	
/** 订货订单明细ID **/
   private String GOODS_ORDER_DTL_ID;
   /** 订货订单ID **/
   private String GOODS_ORDER_ID;
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
   /** 特殊工艺标记 **/
   private String SPCL_TECH_FLAG;
   /** 单价 **/
   private String PRICE;
   /** 折扣率 **/
   private String DECT_RATE;
   /** 折扣价 **/
   private String DECT_PRICE;
   /** 订货数量 **/
   private String ORDER_NUM;
   /** 订货金额 **/
   private String ORDER_AMOUNT;
   /** 备注 **/
   private String REMARK;
   /** 会话ID **/
   private String SESSION_ID;
   /** 特殊工艺加价倍数 **/
   private String TECH_PRICE_MULT;
   /** 删除标记 **/
   private String DEL_FLAG;
   /** 体积 **/
   private String VOLUME;
   /** 总体积 **/
   private String TOTAL_VOLUME;
   /** 原货品ID **/
   private String OLD_PRD_ID;
   /** 原货品编号  **/
   private String OLD_PRD_NO;
   /** 原货品名称  **/
   private String OLD_PRD_NAME;
   /** 原单价 **/
   private String OLD_PRICE;
   /**特殊工艺id*/
   private String SPCL_TECH_ID;
   /**原特殊工艺ID*/
   private String OLD_SPCL_TECH_ID;
   /**是否非标  1->非标**/
   private String IS_NO_STAND_FLAG;
   /**要求到货日期 **/
   private String ORDER_RECV_DATE;
   /** 特殊工艺加价倍数  **/
   private String TECH_MULT;
   /** 特殊工艺加价 金额  **/
   private String TECH_AMOUNT;
   private String SPCL_SPEC_REMARK;
   /**是否备货，即是否抵库 **/
   private String IS_BACKUP_FLAG;
   /** 冻结单价 **/
   private String CREDIT_FREEZE_PRICE;
   /** 行号 **/
   private String ROW_NO;
   
   
     /**
     * get 订货订单明细ID value
     * @return the GOODS_ORDER_DTL_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getGOODS_ORDER_DTL_ID(){
        return GOODS_ORDER_DTL_ID;
    }
	/**
     * set 订货订单明细ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setGOODS_ORDER_DTL_ID(String GOODS_ORDER_DTL_ID){
        this.GOODS_ORDER_DTL_ID=GOODS_ORDER_DTL_ID;
    }
     /**
     * get 订货订单ID value
     * @return the GOODS_ORDER_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getGOODS_ORDER_ID(){
        return GOODS_ORDER_ID;
    }
	/**
     * set 订货订单ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setGOODS_ORDER_ID(String GOODS_ORDER_ID){
        this.GOODS_ORDER_ID=GOODS_ORDER_ID;
    }
     /**
     * get 货品ID value
     * @return the PRD_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPRD_ID(){
        return PRD_ID;
    }
	/**
     * set 货品ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPRD_ID(String PRD_ID){
        this.PRD_ID=PRD_ID;
    }
     /**
     * get 货品编号 value
     * @return the PRD_NO
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPRD_NO(){
        return PRD_NO;
    }
	/**
     * set 货品编号 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPRD_NO(String PRD_NO){
        this.PRD_NO=PRD_NO;
    }
     /**
     * get 货品名称 value
     * @return the PRD_NAME
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPRD_NAME(){
        return PRD_NAME;
    }
	/**
     * set 货品名称 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPRD_NAME(String PRD_NAME){
        this.PRD_NAME=PRD_NAME;
    }
     /**
     * get 规格型号 value
     * @return the PRD_SPEC
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPRD_SPEC(){
        return PRD_SPEC;
    }
	/**
     * set 规格型号 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPRD_SPEC(String PRD_SPEC){
        this.PRD_SPEC=PRD_SPEC;
    }
     /**
     * get 颜色 value
     * @return the PRD_COLOR
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPRD_COLOR(){
        return PRD_COLOR;
    }
	/**
     * set 颜色 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPRD_COLOR(String PRD_COLOR){
        this.PRD_COLOR=PRD_COLOR;
    }
     /**
     * get 品牌 value
     * @return the BRAND
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getBRAND(){
        return BRAND;
    }
	/**
     * set 品牌 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setBRAND(String BRAND){
        this.BRAND=BRAND;
    }
     /**
     * get 标准单位 value
     * @return the STD_UNIT
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSTD_UNIT(){
        return STD_UNIT;
    }
	/**
     * set 标准单位 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSTD_UNIT(String STD_UNIT){
        this.STD_UNIT=STD_UNIT;
    }
     /**
     * get 特殊工艺标记 value
     * @return the SPCL_TECH_FLAG
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSPCL_TECH_FLAG(){
        return SPCL_TECH_FLAG;
    }
	/**
     * set 特殊工艺标记 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSPCL_TECH_FLAG(String SPCL_TECH_FLAG){
        this.SPCL_TECH_FLAG=SPCL_TECH_FLAG;
    }
     /**
     * get 单价 value
     * @return the PRICE
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getPRICE(){
        return PRICE;
    }
	/**
     * set 单价 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setPRICE(String PRICE){
        this.PRICE=PRICE;
    }
     /**
     * get 折扣率 value
     * @return the DECT_RATE
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getDECT_RATE(){
        return DECT_RATE;
    }
	/**
     * set 折扣率 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setDECT_RATE(String DECT_RATE){
        this.DECT_RATE=DECT_RATE;
    }
     /**
     * get 折扣价 value
     * @return the DECT_PRICE
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getDECT_PRICE(){
        return DECT_PRICE;
    }
	/**
     * set 折扣价 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setDECT_PRICE(String DECT_PRICE){
        this.DECT_PRICE=DECT_PRICE;
    }
     /**
     * get 订货数量 value
     * @return the ORDER_NUM
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORDER_NUM(){
        return ORDER_NUM;
    }
	/**
     * set 订货数量 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORDER_NUM(String ORDER_NUM){
        this.ORDER_NUM=ORDER_NUM;
    }
     /**
     * get 订货金额 value
     * @return the ORDER_AMOUNT
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getORDER_AMOUNT(){
        return ORDER_AMOUNT;
    }
	/**
     * set 订货金额 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setORDER_AMOUNT(String ORDER_AMOUNT){
        this.ORDER_AMOUNT=ORDER_AMOUNT;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 会话ID value
     * @return the SESSION_ID
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getSESSION_ID(){
        return SESSION_ID;
    }
	/**
     * set 会话ID value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setSESSION_ID(String SESSION_ID){
        this.SESSION_ID=SESSION_ID;
    }
     /**
     * get 特殊工艺加价倍数 value
     * @return the TECH_PRICE_MULT
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getTECH_PRICE_MULT(){
        return TECH_PRICE_MULT;
    }
	/**
     * set 特殊工艺加价倍数 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setTECH_PRICE_MULT(String TECH_PRICE_MULT){
        this.TECH_PRICE_MULT=TECH_PRICE_MULT;
    }
     /**
     * get 删除标记 value
     * @return the DEL_FLAG
	 * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public String getDEL_FLAG(){
        return DEL_FLAG;
    }
	/**
     * set 删除标记 value
     * @createdate 2013-09-15 10:35:10
     * @author zzb
	 * @createdate 2013-09-15 10:35:10
     */
    public void setDEL_FLAG(String DEL_FLAG){
        this.DEL_FLAG=DEL_FLAG;
    }
	public String getVOLUME() {
		return VOLUME;
	}
	public void setVOLUME(String vOLUME) {
		VOLUME = vOLUME;
	}
	public String getTOTAL_VOLUME() {
		return TOTAL_VOLUME;
	}
	public void setTOTAL_VOLUME(String tOTALVOLUME) {
		TOTAL_VOLUME = tOTALVOLUME;
	}
	public String getOLD_PRD_ID() {
		return OLD_PRD_ID;
	}
	public void setOLD_PRD_ID(String oLDPRDID) {
		OLD_PRD_ID = oLDPRDID;
	}
	public String getOLD_PRD_NO() {
		return OLD_PRD_NO;
	}
	public void setOLD_PRD_NO(String oLDPRDNO) {
		OLD_PRD_NO = oLDPRDNO;
	}
	public String getOLD_PRD_NAME() {
		return OLD_PRD_NAME;
	}
	public void setOLD_PRD_NAME(String oLDPRDNAME) {
		OLD_PRD_NAME = oLDPRDNAME;
	}
	public String getOLD_PRICE() {
		return OLD_PRICE;
	}
	public void setOLD_PRICE(String oLDPRICE) {
		OLD_PRICE = oLDPRICE;
	}
	public String getSPCL_TECH_ID() {
		return SPCL_TECH_ID;
	}
	public void setSPCL_TECH_ID(String spcl_tech_id) {
		SPCL_TECH_ID = spcl_tech_id;
	}
	/**
	 * @return the oLD_SPCL_TECH_ID
	 */
	public String getOLD_SPCL_TECH_ID() {
		return OLD_SPCL_TECH_ID;
	}
	/**
	 * @param oLDSPCLTECHID the oLD_SPCL_TECH_ID to set
	 */
	public void setOLD_SPCL_TECH_ID(String oLDSPCLTECHID) {
		OLD_SPCL_TECH_ID = oLDSPCLTECHID;
	}
	public String getIS_NO_STAND_FLAG() {
		return IS_NO_STAND_FLAG;
	}
	public void setIS_NO_STAND_FLAG(String iSNOSTANDFLAG) {
		IS_NO_STAND_FLAG = iSNOSTANDFLAG;
	}
	public String getORDER_RECV_DATE() {
		return ORDER_RECV_DATE;
	}
	public void setORDER_RECV_DATE(String oRDERRECVDATE) {
		ORDER_RECV_DATE = oRDERRECVDATE;
	}
	public String getTECH_MULT() {
		return TECH_MULT;
	}
	public void setTECH_MULT(String tECHMULT) {
		TECH_MULT = tECHMULT;
	}
	public String getTECH_AMOUNT() {
		return TECH_AMOUNT;
	}
	public void setTECH_AMOUNT(String tECHAMOUNT) {
		TECH_AMOUNT = tECHAMOUNT;
	}
	/**
	 * @return the sPCL_SPEC_REMARK
	 */
	public String getSPCL_SPEC_REMARK() {
		return SPCL_SPEC_REMARK;
	}
	/**
	 * @param sPCLSPECREMARK the sPCL_SPEC_REMARK to set
	 */
	public void setSPCL_SPEC_REMARK(String sPCLSPECREMARK) {
		SPCL_SPEC_REMARK = sPCLSPECREMARK;
	}
	public String getIS_BACKUP_FLAG() {
		return IS_BACKUP_FLAG;
	}
	public void setIS_BACKUP_FLAG(String iSBACKUPFLAG) {
		IS_BACKUP_FLAG = iSBACKUPFLAG;
	}
	public String getCREDIT_FREEZE_PRICE() {
		return CREDIT_FREEZE_PRICE;
	}
	public void setCREDIT_FREEZE_PRICE(String cREDITFREEZEPRICE) {
		CREDIT_FREEZE_PRICE = cREDITFREEZEPRICE;
	}
	public String getROW_NO() {
		return ROW_NO;
	}
	public void setROW_NO(String rOWNO) {
		ROW_NO = rOWNO;
	}
	
	
	
	
    
    
}