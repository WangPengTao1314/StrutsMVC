package com.hoperun.erp.sale.erpadvcorderstatements.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.erpadvcorderstatements.model.ErpAdvcorderStatementsChldModel;
import com.hoperun.erp.sale.erpadvcorderstatements.model.ErpAdvcorderStatementsModel;
import com.hoperun.sys.model.UserBean;

public interface ErpAdvcorderStatementsService extends IBaseService {
	
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
	 * @param ADVC_STATEMENTS_ORDER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String ADVC_STATEMENTS_ORDER_ID, ErpAdvcorderStatementsModel model,
			List<ErpAdvcorderStatementsChldModel> chldList,  UserBean userBean);
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param  
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String ADVC_STATEMENTS_ORDER_ID);
	
	public List <ErpAdvcorderStatementsChldModel> childQuery(String ADVC_STATEMENTS_ORDER_ID);
	
	public List <ErpAdvcorderStatementsChldModel> loadChilds(Map <String, String> params);
	/**
	 * 子表保存
	 * @return
	 */
    public void txChildEdit(String ADVC_STATEMENTS_ORDER_ID, 
    		List<ErpAdvcorderStatementsChldModel> chldList,UserBean userBean);
    
    
    public void txBatch4DeleteChild(String ADVC_STATEMENTS_ORDER_ID,Map<String,String> params);
    
    /**
	 * 查询付款方式
	 * @param params
	 * @return
	 */
	public List <Map<String,Object>> querySumPaymentDel(Map <String, String> params);
	
	   /**
     * 查询已经选择的预订单ID
     * @param ADVC_STATEMENTS_ORDER_ID
     * @return
     */
    public String queryAdvcIds(String ADVC_STATEMENTS_ORDER_ID);
    
    
}
