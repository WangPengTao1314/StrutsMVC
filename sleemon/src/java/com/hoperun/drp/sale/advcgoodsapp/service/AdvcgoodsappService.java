/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappService
*/
package com.hoperun.drp.sale.advcgoodsapp.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModel;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-11-02 18:55:53
 */
public interface AdvcgoodsappService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params,UserBean userBean,List dtlList)throws Exception;
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_SEND_REQ_ID
	 * @param AdvcgoodsappModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public String txEdit(String ADVC_SEND_REQ_ID, AdvcgoodsappModel obj,List<AdvcgoodsappModelChld> chldList, UserBean userBean)throws Exception;
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params,UserBean userBean);
	
	/**
	 * @主表详细页面
	 * @param param ADVC_SEND_REQ_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String ADVC_SEND_REQ_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param ADVC_SEND_REQ_ID the ADVC_SEND_REQ_ID
     * @return the list
     */
    public List <AdvcgoodsappModelChld> childQuery(String ADVC_SEND_REQ_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_SEND_REQ_DTL_IDs the ADVC_SEND_REQ_DTL_IDs
     * 
     * @return the list
     */
    public List <AdvcgoodsappModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param ADVC_SEND_REQ_ID the ADVC_SEND_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String ADVC_SEND_REQ_ID, List <AdvcgoodsappModelChld> modelList,String actionType,UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param ADVC_SEND_REQ_DTL_IDs the ADVC_SEND_REQ_DTL_IDs
     */
    public void txBatch4DeleteChild(String ADVC_SEND_REQ_DTL_IDs,UserBean userBean,String ADVC_SEND_REQ_ID);
    public List<AdvcgoodsappModel> mainQuery(String ADVC_SEND_REQ_ID);
    public void insertTrace(Map<String,String> map);
    public boolean txgetPAYABLE_AMOUNT(UserBean userBean,String ADVC_SEND_REQ_ID);
    public String qeuryId(String QUERYID);
    
    public String checkChangeOrder(String ADVC_ORDER_ID);
    public boolean upOrderDate(Map<String,String> map);
    public int checkAmount(String ADVC_SEND_REQ_ID);
}