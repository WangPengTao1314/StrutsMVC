/**
 * prjName:喜临门营销平台
 * ucName:预订单录入
 * fileName:AdvcorderService
 */
package com.hoperun.drp.sale.uncomm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModel;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelChld;
import com.hoperun.drp.sale.advcorder.model.AdvcorderModelGchld;
import com.hoperun.sys.model.UserBean;

/**
 * *@service *@func *@version 1.1 *@author lyg *@createdate 2013-08-11 17:17:29
 */
public interface UncommService extends IBaseService {
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
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelChld> childQuery(String ADVC_ORDER_ID);
	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 * 
	 * @return the list
	 */
	public List<AdvcorderModelChld> loadChilds(Map<String, String> params);
	/**
	 * * 子表记录编辑：新增/修改
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx child edit
	 */
	public boolean txChildEdit(String ADVC_ORDER_ID,
			List<AdvcorderModelChld> modelList, UserBean userBean) throws ParseException;
	/**
	 * 求和更新应收总金额
	 */
	public void txgetPAYABLE_AMOUNT(UserBean userBean, String ADVC_ORDER_ID);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params);
	/**
	 * * 子表的批量删除
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 */
	public void txBatch4DeleteChild(String PAYMENT_DTL_IDs, String ADVC_ORDER_ID);
	/**
	 * @主表详细页面
	 * @param param
	 *            ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String, String> load(String ADVC_ORDER_ID);
	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param ADVC_ORDER_ID
	 *            the ADVC_ORDER_ID
	 * @return the list
	 */
	public List<AdvcorderModelGchld> gchildQuery(String ADVC_ORDER_ID);
	/**
	 * 修改提交状态
	 */
	public MsgInfo txCommitById(Map<String, String> params, UserBean userBean)
			throws Exception;
	public boolean upStateById(Map<String,String> map);
	/**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_ORDER_ID
	 * @param AdvctoorderModel
	 * @param userBean
	 *            .
	 * 
	 * @return
	 */
	public void txEdit(String ADVC_ORDER_ID, AdvcorderModel obj,
			List<AdvcorderModelChld> gchldList, UserBean userBean) throws ParseException;
	public String checkMonthAcc(String ADVC_ORDER_ID,UserBean userBean);
	/**
	 * 按id查找定金
	 */
	public double amountGetById(String ADVC_ORDER_ID);

	/**
	 * 按id查询付款总金额
	 */
	public double payAmountGetById(String ADVC_ORDER_ID);
	public void txClose(Map<String,String> map);
}