package com.hoperun.drp.sale.changeadvcorder.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class ChangeFlowInterfaceImpl implements FlowInterface {

	@Override
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,
			FlowService flowService) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

	/**
	 * 		审核通过后，需要更新：
	 * 		1.预订单明细(DRP_ADVC_ORDER_DTL) 表的
	 * 			单价（PRICE）
	 * 			折扣率（DECT_RATE）
	 *			折后单价（DECT_PRICE）
	 *			订货数量（ORDER_NUM）
	 * 			应收金额（PAYABLE_AMOUNT）
	 * 			交货日期（ORDER_RECV_DATE）
	 * 		2.预订单（DRP_ADVC_ORDER）表的
	 * 		          应收金额（PAYABLE_AMOUNT）
	 * 
	 * 
	 */
	public boolean afterAuditing(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		
		//业务单据ID
		String id = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		if("审核通过".equals(state)){
			String ADVC_ORDER_CHANGE_ID=id;
			Map<String,String> changeInfo = (Map<String,String>)flowService.load("Changeadvcorder.loadById", ADVC_ORDER_CHANGE_ID);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("ADVC_ORDER_CHANGE_ID", ADVC_ORDER_CHANGE_ID);			
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			List changeChildList = flowService.findList("Changeadvcorder.queryChldByFkId", params);
			Map<String,String> map=new HashMap<String,String>();
			String ADVC_ORDER_ID = changeInfo.get("ADVC_ORDER_ID");
			map.put("CUST_NAME", changeInfo.get("CUST_NAME"));
			map.put("TEL", changeInfo.get("TEL"));
			map.put("RECV_ADDR", changeInfo.get("RECV_ADDR"));
			map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			//修改明细
			//按预订单冻结数量 减冻结数
//			LogicUtil.doStoreFreeze(BusinessConsts.ADVC_ORDER1_CONF_ID, ADVC_ORDER_ID);
			for(int i = 0; i < changeChildList.size(); i++){
				Map model = (HashMap) changeChildList.get(i);
				params = new HashMap<String, String>();
				if(!checkChangeOrder(model,flowService)){
					String pdtNo = (String)model.get("PRD_NO");
					String PRD_NAME = (String)model.get("PRD_NAME");
					StringBuffer msg = new StringBuffer();
					 msg.append("货品:")
					 .append(pdtNo).append(",")
					 .append(PRD_NAME)
					 .append("的变更数量大于实际订货数,不允许变更");
					throw new ServiceException(msg.toString());
				}
				BigDecimal CHANGE_NUM = (BigDecimal)model.get("ORDER_NUM");
				BigDecimal SEND_NUM = (BigDecimal)model.get("SEND_NUM");
				BigDecimal FREEZE_NUM = (BigDecimal)model.get("FREEZE_NUM");
				BigDecimal UNFREEZE_NUM = (BigDecimal)model.get("UNFREEZE_NUM");
				String newFreeNum = getNewFreeNum(CHANGE_NUM,SEND_NUM,FREEZE_NUM,UNFREEZE_NUM);
				String ADVC_ORDER_CHANGE_DTL_ID =  (String)model.get("ADVC_ORDER_CHANGE_DTL_ID");
				String  ADVC_ORDER_DTL_ID = (String) model.get("ADVC_ORDER_DTL_ID");
				params.put("ADVC_ORDER_DTL_ID", (String) model.get("ADVC_ORDER_DTL_ID"));
				params.put("PRICE", String.valueOf(model.get("PRICE")));
				params.put("DECT_RATE", String.valueOf(model.get("DECT_RATE")));
				params.put("DECT_PRICE", String.valueOf(model.get("DECT_PRICE")));
				params.put("ORDER_NUM", String.valueOf(model.get("ORDER_NUM")));
				params.put("PAYABLE_AMOUNT", String.valueOf(model.get("PAYABLE_AMOUNT")));
				params.put("ORDER_RECV_DATE", String.valueOf(model.get("ORDER_RECV_DATE")));
				if(newFreeNum != null){
					params.put("FREEZE_NUM", newFreeNum);
				}
				flowService.update("Advcorder.updateChldById", params);
			}			
			//再按更变后单冻结加冻结数  
//			LogicUtil.doStoreFreeze(BusinessConsts.ADVC_ORDER_CONF_ID, ADVC_ORDER_ID);
	        //修改主表总金额
	        flowService.update("Termreturn.upPAYABLE_AMOUNT", changeInfo.get("ADVC_ORDER_ID"));
	        //修改主表电话，客户，收货地址
	        flowService.update("Advcorder.updateById", map);
	        //修改主表交货日期
	        flowService.update("Changeadvcorder.upOldORDER_RECV_DATE", changeInfo.get("ADVC_ORDER_ID"));
	        
		}
		
		return true;
	}
	
	private boolean checkChangeOrder(Map dtlMap,FlowService flowService){
		String ADVC_ORDER_DTL_ID = (String) dtlMap.get("ADVC_ORDER_DTL_ID");
		Map advcOrderMap = (Map<String,String>)flowService.load("Changeadvcorder.loadOldChldById", ADVC_ORDER_DTL_ID);
		BigDecimal oldOrderNum = (BigDecimal)advcOrderMap.get("ORDER_NUM");
		BigDecimal sendNum = (BigDecimal)advcOrderMap.get("SEND_NUM");
		if(sendNum==null || "".equals(sendNum)){
			sendNum = new BigDecimal(0);
		}
		BigDecimal changeNum = (BigDecimal)dtlMap.get("ORDER_NUM");
		int iOldOrderNum = oldOrderNum.intValue();
		int iSendNum =  sendNum.intValue();
		int ichangeNum =  changeNum.intValue();
		if(ichangeNum<=iOldOrderNum-iSendNum){
			return true;
		}
		return false;
	}
	
	private String getNewFreeNum(BigDecimal CHANGE_NUM,BigDecimal SEND_NUM, BigDecimal FREEZE_NUM,BigDecimal UNFREEZE_NUM){
		if(FREEZE_NUM==null || "".equals(FREEZE_NUM)){
			FREEZE_NUM = new BigDecimal(0);
		}
		if(UNFREEZE_NUM==null || "".equals(UNFREEZE_NUM)){
			UNFREEZE_NUM =  new BigDecimal(0);
		}	
		if(SEND_NUM==null || "".equals(SEND_NUM)){
			SEND_NUM =  new BigDecimal(0);
		}
		int I_CHANGE_NUM =  CHANGE_NUM.intValue();
		int I_FREEZE_NUM = FREEZE_NUM.intValue();
		int I_UNFREEZE_NUM = UNFREEZE_NUM.intValue();
		int I_SEND_NUM = SEND_NUM.intValue();
		if((I_FREEZE_NUM - I_UNFREEZE_NUM) >=  (I_CHANGE_NUM - I_SEND_NUM)){
			int I_NewFreeNum =  I_FREEZE_NUM -((I_FREEZE_NUM - I_UNFREEZE_NUM) - (I_CHANGE_NUM - I_SEND_NUM));
			String newFreeNum = String.valueOf(I_NewFreeNum);
			return newFreeNum;
		}else{
			return null;
		}
	}

	@Override
	public boolean beforeAffirm(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		// TODO Auto-generated method stub
		return true;
	}

}
