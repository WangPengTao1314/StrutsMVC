package com.hoperun.drp.sale.sersaleorder.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.sersaleorder.model.SersaleorderModel;
import com.hoperun.drp.sale.sersaleorder.model.SersaleorderModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@func 分销-区域服务中心-销售订单维护
 * *@version 1.1
 * *@author zzb
 * *@createdate 2014-5-23 13:52:19
 */
public interface SersaleorderService extends IBaseService {
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
	public void txEdit(String SALE_ORDER_ID, SersaleorderModel obj,List<SersaleorderModelChld> chldList, UserBean userBean);
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
    public List <SersaleorderModelChld> childQuery(String SALE_ORDER_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <SersaleorderModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String SALE_ORDER_ID, List <SersaleorderModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs
     */
    public void txBatch4DeleteChild(String SALE_ORDER_DTL_IDs);
    
    /**
     * 转非标订单
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_IDS  明细IDS
     * @param isAll true->全部
     */
    public void txConvertTechOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,boolean isAll,UserBean userBean);
    
    /**
     * 取消预定
     * @param SALE_ORDER_ID 订单ID
     * @param SALE_ORDER_DTL_IDS 明细IDs
     * @param FROM_BILL_DTL_IDS 来源单据IDS
     */
    public void txCalcelSaleOrder(String SALE_ORDER_ID, String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS,UserBean userBean)throws Exception;
    
    /**
     * 恢复预定
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_ID  明细ID
     * @param FROM_BILL_DTL_ID 来源单据ID
     */
    public void txRecoverOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_ID,String FROM_BILL_DTL_ID,UserBean userBean)throws Exception;
    
    /**
     * 提交  
     * @param SALE_ORDER_ID 销售订单ID
     */
    public void txCommit(String SALE_ORDER_ID ,UserBean userBean);
    
    
    
    
}