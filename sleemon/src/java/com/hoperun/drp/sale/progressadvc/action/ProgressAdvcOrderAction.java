package com.hoperun.drp.sale.progressadvc.action;

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
import com.hoperun.drp.sale.advcorder.action.AdvcorderAction;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.progressadvc.service.ProgressAdvcOrderService;
import com.hoperun.sys.model.UserBean;

public class ProgressAdvcOrderAction extends BaseAction{
	
	/** 日志 **/
	private Logger logger = Logger.getLogger(AdvcorderAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0020901";
	private static String PVG_EDIT = "FX0020902";
	private static String PVG_DELETE = "FX0020903";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
 
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "FX0020904";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_RETURN="";//退回
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	
	private ProgressAdvcOrderService progressAdvcOrderService;
	
	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("frames");
	}
	
	
	
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
		ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
		ParamUtil.putStr2Map(request, "CUST_NAME", params);
		ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
		ParamUtil.putStr2Map(request, "PROMOTE_NO", params);
		ParamUtil.putStr2Map(request, "PROMOTE_NAME", params);
		ParamUtil.putStr2Map(request, "CLOSEFLAG", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		ParamUtil.putStr2Map(request, "FROM_BILL_NO", params);
		
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search, "", PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		// 初始时只显示未接收的数据
		if(StringUtil.isEmpty(search)){
			qx.append(" and u.STATE='待完善' ");	
		}else{
			qx.append(" and u.STATE in ('待完善','未提交') ");
		}
		qx.append(" and u.CREATOR='LA' ");	
		params.put("QXJBCON", qx.toString());
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		// 设置终端销售
		params.put("BILL_TYPE", "终端销售");
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = progressAdvcOrderService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		return mapping.findForward("list");
	}

	/**
	 * * 货品明细列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld> result = progressAdvcOrderService.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
	}
	
	
	/**
	 * * 付款明细列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return the action forward
	 */
	public ActionForward gchildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelGchld> result = progressAdvcOrderService
			.gchildQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
	}

	/**
	 * * * to 编辑框架页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
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
	 * @param form
	 * @param request
	 * @param response
	 * @return the action forward
	 */
	public ActionForward toParentEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String, String> entry = new HashMap<String, String>();
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			entry = progressAdvcOrderService.load(ADVC_ORDER_ID);
		}  
		request.setAttribute("rst", entry);
		request.setAttribute("LEDGER_ID",userBean.getLoginZTXXID());
		return mapping.findForward("toedit");
	}

	/**
	 * * 编辑框架页面加载子页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return the action forward
	 */
	public ActionForward modifyToChildEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld> result = progressAdvcOrderService.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("childedit");
	}

	/**
	 * * to 直接编辑明细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return the action forward
	 */
	public ActionForward toChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 多个Id批量查询，用逗号隔开
		String ADVC_ORDER_DTL_IDS = request.getParameter("ADVC_ORDER_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(ADVC_ORDER_DTL_IDS)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
			List<AdvcorderModelChld> list = progressAdvcOrderService.loadChilds(params);
			request.setAttribute("rst", list);
		}
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		return mapping.findForward("childedit");
	}

	/**
	 * * 主表 新增/修改数据
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
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
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			String parentJsonData = ParamUtil.get(request, "parentJsonData");
			AdvcorderModel model = new Gson().fromJson(parentJsonData,
					new TypeToken<AdvcorderModel>(){}.getType());
			String jsonDate = ParamUtil.get(request, "childJsonData");
			List<AdvcorderModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<AdvcorderModelChld>>() {
						}.getType());
			}
			progressAdvcOrderService.txEdit(ADVC_ORDER_ID,model,chldModelList,userBean);

		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
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
	 * * 子表 新增/修改数据
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
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
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<AdvcorderModelChld> modelList = new Gson().fromJson(jsonDate, 
						new TypeToken<List<AdvcorderModelChld>>(){}.getType());
				progressAdvcOrderService.txChildEdit(ADVC_ORDER_ID, modelList, userBean);
				jsonResult = jsonResult(true, ADVC_ORDER_ID);
			}
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
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
	 * * to 付款明细编辑页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return the action forward
	 */
	public ActionForward toGchildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 多个Id批量查询，用逗号隔开
		String PAYMENT_DTL_IDS = request.getParameter("PAYMENT_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(PAYMENT_DTL_IDS)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("PAYMENT_DTL_IDS", PAYMENT_DTL_IDS);
			List<AdvcorderModelGchld> list = progressAdvcOrderService
					.loadGchilds(params);
			request.setAttribute("rst", list);
		}
		
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("gchildedit");
	}
	
	
	/**
	 * 付款明细保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void gchildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			String jsonDate = request.getParameter("gchildJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<AdvcorderModelGchld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<AdvcorderModelGchld>>() {
						}.getType());
				progressAdvcOrderService.txGchildEdit(ADVC_ORDER_ID, modelList);
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
	 * * 付款明细批量删除
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void gchildDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PAYMENT_DTL_IDS = request.getParameter("PAYMENT_DTL_IDS");
			// 批量删除，多个Id之间用逗号隔开
			progressAdvcOrderService.txBatch4DeleteGchild(PAYMENT_DTL_IDS);
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
	 * 主表详细页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			Map<String, String> entry = progressAdvcOrderService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}
	
	
	/**
	 * * 主表 删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
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
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			progressAdvcOrderService.txDelete(params);
			progressAdvcOrderService.clearCaches(params);
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
	 * 提交
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			progressAdvcOrderService.txToCommit(ADVC_ORDER_ID, userBean);
			jsonResult = jsonResult(true, "提交成功");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "提交失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	
	/**
	 * 撤销
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toRevoke(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			progressAdvcOrderService.txRevoke(ADVC_ORDER_ID, userBean);
			jsonResult = jsonResult(true, "撤销成功");
		} catch (Exception e) {
			jsonResult = jsonResult(false, "撤销失败");
			e.printStackTrace();
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
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean, PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean, PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_RETURN", QXUtil.checkPvg(userBean,PVG_RETURN));
		return pvgMap;
	}

	public ProgressAdvcOrderService getProgressAdvcOrderService() {
		return progressAdvcOrderService;
	}

	public void setProgressAdvcOrderService(
			ProgressAdvcOrderService progressAdvcOrderService) {
		this.progressAdvcOrderService = progressAdvcOrderService;
	}
	
	

}
