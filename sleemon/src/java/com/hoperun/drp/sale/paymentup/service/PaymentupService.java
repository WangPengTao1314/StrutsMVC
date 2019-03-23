/**
 * prjName:喜临门营销平台
 * ucName:付款凭证上传
 * fileName:PaymentupService
*/
package com.hoperun.drp.sale.paymentup.service;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.paymentup.model.PaymentupModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-23 10:25:58
 */
public interface PaymentupService extends IBaseService {
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
	 * @param PaymentupModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PAYMENT_UPLOAD_ID, PaymentupModel obj, UserBean userBean);
	
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
	 * 
	 * 查询是否有相同的凭证号
	 * @param PAYMENT_NO
	 * @return
	 */
	public int checkPAYMENT_NO(String PAYMENT_NO);
	/**
	 * 提交
	 */
	public boolean commit(Map<String,String> map);
	 public Map<String,String> loadChann(String CHANNID);
}