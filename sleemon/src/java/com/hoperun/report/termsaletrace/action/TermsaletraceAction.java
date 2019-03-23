package com.hoperun.report.termsaletrace.action;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.termsaletrace.service.TermsaletraceService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——门店销售跟踪表
 * @author gu_hongxiu
 *
 */
public class TermsaletraceAction  extends BaseAction {
	private Logger logger = Logger.getLogger(TermsaletraceAction.class);
	
	
	
	private TermsaletraceService termsaletraceService;
	
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		HttpSession session = request.getSession(true);
//		String USERID = request.getParameter("S_USER_ID");
//		String ZTXXID = request.getParameter("S_ZTXXID");
//		String jumpflag = request.getParameter("S_GOTO_FLG");
//		if("true".equals(jumpflag)){
//			UserBean aUserBean = new UserBean();
//			aUserBean.setXTYHID(USERID);
//			aUserBean.setLoginZTXXID(ZTXXID);
//			session.setAttribute("UserBean", aUserBean);	
//		}
		//设置跳转时需要的一些公用属性
		String rptConFile=request.getParameter("rptConFile");
		request.setAttribute("rptConFile", rptConFile);
		String sjcd  = request.getParameter("sjcd");
		request.setAttribute("sjcd", sjcd);
		String backpath = request.getParameter("backPath");
		request.setAttribute("backPath", backpath);
		request.setAttribute("actionPath", mapping.getPath());
        return mapping.findForward("frames");
	}
	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toConDition(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
		Map<String,String> params = new HashMap<String,String>();
		params.put("XTYH_ID", userBean.getXTYHID());
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		request.setAttribute("params", params);
		
		//设置跳转时需要的一些公用属性
		String rptConFile=request.getParameter("rptConFile");
		request.setAttribute("rptConFile", rptConFile);
		String sjcd  = request.getParameter("sjcd");
		request.setAttribute("sjcd", sjcd);
		String backPath=request.getParameter("backPath");
		request.setAttribute("backPath", backPath);
		String actionPath=request.getParameter("actionPath");
		request.setAttribute("actionPath", actionPath);
        return mapping.findForward(rptConFile);
	}
  
  /**
	 * 查询结果报表
	 * Description :
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
  public ActionForward toReportPageResult(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
 {
		UserBean userBean = ParamUtil.getUserBean(request);
		String LEDGER_ID = userBean.getLoginZTXXID();

		String sale_date = ParamUtil.get(request, "sale_date");
		String BRAND = ParamUtil.get(request, "BRAND");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String buyDate = ParamUtil.get(request, "buyDate");

		/********** 查询条件拼接 start **********/		
		StringBuffer condition = new StringBuffer(" where t.state != '未提交' and t.del_flag = 0 ");
		if (!StringUtil.isEmpty(LEDGER_ID)) {
			condition.append(" and t.LEDGER_ID='" + LEDGER_ID + "'");
		}
		
		if (!StringUtil.isEmpty(sale_date)) {
			condition.append(" and to_char(t.sale_date, 'YYYY-MM-DD')='" + sale_date
					+ "'");
		}
		if (!StringUtil.isEmpty(BRAND)) {
			condition.append(" and d.brand like '%" + BRAND + "%'");
		}		
		if (!StringUtil.isEmpty(ADVC_ORDER_NO)) {
			condition.append(" and t.advc_order_no='" + ADVC_ORDER_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and d.PRD_NO='" + PRD_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(" and d.PRD_NAME like '%" + PRD_NAME + "%'");
		}		
		
		if (!StringUtil.isEmpty(buyDate)) {
			condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')='" + buyDate
					+ "'");
		}
		
		String rptSql = termsaletraceService.createSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "termsaletrace.raq");
		request.setAttribute("printModel", "termsaletrace.raq");

		return mapping.findForward("pageResult");
  }
  
	
	public TermsaletraceService getTermsaletraceService() {
		return termsaletraceService;
	}

	public void setTermsaletraceService(TermsaletraceService termsaletraceService) {
		this.termsaletraceService = termsaletraceService;
	}
}


