package com.hoperun.commons.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.InterReturnMsg;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.CreditCtrlUtil;
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;

/**
 * 改成从销售订单调用U9/UA接口
 * 
 * @author Administrator
 * 
 */
public class InterfaceInvokeServiceImpl extends BaseService {
	/**
	 * 生成销售订单
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String getStrCreateSO(String BusinessId, String Is_Abort_Flag,
			String ServiceCode, String Operation, String AppCode,
			String DestCode, String UId, String OPRCODE) throws Exception {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", ServiceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);

		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer saleBuf = new StringBuffer();
		saleBuf
				.append(
						"SELECT STATE,RECV_ADDR_NO as AddressCode,to_char(t.CRE_TIME,'yyyy-mm-dd') as BusinessDate,t.SALE_ORDER_NO as PlanDocNo,'01' as DocType,(select SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode,t.REMARK as Memo FROM ERP_SALE_ORDER t WHERE t.SALE_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", saleBuf.toString());
		Map saleOrderMap = selcom(params);
		String ADDRESSCODE = (String) saleOrderMap.get("ADDRESSCODE");
		if (ADDRESSCODE == null || ADDRESSCODE.trim().length() == 0) {
			throw new ServiceException("主表收货地址为空,不能提交!");
		}

		StringBuffer deliverDtlBuf = new StringBuffer();
		// 工艺单特殊工艺的说明，要把订单工艺所有记录并装，加上备注信息
		deliverDtlBuf
				.append("SELECT d.ADVC_SEND_DATE as RequireDate,d.NEW_SPEC as Spec,d.NEW_COLOR as FlowerNo, t.ORDER_CHANN_NO as CustomerCode,d.PRD_NO as ItemMaster,d.SPCL_TECH_ID as SpeTech_No,'' as SpeTech_Description, d.U_CODE as PlanDocLineID,d.ORDER_NUM as Qty,d.DECT_PRICE as Price ,d.REMARK as Memo , d.IS_BACKUP_FLAG,NVL(d.IS_FREE_FLAG,0)IS_FREE_FLAG,d.PRODUCT_REMARK FROM ERP_SALE_ORDER t ,ERP_SALE_ORDER_DTL d  WHERE t.SALE_ORDER_ID = d.SALE_ORDER_ID and NVL(d.STATE,'正常')= '正常' and d.SALE_ORDER_ID = '");
		deliverDtlBuf.append(BusinessId).append("' and d.DEL_FLAG = 0 ");
		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List saleDtlList = selcomList(params);
		setSOBodyMap(Is_Abort_Flag, bodyMap, saleOrderMap, saleDtlList);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}

	/**
	 * 销售订单行关闭
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String getStrCloseCreateSO(String BusinessId, String Is_Abort_Flag,
			String ServiceCode, String Operation, String AppCode,
			String DestCode, String UId, String OPRCODE,
			String SALE_ORDER_DTL_IDS) throws Exception {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", ServiceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);

		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer saleBuf = new StringBuffer();
		saleBuf
				.append(
						"SELECT STATE,RECV_ADDR_NO as AddressCode,to_char(t.CRE_TIME,'yyyy-mm-dd') as BusinessDate,t.SALE_ORDER_NO as PlanDocNo,'01' as DocType,(select SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode,t.REMARK as Memo FROM ERP_SALE_ORDER t WHERE t.SALE_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", saleBuf.toString());
		Map saleOrderMap = selcom(params);
		String ADDRESSCODE = (String) saleOrderMap.get("ADDRESSCODE");
		if (ADDRESSCODE == null || ADDRESSCODE.trim().length() == 0) {
			throw new ServiceException("主表收货地址为空,不能提交!");
		}

		StringBuffer deliverDtlBuf = new StringBuffer();
		// 工艺单特殊工艺的说明，要把订单工艺所有记录并装，加上备注信息
		deliverDtlBuf
				.append("SELECT d.ADVC_SEND_DATE as RequireDate,d.NEW_SPEC as Spec,d.NEW_COLOR as FlowerNo, t.ORDER_CHANN_NO as CustomerCode,d.PRD_NO as ItemMaster,d.SPCL_TECH_ID as SpeTech_No,'' as SpeTech_Description, d.U_CODE as PlanDocLineID,d.ORDER_NUM as Qty,d.DECT_PRICE as Price ,d.REMARK as Memo , d.IS_BACKUP_FLAG,NVL(d.IS_FREE_FLAG,0)IS_FREE_FLAG FROM ERP_SALE_ORDER t ,ERP_SALE_ORDER_DTL d  WHERE t.SALE_ORDER_ID = d.SALE_ORDER_ID  and d.SALE_ORDER_ID = '");
		if (Is_Abort_Flag != null) {
			deliverDtlBuf.append(BusinessId).append("'").append(
					" and SALE_ORDER_DTL_ID in (").append(SALE_ORDER_DTL_IDS)
					.append(")");
		} else {
			deliverDtlBuf.append(BusinessId).append("' and d.DEL_FLAG = 0 ");
		}

		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List saleDtlList = selcomList(params);
		setSOBodyMap(Is_Abort_Flag, bodyMap, saleOrderMap, saleDtlList);
		return JesonImplUtil.getImplJson(headMap, bodyMap);
	}

	private void setSOBodyMap(String Is_Abort_Flag, LinkedHashMap bodyMap,
			Map deliverMap, List deliverDtlList) throws Exception {
		HashMap mainMap = new HashMap();
		ArrayList dtlList = new ArrayList();
		mainMap.put("BusinessDate", deliverMap.get("BUSINESSDATE"));
		mainMap.put("PlanDocNo", deliverMap.get("PLANDOCNO"));
		mainMap.put("DocType", deliverMap.get("DOCTYPE"));
		mainMap.put("OrgCode", deliverMap.get("ORGCODE"));
		mainMap.put("Memo", deliverMap.get("MEMO"));
		mainMap.put("Is_Maintenance_Flag", deliverMap
				.get("Is_Maintenance_Flag"));
		if (Is_Abort_Flag != null) {
			mainMap.put("Is_Abort_Flag", Is_Abort_Flag);
		}
		bodyMap.putAll(mainMap);

		for (int i = 0; i < deliverDtlList.size(); i++) {
			HashMap dtlMap = (HashMap) deliverDtlList.get(i);
			String spetechId = (String) dtlMap.get("SPETECH_NO");
			String pdtNo = (String) dtlMap.get("ITEMMASTER");
			String spetechNo = null;
			String spetchDesc = null;
			if (spetechId != null) {
				spetechNo = LogicUtil.getSpecTechNO(spetechId, pdtNo);
				spetchDesc = LogicUtil.getSpclInfoById(spetechId);
				String PRODUCT_REMARK = (String) dtlMap.get("PRODUCT_REMARK");
				if(PRODUCT_REMARK==null){
					PRODUCT_REMARK = "";
				}
				spetchDesc = spetchDesc+","+PRODUCT_REMARK;
				LogicUtil.instSpechNoRemark((String)deliverMap.get("PLANDOCNO"),(String)dtlMap.get("PLANDOCLINEID"),pdtNo,spetechId,spetechNo,spetchDesc);
			}
			
			HashMap tempMap = new HashMap();
			tempMap.put("Spec", dtlMap.get("SPEC"));
			tempMap.put("FlowerNo", dtlMap.get("FLOWERNO"));
			tempMap.put("CustomerCode", dtlMap.get("CUSTOMERCODE"));
			tempMap.put("AddressCode", deliverMap.get("ADDRESSCODE"));
			tempMap.put("ItemMaster", pdtNo);
			tempMap.put("SpeTech_No", spetechNo);
			tempMap.put("SpeTech_Description", spetchDesc);
			tempMap.put("PlanDocLineID", dtlMap.get("PLANDOCLINEID"));
			tempMap.put("Qty", dtlMap.get("QTY"));
			BigDecimal price =  (BigDecimal)dtlMap.get("PRICE");
			if(price.doubleValue()<=0){
				throw new ServiceException("货品:"+pdtNo+"价格小于等于0,不能提交");
			}
			tempMap.put("Price", dtlMap.get("PRICE"));
			tempMap.put("Memo", dtlMap.get("MEMO"));
			tempMap.put("RequireDate", "" + dtlMap.get("REQUIREDATE"));
			BigDecimal IS_BACKUP_FLAG = (BigDecimal) dtlMap
					.get("IS_BACKUP_FLAG");
			if (IS_BACKUP_FLAG == null) {
				IS_BACKUP_FLAG = new BigDecimal("0");
			}
			tempMap.put("IS_BACKUP_FLAG", IS_BACKUP_FLAG.toString());
			BigDecimal IS_FREE_FLAG = (BigDecimal) dtlMap.get("IS_FREE_FLAG");
			tempMap.put("IS_FREE_FLAG", IS_FREE_FLAG.toString());
			dtlList.add(tempMap);

		}
		bodyMap.put("DETAIL", dtlList);
	}
	


	/**
	 * 创建出货单
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String strCreateShipPlan(String BusinessId, String InterfaceCode,
			String ServiceCode, String Operation, String AppCode,
			String DestCode, String UId, String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", InterfaceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf
				.append(
						"SELECT t.DELIVER_ORDER_ID as ShipPlanID,t.DELIVER_ORDER_NO as ShipPlanDocNo,to_char(t.APPCH_TIME,'yyyy-mm-dd') as ShipPlanDate,t.DELIVER_ORDER_NO as ShipPlanNO,(select p.SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode ,REMARK as MEMO FROM ERP_DELIVER_ORDER t WHERE t.DELIVER_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = selcom(params);
		StringBuffer deliverDtlBuf = new StringBuffer();
		deliverDtlBuf
				.append(
						"SELECT d.SALE_ORDER_NO, d.SPCL_TECH_ID,d.ORDER_CHANN_NO as CustomerCode,d.RECV_ADDR_NO as CustomerSiteCode,1 Direction,d.SALE_ORDER_DTL_ID as DeliverPlanLineID,d.U_CODE as PlanDocLineID,d.PRD_NO as ItemMasterCode, (select SPCL_TECH_NO FROM DRP_SPCL_TECH s WHERE s.spcl_tech_id =d.spcl_tech_id) SpeTech_No ,d.PLAN_NUM as ShipQty,0 as ShipType,d.DECT_PRICE as Price,d.REMARK as Memo FROM ERP_DELIVER_ORDER_DTL d WHERE d.DELIVER_ORDER_ID = '")
				.append(BusinessId).append(
						"'   and d.DEL_FLAG =0 and NVL(d.PLAN_NUM,0) > 0");
		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List deliverDtlList = selcomList(params);
		bodyMap.putAll(deliverMap);
		setShipBodyMap(bodyMap,deliverDtlList);
		bodyMap.put("DETAIL", deliverDtlList);
		String strJsonData = JesonImplUtil.getImplJson(headMap, bodyMap);
		return strJsonData;
	}
	
	private void setShipBodyMap(LinkedHashMap bodyMap,List deliverDtlList)  {
		for (int i = 0; i < deliverDtlList.size(); i++) {
			HashMap dtlMap = (HashMap) deliverDtlList.get(i);
			String SALE_ORDER_NO =  (String)dtlMap.get("SALE_ORDER_NO");
			String SPCL_TECH_ID =  (String)dtlMap.get("SPCL_TECH_ID");
			String PLANDOCLINEID =  (String)dtlMap.get("PLANDOCLINEID");
			String SpeTech_No = (String) dtlMap.get("SPETECH_NO");
			if (SpeTech_No != null && SpeTech_No.trim().length()>0) {
				String oldSptechNo = getOldSpechNo(SALE_ORDER_NO,PLANDOCLINEID,SPCL_TECH_ID);
				if(oldSptechNo!=null){
					SpeTech_No = oldSptechNo;
					dtlMap.put("SPETECH_NO", SpeTech_No);
				}
			}
			dtlMap.remove("SPCL_TECH_ID");
			dtlMap.remove("SALE_ORDER_NO");
		}
	}
	
	private String  getOldSpechNo(String PLANDOCNO,String PLANDOCLINEID,String spetechId){
		StringBuffer spechBuf = new StringBuffer();
		spechBuf
		        .append(" select SPCL_TECH_NO from (")
				.append("select t.SPCL_TECH_NO")
				.append("  from DRP_SPCL_TECH_REMARK t")
				.append(" where")
				.append("  t.sale_order_no = '"+PLANDOCNO+"'")
				.append("  and t.sale_order_dtl_id = '"+PLANDOCLINEID+"'")
				.append("  and t.spcl_tech_id = '"+spetechId+"'")
				.append("  order by t.spcl_tech_no desc)")
				.append(" where  rownum=1");
		Map params = new HashMap();
		params.put("SelSQL", spechBuf.toString());
		Map spechMap = selcom(params);
		if(spechMap!=null&& spechMap.size()>0){
			return (String)spechMap.get("SPCL_TECH_NO");
		}
		return null;
	}
	

	public InterReturnMsg returnCheckUserFail() {
		InterReturnMsg msg = new InterReturnMsg();
		msg.setFLAG(BusinessConsts.FLASE);
		msg.setMESSAGE("用户名密码不正确!");
		return msg;
	}

	/**
	 * U9接口 发运单确认时U9导入实出数量,序列号
	 * 
	 * @param jsonDeliver
	 * @param jsonPdt
	 * @param jsonStoreOut
	 * @return
	 * @throws Exception
	 */
	public InterReturnMsg txUpdateDeliverOrder(String jsonDeliver) {
		InterReturnMsg msg = new InterReturnMsg();
		if (jsonDeliver == null || jsonDeliver.trim().length() == 0) {
			throw new ServiceException("参数为空!");
		}
		boolean flg = true;
		HashMap headMap = null;
		HashMap bodyMap = null;
		ArrayList dtlList = null;
		try {
			JesonImplUtil jsonUtil = new JesonImplUtil(jsonDeliver);
			headMap = jsonUtil.getMbHead();
			bodyMap = (HashMap) jsonUtil.getMbBody().get(0);
			dtlList = jsonUtil.getMbDtlList();
		} catch (Exception ex) {
			String errorInfo = BusinessConsts.IMPL_PARAM_FORMAT_ERR + ":"
					+ jsonDeliver;
			throw new ServiceException(errorInfo);
		}
		String APPCODE = (String) headMap.get("AppCode");
		msg.setAPPCODE(APPCODE);
		String APPID = (String) headMap.get("UId");
		String ServiceCode = (String) headMap.get("ServiceCode");
		String Operation = (String) headMap.get("Operation");
		String OPRCODE = ServiceCode + ":" + Operation;
		String deliverOrderNo = (String) bodyMap.get("DELIVER_ORDER_NO");
		String TRUCK_NO = (String) bodyMap.get("TRUCK_NO");
		String STOREOUT_TIME = (String) bodyMap.get("STOREOUT_TIME");
		String U9_SM_NO = (String) bodyMap.get("SHIP_DOCNO"); // U9出库单
		String Ship_Org = (String) bodyMap.get("SHIP_ORG"); // U9出库单
		U9_SM_NO = U9_SM_NO+"-"+Ship_Org;
		String BillType = "正常销售";
		String uCode = null;
		String SARE_ORDER_DTL_ID = null;
		String STOREOUT_ID = StringUtil.uuid32len();
		if ("UA".equals(APPCODE)) {
			msg.setKEY(deliverOrderNo);
			msg.setAPPCODE(APPCODE);
			msg.setFLAG(BusinessConsts.SUCCESS);
			return msg;
		}
		for (int j = 0; j < dtlList.size(); j++) {
			HashMap dtlMap = (HashMap) dtlList.get(j);
			String DELIVER_ORDER_DTL_ID = (String) dtlMap
					.get("DELIVER_ORDER_DTL_ID");
			if (StringUtil.isEmpty(DELIVER_ORDER_DTL_ID)) {
				BillType = "引单出库";
			}
			uCode = (String) dtlMap.get("PLANDOCLINEID");
			String realStoreNum = (String) dtlMap.get("REAL_STOREOUT_NUM");
			try {
				String strPrdSns = (String) dtlMap.get("PRD_SN");
				if (strPrdSns != null && strPrdSns.trim().length() > 0) {
					flg = instErpSN(STOREOUT_ID, deliverOrderNo, Ship_Org,
							uCode, strPrdSns);
				}
			} catch (Exception ex) {
				String errorInfo = "更新出库货品序列号出错!" + getErrorInfo(ex);
				throw new ServiceException(errorInfo);
			}
			if (!flg) {
				throw new ServiceException("更新出库货品序列号出错!");
			}
			if (!StringUtil.isEmpty(DELIVER_ORDER_DTL_ID)) {
//				SARE_ORDER_DTL_ID = DELIVER_ORDER_DTL_ID;  //暂时用
				SARE_ORDER_DTL_ID = getSaleOrderDtlId(uCode, Ship_Org);
			} else {
				try{
					SARE_ORDER_DTL_ID = getSaleOrderDtlId(uCode, Ship_Org);
				}catch(Exception ex){
					String errorInfo = "找不到对应的销售订单!";
					throw new ServiceException(errorInfo);
				}
			}
			try {
				CreditCtrlUtil.updateFreezeCreditForPdtDeliver(
						SARE_ORDER_DTL_ID, realStoreNum);
			} catch (Exception ex) {
				String errorInfo = "更新冻结信用出错!" + getErrorInfo(ex);
				throw new ServiceException(errorInfo);
			}
			try {
				updateDeliverOrderDtl(SARE_ORDER_DTL_ID);
			} catch (Exception ex) {
				String errorInfo = "更新发运单明细状态出错!" + getErrorInfo(ex);
				throw new ServiceException(errorInfo);
			}
		}
		try {
			if (checkU9SmNoIsExsit(U9_SM_NO)) {
				insetErpStoreOut(STOREOUT_ID, BillType, deliverOrderNo,
						SARE_ORDER_DTL_ID, Ship_Org, STOREOUT_TIME, TRUCK_NO,
						U9_SM_NO, dtlList);
			} else {
				String errorInfo = "U9出库单:" + U9_SM_NO + "在DM系统已经存在,不允许推送!";
				throw new ServiceException(errorInfo);
			}
		} catch (Exception ex) {
			String errorInfo = "生成出库单出错!" + getErrorInfo(ex);
			throw new ServiceException(errorInfo);
		}
		try{
			inserStoreinOrder(STOREOUT_ID);
		}catch(Exception ex){
			String errorInfo = "生成入库通知单出错!" + getErrorInfo(ex);
			throw new ServiceException(errorInfo);
		}
		try {
			flg = updateSaleOrderDtl(STOREOUT_ID);
		} catch (Exception ex) {
			String errorInfo = "更新销售订单实出数量出错!" + getErrorInfo(ex);
			throw new ServiceException(errorInfo);
		}
		msg.setKEY(deliverOrderNo);
		msg.setAPPCODE(APPCODE);
		msg.setFLAG(BusinessConsts.SUCCESS);
		return msg;
	}

