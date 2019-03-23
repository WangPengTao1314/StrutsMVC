/**
 * prjName:供应链_贵人鸟
 * ucName:不良通知单
 * fileName:GrantModel
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
public class GrantModel extends BaseModel{
   /** 成品不良通知单ID **/
   private String CPBLTZDID;
   /** 成品不良通知单编号 **/
   private String CPBLTZDBH;
   /** 流程号 **/
   private String LCH;
   /** 物料信息ID **/
   private String WLXXID;
   /** 往来单位信息ID **/
   private String WLDWXXID;
   /** 往来单位编号 **/
   private String WLDWBH;
   /** 往来单位名称 **/
   private String WLDWMC;
   /** 物料信息ID **/
   private String WLXXYSID;
   /** 成品质检通知单ID **/
   private String CPZJTZDID;
   /** 成品质检单编号 **/
   private String CPZJDBH;
   /** 成品采购订单编号 **/
   private String CPCGDDBH;
   /** 品牌 **/
   private String PP;
   /** 年份 **/
   private String NF;
   /** 季节 **/
   private String JJ;
   /** 颜色编号 **/
   private String YSBH;
   /** 颜色名称 **/
   private String YSMC;
   /** 款号 **/
   private String WLBH;
   /** 款名 **/
   private String WLMC;
   /** 通知数量 **/
   private String TZSL;
   /** 通知合格数量 **/
   private String TZHGSL;
   /** 通知不合格数量 **/
   private String TZBHGSL;
   /** 退回数量 **/
   private String THSL;
   /** 让步接收数量 **/
   private String RBJSSL;
   /** 报废数量 **/
   private String BFSL;
   /** 修色数量 **/
   private String XSSL;
   /** 检验人 **/
   private String JYRID;
   /**  **/
   private String JYRXM;
   /** 检验时间 **/
   private String JYSJ;
   /** 处理意见 **/
   private String CLYJ;
   /** 外部原因标记 **/
   private String WBYYBJ;
   /** 备注 **/
   private String REMARK;
   /** 审核人ID **/
   private String AUDITER;
   /** 审核人姓名 **/
   private String AUDITNAME;
   /** 审核时间 **/
   private String AUDITTIME;
   /** 已处理通知不合格数量 **/
   private String YTZBHGSL;
   /** 可处理通知不合格数量 **/
   private String KTZBHGSL;
   /** 可正常入库数量 **/
   private String KZCRKSL;
   /** 入库序号 **/
   private String RKXH;
   /**  **/
   private String CRENAME;
   /**  **/
   private String CREATER;
   /** 制单时间 **/
   private String CRETIME;
   /** 更新人姓名 **/
   private String UPDNAME;
   /**  **/
   private String UPDATER;
   /**  **/
   private String UPDTIME;
   /** 部门信息ID **/
   private String BMXXID;
   /** 部门名称 **/
   private String BMMC;
   /** 机构信息ID **/
   private String JGXXID;
   /** 机构名称 **/
   private String JGMC;
   /** 帐套信息ID **/
   private String ZTXXID;
   /** 状态 **/
   private String STATE;
   /**  **/
   private String DELFLAG;
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
     * get 成品不良通知单编号 value
     * @return the CPBLTZDBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPBLTZDBH(){
        return CPBLTZDBH;
    }
	/**
     * set 成品不良通知单编号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPBLTZDBH(String CPBLTZDBH){
        this.CPBLTZDBH=CPBLTZDBH;
    }
     /**
     * get 流程号 value
     * @return the LCH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getLCH(){
        return LCH;
    }
	/**
     * set 流程号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setLCH(String LCH){
        this.LCH=LCH;
    }
     /**
     * get 物料信息ID value
     * @return the WLXXID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLXXID(){
        return WLXXID;
    }
	/**
     * set 物料信息ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLXXID(String WLXXID){
        this.WLXXID=WLXXID;
    }
     /**
     * get 往来单位信息ID value
     * @return the WLDWXXID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLDWXXID(){
        return WLDWXXID;
    }
	/**
     * set 往来单位信息ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLDWXXID(String WLDWXXID){
        this.WLDWXXID=WLDWXXID;
    }
     /**
     * get 往来单位编号 value
     * @return the WLDWBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLDWBH(){
        return WLDWBH;
    }
	/**
     * set 往来单位编号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLDWBH(String WLDWBH){
        this.WLDWBH=WLDWBH;
    }
     /**
     * get 往来单位名称 value
     * @return the WLDWMC
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLDWMC(){
        return WLDWMC;
    }
	/**
     * set 往来单位名称 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLDWMC(String WLDWMC){
        this.WLDWMC=WLDWMC;
    }
     /**
     * get 物料信息ID value
     * @return the WLXXYSID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLXXYSID(){
        return WLXXYSID;
    }
	/**
     * set 物料信息ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLXXYSID(String WLXXYSID){
        this.WLXXYSID=WLXXYSID;
    }
     /**
     * get 成品质检通知单ID value
     * @return the CPZJTZDID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPZJTZDID(){
        return CPZJTZDID;
    }
	/**
     * set 成品质检通知单ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPZJTZDID(String CPZJTZDID){
        this.CPZJTZDID=CPZJTZDID;
    }
     /**
     * get 成品质检单编号 value
     * @return the CPZJDBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPZJDBH(){
        return CPZJDBH;
    }
	/**
     * set 成品质检单编号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPZJDBH(String CPZJDBH){
        this.CPZJDBH=CPZJDBH;
    }
     /**
     * get 成品采购订单编号 value
     * @return the CPCGDDBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCPCGDDBH(){
        return CPCGDDBH;
    }
	/**
     * set 成品采购订单编号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCPCGDDBH(String CPCGDDBH){
        this.CPCGDDBH=CPCGDDBH;
    }
     /**
     * get 品牌 value
     * @return the PP
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getPP(){
        return PP;
    }
	/**
     * set 品牌 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setPP(String PP){
        this.PP=PP;
    }
     /**
     * get 年份 value
     * @return the NF
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getNF(){
        return NF;
    }
	/**
     * set 年份 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setNF(String NF){
        this.NF=NF;
    }
     /**
     * get 季节 value
     * @return the JJ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJJ(){
        return JJ;
    }
	/**
     * set 季节 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJJ(String JJ){
        this.JJ=JJ;
    }
     /**
     * get 颜色编号 value
     * @return the YSBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getYSBH(){
        return YSBH;
    }
	/**
     * set 颜色编号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setYSBH(String YSBH){
        this.YSBH=YSBH;
    }
     /**
     * get 颜色名称 value
     * @return the YSMC
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getYSMC(){
        return YSMC;
    }
	/**
     * set 颜色名称 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setYSMC(String YSMC){
        this.YSMC=YSMC;
    }
     /**
     * get 款号 value
     * @return the WLBH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLBH(){
        return WLBH;
    }
	/**
     * set 款号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLBH(String WLBH){
        this.WLBH=WLBH;
    }
     /**
     * get 款名 value
     * @return the WLMC
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWLMC(){
        return WLMC;
    }
	/**
     * set 款名 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWLMC(String WLMC){
        this.WLMC=WLMC;
    }
     /**
     * get 通知数量 value
     * @return the TZSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getTZSL(){
        return TZSL;
    }
	/**
     * set 通知数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setTZSL(String TZSL){
        this.TZSL=TZSL;
    }
     /**
     * get 通知合格数量 value
     * @return the TZHGSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getTZHGSL(){
        return TZHGSL;
    }
	/**
     * set 通知合格数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setTZHGSL(String TZHGSL){
        this.TZHGSL=TZHGSL;
    }
     /**
     * get 通知不合格数量 value
     * @return the TZBHGSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getTZBHGSL(){
        return TZBHGSL;
    }
	/**
     * set 通知不合格数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setTZBHGSL(String TZBHGSL){
        this.TZBHGSL=TZBHGSL;
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
     * get 检验人 value
     * @return the JYRID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJYRID(){
        return JYRID;
    }
	/**
     * set 检验人 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJYRID(String JYRID){
        this.JYRID=JYRID;
    }
     /**
     * get  value
     * @return the JYRXM
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJYRXM(){
        return JYRXM;
    }
	/**
     * set  value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJYRXM(String JYRXM){
        this.JYRXM=JYRXM;
    }
     /**
     * get 检验时间 value
     * @return the JYSJ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJYSJ(){
        return JYSJ;
    }
	/**
     * set 检验时间 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJYSJ(String JYSJ){
        this.JYSJ=JYSJ;
    }
     /**
     * get 处理意见 value
     * @return the CLYJ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCLYJ(){
        return CLYJ;
    }
	/**
     * set 处理意见 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCLYJ(String CLYJ){
        this.CLYJ=CLYJ;
    }
     /**
     * get 外部原因标记 value
     * @return the WBYYBJ
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getWBYYBJ(){
        return WBYYBJ;
    }
	/**
     * set 外部原因标记 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setWBYYBJ(String WBYYBJ){
        this.WBYYBJ=WBYYBJ;
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
     * get 审核人ID value
     * @return the AUDITER
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getAUDITER(){
        return AUDITER;
    }
	/**
     * set 审核人ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setAUDITER(String AUDITER){
        this.AUDITER=AUDITER;
    }
     /**
     * get 审核人姓名 value
     * @return the AUDITNAME
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getAUDITNAME(){
        return AUDITNAME;
    }
	/**
     * set 审核人姓名 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setAUDITNAME(String AUDITNAME){
        this.AUDITNAME=AUDITNAME;
    }
     /**
     * get 审核时间 value
     * @return the AUDITTIME
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getAUDITTIME(){
        return AUDITTIME;
    }
	/**
     * set 审核时间 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setAUDITTIME(String AUDITTIME){
        this.AUDITTIME=AUDITTIME;
    }
     /**
     * get 已处理通知不合格数量 value
     * @return the YTZBHGSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getYTZBHGSL(){
        return YTZBHGSL;
    }
	/**
     * set 已处理通知不合格数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setYTZBHGSL(String YTZBHGSL){
        this.YTZBHGSL=YTZBHGSL;
    }
     /**
     * get 可处理通知不合格数量 value
     * @return the KTZBHGSL
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getKTZBHGSL(){
        return KTZBHGSL;
    }
	/**
     * set 可处理通知不合格数量 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setKTZBHGSL(String KTZBHGSL){
        this.KTZBHGSL=KTZBHGSL;
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
     * get 入库序号 value
     * @return the RKXH
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getRKXH(){
        return RKXH;
    }
	/**
     * set 入库序号 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setRKXH(String RKXH){
        this.RKXH=RKXH;
    }
     /**
     * get  value
     * @return the CRENAME
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCRENAME(){
        return CRENAME;
    }
	/**
     * set  value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCRENAME(String CRENAME){
        this.CRENAME=CRENAME;
    }
     /**
     * get  value
     * @return the CREATER
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCREATER(){
        return CREATER;
    }
	/**
     * set  value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCREATER(String CREATER){
        this.CREATER=CREATER;
    }
     /**
     * get 制单时间 value
     * @return the CRETIME
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getCRETIME(){
        return CRETIME;
    }
	/**
     * set 制单时间 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setCRETIME(String CRETIME){
        this.CRETIME=CRETIME;
    }
     /**
     * get 更新人姓名 value
     * @return the UPDNAME
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getUPDNAME(){
        return UPDNAME;
    }
	/**
     * set 更新人姓名 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setUPDNAME(String UPDNAME){
        this.UPDNAME=UPDNAME;
    }
     /**
     * get  value
     * @return the UPDATER
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getUPDATER(){
        return UPDATER;
    }
	/**
     * set  value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setUPDATER(String UPDATER){
        this.UPDATER=UPDATER;
    }
     /**
     * get  value
     * @return the UPDTIME
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getUPDTIME(){
        return UPDTIME;
    }
	/**
     * set  value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setUPDTIME(String UPDTIME){
        this.UPDTIME=UPDTIME;
    }
     /**
     * get 部门信息ID value
     * @return the BMXXID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getBMXXID(){
        return BMXXID;
    }
	/**
     * set 部门信息ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setBMXXID(String BMXXID){
        this.BMXXID=BMXXID;
    }
     /**
     * get 部门名称 value
     * @return the BMMC
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getBMMC(){
        return BMMC;
    }
	/**
     * set 部门名称 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setBMMC(String BMMC){
        this.BMMC=BMMC;
    }
     /**
     * get 机构信息ID value
     * @return the JGXXID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJGXXID(){
        return JGXXID;
    }
	/**
     * set 机构信息ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJGXXID(String JGXXID){
        this.JGXXID=JGXXID;
    }
     /**
     * get 机构名称 value
     * @return the JGMC
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getJGMC(){
        return JGMC;
    }
	/**
     * set 机构名称 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setJGMC(String JGMC){
        this.JGMC=JGMC;
    }
     /**
     * get 帐套信息ID value
     * @return the ZTXXID
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getZTXXID(){
        return ZTXXID;
    }
	/**
     * set 帐套信息ID value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setZTXXID(String ZTXXID){
        this.ZTXXID=ZTXXID;
    }
     /**
     * get 状态 value
     * @return the STATE
	 * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public String getSTATE(){
        return STATE;
    }
	/**
     * set 状态 value
     * @createdate 2013-05-15 10:35:31
     * @author zhuxw
	 * @createdate 2013-05-15 10:35:31
     */
    public void setSTATE(String STATE){
        this.STATE=STATE;
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