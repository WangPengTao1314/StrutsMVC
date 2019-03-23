/**
 * prjName:喜临门营销平台
 * ucName:调拨申请维护
 * fileName:AllocateService
*/
package com.hoperun.drp.store.allocate.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.allocate.model.AllocateModel;
import com.hoperun.drp.store.allocate.model.AllocateModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-09-05 13:29:12
 */
public interface AllocateService extends IBaseService {
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
	 * @param ALLOCATE_ID
	 * @param AllocateModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String ALLOCATE_ID, AllocateModel obj,List<AllocateModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param ALLOCATE_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ALLOCATE_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ALLOCATE_ID the ALLOCATE_ID
     * @return the list
     */
    public List <AllocateModelChld> childQuery(String ALLOCATE_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param ALLOCATE_DTL_IDs the ALLOCATE_DTL_IDs
     * 
     * @return the list
     */
    public List <AllocateModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ALLOCATE_ID the ALLOCATE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String ALLOCATE_ID, List <AllocateModelChld> modelList,String ALLOC_OUT_STORE_ID,String actionType);
	 /**
     * * 子表的批量删除
     * @param ALLOCATE_DTL_IDs the ALLOCATE_DTL_IDs
     */
    public void txBatch4DeleteChild(String ALLOCATE_DTL_IDs);
}