package com.hoperun.drp.main.firpage.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.firpage.service.DrpFirpageService;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.sys.model.NoticeModel;
import com.hoperun.sys.model.UserBean;

/**
 * 分销首页action
 * 
 * @author zhang_zhongbin
 * 
 */
public class DrpFirpageAction extends BaseAction {

	/** 权限对象 **/
	/** 维护 */
	// 增删改查
	private static String PVG_BWS = "";
	private static String PVG_EDIT = "";
	private static String PVG_DELETE = "";
	// 启用,停用
	private static String PVG_START_STOP = "";
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

	private DrpFirpageService drpFirpageService;

	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		UserBean userBean = ParamUtil.getUserBean(request);
		// if(Consts.FUN_CHEK_PVG&&!QXUtil.checkMKQX(userBean, PVG_BWS))
		// {
		// throw new ServiceException("对不起，您没有权限 !");
		// }

//		List<PrmtplanModel> prmtList = this.drpFirpageService.queryPrmtpList(
//				userBean, 5);
//
//		request.setAttribute("prmtList", prmtList);
//		request.setAttribute("noticeList", noticeList);
		return mapping.findForward("index");
	}

	/**
	 * 首页 滚动 促销信息
	 * 
	 * @param userBean
	 * @param request
	 */
	public void queryPrmt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		PrintWriter writer = getWriter(response);

		List<PrmtplanModel> prmtList = this.drpFirpageService.queryPrmtpList(
				userBean, 5);
		String jsonResult = new Gson().toJson(new Result(true, prmtList, ""));

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

	}

	/**
	 * 根据 NOTICE_ID 加载公告
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void loadNotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String NOTICE_ID = ParamUtil.get(request, "NOTICE_ID");
		Map<String, String> model = this.drpFirpageService
				.loadNoticeModel(NOTICE_ID);
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		if(null != model){
			jsonResult = jsonResult(true, model.get("NOTICE"));
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		
	}

	/**
	 * 点击 公告 更多信息
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMoreNotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("toMoreNotice");
	}

	/**
	 * 公告detail
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toNoticeDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String NOTICE_ID = ParamUtil.get(request, "NOTICE_ID");

		if (StringUtils.isNotEmpty(NOTICE_ID)) {
			Map<String, String> model = this.drpFirpageService
					.loadNoticeModel(NOTICE_ID);
			request.setAttribute("rst", model);
		}

		return mapping.findForward("toNoticeDetail");
	}

	/**
	 * 公告 更多信息的 列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward listNotice(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		Map<String, String> params = new HashMap<String, String>();
		String RYXXID = userBean.getRYXXID().toString();
		List<NoticeModel> noticers  = this.drpFirpageService.queryNoticers(RYXXID);
		int count = noticers.size();
		if(count==0){
			ParamUtil.putStr2Map(request, "STATIME", params);
			ParamUtil.putStr2Map(request, "ENDTIME", params);
			ParamUtil.putStr2Map(request, "NOTICE_TYPE", params);
			ParamUtil.putStr2Map(request, "NOTICE_TITLE", params);
			params.put("STATE", "'" + BusinessConsts.PASS + "'");
			if("1".equals(userBean.getIS_DRP_LEDGER())){//如果是分销
				params.put("XTYHID", userBean.getXTYHID());
			}else{
				params.put("BMXXIDS", userBean.getBMXXIDS());
			}
			String TERM_ID = userBean.getTERM_ID();
			String IS_DRP_LEDGER = userBean.getIS_DRP_LEDGER();
			String NOTICE_OBJ = "总部公告";
			if(BusinessConsts.INTEGER_1.equals(IS_DRP_LEDGER)){
				TERM_ID = userBean.getTERM_ID();
				System.out.println("CHANN_ID:"+userBean.getCHANN_ID()); 
				System.out.println("DEPTZ_NO:"+userBean.getBMXXID()); 
				System.out.println("TERM_ID:"+TERM_ID); 
				if(StringUtil.isEmpty(TERM_ID)){
					NOTICE_OBJ = "渠道公告";
				}else{
					NOTICE_OBJ = "门店公告";
				}
			}
			params.put("NOTICE_OBJ", NOTICE_OBJ);
			params.put("CHANN_ID", userBean.getCHANN_ID());
			int pageNo = ParamUtil.getInt(request, "pageNo", 1);
			//字段排序
			ParamUtil.setOrderField(request, params, "u.CRETIME", "DESC");
			IListPage page = drpFirpageService.pageQueryNotice(params, pageNo);
			request.setAttribute("params", params);
			request.setAttribute("page", page);
		}else{
			params.put("STATE", "'" + BusinessConsts.PASS + "'");
			int pageNo = ParamUtil.getInt(request, "pageNo", 1);
			//字段排序
			ParamUtil.setOrderField(request, params, "u.CRETIME", "DESC");
			IListPage page = drpFirpageService.pageAllQueryNotice(params, pageNo);
			request.setAttribute("params", params);
			request.setAttribute("page", page);
		}
		return mapping.findForward("listNotice");
	}

	/**
	 * 促销 更多
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toMorePrmt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		return mapping.findForward("toMorePrmt");
	}

	/**
	 * 促销detail
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toPrmtDetail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		String PRMT_PLAN_ID = ParamUtil.get(request, "PRMT_PLAN_ID");
		if (StringUtils.isNotEmpty(PRMT_PLAN_ID)) {
			Map<String, String> model = this.drpFirpageService
					.loadPrmtModel(PRMT_PLAN_ID);
			request.setAttribute("rst", model);
		}

		return mapping.findForward("toPrmtDetail");
	}

	/**
	 * 促销信息 列表
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward listPrmt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");
		Map<String, String> params = new HashMap<String, String>();
		ParamUtil.putStr2Map(request, "PRMT_PLAN_NO", params);
		ParamUtil.putStr2Map(request, "PRMT_PLAN_NAME", params);
		ParamUtil.putStr2Map(request, "PRMT_TYPE", params);
		ParamUtil.putStr2Map(request, "EFFCT_DATE_BEG", params);
		ParamUtil.putStr2Map(request, "EFFCT_DATE_END", params);
		params.put("STATE", "'" + BusinessConsts.ISSUANCE + "','"
				+ BusinessConsts.OVER + "'");
		params.put("XTYHID", userBean.getXTYHID());
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		params.put("CHANN_ID", userBean.getCHANN_ID());
		
		IListPage page = drpFirpageService.pageQueryPrmt(params, pageNo);

		request.setAttribute("params", params);
		request.setAttribute("page", page);

		return mapping.findForward("listPrmt");
	}

	/**
	 * 查询 待发货预订单 待入库 待退货
	 */
	public void queryCount(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = (UserBean) request.getSession(false).getAttribute(
				"UserBean");

		PrintWriter writer = getWriter(response);

//		List list = this.drpFirpageService.queryCount();
		int prdReturnReq = queryPrdRetuenReqCount(userBean);
		int storeIn = queryStoreInCount(userBean);
		//待发货预订单
		int order = queryOrderCount(userBean);
		Map<String,Object> map = new HashMap<String,Object> ();
		map.put("DRP_ADVC_ORDER", order);
		map.put("DRP_STOREIN_NOTICE", storeIn);
		map.put("DRP_PRD_RETURN_REQ", prdReturnReq);
		
		String jsonResult = new Gson().toJson(new Result(true, map, ""));

		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

	}
	
	
	

	/**
	 * 退货申请单  提交 状态
	 * @param userBean
	 * @return
	 */
	private int queryPrdRetuenReqCount(UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		// 审核模块
		String PVG_BWS_AUDIT = "BS0012101";
		String PVG_BWS = "FX0020701";
		// 审批流参数
		String AUD_TAB_NAME = "DRP_PRD_RETURN_REQ";
		String AUD_TAB_KEY = "PRD_RETURN_REQ_ID";
		String AUD_BUSS_STATE = "";
		String AUD_BUSS_TYPE = "DRP_PRD_RETURN_REQ_AUDIT";

		String search = "";
		String module = "wh";
		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		qx.append(" and STATE='提交' and DEL_FLAG=0 ");
		params.put("QXJBCON", qx.toString());
		int count = this.drpFirpageService.queryPrdRetuenReqCount(params);
		
		return count;
	}
	
	
	/**
	 * 入库通知单  提交 状态
	 * @param userBean
	 * @return
	 */
	private int queryStoreInCount(UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		// 审核模块
		String PVG_BWS_AUDIT = "FX0031201";
		String PVG_BWS = "FX0030301";
		// 审批流参数
		String AUD_TAB_NAME = "DRP_STOREIN_NOTICE";
		String AUD_TAB_KEY = "STOREIN_NOTICE_ID";
		String AUD_BUSS_STATE = "";
		String AUD_BUSS_TYPE = "DRP_STOREIN_NOTICE_AUDIT";

		String search = "";
		String module = "wh";

		StringBuffer qx = new StringBuffer(ParamUtil.getPvgCon(search, module, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
		
		qx.append(" and STATE='提交' and DEL_FLAG=0 and LEDGER_ID='"+userBean.getLoginZTXXID()+"' ");
		params.put("QXJBCON", qx.toString());
		int count = this.drpFirpageService.queryStoreIn(params);
		
		return count;
	}
	
	/**
	 * 查询 待发货预订单   状态 待发货
	 * @return
	 */
	private int queryOrderCount(UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("STATE",BusinessConsts.STATE_WAIT_SEND);//待发货
		params.put("BILL_TYPE",BusinessConsts.TYPE_END_SALE);//终端销售
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
		params.put("LEDGER_ID",userBean.getLoginZTXXID());
		
		String PVG_BWS="FX0020901";
	    // 审批流参数
	    String PVG_BWS_AUDIT="";
		String AUD_TAB_NAME = "";
		String AUD_TAB_KEY = "";
		String AUD_BUSS_STATE = "";
		String AUD_BUSS_TYPE = "";
	    
	   //权限级别和审批流的封装
		params.put("QXJBCON", ParamUtil.getPvgCon(null, null, PVG_BWS,
				PVG_BWS_AUDIT, AUD_TAB_KEY, AUD_TAB_NAME, AUD_BUSS_TYPE,
				AUD_BUSS_STATE, userBean));
	    
		
		return this.drpFirpageService.queryOrderCount(params);
	}
	
	/**
	 * 首页 【待办事宜】先判断是否有权限
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void checkHaveQx(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		//改为页面传递权限
		String QX = ParamUtil.get(request, "QX");
		//入库通知单
		//String STOREIN_PVG_BWS = "FX0030301";
		//预订单
		//String ADVC_PVG_BWS = "FX0020901";
		//退货申请单
		//String PRDRETURNREQ_PVG_BWS = "FX0020701";
		//投诉与建议
		//String PVG_ADVISEINIT_BWS = "FX0040101";
		//订货订单
		//String PVG_GOODSORHD_BWS = "FX0020401";
		 
		int error = 0;
		if (Consts.FUN_CHEK_PVG) {
			if(StringUtil.isEmpty(QX)){
				error = 0;
			}else if(!QXUtil.checkMKQX(userBean, QX)  ){
				error = 1;
			}
		}
		
		if(error == 1){
			jsonResult = jsonResult(true, "对不起，您没有权限");
		}else{
			jsonResult = jsonResult(false, "");
		}
		
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
		
		
	}
	/**
	 * 首页 柱状图
	 */
	public void queryBar(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		UserBean userBean = ParamUtil.getUserBean(request);
		PrintWriter writer = getWriter(response);
		List list = this.drpFirpageService.queryBar(userBean);
		String jsonResult = new Gson().toJson(new Result(true, list, ""));
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}

	}
	
	
	/**
	 * 查找附件
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void downFille(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			String noticeId = request.getParameter("NOTICEID");
			Map<String,String> result = drpFirpageService.queryFilePath(noticeId);
			jsonResult = new Gson().toJson(new Result(true, result, ""));	 
			 
		} catch (Exception e) {
			jsonResult = jsonResult(false, "");
			e.printStackTrace();
		}
		if (null != writer) {
			writer.write(jsonResult);
			writer.close();
		}
	}

	/**
	 * 查询发运单
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void queryDeliver(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		PrintWriter writer = getWriter(response);
		UserBean userBean = ParamUtil.getUserBean(request);
		String jsonResult = jsonResult();
		try {
			List<Map<String,Object>> result = drpFirpageService.queryDeliver(userBean);
			jsonResult = new Gson().toJson(new Result(true, result, ""));	 
			 
		} catch (Exception e) {
			jsonResult = jsonResult(false, "");
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

	public DrpFirpageService getDrpFirpageService() {
		return drpFirpageService;
	}

	public void setDrpFirpageService(DrpFirpageService drpFirpageService) {
		this.drpFirpageService = drpFirpageService;
	}

}
