/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw     
*/
package com.hoperun.commons.util;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class ParamUtil.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

/**
 * 请求参数工具类
 * 
 * @author uke
 * 
 */

public class ParamUtil {

	/**
	 * 获取解密后的参数.
	 * 
	 * @param request the request
	 * @param param the param
	 * 
	 * @return the string
	 */
	public static String decryptStr(HttpServletRequest request, String param) {
		return StringUtil.decrypt(get(request, param));
	}

	/**
	 * <li>獲取Request中的參數，並且去除NULL</li>.
	 * 
	 * @param request the request
	 * @param param the param
	 * 
	 * @return the string
	 */
	public static String get(HttpServletRequest request, String param) {
		String method = request.getMethod();
		//get方法要转码
		if(BusinessConsts.REQUEST_GET.equalsIgnoreCase(method)){
			return  utf8Decoder(request,param);
		}
		//审核通过页面，点击确认，虽然是post方式提交 但是还需解码
		String doGet = request.getParameter("doGet");
		if("doGet".equalsIgnoreCase(doGet)){
			String str = utf8Decoder(request,param);
			return str;
			
		}
		return get(request, param, "");
	}

	/**
	 * <li>獲取Request中的參數，並且去除NULL,以指定内容取代</li>.
	 * 
	 * @param request the request
	 * @param param the param
	 * @param defValue the def value
	 * 
	 * @return the string
	 */
	public static String get(HttpServletRequest request, String param,
			String defValue) {
		String value = request.getParameter(param);
		//这里可以进行特殊字符过滤
		//add by zhuxw 2013-08-09
//		if(!StringUtil.isEmpty(value))
//		{
//			value=StringUtil.filterString(value);
//		}
		return null == value ? defValue : value.trim();
	}
	
