/**
 * 项目名称：平台
 * 系统名：权限管理
 * 文件名：GZZXXService.java
 */
package com.hoperun.sys.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.GZZCYBean;
import com.hoperun.sys.model.GZZXXBean;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface GZZXXService.
 * 
 * @module 系统管理
 * @func 工作组信息
 * @version 1.1
 * @author 吴亚林
 */
public interface GZZXXService extends IBaseService {

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
     * Tx update by id.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    @SuppressWarnings("unchecked")
    public boolean txUpdateById(Map params);


    /**
     * Tx delete.
     * 
     * @param GZZXXID the gZZXXID
     * @param userBean the user bean
     * 
     * @return true, if successful
     */
    @SuppressWarnings("unchecked")
    public boolean txDelete(String GZZXXID, UserBean userBean);


    /**
     * 加载.
     * 
     * @param GZZXXID the gZZXXID
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String GZZXXID);


    /**
     * 查询明细.
     * 
     * @param GZZXXID the gZZXXID
     * 
     * @return the list< gzzcy bean>
     */
    public List <GZZCYBean> childQuery(String GZZXXID);


    /**
     * 逻辑删除工作组成员.
     * 
     * @param GZZCYID the gZZCYID
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String GZZCYID, UserBean userBean);


    /**
     * 编辑.
     * 
     * @param GZZXXID the gZZXXID
     * @param bean the bean
     * @param userBean the user bean
     * @param list the list
     */
    public void txEdit(String GZZXXID, GZZXXBean bean, UserBean userBean, List <GZZCYBean> list);


    /**
     * 根据ID查询.
     * 
     * @param GZZCYID the gZZCYID
     * 
     * @return the list< gzzcy bean>
     */
    public List <GZZCYBean> loadChilds(String GZZCYID);


    /**
     * 子表编辑.
     * 
     * @param GZZXXID the gZZXXID
     * @param list the list
     * @param userBean the user bean
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String GZZXXID, List <GZZCYBean> list, UserBean userBean);


    /**
     * 判断工作组编号是否已经存在.
     * 
     * @param GZZXXBH the gZZXXBH
     * 
     * @return the gzzxx exits
     */
    public int getGzzxxExits(String GZZXXBH);


    /**
     * 判断明细是否重复.
     * 
     * @param params the params
     * 
     * @return the list< gzzcy bean>
     */
    @SuppressWarnings("unchecked")
    public List <GZZCYBean> checkYHBH(Map params);
}
