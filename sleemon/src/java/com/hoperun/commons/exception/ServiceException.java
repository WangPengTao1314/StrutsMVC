/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.exception;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceException.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
/**
 * Service层公用的Exception.
 * 
 * 继承自RuntimeException,从被Spring声明式事务管理的函数中抛出时会触发事务回滚.
 * 
 * @author Covey
 *
 */
public class ServiceException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3583566093089790852L;

	/**
	 * Instantiates a new service exception.
	 */
	public ServiceException() {
		super();
	}

	/**
	 * Instantiates a new service exception.
	 * 
	 * @param message the message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new service exception.
	 * 
	 * @param cause the cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new service exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
