package com.hoperun.drp.sale.saleordera.service.impl;

/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:SaleorderaServiceImpl
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.saleordera.model.SaleorderaModelChld;
import com.hoperun.drp.sale.saleordera.service.SaleorderaService;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class SaleorderaServiceImpl extends BaseService implements
SaleorderaService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Saleordera.pageQuery", "Saleordera.pageCount",
				params, pageNo);
	}


	/**
	 * @详细
	 * @param param
	 *            ADVC_ORDER_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Saleordera.loadById", param);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SaleorderaModelChld> childQuery(String SALE_ORDER_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Saleordera.queryChldByFkId", params);
	}

	public List downQuery(Map<String,String> map){
		return this.findList("Saleordera.downGetById", map);
	}
	
}