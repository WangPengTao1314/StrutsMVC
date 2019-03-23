package com.hoperun.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.util.TimeComm;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.model.ZTWHModel;

// TODO: Auto-generated Javadoc
/**
 * The Class SysLoginService.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class SysLoginService extends BaseService {
	
	/** The logger. */
	private Logger logger = Logger.getLogger(SysLoginService.class);
	
	/**
	 * Inits the qx comm.
	 * 
	 * @param YHM the yHM
	 * @param KL the kL
	 * @param ZTXXID the zTXXID
	 * @param loginType the login type
	 * 
	 * @return the user bean
	 * 
	 * @throws Exception the exception
	 */
	public UserBean initQXComm(String YHM, String KL, String ZTXXID, boolean loginType) throws Exception {
		System.err.println("start init qx Comm");
		//SecurityBean aSecurityBean = new SecurityBean();
		if (loginType) {
			//KL = aSecurityBean.addEncrypt(KL);
		}
		System.err.println("============================ Start Time================================");
		//这里是设置系统最大访问数量的
//		GetNowDate aGetNowDate = new GetNowDate();
//		if (aGetNowDate.returnNum() != 0) {
//			throw new Exception(
//				"\u65E0\u6CD5\u9A8C\u8BC1\u5408\u6CD5\u7684\u7528\u6237\u6570! \u53EF\u80FD\u7684" + "\u539F\u56E0\uFF1A\uFF081\uFF09\u65E0\u6CD5\u542F\u52A8\u6570\u636E\u5E93\u670D\u52A1" + " \uFF082\uFF09\u6388\u6743\u6587\u4EF6\u4E22\u5931");
//		}
 
			StringBuffer aSQL = new StringBuffer("");
			Map params = new HashMap();
			aSQL.append("select YHKL,XTYHID,ZTXXID,IS_DRP_LEDGER,RYJB ,PORTAL_URL,IS_FG_ALL_CHANN from ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".V_XTYH_JGXX_BMXX_RYXX where YHM='");
			aSQL.append(YHM);
			aSQL.append("'");
			//系统用户是否跨帐套
			//aSQL.append(" and ZTXXID='");
			//aSQL.append(ZTXXID);
			//aSQL.append("'");
			aSQL.append("AND YHZT='\u542f\u7528' ");
			params.put("SelSQL", aSQL.toString());
			Map resMap = selcom(params);
			if(resMap==null||resMap.get("XTYHID")==null||resMap.get("XTYHID").equals(""))
			{
				UserBean userbean = null;
				return userbean;
			}
			String XTYHID = resMap.get("XTYHID").toString();
			String YHKL =  resMap.get("YHKL")==null?"":resMap.get("YHKL").toString();
			System.err.println( "YHKL=" + YHKL);
			System.err.println( "KL=" + KL);
			if (!YHKL.equals(KL)) {
				UserBean userbean = null;
				return userbean;
			}
		
		params.clear();
		params.put("XTYHID", XTYHID);
		Map XTYHMap =  selxtyh(params);
		List XTJSMap = selxtyhjs(params);
		List GZZXXBean = selczzcy(params);
		
  	    UserBean aUserBean = new UserBean(XTYHMap, XTJSMap, GZZXXBean);
  	    //首页URL
  	    aUserBean.setPORTAL_URL(StringUtil.nullToSring(resMap.get("PORTAL_URL")));
  	    //终端店长或者导购员
  	    if("门店_导购员".equals(resMap.get("RYJB")))
  	    {
  	    	aUserBean.setEMPY_FLAG("0");
  	    }else if("门店_店长".equals(resMap.get("RYJB")))
  	    {
  	    	aUserBean.setEMPY_FLAG("1");
  	    }
  	   aUserBean.setCHANNS_CHARG(StringUtil.nullToSring(resMap.get("IS_FG_ALL_CHANN")));
// 	    if (isQXNew(aUserBean.getXTYHID())) {
//			try {
//				refreshQXIDS(aUserBean, "JGXXIDS");
//				refreshQXIDS(aUserBean, "BMXXIDS");
//			} catch (Exception ex) {
//				logger.error(ex);
//				throw new Exception("\u7CFB\u7EDF\u4E2DT_SYS_USERQX\u8868\u7684qxsql\u5B57\u6BB5\u7684\u957F\u5EA6\u9002\u5F53" + "\u653E\u5927\u3002\u63A8\u8350\u503C1000-2000\uFF01");
//			}
//		}
  	    aUserBean.setYHLB(StringUtil.nullToSring(resMap.get("IS_DRP_LEDGER"))); 
		aUserBean.setFGZTXXIDS(getAllZTXXIDS(aUserBean.getYHM(), "'"));
		return aUserBean;
	}
	
	/**
	 * 根据ip地址查询用户信息
	 * @param ipAddr
	 * @return
	 */
	public Map queryUserInfoForAD(String ipAddr){
		
		if(StringUtil.isEmpty(ipAddr)){
			return null;
		}
		
		String sql = " select YHM,YHKL from T_SYS_XTYH where RMT_USER_IP = '" + ipAddr + "'";
		
		Map params = new HashMap();
		params.put("SelSQL", sql);
		return selcom(params);
	}
	

	
	/**
	 * Gets the all ztxxids.
	 * 
	 * @param YHM the yHM
	 * @param sperFlag the sper flag
	 * 
	 * @return the all ztxxids
	 */
	public String getAllZTXXIDS(String YHM, String sperFlag) {
		
//		DBBaseBean aDB = new DBBaseBean();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		if (sperFlag == null) {
//			sperFlag = "";
//		}
//		try {
//			try {
//				conn = aDB.getConn();
//				pstmt = aDB.getPstmt(conn, "select ZTXXID from T_SYS_XTYH where YHM='" + YHM + "'");
//				rs = aDB.pstmtQuery(pstmt);
//				String temp = sperFlag;
//				for (int i = 0; rs.next(); i++) {
//					if (i > 0) {
//						temp = temp + "," + rs.getString(1);
//					} else {
//						temp = rs.getString(1);
//					}
//				}
//
//				if (!sperFlag.equals("")) {
//					StringTokenizer strToken1 = new StringTokenizer(temp, ",", false);
//					String tmpStr1 = "";
//					String tmpStr2 = "";
//					for (int ii = 0; strToken1.hasMoreTokens(); ii++) {
//						tmpStr1 = strToken1.nextToken();
//						if (ii > 0) {
//							tmpStr2 = tmpStr2 + "," + sperFlag + tmpStr1 + sperFlag;
//						} else {
//							tmpStr2 = sperFlag + tmpStr1 + sperFlag;
//						}
//					}
//
//					temp = tmpStr2;
//				}
//				String s = temp;
//				return s;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			return "''";
	}
	
	/**
	 * 加载系统所有菜单.
	 * 
	 * @return the list
	 */
	public List findAllMenus() {
		return this.findList("MenuInfo.findAll", null);
	} 

	/**
	 * 加载用户菜单.
	 * 
	 * @param XTYHID the xTYHID
	 * 
	 * @return the list
	 */
	public List findMenuByUserId(String XTYHID,String IS_DRP_LEDGER) {
		Map <String,String> params = new HashMap();
		params.put("userId", XTYHID);
		if("1".equals(IS_DRP_LEDGER))
		{
			return this.findList("MenuInfo.findByUserIdDrp", params);
		}else
		{
			return this.findList("MenuInfo.findByUserIdErp", params);
		}
		
		
	}

	/**
	 * 修改密码.
	 * 
	 * @param user the user
	 * 
	 * @return true, if update pwd
	 */
	public boolean updatePwd(Map user) {
		return this.update("XTYH.updatePassword", user);
	}
	
	
	/**
	 * Do log.
	 * 
	 * @param aUserBean the a user bean
	 * @param fromIP the from ip
	 */
	public void doLog(UserBean aUserBean,String fromIP) 
	{
	StringBuffer aSQL = new StringBuffer();
	try {
		Map params = new HashMap();
        aSQL.append("insert into ");
		aSQL.append(Consts.SYSSCHEMA);
		aSQL.append(".T_SYS_DLRZ(DLRZID,XTYHID,DLSJ,YHIP) values(");
		aSQL.append("'");
		aSQL.append(TimeComm.getTimeStamp("DLRZ"));
		aSQL.append("','");
		aSQL.append(aUserBean.getXTYHID());
		aSQL.append("','");
		aSQL.append(TimeComm.getNYRDate() + " "+ TimeComm.getSFMDate());
		aSQL.append("','");
		aSQL.append(fromIP);
		aSQL.append("')");
		params.put("InsSQL", aSQL.toString());
		inscom(params);
	} catch (Exception ex) {
		System.err
				.println("\u5199\u5165\u767B\u5F55\u65E5\u5FD7\u51FA\u9519:"
						+ ex.toString());
	}
	
	}
	
	/**
	 * Do Action log.
	 * 
	 * @param aUserBean the a user bean
	 * @param fromIP the from ip
	 */
	public void doActLog(String UC_NAME,String ACT_NAME,String OPRATOR,String STATE,String REMARK,String APPCODE,String APPID,String OPRCODE,String KEY) 
	{
	StringBuffer aSQL = new StringBuffer();
	try {
		Map params = new HashMap();
        aSQL.append("insert into ");
		aSQL.append(Consts.SYSSCHEMA);
		aSQL.append(".T_SYS_SYSLOG(SYSLOG_ID,UC_NAME,ACT_NAME,OPRATOR,ACT_TIME,STATE,REMARK,APPCODE,APPID,OPRCODE,KEY) values(");
		aSQL.append("'");
		aSQL.append(StringUtil.uuid32len());
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(UC_NAME));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(ACT_NAME));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(OPRATOR));
		aSQL.append("','");
		aSQL.append(DateUtil.newDataTime());
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(STATE));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(REMARK).replaceAll("'", ""));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(APPCODE));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(APPID));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(OPRCODE));
		aSQL.append("','");
		aSQL.append(StringUtil.nullToSring(KEY));
		aSQL.append("')");
		params.put("InsSQL", aSQL.toString());
		System.err.println("insertSQL==="+aSQL.toString());
		inscom(params);
	} catch (Exception ex) {
		System.err
				.println("\u5199\u5165\u767B\u5F55\u65E5\u5FD7\u51FA\u9519:"
						+ ex.toString());
	}
	
	}	
	/**
	 * Gets the user qxinfo.
	 * 
	 * @param aUserBean the a user bean
	 * 
	 * @return the user qxinfo
	 */
	public Map getUserQXINFO(UserBean aUserBean) 
	{
		Map resMap= new HashMap();
		Map params = new HashMap();
		StringBuffer aSQL = new StringBuffer();
		aSQL.append("select XTMKID from ");
		aSQL.append(Consts.SYSSCHEMA);
		aSQL.append(".T_SYS_YHQX where XTYHID=");
		aSQL.append("'");
		aSQL.append(aUserBean.getXTYHID());
		aSQL.append("'");
		params.put("SelSQL", aSQL.toString());
		List resList = selcomList(params);
		int maxnum = resList.size();
		for (int i = 0; i < maxnum; i++) {
			Map tempMap = (Map) resList.get(i);
			resMap.put(tempMap.get("XTMKID").toString(),1);
		}
		
		aSQL.delete(0, aSQL.length());
		aSQL.append("select XTMKID from ");
		aSQL.append(Consts.SYSSCHEMA);
		aSQL.append(".T_SYS_JSQX where  XTJSID in (");
		aSQL.append(aUserBean.getXTJSIDS());
		aSQL.append(")");
		params.put("SelSQL", aSQL.toString());
		
		resList = selcomList(params);
		maxnum = resList.size();
			for (int i = 0; i < maxnum; i++) {
				Map tempMap = (Map) resList.get(i);
				resMap.put(tempMap.get("XTMKID").toString().trim(),1);
		}
		
		    aSQL.delete(0, aSQL.length());
		    aSQL.append("select XTMKID from ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZQX where  GZZXXID in (");
			aSQL.append(aUserBean.getGZZXXIDS());
			aSQL.append(")");
			params.put("SelSQL", aSQL.toString());
			resList = selcomList(params);
			maxnum = resList.size();
				for (int i = 0; i < maxnum; i++) {
					Map tempMap = (Map) resList.get(i);
					resMap.put(tempMap.get("XTMKID").toString().trim(),1);
			}
		
		  if("administrator".equals(aUserBean.getYHM()))
		  {
			  aSQL.delete(0, aSQL.length());
			  aSQL.append(" select XTMKID from T_SYS_XTMK ");
			  params.put("SelSQL", aSQL.toString());
				resList = selcomList(params);
				maxnum = resList.size();
					for (int i = 0; i < maxnum; i++) {
						Map tempMap = (Map) resList.get(i);
						resMap.put(tempMap.get("XTMKID").toString().trim(),1);
				}
		  }
		  return  resMap; 
	}
	
	
	
	//组织权限级别
	/**
	 * Gets the user qxjb.
	 * 
	 * @param aUserBean the a user bean
	 * 
	 * @return the user qxjb
	 */
	public Map<String,String> getUserQXJB(UserBean aUserBean) 
	{
		Map<String,String> resMap= new HashMap<String,String>();
		Map<String,String> params= new HashMap<String,String>();
		StringBuffer aSQL = new StringBuffer();
		if (Consts.DBTYPE.equals("DB2")) {
			aSQL.append(" select  XTMKID,QXCODE  ");
			aSQL.append(" from (  ");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_YHQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTMKQXJB b on substr(a.XTMKID, 1, length(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID where a.XTYHID=");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" union all");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from  T_SYS_XTJS js,");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_JSQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);	 
			aSQL.append(".T_SYS_XTMKQXJB b on substr(a.XTMKID, 1,length(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID  left join  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTYHJS c on a.XTJSID=c.XTJSID  and c.DELFLAG=0  where js.XTJSID=a.XTJSID and js.DELFLAG=0 and js.STATE='启用' and c.XTYHID= ");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" union all");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from "+Consts.SYSSCHEMA+".T_SYS_GZZXX gzz ,");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTMKQXJB b on substr(a.XTMKID, 1, length(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID  left join  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZCY c on a.GZZXXID=c.GZZXXID and c.DELFLAG=0 where gzz.GZZXXID=a.GZZXXID  and gzz.DELFLAg=0 and gzz.GZZZT='启用' and c.XTYHID= ");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" ) temp  order by XTMKID ASC,QXJBID DESC ");
			
		} else if (Consts.DBTYPE.equals("ORACLE")) {
			aSQL.append(" select  XTMKID,QXCODE  ");
			aSQL.append(" from (  ");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_YHQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTMKQXJB b on substr(a.XTMKID, 1, length(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID where a.XTYHID=");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" union all");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from  T_SYS_XTJS js,");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_JSQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);	 
			aSQL.append(".T_SYS_XTMKQXJB b on substr(a.XTMKID, 1,length(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID  left join  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTYHJS c on a.XTJSID=c.XTJSID  and c.DELFLAG=0  where js.XTJSID=a.XTJSID and js.DELFLAG=0 and js.STATE='启用' and c.XTYHID= ");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" union all");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from "+Consts.SYSSCHEMA+".T_SYS_GZZXX gzz ,");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTMKQXJB b on substr(a.XTMKID, 1, length(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID  left join  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZCY c on a.GZZXXID=c.GZZXXID and c.DELFLAG=0 where gzz.GZZXXID=a.GZZXXID  and gzz.DELFLAg=0 and gzz.GZZZT='启用' and c.XTYHID= ");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" ) temp  order by XTMKID ASC,QXJBID DESC ");
			
		}else if (Consts.DBTYPE.equals("MSSQL")){
			aSQL.append(" select  XTMKID,QXCODE  ");
			aSQL.append(" from (  ");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_YHQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTMKQXJB b on substring(a.XTMKID, 1, datalength(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID where a.XTYHID=");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" union all");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from  T_SYS_XTJS js,");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_JSQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);	 
			aSQL.append(".T_SYS_XTMKQXJB b on substring(a.XTMKID, 1, datalength(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID  left join  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTYHJS c on a.XTJSID=c.XTJSID  and c.DELFLAG=0  where js.XTJSID=a.XTJSID and js.DELFLAG=0 and js.STATE='启用' and c.XTYHID= ");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" union all");
			aSQL.append(" select a.XTMKID,QXCODE,a.QXJBID from "+Consts.SYSSCHEMA+".T_SYS_GZZXX gzz ,");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZQX a left join ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_XTMKQXJB b on substring(a.XTMKID, 1, datalength(b.XTMKID))=b.XTMKID and a.QXJBID=b.QXJBID  left join  ");
			aSQL.append(Consts.SYSSCHEMA);
			aSQL.append(".T_SYS_GZZCY c on a.GZZXXID=c.GZZXXID and c.DELFLAG=0 where gzz.GZZXXID=a.GZZXXID  and gzz.DELFLAg=0 and gzz.GZZZT='启用' and c.XTYHID= ");
			aSQL.append("'");
			aSQL.append(aUserBean.getXTYHID());
			aSQL.append("'");
			aSQL.append(" ) temp  order by XTMKID ASC,QXJBID DESC "); 
		}
		
		params.put("SelSQL", aSQL.toString());
		 if("administrator".equals(aUserBean.getYHM()))
		  {
			  aSQL.delete(0, aSQL.length());
			  aSQL.append(" select XTMKID,QXCODE from T_SYS_XTMKQXJB b where QXJBID='2' ");
		  }
		List<Map<String,String>> resList = selcomList(params);
		int maxnum = resList.size();
		for (int i = 0; i < maxnum; i++) {
			Map<String,String> tempMap = (Map<String,String>) resList.get(i);
			String QXCODE=tranCodeN(tempMap.get("QXCODE"));
			//写出翻译代码
			if(QXCODE.indexOf("#XTYHID#")!=-1)
			{
				QXCODE=QXCODE.replaceAll("#XTYHID#","'"+aUserBean.getXTYHID()+"'");
			}
			if(QXCODE.indexOf("#JGXXID#")!=-1)
			{
				QXCODE=QXCODE.replaceAll("#JGXXID#","'"+aUserBean.getJGXXID()+"'");
			}
			if(QXCODE.indexOf("#BMXXID#")!=-1)
			{
				QXCODE=QXCODE.replaceAll("#BMXXID#","'"+aUserBean.getBMXXID()+"'");
			}
			//机构集中
			if(QXCODE.indexOf("#JGXXIDS#")!=-1)
			{
				QXCODE=QXCODE.replaceAll("#JGXXIDS#"," select JGXXID from T_SYS_JGXX start with JGXXID = '"+aUserBean.getJGXXID()+"' connect by prior JGXXID = SJJG ");
			}
			//多机构
			if(QXCODE.indexOf("#DJGXXIDS#")!=-1)
			{
				//这里要做人员分管机构模块才行
				//QXCODE=QXCODE.replaceAll("#DJGXXIDS#"," select JGXXID from T_SYS_JGXX start with  JGXXID in (select distinct  JGXXID  from T_SYS_XTYHJGFG where XTYHID='"+aUserBean.getXTYHID()+"')  connect by prior JGXXID = SJJG ");
				QXCODE=QXCODE.replaceAll("#DJGXXIDS#"," select   JGXXID  from T_SYS_XTYHJGFG where XTYHID='"+aUserBean.getXTYHID()+"' ");
			}
			//部门集中
			if(QXCODE.indexOf("#BMXXIDS#")!=-1)
			{
				QXCODE=QXCODE.replaceAll("#BMXXIDS#"," select BMXXID from T_SYS_BMXX start with BMXXID = '"+aUserBean.getBMXXID()+"' connect by prior BMXXID = SJBM ");
			}
			//多部门
			if(QXCODE.indexOf("#DBMXXIDS#")!=-1)
			{
				//这里要做人员分管部门模块才行
				//QXCODE=QXCODE.replaceAll("#DBMXXIDS#"," select BMXXID from T_SYS_BMXX start with  BMXXID in (select distinct  BMXXID  from T_SYS_XTYHBMFG where XTYHID='"+aUserBean.getXTYHID()+"')  connect by prior BMXXID = SJBM ");
				QXCODE=QXCODE.replaceAll("#DBMXXIDS#"," select distinct  BMXXID  from T_SYS_XTYHBMFG where XTYHID='"+aUserBean.getXTYHID()+"' ");
			}
			if("".equals("QXCODE"))
			{
				QXCODE="1<>1";
			}
			resMap.put(tranCodeN(tempMap.get("XTMKID")),QXCODE);
		}
		
		return  resMap;
	}
	
	/**
	 * Tran code p.
	 * 
	 * @param Str the str
	 * 
	 * @return the string
	 */
	public String tranCodeP(String Str) {

		return Str == null ? "" : Str;
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
	
	//获得采购组对象
	/**
	 * Gets the xSW model.
	 * 
	 * @return the xSW model
	 */
	public List getXSWModel() 
	{
	  Map params = new HashMap();
	  StringBuffer aSQL = new StringBuffer();
	  aSQL.append(" select NUMTYPE,NUMFORMATMC,DECIMALS,ROUNDTYPE   from  ");
	  aSQL.append(Consts.SYSSCHEMA);
	  aSQL.append(".T_SYS_NUMFORMAT where DELFLAG=0 and STATE='"+BusinessConsts.JC_STATE_ENABLE+"'");
	  params.put("SelSQL", aSQL.toString());
	  List resList = selcomList(params);
	  return resList;
	}
	public Map<String,String> getIS_DRP_LEDGER(String ZTXXID)
	{
		  Map resMap= new HashMap();
		  Map params = new HashMap();
		  StringBuffer aSQL = new StringBuffer();
		  aSQL.append(" select IS_DRP_LEDGER from  ");
		  aSQL.append(" T_SYS_ZTXX where ZTXXID='");
		  aSQL.append(ZTXXID);
		  aSQL.append("'");
		  params.put("SelSQL", aSQL.toString());
		  resMap = selcom(params);
		  return resMap;
		
	}
	public Map<String,String> getBaseChann()
	{
		  Map resMap= new HashMap();
		  Map params = new HashMap();
		  StringBuffer aSQL = new StringBuffer();
		  aSQL.append(" select CHANN_ID,CHANN_NO,CHANN_NAME   from  ");
		  aSQL.append(" BASE_CHANN where IS_BASE_FLAG=1");
		  params.put("SelSQL", aSQL.toString());
		  resMap = selcom(params);
		  return resMap;
		
	}
	public Map<String,String> getcurrChann(String chann_Id)
	{
		  Map<String,String> resMap= new HashMap<String,String>();
		  Map<String,String> params = new HashMap<String,String>();
		  StringBuffer aSQL = new StringBuffer();
		  aSQL.append(" select CHANN_ID,CHANN_NO,CHANN_NAME,AREA_SER_ID,AREA_SER_NO,AREA_SER_NAME,CHANN_TYPE  from  ");
		  aSQL.append(" BASE_CHANN where CHANN_ID='"+chann_Id+"'");
		  params.put("SelSQL", aSQL.toString());
		  resMap = selcom(params);
		  return resMap;
		
	}
	
	public Map<String,String> getCurrTrem(String dept_No)
	{
		  Map<String,String> resMap= new HashMap<String,String>();
		  Map<String,String> params = new HashMap<String,String>();
		  StringBuffer aSQL = new StringBuffer();
		  aSQL.append(" select  TERM_ID,TERM_NO,TERM_NAME from BASE_TERMINAL where TERM_NO='");
		  aSQL.append(dept_No);
		  aSQL.append("'");
		  params.put("SelSQL", aSQL.toString());
		  resMap = selcom(params);
		  return resMap;
		
	}
	
	
	//根据用户名和密码获得用户相关信息
	/**
	 * Gets the xSW model.
	 * 
	 * @return the xSW model
	 */
	public  Map<String,String> getUserInfoByUserIdAndPd(String userId,String pwd) 
	{
		  Map<String,String> resMap= new HashMap<String,String>();
		  Map<String,String> params = new HashMap<String,String>();
		  StringBuffer aSQL = new StringBuffer();
		  
		  aSQL.append(" select c.ZTXXID,c.ZTMC ");
		  aSQL.append(" from T_SYS_XTYH a ");
		  aSQL.append(" left join T_SYS_BMXX b  on a.BMXXID=b.BMXXID ");
		  aSQL.append(" left join T_SYS_ZTXX c on b.ZTXXID=c.ZTXXID ");
		  aSQL.append(" where ");
		  aSQL.append("      a.YHM='"+userId+"' ");
		  aSQL.append(" and  a.YHKL='"+pwd+"' ");
		  params.put("SelSQL", aSQL.toString());
		  resMap = selcom(params);
		  return resMap;
	}
	//通用增删改查
	/**
	 * Selcom.
	 * 
	 * @param params the params
	 * 
	 * @return the map
	 */
	public Map selcom(Map params) {
		return (Map)load("sqlcom.query",params);
	}
	
	/**
	 * Selcom list.
	 * 
	 * @param params the params
	 * 
	 * @return the list
	 */
	public List selcomList(Map params) {
		return this.findList("sqlcom.query", params);
	}
	
	/**
	 * Delcom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean delcom(Map params) {
		return delete("sqlcom.delete", params);
	}
	
	/**
	 * Updcom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean updcom(Map params) {
		return update("sqlcom.update", params);
	}
	
	/**
	 * Inscom.
	 * 
	 * @param params the params
	 * 
	 * @return true, if successful
	 */
	public boolean inscom(Map params) {
		insert("sqlcom.insert", params);
		return true;
	}
	
	/**
	 * Selxtyh.
	 * 
	 * @param params the params
	 * 
	 * @return the map
	 */
	public Map selxtyh(Map params) {
		return (Map)load("XTYH.query",params);
	}
	
	/**
	 * Selxtyhjs.
	 * 
	 * @param params the params
	 * 
	 * @return the list
	 */
	public List selxtyhjs(Map params) {
		return this.findList("XTYHJS.queryFORLOGIN", params);
	}
	
	/**
	 * Selczzcy.
	 * 
	 * @param params the params
	 * 
	 * @return the list
	 */
	public List selczzcy(Map params) {
		return this.findList("CZZCY.queryFORLOGIN", params);
	}
	
	/**
	 * Gets the ztxx.
	 * 
	 * @param userId the user id
	 * 
	 * @return the ztxx
	 */
	public List<ZTWHModel> getZtxx(String userId) {
		return this.findList("XTYH.getZtxx", userId);
	}
	
	/**
	 * Gets the notices.
	 * 
	 * @param loginZTXXID the login ztxxid
	 * 
	 * @return the notices
	 */
	public List<HashMap<String,String>> getNotices(String loginZTXXID) {
		return this.findList("XTYH.getNotices", loginZTXXID);
	}
}
