/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXService.java
 */
package com.hoperun.base.provider.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.base.provider.model.ProviderModel;
import com.hoperun.sys.model.UserBean;

/**
 * The Interface NewSingleTableService.
 * 
 * @module 系统管理
 * @func 单表示例
 * @version 1.1
 * @author 朱晓文
 */

public interface ProviderService extends IBaseService {

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
     * 判断是否存在重复.
     * 
     * @param params the params
     * 
     * @return true, 无重复
     */
    public int queryForInt(String PRVD_NO);
    
    
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
    public String txEdit(String jgxxId, ProviderModel singleTableModel, UserBean userBean);


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
