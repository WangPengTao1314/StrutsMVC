/**
 * 
 */
package com.hoperun.base.syslog.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.hoperun.base.syslog.service.SyslogService;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;

/**
 * @author gu_hongxiu
 *
 */
public class SyslogServiceImpl extends BaseService implements SyslogService {

	/**
     * 分页查询
     * Description :.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		
		return this.pageQuery("Syslog.pageQuery", "Syslog.pageCount", params, pageNo);
	}

	/**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
	@Override
	@SuppressWarnings("unchecked")
	public Map load(String param) {
		Map params = new HashMap();
        params.put("SYSLOG_ID", param);
        return (Map) load("Syslog.loadById", params);
	}

}
