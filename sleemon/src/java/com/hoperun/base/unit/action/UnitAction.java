/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：UnitAction.java
 */
package com.hoperun.base.unit.action;

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
import com.hoperun.base.unit.model.UnitModel;
import com.hoperun.base.unit.service.UnitService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sample.newsingletable.action.NewSingleTableAction;
import com.hoperun.sys.model.UserBean;

/**
 * The Class UnitAction.
 * 
 * @module 系统管理
 * @func 单位维护
 * @version 1.0
 * @author 王栋斌
 */
public class UnitAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(NewSingleTableAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "XT0012301";
	private static String PVG_EDIT = "XT0012302";
	private static String PVG_DELETE = "XT0012303";
	// 启用,停用
	private static String PVG_START_STOP = "XT0012304";
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

	/** The unit service. */
	private UnitService unitService;

	/**
	 * Sets the unit service.
	 * 
	 * @param unitService
	 *            the new unit service
	 */
	public void setunitService(UnitService unitService) {

		this.unitService = unitService;
	}

	/**
	 * 机构信息框架页面.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
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
	 * 查询结果列表.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "MEAS_UNIT_ID", params);
		ParamUtil.putStr2Map(request, "MEAS_UNIT_NAME", params);
		ParamUtil.putStr2Map(request, "MEAS_UNIT_NO", params);
		ParamUtil.putStr2Map(request, "UNIT_NAME_EN", params);
		ParamUtil.putStr2Map(request, "UNIT_TYPE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_FROM", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_TO", params);
		ParamUtil.putStr2Map(request, "STATE", params);

		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
		//权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		// 字段排序
		ParamUtil.setOrderField(request, params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = unitService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 * 明细.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		String MEAS_UNIT_ID = ParamUtil.get(request, "MEAS_UNIT_ID");
		if (!StringUtil.isEmpty(MEAS_UNIT_ID)) {
			Map entry = unitService.load(MEAS_UNIT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("detail");
	}

	/**
	 * 新增.
	 * 
	 * @param mapping
	 *            the mapping
	 * @param form
	 *            the form
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * 
	 * @return the action forward
	 */
	@SuppressWarnings("unchecked")
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("Enter edit()");

		String MEAS_UNIT_ID = ParamUtil.get(request, "MEAS_UNIT_ID");
		if (StringUtils.isNotEmpty(MEAS_UNIT_ID)) {
			Map entry = unitService.load(MEAS_UNIT_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("toedit");
	}

	/**
	 * 修改.
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
		String measUnitId = ParamUtil.get(request, "MEAS_UNIT_ID");
		UnitModel unitModel = new UnitModel();

		if (StringUtils.isNotEmpty(jsonData)) {
			unitModel = new Gson().fromJson(jsonData,
					new TypeToken<UnitModel>() {
					}.getType());
		}

		try {
			String MEAS_UNIT_ID = unitService.txEdit(measUnitId, unitModel,
					userBean);
			jsonResult = jsonResult(true, MEAS_UNIT_ID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

	}

	/**
	 * 删除.
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
	public void delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String MEAS_UNIT_ID = ParamUtil.get(request, "MEAS_UNIT_ID");

		if (StringUtils.isNotEmpty(MEAS_UNIT_ID)) {
			try {
				unitService.txDelete(MEAS_UNIT_ID, userBean);
			} catch (Exception ex) {
				ex.printStackTrace();
				jsonResult = jsonResult(false, "删除失败");
			}
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 停用/启用.
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
	public void changeState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		String MEAS_UNIT_ID = ParamUtil.get(request, "MEAS_UNIT_ID");
		String STATE = ParamUtil.get(request, "STATE");
		if (STATE.equals("1")) {
			if (!QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			logger.info("按钮修改为停用单条记录开始");
			STATE = "停用";
		} else {
			if (!QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
				throw new ServiceException("对不起，您没有权限 !");
			}
			logger.info("按钮修改为启用单条记录开始");
			STATE = "启用";
		}
		if (unitService.updateState(MEAS_UNIT_ID, STATE, userBean)) {
			jsonResult = jsonResult(true, "状态修改成功");
		} else {
			jsonResult = jsonResult(false, "状态修改失败");
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
