/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderAction
 */
package com.hoperun.drp.finance.advcInvoice.action;

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

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advcInvoice.service.AdvcInvoiceService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelTrace;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class AdvcInvoiceAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(AdvcInvoiceAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0050801";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	// 下载，导入
	private static String PVG_FILE = "";
	private static String PVG_CLOSE="";//关闭
	private static String PVG_RETURN_AUDIT="";//反审核
	private static String PVG_UNCOMM="";//待确认
	private static String PVG_UPDATE_ADVC="";//变更信息
	// 解除冻结
	private static String PVG_REMOVE_FREEZE = "";
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "FX0050802";//开票
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
	
	
	/** 审批 end **/
	/** 业务service */
	private AdvcInvoiceService advcInvoiceService;


	public AdvcInvoiceService getAdvcInvoiceService() {
		return advcInvoiceService;
	}

	public void setAdvcInvoiceService(AdvcInvoiceService advcInvoiceService) {
		this.advcInvoiceService = advcInvoiceService;
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		String search = ParamUtil.get(request, "search");
		request.setAttribute("paramUrl", paramUrl);
		request.setAttribute("search", search);
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
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
		String PROMOTE_NO=ParamUtil.utf8Decoder(request, "PROMOTE_NO");
		params.put("PROMOTE_NOQuery", StringUtil.creCon("u.PROMOTE_NO", PROMOTE_NO, ","));
		String PROMOTE_NAME=ParamUtil.utf8Decoder(request, "PROMOTE_NAME");
		params.put("PROMOTE_NAMEQuery", StringUtil.creCon("u.PROMOTE_NAME", PROMOTE_NAME, ","));
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "NEED_RECEIPT_FLAG", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(search, "", PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		params.put("searchSTATE", "'审核通过','部分发货','已发货'");
		if(StringUtil.isEmpty(params.get("NEED_RECEIPT_FLAG"))&&StringUtil.isEmpty(search)){
			params.put("NEED_RECEIPT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		}
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
		IListPage page = advcInvoiceService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("RYXXID", userBean.getRYXXID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("module", module);
		request.setAttribute("search", search);
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
			List<AdvcorderModelChld> result = advcInvoiceService
					.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("childlist");
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			Map<String, String> entry = advcInvoiceService.load(ADVC_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelGchld> result = advcInvoiceService
					.gchildQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
	}


	/**
	 * 预订单跟踪
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toTrace(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelTrace> result = advcInvoiceService
					.traceQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("traceList");
	}
	
	/**
	 * 开票
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void commitInvoice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			Map<String, String> params = new HashMap<String, String>();
			ParamUtil.putStr2Map(request, "ADVC_ORDER_ID", params);
			ParamUtil.putStr2Map(request, "RECEIPT_REMARK", params);
			ParamUtil.putStr2Map(request, "RECEIPT_NO", params);
			params.put("RECEIPT_PSON_ID", userBean.getRYXXID());
			params.put("RECEIPT_TIME", "数据库时间");
			params.put("NEED_RECEIPT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			advcInvoiceService.commitInvoice(params);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "开票失败");
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
	 *            the userBean
	 * @return Map<String,String>
	 */
	private Map<String, String> setPvg(UserBean userBean) {
		Map<String, String> pvgMap = new HashMap<String, String>();
		pvgMap.put("PVG_BWS", QXUtil.checkPvg(userBean, PVG_BWS));
		pvgMap.put("PVG_EDIT", QXUtil.checkPvg(userBean, PVG_EDIT));
		pvgMap.put("PVG_DELETE", QXUtil.checkPvg(userBean, PVG_DELETE));
		pvgMap.put("PVG_START_STOP", QXUtil.checkPvg(userBean, PVG_START_STOP));
		pvgMap.put("PVG_UNCOMM", QXUtil.checkPvg(userBean, PVG_UNCOMM));
		pvgMap.put("PVG_COMMIT_CANCLE", QXUtil.checkPvg(userBean,
				PVG_COMMIT_CANCLE));
		pvgMap.put("PVG_TRACE", QXUtil.checkPvg(userBean, PVG_TRACE));
		pvgMap.put("PVG_BWS_AUDIT", QXUtil.checkPvg(userBean, PVG_BWS_AUDIT));
		pvgMap.put("PVG_AUDIT_AUDIT", QXUtil.checkPvg(userBean, PVG_AUDIT_AUDIT));
		pvgMap.put("PVG_FINISH_CANCLE", QXUtil.checkPvg(userBean,
				PVG_FINISH_CANCLE));
		pvgMap.put("PVG_TRACE_AUDIT", QXUtil
				.checkPvg(userBean, PVG_TRACE_AUDIT));
		pvgMap.put("PVG_FILE", QXUtil.checkPvg(userBean, PVG_FILE));
		pvgMap.put("PVG_REMOVE_FREEZE", QXUtil.checkPvg(userBean,
				PVG_REMOVE_FREEZE));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_CLOSE", QXUtil.checkPvg(userBean,PVG_CLOSE));
		pvgMap.put("PVG_RETURN", QXUtil.checkPvg(userBean,PVG_RETURN));
		pvgMap.put("PVG_RETURN_AUDIT", QXUtil.checkPvg(userBean,PVG_RETURN_AUDIT));
		pvgMap.put("PVG_UPDATE_ADVC", QXUtil.checkPvg(userBean,PVG_UPDATE_ADVC));
		return pvgMap;
	}
}