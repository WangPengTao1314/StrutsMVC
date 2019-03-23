/**
  *@module 系统模块   
  *@func 系统模块   
  *@version 1.1
  *@author zhuxw      
*/
package com.hoperun.commons.model;

import javax.servlet.http.HttpServletRequest;

import com.hoperun.commons.util.ParamUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class ParamCover.
 * 
 * @module 系统模块
 * @func 系统基础类
 * @version 1.1
 * @author zhuxw
 */

/**
 * HTPP请求参数转换工具类
 * <ul>
 * <li>A => _A: 加工参数</li>
 * <li>A => A ：保留原参数</li>
 * <li>_A=>_A : 保留加工过的参数</li>
 * <li>_A=> A : 还原加工过的参数</li>
 * </ul>
 * @author uke
 * 
 */
public class ParamCover {
	
	/** HttpServletRequest 参数前缀. */
	private static final String PREX = "_";
	
	/** The request. */
	private HttpServletRequest request;
	
	/** The forbids. */
	private String[] forbids = new String[] { "action", "pageNo","pageSize"};
	
	
	//add by zhuxw
	/** The _back url. */
	private  String _backUrl = "";
	
	/**
	 * Instantiates a new param cover.
	 * 
	 * @param request the request
	 */
	public ParamCover(HttpServletRequest request) {
		this.request = request;
	}

	/**
	 * 加工参数，a => _a ，参数以Hidden Input方式存储
	 * <ul>
	 * <li>参数名不包括action</li>
	 * <li>参数名不包括空值及NULL值</li>
	 * </ul>.
	 * 
	 * @return the covered inputs
	 */
	public String getCoveredInputs() {
		return ParamUtil.fixParamToHtml(request, PREX);
	}

	/**
	 * 解析参数, 将a => a, 参数以Hidden Input方式存储.
	 * 
	 * @return the un covered inputs
	 */
	public String getUnCoveredInputs() {
		return ParamUtil.fixParamToHtml(request, "");
	}

	/**
	 * 解析参数, 将_a => _a, 参数以Hidden Input方式存储.
	 * 
	 * @return the un covered_ inputs
	 */
	public String getUnCovered_Inputs() {
		return ParamUtil.generyFixParamToHtml(request, PREX);
	}

	/**
	 * 解析参数, 将a => a, 参数以Hidden Input方式存储, 除必要的forbids参数外.
	 * 
	 * @return the un covered forbid inputs
	 */
	public String getUnCoveredForbidInputs() {
		return ParamUtil.forbidFixParamToHtml(request, "", forbids);
	}

	/**
	 * 还原加工的参数，将_a => a，参数以Hidden Input方式存储 未包含_backUrl.
	 * 
	 * @return the decode inputs
	 */
	public String getDecodeInputs() {
		return ParamUtil.decodeParamToHtml(request, PREX);
	}

	/**
	 * Gets the forbids.
	 * 
	 * @return the forbids
	 */
	public String[] getForbids() {
		return forbids;
	}

	/**
	 * Sets the forbids.
	 * 
	 * @param forbids the new forbids
	 */
	public void setForbids(String[] forbids) {
		this.forbids = forbids;
	}
	
	/**
	 * 加工参数，to URL格式
	 * <ul> add by zhuxw
	 * <li>参数名不包括action</li>
	 * <li>参数名不包括空值及NULL值</li>
	 * </ul>.
	 * 
	 * @return the covered url
	 */
	public String getCoveredUrl() {
		return ParamUtil.joinParamToUrl(request, PREX);
	}

	/**
	 * Gets the _back url.
	 * 
	 * @return the _back url
	 */
	public String get_backUrl() {
		return _backUrl;
	}

	/**
	 * Sets the _back url.
	 * 
	 * @param url the new _back url
	 */
	public void set_backUrl(String url) {
		_backUrl = url;
	}

	

}