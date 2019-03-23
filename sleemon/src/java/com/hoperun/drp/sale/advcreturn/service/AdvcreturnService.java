/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:AdvcreturnService
*/
package com.hoperun.drp.sale.advcreturn.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcreturn.model.AdvcreturnModel;
import com.hoperun.drp.sale.advcreturn.model.AdvcreturnModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public interface AdvcreturnService extends IBaseService {
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
	 * @param ADVC_ORDER_ID
	 * @param EmployeeModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String ADVC_ORDER_ID, AdvcreturnModel obj,List<AdvcreturnModelChld> chldList, UserBean userBean);
	
	/**
	 * @主表详细页面
	 * @param param ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ADVC_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @return the list
     */
    public List <AdvcreturnModelChld> childQuery(String ADVC_ORDER_ID);
    /**
     * 按库房编号或库房名称查找库房数据
     */
    public Map<String,String> findSTOREOUT(Map<String,String> map);
    /**
     * 打回
     */
    public void txReverse(Map<String,String> map);
    public Integer getDtlCount(String ADVC_ORDER_ID);
}