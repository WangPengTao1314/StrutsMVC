/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderAction
 */
package com.hoperun.drp.sale.uncomm.action;

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
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.uncomm.service.UncommService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class UncommAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(UncommAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0022401";
	private static String PVG_EDIT = "FX0022404";
	private static String PVG_DELETE = "FX0022402";
	private static String PVG_CLOSE="FX0022405";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "FX0022403";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	/** 审批 end **/
	/** 业务service */
	private UncommService uncommService;
	
	/**
	 * @return the uncommService
	 */
	public UncommService getUncommService() {
		return uncommService;
	}

	/**
	 * @param uncommService the uncommService to set
	 */
	public void setUncommService(UncommService uncommService) {
		this.uncommService = uncommService;
	}

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
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("frames");
	}

	/**
	 * * query List data
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
		ParamUtil.putStr2Map(request, "ADVC_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NO", params);
		ParamUtil.putStr2Map(request, "TERM_NAME", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "SALE_DATE_END", params);
		ParamUtil.putStr2Map(request, "SALE_PSON_NAME", params);
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "ORDER_RECV_DATE_END", params);
		ParamUtil.putStr2Map(request, "CUST_NAME", params);
		ParamUtil.putStr2Map(request, "BEGIN_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "END_CRE_TIME", params);
		ParamUtil.putStr2Map(request, "CONTRACT_NO", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
	 
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));

		if(StringUtil.isEmpty(search)){
			params.put("STATE", "'待确认'");
		}else{
			params.put("STATE", "'待确认','关闭'");
		}
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		// 设置终端销售
		params.put("BILL_TYPE", "终端销售");
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE,u.ADVC_ORDER_NO", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = uncommService.pageQuery(params, pageNo);
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
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld> result = uncommService
					.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
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
		String ADVC_ORDER_DTL_IDS = request.getParameter("ADVC_ORDER_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(ADVC_ORDER_DTL_IDS)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
			params.put("USE_FLAG", BusinessConsts.DEL_FLAG_DROP);
			List<AdvcorderModelChld> list = uncommService.loadChilds(params);
			request.setAttribute("rst", list);
		}
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
		return mapping.findForward("childedit");
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
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			String jsonDate = request.getParameter("childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<AdvcorderModelChld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<AdvcorderModelChld>>() {
						}.getType());
				uncommService.txChildEdit(ADVC_ORDER_ID, modelList, userBean);
				uncommService.txgetPAYABLE_AMOUNT(userBean, ADVC_ORDER_ID);
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
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			uncommService.txDelete(params);
			uncommService.clearCaches(params);
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
			String ADVC_ORDER_DTL_IDS = request
					.getParameter("ADVC_ORDER_DTL_IDS");
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			// 批量删除，多个Id之间用逗号隔开
			uncommService.txBatch4DeleteChild(ADVC_ORDER_DTL_IDS,
					ADVC_ORDER_ID);
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
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			Map<String, String> entry = uncommService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}

	/**
	 * * 提交时，校验是否有明细.
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
	public void toCommit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		logger.info("按钮修改为启用单条记录开始");
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			List<AdvcorderModelGchld> list = uncommService.gchildQuery(ADVC_ORDER_ID);
			List<AdvcorderModelChld> chldList = uncommService.childQuery(ADVC_ORDER_ID);
			if (chldList.size() == 0) {
				throw new ServiceException("没有明细信息，不能提交!");
			}
			double amount=uncommService.amountGetById(ADVC_ORDER_ID);
			double dtlAmount=uncommService.payAmountGetById(ADVC_ORDER_ID);
			if (list.size() == 0&&amount>0) {
				throw new ServiceException("付款明细没有明细信息，不能提交!");
			}
			if (dtlAmount != amount&&amount>0) {
				throw new ServiceException("付款明细的付款金额之和应该等于主表的订金金额，请检查后重新提交!");
			} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
					map.put("STATE", BusinessConsts.PASS);
					map.put("UPDATOR", userBean.getRYXXID());
					map.put("UPD_NAME", userBean.getXM());
					map.put("UPD_TIME", "数据库时间");
					map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					map.put("PAYED_TOTAL_AMOUNT", "订金金额");
					String str=uncommService.checkMonthAcc(ADVC_ORDER_ID, userBean);
					if(!StringUtil.isEmpty(str)){
						throw new ServiceException(str);
					}
					uncommService.upStateById(map);
//					MsgInfo mesgObj = uncommService.txCommitById(map,
//							userBean);
//					if (!mesgObj.isFLAG()) {
//						jsonResult = jsonResult(false, mesgObj.getMESS());
//					} else {
//						jsonResult = jsonResult(true, "");
//					}
				}
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
		} catch (Exception e) {
			jsonResult = jsonResult(false, "提交失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	
	/**
	 * * 提交时，校验是否有明细.
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
	public void toClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		logger.info("按钮修改为启用单条记录开始");
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			Map<String, String> map = new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("STATE", BusinessConsts.COMMON_COLSE);
			map.put("DEL_FLAG", "0");
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			uncommService.txClose(map);
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
		} catch (Exception e) {
			jsonResult = jsonResult(false, "关闭失败");
			logger.info(e);
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
		pvgMap.put("PVG_CLOSE", QXUtil.checkPvg(userBean, PVG_CLOSE));
		
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
	public ActionForward gchildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelGchld> result = uncommService
					.gchildQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
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
		String updateFlag=ParamUtil.get(request, "updateFlag");
		request.setAttribute("updateFlag", updateFlag);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("editFrame");
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
		String updateFlag=ParamUtil.get(request, "updateFlag");
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld> result = uncommService
					.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("updateFlag", updateFlag);
		return mapping.findForward("childedit");
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
		String updateFlag=ParamUtil.get(request, "updateFlag");
		String ADVC_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String, String> entry = new HashMap<String, String>();
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			entry = uncommService.load(ADVC_ORDER_ID);
		} else {
			// 获取当前登录人员姓名id
			entry.put("SALE_PSON_NAME", userBean.getXM());
			entry.put("SALE_PSON_ID", userBean.getRYXXID());
		}
		entry.put("ZTXXID", userBean.getLoginZTXXID());// 终端查询条件传值
		request.setAttribute("rst", entry);
		request.setAttribute("LEDGER_ID",userBean.getLoginZTXXID());
		request.setAttribute("updateFlag", updateFlag);
		return mapping.findForward("toedit");
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
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			String parentJsonData = ParamUtil.get(request, "parentJsonData");
			AdvcorderModel model = new Gson().fromJson(parentJsonData,
					new TypeToken<AdvcorderModel>() {
					}.getType());
			String jsonDate = ParamUtil.get(request, "childJsonData");
			List<AdvcorderModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<AdvcorderModelChld>>() {
						}.getType());
			}
			uncommService.txEdit(ADVC_ORDER_ID, model, chldModelList,
					userBean);

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
}