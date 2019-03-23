/**
 * 
 */
package com.hoperun.base.syslog.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;

/**
 * 
 * @module 系统日志
 * @author gu_hongxiu
 *
 */
public interface SyslogService extends IBaseService {
	
	/**
     * 主表分页查询
     * Description :.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    
    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    @SuppressWarnings("unchecked")
    public Map load(String param);

}
