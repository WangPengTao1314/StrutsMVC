/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ZoneServiceImpl.java
 */
package com.hoperun.base.zone.service.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.hoperun.base.zone.model.ZoneModel;
import com.hoperun.base.zone.model.ZoneTree;
import com.hoperun.base.zone.service.ZoneService;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;


// TODO: Auto-generated Javadoc
/**
 * The Class ZoneServiceImpl.
 * 
 * @module 系统管理
 * @func 行政区划
 * @version 1.1
 * @author 张涛
 */
public class ZoneServiceImpl extends BaseService implements ZoneService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("ZONE.pageQuery", "ZONE.pageCount", params, pageNo);
    }


    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("ZONE.insert", params);
        return true;
    }


    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {

        return update("ZONE.updateById", params);
    }


    /**
     * 删除.
     * 
     * @param Zone_Id the Zone id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params) {

    	return delete("ZONE.delete", params);
    }


    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {
        return update("ZONE.stopById", params);
    }


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params) {
        return update("ZONE.startById", params);
    }


    /**
     * 加载.
     * 
     * @param Zone_Id the Zone id
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String Zone_Id) {

        return (Map) load("ZONE.loadById", Zone_Id);
    }    


    /**
     * 编辑.
     * 
     * @param Zone_Id the Zone id
     * @param XZQHModel the XZQH model
     * @param userBean the user bean
     * 
     * @return the string
     */
    @Override
    public String txEdit(String Zone_Id, ZoneModel ZoneModel, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        
        //params.put("ZONE_ID", XZQHModel.getZONE_ID());
        params.put("NATION", ZoneModel.getNATION());
        params.put("PROV", ZoneModel.getPROV());
        params.put("CITY", ZoneModel.getCITY());
        params.put("COUNTY", ZoneModel.getCOUNTY());       
        params.put("UPDATOR", userBean.getXTYHID());
        params.put("UPD_NAME", userBean.getXM());
        //params.put("UPD_TIME", DateUtil.now());
        
        if (com.hoperun.commons.util.StringUtil.isEmpty(Zone_Id)) {
        	Zone_Id = StringUtil.uuid32len();
        	
            params.put("ZONE_ID", Zone_Id);
            params.put("STATE", "制定");
            params.put("CREATOR", userBean.getXTYHID());
            params.put("CRE_NAME", userBean.getXM());
            //params.put("CRE_TIME", DateUtil.now());
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            //params.put("UPD_TIME", DateUtil.now());
            params.put("DEPT_ID", userBean.getBMBH());            
            params.put("DEPT_NAME", userBean.getBMMC());
            params.put("ORG_ID", userBean.getJGBH());
            params.put("ORG_NAME", userBean.getJGMC());
            txInsert(params);
        } else {
            params.put("ZONE_ID", Zone_Id);
            txUpdateById(params);
            clearCaches(Zone_Id);
        }
        
        return Zone_Id;
    }


    /**
     * 树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
	public List <ZoneTree> getTree() throws Exception {

        List<ZoneTree> menus = this.findList("ZONE.queryTree", "");
        return ResourceUtil.getZTreeFromList(menus, ZoneTree.class);
    }
}
