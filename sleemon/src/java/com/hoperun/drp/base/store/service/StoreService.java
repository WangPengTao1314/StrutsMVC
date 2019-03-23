/**
 * prjName:喜临门营销平台
 * ucName:库房库位信息
 * fileName:StoreService
*/
package com.hoperun.drp.base.store.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.store.model.StoreModel;
import com.hoperun.drp.base.store.model.StoreModelChld;
import com.hoperun.sys.model.UserBean ;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-13 14:01:22
 */
public interface StoreService extends IBaseService {
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
	 * @param STORE_ID
	 * @param StoreModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STORE_ID, StoreModel obj,List<StoreModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STORE_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STORE_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STORE_ID the STORE_ID
     * @return the list
     */
    public List <StoreModelChld> childQuery(String STORE_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STORG_IDs the STORG_IDs
     * 
     * @return the list
     */
    public List <StoreModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STORE_ID the STORE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STORE_ID, List <StoreModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param STORG_IDs the STORG_IDs
     */
    public void txBatch4DeleteChild(String STORG_IDs);
	 /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);

    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);
    
    /**
     * 子表状态更改
     * @param params
     * @return boolean
     */
    public boolean changeChildState(Map<String, String> params);
    /**
     * 根据库房编号查询是否有重复数据
     * @param NO
     * @return
     */
    boolean queryRepeatNo(String NO);
}