package com.hoperun.drp.oamg.rnvtnreform.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformChildModel;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformModel;
import com.hoperun.sys.model.UserBean;

public interface RnvtnreformService extends IBaseService {

	/**
	 * @param RNVTN_REFORM_ID
	 * @param obj
	 * @param userBean
	 * @param mxList
	 * @return
	 */
	public String txEdit(String RNVTN_REFORM_ID, RnvtnreformModel obj,
			UserBean userBean,List<RnvtnreformChildModel>  mxList,String chkLen);
    
    /**
     * @param RRNVTN_REFORM_ID
     * @param obj
     * @param userBean
     * @param STATE
     */
    public void updateState(String RRNVTN_REFORM_ID, RnvtnreformModel obj, UserBean userBean,String STATE);
    
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
     * @param RNVTN_REFORM_ID
     * @return
     */
    public Map <String, String> loadByConfId(String RNVTN_REFORM_ID);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String RNVTN_REFORM_ID, UserBean userBean);
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<RnvtnreformChildModel> childQuery(String RNVTN_REFORM_ID);
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<RnvtnreformChildModel> loadChilds(String RNVTN_REFORM_DTL_IDs);
    
    /**
     * @param RNVTN_REFORM_DTL_IDs
     * @return
     */
    public List<RnvtnreformChildModel> loadChilds1(String RNVTN_REFORM_DTL_IDs);
    
    /**
     * 查询验收明细中是否存在重复记录
     * @param type
     * @param name
     * @return
     */
    public List<RnvtnreformChildModel> queryTypeAndName(String type,String name,String RNVTN_REFORM_ID);

    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String RNVTN_SUBST_STD_IDs, UserBean userBean);
    
    /**
     * @param RNVTN_REFORM_DTL_IDs
     * @param userBean
     */
    public void txBatch4DeleteChild1(String RNVTN_REFORM_DTL_IDs, UserBean userBean);
    
    /**
     * @param RNVTN_REFORM_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     */
    public String insertChild(String RNVTN_REFORM_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK,List <RnvtnreformChildModel> modelList);
    
    /**
     * @param RNVTN_REFORM_DTL_ID
     * @param RNVTN_REFORM_ID
     * @param PRJ_TYPE
     * @param PRJ_NAME
     * @param PRJ_SCORE
     * @param CHECK_SCORE
     * @param CHECK_REMARK
     */
    public void updateChild(String RNVTN_REFORM_DTL_ID,String RNVTN_REFORM_ID, String PRJ_TYPE,String PRJ_NAME,
			String PRJ_SCORE, String CHECK_SCORE,String CHECK_REMARK);
    
    /**
     * @param RNVTN_REFORM_ID
     * @return
     */
    public  List<RnvtnreformChildModel>  queryDtlByReformId(String RNVTN_REFORM_ID);
    
    /**
     * @param RNVTN_REFORM_DTL_ID
     */
    public void txDeleteChild(String RNVTN_REFORM_DTL_ID);
}
