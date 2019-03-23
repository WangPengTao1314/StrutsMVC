/**
 * prjName:喜临门营销平台
 * ucName:入库差异确认
 * fileName:StorediffService
*/
package com.hoperun.erp.sale.storediffaff.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.storediff.model.StorediffModel;
import com.hoperun.drp.store.storediff.model.StorediffModelChld;
import com.hoperun.drp.store.storediff.model.StorediffdealModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-30 14:03:21
 */
public interface StorediffaffService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param STOREIN_DIFF_ID
	 * @param StorediffaffModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREIN_DIFF_ID, StorediffModel obj,List<StorediffModelChld> chldList, UserBean userBean);
	
	/**
	 * @主从保存编辑
	 * @Description :
	 * @param STOREIN_DIFF_ID
	 * @param StorediffaffModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txOver(String STOREIN_DIFF_ID,UserBean userBean);
	
	
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STOREIN_DIFF_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREIN_DIFF_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @return the list
     */
    public List <StorediffModelChld> childQuery(String STOREIN_DIFF_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DIFF_DTL_IDs the STOREIN_DIFF_DTL_IDs
     * 
     * @return the list
     */
    public List <StorediffModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREIN_DIFF_ID, List <StorediffModelChld> modelList,UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param STOREIN_DIFF_DTL_IDs the STOREIN_DIFF_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREIN_DIFF_DTL_IDs);
    
    /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @return the list
     */
    public List <StorediffdealModelChld> dealQuery(String STOREIN_DIFF_ID,String STOREIN_DIFF_DTL_ID);
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param DIFF_DEAL_DTL_IDs the DIFF_DEAL_DTL_IDs
     * 
     * @return the list
     */
    public List <StorediffdealModelChld> loadDealChilds(Map <String, String> params) ;
    
    /**
     * * 子表的批量删除
     * @param DIFF_DEAL_DTL_IDs the DIFF_DEAL_DTL_IDs
     */
    public void txBatch4DeleteDealChild(String DIFF_DEAL_DTL_IDs);
    /**
     * * 子表记录编辑：新增/修改
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public void txdealEdit(List <StorediffdealModelChld> modelList);
    
}