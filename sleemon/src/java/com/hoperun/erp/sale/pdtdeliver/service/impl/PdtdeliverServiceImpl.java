package com.hoperun.erp.sale.pdtdeliver.service.impl;

import java.io.BufferedReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.queryProStatus.model.ProStatus;
import com.hoperun.erp.sale.pdtdeliver.service.PdtdeliverService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public class PdtdeliverServiceImpl extends BaseService implements
		PdtdeliverService {

	/**
	 * 查询并分页.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Pdtdeliver.pageQuery", "Pdtdeliver.pageCount",
				params, pageNo);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param DELIVER_ORDER_ID
	 *            主表ID
	 * 
	 * @return the list
	 */
	public List<TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Pdtdeliver.queryChldByFkId", params);
	}
	
	/**
	 * * 根据主表Id查询子表记录
	 * @param  params
	 * @return the list
	 */
	public List<TurnoverplanChildModel> childQuery(Map<String, String> params) {
		return this.findList("Pdtdeliver.queryChldByFkId", params);
	}
	
    /**
     * * 根据主表Id查询子表记录
     * @param params
     * @return the list
     */
    public List <TurnoverplanChildModel> childQueryByParentIDS(Map<String, String> params){
    	return this.findList("Pdtdeliver.queryChldByFkIds", params);
    }
    

	/**
	 * @主表详细页面
	 * @param param
	 *            DELIVER_ORDER_ID
	 */
	public Map<String, String> load(String DELIVER_ORDER_ID) {
		return (Map<String, String>) this.load("Pdtdeliver.loadById",
				DELIVER_ORDER_ID);
	}

	/**
	 * 编辑：新增
	 * 
	 * @param model
	 *            发运单
	 * @return the string
	 */
	public void txEdit(TurnoverplanModel model, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", model.getDELIVER_ORDER_ID());
		params.put("CHANN_TYPE", model.getCHANN_TYPE());
		params.put("PRVD_ID", model.getPRVD_ID());
		params.put("PRVD_NO", model.getPRVD_NO());
		params.put("PRVD_NAME", model.getPRVD_NAME());
		params.put("AREA_SER_ID", model.getAREA_SER_ID());
		params.put("AREA_SER_NO", model.getAREA_SER_NO());
		params.put("AREA_SER_NAME", model.getAREA_SER_NAME());
		params.put("SEND_CHANN_ID", model.getSEND_CHANN_ID());
		params.put("SEND_CHANN_NO", model.getSEND_CHANN_NO());
		params.put("SEND_CHANN_NAME", model.getSEND_CHANN_NAME());
		params.put("ADVC_SEND_DATE", model.getADVC_SEND_DATE());
		params.put("REMARK", model.getREMARK());
		params.put("APPCH_TIME", model.getAPPCH_TIME());
		params.put("UPDATOR", userBean.getXTYHID());// 更新人id
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		
		//如果没有合并的发运单 默认是自己
        String JOIN_DELIVER_ORDER_NO = model.getJOIN_DELIVER_ORDER_NO();
        if(StringUtil.isEmpty(JOIN_DELIVER_ORDER_NO)){
//        	JOIN_DELIVER_ORDER_NO = model.getDELIVER_ORDER_NO();
        	//modify by zzb 生成新的出货计划号
        	JOIN_DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_JOIN_DELIVER_ORDER_NO");
        }

        params.put("JOIN_DELIVER_ORDER_NO", JOIN_DELIVER_ORDER_NO);
		this.update("Pdtdeliver.updateById", params);

	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param GOODS_ORDER_DTL_IDs
	 *            the GOODS_ORDER_DTL_IDs
	 * 
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<TurnoverplanChildModel> loadChilds(Map<String, String> params) {
		return this.findList("Pdtdeliver.queryChldByFkId", params);
	}

	/**
	 * 提交库房
	 * @param DELIVER_ORDER_ID 发运单ID
	 * @param childList 发运单明细
	 * @param userBean
	 */
	public Map<String,Object> txCommitStore(String DELIVER_ORDER_ID_OLD,
			Map<String, String> dliverMap, String strJsonData,
			List<TurnoverplanChildModel> childList, UserBean userBean)
			throws Exception {
		String strMessage = "操作成功";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("DELIVER_ORDER_ID_OLD", DELIVER_ORDER_ID_OLD);
		params.put("CRE_NAME", userBean.getXM());
		params.put("CREATOR", userBean.getXTYHID());
		params.put("DEPT_ID", userBean.getBMXXID());
		params.put("DEPT_NAME", userBean.getBMMC());
		params.put("ORG_ID", userBean.getJGXXID());
		params.put("ORG_NAME", userBean.getJGMC());
		params.put("LEDGER_ID", userBean.getLoginZTXXID());
		params.put("LEDGER_NAME", userBean.getLoginZTMC());
		params.put("STATE", BusinessConsts.UNCOMMIT);// 未提交
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		List<Map<String, Object>> addChildList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> updateSaleDtlList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> updateGoodDtlList = new ArrayList<Map<String, Object>>();

		Map<String, String> tempMap = new HashMap<String, String>();
		Set<String> deliveIdSet = new HashSet<String>();
		Set<String> deliveNoSet = new HashSet<String>();
		
		if (null != childList) {
			for (TurnoverplanChildModel child : childList) {
				Map<String, Object> map = new HashMap<String, Object>();
				String NO_SEND_DEAL_TYPE = child.getNO_SEND_DEAL_TYPE();
				String deliverOrderDtlId = child.getDELIVER_ORDER_DTL_ID();// 明细ID
				map.put("PLAN_NUM", child.getPLAN_NUM());
				map.put("NO_SEND_NUM", child.getNO_SEND_NUM());
				map.put("NO_SEND_DEAL_TYPE", NO_SEND_DEAL_TYPE);
				map.put("DELIVER_ORDER_DTL_ID", deliverOrderDtlId);
				map.put("REMARK", child.getREMARK());// 备注 add by zzb 2014-7-10
				update("Pdtdeliver.updateDeliverOrderDtl", map);// 更新发运单明细的
																// 未排货品处理方式
				map.clear();
				if (!child.getADVC_PLAN_NUM().equals(child.getPLAN_NUM())) {
					if ("纳入下次交付计划".equals(NO_SEND_DEAL_TYPE)) {
						// 回填 销售订单明细 计算已经生成的 发运单明细的排车数量之和
						map.put("DELIVER_ORDER_DTL_ID", child.getDELIVER_ORDER_DTL_ID());
						map.put("SALE_ORDER_DTL_ID", child.getSALE_ORDER_DTL_ID());
						map.put("NO_SEND_NUM", child.getNO_SEND_NUM());// 剩余货品
						updateSaleDtlList.add(map);
					} else {
						map.clear();
						// 不同的发运方式 产生不同的 发运单 无论 多少明细 最多只会生成两种不同的单据 ‘托运’ 或者
						// ‘整车发运’
						String DELIVER_ORDER_ID = null;
						String DELIVER_ORDER_NO = null;
						// 新生成的发运单 单据为‘已提交生产’ modify by zzb(cj) 2014-6-26
						params.put("STATE", BusinessConsts.COMMIT_FACTORY);// 已提交生产

						if ("托运".equals(NO_SEND_DEAL_TYPE)) {
							String idOne = tempMap.get("idOne");
							if (StringUtil.isEmpty(idOne)) {
								DELIVER_ORDER_ID = StringUtil.uuid32len();
								DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
								params.put("DELIVER_ORDER_ID",DELIVER_ORDER_ID);
								params.put("DELIVER_ORDER_NO",DELIVER_ORDER_NO);
								params.put("DELIVER_TYPE", "托运");
								tempMap.put("idOne", DELIVER_ORDER_ID);
								tempMap.put("noOne", DELIVER_ORDER_NO);
								insert("Pdtdeliver.newTurnoverplan", params);
								deliveIdSet.add(DELIVER_ORDER_ID);//将发运单id放入set后面用
								deliveNoSet.add(DELIVER_ORDER_NO);//将发运单no放入set后面用
							} else {
								DELIVER_ORDER_ID = tempMap.get("idOne");
								DELIVER_ORDER_NO = tempMap.get("noOne");
							}
							
//							 
							
						}else if("自提".equals(NO_SEND_DEAL_TYPE)){
							String idZiTi = tempMap.get("idZiTi");
							if (StringUtil.isEmpty(idZiTi)) {
								DELIVER_ORDER_ID = StringUtil.uuid32len();
								DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
								params.put("DELIVER_ORDER_ID",DELIVER_ORDER_ID);
								params.put("DELIVER_ORDER_NO",DELIVER_ORDER_NO);
								params.put("DELIVER_TYPE", "自提");
								tempMap.put("idZiTi", DELIVER_ORDER_ID);
								tempMap.put("noZiTi", DELIVER_ORDER_NO);
								insert("Pdtdeliver.newTurnoverplan", params);
								deliveIdSet.add(DELIVER_ORDER_ID);//将发运单id放入set后面用
								deliveNoSet.add(DELIVER_ORDER_NO);//将发运单no放入set后面用
							} else {
								DELIVER_ORDER_ID = tempMap.get("idZiTi");
								DELIVER_ORDER_NO = tempMap.get("noZiTi");
							}
							
							
						} else {
							String idOther = tempMap.get("idOther");
							if (StringUtil.isEmpty(idOther)) {
								DELIVER_ORDER_ID = StringUtil.uuid32len();
								DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
								params.put("DELIVER_ORDER_ID",DELIVER_ORDER_ID);
								params.put("DELIVER_ORDER_NO",DELIVER_ORDER_NO);
								params.put("DELIVER_TYPE", "整车发运");
								tempMap.put("idOther", DELIVER_ORDER_ID);
								tempMap.put("noOther", DELIVER_ORDER_NO);
								insert("Pdtdeliver.newTurnoverplan", params);
								deliveIdSet.add(DELIVER_ORDER_ID);//将发运单id放入set后面用
								deliveNoSet.add(DELIVER_ORDER_NO);//将发运单no放入set后面用
							} else {
								DELIVER_ORDER_ID = tempMap.get("idOther");
								DELIVER_ORDER_NO = tempMap.get("noOther");
							}
						}
						map.put("DELIVER_ORDER_ID_OLD", DELIVER_ORDER_ID_OLD);
						map.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
						map.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
						map.put("DELIVER_ORDER_DTL_ID", StringUtil.uuid32len());
						map.put("DELIVER_ORDER_DTL_ID_OLD", child.getDELIVER_ORDER_DTL_ID());
						// 产生新的发运单
//						Integer ADVC_PLAN_NUM = StringUtil.getInteger(child.getADVC_PLAN_NUM());
//						Integer PLAN_NUM = StringUtil.getInteger(child.getPLAN_NUM());
						Double ADVC_PLAN_NUM = StringUtil.getDouble(child.getADVC_PLAN_NUM());
						Double PLAN_NUM = StringUtil.getDouble(child.getPLAN_NUM());
						map.put("ADVC_PLAN_NUM", ADVC_PLAN_NUM - PLAN_NUM);
						map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
						// 如U_CODE 存在了不要再查了
						String U_CODE = getU9Code(child.getDELIVER_ORDER_DTL_ID());
						if (U_CODE == null || U_CODE.equals("")) {
							U_CODE = child.getDELIVER_ORDER_DTL_ID();
						}
						map.put("U_CODE", U_CODE);
						addChildList.add(map);
					}
				}
				Map<String, Object> saleDtlMap = new HashMap<String, Object>();
				saleDtlMap.put("SALE_ORDER_DTL_ID", child.getSALE_ORDER_DTL_ID());// 销售订单明细ID
				updateGoodDtlList.add(saleDtlMap);// 更新订货订单明细的‘已发数量’
			}

		}

		// 更新销售订单明细的 排车数量 此处为 计划排车数量 拿销售订单明细的当前排车数量-这次处理的数量 PLAN_NUM
		if (!updateSaleDtlList.isEmpty()) {
			this.batch4Update("Pdtdeliver.updateSaleOrderPlanNumDtl",updateSaleDtlList);
		}
        //插入发运单明细
		if (!addChildList.isEmpty()) {
			this.batch4Update("Pdtdeliver.insertDeliverOrderDtl", addChildList);
		}
		
		//更新新增的主表的总体积 add by zzb 2017-07-19 
		for(String id : deliveIdSet){
			params.clear();
		    params.put("DELIVER_ORDER_ID", id);
		    this.update("Pdtdeliver.updateDeliveTotalVolum", params);
		}
		
//		String idOther = tempMap.get("idOther");
//		String idOne = tempMap.get("idOne");
//		  
//		if(!StringUtil.isEmpty(idOne)){
//			params.clear();
//			params.put("DELIVER_ORDER_ID", idOne);
//			this.update("Pdtdeliver.updateDeliveTotalVolum", params);
//		}
//		if(!StringUtil.isEmpty(idOther)){
//			params.clear();
//			params.put("DELIVER_ORDER_ID", idOther);
//			this.update("Pdtdeliver.updateDeliveTotalVolum", params);
//		}
		
		
		// 更新订货订单明细的‘已发数量’
		if (!updateGoodDtlList.isEmpty()) {
			this.batch4Update("Pdtdeliver.updateGoodserOrderDtl",updateGoodDtlList);
		}

		params.clear();
		String state = "已提交库房";
		if(LogicUtil.checkDeliverDtlStatById(DELIVER_ORDER_ID_OLD)){ //如果发运单明细计划发货全是0的话,直接更新主表状态为已发货.
			state = "已发货";
		}
		
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID_OLD);
		params.put("STATE", state);// modify zzb 2013-7-16 之前版本的状态是‘已完成交付’
		params.put("UPDATOR", userBean.getXTYHID());// 更新人id
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		params.put("TOTAL_VOLUME_FLAG", "TOTAL_VOLUME_FLAG");// 更新总体积
		
		this.update("Pdtdeliver.updateById", params);

		// //如果 销售订单所有明细的 订货数量=已排车数量 那么该销售订单状态为‘已完成’
		// StringBuffer ids = new StringBuffer("");
		// if(!saleIdSet.isEmpty()){
		// for(String id : saleIdSet){
		// params.clear();
		// params.put("SALE_ORDER_ID", id);
		// //查询该销售订单所有明细是否全部已排车
		// int res = this.queryForInt("Pdtdeliver.querySaleDtl", params);
		// if(res == 0){
		// ids.append("'");
		// ids.append(id);
		// ids.append("',");
		// }
		// }
		// if(!StringUtil.isEmpty(ids.toString())){
		// String SALE_ORDER_IDS = ids.substring(0,ids.length()-1).toString();
		// params.put("SALE_ORDER_IDS",SALE_ORDER_IDS);
		// params.put("STATE", "已完成");
		// params.put("UPDATOR", userBean.getXTYHID());//更新人id
		// params.put("UPD_NAME", userBean.getXM());//更新人名称
		// params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
		//        		
		// this.update("Pdtdeliver.updateSaleOrder", params);
		// }
		// }
		//如果冻结了,要选解冻 再扣信用
		boolean isFreezeFlg = checkIsAllFreezeFlg(DELIVER_ORDER_ID_OLD,"0");
		if(isFreezeFlg){
			 LogicUtil.txDeliverOrderunFreeze(DELIVER_ORDER_ID_OLD);
		}
		for (TurnoverplanChildModel child : childList) {
			String deliverOrderDtlId = child.getDELIVER_ORDER_DTL_ID();
			LogicUtil.updatePdtDeliverFree(deliverOrderDtlId,DELIVER_ORDER_ID_OLD);
		}
		String strResult = null;
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {
			try {
				strResult = LogicUtil.createShipPlan(strJsonData);
			} catch (Exception ex) {
				throw new ServiceException("调用ESB接口失败!");
			}

			Map<String, String> resMap = new Gson().fromJson(strResult,
					new TypeToken<Map<String, String>>() {
					}.getType());
			if (resMap != null) {
				String stats = resMap.get("IsOK");
				if ("0".equals(stats)) {
					StringBuffer buf = new StringBuffer();
					buf.append("操作失败!");
					String Msg = resMap.get("Msg");
					buf.append("错误信息:" + Msg);
					throw new ServiceException(buf.toString());
				}
			} else {
				strMessage = "调用接口失败,查看LOG日志";
				throw new ServiceException(strMessage);
			}
		}

		// 测试U9回填实发数
//		if (!"true".equals(Consts.INVOKE_SYS_FLG)) {
//			U9TestPdtDeliver(dliverMap, childList);
//		}
		Map<String,Object> returnMap = new HashMap<String, Object>(); 
		returnMap.put("strMessage", strMessage);
		returnMap.put("deliveNoSet", deliveNoSet);
		return returnMap;
	}

	public void U9TestPdtDeliver(Map<String, String> dliverMap,
			List<TurnoverplanChildModel> childList) {
		String DELIVER_ORDER_ID = dliverMap.get("DELIVER_ORDER_ID");
		String DELIVER_ORDER_NO = dliverMap.get("DELIVER_ORDER_NO");
		try {
			HashMap inputMap = new HashMap();
			HashMap headMap = new HashMap();
			headMap.put("ServiceCode", "OrderManage");
			headMap.put("Operation", "CreateOrder");
			headMap.put("AppCode", "erp");
			headMap.put("UId", StringUtil.uuid32len());
			headMap.put("AuthId", "erp;password");
			ArrayList headList = new ArrayList();
			headList.add(headMap);
			inputMap.put("MbfHeader", headList);
			HashMap deliverMap = new HashMap();

			ArrayList dtlList = new ArrayList();
			ArrayList pdSnList = new ArrayList();

			List result = childQuery(DELIVER_ORDER_ID);
			for (int i = 0; i < result.size(); i++) {
				Map dtlMap = (Map) result.get(i);
				Map<String, Object> map = new HashMap<String, Object>();
				String saleOrderNo = (String) dtlMap.get("SALE_ORDER_NO");
				String pdNo = (String) dtlMap.get("PRD_NO");
				Map<String, String> params = new HashMap<String, String>();
				params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
				params.put("SALE_ORDER_NO", saleOrderNo);
				params.put("PRD_NO", pdNo);

				map.put("DELIVER_ORDER_DTL_ID", (String) dtlMap
						.get("DELIVER_ORDER_DTL_ID"));
				BigDecimal PLAN_NUM = (BigDecimal) dtlMap.get("PLAN_NUM");
				int iplanNum = PLAN_NUM.intValue();
				map.put("REAL_STOREOUT_NUM", PLAN_NUM);
				StringBuffer prdSnbuf = new StringBuffer();
				for (int j = 0; j < iplanNum; j++) {
					prdSnbuf.append(pdNo + j);
					if (iplanNum - 1 > j) {
						prdSnbuf.append(",");
					}
				}
				map.put("PRD_SN", prdSnbuf.toString());
				dtlList.add(map);
			}
			deliverMap.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
			deliverMap.put("DETAIL", dtlList);
			ArrayList bodyList = new ArrayList();
			bodyList.add(deliverMap);
			inputMap.put("MbfBody", bodyList);

			HashMap serMap = new HashMap();
			ArrayList iputList = new ArrayList();
			iputList.add(inputMap);
			serMap.put("input1", iputList);

			HashMap jsonMap = new HashMap();
			ArrayList serList = new ArrayList();
			serList.add(serMap);
			jsonMap.put("MbfService", serList);
			String jsonDeliver = new Gson().toJson(jsonMap);
			String msg = LogicUtil.updateDeliverOrder(Consts.DM_USERNAME,
					Consts.DM_PASSWORD, jsonDeliver);
			String jsonStr = LogicUtil.reutrnEsbInfo(msg);
			if (jsonStr.contains("false")) {
				throw new ServiceException(jsonStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("回填实发数量失败!");
		}
	}

	private String getU9Code(String DELIVER_ORDER_DTL_ID) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_DTL_ID", DELIVER_ORDER_DTL_ID);
		Map u9CodMap = (Map) this.load("Pdtdeliver.queryU9CODE", params);
		return (String) u9CodMap.get("U_CODE");
	}

	/**
	 * 修改明细
	 * 
	 * @param childList
	 */
	public void txUpdatChild(List<TurnoverplanChildModel> childList) {
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		for (TurnoverplanChildModel child : childList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("DELIVER_ORDER_DTL_ID", child.getDELIVER_ORDER_DTL_ID());
			map.put("PLAN_NUM", child.getPLAN_NUM());
			updateList.add(map);
		}

		if (!updateList.isEmpty()) {
			this.batch4Update("Pdtdeliver.updateChildById", updateList);
		}
	}

    /**
     * 修改明细 修改
     * 计划发运数量|备注|剩余货品处理方式
     * @param params 
     */
    public void txUpdatChild(Map<String,Object>params){
		update("Pdtdeliver.updateChildById", params);
	}

	
	/**
     * 修改明细
     * @param DELIVER_ORDER_DTL_ID 明细ID
     * @param REMARK 备注
     * @param NO_SEND_DEAL_TYPE 剩余货品处理方式
     */
	public void txUpdatChild(String DELIVER_ORDER_DTL_ID,String REMARK,String NO_SEND_DEAL_TYPE) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("DELIVER_ORDER_DTL_ID", DELIVER_ORDER_DTL_ID);
		map.put("REMARK", REMARK);
		map.put("NO_SEND_DEAL_TYPE", NO_SEND_DEAL_TYPE);
		update("Pdtdeliver.updateChildById", map);
	}
	
	public Map<String, Object> loadChann(String CHANN_ID) {
		// 这里的可用信用=渠道的当前信用+有效的零时信用-渠道表的冻结的信用
		return (Map<String, Object>) load("Pdtdeliver.getChannCredit", CHANN_ID);
	}
	

	/**
	 * 查询发运单对应的订货订单的制单人
	 * 
	 * @param DELIVER_ORDER_IDS
	 * @param RECV_CHANN_ID
	 *            收货方ID
	 * @return
	 */
	public Map<String, String> queryGooderOrderCreate(String DELIVER_ORDER_IDS,
			String RECV_CHANN_ID) {

		Map<String, Object> chann = loadChann(RECV_CHANN_ID);
		Double TEMP_CREDIT = StringUtil.getDouble(chann.get("TEMP_CREDIT")
				.toString());
		Map<String, String> paramMap = new HashMap<String, String>();
		// String[] ids = DELIVER_ORDER_IDS.split(",");
		// DELIVER_ORDER_IDS = "";
		// for(int i=0;i<ids.length;i++){
		// DELIVER_ORDER_IDS = DELIVER_ORDER_IDS+"'"+ids[i]+"',";
		// }
		// DELIVER_ORDER_IDS =
		// DELIVER_ORDER_IDS.substring(0,DELIVER_ORDER_IDS.length()-1);

		paramMap.put("DELIVER_ORDER_IDS", DELIVER_ORDER_IDS);
		List<Map<String, Object>> list = this.findList(
				"Pdtdeliver.queryGooderOrderAmount", paramMap);
		Map<String, Object> map;
		Double SEND_AMOUNT = 0.00;
		if (null != list) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);
				SEND_AMOUNT = SEND_AMOUNT
						+ StringUtil.getDouble(map.get("SEND_AMOUNT")
								.toString());
			}
		}
		SEND_AMOUNT = SEND_AMOUNT - TEMP_CREDIT;

		List<Map<String, Object>> createList = this.findList(
				"Pdtdeliver.queryGooderOrderCreate", paramMap);
		String CREATORS = "";
		if (null != createList) {
			for (int i = 0; i < createList.size(); i++) {
				map = createList.get(i);
				CREATORS = CREATORS + map.get("CREATOR") + ",";
			}
		}
		CREATORS = CREATORS.substring(0, CREATORS.length() - 1);
		Map<String, String> result = new HashMap<String, String>();
		result.put("SEND_AMOUNT", SEND_AMOUNT.toString());
		result.put("CREATORS", CREATORS);
		return result;
	}

	/**
	 * 查询 要催款的人员
	 * 
	 * @param DELIVER_ORDER_IDS
	 *            发运单IDS
	 * @return
	 */
	public List<Map<String, Object>> queryDuingPerson(String DELIVER_ORDER_IDS) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("DELIVER_ORDER_IDS", DELIVER_ORDER_IDS);
		List<Map<String, Object>>  newList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = this.findList("Pdtdeliver.queryDuingSql", paramMap);
		if (null != list && !list.isEmpty()) {
			 for(Map<String, Object> map : list){
				 Object obj  = map.get("DELIVER_ORDER_NO");
				 String DELIVER_ORDER_NO = "";
				 if(null != obj){
					 Clob clob = (Clob) map.get("DELIVER_ORDER_NO");
					 DELIVER_ORDER_NO= ClobToString(clob);
				 }
				 map.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
				 newList.add(map);
			 }
			 
		}

		return list;
	}

	// oracle.sql.Clob类型转换成String类型

	public String ClobToString(Clob clob){
		String reString = "";
		try{
			Reader is = clob.getCharacterStream();// 得到流
			BufferedReader br = new BufferedReader(is);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {// 执行循环将字符串全部取出付值给StringBuffer由StringBuffer转成STRING
				sb.append(s);
				s = br.readLine();
			}
			reString = sb.toString();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reString;
	}
	/**
	 * 查询U9数据
	 * @return
	 */
	public List pageQueryU9(Map params) {
		List rst = null;
		
		//交付计划单单号
		String deliverPlanNo = (String) params.get("DeliverPlanNo");
		
		if(StringUtil.isEmpty(deliverPlanNo)){
			return null;
		}
		
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {			
			try {
				String jsonStr = LogicUtil.queryProStatus(deliverPlanNo);
						
				rst = new Gson().fromJson(jsonStr,
						new TypeToken<ArrayList<ProStatus>>() {
						}.getType());
			} catch (Exception e) {
				rst = getErrorData();
			}
		} else {
			rst = getTestDataModel(deliverPlanNo);
		}
		
		if(rst == null){
			rst = new ArrayList();
		}
				
		return formatList(deliverPlanNo, rst);
	}
	private List getErrorData(){
		List rst = new  ArrayList();
		ProStatus temp = new ProStatus();
		temp.setStatus("false");
		temp.setDesc("查询失败！");
		rst.add(temp);
		return rst;
	}
	private List getTestDataModel(String deliverPlanNo){
		List rst = new  ArrayList();
		
		for(int i = 0; i < 50; i++){				
			String temp = "test"+deliverPlanNo + Math.round(Math.random()* 100);		
			rst.add(getModel(temp));
		}
		
		return rst;
	}
	private ProStatus getModel(String temp){
		ProStatus model = new ProStatus();
		
		model.setSaleOrderNo(temp);
		model.setCustNo(temp);
		model.setCustName(temp);
		model.setPrdNo(temp);
		model.setPrdName(temp);
		model.setPrdSpec(temp);
		
		model.setPLAN_NUM(null);
		model.setStroeInNum(null);
		model.setStoreNum(null);
		
		return model;
	}

	
	
	private List formatList(String deliverPlanNo, List<ProStatus> source){
		
		List rst = null;
		if(source != null && source.size() > 0){
			rst = new ArrayList();
			Map<String,String> data = null;
			for(ProStatus model : source){
				data = new HashMap();
				String DELIVER_ORDER_DTL_ID = model.getDELIVER_ORDER_DTL_ID();
				Float f_orderNum = 0.00f;
				int I_IS_BACKUP_FLAG;
				Float backNum = 0.00f ;
				Map map =  LogicUtil.getOrderNumBydeliverId(DELIVER_ORDER_DTL_ID);
				if (map != null && map.size() > 0) {
					BigDecimal ADVC_PLAN_NUM = (BigDecimal)map.get("ADVC_PLAN_NUM");
					f_orderNum = ADVC_PLAN_NUM.floatValue();
					BigDecimal IS_BACKUP_FLAG = (BigDecimal)map.get("IS_BACKUP_FLAG");
					I_IS_BACKUP_FLAG = IS_BACKUP_FLAG.intValue();
					if(1 == I_IS_BACKUP_FLAG){
						backNum = f_orderNum;
					}
				}
				data.put("DeliverOrderNo", deliverPlanNo);
				data.put("SaleOrderNo", model.getSaleOrderNo());
				data.put("CustNo", model.getCustNo());
				data.put("CustName", model.getCustName());
				data.put("PrdNo", model.getPrdNo());
				data.put("PrdName", model.getPrdName());
				data.put("PrdSpec", model.getPrdSpec());
				data.put("PlanNum", String.valueOf(model.getPLAN_NUM()));
				data.put("StroeInNum", String.valueOf(model.getStroeInNum()));
				data.put("StoreNum", String.valueOf(backNum));
				data.put("WorkNum", String.valueOf(f_orderNum - backNum - model.getStroeInNum()));
				data.put("Status", model.getStatus());
				data.put("Desc", model.getDesc());
				data.put("LEDGER_NAME", model.getLEDGER_NAME());
				rst.add(data);
			}
			
		}
		
		return rst;
	}
	public List downQuery(Map<String,String> map){
		return this.findList("Pdtdeliver.downGetById", map);
	}
	
	/**
     * 行关闭
     * @param DELIVER_ORDER_DTL_IDS
     * @param userBean
     */
    public void txCloseChilds(String deliverStats,String DELIVER_ORDER_NO,String DELIVER_ORDER_ID,String DELIVER_ORDER_DTL_IDS,UserBean userBean,String strJsonData,String IS_ALL_CLOSE){
    	if("true".equals(IS_ALL_CLOSE)&&!checkIsAllClose(DELIVER_ORDER_ID,userBean)){
    		throw new ServiceException("整单撤销失败");
    	}
    	if(StringUtil.isEmpty(DELIVER_ORDER_DTL_IDS)){
    		throw new ServiceException("无明细行,不能关闭!");
    	}
    	String returnMsg = checkISExsitCloseRec(DELIVER_ORDER_DTL_IDS);
    	if(!"true".equals(returnMsg)){
    		throw new ServiceException("存在已经关闭的行记录!"+returnMsg);
    	}
    	String strMessage = "操作成功";
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("DELIVER_ORDER_DTL_IDS", DELIVER_ORDER_DTL_IDS);
    	if("true".equals(IS_ALL_CLOSE)){
    		params.put("IS_SEND_FIN", "0");
    	}else{
    		params.put("IS_SEND_FIN", "3");
    	}
    	
    	params.put("REAL_SEND_NUM", "0");
		params.put("UPD_NAME", userBean.getXM());// 更新人名称
		params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);// 更新时间
		this.update("Pdtdeliver.closePdtDeliverOrderDtl", params);
		if(LogicUtil.checkDeliverDtlStat(DELIVER_ORDER_NO)||"true".equals(IS_ALL_CLOSE)){
			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
			params.put("STATE", deliverStats);
			this.update("Pdtdeliver.updateDeliverIdStat", params);
		}
		String strResult = null;
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {
			try {
				strResult = LogicUtil.createShipPlan(strJsonData);
			} catch (Exception ex) {
				throw new ServiceException("调用ESB接口失败!");
			}

			Map<String, String> resMap = new Gson().fromJson(strResult,
					new TypeToken<Map<String, String>>() {
					}.getType());
			if (resMap != null) {
				String stats = resMap.get("IsOK");
				if ("0".equals(stats)) {
					StringBuffer buf = new StringBuffer();
					buf.append("操作失败!");
					String Msg = resMap.get("Msg");
					buf.append("错误信息:" + Msg);
					throw new ServiceException(buf.toString());
				}
			} else {
				strMessage = "调用接口失败,查看LOG日志";
				throw new ServiceException(strMessage);
			}
		}
		try{
			//整单关闭的才要回信用，行关闭是要纳入下次发货的，不用回信用，在发货确认时，纳入下次发货会回信用
			if ("true".equals(IS_ALL_CLOSE)) {  
				String[] DELIVER_ORDER_DTL_IDLS = DELIVER_ORDER_DTL_IDS.split(",");
				for(int i=0;i<DELIVER_ORDER_DTL_IDLS.length;i++){
					String DELIVER_ORDER_DTL_ID = DELIVER_ORDER_DTL_IDLS[i];
					DELIVER_ORDER_DTL_ID = DELIVER_ORDER_DTL_ID.substring(1, DELIVER_ORDER_DTL_ID.length()-1);
					boolean flg = LogicUtil.updateDeliverCofimCredit("发运单关闭",DELIVER_ORDER_DTL_ID,DELIVER_ORDER_ID);
					if(!flg){
						throw new ServiceException("回信用出错!");
					}
				}				
			}
		}catch(Exception ex){
			throw new ServiceException(ex.getMessage());
		}
    }
    
    /**检查是否可以整单撤销
     * @param FROMBILLID
     * @param userBean
     * @return
     */
    public boolean checkIsAllClose(String FROM_BILL_ID,UserBean userBean){
    	//如果子单未提交库房,直接删除,如果子单提交库房了,要选撤子单,
    	List list = null;
    	try{
    	 list = findList("Pdtdeliver.queryDeliverByFromId", FROM_BILL_ID);
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
    	if(list==null || list.size() == 0){
    		return true;
    	}
    	for(int i=0;i<list.size();i++){
    		Map deliverOrder = (Map)list.get(i);
    		String stats = (String)deliverOrder.get("STATE");
    		String DELIVER_ORDER_ID = (String)deliverOrder.get("DELIVER_ORDER_ID");
    		String DELIVER_ORDER_NO = (String)deliverOrder.get("DELIVER_ORDER_NO");
    		if("未提交".equals(stats)||"已提交生产".equals(stats)){
    			HashMap params = new HashMap();
    			params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    			params.put("DEL_FLAG", "1");
    			params.put("UPDATOR", userBean.getXTYHID());
    			params.put("UPD_NAME", userBean.getYHM());
    			params.put("UPD_TIME", DateUtil.now());
    			this.delete("Pdtdeliver.delete", params);
    		}else{
    			String message = "子单据"+DELIVER_ORDER_NO+"处于"+stats+",不提撤销,请选撤销子单!";
    			throw new ServiceException(message);
    		}
    	}
    	return true;
    }
    
    public List getDeliverDtlId(String DELIVER_ORDER_ID) {
		// 这里的可用信用=渠道的当前信用+有效的零时信用-渠道表的冻结的信用
		return  findList("Pdtdeliver.queryDeliverDtlId", DELIVER_ORDER_ID);
	}
    
    public String checkISExsitCloseRec(String DELIVER_ORDER_DTL_IDS){
    	List deliverList = findList("Pdtdeliver.checkIsExsitClose", DELIVER_ORDER_DTL_IDS);
    	if(deliverList!=null && deliverList.size()>0){
    		HashMap deliverMap = (HashMap)deliverList.get(0);
    		String PRD_NO = (String)deliverMap.get("PRD_NO");
    		return PRD_NO;
    	}
    	return "true";
    }
    
    public boolean checkIsAllFreezeFlg(String DELIVER_ORDER_ID,String IS_ALL_FREEZE_FLAG){
    	HashMap paramMap = new HashMap();
    	paramMap.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
        paramMap.put("IS_ALL_FREEZE_FLAG",IS_ALL_FREEZE_FLAG);
    	Map pdtDeliverMap = (Map<String, String>)this.load("Pdtdeliver.checkIsFreezeFlg",paramMap);
    	if(pdtDeliverMap!=null && pdtDeliverMap.size()>0){
    		return false;
    	}
    	return true;
    }
    
    /**
     * 生成出货计划号
     * @param DELIVER_ORDER_IDS
     */
    public String createPlanNo(String DELIVER_ORDER_IDS){
//    	DELIVER_ORDER_IDS = "'"+DELIVER_ORDER_IDS.replace(",", "','")+"'";
    	String JOIN_DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_JOIN_DELIVER_ORDER_NO");
    	HashMap<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("DELIVER_ORDER_IDS", DELIVER_ORDER_IDS);
    	paramMap.put("JOIN_DELIVER_ORDER_NO", JOIN_DELIVER_ORDER_NO);
    	this.update("Pdtdeliver.updateJoinDeliverById", paramMap);
    	return JOIN_DELIVER_ORDER_NO;
    }
    
    
    /**
     * 判断所选订单的收货方是否是同一个区域服务中心
     * @param DELIVER_ORDER_IDS 订单IDS
     * @return fasle->非同一个区域服务中心
     */
    public boolean justOnlyAreaSer(String DELIVER_ORDER_IDS){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("DELIVER_ORDER_IDS", DELIVER_ORDER_IDS);
    	List<Map<String,String>> list = this.findList("Pdtdeliver.justOnlyAreaSer", params);
    	for(int i=0;i<list.size();i++){
    		String RECV_CHANN_ID = list.get(i).get("RECV_CHANN_ID");
    		String AREA_SER_ID = list.get(i).get("AREA_SER_ID");
    		for(int j=list.size()-1;j>i;j--){
    			String RECV_CHANN_ID_ = list.get(j).get("RECV_CHANN_ID");
        		String AREA_SER_ID_ = list.get(j).get("AREA_SER_ID");
        		
        		if(RECV_CHANN_ID.equals(RECV_CHANN_ID_) || RECV_CHANN_ID.equals(AREA_SER_ID_) || (null != AREA_SER_ID && (AREA_SER_ID.equals(AREA_SER_ID_) || AREA_SER_ID.equals(RECV_CHANN_ID_)))){
        			//正常
        		}else{
        			return false;//非同一个区域服务中心
        		}
    		}
    	}
    	
    	return true;
    }
    
}