	/**
	 * 整型值.
	 * 
	 * @param request the request
	 * @param param the param
	 * @param defValue the def value
	 * 
	 * @return the int
	 */
	public static int getInt(HttpServletRequest request, String param,
			int defValue) {
		String value = request.getParameter(param);
		if (!StringUtil.isEmpty(value)) {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
			}
		}
		return defValue;
	}

	/**
	 * 布爾值.
	 * 
	 * @param request the request
	 * @param param the param
	 * 
	 * @return the boolean
	 */
	public static boolean getBoolean(HttpServletRequest request, String param) {
		return "true".equals(get(request, param));
	}

	/**
	 * 金額類型.
	 * 
	 * @param request the request
	 * @param param the param
	 * 
	 * @return the double
	 */
	public static Double getDouble(HttpServletRequest request, String param) {
		try {
			String value = get(request, param, "0").replaceAll(",", "");
			return new Double(value);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 日期類型.
	 * 
	 * @param request the request
	 * @param param the param
	 * 
	 * @return the date
	 */
	public static Date getDate(HttpServletRequest request, String param) {
		return DateUtil.parseDate(get(request, param));
	}

	/**
	 * 日期類型字符串yyyyMMdd.
	 * 
	 * @param request the request
	 * @param param the param
	 * 
	 * @return the date str
	 */
	public static String getDateStr(HttpServletRequest request, String param) {
		return get(request, param).replaceAll("-|\\/", "");
	}

	/**
	 * 向从Request中获取字符串值到Map中,包含空值.
	 * 
	 * @param request the request
	 * @param param the param
	 * @param paramMap the param map
	 */
	@SuppressWarnings("unchecked")
	public static void putStr2Map(HttpServletRequest request, String param,
			Map paramMap) {
		paramMap.put(param, get(request, param));
	}

	
	/**
	 * 向从Request中获取字符串 转换为大写 
	 * 放 入 Map中,
	 * 包含空值.
	 * @param request the request
	 * @param param the param
	 * @param paramMap the param map
	 */
	@SuppressWarnings("unchecked")
	public static void putStr2MaptoUpperCase(HttpServletRequest request, String param,
			Map paramMap) {
		String str = get(request, param);
	    str = StringUtil.toUpperCase(str);
		paramMap.put(param, str);
	}
	
	/**
	 * 向从Request中获取整型值到Map中，不包含空值.
	 * 
	 * @param request the request
	 * @param param the param
	 * @param paramMap the param map
	 */
	public static void putInt2Map(HttpServletRequest request, String param,
			Map paramMap) {
		paramMap.put(param, new Integer(getInt(request, param, 0)));
	}

	/**
	 * 向从Request中获取浮點值到Map中，不包含空值.
	 * 
	 * @param request the request
	 * @param param the param
	 * @param paramMap the param map
	 */
	public static void putDouble2Map(HttpServletRequest request, String param,
			Map paramMap) {
		paramMap.put(param, getDouble(request, param));
	}

	/**
	 * 向从Request中将日期值yyyy-MM-dd转成yyyyMMdd到Map中，不包含空值.
	 * 
	 * @param request the request
	 * @param param the param
	 * @param paramMap the param map
	 */
	public static void putDateStr2Map(HttpServletRequest request, String param,
			Map paramMap) {
		String value = getDateStr(request, param);
		if (!StringUtil.isEmpty(value)) {
			paramMap.put(param, value);
		}
	}

	/** The forbids. */
	private static String[] forbids = { "action","paramUrl" };

	/**
	 * Check params.
	 * 
	 * @param name the name
	 * 
	 * @return true, if successful
	 */
	private static boolean checkParams(String name) {
		for (int i = 0, j = forbids.length; i < j; i++) {
			if (forbids[i].equals(name))
				return false;
		}
		return true;
	}

	/**
	 * Genery hidden input.
	 * 
	 * @param name the name
	 * @param value the value
	 * 
	 * @return the string
	 */
	public static String generyHiddenInput(String name, String value) {
		String html = "<input type='hidden' name='0' value=\"1\">";
		html = html.replaceFirst("0", name);
		html = html.replaceFirst("1", value);
		return html;
	}
	
	/**
	 * Genery hidden con.
	 * 
	 * @param name the name
	 * @param value the value
	 * 
	 * @return the string
	 */
	public static String generyHiddenCon(String name, String value) {
		String html = "document.getElementById(\"0\").value=\"1\";";
		html = html.replaceFirst("0", name);
		html = html.replaceFirst("1", value);
		return html;
	}
	
	/**
	 * 拼接参数为URL
	 * add by zhuxw.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * @param forbid the forbid
	 * 
	 * @return the string
	 */
	public static String joinParamToUrl(HttpServletRequest request,
			String prefix, boolean forbid) {
		StringBuffer bf = new StringBuffer();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = utf8Decoder(request, name);
			if (!StringUtil.isEmpty(value)) {
				value = value.trim();// add by zzb 2014-7-25 去除空格
				if (forbid) {
					if (!checkParams(name)) {
						continue;
					}
				}
				bf.append("&");
				bf.append(name);
				bf.append("=");
				bf.append(value);
				
			}
		}
		return bf.toString();
	}
	
	/**
	 * 转换参数.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * @param forbid the forbid
	 * 
	 * @return the string
	 */
	public static String fixParamToHtml(HttpServletRequest request,
			String prefix, boolean forbid) {
		StringBuffer bf = new StringBuffer();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = get(request, name);
			if (!StringUtil.isEmpty(value)) {
				if (forbid) {
					if (!checkParams(name)) {
						continue;
					}
				}
				bf.append(generyHiddenInput(prefix + name, value));
			}
		}
		return bf.toString();
	}

	/**
	 * 转换参数.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * 
	 * @return the string
	 */
	public static String fixParamToHtml(HttpServletRequest request,
			String prefix) {
		return fixParamToHtml(request, prefix, true);
	}
	
	/**
	 * 把参数拼接为URL.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * 
	 * @return the string
	 */
	public static String joinParamToUrl(HttpServletRequest request,
			String prefix) {
		return joinParamToUrl(request, prefix, true);
	}
	
	/**
	 * <ul>
	 * 保留請求中的參數，生成限制参数之外的参数对
	 * <li>forbids[]内设定的参数名不会生成参数对</li>
	 * </ul>.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * @param forbids the forbids
	 * 
	 * @return the string
	 */
	public static String forbidFixParamToHtml(HttpServletRequest request,
			String prefix, String[] forbids) {
		StringBuffer bf = new StringBuffer();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			String value = get(request, name);
			// 過濾參數對
			if (!StringUtil.isEmpty(value) && StringUtil.indexOf(forbids, name, false) < 0) {
				bf.append(generyHiddenInput(prefix + name, value));
			}
		}
