package com.hoperun.drp.main.carcalculate.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.area.model.AreaModel;
import com.hoperun.commons.action.BaseAction;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.Result;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.carcalculate.service.CarcalculateService;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelChld;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.sys.model.UserBean;

/**
 * 整车计算
 * @author zhang_zhongbin
 *
 */
public class CarcalculateAction extends BaseAction{
    
	private CarcalculateService carcalculateService;
	
	public ActionForward listGoodsOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		UserBean userBean = (UserBean) request.getSession(false).getAttribute("UserBean");
		String CHANN_ID = userBean.getCHANN_ID();
		 
		Map<String,Object> params = new HashMap<String,Object>();
		String CHANN_NAME = ParamUtil.get(request,"CHANN_NAME");
		String search = ParamUtil.get(request,"search");
		String notSend = ParamUtil.utf8Decoder(request,"notSend");
		String carType = ParamUtil.get(request,"carType");
		String page_plag = ParamUtil.get(request,"page_plag");
		String planOrder = null;
		if(!StringUtil.isEmpty(search)){
			planOrder = BusinessConsts.PLAN_ORDER ;
			carType = BusinessConsts.CAR_TYPE;
			CHANN_NAME = userBean.getCHANN_NAME();
		}else{
			planOrder = ParamUtil.get(request,"planOrder");
		}
		
		if(!StringUtil.isEmpty(planOrder) && BusinessConsts.PLAN_ORDER.equals(planOrder)){
			params.put("STATES", "'"+BusinessConsts.STATE_MAKE+"','"+BusinessConsts.REVOKE+"'");
		}else if(!StringUtil.isEmpty(notSend) && BusinessConsts.NOT_SEND.equals(notSend)){
			params.put("STATES", "'未处理','总部退回','区域服务中心退回','制作'");
		}
		
		if(!StringUtil.isEmpty(planOrder) && !StringUtil.isEmpty(notSend)){
			params.put("STATES", "'"+BusinessConsts.STATE_MAKE+"','"+BusinessConsts.REVOKE+"','"+BusinessConsts.COMMIT+"','"+BusinessConsts.PASS+"'");
		}
		 
		if(StringUtil.isEmpty(planOrder) && StringUtil.isEmpty(notSend)){
			params.put("STATES", "'"+BusinessConsts.STATE_MAKE+"','"+BusinessConsts.REVOKE+"','"+BusinessConsts.COMMIT+"','"+BusinessConsts.PASS+"'");
		}
		ParamUtil.putStr2Map(request, "CHANN_NAME", params);
		ParamUtil.putStr2Map(request, "GOODS_ORDER_NO", params);
		
		params.put("ORDER_CHANN_ID", CHANN_ID);
		params.put("CHANN_NAME", CHANN_NAME);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("planOrder", planOrder);
		params.put("SEND_TYPE", notSend);
		params.put("carType", carType);
		
		
		int pageNo = ParamUtil.getInt(request, "pageNo", 1);
		ParamUtil.putStr2Map(request, "pageSize", params);
		IListPage page = carcalculateService.pageQuery(params, pageNo);
		
		//查询 车的容积
		Map<String,Object> map = this.carcalculateService.queryCarVolume(carType);
		if(null != map){
			params.put("VOLUME", map.get("VOLUME"));
		}
		request.setAttribute("page", page);
		request.setAttribute("params", params);
		if(!StringUtil.isEmpty(page_plag)){
			return mapping.findForward("notsend_list");
		}else{
			return mapping.findForward("list");
		}
		
	}
	
	/**
	 * 明细保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void childEdit(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		String childData = ParamUtil.get(request, "childData");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			if (StringUtils.isNotEmpty(childData)){
				GoodsorderhdModelChld model = new Gson().fromJson(childData,
						new TypeToken<GoodsorderhdModelChld>() {
				}.getType());
				String GOODS_ORDER_DTL_ID = this.carcalculateService.childEdit(model);
				Map<String,String> param = new HashMap<String,String>();
				param.put("GOODS_ORDER_DTL_ID", GOODS_ORDER_DTL_ID);
				jsonResult = new Gson().toJson(new Result(true, param, "保存成功"));
				
			}
		}catch(Exception e){
			e.printStackTrace();
			jsonResult = jsonResult(false,"保存失败");
		}
		if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	    }
		
	}
	/**
	 * 明细删除
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void childDelete(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		String GOODS_ORDER_DTL_ID = ParamUtil.get(request, "GOODS_ORDER_DTL_ID");
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try {
			if (StringUtils.isNotEmpty(GOODS_ORDER_DTL_ID)){
				this.carcalculateService.childDelete(GOODS_ORDER_DTL_ID);
				jsonResult = jsonResult(true,"删除成功");
				
			}
		}catch(Exception e){
			jsonResult = jsonResult(false,"删除失败");
		}
		if (null != writer) {
	            writer.write(jsonResult);
	            writer.close();
	    }
		
	}

	/**
	 * 获取车型的 容积
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 */
	public void getVolum(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response){
		
		PrintWriter writer = getWriter(response);
		String jsonResult = jsonResult();
		try{
			String carType = ParamUtil.get(request, "carType");
			//查询 车的容积
			Map<String,Object> map = this.carcalculateService.queryCarVolume(carType);
		    jsonResult = new Gson().toJson(new Result(true, map, ""));
		}catch(Exception e){
		 
		}
		
		if (null != writer) {
            writer.write(jsonResult);
            writer.close();
        }
		
	}
	
	
	public CarcalculateService getCarcalculateService() {
		return carcalculateService;
	}

	public void setCarcalculateService(CarcalculateService carcalculateService) {
		this.carcalculateService = carcalculateService;
	}
	
	
	
}
