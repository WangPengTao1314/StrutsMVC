/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

import java.util.Hashtable;

import com.hoperun.commons.util.StringUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class DataObject.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class DataObject {
	
	/** The key value. */
	private String keyValue;
	
	/** The node. */
	private String node; // 节点
	
	/** The parent node. */
	private String parentNode; // 上级节点
	
	/** The display str. */
	private String displayStr; // 显示的字符串(可包含html语法)
	
	/** The is leaf. */
	private boolean isLeaf; // 是否最终节点
	
	/** The level. */
	private int level; // 层次号
	
	/** The children num. */
	private int childrenNum; // 子节点数
	
	/** The all name. */
	private String[] allName; // 所有字段名
	
	/** The all value. */
	private Hashtable allValue; // 所有字段值
	
	/** The select flag. */
	private boolean selectFlag; // 是否允许被选择

	/**
	 * Returns the displayStr.
	 * 
	 * @return String
	 */
	public String getDisplayStr() {
		String tmpstr = displayStr;
		for (int j = 0; j < allName.length; j++) {
			tmpstr = StringUtil.replace(tmpstr, "#" + allName[j] + "#",
					(String) allValue.get(allName[j]));
		}
		return tmpstr;
	}

	/**
	 * Returns the displayStr.
	 * 
	 * @return String
	 */
	public String getDisplayStrOld() {
		return displayStr;
	}

	/**
	 * Returns the isLeaf.
	 * 
	 * @return boolean
	 */
	public boolean getIsLeaf() {
		return isLeaf;
	}

	/**
	 * Sets the displayStr.
	 * 
	 * @param displayStr The displayStr to set
	 */
	public void setDisplayStr(String displayStr) {
		this.displayStr = displayStr;
	}

	/**
	 * Sets the isLeaf.
	 * 
	 * @param isLeaf The isLeaf to set
	 */
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}

	/**
	 * Returns the jd.
	 * 
	 * @return String
	 */
	public String getNode() {
		return node;
	}

	/**
	 * Returns the level.
	 * 
	 * @return int
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Returns the sjjd.
	 * 
	 * @return String
	 */
	public String getParentNode() {
		return parentNode;
	}

	/**
	 * Sets the jd.
	 * 
	 * @param node the node
	 */
	public void setNode(String node) {
		this.node = node;
	}

	/**
	 * Sets the level.
	 * 
	 * @param level The level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Sets the sjjd.
	 * 
	 * @param parentNode the parent node
	 */
	public void setParentNode(String parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * Returns the childrenNum.
	 * 
	 * @return int
	 */
	public int getChildrenNum() {
		return childrenNum;
	}

	/**
	 * Sets the childrenNum.
	 * 
	 * @param childrenNum The childrenNum to set
	 */
	public void setChildrenNum(int childrenNum) {
		this.childrenNum = childrenNum;
	}

	/**
	 * Returns the allName.
	 * 
	 * @return String[]
	 */
	public String[] getAllName() {
		return allName;
	}

	/**
	 * Returns the allValue.
	 * 
	 * @return Hashtable
	 */
	public Hashtable getAllValue() {
		return allValue;
	}

	/**
	 * Sets the allName.
	 * 
	 * @param allName The allName to set
	 */
	public void setAllName(String[] allName) {
		this.allName = allName;
	}

	/**
	 * Sets the allValue.
	 * 
	 * @param allValue The allValue to set
	 */
	public void setAllValue(Hashtable allValue) {
		this.allValue = allValue;
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
	 * Sets the select flag.
	 * 
	 * @param b the b
	 */
	public void setSelectFlag(boolean b) {
		selectFlag = b;
	}

	/**
	 * Gets the key value.
	 * 
	 * @return the key value
	 * 
	 * @method getKeyValue //方法名
	 * @author dybine //作者
	 * @create time 2004-11-17 14:47:59 //创建时间
	 */
	public String getKeyValue() {
		return keyValue;
	}

	/**
	 * Sets the key value.
	 * 
	 * @param string the string
	 * 
	 * @method setKeyValue //方法名
	 * @author dybine //作者
	 * @create time 2004-11-17 14:48:00 //创建时间
	 */
	public void setKeyValue(String string) {
		keyValue = string;
	}

}
