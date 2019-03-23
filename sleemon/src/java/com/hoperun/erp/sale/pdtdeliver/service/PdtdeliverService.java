package com.hoperun.erp.sale.pdtdeliver.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public interface PdtdeliverService extends IBaseService{
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
    public List <TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID);
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param params
     * @return the list
     */
    public List <TurnoverplanChildModel> childQuery(Map<String, String> params);
    
    /**
     * * 根据主表Id查询子表记录
     * @param params
     * @return the list
     */
    public List <TurnoverplanChildModel> childQueryByParentIDS(Map<String, String> params);
	
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID);
	
	/**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <TurnoverplanChildModel> loadChilds(Map <String, String> params) ;
	
	 /**
     * 编辑：新增
     * @param model 发运单
     * @return the string
     */
    public void txEdit(TurnoverplanModel model,UserBean userBean);
    
    /**
     * 提交库房
     * @param DELIVER_ORDER_ID 发运单ID
     * @param childList 发运单明细
     * @param userBean
     */
    public Map<String,Object> txCommitStore(String DELIVER_ORDER_ID,Map<String,String> dliverMap,String strJsonData,List<TurnoverplanChildModel> childList,UserBean userBean)throws Exception;
    
    /**
     * 修改明细
     * @param childList
     */
    public void txUpdatChild(List<TurnoverplanChildModel> childList);
    
    
    /**
     * 修改明细 修改
     * 计划发运数量|备注|剩余货品处理方式
     * @param params 
     */
    public void txUpdatChild(Map<String,Object>params);
    
    
    /**
     * 查询发运单对应的订货订单的制单人
     * @param DELIVER_ORDER_IDS
     * @param RECV_CHANN_ID 收货方ID
     * @return
     */
    public Map<String,String>  queryGooderOrderCreate(String DELIVER_ORDER_IDS,String RECV_CHANN_ID);
    
    /**
     * 查询 要催款的人员
     * @param DELIVER_ORDER_IDS 发运单IDS
     * @return
     */
    public  List<Map<String, Object>> queryDuingPerson(String DELIVER_ORDER_IDS);
    
    
    /**
	 * 查询U9数据
	 * @return
	 */
	public List pageQueryU9(Map params);
	public List downQuery(Map<String,String> map);
	
	 /**
     * 行关闭
     * @param DELIVER_ORDER_DTL_IDS
     * @param userBean
     */
    public void txCloseChilds(String deliverStats,String DELIVER_ORDER_NO,String DELIVER_ORDER_ID,String DELIVER_ORDER_DTL_IDS,
    		UserBean userBean,String strJsonData,String IS_ALL_CLOSE);
    
    public List getDeliverDtlId(String DELIVER_ORDER_ID);
    
    public String checkISExsitCloseRec(String DELIVER_ORDER_DTL_IDS);
    
    public boolean checkIsAllFreezeFlg(String DELIVER_ORDER_ID,String IS_ALL_FREEZE_FLAG);
    /**
     * 生成出货计划号
     * @param DELIVER_ORDER_IDS
     */
    public String createPlanNo(String DELIVER_ORDER_IDS);
    
    /**
     * 判断所选订单的收货方是否是同一个区域服务中心
     * @param DELIVER_ORDER_IDS 订单IDS
     * @return  fasle->非同一个区域服务中心
     */
    public boolean justOnlyAreaSer(String DELIVER_ORDER_IDS);
    
}
