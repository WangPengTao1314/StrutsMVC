package com.hoperun.report.store.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.store.service.StoreRepertoryService;
import com.hoperun.sys.model.UserBean;

public class StoreRepertoryAction extends BaseAction{
	
	private StoreRepertoryService storeRepertoryService;
	
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
//		if ("true".equals(jumpflag)) {
//			UserBean aUserBean = new UserBean();
//			aUserBean.setXTYHID(USERID);
//			aUserBean.setLoginZTXXID(ZTXXID);
//			session.setAttribute("UserBean", aUserBean);
//		}
		// 设置跳转时需要的一些公用属性
		String rptConFile = request.getParameter("rptConFile");
		request.setAttribute("rptConFile", rptConFile);
		String sjcd = request.getParameter("sjcd");
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
		String rptConFile = request.getParameter("rptConFile");
		try{
			UserBean userBean = ParamUtil.getUserBean(request);
			Map<String, String> params = new HashMap<String, String>();
			params.put("XTYH_ID", userBean.getXTYHID());
			request.setAttribute("params", params);
	
			// 设置跳转时需要的一些公用属性
			
			request.setAttribute("rptConFile", rptConFile);
			String sjcd = request.getParameter("sjcd");
			request.setAttribute("sjcd", sjcd);
			String backPath = request.getParameter("backPath");
			request.setAttribute("backPath", backPath);
			String actionPath = request.getParameter("actionPath");
			request.setAttribute("actionPath", actionPath);
			request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward(rptConFile);
	}

	/**
	 * 查询结果报表 Description :
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward toReportPageResult(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String STORE_ID = ParamUtil.get(request, "STORE_ID");
		String CHANN_NO = ParamUtil.get(request, "CHANN_NO");
		String CHANN_NAME = ParamUtil.get(request, "CHANN_NAME");
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");
		String ORDER_DATE_BEG = ParamUtil.get(request, "ORDER_DATE_BEG");
		String ORDER_DATE_END = ParamUtil.get(request, "ORDER_DATE_END");
		String PRD_TYPE_NAME = ParamUtil.get(request, "PRD_TYPE_NAME");
		
		String KSRQ = ParamUtil.get(request, "KSRQ");
		String JZRQ = ParamUtil.get(request, "JZRQ");
		String STATE = ParamUtil.get(request, "STATE");
		
		UserBean userBean = ParamUtil.getUserBean(request);

		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where s.DEL_FLAG=0 ");
 
		condition.append(" and s.LEDGER_ID='" + userBean.getLoginZTXXID() + "'");
		condition.append(" and s.TERM_STROE_FLAG=0 ");
		if (!StringUtil.isEmpty(STORE_ID)) {
			condition.append(" and a.STORE_ID = '" + STORE_ID + "'");
		}
		if (!StringUtil.isEmpty(CHANN_NO)) {
			condition.append(" and a.ORDER_CHANN_NO='" + CHANN_NO + "'");
		}
		if (!StringUtil.isEmpty(CHANN_NAME)) {
			condition.append(" and a.ORDER_CHANN_NAME like '%" + CHANN_NAME + "%'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and p.PRD_NO='" + PRD_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(" and p.PRD_NAME like '%" + PRD_NAME + "%'");
		}
		if (!StringUtil.isEmpty(KSRQ)) {
			condition.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')>='" + KSRQ
					+ "'");
		}
		if (!StringUtil.isEmpty(JZRQ)) {
			condition.append(" and to_char(a.CRE_TIME,'YYYY-MM-DD')<='" + JZRQ
					+ "'");
		}
		
		if (!StringUtil.isEmpty(ORDER_DATE_BEG)) {
			condition.append(" and to_char(a.ORDER_DATE,'YYYY-MM-DD')>='" + ORDER_DATE_BEG
					+ "'");
		}
		if (!StringUtil.isEmpty(ORDER_DATE_END)) {
			condition.append(" and to_char(a.ORDER_DATE,'YYYY-MM-DD')<='" + ORDER_DATE_END
					+ "'");
		}
		
		if (!StringUtil.isEmpty(PRD_TYPE_NAME)) {
			condition.append(" and f.PRD_NAME like '%" + PRD_TYPE_NAME + "%'");
		}
		
		if (!StringUtil.isEmpty(STATE)) {
			condition.append(" and a.STATE='" + STATE + "'");
		}


		String rptSql = storeRepertoryService.createSql(condition.toString(),"");

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "StoreRepertory.raq");
		request.setAttribute("printModel", "StoreRepertory.raq");

		return mapping.findForward("pageResult");
	}

	public StoreRepertoryService getStoreRepertoryService() {
		return storeRepertoryService;
	}

	public void setStoreRepertoryService(StoreRepertoryService storeRepertoryService) {
		this.storeRepertoryService = storeRepertoryService;
	}
	
	
	

	
	

}
