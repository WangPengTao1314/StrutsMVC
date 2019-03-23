/**
 * prjName:喜临门营销平台
 * ucName:门店退货入库单
 * fileName:TermreturnstoreinService
*/
package com.hoperun.drp.sale.termreturnstorein.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.termreturnstorein.model.TermreturnstoreinModel;
import com.hoperun.drp.sale.termreturnstorein.model.TermreturnstoreinModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-10-27 15:46:33
 */
public interface TermreturnstoreinService extends IBaseService {
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
	 * @param TermreturnstoreinModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREIN_ID, TermreturnstoreinModel obj,List<TermreturnstoreinModelChld> chldList, UserBean userBean);
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
    public List <TermreturnstoreinModelChld> childQuery(String STOREIN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     * 
     * @return the list
     */
    public List <TermreturnstoreinModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREIN_ID the STOREIN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREIN_ID, List <TermreturnstoreinModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREIN_DTL_IDs);
}