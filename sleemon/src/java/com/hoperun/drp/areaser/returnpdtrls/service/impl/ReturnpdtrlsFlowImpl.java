package com.hoperun.drp.areaser.returnpdtrls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ParamUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.prdreturnreq.model.PrdreturnreqModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.FlowInterface;
import com.hoperun.sys.service.FlowService;

public class ReturnpdtrlsFlowImpl implements FlowInterface {

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
	@Override
	public boolean beforeAffirm(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		
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
	@Override
	public boolean afterAffirm(HttpServletRequest request, HttpSession session,
			FlowService flowService) throws Exception {
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
	@Override
	public boolean afterAuditing(HttpServletRequest request,
			HttpSession session, FlowService flowService) throws Exception {
		 //业务单据ID
		String PRD_RETURN_REQ_ID = (String) request.getAttribute("id");
		//业务单据状态
		String state = (String) request.getAttribute("currentState");
		
		UserBean userBean = ParamUtil.getUserBean(request);
		if("审核通过".equals(state))
		{
			try {
				double STOREOUT_AMOUNT=0;
				Map<String,String> prdReturn = new HashMap<String,String>();
				prdReturn = (HashMap<String,String>) flowService.load("Prdreturnreq.loadById", PRD_RETURN_REQ_ID);
				List<PrdreturnreqModelChld> chldList=new ArrayList<PrdreturnreqModelChld>();
				Map<String,String> checkMap=new HashMap<String,String>();
				checkMap.put("PRD_RETURN_REQ_ID", PRD_RETURN_REQ_ID);
				checkMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				chldList=flowService.findList("Prdreturnreq.queryChldByFkId", checkMap);
				Map<String,String> params = new HashMap<String,String>();
				String STOREOUT_ID=StringUtil.uuid32len();
				params.put("STOREOUT_ID", STOREOUT_ID);
				params.put("STOREOUT_NO",  LogicUtil.getBmgz("DRP_STOREOUT_NO"));
				params.put("BILL_TYPE", "退货出库");
				params.put("FROM_BILL_ID", prdReturn.get("PRD_RETURN_REQ_ID"));
				params.put("FROM_BILL_NO", prdReturn.get("PRD_RETURN_REQ_NO"));
				params.put("SEND_CHANN_ID", prdReturn.get("RETURN_CHANN_ID"));
				params.put("SEND_CHANN_NO", prdReturn.get("RETURN_CHANN_NO"));
				params.put("SEND_CHANN_NAME", prdReturn.get("RETURN_CHANN_NAME"));
				params.put("RECV_CHANN_ID", prdReturn.get("RECV_CHANN_ID"));
				params.put("RECV_CHANN_NO", prdReturn.get("RECV_CHANN_NO"));
				params.put("RECV_CHANN_NAME", prdReturn.get("RECV_CHANN_NAME"));
				params.put("STOREOUT_STORE_ID", prdReturn.get("RETURN_STORE_ID"));
				params.put("STOREOUT_STORE_NO", prdReturn.get("RETURN_STORE_NO"));
				params.put("STOREOUT_STORE_NAME", prdReturn.get("RETURN_STORE_NAME"));
				String STORAGE_FLAG=(String) flowService.load("Prdreturnreq.getStorageFlag", prdReturn.get("RETURN_STORE_ID"));
				params.put("STORAGE_FLAG", STORAGE_FLAG);
				params.put("STATE", BusinessConsts.UNDONE);
				params.put("CRE_NAME",userBean.getXM());//制单人名称
				params.put("CREATOR",userBean.getXTYHID());//制单人ID
				params.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
				params.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
				params.put("ORG_ID",userBean.getJGXXID());//制单机构ID
				params.put("ORG_NAME",userBean.getJGMC());//制单机构名称
				params.put("CRE_TIME","sysdate");//制单时间
				params.put("LEDGER_ID",prdReturn.get("RETURN_CHANN_ID"));//帐套ID
				params.put("LEDGER_NAME",prdReturn.get("RETURN_CHANN_NO"));//帐套名称
				params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
				params.put("YEAR_MONTH","sysdate");//年份月份
				params.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
				List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
				for (PrdreturnreqModelChld map : chldList) {
					//退货数量
					int RETURN_NUM = StringUtil.getInteger(map.getRETURN_NUM());//退货数量
					for (int i = 0; i < RETURN_NUM; i++) {
						Map<String,String> chldMap=new HashMap<String,String>();
						Integer NOTICE_NUM = 1;
						String STOREOUT_DTL_ID=StringUtil.uuid32len();
						chldMap.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
						chldMap.put("STOREOUT_ID", STOREOUT_ID);
						chldMap.put("PRD_ID", map.getPRD_ID());
						chldMap.put("PRD_NO", map.getPRD_NO());
						chldMap.put("PRD_NAME", map.getPRD_NAME());
						chldMap.put("PRD_SPEC", map.getPRD_SPEC());
						chldMap.put("PRD_COLOR", map.getPRD_COLOR());
						chldMap.put("BRAND", map.getBRAND());
						chldMap.put("STD_UNIT", map.getSTD_UNIT());
						
						double RETURN_PRICE = StringUtil.getDouble(map.getRETURN_PRICE());//退货单价
						double RETURN_AMOUNT = StringUtil.getDouble(map.getRETURN_AMOUNT());//退货金额
						double DECT_AMOUNT = RETURN_AMOUNT/RETURN_NUM;
						double DECT_RATE=DECT_AMOUNT/RETURN_PRICE;
						chldMap.put("PRICE", String.format("%.2f", RETURN_PRICE));
						chldMap.put("DECT_RATE", String.format("%.2f", DECT_RATE));
						chldMap.put("DECT_PRICE", String.format("%.2f", RETURN_PRICE));
						chldMap.put("DECT_AMOUNT", String.format("%.2f", DECT_AMOUNT));
						chldMap.put("NOTICE_NUM",  NOTICE_NUM.toString());
						chldMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
						chldMap.put("FROM_BILL_DTL_ID", map.getPRD_RETURN_DTL_REQ_ID());
						chldMap.put("YEAR_MONTH","sysdate");//年份月份
						chldMap.put("SPCL_TECH_ID", map.getSPCL_TECH_ID());
						chldMap.put("REMARK", map.getRETURN_RSON());
						chld.add(chldMap);
					}
				}
				params.put("STOREOUT_AMOUNT", STOREOUT_AMOUNT+"");
				flowService.insert("Storeout.insert", params);
				if (!chld.isEmpty()) {
					flowService.batch4Update("Storeout.insertChld", chld);
		        }
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}


}
