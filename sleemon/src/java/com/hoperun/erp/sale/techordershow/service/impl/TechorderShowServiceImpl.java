/**
 * prjName:喜临门营销平台
 * ucName:工艺单维护
 * fileName:TechorderServiceImpl
*/
package com.hoperun.erp.sale.techordershow.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.erp.sale.techordershow.model.TechorderShowModelChld;
import com.hoperun.erp.sale.techordershow.service.TechorderShowService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-10-12 18:31:07
 */
public class TechorderShowServiceImpl extends BaseService implements TechorderShowService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("TechorderShow.pageQuery", "TechorderShow.pageCount",params, pageNo);
	}
	
	
	/**
	 * @详细
	 * @param param TECH_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("TechorderShow.loadById", param);
	}
	
	
	/**
     * * 根据主表Id查询子表记录
     * @param TECH_ORDER_ID the TECH_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <TechorderShowModelChld> childQuery(String TECH_ORDER_ID) {
        Map params = new HashMap();
        params.put("TECH_ORDER_ID", TECH_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("TechorderShow.queryChldByFkId", params);
    }
}