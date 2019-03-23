/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：XTSQModel.java
 */
package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class XTSQModel.
 * 
 * @module 系统管理
 * @func 系统授权
 * @version 1.1
 * @author 吴亚林
 */
public class XTSQModel extends BaseModel {

    /** 版本号. */
    private static final long serialVersionUID = -4510732462558146523L;

    /** The XTSQID. */
    private String            XTSQID;                                  //ID

    /** The XTYHID. */
    private String            XTYHID;                                  //系统用户ID

    /** The XTMKID. */
    private String            XTMKID;                                  //系统模块ID


    /**
     * Gets the xTSQID.
     * 
     * @return the xTSQID
     */
    public String getXTSQID() {

        return XTSQID;
    }


    /**
     * Sets the xTSQID.
     * 
     * @param xtsqid the new xTSQID
     */
    public void setXTSQID(String xtsqid) {

        XTSQID = xtsqid;
    }


    /**
     * Gets the xTYHID.
     * 
     * @return the xTYHID
     */
    public String getXTYHID() {

        return XTYHID;
    }


    /**
     * Sets the xTYHID.
     * 
     * @param xtyhid the new xTYHID
     */
    public void setXTYHID(String xtyhid) {

        XTYHID = xtyhid;
    }


    /**
     * Gets the xTMKID.
     * 
     * @return the xTMKID
     */
    public String getXTMKID() {

        return XTMKID;
    }


    /**
     * Sets the xTMKID.
     * 
     * @param xtmkid the new xTMKID
     */
    public void setXTMKID(String xtmkid) {

        XTMKID = xtmkid;
    }
}
