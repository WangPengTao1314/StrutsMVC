package com.hoperun.erp.sale.deliverconfm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.deliverconfm.service.DeliverconfmService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public class DeliverconfmServiceImpl  extends BaseService implements DeliverconfmService {
	
	
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
		return this.pageQuery("Pdtdeliver.pageQuery",
				"Pdtdeliver.pageCount", params, pageNo);
	}
	
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param DELIVER_ORDER_ID 主表ID
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
    public List <TurnoverplanChildModel> childQuery(Map<String,String> params){
    	return this.findList("Pdtdeliver.queryChldByFkId", params);
    }
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID){
		return (Map<String, String>) this.load("Pdtdeliver.loadById", DELIVER_ORDER_ID);
	}
	
	/**
	 * @主表信息跟据编号
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> loadMain(String DELIVER_ORDER_NO){
		return (Map<String, String>) this.load("Pdtdeliver.loadByNo", DELIVER_ORDER_NO);
	}
	
	
	public Map<String,String> loadDtlBySaleOrderNo(Map <String, String> params){
		return (Map<String, String>) this.load("Pdtdeliver.queryChldByOrderNo", params);
	}

	/**
     * 编辑：新增
     * @param model 发运单
     * @param childList 明细
     * @return the string
     */
    public void txEdit(TurnoverplanModel model,List<TurnoverplanChildModel> childList,UserBean userBean){
		try{
	    	Map<String,String> params = new HashMap<String,String>();
	    	String DELIVER_ORDER_ID = model.getDELIVER_ORDER_ID();
	    	params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
	    	params.put("CONSAR_NO", model.getCONSAR_NO());
	    	params.put("DRV_MOB_NO", model.getDRV_MOB_NO());
	    	params.put("REMARK", model.getREMARK());
	    	params.put("UPDATOR", userBean.getXTYHID());//更新人id
	        params.put("UPD_NAME", userBean.getXM());//更新人名称
	        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
	        params.put("TOTAL_VOLUME_FLAG", "TOTAL_VOLUME_FLAG");// 更新总体积
	    	this.update("Pdtdeliver.updateById", params);
	    	
	    	//子表保存， 
	    	if(!childList.isEmpty()){
//	    		doSaveChild(DELIVER_ORDER_ID,childList,userBean);
	    		for(TurnoverplanChildModel child : childList){
	    			Map<String,Object> map = new HashMap<String,Object>();
	    			String NO_SEND_DEAL_TYPE = child.getNO_SEND_DEAL_TYPE();
					map.put("NO_SEND_DEAL_TYPE",NO_SEND_DEAL_TYPE);
					map.put("DELIVER_ORDER_DTL_ID",child.getDELIVER_ORDER_DTL_ID());
					
					Integer ADVC_PLAN_NUM = StringUtil.getInteger(child.getADVC_PLAN_NUM());//预排车数量
					Integer PLAN_NUM = StringUtil.getInteger(child.getPLAN_NUM());//计划排车数量
					Integer  REAL_SEND_NUM = StringUtil.getInteger(child.getREAL_SEND_NUM()); //实发数量
					map.put("NO_SEND_NUM", (PLAN_NUM-REAL_SEND_NUM));//未发数量
					map.put("DELIVER_ORDER_DTL_ID",child.getDELIVER_ORDER_DTL_ID());
					//更新发运单明细的 未排货品处理方式
	    			update("Pdtdeliver.updateDeliverOrderDtl",map);
	    		}
	    	}
    	}catch(Exception ex){
			new ServiceException(ex);
		}
		
    }
    
    /**  
     * 发运query的时候，根据‘未排货品发运方式’生成对应的发运单
     * @param DELIVER_ORDER_ID_OLD 原始发运单的ID
     * @param childList 明细
     * @param userBean
     */
    public Map<String,Object> doSaveChild(String DELIVER_ORDER_ID_OLD,List<Map<String,Object>> childList,
    		UserBean userBean)throws Exception{
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("DELIVER_ORDER_ID_OLD", DELIVER_ORDER_ID_OLD);
    	params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		params.put("STATE",BusinessConsts.UNCOMMIT);//未提交
	    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	    
    	List<Map<String,Object>> addChildList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> updateSaleDtlPlanList = new ArrayList<Map<String,Object>>();
    	List<Map<String,Object>> updateSaleDtlList = new ArrayList<Map<String,Object>>();
    	Map<String,String> tempMap = new HashMap<String,String>();
    	Set<String> deliveIdSet = new HashSet<String>();
    	Set<String> deliveNoSet = new HashSet<String>();
    	if(null != childList){
    		for(Map<String,Object> childMap : childList){
    			Map<String,Object> map = new HashMap<String,Object>();
    			String NO_SEND_DEAL_TYPE = StringUtil.nullToSring(childMap.get("NO_SEND_DEAL_TYPE"));
//				map.put("NO_SEND_DEAL_TYPE",NO_SEND_DEAL_TYPE);
//				map.put("DELIVER_ORDER_DTL_ID",child.getDELIVER_ORDER_DTL_ID());
//				
//				Integer ADVC_PLAN_NUM = StringUtil.getInteger(child.getADVC_PLAN_NUM());//预排车数量
//				Integer PLAN_NUM = StringUtil.getInteger(child.getPLAN_NUM());//计划排车数量
//				Integer  REAL_SEND_NUM = StringUtil.getInteger(child.getREAL_SEND_NUM()); //实发数量
//				map.put("NO_SEND_NUM", (PLAN_NUM-REAL_SEND_NUM));//未发数量
//				map.put("DELIVER_ORDER_DTL_ID",child.getDELIVER_ORDER_DTL_ID());
//				//更新发运单明细的 未排货品处理方式
//    			update("Pdtdeliver.updateDeliverOrderDtl",map);
//    			map.clear();
//    			
//    			//回填 销售订单明细  已发数量
//				Map<String,Object> mapOld = new HashMap<String,Object>();
//				mapOld.put("DELIVER_ORDER_DTL_ID", child.getDELIVER_ORDER_DTL_ID());
//				mapOld.put("SALE_ORDER_DTL_ID", child.getSALE_ORDER_DTL_ID());
//				updateSaleDtlList.add(mapOld);
    			//行状态 0->未处理，1->已处理了，3->关闭
        		Integer IS_SEND_FIN = StringUtil.getInteger(StringUtil.nullToSring(childMap.get("IS_SEND_FIN")));//行状态
        		if(3 == IS_SEND_FIN){
        			continue;
        		}
//    			int PLAN_NUM = StringUtil.getInteger(StringUtil.nullToSring(childMap.get("PLAN_NUM")));
//    			int REAL_SEND_NUM = StringUtil.getInteger(StringUtil.nullToSring(childMap.get("REAL_SEND_NUM")));
//    			int NO_SEND_NUM = PLAN_NUM - REAL_SEND_NUM;
        		
        		Double PLAN_NUM = StringUtil.getDouble(StringUtil.nullToSring(childMap.get("PLAN_NUM")));
        		Double REAL_SEND_NUM = StringUtil.getDouble(StringUtil.nullToSring(childMap.get("REAL_SEND_NUM")));
        		Double NO_SEND_NUM = PLAN_NUM - REAL_SEND_NUM;
        		
//    			int NO_SEND_NUM = StringUtil.getInteger(StringUtil.nullToSring(childMap.get("NO_SEND_NUM")));//未发货品数量
    			if(0<NO_SEND_NUM){
    				if("纳入下次交付计划".equals(NO_SEND_DEAL_TYPE)){
    					//回填销售订单明细的 排车数量和已发数量
    					map.put("DELIVER_ORDER_DTL_ID", StringUtil.nullToSring(childMap.get("DELIVER_ORDER_DTL_ID")));
    					map.put("SALE_ORDER_DTL_ID",  StringUtil.nullToSring(childMap.get("SALE_ORDER_DTL_ID")));
    					map.put("NO_SEND_NUM", NO_SEND_NUM);//剩余货品
    					updateSaleDtlPlanList.add(map);
    				}else{
    					//不同的发运方式 产生不同的 发运单 无论 多少明细 最多只会生成两种不同的单据  ‘托运’ 或者 ‘整车发运’
    					String DELIVER_ORDER_ID = null;
    					String DELIVER_ORDER_NO = null;
    					//新生成的发运单 单据为‘已提交生产’ modify by zzb(cj) 2014-6-26
						params.put("STATE",BusinessConsts.COMMIT_FACTORY);//已提交生产
						
    					if("托运".equals(NO_SEND_DEAL_TYPE)){
    						String idOne = tempMap.get("idOne");
    						if(StringUtil.isEmpty(idOne)){
    							DELIVER_ORDER_ID = StringUtil.uuid32len();
    							DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
    							params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
        				    	params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
        				    	params.put("DELIVER_TYPE", "托运");
        				    	tempMap.put("idOne", DELIVER_ORDER_ID);
        				    	tempMap.put("noOne", DELIVER_ORDER_NO);
        				    	insert("Pdtdeliver.newTurnoverplan", params);
                                deliveIdSet.add(DELIVER_ORDER_ID);//将发运单id放入set后面用
                                deliveNoSet.add(DELIVER_ORDER_NO);//将发运单no放入set后面用
    						}else{
    							DELIVER_ORDER_ID = tempMap.get("idOne");
    							DELIVER_ORDER_NO = tempMap.get("noOne");
    						}
    				    	
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
							
							
						}else{
    						String idOther = tempMap.get("idOther");
    						if(StringUtil.isEmpty(idOther)){
    							DELIVER_ORDER_ID = StringUtil.uuid32len();
    							DELIVER_ORDER_NO = LogicUtil.getBmgz("ERP_DELIVER_ORDER_NO");
    							params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
        				    	params.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
        				    	params.put("DELIVER_TYPE", "整车发运");
        				    	tempMap.put("idOther", DELIVER_ORDER_ID);
        				    	tempMap.put("noOther", DELIVER_ORDER_NO);
        				    	insert("Pdtdeliver.newTurnoverplan",params);
        				    	deliveIdSet.add(DELIVER_ORDER_ID);//将发运单id放入set后面用
        				    	deliveNoSet.add(DELIVER_ORDER_NO);//将发运单no放入set后面用
    						}else{
    							DELIVER_ORDER_ID = tempMap.get("idOther");
    							DELIVER_ORDER_NO = tempMap.get("noOther");
    						}
    					}
    					map.put("DELIVER_ORDER_ID_OLD", DELIVER_ORDER_ID_OLD);
    					map.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    					map.put("DELIVER_ORDER_NO", DELIVER_ORDER_NO);
    					map.put("DELIVER_ORDER_DTL_ID", StringUtil.uuid32len());
    					map.put("DELIVER_ORDER_DTL_ID_OLD", StringUtil.nullToSring(childMap.get("DELIVER_ORDER_DTL_ID")));
    					
    					//产生新的发运单明细  预排货品数量 就是 当前单据的 未发货品数量
    					map.put("ADVC_PLAN_NUM", NO_SEND_NUM);
    					map.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
    					addChildList.add(map);
    				}
    			}
    			
    		}
    	}
    	
    	 // 纳入下次交付计划   更新销售订单明细的 排车数量 和已发数量 此处为 实发数量 REAL_SEND_NUM
    	if(!updateSaleDtlPlanList.isEmpty()){
    		this.batch4Update("Pdtdeliver.updateSaleOrderDtlDelive", updateSaleDtlPlanList);
    	}
    	
        //更新销售订单明细的 已发数量数量 此处为 实发数量 REAL_SEND_NUM
    	if(!updateSaleDtlList.isEmpty()){
    		this.batch4Update("Pdtdeliver.updateSaleOrderDtlSendNum", updateSaleDtlList);
    	}
    	//插入发运单明细
    	if(!addChildList.isEmpty()){
    		this.batch4Update("Pdtdeliver.insertDeliverOrderDtl", addChildList);
    	}
    	
    	
    	ArrayList  deliverList = new ArrayList();  //所有发运单主表ID
    	//更新新增的主表的总体积    
		for (String deliverOrderId : deliveIdSet){ 
			  if(!StringUtil.isEmpty(deliverOrderId)){
				  params.clear();
				  params.put("DELIVER_ORDER_ID", deliverOrderId);
				  deliverList.add(deliverOrderId);
				  this.update("Pdtdeliver.updateDeliveTotalVolum", params);
			 }
		}
		
 
		udpateCreditByDeliverOrderId(deliverList);
		
		Map<String,Object> returnMap = new HashMap<String, Object>(); 
		returnMap.put("deliveNoSet", deliveNoSet);
		return returnMap;

    }
    
  
    /**按新产生的发运单更新信用和冻结信用
     * @param deliverList
     */
    private void udpateCreditByDeliverOrderId(ArrayList deliverList)throws Exception{
        for(int i=0 ;i<deliverList.size();i++){
     	   String deliverOrderId = (String)deliverList.get(i);
     	  List list  = (List)this.findList("Pdtdeliver.queryNewDeliverOrder", deliverOrderId);
     	  for(int j = 0;list != null &&j<list.size();j++){
     		 HashMap deliverMap =  (HashMap)list.get(j);
     		 String DELIVER_ORDER_DTL_ID = (String)deliverMap.get("DELIVER_ORDER_DTL_ID");
        	 LogicUtil.updateDeliverCofimCredit("发运确认",DELIVER_ORDER_DTL_ID,deliverOrderId);
     	  }
        }
 		
    }
    
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param  
     * @return the list
     */
    public List <TurnoverplanChildModel> loadChilds(Map <String, String> params){
    	return this.findList("Pdtdeliver.loadChldByIds", params);
    }
    
    
    /**
     * 发运确认
     * @param DELIVER_ORDER_ID 发运单ID
     * @param childList 发运单明细
     * @param userBean
     */
    @SuppressWarnings("unchecked")
	public Map<String,Object>  txDespatchAffirm(String DELIVER_ORDER_ID_OLD,UserBean userBean){
    	Map<String,Object> params = new HashMap<String,Object>();
    	Map<String,Object> returnMap = new HashMap<String,Object>();
    	//更新 当前 发运单的 状态
    	params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID_OLD);
    	params.put("STATE","待收货");//待收货  modify by zzb 2014-7-17 改为‘待收货’  审核入库通知单是‘已收货’
    	params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称
        params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
    	this.update("Pdtdeliver.updateById", params);
    	
