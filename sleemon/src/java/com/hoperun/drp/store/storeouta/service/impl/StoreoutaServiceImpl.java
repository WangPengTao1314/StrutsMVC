package com.hoperun.drp.store.storeouta.service.impl;

/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:StoreoutaServiceImpl
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.store.storeouta.model.StoreoutaModelChld;
import com.hoperun.drp.store.storeouta.service.StoreoutaService;

/**
 * *@serviceImpl *@func *@version 1.1 *@author lyg *@createdate 2013-08-11
 * 17:17:29
 */
public class StoreoutaServiceImpl extends BaseService implements
StoreoutaService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Storeouta.pageQuery", "Storeouta.pageCount",
				params, pageNo);
	}


	/**
	 * @详细
	 * @param param
	 *            STOREOUT_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Storeouta.loadById", param);
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param STOREOUT_ID
	 *            the STOREOUT_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StoreoutaModelChld> childQuery(String STOREOUT_ID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("STOREOUT_ID", STOREOUT_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Storeouta.queryChldByFkId", params);
	}

	public List downQuery(Map<String,String> map){
		return this.findList("Storeouta.downGetById", map);
	}
	
}