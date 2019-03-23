package com.hoperun.sys.model;

import java.util.List;
// TODO: Auto-generated Javadoc

/**
 * The Class MenuInfo.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class MenuInfo {

	// Fields
	/** The menu id. */
	private String menuId;
	
	/** The menu name. */
	private String menuName;
	
	/** The menu par id. */
	private String menuParId;
	
	/** The menu sort. */
	private Integer menuSort;
	
	/** The menu url. */
	private String menuUrl;
	
	/** The menu img. */
	private String menuImg;
	
	/** The menu desc. */
	private String menuDesc;
	// 子菜单
	/** The children. */
	private List children;

	// Constructors

	/**
	 * default constructor.
	 */
	public MenuInfo() {
	}

	// Property accessors

	/**
	 * Gets the children.
	 * 
	 * @return the children
	 */
	public List getChildren() {
		return children;
	}

	/**
	 * Sets the children.
	 * 
	 * @param children the new children
	 */
	public void setChildren(List children) {
		this.children = children;
	}

	/**
	 * Gets the menu id.
	 * 
	 * @return the menu id
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * Sets the menu id.
	 * 
	 * @param menuId the new menu id
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * Gets the menu name.
	 * 
	 * @return the menu name
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * Sets the menu name.
	 * 
	 * @param menuName the new menu name
	 */
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	/**
	 * Gets the menu par id.
	 * 
	 * @return the menu par id
	 */
	public String getMenuParId() {
		return menuParId;
	}

	/**
	 * Sets the menu par id.
	 * 
	 * @param menuParId the new menu par id
	 */
	public void setMenuParId(String menuParId) {
		this.menuParId = menuParId;
	}

	/**
	 * Gets the menu sort.
	 * 
	 * @return the menu sort
	 */
	public Integer getMenuSort() {
		return menuSort;
	}

	/**
	 * Sets the menu sort.
	 * 
	 * @param menuSort the new menu sort
	 */
	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	/**
	 * Gets the menu url.
	 * 
	 * @return the menu url
	 */
	public String getMenuUrl() {
		return menuUrl;
	}

	/**
	 * Sets the menu url.
	 * 
	 * @param menuUrl the new menu url
	 */
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	/**
	 * Gets the menu img.
	 * 
	 * @return the menu img
	 */
	public String getMenuImg() {
		return menuImg;
	}

	/**
	 * Sets the menu img.
	 * 
	 * @param menuImg the new menu img
	 */
	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	/**
	 * Gets the menu desc.
	 * 
	 * @return the menu desc
	 */
	public String getMenuDesc() {
		return menuDesc;
	}

	/**
	 * Sets the menu desc.
	 * 
	 * @param menuDesc the new menu desc
	 */
	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

}