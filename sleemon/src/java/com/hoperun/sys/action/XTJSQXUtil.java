package com.hoperun.sys.action;
import java.util.Vector;

import com.hoperun.sys.model.XTJSQXBean;

// TODO: Auto-generated Javadoc
/**
 * The Class XTJSQXUtil.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class XTJSQXUtil {
	
	/**
	 * Instantiates a new xTJSQX util.
	 */
	public XTJSQXUtil() {
	}
	
	/**
	 * Check mkqx.
	 * 
	 * @param aXTJSQXBean the a xtjsqx bean
	 * @param XTMKID the xTMKID
	 * 
	 * @return true, if successful
	 */
	public boolean checkMKQX(XTJSQXBean aXTJSQXBean, String XTMKID) {
		XTMKID = "(" + XTMKID;
		return XTMKID.indexOf(aXTJSQXBean.toString()) != -1;
	}
	
	/**
	 * Creates the xtjsqx vector.
	 * 
	 * @param XTJSID the xTJSID
	 * 
	 * @return the vector
	 * 
	 * @throws Exception the exception
	 */
	public Vector createXTJSQXVector(String XTJSID) throws Exception {
		//dbAccess.selectSql("select T_SYS_XTMK.XTMKID AS XTMKID, MKBH, MKMC,T_SYS_JSQX.QXJBID AS QXJBID,JBMC from T_SYS_XTMK,T_SYS_JSQX,T_SYS_QXJB where XTJSID='" + XTJSID + "' AND (T_SYS_XTMK.XTMKID=T_SYS_JSQX.XTMKID AND T_SYS_QXJB.QXJBID=T_SYS_JSQX.QXJBID) with ur");
		Vector vTemp = new Vector();
//		XTMKQXBean aXTMKQXBean;
//		for (; dbAccess.rsNext(); vTemp.add(aXTMKQXBean)) {
//			aXTMKQXBean = new XTMKQXBean(dbAccess.getField("XTMKID"), dbAccess.getField("MKBH"), dbAccess.getField("MKMC"), dbAccess.getField("QXJBID"), dbAccess.getField("JBMC"));
//		}
//		dbAccess.closeDB();
		return vTemp;
	}
}
