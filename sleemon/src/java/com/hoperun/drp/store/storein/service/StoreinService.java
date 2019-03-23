/**
 * prjName:喜临门营销平台
 * ucName:入库单处理
 * fileName:StoreinService
*/
package com.hoperun.drp.store.storein.service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.storein.model.StoreinModelChld;
import com.hoperun.drp.store.storein.model.StoreinModelGrandChld;
import com.hoperun.drp.store.storeout.model.StoreoutModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-22 09:20:20
 */
public interface StoreinService extends IBaseService {
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
	 * @param StoreinModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREIN_ID, Map<String, String> map,List<StoreinModelChld> chldList, UserBean userBean)throws Exception;
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
    public List <StoreinModelChld> childQuery(String STOREIN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     * 
     * @return the list
     */
    public List <StoreinModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param params map
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREIN_ID, List<StoreinModelChld> chldList,UserBean userBean) ;
	 /**
     * * 子表的批量删除
     * @param STOREIN_DTL_IDs the STOREIN_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREIN_DTL_IDs);
    
    /* =================孙表处理开始=================    */
    
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DTL_ID the STOREIN_DTL_ID
     * @return the list
     */
    public List <StoreinModelGrandChld> grandChildQuery(String STOREIN_DTL_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_STORG_DTL_IDs the STOREIN_STORG_DTL_IDs
     * 
     * @return the list
     */
    public List <StoreinModelGrandChld> loadGrandChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txGrandChildEdit(List <StoreinModelGrandChld> modelList);
	 /**
     * * 子表的批量删除
     * @param STOREIN_STORG_DTL_IDs the STOREIN_STORG_DTL_IDs
     */
    public void txBatch4DeleteGrandChild(String STOREIN_STORG_DTL_IDs,String STOREIN_DTL_ID);
    
    /**
	 * @入库处理时校验
	 * @param params map
	 *
	 * @return the map
	 */
	public  Map<String,String> queryList(Map <String, String> param);
	
    
    /**
     * * 根据子表Id查询孙表记录
     * @param STOREIN_DTL_ID the STOREIN_DTL_ID
     * 
     * @return the list< new master slavemx model>
     */
    public List <StoreinModelGrandChld> grandChild(String STOREIN_ID,String STOREIN_DTL_ID);
    
    /**
     * * 获取库位ID
     * @param params map
     * 
     * @return the map 
     */
    public List<String> getSTORGIDS(Map<String, String> params);
    /**
     * 共通入库单新增
     */
    public void txAddStorein(Map<String,String> model,List<Map<String,String>> chld);
    public boolean txScanChildEdit(String STOREIN_ID, List <StoreinModelChld> modelList,UserBean userBean);
    public HashMap getErpStoreInDtl(String SN);
    public HashMap getDrpStoreInDtl(String pdtId);
    /**
     * 退回
     * @param STOREIN_ID 入库处理单id
     * @param FROM_BILL_ID 来源单据id
     * @param BILL_TYPE 单据类型
     */
    public void txReturnStorein(String STOREIN_ID,String FROM_BILL_ID,String BILL_TYPE,UserBean userBean);
    
    
    public List downQuery(Map<String,String> map);
    /**
     * 反入库
     * @param STOREIN_ID
     * @param userBean
     */
    public void txBackiin(String STOREIN_ID,String BillType,UserBean userBean)throws Exception ;
}