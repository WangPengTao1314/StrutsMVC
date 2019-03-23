/**
 *@module 基础资料
 *@func 编码规则维护
 *@version 1.1
 *@author 孙炜
 */
package com.hoperun.sys.model;

import com.hoperun.commons.model.BaseModel;

// TODO: Auto-generated Javadoc
/**
 * The Class BmgzModel.
 */
public class BmgzModel extends BaseModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6681766681233801853L;

    /* 主键 */
    /** The BMGZID. */
    private String            BMGZID;

    /* 编码编号 */
    /** The BMBH. */
    private String            BMBH;

    /* 编码对象 */
    /** The BMDX. */
    private String            BMDX;

    /* 规则名称 */
    /** The GZMC. */
    private String            GZMC;

    /* 段数 */
    /** The DS. */
    private String            DS;

    /* 总长度 */
    /** The ZCD. */
    private String            ZCD;

    /* 状态 */
    /** The STATE. */
    private String            STATE;

    /* 备注 */
    /** The REMARK. */
    private String            REMARK;

    /** The SEQUENCEMC. */
    private String            SEQUENCEMC;


    /**
     * Gets the bMGZID.
     * 
     * @return the bMGZID
     */
    public String getBMGZID() {

        return BMGZID;
    }


    /**
     * Sets the bMGZID.
     * 
     * @param bmgzid the new bMGZID
     */
    public void setBMGZID(String bmgzid) {

        BMGZID = bmgzid;
    }


    /**
     * Gets the bMDX.
     * 
     * @return the bMDX
     */
    public String getBMDX() {

        return BMDX;
    }


    /**
     * Sets the bMDX.
     * 
     * @param bmdx the new bMDX
     */
    public void setBMDX(String bmdx) {

        BMDX = bmdx;
    }


    /**
     * Gets the gZMC.
     * 
     * @return the gZMC
     */
    public String getGZMC() {

        return GZMC;
    }


    /**
     * Sets the gZMC.
     * 
     * @param gzmc the new gZMC
     */
    public void setGZMC(String gzmc) {

        GZMC = gzmc;
    }


    /**
     * Gets the dS.
     * 
     * @return the dS
     */
    public String getDS() {

        return DS;
    }


    /**
     * Sets the dS.
     * 
     * @param ds the new dS
     */
    public void setDS(String ds) {

        DS = ds;
    }


    /**
     * Gets the zCD.
     * 
     * @return the zCD
     */
    public String getZCD() {

        return ZCD;
    }


    /**
     * Sets the zCD.
     * 
     * @param zcd the new zCD
     */
    public void setZCD(String zcd) {

        ZCD = zcd;
    }


    /**
     * Gets the sTATE.
     * 
     * @return the sTATE
     */
    public String getSTATE() {

        return STATE;
    }


    /**
     * Sets the sTATE.
     * 
     * @param state the new sTATE
     */
    public void setSTATE(String state) {

        STATE = state;
    }


    /**
     * Gets the rEMARK.
     * 
     * @return the rEMARK
     */
    public String getREMARK() {

        return REMARK;
    }


    /**
     * Sets the rEMARK.
     * 
     * @param remark the new rEMARK
     */
    public void setREMARK(String remark) {

        REMARK = remark;
    }


    /**
     * Gets the bMBH.
     * 
     * @return the bMBH
     */
    public String getBMBH() {

        return BMBH;
    }


    /**
     * Sets the bMBH.
     * 
     * @param bmbh the new bMBH
     */
    public void setBMBH(String bmbh) {

        BMBH = bmbh;
    }


    /**
     * Gets the sEQUENCEMC.
     * 
     * @return the sEQUENCEMC
     */
    public String getSEQUENCEMC() {

        return SEQUENCEMC;
    }


    /**
     * Sets the sEQUENCEMC.
     * 
     * @param sequencemc the new sEQUENCEMC
     */
    public void setSEQUENCEMC(String sequencemc) {

        SEQUENCEMC = sequencemc;
    }

}
