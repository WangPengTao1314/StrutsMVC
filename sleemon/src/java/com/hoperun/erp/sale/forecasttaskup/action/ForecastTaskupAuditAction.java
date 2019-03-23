package com.hoperun.erp.sale.forecasttaskup.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.forecasttaskup.model.AdvcRptChannDtl;
import com.hoperun.erp.sale.forecasttask.action.ForecastTaskAction;
import com.hoperun.erp.sale.forecasttaskup.service.ForecastTaskupAuditService;
import com.hoperun.sys.model.UserBean;

public class ForecastTaskupAuditAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(ForecastTaskAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0014401";
	private static String PVG_EDIT = "BS0014402";
	private static String PVG_DELETE = "";
 
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "BS0014403";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";

	private ForecastTaskupAuditService forecastTaskupAuditService;

	/**
	 * * to 框架页面
	 * @param mapping  the mapping
	 * @param form     the form
	 * @param request  the request
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
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("frames");
	}

	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_RPT_JOB_NO", params);
		ParamUtil.putStr2Map(request, "YEAR", params);
		ParamUtil.putStr2Map(request, "MONTH", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "SENDER_NAME", params);
		ParamUtil.putStr2Map(request, "SENDER_TIME_BEG", params);
		ParamUtil.putStr2Map(request, "SENDER_TIME_END", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "CHANN_NO", params);
		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		
		String CHANN_NO = params.get("CHANN_NO");
		String CHANN_NAME = params.get("CHANN_NAME");
		 
		StringBuffer sql = new StringBuffer();
		if(!StringUtil.isEmpty(CHANN_NO)){
			sql.append(StringUtil.creCon("u.CHANN_NO", CHANN_NO, ","));
		}

		if(!StringUtil.isEmpty(CHANN_NAME)){
			sql.append(StringUtil.creCon("u.CHANN_NAME", CHANN_NAME, ","));
		}
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");

		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		if (StringUtil.isEmpty(search)) {
			sb.append(" and u.STATE ='提交' ");
		}else if(StringUtil.isEmpty(params.get("STATE"))){
			sb.append(" and u.STATE in('提交','审核通过') ");
		}
		sb.append(sql);
		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		  //渠道分管
	    String CHANNS_CHARG = userBean.getCHANNS_CHARG();
 	    params.put("CHANNS_CHARG",CHANNS_CHARG);
		// 字段排序
		ParamUtil.setOrderField(request, params, "b.CRE_TIME", "ASC");

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = forecastTaskupAuditService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
	 * 
	 * @param mapping  the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
		if (!StringUtil.isEmpty(RPT_JOB_CHANN_ID)) {
			List<Map<String, String>> result = forecastTaskupAuditService
					.childQuery(RPT_JOB_CHANN_ID);
			request.setAttribute("rst", result);
			Map entry = forecastTaskupAuditService.getTotalInfo(RPT_JOB_CHANN_ID);
			request.setAttribute("rstT",entry);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void saveChild(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jsonDate = ParamUtil.get(request, "jsonDate");
			List<AdvcRptChannDtl> chldList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldList = new Gson().fromJson(jsonDate,
						new TypeToken<List<AdvcRptChannDtl>>() {
						}.getType());
				forecastTaskupAuditService.txSaveChild(chldList);
			}
			jsonResult = jsonResult(true, "保存成功");
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 审核
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void audit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
			forecastTaskupAuditService.txAudit(RPT_JOB_CHANN_ID);
			jsonResult = jsonResult(true, "审核成功");
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "审核失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	public void cancal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String RPT_JOB_CHANN_ID = ParamUtil.get(request, "RPT_JOB_CHANN_ID");
			forecastTaskupAuditService.txReAudit(RPT_JOB_CHANN_ID);
			jsonResult = jsonResult(true, "弃审成功");
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "弃审失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	/**
	 * 设置权限Map
	 * 
	 * @param UserBean
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	public ForecastTaskupAuditService getForecastTaskupAuditService() {
		return forecastTaskupAuditService;
	}

	public void setForecastTaskupAuditService(
			ForecastTaskupAuditService forecastTaskupAuditService) {
		this.forecastTaskupAuditService = forecastTaskupAuditService;
	}

}
