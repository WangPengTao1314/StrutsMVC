/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：RYXXServiceImpl.java
 */
package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.TreeModelLoader;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.RYXXModel;
import com.hoperun.sys.model.RYXXTree;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.RYXXService;

// TODO: Auto-generated Javadoc
/**
 * The Class RYXXServiceImpl.
 * 
 * @module 系统管理
 * @func 人员信息
 * @version 1.1
 * @author 吴亚林
 */
public class RYXXServiceImpl extends BaseService implements RYXXService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    @SuppressWarnings("unchecked")
    public IListPage pageQuery(Map params, int pageNo) {

        return this.pageQuery("RYXX.pageQuery", "RYXX.pageCount", params, pageNo);
    }


    /**
     * 新增.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    @SuppressWarnings("unchecked")
    public boolean txInsert(Map params) {

        insert("RYXX.insert", params);
        return true;
    }


    /**
     * 修改.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    @SuppressWarnings("unchecked")
    public boolean txUpdateById(Map params) {

        return update("RYXX.updateById", params);
    }
    public boolean txUpdateUserById(Map params) {

        return update("RYXX.updateUserById", params);
    }

    /**
     * 逻辑删除.
     * 
     * @param RYXXID the rYXXID
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String RYXXID, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("RYXXID", RYXXID);
        params.put("DATARECYCLEID", StringUtil.uuid32len());
        params.put("DELETOR", userBean.getYHM());
        insert("RYXX.insertRY", params);
        return update("RYXX.delete", RYXXID);
    }


    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String param) {

        Map params = new HashMap();
        params.put("RYXXID", param);
        return (Map) load("RYXX.loadById", params);
    }


    /**
     * 编辑.
     * 
     * @param RYXXID the rYXXID
     * @param model the model
     * @param userBean the user bean
     */
    @Override
    public void txEdit(String RYXXID, RYXXModel model, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>(16);
        params.put("RYBH", model.getRYBH());//人员编号
        params.put("XM", model.getXM());//姓名
        params.put("XB", model.getXB());//性别
        params.put("SFZH", model.getSFZH());//身份证号
        params.put("ZW", model.getZW());//职务
        params.put("GSDH", model.getGSDH());//公司电话
        params.put("SJ", model.getSJ());//手机
        params.put("DZYJ", model.getDZYJ());//电子邮件
        params.put("RYJB", model.getRYJB());//人员级别
        params.put("RYLB", model.getRYLB());//人员类别
        params.put("SBJS", model.getSBJS());//社保基数
        params.put("BMXXID", model.getBMXXID());//部门信息ID
        params.put("JGXXID", model.getJGXXID());//机构信息ID
        params.put("CWDZM", model.getCWDZM());//财务对照码
        if (StringUtils.isEmpty(RYXXID)) {
            params.put("RYZT", BusinessConsts.JC_STATE_DEFAULT);//状态
            params.put("RYXXID", model.getRYBH());
            params.put("IS_DRP_LEDGER", BusinessConsts.YJLBJ_FLAG_FALSE);
            params.put("CREATER", userBean.getXTYHID());//制单人ID
            params.put("CRENAME", userBean.getXM());//制单人名称 
            txInsert(params);
        } else {
            params.put("RYXXID", RYXXID);
            params.put("UPDATER", userBean.getXTYHID());//更新人id            
            txUpdateById(params);
            txUpdateUserById(params);
        }
    }


    /**
     * 判断编号是否已经存在.
     * 
     * @param RYXXID the rYXXID
     * 
     * @return the ryxx exits
     */
    public int getRyxxExits(String RYXXID) {

        return queryForInt("RYXX.getExits", RYXXID);
    }


    /**
     * 部门机构树.
     * 
     * @param ctx the ctx
     * @param theme the theme
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public List <RYXXTree> getTree(String ctx, String theme) throws Exception {

        Map <String, String> params = new HashMap <String, String>();
        params.put("ctx", "'" + ctx + "'");
        params.put("theme", "'" + theme + "'");
        List <RYXXTree> tree = this.findList("RYXX.queryTree", params);
        //return ResourceUtil.getTreeFromList(tree, RYXXTree.class);
        Map <String, String> mapFlds = new HashMap <String, String>();
        mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_ID, "id");
        mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_PID, "pid");
        mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_NAME, "name");
        mapFlds.put("def1", "flag");
        mapFlds.put("icon", "icon");
        return ResourceUtil.getZTreeFromList(tree, RYXXTree.class, mapFlds);

        //        return ResourceUtil.getZTreeFromList(tree, RYXXTree.class);
    }


    /**
     * 得到当前机构的当前最大编号.
     * 
     * @param jgxxid the jgxxid
     * 
     * @return the max bh
     */
    public String getMaxBh(String jgxxid) {

        return (String) load("RYXX.getMaxBh", jgxxid);
    }
    
    /* (non-Javadoc)
     * @see com.hoperun.sys.service.RYXXService#getCountBH(java.lang.String)
     */
    public int getCountBH(String bh){
    	return (Integer) load("RYXX.getCountBH",bh);
    }
    
    /**
     * 加载.
     * 
     * @param SJBM the sJBM
     * 
     * @return true, if load jgzt
     */
    @SuppressWarnings("unchecked")
    public boolean loadBMZT(String SJBM) {

        if ("停用".equals(load("BMXX.loadBMZT", SJBM))) {
            return false;
        } else {
            return true;
        }
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
}
