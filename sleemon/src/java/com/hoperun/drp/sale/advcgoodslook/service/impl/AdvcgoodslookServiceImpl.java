package com.hoperun.drp.sale.advcgoodslook.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.advcgoodslook.service.AdvcgoodslookService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;

public class AdvcgoodslookServiceImpl extends BaseService implements AdvcgoodslookService{
	
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Advcgoodslook.pageQuery", "Advcgoodslook.pageCount",
				params, pageNo);
	}
	
	/**
	 * * 根据主表Id查询子表记录
	 * @param ADVC_ORDER_ID
	 * @return  
	 */
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Advcgoodslook.queryChldByFkId", params);
	}
	
	/**
	 * @详细
	 * @param param
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Advcgoodslook.loadById", param);
	}
	/**
	 * 导出
	 * @param map
	 * @return
	 */
	public List<Map<String,String>> expertExcelQuery(Map<String,String> map){
		return  this.findList("Advcgoodslook.downGet",map);
	}

}
