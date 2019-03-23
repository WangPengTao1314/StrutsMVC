package com.hoperun.erp.sale.forecastchann.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;

public interface ForecastchannService extends IBaseService{
	

	 /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * 货品列表
	 * @param params
	 * @return
	 */
	 public List<Map<String,String>> childQuery(Map<String,String> params);
	 /**
	  * 保存
	  * @param params
	  */
	 public void txEdit(String CHANN_IDS,String DELETE_IDS);
	 /**
	  * 取消上报
	  * @param DELETE_IDS
	  */
	 public void txDelete(String DELETE_IDS);


}
