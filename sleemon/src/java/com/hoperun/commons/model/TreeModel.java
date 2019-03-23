/**
 *@module 系统模块   
 *@func 系统模块   
 *@version 1.1
 *@author zhuxw      
 */
package com.hoperun.commons.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

// TODO: Auto-generated Javadoc
/**
 * The Class TreeModel.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public class TreeModel extends BaseModel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4763263722677270988L;

	/** The id. */
	private String id = null;

	/** The pid. */
	private String pid = null;

	/** The name. */
	private String name = null;

	/** The url. */
	private String url = null;

	/** The gourl. */
	private String gourl = null;

	/** The open. */
	private Boolean open = false;
	
	/** The icon. */
	private String icon = null;//:"../../css/ztreeStyle/img/diy/2.png"

	/** The def1. */
	private String def1 = null;
	
	/** The is parent. */
	private String isParent = null;
	
	/** The level. */
	@Deprecated
	private int level;

	/** The childs. */
	private List<TreeModel> childs = new ArrayList<TreeModel>();

	/**
	 * Instantiates a new tree model.
	 */
	public TreeModel() {
		super();
	}

	/**
	 * Instantiates a new tree model.
	 * 
	 * @param id the id
	 * @param name the name
	 */
	public TreeModel(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * Gets the pid.
	 * 
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}

	/**
	 * Sets the pid.
	 * 
	 * @param pid the new pid
	 */
	public void setPid(String pid) {

		this.pid = pid;
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
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {

		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name the new name
	 */
	public void setName(String name) {

		this.name = name;
	}

	/**
	 * Gets the open.
	 * 
	 * @return the open
	 */
	public Boolean getOpen() {

		return open;
	}

	/**
	 * Sets the open.
	 * 
	 * @param open the new open
	 */
	public void setOpen(Boolean open) {

		this.open = open;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
       if (object == null)
			return false;
		if (!(object instanceof TreeModel))
			return false;
		TreeModel otherModel = (TreeModel) object;
		return (new EqualsBuilder().append(id, otherModel.getId())).isEquals();
	}

	/**
	 * Gets the url.
	 * 
	 * @return the url
	 */
	public String getUrl() {

		return url;
	}

	/**
	 * Sets the url.
	 * 
	 * @param url the new url
	 */
	public void setUrl(String url) {

		this.url = url;
	}

	/**
	 * Gets the gourl.
	 * 
	 * @return the gourl
	 */
	public String getGourl() {

		return gourl;
	}

	/**
	 * Sets the gourl.
	 * 
	 * @param gourl the new gourl
	 */
	public void setGourl(String gourl) {

		this.gourl = gourl;
	}

	/**
	 * Gets the def1.
	 * 
	 * @return the def1
	 */
	public String getDef1() {

		return def1;
	}

	/**
	 * Sets the def1.
	 * 
	 * @param def1 the new def1
	 */
	public void setDef1(String def1) {

		this.def1 = def1;
	}

	/**
	 * Gets the icon.
	 * 
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}

	/**
	 * Sets the icon.
	 * 
	 * @param icon the new icon
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	

	/**
	 * Checks if is leaf.
	 * 
	 * @return true, if is leaf
	 */
	public boolean isLeaf() {
		return childs.isEmpty();
	}

	/**
	 * Adds the childs.
	 * 
	 * @param model the model
	 */
	public void addChilds(TreeModel model) {
		this.childs.add(model);
	}

	/**
	 * Removes the childs.
	 * 
	 * @param model the model
	 */
	public void removeChilds(TreeModel model) {

		this.childs.remove(model);
	}

	/**
	 * Gets the childs.
	 * 
	 * @return the childs
	 */
	public List<TreeModel> getChilds() {

		return childs;
	}

	/**
	 * Sets the childs.
	 * 
	 * @param childs the new childs
	 */
	public void setChilds(List<TreeModel> childs) {

		this.childs = childs;
	}

	/**
	 * Gets the checks if is parent.
	 * 
	 * @return the checks if is parent
	 */
	public String getIsParent() {
		return isParent;
	}

	/**
	 * Sets the checks if is parent.
	 * 
	 * @param isParent the new checks if is parent
	 */
	public void setIsParent(String isParent) {
		this.isParent = isParent;
	}
	
	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	@Deprecated
	public int getLevel() {
		return level;
	}
	
	/**
	 * Sets the level.
	 * 
	 * @param level the new level
	 */
	@Deprecated
	public void setLevel(int level) {
		this.level = level;
	}
}
