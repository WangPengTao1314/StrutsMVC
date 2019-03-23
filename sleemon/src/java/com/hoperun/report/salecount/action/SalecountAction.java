package com.hoperun.report.salecount.action;
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
import com.hoperun.report.salecount.service.SalecountService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——订货订单信息
 * @author gu_hongxiu
 *
 */
public class SalecountAction  extends BaseAction {
	private Logger logger = Logger.getLogger(SalecountAction.class);
	
	
	
	private SalecountService salecountService;
	
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
		String SALE_DATE = ParamUtil.get(request, "SALE_DATE");
		
		String PRD_TYPE = ParamUtil.get(request, "PRD_TYPE");
		
		String ADVC_ORDER_ID = ParamUtil.get(request, "ADVC_ORDER_ID");

		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer("");
		
		if (!StringUtil.isEmpty(SALE_DATE)) {
			condition.append(" and to_char(t.SALE_DATE,'YYYY-MM-DD')='" + SALE_DATE
					+ "'");
		}
		if (!StringUtil.isEmpty(PRD_TYPE)) {
			condition.append(" and d.PRD_TYPE='" + PRD_TYPE + "'");
		}

		if (!StringUtil.isEmpty(ADVC_ORDER_ID)) {
			condition.append(" and t.ADVC_ORDER_ID = '" + ADVC_ORDER_ID + "'");
		}

//		String rptOneSql = salecountService.createOneSql(condition.toString());
//		String rptTwoSql = salecountService.createTwoSql();
//		request.setAttribute("conDition", "rpt1Sql=" + rptOneSql + ";rpt2Sql=" + rptTwoSql);
		
		
		String sql = salecountService.createNewSql();
		request.setAttribute("conDition", "rptSql=" + sql);
		request.setAttribute("rptModel", "saleCountNew.raq");
		request.setAttribute("printModel", "saleCountNew.raq");

		return mapping.findForward("pageResult");
  }
  
	public SalecountService getSalecountService() {
		return salecountService;
	}
	public void setSalecountService(SalecountService salecountService) {
		this.salecountService = salecountService;
	}
}


