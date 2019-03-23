package com.hoperun.erp.sale.storeout.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.storeout.model.ErpStoreoutChildPrdModel;
import com.hoperun.erp.sale.storeout.model.ErpStoreoutModel;
import com.hoperun.sys.model.UserBean;

public interface ErpStoreoutService extends IBaseService{
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <ErpStoreoutChildPrdModel> childQuery(String DELIVER_ORDER_ID);
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param params
     * @return the list
     */
    public List <ErpStoreoutChildPrdModel> childQuery(Map<String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String STOREOUT_ID);
	
	/**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <ErpStoreoutChildPrdModel> loadChilds(Map <String, String> params) ;
	
	 /**
     * 编辑：新增
     * @param model 发运单
     * @return the string
     */
    public void txEdit(ErpStoreoutModel model,UserBean userBean);
    
 
    /**
     * 修改明细
     * @param childList
     */
    public void txUpdatChild(List<ErpStoreoutChildPrdModel> childList);
    
    public List downQuery(Map<String,String> map);
    
    /**
	 * 关闭
	 * 
	 * @param STOREOUT_ID 出库单ID
	 * @param DELIVER_ORDER_ID 发运单ID
	 * @param U9_SM_NO U9单据编号
	 * @param userBean
	 */
	public void txClose(String STOREOUT_ID,String DELIVER_ORDER_ID,String U9_SM_NO, UserBean userBean);
 
    

}
