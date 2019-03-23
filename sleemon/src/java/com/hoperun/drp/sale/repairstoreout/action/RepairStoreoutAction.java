/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairappAction
 */
package com.hoperun.drp.sale.repairstoreout.action;

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
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.drp.sale.repairstoreout.service.RepairStoreoutService;
import com.hoperun.sys.model.UserBean;

/**
 * *返修出库
 * 16:25:12
 */
public class RepairStoreoutAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(RepairStoreoutAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0021801";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交
	private static String PVG_COMMIT = "FX0021802";
	//关闭
	private static String PVG_CLOSE = "FX0021803";
	
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "";
	private static String PVG_AUDIT_AUDIT = "";
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";
	private static String AUD_TAB_KEY = "";
	private static String AUD_BUSS_STATE = "STATE";
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	/** 审批 end **/
	/** 业务service */
	private RepairStoreoutService repairStoreoutService;

	/**
	 * Sets the Repairapp service.
	 * 
	 * @param RepairrecvService
	 *            the new Repairapp service
	 */
	public void setRepairStoreoutService(
			RepairStoreoutService repairStoreoutService) {
		this.repairStoreoutService = repairStoreoutService;
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ERP_REPAIR_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "REPAIR_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "REPAIR_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEG", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "STATE", params);

		ParamUtil.putStr2Map(request, "FINISH_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "FINISH_DATE_END", params);

		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		String frameTolist = ParamUtil.get(request, "frameTolist");
		if (!StringUtil.isEmpty(frameTolist)) {
			// params.put("STATE","审核通过");
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
		}
		// 权限级别和审批流的封装
		// params.put("QXJBCON",
		// ParamUtil.getPvgCon(search,module,PVG_BWS,PVG_BWS_AUDIT,AUD_TAB_KEY,
		// AUD_TAB_NAME,AUD_BUSS_TYPE,AUD_BUSS_STATE,userBean));
		params.put("QXJBCON", getQXString(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));

		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME", "DESC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = repairStoreoutService.pageQuery(params, pageNo);
		// params.put("STATE","");
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
		String ERP_REPAIR_ORDER_ID = ParamUtil.get(request,
				"ERP_REPAIR_ORDER_ID");
		if (!StringUtil.isEmpty(ERP_REPAIR_ORDER_ID)) {
			List<RepairappModelChld> result = repairStoreoutService
					.childQuery(ERP_REPAIR_ORDER_ID);
			request.setAttribute("rst", result);
			
			Map<String,Object> totalResult = repairStoreoutService.queryTotal(ERP_REPAIR_ORDER_ID);
            request.setAttribute("totalResult", totalResult);
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ERP_REPAIR_ORDER_ID = ParamUtil.get(request,
				"ERP_REPAIR_ORDER_ID");
		if (!StringUtil.isEmpty(ERP_REPAIR_ORDER_ID)) {
			Map<String, String> entry = repairStoreoutService
					.load(ERP_REPAIR_ORDER_ID);
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_COMMIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String errorId = "";
		Map<String, String> params = new HashMap<String, String>();
		try {
			String ERP_REPAIR_ORDER_ID = request.getParameter("selRowId");
			List<RepairappModelChld> list = repairStoreoutService
					.childQuery(ERP_REPAIR_ORDER_ID);

			params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
			params.put("UPDATOR", userBean.getYHBH());
			params.put("UPD_NAME", userBean.getXM());
            params.put("STATE", BusinessConsts.STATE_WAIT_SEND);
			repairStoreoutService.txSubmitById(params, userBean);
			jsonResult = jsonResult(true, "提交库房出库成功");
			if (list.size() == 0) {
				errorId = "0";
				throw new Exception();
			}
		} catch (Exception e) {
			if ("0".equals(errorId)) {
				jsonResult = jsonResult(false, "没有明细，不能提交!");
			} else {
				jsonResult = jsonResult(false, "提交失败");
			}
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	/**
	 * 
	 * 关闭
	 */
	public void auditClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_CLOSE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ERP_REPAIR_ORDER_ID = request.getParameter("selRowId");
			repairStoreoutService.txAuditClose(ERP_REPAIR_ORDER_ID, userBean);
			jsonResult = jsonResult(true, "关闭成功");
			 
		} catch (Exception e) {
			jsonResult = jsonResult(false, "关闭失败");
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
		pvgMap.put("PVG_COMMIT", QXUtil.checkPvg(userBean, PVG_COMMIT));
		pvgMap.put("PVG_CLOSE", QXUtil.checkPvg(userBean, PVG_CLOSE));
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

	public String getQXString(String search, String module, String pvgBws,
			String pvgAudit, String pkId, String tableName,
			String businessType, String state, UserBean userBean) {
		StringBuffer tmp = new StringBuffer("");
		if (!StringUtil.isEmpty(module)) {

		} else {
			if (StringUtil.isEmpty(search)) {
				if (!StringUtil.isEmpty(state)) {
					tmp.append(state);
					tmp.append(" ='");
					tmp.append(BusinessConsts.PASS);
					tmp.append("'");
					tmp.append(" and ");
				}
			} else {
				if (!StringUtil.isEmpty(state)) {
					tmp.append(state);
					tmp.append(" in ('");
					tmp.append(BusinessConsts.PASS);
					tmp.append("','");
					tmp.append(BusinessConsts.COMMON_COLSE);
					tmp.append("','");
					tmp.append(BusinessConsts.STATE_WAIT_SEND);
					tmp.append("')");
					tmp.append(" and ");
				}
			}

			tmp.append(QXUtil.getQXTJ(userBean, pvgBws));
		}
		return tmp.toString();

	}
}