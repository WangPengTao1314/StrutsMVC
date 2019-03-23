/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.select.object;

import java.util.Vector;

import com.hoperun.commons.model.BaseTableObject;

// TODO: Auto-generated Javadoc
/**
 * The Class TableObject.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class TableObject extends BaseTableObject {

	/** The is show search layer. */
	private boolean isShowSearchLayer; //默认状态是否显示搜索层
	
	/** The values. */
	private Vector values; //值对象
	
	/** The sub table objects. */
	private TableObject[] subTableObjects; //子表数组
    
    /** The leaf condition. */
    private String leafCondition; //终节点判断条件
	
	/** The start condition. */
	private String startCondition; //根节点条件
	
	/** The check condition. */
	private String checkCondition; //是否允许被选择条件
	
	/** The display model. */
	private String displayModel; //树型显示串
	
	/** The is wlxx. */
	private boolean isWLXX; //是否是物料信息
	
	/** The select flag. */
	private boolean selectFlag; //数据是否从当前表对象中选出
	
	/** The display tree flag. */
	private boolean displayTreeFlag;//是否默认显示树，仅有树形时有效
	
	/** The is wldwxx. */
	private boolean isWLDWXX;//是否往来单位选取

	/**
	 * Gets the sub table objects.
	 * 
	 * @return the sub table objects
	 */
	public TableObject[] getSubTableObjects() {
		return subTableObjects;
	}

	/**
	 * Sets the sub table objects.
	 * 
	 * @param subTableObjects the new sub table objects
	 */
	public void setSubTableObjects(TableObject[] subTableObjects) {
		this.subTableObjects = subTableObjects;
	}

	/**
	 * Gets the values.
	 * 
	 * @return the values
	 */
	public Vector getValues() {
		return values;
	}

	/**
	 * Sets the values.
	 * 
	 * @param values the new values
	 */
	public void setValues(Vector values) {
		this.values = values;
	}

	/**
	 * Gets the checks if is show search layer.
	 * 
	 * @return the checks if is show search layer
	 */
	public boolean getIsShowSearchLayer() {
		return isShowSearchLayer;
	}

	/**
	 * Sets the checks if is show search layer.
	 * 
	 * @param isShowSearchLayer the new checks if is show search layer
	 */
	public void setIsShowSearchLayer(boolean isShowSearchLayer) {
		this.isShowSearchLayer = isShowSearchLayer;
	}

	/**
	 * Gets the check condition.
	 * 
	 * @return the check condition
	 */
	public String getCheckCondition() {
		return checkCondition;
	}

	/**
	 * Sets the check condition.
	 * 
	 * @param checkCondition the new check condition
	 */
	public void setCheckCondition(String checkCondition) {
		this.checkCondition = checkCondition;
	}

	/**
	 * Gets the display model.
	 * 
	 * @return the display model
	 */
	public String getDisplayModel() {
		return displayModel;
	}

	/**
	 * Sets the display model.
	 * 
	 * @param displayModel the new display model
	 */
	public void setDisplayModel(String displayModel) {
		this.displayModel = displayModel;
	}

	/**
	 * Gets the leaf condition.
	 * 
	 * @return the leaf condition
	 */
	public String getLeafCondition() {
		return leafCondition;
	}

	/**
	 * Sets the leaf condition.
	 * 
	 * @param leafCondition the new leaf condition
	 */
	public void setLeafCondition(String leafCondition) {
		this.leafCondition = leafCondition;
	}

	/**
	 * Gets the start condition.
	 * 
	 * @return the start condition
	 */
	public String getStartCondition() {
		return startCondition;
	}

	/**
	 * Sets the start condition.
	 * 
	 * @param startCondition the new start condition
	 */
	public void setStartCondition(String startCondition) {
		this.startCondition = startCondition;
	}

	/**
	 * Gets the checks if is wlxx.
	 * 
	 * @return the checks if is wlxx
	 */
	public boolean getIsWLXX() {
		return isWLXX;
	}

	/**
	 * Sets the checks if is wlxx.
	 * 
	 * @param isWLXX the new checks if is wlxx
	 */
	public void setIsWLXX(boolean isWLXX) {
		this.isWLXX = isWLXX;
	}
	
	/**
	 * Sets the select flag.
	 * 
	 * @param b the new select flag
	 */
	public void setSelectFlag(boolean b) {
		selectFlag = b;
	}
	
	/**
	 * Gets the select flag.
	 * 
	 * @return the select flag
	 */
	public boolean getSelectFlag() {
		return selectFlag;
	}

	/**
	 * Gets the display tree flag.
	 * 
	 * @return the display tree flag
	 */
	public boolean getDisplayTreeFlag() {
		return displayTreeFlag;
	}

	/**
	 * Sets the display tree flag.
	 * 
	 * @param b the new display tree flag
	 */
	public void setDisplayTreeFlag(boolean b) {
		displayTreeFlag = b;
	}

	/**
	 * Gets the checks if is wldwxx.
	 * 
	 * @return the checks if is wldwxx
	 */
	public boolean getIsWLDWXX() {
		return isWLDWXX;
	}

	/**
	 * Sets the checks if is wldwxx.
	 * 
	 * @param b the new checks if is wldwxx
	 */
	public void setIsWLDWXX(boolean b) {
		isWLDWXX = b;
	}

}
