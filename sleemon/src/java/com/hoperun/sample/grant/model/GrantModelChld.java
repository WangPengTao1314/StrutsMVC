/**
 * prjName:供应链_贵人鸟
 * ucName:不良通知单
 * fileName:GrantModelChld
*/
package com.hoperun.sample.grant.model;
import com.hoperun.commons.model.BaseModel;
/**
 * *@module
 * *@func
 * *@version 1.1
 * *@author zhuxw
 * *@createdate 2013-05-15 10:35:31
 */
public class GrantModelChld extends BaseModel{
   /** 成品不良通知单明细ID **/
   private String CPBLTZDMXID;
   /** 成品不良通知单ID **/
   private String CPBLTZDID;
   /** 成品质检项目ID **/
   private String CPZJXMID;
   /** 成品质检项目编号 **/
   private String CPZJXMBH;
   /** 成品质检项目名称 **/
   private String CPZJXMMC;
   /** 质检项目类别 **/
   private String ZJXMLB;
   /** 应用标准 **/
   private String YYBZ;
   /** 检验参数 **/
   private String JYCS;
   /** 合格标准 **/
   private String HGBZ;
   /** 检验值 **/
   private String JYZ;
   /** 让步接收数量 **/
   private String RBJSSL;
   /** 报废数量 **/
   private String BFSL;
   /** 退回数量 **/
   private String THSL;
   /** 修色数量 **/
   private String XSSL;
   /** 备注 **/
   private String REMARK;
   /** 可正常入库数量 **/
   private String KZCRKSL;
   /**  **/
   private String DELFLAG;
     /**
     * get 成品不良通知单明细ID value
     * @return the CPBLTZDMXID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPBLTZDMXID(){
        return CPBLTZDMXID;
    }
	/**
     * set 成品不良通知单明细ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPBLTZDMXID(String CPBLTZDMXID){
        this.CPBLTZDMXID=CPBLTZDMXID;
    }
     /**
     * get 成品不良通知单ID value
     * @return the CPBLTZDID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPBLTZDID(){
        return CPBLTZDID;
    }
	/**
     * set 成品不良通知单ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPBLTZDID(String CPBLTZDID){
        this.CPBLTZDID=CPBLTZDID;
    }
     /**
     * get 成品质检项目ID value
     * @return the CPZJXMID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPZJXMID(){
        return CPZJXMID;
    }
	/**
     * set 成品质检项目ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPZJXMID(String CPZJXMID){
        this.CPZJXMID=CPZJXMID;
    }
     /**
     * get 成品质检项目编号 value
     * @return the CPZJXMBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPZJXMBH(){
        return CPZJXMBH;
    }
	/**
     * set 成品质检项目编号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPZJXMBH(String CPZJXMBH){
        this.CPZJXMBH=CPZJXMBH;
    }
     /**
     * get 成品质检项目名称 value
     * @return the CPZJXMMC
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPZJXMMC(){
        return CPZJXMMC;
    }
	/**
     * set 成品质检项目名称 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPZJXMMC(String CPZJXMMC){
        this.CPZJXMMC=CPZJXMMC;
    }
     /**
     * get 质检项目类别 value
     * @return the ZJXMLB
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getZJXMLB(){
        return ZJXMLB;
    }
	/**
     * set 质检项目类别 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setZJXMLB(String ZJXMLB){
        this.ZJXMLB=ZJXMLB;
    }
     /**
     * get 应用标准 value
     * @return the YYBZ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getYYBZ(){
        return YYBZ;
    }
	/**
     * set 应用标准 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setYYBZ(String YYBZ){
        this.YYBZ=YYBZ;
    }
     /**
     * get 检验参数 value
     * @return the JYCS
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJYCS(){
        return JYCS;
    }
	/**
     * set 检验参数 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJYCS(String JYCS){
        this.JYCS=JYCS;
    }
     /**
     * get 合格标准 value
     * @return the HGBZ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getHGBZ(){
        return HGBZ;
    }
	/**
     * set 合格标准 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setHGBZ(String HGBZ){
        this.HGBZ=HGBZ;
    }
     /**
     * get 检验值 value
     * @return the JYZ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJYZ(){
        return JYZ;
    }
	/**
     * set 检验值 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJYZ(String JYZ){
        this.JYZ=JYZ;
    }
     /**
     * get 让步接收数量 value
     * @return the RBJSSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getRBJSSL(){
        return RBJSSL;
    }
	/**
     * set 让步接收数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setRBJSSL(String RBJSSL){
        this.RBJSSL=RBJSSL;
    }
     /**
     * get 报废数量 value
     * @return the BFSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getBFSL(){
        return BFSL;
    }
	/**
     * set 报废数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setBFSL(String BFSL){
        this.BFSL=BFSL;
    }
     /**
     * get 退回数量 value
     * @return the THSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getTHSL(){
        return THSL;
    }
	/**
     * set 退回数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setTHSL(String THSL){
        this.THSL=THSL;
    }
     /**
     * get 修色数量 value
     * @return the XSSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getXSSL(){
        return XSSL;
    }
	/**
     * set 修色数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setXSSL(String XSSL){
        this.XSSL=XSSL;
    }
     /**
     * get 备注 value
     * @return the REMARK
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getREMARK(){
        return REMARK;
    }
	/**
     * set 备注 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setREMARK(String REMARK){
        this.REMARK=REMARK;
    }
     /**
     * get 可正常入库数量 value
     * @return the KZCRKSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getKZCRKSL(){
        return KZCRKSL;
    }
	/**
     * set 可正常入库数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setKZCRKSL(String KZCRKSL){
        this.KZCRKSL=KZCRKSL;
    }
     /**
     * get  value
     * @return the DELFLAG
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getDELFLAG(){
        return DELFLAG;
    }
	/**
     * set  value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setDELFLAG(String DELFLAG){
        this.DELFLAG=DELFLAG;
    }
}