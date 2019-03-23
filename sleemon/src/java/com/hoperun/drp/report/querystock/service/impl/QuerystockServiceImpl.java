package com.hoperun.drp.report.querystock.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.drp.report.querystock.model.StoreModel;
import com.hoperun.drp.report.querystock.service.QuerystockService;

/**
 * *@serviceImpl
 * *@func 库存查询
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2014-05-04 14:13:12
 */

public class QuerystockServiceImpl extends BaseService implements QuerystockService{
 
	/**
	 * 查询库存数据
	 * @return
	 */
	public List queryStoreAcct(Map params) {

		List<StoreModel> list = new ArrayList<StoreModel>();

		String CustNo  = (String) params.get("CustNo");    // 客户编号
		String PrdNo   = (String) params.get("PrdNo");     // 商品编号
		String OrgCode = (String) params.get("OrgCode");   // 组织编码

		if ("true".equals(Consts.INVOKE_SYS_FLG)) {
			try {
				String jsonStr = LogicUtil.queryStoreAcct(CustNo, PrdNo,
						OrgCode);
				list = new Gson().fromJson(jsonStr,
						new TypeToken<ArrayList<StoreModel>>() {
						}.getType());

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		     list = getTestDataModel(CustNo,PrdNo,OrgCode);
		}
		return formatList(list);
	}
   	
	
	
	private List formatList(List<StoreModel> source){
		List rst = null;
		
		if(source != null && source.size() > 0){
			rst = new ArrayList();
			Map<String,String> data = null;
			for(StoreModel model : source){
				data = new HashMap();
				String status = model.getStatus();
				if(!"false".equals(status)){
				data.put("PrdNo", model.getPrdNo());
				data.put("PrdName", model.getPrdName());
				data.put("PrdSpec", model.getPrdSpec());
				data.put("StoreNo", model.getStoreNo());
				data.put("StoreName", model.getStoreName());
				data.put("StorePrice", String.valueOf(model.getStorePrice()));
				data.put("StoreNum", String.valueOf(model.getStoreNum()));
				data.put("StoreAmount", String.valueOf(model.getStoreAmount()));
				data.put("CustNo", model.getCustNo());
				data.put("CustName", model.getCustName());
				data.put("DeliverAddr",model.getDeliverAddr());
				data.put("SalePrice", String.valueOf(model.getSalePrice()));
				data.put("SaleAmount", String.valueOf(model.getSaleAmount()));
				data.put("Remark",model.getRemark());
				data.put("LedgerName", model.getLedgerName());
				data.put("PrvdPrice", String.valueOf(model.getPrvdPrice()));
				data.put("Unit_Volume", String.valueOf(model.getUnit_Volume()));
				data.put("Total_Volume", String.valueOf(model.getTotal_Volume()));
				data.put("Status", model.getStatus());
				data.put("Desc", model.getDesc());
				rst.add(data);
				}
			}
		}
		
		return rst;
	}
	
	private List getTestDataModel(String CustNo,String PrdNo,String OrgCode){
		List rst = new  ArrayList();
		
		if(CustNo != null && !"".equals(CustNo)){
			String temp = "test"+CustNo;
			rst.add(getModel(temp));
		}
		else if(PrdNo != null && !"".equals(PrdNo)){
			String temp = "test"+PrdNo;
			rst.add(getModel(temp));
		}
		else if(OrgCode != null && !"".equals(OrgCode)){
			String temp = "test"+OrgCode;
			rst.add(getModel(temp));
		}
		else{
			for(int i = 0; i < 5; i++){				
				String temp = "test"+Math.random();				
				rst.add(getModel(temp));
			}
		}
		
		return rst;
	}
	
	private StoreModel getModel(String temp){
		
		StoreModel  model = new StoreModel();
		
		model.setPrdNo("000001");
		model.setPrdName("南京新百");
		model.setPrdSpec("新百描述");
		model.setStoreNo("10001");
		model.setStoreName(temp);
		model.setStorePrice(Float.parseFloat("132.23"));
		model.setSalePrice(Float.parseFloat("10.2"));
		model.setStoreNum(Float.parseFloat("10.2"));
		model.setStoreAmount(temp);
		model.setCustNo(temp);
		model.setCustName("王天一");
		model.setDeliverAddr("江苏南京");
		model.setSalePrice(Float.parseFloat("10.2"));
		model.setSaleAmount(Float.parseFloat("10.2"));
		model.setRemark("暂无");
		model.setLedgerName("江苏润和");
		model.setPrvdPrice(Float.parseFloat("10.2"));
		model.setUnit_Volume(Float.parseFloat("10.2"));
		model.setTotal_Volume(Float.parseFloat("10.2"));
		return model;
	}
	
	private List getTestData(String queryParam){
		List rst = new  ArrayList();		
		HashMap data = null;
		
		if(queryParam != null && !"".equals(queryParam)){
			String temp = "test"+queryParam;				
			data = getOneRow(temp);				
			rst.add(data);
		}else{
			for(int i = 0; i < 5; i++){				
				String temp = "test"+Math.random();				
				data = getOneRow(temp);				
				rst.add(data);
			}
		}
		
		return rst;
	}
	private HashMap getOneRow(String temp){
		HashMap data = new HashMap();
		
		data.put("PrdNo", temp); 
		data.put("PrdName", temp); 
		data.put("PrdSpec", temp); 
		data.put("StoreNo", temp); 	
		data.put("StoreName", temp); 
		data.put("StorePrice", temp); 
		data.put("StoreNum", temp);
		data.put("StoreAmount", temp); 
		data.put("CustNo", temp); 
		data.put("CustName", temp); 
		data.put("DeliverAddr", temp);
		data.put("SalePrice", temp);
		data.put("SaleAmount", temp);
		data.put("Remark", temp);
		data.put("LedgerName", temp);
		data.put("PrvdPrice", temp);
		data.put("Unit_Volume", temp);
		data.put("Total_Volume", temp);
		return data;
	}
}