	/**
	 * 更新发运单明细状态
	 * 
	 * @param pdtList
	 * @return
	 */
	private boolean updateDeliverOrderDtl(String SARE_ORDER_DTL_ID) throws Exception {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append(" UPDATE ERP_DELIVER_ORDER_DTL d SET d.IS_SEND_FIN = 1")
				.append(" WHERE d.DELIVER_ORDER_DTL_ID = '").append(SARE_ORDER_DTL_ID)
				.append("'");
		Map params = new HashMap();
		params.put("UpdSQL", sqlBuf.toString());
		return txUpdcom(params);
	}

	/**
	 * 更新销单主表状态
	 * 
	 * @param deliverOderNo
	 * @param TRUCK_NO
	 * @param STOREOUT_TIME
	 * @param IS_SEND_FIN
	 * @return
	 * @throws Exception
	 */
	public void updateSaleOrder(String SALE_ORDER_DTL_ID)throws ServiceException  {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("select SUM(NVL(d.ORDER_NUM, 0)) ORDER_NUM , SUM(NVL(d.SENDED_NUM, 0)) SENDED_NUM")
		.append("   from ERP_SALE_ORDER_DTL d ")
		.append(" where d.state != '关闭'")
		.append("  and d.del_flag = 0")
		.append("   and d.sale_order_id in ")
		.append("  (select sale_order_id from ERP_SALE_ORDER_DTL d where d.sale_order_dtl_id = '")
		.append(SALE_ORDER_DTL_ID)
		.append("')");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		Map saleOrderMap = selcom(params);
		if(saleOrderMap!=null){
			BigDecimal ORDER_NUM =  (BigDecimal)saleOrderMap.get("ORDER_NUM");
			BigDecimal SENDED_NUM =  (BigDecimal)saleOrderMap.get("SENDED_NUM");
			if(ORDER_NUM.toString().equals(SENDED_NUM.toString())){
				StringBuffer updateBuf = new StringBuffer();
				updateBuf.append("update erp_sale_order t set t.state = '已发货' where t.sale_order_id in (")
				.append("select d.sale_order_id from erp_sale_order_dtl d where d.sale_order_dtl_id = '")
				.append(SALE_ORDER_DTL_ID).append("')");
				params = new HashMap();
				params.put("UpdSQL", updateBuf.toString());
				txUpdcom(params);
			}
		}
	}

