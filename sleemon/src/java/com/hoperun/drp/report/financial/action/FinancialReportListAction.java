package com.hoperun.drp.report.financial.action;

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

public class FinancialReportListAction extends BaseAction{
	
	
	private static String PVG_BWS = "FX0110301";
	
	private static String PVG_CREDIT_BWS = "FX0110302";
	//渠道财务进销存
	private static String PVG_INVOC_ACCT="FX0110303";
	//返利抵扣查询
	private static String PVG_BWS_REBATE="FX0110305";
	
	//合同执行情况
	private static String PVG_BWS_CONTRACT_IMPLEMENT="FX0110304";
	
	//信用流水账
	private static String CREDIT_ACCT_OUT = "FX0110306";
	//渠道财务--门店销售统计表
	private static String PVG_STORE_BWS="FX0110307";
	//渠道采取 待确认预订单
	private static String PVG_UNCOMM_BWS = "FX0110308";
	//门店发货统计表(供货价)
	private static String PVG_BWS_TERM_SALEOUT="FX0110309";
	//库存实时查询(供货价)
	private static String PVG_BWS_STORE="FX0110310";
	//门店销售排名
	private static String PVG_TOP_SALE="FX0110311";
	private static String PVG_ANNUALREBATE_BWS="FX0110312";
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
		request.setAttribute("CHANNID",userBean.getCHANN_ID());
		
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
		pvgMap.put("PVG_CREDIT_BWS", QXUtil.checkPvg(userBean, PVG_CREDIT_BWS));
		pvgMap.put("PVG_INVOC_ACCT", QXUtil.checkPvg(userBean, PVG_INVOC_ACCT));
		pvgMap.put("PVG_BWS_CONTRACT_IMPLEMENT", QXUtil.checkPvg(userBean, PVG_BWS_CONTRACT_IMPLEMENT));
		pvgMap.put("PVG_BWS_REBATE", QXUtil.checkPvg(userBean, PVG_BWS_REBATE));
		pvgMap.put("CREDIT_ACCT_OUT", QXUtil.checkPvg(userBean, CREDIT_ACCT_OUT));
		pvgMap.put("PVG_STORE_BWS", QXUtil.checkPvg(userBean, PVG_STORE_BWS));
		pvgMap.put("PVG_UNCOMM_BWS", QXUtil.checkPvg(userBean, PVG_UNCOMM_BWS));
		pvgMap.put("PVG_BWS_TERM_SALEOUT", QXUtil.checkPvg(userBean, PVG_BWS_TERM_SALEOUT));
		pvgMap.put("PVG_BWS_STORE", QXUtil.checkPvg(userBean, PVG_BWS_STORE));
		pvgMap.put("PVG_TOP_SALE", QXUtil.checkPvg(userBean, PVG_TOP_SALE));
		pvgMap.put("PVG_ANNUALREBATE_BWS", QXUtil.checkPvg(userBean, PVG_ANNUALREBATE_BWS));
		
		return pvgMap;
	}

}
