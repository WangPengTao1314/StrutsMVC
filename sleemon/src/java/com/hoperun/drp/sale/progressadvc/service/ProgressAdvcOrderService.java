package com.hoperun.drp.sale.progressadvc.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.sys.model.UserBean;

public interface ProgressAdvcOrderService extends IBaseService{
	
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	
	
	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 * 
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_ID, AdvcorderModel model, 
			List<AdvcorderModelChld> childList, UserBean userBean);
	
	
	
	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);
	
	
	/**
	 * 货品明细
	 */
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID);
	/**
	 * * 根据子表Id批量加载货品明细
	 */
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params);

	/**
	 * * 根据子表Id批量加载付款明细
	 */
	public List<AdvcorderModelGchld> loadGchilds(Map<String, String> params);
	
	
	/**
	 * 付款明细
	 * @return the list
	 */
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID);
	
	/**
	 * 编辑货品明细
	 * @param ADVC_ORDER_ID
	 * @param modelList
	 * @param userBean
	 */
	public void txChildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelChld> modelList, UserBean userBean);
	
	/**
	 * 编辑付款明细
	 * @param ADVC_ORDER_ID
	 * @param modelList
	 */
	public void txGchildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelGchld> modelList);
	
	/**
	 * 删除付款明细
	 * 
	 * @param PAYMENT_DTL_IDS
	 */
	public void txBatch4DeleteGchild(String PAYMENT_DTL_IDS);
	
	/**
	 * 提交
	 * @param ADVC_ORDER_ID
	 * @param userBean
	 */
	public void txToCommit(String ADVC_ORDER_ID,UserBean userBean);
	
	/**
	 * 撤销
	 * @param ADVC_ORDER_ID
	 * @param userBean
	 */
	public void txRevoke(String ADVC_ORDER_ID,UserBean userBean);
	
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params);

}
