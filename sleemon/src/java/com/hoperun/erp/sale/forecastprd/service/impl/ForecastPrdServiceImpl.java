package com.hoperun.erp.sale.forecastprd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.forecastprd.service.ForecastPrdService;

public class ForecastPrdServiceImpl extends BaseService implements ForecastPrdService{

	
	
	/**
	 * @查询
	 * @param params   map
	 * @param pageNo
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("ForecastPrd.pageQuery", "ForecastPrd.pageCount",
				params, pageNo);
	}
	
	/**
	 * 货品列表
	 * @param params
	 * @return
	 */
	 public List<Map<String,String>> childQuery(Map<String,String> params){
		 return this.findList("ForecastPrd.queryPrdByparams", params);
	 }
	 
	 /**
	  * 保存
	  * @param params
	  */
	 public void txEdit(String PRD_IDS,String DELETE_IDS){
		 Map<String,String>params = new HashMap<String,String>();
		 if(!StringUtil.isEmpty(PRD_IDS)){
			 params.put("PRD_IDS", PRD_IDS);
			 this.update("ForecastPrd.updatePrd", params);
		 }
		 txDelete(DELETE_IDS);
	 }
	 
	 /**
	  * 取消上报
	  * @param DELETE_IDS
	  */
	 public void txDelete(String DELETE_IDS){
		 if(!StringUtil.isEmpty(DELETE_IDS)){
			 this.update("ForecastPrd.deleteByIds", DELETE_IDS);
		 }
	 }
}
