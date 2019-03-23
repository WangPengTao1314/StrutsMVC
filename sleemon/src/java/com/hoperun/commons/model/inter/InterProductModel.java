package com.hoperun.commons.model.inter;
/**  
 * @func
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-2 上午10:29:11 
 */
public class InterProductModel extends FixedInterModel {
	/**
	 * 物料编码
	 */
	private String MATERIAL_CODE;
	/**
	 * 品名
	 */
	private String MATERIAL_NAME;
	/**
	 * 单位
	 */
	private String UNIT_ID;
	
	private String UNIT_ID_VAL;
	/**
	 * 存储分类
	 */
	private String STOCK_TYPE;
	
	private String STOCK_TYPE_VAL;
	/**
	 * 销售分类
	 */
	private String SALE_TYPE;
	/**
	 * 财务分类
	 */
	private String FINANCE_TYPE;
	/**
	 * 包装体积
	 */
	private String PACK_VOLUME;
	/**
	 * 重量
	 */
	private String WEIGHT;
	/**
	 * 长度
	 */
	private String LENGTH;
	/**
	 * 宽度
	 */
	private String WIDTH;
	/**
	 * 高
	 */
	private String HEIGHT;
	/**
	 * 厚
	 */
	private String THICKNIESS;
	/**
	 * 颜色
	 */
	private String COLOR;
	/**
	 * 型号
	 */
	private String CITY;
	/**
	 * 克重
	 */
	private String FAW;
	/**
	 * 牛顿
	 */
	private String NEWTON;
	/**
	 * 材质
	 */
	private String TEXTURE;
	/**
	 * 品牌(选项)
	 */
	private String BRAND;
	/**
	 * 
	 */
	private String BRAND_VAL;
	/**
	 * 喜临门花号
	 */
	private String INNER_MATERIAL_MODEL;
	/**
	 * 等级(选项)
	 */
	private String MATERIAL_LEVEL;
	/**
	 * 
	 */
	private String MATERIAL_LEVEL_VAL;
	/**
	 * 内容
	 */
	private String CONTENT;
	/**
	 * 圈数
	 */
	private String CYCLES;
	/**
	 * 直径
	 */
	private String DIAMETER;
	/**
	 * 线径
	 */
	private String WIRE_DIAMETER;
	/**
	 * 口径
	 */
	private String CALIBRE;
	/**
	 * 中径
	 */
	private String MEDIUM_DIAMETER;
	/**
	 * 对应产品
	 */
	private String APPLIED_NAME;
	/**
	 * 张数
	 */
	private String PIECES;
	/**
	 * 层数
	 */
	private String PLIES;
	/**
	 * 密度
	 */
	private String DENSITY;
	/**
	 * 旦尼尔
	 */
	private String DENIER;
	/**
	 * 支数
	 */
	private String COUNTS;
	/**
	 * 规格
	 */
	private String SPEC;
	/**
	 * 钢号
	 */
	private String STEEL_NO;
	/**
	 * 分区
	 */
	private String SUBAREA;
	/**
	 * 图纸编号
	 */
	private String DRAWING_NO;
	/**
	 * 封尾
	 */
	private String ENDCAPPING_TYPE;
	/**
	 * 股数
	 */
	private String STRANDS;
	/**
	 * 切割方式
	 */
	private String CUTTING_MODE;
	/**
	 * 纬度
	 */
	private String LATITUDE;
	/**
	 * 排列
	 */
	private String ARRANGEMENTS;
	/**
	 * 其他特性
	 */
	private String OTHER_FEATURES;
	/**
	 * 生产部门
	 */
	private String PRODUCING_DEPART;
	/**
	 * 仓库
	 */
	private String DEPOT;
	/**
	 * 床垫内芯特性(选项)
	 */
	private String MATTESS_CORE_FEATURE;
	/**
	 * 
	 */
	private String MATTESS_CORE_FEATURE_VAL;
	/**
	 * 主要材质(选项)
	 */
	private String MAIN_MATERIAL;
	/**
	 * 
	 */
	private String MAIN_MATERIAL_VAL;
	/**
	 * 是否分体(选项)
	 */
	private String SPLIT_TYPE;
	/**
	 * 
	 */
	private String SPLIT_TYPE_VAL;
	/**
	 * 软硬级别(选项)
	 */
	private String HARDNESS_LEVEL;
	/**
	 * 
	 */
	private String HARDNESS_LEVEL_VAL;
	/**
	 * 款式(选项)
	 */
	private String STYLE;
	/**
	 * 
	 */
	private String STYLE_VAL;
	/**
	 * 套件数(选项)
	 */
	private String SUITE_NUM;
	/**
	 * 
	 */
	private String SUITE_NUM_VAL;
	/**
	 * 是否可拆洗(选项)
	 */
	private String WASHABLE;
	/**
	 * 
	 */
	private String WASHABLE_VAL;
	/**
	 * 排骨架类型(选项)
	 */
	private String SKELETON_TYPE;
	/**
	 * 
	 */
	private String SKELETON_TYPE_VAL;
	/**
	 * 宜家备注(选项)
	 */
	private String IKEA_NOTE;
	/**
	 * 
	 */
	private String IKEA_NOTE_VAL;
	/**
	 * 花号
	 */
	private String MATERIAL_MODEL;
	/**
	 * 工艺单号
	 */
	private String PROCESS_NO;
	/**
	 * 供货价
	 */
	private String WHOLESALE_PRICE;
	/**
	 * 统一零售价
	 */
	private String RETAIL_PRICE;
	/**
	 * 最低零售价
	 */
	private String LOWEST_PRICE;
	/**
	 * 参考料号1
	 */
	private String REFER_NO1;
	/**
	 * 参考料号2
	 */
	private String REFER_NO2;
	/**
	 * 允超率
	 */
	private String ALLOW_RATE;
	/**
	 * 是否片材
	 */
	private String ISSHEET;
	/**
	 * 包装要求(选项)
	 */
	private String PACKING_REQUIRE;
	/**
	 * 
	 */
	private String PACKING_REQUIRE_VAL;
	/**
	 * 体验标准(选项)
	 */
	private String EXPERIENCE;
	/**
	 * 
	 */
	private String EXPERIENCE_VAL;
	/**
	 * 供应商信息
	 */
	private String SUPPLIER;
	/**
	 * 外观要求
	 */
	private String SURFACE_REQUIRE;
	/**
	 * 合成材质
	 */
	private String COMPOSITOR;
	/**
	 * 加工要求
	 */
	private String PROCESS_REQUIRE;
	/**
	 * 票签使用要求
	 */
	private String TICKET_REQUIRE;
	/**
	 * 料品形态属性(选项)
	 */
	private String MATERIAL_SHAPE;
	/**
	 * 
	 */
	private String MATERIAL_SHAPE_VAL;
	/**
	 * 可生产(选项)
	 */
	private String ALLOW_PRODUCE;
	/**
	 * 
	 */
	private String ALLOW_PRODUCE_VAL;
	/**
	 * 计划上市日期
	 */
	private String PLAN_LAUNCH_DATE;
	/**
	 * 实际上市日期
	 */
	private String LAUNCH_DATE;
	/**
	 * 停产日期
	 */
	private String SHUTDOWN_DATE;
	/**
	 * 图片
	 */
	private String PHOTO;
	public String getMATERIAL_CODE() {
		return MATERIAL_CODE;
	}
	public void setMATERIAL_CODE(String mATERIALCODE) {
		MATERIAL_CODE = mATERIALCODE;
	}
	public String getMATERIAL_NAME() {
		return MATERIAL_NAME;
	}
	public void setMATERIAL_NAME(String mATERIALNAME) {
		MATERIAL_NAME = mATERIALNAME;
	}
	public String getUNIT_ID() {
		return UNIT_ID;
	}
	public void setUNIT_ID(String uNITID) {
		UNIT_ID = uNITID;
	}
	public String getSTOCK_TYPE() {
		return STOCK_TYPE;
	}
	public void setSTOCK_TYPE(String sTOCKTYPE) {
		STOCK_TYPE = sTOCKTYPE;
	}
	public String getSALE_TYPE() {
		return SALE_TYPE;
	}
	public void setSALE_TYPE(String sALETYPE) {
		SALE_TYPE = sALETYPE;
	}
	public String getFINANCE_TYPE() {
		return FINANCE_TYPE;
	}
	public void setFINANCE_TYPE(String fINANCETYPE) {
		FINANCE_TYPE = fINANCETYPE;
	}
	public String getPACK_VOLUME() {
		return PACK_VOLUME;
	}
	public void setPACK_VOLUME(String pACKVOLUME) {
		PACK_VOLUME = pACKVOLUME;
	}
	public String getWEIGHT() {
		return WEIGHT;
	}
	public void setWEIGHT(String wEIGHT) {
		WEIGHT = wEIGHT;
	}
	public String getLENGTH() {
		return LENGTH;
	}
	public void setLENGTH(String lENGTH) {
		LENGTH = lENGTH;
	}
	public String getWIDTH() {
		return WIDTH;
	}
	public void setWIDTH(String wIDTH) {
		WIDTH = wIDTH;
	}
	public String getHEIGHT() {
		return HEIGHT;
	}
	public void setHEIGHT(String hEIGHT) {
		HEIGHT = hEIGHT;
	}
	public String getTHICKNIESS() {
		return THICKNIESS;
	}
	public void setTHICKNIESS(String tHICKNIESS) {
		THICKNIESS = tHICKNIESS;
	}
	public String getCOLOR() {
		return COLOR;
	}
	public void setCOLOR(String cOLOR) {
		COLOR = cOLOR;
	}
	public String getCITY() {
		return CITY;
	}
	public void setCITY(String cITY) {
		CITY = cITY;
	}
	public String getFAW() {
		return FAW;
	}
	public void setFAW(String fAW) {
		FAW = fAW;
	}
	public String getNEWTON() {
		return NEWTON;
	}
	public void setNEWTON(String nEWTON) {
		NEWTON = nEWTON;
	}
	public String getTEXTURE() {
		return TEXTURE;
	}
	public void setTEXTURE(String tEXTURE) {
		TEXTURE = tEXTURE;
	}
	public String getBRAND() {
		return BRAND;
	}
	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}
	public String getBRAND_VAL() {
		return BRAND_VAL;
	}
	public void setBRAND_VAL(String bRANDVAL) {
		BRAND_VAL = bRANDVAL;
	}
	public String getINNER_MATERIAL_MODEL() {
		return INNER_MATERIAL_MODEL;
	}
	public void setINNER_MATERIAL_MODEL(String iNNERMATERIALMODEL) {
		INNER_MATERIAL_MODEL = iNNERMATERIALMODEL;
	}
	public String getMATERIAL_LEVEL() {
		return MATERIAL_LEVEL;
	}
	public void setMATERIAL_LEVEL(String mATERIALLEVEL) {
		MATERIAL_LEVEL = mATERIALLEVEL;
	}
	public String getMATERIAL_LEVEL_VAL() {
		return MATERIAL_LEVEL_VAL;
	}
	public void setMATERIAL_LEVEL_VAL(String mATERIALLEVELVAL) {
		MATERIAL_LEVEL_VAL = mATERIALLEVELVAL;
	}
	public String getCONTENT() {
		return CONTENT;
	}
	public void setCONTENT(String cONTENT) {
		CONTENT = cONTENT;
	}
	public String getCYCLES() {
		return CYCLES;
	}
	public void setCYCLES(String cYCLES) {
		CYCLES = cYCLES;
	}
	public String getDIAMETER() {
		return DIAMETER;
	}
	public void setDIAMETER(String dIAMETER) {
		DIAMETER = dIAMETER;
	}
	public String getWIRE_DIAMETER() {
		return WIRE_DIAMETER;
	}
	public void setWIRE_DIAMETER(String wIREDIAMETER) {
		WIRE_DIAMETER = wIREDIAMETER;
	}
	public String getCALIBRE() {
		return CALIBRE;
	}
	public void setCALIBRE(String cALIBRE) {
		CALIBRE = cALIBRE;
	}
	public String getMEDIUM_DIAMETER() {
		return MEDIUM_DIAMETER;
	}
	public void setMEDIUM_DIAMETER(String mEDIUMDIAMETER) {
		MEDIUM_DIAMETER = mEDIUMDIAMETER;
	}
	public String getAPPLIED_NAME() {
		return APPLIED_NAME;
	}
	public void setAPPLIED_NAME(String aPPLIEDNAME) {
		APPLIED_NAME = aPPLIEDNAME;
	}
	public String getPIECES() {
		return PIECES;
	}
	public void setPIECES(String pIECES) {
		PIECES = pIECES;
	}
	public String getPLIES() {
		return PLIES;
	}
	public void setPLIES(String pLIES) {
		PLIES = pLIES;
	}
	public String getDENSITY() {
		return DENSITY;
	}
	public void setDENSITY(String dENSITY) {
		DENSITY = dENSITY;
	}
	public String getDENIER() {
		return DENIER;
	}
	public void setDENIER(String dENIER) {
		DENIER = dENIER;
	}
	public String getCOUNTS() {
		return COUNTS;
	}
	public void setCOUNTS(String cOUNTS) {
		COUNTS = cOUNTS;
	}
	public String getSPEC() {
		return SPEC;
	}
	public void setSPEC(String sPEC) {
		SPEC = sPEC;
	}
	public String getSTEEL_NO() {
		return STEEL_NO;
	}
	public void setSTEEL_NO(String sTEELNO) {
		STEEL_NO = sTEELNO;
	}
	public String getSUBAREA() {
		return SUBAREA;
	}
	public void setSUBAREA(String sUBAREA) {
		SUBAREA = sUBAREA;
	}
	public String getDRAWING_NO() {
		return DRAWING_NO;
	}
	public void setDRAWING_NO(String dRAWINGNO) {
		DRAWING_NO = dRAWINGNO;
	}
	public String getENDCAPPING_TYPE() {
		return ENDCAPPING_TYPE;
	}
	public void setENDCAPPING_TYPE(String eNDCAPPINGTYPE) {
		ENDCAPPING_TYPE = eNDCAPPINGTYPE;
	}
	public String getSTRANDS() {
		return STRANDS;
	}
	public void setSTRANDS(String sTRANDS) {
		STRANDS = sTRANDS;
	}
	public String getCUTTING_MODE() {
		return CUTTING_MODE;
	}
	public void setCUTTING_MODE(String cUTTINGMODE) {
		CUTTING_MODE = cUTTINGMODE;
	}
	public String getLATITUDE() {
		return LATITUDE;
	}
	public void setLATITUDE(String lATITUDE) {
		LATITUDE = lATITUDE;
	}
	public String getARRANGEMENTS() {
		return ARRANGEMENTS;
	}
	public void setARRANGEMENTS(String aRRANGEMENTS) {
		ARRANGEMENTS = aRRANGEMENTS;
	}
	public String getOTHER_FEATURES() {
		return OTHER_FEATURES;
	}
	public void setOTHER_FEATURES(String oTHERFEATURES) {
		OTHER_FEATURES = oTHERFEATURES;
	}
	public String getPRODUCING_DEPART() {
		return PRODUCING_DEPART;
	}
	public void setPRODUCING_DEPART(String pRODUCINGDEPART) {
		PRODUCING_DEPART = pRODUCINGDEPART;
	}
	public String getDEPOT() {
		return DEPOT;
	}
	public void setDEPOT(String dEPOT) {
		DEPOT = dEPOT;
	}
	public String getMATTESS_CORE_FEATURE() {
		return MATTESS_CORE_FEATURE;
	}
	public void setMATTESS_CORE_FEATURE(String mATTESSCOREFEATURE) {
		MATTESS_CORE_FEATURE = mATTESSCOREFEATURE;
	}
	public String getMATTESS_CORE_FEATURE_VAL() {
		return MATTESS_CORE_FEATURE_VAL;
	}
	public void setMATTESS_CORE_FEATURE_VAL(String mATTESSCOREFEATUREVAL) {
		MATTESS_CORE_FEATURE_VAL = mATTESSCOREFEATUREVAL;
	}
	public String getMAIN_MATERIAL() {
		return MAIN_MATERIAL;
	}
	public void setMAIN_MATERIAL(String mAINMATERIAL) {
		MAIN_MATERIAL = mAINMATERIAL;
	}
	public String getMAIN_MATERIAL_VAL() {
		return MAIN_MATERIAL_VAL;
	}
	public void setMAIN_MATERIAL_VAL(String mAINMATERIALVAL) {
		MAIN_MATERIAL_VAL = mAINMATERIALVAL;
	}
	public String getSPLIT_TYPE() {
		return SPLIT_TYPE;
	}
	public void setSPLIT_TYPE(String sPLITTYPE) {
		SPLIT_TYPE = sPLITTYPE;
	}
	public String getSPLIT_TYPE_VAL() {
		return SPLIT_TYPE_VAL;
	}
	public void setSPLIT_TYPE_VAL(String sPLITTYPEVAL) {
		SPLIT_TYPE_VAL = sPLITTYPEVAL;
	}
	public String getHARDNESS_LEVEL() {
		return HARDNESS_LEVEL;
	}
	public void setHARDNESS_LEVEL(String hARDNESSLEVEL) {
		HARDNESS_LEVEL = hARDNESSLEVEL;
	}
	public String getHARDNESS_LEVEL_VAL() {
		return HARDNESS_LEVEL_VAL;
	}
	public void setHARDNESS_LEVEL_VAL(String hARDNESSLEVELVAL) {
		HARDNESS_LEVEL_VAL = hARDNESSLEVELVAL;
	}
	public String getSTYLE() {
		return STYLE;
	}
	public void setSTYLE(String sTYLE) {
		STYLE = sTYLE;
	}
	public String getSTYLE_VAL() {
		return STYLE_VAL;
	}
	public void setSTYLE_VAL(String sTYLEVAL) {
		STYLE_VAL = sTYLEVAL;
	}
	public String getSUITE_NUM() {
		return SUITE_NUM;
	}
	public void setSUITE_NUM(String sUITENUM) {
		SUITE_NUM = sUITENUM;
	}
	public String getSUITE_NUM_VAL() {
		return SUITE_NUM_VAL;
	}
	public void setSUITE_NUM_VAL(String sUITENUMVAL) {
		SUITE_NUM_VAL = sUITENUMVAL;
	}
	public String getWASHABLE() {
		return WASHABLE;
	}
	public void setWASHABLE(String wASHABLE) {
		WASHABLE = wASHABLE;
	}
	public String getWASHABLE_VAL() {
		return WASHABLE_VAL;
	}
	public void setWASHABLE_VAL(String wASHABLEVAL) {
		WASHABLE_VAL = wASHABLEVAL;
	}
	public String getSKELETON_TYPE() {
		return SKELETON_TYPE;
	}
	public void setSKELETON_TYPE(String sKELETONTYPE) {
		SKELETON_TYPE = sKELETONTYPE;
	}
	public String getSKELETON_TYPE_VAL() {
		return SKELETON_TYPE_VAL;
	}
	public void setSKELETON_TYPE_VAL(String sKELETONTYPEVAL) {
		SKELETON_TYPE_VAL = sKELETONTYPEVAL;
	}
	public String getIKEA_NOTE() {
		return IKEA_NOTE;
	}
	public void setIKEA_NOTE(String iKEANOTE) {
		IKEA_NOTE = iKEANOTE;
	}
	public String getIKEA_NOTE_VAL() {
		return IKEA_NOTE_VAL;
	}
	public void setIKEA_NOTE_VAL(String iKEANOTEVAL) {
		IKEA_NOTE_VAL = iKEANOTEVAL;
	}
	public String getMATERIAL_MODEL() {
		return MATERIAL_MODEL;
	}
	public void setMATERIAL_MODEL(String mATERIALMODEL) {
		MATERIAL_MODEL = mATERIALMODEL;
	}
	public String getPROCESS_NO() {
		return PROCESS_NO;
	}
	public void setPROCESS_NO(String pROCESSNO) {
		PROCESS_NO = pROCESSNO;
	}
	public String getWHOLESALE_PRICE() {
		return WHOLESALE_PRICE;
	}
	public void setWHOLESALE_PRICE(String wHOLESALEPRICE) {
		WHOLESALE_PRICE = wHOLESALEPRICE;
	}
	public String getRETAIL_PRICE() {
		return RETAIL_PRICE;
	}
	public void setRETAIL_PRICE(String rETAILPRICE) {
		RETAIL_PRICE = rETAILPRICE;
	}
	public String getLOWEST_PRICE() {
		return LOWEST_PRICE;
	}
	public void setLOWEST_PRICE(String lOWESTPRICE) {
		LOWEST_PRICE = lOWESTPRICE;
	}
	public String getREFER_NO1() {
		return REFER_NO1;
	}
	public void setREFER_NO1(String rEFERNO1) {
		REFER_NO1 = rEFERNO1;
	}
	public String getREFER_NO2() {
		return REFER_NO2;
	}
	public void setREFER_NO2(String rEFERNO2) {
		REFER_NO2 = rEFERNO2;
	}
	public String getALLOW_RATE() {
		return ALLOW_RATE;
	}
	public void setALLOW_RATE(String aLLOWRATE) {
		ALLOW_RATE = aLLOWRATE;
	}
	public String getISSHEET() {
		return ISSHEET;
	}
	public void setISSHEET(String iSSHEET) {
		ISSHEET = iSSHEET;
	}
	public String getPACKING_REQUIRE() {
		return PACKING_REQUIRE;
	}
	public void setPACKING_REQUIRE(String pACKINGREQUIRE) {
		PACKING_REQUIRE = pACKINGREQUIRE;
	}
	public String getPACKING_REQUIRE_VAL() {
		return PACKING_REQUIRE_VAL;
	}
	public void setPACKING_REQUIRE_VAL(String pACKINGREQUIREVAL) {
		PACKING_REQUIRE_VAL = pACKINGREQUIREVAL;
	}
	public String getEXPERIENCE() {
		return EXPERIENCE;
	}
	public void setEXPERIENCE(String eXPERIENCE) {
		EXPERIENCE = eXPERIENCE;
	}
	public String getEXPERIENCE_VAL() {
		return EXPERIENCE_VAL;
	}
	public void setEXPERIENCE_VAL(String eXPERIENCEVAL) {
		EXPERIENCE_VAL = eXPERIENCEVAL;
	}
	public String getSUPPLIER() {
		return SUPPLIER;
	}
	public void setSUPPLIER(String sUPPLIER) {
		SUPPLIER = sUPPLIER;
	}
	public String getSURFACE_REQUIRE() {
		return SURFACE_REQUIRE;
	}
	public void setSURFACE_REQUIRE(String sURFACEREQUIRE) {
		SURFACE_REQUIRE = sURFACEREQUIRE;
	}
	public String getCOMPOSITOR() {
		return COMPOSITOR;
	}
	public void setCOMPOSITOR(String cOMPOSITOR) {
		COMPOSITOR = cOMPOSITOR;
	}
	public String getPROCESS_REQUIRE() {
		return PROCESS_REQUIRE;
	}
	public void setPROCESS_REQUIRE(String pROCESSREQUIRE) {
		PROCESS_REQUIRE = pROCESSREQUIRE;
	}
	public String getTICKET_REQUIRE() {
		return TICKET_REQUIRE;
	}
	public void setTICKET_REQUIRE(String tICKETREQUIRE) {
		TICKET_REQUIRE = tICKETREQUIRE;
	}
	public String getMATERIAL_SHAPE() {
		return MATERIAL_SHAPE;
	}
	public void setMATERIAL_SHAPE(String mATERIALSHAPE) {
		MATERIAL_SHAPE = mATERIALSHAPE;
	}
	public String getMATERIAL_SHAPE_VAL() {
		return MATERIAL_SHAPE_VAL;
	}
	public void setMATERIAL_SHAPE_VAL(String mATERIALSHAPEVAL) {
		MATERIAL_SHAPE_VAL = mATERIALSHAPEVAL;
	}
	public String getALLOW_PRODUCE() {
		return ALLOW_PRODUCE;
	}
	public void setALLOW_PRODUCE(String aLLOWPRODUCE) {
		ALLOW_PRODUCE = aLLOWPRODUCE;
	}
	public String getALLOW_PRODUCE_VAL() {
		return ALLOW_PRODUCE_VAL;
	}
	public void setALLOW_PRODUCE_VAL(String aLLOWPRODUCEVAL) {
		ALLOW_PRODUCE_VAL = aLLOWPRODUCEVAL;
	}
	public String getPLAN_LAUNCH_DATE() {
		return PLAN_LAUNCH_DATE;
	}
	public void setPLAN_LAUNCH_DATE(String pLANLAUNCHDATE) {
		PLAN_LAUNCH_DATE = pLANLAUNCHDATE;
	}
	public String getLAUNCH_DATE() {
		return LAUNCH_DATE;
	}
	public void setLAUNCH_DATE(String lAUNCHDATE) {
		LAUNCH_DATE = lAUNCHDATE;
	}
	public String getSHUTDOWN_DATE() {
		return SHUTDOWN_DATE;
	}
	public void setSHUTDOWN_DATE(String sHUTDOWNDATE) {
		SHUTDOWN_DATE = sHUTDOWNDATE;
	}
	public String getPHOTO() {
		return PHOTO;
	}
	public void setPHOTO(String pHOTO) {
		PHOTO = pHOTO;
	}
	public String getSTOCK_TYPE_VAL() {
		return STOCK_TYPE_VAL;
	}
	public void setSTOCK_TYPE_VAL(String sTOCKTYPEVAL) {
		STOCK_TYPE_VAL = sTOCKTYPEVAL;
	}
	public String getUNIT_ID_VAL() {
		return UNIT_ID_VAL;
	}
	public void setUNIT_ID_VAL(String uNITIDVAL) {
		UNIT_ID_VAL = uNITIDVAL;
	}
	
	
}
