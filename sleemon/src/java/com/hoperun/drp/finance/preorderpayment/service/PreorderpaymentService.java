/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentService
*/
package com.hoperun.drp.finance.preorderpayment.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.finance.preorderpayment.model.PreorderpaymentModel;
import com.hoperun.drp.finance.preorderpayment.model.PreorderpaymentModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public interface PreorderpaymentService extends IBaseService {
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
	 * @param STATEMENTS_ID
	 * @param PreorderpaymentModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STATEMENTS_ID, PreorderpaymentModel obj,List<PreorderpaymentModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STATEMENTS_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STATEMENTS_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STATEMENTS_ID the STATEMENTS_ID
     * @return the list
     */
    public List <PreorderpaymentModelChld> childQuery(String STATEMENTS_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STATEMENTS_PAYMENT_DTL_IDs the STATEMENTS_PAYMENT_DTL_IDs
     * 
     * @return the list
     */
    public List <PreorderpaymentModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STATEMENTS_ID the STATEMENTS_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STATEMENTS_ID, List <PreorderpaymentModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param STATEMENTS_PAYMENT_DTL_IDs the STATEMENTS_PAYMENT_DTL_IDs
     */
    public void txBatch4DeleteChild(String STATEMENTS_PAYMENT_DTL_IDs);
    
    /**
	 * @提交
	 * @param params
	 * @return
	 */
	public boolean txSub(Map <String, String> params);
	
	/**
	 * 查询结算单明细
	 * @return
	 */
	public List<Map<String,String>> queryChild(Map<String,String> params);
}