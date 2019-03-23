package com.hoperun.drp.oamg.promoexpen.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class PromoexpenFlowInterface implements FlowInterface {

	/**
	 * 提交前事件，在此事件之前，流程没有做任何动作.
	 * @param request the request
	 * @param session the session
	 * @param flowService the flow service
	 * @return true, if before affirm
	 * @throws Exception the exception
	 */
	public boolean beforeAffirm(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
		return true;
	}

	/**
	 * 提交后事件，在此事件之前，流程修改了业务数据状态("提交")，状态变更时间(当前时间).
	 * @param request the request
	 * @param session the session
	 * @param flowService the flow service
	 * @return true, if after affirm
	 * @throws Exception the exception
	 */
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
		return true;
	}

	/**
	 * 审核完毕事件，在此事件之前，流程修改业务数据状态（"审核通过"/"否决"/"撤签"）.
	 * @param request the request
	 * @param session the session
	 * @param flowService the flow service
	 * @return true, if after auditing
	 * @throws Exception the exception
	 */
	@SuppressWarnings("unchecked")
	public boolean afterAuditing(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
		String bussId = (String) request.getAttribute("id");
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state))
		{
			try {
				CreditCtrlUtil.txUpdateFreeBudget(bussId);
			} catch (Exception e) {
				request.setAttribute("faultInfo",e.getMessage());
				throw new Exception();
			}
		} 
		return true;
	}
}
