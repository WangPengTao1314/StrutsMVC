/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

// TODO: Auto-generated Javadoc
/**
 * The Class BaseTableObject.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
/**
 * <p>Title: 通用表对象</p>
 * <p>Description: 这个对象定义了常用的表对象中的一些属性</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: njbutone</p>
 * @dybine
 * @version 1.1
 * date 2004-03-02
 */
public class BaseTableObject {
	//通用表属性
	/** The id. */
	private String id; //序号
	
	/** The table py name. */
	private String tablePyName; //表物理名
	
	/** The table as name. */
	private String tableAsName; //表别名
	
	/** The table lg name. */
	private String tableLgName; //表逻辑名
	
	/** The order name. */
	private String orderName; //排序字段名
	
	/** The order type. */
	private String orderType; //排序方式

	//通用字段属性
	/** The field py name. */
	private String[] fieldPyName; //字段物理名
	
	/** The field as name. */
	private String[] fieldAsName; //字段别名
	
	/** The field lg name. */
	private String[] fieldLgName; //字段逻辑名
	
	/** The field type. */
	private String[] fieldType; //字段数据类型
	
	/** The decimal system. */
	private String[] decimalSystem; //系统，和数据类型一起使用，用于格式化小数
	
	/** The decimal type. */
	private String[] decimalType; //数据类型，和系统一起使用，用于格式化小数
	
	/** The decimal max. */
	private String[] decimalMax; //小数位数
	
	/** The primary key id. */
	private String[] primaryKeyId; //主关键字序号
	
	/** The foreign key id. */
	private String[] foreignKeyId; //外关键字序号
	
	/** The is search. */
	private boolean[] isSearch; //是否能够搜索
	
	/** The is show. */
	private boolean[] isShow; //是否显示

	//通用树形属性
	/** The is parent node. */
	private boolean[] isParentNode; //是否是父结点
	
	/** The is node. */
	private boolean[] isNode; //是否是结点
	
	/** The is tree key. */
	private boolean[] isTreeKey;//树型节点关键字
	
	/** The is tree. */
	private boolean isTree; //是否需要树型显示
	
	//明细字段作为主表查询条件属性
	/** The is child con. */
	private boolean[] isChildCon; //是否是孩子的查询条件
	
	/** The child tab id. */
	private String[] childTabId;  //是否是孩子的查询条件对应的表
	

	/**
	 * Gets the child tab id.
	 * 
	 * @return the child tab id
	 */
	public String[] getChildTabId() {
		return childTabId;
	}

	/**
	 * Sets the child tab id.
	 * 
	 * @param childTabId the new child tab id
	 */
	public void setChildTabId(String[] childTabId) {
		this.childTabId = childTabId;
	}

	/**
	 * Gets the decimal max.
	 * 
	 * @return the decimal max
	 */
	public String[] getDecimalMax() {
		return decimalMax;
	}

	/**
	 * Sets the decimal max.
	 * 
	 * @param decimalMax the new decimal max
	 */
	public void setDecimalMax(String[] decimalMax) {
		this.decimalMax = decimalMax;
	}

	/**
	 * Gets the decimal system.
	 * 
	 * @return the decimal system
	 */
	public String[] getDecimalSystem() {
		return decimalSystem;
	}

