package com.hoperun.erp.sale.allotchanncard.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;

public interface AllotChannCardService extends IBaseService{
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * 分配卡券给加盟商 
	 */
	public void txUpdateCard(Map<String,String> paramMap);
	
	/**
	 * @查询已分配为销售的卡券
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageNoSaleQuery(Map<String,String> params, int pageNo);
 

}
