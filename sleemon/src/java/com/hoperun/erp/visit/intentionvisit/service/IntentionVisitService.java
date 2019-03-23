package com.hoperun.erp.visit.intentionvisit.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.visit.intentionvisit.model.IntentionVisitModel;
import com.hoperun.sys.model.UserBean;

public interface IntentionVisitService extends IBaseService  {

	/**
	 * @param INTE_CHANN_ID
	 * @param obj
	 * @param userBean
	 */
    public void txEdit(String EXPAND_VISIT_ID, IntentionVisitModel obj, UserBean userBean);

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    
    /**
     * @param INTE_CHANN_ID
     * @param userBean
     */
    public void txDelete(String INTE_CHANN_ID,UserBean userBean);
    
    /**
     * @param INTE_CHANN_ID
     * @return
     */
    public Map<String,String> loadById(String INTE_CHANN_ID);
    
}
