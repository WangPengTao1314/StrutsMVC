/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.select.object;

// TODO: Auto-generated Javadoc
/**
 * The Class InitObject.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class InitObject {
	
	/** The key name. */
	private String keyName;
	
	/** The node name. */
	private String nodeName; //节点字段物理名
	
	/** The parent node name. */
	private String parentNodeName; //上级节点字段物理名
	
	/** The table py name. */
	private String tablePyName; //表物理名
	
	/** The table as name. */
	private String tableAsName; //表别名
	
	/** The field py name. */
	private String[] fieldPyName; //字段物理名
	
	/** The field as name. */
	private String[] fieldAsName; //字段别名
	
	/** The order name. */
	private String orderName; //排序字段
	
	/** The order type. */
	private String orderType; //排序类型

	/** The with sql. */
	private String withSql; //放在查询语句最前面的语句，主要用于with语句

	/** The filter condition. */
	private String filterCondition; //过滤条件
	
	/** The leaf condition. */
	private String leafCondition; //终节点判断条件
	
	/** The start condition. */
	private String startCondition; //根节点条件
	
	/** The check condition. */
	private String checkCondition; //是否允许被选择条件
	
	/** The BMCD. */
	private int BMCD; //单层编码长度
	
	/** The BMMC. */
	private String BMMC;//编码字段名成

	/**
	 * Gets the check condition.
	 * 
	 * @return the check condition
	 */
	public String getCheckCondition() {
		return checkCondition;
	}

	/**
	 * Gets the field as name.
	 * 
	 * @return the field as name
	 */
	public String[] getFieldAsName() {
		return fieldAsName;
	}

	/**
	 * Gets the field py name.
	 * 
	 * @return the field py name
	 */
	public String[] getFieldPyName() {
		return fieldPyName;
	}

	/**
	 * Gets the filter condition.
	 * 
	 * @return the filter condition
	 */
	public String getFilterCondition() {
		return filterCondition;
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
	 * Gets the node name.
	 * 
	 * @return the node name
	 */
	public String getNodeName() {
		return nodeName;
	}

	/**
	 * Gets the order name.
	 * 
	 * @return the order name
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * Gets the order type.
	 * 
	 * @return the order type
	 */
	public String getOrderType() {
		return orderType;
	}

	/**
	 * Gets the parent node name.
	 * 
	 * @return the parent node name
	 */
	public String getParentNodeName() {
		return parentNodeName;
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
	 * Gets the table as name.
	 * 
	 * @return the table as name
	 */
	public String getTableAsName() {
		return tableAsName;
	}

	/**
	 * Gets the table py name.
	 * 
	 * @return the table py name
	 */
	public String getTablePyName() {
		return tablePyName;
	}

	/**
	 * Gets the with sql.
	 * 
	 * @return the with sql
	 */
	public String getWithSql() {
		return withSql;
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
	 * Sets the field as name.
	 * 
	 * @param fieldAsName the new field as name
	 */
	public void setFieldAsName(String[] fieldAsName) {
		this.fieldAsName = fieldAsName;
	}

	/**
	 * Sets the field py name.
	 * 
	 * @param fieldPyName the new field py name
	 */
	public void setFieldPyName(String[] fieldPyName) {
		this.fieldPyName = fieldPyName;
	}

	/**
	 * Sets the filter condition.
	 * 
	 * @param filterCondition the new filter condition
	 */
	public void setFilterCondition(String filterCondition) {
		this.filterCondition = filterCondition;
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
	 * Sets the node name.
	 * 
	 * @param nodeName the new node name
	 */
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	/**
	 * Sets the order name.
	 * 
	 * @param orderName the new order name
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * Sets the order type.
	 * 
	 * @param orderType the new order type
	 */
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	/**
	 * Sets the parent node name.
	 * 
	 * @param parentNodeName the new parent node name
	 */
	public void setParentNodeName(String parentNodeName) {
		this.parentNodeName = parentNodeName;
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
	 * Sets the table as name.
	 * 
	 * @param tableAsName the new table as name
	 */
	public void setTableAsName(String tableAsName) {
		this.tableAsName = tableAsName;
	}

	/**
	 * Sets the table py name.
	 * 
	 * @param tablePyName the new table py name
	 */
	public void setTablePyName(String tablePyName) {
		this.tablePyName = tablePyName;
	}

	/**
	 * Sets the with sql.
	 * 
	 * @param withSql the new with sql
	 */
	public void setWithSql(String withSql) {
		this.withSql = withSql;
	}

	/**
	 * Gets the bMCD.
	 * 
	 * @return the bMCD
	 */
	public int getBMCD() {
		return BMCD;
	}

	/**
	 * Sets the bmcd.
	 * 
	 * @param i the i
	 */
	public void setBMCD(int i) {
		BMCD = i;
	}

	/**
	 * Gets the bMMC.
	 * 
	 * @return the bMMC
	 */
	public String getBMMC() {
		return BMMC;
	}

	/**
	 * Sets the bmmc.
	 * 
	 * @param string the string
	 */
	public void setBMMC(String string) {
		BMMC = string;
	}

	/**
	 * Gets the key name.
	 * 
	 * @return the key name
	 */
	public String getKeyName() {
		return keyName;
	}

	/**
	 * Sets the key name.
	 * 
	 * @param string the new key name
	 */
	public void setKeyName(String string) {
		keyName = string;
	}

}
