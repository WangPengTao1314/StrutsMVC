package com.hoperun.erp.sale.deliveryhd.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.deliveryhd.model.DeliveryhdGchldModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public interface DeliveryhdService extends IBaseService{
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <TurnoverplanChildModel> childQuery(Map<String, String> params);
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID);
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <Map<String,String>> gchildQuery(String DELIVER_ORDER_ID);
    
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID);
	
	 /**
     * 编辑：新增
     * @param model 发运单
     * @return the string
     */
    public String txEdit(TurnoverplanModel model,List<TurnoverplanChildModel> chldModelList,
    		UserBean userBean,String SHIP_POINT_ID,
    		String SHIP_POINT_NAME,List<DeliveryhdGchldModel> gchldModelList)throws Exception;
    
    public void txDelete(Map<String,String> map);
	

	/**
     * * 根据主表Id查询生命周期子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
	public List <Map<String,String>> queryTrace(String SALE_ORDER_ID);
	
	/**
	 * * 子表的批量删除
	 * 
	 * @param PAYMENT_DTL_IDs
	 *            the PAYMENT_DTL_IDs
	 */
	public void txBatch4DeleteChild(String DELIVER_ORDER_DTL_IDS);
	 /**
	 * * 明细数据编辑
	 * 
	 */
	public void txChildEdit(String DELIVER_ORDER_ID, List<TurnoverplanChildModel> chldList,UserBean userBean)throws Exception;
	public List downQuery(Map<String,String> map);
	public IListPage queryEdit(Map<String,String> map, int pageNo);
	public long getCount(Map<String,String> map);
	public List queryEdit(Map<String,String> map);
	public List<TurnoverplanChildModel> findChld(String SALE_ORDER_DTL_ID,String CHANNS_CHARG);
	public Map<String,Object> txCommitStore(String DELIVER_ORDER_ID_OLD,Map<String, String> dliverMap, String strJsonData,List<TurnoverplanChildModel> childList, UserBean userBean,String AppCode, String UId,String OPRCODE)throws Exception;
	public String txCloseAllOrder(String DELIVER_ORDER_ID,String strJsonData);
	public String txClose(String DELIVER_ORDER_ID,String strJsonData);
	/**
     * 行关闭
     * @param DELIVER_ORDER_DTL_IDS
     * @param userBean
     */
    public String txCloseChilds(String DELIVER_ORDER_NO,String DELIVER_ORDER_ID,String DELIVER_ORDER_DTL_IDS,
    		UserBean userBean,String strJsonData,String IS_ALL_CLOSE);

    public List getDeliverDtlId(String DELIVER_ORDER_ID);
	public List<DeliveryhdGchldModel> freeChildQuery(String DELIVER_ORDER_ID);
	public List<DeliveryhdGchldModel> freeChildQueryByIds(String DELIVER_FREEZE_DTL_IDS);
	public void txGchildEdit(String DELIVER_ORDER_ID,List<DeliveryhdGchldModel> gchldModelList);
	/**
	 * * 子表的批量删除
	 * 
	 * @param ADVC_ORDER_DTL_IDs
	 *            the ADVC_ORDER_DTL_IDs
	 */
	public void txBatch4DeleteGchild(String DELIVER_FREEZE_DTL_IDS);
	public int checkFreeChann(Map<String,String> map);
	
}
