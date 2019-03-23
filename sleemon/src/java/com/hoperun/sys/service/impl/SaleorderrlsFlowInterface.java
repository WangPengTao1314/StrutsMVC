package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;
/**
 * 销售订单审核 后的操作
 * @author zhang_zhongbin
 *
 */
public class SaleorderrlsFlowInterface implements FlowInterface{

	/**
	 * 提交前事件，在此事件之前，流程没有做任何动作.
	 * 
	 * @param request the request
	 * @param session the session
	 * @param flowService the flow service
	 * 
	 * @return true, if before affirm
	 * 
	 * @throws Exception the exception
	 */
	public boolean beforeAffirm(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
		return true;
	}

	/**
	 * 提交后事件，在此事件之前，流程修改了业务数据状态("提交")，状态变更时间(当前时间).
	 * 
	 * @param request the request
	 * @param session the session
	 * @param flowService the flow service
	 * 
	 * @return true, if after affirm
	 * 
	 * @throws Exception the exception
	 */
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
		return true;
	}

	/**
	 * 审核完毕事件，在此事件之前，流程修改业务数据状态（"审核通过"/"否决"/"撤签"）.
	 * 
	 * @param request the request
	 * @param session the session
	 * @param flowService the flow service
	 * 
	 * @return true, if after auditing
	 * 
	 * @throws Exception the exception
	 */
	public boolean afterAuditing(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
      
		UserBean userBean  = ParamUtil.getUserBean(request);
		//业务单据ID
		String id = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		
		if(BusinessConsts.PASS.equals(state)){
			Map<String,String> result = (Map<String, String>) flowService.load("Saleorderrls.loadById", id);
			Map<String,Object>params = new HashMap<String,Object>();
			String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
			params.put("SALE_ORDER_ID", id);
			params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
			params.put("BILL_NO", result.get("SALE_ORDER_NO"));//销售订单的NO
			String billType = result.get("BILL_TYPE");
			if(BusinessConsts.STANDARD.equals(billType)){
				params.put("BILL_TYPE", "标准销售订单");//订货订单的type
			}else{
				params.put("BILL_TYPE", "非标销售订单");
			}
			
			
			params.put("ACTION_NAME", BusinessConsts.SALE_BILL_ACTION_AUDIT);//订单员核单
			params.put("TRACE_URL", BusinessConsts.SALE_BILL_ACTION_TRACE_URL + id);
			params.put("DEAL_PSON_NAME", userBean.getXM());
			params.put("STATE", BusinessConsts.PASS);
			
			flowService.insert("Goodsorderhd.insertOrderTrack", params);
		}
		return true;
	}
}
