package com.hoperun.drp.oamg.termrefinecheck.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModel;
import com.hoperun.drp.oamg.termrefinecheck.model.ChanncheckplanModelChild;
import com.hoperun.sys.model.UserBean;

public interface ChanncheckplanService  extends IBaseService {

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

    public String queryIdByNo(String CHANN_CHECK_PLAN_NO);

}
