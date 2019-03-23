/**
 * prjName:喜临门营销平台
 * ucName:库存储备信息
 * fileName:ResvstoreService
*/
package com.hoperun.drp.base.resvstore.service;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.resvstore.model.ResvstoreModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-07 14:13:12
 */
public interface ResvstoreService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
     * 判断是否存在重复.
     * 
     * @param params the params
     * 
     * @return true, 无重复
     */
    public boolean queryForInt(Map <String, Object> params);
    
    /**
	 * @编辑
	 * @Description :
	 * @param RESV_STOCK_ID
	 * @param ResvstoreModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String RESV_STOCK_ID, ResvstoreModel obj, UserBean userBean);
	
	/**
	 * @删除
	 * @param RESV_STOCK_ID.
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @详细页面
	 * @param param RESV_STOCK_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param);
	
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
}