package com.hoperun.report.returncount.action;
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
import com.hoperun.report.returncount.service.ReturncountService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public class ReturncountAction  extends BaseAction {
	private Logger logger = Logger.getLogger(ReturncountAction.class);
	
	
	
	private ReturncountService returncountService;
	
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
		HttpSession session = request.getSession(true);
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
		String rptConFile=request.getParameter("rptConFile");
		try{
			UserBean userBean =  ParamUtil.getUserBean(request);
			Map<String,String> params = new HashMap<String,String>();
			params.put("XTYH_ID", userBean.getXTYHID());
			request.setAttribute("params", params);
			
			//设置跳转时需要的一些公用属性
			request.setAttribute("rptConFile", rptConFile);
			String sjcd  = request.getParameter("sjcd");
			request.setAttribute("sjcd", sjcd);
			String backPath=request.getParameter("backPath");
			request.setAttribute("backPath", backPath);
			String actionPath=request.getParameter("actionPath");
			request.setAttribute("actionPath", actionPath);
		}catch(Exception ex){
			ex.printStackTrace();
		}
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
	  	UserBean userBean =  ParamUtil.getUserBean(request);
		String SALE_DATE = ParamUtil.get(request, "SALE_DATE");
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE");
		String TERM_NO = ParamUtil.get(request, "TERM_NO");
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String RT_DATE = ParamUtil.get(request, "RT_DATE");
		
		
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");
		
		if(!StringUtil.isEmpty(TERM_NO)){
			condition.append(" and t.TERM_NO='" + TERM_NO + "'");
		}
		if(!StringUtil.isEmpty(ADVC_ORDER_NO)){
			condition.append(" and t.ADVC_ORDER_NO='" + ADVC_ORDER_NO + "'");
		}
		if (!StringUtil.isEmpty(SALE_DATE)) {
			condition.append(" and to_char(t.SALE_DATE,'YYYY-MM-DD')='" + SALE_DATE + "'");
		}
		if (!StringUtil.isEmpty(RT_DATE)) {
			condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')='" + RT_DATE + "'");
		}
		if (!StringUtil.isEmpty(PRD_TYPE)) {
			condition.append(" and t.PRD_TYPE='" + PRD_TYPE + "'");
		}
		if(!StringUtil.isEmpty(PRD_NO)){
			condition.append(" and d.PRD_NO='" + PRD_NO + "'");
		}

		String rptOneSql = returncountService.createOneSql(condition.toString());
		

		request.setAttribute("conDition", "rptSql=" + rptOneSql);
		request.setAttribute("rptModel", "returnCount.raq");
		request.setAttribute("printModel", "returnCount.raq");

		return mapping.findForward("pageResult");
  }
  
	public ReturncountService getReturncountService() {
		return returncountService;
	}
	public void setReturncountService(ReturncountService returncountService) {
		this.returncountService = returncountService;
	}
}


