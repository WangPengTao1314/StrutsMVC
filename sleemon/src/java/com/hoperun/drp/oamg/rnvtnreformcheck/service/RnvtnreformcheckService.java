package com.hoperun.drp.oamg.rnvtnreformcheck.service;

import java.util.List;
import java.util.Map;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.rnvtnreform.model.RnvtnreformModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.model.RnvtnreformcheckChildModel;
import com.hoperun.drp.oamg.rnvtnreformcheck.model.RnvtnreformcheckModel;
import com.hoperun.sys.model.UserBean;

public interface RnvtnreformcheckService  extends IBaseService {

    /**
     * @param RNVTN_REFORM_ID
     * @param obj
     * @param userBean
     */
	public void txEdit(String RNVTN_REFORM_ID, RnvtnreformcheckModel obj,
			UserBean userBean);
    
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
     * 根据主表Id查询子表记录
     * Description :.
     * @param sjzdID the sjzd id
     * @return the list< sjzd mx model>
     */
    public List<RnvtnreformcheckChildModel> childQuery(String RNVTN_REFORM_ID);
    
    /**
     * @param RNVTN_REFORM_ID
     * @return
     */
    public Map <String, String> loadByConfId(String RNVTN_REFORM_ID);
    
    /**
     * @param RNVTN_REFORM_ID
     * @return
     */
    public Map <String, String> loadByConfIdT(String RNVTN_REFORM_ID);
    
    /**
     * @param RNVTN_REFORM_ID
     * @param checkId
     * @return
     */
    public Map <String, String> loadByConfIdTt(String RNVTN_REFORM_ID,String checkId);
    
    /**
     * @param RRNVTN_REFORM_ID
     * @param obj
     * @param userBean
     * @param STATE
     */
    public void updateState(String RRNVTN_REFORM_ID, RnvtnreformModel obj, UserBean userBean,String STATE);
    
    /**
     * @param RNVTN_REFORM_ID
     * @return
     */
    public  String  queryCheckByReform(String RNVTN_REFORM_ID);
    
    /**
     * @param RNVTN_CHECK_NO
     * @return
     */
    public  String  queryCheckById(String RNVTN_CHECK_NO);
}
