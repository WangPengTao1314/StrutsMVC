/**
 * prjName:喜临门营销平台
 * ucName:出库单处理
 * fileName:StoreoutService
*/
package com.hoperun.drp.store.storeout.service;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.storeout.model.StoreoutModel;
import com.hoperun.drp.store.storeout.model.StoreoutModelChld;
import com.hoperun.drp.store.storeout.model.StoreoutModelGrandChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-09-15 14:59:50
 */
public interface StoreoutService extends IBaseService {
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
	 * @param StoreoutconfirmModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREOUT_ID, StoreoutModel obj,List<StoreoutModelChld> chldList, UserBean userBean);
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
	public HashMap<String,String> load(String STOREOUT_ID);
	
	
	
	/**
	 * 加载渠道
	 * @param CHANN_ID
	 * @return
	 */
	public Map<String,String> loadChann(String CHANN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * @return the list
     */
    public List <StoreoutModelChld> childQuery(String STOREOUT_ID);
    
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     * 
     * @return the list
     */
    public List <StoreoutModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param STOREOUT_ID the STOREOUT_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREOUT_ID, List <StoreoutModelChld> modelList,UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREOUT_DTL_IDs);
    
    /**
     * * 根据子表Id查询孙表记录
     * @param STOREOUT_DTL_ID the STOREOUT_DTL_ID
     * 
     * @return the list< new master slavemx model>
     */
    public List <StoreoutModelGrandChld> storgChildQuery(Map <String, String> params);

    public boolean txBatchStorgEdit(String storeoutId,String storeoutDtlId, List<StoreoutModelGrandChld> chldList,UserBean userBean);

    public List <StoreoutModelChld> childQueryById(String STOREOUT_DTL_ID);
    
    public void txSlCommit(List<StoreoutModel> storeOutList, UserBean userBean,String storeOutId,List chldlist) throws Exception;
    
    public void txThCommit(List<StoreoutModel> storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId)throws Exception;
    
    public void txXXCommit(List<StoreoutModel> storeOutList,List storeOutDtlList,List dtlGroupList, UserBean userBean,String storeOutId)throws Exception;
    
    public MsgInfo txLxCommit(List<StoreoutModel> storeOutList, UserBean userBean,String storeOutId) throws Exception;
    /**区域服务中心退货出库处理
     * @param storeOutList
     * @param userBean
     */
    public MsgInfo txAreaSerStoreOutDone(HashMap<String,String> storeOutMap,List<StoreoutModelChld> storeOutDtlList, UserBean userBean,String storeOutId)  throws Exception;
  
    public MsgInfo txDbCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId)throws Exception;
    
    public void txFFCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId)throws Exception;

    public void txFXCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId)throws Exception;

    public List <StoreoutModel> mainQuery(String STOREOUT_ID);
    public List <StoreoutModel> mainSaleToLowerQuery(String STOREOUT_ID);
    
    /**
     * 共通入库单新增
     */
    public void txAddStoreOut(Map<String,Object> model,List<Map<String,String>> chld);

    public HashMap getErpStoreOutDtl(String SN);
    
    public HashMap getDrpStoreOutDtl(String pdtId);
    
    public boolean txScanChildEdit(String STOREOUT_ID, List <StoreoutModelChld> modelList,UserBean userBean); 
    /**
     * 查询 出库单 是否已经扫过该序列号
     * @param SN
     * @return true 已扫过  false 未扫
     */
    public boolean getDrpStoreOutDtlBySN(String SN);
    public Map<String,String> getSellInfo(String STOREOUT_ID);
    
    /**
     * * 根据主表Id查询子表记录并且合并
     * @param STOREOUT_ID the STOREOUT_ID
     * @return the list
     */
    public List <Map<String,String>> childQueryGroup(String STOREOUT_ID);
    
    /**
     * * 获得出库单和销售出库通知的信息
     * @param STOREOUT_ID the STOREOUT_ID
     * @return the list
     */
    public List <StoreoutModelChld>  childQuerySaleOutDtl(String STOREOUT_ID);
    /**
     * 根据当前登录人所属渠道获取渠道类型
     * @param CHANN_ID
     * @return
     */
    public String getCHANN_TYPE(String CHANN_ID);
    
    public boolean txCancelStoreOut(String STOREOUT_ID)throws Exception;
    
    /**
     * 提交的时候判断
     * 出库数量不能大于预订单订货数量
     * (出库数量<=预订单订货数量-已发货数量)
     * @param STOREOUT_ID
     * @return
     */
    public Map<String,String> txCheckRealNumAndAdvcOrderNum(String STOREOUT_ID);
    
    /**
     * 修改备注
     * @param params
     */
    public void updateRemark(Map<String,Object> params);
    /**
     * 关闭销售出库确认
     * @param STOREOUT_ID
     */
    public void closeDocument(String STOREOUT_ID,UserBean userBean);
    /**
     * 零星出库单处理关闭
     * @param params
     */
    public void txCloseSporadicStoreout(String STOREOUT_ID,UserBean userBean);
    //获取渠道电话
    public String getChannTel(String CHANN_ID);
    /**
     * 反出库处理
     * @param map
     */
    public void txReturnStore(Map<String,String> map,UserBean userBean)throws Exception;
    /**
     * 根据出库单ID获取出库单进行验证
     * @param STOREOUT_ID
     * @return
     */
    public Map<String,Object> getStoreoutInfo(String STOREOUT_ID);
    /**
     * 根据出库单id获取已扫码明细条数
     * @param STOREOUT_ID
     * @return
     */
    public Integer countSnNum(String STOREOUT_ID);
    
    /**
     * 查询出库总金额
     * @param paramMap
     * @return
     */
    public Map<String,Object> quereyDealAmount(Map<String,String> paramMap);
    //获取赠品总金额
    public double getDtlGiftAmount(String STOREOUT_ID);
    //获取明细行数
    public int getDtlCount(String STOREOUT_ID);
    //导出
    public List<Map<String,String>> downQuery(Map<String,String> map);
    /**
     * 根据出库单id验证是否存在退货单
     * @param STOREOUT_ID
     * @return
     */
    public boolean checkReturnAdvc(String STOREOUT_ID);
    //获取当前单据状态
    public String getStoreoutState(String STOREOUT_ID);
}