/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXServiceImpl.java
 */
package com.hoperun.sample.singletable.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sample.singletable.service.SingleTableService;
import com.hoperun.sys.model.JGXXModel;
import com.hoperun.sys.model.JGXXTree;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class SingleTableServiceImpl.
 * 
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */
public class SingleTableServiceImpl extends BaseService implements SingleTableService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("JGXX.pageQuery", "JGXX.pageCount", params, pageNo);
    }


    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("JGXX.insert", params);
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

        return update("JGXX.updateById", params);
    }


    /**
     * 软删除.
     * 
     * @param jgxxId the jgxx id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String jgxxId, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("JGXXID", jgxxId);
        params.put("DATARECYCLEID", StringUtil.uuid32len());

        params.put("DELETOR", userBean.getYHM());

        insert("JGXX.insertDelDate", params);

        return delete("JGXX.delete", jgxxId);
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

        //update("JGXX.stopRyById", params);
        //update("JGXX.stopBmById", params);
        return update("JGXX.stopById", params);
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

        //update("JGXX.stopRyById", params);
        //update("JGXX.stopBmById", params);
        return update("JGXX.startById", params);
    }


    /**
     * 加载.
     * 
     * @param jgxxId the jgxx id
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String jgxxId) {

        return (Map) load("JGXX.loadById", jgxxId);
    }


    /**
     * 加载.
     * 
     * @param SJJG the sJJG
     * 
     * @return true, if load jgzt
     */
    @SuppressWarnings("unchecked")
    public boolean loadJGZT(String SJJG) {

        if ("停用".equals(load("JGXX.loadJGZT", SJJG))) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * 判断是否存在重复机构.
     * 
     * @param params the params
     * 
     * @return true, if query for int
     */
    public boolean queryForInt(Map <String, String> params) {

        if (0 == queryForInt("JGXX.queryBhForInt", params)) {
            return true;
        } else
            return false;
    }


    /**
     * 判断是否存在下级机构.
     * 
     * @param params the params
     * 
     * @return true, if query sj for int
     */
    public boolean querySjForInt(Map <String, String> params) {

        if (1 == queryForInt("JGXX.querySjForInt", params)) {
            return true;
        } else {
            return false;
        }
    }


    /**
     * 编辑.
     * 
     * @param jgxxId the jgxx id
     * @param jgxxModel the jgxx model
     * @param userBean the user bean
     * 
     * @return the string
     */
    @Override
    public String txEdit(String jgxxId, JGXXModel jgxxModel, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("DZXXID", jgxxModel.getDZXXID());
        params.put("ZTXXID", jgxxModel.getZTXXID());
        params.put("JGBH", jgxxModel.getJGBH());
        params.put("JGMC", jgxxModel.getJGMC());
        params.put("JGJC", jgxxModel.getJGJC());
        params.put("FRDB", jgxxModel.getFRDB());
        params.put("DH", jgxxModel.getDH());
        params.put("CZ", jgxxModel.getCZ());
        params.put("DZYJ", jgxxModel.getDZYJ());
        params.put("ZYDZ", jgxxModel.getZYDZ());
        params.put("SJJG", jgxxModel.getSJJG());
        params.put("QTSM", jgxxModel.getQTSM());
        params.put("XXDZ", jgxxModel.getXXDZ());
        params.put("YZBM", jgxxModel.getYZBM());
        params.put("JGYWMC", jgxxModel.getJGYWMC());
        params.put("FGCWRYID", jgxxModel.getFGCWRYID());
        params.put("FGCWRYXM", jgxxModel.getFGCWRYXM());
        params.put("JGYWJC", jgxxModel.getJGYWJC());
        params.put("JGYWXXDZ", jgxxModel.getJGYWXXDZ());
        params.put("XH", String.valueOf(jgxxModel.getXH()));
        params.put("UPDATER", userBean.getXTYHID());
        params.put("UPDTIME", DateUtil.now());

        if (StringUtils.isEmpty(jgxxId)) {
            //jgxxId = StringUtil.uuid32len();
            params.put("JGXXID", jgxxModel.getJGBH());
            params.put("JGZT", "制定");
            params.put("CREATER", userBean.getXTYHID());
            params.put("CRENAME", userBean.getXM());
            params.put("NFYF", DateUtil.nfyf());
            txInsert(params);
        } else {
            params.put("JGXXID", jgxxId);
            txUpdateById(params);
            clearCaches(jgxxId);
        }
        return jgxxId;
    }


    /**
     * 树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <JGXXTree> getTree() throws Exception {

        List <JGXXTree> menus = this.findList("JGXX.queryTree", "");
        return ResourceUtil.getZTreeFromList(menus, JGXXTree.class);
    }
}
