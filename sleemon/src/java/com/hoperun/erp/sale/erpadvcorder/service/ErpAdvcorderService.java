package com.hoperun.erp.sale.erpadvcorder.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcGiftModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderChldModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpPaymentDtlModel;
import com.hoperun.sys.model.UserBean;

public interface ErpAdvcorderService extends IBaseService {
	
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
	 * @param ADVC_ORDER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String ADVC_ORDER_ID, ErpAdvcorderModel model,
			List<ErpAdvcorderChldModel> chldList,  UserBean userBean);
	
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
	public Map<String,Object> load(String ADVC_ORDER_ID);
	
	public List <ErpAdvcorderChldModel> childQuery(String ADVC_ORDER_ID);
	
	public List <ErpAdvcorderChldModel> loadChilds(Map <String, String> params);
	/**
	 * 子表保存
	 * @return
	 */
    public boolean txChildEdit(String ADVC_ORDER_ID, 
    		List<ErpAdvcorderChldModel> chldList,UserBean userBean);
    
    
    public void txBatch4DeleteChild(Map<String,String> params);
    
    /**
	 * 付款明细编辑
	 */
	public void txPaymentEdit(String ADVC_ORDER_ID, List<ErpPaymentDtlModel> chldList,
			UserBean userBean);
	
	/**
	 * 根据主表ID查询付款明细
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public List<ErpPaymentDtlModel> paymentQuery(String ADVC_ORDER_ID);
	
	/**
	 * 付款明细
	 * @param params
	 * @return
	 */
	public List<ErpPaymentDtlModel> loadPayments(Map<String, String> params);
	/**
	 * 删除付款明细
	 */
	public void txBatch4DeletePayment(Map<String, String> params) ;
	
	
	/**
	 *   赠品信息编辑
	 */
	public void txGiftEdit(String ADVC_ORDER_ID, List<ErpAdvcGiftModel> chldList,
			UserBean userBean);
	
	/**
	 * 根据主表ID查询 赠品
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public List<ErpAdvcGiftModel> giftQuery(String ADVC_ORDER_ID);
	
	
	/**
	 * 查询 赠品
	 * @param params
	 * @return
	 */
	public List<ErpAdvcGiftModel> loadGifts(Map<String, String> params) ;
	
	
	/**
	 * 删除 赠品明细
	 */
	public void txBatch4DeleteGift(Map<String, String> params);
	
	
	/**
	 * 查询付款明细的和
	 * @return
	 */
	public double querySumPaymentDel(Map<String,String>paramMap);

}
