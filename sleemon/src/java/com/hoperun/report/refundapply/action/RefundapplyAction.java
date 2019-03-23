package com.hoperun.report.refundapply.action;
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
import com.hoperun.report.refundapply.service.RefundapplyService;
import com.hoperun.sys.model.UserBean;

/**
 * 报表——退款申请表
 * @author gu_hongxiu
 *
 */
public class RefundapplyAction  extends BaseAction {
	private Logger logger = Logger.getLogger(RefundapplyAction.class);
	
	
	
	private RefundapplyService refundapplyService;
	
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
		String ADVC_ORDER_NO = ParamUtil.get(request, "ADVC_ORDER_NO");
		String FROM_BILL_ID = ParamUtil.get(request, "FROM_BILL_ID");
		
		/********** 查询条件拼接 start **********/
		StringBuffer condition = new StringBuffer(" where t.del_flag = 0 and t.bill_type =  '终端退货' ");
		if (!StringUtil.isEmpty(ADVC_ORDER_NO)) {
			condition.append(" and t.advc_order_no='" + ADVC_ORDER_NO + "'");
		}

		String rptSql = refundapplyService.createSql(condition.toString());
		String paySql = "select p.pay_amont,p.pay_type from DRP_PAYMENT_DTL p where p.DEL_FLAG=0 and p.advc_order_id = '"+ FROM_BILL_ID +"'";
		/*String refundSql = " select d.pay_type,d.pay_amont from  DRP_STATEMENTS_PAYMENT_DTL d "+
		   " left join DRP_STATEMENTS u on d.statements_id = u.statements_id "+
		   " where  u.BILL_TYPE in ('其它退款','终端退货退款')      and   u.DEL_FLAG=0 " +
		   " and u.advc_order_no = '"+ ADVC_ORDER_NO +"'";*/
		
		request.setAttribute("conDition", "rptSql=" + rptSql + ";paySql="+paySql);
		request.setAttribute("rptModel", "refundapply.raq");
		request.setAttribute("printModel", "refundapply.raq");

		return mapping.findForward("pageResult");
  }
  
	
	public RefundapplyService getRefundapplyService() {
		return refundapplyService;
	}

	public void setRefundapplyService(RefundapplyService refundapplyService) {
		this.refundapplyService = refundapplyService;
	}
}


