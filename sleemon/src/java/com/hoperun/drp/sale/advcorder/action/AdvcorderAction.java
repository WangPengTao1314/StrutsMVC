/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderAction
 */
package com.hoperun.drp.sale.advcorder.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.NumberUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelTrace;
import com.hoperun.drp.sale.advcorder.service.AdvcorderService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class AdvcorderAction extends BaseAction {
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
	private static String PVG_FINISH_CANCLE = "FX0020907";
	private static String AUD_BUSS_STATE = "STATE";
	/** end */
	// 下载，导入
	private static String PVG_FILE = "FX0020905";
	private static String PVG_CLOSE="FX0020909";//关闭
	private static String PVG_RETURN_AUDIT="FX0022504";//反审核
	private static String PVG_UNCOMM="FX0020908";//待确认
	private static String PVG_UPDATE_ADVC="FX0020910";//客户信息变更
	private static String PVG_PRICE_MODIFY="FX0022505";//销售价格修改
	private static String PVG_ALLPRINT="FX0020911";//批量打印
	private static String PVG_ADVC_CLOSE_PRINT="FX0020912";//关闭订单打印
	// 解除冻结
	private static String PVG_REMOVE_FREEZE = "FX0020906";
	/** 审批维护必须维护字段 **/
	// 提交撤销
	private static String PVG_COMMIT_CANCLE = "FX0020904";
	private static String PVG_TRACE = "";
	// 审核模块
	private static String PVG_BWS_AUDIT = "FX0022501";
	private static String PVG_AUDIT_AUDIT = "FX0022502";
	private static String PVG_RETURN="FX0022503";//退回
	private static String PVG_TRACE_AUDIT = "";
	// 审批流参数
	private static String AUD_TAB_NAME = "";// 表名
	private static String AUD_TAB_KEY = "";// 主键
	private static String AUD_BUSS_TYPE = "";
	private static String AUD_FLOW_INS = "";
	
	
	/** 审批 end **/
	/** 业务service */
	private AdvcorderService advcorderService;

	/**
	 * Sets the Advcorder service.
	 * 
	 * @param AdvctoorderService
	 *            the new Advcorder service
	 */
	public void setAdvcorderService(AdvcorderService advcorderService) {
		this.advcorderService = advcorderService;
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
		// [STATE][search]add by zzb 分销首页 [待发货预订单]url用到 该参数
		String search = ParamUtil.get(request, "search");
		String STATE = ParamUtil.utf8Decoder(request, "STATE");
		String isNoEdit = ParamUtil.utf8Decoder(request, "isNoEdit");
		request.setAttribute("paramUrl", paramUrl);
		request.setAttribute("search", search);
		request.setAttribute("STATE", STATE);
		request.setAttribute("isNoEdit", isNoEdit);
		return mapping.findForward("frames");
	}
	/**
	 * 相关单据跟踪查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openAdvcInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<Map<String,String>> result = advcorderService.joinAdvcQuery(ADVC_ORDER_ID,userBean.getCHANN_TYPE());
			request.setAttribute("rst", result);
		}
		return mapping.findForward("joinAdvc");
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
		ParamUtil.putStr2Map(request, "CLOSEFLAG", params);
		String PROMOTE_NO=ParamUtil.utf8Decoder(request, "PROMOTE_NO");
		params.put("PROMOTE_NOQuery", StringUtil.creCon("u.PROMOTE_NO", PROMOTE_NO, ","));
		String PROMOTE_NAME=ParamUtil.utf8Decoder(request, "PROMOTE_NAME");
		params.put("PROMOTE_NAMEQuery", StringUtil.creCon("u.PROMOTE_NAME", PROMOTE_NAME, ","));
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
//		Test();
		// 权限级别和审批流的封装
		if("sh".equals(module)){
			params.put("QXJBCON", ParamUtil.getPvgCon("true", "", PVG_BWS_AUDIT,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
			// 初始时只显示未接收的数据
			if (StringUtil.isEmpty(search)) {
				// 状态为未提交
				params.put("searchSTATE", "'提交'");
			} else{
				params.put("searchSTATE", "'提交','退回','审核通过'");
			}
		}else{
			params.put("QXJBCON", ParamUtil.getPvgCon(search, "", PVG_BWS,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
			// 初始时只显示未接收的数据
			if (StringUtil.isEmpty(search)) {
				// 状态为未提交
				params.put("searchSTATE", "'未提交','退回'");
			}
		}
		//是否可编辑标记
		String isNoEdit = ParamUtil.get(request, "isNoEdit");
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
		IListPage page = advcorderService.pageQuery(params, pageNo);
		String sysdate=advcorderService.getDate();
		boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),sysdate);
		request.setAttribute("isMonthAcc", isMonthAcc);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("RYXXID", userBean.getRYXXID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("module", module);
		request.setAttribute("isNoEdit", isNoEdit);
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
			List<AdvcorderModelChld> result = advcorderService
					.childQuery(ADVC_ORDER_ID);
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
		String updateFlag=ParamUtil.get(request, "updateFlag");
		request.setAttribute("updateFlag", updateFlag);
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
		String updateFlag=ParamUtil.get(request, "updateFlag");
		String ADVC_ORDER_ID = ParamUtil.get(request, "selRowId");
		Map<String, String> entry = new HashMap<String, String>();
		// 获取数据库当前日期
		String DATE = advcorderService.getDate();
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			entry = advcorderService.load(ADVC_ORDER_ID);
		} else {
			// 按登陆人所属部门id查询终端信息
			Map<String, String> map = advcorderService.getTerminalInfoById(userBean.getBMXXID());
			if (null != map) {
				entry.put("TERM_ID", map.get("TERM_ID"));
				entry.put("TERM_NO", map.get("TERM_NO"));
				entry.put("TERM_NAME", map.get("TERM_NAME"));
				entry.put("TERMFLAG", "1");
			}
			entry.put("SALE_DATE", DATE);
			// 获取当前登录人员姓名id
			entry.put("SALE_PSON_NAME", userBean.getXM());
			entry.put("SALE_PSON_ID", userBean.getRYXXID());
		}
		entry.put("ZTXXID", userBean.getLoginZTXXID());// 终端查询条件传值
		entry.put("DATE", DATE);
		request.setAttribute("rst", entry);
		request.setAttribute("LEDGER_ID",userBean.getLoginZTXXID());
		request.setAttribute("updateFlag", updateFlag);
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
		String updateFlag=ParamUtil.get(request, "updateFlag");
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld> result = advcorderService
					.childQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		float TERM_DECT_FROM = advcorderService.getTERM_DECT_FROM(userBean
				.getRYXXID());
		request.setAttribute("TERM_DECT_FROM", TERM_DECT_FROM);
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("MAX_FREZZ_DAYS", advcorderService.getMaxFrezzDays(userBean.getLoginZTXXID()));
		request.setAttribute("updateFlag", updateFlag);
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
		String ADVC_ORDER_DTL_IDS = request.getParameter("ADVC_ORDER_DTL_IDS");
		// 没有零星领料Id可以直接跳转。
		if (!StringUtil.isEmpty(ADVC_ORDER_DTL_IDS)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
			params.put("USE_FLAG", BusinessConsts.DEL_FLAG_DROP);
			List<AdvcorderModelChld> list = advcorderService.loadChilds(params);
			request.setAttribute("rst", list);
		}
		float TERM_DECT_FROM = advcorderService.getTERM_DECT_FROM(userBean
				.getRYXXID());
		request.setAttribute("TERM_DECT_FROM", TERM_DECT_FROM);
		request.setAttribute("CHANN_ID", userBean.getCHANN_ID());
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		
		request.setAttribute("MAX_FREZZ_DAYS", advcorderService.getMaxFrezzDays(userBean.getLoginZTXXID()));
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
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			String parentJsonData = ParamUtil.get(request, "parentJsonData");
			String oldSaleDate = ParamUtil.get(request, "oldSaleDate");
			String updateFlag = ParamUtil.get(request, "updateFlag");
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
			advcorderService.txEdit(ADVC_ORDER_ID, model, chldModelList,
					userBean,oldSaleDate,updateFlag);

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
				advcorderService
						.txChildEdit(ADVC_ORDER_ID, modelList, userBean,"");
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
	 * * 关闭
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
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			String ADVC_ORDER_DTL_IDS=request.getParameter("ADVC_ORDER_DTL_IDS");
			Map<String,String> map=new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("ADVC_ORDER_DTL_IDS", ADVC_ORDER_DTL_IDS);
			map.put("STATE", BusinessConsts.COMMON_COLSE);
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			advcorderService.txClose(map);
		} catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
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
			advcorderService.txDelete(params);
			advcorderService.clearCaches(params);
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
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
			advcorderService.txBatch4DeleteChild(ADVC_ORDER_DTL_IDS,
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
		if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_AUDIT))){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			Map<String, String> entry = advcorderService.load(ADVC_ORDER_ID);
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
			List<AdvcorderModelGchld> list = advcorderService
					.gchildQuery(ADVC_ORDER_ID);
			List<AdvcorderModelChld> chldList = advcorderService.childQuery(ADVC_ORDER_ID);
			double amount=advcorderService.amountGetById(ADVC_ORDER_ID);
			double dtlAmount=advcorderService.payAmountGetById(ADVC_ORDER_ID);
			if (chldList.size() == 0) {
				throw new ServiceException("没有明细信息，不能提交!");
			}
			String message=advcorderService.checkAdvcAllPrice(ADVC_ORDER_ID);
			if(!StringUtil.isEmpty(message)){
				throw new ServiceException(message);
			}
			if (list.size() == 0&&amount>0) {
				throw new ServiceException("付款明细没有明细信息，不能提交!");
			}
			if (dtlAmount != amount&&amount>0) {
				throw new ServiceException("付款明细的付款金额之和应该等于主表的订金金额，请检查后重新提交!");
			} else {
				Map<String, Object> RateMap = new HashMap<String, Object>();
				RateMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
				RateMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				RateMap.put("PRD_TYPE", "'赠品'");
				float RATE = advcorderService.getRate(RateMap);
				float TERM_DECT_FROM = advcorderService.getTERM_DECT_FROM(userBean.getRYXXID());
				if (RATE < TERM_DECT_FROM && RATE != -1) {
					RateMap.put("RATE", RATE);
					jsonResult = new Gson().toJson(new Result(false, RateMap, "失败"));
				} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
					map.put("STATE", BusinessConsts.COMMIT);
					map.put("UPDATOR", userBean.getRYXXID());
					map.put("UPD_NAME", userBean.getXM());
					map.put("UPD_TIME", "数据库时间");
					map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					map.put("PAYED_TOTAL_AMOUNT", "订金金额");
//					MsgInfo mesgObj = advcorderService.txCommitById(map,
//							userBean);
//					if (!mesgObj.isFLAG()) {
//						jsonResult = jsonResult(false, mesgObj.getMESS());
//					} else {
//						jsonResult = jsonResult(true, "");
//					}
					MsgInfo msgObj = advcorderService.txUpStateById(map,userBean,amount);
					if (!msgObj.isFLAG()) {
						jsonResult = jsonResult(false, msgObj.getMESS());
					} else {
						jsonResult = jsonResult(true, "");
					}
				}

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
	public void checkAdvcAmout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
 
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
//			String ADVC_AMOUNT = ParamUtil.get(request, "ADVC_AMOUNT");
//			Map<String,Object> result = advcorderService.queryStatements(ADVC_ORDER_ID);
//			if(null != result){
//				Double ADVC_AMOUNT_ = StringUtil.getDouble(result.get("ADVC_AMOUNT"));
//				Double ADVC_AMOUNT_D = StringUtil.getDouble(ADVC_AMOUNT);
//				if(ADVC_AMOUNT_ != ADVC_AMOUNT_D){
//					
//				}
//			}
			
			boolean havaWRITE = advcorderService.chackHavaWRITE(ADVC_ORDER_ID);
			if(havaWRITE){
				throw new ServiceException("客户收款单已核销，请先反核销!");
			}else{
				//没有核销的单据 直接删除
				advcorderService.deletePayments(ADVC_ORDER_ID);
			}
			 
		}catch (ServiceException s) {
			jsonResult = jsonResult(false, s.getMessage());
			s.printStackTrace();
		}catch (Exception e) {
			jsonResult = jsonResult(false, "");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
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
			List<AdvcorderModelGchld> result = advcorderService
					.gchildQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("gchildlist");
	}

	/**
	 * 解除冻结
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void removeFreeze(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_REMOVE_FREEZE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String advcOrderId = ParamUtil.get(request, "ADVC_ORDER_ID");
			String ADVC_ORDER_DTL_IDS = request
					.getParameter("ADVC_ORDER_DTL_IDS");
			advcorderService.txUpdateFreeze(advcOrderId, ADVC_ORDER_DTL_IDS);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "解除失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 审核通过
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void toAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_AUDIT_AUDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map<String,String> map=new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("STATE", BusinessConsts.PASS);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			double amount=advcorderService.amountGetById(ADVC_ORDER_ID);
			MsgInfo msgObj = advcorderService.txUpStateById(map,userBean,amount);
			if (!msgObj.isFLAG()) {
				jsonResult = jsonResult(false, msgObj.getMESS());
			} else {
				jsonResult = jsonResult(true, "");
			}
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "审核失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**
	 * 撤销
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void toCancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_COMMIT_CANCLE)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map<String,String> map=new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("STATE", BusinessConsts.UNCOMMIT);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			MsgInfo msgObj = advcorderService.txUpStateById(map,userBean,0);
			if (!msgObj.isFLAG()) {
				jsonResult = jsonResult(false, msgObj.getMESS());
			} else {
				jsonResult = jsonResult(true, "");
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "审核失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 反审核
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void toRetuAudit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_RETURN_AUDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map<String,String> map=new HashMap<String, String>();
			boolean flag=advcorderService.getTraceById(ADVC_ORDER_ID);
			if(flag){
				map.put("STATE", "待确认");
			}else{
				map.put("STATE", BusinessConsts.COMMIT);
			}
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			map.put("CHANN_TYPE", userBean.getCHANN_TYPE());
			//根据预订单id查询明细是否有未处理或者已发货的
			String str=advcorderService.checkReturnAudit(map);
			if(!StringUtil.isEmpty(str)){
				throw new ServiceException(str);
			}
			int amount=advcorderService.checkWriteFlag(ADVC_ORDER_ID);
			if(amount>0){
				throw new ServiceException("对不起，该单据存在已核销的单子，不能反审核 !");
			}
			//反审核的时候 查询时候核销
			int writeoff = advcorderService.queryWriteoffCount(ADVC_ORDER_ID);
			if(writeoff>0){
				throw new ServiceException("对不起，该单据已经开始核销，不能反审核 !");
			}
			Map<String,String> model=advcorderService.load(ADVC_ORDER_ID);
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),model.get("SALE_DATE"));
			if(isMonthAcc){
				throw new ServiceException("销售结算日期:"+model.get("SALE_DATE")+"已月结不能退回");
			}
			advcorderService.txRetuAudit(map,userBean);
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "退回失败");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**
	 * 退回
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void toReturn(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_RETURN)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
			Map<String,String> map=new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("RETURN_RSON", ParamUtil.get(request, "RETURN_RSON"));
			map.put("STATE", BusinessConsts._BACK);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "数据库时间");
			MsgInfo msgObj = advcorderService.txUpStateById(map,userBean,0);
			if (!msgObj.isFLAG()) {
				jsonResult = jsonResult(false, msgObj.getMESS());
			} else {
				jsonResult = jsonResult(true, "");
			}
		} catch (Exception e) {
			jsonResult = jsonResult(false, "退回成功");
			logger.info(e);
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
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
	public ActionForward modifyToGchildEdit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_EDIT)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelGchld> result = advcorderService
					.gchildQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("gchildedit");
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
			List<AdvcorderModelGchld> list = advcorderService
					.loadGchilds(params);
			request.setAttribute("rst", list);
		}
		
		request.setAttribute("LEDGER_ID", userBean.getLoginZTXXID());
		return mapping.findForward("gchildedit");
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
			List<AdvcorderModelTrace> result = advcorderService
					.traceQuery(ADVC_ORDER_ID);
			request.setAttribute("rst", result);
		}
		return mapping.findForward("traceList");
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
				advcorderService.txGchildEdit(ADVC_ORDER_ID, modelList);
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
			advcorderService.txBatch4DeleteGchild(PAYMENT_DTL_IDS);
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
	 * EXECL导入解析 Description :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @SuppressWarnings("unchecked")
	 */
	@SuppressWarnings("unchecked")
	public void parseExecl(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		UserBean userBean = ParamUtil.getUserBean(request);
		String jsonResult = jsonResult();
		try {
			String serverDir = Properties.getString("UPLOADFILE_DIR");
			String fileName = "sample"
					+ ParamUtil.utf8Decoder(request, "fileName");
			String secPath = Properties.getString("SAMPLE_DIR");
			String p = serverDir + File.separatorChar + secPath
					+ File.separatorChar + fileName;
			Map<String,String> map = new HashMap<String,String>();
			List<Map<String,String>> alist = new ArrayList<Map<String,String>>();
			String[] a = new String[]{"1"};
			map.put("TERM_NO", "0");//终端编号
			map.put("TERM_NAME", "1");//终端名称
			map.put("SALE_DATE", "2");//销售日期
			map.put("SALE_PSON_NAME", "3");//业务员
			map.put("CUST_NAME", "4");//客户名称
			map.put("TEL", "5");//电话
			map.put("RECV_ADDR", "6");//收货地址
			map.put("REMARK", "7");//备注
			map.put("CONTRACT_NO", "8");//合同编号
			map.put("PROMOTE_NO", "9");//渠道活动编号
			map.put("PROMOTE_NAME", "10");//渠道活动名称
			map.put("CUSTOMER_AGE", "11");//消费者年龄
			map.put("CUSTOMER_SOURCE", "12");//客户来源
			map.put("CUSTOMER_DEMAND", "13");//客户需求
			map.put("PRD_NO", "14");//货品编号
			map.put("PRD_NAME", "15");//货品名称
//			map.put("PRD_SPEC", "16");//规格型号
//			map.put("PRD_COLOR", "17");//颜色
//			map.put("BRAND", "18");//品牌
//			map.put("STD_UNIT", "19");//标准单位
			map.put("PRICE", "16");//单价
			map.put("DECT_PRICE", "17");//折后单价
			map.put("ORDER_NUM", "18");//订货数量
			map.put("PAYABLE_AMOUNT", "19");//应收金额
			map.put("REMARK_DTL", "20");//明细备注
			map.put("SPCL_TECH_NO", "21");//特殊工艺编号
			map.put("SPCL_TECH_REMARK", "22");//特殊工艺描述
			map.put("PRD_TYPE", "23");//货品类型
			map.put("ORDER_RECV_DATE", "24");//交货日期
			map.put("PAY_TYPE", "25");//付款方式
			map.put("PAY_INFO", "26");//付款信息
			map.put("PAY_AMONT", "27");//付款金额
			map.put("PAY_BILL_NO", "28");//付款单据号
			alist.add(map);
			List lists = ExcelUtil.readExcelbyModel(fileName, p,1,alist, a);
			advcorderService.txParseExeclToDbNew(lists, userBean);
			jsonResult = jsonResult(true, "上传成功");// 
		} catch (ServiceException e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, e.getMessage());
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			jsonResult = jsonResult(false, "导入失败，请联系管理员");
			
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 转预订单核价
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * 
	 */
	public void toPrice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		UserBean userBean = ParamUtil.getUserBean(request);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = ParamUtil.utf8Decoder(request,
					"ADVC_ORDER_ID");
			Map<String, String> entry = advcorderService.load(ADVC_ORDER_ID);
			Map<String, String> map = new HashMap<String, String>();
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			map.put("CHK_PRICE_PSON_ID", ParamUtil.utf8Decoder(request,
					"RYXXID"));
			map
					.put("CHK_PRICE_PSON_NAME", ParamUtil.utf8Decoder(request,
							"XM"));
			map.put("STATE", "待核价");
			advcorderService.txUpdateById(map);
			Map<String, String> priceMap = new HashMap<String, String>();
			priceMap.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
			priceMap.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			priceMap.put("ACTION", "预订单核价");
			priceMap.put("REMARK", "已生成");
			priceMap.put("ACTOR", userBean.getXM());
			priceMap.put("BILL_NO", entry.get("ADVC_ORDER_NO"));
			advcorderService.insert("Advcorder.insertTrace", priceMap);
		} catch (Exception e) {
			e.printStackTrace();
			jsonResult = jsonResult(false, "提交失败");
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 取消预订
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toCanCel(ActionMapping mapping, ActionForm form,
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
			advcorderService.txBatch4DeleteChild(ADVC_ORDER_DTL_IDS,
					ADVC_ORDER_ID);
		} catch (Exception e) {
			jsonResult = jsonResult(false, "取消预订失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**
	 * 打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward printInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	 String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
//		 	 Map<String,String> map=new HashMap<String, String>();
//		 	 map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
//		 	 map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//		 	String amount=NumberUtil.format(advcorderService.getPAYABLE_AMOUNT(map));
		 	//记录打印次数
		 	StringBuffer paramSql=new StringBuffer();
		 	paramSql.append("advcPrint.raq&ADVC_ORDER_ID=").append(ADVC_ORDER_ID);
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_ADVC_ORDER");
		 	printMap.put("BUSS_ID", ADVC_ORDER_ID);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
		 	 /** 直接打印
			 StringBuffer advSql = new StringBuffer();
			 advSql.append("rptSql=select ")
			 		.append("a.ADVC_ORDER_ID,a.CUST_NAME,a.REMARK,")
			 		.append("a.TERM_NAME,")
			 		.append("a.TEL,")
			 		.append("b.TEL TERTEL,")
			 		.append("b.ADDRESS,")
			 		.append("a.RECV_ADDR,")
			 		.append("a.CUST_NAME,")
			 		.append("a.TEL,")
			 		.append("b.TAX,")
			 		.append("a.ORDER_RECV_DATE,")
			 		.append("a.CRE_NAME,")
			 		.append("a.ADVC_ORDER_NO,")
			 		.append("(NVL(a.PAYABLE_AMOUNT,0)-NVL(a.PAYED_TOTAL_AMOUNT,0))RESIDUE_AMOUNT,")
			 		.append("a.PAYABLE_AMOUNT,")
			 		.append("a.DISCOUNT_AMOUNT,")
			 		.append("to_char(a.SALE_DATE, 'yyyy-MM-DD') GETDATE")
			 		.append(" from DRP_ADVC_ORDER a left join BASE_TERMINAL b on a.TERM_ID=b.TERM_ID ")
			 		.append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 		.append(" and ADVC_ORDER_ID  ='").append(ADVC_ORDER_ID).append("'; ");
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select rownum,temp.* from ( select ")
			 	   .append("a.ADVC_ORDER_ID,a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.PRICE,")
			 	   .append("a.DECT_RATE,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("a.ORDER_NUM,")
			 	   .append("a.PAYABLE_AMOUNT,")
			 	   .append("a.REMARK,")
			 	   .append("b.SPCL_DTL_REMARK")
			 	   .append(" from DRP_ADVC_ORDER_DTL a ")
			 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and USE_FLAG=1 ")
			 	   .append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 	   .append(" and a.ADVC_ORDER_ID ='").append(ADVC_ORDER_ID).append("' and a.STATE='正常' order by a.PRD_TYPE ) temp  ");
			 int count=advcorderService.getDtlCount(ADVC_ORDER_ID);
			 double page=0;
			 if(count%8!=0){
				 page=Math.ceil((double)count/8);
			 }
			 double newCount=page*8;
			 for (int i = 0; i < (newCount-count); i++) {
				 dtlSql.append(" union all select ").append(count+i+1).append(",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual ");
			}
			 dtlSql.append(";");
			 StringBuffer paymentSql=new StringBuffer();
			 paymentSql.append("paymentSql=select ")
			 		   .append("ADVC_ORDER_ID,PAY_TYPE,")
			 		   .append("PAY_AMONT,")
			 		   .append("PAY_TYPE,")
			 		   .append("PAY_BILL_NO,")
			 		   .append("PAY_INFO")
			 		   .append(" from DRP_PAYMENT_DTL ")
			 		   .append(" where ADVC_ORDER_ID ='").append(ADVC_ORDER_ID).append("' ")
			 		   .append(" and DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON).append("; ");
			 StringBuffer sql=new StringBuffer();
			 StringBuffer arg=new StringBuffer();
			 arg.append(" arg1=").append(newCount);
			 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString()).append(arg.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName", "advcPrint.raq");
			 **/
			 System.out.println(paramSql.toString());
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 
//			 return mapping.findForward("smReportPrint");
			 return mapping.findForward("flashReportPrint");
	}
	
	
	/**
	 * 关闭打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward closePrintInfo(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	 String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
		 	 Map<String,String> map=new HashMap<String, String>();
		 	 map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		 	 map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 	 //判断是否存在已关闭的预订单
		 	 int checkCount=advcorderService.checkCloseFlag(ADVC_ORDER_ID);
		 	 if(checkCount<=0){
		 		throw new ServiceException("对不起，该单据没有货品已关闭，不能打印 !");
		 	 }
		 	//记录打印次数
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_ADVC_ORDER");
		 	printMap.put("BUSS_ID", ADVC_ORDER_ID);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
		 	StringBuffer paramSql=new StringBuffer();
		 	paramSql.append("advcClosePrint.raq&ADVC_ORDER_ID=").append(ADVC_ORDER_ID);
		 	/**
			 StringBuffer advSql = new StringBuffer();
			 advSql.append("rptSql=select ")
			 		.append("a.ADVC_ORDER_ID,a.CUST_NAME,a.REMARK,")
			 		.append("a.TERM_NAME,")
			 		.append("a.TEL,")
			 		.append("b.TEL TERTEL,")
			 		.append("b.ADDRESS,")
			 		.append("a.RECV_ADDR,")
			 		.append("a.CUST_NAME,")
			 		.append("a.TEL,")
			 		.append("b.TAX,")
			 		.append("a.ORDER_RECV_DATE,")
			 		.append("a.CRE_NAME,")
			 		.append("a.ADVC_ORDER_NO,")
			 		.append("(NVL(a.PAYABLE_AMOUNT,0)-NVL(a.PAYED_TOTAL_AMOUNT,0))RESIDUE_AMOUNT,")
			 		.append("a.PAYABLE_AMOUNT,")
			 		.append("a.DISCOUNT_AMOUNT,")
			 		.append("to_char(a.SALE_DATE, 'yyyy-MM-DD') GETDATE")
			 		.append(" from DRP_ADVC_ORDER a left join BASE_TERMINAL b on a.TERM_ID=b.TERM_ID ")
			 		.append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 		.append(" and ADVC_ORDER_ID  ='").append(ADVC_ORDER_ID).append("'; ");
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select rownum,temp.* from ( select ")
			 	   .append("a.ADVC_ORDER_ID,a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.PRICE,")
			 	   .append("a.DECT_RATE,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("a.ORDER_NUM,")
			 	   .append("a.PAYABLE_AMOUNT,")
			 	   .append("a.REMARK,")
			 	   .append("b.SPCL_DTL_REMARK")
			 	   .append(" from DRP_ADVC_ORDER_DTL a ")
			 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and USE_FLAG=1 ")
			 	   .append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 	   .append(" and a.ADVC_ORDER_ID ='").append(ADVC_ORDER_ID).append("' and a.STATE='关闭' order by a.PRD_TYPE ) temp  ");
			 int count=advcorderService.getDtlCount(ADVC_ORDER_ID);
			 double page=0;
			 if(count%8!=0){
				 page=Math.ceil((double)count/8);
			 }
			 double newCount=page*8;
			 for (int i = 0; i < (newCount-count); i++) {
				 dtlSql.append(" union all select ").append(count+i+1).append(",NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL from dual ");
			}
			 dtlSql.append(";");
			 StringBuffer paymentSql=new StringBuffer();
			 paymentSql.append("paymentSql=select ")
			 		   .append("ADVC_ORDER_ID,PAY_TYPE,")
			 		   .append("PAY_AMONT,")
			 		   .append("PAY_TYPE,")
			 		   .append("PAY_BILL_NO,")
			 		   .append("PAY_INFO")
			 		   .append(" from DRP_PAYMENT_DTL ")
			 		   .append(" where ADVC_ORDER_ID ='").append(ADVC_ORDER_ID).append("' ")
			 		   .append(" and DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON).append("; ");
			 StringBuffer sql=new StringBuffer();
			 StringBuffer arg=new StringBuffer();
			 arg.append(" arg1=").append(newCount);
			 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString()).append(arg.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName", "advcPrint.raq");
			 **/
			 System.out.println(paramSql.toString());
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			 
//			 return mapping.findForward("smReportPrint");
			 return mapping.findForward("flashReportPrint");
	}
	
	
	/**
	 * 批量打印
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward allPrint(ActionMapping mapping, ActionForm form,
			 HttpServletRequest request, HttpServletResponse response) {
		 	 String sessionId = request.getParameter("sessionId");
		 	 String ADVC_ORDER_IDS=(String) request.getSession().getAttribute(sessionId);
		 	 Map<String,String> map=new HashMap<String, String>();
		 	 map.put("ADVC_ORDER_IDS", ADVC_ORDER_IDS);
		 	 map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 	String amount=NumberUtil.format(advcorderService.getPAYABLE_AMOUNT(map));
		 	//记录打印次数
		 	Map<String,String> printMap=new HashMap<String, String>();
		 	printMap.put("TABLE_NAME", "DRP_ADVC_ORDER");
		 	printMap.put("BUSS_ID", ADVC_ORDER_IDS);
		 	if(!LogicUtil.txUpdatePrintNum(printMap)){
		 		throw new ServiceException("对不起，记录打印次数有误，请联系管理员 !");
		 	}
		 	StringBuffer paramSql=new StringBuffer();
		 	paramSql.append("batchAdvcPrint.raq&ADVC_ORDER_ID=").append(ADVC_ORDER_IDS);
		 	/**
			 StringBuffer advSql = new StringBuffer();
			 advSql.append("rptSql=select ")
			 		.append("a.ADVC_ORDER_ID,a.CUST_NAME,rownum,")
			 		.append("a.TERM_NAME,")
			 		.append("a.TEL,")
			 		.append("b.TEL TERTEL,")
			 		.append("b.ADDRESS,")
			 		.append("a.RECV_ADDR,")
			 		.append("a.CUST_NAME,")
			 		.append("a.TEL,")
			 		.append("b.TAX,")
			 		.append("a.ORDER_RECV_DATE,")
			 		.append("a.CRE_NAME,")
			 		.append("a.ADVC_ORDER_NO,")
			 		.append("(NVL(a.PAYABLE_AMOUNT,0)-NVL(a.PAYED_TOTAL_AMOUNT,0))RESIDUE_AMOUNT,")
			 		.append("to_char(a.SALE_DATE, 'yyyy-MM-DD') GETDATE")
			 		.append(" from DRP_ADVC_ORDER a left join BASE_TERMINAL b on a.TERM_ID=b.TERM_ID ")
			 		.append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 		.append(" and ADVC_ORDER_ID  in (").append(ADVC_ORDER_IDS).append("); ");
			 StringBuffer dtlSql=new StringBuffer();
			 dtlSql.append("dtlSql=select rownum,temp.* from ( select ")
			 	   .append("a.ADVC_ORDER_ID,a.PRD_NAME,")
			 	   .append("a.PRD_SPEC,")
			 	   .append("a.PRICE,")
			 	   .append("a.DECT_RATE,")
			 	   .append("a.DECT_PRICE,")
			 	   .append("a.ORDER_NUM,")
			 	   .append("a.PAYABLE_AMOUNT,")
			 	   .append("a.REMARK,")
			 	   .append("b.SPCL_DTL_REMARK")
			 	   .append(" from DRP_ADVC_ORDER_DTL a ")
			 	   .append(" left join DRP_SPCL_TECH b on a.SPCL_TECH_ID=b.SPCL_TECH_ID and USE_FLAG=1 ")
			 	   .append(" where a.DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON)
			 	   .append(" and a.ADVC_ORDER_ID in (").append(ADVC_ORDER_IDS).append(") ")
			 	   .append("union all select ADVC_ORDER_ID,'折扣金额','',NULL,NULL,NULL,NULL,(-1)*DISCOUNT_AMOUNT PAYABLE_AMOUNT,'' REMARK,''  FROM DRP_ADVC_ORDER")
			 	   .append(" WHERE ADVC_ORDER_ID in (").append(ADVC_ORDER_IDS).append(")  and DISCOUNT_AMOUNT>0 ")
			 	   .append(" order by PRD_NAME asc ) temp;");
			 StringBuffer paymentSql=new StringBuffer();
			 paymentSql.append("paymentSql=select ")
			 		   .append("ADVC_ORDER_ID,PAY_TYPE,")
			 		   .append("PAY_AMONT,")
			 		   .append("PAY_TYPE,")
			 		   .append("PAY_BILL_NO,")
			 		   .append("PAY_INFO")
			 		   .append(" from DRP_PAYMENT_DTL ")
			 		   .append(" where ADVC_ORDER_ID in (").append(ADVC_ORDER_IDS).append(")")
			 		   .append(" and DEL_FLAG=").append(BusinessConsts.DEL_FLAG_COMMON).append("; ");
			 StringBuffer sql=new StringBuffer();
			 StringBuffer arg=new StringBuffer();
			 arg.append(" arg1=").append(amount);
			 sql.append(advSql.toString()).append(dtlSql.toString()).append(paymentSql.toString()).append(arg.toString());
			 // 报表路径名称
			 request.setAttribute("reportFileName", "batchAdvcPrint.raq");
			 **/
			 System.out.println(paramSql.toString());
			 request.getSession().removeAttribute(sessionId);
			 // 要传递的宏参数！！！
			 request.setAttribute("params", paramSql.toString());
			
			 return mapping.findForward("flashReportPrint");
	}
	
	public void getAdvcSession(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_IDS = request.getParameter("ADVC_ORDER_IDS");
			String sessionId=StringUtil.uuid32len();
			Map<String,String> map=new HashMap<String, String>();
			if(!StringUtil.isEmpty(ADVC_ORDER_IDS)){
				request.getSession().setAttribute(sessionId, ADVC_ORDER_IDS);
				map.put("sessionId",sessionId);
			}
			jsonResult = new Gson().toJson(new Result(true, map, ""));
		} catch (Exception e) {
			jsonResult = jsonResult(false, "存储选取预订单信息失败！");
			e.printStackTrace();
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
	public void toUncomm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& !QXUtil.checkMKQX(userBean, PVG_UNCOMM)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		logger.info("按钮修改为启用单条记录开始");
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			List<AdvcorderModelChld> chldList = advcorderService.childQuery(ADVC_ORDER_ID);
			for(int i=0;i<chldList.size();i++){
				Map<String,Object> model=(Map<String, Object>) chldList.get(i);
				if(!StringUtil.isEmpty(model.get("IS_FREEZE_FLAG").toString())&&"1".equals(model.get("IS_FREEZE_FLAG").toString())){
					throw new ServiceException("待交期确认数据不能冻结货品!");
				}
			}
			double amount = advcorderService.amountGetById(ADVC_ORDER_ID);
			double payAmount = advcorderService.payAmountGetById(ADVC_ORDER_ID);
			
			if (amount != payAmount&&amount>0) {
				throw new ServiceException("付款明细的付款金额之和应该等于主表的订金金额，请检查后重新提交!");
			} else {
					Map<String, String> map = new HashMap<String, String>();
					map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
					map.put("STATE", "待确认");
					map.put("UPDATOR", userBean.getRYXXID());
					map.put("UPD_NAME", userBean.getXM());
					map.put("UPD_TIME", "数据库时间");
					advcorderService.txUpStartDoUncomm(map,userBean,amount);
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
	
	
	
	// 导出
	@SuppressWarnings("unchecked")
	public void expertExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
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
		//导出时如果有关闭则只查关闭数据
		ParamUtil.putStr2Map(request, "CLOSEFLAG", params);
		String PROMOTE_NO = ParamUtil.utf8Decoder(request, "PROMOTE_NO");
		params.put("PROMOTE_NOQuery", StringUtil.creCon("u.PROMOTE_NO", PROMOTE_NO, ","));
		String PROMOTE_NAME = ParamUtil.utf8Decoder(request, "PROMOTE_NAME");
		params.put("PROMOTE_NAMEQuery", StringUtil.creCon("u.PROMOTE_NAME", PROMOTE_NAME, ","));
		ParamUtil.putStr2Map(request, "STATE", params);
		ParamUtil.putStr2Map(request, "TEL", params);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		if("sh".equals(module)){
			params.put("QXJBCON", ParamUtil.getPvgCon("true", "", PVG_BWS_AUDIT,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
			// 初始时只显示未接收的数据
			if (StringUtil.isEmpty(search)) {
				// 状态为未提交
				params.put("searchSTATE", "'提交'");
			} else{
				params.put("searchSTATE", "'提交','退回','审核通过'");
			}
		}else{
			params.put("QXJBCON", ParamUtil.getPvgCon(search, "", PVG_BWS,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
			// 初始时只显示未接收的数据
			if (StringUtil.isEmpty(search)) {
				// 状态为未提交
				params.put("searchSTATE", "'未提交','退回'");
			}
		}
		// 设置帐套id
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		// 设置终端销售
		params.put("BILL_TYPE", "终端销售");
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 字段排序
		
 
	 
		List list = advcorderService.expertExcelQuery(params);
		// excel数据上列显示的列明
		String tmpContentCn ="预订单编号,终端编号,终端名称,合同编号,客户姓名,电话,定金金额,应收总额,业务员,销售日期," +
				"货品编号,货品名称,规格型号,品牌,特殊规格说明," +
				"单价,折扣率,折后单价,订货数量," +
				"交货日期,是否冻结,冻结数量,冻结库房,应收金额,已发数量,取消数量";
		String tmpContent = "ADVC_ORDER_NO,TERM_NO,TERM_NAME,CONTRACT_NO,CUST_NAME,TEL,ADVC_AMOUNT,F_PAYABLE_AMOUNT,SALE_PSON_NAME,SALE_DATE," +
				"PRD_NO,PRD_NAME,PRD_SPEC,BRAND,SPCL_DTL_REMARK," +
				"PRICE,DECT_RATE,DECT_PRICE,ORDER_NUM," +
				"ORDER_RECV_DATE,IS_FREEZE_FLAG,FREEZE_NUM,FREEZE_STORE_NAME,PAYABLE_AMOUNT,SEND_NUM,CANCLE_ORDER_NUM";
		String colType= "string,string,string,string,string,string,number,number,string,string," +
					   "string,string,string,string,string," +
					   "number,number,number,number," +
					   "string,string,number,string,number,number,number";
		try {
			ExcelUtil
					.doExport(response, list, "预订单", tmpContent, tmpContentCn,colType);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 保存销售价格
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void childPriceEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_PRICE_MODIFY)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String jsonDate = ParamUtil.get(request, "childJsonData");
			List<AdvcorderModelChld> chldModelList = null;
			if (!StringUtil.isEmpty(jsonDate)) {
				chldModelList = new Gson().fromJson(jsonDate,
						new TypeToken<List<AdvcorderModelChld>>() {
						}.getType());
			}
			advcorderService.txUpdateChldPrice(chldModelList);
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
	 * 验证是否存在发货申请单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void checkSendAdvcNum(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_UPDATE_ADVC)) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			int count=advcorderService.getSendAdvcNum(ADVC_ORDER_ID);
			if(count>0){
				throw new ServiceException("对不起，您所选的预订单已存在发货申请单，不能变更客户信息 !");
			}
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "获取变更信息失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**
	 * 搜索框查询
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void seachBox(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_NO = request.getParameter("PRD_NO");
			String PRD_NAME = request.getParameter("PRD_NAME");
			Map<String,String> map=new HashMap<String, String>();
			if(!StringUtil.isEmpty(PRD_NO)){
				map.put("TYPE", "PRD_NO");
				map.put("VALUE", PRD_NO);
			}
			if(!StringUtil.isEmpty(PRD_NAME)){
				map.put("TYPE", "PRD_NAME");
				map.put("VALUE", PRD_NAME);
			}
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			map.put("STATE", "('启用','停用')");
			map.put("FINAL_NODE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			map.put("IS_NO_STAND_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
			map.put("LEDGER_ID", userBean.getLoginZTXXID());
			map.put("COUNT", "11");
			List<Map<String,String>> list =advcorderService.querySeachBox(map);
			jsonResult = new Gson().toJson(new Result(true, list, ""));
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "获取信息失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	/**
	 * 根据货品Id获取货品信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void getPrdInfoById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String PRD_ID = request.getParameter("PRD_ID");
			Map<String,String> map =advcorderService.getPrdInfoById(PRD_ID);
			jsonResult = new Gson().toJson(new Result(true, map, ""));
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "获取信息失败");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}
	
	/**
	 * 弹出修改销售价格页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward openPriceModify(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_PRICE_MODIFY)){
			throw new ServiceException("对不起，您没有权限 !");
		}
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");
		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			List<AdvcorderModelChld>result = advcorderService.priceChldQuery(ADVC_ORDER_ID,userBean.getCHANN_TYPE());
			request.setAttribute("rst", result);
		}
		return mapping.findForward("priceModify");
	}
	
	public ActionForward toAllPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if(Consts.FUN_CHEK_PVG && !QXUtil.checkMKQX(userBean, PVG_ALLPRINT)){
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
		ParamUtil.putStr2Map(request, "TEL", params);
		String search = ParamUtil.get(request, "search");
		String module = ParamUtil.get(request, "module");
		// 权限级别和审批流的封装
		if("sh".equals(module)){
			params.put("QXJBCON", ParamUtil.getPvgCon("true", "", PVG_BWS_AUDIT,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
		}else{
			params.put("QXJBCON", ParamUtil.getPvgCon(search, "", PVG_BWS,
					PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
					AUD_BUSS_STATE, userBean));
		}
		//是否可编辑标记
		String isNoEdit = ParamUtil.get(request, "isNoEdit");
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
		if(StringUtil.isEmpty(params.get("pageSize"))){
			params.put("pageSize", "20");
		}
		IListPage page = advcorderService.pageQuery(params, pageNo);
		String sysdate=advcorderService.getDate();
		boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),sysdate);
		request.setAttribute("isMonthAcc", isMonthAcc);
		request.setAttribute("params", params);
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("page", page);
		request.setAttribute("BMXXID", userBean.getBMXXID());
		request.setAttribute("RYXXID", userBean.getRYXXID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("module", module);
		request.setAttribute("isNoEdit", isNoEdit);
		request.setAttribute("search", search);
		return mapping.findForward("allPrint");
	}
	/**
	 * 导出模版
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void ExcelOutputModel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        //excel数据上列显示的列明
        String tmpContentCn="终端编号（必填）,终端名称（必填）,销售日期（必填）,业务员（必填）,客户名称（必填）,电话（必填）,收货地址（必填）,备注,合同编号,渠道活动编号," +
        					"渠道活动名称,消费者年龄,客户来源,客户需求,货品编号（必填）,货品名称,"+//,规格型号（必填）,颜色,品牌,标准单位," +
        					"单价（必填）,折后单价（必填）,订货数量（必填）,应收金额（必填）,明細备注,特殊工艺编号,特殊工艺描述,是否赠品（必填）,交货日期（必填）,付款方式（存在订金时必填）,付款信息," +
        					"付款金额（存在订金时必填）,付款单据号";
        try {
        	doExport(response, "预订单模版",tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	//Excel文件导出成文件流
	//dataList 需要导出的数据列表
	//execlName 导出后默认的文件名
	//tmpContent:数据库字段名，多字段以逗号分割
	//tmpContentCnexcel:文件名字段名，多字段以逗号分割
	/**
	 * Do export.
	 * 
	 * @param response the response
	 * @param dataList the data list
	 * @param execlName the execl name
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * 
	 * @throws Exception the exception
	 */
	public  void  doExport(HttpServletResponse response,String fileNameCn,String tmpContentCn) throws Exception{
		//生成excel
        HSSFWorkbook workbook = PrintExcel(tmpContentCn);
        //导出excel
        writeExecl(response,workbook,fileNameCn);
	}
	
	/** add by zhuxw 2014-08-18 增加字段类型
	 * Prints the excel.
	 * 
	 * @param tmpContent the tmp content
	 * @param tmpContentCn the tmp content cn
	 * @param dataList the data list
	 * 
	 * @return the hSSF workbook
	 */
	private  HSSFWorkbook PrintExcel(String ContentCn){
		HSSFWorkbook workbook = null;
	     String[] titles_CN = ContentCn.split(",");
	     try{
	          //创建工作簿实例 
	           workbook = new HSSFWorkbook();
	          //创建工作表实例 
	         HSSFSheet sheet = workbook.createSheet("Sheet1"); 
	          //设置列宽 
	          setSheetColumnWidth(titles_CN,sheet);
	        //获取样式 
	          HSSFCellStyle style = createTitleStyle(workbook); 
	               //创建第一行标题 
	                HSSFRow row = sheet.createRow((short)0);// 建立新行
	                row.setHeight((short) 0);
	                createCell(row, 0, style, HSSFCell.CELL_TYPE_STRING, "hoperun");
	                HSSFRow rows = sheet.createRow((short)1);// 建立新行
	                for(int i=0;i<titles_CN.length;i++){
	                createCell(rows, i, null, HSSFCell.CELL_TYPE_STRING, 
	                       titles_CN[i]);
	                }
	  }catch(Exception e){
	              e.printStackTrace();
	  }
	 return workbook;
	}
	/**
	 * Write execl.
	 * 
	 * @param response the response
	 * @param workbook the workbook
	 * @param execlName the execl name
	 */
	public static void writeExecl(HttpServletResponse response,HSSFWorkbook workbook, String execlName) {
		if (null == workbook)
		{
			workbook = new HSSFWorkbook();
		}
		
		if (0 == workbook.getNumberOfSheets()) {
			HSSFSheet sheet = workbook.createSheet("无数据");
			sheet.createRow(3).createCell(3).setCellValue("未查询到数据!");
		}
		response.reset();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/msexcel;charset=UTF-8");
		ServletOutputStream outputStream = null;
		try {
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(execlName.getBytes("gb2312"), "ISO8859-1") + "_" + DateFormatUtils.format(new Date(), "MM-DD") + ".xls");
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != outputStream) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//设置列宽
	/**
	 * Sets the sheet column width.
	 * 
	 * @param titles_CN the titles_ cn
	 * @param sheet the sheet
	 */
	private  void setSheetColumnWidth(String[] titles_CN,HSSFSheet sheet){ 
	   // 根据你数据里面的记录有多少列，就设置多少列
	  for(int i=0;i<titles_CN.length;i++){
		  sheet.setColumnWidth((short)i, (short) 6000);
	          
	  }
	}
	//设置excel的title样式  
	/**
	 * Creates the title style.
	 * 
	 * @param wb the wb
	 * 
	 * @return the hSSF cell style
	 */
	private HSSFCellStyle createTitleStyle(HSSFWorkbook wb) { 
	  HSSFFont boldFont = wb.createFont(); 
	  boldFont.setFontHeight((short) 200); 
	  HSSFCellStyle style = wb.createCellStyle(); 
	  style.setFont(boldFont); 
	  style.setDataFormat(HSSFDataFormat.getBuiltinFormat("###,##0.00"));
	  //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	  //style.setFillBackgroundColor(HSSFColor.LIGHT_ORANGE.index);
	  return style;  
	}
	//创建Excel单元格  
	/**
	 * Creates the cell.
	 * 
	 * @param row the row
	 * @param column the column
	 * @param style the style
	 * @param cellType the cell type
	 * @param value the value
	 */
	private void createCell(HSSFRow row, int column, HSSFCellStyle style,int cellType,Object value) { 
	  HSSFCell cell = row.createCell( column);  
	  if (style != null) { 
	       cell.setCellStyle(style); 
	  }   
	  String res = (value==null?"":value).toString();
	  switch(cellType){ 
	       case HSSFCell.CELL_TYPE_BLANK: {} break; 
	       case HSSFCell.CELL_TYPE_STRING: {cell.setCellValue(res+"");} break; 
	       case HSSFCell.CELL_TYPE_NUMERIC: {
	       cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC); 
	           cell.setCellValue(Double.parseDouble(value.toString()));}break; 
	       default: break; 
		 }  
		
		} 
	/**
	 * 反审核前验证是否为待确认订单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void toRetuAuditCheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String ADVC_ORDER_ID = request.getParameter("ADVC_ORDER_ID");
			boolean flag=advcorderService.getTraceById(ADVC_ORDER_ID);
			Map<String,Object> map =new HashMap<String, Object>();
			map.put("flag", flag);
			jsonResult = new Gson().toJson(new Result(true, map, ""));
		}catch(ServiceException s){
			jsonResult = jsonResult(false, s.getMessage());
		} catch (Exception e) {
			jsonResult = jsonResult(false, "获取信息失败");
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
		pvgMap.put("PVG_PRICE_MODIFY", QXUtil.checkPvg(userBean,PVG_PRICE_MODIFY));
		pvgMap.put("PVG_ALLPRINT", QXUtil.checkPvg(userBean,PVG_ALLPRINT));
		pvgMap.put("PVG_ADVC_CLOSE_PRINT", QXUtil.checkPvg(userBean,PVG_ADVC_CLOSE_PRINT));
		return pvgMap;
	}
}