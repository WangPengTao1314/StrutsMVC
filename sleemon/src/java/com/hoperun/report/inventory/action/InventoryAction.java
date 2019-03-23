package com.hoperun.report.inventory.action;
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
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.report.inventory.service.InventoryService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——门店库存
 * @author gu_hongxiu
 *
 */
public class InventoryAction  extends BaseAction {
	private Logger logger = Logger.getLogger(InventoryAction.class);
	
	
	
	private InventoryService inventoryService;
	
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
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
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
	  String LEDGER_ID = userBean.getLoginZTXXID();
	  	
	  String PRD_NO = ParamUtil.get(request, "PRD_NO");
	  String PRD_NAME = ParamUtil.get(request, "PRD_NAME");

		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where 1=1 ");
		if (!StringUtil.isEmpty(LEDGER_ID)) {
			condition.append(" and a.LEDGER_ID='" + LEDGER_ID + "'");
		}
		if (!StringUtil.isEmpty(PRD_NO)) {
			condition.append(" and p.PRD_NO='" + PRD_NO + "'");
		}
		if (!StringUtil.isEmpty(PRD_NAME)) {
			condition.append(" and p.PRD_NAME like '%" + PRD_NAME + "%'");
		}
				
		String rptSql = inventoryService.createSql(condition.toString());

		request.setAttribute("conDition", "rptSql=" + rptSql);
		request.setAttribute("rptModel", "inventory.raq");
		request.setAttribute("printModel", "inventory.raq");
		
		return mapping.findForward("pageResult");
  }
  
	
	public InventoryService getInventoryService() {
		return inventoryService;
	}

	public void setInventoryService(InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
}


