package com.hoperun.drp.visit.expandvisit.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.visit.expandvisit.model.ExpandVisitDtlModel;
import com.hoperun.drp.visit.expandvisit.model.ExpandVisitModel;
import com.hoperun.sys.model.UserBean;

public interface ExpandVisitService extends IBaseService  {

	/**
	 * @param EXPAND_VISIT_ID
	 * @param obj
	 * @param userBean
	 */
    public void txEdit(String EXPAND_VISIT_ID, ExpandVisitModel obj, UserBean userBean);

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
     * @param EXPAND_VISIT_ID
     * @param userBean
     */
    public void txDelete(String EXPAND_VISIT_ID,UserBean userBean);
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfId(String EXPAND_VISIT_ID);
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public Map<String,String> loadByConfIdT(String EXPAND_VISIT_ID);
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<ExpandVisitDtlModel> loadChilds(String EXPAND_VISIT_DTL_ID);
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public List<ExpandVisitDtlModel> childQuery(String EXPAND_VISIT_ID);
    
    /**
     * @param EXPAND_VISIT_ID
     * @return
     */
    public List<ExpandVisitDtlModel> childQueryT(String EXPAND_VISIT_ID);
    
    /**
     * @param EXPAND_VISIT_ID
     * @param model
     */
    public void insertChild(String EXPAND_VISIT_ID, ExpandVisitDtlModel model);
    
    /**
     * @param EXPAND_VISIT_DTL_ID
     * @param model
     */
    public void updateChild(String EXPAND_VISIT_DTL_ID,ExpandVisitDtlModel model);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String EXPAND_VISIT_DTL_IDS, String EXPAND_VISIT_ID,UserBean userBean);
    
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
	public List<ExpandVisitModel> expertExcelQuery(Map<String,String> params);
	
	/**
	 * @param params
	 * @return
	 */
	public List<ExpandVisitModel> expertExcelQuerySH(Map<String,String> params);
}
