/**
 * prjName:喜临门营销平台
 * ucName:分发指令接收
 * fileName:DstrorderServiceImpl
*/
package com.hoperun.drp.sale.dstrorder.service.impl;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.drp.sale.dstrorder.model.DstrorderModelChld;
import com.hoperun.drp.sale.dstrorder.service.DstrorderService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-16 10:31:37
 */
public class DstrorderServiceImpl extends BaseService implements DstrorderService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Dstrorder.pageQuery", "Dstrorder.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Dstrorder.insert", params);
		return true;
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Dstrorder.updateById", params);
	}
	
	/**
	 * @详细
	 * @param param DSTR_ORDER_ID
	 * @param param the param
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Dstrorder.loadById", param);
	}
	
	/**
     * * 根据主表Id查询子表记录
     * @param DSTR_ORDER_ID the DSTR_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DstrorderModelChld> childQuery(String DSTR_ORDER_ID) {
        Map params = new HashMap();
        params.put("DSTR_ORDER_ID", DSTR_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Dstrorder.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param DSTR_ORDER_DTL_IDs the DSTR_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <DstrorderModelChld> loadChilds(Map <String, String> params) {
       return findList("Dstrorder.loadChldByIds",params);
    }
    
    /**
     * 修改状态为已接收
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
	@Override
	public boolean txReceivedById(Map<String, String> params) {
		 return update("Dstrorder.updStusById", params);
	}
}