package com.hoperun.erp.sale.budgetquota.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaTree;
import com.hoperun.sys.model.UserBean;

public interface BudgetquotaService extends IBaseService{
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
    
    public Map<String,String>loadBatchById(String param);
    
    /**
     * 编辑：新增/删除
     * Description :.
     * @param userBean the user bean
     * @return the string
     */
    public String txEdit(String BUDGET_QUOTA_ID, BudgetquotaModel model, UserBean userBean);
    
    public void txStartById(Map<String, String> params);
    public void txStopById(Map<String, String> params);
	
    
    /**
     * 树
     * @return
     * @throws Exception
     */
    public List <BudgetquotaTree> getTree() throws Exception;
    
    /**
     * 同一个科目下 年份 月份 季度不能重复设置额度
     * @param model
     * @return
     */
    public boolean isRepeatBudgetItem(String BUDGET_QUOTA_ID,BudgetquotaModel model);
    /**
     * 批量编辑.
     * 
     */
    public void txBatchEdit(String BUDGET_QUOTA_ID, BudgetquotaModel model, UserBean userBean);
    
    /**
     * @param BUDGET_QUOTA_ID
     * @return
     */
    public BudgetquotaModel queryJudgeModelByQuota(String BUDGET_QUOTA_ID);
    

}
