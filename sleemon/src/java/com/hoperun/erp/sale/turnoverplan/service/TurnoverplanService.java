package com.hoperun.erp.sale.turnoverplan.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanChildModel;
import com.hoperun.erp.sale.turnoverplan.model.TurnoverplanModel;
import com.hoperun.sys.model.UserBean;

public interface TurnoverplanService extends IBaseService{
	
	
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
     * @param SALE_ORDER_IDS  主表IDS
     * @return the list
     */
    public List <SaleorderspModelChld> childQuery(String SALE_ORDER_IDS);
    
    
    /**
     * 编辑：新增
     * @param model 发运单
     * @param childList 明细
     * @return the string
     */
    public void txEdit(TurnoverplanModel model, List<TurnoverplanChildModel> childList,UserBean userBean);
    
    /**
     *  返修单转发运单
     * @param model 发运单
     * @param childList 明细
     * @return the string
     */
    public void txEditFY(Map<String,Object> model, List<Map<String,String>> childList,UserBean userBean);
    
    
    /**
     * 判断所选订单的发货方是否是同一个区域服务中心
     * @param SALE_ORDER_IDS 订单IDS
     * @return  fasle->非同一个区域服务中心
     */
    public boolean justOnlyAreaSer(String SALE_ORDER_IDS);
    /**
     * 修改单据状态
     * @param map
     */
    public void upStart(Map<String,String> map);
    
    public List downQuery(Map<String,String> map);
}
