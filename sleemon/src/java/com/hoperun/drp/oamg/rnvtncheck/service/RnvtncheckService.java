package com.hoperun.drp.oamg.rnvtncheck.service;


import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.decorationapp.model.DecorationappModel;
import com.hoperun.drp.oamg.rnvtncheck.model.RnvtncheckChildModel;
import com.hoperun.drp.oamg.rnvtncheck.model.RnvtncheckModel;
import com.hoperun.sys.model.UserBean;

public interface RnvtncheckService  extends IBaseService  {
	
	/**
	 * @param RNVTN_CHECK_ID
	 * @param obj
	 * @param userBean
	 * @param mxList
	 * @return
	 */
    public String txEdit(String RNVTN_CHECK_ID, RnvtncheckModel obj, UserBean userBean,List <RnvtncheckChildModel>  mxList,String chk);

    /**
     * @param obj
     * @param userBean
     * @param RNVTN_CHECK_ID
     */
    public void txEditReform(RnvtncheckModel obj,UserBean userBean,String RNVTN_CHECK_ID);
    
    /**
     * @param RNVTN_CHECK_ID
     * @param obj
     * @param userBean
     * @param STATE
     */
    public void updateState(String RNVTN_CHECK_ID, RnvtncheckModel obj, UserBean userBean,String STATE);
    
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
    public IListPage pageQueryT(Map <String, String> params, int pageNo);

    /**
     * @param RNVTN_CHECK_ID
     * @return
     */
    public Map <String, String> loadByConfId(String RNVTN_CHECK_ID);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String RNVTN_CHECK_ID, UserBean userBean);
    
    /**
     * 查询验收明细中是否存在重复记录
     * @param type
     * @param name
     * @return
     */
    public List<RnvtncheckChildModel> queryTypeAndName(String type,String name,String RNVTN_CHECK_ID);
    
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<RnvtncheckChildModel> childQuery(String RNVTN_CHECK_ID);
    
    /**
     * @param localType
     * @return
     */
    public List<DecorationappModel> loadByDictionary(String localType);
    
    /**
     * 根据RNVTN_CHECK_ID查询
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckChildModel> queryTtl(String RNVTN_CHECK_ID);
    
    /**
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckModel> childQueryT(String RNVTN_CHECK_ID);
    /**
     * 根据验收单ID查询相关信息
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckModel>  queryByCheckId(String RNVTN_CHECK_ID);
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<RnvtncheckChildModel> loadChilds(String RNVTN_CHECK_DTL_IDs);
    
    /**
     * @param RNVTN_CHECK_DTL_IDs
     * @return
     */
    public List<RnvtncheckChildModel> loadChilds1(String RNVTN_CHECK_DTL_IDs);
    
    /**
     * @param RNVTN_CHECK_ID
     * @return
     */
    public List<RnvtncheckChildModel> queryReformTtl(String RNVTN_CHECK_ID);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String RNVTN_CHECK_DTL_IDS, String RNVTN_CHECK_ID,UserBean userBean);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param bmgzMxIds the bmgz mx ids
     * @param bmgzId the bmgz id
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild1(String RNVTN_CHECK_DTL_IDS,UserBean userBean);
    
    /**
     * @param RNVTN_CHECK_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     * @param IS_REFORM_FLAG
     * @return
     */
    public String insertChild(String RNVTN_CHECK_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,String IS_REFORM_FLAG);
    
    /**
     * @param RNVTN_CHECK_DTL_ID
     * @param RNVTN_CHECK_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     * @param IS_REFORM_FLAG
     * @return
     */
    public String updateChild(String RNVTN_CHECK_DTL_ID,String RNVTN_CHECK_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,String IS_REFORM_FLAG);
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChildT(String RNVTN_CHECK_DTL_IDs, UserBean userBean);
    
    /**
     * @param RNVTN_PROP
     * @return
     */
    public  String  queryName(String RNVTN_PROP);
}
