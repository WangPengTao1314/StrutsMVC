/**
 * prjName:喜临门营销平台
 * ucName:付款凭证确认
 * fileName:PaymentcmtService
*/
package com.hoperun.erp.sale.paymentcmt.service;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.paymentcmt.model.PaymentcmtModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-25 09:50:09
 */
public interface PaymentcmtService extends IBaseService {
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
	 * @param PAYMENT_UPLOAD_ID
	 * @param PaymentcmtModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PAYMENT_UPLOAD_ID, PaymentcmtModel obj, UserBean userBean);
	
	/**
	 * @删除
	 * @param PAYMENT_UPLOAD_ID.
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @详细页面
	 * @param param PAYMENT_UPLOAD_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param);
	/**
	 * 确认/打回
	 * 状态修改
	 */
	public boolean EditState(Map<String,String> map);
	
	public void txPaymentComit(Map<String,String> map)throws Exception;
}