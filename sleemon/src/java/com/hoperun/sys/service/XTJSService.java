/**

 * 项目名称：平台

 * 系统名：基础数据

 * 文件名：XTJSService.java

 */
package com.hoperun.sys.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;
import com.hoperun.sys.model.XTJSBean;
import com.hoperun.sys.model.XTYHJSBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface XTJSService.
 * 
 * @module 系统管理
 * @fuc 系统角色信息维护
 * @version 1.1
 * @author 唐赟
 */
public interface XTJSService extends IBaseService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);


    /**
     * Insert.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean insert(Map <String, String> params);


    /**
     * Insert child.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean insertChild(Map <String, String> params);


    /**
     * Update by id.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean updateById(Map <String, String> params);


    /**
     * Delete.
     * 
     * @param params the params
     * 
     * @return true, if successful
     */
    public boolean delete(Map <String, String> params);


    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);


    /**
     * Query.
     * 
     * @param params the params
     * 
     * @return the map< string, string>
     */
    public Map <String, String> query(Map <String, String> params);


    /**
     * Child query.
     * 
     * @param id the id
     * 
     * @return the list< xtyhjs bean>
     */
    public List <XTYHJSBean> childQuery(String id);


    /**
     * 得到所有角色编号.
     * 
     * @return the BH list
     */
    public List <XTJSBean> getBHList();


    /**
     * 得到角色用户编号.
     * 
     * @param XTYHID the xTYHID
     * 
     * @return the YHBH list
     */
    public List <XTYHJSBean> getYHBHList(String XTYHID);


    /**
     * Load childs.
     * 
     * @param xtjsids the xtjsids
     * 
     * @return the list< xtyhjs bean>
     */
    public List <XTYHJSBean> loadChilds(String xtjsids);


    /**
     * 主表编缉.
     * 
     * @param status the status
     * @param userBean the user bean
     * @param xtjs the xtjs
     * @param xtyhjs the xtyhjs
     * 
     * @return true, if tx edit
     */
    public boolean txEdit(String status, UserBean userBean, XTJSBean xtjs, List <XTYHJSBean> xtyhjs);


    /**
     * 子表编缉.
     * 
     * @param status the status
     * @param xtjs the xtjs
     * @param xtyhjs the xtyhjs
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String status, XTJSBean xtjs, List <XTYHJSBean> xtyhjs);


    /**
     * 子表的批量删除
     * Description :.
     * 
     * @param xtyhjsids the xtyhjsids
     * @param userBean the user bean
     */
    public void txBatch4DeleteChild(String xtyhjsids, UserBean userBean);


    /**
     * 主从表删除.
     * 
     * @param xtjsID the xtjs id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String xtjsID, UserBean userBean);


    /**
     * 状态更新.
     * 
     * @param params the params
     */
    public void updateJSStatus(Map <String, String> params);
}
