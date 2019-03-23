package com.hoperun.drp.distributer.distributerendreq.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class DistributerEndReqFlowInterface implements FlowInterface {

	@Override
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,
			FlowService flowService) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean afterAuditing(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		String bussId = (String) request.getAttribute("id");
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state))
		{
			UserBean userBean  = ParamUtil.getUserBean(request);
			Map<String,String> params = new HashMap<String,String> ();
			params.put("CHANN_END_REQ_ID", bussId);	
			
			Map<String,String> entry = (Map<String, String>) flowService.load("distributerEndReq.loadById",bussId);
			String CHANN_ID = entry.get("CHANN_ID");			
			
			params.put("CHANN_ID", CHANN_ID);
        	params.put("STATE", BusinessConsts.JC_STATE_DISENABLE);//状态
        	params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称 
            params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
        	
            //停用渠道相关数据
        	// 系统用户表里 设置了分管所以渠道的标记为1时，删除 分管给该用户的渠道
        	flowService.update("XTYH.delChannChargByChangeChann", params);    	
        	//停用渠道的组织
        	flowService.update("chann.upZTXX", params);
        	flowService.update("chann.upJGXX", params);
        	flowService.update("chann.upBMXX", params);
        	flowService.update("distributerEndReq.upRYXX", params);
        	flowService.update("distributerEndReq.upXTYH", params);
        	flowService.update("distributerEndReq.upXTYHJS", params);        	        	
        	//停用渠道的终端
        	flowService.update("chann.upTerminal",params);        	
        	//停用渠道的分销商
        	flowService.update("distributerEndReq.upDISTRIBUTOR",params);        	
        	//停用渠道
			flowService.update("chann.updateStateById", params);
		}
		return true;
	}

	@Override
	public boolean beforeAffirm(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
