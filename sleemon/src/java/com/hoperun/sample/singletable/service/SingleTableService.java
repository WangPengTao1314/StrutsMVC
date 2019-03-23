/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXService.java
 */
package com.hoperun.sample.singletable.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.JGXXModel;
import com.hoperun.sys.model.JGXXTree;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface SingleTableService.
 * 
 * @module 系统管理
 * @func 机构信息
 * @version 1.1
 * @author 蔡瑾钊
 */

public interface SingleTableService extends IBaseService {

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
     * @param jgxxId the jgxx id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String jgxxId, UserBean userBean);


    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);


    /**
     * 加载.
     * 
     * @param SJJG the sJJG
     * 
     * @return true, if load jgzt
     */
    public boolean loadJGZT(String SJJG);


    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param jgxxId the jgxx id
     * @param jgxxModel the jgxx model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String jgxxId, JGXXModel jgxxModel, UserBean userBean);


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
    public List <JGXXTree> getTree() throws Exception;


    /**
     * 判断是否存在重复.
     * 
     * @param params the params
     * 
     * @return true, if query for int
     */
    public boolean queryForInt(Map <String, String> params);


    /**
     * 判断是否存在下级机构.
     * 
     * @param params the params
     * 
     * @return true, if query sj for int
     */
    public boolean querySjForInt(Map <String, String> params);


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
