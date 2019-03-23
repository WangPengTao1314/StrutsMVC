/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.exception;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

// TODO: Auto-generated Javadoc
/**
 * The Class ServiceExceptionHandler.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
/**
 * 自定义异常处理类
 * 
 * @author Covey
 * 
 */
public class ServiceExceptionHandler extends ExceptionHandler {

	/**
	 * 异常跳转控制.
	 * 
	 * @param ex the ex
	 * @param ec the ec
	 * @param mapping the mapping
	 * @param formInstance the form instance
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 * 
	 * @throws ServletException the servlet exception
	 */
	public ActionForward execute(Exception ex, ExceptionConfig ec,
			ActionMapping mapping, ActionForm formInstance,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException {
		ActionForward forward = null;
		if (ec.getPath() != null) {
			forward = new ActionForward(ec.getPath());
		} else {
			forward = mapping.getInputForward();
		}
		if (ex instanceof ServiceException) {
			System.out.println("Throw ServiceException!");
			ServiceException se = (ServiceException) ex;
			request.setAttribute("msg", se.getMessage());
			return forward;
		}
		return super.execute(ex, ec, mapping, formInstance, request, response);
	}
}
