/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：FactoryProductModel.java
 */
package com.hoperun.base.factory.model;

/**
 * The Class FactoryProductModel.
 * 
 * @module 系统管理
 * @func 生产工厂维护
 * @version 1.0
 * @author 王志格
 */
public class FactoryProductModel {
	
	/**
	 * 生产货品类型ID
	 */
	private String FACTORY_PRD_ID;
	
	/**
	 * 生产工厂ID
	 */
	private String FACTORY_ID;
	
	/**
	 * 货品ID
	 */
	private String PRD_ID;
	
	/**
	 * 货品编号
	 */
	private String PRD_NO;
	
	/**
	 * 货品名称
	 */
	private String PRD_NAME;
	
	/**
	 * 品牌
	 */
	private String BRAND;
	
	/**
	 * 删除标记
	 */
	private int DEL_FLAG;

	public String getFACTORY_PRD_ID() {
		return FACTORY_PRD_ID;
	}

	public void setFACTORY_PRD_ID(String fACTORYPID) {
		FACTORY_PRD_ID = fACTORYPID;
	}

	public String getFACTORY_ID() {
		return FACTORY_ID;
	}

	public void setFACTORY_ID(String fACTORYID) {
		FACTORY_ID = fACTORYID;
	}

	public String getPRD_ID() {
		return PRD_ID;
	}

	public void setPRD_ID(String pRDID) {
		PRD_ID = pRDID;
	}

	public String getPRD_NO() {
		return PRD_NO;
	}

	public void setPRD_NO(String pRDNO) {
		PRD_NO = pRDNO;
	}

	public String getPRD_NAME() {
		return PRD_NAME;
	}

	public void setPRD_NAME(String pRDNAME) {
		PRD_NAME = pRDNAME;
	}

	public String getBRAND() {
		return BRAND;
	}

	public void setBRAND(String bRAND) {
		BRAND = bRAND;
	}

	public int getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(int dELFLAG) {
		DEL_FLAG = dELFLAG;
	}


}
