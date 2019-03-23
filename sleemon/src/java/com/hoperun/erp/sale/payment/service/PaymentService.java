/**
 * prjName:喜临门营销平台
 * ucName:客户付款单
 * fileName:PaymentService
*/
package com.hoperun.erp.sale.payment.service;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.payment.model.PaymentModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-15 09:31:13
 */
public interface PaymentService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @编辑
	 * @Description :
	 * @param PAYMENT_ID
	 * @param PaymentModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PAYMENT_ID, PaymentModel obj, UserBean userBean);
	
	/**
	 * @删除
	 * @param PAYMENT_ID.
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @详细页面
	 * @param param PAYMENT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param);
	
}