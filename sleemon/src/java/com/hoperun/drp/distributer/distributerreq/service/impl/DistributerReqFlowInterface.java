package com.hoperun.drp.distributer.distributerreq.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.base.distributor.model.DistributorModel;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModelChld;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class DistributerReqFlowInterface implements FlowInterface {

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
			Map<String,String> params = new HashMap<String,String> ();
			params.put("DISTRIBUTOR_REQ_ID", bussId);
			
        	params.put("DISTRIBUTOR_ID", StringUtil.uuid32len());
        	
        	List<DistributorModel> list=new ArrayList<DistributorModel>();
        	list =  flowService.findList("distributor.queryMaxNoT","");
            String DISTRIBUTOR_NO = "";
            if(list != null){
	            for(int i=0;i<list.size();i++){
	            	DistributorModel model = list.get(i);
	            	DISTRIBUTOR_NO = model.getDISTRIBUTOR_NO().toString();
	            }
            } 
        	if("".equals(DISTRIBUTOR_NO)){
        		DISTRIBUTOR_NO = "1300001";
        	}else{
        		DISTRIBUTOR_NO = String.valueOf(Integer.parseInt(DISTRIBUTOR_NO)+1);
        	}
            //}
        	params.put("DISTRIBUTOR_NO",DISTRIBUTOR_NO);
        	params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//状态
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记 
			
			flowService.update("distributerReq.insertDistributer", params);
		}
		return true;
	}

	@Override
	public boolean beforeAffirm(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		return true;
	}

}
