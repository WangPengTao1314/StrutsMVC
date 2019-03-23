package com.hoperun.drp.report.queryProStatus.service.impl;

import java.math.BigDecimal;
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
import com.hoperun.drp.report.queryProStatus.model.ProStatus;
import com.hoperun.drp.report.queryProStatus.service.QueryProStatusService;

public class QueryProStatusServiceImpl extends BaseService implements QueryProStatusService {
	
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

}
