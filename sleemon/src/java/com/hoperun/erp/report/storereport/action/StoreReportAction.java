package com.hoperun.erp.report.storereport.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.Properties;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.erp.report.storereport.service.StoreReportService;
import com.hoperun.sys.model.UserBean;

public class StoreReportAction extends BaseAction{
	//门店报表
	private static String PVG_BWS = "FX0110401";
	//总部相关报表
	private static String PVG_BWS_HEADQUARTERS="FX0110601";
	//订单发货状态跟踪查询
	private static String PVG_BWS_GOODS="FX0110603";
	//销售订单出货统计明细表
	private static String PVG_BWS_ADVC_STATISTICAL="FX0110602";
	//发货情况统计表 
	private static String PVG_BWS_DELIVER="FX0110604";
	//门店销统计表
	private static String PVG_STORE_BWS = "FX0110402";
	//门店退货统计表
	private static String PVG_STORE_RETURN_BWS = "FX0110403";
	//待确认预订单统计
	private static String PVG_BWS_UNCOMM="FX0110409";
	//日销售商品明细表
	private static String PVG_BWS_ADC_ORDER="FX0110405";
	//收款情况查询
	private static String PVG_BWS_CLAUSE="FX0110406";
	//收款情况查询
	private static String PVG_BWS_REBATES="FX0110407";
	//门店发货统计表
	private static String PVG_BWS_TERM_SALEOUT = "FX0110408";
	//销售订单生产状态查询
	private static String PVG_BWS_ADVC_STORE="FX0110605";
	//总部对账单查询
	private static String PVG_BWS_ACCOUNT="FX0110606";
	
	//直营办报表浏览页面
	private static String PVG_BWS_TERM="FX0110701";
	//直营办-销售情况统计表
	private static String PVG_BWS_SALEA_ORDER="FX0110702";
	//直营办-发货情况统计表
	private static String PVG_BWS_SALEA_STOREOUT="FX0110703";
	//直营办-退货情况统计表
	private static String PVG_BWS_RETURNA="FX0110704";
	//直营办-进销存报表
	private static String PVG_BWS_INVOICING="FX0110705";
	
	private StoreReportService storeReportService;
	/**
	 * @return the reptShareViewService
	 */

	public StoreReportService getStoreReportService() {
		return storeReportService;
	}

	public void setStoreReportService(StoreReportService storeReportService) {
		this.storeReportService = storeReportService;
	}
	//销售报表
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL = Consts.SLEEMON_REPORT_URL;
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	//门店销售
	public ActionForward toStoreList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_STORE_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("storeList");
	}
	
	//门店退货
	public ActionForward toStoreReturnList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_STORE_RETURN_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("storeReturnList");
	}
	
	
	
	//总部相关报表
	public ActionForward toHeadReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS_HEADQUARTERS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		request.setAttribute("IS_NO_DELIVER_PLAN_FLAG", IS_NO_DELIVER_PLAN_FLAG);
		return mapping.findForward("list");
	}
	
	//直营办报表
	public ActionForward toTermReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS_TERM) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		String IS_NO_DELIVER_PLAN_FLAG=Properties.getString("IS_NO_DELIVER_PLAN_FLAG");
		request.setAttribute("IS_NO_DELIVER_PLAN_FLAG", IS_NO_DELIVER_PLAN_FLAG);
		return mapping.findForward("list");
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
		pvgMap.put("PVG_STORE_BWS", QXUtil.checkPvg(userBean, PVG_STORE_BWS));
		pvgMap.put("PVG_STORE_RETURN_BWS", QXUtil.checkPvg(userBean, PVG_STORE_RETURN_BWS));
		pvgMap.put("PVG_BWS_GOODS", QXUtil.checkPvg(userBean, PVG_BWS_GOODS));
		pvgMap.put("PVG_BWS_ADC_ORDER", QXUtil.checkPvg(userBean, PVG_BWS_ADC_ORDER));
		pvgMap.put("PVG_BWS_CLAUSE", QXUtil.checkPvg(userBean, PVG_BWS_CLAUSE));
		pvgMap.put("PVG_BWS_ADVC_STATISTICAL", QXUtil.checkPvg(userBean, PVG_BWS_ADVC_STATISTICAL));
		pvgMap.put("PVG_BWS_HEADQUARTERS", QXUtil.checkPvg(userBean, PVG_BWS_HEADQUARTERS));
		pvgMap.put("PVG_BWS_TERM_SALEOUT", QXUtil.checkPvg(userBean, PVG_BWS_TERM_SALEOUT));
		pvgMap.put("PVG_BWS_REBATES", QXUtil.checkPvg(userBean, PVG_BWS_REBATES));
		pvgMap.put("PVG_BWS_DELIVER", QXUtil.checkPvg(userBean, PVG_BWS_DELIVER));
		pvgMap.put("PVG_BWS_UNCOMM", QXUtil.checkPvg(userBean, PVG_BWS_UNCOMM));
		pvgMap.put("PVG_BWS_ADVC_STORE", QXUtil.checkPvg(userBean, PVG_BWS_ADVC_STORE));
		pvgMap.put("PVG_BWS_ACCOUNT", QXUtil.checkPvg(userBean, PVG_BWS_ACCOUNT));
		pvgMap.put("PVG_BWS_TERM", QXUtil.checkPvg(userBean, PVG_BWS_TERM));
		pvgMap.put("PVG_BWS_SALEA_ORDER", QXUtil.checkPvg(userBean, PVG_BWS_SALEA_ORDER));
		pvgMap.put("PVG_BWS_SALEA_STOREOUT", QXUtil.checkPvg(userBean, PVG_BWS_SALEA_STOREOUT));
		pvgMap.put("PVG_BWS_RETURNA", QXUtil.checkPvg(userBean, PVG_BWS_RETURNA));
		pvgMap.put("PVG_BWS_INVOICING", QXUtil.checkPvg(userBean, PVG_BWS_INVOICING));
		
		return pvgMap;
	}
	
}
