/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXServiceImpl.java
 */
package com.hoperun.sample.newsingletable.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sample.newsingletable.model.NewSingleTableModel;
import com.hoperun.sample.newsingletable.service.NewSingleTableService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class NewSingleTableServiceImpl.
 * 
 * @module 系统管理
 * @func 单表示例
 * @version 1.1
 * @author 朱晓文
 */
public class NewSingleTableServiceImpl extends BaseService implements NewSingleTableService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("newsingtable.pageQuery", "newsingtable.pageCount", params, pageNo);
    }


    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("newsingtable.insert", params);
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

        return update("newsingtable.updateById", params);
    }


    /**
     * 软删除.
     * 
     * @param jgxxId the jgxx id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params) {
        //insert("newsingtable.insertDelDate", params);
        return delete("newsingtable.delete", params);
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

        return (Map) load("newsingtable.loadById", jgxxId);
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
    public String txEdit(String jgxxId, NewSingleTableModel singleTableModel, UserBean userBean) {
        Map <String, String> params = new HashMap <String, String>();
        params.put("DZXXID", singleTableModel.getDZXXID());
        params.put("ZTXXID", singleTableModel.getZTXXID());
        params.put("JGBH", singleTableModel.getJGBH());
        params.put("JGMC", singleTableModel.getJGMC());
        params.put("JGJC", singleTableModel.getJGJC());
        params.put("FRDB", singleTableModel.getFRDB());
        params.put("DH", singleTableModel.getDH());
        params.put("CZ", singleTableModel.getCZ());
        params.put("DZYJ", singleTableModel.getDZYJ());
        params.put("ZYDZ", singleTableModel.getZYDZ());
        params.put("SJJG", singleTableModel.getSJJG());
        params.put("QTSM", singleTableModel.getQTSM());
        params.put("XXDZ", singleTableModel.getXXDZ());
        params.put("YZBM", singleTableModel.getYZBM());
        params.put("JGYWMC", singleTableModel.getJGYWMC());
        params.put("FGCWRYID", singleTableModel.getFGCWRYID());
        params.put("FGCWRYXM", singleTableModel.getFGCWRYXM());
        params.put("JGYWJC", singleTableModel.getJGYWJC());
        params.put("JGYWXXDZ", singleTableModel.getJGYWXXDZ());
        params.put("XH", String.valueOf(singleTableModel.getXH()));
        params.put("UPDATER", userBean.getXTYHID());
        params.put("UPDTIME", DateUtil.now());
        if (StringUtils.isEmpty(jgxxId)) {
            jgxxId = StringUtil.uuid32len();
            params.put("JGXXID", jgxxId);
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
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {

        return update("newsingtable.updStusById", params);
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
        return update("newsingtable.updStusById", params);
    }
}
