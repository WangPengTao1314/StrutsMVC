/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：BMXXServiceImpl.java
 */
package com.hoperun.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.TreeModelLoader;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.BMXXModel;
import com.hoperun.sys.model.BMXXTree;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.service.BMXXService;

// TODO: Auto-generated Javadoc
/**
 * The Class BMXXServiceImpl.
 * 
 * @module 系统管理
 * @func 部门信息
 * @version 1.1
 * @author 吴亚林
 */
public class BMXXServiceImpl extends BaseService implements BMXXService {

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

        return this.pageQuery("BMXX.pageQuery", "BMXX.pageCount", params, pageNo);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.BMXXService#insert(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public boolean insert(Map params) {

        insert("BMXX.insert", params);
        return true;
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.BMXXService#updateById(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    public boolean updateById(Map params) {

        return update("BMXX.updateById", params);
    }


    /* (non-Javadoc)
     * @see com.hoperun.sys.service.BMXXService#txDelete(java.lang.String, com.hoperun.sys.model.UserBean)
     */
    @SuppressWarnings("unchecked")
    public boolean txDelete(String BMXXID, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("BMXXID", BMXXID);
        params.put("DATARECYCLEID", StringUtil.uuid32len());
        params.put("DELETOR", userBean.getYHM());
        insert("BMXX.insertBM", params);
        return delete("BMXX.delete", BMXXID);
    }


    /**
     * 软删除.
     * 
     * @param BMXXID the BMXX id
     * @param userBean the user bean
     * @return true, if tx delete
     */
    public void txNewDelete(String BMXXID, UserBean userBean) {
        Map <String, String> params = new HashMap <String, String>();
        params.put("BMXXID", BMXXID);
        params.put("DELFLAG", BusinessConsts.DEL_FLAG_DROP);
        update("BMXX.updateStateById", params);
        this.clearCaches(BMXXID);
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
        params.put("BMXXID", param);
        return (Map) load("BMXX.loadById", params);
    }


    /**
     * 新增.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("BMXX.insert", params);
        return true;
    }


    /**
     * 修改.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {

        return update("BMXX.updateById", params);

    }


    /**
     * 将下级部门和人员全部停用.
     * 
     * @param params the params
     * 
     * @return true, if tx update all by id
     */
    public boolean txUpdateAllById(Map <String, String> params) {

        //停用所有下级部门
        update("BMXX.updateAllById", params);
        //停用人员
        update("BMXX.updateRById", params);
        return true;
    }


    /**
     * 编辑.
     * 
     * @param BMXXID the bMXXID
     * @param model the model
     * @param userBean the user bean
     */
    @Override
    public void txEdit(String BMXXID, BMXXModel model, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();

        params.put("BMBH", model.getBMBH());//部门编号
        params.put("BMMC", model.getBMMC());//部门名称
        params.put("BMJC", model.getBMJC());//部门简称
        params.put("DH", model.getDH());//电话
        params.put("CZ", model.getCZ());//传真
        params.put("DZYJ", model.getDZYJ());//邮件
        params.put("SJBM", model.getSJBM());//上级部门编号
//        if("".equals(model.getSJBM()))
//        {
//        	params.put("SJBM", null);
//        }
        params.put("QTSM", model.getQTSM());//其他说明
        params.put("XXDZ", model.getXXDZ());//详细地址
        params.put("JGXXID", model.getJGXXID());//机构信息
        params.put("ZTXXID", model.getZTXXID());//帐套
        params.put("XH", model.getXH().toString());//序号
        params.put("YZBM", model.getYZBM());//邮政编码
        params.put("ZYDZ", model.getZYDZ());//主页地址

        if (StringUtils.isEmpty(BMXXID)) {
            params.put("BMZT", BusinessConsts.JC_STATE_DEFAULT);//部门状态
            params.put("BMXXID", model.getBMBH());
            txInsert(params);
        } else {
            params.put("BMXXID", BMXXID);
            txUpdateById(params);
        }
    }


    /**
     * 部门树.
     * 
     * @param ctx the ctx
     * @param theme the theme
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public List <BMXXTree> getTree(String ctx, String theme) throws Exception {

        Map <String, String> params = new HashMap <String, String>();
        params.put("ctx", "'" + ctx + "'");
        params.put("theme", "'" + theme + "'");
        List <BMXXTree> tree = this.findList("BMXX.queryTree", params);

        Map <String, String> mapFlds = new HashMap <String, String>();
        mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_ID, "id");
        mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_PID, "pid");
        mapFlds.put(TreeModelLoader.TREE_MODEL_FLD_NAME, "name");
        mapFlds.put("def1", "flag");
        mapFlds.put("icon", "icon");
        return ResourceUtil.getZTreeFromList(tree, BMXXTree.class, mapFlds);
    }


    /**
     * 判断部门编号是否存在.
     * 
     * @param BMXXBH the bMXXBH
     * 
     * @return the bmxx exits
     */
    public int getBmxxExits(String BMXXBH) {

        return queryForInt("BMXX.getExits", BMXXBH);
    }


    /**
     * 校验是否有未生效的上级部门.
     * 
     * @param params the params
     * 
     * @return the int
     */
    public int cksjbm(Map <String, String> params) {

        return queryForInt("BMXX.getWsxSJ", params);
    }


    /**
     * 校验是否有未停用的下级部门.
     * 
     * @param params the params
     * 
     * @return the int
     */
    public int ckxjbm(Map <String, String> params) {

        return queryForInt("BMXX.getWtyXJ", params);
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
