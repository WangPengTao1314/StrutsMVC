package com.hoperun.drp.areaser.sotoadvorder.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.areaser.sotoadvorder.model.SotoadvorderGoodModelChld;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.sys.model.UserBean;

public interface SotoadvorderService extends IBaseService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	public IListPage query(Map params, int pageNo);
	/**
	 * @主表详细页面
	 * @param param
	 *            SALE_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String SALE_ORDER_ID);
	
	/**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    public List <SaleorderModelChld> childQuery(String SALE_ORDER_ID);
    
    /**
	 * 按预订单id获取分组后的货品信息
	 */
	public List findGroInfo(Map<String, String> map) ;
	/**
	 * 根据 销售订单明细ID查询 货品的库存量
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> loadProductStoreNum(Map<String, String> map);
	/**
	 * 新增销售订单
	 * @param SALE_ORDER_IDS
	 * @param chldModelList
	 * @param userBean
	 * @param map
	 */
	public void txEdit(String SALE_ORDER_IDS,List <SotoadvorderGoodModelChld> chldModelList,UserBean userBean, Map<String,String> map,String REBATEFLAG,String REBATEDSCT);
	/**
	 * 根据渠道id查询渠道信息级别
	 * @param CHANN_ID
	 * @return
	 */
	public Map<String,String> getChannInfoLv(String CHANN_ID);
}

