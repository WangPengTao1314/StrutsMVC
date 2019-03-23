/**
 * prjName:喜临门营销平台
 * ucName:退货单维护
 * fileName:PrdreturnService
*/
package com.hoperun.erp.sale.prdreturn.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.prdreturn.model.PrdreturnModel;
import com.hoperun.erp.sale.prdreturn.model.PrdreturnModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-19 15:33:31
 */
public interface PrdreturnService extends IBaseService {
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
	 * @param PRD_RETURN_ID
	 * @param PrdreturnfinModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRD_RETURN_ID, PrdreturnModel obj,List<PrdreturnModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param PRD_RETURN_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String PRD_RETURN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @return the list
     */
    public List <PrdreturnModelChld> childQuery(String PRD_RETURN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     * 
     * @return the list
     */
    public List <PrdreturnModelChld> loadChilds(Map <String, String> params) ;
    public boolean txUpdateById(Map<String,String> params);
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String PRD_RETURN_ID, List <PrdreturnModelChld> modelList,UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     */
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_IDs);
    public Object getStoreInDiff(String Bussid);
}