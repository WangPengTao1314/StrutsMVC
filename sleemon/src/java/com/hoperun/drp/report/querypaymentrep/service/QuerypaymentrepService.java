package com.hoperun.drp.report.querypaymentrep.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.service.IBaseService;

/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zhu_changxia
 * *@createdate 2014-05-14 14:13:12
 */

public interface QuerypaymentrepService extends IBaseService {
	
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public List queryPayMentRep(Map params);
	
	public Map<String,Object> loadChann(String CHANN_ID);
	
	

}
