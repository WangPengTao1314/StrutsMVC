/**
 * prjName:喜临门营销平台
 * ucName:终端退货录入
 * fileName:TermreturnService
*/
package com.hoperun.drp.sale.termreturn.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.termreturn.model.TermreturnModel;
import com.hoperun.drp.sale.termreturn.model.TermreturnModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public interface TermreturnService extends IBaseService {
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
	public void txEdit(String ADVC_ORDER_ID, TermreturnModel obj,List<TermreturnModelChld> chldList, UserBean userBean);
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
	public Map<String,String> load(String ADVC_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @return the list
     */
    public List <TermreturnModelChld> childQuery(String ADVC_ORDER_ID,String CHANN_TYPE);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <TermreturnModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ADVC_ORDER_ID the ADVC_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public void txChildEdit(String ADVC_ORDER_ID, List <TermreturnModelChld> modelList,String actionType,String CHANN_TYPE);
	 /**
     * * 子表的批量删除
     * @param ADVC_ORDER_DTL_IDs the ADVC_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String ADVC_ORDER_DTL_IDs,String ADVC_ORDER_ID);
    /**
     * 修改提交状态
     */
    public void txCommitById(Map <String, String> params);
    /**
     * 根据主表id查询现有终端退货明细来源单据明细id
     * @param QUERYID
     * @return
     */
    public String qeuryId(String QUERYID) ;
    /**
	 * 根据当前登陆人所属部门ID查询终端信息
	 * 
	 * @param BMXXID
	 * @return
	 */
	public Map<String, String> getTerminalInfoById(String BMXXID);
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
}