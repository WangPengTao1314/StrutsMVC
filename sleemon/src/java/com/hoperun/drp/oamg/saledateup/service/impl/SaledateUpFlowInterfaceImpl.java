package com.hoperun.drp.oamg.saledateup.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class SaledateUpFlowInterfaceImpl implements FlowInterface{

	@Override
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,
			FlowService flowService) throws Exception {
		return true;
	}

	@Override
	public boolean afterAuditing(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		return true;
	}

	@Override
	public boolean beforeAffirm(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		return true;
	}

}
