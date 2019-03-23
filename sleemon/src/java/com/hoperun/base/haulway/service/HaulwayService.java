/**
  *@module 系统管理
  *@fuc 单位维护接口
  *@version 1.0
  *@author 王栋斌
*/
package com.hoperun.base.haulway.service;

import java.util.Map;

import com.hoperun.base.haulway.model.HaulwayModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.sys.model.UserBean;


public interface HaulwayService {

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
     * @param HAULWAY_ID the HAULWAY id
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String HAULWAY_ID);
    
    /**
     * 编辑：新增/修改
     * Description :.
     * 
     * @param HAULWAY_ID the HAULWAY id
     * @param haulModel the haul model
     * @param userBean the user bean
     * 
     * @return the String
     */
    public Boolean txEdit(String HAULWAY_ID, HaulwayModel haulModel, UserBean userBean);

    /**
     * 单条记录删除
     * Description :.
     * 
     * @param HAULWAY_ID the HAULWAY id
     * 
     * @return true, if delete
     */
    public boolean txDelete(String HAULWAY_ID,UserBean userBean);

    /**
     * 状态更改
     * Description:
     * 
     * @param HAULWAY_ID
     * @param STATE
     * @param userBean 
     * 
     * @return boolean
     */
    public boolean updateState(String HAULWAY_ID,String STATE, UserBean userBean);
}
