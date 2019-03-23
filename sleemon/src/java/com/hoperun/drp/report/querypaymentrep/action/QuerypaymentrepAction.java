package com.hoperun.drp.report.querypaymentrep.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.querypaymentrep.service.QuerypaymentrepService;
import com.hoperun.sys.model.UserBean;

/**
 * 应收余额查询
 * @author zhu_changxia
 *
 */

public class QuerypaymentrepAction extends BaseAction {

	private  QuerypaymentrepService queryPayMentRepService;
	
	/**
	 * * to 框架页面
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * @return the action forward
	 */
	public ActionForward toFrames(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = ParamUtil.getUserBean(request);
		request.setAttribute("CHANNS_CHARG",userBean.getCHANNS_CHARG());
		return mapping.findForward("frames");
	}
	
	/**
	 * * query List data
	 * @param mapping the mapping
	 * @param form the form
	 * @param request the request
	 * @param response the response
	 * 
	 * @return the action forward
	 */
	public ActionForward toList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//获取参数
		Map<String,String> params = new HashMap<String,String> ();
		String CustNo = ParamUtil.utf8Decoder(request, "CustNo");
		String OrgCode = ParamUtil.utf8Decoder(request, "OrgCode");
    	params.put("CustNo", CustNo);//渠道编号
    	params.put("OrgCode",OrgCode);//帐套编号
		
    	request.setAttribute("CustNo",CustNo);
    	request.setAttribute("OrgCode", OrgCode);
    	
    	Map<String,Object> page = new HashMap<String,Object>();
    	
    	//调用服务
		List rst = queryPayMentRepService.queryPayMentRep(params);
		page.put("result", rst);
		
		//检查服务调用结果
		if(rst!=null && rst.size() == 1){
			Map temp = (Map) rst.get(0);
			String status =  (String) temp.get("Status");
			String showMsg = (String) temp.get("Desc");
			
			if(!StringUtil.isEmpty(status) && "false".equalsIgnoreCase(status)){
				page.put("errorMsg", showMsg);
			}
		}
		String flag    = ParamUtil.get(request, "flag");
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		request.setAttribute("flag", flag);
		return mapping.findForward("list");
	}

	public ActionForward toListT(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("list");
	}
	public QuerypaymentrepService getQueryPayMentRepService() {
		return queryPayMentRepService;
	}

	public void setQueryPayMentRepService(
			QuerypaymentrepService queryPayMentRepService) {
		this.queryPayMentRepService = queryPayMentRepService;
	}
	//导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		Map params = new HashMap();
    	params.put("CustNo", ParamUtil.utf8Decoder(request, "CustNo"));//渠道编号
    	params.put("OrgCode",ParamUtil.utf8Decoder(request, "OrgCode"));//帐套编号
    	//调用服务
		List rst = queryPayMentRepService.queryPayMentRep(params);
        //excel数据上列显示的列明
        String tmpContentCn="客户编号,客户名称,应收余额,账户余额 ";
        //
        String tmpContent="CustNo,CustName,PayableAmount,AcctAmount";
        try {
        	ExcelUtil.doExport(response, rst, "应收余额", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
