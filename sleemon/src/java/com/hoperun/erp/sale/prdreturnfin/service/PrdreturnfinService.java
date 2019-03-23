/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnService
*/
package com.hoperun.erp.sale.prdreturnfin.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.prdreturnfin.model.PrdreturnfinModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public interface PrdreturnfinService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param PRD_RETURN_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String PRD_RETURN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @return the list
     */
    public List <PrdreturnfinModelChld> childQuery(String PRD_RETURN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     * 
     * @return the list
     */
    public List <PrdreturnfinModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(List <PrdreturnfinModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     */
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_IDs);
    
    public String txCommit(Map<String,String> map)throws Exception;
    
    /**
     * 退回
     * @param PRD_RETURN_ID
     * @param userBean
     */
    public void txReback(String PRD_RETURN_ID,UserBean userBean,String REMARK);
    
    
    
}