package com.hoperun.erp.sale.deliveryhd.action;

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
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.deliveryhd.model.DeliveryhdGchldModel;
import com.hoperun.erp.sale.deliveryhd.service.DeliveryhdService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

/**
 * 交付单维护
 * 
 * @author zhang_zhongbin
 * 
 */
public class DeliveryhdAction extends BaseAction {

	// 日志记录
	/** The logger. */
	private Logger logger = Logger.getLogger(DeliveryhdAction.class);
	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "BS0013801";
	private static String PVG_EDIT = "BS0013802";
	private static String PVG_SHIP_EDIT = "";

	// 行关闭
	private static String PVG_ROW_CLOSE = "BS0013805";

	private static String PVG_DELETE = "BS0013803";
	// 启用,停用
	private static String PVG_START_STOP = "";
	// 确认，取消
	private static String PVG_FINISH_CANCLE = "";
	private static String AUD_BUSS_STATE = "";
	/** end */
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "BS0013804";// 提交/撤销
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

	private DeliveryhdService deliveryhdService;

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		ParamUtil.setCommAttributeEx(request);
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("frames");
	}

	/**
	 * 一览页面
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "TRUCK_TYPE", params);
		ParamUtil.putStr2Map(request, "RECV_ADDR_NO", params);
		ParamUtil.putStr2Map(request, "RECV_ADDR", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		// ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);
		// ParamUtil.putStr2Map(request, "PROV", params);
		// ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
		ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);

		ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); // 发运单号
																				// 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); // 来源发运单编号
																			// 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO",
				params); // 出货计划号 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); // 销售订单编号
																			// 转换为大写
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");

		StringBuffer sb = new StringBuffer(ParamUtil.getPvgCon(search, module,
				PVG_BWS, PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME,
				AUD_BUSS_TYPE, AUD_BUSS_STATE, userBean));
		
//		if (StringUtil.isEmpty(search)) {
//			sb.append(" and u.STATE = '" + BusinessConsts.UNCOMMIT + "'");// '未提交'
//		} else {
//			// sb.append(" and u.STATE in('" +
//			// BusinessConsts.UNCOMMIT+"','已提交生产')");
//		}
		if(StringUtil.isEmpty(search) &&  StringUtil.isEmpty(params.get("STATE"))){
			params.put("STATES", "'未提交','已提交库房'");
		}
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL);
		params.put("PROV", PROV);

		// 权限级别和审批流的封装
		params.put("QXJBCON", sb.toString());
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.CRE_TIME DESC,u.DELIVER_ORDER_NO", "ASC");
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = deliveryhdService.pageQuery(params, pageNo);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("list");
	}

	/**
	 * * 明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward childList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, params, "u.PRD_NO", "desc");
		
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			List<TurnoverplanChildModel> result = deliveryhdService.childQuery(params);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("childlist");
	}
	/**
	 * * 子明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward gchildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			List<Map<String,String>> result = deliveryhdService
					.gchildQuery(DELIVER_ORDER_ID);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("gchildlist");
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
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			Map<String, String> entry = deliveryhdService
					.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", entry);
		}
		return mapping.findForward("detail");
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
			String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
			Map<String, String> params = new HashMap<String, String>();
			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			params.put("UPD_NAME", userBean.getYHM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			deliveryhdService.txDelete(params);
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
	 * * 到编辑页面
	 * 
	 */
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		Map<String, String> map = new HashMap<String, String>();
		String ORDER_CHANN_NO = request.getParameter("ORDER_CHANN_NO");// 客户编号
		if (!StringUtil.isEmpty(ORDER_CHANN_NO)) {
			map.put("ORDER_CHANN_NOS", StringUtil.creCon("a.ORDER_CHANN_NO",
					ORDER_CHANN_NO, ","));
			map.put("ORDER_CHANN_NO", ORDER_CHANN_NO);
		}
		String ORDER_CHANN_NAME = request.getParameter("ORDER_CHANN_NAME");// 客户名称
		if (!StringUtil.isEmpty(ORDER_CHANN_NAME)) {
			map.put("ORDER_CHANN_NAMES", StringUtil.creCon(
					"a.ORDER_CHANN_NAME", ORDER_CHANN_NAME, ","));
			map.put("ORDER_CHANN_NAME", ORDER_CHANN_NAME);
		}
		String PRD_NO = request.getParameter("PRD_NO");// 货品编号
		if (!StringUtil.isEmpty(PRD_NO)) {
			map.put("PRD_NOS", StringUtil.creCon("b.PRD_NO", PRD_NO, ","));
			map.put("PRD_NO", PRD_NO);
		}
		String PRD_NAME = request.getParameter("PRD_NAME");// 货品名称
		if (!StringUtil.isEmpty(PRD_NAME)) {
			map
					.put("PRD_NAMES", StringUtil.creCon("b.PRD_NAME", PRD_NAME,
							","));
			map.put("PRD_NAME", PRD_NAME);
		}
		map.put("SHIP_POINT_ID", request.getParameter("SHIP_POINT_ID"));
		// String GOODS_ORDER_NO=request.getParameter("GOODS_ORDER_NO");//订货订单编号
		// if(!StringUtil.isEmpty(GOODS_ORDER_NO)){
		// map.put("GOODS_ORDER_NO", StringUtil.creCon("b.GOODS_ORDER_NO",
		// GOODS_ORDER_NO, ","));
		// }
		// String SALE_ORDER_NO=request.getParameter("SALE_ORDER_NO");//销售订单编号
		// if(!StringUtil.isEmpty(SALE_ORDER_NO)){
		// map.put("SALE_ORDER_NO", StringUtil.creCon("b.SALE_ORDER_NO",
		// SALE_ORDER_NO, ","));
		// }
		String SALE_ORDER_NO=request.getParameter("SALE_ORDER_NO");
		if(!StringUtil.isEmpty(SALE_ORDER_NO)){
			map.put("SALE_ORDER_NOS", StringUtil.creCon("a.SALE_ORDER_NO",SALE_ORDER_NO, ","));
			map.put("SALE_ORDER_NO", SALE_ORDER_NO);
		}
		
		String GOODS_ORDER_NO=request.getParameter("GOODS_ORDER_NO");
		if(!StringUtil.isEmpty(GOODS_ORDER_NO)){
			map.put("GOODS_ORDER_NOS", StringUtil.creCon("b.GOODS_ORDER_NO",GOODS_ORDER_NO, ","));
			map.put("GOODS_ORDER_NO", GOODS_ORDER_NO);
		}
		
		String PAR_PRD_NO = request.getParameter("PAR_PRD_NO");// 货品分类编号
		if (!StringUtil.isEmpty(PAR_PRD_NO)) {
			map.put("PAR_PRD_NOS", StringUtil.creCon("f.PAR_PRD_NO",
					PAR_PRD_NO, ","));
			map.put("PAR_PRD_NO", PAR_PRD_NO);
		}
		String PAR_PRD_NAME = request.getParameter("PAR_PRD_NAME");// 货品分类名称
		if (!StringUtil.isEmpty(PAR_PRD_NAME)) {
			map.put("PAR_PRD_NAMES", StringUtil.creCon("f.PAR_PRD_NAME",
					PAR_PRD_NAME, ","));
			map.put("PAR_PRD_NAME", PAR_PRD_NAME);
		}
		map.put("AUDIT_TIME_BEG", request.getParameter("AUDIT_TIME_BEG"));// 审核时间从
		map.put("AUDIT_TIME_END", request.getParameter("AUDIT_TIME_END"));// 审核时间到
		map.put("ADVC_SEND_DATE_BEG", request
				.getParameter("ADVC_SEND_DATE_BEG"));// 交期从
		map.put("ADVC_SEND_DATE_END", request
				.getParameter("ADVC_SEND_DATE_END"));// 交期到
		map.put("RECV_ADDR", request.getParameter("RECV_ADDR"));// 收货地址
		String RECV_ADDR=request.getParameter("RECV_ADDR");
		if(!StringUtil.isEmpty(RECV_ADDR)){
			map.put("RECV_ADDRS", StringUtil.creCon("a.RECV_ADDR",
					RECV_ADDR, ","));
		}
		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		map.put("PROV_SQL", PROV_SQL);
		map.put("PROV", PROV);
		String SHIP_POINT_NAME=request.getParameter("SHIP_POINT_NAME");
		if(StringUtil.isEmpty(SHIP_POINT_NAME)){
			map.put("ORDER_CHANN_NOS", " and 1<>1");//生产基地为必填，如果为空时说明第一次打开 不查询数据
		}
		map.put("SHIP_POINT_NAME", SHIP_POINT_NAME);// 生产基地
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		ParamUtil.setOrderField(request, map, "b.GOODS_ORDER_NO,b.ROW_NO,b.PRD_NO", "ASC");
		// 区域分管
		map.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		Map<String,String> prvdMap=LogicUtil.getPrvdInfo();
		if(null!=prvdMap){
			map.putAll(prvdMap);
		}
		 int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		 int pageSize=ParamUtil.getInt(request, "pageSize",500);
		 if(StringUtil.isEmpty(request.getParameter("pageSize"))){
			 map.put("pageSize", pageSize+"");
		 }else{
			 ParamUtil.putStr2Map(request, "pageSize", map);
		 }
		IListPage page = deliveryhdService.queryEdit(map, pageNo);
