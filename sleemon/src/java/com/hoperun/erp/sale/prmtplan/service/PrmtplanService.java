/**
 * prjName:喜临门营销平台
 * ucName:推广促销方案维护
 * fileName:PrmtplanService
*/
package com.hoperun.erp.sale.prmtplan.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.prmtplan.model.PrmtplaAreaModel;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModel;
import com.hoperun.erp.sale.prmtplan.model.PrmtplanModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-08-23 16:04:28
 */
public interface PrmtplanService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param PRMT_PLAN_ID
	 * @param PrmtplanModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String PRMT_PLAN_ID, PrmtplanModel obj,List<PrmtplanModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param PRMT_PLAN_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String PRMT_PLAN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param PRMT_PLAN_ID the PRMT_PLAN_ID
     * @return the list
     */
    public List <PrmtplanModelChld> childQuery(String PRMT_PLAN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param PRMT_PRD_GROUP_IDs the PRMT_PRD_GROUP_IDs
     * 
     * @return the list
     */
    public List <PrmtplanModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param PRMT_PLAN_ID the PRMT_PLAN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String PRMT_PLAN_ID, List <PrmtplanModelChld> modelList);
	 /**
     * * 子表的批量删除
     * @param PRMT_PRD_GROUP_IDs the PRMT_PRD_GROUP_IDs
     */
    public void txBatch4DeleteChild(String PRMT_PRD_GROUP_IDs);
    
    /**
     * 发布
     * 更新状态  以及相关信息
     */
    public void updateIssuance(String id,String state,UserBean userBean);
    
    
	 /**
     * * 根据主表Id查询子表记录  生效区域
     * @param PRMT_PLAN_ID the PRMT_PLAN_ID
     * @return the list 生效区域
     */
    public List <PrmtplaAreaModel> areaQuery(String PRMT_PLAN_ID);
    
    /**
     * * 子表的批量删除 生效区域
     * @param PRMT_EFFCT_AREA_IDS the PRMT_EFFCT_AREA_IDS
     */
    public void txBatch4DeleteArea(String PRMT_EFFCT_AREA_IDS);
    
    /**
     * * 根据子表Id批量加载子表信息 生效区域
     * @param PRMT_PRD_GROUP_IDs the PRMT_PRD_GROUP_IDs
     * 
     * @return the list
     */
    public List <PrmtplaAreaModel> loadAreas(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改 生效区域
     * @param PRMT_PLAN_ID the PRMT_PLAN_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txAreaEdit(String PRMT_PLAN_ID, List <PrmtplaAreaModel> modelList);
    
    /**
     * 
     * @param params
     * @return
     */
    public List findAreaList(Map <String, String> params);
}