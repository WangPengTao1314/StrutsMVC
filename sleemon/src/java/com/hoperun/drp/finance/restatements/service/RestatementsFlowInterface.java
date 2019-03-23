/**
 * 项目名称：平台
 * 系统名：共通审批接口
 * 文件名：NewMasterSlaveFlowInterface.java
*/
package com.hoperun.drp.finance.restatements.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.model.BusinessConsts;
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
public class RestatementsFlowInterface implements FlowInterface {

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
		
		Map params=new HashMap();
		params.put("RETURN_STATEMENTS_ID", cpbltzdid);
		
		Map<String,String> statementsMap = (Map<String,String>)flowService.load("Restatements.loadById", cpbltzdid);
		
		if(statementsMap!=null){
			
			params.put("STATE", BusinessConsts.STATE_IS_PAY);
			flowService.update("Restatements.updateById", params);
			
			params = new HashMap();
			params.put("ADVC_ORDER_ID", statementsMap.get("ADVC_ORDER_ID"));
			params.put("ADVC_AMOUNT_FLAG", BusinessConsts.DEL_FLAG_DROP);
			flowService.update("Advcorder.updateById", params);
		}
		
		
		
		//业务单据状态
		//String state = (String) request.getAttribute("currentState");
		//if("审核通过".equals(state))
		//{
		//	Map params=new HashMap();
			
		//	flowService.update();
		//}
		return true;
	}
	


 }
