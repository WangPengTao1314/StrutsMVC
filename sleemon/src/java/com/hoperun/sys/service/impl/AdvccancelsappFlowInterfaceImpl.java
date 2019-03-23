package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class AdvccancelsappFlowInterfaceImpl implements FlowInterface{

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
	public boolean beforeAffirm(HttpServletRequest request, 
			HttpSession session,FlowService flowService) throws Exception{
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
	public boolean afterAffirm(HttpServletRequest request, 
			HttpSession session,FlowService flowService) throws Exception{
		
		UserBean userBean  = ParamUtil.getUserBean(request);
		//业务单据ID
		String id = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		if(null == state){
			state = "提交";
		}
		if(BusinessConsts.COMMIT.equals(state)){
		 
		}
		
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
	public boolean afterAuditing(HttpServletRequest request, 
			HttpSession session,FlowService flowService) throws Exception{
		//业务单据ID
		String ADVC_SEND_CANCEL_ID = (String) request.getAttribute("id");
		UserBean userBean  = ParamUtil.getUserBean(request);
		//业务单据状态
		String state = (String) request.getAttribute("currentState");

		if(BusinessConsts.PASS.equals(state)){
			Map<String, String> params = new HashMap<String, String>();
	        Map<String,String> entry = (Map<String, String>) flowService.load("Advccancelsapp.loadById", ADVC_SEND_CANCEL_ID);
	        params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
//			params.put("STATE", BusinessConsts.PASS);//审核通过
			flowService.update("Advccancelsapp.updateById", params);
			
			//更新 预订单发货申请明细
			params.put("CANCEL_FLAG", BusinessConsts.INTEGER_1);//1
			flowService.update("Advccancelsapp.updateAdvcReqChldById",params);
			
			//如果出库单状态为已处理,要恢复库存
			params.clear();
			String ADVC_SEND_REQ_ID = entry.get("ADVC_SEND_REQ_ID");
			params.put("ADVC_SEND_REQ_ID",ADVC_SEND_REQ_ID);
			Map<String,String> storeMap = (Map<String,String>)flowService.load("Advccancelsapp.queryStoreOut", params);
			if(storeMap!=null){
				String stats = storeMap.get("STATE");
				if("已处理".equals(stats)){
					throw new ServiceException("对不起，货品已做出库处理，不能取消 !");
//					LogicUtil.doStoreAcct(BusinessConsts.ADVC_ORDER_CANCEL_CONF_ID,ADVC_SEND_CANCEL_ID);
//					LogicUtil.doJournalAcct(BusinessConsts.ADVC_ORDER_CANCEL_CONF_ID,ADVC_SEND_CANCEL_ID);
				}
			}
			//更新 状态=‘未提交’出库单 
			params.clear();
			params.put("ADVC_SEND_REQ_ID", entry.get("ADVC_SEND_REQ_ID"));
			params.put("STATE", "已取消");//已取消
			flowService.update("Advccancelsapp.updateStoreOutById",params);
			
			//把取消数量更新到发货申请的已取消数量
			
			
			//如果取消数量=发货数量，把预订单发货申请状态更新为已取消
			params.clear();
			params.put("ADVC_SEND_REQ_ID", entry.get("ADVC_SEND_REQ_ID"));
			int nommerNum = flowService.queryForInt("Advccancelsapp.queryIntFromReqDtl",params);
			params.put("CANCEL_FLAG", BusinessConsts.INTEGER_1);//1
			int cancelNum = flowService.queryForInt("Advccancelsapp.queryIntFromReqDtl",params);
			if(cancelNum >0 && nommerNum == cancelNum){
				params.put("STATE", "已取消");
				flowService.update("Advccancelsapp.updateAdvcSendReq",params);
			}	
			LogicUtil.doStoreFreeze(BusinessConsts.ADVC_ORDER_CANCEL_CONF_ID, ADVC_SEND_REQ_ID);
		}
		
		return true;
	}
	

	 
}
