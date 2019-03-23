package com.hoperun.drp.areaser.storeoutnotice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.areaser.storeoutnotice.model.StoreoutnoticeChldModel;
import com.hoperun.drp.areaser.storeoutnotice.model.StoreoutnoticeModel;
import com.hoperun.sys.model.UserBean;

public interface StoreoutnoticeService extends IBaseService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_INV_ID the PRD_INV_ID
     * @return the list
     */
    public List <StoreoutnoticeChldModel> childQuery(String STOREOUT_NOTICE_ID);
    
    /**
	 * @主表详细页面
	 * @param param PRD_INV_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREOUT_NOTICE_ID);
	/**
     * * 根据子表Id批量加载子表信息
     * @param PRD_INV_DTL_IDs the PRD_INV_DTL_IDs
     * 
     * @return the list
     */
    public List <StoreoutnoticeChldModel> loadChilds(Map <String, String> params) ;
    
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param PRD_INV_ID
	 * @param InventoryModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String STOREOUT_NOTICE_ID, StoreoutnoticeModel obj,List<StoreoutnoticeChldModel> chldList, UserBean userBean);
	
	/**
     * * 子表记录编辑：新增/修改
     * @param PRD_INV_ID the PRD_INV_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String STOREOUT_NOTICE_ID, List <StoreoutnoticeChldModel> modelList,UserBean userBean,String actionType);
    
    /**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	 /**
     * * 子表的批量删除
     * @param STOREOUT_NOTICE_DTL_IDS the STOREOUT_NOTICE_DTL_IDS
     */
    public void txBatch4DeleteChild(String STOREOUT_NOTICE_DTL_IDS);
    
    public boolean txUpdateById(Map<String, String> params);
    /**
     * 点击提交按钮时改变单据状态为审核通过并且生成出库单
     * @param STOREOUT_NOTICE_ID
     * @param userBean
     */
	public void txCommit(String STOREOUT_NOTICE_ID,UserBean userBean);
	
	/**
	 * 查询 明细的库存
	 * @param modelList 明细
	 * @param STORE_ID 库房ID
	 * @param SALE_ORDER_ID 销售订单ID
	 * @return
	 */
	public boolean checkStoreNum(List <StoreoutnoticeChldModel> modelList,String STORE_ID,String SALE_ORDER_ID);
	
}
