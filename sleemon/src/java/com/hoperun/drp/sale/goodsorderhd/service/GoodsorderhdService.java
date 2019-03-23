/**
 * prjName:喜临门营销平台
 * ucName:订货订单维护
 * fileName:GoodsorderhdService
*/
package com.hoperun.drp.sale.goodsorderhd.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModel;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelChld;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-09-15 10:35:10
 */
public interface GoodsorderhdService extends IBaseService {
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
	 * @param GOODS_ORDER_ID
	 * @param GoodsorderhdModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public String txEdit(String GOODS_ORDER_ID, GoodsorderhdModel obj,List<GoodsorderhdModelChld> chldList, UserBean userBean,String REBATEDSCT);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param GOODS_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String GOODS_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @return the list
     */
    public List <GoodsorderhdModelChld> childQuery(String GOODS_ORDER_ID);
    
    /**
     * * 根据主表Id查询生命周期子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @return the list
     */
    public List <GoodsorderhdModelTrace> traceQuery(String GOODS_ORDER_ID);
    
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <GoodsorderhdModelChld> loadChilds(Map <String, String> params) ;
	
	/**
	 * 子 保存
	 * @param GOODS_ORDER_ID 订单ID
	 * @param modelList 明细list
	 * @param ORDER_RECV_DATE 交期
	 * @param params 参数
	 * @return
	 */
    public boolean txChildEdit(String GOODS_ORDER_ID, List <GoodsorderhdModelChld> modelList, 
    		String ORDER_RECV_DATE,Map<String,String> params,String action);
	 /**
     * * 子表的批量删除
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String GOODS_ORDER_DTL_IDs);
    
	/**
	 * 加载渠道
	 * @param param CHANN_ID
	 * @return the map< string, string>
	 */
	public Map<String,Object> loadChann(String CHANN_ID);
	
	/**
	 * 查询货品的 折扣率
	 * @param AREA_ID 区域ID
	 * @param PRD_ID 货品ID
	 * @return
	 */
	public Map<String,String> getRateByAreaIdPId(String AREA_ID,String PRD_ID);
	
	/**
	 * 插入生命周期表
	 * @param GOODS_ORDER_ID 订货订单ID
	 * @param BILL_TYPE 单据类型
	 * @param GOODS_ORDER_NO 订货订单NO
	 * @param userBean
	 */
	public void saveOrderTruck(String GOODS_ORDER_ID,String BILL_TYPE,String GOODS_ORDER_NO, UserBean userBean);
	/**
	 * 根据货品id查询明细的总金额和总价格
	 * @param GOODS_ORDER_ID
	 */
	public Map<String,String> queryTotal(String GOODS_ORDER_ID);
	/**
	 * 提交
	 * @param GOODS_ORDER_ID 订货订单ID
	 * @param BILL_TYPE 单据类型
	 * @param GOODS_ORDER_NO 订货订单NO
	 * @param userBean
	 */
	public void txCommit(String GOODS_ORDER_ID,String BILL_TYPE,String GOODS_ORDER_NO,UserBean userBean)throws Exception;
	
	/**
	 * 检查信用
	 * @param GOODS_ORDER_ID 订货单订单ID
	 * @throws Exception
	 */
	public void txCheckCredit(String CHANN_ID,String GOODS_ORDER_ID,String BILL_TYPE) throws Exception;
	
	/**
	 * 撤销
	 * @param GOODS_ORDER_ID 订货订单ID
	 * @param GOODS_ORDER_NO 订货订单NO
	 * @param userBean
	 */
	public void revoke(String GOODS_ORDER_ID,String GOODS_ORDER_NO,UserBean userBean);
	
	/**
	 * 根据当前登录人所属渠道获取渠道折扣率
	 */
	public String getChannDiscount(String CHANN_ID);
	/**
	 * 查询预订单明细
	 * @return
	 */
	public List<Map<String,Object>>toQuertAvdcDtl(Map<String,String>params);
	
	/**
	 * 查询 锁住此行
	 * @param GOODS_ORDER_ID
	 * @return
	 */
	public Map<String,Object> txLoadForUpdate(String GOODS_ORDER_ID);
	
	 
	 
}