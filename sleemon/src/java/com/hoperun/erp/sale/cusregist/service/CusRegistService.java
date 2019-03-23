package com.hoperun.erp.sale.cusregist.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleChldModel;
import com.hoperun.sys.model.UserBean;

public interface CusRegistService extends IBaseService {
	
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * 加载
	 * @param CARD_SALE_ORDER_DTL_ID
	 * @return
	 */
	public Map<String,Object> load(String CARD_SALE_ORDER_DTL_ID);
	/**
	 * 根据看清编号查询卡券信息
	 * @param MARKETING_CARD_NO
	 * @return
	 */
	public Map<String,String> loadCard(String MARKETING_CARD_NO);
	/**
	 * 编辑
	 * @param childModel 卡券信息
	 * @param userBean
	 */
	public void txEdit(MarketcardSaleChldModel childModel,UserBean userBean);
	 
	/**
	 * 根据卡券编号查询   未销售的卡券信息 
	 * @param MARKETING_CARD_NO
	 * @return
	 */
	public Map<String,String> loadNoSaleCard(String MARKETING_CARD_NO);
	
	/**
	 * 更新
	 * @param params
	 */
	public void txUpdateCardMx(Map<String,String>params );
}
