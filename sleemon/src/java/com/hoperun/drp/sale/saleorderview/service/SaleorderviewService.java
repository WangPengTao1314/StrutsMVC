package com.hoperun.drp.sale.saleorderview.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.saleorderview.model.SaleorderviewChldModel;

public interface SaleorderviewService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	/**
	 * @订货订单查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage goodsPageQuery(Map<String, String> params, int pageNo);
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<SaleorderviewChldModel> childQuery(String SALE_ORDER_ID);
	/**
	 * * 根据订货订单主表Id查询子表记录
	 * 
	 * @param GOODS_ORDER_ID
	 *            the GOODS_ORDER_ID
	 * @return the list
	 */
	public List<SaleorderviewChldModel> goodsChildQuery(String GOODS_ORDER_ID);
	
	public Map<String, String> load(String SALE_ORDER_ID);
	public List <GoodsorderhdModelTrace> traceQuery(String SALE_ORDER_ID);
	 /**
	 * 加载渠道
	 * @param param CHANN_ID
	 * @return the map< string, string>
	 */
	public Map<String,Object> loadChann(String CHANN_ID);
	/**
     * 查询导出数据
     */
    public  List <Map> qrySaleOrderExp(Map params);
}
