/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：RYXXTree.java
 */
package com.hoperun.sys.model;

import com.hoperun.commons.model.TreeModel;

/**
 * The Class RYXXTree.
 * 
 * @module 系统管理
 * @func 人员信息
 * @version 1.1
 * @author 吴亚林
 */
public class RYXXTree extends TreeModel {
	
	/** 部门机构标识. */
    private String flag;


    /**
     * Gets the flag.
     * 
     * @return the flag
     */
    public String getFlag() {

        return flag;
    }


    /**
     * Sets the flag.
     * 
     * @param flag the new flag
     */
    public void setFlag(String flag) {

        this.flag = flag;
    }

}
