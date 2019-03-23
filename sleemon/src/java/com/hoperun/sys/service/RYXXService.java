/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：RYXXService.java
 */
package com.hoperun.sys.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.RYXXModel;
import com.hoperun.sys.model.RYXXTree;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface RYXXService.
 * 
 * @module 系统管理
 * @func 人员信息
 * @version 1.1
 * @author 吴亚林
 */
public interface RYXXService extends IBaseService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    @SuppressWarnings("unchecked")
    public IListPage pageQuery(Map params, int pageNo);


    /**
     * 逻辑删除.
     * 
     * @param RYXXID the rYXXID
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String RYXXID, UserBean userBean);


    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String param);


    /**
     * 编辑.
     * 
     * @param RYXXID the rYXXID
     * @param model the model
     * @param userBean the user bean
     */
    public void txEdit(String RYXXID, RYXXModel model, UserBean userBean);


    /**
     * 判断编号是否已经存在.
     * 
     * @param RYXXID the rYXXID
     * 
     * @return the ryxx exits
     */
    public int getRyxxExits(String RYXXID);


    /**
     * 树.
     * 
     * @param ctx the ctx
     * @param theme the theme
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <RYXXTree> getTree(String ctx, String theme) throws Exception;


    /**
     * 修改.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params);


    /**
     * 得到当前机构的当前最大编号.
     * 
     * @param jgxxid the jgxxid
     * 
     * @return the max bh
     */
    public String getMaxBh(String jgxxid);
    
    /**
     * Gets the count bh.
     * 
     * @param BH the bH
     * 
     * @return the count bh
     */
    public int getCountBH(String BH);
    
    /**
     * 加载.
     * 
     * @param SJBM the sJBM
     * 
     * @return true, if load jgzt
     */
    public boolean loadBMZT(String SJBM);
    
    /**
     * 加载.
     * 
     * @param SJJG the sJJG
     * 
     * @return true, if load jgzt
     */
    public boolean loadJGZT(String SJJG);
}
