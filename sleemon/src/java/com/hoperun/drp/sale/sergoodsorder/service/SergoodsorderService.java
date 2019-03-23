/**
 * prjName:喜临门营销平台
 * ucName:订货订单处理
 * fileName:GoodsorderService
*/
package com.hoperun.drp.sale.sergoodsorder.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.goodsorderhd.model.GoodsorderhdModelTrace;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModel;
import com.hoperun.erp.sale.goodsorder.model.GoodsorderModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl *@func 分销 -区域服务中心- 订货订单处理 
 * *@version 1.1 *@author zzb 
 * *@createdate
 * 2014-05-22 15:55:09
 */
public interface SergoodsorderService extends IBaseService {
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
	 * @param GoodsorderModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String GOODS_ORDER_ID, GoodsorderModel obj,List<GoodsorderModelChld> chldList, UserBean userBean);
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
	public Map<String,String> load(String GOODS_ORDER_ID);
	
	/**
	 * 加载主表数据 
	 * @param param GOODS_ORDER_ID
	 * @return GoodsorderModel
	 */
	public GoodsorderModel loadById(String GOODS_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @return the list
     */
    public List <GoodsorderModelChld> childQuery(String GOODS_ORDER_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <GoodsorderModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String GOODS_ORDER_ID, List <GoodsorderModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String GOODS_ORDER_DTL_IDs);
    
    /**
     * 查询默认的工厂
     * @param GOODS_ORDER_ID
     * @return
     */
    public Map<String,String>  queryDefaultFactory(String GOODS_ORDER_ID);
    
    /**
     * 插入 销售订单、销售订单明细 、销售订单特殊工艺 三张表
     * @param model 订货订单
     * @param chilList 订货订单明细
     */
    public void txAddSalOrder(GoodsorderModel model,List <GoodsorderModelChld> chilList,UserBean userBean);
    
    /**
     * 更新预订单的状态
     * @param GOODS_ORDER_ID 订货订单ID
     * @param reason 退货原因
     */
    public void txUpdateAdvcOrder(String GOODS_ORDER_ID,String reason,UserBean userBean)throws Exception;
    
    
    /**
     * 取消 订货
     * @param GOODS_ORDER_ID 订单ID
     * @param mxIds 明细IDS
     * @param remark 取消原因
     * @param isAll 是否全部 取消 true 全部取消
     */
    public void updateOrderClose(String GOODS_ORDER_ID,String mxIds,String remark,boolean isAll,UserBean userBean);
    
    
    /**
     * * 根据主表Id查询生命周期子表记录
     * @param GOODS_ORDER_ID the GOODS_ORDER_ID
     * @return the list
     */
    public List <GoodsorderhdModelTrace> traceQuery(String GOODS_ORDER_ID);
    
    
    
    
    
}