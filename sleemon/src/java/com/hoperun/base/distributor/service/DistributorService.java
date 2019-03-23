package com.hoperun.base.distributor.service;


/**  
 * @module 系统管理
 * @func 分销渠道信息
 * @version 1.0
 * @author gu_hongxiu
 */

import java.util.List;
import java.util.Map;

import com.hoperun.base.distributor.model.DistributorModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

public interface DistributorService extends IBaseService {
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    public Map<String,String> load(String param);
    
    /**
     * 查询渠道状态
     * @param CHANN_ID
     * @return
     */
    public boolean loadSTATE(String CHANN_ID);
    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param DISTRIBUTOR_ID the DISTRIBUTOR_ID
     * @param DistributorModel the Distributor model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String DISTRIBUTOR_ID, DistributorModel model, UserBean userBean);
    
	/**
     * 删除记录
     * Description :.
     * 
     * @param CHANN_ID the chann id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map<String,String> map);
    
    //启用
    public boolean txStartById(Map <String, String> params);
    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);
  //按渠道id查找渠道状态
    public String getSTATE(String CHANN_ID);
    
    /**
     * 查询导出数据
     */
    public  List <Map> qryExp(Map params);

}
