package com.hoperun.erp.sale.creditreq.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.creditreq.model.CreditReqModel;
import com.hoperun.sys.model.UserBean;

/**
 * 信用额度变更申请
 * @author zhang_zhongbin
 *
 */
public interface CreditReqService extends IBaseService{
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    
    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);
    
    /**
     * 编辑
     * @param id
     * @param model
     * @param userBean
     * @return
     */
    public String txEdit(String id,CreditReqModel model, UserBean userBean);
    
    
    
    /**
     * 删除记录
     * Description :.
     * 
     * @param modelID the model id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String modelID, UserBean userBean);
    
    public Map<String,String> loadChann(String CHANNID);

}
