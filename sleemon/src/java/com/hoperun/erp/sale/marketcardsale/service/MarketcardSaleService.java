package com.hoperun.erp.sale.marketcardsale.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleChldModel;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleModel;
import com.hoperun.sys.model.UserBean;

public interface MarketcardSaleService extends IBaseService {
	
	
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
	 * @param CARD_SALE_ORDER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String CARD_SALE_ORDER_ID, MarketcardSaleModel model,
			List<MarketcardSaleChldModel> chldList,  UserBean userBean);
	
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param  
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String CARD_SALE_ORDER_ID);
	
	public List <MarketcardSaleChldModel> childQuery(String CARD_SALE_ORDER_ID);
	
	public List <MarketcardSaleChldModel> loadChilds(Map <String, String> params);
	/**
	 * 子表保存
	 * @return
	 */
    public void txChildEdit(String CARD_SALE_ORDER_ID, 
    		List<MarketcardSaleChldModel> chldList,UserBean userBean);
    
    
    public void txBatch4DeleteChild(Map<String,String> params);
    
  
    /**
     * 编辑会员信息表
     * @param CARD_SALE_ORDER_ID 卡券销售ID
     * @param userBean
     */
    public void txCreateMemberInfo(String CARD_SALE_ORDER_ID,UserBean userBean);
    
    /**
     * 同一活动下 同一个客户只能有一张卡券
     * @param CARD_SALE_ORDER_ID
     */
    public void queryCusNum(String CARD_SALE_ORDER_ID);
    
    /**
     * 根据手机号查询会员信息
     * @param MOBILE_PHONE
     * @return
     */
    public Map<String,String> getMemberInfo(String MOBILE_PHONE);
    
    /**
     * 手机卡券销售
     * @param chldList 
     * @param userBean
     */
	public void txMobileCrdSaleEdit(String MARKETING_CARD_NO,List<MarketcardSaleChldModel> chldList, UserBean userBean);
	
	
	/**
	 * 根据卡券编号 查询 已销售的卡券信息
	 * @param MARKETING_CARD_NO
	 * @return
	 */
	public Map<String,String> loadCard(String MARKETING_CARD_NO);
	
	/**
	 * 根据卡券编号查询   未销售的卡券信息 
	 * @param MARKETING_CARD_NO
	 * @return
	 */
	public Map<String,String> loadNoSaleCard(String MARKETING_CARD_NO);
 
    /**
     * 已销售未签到的卡券可以关闭
     * @param CARD_SALE_ORDER_ID 
     * @param CARD_SALE_ORDER_DTL_IDS
     * @param userBean
     */
	public void txChildClose(String CARD_SALE_ORDER_ID,String CARD_SALE_ORDER_DTL_IDS,UserBean userBean);
	
	/**
	 * 开启
	 * @param CARD_SALE_ORDER_ID
	 * @param CARD_SALE_ORDER_DTL_IDS
	 * @param userBean
	 */
	public void txChildOpen(String CARD_SALE_ORDER_ID,String CARD_SALE_ORDER_DTL_IDS,UserBean userBean);

}
