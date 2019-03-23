/**
 * 项目名称：喜临门平台
 * 系统名：基础数据
 * 文件名：LogicprvdService.java
 */
package com.hoperun.base.logicprvd.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.logicprvd.model.LogicprvdModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

/**
 * The Interface LogicprvdService.
 * 
 * @module 系统管理
 * @func 物流供应商管理
 * @version 1.0
 * @author 王栋斌
 */

public interface LogicprvdService extends IBaseService {

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
     * 编辑：新增/删除
     * Description :.
     * 
     * @param prvdId the prvd id
     * @param logicprvdModel the logicprvd model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public Boolean txEdit(String prvdId, LogicprvdModel logicprvdModel, UserBean userBean);


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
    
    /**
     * 车辆信息一览
     * @param PRVD_ID
     * @return List<LogicprvdModel> 
     */
    public List<LogicprvdModel> truckList(String PRVD_ID);
    
    /**
     * 车辆信息记录编辑页面跳转
     * @param TRUCK_ID
     * @return boolean
     */
    public List<LogicprvdModel> toEditTruck(String TRUCK_ID);
    
    /**
     * 车辆信息记录删除
     * @param TRUCK_ID
     * @return boolean
     */
    public boolean truckDelete(String TRUCK_ID);
    
    /**
     * 车辆信息状态更改
     * @param params
     * @return boolean
     */
    public boolean changeTruckState(Map<String, String> params);
    
    /**
     * 车辆信息修改/新增
     * @param logicprvdModel
     * @return void
     */
    public void truckSave(List<LogicprvdModel> logicprvdModel, String PRVD_ID);
    
    /**
     * 合并路线一览
     * @param PRVD_ID
     * @return List<LogicprvdModel> 
     */
    public List<LogicprvdModel> waymergeList(String PRVD_ID);
    
    /**
     * 合并路线编辑页面跳转
     * @param TRUCK_ID
     * @return boolean
     */
    public List<LogicprvdModel> toEditWaymerge(String WAY_MERGE_ID);
    
    /**
     * 合并路线记录删除
     * @param TRUCK_ID
     * @return boolean
     */
    public boolean waymergeDelete(String WAY_MERGE_ID);
    
    /**
     * 合并路线状态更改
     * @param params
     * @return boolean
     */
    public boolean changeWaymergeState(Map<String, String> params);
    
    /**
     * 合并路线修改/新增
     * @param logicprvdModel
     * @return void
     */
    public Boolean waymergeSave(List<LogicprvdModel> logicprvdModel, String PRVD_ID);
    
    /**
     * 合并路线明细页面跳转
     * @param TRUCK_ID
     * @return boolean
     */
    public List<LogicprvdModel> toEditWaymergedtl(String WAY_MERGE_ID);
    
    /**
     * 合并路线明细记录删除
     * @param TRUCK_ID
     * @return boolean
     */
    public boolean waymergedtlDelete(String WAY_MERGE_DTL_ID);
    
    /**
     * 合并路线明细修改/新增
     * @param logicprvdModel
     * @return void
     */
    public void waymergedtlSave(List<LogicprvdModel> logicprvdModel, String WAY_MERGE_ID);
}
