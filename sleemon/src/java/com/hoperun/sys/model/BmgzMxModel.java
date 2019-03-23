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
 * The Class BmgzMxModel.
 */
public class BmgzMxModel extends BaseModel {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -2724620994676861889L;

    /* 主键 */
    /** The BMGZMXID. */
    private String            BMGZMXID;

    /* 编码规则id(外键) */
    /** The BMGZID. */
    private String            BMGZID;

    /* 顺序号 */
    /** The SXH. */
    private String            SXH;

    /* 段类型 */
    /** The DLX. */
    private String            DLX;

    /* 段长度 */
    /** The DCD. */
    private String            DCD;

    /* 段头 */
    /** The DT. */
    private String            DT;

    /* 年样式 */
    /** The NYS. */
    private String            NYS;

    /* 步长 */
    /** The BC. */
    private String            BC;

    /* 初始值 */
    /** The CSZ. */
    private String            CSZ;

    /* 状态 */
    /** The STATE. */
    private String            STATE;


    /**
     * Gets the bMGZMXID.
     * 
     * @return the bMGZMXID
     */
    public String getBMGZMXID() {

        return BMGZMXID;
    }


    /**
     * Sets the bMGZMXID.
     * 
     * @param bmgzmxid the new bMGZMXID
     */
    public void setBMGZMXID(String bmgzmxid) {

        BMGZMXID = bmgzmxid;
    }


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
     * Gets the sXH.
     * 
     * @return the sXH
     */
    public String getSXH() {

        return SXH;
    }


    /**
     * Sets the sXH.
     * 
     * @param sxh the new sXH
     */
    public void setSXH(String sxh) {

        SXH = sxh;
    }


    /**
     * Gets the dLX.
     * 
     * @return the dLX
     */
    public String getDLX() {

        return DLX;
    }


    /**
     * Sets the dLX.
     * 
     * @param dlx the new dLX
     */
    public void setDLX(String dlx) {

        DLX = dlx;
    }


    /**
     * Gets the dCD.
     * 
     * @return the dCD
     */
    public String getDCD() {

        return DCD;
    }


    /**
     * Sets the dCD.
     * 
     * @param dcd the new dCD
     */
    public void setDCD(String dcd) {

        DCD = dcd;
    }


    /**
     * Gets the dT.
     * 
     * @return the dT
     */
    public String getDT() {

        return DT;
    }


    /**
     * Sets the dT.
     * 
     * @param dt the new dT
     */
    public void setDT(String dt) {

        DT = dt;
    }


    /**
     * Gets the nYS.
     * 
     * @return the nYS
     */
    public String getNYS() {

        return NYS;
    }


    /**
     * Sets the nYS.
     * 
     * @param nys the new nYS
     */
    public void setNYS(String nys) {

        NYS = nys;
    }


    /**
     * Gets the bC.
     * 
     * @return the bC
     */
    public String getBC() {

        return BC;
    }


    /**
     * Sets the bC.
     * 
     * @param bc the new bC
     */
    public void setBC(String bc) {

        BC = bc;
    }


    /**
     * Gets the cSZ.
     * 
     * @return the cSZ
     */
    public String getCSZ() {

        return CSZ;
    }


    /**
     * Sets the cSZ.
     * 
     * @param csz the new cSZ
     */
    public void setCSZ(String csz) {

        CSZ = csz;
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

}
