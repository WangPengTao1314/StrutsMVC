/**
 * 项目名称：平台
 * 系统名：分销业务
 * 文件名：EmployeeModel.java
 */
package com.hoperun.drp.base.employee.model;

// TODO: Auto-generated Javadoc
/**
 * The Class RYXXModel.
 * 
 * @module 系统管理
 * @func 人员信息
 * @version 1.1
 * @author 吴亚林
 */
public class EmployeeModel {

    /** The RYLB. */
    private String RYLB;      //人员类别

    /** The SBJS. */
    private String SBJS;      //社保基数

    /** The RYXXID. */
    private String RYXXID;    //人员ID

    /** The RYBH. */
    private String RYBH;      //人员编号

    /** The XM. */
    private String XM;        //姓名

    /** The XB. */
    private String XB;        //性别

    /** The SFZH. */
    private String SFZH;      //身份证号

    /** The ZW. */
    private String ZW;        //职务

    /** The GSDH. */
    private String GSDH;      //公司电话

    /** The SJ. */
    private String SJ;        //手机

    /** The DZYJ. */
    private String DZYJ;      //电子邮件

    /** The RYZT. */
    private String RYZT;      //人员状态

    /** The RYJB. */
    private String RYJB;      //人员级别

    /** The JGXXID. */
    private String JGXXID;    //机构信息

    /** The BMXXID. */
    private String BMXXID;    //部门信息

    /** The CWDZM. */
    private String CWDZM;

    /** The order field. */
    private String orderField;

    private String TERM_DECT_FROM; //可改终端销售折扣从
    private String TERM_DECT_END;	//可改终端销售折扣到
    private String YHM;//用户名
    
    /**
	 * @return the tERM_DECT_FROM
	 */
	public String getTERM_DECT_FROM() {
		return TERM_DECT_FROM;
	}


	/**
	 * @param tERMDECTFROM the tERM_DECT_FROM to set
	 */
	public void setTERM_DECT_FROM(String tERMDECTFROM) {
		TERM_DECT_FROM = tERMDECTFROM;
	}


	/**
	 * @return the tERM_DECT_END
	 */
	public String getTERM_DECT_END() {
		return TERM_DECT_END;
	}


	/**
	 * @param tERMDECTEND the tERM_DECT_END to set
	 */
	public void setTERM_DECT_END(String tERMDECTEND) {
		TERM_DECT_END = tERMDECTEND;
	}


	/**
     * Gets the dZYJ.
     * 
     * @return the dZYJ
     */
    public String getDZYJ() {

        return DZYJ;
    }


    /**
     * Sets the dZYJ.
     * 
     * @param dzyj the new dZYJ
     */
    public void setDZYJ(String dzyj) {

        DZYJ = dzyj;
    }


    /**
     * Gets the gSDH.
     * 
     * @return the gSDH
     */
    public String getGSDH() {

        return GSDH;
    }


    /**
     * Sets the gSDH.
     * 
     * @param gsdh the new gSDH
     */
    public void setGSDH(String gsdh) {

        GSDH = gsdh;
    }


    /**
     * Gets the rYBH.
     * 
     * @return the rYBH
     */
    public String getRYBH() {

        return RYBH;
    }


    /**
     * Sets the rYBH.
     * 
     * @param rybh the new rYBH
     */
    public void setRYBH(String rybh) {

        RYBH = rybh;
    }


    /**
     * Gets the xB.
     * 
     * @return the xB
     */
    public String getXB() {

        return XB;
    }


    /**
     * Sets the xB.
     * 
     * @param xb the new xB
     */
    public void setXB(String xb) {

        XB = xb;
    }


    /**
     * Gets the rYXXID.
     * 
     * @return the rYXXID
     */
    public String getRYXXID() {

        return RYXXID;
    }


    /**
     * Sets the rYXXID.
     * 
     * @param ryxxid the new rYXXID
     */
    public void setRYXXID(String ryxxid) {

        RYXXID = ryxxid;
    }


    /**
     * Gets the rYZT.
     * 
     * @return the rYZT
     */
    public String getRYZT() {

        return RYZT;
    }


    /**
     * Sets the rYZT.
     * 
     * @param ryzt the new rYZT
     */
    public void setRYZT(String ryzt) {

        RYZT = ryzt;
    }


    /**
     * Gets the sFZH.
     * 
     * @return the sFZH
     */
    public String getSFZH() {

        return SFZH;
    }


    /**
     * Sets the sFZH.
     * 
     * @param sfzh the new sFZH
     */
    public void setSFZH(String sfzh) {

        SFZH = sfzh;
    }


    /**
     * Gets the sJ.
     * 
     * @return the sJ
     */
    public String getSJ() {

        return SJ;
    }


    /**
     * Sets the sJ.
     * 
     * @param sj the new sJ
     */
    public void setSJ(String sj) {

        SJ = sj;
    }


    /**
     * Gets the xM.
     * 
     * @return the xM
     */
    public String getXM() {

        return XM;
    }


    /**
     * Sets the xM.
     * 
     * @param xm the new xM
     */
    public void setXM(String xm) {

        XM = xm;
    }


    /**
     * Gets the zW.
     * 
     * @return the zW
     */
    public String getZW() {

        return ZW;
    }


    /**
     * Sets the zW.
     * 
     * @param zw the new zW
     */
    public void setZW(String zw) {

        ZW = zw;
    }


    /**
     * Gets the order field.
     * 
     * @return the order field
     */
    public String getOrderField() {

        return orderField;
    }


    /**
     * Sets the order field.
     * 
     * @param orderField the new order field
     */
    public void setOrderField(String orderField) {

        this.orderField = orderField;
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
     * Gets the rYJB.
     * 
     * @return the rYJB
     */
    public String getRYJB() {

        return RYJB;
    }


    /**
     * Sets the rYJB.
     * 
     * @param ryjb the new rYJB
     */
    public void setRYJB(String ryjb) {

        RYJB = ryjb;
    }


    /**
     * Gets the rYLB.
     * 
     * @return the rYLB
     */
    public String getRYLB() {

        return RYLB;
    }


    /**
     * Sets the rYLB.
     * 
     * @param rylb the new rYLB
     */
    public void setRYLB(String rylb) {

        RYLB = rylb;
    }


    /**
     * Gets the sBJS.
     * 
     * @return the sBJS
     */
    public String getSBJS() {

        return SBJS;
    }


    /**
     * Sets the sBJS.
     * 
     * @param sbjs the new sBJS
     */
    public void setSBJS(String sbjs) {

        SBJS = sbjs;
    }


    /**
     * Gets the cWDZM.
     * 
     * @return the cWDZM
     */
    public String getCWDZM() {

        return CWDZM;
    }


    /**
     * Sets the cWDZM.
     * 
     * @param cwdzm the new cWDZM
     */
    public void setCWDZM(String cwdzm) {

        CWDZM = cwdzm;
    }


	/**
	 * @return the yHM
	 */
	public String getYHM() {
		return YHM;
	}


	/**
	 * @param yHM the yHM to set
	 */
	public void setYHM(String yHM) {
		YHM = yHM;
	}

}
