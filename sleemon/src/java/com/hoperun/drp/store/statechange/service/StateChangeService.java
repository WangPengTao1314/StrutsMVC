/**
 * prjName:喜临门营销平台
 * ucName:形态转换
 * fileName:StateChangeService
*/
package com.hoperun.drp.store.statechange.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.statechange.model.StateChangeModel;
import com.hoperun.drp.store.statechange.model.StateChangeModelChild;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-05 14:00:34
 */
public interface StateChangeService extends IBaseService {
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
	 * @return 
	 */
	public void txEdit(String STATE_CHANGE_ID, StateChangeModel obj,List<StateChangeModelChild> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STATE_CHANGE_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STATE_CHANGE_ID the STATE_CHANGE_ID
     * @return the list
     */
    public List<Map<String,Object>> childQuery(String STATE_CHANGE_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * 
     * @return the list
     */
    public List <StateChangeModelChild> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STATE_CHANGE_ID the STATE_CHANGE_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STATE_CHANGE_ID, List <StateChangeModelChild> modelList);
	 /**
     * * 子表的批量删除
     * @param STATE_CHANGE_DTL_IDS the STATE_CHANGE_DTL_IDS
     */
    public void txBatch4DeleteChild(String STATE_CHANGE_DTL_IDS);
    /**
     * 提交
     * @param STATE_CHANGE_ID
     */
    public void txCommit(String STATE_CHANGE_ID);
    
}