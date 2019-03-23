package com.hoperun.drp.areaser.senddirectnotice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.areaser.senddirectnotice.model.SenddirectnoticeModelChld;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.sys.model.UserBean;

public interface SenddirectnoticeService extends IBaseService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * @分页查询销售订单
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public List<Map<String,String>> querySaleOrder(Map<String,String> params);
	/**
	 * @主表详细页面
	 * @param param
	 *            SALE_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String BASE_DELIVER_NOTICE_ID);
	/**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    public List <SaleorderModelChld> childQuery(String BASE_DELIVER_NOTICE_ID);
    //反填销售订单
    public void txbackSale(String BASE_DELIVER_NOTICE_ID ,List<SenddirectnoticeModelChld> modelList,UserBean userBean)throws Exception;
    /**
     * 根据总部直发通知单获取总货品数量
     * @param BASE_DELIVER_NOTICE_ID
     * @return
     */
    public String getTotalNum(String BASE_DELIVER_NOTICE_ID);
}
