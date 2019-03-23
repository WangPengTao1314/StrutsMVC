/**
 * prjName:喜临门营销平台
 * ucName:订货订单处理
 * fileName:GoodsorderAction
 */
package com.hoperun.drp.sale.sergoodsorder.action;

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
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.sergoodsorder.service.SergoodsorderService;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModel;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func 分销 -区域服务中心- 订货订单处理 
 * *@version 1.1 *@author zzb 
 * *@createdate
 * 2014-05-22 15:55:09
 */
public class SergoodsorderAction extends BaseAction {
	/** 日志 **/
	private Logger logger = Logger.getLogger(SergoodsorderAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "FX0070101";
	private static String PVG_EDIT = "FX0070102";
	private static String PVG_DELETE = "";
	// 处理
	private static String PVG_TO_DISPOSED = "FX0070102";
	// 回退
	private static String PVG_RETURN = "FX0070103";
	// 取消 订单明细
	private static String PVG_CAL_MX = "";
	

	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "FX0070102";
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

 
	

	/** 审批 end **/
	/** 业务service */
	private SergoodsorderService sergoodsorderService;

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
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "ORDER_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "AREA_NO", params);
		ParamUtil.putStr2Map(request, "AREA_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		params.put("CHANN_ID", userBean.getCHANN_ID());//登录人所属渠道
		//params.put("LEDGER_ID", userBean.getLoginZTXXID());//登录人帐套
		
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");

		StringBuffer strQx = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u",strQx.toString()));
		
		if (StringUtil.isEmpty(search)) {
			sb.append(" and u.STATE = '" + BusinessConsts.UNDONE + "'");// 菜单页 // 点击显示 // “未处理“
		} else if (StringUtil.isEmpty(params.get("STATE"))) {
			// 点击查询 只查询 “未处理、已处理、区域服务中心退回”
			sb.append(" and u.STATE in('" + BusinessConsts.UNDONE + "','"
					+ BusinessConsts.DONE + "','区域服务中心退回') ");
		}
		sb.append(" and u.IS_AREA_SER_ORDER = 1 ");
		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.ORDER_RECV_DATE", "ASC");

		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = sergoodsorderService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("JGXXID", userBean.getJGXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
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
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if (!StringUtil.isEmpty(GOODS_ORDER_ID)) {
			List<GoodsorderModelChld> result = sergoodsorderService
					.childQuery(GOODS_ORDER_ID);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
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
	 * * to 到处理页面
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
	public ActionForward toDisposed(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_TO_DISPOSED)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("todisposed");
	}

	/**
	 * 处理页面 上帧
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward parentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_TO_DISPOSED)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String GOODS_ORDER_ID = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(GOODS_ORDER_ID)) {
			Map<String, String> entry = sergoodsorderService
					.load(GOODS_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("JGXXID", userBean.getJGXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("parentlist");
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
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if (!StringUtil.isEmpty(GOODS_ORDER_ID)) {
			List<GoodsorderModelChld> result = sergoodsorderService
					.childQuery(GOODS_ORDER_ID);
			// Map<String,String> factory =
			// goodsorderService.queryDefaultFactory(GOODS_ORDER_ID);
			// request.setAttribute("factory", factory);

			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}

			request.setAttribute("rst", result);
			request.setAttribute("pvg", setPvg(userBean));
			request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		}
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
		String GOODS_ORDER_DTL_IDs = ParamUtil.get(request,
				"GOODS_ORDER_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(GOODS_ORDER_DTL_IDs)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("GOODS_ORDER_DTL_IDS", GOODS_ORDER_DTL_IDs);
			List<GoodsorderModelChld> list = sergoodsorderService
					.loadChilds(params);
			request.setAttribute("rst", list);
		}
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
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			String parentJsonData = ParamUtil.utf8Decoder(request,
					"parentJsonData");
			GoodsorderModel model = new Gson().fromJson(parentJsonData,
					new TypeToken<GoodsorderModel>() {
					}.getType());
			String jsonDate = ParamUtil.get(request, "childJsonData");
			List<GoodsorderModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<GoodsorderModelChld>>() {
						}.getType());
			}
			sergoodsorderService.txEdit(GOODS_ORDER_ID, model, chldModelList,
					userBean);
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
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			String jsonDate = ParamUtil.get(request, "childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<GoodsorderModelChld> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<GoodsorderModelChld>>() {
						}.getType());
				sergoodsorderService.txChildEdit(GOODS_ORDER_ID, modelList);
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
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			sergoodsorderService.txDelete(params);
			sergoodsorderService.clearCaches(params);
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
			String GOODS_ORDER_DTL_IDs = ParamUtil.get(request,
					"GOODS_ORDER_DTL_IDS");
			// 批量删除，多个Id之间用逗号隔开
			sergoodsorderService.txBatch4DeleteChild(GOODS_ORDER_DTL_IDs);
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if (!StringUtil.isEmpty(GOODS_ORDER_ID)) {
			Map<String, String> entry = sergoodsorderService
					.load(GOODS_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("todetail");
	}

	/**
	 * * 提交
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
				&& !QXUtil.checkMKQX(userBean, PVG_TO_DISPOSED)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String errorId = "";
		try {
			String jsonDate = ParamUtil.get(request, "childData");
			String GOODS_ORDER_ID = ParamUtil.utf8Decoder(request, "selRowId");
			 
			GoodsorderModel model = sergoodsorderService.loadById(GOODS_ORDER_ID);
			List<GoodsorderModelChld> modelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				  modelList = new Gson().fromJson(jsonDate, new TypeToken<List<GoodsorderModelChld>>(){}.getType());
			}else{
				modelList = sergoodsorderService.childQuery(GOODS_ORDER_ID);
			}
			
			sergoodsorderService.txAddSalOrder(model, modelList, userBean);

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

	public ActionForward toSpclDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// String GOODS_ORDER_DTL_ID = ParamUtil.get(request,
		// "GOODS_ORDER_DTL_ID");
		// if(!StringUtil.isEmpty(GOODS_ORDER_DTL_ID)){
		// Map<String,String> entry =
		// goodsorderService.load(GOODS_ORDER_DTL_ID);
		// request.setAttribute("rst", entry);
		// }
		return mapping.findForward("tospcldetail");
	}

	/**
	 * * 到回退原因页面
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
	public ActionForward toReason(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}

		return mapping.findForward("toreason");
	}

	/**
	 * * 确认回退
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
	public void returnBack(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_RETURN)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		try {
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			String reason = ParamUtil.get(request, "msg");

			// 更新订货订单的状态为“退回”
			this.sergoodsorderService.txUpdateAdvcOrder(GOODS_ORDER_ID, reason,
					userBean);
			jsonResult = jsonResult(true, "退回成功");

		} catch (Exception e) {
			jsonResult = jsonResult(true, "退回失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * 取消订单明细
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
	public void orderClose(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_CAL_MX)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();

		try {
			String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
			String mxIds = ParamUtil.get(request, "mxIds");
			String remark = ParamUtil.get(request, "remark");
			boolean isAll = ParamUtil.getBoolean(request, "isAll");

			// 更新当前订货订单的状态为“交易关闭”
			this.sergoodsorderService.updateOrderClose(GOODS_ORDER_ID, mxIds,
					remark, isAll, userBean);
			jsonResult = jsonResult(true, "操作成功");

		} catch (Exception e) {
			jsonResult = jsonResult(true, "操作失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * * 生命周期
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
	public ActionForward traceList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String GOODS_ORDER_ID = ParamUtil.get(request, "GOODS_ORDER_ID");
		if (!StringUtil.isEmpty(GOODS_ORDER_ID)) {
			List<GoodsorderhdModelTrace> result = sergoodsorderService
					.traceQuery(GOODS_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("traceList");
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
		pvgMap.put("PVG_RETURN", QXUtil.checkPvg(userBean, PVG_RETURN));
		pvgMap.put("PVG_CAL_MX", QXUtil.checkPvg(userBean, PVG_CAL_MX));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);

		pvgMap.put("PVG_TO_DISPOSED", QXUtil
				.checkPvg(userBean, PVG_TO_DISPOSED));

		return pvgMap;
	}

	public SergoodsorderService getSergoodsorderService() {
		return sergoodsorderService;
	}

	public void setSergoodsorderService(
			SergoodsorderService sergoodsorderService) {
		this.sergoodsorderService = sergoodsorderService;
	}

}