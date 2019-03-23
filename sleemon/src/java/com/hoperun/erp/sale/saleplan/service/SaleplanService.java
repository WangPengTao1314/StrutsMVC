package com.hoperun.erp.sale.saleplan.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.saleplan.model.SaleplanModel;
import com.hoperun.erp.sale.saleplan.model.SaleplandtlModel;
import com.hoperun.sys.model.UserBean;

public interface SaleplanService extends IBaseService {

	/** 
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
     public IListPage pageQuery(Map <String, String> params, int pageNo);
     
     /**
      * @param params
      * @param pageNo
      * @return
      */
     public IListPage pageBatch(Map <String, String> params, int pageNo);
     /**
      * * 根据主表Id查询子表记录
      * @param DELIVER_ORDER_ID  主表ID
      * @return the list
      */
     public List <SaleplandtlModel> childQuery(String DELIVER_ORDER_ID);
     
     
     /**
 	 * @主表详细页面
 	 * @param param SALE_PLAN_ID
 	 */
 	public Map<String,String> load(String SALE_PLAN_ID);
 	
 	
    /**
     * @param year
     * @param month
     * @param saleAmount
     * @param chanAmount
     * @return
     */
    public void insertChild(String SALE_PLAN_ID,String year,String month,
			String saleAmount, String chanAmount);
    
    
    /**
     * @param SALE_PLAN_ID
     * @param year
     * @param month
     * @param saleAmount
     * @param chanAmount
     * @return
     */
    public void updateChild(String SALE_PLAN_DTL_ID,String SALE_PLAN_ID,String year,String month,
			String saleAmount, String chanAmount);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String SALE_PLAN_DTL_IDs, UserBean userBean);
    
    /**
     * @param SALE_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     * @return
     */
    public void txEdit(String SALE_PLAN_ID, SaleplanModel obj, UserBean userBean,List <SaleplandtlModel>  mxList);
    
    /**
     * @param SALE_PLAN_ID
     * @param obj
     * @param userBean
     */
    public void batchUpdate(SaleplanModel obj, UserBean userBean);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String SALE_PLAN_ID, UserBean userBean);
    
    /**
     * @param SALE_PLAN_ID
     * @param userBean
     * @return
     */
    public void txDeleteChild(String SALE_PLAN_ID, UserBean userBean);
    
    /**
     * @param SALE_PLAN_DTL_IDS
     * @return
     */
    public List<SaleplandtlModel> loadByIds(String SALE_PLAN_DTL_IDS);
    
    /**
     * @param PLAY_YEAR
     * @return
     */
    public List<SaleplanModel> queryJudgeModel(String PLAY_YEAR);
    
    /**
     * @param obj
     * @return
     */     
    public List<SaleplanModel> queryChannAndYear(Map<String,String> params);
}
