package com.hoperun.drp.oamg.plancheck.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.plancheck.model.ChanncheckplanModel;
import com.hoperun.drp.oamg.plancheck.model.ChanncheckplanModelChild;
import com.hoperun.drp.oamg.termrefinecheck.model.TermrefinecheckModelChld;
import com.hoperun.sys.model.UserBean;

public interface ChanncheckplanService extends IBaseService {

	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param TERM_REFINE_CHECK_ID the TERM_REFINE_CHECK_ID
     * @return the list
     */
    public List <ChanncheckplanModelChild> childQuery(String CHANN_CHECK_PLAN_ID);
    
	/**
	 * @param TERM_REFINE_CHECK_ID
	 * @return
	 */
	public Map<String,String> loadByConf(String CHANN_CHECK_PLAN_ID);
	
	/**
	 * @param TERM_REFINE_CHECK_ID
	 * @return
	 */
	public Map<String,String> loadByConfT(String CHANN_CHECK_PLAN_ID);
	
    /**
     * 查询主表单条记录详细信息
     * Description :.
     * @param bmgzid the bmgzid
     * @return the map< string, string>
     */
    public Map <String, String> load(String CHANN_CHECK_PLAN_ID);
    
    /**
     * 状态修改.
     * @param params the params
     */
    public void updateState(Map <String, String> params);
    
    //public String queryIdByNo(String CHANN_CHECK_PLAN_NO);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String CHANN_CHECK_PLAN_ID, UserBean userBean);
    
    /**
     * @param CHANN_RNVTN_ID
     * @return
     */
    public List <ChanncheckplanModelChild> queryJudgeModel(String CHANN_CHECK_PLAN_ID);
	 /**
	 * @主从保存编辑
	 * @Description :
	 * @param TERM_REFINE_CHECK_ID
	 * @param TermrefinecheckModel
	 * @param userBean.
	 * @return 
	 */
	public String txEdit(String CHANN_CHECK_PLAN_ID, ChanncheckplanModel obj,List<ChanncheckplanModelChild> mxList, UserBean userBean,String chkLen);
    
    public void txBatch4DeleteChild(String CHANN_CHECK_PLAN_DTL_IDs, UserBean userBean);
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param TERM_REFINE_CHECK_DTL_IDs the TERM_REFINE_CHECK_DTL_IDs
     * @return the list
     */
    public List <ChanncheckplanModelChild> loadChilds(Map <String, String> params) ;
    
    /***
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
    public String insertChild(String CHANN_CHECK_PLAN_ID,String PRJ_TYPE,String PRJ_CODE,String PRJ_NAME,
			String PRJ_SCORE,List <ChanncheckplanModelChild> modelList);
    
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
    public void updateChild(String CHANN_CHECK_PLAN_DTL_ID,String CHANN_CHECK_PLAN_ID,String PRJ_TYPE,String PRJ_CODE,String PRJ_NAME,
			String PRJ_SCORE);
    /**
     * @param code
     * @param name
     * @param TERM_REFINE_CHECK_ID
     * @return
     */
    public List<ChanncheckplanModelChild> queryTypeAndName(String code,String name,String CHANN_CHECK_PLAN_ID);
}
