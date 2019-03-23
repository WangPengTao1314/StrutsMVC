package com.hoperun.drp.visit.storevisit.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.visit.storevisit.model.StoreVisitDtlModel;
import com.hoperun.drp.visit.storevisit.model.StoreVisitModel;
import com.hoperun.sys.model.UserBean;

public interface StoreVisitService extends IBaseService   {

	/**
	 * @param STORE_VISIT_ID
	 * @param obj
	 * @param userBean
	 */
    public void txEdit(String STORE_VISIT_ID, StoreVisitModel obj, UserBean userBean);

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
    public IListPage pageQueryWH(Map <String, String> params, int pageNo);
    
    /**
     * @param params
     * @param pageNo
     * @return
     */
    public IListPage pageQueryT(Map <String, String> params, int pageNo);
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfId(String STORE_VISIT_ID);
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfIdT(String STORE_VISIT_ID);
    
    /**
     * @param STORE_VISIT_ID
     * @param userBean
     */
    public void txDelete(String STORE_VISIT_ID,UserBean userBean);
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public List<StoreVisitDtlModel> childQuery(String STORE_VISIT_ID);
    
    /**
     * @param STORE_VISIT_ID
     * @return
     */
    public List<StoreVisitDtlModel> childQueryT(String STORE_VISIT_ID);
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<StoreVisitDtlModel> loadChilds(String STORE_VISIT_DTL_ID);
    
    /**
     * @param STORE_VISIT_ID
     * @param model
     */
    public void insertChild(String STORE_VISIT_ID, StoreVisitDtlModel model);
    
    /**
     * @param STORE_VISIT_DTL_ID
     * @param model
     */
    public void updateChild(String STORE_VISIT_DTL_ID,StoreVisitDtlModel model);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String STORE_VISIT_DTL_IDS, String STORE_VISIT_ID,UserBean userBean);
    
    /**
     * 查询数据字典信息
     * @param DATA_DTL_ID
     * @return
     */
    public String queryPro(String DATA_DTL_ID);
    
    /**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
	
	/**
	 * @param params
	 * @return
	 */
	public List expertExcelQuerySH(Map<String,String> params);
}
