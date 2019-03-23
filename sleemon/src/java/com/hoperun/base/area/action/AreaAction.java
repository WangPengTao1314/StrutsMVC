package com.hoperun.base.area.action;

import java.io.PrintWriter;
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
import com.hoperun.base.area.model.AreaChrgModel;
import com.hoperun.base.area.model.AreaModel;
import com.hoperun.base.area.model.AreaTree;
import com.hoperun.base.area.service.AreaChrgService;
import com.hoperun.base.area.service.AreaService;
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
 * 区域信息
 * 
 * @author zhang_zhongbin
 * 
 */
public class AreaAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(AreaAction.class);

	// 维护界面
	// 增删改查
	private static String PVG_BWS = "XT0012901";
	private static String PVG_EDIT = "XT0012902";
	private static String PVG_DELETE = "XT0012903";
	// 启用,停用
	private static String PVG_START_STOP = "XT0012904";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
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
	private static String AUD_BUSS_STATE = "";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";

	private AreaService areaService;
	private AreaChrgService areaChrgService;

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		logger.info("toFrame方法调用开始");

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		logger.info("toFrame方法调用结束");
		return mapping.findForward("frames");

	}

	/**
	 * 查询结果列表.
	 * 
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// chk权限
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "AREA_ID", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "AREA_NO_P", params);
		ParamUtil.putStr2Map(request, "AREA_NAME_P", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.setOrderField(request, params);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
			//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
			
		//只查询0的记录。1为删除。0为正常
        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		IListPage page = areaService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("list");
	}

	/**
	 * 显示树.
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
	public ActionForward showTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		// chk权限
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		List<AreaTree> trees;
		try {
			trees = areaService.getTree();
			String treeData = new Gson().toJson(trees);
			request.setAttribute("tree", treeData);
			request.setAttribute("pvg", setPvg(userBean));
			return mapping.findForward("tree");
		} catch (Exception e) {
			request.setAttribute("msg", "获区域信息失败！");
			return mapping.findForward(FAILURE);
		}
	}

	/**
	 * 查看区域详细信息.
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
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String areaID = ParamUtil.get(request, "areaID");
		if (StringUtils.isNotEmpty(areaID)) {
			Map<String, String> entry = areaService.load(areaID);
			request.setAttribute("rst", entry);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("view");
	}

	/**
	 * 区域信息编辑 保存.
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

		String parentJsonData = ParamUtil.get(request, "parentJsonData");

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		AreaModel model = new AreaModel();

		if (StringUtils.isNotEmpty(parentJsonData)) {
			model = new Gson().fromJson(parentJsonData,
					new TypeToken<AreaModel>() {
					}.getType());
		}

		String areaID = model.getAREA_ID();
		String AREA_ID_P = model.getAREA_ID_P();

		Map<String, String> params = new HashMap<String, String>();
		params.put("AREA_ID", areaID);
		params.put("AREA_NO", model.getAREA_NO());
		// 主表编号重复判断
		boolean isHaveNo = areaService.queryForInt(params);

		int errorFlag = 0;
		if (isHaveNo) {
			if (!areaService.loadAreaState(AREA_ID_P)) {
				jsonResult = jsonResult(false, "不可选择停用的区域作为上级区域");
				errorFlag = 1;
			}
		} else {
			jsonResult = jsonResult(false, "该编号已存在");
			errorFlag = 1;
		}

		// 判断子表 数据重复情况
		String jsonDate = ParamUtil.get(request, "childJsonData");
		String areaChrgIds = ParamUtil.get(request, "areaChrgIds");

		List<AreaChrgModel> modelList = this.getChildList(jsonDate);
		if (null != modelList && modelList.size() > 0) {
			// 子表判断重复与否
			String returnMessage = this.vailChild(areaID, areaChrgIds,
					modelList);
			if (!StringUtil.isEmpty(returnMessage)) {
				jsonResult = jsonResult(false, returnMessage);
				errorFlag = 1;
			}
		}

		try {
			if (0 == errorFlag) {
				areaID = areaService.txEdit(model, userBean, modelList);
				jsonResult = jsonResult(true, areaID);
			}

		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 解析明细
	 * 
	 * @param jsonDate
	 * @return
	 */
	private List<AreaChrgModel> getChildList(String jsonDate) {

		List<AreaChrgModel> modelList = null;
		if (StringUtils.isNotEmpty(jsonDate)) {
			modelList = new Gson().fromJson(jsonDate,
					new TypeToken<List<AreaChrgModel>>() {
					}.getType());
		}

		return modelList;

	}

	/**
	 * 明细重复判断
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String vailChild(String areaId, String areaChrgIds,
			List<AreaChrgModel> modelList) {

		String returnMessage = null;
		if ("''".equals(areaChrgIds) || areaChrgIds == "''") {
			areaChrgIds = "";
		}
		int size = modelList.size();
		StringBuffer CHRGNOS = new StringBuffer();

		// 如果页面的之无重复，在和同一区域下的数据库的值判断
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				CHRGNOS.append("'" + modelList.get(i).getCHRG_NO() + "'");
				if ((i + 1) != size) {
					CHRGNOS.append(",");
				}
			}

			Map<String, String> paramsMx = new HashMap<String, String>();
			paramsMx.put("AREA_ID", areaId);
			paramsMx.put("CHRGNOS", CHRGNOS.toString());
			paramsMx.put("AREACHRGIDS", areaChrgIds);
			List list = areaChrgService.findList(paramsMx);
			if (null != list && list.size() > 0) {
				returnMessage = "分管对象编号与已有的记录重复";
			}
		}

		return returnMessage;
	}

	/**
	 * 明细保存
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void childEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		int errorFlg = 0;
		try {
			String areaID = ParamUtil.utf8Decoder(request, "areaID");
			String areaChrgIds = request.getParameter("areaChrgIds");
			if ("''".equals(areaChrgIds) || areaChrgIds == "''") {
				areaChrgIds = "";
			}
			String jsonDate = request.getParameter("childJsonData");
			if (StringUtils.isNotEmpty(jsonDate)) {

				// 明细去重复判断

				List<AreaChrgModel> modelList = this.getChildList(jsonDate);
				if(null != modelList && modelList.size()>0){
					String returnMessage = this.vailChild(areaID, areaChrgIds,
							modelList);
					if (!StringUtil.isEmpty(returnMessage)) {
						jsonResult = jsonResult(false, returnMessage);
						errorFlg = 1;
					}
					if (errorFlg != 1) {
						this.areaService.txChildEdit(areaID, modelList);
					}
				}

			}

		} catch (Exception e) {
			jsonResult = jsonResult(false, "保存失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

	}

	/**
	 * 到明细编辑页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		String areaChrgIds = request.getParameter("areaChrgIds");
		//String chistate = ParamUtil.utf8Decoder(request, "chistate");
		if (StringUtils.isNotEmpty(areaChrgIds)) {
			List<AreaChrgModel> list = this.areaChrgService
					.loadChilds(areaChrgIds);
			request.setAttribute("rst", list);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childedit");
	}

	/**
	 * 区域分管明细列表 Description :.
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
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String areaID = ParamUtil.get(request, "areaID");
		if (StringUtils.isNotEmpty(areaID)) {
			List<AreaChrgModel> list = this.areaChrgService.childQuery(areaID);
			request.setAttribute("rst", list);
		}

		request.setAttribute("pvg", setPvg(userBean));
		// 为空直接跳转显示页面，而不是错误提示。
		return mapping.findForward("childlist");
	}

	/**
	 * 分管信息明细批量删除 软删除 Description :.
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
	public void childDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			//区域分管ID
			String areaChrgIds = request.getParameter("areaChrgIds");
			//人员信息ID
			String CHRG_IDS = request.getParameter("CHRG_IDS");
			//区域ID
			String AREA_ID = request.getParameter("AREA_ID");
			
			// 批量删除，多个Id之间用逗号隔开  同时删除扁平表的相关信息
			areaChrgService.txBatch4DeleteChild(AREA_ID,areaChrgIds,CHRG_IDS, userBean);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 主从表编辑框架页面.
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
	public ActionForward toEditFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		// 设置跳转时需要的一些公用属性
		logger.info("toEditFrame方法调用开始");
		ParamUtil.setCommAttributeEx(request);
		logger.info("toEditFrame方法调用结束");
		return mapping.findForward("editFrame");
	}

	/**
	 * 编码规则维护编辑页面加载子页面 Description :.
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
	public ActionForward modifyToChildEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		logger.info("modifyToChildEdit方法调用开始");
		String areaId = ParamUtil.utf8Decoder(request, "areaID");// ParamUtil.get(request,
		// "BMGZID");
		if (StringUtils.isNotEmpty(areaId)) {
			List<AreaChrgModel> result = this.areaChrgService
					.childQuery(areaId);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		logger.info("modifyToChildEdit方法调用结束");
		return mapping.findForward("childedit");
	}

	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String areaID = ParamUtil.get(request, "areaID");
		if (StringUtils.isNotEmpty(areaID)) {
			Map<String, String> entry = areaService.load(areaID);
			request.setAttribute("rst", entry);
		}

		return mapping.findForward("toedit");

	}

	/**
	 * * to 主表编辑页面初始化
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
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("toParentEdit方法调用开始");
		String selRowId = StringUtil.utf8Decoder(ParamUtil.get(request,
				"selRowId"));// ParamUtil.utf8Decoder(request, "BMGZID");//;
		// 为空则是新增，否则是修改
		if (StringUtils.isNotEmpty(selRowId)) {
			Map<String, String> entry = areaService.load(selRowId);
			request.setAttribute("rst", entry);
			request.setAttribute("isNew", false);
		} else {
			request.setAttribute("isNew", true);
		}
		request.setAttribute("pvg", setPvg(userBean));
		logger.info("toParentEdit方法调用结束");
		return mapping.findForward("toedit");
	}

	/**
	 * 主表删除. 软删除
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
		try {
			String areaID = ParamUtil.get(request, "areaID");
			areaService.txDelete(areaID, userBean);
			areaService.clearCaches(areaID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
		}

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 按钮修改状态为停用.
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
	public void changeStateTy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("按钮修改为停用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		boolean isChanged = false;
		try {
			logger.warn("取得对应记录的状态");
			String areaID = request.getParameter("areaID");

			Map<String, String> params = new HashMap<String, String>();
			String changedState = "";
			params.put("AREA_ID", areaID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());

			changedState = BusinessConsts.JC_STATE_DISENABLE;
			params.put("STATE", changedState);

			areaService.txStopById(params);
			isChanged = true;

			if (isChanged) {
				jsonResult = jsonResult(true, changedState);
			} else {
				jsonResult = jsonResult(false, "状态不用修改");
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "状态修改失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 按钮修改状态为启用.
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
	public void changeStateQy(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_START_STOP)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("按钮修改为启用单条记录开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		boolean isChanged = false;
		try {
			logger.warn("取得对应记录的状态");
			String areaID = request.getParameter("areaID");

			Map<String, String> params = new HashMap<String, String>();
			String changedState = "";
			params.put("AREA_ID", areaID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			changedState = BusinessConsts.JC_STATE_ENABLE;
			params.put("STATE", changedState);

			areaService.txStopById(params);
			isChanged = true;

			if (isChanged) {
				jsonResult = jsonResult(true, changedState);
			} else {
				jsonResult = jsonResult(false, "状态不用修改");
			}
		} catch (Exception e) {
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

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public AreaChrgService getAreaChrgService() {
		return areaChrgService;
	}

	public void setAreaChrgService(AreaChrgService areaChrgService) {
		this.areaChrgService = areaChrgService;
	}

}
