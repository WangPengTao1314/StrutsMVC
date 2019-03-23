package com.hoperun.drp.base.promote.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.promote.model.PromoteModel;
import com.hoperun.sys.model.UserBean;
/***
 * 活动
 * @author zhang_zhongbin
 *
 */
public interface PromoteService extends IBaseService {
	
	 /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	
	
	public String txEdit(String PROMOTE_ID,PromoteModel model, UserBean userBean);
	
	  /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);
    
    
    /**
     * 删除记录
     */
    public boolean txDelete(String PROMOTE_ID, UserBean userBean);
    

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
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);
 
    
    /**
     * 判断是否存在重复编号.
     * 
     * @param params the params
     * @return true, 无重复
     */
    public boolean queryForInt(Map <String, String> params);
    
    
    
    

}
