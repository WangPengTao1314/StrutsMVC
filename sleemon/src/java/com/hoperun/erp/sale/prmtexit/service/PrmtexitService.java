/**
 * prjName:喜临门营销平台
 * ucName:促销品发放
 * fileName:PrmtexitService
*/
package com.hoperun.erp.sale.prmtexit.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.prmtexit.model.PrmtexitModel;
import com.hoperun.erp.sale.prmtexit.model.PrmtexitModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-19 16:54:28
 */
public interface PrmtexitService extends IBaseService {
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
	 * @param PRMT_GOODS_EXTD_ID
	 * @param PrmtexitModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRMT_GOODS_EXTD_ID, PrmtexitModel obj,List<PrmtexitModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param PRMT_GOODS_EXTD_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String PRMT_GOODS_EXTD_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param PRMT_GOODS_EXTD_ID the PRMT_GOODS_EXTD_ID
     * @return the list
     */
    public List <PrmtexitModelChld> childQuery(String PRMT_GOODS_EXTD_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRMT_GOODS_EXTD_DTL_IDs the PRMT_GOODS_EXTD_DTL_IDs
     * 
     * @return the list
     */
    public List <PrmtexitModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRMT_GOODS_EXTD_ID the PRMT_GOODS_EXTD_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String PRMT_GOODS_EXTD_ID, List <PrmtexitModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param PRMT_GOODS_EXTD_DTL_IDs the PRMT_GOODS_EXTD_DTL_IDs
     */
    public void txBatch4DeleteChild(String PRMT_GOODS_EXTD_DTL_IDs);
    
    public String countSum(String param);
    /**
	 * @提交
	 * @param PRMT_GOODS_EXTD_ID
	 * 
	 * @return true, if tx delete
	 */
	public void commit(Map <String, String> params) ;
	
	public boolean loadPrmtExitByPrd(Map <String, String> params);
}