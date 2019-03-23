package com.hoperun.erp.sale.forecastchann.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.forecastchann.service.ForecastchannService;

public  class ForecastchannServiceImpl extends BaseService implements ForecastchannService{

	
	/**
	 * @查询
	 * @param params   map
	 * @param pageNo
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("ForecastChann.pageQuery", "ForecastChann.pageCount",
				params, pageNo);
	}
	
	/**
	 * 渠道列表
	 * @param params
	 * @return
	 */
	 public List<Map<String,String>> childQuery(Map<String,String> params){
		 return this.findList("ForecastChann.queryPrdByparams", params);
	 }
	 
	 /**
	  * 保存
	  * @param params
	  */
	 public void txEdit(String CHANN_IDS,String DELETE_IDS){
		 Map<String,String>params = new HashMap<String,String>();
		 if(!StringUtil.isEmpty(CHANN_IDS)){
			 params.put("CHANN_IDS", CHANN_IDS);
			 this.update("ForecastChann.updateChann", params);
		 }
		 txDelete(DELETE_IDS);
	 }
	 
	 /**
	  * 取消上报
	  * @param DELETE_IDS
	  */
	 public void txDelete(String DELETE_IDS){
		 if(!StringUtil.isEmpty(DELETE_IDS)){
			 this.update("ForecastChann.deleteByIds", DELETE_IDS);
		 }
	 }

}