	public boolean checkDeliverDtlStat(String deliverOderNo) {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf.append("SELECT count(d.DELIVER_ORDER_DTL_ID) IS_SEND_FIN")
				.append("  FROM ERP_DELIVER_ORDER t").append(
						"   left join ERP_DELIVER_ORDER_DTL d").append(
						" on t.DELIVER_ORDER_ID = d.DELIVER_ORDER_ID").append(
						" where d.DEL_FLAG = 0 and NVL(d.IS_SEND_FIN, 0) = 0")
				.append("  and t.DELIVER_ORDER_NO = '" + deliverOderNo + "'");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		Map checkMap = selcom(map);
		BigDecimal IS_SEND_FIN = (BigDecimal) checkMap.get("IS_SEND_FIN");
		int allNum = IS_SEND_FIN.intValue();
		if (0 == allNum) {
			return true;
		}
		return false;
	}

	/**
	 * 更新销售订单明细货品实出数量
	 * 
	 * @param pdtList
	 * @return
	 */
	private boolean updateSaleOrderDtl(String STOREOUT_ID)
			throws ServiceException {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						" select d.SALE_ORDER_DTL_ID, sum(NVL(d.STOREOUT_NUM, 0)) STOREOUT_NUM")
				.append("   from ERP_STOREOUT t, ERP_STOREOUT_PRD_DTL d")
				.append("  where t.storeout_id = d.storeout_id").append(
						"    and d.storeout_id = '" + STOREOUT_ID + "'")
				.append("  group by d.sale_order_dtl_id");
		HashMap params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		List saleDtlList = selcomList(params);
		for (int i = 0; saleDtlList != null && i < saleDtlList.size(); i++) {
			HashMap saleMap = (HashMap) saleDtlList.get(i);
			String SALE_ORDER_DTL_ID = (String) saleMap
					.get("SALE_ORDER_DTL_ID");
			BigDecimal STOREOUT_NUM = (BigDecimal) saleMap.get("STOREOUT_NUM");
			StringBuffer updateSql = new StringBuffer();
			updateSql
					.append(
							"UPDATE ERP_SALE_ORDER_DTL d SET d.SENDED_NUM = NVL(d.SENDED_NUM,0) +")
					.append(STOREOUT_NUM).append(
							" WHERE d.SALE_ORDER_DTL_ID = '").append(
							SALE_ORDER_DTL_ID).append("'");
			params = new HashMap();
			params.put("UpdSQL", updateSql.toString());
			txUpdcom(params);
			
		    updateSql = new StringBuffer();
		    updateSql.append("update erp_sale_order_dtl d set d.state = '已发货' where d.sale_order_dtl_id = '"+SALE_ORDER_DTL_ID+"' and NVL(d.order_num,0)=NVL(d.sended_num,0)");
		    params.put("UpdSQL", updateSql.toString());
			txUpdcom(params);
			
			updateSaleOrder(SALE_ORDER_DTL_ID);
		}
		return true;
	}

	/**
	 * 检查U9出库单是否存在，存在不允许再推
	 * 
	 * @param U9_SM_NO
	 * @return
	 */
	private boolean checkU9SmNoIsExsit(String U9_SM_NO) {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append("select s.storeout_id from ERP_STOREOUT s where s.u9_sm_no = '"
						+ U9_SM_NO + "' and s.del_flag = 0");
		Map map = new HashMap();
		map.put("SelSQL", sqlBuf.toString());
		List smList = selcomList(map);
		if (smList.size() > 0) {
			return false;
		}
		return true;
	}

	/**
	 * 生成出库单
	 * 
	 * @param dtlList
	 */
	private void insetErpStoreOut(String STOREOUT_ID, String BillType,
			String deliverOrderNo, String SARE_ORDER_DTL_ID, String Ship_Org,
			String STOREOUT_TIME, String TRUCK_NO, String U9_SM_NO,
			ArrayList dtlList) {

		HashMap deliverMap = getDeliverData(deliverOrderNo, SARE_ORDER_DTL_ID);
		HashMap storeOutOrderMap = new HashMap();
		HashMap params = new HashMap();
		params.put("STOREOUT_ID", STOREOUT_ID);
		params.put("STOREOUT_NO", LogicUtil.getBmgz("ERP_STOREOUT_NO"));
		params.put("BILL_TYPE", BillType);
		params.put("DELIVER_ORDER_ID", deliverMap.get("DELIVER_ORDER_ID"));
		params.put("DELIVER_ORDER_NO", deliverOrderNo);
//		params.put("RECV_CHANN_ID", deliverMap.get("RECV_CHANN_ID"));
//		params.put("RECV_CHANN_NO", deliverMap.get("RECV_CHANN_NO"));
//		params.put("RECV_CHANN_NAME", deliverMap.get("RECV_CHANN_NAME"));
		params.put("ORDER_CHANN_ID", deliverMap.get("ORDER_CHANN_ID"));
		params.put("ORDER_CHANN_NO", deliverMap.get("ORDER_CHANN_NO"));
		params.put("ORDER_CHANN_NAME", deliverMap.get("ORDER_CHANN_NAME"));
		params.put("SEND_CHANN_ID", deliverMap.get("SEND_CHANN_ID"));
		params.put("SEND_CHANN_NO", deliverMap.get("SEND_CHANN_NO"));
		params.put("SEND_CHANN_NAME", deliverMap.get("SEND_CHANN_NAME"));		
		params.put("SHIP_POINT_ID", deliverMap.get("SHIP_POINT_ID"));
		params.put("SHIP_POINT_NAME", deliverMap.get("SHIP_POINT_NAME"));
		params.put("STOREOUT_TIME", STOREOUT_TIME);
		params.put("U9_SM_NO", U9_SM_NO);
		params.put("TRUCK_NO", TRUCK_NO);
		params.put("STATE", "已处理");
		params.put("REMARK", deliverMap.get("REMARK"));
		params.put("CREATOR", deliverMap.get("CREATOR"));
		params.put("CRE_NAME", "系统");
		params.put("DEPT_ID", deliverMap.get("DEPT_ID"));
		params.put("DEPT_NAME", deliverMap.get("DEPT_NAME"));
		params.put("ORG_ID", deliverMap.get("ORG_ID"));
		params.put("ORG_NAME", deliverMap.get("ORG_NAME"));
		params.put("LEDGER_ID", deliverMap.get("LEDGER_ID"));
//		params.put("RECV_ADDR_NO", deliverMap.get("RECV_ADDR_NO"));
//		params.put("RECV_ADDR", deliverMap.get("RECV_ADDR"));
		params.put("DEL_FLAG", "0");
		ArrayList StoreOutDtlList = new ArrayList();
		for (int i = 0; i < dtlList.size(); i++) {
			HashMap dtlMap = (HashMap) dtlList.get(i);
			String sare_order_dtl_id = null;
			String DELIVER_ORDER_DTL_ID = (String) dtlMap
					.get("DELIVER_ORDER_DTL_ID");
			String strUCode = (String) dtlMap.get("PLANDOCLINEID");
			if (!StringUtil.isEmpty(DELIVER_ORDER_DTL_ID)) {
//				sare_order_dtl_id = DELIVER_ORDER_DTL_ID;  //暂时用
				sare_order_dtl_id = getSaleOrderDtlId(strUCode, Ship_Org);
			} else {
				sare_order_dtl_id = getSaleOrderDtlId(strUCode, Ship_Org);
			}
			String REAL_STOREOUT_NUM = (String) dtlMap.get("REAL_STOREOUT_NUM");
			BigDecimal I_REAL_STOREOUT_NUM = new BigDecimal(REAL_STOREOUT_NUM);
			String U9_SM_DTL_ID = (String) dtlMap.get("SHIPLINE_ID");
			HashMap deliverDtlMap = getDeliverDtlData(deliverOrderNo, strUCode,Ship_Org,
					REAL_STOREOUT_NUM);
			HashMap storeOutDtlMap = new HashMap();
			storeOutDtlMap.put("STOREOUT_PRD_DTL_ID", StringUtil.uuid32len());
			storeOutDtlMap.put("STOREOUT_ID", STOREOUT_ID);
			storeOutDtlMap.put("PRD_ID", deliverDtlMap.get("PRD_ID"));
			storeOutDtlMap.put("PRD_NO", deliverDtlMap.get("PRD_NO"));
			storeOutDtlMap.put("PRD_NAME", deliverDtlMap.get("PRD_NAME"));
			storeOutDtlMap.put("PRD_SPEC", deliverDtlMap.get("PRD_SPEC"));
			storeOutDtlMap.put("PRD_COLOR", deliverDtlMap.get("PRD_COLOR"));
			storeOutDtlMap.put("BRAND", deliverDtlMap.get("BRAND"));
			storeOutDtlMap.put("STD_UNIT", deliverDtlMap.get("STD_UNIT"));
			storeOutDtlMap.put("STOREOUT_NUM", I_REAL_STOREOUT_NUM);
			storeOutDtlMap.put("PRICE", deliverDtlMap.get("PRICE"));
			storeOutDtlMap.put("DECT_RATE", deliverDtlMap.get("DECT_RATE"));
			storeOutDtlMap.put("DECT_PRICE", deliverDtlMap.get("DECT_PRICE"));
			storeOutDtlMap.put("DECT_AMOUNT", deliverDtlMap.get("DECT_AMOUNT"));
			storeOutDtlMap.put("VOLUMES", deliverDtlMap.get("VOLUMES"));
			storeOutDtlMap.put("SALE_ORDER_DTL_ID", sare_order_dtl_id);
			storeOutDtlMap.put("U9_SALE_ORDER_NO", deliverDtlMap
					.get("U9_SALE_ORDER_NO"));
			storeOutDtlMap.put("U9_SALE_ORDER_DTL_NO", deliverDtlMap
					.get("U9_SALE_ORDER_DTL_NO"));
			storeOutDtlMap.put("DEL_FLAG", "0");
			storeOutDtlMap.put("U9_SM_DTL_ID", U9_SM_DTL_ID);
			storeOutDtlMap.put("SPCL_TECH_FLAG",deliverDtlMap.get("SPCL_TECH_FLAG"));
			storeOutDtlMap.put("SPCL_TECH_ID", deliverDtlMap.get("SPCL_TECH_ID"));

			storeOutDtlMap.put("RECV_CHANN_ID", deliverDtlMap.get("RECV_CHANN_ID"));
			storeOutDtlMap.put("RECV_CHANN_NO", deliverDtlMap.get("RECV_CHANN_NO"));
			storeOutDtlMap.put("RECV_CHANN_NAME", deliverDtlMap.get("RECV_CHANN_NAME"));	
			storeOutDtlMap.put("RECV_ADDR_NO", deliverDtlMap.get("RECV_ADDR_NO"));
			storeOutDtlMap.put("RECV_ADDR", deliverDtlMap.get("RECV_ADDR"));
			Map saleMap = getSaleOrder(sare_order_dtl_id);
			storeOutDtlMap.put("SALE_ORDER_ID", saleMap.get("SALE_ORDER_ID"));
			storeOutDtlMap.put("SALE_ORDER_NO", saleMap.get("SALE_ORDER_NO"));
			StoreOutDtlList.add(storeOutDtlMap);
		}
		insert("ErpStoreout.insertErpStoreOut", params);
		this.batch4Update("ErpStoreout.insertErpStoreOutDtl", StoreOutDtlList);
	}

	/**
	 * 生成 入库通知单
	 */
	public boolean inserStoreinOrder(String STOREOUT_ID) {
		try {
			List erpStoreOutList =  this.findList("ErpStoreout.loadByStoreOutId",
					STOREOUT_ID);
			for(int rec=0;rec<erpStoreOutList.size();rec++){
				Map erpStoreOutMap = (Map)erpStoreOutList.get(rec);
				String U9SMNO =  (String)erpStoreOutMap.get("U9_SM_NO");
				String STOREIN_NOTICE_NO = U9SMNO+"-"+rec;
				Map erpParams = new HashMap();
				erpParams.put("STOREOUT_ID", STOREOUT_ID);
				String RECV_CHANN_ID = (String)erpStoreOutMap.get("RECV_CHANN_ID");
				String billType = "订货入库";
				HashMap baseParams = new HashMap();
				baseParams.put("CHANN_ID", RECV_CHANN_ID);
				Map channlMap = (Map) load("chann.loadById", baseParams);
	 			String AREA_SER_ID = (String)channlMap.get("AREA_SER_ID");
	 			String AREA_SER_NO =  null;
	 			String AREA_SER_NAME =  null;
	 			if(AREA_SER_ID!=null){
		 			 AREA_SER_NO =  (String)channlMap.get("AREA_SER_NO");
		 			 AREA_SER_NAME =  (String)channlMap.get("AREA_SER_NAME");
	 			}
				String STOREIN_NOTICE_ID = StringUtil.uuid32len();
				StringBuffer sqlBuf = new StringBuffer();
				sqlBuf
						.append(
								"insert into DRP_STOREIN_NOTICE(STOREIN_NOTICE_ID,STOREIN_NOTICE_NO,")
						.append(
								"BILL_TYPE,FROM_BILL_ID,FROM_BILL_NO,ORDER_CHANN_ID,ORDER_CHANN_NO,")
						.append(
								"ORDER_CHANN_NAME,RECV_CHANN_ID,RECV_CHANN_NO,RECV_CHANN_NAME,SEND_CHANN_ID,")
						.append("SEND_CHANN_NO,SEND_CHANN_NAME,")
						.append(
								"CRE_NAME,CREATOR,CRE_TIME,DEPT_ID,DEPT_NAME,ORG_ID,ORG_NAME,")
						.append("LEDGER_ID,LEDGER_NAME,STATE,DEL_FLAG")
						.append(" ) select distinct ")
						.append("'" + STOREIN_NOTICE_ID + "'")
						.append(",")
						.append(
								"'"+STOREIN_NOTICE_NO+"'")
						.append(",")
						.append("'" + billType + "'")
						.append(",")
						.append(
								"decode(t.DELIVER_ORDER_ID, null, '"+U9SMNO+"', t.DELIVER_ORDER_ID),decode(t.DELIVER_ORDER_NO, null, '"+U9SMNO+"', t.DELIVER_ORDER_NO),t.ORDER_CHANN_ID,")
						.append(
								"t.ORDER_CHANN_NO,t.ORDER_CHANN_NAME,d.RECV_CHANN_ID,")
						.append("d.RECV_CHANN_NO,d.RECV_CHANN_NAME,");
				if (AREA_SER_ID != null) {
					sqlBuf.append("'" + AREA_SER_ID + "','" + AREA_SER_NO + "','"
							+ AREA_SER_NAME + "'");
				} else {
					sqlBuf.append("t.SEND_CHANN_ID,t.SEND_CHANN_NO,t.SEND_CHANN_NAME");
				}
	
				sqlBuf
						.append(",'U9',d.RECV_CHANN_ID,")
						.append("sysdate")
						.append(",")
						.append(
								"d.RECV_CHANN_ID,d.RECV_CHANN_NAME,d.RECV_CHANN_ID,")
						.append(
								"d.RECV_CHANN_NAME,d.RECV_CHANN_ID,d.RECV_CHANN_NAME,")
						.append("'未提交',")
						.append(BusinessConsts.DEL_FLAG_COMMON)
						.append(
								" from ERP_STOREOUT t  ,ERP_STOREOUT_PRD_DTL d ")
						.append(" where t.STOREOUT_ID = d.STOREOUT_ID and t.STOREOUT_ID = '").append(STOREOUT_ID)
						.append("'").append(" and d.recv_chann_id = '").append(RECV_CHANN_ID).append("'");
	
						Map params = new HashMap();
						params.put("InsSQL", sqlBuf.toString());
						txInscom(params);
						
						Map dtlParams = new HashMap();
						dtlParams.put("STOREOUT_ID", STOREOUT_ID);
						dtlParams.put("RECV_CHANN_ID", RECV_CHANN_ID);
						List erpStoreOutDtlList =  this.findList("ErpStoreout.loadDtlByStoreOutId",
								dtlParams);
						for (int i = 0; i < erpStoreOutDtlList.size(); i++) {
							Map dtlMap = (HashMap) erpStoreOutDtlList.get(i);
							String STOREOUT_PRD_DTL_ID =  (String)dtlMap.get("STOREOUT_PRD_DTL_ID");
							StringBuffer dtlSqlBuf = new StringBuffer();
							dtlSqlBuf
									.append("insert into DRP_STOREIN_NOTICE_DTL(")
									.append(
											"STOREIN_NOTICE_DTL_ID,STOREIN_NOTICE_ID,")
									.append(
											"PRD_ID,PRD_NO,")
									.append(
											"PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,PRICE,")
									.append("DECT_RATE,DECT_PRICE,NOTICE_NUM,DECT_AMOUNT,")
									.append(
											"SALE_ORDER_ID,SALE_ORDER_NO,SALE_ORDER_DTL_ID,")
									.append("GOODS_ORDER_NO,SPCL_TECH_ID,DEL_FLAG,")
									.append(
											"TAX_RATE,NO_TAX_DECT_PRICE,NO_TAX_DECT_AMOUNT ,TAX_AMOUNT ")
									.append(")select rawtohex(sys_guid()) as UUID")
									.append(",'")
									.append(STOREIN_NOTICE_ID)
									.append("',")
									.append("d.PRD_ID,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,")
									.append("d.PRD_COLOR,d.BRAND,d.STD_UNIT,s.PRICE,")
									.append("s.DECT_RATE,d.DECT_PRICE,d.STOREOUT_NUM,")
									.append(
											"(d.STOREOUT_NUM*d.DECT_PRICE),t.SALE_ORDER_ID,")
									.append(
											"t.SALE_ORDER_NO,d.SALE_ORDER_DTL_ID,s.GOODS_ORDER_NO,")
									.append("s.SPCL_TECH_ID,")
									.append(BusinessConsts.DEL_FLAG_COMMON)
									.append(",c.TAX_RATE,")
									.append("round(d.DECT_PRICE/(1+c.TAX_RATE),2),")
									.append(
											"round( d.STOREOUT_NUM *(d.DECT_PRICE/(1+c.TAX_RATE)),2),")
									.append(
											"round( (d.STOREOUT_NUM * d.DECT_PRICE) - ( d.STOREOUT_NUM *(d.DECT_PRICE/(1+c.TAX_RATE))),2)")
									.append(" from ERP_STOREOUT_PRD_DTL d left join ERP_STOREOUT t  on d.STOREOUT_ID = t.STOREOUT_ID ")
									.append(
											" left join BASE_CHANN c on t.ORDER_CHANN_ID=c.CHANN_ID")
									.append(
											" left join ERP_SALE_ORDER_DTL s on s.SALE_ORDER_DTL_ID = d.SALE_ORDER_DTL_ID")
									.append(" where d.STOREOUT_PRD_DTL_ID = '").append(STOREOUT_PRD_DTL_ID)
									.append("'");
			
							Map storeIndtlParams = new HashMap();
							storeIndtlParams.put("InsSQL", dtlSqlBuf.toString());
							txInscom(storeIndtlParams);
						}
			}
			
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	/**
	 * 查询发运单主表
	 * 
	 * @param deliverOrderId
	 * @return
	 */
	private Map getDeliverOrder(String deliverOrderId) {
		StringBuffer sqlBuf = new StringBuffer();
		sqlBuf
				.append(
						"select u.DELIVER_ORDER_ID,u.DELIVER_ORDER_ID,u.DELIVER_ORDER_NO,")
				.append(
						"u.DELIVER_TYPE,u.DELIVER_TYPE,u.BILL_TYPE,u.TRUCK_TYPE,u.CHANN_TYPE,u.PRVD_ID,u.PRVD_NO,")
				.append(
						"u.PRVD_NAME,u.AREA_SER_ID,u.AREA_SER_NO,u.AREA_SER_NAME,u.SEND_CHANN_ID,u.RECV_CHANN_ID,u.RECV_CHANN_NO,u.RECV_CHANN_NAME,")
				.append(
						"u.SEND_CHANN_NO,u.ADVC_SEND_DATE,u.TOTAL_VOLUME,u.STATE,u.REMARK,u.SHIP_POINT_ID,")
				.append(
						"u.SHIP_POINT_NAME,to_char(u.APPCH_TIME,'yyyy-MM-DD HH24:MI:SS') APPCH_TIME,")
				.append(
						"u.CRE_NAME,u.CRE_NAME,u.CREATOR,to_char(u.CRE_TIME,'yyyy-MM-DD HH24:MI:SS') CRE_TIME,")
				.append(
						"u.UPD_NAME,u.UPDATOR,to_char(u.UPD_TIME,'yyyy-MM-DD HH24:MI:SS') UPD_TIME,u.DEPT_ID,")
				.append(
						"u.DEPT_NAME,u.ORG_ID,u.ORG_NAME,u.LEDGER_ID,u.LEDGER_NAME,u.DEL_FLAG,u.BILL_TYPE")
				.append(
						" from ERP_DELIVER_ORDER u where u.DELIVER_ORDER_NO = '")
				.append(deliverOrderId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", sqlBuf.toString());
		return selcom(params);
	}

	private HashMap getDeliverData(String deliverOrderNo,
			String SARE_ORDER_DTL_ID) {
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf
				.append("SELECT t.SEND_CHANN_ID,")
				.append(
						"  (select tt.DELIVER_ORDER_ID FROM ERP_DELIVER_ORDER tt where tt.DELIVER_ORDER_NO = '"
								+ deliverOrderNo + "' ) DELIVER_ORDER_ID,")
				.append(" t.SEND_CHANN_NO,").append("t.SEND_CHANN_NAME,")
				.append(" t.RECV_CHANN_ID,").append(" t.RECV_CHANN_NO,")
				.append(" t.RECV_CHANN_NAME,").append("  t.ORDER_CHANN_ID,")
				.append(" t.ORDER_CHANN_NO,").append(" t.ORDER_CHANN_NAME,")
				.append(" t.REMARK,").append(" t.CREATOR,").append(
						" t.DEPT_ID,").append(" t.DEPT_NAME,").append(
						" t.ORG_ID,").append(" t.ORG_NAME,").append(
						" t.LEDGER_ID,").append(" t.RECV_ADDR_NO,").append(
						" t.RECV_ADDR,")
				.append("t.SHIP_POINT_ID, t.SHIP_POINT_NAME")
				.append(
						"  FROM ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL d")
				.append(" WHERE t.sale_order_id = d.sale_order_id").append(
						" and d.sale_order_dtl_id = '" + SARE_ORDER_DTL_ID
								+ "'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = selcom(params);

		return (HashMap) deliverMap;
	}

	private HashMap getDeliverDtlData(String deliverOrderNo, String strUCode,String Ship_Org,
			String REAL_STOREOUT_NUM) {
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf
				.append(
						"SELECT (SELECT tt.deliver_order_id FROM ERP_DELIVER_ORDER tt where tt.deliver_order_no = '"
								+ deliverOrderNo + "') DELIVER_ORDER_ID ,")
				.append(
						"(SELECT dd.deliver_order_dtl_id FROM ERP_DELIVER_ORDER tt,ERP_DELIVER_ORDER_DTL dd where tt.deliver_order_id= dd.deliver_order_id and  tt.DELIVER_ORDER_NO = '"
								+ deliverOrderNo
								+ "' and dd.SALE_ORDER_DTL_ID = '"
								+ strUCode
								+ "' and dd.del_flag =0 )DELIVER_ORDER_DTL_ID,")
				.append(" t.SALE_ORDER_ID,")
				.append(" t.SALE_ORDER_NO,")
				.append(" d.SALE_ORDER_DTL_ID,")
				.append(" t.RECV_CHANN_ID,")
				.append(" t.RECV_CHANN_NO,")
				.append(" t.RECV_CHANN_NAME,")
				.append(" t.RECV_ADDR_NO,")
				.append(" t.RECV_ADDR,")
				.append(" d.PRD_ID,")
				.append(
						" d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,d.PRD_COLOR,d.BRAND,d.STD_UNIT,d.SPCL_TECH_FLAG,d.SPCL_TECH_ID,")
				.append(REAL_STOREOUT_NUM).append(" REAL_NUM,").append(
						"  d.PRICE,").append(" d.DECT_RATE,").append(
						" d.DECT_PRICE,").append(
						"  NVL(d.DECT_PRICE, 0) * NVL(" + REAL_STOREOUT_NUM
								+ ", 0) DECT_AMOUNT,").append(
						"  NVL(d.VOLUME, 0) * NVL(" + REAL_STOREOUT_NUM
								+ ", 0) VOLUMES,").append(
						" d.U9_SALE_ORDER_NO,").append(
						" d.U9_SALE_ORDER_DTL_NO").append(
						" FROM ERP_SALE_ORDER t ,ERP_SALE_ORDER_DTL d").append(
						" WHERE t.sale_order_id = d.sale_order_id  ")
						.append(" and d.del_flag = 0 and t.del_flag = 0 and t.ship_point_id = '").append(Ship_Org).append("'")
						.append(
						" and d.u_code = '" + strUCode + "'");

		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = selcom(params);
		return (HashMap) deliverMap;
	}

	private String getSaleOrderDtlId(String strUCode, String Ship_Org) {
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf.append("select d.SALE_ORDER_DTL_ID ").append(
				" from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL d").append(
				" where  d.del_flag =0 and t.del_flag=0 and t.sale_order_id = d.sale_order_id").append(
				" and d.u_code = '" + strUCode + "'").append(
				"  and t.ship_point_id = '" + Ship_Org + "'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map saleOrderMap = selcom(params);
		String SALE_ORDER_DTL_ID = (String) saleOrderMap
				.get("SALE_ORDER_DTL_ID");
		return SALE_ORDER_DTL_ID;
	}
	
	private Map getSaleOrder(String SALE_ORDER_DTL_ID){
		StringBuffer saleOrder = new StringBuffer();
		saleOrder.append("select t.SALE_ORDER_ID, t.SALE_ORDER_NO")
		.append(" from erp_sale_order t, erp_sale_order_dtl d")
		.append("  where d.del_flag =0 and t.del_flag=0 and t.sale_order_id = d.sale_order_id")
		.append(" and d.sale_order_dtl_id = '")
		.append(SALE_ORDER_DTL_ID)
		.append("'");
		Map params = new HashMap();
		params.put("SelSQL", saleOrder.toString());
		Map saleOrderMap = selcom(params);
		return saleOrderMap;
	}

	/**
	 * 更新ERP_STOREOUT_DTL表的货品信息
	 * 
	 * @param storeOutList
	 * @return
	 * @throws Exception
	 */
	private boolean instErpSN(String STOREOUT_ID, String deliverOrderNo,
			String Ship_Org, String uCode, String strPrdSns)
			throws ServiceException {
		strPrdSns = DecompressSnList(strPrdSns);
		boolean flg = true;
		String[] pdSnls = strPrdSns.split(",");
		for (int i = 0; i < pdSnls.length; i++) {
			HashMap params = new HashMap();
			String prdSn = pdSnls[i];
			StringBuffer sqlBuf = new StringBuffer();
			sqlBuf
					.append(
							" INSERT INTO ERP_STOREOUT_DTL (STOREOUT_DTL_ID,STOREOUT_ID,DELIVER_ORDER_ID,DELIVER_ORDER_DTL_ID,PRD_ID,PRD_NO,PRD_NAME,PRD_SPEC,PRD_COLOR,BRAND,STD_UNIT,REAL_NUM,PRICE,DECT_RATE,DECT_PRICE,DECT_AMOUNT,YEAR_MONTH,SN) SELECT  ")
					.append(
							" sys_guid(),'"
									+ STOREOUT_ID
									+ "',(SELECT tt.deliver_order_id FROM ERP_DELIVER_ORDER tt where tt.deliver_order_no = '"
									+ deliverOrderNo + "') DELIVER_ORDER_ID ,")
					.append(
							"  (SELECT dd.deliver_order_dtl_id FROM ERP_DELIVER_ORDER tt,ERP_DELIVER_ORDER_DTL dd where tt.deliver_order_id= dd.deliver_order_id and dd.del_flag = 0 and   tt.DELIVER_ORDER_NO = '"
									+ deliverOrderNo
									+ "' and dd.SALE_ORDER_DTL_ID = '"
									+ uCode
									+ "')DELIVER_ORDER_DTL_ID,")
					.append(
							"d.PRD_ID,d.PRD_NO,d.PRD_NAME,d.PRD_SPEC,d.PRD_COLOR,BRAND,d.STD_UNIT,d.SENDED_NUM,0,0,d.DECT_PRICE,0, to_char(sysdate,'YYYY/MM'),'")
					.append(prdSn)
					.append(
							"'   FROM ERP_SALE_ORDER t ,ERP_SALE_ORDER_DTL d WHERE  t.SALE_ORDER_ID = d.SALE_ORDER_ID and  d.SALE_ORDER_DTL_ID = '")
					.append(uCode).append("'").append(
							" and t.ship_point_id = '" + Ship_Org + "' ");
			params.put("InsSQL", sqlBuf.toString());
			flg = txInscom(params);
			if (!flg) {
				return flg;
			}
		}
		return flg;
	}

	public static String DecompressSnList(String snList) {
		if (StringUtil.isEmpty(snList)) {
			return "";
		} else {
			StringBuilder sb = new StringBuilder();
			String[] snArray = snList.split(";");
			for (int i = 0; i < snArray.length; i += 2) {
				String prefix = snArray[i]; // 前缀
				String[] snTails = snArray[i + 1].split(","); // 后缀清单
				for (String snt : snTails) {
					sb.append(prefix).append(snt).append(',');
				}
			}
			// 移除尾巴的","。
			if (sb.length() > 0) {
				sb.substring(0, sb.length() - 1);
			}
			return sb.toString();
		}
	}

	/**
	 * 创建出货单按行 为了(货品发运的关闭)
	 * 
	 * @param BusinessId
	 * @return
	 */
	public String createShipPlanByClose(String BusinessId,
			String DELIVER_ORDER_DTL_IDS, String InterfaceCode,
			String ServiceCode, String Operation, String AppCode,
			String DestCode, String UId, String OPRCODE) {
		LinkedHashMap headMap = new LinkedHashMap();
		headMap.put("InterfaceCode", InterfaceCode);
		headMap.put("ServiceCode", ServiceCode);
		headMap.put("Operation", Operation);
		headMap.put("AppCode", AppCode);
		headMap.put("DestCode", DestCode);
		headMap.put("TransType", "1");
		headMap.put("UId", UId);
		LinkedHashMap bodyMap = new LinkedHashMap();
		StringBuffer deliverBuf = new StringBuffer();
		deliverBuf
				.append(
						"SELECT '-1' Direction,t.DELIVER_ORDER_ID as ShipPlanID,t.DELIVER_ORDER_NO as ShipPlanDocNo,to_char(t.APPCH_TIME,'yyyy-mm-dd') as ShipPlanDate,t.DELIVER_ORDER_NO as ShipPlanNO,(select p.SHIP_POINT_NO from BASE_SHIP_POINT p where p.SHIP_POINT_ID = t.SHIP_POINT_ID) as OrgCode ,REMARK as MEMO FROM ERP_DELIVER_ORDER t WHERE t.DELIVER_ORDER_ID = '")
				.append(BusinessId).append("'");
		Map params = new HashMap();
		params.put("SelSQL", deliverBuf.toString());
		Map deliverMap = selcom(params);
		StringBuffer deliverDtlBuf = new StringBuffer();
		deliverDtlBuf
				.append(
						"SELECT d.ORDER_CHANN_NO as CustomerCode,d.RECV_ADDR_NO as CustomerSiteCode,1 Direction,d.SALE_ORDER_DTL_ID as DeliverPlanLineID,d.U_CODE as PlanDocLineID,d.PRD_NO as ItemMasterCode, (select SPCL_TECH_NO FROM DRP_SPCL_TECH s WHERE s.spcl_tech_id =d.spcl_tech_id) SpeTech_No ,d.PLAN_NUM as ShipQty,0 as ShipType,d.DECT_PRICE as Price,d.REMARK as Memo FROM ERP_DELIVER_ORDER_DTL d WHERE d.DELIVER_ORDER_ID = '")
				.append(BusinessId).append(
						"'   and d.DEL_FLAG =0 and DELIVER_ORDER_DTL_ID in ("
								+ DELIVER_ORDER_DTL_IDS + ")");

		params = new HashMap();
		params.put("SelSQL", deliverDtlBuf.toString());
		List deliverDtlList = selcomList(params);
		bodyMap.putAll(deliverMap);
		bodyMap.put("DETAIL", deliverDtlList);
		String strJsonData = JesonImplUtil.getImplJson(headMap, bodyMap);
		return strJsonData;
	}

	/**
	 * 查询个人信息
	 */
	public Map selcom(Map params) {
		return (Map) load("sqlcom.query", params);
	}

	/**
	 * 获取列表信息
	 * 
	 * @param params
	 * @return
	 */
	public List selcomList(Map params) {
		return this.findList("sqlcom.query", params);
	}

	public boolean txUpdcom(Map params) {
		return update("sqlcom.update", params);
	}

	public boolean txInscom(Map params) {
		insert("sqlcom.insert", params);
		return true;
	}

	private String getErrorInfo(Exception ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		String errorInfo = ex.toString();
		if (errorInfo.length() > 2900) {
			errorInfo = errorInfo.substring(0, 2900);
		}
		return sw.toString();
	}
}
