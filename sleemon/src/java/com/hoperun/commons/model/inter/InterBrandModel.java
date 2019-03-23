package com.hoperun.commons.model.inter;




/**  
 * @func 品牌
 * @author lyg
 * @version 1.1
 * @createdate 2015-9-1 上午10:21:20 
 */
public class InterBrandModel extends FixedInterModel{
	/**
	 * 品牌编码
	 */
	private String BRAND_CODE;
	/**
	 * 品牌名称
	 */
	private String BRAND_NAME;
	/**
	 * 品牌英文名
	 */
	private String ENGLISH_NAME;
	/**
	 * 市场类别(选项)
	 */
	private String MARKET_TYPE;
	/**
	 * 
	 */
	private String MARKET_TYPE_VAL;
	public String getBRAND_CODE() {
		return BRAND_CODE;
	}
	public void setBRAND_CODE(String bRANDCODE) {
		BRAND_CODE = bRANDCODE;
	}
	public String getBRAND_NAME() {
		return BRAND_NAME;
	}
	public void setBRAND_NAME(String bRANDNAME) {
		BRAND_NAME = bRANDNAME;
	}
	public String getENGLISH_NAME() {
		return ENGLISH_NAME;
	}
	public void setENGLISH_NAME(String eNGLISHNAME) {
		ENGLISH_NAME = eNGLISHNAME;
	}
	public String getMARKET_TYPE() {
		return MARKET_TYPE;
	}
	public void setMARKET_TYPE(String mARKETTYPE) {
		MARKET_TYPE = mARKETTYPE;
	}
	public String getMARKET_TYPE_VAL() {
		return MARKET_TYPE_VAL;
	}
	public void setMARKET_TYPE_VAL(String mARKETTYPEVAL) {
		MARKET_TYPE_VAL = mARKETTYPEVAL;
	}
	
}
