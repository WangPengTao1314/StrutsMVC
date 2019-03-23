/**
 * prjName:喜临门营销平台
 * ucName:推广费用报销单维护
 * fileName:PromoreimService
*/
package com.hoperun.drp.oamg.promoreim.service;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.promoreim.model.PromoreimModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-25 19:49:05
 */
public interface PromoreimService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @编辑
	 * @Description :
	 * @param PRMT_COST_REIT_ID
	 * @param PromoreimModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRMT_COST_REIT_ID, PromoreimModel obj, UserBean userBean);
	
	/**
	 * @删除
	 * @param PRMT_COST_REIT_ID.
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @详细页面
	 * @param param PRMT_COST_REIT_ID
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
    
    /**
     * 加载多文件路径
     * @param param
     * @return
     */
    public String loadFiles(String param);
}