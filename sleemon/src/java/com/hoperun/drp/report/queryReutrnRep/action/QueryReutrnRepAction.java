package com.hoperun.drp.report.queryReutrnRep.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.util.ExcelUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.QXUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.queryReutrnRep.model.ReutrnRep;
import com.hoperun.drp.report.queryReutrnRep.service.QueryReutrnRepService;
import com.hoperun.sys.model.UserBean;

/**
 * 退货查询
 * @author guhongxiu
 *
 */
public class QueryReutrnRepAction extends BaseAction{
	
	private Logger logger = Logger.getLogger(QueryReutrnRepAction.class);
		
	private static String PVG_BWS="BS0050103";
    
	private QueryReutrnRepService queryReutrnRepService;
	
	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}

		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		
		return mapping.findForward("frames");

	}
	
		
	/**
	 * u9退货数据列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward listFromU9(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        
		//获取参数
		Map params = new HashMap();
    	params.put("RETURN_NO", ParamUtil.utf8Decoder(request, "RETURN_NO"));//退货单号
		
		Map<String,Object> page = new HashMap<String,Object>();
		
		//调用服务
		List rst = queryReutrnRepService.pageQueryU9(params);
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
		
		request.setAttribute("params", params);
		request.setAttribute("page", page);
		return mapping.findForward("list");	
	}
	
	
	
	
	public QueryReutrnRepService getQueryReutrnRepService() {
		return queryReutrnRepService;
	}

	public void setQueryReutrnRepService(QueryReutrnRepService queryReutrnRepService) {
		this.queryReutrnRepService = queryReutrnRepService;
	}
	//导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
    	params.put("RETURN_NO", ParamUtil.utf8Decoder(request, "RETURN_NO"));//退货单号
    	List rst = queryReutrnRepService.pageQueryU9(params);
        //excel数据上列显示的列明
        String tmpContentCn="退货单号,客户编号,客户名称,商品序列号,商品编号,商品名称,规格型号,入库数量,退货单价,退货金额,入库时间,入库处理人姓名,财务审核时间,财务审核人姓名";
        //
        String tmpContent="RETURN_NO,CUST_NO,CUST_NAME,PRD_SN,PRD_NO,PRD_NAME,PRD_SPEC,STORE_IN_NUM,RETURN_PRICE,RETURN_AMOUNT,STORE_IN_TIME,STORE_IN_USERNAME,FNC_AUDIT_TIME,FNC_AUDIT_USERNAME";
        try {
        	ExcelUtil.doExport(response, rst, "退货单跟踪", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
}
