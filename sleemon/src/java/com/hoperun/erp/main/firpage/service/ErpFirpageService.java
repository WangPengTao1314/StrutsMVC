package com.hoperun.erp.main.firpage.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.sys.model.UserBean;

public interface ErpFirpageService extends IBaseService{
	
	/**
	 * 查询推广促销
	 * @param size 记录数
	 * @return
	 */
	public List<PrmtplanModel> queryPrmtpList(UserBean userBean,int size);
	
	
	/**
	 * 加载公告
	 * @param NOTICE_ID
	 * @return
	 */
	public Map<String,String> loadNoticeModel(String NOTICE_ID);
	
	/**
	 * 公告加载更多
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryNotice(Map<String,String> params, int pageNo);
	
	
	/**
	 * 加载促销
	 * @param PRMT_PLAN_ID
	 * @return
	 */
	public Map<String,String> loadPrmtModel(String PRMT_PLAN_ID);
	
	/**
	 * 公告促销更多
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryPrmt(Map<String,String> params, int pageNo);
	
	/**
	 * 查询  待发货预订单   待入库  待退货 
	 */
    public List queryCount();
    
	/**
	 *查询 待退货 
	 */
    public int queryPrdRetuenReqCount(Map<String,String> params);
    
    /**
	 *查询  待入库  状态 已提交
	 */
    public int queryStoreIn(Map<String,String> params);
    /**
	 *查询  待发货  状态 待发货
	 */
    public int queryOrderCount(Map<String,String> params);
    
    /**
     * 首页柱状图
     * @return
     */
    public List queryBar();
    
    
    //查询订货订单处理未处理状态条数
    public int queryGoodsCount(String CHANNS_CHARG);
    //查询制定交付计划条数
    public int queryTurnoverplanCount(UserBean userBean);
    //查询交付计划维护
    public int queryTurnoverhdCount(UserBean userBean);
    //货品发运
    public int queryPdtdeliverCount(UserBean userBean);
    
    //发运确认
    public int querydeliverconfmCount(UserBean userBean);
    /**
     * 查询待审核的工艺单
     * @param userBean
     * @return
     */
    public int queryTechorderAudit(UserBean userBean);
    /**
     * 查询核价的工艺单
     * @param userBean
     * @return
     */
    public int queryTechorderPrice(UserBean userBean);
   

}
