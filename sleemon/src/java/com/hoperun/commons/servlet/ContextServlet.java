/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.servlet;

import java.io.File;
import java.util.Hashtable;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


import com.hoperun.commons.model.Properties;

// TODO: Auto-generated Javadoc
/**
 * The Class ContextServlet.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class ContextServlet implements ServletContextListener {
	
	/** The init parameter dict service. */
	InitParameterDictService initParameterDictService;
	
	/** The dic list. */
	public static Hashtable<String, List> dicList = new Hashtable<String, List>();

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// System.out.println("Context destroyed on " + new Date() + ".");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// System.out.println("Context created on " + new Date() + ".");
		// System.out.println("init system dictionary ..........");
		String filePath = arg0.getServletContext().getRealPath("/") + "pages"
				+ File.separator + "sys" + File.separator + "configfiles"
				+ File.separator + "dictionary" + File.separator;
		// + "selCommConfig_sel.xml";
		String dic_init = Properties.getString("DICINIT");
		StringTokenizer stk = new StringTokenizer(dic_init, ",", false);
        //InitParameterDictService initDicBean = new InitParameterDictService();
		while (stk.hasMoreTokens()) {
			String tmp = (String) stk.nextToken();
			if (tmp != null && !tmp.equals("")) {
				String fileName = filePath + "dictConfig_" + tmp + ".xml";
				initParameterDictService.initDictList(fileName);
			}
		}
		// dicList = InitParameterDictService.getDictList(fileName);
	}

	/**
	 * Gets the dic list.
	 * 
	 * @param key the key
	 * 
	 * @return the dic list
	 */
	public static List getDicList(String key) {
		if (null == dicList || dicList.size() == 0) {
			return null;
		}
		return (List) dicList.get(key);
	}

	/**
	 * Sets the inits the parameter dict service.
	 * 
	 * @param initParameterDictService the new inits the parameter dict service
	 */
	public void setInitParameterDictService(InitParameterDictService initParameterDictService) {
		this.initParameterDictService = initParameterDictService;
	}
}
