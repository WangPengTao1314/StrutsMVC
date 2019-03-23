/**
 * prjName:喜临门营销平台
 * ucName:推广费用申请单维护
 * fileName:PromoexpenService
*/
package com.hoperun.drp.oamg.promoexpen.service;
import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.promoexpen.model.PromoexpenModel;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderChldModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2014-01-24 10:59:55
 */
public interface PromoexpenService extends IBaseService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuerySH(Map<String,String> params, int pageNo);
	
    /**
	 * @编辑
	 * @Description :
	 * @param PRMT_COST_REQ_ID
	 * @param PromoexpenModel
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String PRMT_COST_REQ_ID, PromoexpenModel obj, UserBean userBean);
	
	/**
     * 新增的时候 自动判断是第几次申请，如果不是首次数据项自动带出
     * @param CHANN_RNVTN_ID 装修申请单ID
     * @return
     */
	 public Map<String,Object> queryJudgeModel(String CHANN_ID,String QUARTER,String YEAR);
	 
	 /**
	  * @param CHANN_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel1(String CHANN_ID,String QUARTER,String YEAR);
	 
	 /**
	  * @param RELATE_AREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel2(String RELATE_AREA_ID,String QUARTER,String YEAR);
	 
	 /**
	  * @param RELATE_AREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel3(String RELATE_AREA_ID,String QUARTER,String YEAR);
	 
	 /**
	  * @param WAREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel4(String WAREA_ID,String QUARTER,String YEAR);
	 
	 /**
	  * @param WAREA_ID
	  * @param QUARTER
	  * @param YEAR
	  * @return
	  */
	 public String queryJudgeModel5(String WAREA_ID,String QUARTER,String YEAR);
	 
	/**
	 * @删除
	 * @param PRMT_COST_REQ_ID.
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @详细页面
	 * @param param PRMT_COST_REQ_ID
	 * @param param the param
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param);
	
	 /**
     * 修改状态为停用
     * Description :.
     * @param params the params
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);

    /**
     * 修改状态为启用
     * Description :.
     * @param params the params
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);
    
    /**
     * 加载多文件路径
     * @param param
     * @return
     */
    public String loadFiles(String param);
    
	/**
	 * 导出excel
	 * @return
	 */
	public List expertExcelQuery(Map<String,String> params);
	
	/**
	 * @param params
	 * @return
	 */
	public List expertExcelQueryWH(Map<String,String> params);
	
	/**
	 * @param PRMT_COST_REQ_ID
	 * @return
	 */
	public BudgetquotaModel qyeryQuotaAmount(String PRMT_COST_REQ_ID);
	
	/**
	 * @param PRMT_COST_REQ_ID
	 * @return
	 */
	public List <PromoexpenModel> childQuery(String PRMT_COST_REQ_ID);

}