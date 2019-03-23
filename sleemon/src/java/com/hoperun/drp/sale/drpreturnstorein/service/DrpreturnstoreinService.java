/**
 * prjName:喜临门营销平台
 * ucName:直营办下级退货入库单
 * fileName:DrpreturnstoreinService
*/
package com.hoperun.drp.sale.drpreturnstorein.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.drpreturnstorein.model.DrpreturnstoreinModel;
import com.hoperun.drp.sale.drpreturnstorein.model.DrpreturnstoreinModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-27 14:51:37
 */
public interface DrpreturnstoreinService extends IBaseService {
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
	 * @param STOREIN_ID
	 * @param DrpreturnstoreinModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREIN_ID, DrpreturnstoreinModel obj,List<DrpreturnstoreinModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STOREIN_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREIN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_ID the STOREIN_ID
     * @return the list
     */
    public List <DrpreturnstoreinModelChld> childQuery(String STOREIN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     * 
     * @return the list
     */
    public List <DrpreturnstoreinModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREIN_ID the STOREIN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREIN_ID, List <DrpreturnstoreinModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREIN_DTL_IDs);
    
    /**
     * @param map
     * @return
     */
	public List downQuery(Map<String,String> map);
}