//		List page = deliveryhdService.queryEdit(map);
		 String html=getHtml(map, pageNo,pageSize);
		 request.setAttribute("html", html);
		request.setAttribute("page", page);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("params", map);
		return mapping.findForward("edit");
	}

	/**
	 * 编辑.
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
		logger.info("编辑开始");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String jsonData = ParamUtil.get(request, "childJsonData");
		String mainData = ParamUtil.get(request, "mainData");
		String gchldData= ParamUtil.get(request, "gchldData");
		String SHIP_POINT_ID;
		String SHIP_POINT_NAME;
		try {
			TurnoverplanModel model = new Gson().fromJson(mainData,
					new TypeToken<TurnoverplanModel>() {
					}.getType());
			if (StringUtil.isEmpty(model.getDELIVER_ORDER_ID())) {
				SHIP_POINT_ID = ParamUtil.get(request, "SHIP_POINT_ID");
				SHIP_POINT_NAME = ParamUtil.get(request, "SHIP_POINT_NAME");
			} else {
				SHIP_POINT_ID = model.getSHIP_POINT_ID();
				SHIP_POINT_NAME = model.getSHIP_POINT_NAME();
			}
			List<TurnoverplanChildModel> chldModelList = new ArrayList<TurnoverplanChildModel>();
			if (!StringUtil.isEmpty(jsonData)) {
				chldModelList = new Gson().fromJson(jsonData,
						new TypeToken<List<TurnoverplanChildModel>>() {
						}.getType());
			}
			List<DeliveryhdGchldModel> gchldModelList = new ArrayList<DeliveryhdGchldModel>();
			if (!StringUtil.isEmpty(gchldData)) {
				gchldModelList = new Gson().fromJson(gchldData,
						new TypeToken<List<DeliveryhdGchldModel>>() {
						}.getType());
			}
			String DELIVER_ORDER_NO = deliveryhdService.txEdit(model, chldModelList, userBean,
					SHIP_POINT_ID, SHIP_POINT_NAME,gchldModelList);
			if(StringUtil.isEmpty(model.getDELIVER_ORDER_ID())){
				jsonResult = jsonResult(true, "新增成功,新生成出货计划单号："+DELIVER_ORDER_NO);
			}else{
				jsonResult = jsonResult(true, "修改成功");
			}
			

		}catch(ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
		}catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "操作失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 整单撤销.
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
	public void toCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String InterfaceCode = "TC0300020";
		String ServiceCode = "ShipPlanManage";
		String Operation = "createShipPlan";
		String AppCode = "DM";
		String DestCode = "UA";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createShipPlan";

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		Map<String, String> bill = deliveryhdService.load(DELIVER_ORDER_ID);
		String DELIVER_ORDER_NO = bill.get("DELIVER_ORDER_NO");
		String stats = (String) bill.get("STATE");
		String strJsonData = "";
		try {
			if (checkIsClose(stats)) {
				String DELIVER_ORDER_DTL_IDS = getDeliverDtlId(DELIVER_ORDER_ID);
				if (StringUtils.isNotEmpty(DELIVER_ORDER_DTL_IDS)) {
					strJsonData = InterfaceInvokeUtil.strCreateShipPlanByClose(
								DELIVER_ORDER_ID, DELIVER_ORDER_DTL_IDS,
								InterfaceCode, ServiceCode, Operation, AppCode,
								DestCode, UId, OPRCODE);
					LogicUtil.actLog("出货单管理", "成功调入整单撤销出货单接口", "UA系统", "成功",
							strJsonData, AppCode, UId, OPRCODE,DELIVER_ORDER_NO);

					String strResult=deliveryhdService.txCloseAllOrder(DELIVER_ORDER_ID,strJsonData);
					if ("操作成功".equals(strResult)) {
						LogicUtil.actLog("出货单管理", "整单撤销出货单成功", "UA系统", "成功", strResult,
								AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
						jsonResult = jsonResult(true, "整单撤销成功");
					} else {
						LogicUtil.actLog("出货单管理", "整单撤销出货单失败", "UA系统", "失败", strResult,
								AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
						jsonResult = jsonResult(false, strResult);
					}
				}
			} else {
				jsonResult = jsonResult(false, "此主表状态不允许关闭发运单");
			}
		}catch (ServiceException ex) {
			LogicUtil.actLog("出货单管理", "整单撤销出货单失败", "UA系统", "失败", ex.getMessage()
					+ strJsonData, AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
			jsonResult = jsonResult(false, ex.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "操作失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	
	/**
	 * 行关闭
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
	public void closeChilds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String InterfaceCode = "TC0300020";
		String ServiceCode = "ShipPlanManage";
		String Operation = "createShipPlan";
		String AppCode = "DM";
		String DestCode = "UA";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createShipPlan";
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_ROW_CLOSE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String strJsonData = "";
		try {
			String DELIVER_ORDER_ID = ParamUtil
					.get(request, "DELIVER_ORDER_ID");
			String IS_ALL_CLOSE = ParamUtil.get(request, "IS_ALL_CLOSE");
			Map<String, String> bill = deliveryhdService.load(DELIVER_ORDER_ID);
			String DELIVER_ORDER_NO = bill.get("DELIVER_ORDER_NO");
			String stats = (String) bill.get("STATE");
			if (checkIsClose(stats)) {
				String DELIVER_ORDER_DTL_IDS = ParamUtil.get(request,
						"DELIVER_ORDER_DTL_IDS");
				if ("true".equals(IS_ALL_CLOSE)) {
					DELIVER_ORDER_DTL_IDS = getDeliverDtlId(DELIVER_ORDER_ID);
				}
				if (StringUtils.isNotEmpty(DELIVER_ORDER_DTL_IDS)) {
					strJsonData = InterfaceInvokeUtil.strCreateShipPlanByClose(
							DELIVER_ORDER_ID, DELIVER_ORDER_DTL_IDS,
							InterfaceCode, ServiceCode, Operation, AppCode,
							DestCode, UId, OPRCODE);
					LogicUtil.actLog("出货单管理", "成功调入行关闭出货单接口", "UA系统", "成功",
							strJsonData, AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
					String strResult = deliveryhdService.txCloseChilds(
							DELIVER_ORDER_NO, DELIVER_ORDER_ID,
							DELIVER_ORDER_DTL_IDS, userBean, strJsonData,
							IS_ALL_CLOSE);
					if ("操作成功".equals(strResult)) {
						LogicUtil.actLog("出货单管理", "行关闭成功", "UA系统", "成功", strResult,
								AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
						jsonResult = jsonResult(true, "行关闭成功");
					} else {
						LogicUtil.actLog("出货单管理", "行关闭失败", "UA系统", "失败", strResult,
								AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
						jsonResult = jsonResult(false, strResult);
					}
				}
			} else {
				jsonResult = jsonResult(false, "此主表状态不允许关闭出货单");
			}
		} catch (ServiceException ex) {
			LogicUtil.actLog("出货单管理", "关闭出货单失败", "UA系统", "失败", ex.getMessage()
					+ strJsonData, AppCode, UId, OPRCODE);
			jsonResult = jsonResult(false, ex.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "操作失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 整单关闭.
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_ROW_CLOSE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String InterfaceCode = "TC0300020";
		String ServiceCode = "ShipPlanManage";
		String Operation = "createShipPlan";
		String AppCode = "DM";
		String DestCode = "UA";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createShipPlan";

		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		Map<String, String> bill = deliveryhdService.load(DELIVER_ORDER_ID);
		String DELIVER_ORDER_NO = bill.get("DELIVER_ORDER_NO");
		String stats = (String) bill.get("STATE");
		String strJsonData = "";
		try {
			if (checkIsClose(stats)) {
				String DELIVER_ORDER_DTL_IDS = getDeliverDtlId(DELIVER_ORDER_ID);
				if (StringUtils.isNotEmpty(DELIVER_ORDER_DTL_IDS)) {
					strJsonData = InterfaceInvokeUtil.strCreateShipPlanByClose(
								DELIVER_ORDER_ID, DELIVER_ORDER_DTL_IDS,
								InterfaceCode, ServiceCode, Operation, AppCode,
								DestCode, UId, OPRCODE);
					LogicUtil.actLog("出货单管理", "成功调入整单关闭出货单接口", "UA系统", "成功",
							strJsonData, AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
				}
				String strResult=deliveryhdService.txClose(DELIVER_ORDER_ID,strJsonData);
				if ("操作成功".equals(strResult)) {
					LogicUtil.actLog("出货单管理", "整单关闭出货单成功", "UA系统", "成功", strResult,
							AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
					jsonResult = jsonResult(true, "整单关闭成功");
				} else {
					LogicUtil.actLog("出货单管理", "整单关闭出货单失败", "UA系统", "失败", strResult,
							AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
					jsonResult = jsonResult(false, strResult);
				}
			} else {
				jsonResult = jsonResult(false, "此主表状态不允许关闭发运单");
			}
		}catch (ServiceException ex) {
			LogicUtil.actLog("出货单管理", "整单关闭出货单失败", "UA系统", "失败", ex.getMessage()
					+ strJsonData, AppCode, UId, OPRCODE,DELIVER_ORDER_NO);
			jsonResult = jsonResult(false, ex.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "操作失败");
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
			String DELIVER_ORDER_ID = ParamUtil
					.get(request, "DELIVER_ORDER_ID");
			String jsonDate = ParamUtil.get(request, "childJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<TurnoverplanChildModel> modelList = new Gson().fromJson(
						jsonDate,
						new TypeToken<List<TurnoverplanChildModel>>() {
						}.getType());
				deliveryhdService.txChildEdit(DELIVER_ORDER_ID, modelList,
						userBean);
				jsonResult = jsonResult(true, "保存成功");
			}
		}catch(ServiceException e){
			jsonResult = jsonResult(false, e.getMessage());
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
		String paramUrl = ParamUtil.utf8Decoder(request, "paramUrl");
		request.setAttribute("paramUrl", paramUrl);
		return mapping.findForward("editFrame");
	}

	/**
	 * * 到编辑页面
	 * 
	 */
	public ActionForward toUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "selRowId");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			Map<String, String> result = deliveryhdService
					.load(DELIVER_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("update");
	}

	/**
	 * 提交库房
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void commitStore(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		logger.info("提交库房开始");
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		String InterfaceCode = "TC0300020";
		String ServiceCode = "ShipPlanManage";
		String Operation = "createShipPlan";
		String AppCode = "DM";
		String DestCode = "UA";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createShipPlan";
		try {
			Map<String,String> checkMap=new HashMap<String, String>();
			checkMap.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			checkMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			int count=deliveryhdService.checkFreeChann(checkMap);
			if(count>0){
				throw new ServiceException("对不起，您的发运冻结明细里存在未订货的渠道，请检查后重新提交");
			}
			Map<String, String> map = deliveryhdService.load(DELIVER_ORDER_ID);
			List<TurnoverplanChildModel> childList = deliveryhdService
					.childQuery(DELIVER_ORDER_ID);
			String strJsonData = InterfaceInvokeUtil.strCreateShipPlan(
					DELIVER_ORDER_ID, InterfaceCode, ServiceCode, Operation,
					AppCode, DestCode, UId, OPRCODE);
			LogicUtil.actLog("出货单管理", "成功调入创建出货单接口", "UA系统", "成功", strJsonData,
					AppCode, UId, OPRCODE);
			// 提交库房操作
			Map<String, Object> returnMap = deliveryhdService.txCommitStore(
					DELIVER_ORDER_ID, map, strJsonData, childList, userBean,AppCode, UId, OPRCODE);

			String strResult = StringUtil.nullToSring(returnMap
					.get("strMessage"));
			if ("操作成功".equals(strResult)) {
				LogicUtil.actLog("出货单管理", "生成出货单成功", "UA系统", "成功", strResult,
						AppCode, UId, OPRCODE);
				jsonResult = new Gson().toJson(new Result(true, returnMap, ""));
			} else {
				LogicUtil.actLog("出货单管理", "生成出货单失败", "UA系统", "失败", strResult,
						AppCode, UId, OPRCODE);
				jsonResult = jsonResult(false, strResult);
			}
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
			LogicUtil.actLog("出货单管理", "生成出货单失败", "UA系统", "失败", s.getMessage(),
					AppCode, UId, OPRCODE);
		} catch (Exception e) {
			logger.info(e);
			LogicUtil.actLog("出货单管理", "生成出货单失败", "UA系统", "失败", e.getMessage(),
					AppCode, UId, OPRCODE);
			jsonResult = jsonResult(false, e.getMessage());
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	// 导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "ADVC_SEND_DATE", params);
		ParamUtil.putStr2Map(request, "SEND_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_BEGIN", params);
		ParamUtil.putStr2Map(request, "CRE_TIME_END", params);
		ParamUtil.putStr2Map(request, "AREA_SER_NAME", params);
		ParamUtil.putStr2Map(request, "TRUCK_TYPE", params);
		ParamUtil.putStr2Map(request, "DELIVER_TYPE", params);
		ParamUtil.putStr2Map(request, "CHANN_TYPE", params);
		ParamUtil.putStr2Map(request, "BILL_TYPE", params);
		ParamUtil.putStr2Map(request, "DELIVER_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NO", params);
		ParamUtil.putStr2Map(request, "RECV_CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "CRE_NAME", params);
		ParamUtil.putStr2Map(request, "SHIP_POINT_NAME", params);
		ParamUtil.putStr2Map(request, "PROV", params);
		ParamUtil.putStr2Map(request, "SALE_ORDER_NO", params);
		ParamUtil.putStr2Map(request, "PRD_SPEC", params);
		ParamUtil.putStr2Map(request, "PRD_NO", params);
		ParamUtil.putStr2Map(request, "PRD_NAME", params);
		ParamUtil.putStr2MaptoUpperCase(request, "DELIVER_ORDER_NO", params); // 发运单号
																				// 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "FROM_BILL_NO", params); // 来源发运单编号
																			// 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "JOIN_DELIVER_ORDER_NO",
				params); // 出货计划号 转换为大写
		ParamUtil.putStr2MaptoUpperCase(request, "SALE_ORDER_NO", params); // 销售订单编号
																			// 转换为大写

		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		StringBuffer strQx = new StringBuffer("");
		strQx.append(ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));

		// 权限级别和审批流的封装
		StringBuffer sb = new StringBuffer();
		sb.append(StringUtil.getStrQX("u", strQx.toString()));
		params.put("QXJBCON", sb.toString());
		// 区域分管
//		params.put("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		String PROV = ParamUtil.get(request, "PROV");
		String PROV_SQL = StringUtil.creCon("PROV", PROV, ",");
		params.put("PROV_SQL", PROV_SQL);
		params.put("PROV", PROV);
		if(StringUtil.isEmpty(params.get("STATE"))){
			params.put("STATES", "'未提交','关闭','已提交库房'");
		}
		List list = deliveryhdService.downQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="出货计划号,U9订单编号,U9订单行编号,交货日期,订货方编号,订货方名称,收货方编号,收货方名称,收货地址编号," +
							 "收货地址,生产基地,货品编号,抵库,货品名称,规格型号,特殊规格说明,备注,计划发运数量," +
							 "实发数量,未发数量,单价,计划发运金额,单位体积,汇总行总体积,货品分类,行状态,状态,排骨架,明细备注";
		String tmpContent = "DELIVER_ORDER_NO,U9_SALE_ORDER_NO,U9_SALE_ORDER_DTL_NO,ADVC_SEND_DATE,ORDER_CHANN_NO,ORDER_CHANN_NAME,RECV_CHANN_NO,RECV_CHANN_NAME,RECV_ADDR_NO," +
							"RECV_ADDR,SHIP_POINT_NAME,PRD_NO,IS_BACKUP_FLAG,PRD_NAME,PRD_SPEC,SPCL_SPEC_REMARK,PRIMARYREMARK,PLAN_NUM," +
							"REAL_SEND_NUM,NO_SEND_NUM,DECT_PRICE,ALLPRICE,VOLUME,ALLVOLDTL,PAR_PRD_NAME,ROWSTATE,STATE,EXPAND_REMARK,REMARK";
		String colType= "string,string,string,string,string,string,string,string,string," +
					   "string,string,string,string,string,string,string,string,number," +
					   "number,number,number,number,number,number,string,string,string,string,string";
		try {
			ExcelUtil
					.doExport(response, list, "出货计划维护", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
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
			String DELIVER_ORDER_DTL_IDS = request
					.getParameter("DELIVER_ORDER_DTL_IDS");
			// String DELIVER_ORDER_ID =
			// request.getParameter("DELIVER_ORDER_ID");
			// 批量删除，多个Id之间用逗号隔开
			deliveryhdService.txBatch4DeleteChild(DELIVER_ORDER_DTL_IDS);
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
	 * * 根据销售订单id查询销售订单信息
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
	public void getChld(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		UserBean userBean = ParamUtil.getUserBean(request);
		try {
			String SALE_ORDER_DTL_IDS = request
					.getParameter("SALE_ORDER_DTL_IDS");
			String[] SALE_ORDER_DTL_IDArr = SALE_ORDER_DTL_IDS.split(",");
			String SALE_ORDER_DTL_ID = "";
			for (String str : SALE_ORDER_DTL_IDArr) {
				SALE_ORDER_DTL_ID += "'" + str + "',";
			}
			if (!StringUtil.isEmpty(SALE_ORDER_DTL_ID)) {
				SALE_ORDER_DTL_ID = SALE_ORDER_DTL_ID.substring(0,
						SALE_ORDER_DTL_ID.length() - 1);
			}
			List<TurnoverplanChildModel> lis = deliveryhdService.findChld(
					SALE_ORDER_DTL_ID, userBean.getCHANNS_CHARG());
			jsonResult = new Gson().toJson(new Result(true, lis, ""));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "新增失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	
	/**
	 * * 冻结明细 新增/修改数据
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
	public void freeChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String DELIVER_ORDER_ID = request.getParameter("DELIVER_ORDER_ID");
			String jsonDate = request.getParameter("gchildJsonData");
			if (!StringUtil.isEmpty(jsonDate)) {
				List<DeliveryhdGchldModel> modelList = new Gson().fromJson(
						jsonDate, new TypeToken<List<DeliveryhdGchldModel>>() {
						}.getType());
				deliveryhdService.txGchildEdit(DELIVER_ORDER_ID, modelList);
			}
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
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
	 * * 冻结明细批量删除
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
	public void freeChildDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_DELETE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String DELIVER_FREEZE_DTL_IDS = request.getParameter("DELIVER_FREEZE_DTL_IDS");
			// 批量删除，多个Id之间用逗号隔开
			deliveryhdService.txBatch4DeleteGchild(DELIVER_FREEZE_DTL_IDS);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "删除失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	private boolean checkIsClose(String stats) {
		String[] closeStats = { "已提交库房" };
		for (int i = 0; i < closeStats.length; i++) {
			String colseStats = closeStats[i];
			if (stats.equals(colseStats)) {
				return true;
			}
		}
		return false;
	}

	private String getDeliverDtlId(String deliverId) {
		StringBuffer sqlBuff = new StringBuffer();
		List deliverDtlIdList = deliveryhdService.getDeliverDtlId(deliverId);
		for (int i = 0; deliverDtlIdList != null && i < deliverDtlIdList.size(); i++) {
			HashMap deliverDtlIdMap = (HashMap) deliverDtlIdList.get(i);
			String DELIVER_ORDER_DTL_ID = (String) deliverDtlIdMap
					.get("DELIVER_ORDER_DTL_ID");
			sqlBuff.append("'").append(DELIVER_ORDER_DTL_ID).append("'")
					.append(",");
		}
		String deliverDtlIds = sqlBuff.toString();
		if (deliverDtlIds.endsWith(",")) {
			deliverDtlIds = deliverDtlIds.substring(0,
					deliverDtlIds.length() - 1);
		}
		return deliverDtlIds;
	}
	
	
	/**
	 * * 冻结明细列表
	 * 
	 * @return the action forward
	 */
	public ActionForward freeChildList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_BWS)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String DELIVER_ORDER_ID = ParamUtil.get(request, "DELIVER_ORDER_ID");
		if (!StringUtil.isEmpty(DELIVER_ORDER_ID)) {
			List<DeliveryhdGchldModel> result = deliveryhdService
					.freeChildQuery(DELIVER_ORDER_ID);
			if (null != result) {
				request.setAttribute("resultSize", result.size());
			}
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		return mapping.findForward("freeChildlist");
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
	public ActionForward toFreeChildEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		// 多个Id批量查询，用逗号隔开
		String DELIVER_FREEZE_DTL_IDS = request.getParameter("DELIVER_FREEZE_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(DELIVER_FREEZE_DTL_IDS)) {
			List<DeliveryhdGchldModel> list = deliveryhdService.freeChildQueryByIds(DELIVER_FREEZE_DTL_IDS);
			request.setAttribute("rst", list);
		}
		request.setAttribute("CHANNS_CHARG", userBean.getCHANNS_CHARG());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("freeChildedit");
	}
	
	
	
	/**
	 * 查询订单的生命周期
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
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) && !QXUtil.checkMKQX(
						userBean, PVG_BWS_AUDIT))) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String SALE_ORDER_ID = ParamUtil.get(request, "SALE_ORDER_ID");
		if (!StringUtil.isEmpty(SALE_ORDER_ID)) {
			List<Map<String, String>> result = deliveryhdService
					.queryTrace(SALE_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("toTrace");
	}

	public String getHtml(Map<String, String> map, int pageNo, int pageSize) {
		long totalCount = deliveryhdService.getCount(map);
		if (pageSize == 0) {
			// pageSize = Consts.PAGE_SIZE;
			pageSize = 100;
		}
		int start = (pageNo - 1) * pageSize;
		long curPageNo = start / pageSize + 1;
		long totalPageCount = 0;
		if (totalPageCount == 0) {
			if (totalCount % pageSize == 0)
				totalPageCount = totalCount / pageSize;
			else
				totalPageCount = totalCount / pageSize + 1;
		}
		String html = getToolbarHtml(curPageNo, totalPageCount, totalCount,
				pageSize);
		return html;
	}

	/**
	 * 翻页控制.
	 * 
	 * @return the toolbar html
	 */
	public String getToolbarHtml(long curPageNo, long totalPage,
			long totalCount, int pageSize) {
		StringBuffer html = new StringBuffer();
		html.append("&nbsp;&nbsp;共").append(totalCount).append(
				"条记录&nbsp;&nbsp;");
		// 总页数大于1才显示上一页
		if (totalPage > 1) {
			html
					.append("&nbsp;<span class='otherPage' onclick='_gotopageahead()'>&nbsp;上一页&nbsp;</span>&nbsp;");
		}
		if (curPageNo < 6) {
			for (int i = 0; i < totalPage; i++) {
				if (i > 5) {
					if (totalPage > 6) {
						if (totalPage > 7) {
							html.append("<b>.&nbsp;.&nbsp;.</b>");
						}
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ totalPage
										+ ")' >&nbsp;&nbsp;"
										+ totalPage
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
					break;
				} else {
					int j = i + 1;
					if (curPageNo == j) {
						html.append("<span class='curPage' >&nbsp;&nbsp;" + j
								+ "&nbsp;&nbsp;</span>&nbsp;");
					} else {
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ j
										+ ")' >&nbsp;&nbsp;"
										+ j
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
				}
			}
		} else {
			html
					.append("<span class='otherPage' onclick='javascript:_gotopagecho("
							+ 1
							+ ")' >&nbsp;&nbsp;"
							+ 1
							+ "&nbsp;&nbsp;</span>&nbsp;");
			if (totalPage <= curPageNo + 3) {
				if (totalPage != 6 && totalPage != 7) {
					html.append("<b>.&nbsp;.&nbsp;.</b>");
				}
				for (long i = (totalPage - 6 == 0 ? 1 : totalPage - 6); i < totalPage; i++) {
					long j = i + 1;
					if (curPageNo == j) {
						html
								.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"
										+ j + "&nbsp;&nbsp;</span>&nbsp;");
					} else {
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ j
										+ ")' >&nbsp;&nbsp;"
										+ j
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
				}
			} else {
				html.append("<b>.&nbsp;.&nbsp;.</b>");
				for (long i = (curPageNo - 3), t = curPageNo + 2; i < t; i++) {
					long j = i + 1;
					if (curPageNo == j) {
						html
								.append("<span aligh='center' class='curPage' >&nbsp;&nbsp;"
										+ j + "&nbsp;&nbsp;</span>&nbsp;");
					} else {
						html
								.append("<span class='otherPage' onclick='javascript:_gotopagecho("
										+ j
										+ ")' >&nbsp;&nbsp;"
										+ j
										+ "&nbsp;&nbsp;</span>&nbsp;");
					}
				}
				html.append("<b>.&nbsp;.&nbsp;.</b>");
				html
						.append("<span class='otherPage' onclick='javascript:_gotopagecho("
								+ totalPage
								+ ")' >&nbsp;&nbsp;"
								+ totalPage
								+ "&nbsp;&nbsp;</span>&nbsp;");
			}
		}

		long tempNextPage = curPageNo + 1;
		if (totalPage > 1) {
			html
					.append("&nbsp;<span class='otherPage' onclick='_gotopagenext()'>&nbsp;下一页&nbsp;</span>");
		}

		html
				.append("&nbsp;&nbsp;每页&nbsp;<select onChange='_gotoPage3()' style='font-size:12px;' id='_gotoPageSize' class='page_no' maxlength='5'>");
		// xingkefa 2012-01-19 每页显示条数列表！ start
		String pageSizeList = Properties.getStrList("PAGE_SIZE_LIST");
		String[] pagesizes = pageSizeList.split(",");
		String pagesize = pageSize + "";
		for (int i = 0; i < pagesizes.length; i++) {
			if (pagesize.equals(pagesizes[i])) {
				html.append(
						"<option selected='selected' value='" + pagesizes[i]
								+ "'>").append(pagesizes[i])
						.append("</option>");
			} else {
				html.append("<option value='" + pagesizes[i] + "'>").append(
						pagesizes[i]).append("</option>");
			}
		}
		html.append("</select>&nbsp;条");
		// end
		html
				.append("&nbsp;&nbsp;到第&nbsp;<input id='_gotoPageNo' class='page_no' maxlength='5' value="
						+ curPageNo + ">&nbsp;页");
		html
				.append("&nbsp;<input type='button' id='_gotoPageNobt'  class='btn'  onclick='_gotoPage2()' value='确定'>");

		html.append("<script type='text/javascript'>");
		// 刘曰刚-2014-06-17 //
		// 修改当有选中货品时跳转页面的提示框，改为确定提示框，如果确定则跳转，取消则不变
		// 选择确定的页面后的跳转
		html.append("function _gotopagecho(page){");
		html.append("listForm").append(".pageNo.value = page;");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");

		// 点击下一页后的页面跳转
		// modify by zhuxw parent 有可能为null
		html
				.append("function _gotopagenext(){if("
						+ tempNextPage
						+ ">"
						+ totalPage
						+ "){ if(parent.showErrorMsg){parent.showErrorMsg('已经是最后一页!');return;}else{showErrorMsg('已经是最后一页!');return;}}");
		html.append("listForm").append(".pageNo.value = " + tempNextPage + ";");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");

		long tempAheadPage = curPageNo - 1;
		// 点击上一页后的页面跳转
		html
				.append("function _gotopageahead(){if("
						+ tempAheadPage
						+ "<"
						+ 1
						+ "){if(parent.showErrorMsg){parent.showErrorMsg('已经是第一页!');return;}else{showErrorMsg('已经是第一页!');return;}}");
		html.append("listForm")
				.append(".pageNo.value = " + tempAheadPage + ";");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");

		// 点击确定后的跳转
		html.append("function _gotoPage2(){");
		html.append("var inpt = g('_gotoPageNo');");
		html.append("var pageNo = inpt.value*1;");
		// 頁碼等於當前頁碼時，不飜頁
		html.append("if(").append(curPageNo).append(" == pageNo ){return;");
		// 頁碼是否超出範圍
		html.append("}else if (").append(totalPage).append(" < pageNo){");
		html.append("parent.showErrorMsg('页码超出范围!');return;");
		html.append("}else if ( 1 > pageNo){");
		html.append("parent.showErrorMsg('页码超出范围!');return;");
		html.append("}");
		// 頁碼跳轉
		html.append("listForm").append(".pageNo.value = pageNo;");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");

		html.append("}");

		// pagesizelist 改变时跳转！
		html.append("function _gotoPage3(){");
		html.append("var inpt = g('_gotoPageSize');");
		html.append("var pagesize = inpt.value;");
		// pagesize跳轉
		html.append("listForm").append(".pageSize.value = pagesize;");
		html.append("showWaitPanel('');");
		html.append("checkPrdId();");
		html.append("}");
		html
				.append("function checkPrdId(){")
				.append("var checkBox = $('#jsontb tr:gt(0) input:checked');")
				.append("if(!checkBox.length<1){")
				.append(
						"parent.showConfirm('页面有货品未下单，如不下单则所选货品记录会丢失,确定跳转吗？','bottomcontent.retrue()','N');")
				.append("closeWindow();").append("}else{").append(
						"setTimeout('").append("listForm").append(
						".submit()',100);").append("}").append("}");
		html.append("function retrue(){").append("setTimeout('").append(
				"listForm").append(".submit()',100);").append("}");
		html.append("</script>");
		return html.toString();
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
		pvgMap.put("PVG_ROW_CLOSE", QXUtil.checkPvg(userBean, PVG_ROW_CLOSE));
		pvgMap.put("AUD_TAB_NAME", AUD_TAB_NAME);
		pvgMap.put("AUD_TAB_KEY", AUD_TAB_KEY);
		pvgMap.put("AUD_BUSS_STATE", AUD_BUSS_STATE);
		pvgMap.put("AUD_BUSS_TYPE", AUD_BUSS_TYPE);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("AUD_FLOW_INS", AUD_FLOW_INS);
		pvgMap.put("PVG_SHIP_EDIT", QXUtil.checkPvg(userBean, PVG_SHIP_EDIT));
		return pvgMap;
	}

	/**
	 * @return the deliveryhdService
	 */
	public DeliveryhdService getDeliveryhdService() {
		return deliveryhdService;
	}

	/**
	 * @param deliveryhdService
	 *            the deliveryhdService to set
	 */
	public void setDeliveryhdService(DeliveryhdService deliveryhdService) {
		this.deliveryhdService = deliveryhdService;
	}

}
