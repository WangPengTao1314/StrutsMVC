package com.hoperun.drp.report.reportlist.action;

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
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.sys.model.UserBean;

public class DrpReportListAction extends BaseAction{
	
	
	private static String PVG_BWS = "FX0110201";
	
	private static String PVG_STORE_STOCK = "FX0110203";
	
	private static String PVG_FREEZE_STOCK_DTL = "FX0110204";
	//调拨统计
	private static String PVG_BWS_INVALL = "FX0110205";
	//
	private static String PVG_BWS_INVOC_NUM="FX0110206";
	//库存预订单对照备货查询
	private static String PVG_BWS_ADVC_STORE_ACCT="FX0110207";
	
	//渠道库存报表
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_BWS) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		String RUNQIAN_REPORT_URL=Consts.RUNQIAN_REPORT_URL;
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("pvg", setPvg(userBean));
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYHID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		return mapping.findForward("list");
	}
	
	//门店库存
	public ActionForward toStoreStockList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		if (Consts.FUN_CHEK_PVG
				&& (!QXUtil.checkMKQX(userBean, PVG_STORE_STOCK) )) {
			throw new ServiceException("对不起，您没有权限 !");
		}
		//当前报表URL
		String RUNQIAN_REPORT_URL = userBean.getCURRT_RPT_URL();
		String SLEEMON_REPORT_URL=Consts.SLEEMON_REPORT_URL;
		request.setAttribute("RUNQIAN_REPORT_URL", RUNQIAN_REPORT_URL);
		request.setAttribute("SLEEMON_REPORT_URL", SLEEMON_REPORT_URL);
		request.setAttribute("XTYH_ID", userBean.getXTYHID());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("pvg", setPvg(userBean));
		return mapping.findForward("storeStockList");
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
		pvgMap.put("PVG_STORE_STOCK", QXUtil.checkPvg(userBean, PVG_STORE_STOCK));
		pvgMap.put("PVG_FREEZE_STOCK_DTL", QXUtil.checkPvg(userBean, PVG_FREEZE_STOCK_DTL));
		pvgMap.put("PVG_BWS_INVALL", QXUtil.checkPvg(userBean, PVG_BWS_INVALL));
		pvgMap.put("PVG_BWS_INVOC_NUM", QXUtil.checkPvg(userBean, PVG_BWS_INVOC_NUM));
		pvgMap.put("PVG_BWS_ADVC_STORE_ACCT", QXUtil.checkPvg(userBean, PVG_BWS_ADVC_STORE_ACCT));
		return pvgMap;
	}

}
