/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.sample.service;

import java.util.List;

import com.hoperun.commons.sample.model.SampleTree;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.JGXXTree;

// TODO: Auto-generated Javadoc
/**
 * The Interface SampleService.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

public interface SampleService extends IBaseService {
	
	/**
	 * Gets the tree.
	 * 
	 * @return the tree
	 * 
	 * @throws Exception the exception
	 */
	public List<SampleTree> getTree() throws Exception;

	/**
	 * Gets the tree async init.
	 * 
	 * @return the tree async init
	 * 
	 * @throws Exception the exception
	 */
	public List<SampleTree> getTreeAsyncInit() throws Exception;

	/**
	 * Gets the tree async.
	 * 
	 * @param sjjg the sjjg
	 * 
	 * @return the tree async
	 * 
	 * @throws Exception the exception
	 */
	public List<SampleTree> getTreeAsync(String sjjg) throws Exception;

}
