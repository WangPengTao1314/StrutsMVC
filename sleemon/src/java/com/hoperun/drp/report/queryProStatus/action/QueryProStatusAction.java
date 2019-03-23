package com.hoperun.drp.report.queryProStatus.action;

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
import com.hoperun.drp.report.queryProStatus.service.QueryProStatusService;
import com.hoperun.sys.model.UserBean;

/**
 * 生产情况查询
 * @author guhongxiu
 *
 */
public class QueryProStatusAction extends BaseAction{
	
	private Logger logger = Logger.getLogger(QueryProStatusAction.class);
	
	private static String PVG_BWS="BS0050102";
    
	private QueryProStatusService queryProStatusService;
	
	
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
	@SuppressWarnings("unchecked")
	public ActionForward listFromU9(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
//		UserBean userBean =  ParamUtil.getUserBean(request);
//        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS))){
//    		throw new ServiceException("对不起，您没有权限 !");
//    	}
        
		//获取参数
		Map params = new HashMap();
    	params.put("DeliverPlanNo", ParamUtil.utf8Decoder(request, "DeliverPlanNo"));//发运单号
		
		Map<String,Object> page = new HashMap<String,Object>();
		
		//调用服务
		List rst = queryProStatusService.pageQueryU9(params);		
		page.put("result", rst);
		
		//检查服务调用结果
		if(rst != null && rst.size() == 1){
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
	
	
	
	
	public QueryProStatusService getQueryProStatusService() {
		return queryProStatusService;
	}

	public void setQueryProStatusService(QueryProStatusService queryProStatusService) {
		this.queryProStatusService = queryProStatusService;
	}
	//导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		Map params = new HashMap();
    	params.put("DeliverPlanNo", ParamUtil.utf8Decoder(request, "DELIVERPLANNO"));//发运单号
        List rst = queryProStatusService.pageQueryU9(params);
        //excel数据上列显示的列明
        String tmpContentCn="发运单号,销售订单编号,客户编号,客户名称,商品编号,商品名称,规格型号,排车数量,抵库数量,已入库数量,生产中,组织";
        //
        String tmpContent="DeliverOrderNo,SaleOrderNo,CustNo,CustName,PrdNo,PrdName,PrdSpec,PlanNum,StoreNum,StroeInNum,WorkNum,LEDGER_NAME";
        try {
        	ExcelUtil.doExport(response, rst, "生产情况", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
}
