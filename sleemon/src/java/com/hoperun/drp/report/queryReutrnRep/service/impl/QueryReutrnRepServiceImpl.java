package com.hoperun.drp.report.queryReutrnRep.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.report.queryReutrnRep.model.ReutrnRep;
import com.hoperun.drp.report.queryReutrnRep.service.QueryReutrnRepService;

public class QueryReutrnRepServiceImpl extends BaseService implements QueryReutrnRepService {
	
	/**
	 * 查询U9数据
	 * @return
	 */
	public List pageQueryU9(Map params) {
		List rst = null;
		
		//退货单号
		String returnNo = (String) params.get("RETURN_NO");
		if(StringUtil.isEmpty(returnNo)){
			return null;
		}		
		if ("true".equals(Consts.INVOKE_SYS_FLG)) {			
			try {				
				String jsonStr = LogicUtil.queryReturnTrace(returnNo);
						
				rst = new Gson().fromJson(jsonStr,
						new TypeToken<ArrayList<ReutrnRep>>() {
						}.getType());
				
			} catch (Exception e) {	
				rst = getErrorData();
				//e.printStackTrace();
			}
		} else {
			rst = getTestDataModel(returnNo);
		}
		
		if(rst == null){
			rst = new ArrayList();
		}
				
		return formatList(rst);
	}
	
	private List formatList(List<ReutrnRep> source){
		List rst = null;
		
		if(source != null && source.size() > 0){
			rst = new ArrayList();
			Map<String,String> data = null;
			for(ReutrnRep model : source){
				data = new HashMap();
				data.put("RETURN_NO", model.getReturn_no());
				data.put("CUST_NO", model.getCust_no());
				data.put("CUST_NAME", model.getCust_name());
				data.put("PRD_NO", model.getPrd_no());
				data.put("PRD_SN", model.getPrd_sn());
				data.put("PRD_NAME", model.getPrd_name());
				data.put("PRD_SPEC", model.getPrd_spec());
				data.put("STORE_IN_NUM", String.valueOf(model.getStore_in_num()));
				data.put("RETURN_PRICE", String.valueOf(model.getReturn_price()));
				data.put("RETURN_AMOUNT", String.valueOf(model.getReturn_amount()));
				data.put("STORE_IN_TIME", model.getStore_in_time());
				data.put("STORE_IN_USERNAME", model.getStore_in_username());
				data.put("FNC_AUDIT_TIME", model.getFnc_audit_time());
				data.put("FNC_AUDIT_USERNAME", model.getFnc_audit_username());								
				data.put("Status", model.getStatus());
				data.put("Desc", model.getDesc());
				
				rst.add(data);
			}
			
		}
		
		return rst;
	}
	
	private List getErrorData(){
		List rst = new  ArrayList();
		ReutrnRep temp = new ReutrnRep();
		temp.setStatus("false");
		temp.setDesc("查询失败！");
		rst.add(temp);
		
		return rst;
	}
	
	private List getTestDataModel(String queryParam){
		List rst = new  ArrayList();
		
		for(int i = 0; i < 50; i++){				
			String temp = "test"+queryParam + Math.round(Math.random()* 100);		
			rst.add(getModel(temp));
		}
				
		return rst;
	}
	
	private ReutrnRep getModel(String temp){
		ReutrnRep model = new ReutrnRep();
		
		model.setReturn_no(temp);
		model.setCust_no(temp);
		model.setCust_name(temp);
		model.setPrd_sn(temp);
		model.setPrd_no(temp);
		model.setPrd_name(temp);
		model.setPrd_spec(temp);
		model.setStore_in_num(0.0f);
		model.setReturn_price(0.0f);
		model.setReturn_amount(0.0f);
		model.setStore_in_time("");//new Date()
		model.setStore_in_username(temp);
		model.setFnc_audit_time("");//new Date()
		model.setFnc_audit_username(temp);
		
		return model;
	}
	
	

}
