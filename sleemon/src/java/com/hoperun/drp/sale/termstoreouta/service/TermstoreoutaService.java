/**
 * prjName:喜临门营销平台
 * ucName:门店销售出库单
 * fileName:TermstoreoutaService
*/
package com.hoperun.drp.sale.termstoreouta.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.termstoreouta.model.TermstoreoutaModel;
import com.hoperun.drp.sale.termstoreouta.model.TermstoreoutaModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-26 14:47:09
 */
public interface TermstoreoutaService extends IBaseService {
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
	 * @param STOREOUT_ID
	 * @param TermstoreoutaModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREOUT_ID, TermstoreoutaModel obj,List<TermstoreoutaModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STOREOUT_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREOUT_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * @return the list
     */
    public List <TermstoreoutaModelChld> childQuery(String STOREOUT_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     * 
     * @return the list
     */
    public List <TermstoreoutaModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREOUT_ID the STOREOUT_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREOUT_ID, List <TermstoreoutaModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREOUT_DTL_IDs);
    
    /**
     * @param map
     * @return
     */
	public List downQuery(Map<String,String> map);
}