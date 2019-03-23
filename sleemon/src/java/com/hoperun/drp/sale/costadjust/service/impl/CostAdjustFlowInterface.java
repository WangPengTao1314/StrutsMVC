/**
 * 项目名称：平台
 * 系统名：共通审批接口
 * 文件名：NewMasterSlaveFlowInterface.java
*/

package com.hoperun.drp.sale.costadjust.service.impl;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

// TODO: Auto-generated Javadoc
/**
 * The Class NewMasterSlaveFlowInterface.
 * 
 * @module 共通管理
 * @fuc 共通审批接口
 * @version 1.1
 * @author zhuxw
 */
public class CostAdjustFlowInterface implements FlowInterface {
	private Logger logger = Logger.getLogger(CostAdjustFlowInterface.class);
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
		String COST_ADJUST_ID = (String) request.getAttribute("id");
		UserBean userBean =  ParamUtil.getUserBean(request);
		Map<String,Object> map=(Map<String, Object>) flowService.load("CostAdjust.loadById", COST_ADJUST_ID);
		String data=map.get("YEAR")+"-"+map.get("MONTH");
		boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),data);
		if(!isMonthAcc){
			throw new ServiceException(map.get("YEAR")+"年"+map.get("MONTH")+"月没有月结，不能操作");
		}
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state)){
			CreditCtrlUtil.trUpdateCostAdJust(COST_ADJUST_ID);
//			LogicUtil.doJournalAcct(BusinessConsts.COSTAD_CONF_ID,COST_ADJUST_ID);
		}
		
		return true;
	}
	


 }

