package com.hoperun.erp.oamg.monthVisit.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.oamg.monthVisit.model.MonthvisitModel;
import com.hoperun.erp.oamg.monthVisit.model.MonthvisitdtlModel;
import com.hoperun.sys.model.UserBean;

public interface MonthVisitService  extends IBaseService {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * * 根据主表Id查询子表记录
     * @param DELIVER_ORDER_ID  主表ID
     * @return the list
     */
    public List <MonthvisitdtlModel> childQuery(String MONTH_VISIT_PLAN_ID);
    
    /**
 	 * @主表详细页面
 	 * @param param SALE_PLAN_ID
 	 */
 	public Map<String,String> load(String MONTH_VISIT_PLAN_ID);
 	
 	 /**
     * @param MONTH_VISIT_PLAN_DTL_IDS
     * @return
     */
    public List<MonthvisitdtlModel> loadByIds(String MONTH_VISIT_PLAN_DTL_IDS);
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param VISIT_OBJ_TYPE
     * @param VISIT_OBJ_ID
     * @param VISIT_OBJ_NO
     * @param VISIT_OBJ_NAME
     * @param PLAN_VISIT_NUM
     */
    public void txInsertChild(String MONTH_VISIT_PLAN_ID,String VISIT_OBJ_TYPE,String PLAN_VISIT_NUM,String VISIT_TYPE);
    
    /**
     * @param MONTH_VISIT_PLAN_DTL_ID
     * @param MONTH_VISIT_PLAN_ID
     * @param VISIT_OBJ_TYPE
     * @param VISIT_OBJ_ID
     * @param VISIT_OBJ_NO
     * @param VISIT_OBJ_NAME
     * @param PLAN_VISIT_NUM
     */    
    public void txUpdateChild(String MONTH_VISIT_PLAN_DTL_ID,String MONTH_VISIT_PLAN_ID,String VISIT_OBJ_TYPE,String PLAN_VISIT_NUM,String VISIT_TYPE);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public String txBatch4DeleteChild(String MONTH_VISIT_PLAN_DTL_IDs, UserBean userBean,String MONTH_VISIT_PLAN_ID);
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     */
    public void txEdit(String MONTH_VISIT_PLAN_ID, MonthvisitModel obj, UserBean userBean,List <MonthvisitdtlModel>  mxList);
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String MONTH_VISIT_PLAN_ID, UserBean userBean);
    
    /**
     * @param MONTH_VISIT_PLAN_ID
     * @param userBean
     */
    public void txDeleteChild(String MONTH_VISIT_PLAN_ID, UserBean userBean);
}
