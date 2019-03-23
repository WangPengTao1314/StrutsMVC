package com.hoperun.drp.oamg.advreit.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.advreit.model.AdvreitModel;
import com.hoperun.sys.model.UserBean;

public interface AdvreitService  extends IBaseService  {

	/**
	 * @param ERP_ADV_REIT_ID
	 * @param obj
	 * @param userBean
	 */
	public void txEdit(String ERP_ADV_REIT_ID, AdvreitModel obj, UserBean userBean);
	
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
     * 删除数据
     * @param DATA_CONF 
     * @return true, if tx delete
     */
    public boolean txDelete(String ERP_ADV_REIT_ID, UserBean userBean);
    
    /**
     * @param ERP_ADV_REIT_ID
     * @return
     */
    public Map <String, String> loadByConfId(String ERP_ADV_REIT_ID);
    
    /**
     * @param ERP_ADV_REQ_ID
     * @return
     */
    public Map <String, String> loadPath(String ERP_ADV_REQ_ID);
    
    /**
     * @param ERP_ADV_REQ_ID
     * @return
     */
    public  String  queryReitAmount(String ERP_ADV_REQ_ID);
}
