package com.hoperun.drp.store.dststoreout.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.dststoreout.model.DststoreoutModel;
import com.hoperun.drp.store.dststoreout.model.DststoreoutModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * 预订单出库维护
*    
* 项目名称：sleemon   
* 类名称：DststoreoutService.java
* 类描述：   
* 创建人：liu_yg   
* 创建时间：2016-1-11 下午03:07:05   
* @version
 */
public interface DststoreoutService extends IBaseService {
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
	 * @param DststoreoutModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREOUT_ID, DststoreoutModel obj,List<DststoreoutModelChld> chldList, UserBean userBean);
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
    public List <DststoreoutModelChld> childQuery(String STOREOUT_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     * 
     * @return the list
     */
    public List <DststoreoutModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREOUT_ID the STOREOUT_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREOUT_ID, List <DststoreoutModelChld> modelList,String actionType);
	 /**
     * * 子表的批量删除
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREOUT_DTL_IDs);
    public String qeuryId(String QUERYID);
    public void txCommit(Map<String,String> storeOutMap, UserBean userBean,String storeOutId,List chldlist)throws Exception ;
    public String checkRevcNum(String STOREOUT_ID);
    public List <DststoreoutModelChld> getAdvcDtlById(String ADVC_ORDER_ID);
    public String getChannTel(String CHANN_ID);
    public double getDtlGiftAmount(String STOREOUT_ID);
    public Map<String,String> getSellInfo(String STOREOUT_ID);
}