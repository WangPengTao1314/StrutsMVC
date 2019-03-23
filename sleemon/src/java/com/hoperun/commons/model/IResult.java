/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

// TODO: Auto-generated Javadoc
/**
 * The Interface IResult.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public interface IResult {
	
	/**
	 * 操作是否成功.
	 * 
	 * @return 成功标识符
	 */
	public boolean isSuccess();
	
	/**
	 * 获取消息列表.
	 * 
	 * @return 消息列表
	 */
	public String getMessages();
	
	/**
	 * 获取结果数据.
	 * 
	 * @return 结果数据
	 */
	public Object getData();
	
	/**
	 * 获取结果数据集的总数.
	 * 
	 * @return 结果数据集总数
	 */
	public int getTotalCount();
	
}
