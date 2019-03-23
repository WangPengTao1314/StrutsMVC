package com.hoperun.drp.areaser.sotoadvorder.model;

import com.hoperun.commons.model.BaseModel;

public class SotoadvorderGoodModelChld extends BaseModel{
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
	   /** 特殊工艺 **/
	   private String SPCL_TECH;
	   /** 单价 **/
	   private String PRICE;
	   /** 折扣率 **/
	   private String DECT_RATE;
	   /** 折扣价 **/
	   private String DECT_PRICE;
	   /** 订货数量 **/
	   private String ORDER_NUM;
	   /** 折后金额 **/
	   private String DECT_AMOUNT;
	   /** 删除标记 **/
	   private String DEL_FLAG;
	   /** 是否备货 **/
	   private String IS_BACKUP_FLAG;
	   /** 是否非标 **/
	   private String IS_NO_STAND_FLAG;
	   /** 特殊工艺标记 **/
	   private String SPCL_TECH_FLAG;
	   /** 生产工厂ID **/
	   private String FACTORY_ID;
	   /**生产工厂名称 **/
	   private String FACTORY_NAME;
	   /** 来源单据ID **/
	   private String FROM_BILL_DTL_ID;
	   /** 预计发货日期**/
	   private String ADVC_SEND_DATE;
	   /** 发货点ID**/
	   private String SHIP_POINT_ID;
	   /** 发货点名称**/
	   private String SHIP_POINT_NAME;
	   /** 订货金额 **/
	   private String ORDER_AMOUNT;
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
	   /**订单特殊工艺ID**/
	   private String SPCL_TECH_ID;
	   /**订货订单编号**/
	   private String GOODS_ORDER_NO;
	   /**   信用冻结单价**/
	   private String CREDIT_FREEZE_PRICE;
	   /** 要求到货日期 **/
	   private String ORDER_RECV_DATE;
	   /**抵库数量*/
	   private String PLE_REP;
	   /**备注*/
	   private String REMARK;
	   /**已实际发货数量*/
	   private String SENDED_NUM;
	   
	   
	   
