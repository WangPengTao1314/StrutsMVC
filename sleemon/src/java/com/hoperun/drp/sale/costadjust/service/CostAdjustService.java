/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnService
*/
package com.hoperun.drp.sale.costadjust.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.costadjust.model.CostAdjustModel;
import com.hoperun.drp.sale.costadjust.model.CostAdjustModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public interface CostAdjustService extends IBaseService {
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
	public void txEdit(String COST_ADJUST_ID, CostAdjustModel obj,List<CostAdjustModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param ADVC_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String COST_ADJUST_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @return the list
     */
    public List <CostAdjustModelChld> childQuery(String COST_ADJUST_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <CostAdjustModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public void txChildEdit(String COST_ADJUST_ID, List <CostAdjustModelChld> modelList,String actionType);
	 /**
     * * 子表的批量删除
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String COST_ADJUST_DTL_IDS,String COST_ADJUST_ID);
}