package com.hoperun.drp.sale.delivershow.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.delivershow.service.DelivershowService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;

public class DelivershowServiceImpl extends BaseService implements DelivershowService {
	/**
	 * 查询并分页.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Delivershow.pageQuery",
				"Delivershow.pageCount", params, pageNo);
	}
	
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param DELIVER_ORDER_ID 主表ID
	 *             
	 * @return the list
	 */
	public List<TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DELIVER_ORDER_ID", DELIVER_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Delivershow.queryChldByFkId", params);
	}
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID){
		return (Map<String, String>) this.load("Delivershow.loadById", DELIVER_ORDER_ID);
	}
	

}
