package com.hoperun.drp.report.querystock.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.report.querystock.model.StoreModel;

/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2014-05-14 14:13:12
 */

public interface QuerystockService extends IBaseService {
   
	/**
	 * 查询库存数据
	 * @return
	 */
	public  List<StoreModel>  queryStoreAcct(Map params);
}
