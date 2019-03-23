package com.hoperun.sys.service;

import java.util.HashMap;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class MenuService.
 * 
 * @module 系统模块
 * @fuc 系统模块
 * @version 1.1
 * @author 朱晓文
 */
public class MenuService extends BaseService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map params, int pageNo) {

        return this.pageQuery("MenuInfo.pageQuery", "MenuInfo.pageCount", params, pageNo);
    }


    /**
     * Insert.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean insert(Map params) {

        insert("MenuInfo.insert", params);
        return true;
    }


    /**
     * Update by id.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean updateById(Map params) {

        return update("MenuInfo.updateById", params);
    }


    /**
     * Delete.
     * 
     * @param paramsID the params id
     * @param userBean the user bean
     * 
     * @return true, if successful
     */
    public boolean delete(Map paramsID, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("menuId", (String) paramsID.get("menuId"));
        params.put("DATARECYCLEID", StringUtil.uuid32len());
        params.put("DELETOR", userBean.getYHM());
        insert("MenuInfo.insertdata", params);
        return delete("MenuInfo.delete", paramsID);
    }


    /**
     * Load.
     * 
     * @param params the params
     * 
     * @return the map
     */
    public Map load(Map params) {

        return (Map) load("MenuInfo.query", params);
    }
}
