/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ProviderAction.java
 */
package com.hoperun.base.provider.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.provider.service.ProviderService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.base.provider.model.ProviderModel;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ZoneAction.
 * 
 * @module 系统管理
 * @func 供应商信息
 * @version 1.1
 * @author 张涛
 */
public class ProviderAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(ProviderAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "XT0012801";
	private static String PVG_EDIT = "XT0012802";
	private static String PVG_DELETE = "XT0012803";
	// 启用,停用
	private static String PVG_START_STOP = "XT0012804";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "STATE";
	/** end */
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
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	/** 审批 end **/
	private ProviderService providerService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	/**
	 * 供应商信息框架页面
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("frames");
	}

	/**
	 * 查询结果列表
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "PRVD_ID", params);

		ParamUtil.putStr2Map(request, "PRVD_NO", params);
		ParamUtil.putStr2Map(request, "PRVD_NAME", params);
		ParamUtil.putStr2Map(request, "PRVD_TYPE", params);
		ParamUtil.putStr2Map(request, "PROV", params);
		ParamUtil.putStr2Map(request, "CITY", params);
		ParamUtil.putStr2Map(request, "PERSON_BUSS", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_FROM", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_TO", params);

		// 只查询0的记录。1为删除。0为正常
		params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		// 字段排序
		ParamUtil.setOrderField(request, params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = providerService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 * 供应商信息编辑初始化页面
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		if (!StringUtil.isEmpty(prvd_id)) {
			Map<String, String> entry = providerService.load(prvd_id);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("toedit");
	}

	/**
	 * 供应商信息编辑 新增/修改
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("Enter edit()");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "jsonData");
		ProviderModel providerModel = new ProviderModel();
		if (!StringUtil.isEmpty(jsonData)) {
			providerModel = new Gson().fromJson(jsonData,
					new TypeToken<ProviderModel>() {
					}.getType());
		}
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRVD_ID", prvd_id);
		params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		// 主表编号重复判断
		try {
			if (StringUtils.isEmpty(providerModel.getPRVD_ID())&&providerService.queryForInt(providerModel.getPRVD_NO())>0) {
				jsonResult = jsonResult(false, "供应商编号有重复值，请重新输入");
			} else {
				prvd_id = providerService.txEdit(prvd_id, providerModel,userBean);
				jsonResult = jsonResult(true, prvd_id);
			}

		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 查看供应商详细信息
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @return the action forward
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		if (!StringUtil.isEmpty(prvd_id)) {
			Map<String, String> entry = providerService.load(prvd_id);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("view");
	}

	/**
	 * 删除
	 * 
	 * @param mapping
	 *            the mapping
	 * @param formthe
	 *            form
	 * @param requestthe
	 *            request
	 * @param response
	 *            the response
	 */
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String prvd_id = ParamUtil.get(request, "PRVD_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRVD_ID", prvd_id);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			providerService.txDelete(params);
			providerService.clearCaches(params);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 启用
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void changeStateStart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String prvd_id = request.getParameter("PRVD_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRVD_ID", prvd_id);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
			providerService.txStartById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "状态修改失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 停用
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 */
	public void changeStateStop(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("按钮修改为停用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			logger.warn("取得对应记录的状态");
			String prvd_id = request.getParameter("PRVD_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRVD_ID", prvd_id);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
			providerService.txStopById(params);
			jsonResult = jsonResult(true, "状态修改成功!");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "状态修改失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * 设置权限Map
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
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,
				PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}
}
