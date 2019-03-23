/**
 * 项目名称：平台
 * 系统名：共通审批接口
 * 文件名：NewMasterSlaveFlowInterface.java
*/
package com.hoperun.drp.store.allocate.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
public class AllocateFlowInterface implements FlowInterface {

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
		UserBean userBean =  ParamUtil.getUserBean(request);
        //业务单据ID
		String ALLOCATE_ID = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state))
		{
			try {
				//UserBean userBean =  ParamUtil.getUserBean(request);
				//更新审核人，审核时间
//				params.put("TEMP_CREDIT_REQ_ID", TEMP_CREDIT_REQ_ID);
//				params.put("AUDIT_ID", userBean.getRYXXID());
//				params.put("AUDIT_NAME", userBean.getXM());
//				params.put("AUDIT_TIME", "数据库时间");
//				params.put("UPD_TIME", "数据库时间");
//				flowService.update("temp_credit_req.updateAUDIT", params);
//				params.clear();
				Map<String,String> map=new HashMap<String,String>();
				List list=new ArrayList();
				LogicUtil.genStoreOutOrder(ALLOCATE_ID, "DRP_ALLOCATE", userBean, map,list);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return true;
	}
	


 }
