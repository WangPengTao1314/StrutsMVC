/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.util.sort;

// TODO: Auto-generated Javadoc
/**
 * The Interface Compare.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public interface Compare {
	
	/**
	 * Less than.
	 * 
	 * @param lhs the lhs
	 * @param rhs the rhs
	 * 
	 * @return true, if successful
	 */
	boolean lessThan(Object lhs, Object rhs);
    
    /**
     * Less than or equal.
     * 
     * @param lhs the lhs
     * @param rhs the rhs
     * 
     * @return true, if successful
     */
    boolean lessThanOrEqual(Object lhs, Object rhs);
}