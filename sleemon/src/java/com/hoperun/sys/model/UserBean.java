package com.hoperun.sys.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
// TODO: Auto-generated Javadoc

/**
 * The Class UserBean.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class UserBean implements Serializable {
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -7502879521636129425L;
    
    /** The XTYH bean. */
    Map XTYHBean;//系统用户相关信息
	
	/** The v xtjs bean. */
	List vXTJSBean;//系统用户所属角色相关信息
	
	/** The v gzzxx bean. */
	List vGZZXXBean;//系统用户所属工作相关信息
	
	/** The JGXXIDS. */
	String JGXXIDS;//用户分管的机构信息ID，以逗号隔开
	
	/** The BMXXIDS. */
	String BMXXIDS;//用户分管的机构信息ID，以逗号隔开
	
	/** The DJGXXIDS. */
	String DJGXXIDS;//用户分管的机构信息ID，SQL条件格式
	
	/** The DBMXXIDS. */
	String DBMXXIDS;//用户分管的部门信息ID，SQL条件格式
    
    /** The FGZTXXIDS. */
    String FGZTXXIDS;//用户分管的帐套信息ID
	
	/** The JGZTMC. */
	String JGZTMC;//用户对应的机构帐套
	
	/** The BMZTMC. */
	String BMZTMC;//用户对应的部门帐套
	
	/** The real yhkl. */
	String realYHKL;//用户口令
	
	/** The DESYHKL. */
	String DESYHKL;//
	
	/** The JGJC. */
	String JGJC;//机构简称
	
	/** The BMJC. */
	String BMJC;//部门简称
	
	/** The BMBHS. */
	String BMBHS;//用户分管的部门信息编号，以逗号隔开
	
	/** The JGBHS. */
	String JGBHS;//用户分管的机构信息编号，以逗号隔开
	
	/** The DJGBHS. */
	String DJGBHS;//用户分管的机构信息编号，SQL条件格式
	
	/** The DBMBHS. */
	String DBMBHS;//用户分管的部门信息编号，SQL条件格式
	
	/** The Login ztxxid. */
	String LoginZTXXID;//当前登录帐套ID
	
	/** The Login ztmc. */
	String LoginZTMC;//当前登录帐套名称
	
	/** The USERSTYLE. */
	String USERSTYLE;//用户样式
	
	/** The YHLB. */
	String YHLB; //用户类别 
	
	/** The Constant ADMIN_XTYHID. */
	public static final String ADMIN_XTYHID = "XTYHADMIN_DEFAULT";
	
	/** The xsw model list. */
	List xswModelList;
	
	/** The QX map. */
	Map QXMap;
	
	/** The QXJB map. */
	Map<String,String> QXJBMap;
	//erp 常用业务分管条件Start
	/** The FGWLXXIDS. */
	String FGWLXXIDS;//用户分管的物料信息ID，SQL条件格式
	
	/** The FGWLXX con. */
	String FGWLXXCon;//用户分管的物料信息SQL条件
	
	/** The FGWLDWXXIDS. */
	String FGWLDWXXIDS;//用户分管的客商信息ID，SQL条件格式
	
	/** The FGKFXXID. */
	String FGKFXXID;//用户分管的库房信息ID，SQL条件格式
	
	/** The FGKWXXID. */
	String FGKWXXID;//用户分管的库位信息ID，SQL条件格式
	
	/** The XSZID. */
	String XSZID;//系统用户所属销售组ID
	
	/** The XSZMC. */
	String XSZMC;//系统用户所属销售组名称
	
	/** The CGZID. */
	String CGZID;//系统用户所属采购组ID
	
	/** The CGZMC. */
	String CGZMC;//系统用户所属采购组名称
	
	/** The KWFGXXIDS. */
	String KWFGXXIDS;
    
    /** The CGZSCON. */
    String STORE_CHARG;
    
    /** 喜临门项目：渠道ID. */ 
    String CHANN_ID;
    
    /** 喜临门项目：渠道编号. */ 
    String CHANN_NO;
    
    /** 喜临门项目：渠道名称. */ 
    String CHANN_NAME;
    //end 
    /** 喜临门项目：总部名称. */ 
    String BASE_CHANN_ID;
    
    /** 喜临门项目：总部编号. */ 
    String BASE_CHANN_NO;
    
    /** 喜临门项目：总部名称. */ 
    String BASE_CHANN_NAME;
    /** 喜临门项目：渠道分管. */ 
    String CHANNS_CHARG; 
    /** 喜临门项目：是否是分销帐套 */ 
    String  IS_DRP_LEDGER;
    
    /** 区域服务中心ID **/ 
    String AREA_SER_ID;
    
    /** 区域服务中心**/
    String AREA_SER_NO;
    
    /** 区域服务中心名称 **/
    String AREA_SER_NAME;
    /** 喜临门项目：区域分管. */ 
    String AREA_CHARG;
    
    /** 喜临门项目：终端ID. */ 
    String TERM_ID;
    
    /** 喜临门项目：终端编号. */ 
    String TERM_NO;
    
    /** 喜临门项目：终端名称. */ 
    String TERM_NAME;
    
    /** 喜临门项目：用户类型标记 0->门店_导购员;1->门店_店长*/ 
    String EMPY_FLAG;
    
    /** 喜临门项目：终端分管*/ 
    String TERM_CHARGE;
    
    /** 喜临门项目：渠道类型*/ 
    String CHANN_TYPE; 
    
    /** 当前报表URL*/ 
    String CURRT_RPT_URL;
    /** 当前图片URL*/
    String CURRT_PIC_URL;
    /** 是否当前月份已月结标记*/
    String IS_MONTH_ACC_FLAG;
    /**首页URL**/
    String PORTAL_URL;
    public String getYHLB() {
		return YHLB;
	}
	public void setYHLB(String yHLB) {
		YHLB = yHLB;
	}
    public String getPORTAL_URL() {
		return PORTAL_URL;
	}
	public void setPORTAL_URL(String pORTALURL) {
		PORTAL_URL = pORTALURL;
	}
	//用户分管的库房信息ID，SQL条件格式
	/**
     * Gets the kWFGXXIDS.
     * 
     * @return the kWFGXXIDS
     */
    public String getKWFGXXIDS() {
		return KWFGXXIDS;
	}
	//初始化Userbean
	/**
	 * Instantiates a new user bean.
	 * 
	 * @param aXTYHBean the a xtyh bean
	 * @param JSBeaVXTJSBeanan the jS bea vxtjs beanan
	 * @param aVGZZXXBean the a vgzzxx bean
	 */
	public UserBean(Map aXTYHBean, List JSBeaVXTJSBeanan, List aVGZZXXBean) {
		XTYHBean = aXTYHBean;
		if(XTYHBean==null)
			XTYHBean=new HashMap();
		vXTJSBean = JSBeaVXTJSBeanan;
		if(vXTJSBean==null)
			vXTJSBean=new ArrayList();
		vGZZXXBean = aVGZZXXBean;
		if(vGZZXXBean==null)
			vGZZXXBean=new ArrayList();
	}
	
	/**
	 * Sets the real yhkl.
	 * 
	 * @param YHKL the new real yhkl
	 */
	public void setRealYHKL(String YHKL) {
		realYHKL = YHKL;
	}
	//用户口令
	/**
	 * Gets the real yhkl.
	 * 
	 * @return the real yhkl
	 */
	public String getRealYHKL() {
		return realYHKL;
	}
	//用户对应的机构帐套
	/**
	 * Gets the jGZTXXID.
	 * 
	 * @return the jGZTXXID
	 */
	public String getJGZTXXID() {
		return XTYHBean.get("JGZTXXID")==null?"":XTYHBean.get("JGZTXXID").toString();
	}
	//用户对应的部门帐套
	/**
	 * Gets the bMZTXXID.
	 * 
	 * @return the bMZTXXID
	 */
	public String getBMZTXXID() {
		return XTYHBean.get("BMZTXXID")==null?"":XTYHBean.get("BMZTXXID").toString();
	}
	//机构名称
	/**
	 * Gets the jGMC.
	 * 
	 * @return the jGMC
	 */
	public String getJGMC() {
		return XTYHBean.get("JGMC")==null?"":XTYHBean.get("JGMC").toString();
	}
	//部门名称
	/**
	 * Gets the bMMC.
	 * 
	 * @return the bMMC
	 */
	public String getBMMC() {
		return XTYHBean.get("BMMC")==null?"":XTYHBean.get("BMMC").toString();
	}
	//用户对应角色ID，用逗号分开
	/**
	 * Gets the xTJSIDS.
	 * 
	 * @return the xTJSIDS
	 */
	public String getXTJSIDS() {
		StringBuffer tempStr = new StringBuffer("");
		int num = 0;
		for (int i = 0; i < vXTJSBean.size(); i++) {
			Map aXTJSBean = (Map) vXTJSBean.get(i);
			if (i > 0) {
				tempStr.append(",");
			}
			tempStr.append("'" + aXTJSBean.get("XTJSID") + "'");
			num++;
		}
		if (num == 0) {
			return " '' ";
		} else {
			return tempStr.toString();
		}
	}
	//用户对应角色编号，用逗号分开
	/**
	 * Gets the jSBHS.
	 * 
	 * @return the jSBHS
	 */
	public String getJSBHS() {
		StringBuffer tempStr = new StringBuffer("");
		int num = 0;
		for (int i = 0; i < vXTJSBean.size(); i++) {
			Map aXTJSBean = (Map) vXTJSBean.get(i);
			if (i > 0) {
				tempStr.append(",");
			}
			tempStr.append("'" + aXTJSBean.get("JSBH") + "'");
			num++;
		}
		if (num == 0) {
			return " '' ";
		} else {
			return tempStr.toString();
		}
	}
	//用户对应工作组ID，用逗号分开
	/**
	 * Gets the gZZXXIDS.
	 * 
	 * @return the gZZXXIDS
	 */
	public String getGZZXXIDS() {
		StringBuffer tempStr = new StringBuffer("");
		int num = 0;
		for (int i = 0; i < vGZZXXBean.size(); i++) {
			Map aGZZXXBean = (Map) vGZZXXBean.get(i);
			if (i > 0) {
				tempStr.append(",");
			}
			tempStr.append("'" + aGZZXXBean.get("GZZXXID") + "'");
			num++;
		}
		if (num == 0) {
			return " '' ";
		} else {
			return tempStr.toString();
		}
	}
	//用户对应工作组编号，用逗号分开
	/**
	 * Gets the gZZBHS.
	 * 
	 * @return the gZZBHS
	 */
	public String getGZZBHS() {
		StringBuffer tempStr = new StringBuffer("");
		int num = 0;
		for (int i = 0; i < vGZZXXBean.size(); i++) {
			Map aGZZXXBean = (Map) vGZZXXBean.get(i);
			if (i > 0) {
				tempStr.append(",");
			}
			tempStr.append("'" + aGZZXXBean.get("GZZBH") + "'");
			num++;
		}
		if (num == 0) {
			return " '' ";
		} else {
			return tempStr.toString();
		}
	}
	//用户ID
	/**
	 * Gets the xTYHID.
	 * 
	 * @return the xTYHID
	 */
	public String getXTYHID() {
		return XTYHBean.get("XTYHID")==null?"":XTYHBean.get("XTYHID").toString();
	}
	//用户名
	/**
	 * Gets the yHM.
	 * 
	 * @return the yHM
	 */
	public String getYHM() {
		return XTYHBean.get("YHM")==null?"":XTYHBean.get("YHM").toString();
	}
	//用户编号
	/**
	 * Gets the yHBH.
	 * 
	 * @return the yHBH
	 */
	public String getYHBH() {
		return XTYHBean.get("YHBH")==null?"":XTYHBean.get("YHBH").toString();
	}
	//用户对应人员信息ID
	/**
	 * Gets the rYXXID.
	 * 
	 * @return the rYXXID
	 */
	public String getRYXXID() {
		return XTYHBean.get("RYXXID")==null?"":XTYHBean.get("RYXXID").toString();
	}
	//用户对应人员信息编号
	/**
	 * Gets the rYBH.
	 * 
	 * @return the rYBH
	 */
	public String getRYBH() {
		return XTYHBean.get("RYBH")==null?"":XTYHBean.get("RYBH").toString();
	}
	//用户对应人员信息编号
	/**
	 * Gets the xM.
	 * 
	 * @return the xM
	 */
	public String getXM() {
		return XTYHBean.get("XM")==null?"":XTYHBean.get("XM").toString();
	}
	//用户对应机构信息ID
	/**
	 * Gets the jGXXID.
	 * 
	 * @return the jGXXID
	 */
	public String getJGXXID() {
		return XTYHBean.get("JGXXID")==null?"":XTYHBean.get("JGXXID").toString();
	}
	//用户对应机构编号
	/**
	 * Gets the jGBH.
	 * 
	 * @return the jGBH
	 */
	public String getJGBH() {
		return XTYHBean.get("JGBH")==null?"":XTYHBean.get("JGBH").toString();
	}
	//用户对应机构上级机构
	/**
	 * Gets the sJJG.
	 * 
	 * @return the sJJG
	 */
	public String getSJJG() {
		return XTYHBean.get("SJJG")==null?"":XTYHBean.get("SJJG").toString();
	}
	//用户对应部门编号
	/**
	 * Gets the bMBH.
	 * 
	 * @return the bMBH
	 */
	public String getBMBH() {
		return XTYHBean.get("BMBH")==null?"":XTYHBean.get("BMBH").toString();
	}
	//用户对应部门ID
	/**
	 * Gets the bMXXID.
	 * 
	 * @return the bMXXID
	 */
	public String getBMXXID() {
		return XTYHBean.get("BMXXID")==null?"":XTYHBean.get("BMXXID").toString();
	}
	
	/**
	 * Gets the sJBM.
	 * 
	 * @return the sJBM
	 */
	public String getSJBM() {
		return XTYHBean.get("SJBM")==null?"":XTYHBean.get("SJBM").toString();
	}
	//用户对应人员级别
	/**
	 * Gets the rYJB.
	 * 
	 * @return the rYJB
	 */
	public String getRYJB() {
		return XTYHBean.get("RYJB")==null?"":XTYHBean.get("RYJB").toString();
	}
	//用户分管的部门信息ID，SQL条件格式
	/**
	 * Gets the dBMXXIDS.
	 * 
	 * @return the dBMXXIDS
	 */
	public String getDBMXXIDS() {
		return DBMXXIDS;
	}
	//用户分管的机构信息ID，SQL条件格式
	/**
	 * Gets the dJGXXIDS.
	 * 
	 * @return the dJGXXIDS
	 */
	public String getDJGXXIDS() {
		return DJGXXIDS;
	}
	
	/**
	 * Sets the dBMXXIDS.
	 * 
	 * @param dBMXXIDS the new dBMXXIDS
	 */
	public void setDBMXXIDS(String dBMXXIDS) {
		DBMXXIDS = dBMXXIDS;
	}
	
	/**
	 * Sets the dJGXXIDS.
	 * 
	 * @param dJGXXIDS the new dJGXXIDS
	 */
	public void setDJGXXIDS(String dJGXXIDS) {
		DJGXXIDS = dJGXXIDS;
	}
	//系统用户所属采购组ID
	/**
	 * Gets the cGZID.
	 * 
	 * @return the cGZID
	 */
	public String getCGZID() {
		return CGZID;
	}
	//用户分管的客商信息ID，SQL条件格式
	/**
	 * Gets the fGWLDWXXIDS.
	 * 
	 * @return the fGWLDWXXIDS
	 */
	public String getFGWLDWXXIDS() {
		return FGWLDWXXIDS;
	}
	//用户分管的物料信息ID，SQL条件格式
	/**
	 * Gets the fGWLXXIDS.
	 * 
	 * @return the fGWLXXIDS
	 */
	public String getFGWLXXIDS() {
		return FGWLXXIDS;
	}
	//系统用户所属销售组ID
	/**
	 * Gets the xSZID.
	 * 
	 * @return the xSZID
	 */
	public String getXSZID() {
		return XSZID;
	}
	
	/**
	 * Sets the cGZID.
	 * 
	 * @param cGZID the new cGZID
	 */
	public void setCGZID(String cGZID) {
		CGZID = cGZID;
	}
	
	/**
	 * Sets the fGWLDWXXIDS.
	 * 
	 * @param fGWLDWXXIDS the new fGWLDWXXIDS
	 */
	public void setFGWLDWXXIDS(String fGWLDWXXIDS) {
		FGWLDWXXIDS = fGWLDWXXIDS;
	}
	
	/**
	 * Sets the fGWLXXIDS.
	 * 
	 * @param fGWLXXIDS the new fGWLXXIDS
	 */
	public void setFGWLXXIDS(String fGWLXXIDS) {
		FGWLXXIDS = fGWLXXIDS;
	}
	
	/**
	 * Sets the xSZID.
	 * 
	 * @param xSZID the new xSZID
	 */
	public void setXSZID(String xSZID) {
		XSZID = xSZID;
	}
	//系统用户所属采购组名称
	/**
	 * Gets the cGZMC.
	 * 
	 * @return the cGZMC
	 */
	public String getCGZMC() {
		return CGZMC;
	}
	//系统用户所属销售组名称
	/**
	 * Gets the xSZMC.
	 * 
	 * @return the xSZMC
	 */
	public String getXSZMC() {
		return XSZMC;
	}
	
	/**
	 * Sets the cGZMC.
	 * 
	 * @param cGZMC the new cGZMC
	 */
	public void setCGZMC(String cGZMC) {
		CGZMC = cGZMC;
	}
	
	/**
	 * Sets the xSZMC.
	 * 
	 * @param xSZMC the new xSZMC
	 */
	public void setXSZMC(String xSZMC) {
		XSZMC = xSZMC;
	}
	//用户分管的库房信息ID，SQL条件格式
	/**
	 * Gets the fGKFXXID.
	 * 
	 * @return the fGKFXXID
	 */
	public String getFGKFXXID() {
		return FGKFXXID;
	}
	
	/**
	 * Sets the fGKFXXID.
	 * 
	 * @param fGKFXXID the new fGKFXXID
	 */
	public void setFGKFXXID(String fGKFXXID) {
		FGKFXXID = fGKFXXID;
	}
	//用户分管的帐套信息ID
	/**
	 * Gets the fGZTXXIDS.
	 * 
	 * @return the fGZTXXIDS
	 */
	public String getFGZTXXIDS() {
		return FGZTXXIDS;
	}
	
	/**
	 * Sets the fGZTXXIDS.
	 * 
	 * @param fGZTXXIDS the new fGZTXXIDS
	 */
	public void setFGZTXXIDS(String fGZTXXIDS) {
		FGZTXXIDS = fGZTXXIDS;
	}
	//用户对应的部门帐套
	/**
	 * Gets the bMZTMC.
	 * 
	 * @return the bMZTMC
	 */
	public String getBMZTMC() {
		return BMZTMC;
	}
	//用户对应的机构帐套
	/**
	 * Gets the jGZTMC.
	 * 
	 * @return the jGZTMC
	 */
	public String getJGZTMC() {
		return JGZTMC;
	}
	
	/**
	 * Sets the bMZTMC.
	 * 
	 * @param bMZTMC the new bMZTMC
	 */
	public void setBMZTMC(String bMZTMC) {
		BMZTMC = bMZTMC;
	}
	
	/**
	 * Sets the jGZTMC.
	 * 
	 * @param jGZTMC the new jGZTMC
	 */
	public void setJGZTMC(String jGZTMC) {
		JGZTMC = jGZTMC;
	}
	//用户分管的部门信息ID，以逗号隔开
	/**
	 * Gets the bMXXIDS.
	 * 
	 * @return the bMXXIDS
	 */
	public String getBMXXIDS() {
		return BMXXIDS;
	}
	//户分管的机构信息ID，以逗号隔开
	/**
	 * Gets the jGXXIDS.
	 * 
	 * @return the jGXXIDS
	 */
	public String getJGXXIDS() {
		return JGXXIDS;
	}
	
	/**
	 * Sets the bMXXIDS.
	 * 
	 * @param bMXXIDS the new bMXXIDS
	 */
	public void setBMXXIDS(String bMXXIDS) {
		BMXXIDS = bMXXIDS;
	}
	
	/**
	 * Sets the jGXXIDS.
	 * 
	 * @param jGXXIDS the new jGXXIDS
	 */
	public void setJGXXIDS(String jGXXIDS) {
		JGXXIDS = jGXXIDS;
	}
	//用户分管的物料信息SQL条件
	/**
	 * Gets the fGWLXX con.
	 * 
	 * @return the fGWLXX con
	 */
	public String getFGWLXXCon() {
		return FGWLXXCon;
	}
	
	/**
	 * Sets the fGWLXX con.
	 * 
	 * @param fGWLXXCon the new fGWLXX con
	 */
	public void setFGWLXXCon(String fGWLXXCon) {
		FGWLXXCon = fGWLXXCon;
	}
	//部门简称
	/**
	 * Gets the bMJC.
	 * 
	 * @return the bMJC
	 */
	public String getBMJC() {
		return BMJC;
	}
	//机构简称
	/**
	 * Gets the jGJC.
	 * 
	 * @return the jGJC
	 */
	public String getJGJC() {
		return JGJC;
	}
	
	/**
	 * Sets the bMJC.
	 * 
	 * @param string the new bMJC
	 */
	public void setBMJC(String string) {
		BMJC = string;
	}
	
	/**
	 * Sets the jGJC.
	 * 
	 * @param string the new jGJC
	 */
	public void setJGJC(String string) {
		JGJC = string;
	}
	//用户分管的部门信息编号，以逗号隔开
	/**
	 * Gets the bMBHS.
	 * 
	 * @return the bMBHS
	 */
	public String getBMBHS() {
		return BMBHS;
	}
	//用户分管的部门信息编号，SQL条件格式
	/**
	 * Gets the dBMBHS.
	 * 
	 * @return the dBMBHS
	 */
	public String getDBMBHS() {
		return DBMBHS;
	}
	//用户分管的机构信息编号，SQL条件格式
	/**
	 * Gets the dJGBHS.
	 * 
	 * @return the dJGBHS
	 */
	public String getDJGBHS() {
		return DJGBHS;
	}
	//用户分管的机构信息编号，以逗号隔开
	/**
	 * Gets the jGBHS.
	 * 
	 * @return the jGBHS
	 */
	public String getJGBHS() {
		return JGBHS;
	}
	
	/**
	 * Sets the bMBHS.
	 * 
	 * @param string the new bMBHS
	 */
	public void setBMBHS(String string) {
		BMBHS = string;
	}
	
	/**
	 * Sets the dBMBHS.
	 * 
	 * @param string the new dBMBHS
	 */
	public void setDBMBHS(String string) {
		DBMBHS = string;
	}
	
	/**
	 * Sets the dJGBHS.
	 * 
	 * @param string the new dJGBHS
	 */
	public void setDJGBHS(String string) {
		DJGBHS = string;
	}
	
	/**
	 * Sets the jGBHS.
	 * 
	 * @param string the new jGBHS
	 */
	public void setJGBHS(String string) {
		JGBHS = string;
	}
	//当前登录帐套ID
	/**
	 * Gets the login ztxxid.
	 * 
	 * @return the login ztxxid
	 */
	public String getLoginZTXXID() {
		return LoginZTXXID;
	}
	
	/**
	 * Sets the login ztxxid.
	 * 
	 * @param string the new login ztxxid
	 */
	public void setLoginZTXXID(String string) {
		LoginZTXXID = string;
	}
	//当前登录帐套名称
	/**
	 * Gets the login ztmc.
	 * 
	 * @return the login ztmc
	 */
	public String getLoginZTMC() {
		return LoginZTMC;
	}
	
	/**
	 * Sets the login ztmc.
	 * 
	 * @param string the new login ztmc
	 */
	public void setLoginZTMC(String string) {
		LoginZTMC = string;
	}
	//权限CODE Map
	/**
	 * Gets the qX map.
	 * 
	 * @return the qX map
	 */
	public Map getQXMap() {
		return QXMap;
	}
	
	/**
	 * Sets the qX map.
	 * 
	 * @param map the new qX map
	 */
	public void setQXMap(Map map) {
		QXMap = map;
	}
	//用户样式
	/**
	 * Gets the uSERSTYLE.
	 * 
	 * @return the uSERSTYLE
	 */
	public String getUSERSTYLE() {
		return USERSTYLE;
	}
	
	/**
	 * Sets the uSERSTYLE.
	 * 
	 * @param userstyle the new uSERSTYLE
	 */
	public void setUSERSTYLE(String userstyle) {
		USERSTYLE = userstyle;
	}
	
	/**
	 * Sets the xsw model list.
	 * 
	 * @param xswModelList the new xsw model list
	 */
	public void setXswModelList(List xswModelList) {
		this.xswModelList = xswModelList;
	}
	//获得小数位设置对象
	/**
	 * Gets the xsw model.
	 * 
	 * @param NUMTYPE the nUMTYPE
	 * 
	 * @return the xsw model
	 */
	public XswModel getXswModel(String NUMTYPE) {
		XswModel axswModel =new XswModel();
		int maxlen=this.xswModelList.size();
		for(int i=0;i<maxlen;i++)
		{
			Map temp=(Map)xswModelList.get(i);
			if(NUMTYPE.equals(temp.get("NUMTYPE")))
			{
				axswModel.setROUNDTYPE(tranCodeN(temp.get("ROUNDTYPE")));
				axswModel.setDECIMALS(tranCodeN(temp.get("DECIMALS")));
				axswModel.setNUMTYPE(tranCodeN(temp.get("NUMTYPE")));
			}
			
		}
		return axswModel;
	}
	
	/**
	 * Tran code n.
	 * 
	 * @param Str the str
	 * 
	 * @return the string
	 */
	public String tranCodeN(Object Str) {

		return Str == null ? "" : Str.toString();
	}
	
	/**
	 * Gets the qXJB map.
	 * 
	 * @return the qXJB map
	 */
	public Map<String, String> getQXJBMap() {
		return QXJBMap;
	}
	
	/**
	 * Sets the qxjb map.
	 * 
	 * @param map the map
	 */
	public void setQXJBMap(Map<String, String> map) {
		QXJBMap = map;
	}
	
	
	//用户分管的库位信息ID，SQL条件格式
	/**
	 * Gets the fGKWXXID.
	 * 
	 * @return the fGKWXXID
	 */
	public String getFGKWXXID() {
		return FGKWXXID;
	}
	
	/**
	 * Sets the fGKWXXID.
	 * 
	 * @param fgkwxxid the new fGKWXXID
	 */
	public void setFGKWXXID(String fgkwxxid) {
		FGKWXXID = fgkwxxid;
	}
	
	/**
	 * Gets the cHAN n_ id.
	 * 
	 * @return the cHAN n_ id
	 */
	public String getCHANN_ID() {
		return CHANN_ID;
	}
	
	/**
	 * Sets the cHAN n_ id.
	 * 
	 * @param chann_id the new cHAN n_ id
	 */
	public void setCHANN_ID(String chann_id) {
		CHANN_ID = chann_id;
	}
	
	/**
	 * Gets the cHAN n_ no.
	 * 
	 * @return the cHAN n_ no
	 */
	public String getCHANN_NO() {
		return CHANN_NO;
	}
	
	/**
	 * Sets the cHAN n_ no.
	 * 
	 * @param chann_no the new cHAN n_ no
	 */
	public void setCHANN_NO(String chann_no) {
		CHANN_NO = chann_no;
	}
	
	/**
	 * Gets the cHAN n_ name.
	 * 
	 * @return the cHAN n_ name
	 */
	public String getCHANN_NAME() {
		return CHANN_NAME;
	}
	
	/**
	 * Sets the cHAN n_ name.
	 * 
	 * @param chann_name the new cHAN n_ name
	 */
	public void setCHANN_NAME(String chann_name) {
		CHANN_NAME = chann_name;
	}
	
	/**
	 * Gets the bAS 总部帐套
	 * 
	 * @return the bAS e_ chan n_ id
	 */
	public String getBASE_CHANN_ID() {
		return BASE_CHANN_ID;
	}
	
	/**
	 * Sets the bAS e_ chan n_ id.
	 * 
	 * @param base_chann_id the new bAS e_ chan n_ id
	 */
	public void setBASE_CHANN_ID(String base_chann_id) {
		BASE_CHANN_ID = base_chann_id;
	}
	
	/**
	 * Gets the bAS e_ chan n_ no.
	 * 
	 * @return the bAS e_ chan n_ no
	 */
	public String getBASE_CHANN_NO() {
		return BASE_CHANN_NO;
	}
	
	/**
	 * Sets the bAS e_ chan n_ no.
	 * 
	 * @param base_chann_no the new bAS e_ chan n_ no
	 */
	public void setBASE_CHANN_NO(String base_chann_no) {
		BASE_CHANN_NO = base_chann_no;
	}
	
	/**
	 * Gets the bAS e_ chan n_ name.
	 * 
	 * @return the bAS e_ chan n_ name
	 */
	public String getBASE_CHANN_NAME() {
		return BASE_CHANN_NAME;
	}
	
	/**
	 * Sets the bAS e_ chan n_ name.
	 * 
	 * @param base_chann_name the new bAS e_ chan n_ name
	 */
	public void setBASE_CHANN_NAME(String base_chann_name) {
		BASE_CHANN_NAME = base_chann_name;
	}
	public String getCHANNS_CHARG() {
		return CHANNS_CHARG;
	}
	public void setCHANNS_CHARG(String channs_charg) {
		CHANNS_CHARG = channs_charg;
	}
	public String getSTORE_CHARG() {
		STORE_CHARG=" 1=1 ";
		return STORE_CHARG;
	}
	public void setSTORE_CHARG(String store_charg) {
		STORE_CHARG = store_charg;
	}
	/** 喜临门项目：是否是分销帐套 */ 
	public String getIS_DRP_LEDGER() {
		return IS_DRP_LEDGER;
	}
	/** 喜临门项目：是否是分销帐套 */ 
	public void setIS_DRP_LEDGER(String is_drp_ledger) {
		IS_DRP_LEDGER = is_drp_ledger;
	}
	/** 区域服务中心ID **/ 
	public String getAREA_SER_ID() {
		return AREA_SER_ID;
	}
	public void setAREA_SER_ID(String aREASERID) {
		AREA_SER_ID = aREASERID;
	}
	  /** 区域服务中心编号 **/ 
	public String getAREA_SER_NO() {
		return AREA_SER_NO;
	}
	public void setAREA_SER_NO(String aREASERNO) {
		AREA_SER_NO = aREASERNO;
	}
	  /** 区域服务中心名称 **/ 
	public String getAREA_SER_NAME() {
		return AREA_SER_NAME;
	}
	public void setAREA_SER_NAME(String aREASERNAME) {
		AREA_SER_NAME = aREASERNAME;
	}
	public String getAREA_CHARG() {
		return AREA_CHARG;
	}
	public void setAREA_CHARG(String area_charg) {
		AREA_CHARG = area_charg;
	}
	public String getTERM_ID() {
		return TERM_ID;
	}
	public void setTERM_ID(String term_id) {
		TERM_ID = term_id;
	}
	public String getTERM_NO() {
		return TERM_NO;
	}
	public void setTERM_NO(String term_no) {
		TERM_NO = term_no;
	}
	public String getTERM_NAME() {
		return TERM_NAME;
	}
	public void setTERM_NAME(String term_name) {
		TERM_NAME = term_name;
	}
	 /** 喜临门项目：用户类型标记 0->门店_导购员;1->门店_店长*/ 
	public String getEMPY_FLAG() {
		return EMPY_FLAG;
	}
	public void setEMPY_FLAG(String empy_flag) {
		EMPY_FLAG = empy_flag;
	}
	public String getTERM_CHARGE() {
		return TERM_CHARGE;
	}
	public void setTERM_CHARGE(String term_charge) {
		TERM_CHARGE = term_charge;
	}
	public String getCHANN_TYPE() {
		return CHANN_TYPE;
	}
	public void setCHANN_TYPE(String chann_type) {
		CHANN_TYPE = chann_type;
	}
	public String getCURRT_RPT_URL() {
		return CURRT_RPT_URL;
	}
	public void setCURRT_RPT_URL(String currt_rpt_url) {
		CURRT_RPT_URL = currt_rpt_url;
	}
	public String getCURRT_PIC_URL() {
		return CURRT_PIC_URL;
	}
	public void setCURRT_PIC_URL(String currt_pic_url) {
		CURRT_PIC_URL = currt_pic_url;
	}
	public String getIS_MONTH_ACC_FLAG() {
		return IS_MONTH_ACC_FLAG;
	}
	public void setIS_MONTH_ACC_FLAG(String is_month_acc_flag) {
		IS_MONTH_ACC_FLAG = is_month_acc_flag;
	}
	
	
	
}
