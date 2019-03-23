/**
 * 项目名称：平台
 * 系统名：共通审批接口
 * 文件名：NewMasterSlaveFlowInterface.java
*/

package com.hoperun.drp.store.inventory.service.impl;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.DateUtil;
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
public class InventoryFlowInterface implements FlowInterface {
	private Logger logger = Logger.getLogger(InventoryFlowInterface.class);
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
	@SuppressWarnings("unchecked")
	public boolean afterAuditing(HttpServletRequest request, HttpSession session,FlowService flowService) throws Exception {
		String invenToryId = (String) request.getAttribute("id");
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state)){
			UserBean userBean =  ParamUtil.getUserBean(request);
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
			if(isMonthAcc){
				throw new Exception("处于月结阶段,不能做出库!");
			}
			LogicUtil.doStoreAcct(BusinessConsts.INVEN_CONF_ID,invenToryId);
			LogicUtil.doJournalAcct(BusinessConsts.INVEN_CONF_ID,invenToryId);
			LogicUtil.genProfitLossOrder(invenToryId);
			Map<String,String> map=new HashMap<String, String>();
			map.put("PRD_INV_ID", invenToryId);
			map.put("TECH_NO_EDIT_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
			flowService.update("Inventory.txUpSpclEditFlag", map);
			return true;
		}	
		if("否决".equals(state)||"撤销".equals(state)){
			Map<String,String> map=new HashMap<String, String>();
			map.put("PRD_INV_ID", invenToryId);
			map.put("STATE", "结束盘点");
			flowService.update("Inventory.updateById", map);
			return true;
		}
		return false;
	}
	


 }

