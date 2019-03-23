/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentService
*/
package com.hoperun.drp.finance.advpayment.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModel;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public interface AdvpaymentService extends IBaseService {
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
	public void txEdit(String STATEMENTS_ID, AdvpaymentModel obj,List<AdvpaymentModelChld> chldList, UserBean userBean);
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
    public List <AdvpaymentModelChld> childQuery(String STATEMENTS_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STATEMENTS_PAYMENT_DTL_IDs the STATEMENTS_PAYMENT_DTL_IDs
     * 
     * @return the list
     */
    public List <AdvpaymentModelChld> loadChilds(Map <String, String> params) ;
	
    /**
     * * 明细数据编辑
     * @param STATEMENTS_ID the STATEMENTS_ID
     * @param chldList the model list
     * @param updateMastRecord  
     * @param ADVC_ORDER_ID 预订单ID
     * @param isUpdateAdvcPayDtl 是否更新预订单的付款方式 true ->改
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STATEMENTS_ID, List <AdvpaymentModelChld> modelList,boolean updateMastRecord,String ADVC_ORDER_ID,boolean isUpdateAdvcPayDtl);
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
	public String txSub(Map <String, String> params,UserBean userBean);
	
	/**
	 * 撤销
	 * @param STATEMENTS_ID
	 * @param userBean
	 */
	public void txRevoke(String STATEMENTS_ID,UserBean userBean,String type);
	/**
	 * 查询是否有核销的明细
	 * @param STATEMENTS_ID
	 * @return
	 */
	public int queryHXChildsCount(String STATEMENTS_ID);
	
	public List PaymentToExcel(Map<String,String> params);
	
}