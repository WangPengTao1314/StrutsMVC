package com.hoperun.erp.sale.turnoverhd.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public interface TurnoverhdService extends IBaseService{
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    
    
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <TurnoverplanChildModel> childQuery(String DELIVER_ORDER_ID);
    
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID);
	
	 /**
     * 编辑：新增
     * @param model 发运单
     * @return the string
     */
    public void txEdit(TurnoverplanModel model,UserBean userBean);
    
    
    /**
     * 未排货品查看 
     *  查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageNotPalanQuery(Map<String, String> params, int pageNo);
    
    /**
     * 重排
     * @param DELIVER_ORDER_ID 发运单ID
     */
    public void txAgain(String DELIVER_ORDER_ID,UserBean userBean);
    
    /**
     * 提交交付计划
     * @param DELIVER_ORDER_ID
     * @param userBean
     */
    public String txCommitPlan(String DELIVER_ORDER_ID,String strJsonData,UserBean userBean,String AppCode,String UId,String OPRCODE,String DELIVER_ORDER_NO)throws Exception;
    
    /**
     * 修改交期
     * @param DELIVER_ORDER_ID 主表ID
     * @param DATE 交期
     * @param userBean
     */
    public String txUpdateAdvDate(String DELIVER_ORDER_ID,String strJsonData,String DATE,UserBean userBean)throws Exception;
	

	/**
     * * 根据主表Id查询生命周期子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
	public List <Map<String,String>> queryTrace(String SALE_ORDER_ID);
	/**
	 * 撤销
	 * @param DELIVER_ORDER_ID
	 * @param userBean
	 */
	public String txCancel(String DELIVER_ORDER_ID,UserBean userBean,String strJsonData);
	
	
	 /**
     * 行关闭
     * @param DELIVER_ORDER_DTL_IDS
     * @param userBean
     */
    public String txCloseChilds(String DELIVER_ORDER_ID,String DELIVER_ORDER_DTL_IDS,
    		UserBean userBean,String strJsonData);
    
    
    
	public List downQuery(Map<String,String> map);
	
	public List checkDeliverOrderDtl(String DELIVER_ORDER_ID);
	
	
	 /**
	 * * 明细数据编辑
	 * 
	 */
	public void txChildEdit(String DELIVER_ORDER_ID, List<TurnoverplanChildModel> chldList,UserBean userBean);
	
}
