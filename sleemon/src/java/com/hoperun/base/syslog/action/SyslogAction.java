/**
 * 
 */
package com.hoperun.base.syslog.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.base.area.action.AreaAction;
import com.hoperun.base.syslog.service.SyslogService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.sys.model.UserBean;

/**
 * 系统日志
 * 
 * @author gu_hongxiu
 *
 */
public class SyslogAction extends BaseAction {
	
	/** 
	 * 日志记录
	 * */
	private Logger logger = Logger.getLogger(AreaAction.class);
	
	private SyslogService syslogService;

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("toFrame方法调用开始");

		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		logger.info("toFrame方法调用结束");
		return mapping.findForward("frames");

	}
	
	/**
     * 系统日志列表.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * s
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	//UserBean userBean = ParamUtil.getUserBean(request);
    	Map params = new HashMap();
    	params.put("SYSLOG_ID", ParamUtil.utf8Decoder(request, "SYSLOG_ID"));		//系统日志id
    	params.put("UC_NAME", ParamUtil.utf8Decoder(request, "UC_NAME"));			//模块名称
        params.put("ACT_NAME", ParamUtil.utf8Decoder(request, "ACT_NAME"));			//操作名称
        params.put("OPRATOR", ParamUtil.utf8Decoder(request, "OPRATOR"));			//调用/被调用
        params.put("ACT_TIME_FROM", ParamUtil.utf8Decoder(request, "ACT_TIME_FROM"));//操作时间从
        params.put("ACT_TIME_TO", ParamUtil.utf8Decoder(request, "ACT_TIME_TO"));	 //操作时间到
        params.put("STATE", ParamUtil.utf8Decoder(request, "STATE"));				 //状态
        params.put("APPCODE", ParamUtil.utf8Decoder(request, "APPCODE"));			//调用方appcode
        params.put("APPID", ParamUtil.utf8Decoder(request, "APPID"));			//uid
        params.put("OPRCODE", ParamUtil.utf8Decoder(request, "OPRCODE"));	//服务码+操作码       
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = syslogService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		//request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
        return mapping.findForward("list");
    }
    
    /**
     * 查看系统日志详细信息.
     * 
     * @param mapping the mapping
     * @param form the form
     * @param request the request
     * @param response the response
     * 
     * @return the action forward
     */
    @SuppressWarnings("unchecked")
    public ActionForward toDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	    	
        String SYSLOG_ID = ParamUtil.get(request, "SYSLOG_ID");
        Map entry = syslogService.load(SYSLOG_ID);
        request.setAttribute("rst", entry);

        return mapping.findForward("detail");
    }
	
	public SyslogService getSyslogService() {
		return syslogService;
	}

	public void setSyslogService(SyslogService syslogService) {
		this.syslogService = syslogService;
	}

}
