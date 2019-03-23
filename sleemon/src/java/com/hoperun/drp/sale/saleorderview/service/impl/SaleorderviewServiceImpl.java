package com.hoperun.drp.sale.saleorderview.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.drp.sale.saleorderview.model.SaleorderviewChldModel;
import com.hoperun.drp.sale.saleorderview.service.SaleorderviewService;

public class SaleorderviewServiceImpl extends BaseService implements SaleorderviewService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Saleorderview.pageQuery", "Saleorderview.pageCount",
				params, pageNo);
	}
	/**
	 * @订货订单查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage goodsPageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Goodsorderhd.pageQuery", "Goodsorderhd.pageCount",params, pageNo);
	}
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param SALE_ORDER_ID
	 *            the SALE_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@SuppressWarnings("unchecked")
	public List<SaleorderviewChldModel> childQuery(String SALE_ORDER_ID) {
		Map params = new HashMap();
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Saleorderview.queryChldByFkId", params);
	}
	/**
	 * * 根据订货订单主表Id查询子表记录
	 * 
	 * @param GOODS_ORDER_ID
	 *            the GOODS_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@SuppressWarnings("unchecked")
	public List<SaleorderviewChldModel> goodsChildQuery(String GOODS_ORDER_IDS) {
		Map params = new HashMap();
		params.put("GOODS_ORDER_IDS", GOODS_ORDER_IDS);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
//		return this.findList("Goodsorderhd.queryChldByFkId", params);
		return this.findList("Goodsorder.queryChldByFkMore", params);
	}
	/**
	 * @详细
	 * @param param
	 *            SALE_ORDER_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String SALE_ORDER_ID) {
		return (Map<String, String>) load("Saleorderview.loadById", SALE_ORDER_ID);
	}
	  /**
     * * 根据主表Id查询生命周期子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List <GoodsorderhdModelTrace> traceQuery(String SALE_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("SALE_ORDER_ID", SALE_ORDER_ID);
         return this.findList("Saleorderview.queryTraceByFkId", params);
    }
    
    /**
	 * 加载渠道
	 * @param param CHANN_ID
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,Object> loadChann(String CHANN_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("CHANN_ID",CHANN_ID);
        return (Map<String, Object>) load("Shopcar.getChannCreDit",params);
	}
	/**
     * 查询导出数据
     */
    public  List <Map> qrySaleOrderExp(Map params){
        return findList("Saleorderview.qrySaleOrderExp",params);
    }
}
