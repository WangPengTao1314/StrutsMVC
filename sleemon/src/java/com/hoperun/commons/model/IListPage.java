/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

// TODO: Auto-generated Javadoc
/**
 * The Interface IListPage.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
public interface IListPage {

	/** The Constant DEFAULT_PAGE_SIZE. */
	public final static int DEFAULT_PAGE_SIZE = Properties.getInt("PAGE_SIZE");
	
	/** The Constant DEFAULT_PAGE_SIZE_LIST. */
	public final static String DEFAULT_PAGE_SIZE_LIST = Properties.getStrList("PAGE_SIZE_LIST");
	
	/**
	 * Gets the form.
	 * 
	 * @return the form
	 */
	public abstract String getForm();

	/**
	 * Sets the form.
	 * 
	 * @param form the new form
	 */
	public abstract void setForm(String form);

	/**
	 * Gets the start.
	 * 
	 * @return the start
	 */
	public abstract long getStart();

	/**
	 * Sets the start.
	 * 
	 * @param start the new start
	 */
	public abstract void setStart(long start);

	/**
	 * Sets the page size.
	 * 
	 * @param pageSize the new page size
	 */
	public abstract void setPageSize(int pageSize);

	/**
	 * Sets the total count.
	 * 
	 * @param totalCount the new total count
	 */
	public abstract void setTotalCount(long totalCount);

	/**
	 * 取每页数据容量.
	 * 
	 * @return the page size
	 */
	public abstract int getPageSize();

	/**
	 * 取总记录数.
	 * 
	 * @return the total count
	 */
	public abstract long getTotalCount();

	/**
	 * 取总页数.
	 * 
	 * @return the total page count
	 */
	public abstract long getTotalPageCount();

	/**
	 * 取当前页中的记录.
	 * 
	 * @return the result
	 */
	public abstract Object getResult();

	/**
	 * 取该页当前页码,页码从1开始.
	 * 
	 * @return the current page no
	 */
	public abstract long getCurrentPageNo();

	/**
	 * 该页是否有下一页.
	 * 
	 * @return true, if checks for next page
	 */
	public abstract boolean hasNextPage();

	/**
	 * 该页是否有上一页.
	 * 
	 * @return true, if checks for previous page
	 */
	public abstract boolean hasPreviousPage();

	/**
	 * 获取HTML内容.
	 * 
	 * @return the footer html
	 */
	public abstract String getFooterHtml();

	/**
	 * 分页按钮栏.
	 * 
	 * @return the toolbar html
	 */
	public abstract String getToolbarHtml();

}