	/**
	 * Gets the decimal type.
	 * 
	 * @return the decimal type
	 */
	public String[] getDecimalType() {
		return decimalType;
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
	 * Gets the field lg name.
	 * 
	 * @return the field lg name
	 */
	public String[] getFieldLgName() {
		return fieldLgName;
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
	 * Gets the field type.
	 * 
	 * @return the field type
	 */
	public String[] getFieldType() {
		return fieldType;
	}

	/**
	 * Gets the checks if is node.
	 * 
	 * @return the checks if is node
	 */
	public boolean[] getIsNode() {
		return isNode;
	}

	/**
	 * Gets the checks if is parent node.
	 * 
	 * @return the checks if is parent node
	 */
	public boolean[] getIsParentNode() {
		return isParentNode;
	}

	/**
	 * Gets the checks if is search.
	 * 
	 * @return the checks if is search
	 */
	public boolean[] getIsSearch() {
		return isSearch;
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
	 * Gets the checks if is show.
	 * 
	 * @return the checks if is show
	 */
	public boolean[] getIsShow() {
		return isShow;
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
	 * Gets the table as name.
	 * 
	 * @return the table as name
	 */
	public String getTableAsName() {
		return tableAsName;
	}

	/**
	 * Gets the table lg name.
	 * 
	 * @return the table lg name
	 */
	public String getTableLgName() {
		return tableLgName;
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
	 * Sets the decimal system.
	 * 
	 * @param decimalSystem the new decimal system
	 */
	public void setDecimalSystem(String[] decimalSystem) {
		this.decimalSystem = decimalSystem;
	}

	/**
	 * Sets the decimal type.
	 * 
	 * @param decimalType the new decimal type
	 */
	public void setDecimalType(String[] decimalType) {
		this.decimalType = decimalType;
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
	 * Sets the field lg name.
	 * 
	 * @param fieldLgName the new field lg name
	 */
	public void setFieldLgName(String[] fieldLgName) {
		this.fieldLgName = fieldLgName;
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
	 * Sets the field type.
	 * 
	 * @param fieldType the new field type
	 */
	public void setFieldType(String[] fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * Sets the checks if is node.
	 * 
	 * @param isNode the new checks if is node
	 */
	public void setIsNode(boolean[] isNode) {
		this.isNode = isNode;
	}

	/**
	 * Sets the checks if is parent node.
	 * 
	 * @param isParentNode the new checks if is parent node
	 */
	public void setIsParentNode(boolean[] isParentNode) {
		this.isParentNode = isParentNode;
	}

	/**
	 * Sets the checks if is search.
	 * 
	 * @param isSearch the new checks if is search
	 */
	public void setIsSearch(boolean[] isSearch) {
		this.isSearch = isSearch;
	}

	/**
	 * Sets the checks if is show.
	 * 
	 * @param isShow the new checks if is show
	 */
	public void setIsShow(boolean[] isShow) {
		this.isShow = isShow;
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
	 * Sets the table as name.
	 * 
	 * @param tableAsName the new table as name
	 */
	public void setTableAsName(String tableAsName) {
		this.tableAsName = tableAsName;
	}

	/**
	 * Sets the table lg name.
	 * 
	 * @param tableLgName the new table lg name
	 */
	public void setTableLgName(String tableLgName) {
		this.tableLgName = tableLgName;
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
	 * Gets the foreign key id.
	 * 
	 * @return the foreign key id
	 */
	public String[] getForeignKeyId() {
		return foreignKeyId;
	}

	/**
	 * Sets the foreign key id.
	 * 
	 * @param foreignKeyId the new foreign key id
	 */
	public void setForeignKeyId(String[] foreignKeyId) {
		this.foreignKeyId = foreignKeyId;
	}

	/**
	 * Gets the primary key id.
	 * 
	 * @return the primary key id
	 */
	public String[] getPrimaryKeyId() {
		return primaryKeyId;
	}

	/**
	 * Sets the primary key id.
	 * 
	 * @param primaryKeyId the new primary key id
	 */
	public void setPrimaryKeyId(String[] primaryKeyId) {
		this.primaryKeyId = primaryKeyId;
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 * 
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the checks if is tree.
	 * 
	 * @return the checks if is tree
	 */
	public boolean getIsTree() {
		return isTree;
	}

	/**
	 * Sets the checks if is tree.
	 * 
	 * @param isTree the new checks if is tree
	 */
	public void setIsTree(boolean isTree) {
		this.isTree = isTree;
	}

	/**
	 * Gets the is tree key.
	 * 
	 * @return the checks if is tree key
	 * 
	 * @method getIsKey  //方法名
	 * @author dybine   //作者
	 * @create time 2004-11-17 16:06:42   //创建时间
	 */
	public boolean[] getIsTreeKey() {
		return isTreeKey;
	}

	/**
	 * Sets the is tree key.
	 * 
	 * @param bs the bs
	 * 
	 * @method setIsKey  //方法名
	 * @author dybine   //作者
	 * @create time 2004-11-17 16:06:42   //创建时间
	 */
	public void setIsTreeKey(boolean[] bs) {
		isTreeKey = bs;
	}

	/**
	 * Gets the checks if is child con.
	 * 
	 * @return the checks if is child con
	 */
	public boolean[] getIsChildCon() {
		return isChildCon;
	}

	/**
	 * Sets the checks if is child con.
	 * 
	 * @param isChildCon the new checks if is child con
	 */
	public void setIsChildCon(boolean[] isChildCon) {
		this.isChildCon = isChildCon;
	}

	/**
	 * Sets the tree.
	 * 
	 * @param isTree the new tree
	 */
	public void setTree(boolean isTree) {
		this.isTree = isTree;
	}

}
