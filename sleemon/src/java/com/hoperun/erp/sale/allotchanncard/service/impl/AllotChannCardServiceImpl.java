package com.hoperun.erp.sale.allotchanncard.service.impl;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.erp.sale.allotchanncard.service.AllotChannCardService;

public class AllotChannCardServiceImpl extends BaseService implements AllotChannCardService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("AllotChannCard.pageQuery", "AllotChannCard.pageCount",params, pageNo);
	}
	
	/**
	 * 分配卡券给加盟商 
	 */
	public void txUpdateCard(Map<String,String> paramMap){
		this.update("AllotChannCard.updateMarketCardByIds", paramMap);
	}
	
	/**
	 * @查询已分配为销售的卡券
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageNoSaleQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("AllotChannCard.pageNoSaleQuery", "AllotChannCard.pageNoSaleCount",params, pageNo);
	}

}
