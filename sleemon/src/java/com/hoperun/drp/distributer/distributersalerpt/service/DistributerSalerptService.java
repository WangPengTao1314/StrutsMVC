package com.hoperun.drp.distributer.distributersalerpt.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.distributer.distributersalerpt.model.DistributerSalerptChildModel;
import com.hoperun.drp.distributer.distributersalerpt.model.DistributerSalerptModel;
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModelChld;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   分销商购货月报
 *@version 1.1
 *@author gu_hx
 */
public interface DistributerSalerptService extends IBaseService  {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @return
     */
    public Map <String, String> load(String DISTRIBUTOR_SALE_RPT_ID);    
    
	/**
	 * 编辑
	 * @param obj
	 * @param userBean
	 */
	public void txEdit(DistributerSalerptModel obj,UserBean userBean);
	
    /**
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String DISTRIBUTOR_SALE_RPT_ID, UserBean userBean);
    
    /**
     * 主子表信息详情
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @return
     */
    public DistributerSalerptModel find(String DISTRIBUTOR_SALE_RPT_ID);
    
    /**
     * * 子表记录编辑：新增/修改
     * @param DISTRIBUTOR_SALE_RPT_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String DISTRIBUTOR_SALE_RPT_ID, List <DistributerSalerptChildModel> modelList);
        
	/**
	 * 子表的批量删除
	 * @param DISTRIBUTOR_SALE_RPT_DTL_IDS
	 */
	public void txBatch4DeleteChild(String DISTRIBUTOR_SALE_RPT_DTL_IDS);
		
    /**
     * 主表状态更新（提交/撤销）
     * @param params
     * @return
     */
    public boolean txUpdateStateById(Map <String, String> params);
    
    /**
     * 查询导出数据
     */
    public  List <Map> qryExp(Map params);
    
}
