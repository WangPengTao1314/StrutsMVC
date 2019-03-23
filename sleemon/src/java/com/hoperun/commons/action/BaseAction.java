/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.RequestUtils;

import com.google.gson.Gson;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.model.UserSession;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.commons.util.ParamUtil;

/**
 * The Class BaseAction.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */
/**
 * 
 * <ul>
 * Action 基类
 * <li>提供了基础参数获取方法:setBean(object,request)</li>
 * <li>提供了用户会话获取方法:getUserSession()</li>
 * </ul>
 * <ul>
 * 相关 Action 配制
 * <li>需要在/WEB-INF/spring-cfg/*.xml,/WEB-INF/struts-cfg/*.xml中声明并定义</li>
 * </ul>
 * 
 * @author uke
 * 
 */


public abstract class BaseAction extends DispatchAction implements Consts {

	/**
	 * 加载页面Form内容:
	 * <p>
	 * 对字符串类型的的属性可精确匹配
	 * </p>.
	 * 
	 * @param bean the bean
	 * @param request the request
	 */
	public void setBean(Object bean, HttpServletRequest request) {
		try {
			RequestUtils.populate(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户Session.
	 * 
	 * @param request the request
	 * 
	 * @return the user session
	 */
	public UserSession getUserSession(HttpServletRequest request) {
		return (UserSession) request.getSession().getAttribute(USR_SESS);
	}

	/**
	 * 保存访问日志.
	 * 
	 * @param request the request
	 * @param type the type
	 * @param mode the mode
	 * @param service the service
	 * @param desc the desc
	 * 
	 * @return true, if save log
	 */
	public boolean saveLog(HttpServletRequest request, IBaseService service,
			String type, String mode, String desc) {
		// 不保存访问痕迹
		saveLog(request, service, type, mode, desc, false, false);
		return true;
	}

	/**
	 * 保存访问日志.
	 * 
	 * @param request the request
	 * @param service the service
	 * @param type the type
	 * @param mode the mode
	 * @param desc the desc
	 * @param saveConf the save conf
	 * @param forbidVar the forbid var
	 * 
	 * @return true, if save log
	 */
	public boolean saveLog(HttpServletRequest request, IBaseService service,
			String type, String mode, String desc, boolean saveConf,
			boolean forbidVar) {
		Map<String,String> params = new HashMap<String,String>();
		// 用户信息
		UserSession userSess = this.getUserSession(request);
		params.put("logMan", userSess.getUserId());
		params.put("logOrg", userSess.getOrgId());
		params.put("userType", userSess.getUserType());
		// 客户端IP
		params.put("accIp", request.getRemoteAddr());
		// 访问内容
		params.put("logType", type);
		params.put("logMode", mode);
		params.put("logDesc", desc);
		// 保存访问痕迹,注意日志大小
		if (saveConf){
			params.put("logConf", ParamUtil.favorit(request, forbidVar));
		}

		// 服务端IP
		try {
			InetAddress server = InetAddress.getLocalHost();
			params.put("sevIp", server.getHostAddress());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// 保存日志
		service.saveLog(params);
		return true;
	}
	
	/**
	 * 用法:String jsonResult = jsonResult(true,"保存成功");
	 * writer.write(jsonResult);
	 * 
	 * @param isSuccess the is success
	 * @param messages the messages
	 * 
	 * @return the string
	 */
	public String jsonResult(boolean isSuccess,String messages)
	{
		String jsonResult = new Gson().toJson(new Result(isSuccess, null, messages));
		return jsonResult;
	}
	
	/**
	 * Json result.
	 * 
	 * @return the string
	 */
	public String jsonResult()
	{
		return jsonResult(true,"");
	}

	
	/**
	 * Gets the writer.
	 * 
	 * @param response the response
	 * 
	 * @return the writer
	 */
	public PrintWriter getWriter(HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			//异常统一处理
			//e.printStackTrace();
		}
		return writer;
	}
}
