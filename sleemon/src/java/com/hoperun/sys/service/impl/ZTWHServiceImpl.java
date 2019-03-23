/**

* 项目名称：平台

* 系统名：财务基础数据

* 文件名：ZTWHServiceImpl.java
*/
package com.hoperun.sys.service.impl;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.sys.model.ZTWHModel;
import com.hoperun.sys.model.ZTWHTree;
import com.hoperun.sys.service.ZTWHService;

// TODO: Auto-generated Javadoc
/**
 * The Class ZTWHServiceImpl.
 * 
 * @module 财务管理
 * @fuc 帐套信息维护
 * @version 1.1
 * @author 唐赟
 */
public class ZTWHServiceImpl extends BaseService implements ZTWHService {

    /**
     * 帐套维护列表信息.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("CWZT.pageQuery", "CWZT.pageCount", params, pageNo);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#pageQuery1(java.util.Map, int)
     */
    public IListPage pageQuery1(Map <String, String> params, int pageNo) {

        return this.pageQuery("CWZT.pageQuery1", "CWZT.pageCount1", params, pageNo);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#load(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Map <String, String> load(String ztxxID) {

        return (Map <String, String>) this.load("CWZT.getOneRecord", ztxxID);
    }
    
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#loadOne(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public Map <String, String> loadOne(String ztxxID) {

        return (Map <String, String>) this.load("CWZT.getOne", ztxxID);
    }


    /**
     * 更新.
     * 
     * @param params the params
     * 
     * @return true, if update by id
     */
    public boolean updateById(Map <String, String> params) {

        return update("CWZT.updateById", params);
    }


    /**
     * 插入.
     * 
     * @param params the params
     * 
     * @return true, if insert record
     */
    public boolean insertRecord(Map <String, String> params) {

        insert("CWZT.insertRecord", params);
        return true;
    }


    /**
     * 得到所有帐套编号.
     * 
     * @return the BH list
     */
    @SuppressWarnings("unchecked")
    public List <ZTWHModel> getBHList() {

        return findList("CWZT.getAllBH", "");
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#updateZTStatus(java.util.Map)
     */
    public void updateZTStatus(Map <String, String> params) {

        update("CWZT.updateZTStatus", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#deleteById(java.util.Map)
     */
    public void deleteById(Map <String, String> params) {

        update("CWZT.delete", params);
    }


    /**
     * 树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public List <ZTWHTree> getTree() throws Exception {

        List <ZTWHTree> menus = this.findList("CWZT.queryTree", "");
        return ResourceUtil.getZTreeFromList(menus, ZTWHTree.class);
    	
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#getCountRecord(java.lang.String)
     */
    public int getCountRecord(String ztxxid) {

        return queryForInt("CWZT.getCountRecord", ztxxid);
    }
    
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#querySjForInt(java.util.Map)
     */
    public boolean querySjForInt(Map<String,String> params){
    	if (0 == queryForInt("CWZT.querySjForInt", params)) {
            return true;
        } else {
            return false;
        }
    }
    
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.ZTWHService#querySjForInt(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public boolean loadZTZT(String SJZT) {

        if ("停用".equals(load("CWZT.loadZTZT", SJZT))) {
            return false;
        } else {
            return true;
        }
    }
    
}
