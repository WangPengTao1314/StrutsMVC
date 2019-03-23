/**
 * 项目名称：平台
 * 系统名：系统管理
 * 文件名：CustomQueryBean.java
 */
package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomQueryDtlBean.
 * 
 * @module 系统管理
 * @func 自定义报表管理
 * @version 1.1
 * @author zhuxw
 */
public class CustomQueryDtlBean extends BaseModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** 主键. */
	private String rptColPk = "";
	
	/** The rpt pk. */
	private String rptPk = "";
	
	/** PHYSICNAME   物理名称. */
	private String physicName = "";
	
	/** LOGICNAME 逻辑名称. */
	private String logicName = "";
	
	/** COL_TYPE字段类型. */
	private String colType = "";
	
	/** ISCONDITION 是否查询条件. */
	private String isCondition = "";
	
	/** MUSTINPUT 是否必输. */
	private String mustInput = "";
	
	/** MATCH_MODEL 匹配模式. */
	private String matchModel = "";
	
	/** COMPONENTTYPE 组件类型. */
	private String compoentType = "";
	
	/** COMPONENTCONDITION 组件条件. */
	private String componentCondition = "";
	
	/** HEADDISPLAY 列表显示. */
	private String headDisplay = "";
	
	/** 表头显示. */
	private String display = "";
	
	/** COL_INDEX  排序号. */
	private String colIndex = "";

	/**
	 * Gets the rpt col pk.
	 * 
	 * @return the rpt col pk
	 */
	public String getRptColPk() {
		return rptColPk;
	}

	/**
	 * Sets the rpt col pk.
	 * 
	 * @param rptColPk the new rpt col pk
	 */
	public void setRptColPk(String rptColPk) {
		this.rptColPk = rptColPk;
	}

	/**
	 * Gets the physic name.
	 * 
	 * @return the physic name
	 */
	public String getPhysicName() {
		return physicName;
	}

	/**
	 * Sets the physic name.
	 * 
	 * @param physicName the new physic name
	 */
	public void setPhysicName(String physicName) {
		this.physicName = physicName;
	}

	/**
	 * Gets the logic name.
	 * 
	 * @return the logic name
	 */
	public String getLogicName() {
		return logicName;
	}

	/**
	 * Sets the logic name.
	 * 
	 * @param logicName the new logic name
	 */
	public void setLogicName(String logicName) {
		this.logicName = logicName;
	}

	/**
	 * Gets the col type.
	 * 
	 * @return the col type
	 */
	public String getColType() {
		return colType;
	}

	/**
	 * Sets the col type.
	 * 
	 * @param colType the new col type
	 */
	public void setColType(String colType) {
		this.colType = colType;
	}

	/**
	 * Gets the checks if is condition.
	 * 
	 * @return the checks if is condition
	 */
	public String getIsCondition() {
		return isCondition;
	}

	/**
	 * Sets the checks if is condition.
	 * 
	 * @param isCondition the new checks if is condition
	 */
	public void setIsCondition(String isCondition) {
		this.isCondition = isCondition;
	}

	/**
	 * Gets the must input.
	 * 
	 * @return the must input
	 */
	public String getMustInput() {
		return mustInput;
	}

	/**
	 * Sets the must input.
	 * 
	 * @param mustInput the new must input
	 */
	public void setMustInput(String mustInput) {
		this.mustInput = mustInput;
	}

	/**
	 * Gets the match model.
	 * 
	 * @return the match model
	 */
	public String getMatchModel() {
		return matchModel;
	}

	/**
	 * Sets the match model.
	 * 
	 * @param matchModel the new match model
	 */
	public void setMatchModel(String matchModel) {
		this.matchModel = matchModel;
	}

	/**
	 * Gets the compoent type.
	 * 
	 * @return the compoent type
	 */
	public String getCompoentType() {
		return compoentType;
	}

	/**
	 * Sets the compoent type.
	 * 
	 * @param compoentType the new compoent type
	 */
	public void setCompoentType(String compoentType) {
		this.compoentType = compoentType;
	}

	/**
	 * Gets the component condition.
	 * 
	 * @return the component condition
	 */
	public String getComponentCondition() {
		return componentCondition;
	}

	/**
	 * Sets the component condition.
	 * 
	 * @param componentCondition the new component condition
	 */
	public void setComponentCondition(String componentCondition) {
		this.componentCondition = componentCondition;
	}

	/**
	 * Gets the head display.
	 * 
	 * @return the head display
	 */
	public String getHeadDisplay() {
		return headDisplay;
	}

	/**
	 * Sets the head display.
	 * 
	 * @param headDisplay the new head display
	 */
	public void setHeadDisplay(String headDisplay) {
		this.headDisplay = headDisplay;
	}

	/**
	 * Gets the col index.
	 * 
	 * @return the col index
	 */
	public String getColIndex() {
		return colIndex;
	}

	/**
	 * Sets the col index.
	 * 
	 * @param colIndex the new col index
	 */
	public void setColIndex(String colIndex) {
		this.colIndex = colIndex;
	}

	/**
	 * Gets the rpt pk.
	 * 
	 * @return the rpt pk
	 */
	public String getRptPk() {
		return rptPk;
	}

	/**
	 * Sets the rpt pk.
	 * 
	 * @param rptPk the new rpt pk
	 */
	public void setRptPk(String rptPk) {
		this.rptPk = rptPk;
	}

	/**
	 * Gets the display.
	 * 
	 * @return the display
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * Sets the display.
	 * 
	 * @param display the new display
	 */
	public void setDisplay(String display) {
		this.display = display;
	}
	
}
