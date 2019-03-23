/**
 * prjName:喜临门营销平台
 * ucName:入库通知单维护
 * fileName:StoreinnoticeService
*/
package com.hoperun.drp.store.storeinnotice.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.storeinnotice.model.StoreinnoticeModel;
import com.hoperun.drp.store.storeinnotice.model.StoreinnoticeModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author glw
 * *@createdate 2013-08-17 17:07:03
 */
public interface StoreinnoticeService extends IBaseService {
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
	 * @param STOREIN_NOTICE_ID
	 * @param StoreinnoticeModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREIN_NOTICE_ID, StoreinnoticeModel obj,List<StoreinnoticeModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STOREIN_NOTICE_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREIN_NOTICE_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_NOTICE_ID the STOREIN_NOTICE_ID
     * @return the list
     */
    public List <StoreinnoticeModelChld> childQuery(String STOREIN_NOTICE_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_NOTICE_DTL_IDs the STOREIN_NOTICE_DTL_IDs
     * 
     * @return the list
     */
    public List <StoreinnoticeModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREIN_NOTICE_ID the STOREIN_NOTICE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREIN_NOTICE_ID, List <StoreinnoticeModelChld> modelList,UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param STOREIN_NOTICE_DTL_IDs the STOREIN_NOTICE_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREIN_NOTICE_DTL_IDs);
    
    @SuppressWarnings("unchecked")
    public List loadFullData(String storeinNoticeId);
    
    @SuppressWarnings("unchecked")
    public List loadFullDtlData(String storeinNoticeId,String storeinStoreId);
    
    @SuppressWarnings("unchecked")
    public boolean txInsertStoreinData(List<Map> storeList,List<Map> storeDtlList);
    /**
	 * 修改总部/渠道修改入库通知单时，如果已生成入库单处理，则统一数据
	 * @param STOREIN_NOTICE_ID
	 */
	public void txUpStoreinDtlInfo(String STOREIN_NOTICE_ID) throws Exception;
	
	  /**
     * @return
     */
    public String  getStoreInCount(String RECV_CHANN_NO);
    
    /**
     * @param params
     * @return
     */
    public List <StoreinnoticeModelChld> loadChildsByModel(Map <String, String> params) ;
}