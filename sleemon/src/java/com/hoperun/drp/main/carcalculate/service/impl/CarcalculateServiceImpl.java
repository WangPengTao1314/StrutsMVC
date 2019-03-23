package com.hoperun.drp.main.carcalculate.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.main.carcalculate.service.CarcalculateService;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelChld;

public class CarcalculateServiceImpl extends BaseService implements CarcalculateService {
	
	  /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Carcalculate.pageQuery", "Carcalculate.pageCount",params, pageNo);
	}
	
	
	/**
	 * 明细编辑
	 * @param model
	 */
	public String childEdit(GoodsorderhdModelChld model){
		Map<String,Object> params = new HashMap<String,Object>();
		String GOODS_ORDER_DTL_ID = StringUtil.uuid32len();
		params.put("GOODS_ORDER_DTL_ID",GOODS_ORDER_DTL_ID);
		params.put("PRD_ID",model.getPRD_ID());
	    params.put("PRD_NO",model.getPRD_NO());
	    params.put("PRD_NAME",model.getPRD_NAME());
	    params.put("PRD_SPEC",model.getPRD_SPEC());
	    params.put("PRD_COLOR",model.getPRD_COLOR());
	    params.put("BRAND",model.getBRAND());
	    params.put("STD_UNIT",model.getSTD_UNIT());
	    params.put("PRICE",model.getPRICE());
	    params.put("ORDER_NUM",model.getORDER_NUM());
        params.put("GOODS_ORDER_ID",model.getGOODS_ORDER_ID()); 
		
		this.insert("Carcalculate.insertChld", params);
		return GOODS_ORDER_DTL_ID;
	}
	
	/**
	 * 明细删除
	 * @param GOODS_ORDER_DTL_ID
	 */
	public boolean childDelete(String GOODS_ORDER_DTL_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("GOODS_ORDER_DTL_ID", GOODS_ORDER_DTL_ID);
		return update("Carcalculate.deleteChldById", params);
	}
	
	/**
	 * 查询容积
	 * @return
	 */
	public Map<String,Object> queryCarVolume(String TRUCK_TYPE){
		Map<String,String> params = new HashMap<String,String>();
		params.put("TRUCK_TYPE", TRUCK_TYPE);
		return (Map<String, Object>) this.load("Carcalculate.queryCar", params);
	}

}
