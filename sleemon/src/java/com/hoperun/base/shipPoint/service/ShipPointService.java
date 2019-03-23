/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：ShipPointService.java
 */
package com.hoperun.base.shipPoint.service;

import java.util.Map;

import com.hoperun.base.shipPoint.model.ShipPointModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

/**
 * The Interface ShipPointService.
 * 
 * @module 系统管理
 * @func 生产基地维护
 * @version 1.0
 * @author 王志格
 */
public interface ShipPointService extends IBaseService {
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
     * @param SHIP_POINT_ID the SHIP_POINT_ID
     * @param model the model
     * @param userBean the user bean
     */
    public String txEdit(String SHIP_POINT_ID, ShipPointModel model, UserBean userBean);
    
    
    /**
     * 删除.
     * 
     * @param SHIP_POINT_ID the SHIP_POINT_ID
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String SHIP_POINT_ID, UserBean userBean);
    
    /**
     * 停用/启用.
     * 
     * @param params the params
     * 
     * @return true, if update by id
     */
    public boolean txUpdateById(Map params);
    
    /**
     * 判断生产基地编号是否存在.
     * 
     * @param SHIP_POINT_NO the SHIP_POINT_NO
     * 
     * @return the bmxx exits
     */
    public int getShipExits(String SHIP_POINT_NO);
    
}
