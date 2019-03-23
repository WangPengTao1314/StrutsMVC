package com.hoperun.report.allocatecount.action;
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
import com.hoperun.report.allocatecount.service.AllocatecountService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——调拨表统计
 * @author gu_hongxiu
 *
 */
public class AllocatecountAction  extends BaseAction {
	private Logger logger = Logger.getLogger(AllocatecountAction.class);
	
	
	
	private AllocatecountService allocatecountService;
	
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
			params.put("ZTXXID", userBean.getLoginZTXXID());
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
	  	UserBean userBean = ParamUtil.getUserBean(request);
		String LEDGER_ID = userBean.getLoginZTXXID();
		
	  	String ALLO_DATE = ParamUtil.get(request, "ALLO_DATE");
		String ALLOC_OUT_STORE_NO = ParamUtil.get(request, "ALLOC_OUT_STORE_NO");
		String ALLOC_OUT_STORE_NAME = ParamUtil.get(request, "ALLOC_OUT_STORE_NAME");		
		String DEF_STORE_NO = ParamUtil.get(request, "DEF_STORE_NO");
		String DEF_STORE_NAME = ParamUtil.get(request, "DEF_STORE_NAME");		
		String PRD_NO = ParamUtil.get(request, "PRD_NO");
		String PRD_NAME = ParamUtil.get(request, "PRD_NAME");		
		
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where t.state = '审核通过' and t.del_flag = 0 ");
		if (!StringUtil.isEmpty(LEDGER_ID)) {
			condition.append(" and t.LEDGER_ID='" + LEDGER_ID + "'");
		}
		if (!StringUtil.isEmpty(ALLO_DATE)) {
			condition.append(" and to_char(t.CRE_TIME,'YYYY-MM-DD')='" + ALLO_DATE
					+ "'");
		}		
		if (!StringUtil.isEmpty(ALLOC_OUT_STORE_NO)) {
			condition.append(" and t.ALLOC_OUT_STORE_NO='" + ALLOC_OUT_STORE_NO + "'");
		}		
		if (!StringUtil.isEmpty(ALLOC_OUT_STORE_NAME)) {
			condition.append(" and t.ALLOC_OUT_STORE_NAME like '%" + ALLOC_OUT_STORE_NAME + "%'");
		}
		if (!StringUtil.isEmpty(DEF_STORE_NO)) {
			condition.append(" and n.DEF_STORE_NO='" + DEF_STORE_NO + "'");
		}
		if (!StringUtil.isEmpty(DEF_STORE_NAME)) {
			condition.append(" and n.def_store_name like '%" + DEF_STORE_NAME + "%'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and d.prd_no='" + PRD_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(" and d.prd_name like '%" + PRD_NAME + "%'");
		}

		String rptSql = allocatecountService.createSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "allocatecount.raq");
		request.setAttribute("printModel", "allocatecount.raq");

		return mapping.findForward("pageResult");
  }
  
	
	public AllocatecountService getAllocatecountService() {
		return allocatecountService;
	}

	public void setAllocatecountService(AllocatecountService allocatecountService) {
		this.allocatecountService = allocatecountService;
	}
}


