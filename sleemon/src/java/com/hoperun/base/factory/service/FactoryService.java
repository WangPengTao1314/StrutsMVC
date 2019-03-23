/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：SCJDService.java
 */
package com.hoperun.base.factory.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.factory.model.FactoryModel;
import com.hoperun.base.factory.model.FactoryProductModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

/**
 * The Interface BMXXService.
 * 
 * @module 系统管理
 * @func 生产基地维护
 * @version 1.0
 * @author 王志格
 */
public interface FactoryService extends IBaseService {
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
     * @param BMXXID the bMXXID
     * @param model the model
     * @param userBean the user bean
     */
    public void txEdit(String PROD_B_ID, FactoryModel model, UserBean userBean);
    
    
    /**
     * 删除.
     * 
     * @param BMXXID the bMXXID
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String FACTORY_ID, UserBean userBean);
    
    /**
     * 修改.
     * 
     * @param params the params
     * 
     * @return true, if update by id
     */
    @SuppressWarnings("unchecked")
	public boolean txUpdateById(Map params);
    
    /**
     * 判断生产工厂编号是否存在.
     * 
     * @param BMXXBH the bMXXBH
     * 
     * @return the bmxx exits
     */
    public int getScjdExits(String FACTORY_NO);
    
    /**
     * 生产货品类型->列表查询
     * 
     * @param FACTORY_ID the FACTORY_ID
     * @return the list<FactoryProductModel>
     */
    public List <FactoryProductModel> productQuery(String FACTORY_ID);
    
    /**
     * 生产货品类型->批量加载表信息
     * 
     * @param FACTORY_P_IDS the FACTORY_P_IDS
     * @return the list<FactoryProductModel>
     */
    public List <FactoryProductModel> loadProducts(String FACTORY_P_IDS);
    
    /**
     * 生产货品类型->删除
     * 
     * @param updateList the updateList
     */
    public void txProductDelete(List <Map <String, String>> updateList);
    
    /**
     * 生产货品类型->新增/修改
     * 
     * @param FACTORY_ID the FACTORY_ID
     * @param modelList the model list
     */
    public boolean txProductEdit(String FACTORY_ID, List <FactoryProductModel> modelList);
    
}
