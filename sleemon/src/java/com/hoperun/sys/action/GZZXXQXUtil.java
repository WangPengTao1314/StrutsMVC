package com.hoperun.sys.action;
import java.util.Vector;

import com.hoperun.sys.model.GZZXXQXBean;
// TODO: Auto-generated Javadoc

/**
 * The Class GZZXXQXUtil.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class GZZXXQXUtil {
	
	/**
	 * Instantiates a new gZZXXQX util.
	 */
	public GZZXXQXUtil() {
	}
	
	/**
	 * Check mkqx.
	 * 
	 * @param aGZZXXQXBean the a gzzxxqx bean
	 * @param XTMKID the xTMKID
	 * 
	 * @return true, if successful
	 */
	public boolean checkMKQX(GZZXXQXBean aGZZXXQXBean, String XTMKID) {
		XTMKID = "(" + XTMKID;
		return XTMKID.indexOf(aGZZXXQXBean.toString()) != -1;
	}
	
	/**
	 * Creates the gzzxxqx vector.
	 * 
	 * @param GZZXXID the gZZXXID
	 * 
	 * @return the vector
	 * 
	 * @throws Exception the exception
	 */
	public Vector createGZZXXQXVector(String GZZXXID) throws Exception {
//		SuperBean dbAccess = new SuperBean();
//		dbAccess.selectSql("select T_SYS_XTMK.XTMKID AS XTMKID, MKBH, MKMC,T_SYS_GZZQX.QXJBID AS QXJBID,JBMC from T_" + "XTMK,T_SYS_GZZQX,T_SYS_QXJB where GZZXXID='" + GZZXXID + "' AND (T_SYS_XTMK.XTMKID=T_SYS_GZZQX.XTMKID AND T_SYS_QXJB.QXJBID=T_SYS_GZZQX.QXJBID) with ur");
		Vector vTemp = new Vector();
//		XTMKQXBean aXTMKQXBean;
//		for (; dbAccess.rsNext(); vTemp.add(aXTMKQXBean)) {
//			aXTMKQXBean = new XTMKQXBean(dbAccess.getField("XTMKID"), dbAccess.getField("MKBH"), dbAccess.getField("MKMC"), dbAccess.getField("QXJBID"), dbAccess.getField("JBMC"));
//		}
//		dbAccess.closeDB();
		
		
		return vTemp;
	}
}