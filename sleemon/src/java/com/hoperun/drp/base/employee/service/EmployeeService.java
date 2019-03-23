/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnService
*/
package com.hoperun.drp.base.employee.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.base.employee.model.EmployeeModel;
import com.hoperun.drp.base.employee.model.EmployeeModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public interface EmployeeService extends IBaseService {
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
	 * @param RYXXID
	 * @param EmployeeModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public String txEdit(String RYXXID, EmployeeModel obj, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param RYXXID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String RYXXID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param RYXXID the RYXXID
     * @return the list
     */
    public List <EmployeeModelChld> childQuery(String RYXXID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <EmployeeModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param 
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String RYXXID, List <EmployeeModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param XTYHJSIDS the XTYHJSIDS
     */
    public void txBatch4DeleteChild(String XTYHJSIDS);
    /**
     * 判断编号是否已经存在.
     * 
     * @param RYBH the RYBH
     * 
     * @return the ryxx exits
     */
    public int getRyxxExits(String RYBH);
    /**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params);
	
	/**
	 * 通过当前登录人的渠道id获取终端销售折扣控制标记
	 */
	public String getTERM_DECT_CTR_FLAG(String CHANN_ID);
	public int getRHMExits(String YHM);
	
    /**
     * 更新用户密码.
     * 
     * @param params the params
     */
    public void updatePassword(Map <String, String> params);
}