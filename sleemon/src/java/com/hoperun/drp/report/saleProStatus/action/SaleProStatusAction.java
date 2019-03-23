package com.hoperun.drp.report.saleProStatus.action;

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
import com.hoperun.drp.report.saleProStatus.service.SaleProStatusService;
import com.hoperun.sys.model.UserBean;

/**
 * 销售订单生产状态查询
 * @author guhongxiu
 *
 */
public class SaleProStatusAction extends BaseAction{
	
	private Logger logger = Logger.getLogger(SaleProStatusAction.class);
	
	private static String PVG_BWS="BS0050108";
	//渠道查询权限
	private static String PVG_BWS_ADVC_STORE="FX0110605";
    
	private SaleProStatusService saleProStatusService;
	
	
	public ActionForward toFrame(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_ADVC_STORE))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}

		// 设置跳转时需要的一些公用属性
		ParamUtil.setCommAttributeEx(request);
		request.setAttribute("paramUrl", ParamUtil.utf8Decoder(request,
				"paramUrl"));
		
		//其它地方调用报表 参数传递  如：标准订单和非标订单模块
		String SALE_ORDER_NO = ParamUtil.utf8Decoder(request,"SALE_ORDER_NO");
		Map<String,String> params = new HashMap<String,String>();
		params.put("SALE_ORDER_NO", SALE_ORDER_NO);
		request.setAttribute("IS_DRP_LEDGER", userBean.getIS_DRP_LEDGER());
		request.setAttribute("ZTXXID", userBean.getLoginZTXXID());
		request.setAttribute("params", params);
		return mapping.findForward("frames");

	}
	
		
	/**
	 * u9销售订单生产状态列表
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward listFromU9(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean =  ParamUtil.getUserBean(request);
        if(Consts.FUN_CHEK_PVG&&(!QXUtil.checkMKQX(userBean, PVG_BWS)&&!QXUtil.checkMKQX(userBean, PVG_BWS_ADVC_STORE))){
    		throw new ServiceException("对不起，您没有权限 !");
    	}
        
		//获取参数
		Map<String,String> params = new HashMap<String,String>();
    	params.put("SALE_ORDER_NO", ParamUtil.get(request,"SALE_ORDER_NO"));//销售订单号
    	params.put("DEL_FLAG", "0");
		Map<String,Object> page = new HashMap<String,Object>();
		//渠道分管 
		String CHANNS_CHARG = userBean.getCHANNS_CHARG();
		params.put("CHANNS_CHARG", CHANNS_CHARG);
		//调用服务
		List rst = saleProStatusService.pageQueryU9New(params);		
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
	
	
	
	
	public SaleProStatusService getSaleProStatusService() {
		return saleProStatusService;
	}

	public void setSaleProStatusService(SaleProStatusService saleProStatusService) {
		this.saleProStatusService = saleProStatusService;
	}
	//导出
	@SuppressWarnings("unchecked")
	public void ExcelOutput(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//获取参数
		Map params = new HashMap();
    	params.put("SALE_ORDER_NO", ParamUtil.utf8Decoder(request, "SALE_ORDER_NO"));//销售订单号
    	//调用服务
		List rst = saleProStatusService.pageQueryU9(params);		
        //excel数据上列显示的列明
        String tmpContentCn="销售订单编号,发运单号,客户编号,客户名称,商品编号,商品名称,规格型号,订货数量,抵库数量,已入库数量,生产中";
        //
        String tmpContent="SaleOrderNo,DeliverOrderNo,CustNo,CustName,PrdNo,PrdName,PrdSpec,OrderNum,StoreNum,StroeInNum,WorkNum";
        try {
        	ExcelUtil.doExport(response, rst, "销售订单生产状态", tmpContent, tmpContentCn);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	
}
