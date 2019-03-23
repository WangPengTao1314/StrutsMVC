/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairappService
*/
package com.hoperun.drp.sale.repairstoreout.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.repairapp.model.RepairappModel;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public interface RepairStoreoutService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * @主表详细页面
	 * @param param ERP_REPAIR_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ERP_REPAIR_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ERP_REPAIR_ORDER_ID the ERP_REPAIR_ORDER_ID
     * @return the list
     */
    public List <RepairappModelChld> childQuery(String ERP_REPAIR_ORDER_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param REPAIR_ORDER_DTL_IDs the REPAIR_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <RepairappModelChld> loadChilds(Map <String, String> params) ;
	
	
    

	public void txSubmitById(Map<String, String> params,UserBean userBean) throws Exception;
	/**
	 * 关闭
	 * @param ERP_REPAIR_ORDER_ID
	 * @param userBean
	 */
	public void txAuditClose(String ERP_REPAIR_ORDER_ID,UserBean userBean);
	
	 /**
     * 查询总体积 总数量 总金额
     * @param ERP_REPAIR_ORDER_ID
     * @return
     */
    public Map<String,Object> queryTotal(String ERP_REPAIR_ORDER_ID);
    
    
    
}