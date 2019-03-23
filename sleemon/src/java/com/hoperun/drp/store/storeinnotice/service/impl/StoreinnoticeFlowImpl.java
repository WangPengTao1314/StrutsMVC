package com.hoperun.drp.store.storeinnotice.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class StoreinnoticeFlowImpl implements FlowInterface{
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
        //业务单据ID
		String cpbltzdid = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		UserBean userBean =  ParamUtil.getUserBean(request);
		if("审核通过".equals(state))
		{
			LogicUtil.storeNoticeToIn(cpbltzdid,request);
//			HashMap deliverMap = new HashMap();
//			deliverMap.put("STOREIN_NOTICE_ID", cpbltzdid);
//			flowService.update("Storeinnotice.udpateDeliverStat", deliverMap);
		} else if ("否决".equals(state)) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("AUDIT_ID", userBean.getXTYHID());
			params.put("AUDIT_NAME", userBean.getXM());
			params.put("STOREIN_NOTICE_ID", cpbltzdid);
			flowService.update("Storeinnotice.updateById", params);
		}
		
		return true;
	}
}