		/**
	 * @return the sENDED_NUM
	 */
	public String getSENDED_NUM() {
		return SENDED_NUM;
	}
	/**
	 * @param sENDEDNUM the sENDED_NUM to set
	 */
	public void setSENDED_NUM(String sENDEDNUM) {
		SENDED_NUM = sENDEDNUM;
	}
		/**
	 * @return the rEMARK
	 */
	public String getREMARK() {
		return REMARK;
	}
	/**
	 * @param rEMARK the rEMARK to set
	 */
	public void setREMARK(String rEMARK) {
		REMARK = rEMARK;
	}
		/**
	 * @return the pLE_REP
	 */
	public String getPLE_REP() {
		return PLE_REP;
	}
	/**
	 * @param pLEREP the pLE_REP to set
	 */
	public void setPLE_REP(String pLEREP) {
		PLE_REP = pLEREP;
	}
		/**
	     * get 订货订单明细ID value
	     * @return the GOODS_ORDER_DTL_ID
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getGOODS_ORDER_DTL_ID(){
	        return GOODS_ORDER_DTL_ID;
	    }
		/**
	     * set 订货订单明细ID value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setGOODS_ORDER_DTL_ID(String GOODS_ORDER_DTL_ID){
	        this.GOODS_ORDER_DTL_ID=GOODS_ORDER_DTL_ID;
	    }
	     /**
	     * get 订货订单ID value
	     * @return the GOODS_ORDER_ID
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getGOODS_ORDER_ID(){
	        return GOODS_ORDER_ID;
	    }
		/**
	     * set 订货订单ID value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setGOODS_ORDER_ID(String GOODS_ORDER_ID){
	        this.GOODS_ORDER_ID=GOODS_ORDER_ID;
	    }
	     /**
	     * get 货品ID value
	     * @return the PRD_ID
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getPRD_ID(){
	        return PRD_ID;
	    }
		/**
	     * set 货品ID value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setPRD_ID(String PRD_ID){
	        this.PRD_ID=PRD_ID;
	    }
	     /**
	     * get 货品编号 value
	     * @return the PRD_NO
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getPRD_NO(){
	        return PRD_NO;
	    }
		/**
	     * set 货品编号 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setPRD_NO(String PRD_NO){
	        this.PRD_NO=PRD_NO;
	    }
	     /**
	     * get 货品名称 value
	     * @return the PRD_NAME
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getPRD_NAME(){
	        return PRD_NAME;
	    }
		/**
	     * set 货品名称 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setPRD_NAME(String PRD_NAME){
	        this.PRD_NAME=PRD_NAME;
	    }
	     /**
	     * get 规格型号 value
	     * @return the PRD_SPEC
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getPRD_SPEC(){
	        return PRD_SPEC;
	    }
		/**
	     * set 规格型号 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setPRD_SPEC(String PRD_SPEC){
	        this.PRD_SPEC=PRD_SPEC;
	    }
	     /**
	     * get 颜色 value
	     * @return the PRD_COLOR
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getPRD_COLOR(){
	        return PRD_COLOR;
	    }
		/**
	     * set 颜色 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setPRD_COLOR(String PRD_COLOR){
	        this.PRD_COLOR=PRD_COLOR;
	    }
	     /**
	     * get 品牌 value
	     * @return the BRAND
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getBRAND(){
	        return BRAND;
	    }
		/**
	     * set 品牌 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setBRAND(String BRAND){
	        this.BRAND=BRAND;
	    }
	     /**
	     * get 标准单位 value
	     * @return the STD_UNIT
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getSTD_UNIT(){
	        return STD_UNIT;
	    }
		/**
	     * set 标准单位 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setSTD_UNIT(String STD_UNIT){
	        this.STD_UNIT=STD_UNIT;
	    }
	     /**
	     * get 特殊工艺 value
	     * @return the SPCL_TECH
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getSPCL_TECH(){
	        return SPCL_TECH;
	    }
		/**
	     * set 特殊工艺 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setSPCL_TECH(String SPCL_TECH){
	        this.SPCL_TECH=SPCL_TECH;
	    }
	     /**
	     * get 单价 value
	     * @return the PRICE
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getPRICE(){
	        return PRICE;
	    }
		/**
	     * set 单价 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setPRICE(String PRICE){
	        this.PRICE=PRICE;
	    }
	     /**
	     * get 折扣率 value
	     * @return the DECT_RATE
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getDECT_RATE(){
	        return DECT_RATE;
	    }
		/**
	     * set 折扣率 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setDECT_RATE(String DECT_RATE){
	        this.DECT_RATE=DECT_RATE;
	    }
	     /**
	     * get 折扣价 value
	     * @return the DECT_PRICE
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getDECT_PRICE(){
	        return DECT_PRICE;
	    }
		/**
	     * set 折扣价 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setDECT_PRICE(String DECT_PRICE){
	        this.DECT_PRICE=DECT_PRICE;
	    }
	     /**
	     * get 订货数量 value
	     * @return the ORDER_NUM
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getORDER_NUM(){
	        return ORDER_NUM;
	    }
		/**
	     * set 订货数量 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setORDER_NUM(String ORDER_NUM){
	        this.ORDER_NUM=ORDER_NUM;
	    }
	     /**
	     * get 折后金额 value
	     * @return the DECT_AMOUNT
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getDECT_AMOUNT(){
	        return DECT_AMOUNT;
	    }
		/**
	     * set 折后金额 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setDECT_AMOUNT(String DECT_AMOUNT){
	        this.DECT_AMOUNT=DECT_AMOUNT;
	    }
	     /**
	     * get 删除标记 value
	     * @return the DEL_FLAG
		 * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public String getDEL_FLAG(){
	        return DEL_FLAG;
	    }
		/**
	     * set 删除标记 value
	     * @createdate 2013-08-30 15:55:09
	     * @author zzb
		 * @createdate 2013-08-30 15:55:09
	     */
	    public void setDEL_FLAG(String DEL_FLAG){
	        this.DEL_FLAG=DEL_FLAG;
	    }
		public String getIS_BACKUP_FLAG() {
			return IS_BACKUP_FLAG;
		}
		public void setIS_BACKUP_FLAG(String iSBACKUPFLAG) {
			IS_BACKUP_FLAG = iSBACKUPFLAG;
		}
		public String getSPCL_TECH_FLAG() {
			return SPCL_TECH_FLAG;
		}
		public void setSPCL_TECH_FLAG(String sPCLTECHFLAG) {
			SPCL_TECH_FLAG = sPCLTECHFLAG;
		}
		public String getFACTORY_ID() {
			return FACTORY_ID;
		}
		public void setFACTORY_ID(String fACTORYID) {
			FACTORY_ID = fACTORYID;
		}
		public String getFACTORY_NAME() {
			return FACTORY_NAME;
		}
		public void setFACTORY_NAME(String fACTORYNAME) {
			FACTORY_NAME = fACTORYNAME;
		}
		public String getFROM_BILL_DTL_ID() {
			return FROM_BILL_DTL_ID;
		}
		public void setFROM_BILL_DTL_ID(String fROMBILLDTLID) {
			FROM_BILL_DTL_ID = fROMBILLDTLID;
		}
		public String getADVC_SEND_DATE() {
			return ADVC_SEND_DATE;
		}
		public void setADVC_SEND_DATE(String aDVCSENDDATE) {
			ADVC_SEND_DATE = aDVCSENDDATE;
		}
		public String getSHIP_POINT_ID() {
			return SHIP_POINT_ID;
		}
		public void setSHIP_POINT_ID(String sHIPPOINTID) {
			SHIP_POINT_ID = sHIPPOINTID;
		}
		public String getSHIP_POINT_NAME() {
			return SHIP_POINT_NAME;
		}
		public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
			SHIP_POINT_NAME = sHIPPOINTNAME;
		}
		public String getORDER_AMOUNT() {
			return ORDER_AMOUNT;
		}
		public void setORDER_AMOUNT(String oRDERAMOUNT) {
			ORDER_AMOUNT = oRDERAMOUNT;
		}
		public String getIS_NO_STAND_FLAG() {
			return IS_NO_STAND_FLAG;
		}
		public void setIS_NO_STAND_FLAG(String iSNOSTANDFLAG) {
			IS_NO_STAND_FLAG = iSNOSTANDFLAG;
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
		public void setSPCL_TECH_ID(String sPCLTECHID) {
			SPCL_TECH_ID = sPCLTECHID;
		}
		public String getGOODS_ORDER_NO() {
			return GOODS_ORDER_NO;
		}
		public void setGOODS_ORDER_NO(String gOODSORDERNO) {
			GOODS_ORDER_NO = gOODSORDERNO;
		}

	    public String getCREDIT_FREEZE_PRICE() {
	    	return CREDIT_FREEZE_PRICE;
	    }
	    public void setCREDIT_FREEZE_PRICE(String cREDITFREEZEPRICE) {
	    	CREDIT_FREEZE_PRICE = cREDITFREEZEPRICE;
	    }
		public String getORDER_RECV_DATE() {
			return ORDER_RECV_DATE;
		}
		public void setORDER_RECV_DATE(String oRDERRECVDATE) {
			ORDER_RECV_DATE = oRDERRECVDATE;
		}
}
