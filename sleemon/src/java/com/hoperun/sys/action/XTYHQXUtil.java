package com.hoperun.sys.action;
import java.util.Vector;

import com.hoperun.sys.model.XTYHQXBean;

// TODO: Auto-generated Javadoc
/**
 * The Class XTYHQXUtil.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class XTYHQXUtil {
	
	/**
	 * Instantiates a new xTYHQX util.
	 */
	public XTYHQXUtil() {
	}
	
	/**
	 * Check mkqx.
	 * 
	 * @param aXTYHQXBean the a xtyhqx bean
	 * @param XTMKID the xTMKID
	 * 
	 * @return true, if successful
	 */
	public boolean checkMKQX(XTYHQXBean aXTYHQXBean, String XTMKID) {
		XTMKID = "(" + XTMKID;
		return XTMKID.indexOf(aXTYHQXBean.toString()) != -1;
	}
	
	/**
	 * Creates the xtyhqx vector.
	 * 
	 * @param XTYHID the xTYHID
	 * 
	 * @return the vector
	 * 
	 * @throws Exception the exception
	 */
	public Vector createXTYHQXVector(String XTYHID) throws Exception {
//		dbAccess.selectSql("select T_SYS_XTMK.XTMKID AS XTMKID, MKBH, MKMC,T_SYS_YHQX.QXJBID AS QXJBID,JBMC from T_X" + "TMK,T_SYS_YHQX,T_SYS_QXJB where XTYHID='" + XTYHID + "' AND (T_SYS_XTMK.XTMKID=T_SYS_YHQX.XTMKID AND T_SYS_QXJB.QXJBID=T_SYS_YHQX.QXJBID) with ur");
		Vector vTemp = new Vector();
//		XTMKQXBean aXTMKQXBean;
//		for (; dbAccess.rsNext(); vTemp.add(aXTMKQXBean)) {
//			aXTMKQXBean = new XTMKQXBean(dbAccess.getField("XTMKID"), dbAccess.getField("MKBH"), dbAccess.getField("MKMC"), dbAccess.getField("QXJBID"), dbAccess.getField("JBMC"));
//		}
//		dbAccess.closeDB();
		return vTemp;
	}
}
