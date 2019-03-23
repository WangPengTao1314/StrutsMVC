/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：XZQHService.java
 */
package com.hoperun.base.zone.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.base.zone.model.ZoneModel;
import com.hoperun.base.zone.model.ZoneTree;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface XZQHService.
 * 
 * @module 系统管理
 * @func 行政区划
 * @version 1.1
 * @author 张涛
 */

public interface ZoneService extends IBaseService {

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
     * 删除记录
     * Description :.
     * 
     * @param Zone_Id the XZQH id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params);


    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);


    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param Zone_Id the XZQH id
     * @param XZQHModel the XZQH model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String Zone_Id, ZoneModel ZoneModel, UserBean userBean);
    
    /**
     * 更新记录
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params);


    /**
     * 显示树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <ZoneTree> getTree() throws Exception;


    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);
}
