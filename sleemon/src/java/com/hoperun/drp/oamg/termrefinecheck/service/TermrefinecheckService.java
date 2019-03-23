/**
 * prjName:喜临门营销平台
 * ucName:门店精致化检查结果
 * fileName:TermrefinecheckService
*/
package com.hoperun.drp.oamg.termrefinecheck.service;
import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformChildModel;
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModelChild;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModel;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModelChld;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2014-01-26 14:46:31
 */
public interface TermrefinecheckService extends IBaseService {
    
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	public String queryIdByNo(String CHANN_CHECK_PLAN_NO);
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param TERM_REFINE_CHECK_ID
	 * @param TermrefinecheckModel
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String CHANN_CHECK_PLAN_ID, TermrefinecheckModel obj,List<TermrefinecheckModelChld> mxList, UserBean userBean,String chkLen);
	
	/**
	 * @主表删除
	 * @param Map
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param TERM_REFINE_CHECK_ID
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String CHANN_CHECK_PLAN_ID);
	
	/**
	 * @param TERM_REFINE_CHECK_ID
	 * @return
	 */
	public Map<String,String> loadByConf(String CHANN_CHECK_PLAN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param TERM_REFINE_CHECK_ID the TERM_REFINE_CHECK_ID
     * @return the list
     */
    public List <TermrefinecheckModelChld> childQuery(String CHANN_CHECK_PLAN_ID);
	
    /**
     * * 根据子表Id批量加载子表信息
     * @param TERM_REFINE_CHECK_DTL_IDs the TERM_REFINE_CHECK_DTL_IDs
     * @return the list
     */
    public List <TermrefinecheckModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param TERM_REFINE_CHECK_ID the TERM_REFINE_CHECK_ID
     * @param modelList the model list
     * @return true, if tx child edit
     */
    public String txChildEdit(String TERM_REFINE_CHECK_ID, List <TermrefinecheckModelChld> modelList);
	 
    /**
     * * 子表的批量删除
     * @param TERM_REFINE_CHECK_DTL_IDs the TERM_REFINE_CHECK_DTL_IDs
     */
    public void txBatch4DeleteChild(String CHANN_CHECK_PLAN_ID,String CHANN_CHECK_PLAN_DTL_IDs);
    
    /**
     * 获取所有品牌名称
     * @return
     */
    public List<String> getBrand(String type);
    
    /**
     * @param list
     * @param userBean
     * @param PRD_INV_ID
     * @throws Exception
     */
    public void txParseExeclToDbNew(List list, UserBean userBean,String PRD_INV_ID) throws Exception;
    
    /**
     * @param TERM_REFINE_CHECK_DTL_ID
     */
    public void txDeleteChild(String CHANN_CHECK_PLAN_DTL_ID);
    
    /**
     * @param code
     * @param name
     * @param TERM_REFINE_CHECK_ID
     * @return
     */
    public List<TermrefinecheckModelChld> queryTypeAndName(String code,String name,String CHANN_CHECK_PLAN_ID);
    
    /**
     * @param TERM_REFINE_CHECK_ID
     * @param PRJ_TYPE
     * @param PRJ_CODE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     * @param modelList
     * @return
     */
    public String insertChild(String TERM_REFINE_CHECK_ID,String PRJ_TYPE,String PRJ_CODE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,List <TermrefinecheckModelChld> modelList);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public List <TermrefinecheckModelChld> queryJudgeModel(String CHANN_CHECK_PLAN_ID,String BUSS_SCOPE);
    
    /**
     * @param TERM_REFINE_CHECK_DTL_ID
     * @param TERM_REFINE_CHECK_ID
     * @param PRJ_TYPE
     * @param PRJ_CODE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     */
    public void updateChild(String TERM_REFINE_CHECK_DTL_ID,String TERM_REFINE_CHECK_ID,String PRJ_TYPE,String PRJ_CODE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK);
    
    
    public void txChildSave(String TERM_REFINE_CHECK_ID, List <TermrefinecheckModelChld> modelList);
    
    public void txUpdateChild(String TERM_REFINE_CHECK_ID);
}