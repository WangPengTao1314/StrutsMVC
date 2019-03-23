package com.hoperun.erp.sale.deliverconfm.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public interface DeliverconfmService extends IBaseService{
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
     * * 根据主表Id查询子表记录
     * @param  params
     * @return the list
     */
    public List <TurnoverplanChildModel> childQuery(Map<String,String> params);
    
	
	
	/**
	 * @主表详细页面
	 * @param param DELIVER_ORDER_ID
	 */
	public Map<String,String> load(String DELIVER_ORDER_ID);
	
	
	/**
	 * @主表面
	 * @param param DELIVER_ORDER_NO
	 */
	public Map<String,String> loadMain(String DELIVER_ORDER_ID);

	
	public Map<String,String> loadDtlBySaleOrderNo(Map <String, String> params);
	
	/**
     * * 根据子表Id批量加载子表信息
     * @param GOODS_ORDER_DTL_IDs the GOODS_ORDER_DTL_IDs
     * 
     * @return the list
     */
    public List <TurnoverplanChildModel> loadChilds(Map <String, String> params) ;
	
	 /**
     * 编辑：新增
     * @param model 发运单
     * @param childList 明细
     * @return the string
     */
    public void txEdit(TurnoverplanModel model,List<TurnoverplanChildModel> childList,UserBean userBean);
    
    /**
     * 发运确认
     * @param DELIVER_ORDER_ID 发运单ID
     * @param childList 发运单明细
     * @param userBean
     */
    public Map<String,Object>  txDespatchAffirm(String DELIVER_ORDER_ID,UserBean userBean);
    
    /**
     * 填写延时原因
     * @param  params
     */
    public void txWriteReason(Map<String,String> params);
    
    /**
     * 发运确认的时候验证明细是否有差异数量
     * @param DELIVER_ORDER_ID
     * @return return true -> 有差异不能提交
     */
    public boolean txCheckCommit(String DELIVER_ORDER_ID);
    public List downQuery(Map<String,String> map);
    
    /**
     * 字表编辑
     * @param childList
     * @param userBean
     */
    public void txChildEdit(String DELIVER_ORDER_ID,String DELIVER_ORDER_NO,List<TurnoverplanChildModel> childList,UserBean userBean);
	
	

}