//    	List<Map<String,String>> saleIDList = this.findList("Pdtdeliver.queryDeliverDtlFormID", params);
    	Set<String> saleIdSet = new HashSet<String>();
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	List<Map<String,Object>> childModelList = this.findList("Pdtdeliver.queryChldByFkId", params);
    	for(Map<String,Object> map : childModelList){
    		//行状态 0->未处理，1->已处理了，3->关闭
    		Integer IS_SEND_FIN = StringUtil.getInteger(StringUtil.nullToSring(map.get("IS_SEND_FIN")));//行状态
    		if(3 == IS_SEND_FIN){
    			continue;
    		}
			Integer PLAN_NUM = StringUtil.getInteger(StringUtil.nullToSring(map.get("PLAN_NUM")));//计划排车数量
			Integer  REAL_SEND_NUM =  StringUtil.getInteger(StringUtil.nullToSring(map.get("REAL_SEND_NUM")));//实发数量
			int NO_SEND_NUM = PLAN_NUM-REAL_SEND_NUM;//未发数量
			String NO_SEND_DEAL_TYPE = StringUtil.nullToSring(map.get("NO_SEND_DEAL_TYPE"));//剩余货品处理方式
			if(0 < NO_SEND_NUM && StringUtil.isEmpty(NO_SEND_DEAL_TYPE)){
				throw new ServiceException("该单据还有差异数量，请选择剩余货品的处理方式!");
			}
			saleIdSet.add(StringUtil.nullToSring(map.get("SALE_ORDER_ID")));
    	}
    	
    	try{
    		//生产新的发运单
    	   returnMap = doSaveChild(DELIVER_ORDER_ID_OLD,childModelList,userBean);
    	}catch (Exception e) {
    		e.printStackTrace();
		}
    	
    	
