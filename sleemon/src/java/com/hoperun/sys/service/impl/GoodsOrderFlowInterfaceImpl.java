package com.hoperun.sys.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

/**
 * 分销 -订货订单 提交后执行的操作 
 * @author zhang_zhongbin
 *
 */
public class GoodsOrderFlowInterfaceImpl implements FlowInterface{
	
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
			Map<String,Object> model = (Map<String, Object>) flowService.load("Goodsorderhd.loadById", id);
			model.put("ACTION_NAME", BusinessConsts.ORDER_BILL_ACTION_COMMIT);//订货订单提交
			model.put("STATE", state);
			//插入生命周期
			insertTrack(id,model,userBean,flowService);
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
		String goodsOrderId = (String) request.getAttribute("id");
		UserBean userBean  = ParamUtil.getUserBean(request);
		//业务单据状态
		String state = (String) request.getAttribute("currentState");

		if(BusinessConsts.PASS.equals(state)){
			
			Map<String,Object>params = new HashMap<String,Object>();
			params.put("GOODS_ORDER_ID", goodsOrderId);
			params.put("STATE", BusinessConsts.UNDONE);//未处理
			flowService.update("Goodsorderhd.updateCREDIT_FREEZE_PRICE", goodsOrderId);
			if(LogicUtil.chkCanUseCreditForCommit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, goodsOrderId)){
				//更新信用冻结单价
				LogicUtil.updFreezeCredit(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, goodsOrderId);
				LogicUtil.inertCreditJournal(BusinessConsts.ACCT_GOODS_ORDER_CONF_ID, goodsOrderId);
				flowService.update("Goodsorderhd.afterAuditing", params);
			}else{
				request.setAttribute("faultInfo", "对不起，您的信用额度不够!");
				throw new Exception();
			}
			Map<String,Object> orderMap = (Map<String,Object>)flowService.load("Goodsorderhd.loadById", goodsOrderId);
			String  isUseRebate  =  orderMap.get("IS_USE_REBATE").toString();
			if("1".equals(isUseRebate.toString())){
				if(LogicUtil.chkCanUseRebate(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, goodsOrderId)){
					if(LogicUtil.chkAllCanUseRebate(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, goodsOrderId)){
						LogicUtil.updateRebate(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, goodsOrderId);
						LogicUtil.insRebateJournal(BusinessConsts.FL_GOODS_ORDER1_CONF_ID, goodsOrderId);
					}else{
						throw new ServiceException("对不起，您总的信用不够订货!");
					}
				}else{
					request.setAttribute("faultInfo", "对不起，您的返利额度不够!");
					throw new Exception();
				}				
			}
			
			orderMap.put("ACTION_NAME", "订货订单审核");
			orderMap.put("STATE", state);
			
			//插入生命周期
			insertTrack(goodsOrderId,orderMap,userBean,flowService);

		}
		
		return true;
	}
	

	/**
	 * 插入生命周期
	 * @param id 单据ID
	 * @param model 主表
	 * @param userBean
	 * @param flowService
	 */
	private void insertTrack(String id,Map<String,Object> model,UserBean userBean,FlowService flowService){
		Map<String,Object>params = new HashMap<String,Object>();
		String GOODS_ORDER_TRACE_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_TRACE_ID", GOODS_ORDER_TRACE_ID);
		params.put("GOODS_ORDER_ID", id);
		params.put("BILL_NO", model.get("GOODS_ORDER_NO"));//订货订单的NO
		params.put("BILL_TYPE", BusinessConsts.INSTORE_ORDER_TYPE);//订货订单的type
		params.put("ACTION_NAME", model.get("ACTION_NAME"));//订货订单动作
		params.put("TRACE_URL", BusinessConsts.ORDER_BILL_ACTION_TRACE_URL + id);
		params.put("DEAL_PSON_NAME", userBean.getXM());
		params.put("STATE", model.get("STATE"));
		
		flowService.insert("Goodsorderhd.insertOrderTrack", params);
	}
 

}
