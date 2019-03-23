package com.hoperun.erp.visit.intentionvisit.action;

import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.visit.intentionvisit.model.IntentionVisitModel;
import com.hoperun.erp.visit.intentionvisit.service.IntentionVisitService;
import com.hoperun.sys.model.UserBean;

/**
 *意向客户拜访报表
 * 
 * @author zhang_zhongbin
 * 
 */
public class IntentionVisitAction extends BaseAction {

	private IntentionVisitService intentionVisitService;

	// 增删改查
	private static String PVG_BWS = "BS0034001";
	private static String PVG_EDIT = "BS0034002";
	private static String PVG_DELETE = "BS0034003";

	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0034004";
	// 流程跟踪
	private static String PVG_TRACE = "BS0034005";
	// 审核模块
	private static String PVG_BWS_AUDIT = "BS0034101";
	private static String PVG_AUDIT_AUDIT = "BS0034102";
	private static String PVG_TRACE_AUDIT = "BS0034103";

	// 审批流参数
	private static String AUD_TAB_NAME = "ERP_INTENTION_CHANN";
	private static String AUD_TAB_KEY = "INTE_CHANN_ID";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "ERP_INTENTION_CHANN_AUDIT";
	private static String AUD_FLOW_INS = "com.hoperun.sys.service.PublicFlowInterface";

	/**
	 * * to 框架页面
	 * 
	 * @param mapping  the mapping
	 * @param form   the form
	 * @param request the request
	 * @param response  the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& ((!QXUtil.checkMKQX(userBean, PVG_BWS)) && (!QXUtil
						.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,"paramUrl"));
		request.setAttribute("module", ParamUtil.get(request, "module"));
		return mapping.findForward("frames");
	}

	/**
	 * 拓展拜访维护列表 Description :.
	 * @param mapping the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response  the response
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 权限级别
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && ((!QXUtil.checkMKQX(userBean, PVG_BWS))
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT)))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new TreeMap<String, String>();
		ParamUtil.putStr2Map(request, "INTE_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "APPLY_PERSON_NAME", params);
		ParamUtil.putStr2Map(request, "APPLY_DEP", params);
		ParamUtil.putStr2Map(request, "APPLY_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "APPLY_DATE_END", params);
		ParamUtil.putStr2Map(request, "INTE_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "INTE_CUSTOMER_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		ParamUtil.putStr2Map(request, "CITY", params);

		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");

		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));

		params.put("QXJBCON", qx.toString());
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		request.setAttribute("module", module);
		// 渠道分管 
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		IListPage page = intentionVisitService.pageQuery(params, pageNo);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("module", module);
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		request.setAttribute("module", module);
		return mapping.findForward("list");
	}

	/**
	 * 修改页面跳转 Description :.
	 * 
	 * @param mapping  the mapping
	 * @param form  the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String INTE_CHANN_ID = ParamUtil.get(request, "INTE_CHANN_ID");
		Map<String, String> entry = null;
		if (StringUtils.isNotEmpty(INTE_CHANN_ID)) {
			entry = intentionVisitService.loadById(INTE_CHANN_ID);
		} else {
			entry = new HashMap<String, String>();
			entry.put("APPLY_PERSON_ID", userBean.getRYXXID());
			entry.put("APPLY_PERSON_NAME", userBean.getXM());
			entry.put("APPLY_DEP", userBean.getBMMC());
			entry.put("APPLY_DATE", DateUtil.format(new Date(), "yyyy-MM-dd"));
		}
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("CHANN_ID", userBean.getCHANNS_CHARG());
		request.setAttribute("rst", entry);
		return mapping.findForward("toedit");
	}

	/**
	 * 拓展拜访表详细信息 Description :.
	 * 
	 * @param mapping  the mapping
	 * @param form   the form
	 * @param request the request
	 * @param response the response
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
		String INTE_CHANN_ID = ParamUtil.get(request, "INTE_CHANN_ID");
		if (StringUtils.isNotEmpty(INTE_CHANN_ID)) {
			Map<String, String> entry = intentionVisitService.loadById(INTE_CHANN_ID);
			List<Map<String,Object>>flowInfoList = LogicUtil.getFlowInfos(INTE_CHANN_ID);
		    request.setAttribute("flowInfoList", flowInfoList);
			request.setAttribute("rst", entry);
		}
		
		return mapping.findForward("todetail");
	}

	/**
	 * 拓展拜访表维护编辑//新增或修改。 Description :.
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
		String jsonData = ParamUtil.get(request, "jsonData");
		String INTE_CHANN_ID = ParamUtil.get(request, "INTE_CHANN_ID");
		try {
			if (!StringUtil.isEmpty(jsonData)) {
				IntentionVisitModel model = new Gson().fromJson(jsonData,
						new TypeToken<IntentionVisitModel>() {
						}.getType());
				intentionVisitService.txEdit(INTE_CHANN_ID, model, userBean);
				jsonResult = jsonResult(true, "保存成");
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
	 * 拓展拜访表删除 Description :.
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
		String INTE_CHANN_ID = ParamUtil.get(request, "INTE_CHANN_ID");
		if (StringUtils.isNotEmpty(INTE_CHANN_ID)) {
			try {
				intentionVisitService.txDelete(INTE_CHANN_ID, userBean);
				jsonResult = jsonResult(true, "删除成功");
			} catch (RuntimeException e) {
				jsonResult = jsonResult(false, "删除失败");
			}
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 子表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toChild(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) {
		String INTE_CHANN_ID = ParamUtil.get(request, "INTE_CHANN_ID"); 
		boolean isWirte = false;
		String STATE = null;
		if(!StringUtil.isEmpty(INTE_CHANN_ID)){
			Map<String,String> model = intentionVisitService.loadById(INTE_CHANN_ID);
			STATE = StringUtil.nullToSring(model.get("STATE"));
			if("未提交".equals(STATE)){
				isWirte = true;
			}
		}
		
		StringBuffer sql = new StringBuffer();
		sql.append("dtlSql = select * from ERP_INTENTION_CHANN_DTL u where u.INTE_CHANN_ID='")
		.append(INTE_CHANN_ID).append("'")
		.append(";")
		.append("ID=").append(INTE_CHANN_ID)
		.append(";")
		.append("isWirte=").append(isWirte);
		
		request.setAttribute("STATE", STATE);
		
		// 要传递的宏参数！！！
		request.setAttribute("params", sql.toString());
		// 报表路径名称                  
		request.setAttribute("raq", "IntentionVisit_Child.raq");
		return mapping.findForward("fillReportShow");
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
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil
				.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		return pvgMap;
	}

	public IntentionVisitService getIntentionVisitService() {
		return intentionVisitService;
	}

	public void setIntentionVisitService(
			IntentionVisitService intentionVisitService) {
		this.intentionVisitService = intentionVisitService;
	}

}
