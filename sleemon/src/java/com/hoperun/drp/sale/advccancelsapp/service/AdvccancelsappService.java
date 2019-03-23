package com.hoperun.drp.sale.advccancelsapp.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModel;
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModelChld;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModelChld;
import com.hoperun.sys.model.UserBean;

public interface AdvccancelsappService extends IBaseService {
	  /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	
	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_SEND_CANCEL_ID 
	 * @param model
	 * @param userBean.
	 *
	 * @return 
	 */
	public String txEdit(String ADVC_SEND_CANCEL_ID, AdvccancelsappModel model,List<AdvccancelsappModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ADVC_SEND_CANCEL_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String ADVC_SEND_CANCEL_ID, List <AdvccancelsappModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param ADVC_SEND_CANCEL_DTL_IDS
     */
    public void txBatch4DeleteChild(String ADVC_SEND_CANCEL_DTL_IDS);
    
	
	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 * 
	 * @return the list
	 */
	public List<AdvccancelsappModelChld> loadChilds(Map<String, String> params);
	
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_SEND_CANCEL_ID
	 * @return the list
	 */
	 public List<AdvccancelsappModelChld> childQuery(String ADVC_SEND_CANCEL_ID);
	
	/**
	 * 提交
	 * @param ADVC_SEND_CANCEL_ID 主表ID
	 */
	public void txCommit(String ADVC_SEND_CANCEL_ID,UserBean userBean);
	
	/**
	 * 审核
	 * @param ADVC_SEND_CANCEL_ID 主表ID
	 * @param ADVC_SEND_REQ_ID 预订单发货申请ID
	 */
	public void txAudit(String ADVC_SEND_CANCEL_ID,String ADVC_SEND_REQ_ID,UserBean userBean);
	
	/**
	 * 查询 已经选择的货品
	 * @param ADVC_SEND_REQ_ID 预订单发货申请ID
	 * @return
	 */
	public List<Map<String,String>> getPrdIdFrom(String ADVC_SEND_REQ_ID);
	
	  

}
