package com.hoperun.drp.oamg.decorationallo.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.decorationallo.model.DecorationalloModel;
import com.hoperun.drp.oamg.decorationallo.model.DrprnvtnsubststddtlModel;
import com.hoperun.sys.model.UserBean;

public interface DecorationalloService extends IBaseService {

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
     * @param RNVTN_SUBST_STD_ID
     * @param obj
     * @param userBean
     * @param mxList
     * @return
     */
    public String txEdit(String RNVTN_SUBST_STD_ID, DecorationalloModel obj, UserBean userBean,List <DrprnvtnsubststddtlModel>  mxList);
    
    /**
     * @param RNVTN_SUBST_STD_ID
     * @return
     */
    public Map <String, String> loadByConfId(String RNVTN_SUBST_STD_ID);
    
    /**
     * @param RNVTN_SUBST_STD_ID
     * @return
     */
    public Map <String, String> loadByConfIdT(String RNVTN_SUBST_STD_ID);
    
    /**
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String RNVTN_SUBST_STD_ID, UserBean userBean);
    
    /**
     * 判断同品牌是否只有一条启用状态
     * @param BRAND
     * @return
     */
    public int countFrom(String BRAND,String LEDGER_ID);
    
    /**
     * 控制明细同一商场位置类型是否只有一条记录
     * @param LOCAL_TYPE
     * @return
     */
    public int countLocalType(String LOCAL_TYPE,String RNVTN_SUBST_STD_ID);
    
    /**
     * 查询主表单条记录详细信息
     * Description :.
     * @param bmgzid the bmgzid
     * @return the map< string, string>
     */
    public Map <String, String> load(String RNVTN_SUBST_STD_ID);
    
    /**
     * 状态修改.
     * @param params the params
     */
    public void updateState(Map <String, String> params);
    
    /**
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<DrprnvtnsubststddtlModel> childQuery(String RNVTN_SUBST_STD_ID);
    
    /**
     * @param RNVTN_SUBST_STD_ID
     * @return
     */
    public List<DrprnvtnsubststddtlModel> childQueryT(String RNVTN_SUBST_STD_ID);
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List<DrprnvtnsubststddtlModel> loadChilds(String RNVTN_SUBST_STD_IDs);
    
    /**
     * @param RNVTN_SUBST_STD_IDs
     * @return
     */
    public List<DrprnvtnsubststddtlModel> loadByIds(String RNVTN_SUBST_STD_IDs);
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * @param sjzdMxIds the sjzd mx ids
     * @return the list< sjzd mx model>
     */
    public List loadChilds1(String RNVTN_SUBST_STD_IDs);
    
    /**
     * @param RNVTN_SUBST_STD_ID
     * @param TYPE
     * @param AMOUNT
     * @param REMARK
     * @return
     */
    public String insertChild(String RNVTN_SUBST_STD_ID,String TYPE,
			String AMOUNT, String REMARK,List <DrprnvtnsubststddtlModel> mxList);
    
    /**
     * @param RNVTN_SUBST_STD_DTL_ID
     * @param RNVTN_SUBST_STD_ID
     * @param TYPE
     * @param AMOUNT
     * @param REMARK
     * @return
     */
    public String updateChild(String RNVTN_SUBST_STD_DTL_ID,String RNVTN_SUBST_STD_ID,String TYPE,
			String AMOUNT, String REMARK,List <DrprnvtnsubststddtlModel> mxList);
    
    
    /**
     * 子表的批量删除
     * Description :.
     * @param sjzdmxids the sjzdmxids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String RNVTN_SUBST_STD_IDs, UserBean userBean);
    
    /**
     * @param RNVTN_SUBST_STD_IDs
     * @param userBean
     */
    public void txBatch4DeleteChild1(String RNVTN_SUBST_STD_IDs, UserBean userBean);
 
    /**
     * 查询数据字典信息
     * @param DATA_DTL_ID
     * @return
     */
    public String queryPro(String DATA_DTL_ID);
    
    /**
     * @param DATA_DTL_NAME
     * @return
     */
    public String queryProT(String DATA_DTL_NAME);
}
