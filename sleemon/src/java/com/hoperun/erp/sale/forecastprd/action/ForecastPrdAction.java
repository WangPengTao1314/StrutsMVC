package com.hoperun.erp.sale.forecastprd.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.forecastprd.service.ForecastPrdService;
import com.hoperun.sys.model.UserBean;

/**
 * 预计量上报 货品设置
 * 
 * @author zhang_zb
 * 
 */
public class ForecastPrdAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(ForecastPrdAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0014101";
	private static String PVG_EDIT = "BS0014102";
	private static String PVG_DELETE = "BS0014103";

	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";


	private ForecastPrdService forecastPrdService;
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request, "paramUrl"));
		return mapping.findForward("frames");
	}

	
	public ActionForward toTopPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		logger.info("toFrame方法调用开始");
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && (!QXUtil.checkMKQX(userBean, PVG_BWS))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("top");

	}
	
	/**
	 * 列表页
	 * @param mapping  the mapping
	 * @param form   the form
	 * @param request  the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
	 * @param mapping the mapping
	 * @param form the form
	 * @param request   the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		 
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
		ParamUtil.putStr2Map(request, "PAR_PRD_NO", params);
		ParamUtil.putStr2Map(request, "PAR_PRD_NAME", params);
		
		String search = ParamUtil.get(request, "search");
		String notcharg = ParamUtil.get(request, "notcharg");//只显示未分管
		String PAR_PRD_NO =  params.get("PAR_PRD_NO");
		String PAR_PRD_NAME =  params.get("PAR_PRD_NAME");
		StringBuffer sqlbuf = new StringBuffer();
		if(!StringUtil.isEmpty(PAR_PRD_NO)){
			sqlbuf.append(StringUtil.creCon("p.PAR_PRD_NO", PAR_PRD_NO, ","));
		}
		if(!StringUtil.isEmpty(PAR_PRD_NAME)){
			sqlbuf.append(StringUtil.creCon("p.PAR_PRD_NAME", PAR_PRD_NAME, ","));
		}
	 
		params.put("sql", sqlbuf.toString());
		
		//查询  未分管的
		if(BusinessConsts.INTEGER_1.equals(notcharg)){
			params.put("notcharg", notcharg);
		}
		//查询  已分管的
		if(BusinessConsts.INTEGER_2.equals(notcharg)){
			params.put("havecharg", notcharg);
		}
		
		params.put("search", search);
//		List<Map<String,String>> list = this.forecastProductSetService.childQuery(params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		params.put("pageSize", ParamUtil.get(request, "pageSize", "100"));
		IListPage page = forecastPrdService.pageQuery(params, pageNo);
        request.setAttribute("params", params);
		request.setAttribute("pvg",setPvg(userBean));
		request.setAttribute("page", page);
//		request.setAttribute("result", list);
		return mapping.findForward("childlist");
	}

	/**
	 * 编辑 保存.
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String PRD_IDS = ParamUtil.get(request, "PRD_IDS");
        String DELETE_IDS = ParamUtil.get(request, "deleteIds");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			forecastPrdService.txEdit(PRD_IDS,DELETE_IDS);
			jsonResult = jsonResult(true, "保存成功");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
 
	/**
	 * 取消.
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
        String DELETE_IDS = ParamUtil.get(request, "deleteIds");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			forecastPrdService.txDelete(DELETE_IDS);
			jsonResult = jsonResult(true, "取消成功");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "取消失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 设置权限Map
	 * @param UserBean  the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	public ForecastPrdService getForecastPrdService() {
		return forecastPrdService;
	}

	public void setForecastPrdService(ForecastPrdService forecastPrdService) {
		this.forecastPrdService = forecastPrdService;
	}
}