//		names = request.getParameterNames();
//		StringBuffer serchCon = new StringBuffer("<script type=\"text/javascript\">");
//		serchCon.append(" document.body.onload = function(){ setTimeout(\"_setCon()\",500);}; ");
//		serchCon.append(" function _setCon(){ alert(1);");
//		
//	 while (names.hasMoreElements()) {
//			
//			
//			String name = (String) names.nextElement();
//			if (name.startsWith(prefix)) {
//				try {
//					String value = get(request, name);
//					if (!StringUtil.isEmpty(value)&&!name.equals("module")&&!name.equals("condition")&&!name.equals("tiaojian")&&!name.equals("search")&&!name.equals("pageNo")&&!name.equals("action")) {
//						
//						serchCon.append(generyHiddenCon(name, value));
//					}
//				} catch (Exception e) {
//				}
//			}
//			
//			
//		}
//	    serchCon.append("}</script>");
//	    System.err.println("serchCon==="+serchCon.toString());
//	    bf.append(serchCon.toString());
		return bf.toString();
	}

	/**
	 * Genery fix param to html.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * 
	 * @return the string
	 */
	public static String generyFixParamToHtml(HttpServletRequest request,
			String prefix) {
		StringBuffer bf = new StringBuffer();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if (name.startsWith(prefix)) {
				try {
					String value = get(request, name);
					if (!StringUtil.isEmpty(value)) {
						bf.append(generyHiddenInput(name, value));
					}
				} catch (Exception e) {
				}
			}
		}
		return bf.toString();
	}

	/**
	 * 将指定前缀的参数的前缀去除，并生成隐含HTML对象.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * 
	 * @return the string
	 */
	public static String decodeParamToHtml(HttpServletRequest request,
			String prefix) {
		StringBuffer bf = new StringBuffer();
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if (name.startsWith(prefix) && !"_backUrl".equals(name)) {
				String value = get(request, name);
				name = name.substring(prefix.length());
				if (!StringUtil.isEmpty(value)) {
					bf.append(generyHiddenInput(name, value));
				}
			}
		}
		return bf.toString();
	}

	/**
	 * 将裝飾過的HttpServletRequest參數分离出来，存储到MAP中
	 * <li>request: _name ==> map:_name </li>.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * 
	 * @return the map
	 */
	public static Map enfixParam(HttpServletRequest request, String prefix) {
		Enumeration names = request.getParameterNames();
		Map params = new HashMap();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if (name.startsWith(prefix)) {
				String value = get(request, name);
				if (!StringUtil.isEmpty(value)) {
					params.put(name, value);
				}
			}
		}
		return params;
	}

	/**
	 * <li>还原被装饰过的HttpServletRequest參數</li>
	 * <li>去除装饰，并将还原后的参数存储到MAP中</li>
	 * <li>request: _name ==> map:name </li>.
	 * 
	 * @param request the request
	 * @param prefix the prefix
	 * 
	 * @return the map
	 */
	public static Map defixParam(HttpServletRequest request, String prefix) {
		Enumeration names = request.getParameterNames();
		Map params = new HashMap();
		while (names.hasMoreElements()) {
			String name = (String) names.nextElement();
			if (name.startsWith(prefix)) {
				String value = get(request, name);
				name = name.substring(prefix.length());
				if (!StringUtil.isEmpty(value)) {
					params.put(name, value);
				}
			}
		}
		return params;
	}

	/**
	 * 解析请求，并将参数值对以key:value字符串保存.
	 * 
	 * @param request the request
	 * 
	 * @return the string
	 */
	public static String favorit(HttpServletRequest request) {
		return favorit(request, true);
	}

	/**
	 * 保存有效页面参数.
	 * 
	 * @param request the request
	 * @param forbid the forbid
	 * 
	 * @return the string
	 */
	public static String favorit(HttpServletRequest request, boolean forbid) {
		String url = request.getRequestURL().toString();
		String ctx = request.getContextPath();
		int index = url.indexOf(ctx);
		String name, value;
		StringBuffer bf = new StringBuffer();
		bf.append("URL:'").append(url.substring(index + ctx.length() + 1))
				.append("'");
		Enumeration names = request.getParameterNames();
		while (names.hasMoreElements()) {
			name = (String) names.nextElement();
			if (forbid && name.startsWith("_"))
				continue;
			value = get(request, name);
			if (!(null == value || "".equals(value))) {
				bf.append(",").append(name).append(":\"").append(value).append(
						"\"");
			}
		}
		return bf.toString();
	}
	
	/**
	 * utf8转码
	 * Description :.
	 * 
	 * @param str the str
	 * @param request the request
	 * 
	 * @return the string
	 */
	public static String utf8Decoder(HttpServletRequest request,String str){
		String s = request.getParameter(str);
		if(null==s)
		  return  "";
		else
		  return StringUtil.utf8Decoder(s);
	}
	
	/**
	 * 设置字段排序
	 * Description :.
	 * 
	 * @param request the request
	 * @param params the params
	 */
	public static void setOrderField(HttpServletRequest request,Map<String,String> params){
		//字段排序
		String orderId = ParamUtil.get(request, "orderId");
		String orderType = ParamUtil.get(request, "orderType");
		if(StringUtils.isNotEmpty(orderId)){
			params.put("orderField",orderId+" "+orderType);
			request.setAttribute("orderType", orderType);
			request.setAttribute("orderId", orderId);
		}
	}
	/**
	 * 设置字段排序带默认值
	 * Description :.add by zhuxw 2013-08-20
	 * 
	 * @param request the request
	 * @param params the params
	 */
	public static void setOrderField(HttpServletRequest request,Map<String,String> params,String defOrderId,String defOrdertype){
		//字段排序
		String orderId = ParamUtil.get(request, "orderId");
		String orderType = ParamUtil.get(request, "orderType");
		if(StringUtils.isNotEmpty(orderId)){
			params.put("orderField",orderId+" "+orderType);
			request.setAttribute("orderType", orderType);
			request.setAttribute("orderId", orderId);
		}else
		{
			params.put("orderField",defOrderId+" "+defOrdertype);
			request.setAttribute("orderType", defOrdertype);
			request.setAttribute("orderId", defOrderId);	
			
		}
	}
	
	/**
	 * 页面跳转用的一些常用属性设置
	 * Description :.
	 * 
	 * @param request the request
	 */
	public static void setCommAttributeEx(HttpServletRequest request){
		String orderId = ParamUtil.get(request, "orderId");
		String orderType = ParamUtil.get(request, "orderType");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		int pageSize = ParamUtil.getInt(request, "pageSize", Consts.PAGE_SIZE);
		String selRowId = ParamUtil.get(request, "selRowId");
		String module = ParamUtil.get(request, "module");
		request.setAttribute("orderId", orderId);
		request.setAttribute("orderType",orderType);
		request.setAttribute("pageNo",pageNo);
		request.setAttribute("pageSize",pageSize);
		request.setAttribute("selRowId",selRowId);
		request.setAttribute("module",module);
	}

	/**
	 * Put qxjb str2 map.
	 * @author zhuxw 2013-08-20
	 * @param request the request
	 * @param params the params
	 * @param constsWh the consts wh
	 * @param constsSh the consts sh
	 * @param pkId the pk id
	 * @param tableName the table name
	 * @param businessType the business type
	 */
	public static String getPvgCon(String search,String module,String pvgBws, String pvgAudit,
			String pkId, String tableName, String businessType,String state,UserBean userBean) {
		StringBuffer tmp=new StringBuffer("");
		if (!StringUtil.isEmpty(module))
         {
        	if ("sh".equals(module)) {
    			if (StringUtil.isEmpty(search)) {
    				tmp.append(QXUtil.getQXTJ(userBean, pvgAudit));
    				if(!StringUtil.isEmpty(state))
    				{
    					tmp.append(" and ");
        				tmp.append(state);
        				tmp.append(" ='");
        				tmp.append(BusinessConsts.COMMIT);
        				tmp.append("'");
    				}
    				tmp.append(" and ");
    				tmp.append(pkId);
    				tmp.append(" in ");
    				tmp.append(QXUtil.getAudCondition(userBean, tableName,businessType));
    			} else {
    				tmp.append(QXUtil.getQXTJ(userBean, pvgAudit));
    				tmp.append(" and ");
    				tmp.append(pkId);
    				tmp.append(" in ");
    				tmp.append(QXUtil.getAudQureyCon(userBean, tableName,businessType));
    			}
    		} else {
    			if (StringUtil.isEmpty(search)) {
    				tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
    				if(!StringUtil.isEmpty(state))
    				{
        			tmp.append(" and ");
    				tmp.append(state);
    				tmp.append(" in ('");
    				tmp.append(BusinessConsts.UNCOMMIT);
    				tmp.append("','");
    				tmp.append(BusinessConsts.REVOKE);
    				tmp.append("','");
    				tmp.append(BusinessConsts.REJECT);
    				tmp.append("')");
    				}
    			} else {
    				tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
    			}
    			
    		}
        }else
        {
        	 tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
        }
	  return tmp.toString();
	}
	/**
	 * get User Bean
	 * @author zxw
	 * @param request the request
	 */
	public static UserBean getUserBean(HttpServletRequest request) {
		return (UserBean) request.getSession(false).getAttribute(Consts.USR_SESS);
	}
	
	
 
 
}
