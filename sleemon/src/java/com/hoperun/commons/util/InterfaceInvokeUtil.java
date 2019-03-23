package com.hoperun.commons.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.InterReturnMsg;
import com.hoperun.commons.service.impl.InterfaceInvokeServiceImpl;
import com.hoperun.commons.webservice.CommWebServiceClient;

public class InterfaceInvokeUtil {
	private static String callESBAsyMethod = "doAsynTask"; //调用ESB异步方法
	private static String callESBSynMethod = "doSyncTask"; //调用ESB同步方法
	/**产生接口CreateSO 的Json
	 * @param BusinessId
	 * @param Is_Abort_Flag
	 * @return
	 * @throws Exception
	 */
	public static String getStrCreateSO(String BusinessId,String Is_Abort_Flag,String UId)throws Exception{
		InterfaceInvokeServiceImpl InterfaceInvokeImpl = (InterfaceInvokeServiceImpl) SpringContextUtil.getBean("interfaceService");
		String ServiceCode = "TC0300010";
		String Operation = "createSO";
		String AppCode = "DM";
		String DestCode = "U9";
		String OPRCODE = ServiceCode + ":" + "createSO";
		String strJsonData =  InterfaceInvokeImpl.getStrCreateSO(BusinessId,Is_Abort_Flag,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	/**产生接口CreateSO 的Json
	 * @param BusinessId
	 * @param Is_Abort_Flag
	 * @return
	 * @throws Exception
	 */
	public static String getStrCloseCreateSO(String BusinessId,String Is_Abort_Flag,String SALE_ORDER_DTL_IDS,String UId)throws Exception{
		InterfaceInvokeServiceImpl InterfaceInvokeImpl = (InterfaceInvokeServiceImpl) SpringContextUtil.getBean("interfaceService");
		String ServiceCode = "TC0300010";
		String Operation = "createSO";
		String AppCode = "DM";
		String DestCode = "U9";
		String OPRCODE = ServiceCode + ":" + "createSO";
		String strJsonData =  InterfaceInvokeImpl.getStrCloseCreateSO(BusinessId,Is_Abort_Flag,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE,SALE_ORDER_DTL_IDS);
		return strJsonData;
	}
	
	/**DM销售订单生成U9销售订单
	 * @param BusinessId 
	 * @return 
	 */
	public static String createSO(String strJsonData)throws Exception{
		String ServiceCode = "TC0300010";
		ArrayList bussParam = new ArrayList();
		bussParam.add(ServiceCode);
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
		return result;
	}
	
	public static String strCreateShipPlan(String BusinessId,String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE)throws Exception{
		InterfaceInvokeServiceImpl InterfaceInvokeImpl = (InterfaceInvokeServiceImpl) SpringContextUtil.getBean("interfaceService");
		String strJsonData =  InterfaceInvokeImpl.strCreateShipPlan(BusinessId,InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	/**创建出货单
	 * @param BusinessId
	 * @return
	 */
	public static String createShipPlan(String strJsonData)throws Exception{
		String InterfaceCode = "TC0300020";
		ArrayList bussParam = new ArrayList();
		bussParam.add(InterfaceCode);
		bussParam.add(strJsonData);
		String result = new CommWebServiceClient().callWebService(callESBSynMethod, bussParam);
//		return returnRspInfo(result);
		return result;
	}
	
	
	/**按行取消
	 * @param deliverId
	 * @param InterfaceCode
	 * @param ServiceCode
	 * @param Operation
	 * @param AppCode
	 * @param DestCode
	 * @param UId
	 * @param OPRCODE
	 * @return
	 * @throws Exception
	 */
	public static String strCreateShipPlanByClose(String deliverId,String DELIVER_ORDER_DTL_IDS,String InterfaceCode,String ServiceCode,String Operation,String AppCode,String DestCode,String UId,String OPRCODE)throws Exception{
		InterfaceInvokeServiceImpl InterfaceInvokeImpl = (InterfaceInvokeServiceImpl) SpringContextUtil.getBean("interfaceService");
		String strJsonData =  InterfaceInvokeImpl.createShipPlanByClose(deliverId,DELIVER_ORDER_DTL_IDS,InterfaceCode,ServiceCode,Operation,AppCode,DestCode,UId,OPRCODE);
		return strJsonData;
	}
	
	
	/**U9回填实际发货数量，做相关处理 发运确认
	 * @param messageDate  出库主表信息
	 * @param jsonPdt   出库货品明细信息
	 * @param jsonPdt   出库序列号明细
	 * @throws Exception
	 */
	public static String updateDeliverOrder(String userName,String passWord,String messageDate){
		InterReturnMsg result = null;
		try{
			LogicUtil.actLog("发运管理", "开始调入发运单确认接口", "U9或UA系统", "成功", messageDate, "", "", "","");
			InterfaceInvokeServiceImpl InterfaceInvokeImpl = (InterfaceInvokeServiceImpl) SpringContextUtil.getBean("interfaceService");
			if(!LogicUtil.checkEsbUser(userName,passWord)){
				result =  InterfaceInvokeImpl.returnCheckUserFail();
			}else{
				result =  InterfaceInvokeImpl.txUpdateDeliverOrder(messageDate);
			}
			if(BusinessConsts.SUCCESS.equals(result.getFLAG())){
				LogicUtil.actLog("发运管理", "发运单确认", result.getAPPCODE(), "成功", messageDate, "", "", "",result.getKEY());
			}
			if(BusinessConsts.FLASE.equals(result.getFLAG())){
				LogicUtil.actLog("发运管理", "发运单确认", result.getAPPCODE(), "失败", "["+result.getMESSAGE()+"]"+messageDate, "", "", "",result.getKEY());
			}
		}catch(ServiceException e){
			result = new InterReturnMsg();
			result.setFLAG(BusinessConsts.FLASE);
			result.setMESSAGE(e.getMessage());
			JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
			HashMap headMap = jsonUtil.getMbHead();
			String APPID = (String) headMap.get("UId");
			LogicUtil.actLog("发运管理", "发运单确认", "U9", "失败", "["+result.getMESSAGE()+"]"+messageDate, "", "", "",result.getKEY());
			return returnDMInfo("TU0300010","updateDeliverOrder","E0000016",APPID,result); 
		}catch(Exception ex){
			result = new InterReturnMsg();
			result.setFLAG(BusinessConsts.FLASE);
			result.setMESSAGE(ex.getMessage());
			JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
			HashMap headMap = jsonUtil.getMbHead();
			String APPID = (String) headMap.get("UId");
			LogicUtil.actLog("发运管理", "发运单确认", "U9", "失败", "["+result.getMESSAGE()+"]"+messageDate, "", "", "",result.getKEY());
			return returnDMInfo("TU0300010","updateDeliverOrder","E0000016",APPID,result); 
		}
		JesonImplUtil jsonUtil = new JesonImplUtil(messageDate);
		HashMap headMap = jsonUtil.getMbHead();
		String APPID = (String) headMap.get("UId");
		return returnDMInfo("TU0300010","updateDeliverOrder","E0000016",APPID,result); 

	}
	
	/**U9客户信用明细信息查询
	 * @param BusinessId 
	 * @return 
	 */
	public static String queryPayMentDetails(String strJsonData)throws Exception{
		String result = new CommWebServiceClient().callU9WebService("queryPayMentDetails", strJsonData);
		LogicUtil.actLog("U9查询对账单", "U9查询对账单", "U9", "成功", result, "", "", "","");
		return result;
	}
	
	public static String querytest(String strJsonData)throws Exception{
		String result = new CommWebServiceClient().callSelfTestWebService("qrySales", strJsonData);
		LogicUtil.actLog("U9查询对账单", "U9查询对账单", "U9", "成功", result, "", "", "","");
		return result;
	}
	public static String insAdvcOrder(String strJsonData)throws Exception{
		String result = new CommWebServiceClient().callSelfTestWebService("insAdvcOrder", strJsonData);
		LogicUtil.actLog("U9查询对账单", "U9查询对账单", "U9", "成功", result, "", "", "","");
		return result;
	}
	
	
	private static String returnDMInfo(String code,String operation,String errorCode,String UId,InterReturnMsg msg){
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", code);
		headMap.put("ServiceCode", code);
		headMap.put("Operation", operation);
		headMap.put("AppCode", "U9");
		headMap.put("DestCode", "DM");
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		ArrayList respList = new ArrayList();
		HashMap rspMap = new HashMap();
		rspMap.put("Status", msg.getFLAG());
		
		if(BusinessConsts.SUCCESS.equals(msg.getFLAG())){
			rspMap.put("Code", "00000000");
		}else{
			rspMap.put("Code",errorCode);
		}
		String messge ="";
		if(msg.getMESSAGE()!=null){
			messge = msg.getMESSAGE();
		}
		rspMap.put("Desc", messge);
		respList.add(rspMap);
		headMap.put("ServiceResponse", respList);
		return JesonImplUtil.getImplRsponseJson(headMap, new LinkedHashMap());
	}
	
	public static String returnRspInfo(String strJsonData){
		JesonImplUtil jsUtil = new JesonImplUtil(true,strJsonData);
		HashMap headMap =  jsUtil.getMbHead();
		Map rspMap = jsUtil.getResponse();
		ArrayList bodyList = jsUtil.getMbBody();
		HashMap bodyMap = (HashMap)bodyList.get(0);
		String resuData = new Gson().toJson(bodyMap);
    	return resuData;
	}
	


}
