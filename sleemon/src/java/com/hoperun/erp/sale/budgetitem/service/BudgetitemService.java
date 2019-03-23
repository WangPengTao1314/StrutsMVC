package com.hoperun.erp.sale.budgetitem.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.budgetitem.model.BudgetitemModel;
import com.hoperun.erp.sale.budgetitem.model.BudgetitemTree;
import com.hoperun.sys.model.UserBean;

public interface BudgetitemService extends IBaseService{
	
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
     * 更新记录
     * Description :.
     * @param params the params
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, Object> params);
    
    /**
     * 删除记录
     * Description :.
     * @param userBean the user bean
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params);
    

    /**
     * 详细信息.
     * @param param the param
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);



    /**
     * 编辑：新增/删除
     * Description :.
     * @param userBean the user bean
     * @return the string
     */
    public String txEdit(String BUDGET_ITEM_ID, BudgetitemModel model, UserBean userBean);
    
    /**
     * 树
     * @return
     * @throws Exception
     */
    public List <BudgetitemTree> getTree() throws Exception;
    
    /**
     * 判断编号重复
     * @param BUDGET_ITEM_NO
     * @return
     */
    public boolean isRepeat(String BUDGET_ITEM_NO);
    
    public void txStartById(Map<String, String> params);
    public void txStopById(Map<String, String> params);
	

}
