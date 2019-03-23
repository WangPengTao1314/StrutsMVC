
package com.hoperun.drp.main.shopcar.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.main.shopcar.model.ShopcarChannInfoModel;
import com.hoperun.drp.main.shopcar.model.ShopcarModel;
import com.hoperun.sys.model.UserBean;

/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-08-19 14:35:52
 */
public interface ShopcarService extends IBaseService {
	/**
	 * 查询
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	//修改购物车
	public void update(List<ShopcarModel> ModelList,UserBean userBean)throws ParseException;
     
	/**
	 * 查询当前渠道
	 * @param CHANN_ID 渠道ID
	 * @return
	 */
    public Map<String,String> getChannInfo(String CHANN_ID);
    
    
    //修改特殊工艺后修改该条的价格
    public void upPrice(Map<String,String> map);
    
    /**
     * 反填到订货订单里
     * 
     * @param channModel
     * @param SHOP_CART_IDS
     * @param userBean
     * @param info
     * @return
     */
    public Map<String,String> txInsertDRP_GOODS_ORDER(ShopcarChannInfoModel channModel,String SHOP_CART_IDS,UserBean userBean,Map<String,String> info) throws Exception;
    public long getCount(Map<String,String> map);
    public Map<String,String> getCHANN_TYPE(String CHANN_ID);
    public String getLargessDect(String CHANN_ID);
    //购物车保存提交
    public boolean txSaveCommit(Map<String,String> shopParams,List<ShopcarModel> ModelList,UserBean userBean,ShopcarChannInfoModel channModel) throws Exception;
    
}