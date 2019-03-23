package com.hoperun.erp.sale.marketing.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.marketing.model.MarketActivitiyModel;
import com.hoperun.erp.sale.marketing.model.MarketCardModel;
import com.hoperun.erp.sale.marketing.model.MarketChannModel;
import com.hoperun.sys.model.UserBean;

public interface MarketActivitiyService extends IBaseService{
	
	  /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * 卡券子表 分页
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageCardQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param MARKETING_ACT_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String MARKETING_ACT_ID, MarketActivitiyModel model,List<MarketChannModel> chldList, 
			UserBean userBean);
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
	public Map<String,Object> load(String MARKETING_ACT_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     */
    public List <MarketChannModel> marketChannQuery(String MARKETING_ACT_ID);
	 /**
     * * 根据主表Id查询子表记录
     */
    public List <MarketCardModel> marketCardQuery(String MARKETING_ACT_ID);
    
    
    /**
     * * 根据子表Id批量加载子表信息
     */
    public List <MarketChannModel> loadMarketChannModels(Map <String, String> params) ;
	
    /**
     * * 根据子表Id批量加载子表信息
     */
    public List <MarketCardModel> loadMarketCardModels(Map <String, String> params) ;
    
	/**
	 * 子表保存
	 * @return
	 */
    public boolean txMarketChannEdit(String MARKETING_ACT_ID, List <MarketChannModel> modelList);
	/**
	 * 批量 生成卡券
	 * @return
	 */
    public boolean txMarketCardEdit(String MARKETING_ACT_ID, MarketCardModel model,UserBean userBean);
    
	 /**
     * * 子表的批量删除
     */
    public void txBatch4DeleteMarketCard(Map<String,String>params);
    
	 /**
     * * 子表的批量删除
     */
    public void txBatch4DeleteMarketChann(Map<String,String>params);
    
    
	/**
	 * 加载渠道
	 * @param param CHANN_ID
	 * @return the map< string, string>
	 */
	public Map<String,Object> loadChann(String CHANN_ID);
	
    /**
     * 卡券开启
     * @param MARKETING_CARD_IDS
     * @param userBean
     */
	public void txMarketCardStart(String MARKETING_CARD_IDS, UserBean userBean);
	
	  /**
     * 卡券关闭
     * @param MARKETING_CARD_IDS
     * @param userBean
     */
	public void txMarketCardStop(String MARKETING_CARD_IDS, UserBean userBean);
	
	/**
     * 查找已经参加的活动加盟商ID
     * @param MARKETING_ACT_ID
     * @return
     */
    public String queryChannIdsByFkId(String MARKETING_ACT_ID);
    
    /**
     * 导出excel
     * @param params
     * @return
     */
    public List exportExcel(Map<String,String> params);
	

}
