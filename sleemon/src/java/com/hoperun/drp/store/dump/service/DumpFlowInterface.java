package com.hoperun.drp.store.dump.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class DumpFlowInterface  implements FlowInterface{
	private Logger logger = Logger.getLogger(DumpFlowInterface.class);
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
		String dumpId = (String) request.getAttribute("id");
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state)){
			UserBean userBean =  ParamUtil.getUserBean(request);
			boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),DateUtil.newDataTime());
			if(isMonthAcc){
				throw new Exception("处于月结阶段,不能做出库!");
			}
			Map<String,String> map=(Map<String, String>) flowService.load("Dump.getDumpStoreId", dumpId);
			if(null==map){
				throw new Exception("未找到转储库房!");
			}
			boolean storeFreezeFlag=LogicUtil.checkFreezeStore(map.get("DUMP_OUT_STORE_ID"));
			if(!storeFreezeFlag){
				throw new Exception("当前转出库房已被冻结,不能做转储!");
			}
			storeFreezeFlag=LogicUtil.checkFreezeStore(map.get("DUMP_IN_STORE_ID"));
			if(!storeFreezeFlag){
				throw new Exception("当前转入库房已被冻结,不能做转储!");
			}
			String checkStoreAcctJson = LogicUtil.doChkCanUseStoreNum(BusinessConsts.DUMP_OUT_CONF_ID, dumpId, BusinessConsts.STORE_DESC);
	    	MsgInfo model = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
	        }.getType());
	    	if(model.isFLAG()){
	    		LogicUtil.doStoreAcct(BusinessConsts.DUMP_OUT_CONF_ID,dumpId);
				LogicUtil.doJournalAcct(BusinessConsts.DUMP_OUT_CONF_ID,dumpId);
				LogicUtil.doStoreAcct(BusinessConsts.DUMP_IN_CONF_ID,dumpId);
				LogicUtil.doJournalAcct(BusinessConsts.DUMP_IN_CONF_ID,dumpId);
				return true;
	    	}else{
				request.setAttribute("faultInfo", model.getMESS());
				throw new Exception(model.getMESS());
	    	}
		}		
		return false;
	}
	


}
