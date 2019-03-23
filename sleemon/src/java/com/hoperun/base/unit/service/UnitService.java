/**
  *@module 系统管理
  *@fuc 单位维护接口
  *@version 1.0
  *@author 王栋斌
*/
package com.hoperun.base.unit.service;

import java.util.Map;

import com.hoperun.base.unit.model.UnitModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.sys.model.UserBean;


public interface UnitService {

	 /**
     * 主表分页查询
     * Description :.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);


    /**
     * 查询主表单条记录详细信息
     * Description :.
     * 
     * @param MEAS_UNIT_ID the MEAS_UNIT id
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String MEAS_UNIT_ID);
    
    /**
     * 编辑：新增/修改
     * Description :.
     * 
     * @param jgxxId the jgxx id
     * @param jgxxModel the jgxx model
     * @param userBean the user bean
     * 
     * @return the String
     */
    public String txEdit(String MEAS_UNIT_ID, UnitModel unitModel, UserBean userBean);

    /**
     * 单条记录删除
     * Description :.
     * 
     * @param MEAS_UNIT_ID the MEAS_UNIT id
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String MEAS_UNIT_ID,UserBean userBean);

    /**
     * 状态更改
     * Description:
     * 
     * @param MEAS_UNIT_ID
     * @param STATE
     * @param userBean 
     * 
     * @return boolean
     */
    public boolean updateState(String MEAS_UNIT_ID,String STATE, UserBean userBean);
}
