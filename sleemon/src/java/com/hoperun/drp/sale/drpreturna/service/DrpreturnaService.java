/**
 * prjName:喜临门营销平台
 * ucName:门店下级退货单
 * fileName:DrpreturnaService
*/
package com.hoperun.drp.sale.drpreturna.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.drpreturna.model.DrpreturnaModel;
import com.hoperun.drp.sale.drpreturna.model.DrpreturnaModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-26 15:41:36
 */
public interface DrpreturnaService extends IBaseService {
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
	 * @param DrpreturnaModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRD_RETURN_ID, DrpreturnaModel obj,List<DrpreturnaModelChld> chldList, UserBean userBean);
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
    public List <DrpreturnaModelChld> childQuery(String PRD_RETURN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     * 
     * @return the list
     */
    public List <DrpreturnaModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRD_RETURN_ID the PRD_RETURN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String PRD_RETURN_ID, List <DrpreturnaModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param PRD_RETURN_DTL_IDs the PRD_RETURN_DTL_IDs
     */
    public void txBatch4DeleteChild(String PRD_RETURN_DTL_IDs);
    
    /**
     * @param map
     * @return
     */
	public List downQuery(Map<String,String> map);
}