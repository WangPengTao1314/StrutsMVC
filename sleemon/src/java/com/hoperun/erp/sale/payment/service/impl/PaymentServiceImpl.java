/**
 * prjName:喜临门营销平台
 * ucName:客户付款单
 * fileName:PaymentServiceImpl
*/
package com.hoperun.erp.sale.payment.service.impl;
import java.util.HashMap;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.payment.model.PaymentModel;
import com.hoperun.sys.model.UserBean;
import com.hoperun.erp.sale.payment.service.PaymentService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-15 09:31:13
 */
public class PaymentServiceImpl extends BaseService implements PaymentService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Payment.pageQuery", "Payment.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Payment.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param PAYMENT_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
        return update("Payment.delete", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Payment.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param PAYMENT_ID
	 * @param PaymentModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String PAYMENT_ID, PaymentModel model, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(PAYMENT_ID)){
			PAYMENT_ID= StringUtil.uuid32len();
			params.put("PAYMENT_ID", PAYMENT_ID);
		txInsert(params);
		} else{
			params.put("PAYMENT_ID", PAYMENT_ID);
			txUpdateById(params);
			clearCaches(PAYMENT_ID);
		}
	}
	/**
	 * @详细
	 * @param param PAYMENT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Payment.loadById", param);
	}
}