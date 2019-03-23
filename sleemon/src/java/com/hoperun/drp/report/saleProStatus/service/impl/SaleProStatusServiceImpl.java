package com.hoperun.drp.report.saleProStatus.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.queryProStatus.model.ProStatus;
import com.hoperun.drp.report.saleProStatus.service.SaleProStatusService;

public class SaleProStatusServiceImpl extends BaseService implements SaleProStatusService {
	
	
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> pageQueryU9New(Map<String,String> params){
		List<ProStatus> rst = new ArrayList<ProStatus>();
		//销售订单号
		String SALE_ORDER_NO = params.get("SALE_ORDER_NO");
		if(StringUtil.isEmpty(SALE_ORDER_NO)){
			return null;
		}
		
		List<Map<String,Object>> childList = this.findList("Saleordermge.queryDtlBySaleNo", params);
		if(null == childList || childList.isEmpty()){
			return null;
		}
			
		try {
			if("true".equals(Consts.INVOKE_SYS_FLG)){	
				List<ProStatus> uData = null;
				String jsonStr = LogicUtil.queryProStatus(SALE_ORDER_NO);
				uData = new Gson().fromJson(jsonStr, new TypeToken<List<ProStatus>>() {
						}.getType());
				for(Map<String,Object> child : childList){
					for(ProStatus u9model : uData){
						if(StringUtil.nullToSring(child.get("SALE_ORDER_DTL_ID")).equals(u9model.getDELIVER_ORDER_DTL_ID())){
							u9model.setOrderNum(StringUtil.getInteger(child.get("ORDER_NUM")));
							u9model.setDeliverOrderNo(StringUtil.nullToSring(child.get("DELIVER_ORDER_NO")));
							rst.add(u9model);
							continue;
						}
					}
					
				}
			}else{
				rst = getTestDataModelNew(SALE_ORDER_NO,childList);
			}
		}catch (Exception e) {
			e.printStackTrace();
			rst = getErrorData();
		}
			
		return formatList(SALE_ORDER_NO, rst);
	}
	
	
	
	
	
	/**
	 * 查询U9数据
	 * @return
	 */
	public List pageQueryU9(Map params) {
		List rst = new ArrayList();
		
		//销售订单号
		String SALE_ORDER_NO = (String) params.get("SALE_ORDER_NO");
		
		if(StringUtil.isEmpty(SALE_ORDER_NO)){
			return null;
		}
		
		//查询运单号
		List deliverList = this.findList("Pdtdeliver.queryDeliverBySaleNo", SALE_ORDER_NO);
		if(deliverList == null || deliverList.size() == 0){
			return null;
		}
		Map<String,String> param=new HashMap<String, String>();
		for (int i = 0; i < deliverList.size(); i++) {
			Map<String,String> map=(Map<String, String>) deliverList.get(i);
			param.put(map.get("DELIVER_ORDER_NO"),"1");
		}
		List<ProStatus> tempList=new ArrayList<ProStatus>();
		List<ProStatus> list=new ArrayList<ProStatus>();
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {			
			try {
				List<ProStatus> uData = null;
				 Set<String> key = param.keySet();
				for (Iterator it = key.iterator(); it.hasNext();) { 
					String deliverNo = (String) it.next();
					String jsonStr = LogicUtil.queryProStatus(deliverNo);
					uData = new Gson().fromJson(jsonStr,
							new TypeToken<List<ProStatus>>() {
							}.getType());
					tempList.addAll(uData);
				}
				for (int i = 0; i < tempList.size(); i++) {
					ProStatus model=tempList.get(i);
					for (int j = 0; j < deliverList.size(); j++) {
						Map map=(Map<String, String>) deliverList.get(j);
						if(map.get("DELIVER_ORDER_DTL_ID").equals(model.getDELIVER_ORDER_DTL_ID())){
							String deliverNo = (String)map.get("DELIVER_ORDER_NO");
							BigDecimal orderNum = (BigDecimal)map.get("ORDER_NUM");
							model.setOrderNum(orderNum.intValue());
							model.setDeliverOrderNo(deliverNo);
							rst.add(model);
							continue;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				rst = getErrorData();
			}
		} else {
			rst = getTestDataModel(SALE_ORDER_NO,deliverList);
		}
		
						
		return formatList(SALE_ORDER_NO, rst);
	}
	
	private List formatList(String SALE_ORDER_NO, List<ProStatus> source){
		
		List rst = null;
		if(source != null && source.size() > 0){
			rst = new ArrayList();
			Map<String,String> data = null;
			for(ProStatus model : source){
				data = new HashMap<String,String>();				
				data.put("SaleOrderNo", SALE_ORDER_NO);
				data.put("DeliverOrderNo", model.getDeliverOrderNo());				
				data.put("CustNo", model.getCustNo());
				data.put("CustName", model.getCustName());
				data.put("PrdNo", model.getPrdNo());
				data.put("PrdName", model.getPrdName());
				data.put("PrdSpec", model.getPrdSpec());
				data.put("OrderNum", String.valueOf(model.getOrderNum()));
				data.put("StroeInNum", String.valueOf(model.getStroeInNum()));
				data.put("StoreNum", String.valueOf(model.getStoreNum()));
				data.put("WorkNum", String.valueOf(model.getOrderNum() - model.getStoreNum() - model.getStroeInNum()));
				
				data.put("Status", model.getStatus());
				data.put("Desc", model.getDesc());
				
				rst.add(data);
				
			}
			
		}
		
		return rst;
	}
	
	private List<ProStatus> getErrorData(){
		List<ProStatus> rst = new  ArrayList<ProStatus>();
		ProStatus temp = new ProStatus();
		temp.setStatus("false");
		temp.setDesc("U9查询失败！");
		rst.add(temp);
		
		return rst;
	}
	
	private List getTestDataModel(String SALE_ORDER_NO,List deliverList){
		List rst = new  ArrayList();
		
		for(int i = 0; i < deliverList.size(); i++){	
			HashMap deliverInfo = (HashMap) deliverList.get(i);
			String temp = "test"+SALE_ORDER_NO + Math.round(Math.random()* 100);
			ProStatus model  = getModel(temp);
			BigDecimal orderNum = (BigDecimal)deliverInfo.get("ORDER_NUM");
			model.setOrderNum(orderNum.intValue());
			
			rst.add(model);
		}
		
		return rst;
	}
	
	/**
	 * 测试数据
	 * @param SALE_ORDER_NO
	 * @param childList
	 * @return
	 */
	private List<ProStatus> getTestDataModelNew(String SALE_ORDER_NO,List<Map<String,Object>> childList){
		List<ProStatus> rst = new ArrayList<ProStatus>();
		for(int i = 0; i < childList.size(); i++){	
			Map<String,Object> child =  childList.get(i);
			String temp = "test"+SALE_ORDER_NO + Math.round(Math.random()* 100);
			ProStatus model  = getModel(temp);
			Integer orderNum = StringUtil.getInteger(child.get("ORDER_NUM"));
			model.setOrderNum(orderNum);
			rst.add(model);
		}
		return rst;
	}
	
	private ProStatus getModel(String temp){
		ProStatus model = new ProStatus();
				
		model.setDeliverOrderNo(temp);
		model.setSaleOrderNo(temp);
		model.setCustNo(temp);
		model.setCustName(temp);
		model.setPrdNo(temp);
		model.setPrdName(temp);
		model.setPrdSpec(temp);		
		//model.setPLAN_NUM(0.0f);
		//model.setStroeInNum(0.0f);
		//model.setStoreNum(0.0f);
		
		return model;
	}

}
