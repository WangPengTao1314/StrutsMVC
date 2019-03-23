/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
// TODO: Auto-generated Javadoc

/**
 * The Class Properties.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public class Properties {

	/** The Constant CONFIG. */
	private static final String CONFIG = "conf";
	
	/** The CONFI g_ res. */
	private static ResourceBundle CONFIG_RES = ResourceBundle.getBundle(CONFIG);

	/**
	 * Instantiates a new properties.
	 */
	private Properties() {
	}

	/**
	 * Gets the string.
	 * 
	 * @param key the key
	 * 
	 * @return the string
	 */
	public static String getString(String key) {
		try {
			return CONFIG_RES.getString(key);
		} catch (MissingResourceException e) {
			// CONFIG_RES = ResourceBundle.getBundle(CONFIG);
		}
		return "";
	}

	/**
	 * Gets the int.
	 * 
	 * @param key the key
	 * 
	 * @return the int
	 */
	public static int getInt(String key) {
		String value = getString(key);
		if (!StringUtil.isEmpty(value)) {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	
	/**
	 * Gets the str list.
	 * 
	 * @param key the key
	 * 
	 * @return the str list
	 */
	public static String getStrList(String key) {
		String value = getString(key);
		if (!StringUtil.isEmpty(value)) {
			try {
				return value;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "8";
	}
	
	/**
	 * Gets the boolean.
	 * 
	 * @param key the key
	 * 
	 * @return the boolean
	 */
	public static boolean getBoolean(String key) {
		return "true".equals(getString(key));
	}

}