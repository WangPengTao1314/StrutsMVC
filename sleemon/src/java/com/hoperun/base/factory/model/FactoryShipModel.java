/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：FactoryShipModel.java
 */
package com.hoperun.base.factory.model;

/**
 * 
 * The Class FactoryShipModel.
 * 
 * @module 系统管理
 * @func 生产工厂维护
 * @version 1.0
 * @author 王志格
 */
public class FactoryShipModel {
		
	/**
	 * 工厂发货点ID
	 */
	private String FACTORY_SHIP_ID;
	/**
	 * 生产基地ID
	 */
	private String FACTORY_ID;
	
	/**
	 * 发货点ID
	 */
	private String SHIP_POINT_ID;
	
	/**
	 * 发货点编号
	 */
	private String SHIP_POINT_NO;
	
	/**
	 * 发货点名称
	 */
	private String SHIP_POINT_NAME;
	
	/**
	 * 删除标记
	 */
	private String DEL_FLAG;

	public String getFACTORY_SHIP_ID() {
		return FACTORY_SHIP_ID;
	}

	public void setFACTORY_SHIP_ID(String fACTORYSHIPID) {
		FACTORY_SHIP_ID = fACTORYSHIPID;
	}

	public String getFACTORY_ID() {
		return FACTORY_ID;
	}

	public void setFACTORY_ID(String fACTORYID) {
		FACTORY_ID = fACTORYID;
	}

	public String getSHIP_POINT_ID() {
		return SHIP_POINT_ID;
	}

	public void setSHIP_POINT_ID(String sHIPPOINTID) {
		SHIP_POINT_ID = sHIPPOINTID;
	}

	public String getSHIP_POINT_NO() {
		return SHIP_POINT_NO;
	}

	public void setSHIP_POINT_NO(String sHIPPOINTNO) {
		SHIP_POINT_NO = sHIPPOINTNO;
	}

	public String getSHIP_POINT_NAME() {
		return SHIP_POINT_NAME;
	}

	public void setSHIP_POINT_NAME(String sHIPPOINTNAME) {
		SHIP_POINT_NAME = sHIPPOINTNAME;
	}

	public String getDEL_FLAG() {
		return DEL_FLAG;
	}

	public void setDEL_FLAG(String dELFLAG) {
		DEL_FLAG = dELFLAG;
	}

}
