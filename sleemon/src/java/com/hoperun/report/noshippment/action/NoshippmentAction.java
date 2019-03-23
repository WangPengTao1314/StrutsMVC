package com.hoperun.report.noshippment.action;
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
import com.hoperun.report.noshippment.service.NoshippmentService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——未出货统计表
 * @author gu_hongxiu
 *
 */
public class NoshippmentAction  extends BaseAction {
	private Logger logger = Logger.getLogger(NoshippmentAction.class);
	
	
	
	private NoshippmentService noshippmentService;
	
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
		String AREA_NO = ParamUtil.get(request, "AREA_NO");
		String AREA_NAME = ParamUtil.get(request, "AREA_NAME");
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		String STATE = ParamUtil.get(request, "STATE");
		String PAR_PRD_NAME = ParamUtil.get(request, "PAR_PRD_NAME");

		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where 1=1 ");
		if (!StringUtil.isEmpty(AREA_NO)) {
			condition.append(" and a.AREA_NO='" + AREA_NO + "'");
		}
		if (!StringUtil.isEmpty(AREA_NAME)) {
			condition.append(" and a.AREA_NAME like '%" + AREA_NAME + "%'");
		}
		if (!StringUtil.isEmpty(CHANN_NO)) {
			condition.append(" and a.ORDER_CHANN_NO='" + CHANN_NO + "'");
		}
		if (!StringUtil.isEmpty(CHANN_NAME)) {
			condition.append(" and a.ORDER_CHANN_NAME like '%" + CHANN_NAME + "%'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and b.PRD_NO='" + PRD_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(" and b.PRD_NAME like '%" + PRD_NAME + "%'");
		}
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and to_char(a.ORDER_DATE,'YYYY-MM-DD')>='" + KSRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and to_char(a.ORDER_DATE,'YYYY-MM-DD')<='" + JZRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(STATE)) {
			condition.append(" and a.STATE='" + STATE + "'");
		}

		if (!StringUtil.isEmpty(PAR_PRD_NAME)) {
			condition.append(" and f.PRD_NAME like '%" + PAR_PRD_NAME
					+ "%'");
		}

		String rptSql = noshippmentService.createSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "noshippment.raq");
		request.setAttribute("printModel", "noshippment.raq");

		return mapping.findForward("pageResult");
  }
  
	
	public NoshippmentService getNoshippmentService() {
		return noshippmentService;
	}

	public void setNoshippmentService(NoshippmentService noshippmentService) {
		this.noshippmentService = noshippmentService;
	}
}


