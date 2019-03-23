package com.hoperun.drp.oamg.workplanmage.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmageModel;
import com.hoperun.drp.oamg.workplanmage.model.WorkplanmagedtlModel;
import com.hoperun.sys.model.UserBean;

public interface WorkplanMageService extends IBaseService {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
 	 * @主表详细页面queryJudgeModel
 	 * 
 	 * @param param WORK_PLAN_ID
 	 */
 	public Map<String,String> load(String WORK_PLAN_ID);
 	
 	/**
     * * 根据主表Id查询子表记录
     * @param WORK_PLAN_ID  主表ID
     * @return the list
     */
    public List <WorkplanmagedtlModel> childQuery(String WORK_PLAN_ID);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public List <WorkplanmagedtlModel> queryJudgeModel(String WAREA_ID);
    
    /**
     * @param WORK_PLAN_ID
     * @param obj
     * @param userBean
     * @param mxList
     */
    public String txEdit(String WORK_PLAN_ID, WorkplanmageModel obj, UserBean userBean,List <WorkplanmagedtlModel>  mxList);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String WORK_PLAN_ID, UserBean userBean);
    
    /**
     * @param WORK_PLAN_ID
     * @param userBean
     * @return
     */
    public void txDeleteChild(String WORK_PLAN_ID, UserBean userBean);
    
	 /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<WorkplanmagedtlModel> loadChilds(String WORK_PLAN_DTL_IDs);
    
    /**
     * @param WORK_PLAN_ID
     * @param RYXXID
     * @param RYBH
     * @param RYMC
     */
    public String insertChild(String WORK_PLAN_ID,String RYXXID,String RYBH,String RYMC,List <WorkplanmagedtlModel> modelList);
    
    /**
     * @param SALE_PLAN_DTL_ID
     * @param WORK_PLAN_ID
     * @param RYXXID
     * @param RYBH
     * @param RYMC
     */
    public String updateChild(String SALE_PLAN_DTL_ID,String WORK_PLAN_ID,String RYXXID,String RYBH,String RYMC,List <WorkplanmagedtlModel> modelList);
    
    /**
     * @param WORK_PLAN_DTL_IDs
     * @param userBean
     */
    public void txBatch4DeleteChild(String WORK_PLAN_DTL_IDs, UserBean userBean);
    
    /**
     * @param WORK_PLAN_DTL_IDs
     * @param userBean
     */
    public String txBatch4DeleteChildT(String WORK_PLAN_DTL_IDs, UserBean userBean,String WORK_PLAN_ID);
    
    /**
     * @param WORK_PLAN_ID
     * @param userBean
     */
    public void txDeleteChildT(String WORK_PLAN_ID, UserBean userBean);
    
    /**
     * @param WORK_PLAN_ID
     * @param RYBH
     * @return
     */
    public int  queryRYForInt(String WORK_PLAN_ID,String RYBH);
    
    
}
