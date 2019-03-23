package com.hoperun.erp.sale.expenseorder.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.promoexpen.model.PromoexpenModel;
import com.hoperun.erp.sale.expenseorder.model.ExpenseorderChildModel;
import com.hoperun.erp.sale.expenseorder.model.ExpenseorderModel;
import com.hoperun.sys.model.UserBean;

public interface ExpenseorderService extends IBaseService {
	  /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuerySH(Map<String,String> params, int pageNo);
	  /**
	 * @主从保存编辑
	 * @Description :
	 * @param EXPENSE_ORDER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String EXPENSE_ORDER_ID, ExpenseorderModel model,
			List<ExpenseorderChildModel> chldList,  UserBean userBean);
 
	/**
	 * @param params
	 * @return
	 */
	public boolean txUpdateById(Map<String,String> params);
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param EXPENSE_ORDER_ID
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String EXPENSE_ORDER_ID);
	
	public List <ExpenseorderChildModel> childQuery(String EXPENSE_ORDER_ID);
	
	public List <ExpenseorderChildModel> loadChilds(Map <String, String> params) ;
	  
	/**
	 * 子表保存
	 * @return
	 */
    public boolean txChildEdit(String EXPENSE_ORDER_ID, List<ExpenseorderChildModel> chldList,UserBean userBean);
    
    
    public void txBatch4DeleteChild(Map<String,String> params);
    
    
    /**
	 * 查看推广费
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pagePromoexpenQuery(Map params, int pageNo);
    
	/**
	 * @param RELATE_ORDER_NOS
	 * @return
	 */
	public String queryRelateOrder(String RELATE_ORDER_NOS);
	
	/**
	 * @param PRMT_COST_REQ_ID
	 * @return
	 */
	public ExpenseorderModel expenseOrderQuery(String EXPENSE_ORDER_ID);
	
	/**
	 * @param map
	 * @return
	 */
	public List downQuery(Map<String,String> map);
   
	/**
	 * @param map
	 * @return
	 */
	public List downQuerySH(Map<String,String> map);
	
	/**
	 * @param CHANN_ID
	 * @param YEAR
	 * @return
	 */
	public Map<String,Object> queryJudgeModelT(String CHANN_ID,String YEAR);
	
	/**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public String queryChannExpenseAmount(String CHANN_ID,String YEAR);
    
    /**
	 * @param EXPENSE_ORDER_ID
	 * @return
	 */
	public List <ExpenseorderModel> childQueryT(String EXPENSE_ORDER_ID);
	
}
