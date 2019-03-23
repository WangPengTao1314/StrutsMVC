package com.hoperun.base.prdgroup.action;

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
import com.hoperun.base.prdgroup.model.PrdGroupDtlModel;
import com.hoperun.base.prdgroup.model.PrdGroupModel;
import com.hoperun.base.prdgroup.service.PrdGroupService;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sample.grant.action.GrantAction;
import com.hoperun.sys.model.UserBean;
/**
 * 货品组
 * @author zhang_zhongbin
 *
 */
public class PrdGroupAction extends BaseAction {

	/** 日志 **/
	private Logger logger = Logger.getLogger(GrantAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 维护界面
	// 增删改查
	private static String PVG_BWS = "XT0012501";
	private static String PVG_EDIT = "XT0012502";
	private static String PVG_DELETE = "XT0012503";
	// 启用,停用
	private static String PVG_START_STOP = "XT0012504";
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
	private static String AUD_TAB_NAME = "T_ERP_ZJ_CPBLTZD";
	private static String AUD_TAB_KEY = "groupID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "ERP_ZJGL_CPBLTZSP";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.PublicFlowInterface";

	private PrdGroupService prdGroupService;

	/**
	 * * to 框架页面
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
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("frames");
	}

	/**
	 * 主表列表 query List data
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
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		String PRD_GROUP_NO=ParamUtil.utf8Decoder(request, "PRD_GROUP_NO");
		params.put("PRD_GROUP_NOQuery", StringUtil.creCon("u.PRD_GROUP_NO", PRD_GROUP_NO, ","));
		ParamUtil.putStr2Map(request, "PRD_GROUP_NO", params);
		String PRD_GROUP_NAME=ParamUtil.utf8Decoder(request, "PRD_GROUP_NAME");
		params.put("PRD_GROUP_NAMEQuery", StringUtil.creCon("u.PRD_GROUP_NAME", PRD_GROUP_NAME, ","));
		ParamUtil.putStr2Map(request, "PRD_GROUP_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		
		String search = ParamUtil.get(request,"search");
		String module = ParamUtil.get(request,"module");
			//权限级别和审批流的封装
	    params.put("QXJBCON", ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY, AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = prdGroupService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
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
		String groupID = ParamUtil.get(request, "groupID");
		if (!StringUtil.isEmpty(groupID)) {
			List<PrdGroupDtlModel> result = prdGroupService.childQuery(groupID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}

	/**
	 * * * to 编辑框架页面
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
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("editFrame");
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
		String groupId = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(groupId)) {
			Map<String, String> entry = prdGroupService.load(groupId);
			request.setAttribute("rst", entry);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("toedit");
	}

	/**
	 * * 编辑框架页面加载子页面
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
		String groupId = ParamUtil.get(request, "groupID");
		if (!StringUtil.isEmpty(groupId)) {
			List<PrdGroupDtlModel> result = prdGroupService.childQuery(groupId);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childedit");
	}

	/**
	 * * to 直接编辑明细页面
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
	public ActionForward toChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 多个Id批量查询，用逗号隔开
		String MXIDS = request.getParameter("MXIDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(MXIDS)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("MXIDS", MXIDS);
			List<PrdGroupDtlModel> list = prdGroupService.loadChilds(params);
			request.setAttribute("rst", list);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childedit");
	}

	/**
	 * * 主表 新增/修改数据
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
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String groupID = ParamUtil.get(request, "groupID");
			String parentJsonData = ParamUtil.get(request, "parentJsonData");

			PrdGroupModel model = new Gson().fromJson(parentJsonData,
					new TypeToken<PrdGroupModel>() {
					}.getType());
			model.setPRD_GROUP_ID(groupID);

			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_GROUP_ID", model.getPRD_GROUP_ID());
			params.put("PRD_GROUP_NO", model.getPRD_GROUP_NO());
			boolean isNotHave = this.prdGroupService.queryForInt(params);
			int errorFlag = 0;
			
			if (isNotHave) {
				String jsonDate = ParamUtil.get(request, "childJsonData");
				String detIDs = request.getParameter("MXIDS");
				List<PrdGroupDtlModel> chldModelList = null;
				if (!StringUtil.isEmpty(jsonDate)) {
					chldModelList = new Gson().fromJson(jsonDate,
							new TypeToken<List<PrdGroupDtlModel>>() {
							}.getType());
					
					// 子表判断重复与否
					String returnMessage = this.vailChild(groupID, detIDs,chldModelList);
					if (!StringUtil.isEmpty(returnMessage)) {
						jsonResult = jsonResult(false, returnMessage);
						errorFlag = 1;
					}
				}
				
				if(errorFlag == 0){
					prdGroupService.txEdit(model, userBean, chldModelList);
				}
				
			} else {
				jsonResult = jsonResult(false, "货品组编号重复");
			}

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
	 * 明细重复判断
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private String vailChild(String pId, String chilIds,
			List<PrdGroupDtlModel> modelList) {

		String returnMessage = null;

		if ("''".equals(chilIds) || chilIds == "''") {
			chilIds = "";
		}

		int size = modelList.size();
		StringBuffer prdNOS = new StringBuffer();

		// 如果页面的之无重复，在和同一区域下的数据库的值判断
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				prdNOS.append("'" + modelList.get(i).getPRD_NO() + "'");
				if ((i + 1) != size) {
					prdNOS.append(",");
				}
			}

			Map<String, String> paramsMx = new HashMap<String, String>();
			paramsMx.put("PRD_GROUP_ID", pId);
			paramsMx.put("PRDNOS", prdNOS.toString());
			paramsMx.put("CHILDIDS", chilIds);
			List list = prdGroupService.findList(paramsMx);
			if (null != list && list.size() > 0) {
				returnMessage = "货品编号与已有的记录重复";
			}
		}

		return returnMessage;
	}
	/**
	 * * 子表 新增/修改数据
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
	public void childEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String groupId = request.getParameter("groupID");
			String jsonDate = request.getParameter("childJsonData");
			String MXIDS = request.getParameter("MXIDS");
			 
			if (!StringUtil.isEmpty(jsonDate)) {
				List<PrdGroupDtlModel> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<PrdGroupDtlModel>>() {
						}.getType());
				
				// 子表判断重复与否
				String returnMessage = this.vailChild(groupId, MXIDS,modelList);
				if (!StringUtil.isEmpty(returnMessage)) {
					jsonResult = jsonResult(false, returnMessage);
				}else{
					prdGroupService.txChildEdit(groupId, modelList);
				}
				 
			}
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
	 * * 主表 删除
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
			String groupID = ParamUtil.get(request, "groupID");
			prdGroupService.txDelete(groupID, userBean);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "删除失败!");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * 明细批量删除
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
			String detIDs = request.getParameter("MXIDS");
			// 批量删除，多个Id之间用逗号隔开
			prdGroupService.txBatch4DeleteChild(detIDs);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * to 详细信息
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
		String groupId = ParamUtil.get(request, "groupID");
		if (!StringUtil.isEmpty(groupId)) {
			Map<String, String> entry = prdGroupService.load(groupId);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
			String groupID = request.getParameter("groupID");

			Map<String, String> params = new HashMap<String, String>();
			String changedState = "";
			params.put("PRD_GROUP_ID", groupID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());

			changedState = BusinessConsts.JC_STATE_DISENABLE;
			params.put("STATE", changedState);

			prdGroupService.txStopById(params);
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
			String groupID = request.getParameter("groupID");

			Map<String, String> params = new HashMap<String, String>();
			String changedState = "";
			params.put("PRD_GROUP_ID", groupID);
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getYHM());
			changedState = BusinessConsts.JC_STATE_ENABLE;
			params.put("STATE", changedState);

			prdGroupService.txStopById(params);
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

	public PrdGroupService getPrdGroupService() {
		return prdGroupService;
	}

	public void setPrdGroupService(PrdGroupService prdGroupService) {
		this.prdGroupService = prdGroupService;
	}

	 

}
