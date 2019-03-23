/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.servlet;

import java.util.Vector;

import com.hoperun.commons.model.BaseTableObject;

// TODO: Auto-generated Javadoc
/**
 * The Class TableObjectDataDic.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class TableObjectDataDic extends BaseTableObject {
	
	/** The is db table. */
	private boolean isDbTable; // 是否是数据库表
	
	/** The values. */
	private Vector values; // 值对象
	
	/** The sub table objects. */
	private TableObjectDataDic[] subTableObjects; // 子表数组

	/** The leaf condition. */
	private String leafCondition; // 终节点判断条件
	
	/** The start condition. */
	private String startCondition; // 根节点条件
	
	/** The check condition. */
	private String checkCondition; // 是否允许被选择条件
	
	/** The display model. */
	private String displayModel; // 树型显示串
	
	/** The is static. */
	private boolean isSTATIC; // 是否静态化数据 只有isDbTable为T是有效
	
	/** The select flag. */
	private boolean selectFlag; // 数据是否从当前表对象中选出
	
	/** The display tree flag. */
	private boolean displayTreeFlag;// 是否默认显示树，仅有树形时有效
	
	/** The text. */
	private String[] text;
    
    /** The value. */
    private String[] value;

	/**
	 * Gets the sub table objects.
	 * 
	 * @return the sub table objects
	 */
	public TableObjectDataDic[] getSubTableObjects() {
		return subTableObjects;
	}

	/**
	 * Sets the sub table objects.
	 * 
	 * @param subTableObjects the new sub table objects
	 */
	public void setSubTableObjects(TableObjectDataDic[] subTableObjects) {
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
	 * Gets the checks if is static.
	 * 
	 * @return the checks if is static
	 */
	public boolean getIsSTATIC() {
		return isSTATIC;
	}

	/**
	 * Sets the checks if is static.
	 * 
	 * @param isSTATIC the new checks if is static
	 */
	public void setIsSTATIC(boolean isSTATIC) {
		this.isSTATIC = isSTATIC;
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
	 * @param b the b
	 */
	public void setDisplayTreeFlag(boolean b) {
		displayTreeFlag = b;
	}

	/**
	 * Gets the checks if is db table.
	 * 
	 * @return the checks if is db table
	 */
	public boolean getIsDbTable() {
		return isDbTable;
	}

	/**
	 * Sets the checks if is db table.
	 * 
	 * @param isDbTable the new checks if is db table
	 */
	public void setIsDbTable(boolean isDbTable) {
		this.isDbTable = isDbTable;
	}

	/**
	 * Gets the sql string.
	 * 
	 * @param serCondition the ser condition
	 * 
	 * @return the sql string
	 */
	public String getSqlString(String serCondition) {
		StringBuffer tempSql = new StringBuffer("");
		String tablePyName = this.getTablePyName();
		String tableAsName = this.getTableAsName();
		if (this.getTableAsName() != null && !"".equals(this.getTableAsName())) {
			tablePyName = tablePyName + " " + tableAsName;
		}

		int fieldCount = this.getFieldPyName().length;
		// 组建选取的列名数组
		for (int i = 0; i < fieldCount; i++) {
			if (this.getFieldAsName()[i] == null
					|| "".equals(this.getFieldAsName()[i])) {
				if ("0".equals(this.getPrimaryKeyId()[i])) {
					tempSql.insert(0, this.getFieldPyName()[i]+",");
				} else {
					tempSql.append(this.getFieldPyName()[i]).append(",");
				}
			} else {
				if ("0".equals(this.getPrimaryKeyId()[i])) {
					tempSql.insert(0, this.getFieldPyName()[i]+" "+this.getFieldAsName()[i]+",");
				}
				else{
				tempSql.append(this.getFieldPyName()[i]).append(" ").append(
						this.getFieldAsName()[i]).append(",");
				}
			}
		}
		tempSql.insert(0,"select ");
		tempSql.delete(tempSql.length()-1, tempSql.length());
		tempSql.append(" from ").append(tablePyName);
		String wCondition=this.getCheckCondition();
		
		if (null!=serCondition && !"".equals(serCondition)){
			wCondition=wCondition + " and " + serCondition;
		}
		if (null!=wCondition && !"".equals(wCondition)){
			tempSql.append(" where ").append(wCondition);
		}
		// 如果没有排序方式
		// 如果配置文件中没有定义排序方式，则按第一个字段排序
			if (this.getOrderName() == null
					|| this.getOrderName().equals("")) {
				if (this.getFieldAsName()[0] == null || "".equals(this.getFieldAsName()[0])) {
					tempSql.append(" order by ").append(this.getFieldPyName()[0]);
				} else {
					tempSql.append(" order by ").append(this.getFieldAsName()[0]);
				}
				tempSql.append(" DESC");
			} else {
				// 否则按配置文件中的定义排序
				tempSql.append(" order by ").append(this.getOrderName());
				tempSql.append(" ").append(this.getOrderType());
			}
		
		return tempSql.toString();
	}

    
    /**
     * Gets the text.
     * 
     * @return the text
     */
    public String[] getText() {
    
        return text;
    }

    
    /**
     * Sets the text.
     * 
     * @param text the new text
     */
    public void setText(String[] text) {
    
        this.text = text;
    }

    
    /**
     * Gets the value.
     * 
     * @return the value
     */
    public String[] getValue() {
    
        return value;
    }

    
    /**
     * Sets the value.
     * 
     * @param value the new value
     */
    public void setValue(String[] value) {
    
        this.value = value;
    }
}
