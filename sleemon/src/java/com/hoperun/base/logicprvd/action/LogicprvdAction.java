/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：LogicprvdAction.java
 */
package com.hoperun.base.logicprvd.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.hoperun.base.logicprvd.model.LogicprvdModel;
import com.hoperun.base.logicprvd.service.LogicprvdService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class LogicprvdAction.
 * 
 * @module 系统管理
 * @func 物流供应商管理
 * @version 1.0
 * @author 王栋斌
 */
public class LogicprvdAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(LogicprvdAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0010401";
	private static String PVG_EDIT = "BS0010402";
	private static String PVG_DELETE = "BS0010403";
	// 启用,停用
	private static String PVG_START_STOP = "BS0010404";
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

	private LogicprvdService logicprvdService;

	/**
	 * get the logicprvdService
	 * 
	 * @return logicprvdService
	 */
	public LogicprvdService getLogicprvdService() {
		return logicprvdService;
	}

	/**
	 * set the logicprvdService
	 * 
	 * @param logicprvdService
	 *            the logicprvdService
	 */
	public void setLogicprvdService(LogicprvdService logicprvdService) {
		this.logicprvdService = logicprvdService;
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
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = logicprvdService.pageQuery(params, pageNo);
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
			Map<String, String> entry = logicprvdService.load(prvd_id);
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
		LogicprvdModel logicprvdModel = new LogicprvdModel();
		if (!StringUtil.isEmpty(jsonData)) {
			logicprvdModel = new Gson().fromJson(jsonData,
					new TypeToken<LogicprvdModel>() {
					}.getType());
		}
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRVD_ID", prvd_id);
		params.put("DELFLAG", BusinessConsts.DEL_FLAG_COMMON);
		try {
			Boolean flag = logicprvdService.txEdit(prvd_id, logicprvdModel, userBean);
			if (flag) {
				jsonResult = jsonResult(true, "保存成功");
			} else {
				jsonResult = jsonResult(false, "编号已存在");
			}
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
			Map<String, String> entry = logicprvdService.load(prvd_id);
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
			logicprvdService.txDelete(params);
			logicprvdService.clearCaches(params);
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
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
			logicprvdService.txStartById(params);
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
			params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
			logicprvdService.txStopById(params);
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

	/**
	 * 车辆信息一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return trucklist
	 */
	public ActionForward toListTruck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		if (!QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		if (!StringUtil.isEmpty(prvd_id)) {
			List<LogicprvdModel> entry = logicprvdService.truckList(prvd_id);
			request.setAttribute("PRVD_ID",prvd_id);
			request.setAttribute("result", entry);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("trucklist");
	}

	/**
	 * 车辆信息编辑页面跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return truckedit
	 */
	public ActionForward toEditTruck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String truck_id = ParamUtil.get(request, "TRUCK_ID");
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		if (!StringUtil.isEmpty(truck_id)) {
			List<LogicprvdModel> entry = logicprvdService.toEditTruck(truck_id);
			request.setAttribute("result", entry);
		} else {
			List<LogicprvdModel> entry = new ArrayList<LogicprvdModel>();
			LogicprvdModel logicprvdModel = new LogicprvdModel();
			logicprvdModel.setPRVD_ID(prvd_id);
			entry.add(logicprvdModel);
			request.setAttribute("result", entry);
		}
		return mapping.findForward("truckedit");
	}

	/**
	 * 车辆信息记录删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void deleteTruck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String truck_id = ParamUtil.get(request, "TRUCK_ID");
			logicprvdService.truckDelete(truck_id);
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
	 * 车辆信息状态变更
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void changeTruckState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String state = request.getParameter("STATE");
		String TRUCK_IDS = request.getParameter("TRUCK_IDS");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		Map<String, String> params = new HashMap<String, String>();
		params.put("TRUCK_IDS", TRUCK_IDS);
		if (state.equals("1")) {
			logger.info("按钮修改为启用单条记录开始");
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		} else {
			logger.info("按钮修改为停用单条记录开始");
			params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
		}

		try {
			logger.warn("取得对应记录的状态");
			logicprvdService.changeTruckState(params);
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
	 * 车辆信息新增/修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void saveTruck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String jsonData = ParamUtil.get(request, "jsonData");
		String prvdId = ParamUtil.get(request, "PRVD_ID");
		List<LogicprvdModel> logicprvdModel = new ArrayList<LogicprvdModel>();
		
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		if (StringUtils.isNotEmpty(jsonData)) {
			logicprvdModel = new Gson().fromJson(jsonData,
					new TypeToken<List<LogicprvdModel>>() {
					}.getType());
		}
		try{
			logicprvdService.truckSave(logicprvdModel, prvdId);
			jsonResult = jsonResult(true, "保存成功!");
		}catch(Exception e){
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 合并路线一览
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return waymergelist
	 */
	public ActionForward toListWaymerge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		
		if (!QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		
		// 权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		if (!StringUtil.isEmpty(prvd_id)) {
			List<LogicprvdModel> entry = logicprvdService.waymergeList(prvd_id);
			request.setAttribute("PRVD_ID",prvd_id);
			request.setAttribute("result", entry);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("waymergelist");
	}
	
	/**
	 * 合并路线编辑页面跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return waymergeedit
	 */
	public ActionForward toEditWaymerge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String way_merge_id = ParamUtil.get(request, "WAY_MERGE_ID");
		String prvd_id = ParamUtil.get(request, "PRVD_ID");
		if (!StringUtil.isEmpty(way_merge_id)) {
			List<LogicprvdModel> entry = logicprvdService.toEditWaymerge(way_merge_id);
			request.setAttribute("result", entry);
		} else {
			List<LogicprvdModel> entry = new ArrayList<LogicprvdModel>();
			LogicprvdModel logicprvdModel = new LogicprvdModel();
			logicprvdModel.setPRVD_ID(prvd_id);
			entry.add(logicprvdModel);
			request.setAttribute("result", entry);
		}
		return mapping.findForward("waymergeedit");
	}

	/**
	 * 合并路线记录删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void deleteWaymerge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String way_merge_id = ParamUtil.get(request, "WAY_MERGE_ID");
			logicprvdService.waymergeDelete(way_merge_id);
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
	 * 合并路线状态变更
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void changeWaymergeState(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String state = request.getParameter("STATE");
		String WAY_MERGE_IDS = request.getParameter("WAY_MERGE_IDS");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		Map<String, String> params = new HashMap<String, String>();
		params.put("WAY_MERGE_IDS", WAY_MERGE_IDS);
		if (state.equals("1")) {
			logger.info("按钮修改为启用单条记录开始");
			params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		} else {
			logger.info("按钮修改为停用单条记录开始");
			params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);
		}

		try {
			logger.warn("取得对应记录的状态");
			logicprvdService.changeWaymergeState(params);
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
	 * 合并路线新增/修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void saveWaymerge(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String jsonData = ParamUtil.get(request, "jsonData");
		String prvdId = ParamUtil.get(request, "PRVD_ID");
		List<LogicprvdModel> logicprvdModel = new ArrayList<LogicprvdModel>();
		
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		if (StringUtils.isNotEmpty(jsonData)) {
			logicprvdModel = new Gson().fromJson(jsonData,
					new TypeToken<List<LogicprvdModel>>() {
					}.getType());
		}
		try{
			Boolean flag = logicprvdService.waymergeSave(logicprvdModel, prvdId);
			if (flag) {
				jsonResult = jsonResult(true, "保存成功!");
			}
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 合并路线明细页面跳转
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return waymergedtledit
	 */
	public ActionForward toEditWaymergeDtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		
		if (!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String way_merge_id = ParamUtil.get(request, "WAY_MERGE_ID");
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		
		// 权限级别和审批流的封装以及状态的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		if (!StringUtil.isEmpty(way_merge_id)) {
			List<LogicprvdModel> entry = logicprvdService.toEditWaymergedtl(way_merge_id);
			if (entry.size()>0){
				request.setAttribute("result", entry);
			} else {
				LogicprvdModel logicprvdModel = new LogicprvdModel();
				logicprvdModel.setWAY_MERGE_ID(way_merge_id);
				entry.add(logicprvdModel);
				request.setAttribute("result", entry);
			}
			
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("waymergedtledit");
	}
	
	/**
	 * 合并路线明细删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void deleteWaymergedtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String way_merge_dtl_id = ParamUtil.get(request, "WAY_MERGE_DTL_ID");
			System.out.println(way_merge_dtl_id);
			logicprvdService.waymergedtlDelete(way_merge_dtl_id);
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
	 * 合并路线明细新增/修改
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void saveWaymergedtl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		UserBean userBean = ParamUtil.getUserBean(request);
		if (!QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String jsonData = ParamUtil.get(request, "jsonData");
		String waymergeId = ParamUtil.get(request, "WAY_MERGE_ID");
		List<LogicprvdModel> logicprvdModel = new ArrayList<LogicprvdModel>();
		
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		if (StringUtils.isNotEmpty(jsonData)) {
			logicprvdModel = new Gson().fromJson(jsonData,
					new TypeToken<List<LogicprvdModel>>() {
					}.getType());
		}
		try{
			logicprvdService.waymergedtlSave(logicprvdModel, waymergeId);
			jsonResult = jsonResult(true, "保存成功!");
		}catch(Exception e){
			e.printStackTrace();
			jsonResult = jsonResult(false, "保存失败");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
}