/*     modify  by zzb 2014-08-15 不需要回写销售订单
 *  	//如果 销售订单所有明细的 订货数量=已排车数量 那么该销售订单状态为‘已完成’
    	StringBuffer ids = new StringBuffer("");
    	if(!saleIdSet.isEmpty()){
    		for(String id: saleIdSet){
    			params.clear();
    			params.put("SALE_ORDER_ID", id);
    			//查询该销售订单所有明细是否全部已排车
    			int res  = this.queryForInt("Pdtdeliver.querySaleDtl", params);
    			if(res == 0){
    				ids.append("'");
    				ids.append(id);
    				ids.append("',");
    			} 
    		}
    		if(!StringUtil.isEmpty(ids.toString())){
    			String SALE_ORDER_IDS = ids.substring(0,ids.length()-1).toString();
        		params.put("SALE_ORDER_IDS",SALE_ORDER_IDS);
        		params.put("STATE", "已完成");
    			params.put("UPDATOR", userBean.getXTYHID());//更新人id
    			params.put("UPD_NAME", userBean.getXM());//更新人名称
    			params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
        		
        		this.update("Pdtdeliver.updateSaleOrder", params);
    		}
    	}*/
    	
    	return returnMap;
    }
    
    
    /**
     * 填写延时原因
     * @param params 
     */
    public void txWriteReason(Map<String,String> params){
    	this.update("Pdtdeliver.updateById", params);
    	
    }
    
    /**
     * 发运确认的时候验证明细是否有差异数量
     * @param DELIVER_ORDER_ID
     * @return true -> 有差异不能提交
     */
    @SuppressWarnings("unchecked")
	public boolean txCheckCommit(String DELIVER_ORDER_ID){
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	List<Map<String,Object>> childModelList = this.findList("Pdtdeliver.queryChldByFkId", params);
    	for(Map<String,Object> map : childModelList){
    		//行状态 0->未处理，1->已处理了，3->关闭
    		Integer IS_SEND_FIN = StringUtil.getInteger(StringUtil.nullToSring(map.get("IS_SEND_FIN")));//行状态
    		if(3 == IS_SEND_FIN){
    			continue;
    		}
			Integer PLAN_NUM = StringUtil.getInteger(StringUtil.nullToSring(map.get("PLAN_NUM")));//计划排车数量
			Integer  REAL_SEND_NUM =  StringUtil.getInteger(StringUtil.nullToSring(map.get("REAL_SEND_NUM")));//实发数量
			int NO_SEND_NUM = PLAN_NUM-REAL_SEND_NUM;//未发数量
			String NO_SEND_DEAL_TYPE = StringUtil.nullToSring(map.get("NO_SEND_DEAL_TYPE"));//剩余货品处理方式
			if(0 < NO_SEND_NUM && StringUtil.isEmpty(NO_SEND_DEAL_TYPE)){
				 return true;
			}
    	}
    	
    	return false;
    }
    public List downQuery(Map<String,String> map){
		return this.findList("Pdtdeliver.downGetById", map);
	}
    
    
    
    /**
     * 子表编辑
     * @param childList
     * @param userBean
     */
    public void txChildEdit(String DELIVER_ORDER_ID,String DELIVER_ORDER_NO, List<TurnoverplanChildModel> childList,UserBean userBean){
//    	LogicUtil.updateRealNum(DELIVER_ORDER_NO,childList,userBean.getXTYHID(),userBean.getXM());
    	List<Map<String,Object>> updateDtlList = new ArrayList<Map<String,Object>>();

    	for(TurnoverplanChildModel child : childList){
    		Map<String,Object> params = new HashMap<String,Object>();
    		params.put("DELIVER_ORDER_DTL_ID", child.getDELIVER_ORDER_DTL_ID());
    		params.put("REAL_SEND_NUM", child.getREAL_SEND_NUM());
    		params.put("NO_SEND_NUM", child.getNO_SEND_NUM());
    		params.put("IS_SEND_FIN", BusinessConsts.INTEGER_1);//已完成
    		params.put("UPD_NAME", userBean.getXM());//更新人名称
			params.put("UPD_TIME", BusinessConsts.UPDATE_TIME);//更新时间
    		updateDtlList.add(params);
    		
    		StringBuffer remark = new StringBuffer("行ID:");
    		remark.append(child.getDELIVER_ORDER_DTL_ID());
    		remark.append(" 历史实发数量：");
    		remark.append(child.getREAL_SEND_NUM_OLD());
    		remark.append(" 本次实发数量：");
    		remark.append(child.getREAL_SEND_NUM());
    		remark.append(" 历史行状态：");
    		remark.append(child.getIS_SEND_FIN());
    		remark.append(" 本次行状态：");
    		remark.append(BusinessConsts.INTEGER_1);
    		remark.append(" 本次修改人：")
    		.append(userBean.getXM());
    		
    		LogicUtil.actLog("发运确认修改实发数量", "修改实发数量", "DM系统", "更新明细行实发数量",
    				remark.toString(), "", "", "");
    	}
    	if(!updateDtlList.isEmpty()){
    		this.batch4Update("Pdtdeliver.updateDeliverOrderDtl", updateDtlList);
    	}
    	
    	Map<String,String> billMap = this.load(DELIVER_ORDER_ID);
    	String STATE = billMap.get("STATE");
    	if("已提交库房".equals(STATE) || "部分发货".equals(STATE)){
    		 Map<String,Object> paramMap = new HashMap<String,Object>();
    		 paramMap.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
    		 int rs = this.queryForInt("Pdtdeliver.queryIsSendFin", paramMap);
    		 if(rs == 0){
    			 paramMap.put("STATE","已发货");
    			 this.update("Pdtdeliver.updateById", paramMap);
    		 }
    		 StringBuffer remark = new StringBuffer("主表ID:");
    		 remark.append(DELIVER_ORDER_ID)
    		 .append(" 历史状态：")
    		 .append(STATE)
    		 .append(" 本次状态：")
    		 .append("已发货")
    		 .append(" 本次修改人：")
     		 .append(userBean.getXM());
             LogicUtil.actLog("货品发运", "修改实发数量", "DM系统", "更新主表状态",
     				remark.toString(), "", "", "");
    	}
    	
    }

}


