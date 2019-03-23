package com.hoperun.base.area.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.area.model.AreaChrgModel;
import com.hoperun.base.area.model.AreaModel;
import com.hoperun.base.area.model.AreaTree;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

public interface AreaService extends IBaseService{
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
     * 显示树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <AreaTree> getTree() throws Exception;
    
    
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
    public boolean queryForInt(Map <String, String> params);


    /**
     * 状态
     * 启用->true
     * 
     * @param AREA_ID_P the 上级区域ID
     * 
     * @return 
     */
    public boolean loadAreaState(String AREA_ID_P);


    
    /**
     * 编辑.
     * 
     * @param model the 区域实体 model
     * @param userBean the user bean
     * @param modelList 子表
     * @return the string
     */
    public String txEdit(AreaModel model, UserBean userBean,List <AreaChrgModel> modelList);


    /**
     * 删除记录
     * Description :.
     * 
     * @param areaId the 区域 id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String areaId, UserBean userBean);
    
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
     * 子表记录编辑：新增/修改
     * Description :.
     * 
     * @param areaId 区域信息ID
     * @param  modelList 区域分管明细
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String areaId, List <AreaChrgModel> modelList);



}
