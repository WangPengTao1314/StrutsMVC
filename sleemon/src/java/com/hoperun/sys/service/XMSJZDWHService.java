/**
 * prjName:喜临门营销平台
 * ucName:项目数据字典维护
 * fileName:SjzdwhService
*/
package com.hoperun.sys.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.XMSJZDWHModel;
import com.hoperun.sys.model.XMSJZDWHModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-30 10:18:20
 */
public interface XMSJZDWHService extends IBaseService {
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
	 * @param DATA_ID
	 * @param SjzdwhModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String DATA_ID, XMSJZDWHModel obj,List<XMSJZDWHModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param DATA_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String DATA_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param DATA_ID the DATA_ID
     * @return the list
     */
    public List <XMSJZDWHModelChld> childQuery(String DATA_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param DATA_DTL_IDs the DATA_DTL_IDs
     * 
     * @return the list
     */
    public List <XMSJZDWHModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param DATA_ID the DATA_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String DATA_ID, List <XMSJZDWHModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param DATA_DTL_IDs the DATA_DTL_IDs
     */
    public void txBatch4DeleteChild(String DATA_DTL_IDs);
}