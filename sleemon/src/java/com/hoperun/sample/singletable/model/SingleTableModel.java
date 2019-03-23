/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXModel.java
 */

package com.hoperun.sample.singletable.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SingleTableModel.
 * 
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
public class SingleTableModel extends BaseModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -4943417535815136771L;
    
    /** The FGCWRYID. */
    private String FGCWRYID;//分管财务人员ID
    
    /** The FGCWRYXM. */
    private String FGCWRYXM;//分管财务人员XM
    
    /** The JGXXID. */
    private String            JGXXID;                                  //机构信息ID

    /** The DZXXID. */
    private String            DZXXID;                                  //地址信息ID

    /** The ZTXXID. */
    private String            ZTXXID;                                  //帐套信息ID

    /** The JGBH. */
    private String            JGBH;                                    //机构编号

    /** The JGMC. */
    private String            JGMC;                                    //机构名称

    /** The JGJC. */
    private String            JGJC;                                    //机构简称

    /** The FRDB. */
    private String            FRDB;                                    //法人代表

    /** The DH. */
    private String            DH;                                      //电话

    /** The CZ. */
    private String            CZ;                                      //传真

    /** The DZYJ. */
    private String            DZYJ;                                    //电子邮件

    /** The ZYDZ. */
    private String            ZYDZ;                                    //主页地址

    /** The SJJG. */
    private String            SJJG;                                    //上级机构

    /** The JGZT. */
    private String            JGZT;                                    //机构状态

    /** The QTSM. */
    private String            QTSM;                                    //其他说明

    /** The XXDZ. */
    private String            XXDZ;                                    //详细地址

    /** The YZBM. */
    private String            YZBM;                                    //邮政编码

    /** The JGYWMC. */
    private String            JGYWMC;                                  //机构英文名称

    /** The JGYWJC. */
    private String            JGYWJC;                                  //机构英文简称

    /** The JGYWXXDZ. */
    private String            JGYWXXDZ;                                //英文名称详细地址

    /** The XH. */
    private Integer           XH;                                      //序号

    /** The order field. */
    private String            orderField;                              //


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
     * @param jGXXID the new jGXXID
     */
    public void setJGXXID(String jGXXID) {

        JGXXID = jGXXID;
    }


    /**
     * Gets the dZXXID.
     * 
     * @return the dZXXID
     */
    public String getDZXXID() {

        return DZXXID;
    }


    /**
     * Sets the dZXXID.
     * 
     * @param dZXXID the new dZXXID
     */
    public void setDZXXID(String dZXXID) {

        DZXXID = dZXXID;
    }


    /**
     * Gets the zTXXID.
     * 
     * @return the zTXXID
     */
    public String getZTXXID() {

        return ZTXXID;
    }


    /**
     * Sets the zTXXID.
     * 
     * @param zTXXID the new zTXXID
     */
    public void setZTXXID(String zTXXID) {

        ZTXXID = zTXXID;
    }


    /**
     * Gets the jGBH.
     * 
     * @return the jGBH
     */
    public String getJGBH() {

        return JGBH;
    }


    /**
     * Sets the jGBH.
     * 
     * @param jGBH the new jGBH
     */
    public void setJGBH(String jGBH) {

        JGBH = jGBH;
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
     * @param jGMC the new jGMC
     */
    public void setJGMC(String jGMC) {

        JGMC = jGMC;
    }


    /**
     * Gets the jGJC.
     * 
     * @return the jGJC
     */
    public String getJGJC() {

        return JGJC;
    }


    /**
     * Sets the jGJC.
     * 
     * @param jGJC the new jGJC
     */
    public void setJGJC(String jGJC) {

        JGJC = jGJC;
    }


    /**
     * Gets the fRDB.
     * 
     * @return the fRDB
     */
    public String getFRDB() {

        return FRDB;
    }


    /**
     * Sets the fRDB.
     * 
     * @param fRDB the new fRDB
     */
    public void setFRDB(String fRDB) {

        FRDB = fRDB;
    }


    /**
     * Gets the dH.
     * 
     * @return the dH
     */
    public String getDH() {

        return DH;
    }


    /**
     * Sets the dH.
     * 
     * @param dH the new dH
     */
    public void setDH(String dH) {

        DH = dH;
    }


    /**
     * Gets the cZ.
     * 
     * @return the cZ
     */
    public String getCZ() {

        return CZ;
    }


    /**
     * Sets the cZ.
     * 
     * @param cZ the new cZ
     */
    public void setCZ(String cZ) {

        CZ = cZ;
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
     * @param dZYJ the new dZYJ
     */
    public void setDZYJ(String dZYJ) {

        DZYJ = dZYJ;
    }


    /**
     * Gets the zYDZ.
     * 
     * @return the zYDZ
     */
    public String getZYDZ() {

        return ZYDZ;
    }


    /**
     * Sets the zYDZ.
     * 
     * @param zYDZ the new zYDZ
     */
    public void setZYDZ(String zYDZ) {

        ZYDZ = zYDZ;
    }


    /**
     * Gets the sJJG.
     * 
     * @return the sJJG
     */
    public String getSJJG() {

        return SJJG;
    }


    /**
     * Sets the sJJG.
     * 
     * @param sJJG the new sJJG
     */
    public void setSJJG(String sJJG) {

        SJJG = sJJG;
    }


    /**
     * Gets the jGZT.
     * 
     * @return the jGZT
     */
    public String getJGZT() {

        return JGZT;
    }


    /**
     * Sets the jGZT.
     * 
     * @param jGZT the new jGZT
     */
    public void setJGZT(String jGZT) {

        JGZT = jGZT;
    }


    /**
     * Gets the qTSM.
     * 
     * @return the qTSM
     */
    public String getQTSM() {

        return QTSM;
    }


    /**
     * Sets the qTSM.
     * 
     * @param qTSM the new qTSM
     */
    public void setQTSM(String qTSM) {

        QTSM = qTSM;
    }


    /**
     * Gets the xXDZ.
     * 
     * @return the xXDZ
     */
    public String getXXDZ() {

        return XXDZ;
    }


    /**
     * Sets the xXDZ.
     * 
     * @param xXDZ the new xXDZ
     */
    public void setXXDZ(String xXDZ) {

        XXDZ = xXDZ;
    }


    /**
     * Gets the yZBM.
     * 
     * @return the yZBM
     */
    public String getYZBM() {

        return YZBM;
    }


    /**
     * Sets the yZBM.
     * 
     * @param yZBM the new yZBM
     */
    public void setYZBM(String yZBM) {

        YZBM = yZBM;
    }


    /**
     * Gets the jGYWMC.
     * 
     * @return the jGYWMC
     */
    public String getJGYWMC() {

        return JGYWMC;
    }


    /**
     * Sets the jGYWMC.
     * 
     * @param jGYWMC the new jGYWMC
     */
    public void setJGYWMC(String jGYWMC) {

        JGYWMC = jGYWMC;
    }


    /**
     * Gets the jGYWJC.
     * 
     * @return the jGYWJC
     */
    public String getJGYWJC() {

        return JGYWJC;
    }


    /**
     * Sets the jGYWJC.
     * 
     * @param jGYWJC the new jGYWJC
     */
    public void setJGYWJC(String jGYWJC) {

        JGYWJC = jGYWJC;
    }


    /**
     * Gets the jGYWXXDZ.
     * 
     * @return the jGYWXXDZ
     */
    public String getJGYWXXDZ() {

        return JGYWXXDZ;
    }


    /**
     * Sets the jGYWXXDZ.
     * 
     * @param jGYWXXDZ the new jGYWXXDZ
     */
    public void setJGYWXXDZ(String jGYWXXDZ) {

        JGYWXXDZ = jGYWXXDZ;
    }


    /**
     * Gets the xH.
     * 
     * @return the xH
     */
    public Integer getXH() {

        return XH;
    }


    /**
     * Sets the xH.
     * 
     * @param xH the new xH
     */
    public void setXH(Integer xH) {

        XH = xH;
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
	 * Gets the fGCWRYID.
	 * 
	 * @return the fGCWRYID
	 */
	public String getFGCWRYID() {
		return FGCWRYID;
	}


	/**
	 * Sets the fGCWRYID.
	 * 
	 * @param fgcwryid the new fGCWRYID
	 */
	public void setFGCWRYID(String fgcwryid) {
		FGCWRYID = fgcwryid;
	}


	/**
	 * Gets the fGCWRYXM.
	 * 
	 * @return the fGCWRYXM
	 */
	public String getFGCWRYXM() {
		return FGCWRYXM;
	}


	/**
	 * Sets the fGCWRYXM.
	 * 
	 * @param fgcwryxm the new fGCWRYXM
	 */
	public void setFGCWRYXM(String fgcwryxm) {
		FGCWRYXM = fgcwryxm;
	}

}
