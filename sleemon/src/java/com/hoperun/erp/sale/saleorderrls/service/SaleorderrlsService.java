﻿/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderService
*/
package com.hoperun.erp.sale.saleorderrls.service;
import java.util.Map;
import java.util.List;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModel;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
 
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public interface SaleorderrlsService extends IBaseService {
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
	 * @param SALE_ORDER_ID
	 * @param SaleorderModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String SALE_ORDER_ID, SaleorderspModel obj,List<SaleorderspModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param SALE_ORDER_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String SALE_ORDER_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    public List <SaleorderspModelChld> childQuery(String SALE_ORDER_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <SaleorderspModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String SALE_ORDER_ID, List <SaleorderspModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String SALE_ORDER_DTL_IDs);
    
    /**
     * 转标准订单
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_IDS 选择的明细
     * @param isAll 是否全部明细 true->是
     */
    public void txConvertTechOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,boolean isAll,UserBean userBean);
    
    /**
     * 取消预定
     * @param SALE_ORDER_DTL_IDS
     */
    public void calcelSaleOrder(String SALE_ORDER_DTL_IDS);
    
    /**
     * 恢复预定
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_ID  明细ID
     */
    public void recoverOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_ID);
    
    /**
     * 查询 工艺单 状态
     * @param SALE_ORDER_ID 销售订单ID
     * @return
     */
    public String queryTechState(String SALE_ORDER_ID);
    
    
    /**
     * 取消预定
     * @param SALE_ORDER_ID 订单ID
     * @param BILL_TYPE 单据类型
     * @param SALE_ORDER_DTL_IDS 明细IDs
     * @param FROM_BILL_DTL_IDS 来源单据IDS
     */
    public void txCalcelSaleOrder(String SALE_ORDER_ID, String BILL_TYPE,String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS,List<SaleorderspModelChld> chldModelList ,UserBean userBean)throws Exception;
    /**
     * 明细保存，保存是否备货和备注
     * @param modelList
     */
    public void txChildSave(List <SaleorderspModelChld> modelList);
    
    /**
     * 查询导出数据
     */
    public  List <Map> qrySaleOrderExp(Map params);
}