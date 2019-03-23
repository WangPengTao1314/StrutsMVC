/**
 * prjName:喜临门营销平台
 * ucName:客户退货结算
 * fileName:RestatementsService
*/
package com.hoperun.drp.finance.restatements.service;
import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.finance.restatements.model.RestatementsModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-12 15:21:43
 */
public interface RestatementsService extends IBaseService {
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
	 * @param RETURN_STATEMENTS_ID
	 * @param RestatementsModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String RETURN_STATEMENTS_ID, RestatementsModel obj, UserBean userBean);
	
	/**
	 * @删除
	 * @param RETURN_STATEMENTS_ID.
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @详细页面
	 * @param param RETURN_STATEMENTS_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param);
	
	/**
	 * 查询结算单明细
	 * @return
	 */
	public List<Map<String,String>> queryChild(Map<String,String> params);
	
	/**
	 * @提交
	 * @param params
	 * @return
	 */
	public boolean txSub(Map <String, String> params);
	
}