/**
 * 项目名称：平台
 * 系统名：权限管理
 * 文件名：GZZCYBean.java
 */
package com.hoperun.sys.model;

// TODO: Auto-generated Javadoc
/**
 * The Class GZZCYBean.
 * 
 * @module 系统管理
 * @func 工作组信息
 * @version 1.1
 * @author 吴亚林
 */
public class GZZCYBean {

    /** The CZZCYID. */
    private String CZZCYID; //工作组成员ID

    /** The GZZXXID. */
    private String GZZXXID; //工作组信息ID

    /** The GZZBH. */
    private String GZZBH;   //工作组编号

    /** The GZZMC. */
    private String GZZMC;   //工作组名称

    /** The GZZSM. */
    private String GZZSM;   //工作组说明

    /** The GZZZT. */
    private String GZZZT;   //工作组状态

    /** The YHM. */
    private String YHM;     //用户名

    /** The XTYHID. */
    private String XTYHID;  //系统用户ID

    /** The GZZCYID. */
    private String GZZCYID; //工作组成员ID
    
    /** The JGXXID. */
    private String JGXXID; //机构信息ID
    
    /** The JGMC. */
    private String JGMC; //机构名称
    
    /** The BMXXID. */
    private String BMXXID; //部门信息ID
    
    /** The BMMC. */
    private String BMMC; //部门名称
    private String xm;
    
    public String getXm() {
		return xm;
	}


	public void setXm(String xm) {
		this.xm = xm;
	}


	/**
     * Gets the gZZCYID.
     * 
     * @return the gZZCYID
     */
    public String getGZZCYID() {

        return GZZCYID;
    }


    /**
     * Sets the gZZCYID.
     * 
     * @param gzzcyid the new gZZCYID
     */
    public void setGZZCYID(String gzzcyid) {

        GZZCYID = gzzcyid;
    }


    /**
     * Gets the gZZXXID.
     * 
     * @return the gZZXXID
     */
    public String getGZZXXID() {

        return GZZXXID;
    }


    /**
     * Sets the gZZXXID.
     * 
     * @param gzzxxid the new gZZXXID
     */
    public void setGZZXXID(String gzzxxid) {

        GZZXXID = gzzxxid;
    }


    /**
     * Gets the gZZBH.
     * 
     * @return the gZZBH
     */
    public String getGZZBH() {

        return GZZBH;
    }


    /**
     * Sets the gZZBH.
     * 
     * @param gzzbh the new gZZBH
     */
    public void setGZZBH(String gzzbh) {

        GZZBH = gzzbh;
    }


    /**
     * Gets the gZZMC.
     * 
     * @return the gZZMC
     */
    public String getGZZMC() {

        return GZZMC;
    }


    /**
     * Sets the gZZMC.
     * 
     * @param gzzmc the new gZZMC
     */
    public void setGZZMC(String gzzmc) {

        GZZMC = gzzmc;
    }


    /**
     * Gets the gZZSM.
     * 
     * @return the gZZSM
     */
    public String getGZZSM() {

        return GZZSM;
    }


    /**
     * Sets the gZZSM.
     * 
     * @param gzzsm the new gZZSM
     */
    public void setGZZSM(String gzzsm) {

        GZZSM = gzzsm;
    }


    /**
     * Gets the gZZZT.
     * 
     * @return the gZZZT
     */
    public String getGZZZT() {

        return GZZZT;
    }


    /**
     * Sets the gZZZT.
     * 
     * @param gzzzt the new gZZZT
     */
    public void setGZZZT(String gzzzt) {

        GZZZT = gzzzt;
    }


    /**
     * Gets the yHM.
     * 
     * @return the yHM
     */
    public String getYHM() {

        return YHM;
    }


    /**
     * Sets the yHM.
     * 
     * @param yhm the new yHM
     */
    public void setYHM(String yhm) {

        YHM = yhm;
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
     * Gets the cZZCYID.
     * 
     * @return the cZZCYID
     */
    public String getCZZCYID() {

        return CZZCYID;
    }


    /**
     * Sets the cZZCYID.
     * 
     * @param czzcyid the new cZZCYID
     */
    public void setCZZCYID(String czzcyid) {

        CZZCYID = czzcyid;
    }


	/**
	 * Gets the jGXXID.
	 * 
	 * @return the jGXXID
	 */
	public String getJGXXID() {
		return JGXXID;
	}


	/**
	 * Sets the jGXXID.
	 * 
	 * @param jgxxid the new jGXXID
	 */
	public void setJGXXID(String jgxxid) {
		JGXXID = jgxxid;
	}


	/**
	 * Gets the jGMC.
	 * 
	 * @return the jGMC
	 */
	public String getJGMC() {
		return JGMC;
	}


	/**
	 * Sets the jGMC.
	 * 
	 * @param jgmc the new jGMC
	 */
	public void setJGMC(String jgmc) {
		JGMC = jgmc;
	}


	/**
	 * Gets the bMXXID.
	 * 
	 * @return the bMXXID
	 */
	public String getBMXXID() {
		return BMXXID;
	}


	/**
	 * Sets the bMXXID.
	 * 
	 * @param bmxxid the new bMXXID
	 */
	public void setBMXXID(String bmxxid) {
		BMXXID = bmxxid;
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
	 * Sets the bMMC.
	 * 
	 * @param bmmc the new bMMC
	 */
	public void setBMMC(String bmmc) {
		BMMC = bmmc;
	}

}
