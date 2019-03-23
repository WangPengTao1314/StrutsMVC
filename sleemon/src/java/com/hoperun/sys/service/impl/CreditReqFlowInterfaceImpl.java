package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;
/**
 * 信用变更申请 审核 事件
 * @author zhang_zhongbin
 *
 */
public class CreditReqFlowInterfaceImpl implements FlowInterface{

	public boolean afterAffirm(HttpServletRequest request, HttpSession session,
			FlowService flowService) throws Exception {
		return true;
	}

	/**
	 * 2.	审核通过后，需要更新：渠道信息(BASE_CHANN) 表的
			信用总额(CREDIT_TOTAL)= 变更后信用额度
			信用修改人(CREDIT_CRE_NAME)=userBean.getYHM()
			信用修改时间(CREDIT_CRE_TIME)=当前数据库时间;

	 */
	public boolean afterAuditing(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		UserBean userBean  = ParamUtil.getUserBean(request);
		   //业务单据ID
		String id = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state)){
    		String CREDIT_REQ_ID=id;
    		Map <String, String> result = (Map<String, String>) flowService.load("CreditReq.loadById", CREDIT_REQ_ID);
    		Object obj=result.get("NEW_CREDIT_TOTAL");
    		String big = obj.toString();
    		Map<String,Object> paramMap = new HashMap<String,Object>();
    		paramMap.put("INI_CREDIT", big);
    		paramMap.put("CREDIT_CRE_NAME", userBean.getXM());
    		paramMap.put("CHANN_ID", result.get("CHANN_ID"));
    		flowService.update("CreditReq.insertChangeCredit", paramMap);  //先记流水账再更新BASE_CHANN 的信用
    		flowService.update("CreditReq.updateAuditAfter", paramMap);
    		
    	}
		return true;
	}

	@Override
	public boolean beforeAffirm(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		return true;
	}

}
