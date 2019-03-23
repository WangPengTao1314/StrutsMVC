/**
 * 项目名称：平台
 * 系统名：系统管理
 * 文件名：CustomQueryBean.java
 */
package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomQueryBean.
 * 
 * @module 系统管理
 * @func 自定义报表管理
 * @version 1.1
 * @author zhuxw
 */
public class CustomQueryBean extends BaseModel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** 报表编号. */
    private String rptCode; 
    
    /** 用户权限名称. */
    private String rptCodeName;
    
    /** 工作组编码. */
    private String workGroupCode;
    
    /** 工作组名称. */
    private String workGroupName;
    
    /** 角色编码. */
    private String roleCode;
    
    /** 角色名称. */
    private String roleName;
    
    /** 用户编号. */
    private String roleUser;
    
    /** 用户名称. */
    private String roleUserName;
    
    /** 报表名称. */
    private String rptName; 
    
    /** 报表SQL. */
    private String rptSql;
    
    /** 是否设置权限. */
    private String isRole;
    
    /** 说明. */
    private String remark;

	/**
	 * Gets the rpt code.
	 * 
	 * @return the rpt code
	 */
	public String getRptCode() {
		return rptCode;
	}

	/**
	 * Sets the rpt code.
	 * 
	 * @param rptCode the new rpt code
	 */
	public void setRptCode(String rptCode) {
		this.rptCode = rptCode;
	}

	/**
	 * Gets the rpt name.
	 * 
	 * @return the rpt name
	 */
	public String getRptName() {
		return rptName;
	}

	/**
	 * Sets the rpt name.
	 * 
	 * @param rptName the new rpt name
	 */
	public void setRptName(String rptName) {
		this.rptName = rptName;
	}  

	/**
	 * Gets the rpt sql.
	 * 
	 * @return the rpt sql
	 */
	public String getRptSql() {
		return rptSql;
	}

	/**
	 * Sets the rpt sql.
	 * 
	 * @param rptSql the new rpt sql
	 */
	public void setRptSql(String rptSql) {
		this.rptSql = rptSql;
	}

	/**
	 * Gets the checks if is role.
	 * 
	 * @return the checks if is role
	 */
	public String getIsRole() {
		return isRole;
	}

	/**
	 * Sets the checks if is role.
	 * 
	 * @param isRole the new checks if is role
	 */
	public void setIsRole(String isRole) {
		this.isRole = isRole;
	}

	/**
	 * Gets the remark.
	 * 
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * Sets the remark.
	 * 
	 * @param remark the new remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * Gets the rpt code name.
	 * 
	 * @return the rpt code name
	 */
	public String getRptCodeName() {
		return rptCodeName;
	}

	/**
	 * Sets the rpt code name.
	 * 
	 * @param rptCodeName the new rpt code name
	 */
	public void setRptCodeName(String rptCodeName) {
		this.rptCodeName = rptCodeName;
	}

	/**
	 * Gets the work group code.
	 * 
	 * @return the work group code
	 */
	public String getWorkGroupCode() {
		return workGroupCode;
	}

	/**
	 * Sets the work group code.
	 * 
	 * @param workGroupCode the new work group code
	 */
	public void setWorkGroupCode(String workGroupCode) {
		this.workGroupCode = workGroupCode;
	}

	/**
	 * Gets the work group name.
	 * 
	 * @return the work group name
	 */
	public String getWorkGroupName() {
		return workGroupName;
	}

	/**
	 * Sets the work group name.
	 * 
	 * @param workGroupName the new work group name
	 */
	public void setWorkGroupName(String workGroupName) {
		this.workGroupName = workGroupName;
	}

	/**
	 * Gets the role code.
	 * 
	 * @return the role code
	 */
	public String getRoleCode() {
		return roleCode;
	}

	/**
	 * Sets the role code.
	 * 
	 * @param roleCode the new role code
	 */
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	/**
	 * Gets the role name.
	 * 
	 * @return the role name
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the role name.
	 * 
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets the role user.
	 * 
	 * @return the role user
	 */
	public String getRoleUser() {
		return roleUser;
	}

	/**
	 * Sets the role user.
	 * 
	 * @param roleUser the new role user
	 */
	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}

	/**
	 * Gets the role user name.
	 * 
	 * @return the role user name
	 */
	public String getRoleUserName() {
		return roleUserName;
	}

	/**
	 * Sets the role user name.
	 * 
	 * @param roleUserName the new role user name
	 */
	public void setRoleUserName(String roleUserName) {
		this.roleUserName = roleUserName;
	}

}
