package com.hoperun.drp.main.carcalculate.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelChld;

public interface CarcalculateService extends IBaseService{
	
	   /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,Object> params, int pageNo);
	
	/**
	 * 明细编辑
	 * @param model
	 */
	public String childEdit(GoodsorderhdModelChld model);
	
	/**
	 * 明细删除
	 * @param GOODS_ORDER_DTL_ID
	 */
	public boolean childDelete(String GOODS_ORDER_DTL_ID);
	
	/**
	 * 查询容积
	 * @return
	 */
	public Map<String,Object> queryCarVolume(String TRUCK_TYPE);

}
