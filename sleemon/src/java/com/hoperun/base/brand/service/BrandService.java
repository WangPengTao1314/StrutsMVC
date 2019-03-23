/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：BrandService.java
 */
package com.hoperun.base.brand.service;

import java.util.Map;

import com.hoperun.base.brand.model.BrandModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface PPXXService.
 * 
 * @module 系统管理
 * @func 品牌信息
 * @version 1.1
 * @author 郭利伟
 */
public interface BrandService extends IBaseService {

    /**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@SuppressWarnings("unchecked")
    public IListPage pageQuery(Map params, int pageNo);


    /**
     * 删除数据
     * 
     * @param BRANDID the brandId
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String brandId, UserBean userBean);


    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    @SuppressWarnings("unchecked")
    public Map load(String param);


    /**
     * 编辑：新增/删除
     * 
     * @param brandId the brand id
     * @param brandModel the brand model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public void txEdit(String brandId, BrandModel model, UserBean userBean);

   
    /**
     * 更新记录
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params);

   
    /**
     * 修改状态为停用
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);

   
    /**
     * 修改状态为启用
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);
    
    
}
