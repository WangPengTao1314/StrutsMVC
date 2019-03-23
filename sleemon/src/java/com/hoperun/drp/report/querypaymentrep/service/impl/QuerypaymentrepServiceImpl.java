package com.hoperun.drp.report.querypaymentrep.service.impl;

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
import com.hoperun.drp.report.querypaymentrep.model.TestPojo;
import com.hoperun.drp.report.querypaymentrep.service.QuerypaymentrepService;

/**
 * *@serviceImpl
 * *@func 应收余额查询
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2014-05-04 14:13:12
 */

public class QuerypaymentrepServiceImpl extends BaseService implements QuerypaymentrepService{

	
	public Map<String,Object> loadChann(String CHANN_ID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANN_ID);
        return (Map<String, Object>) load("chann.loadById", params);
	}
	
	 public List  queryPayMentRep(Map params) { 
		List  list = null;
		String CustNo = (String) params.get("CustNo");
		String OrgCode = (String) params.get("OrgCode");
		
		if("true".equals(Consts.INVOKE_SYS_FLG)){
			try {
				if(!CustNo.equals("") || !OrgCode.equals("")){
				  String jsonStr = LogicUtil.queryPayMent(CustNo,OrgCode);
				  list = new Gson().fromJson(jsonStr,
							new TypeToken<ArrayList<TestPojo>>() {
							}.getType());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
		  list = getTestDataModel(CustNo,OrgCode);
		}
		return formatList(list);
	}
	 

		
		private List formatList(List<TestPojo> source){
			
			List rst = null;
			if(source != null && source.size() > 0){
				rst = new ArrayList();
				Map<String,Object> data = null;
				for(TestPojo model : source){
					data = new HashMap<String,Object>();
					String status = model.getStatus();
					if(!"false".equals(status)){
					data.put("CustNo", model.getCustNo());
					data.put("CustName", model.getCustName());
					data.put("PayableAmount", String.format("%.2f", model.getPayableAmount()));
					data.put("AcctAmount", String.format("%.2f", StringUtil.getDouble(model.getAcctAmount())));
					data.put("Status", model.getStatus());
					data.put("Desc", model.getDesc());
					rst.add(data);
					}
					
				}
			}
			return rst;
		}
		
		private List getTestDataModel(String CustNo,String OrgCode){
			List rst = new  ArrayList();
			
			if(CustNo != null && !"".equals(CustNo)){
				String temp = "test"+CustNo;
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
		
		private TestPojo getModel(String temp){
			
			TestPojo  model = new TestPojo();
			
			model.setCustNo("000001");
			model.setCustName("南京新百");
			model.setPayableAmount(Float.parseFloat("1000.23"));
//			model.setAcctAmount(Float.parseFloat("443434"));
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
			data.put("CustNo", temp); 
			data.put("CustName", temp); 
			data.put("PayableAmount", temp); 
			data.put("AcctAmount", temp); 	
			return data;
		}
}
