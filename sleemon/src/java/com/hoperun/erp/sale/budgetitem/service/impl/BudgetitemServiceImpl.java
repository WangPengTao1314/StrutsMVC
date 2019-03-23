package com.hoperun.erp.sale.budgetitem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.budgetitem.model.BudgetitemModel;
import com.hoperun.erp.sale.budgetitem.model.BudgetitemTree;
import com.hoperun.erp.sale.budgetitem.service.BudgetitemService;
import com.hoperun.sys.model.UserBean;

public class BudgetitemServiceImpl extends BaseService implements BudgetitemService{
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("Budgetitem.pageQuery", "Budgetitem.pageCount", params, pageNo);
    }
    
    /**
     * 增加记录 Description :.
     * @param params the params
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("Budgetitem.insert", params);
        return true;
    }
    
    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, Object> params) {
        return update("Budgetitem.updateById", params);
    }

    /**
     * 删除.
     * 
     * @param userBean the user bean
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params) {
       return update("Budgetitem.delete", params);
    }


    
    /**
     * 加载.
     * 
     */
    @SuppressWarnings("unchecked")
    public Map load(String BUDGET_ITEM_ID) {
        return (Map) load("Budgetitem.loadById", BUDGET_ITEM_ID);
    }
    
    
    /**
     * 编辑.
     * 
     */
    public String txEdit(String BUDGET_ITEM_ID, BudgetitemModel model, UserBean userBean) {
        Map <String, Object> params = new HashMap <String, Object>();
        params.put("BUDGET_ITEM_NO", model.getBUDGET_ITEM_NO()); 
        params.put("BUDGET_ITEM_NAME", model.getBUDGET_ITEM_NAME());
        params.put("BUDGET_ITEM_TYPE", model.getBUDGET_ITEM_TYPE());
        params.put("PAR_BUDGET_ITEM_ID", model.getPAR_BUDGET_ITEM_ID());
        params.put("PAR_BUDGET_ITEM_NO", model.getPAR_BUDGET_ITEM_NO());
        params.put("PAR_BUDGET_ITEM_NAME", model.getPAR_BUDGET_ITEM_NAME());
        params.put("FINAL_NODE_FLAG", model.getFINAL_NODE_FLAG());
        params.put("REMARK", model.getREMARK());
        if (StringUtils.isEmpty(BUDGET_ITEM_ID)) {
        	params.put("BUDGET_ITEM_ID", StringUtil.uuid32len());
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", "sysdate");//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
        	params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//状态
            txInsert(params);
        }else{
        	 params.put("BUDGET_ITEM_ID", BUDGET_ITEM_ID);
             params.put("UPDATOR", userBean.getXTYHID());//更新人id
             params.put("UPD_NAME", userBean.getXM());//更新人名称
             params.put("UPD_TIME", "sysdate");//更新时间
             txUpdateById(params);
             clearCaches(BUDGET_ITEM_ID);
        }
        return BUDGET_ITEM_ID;
    }
    
    /**
     * 树
     * @return
     * @throws Exception
     */
    public List <BudgetitemTree> getTree() throws Exception {
        List<BudgetitemTree> menus = this.findList("Budgetitem.queryTree", "");
        return ResourceUtil.getZTreeFromList(menus, BudgetitemTree.class);
    }
    
    
    /**
     * 判断编号重复
     * @param BUDGET_ITEM_ID
     * @return
     */
    public boolean isRepeat(String BUDGET_ITEM_NO){
    	Map <String, Object> paramMap = new HashMap <String, Object>();
    	paramMap.put("BUDGET_ITEM_NO", BUDGET_ITEM_NO);
    	int rs = this.queryForInt("Budgetitem.isRepeat", paramMap);
    	if(rs == 0){
    		return false;
    	}
    	return true;
    	
    }
    
    public void txStartById(Map<String, String> params){
    	update("Budgetitem.updateStateById", params);
    }
    
    /**
     * 修改状态为停用
     * Description :.
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public void txStopById(Map <String, String> params) {
         update("Budgetitem.updateStateById", params);
    }
    

    
    

}
