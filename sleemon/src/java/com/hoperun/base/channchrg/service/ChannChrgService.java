package com.hoperun.base.channchrg.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;

public interface ChannChrgService extends IBaseService{
	
	  /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    
    public   List<Map<String,String>>  childQuery(Map<String,String> params);
    
    
    public void txEdit(String XTYHID,String CHANN_IDS,String deleteIds);
    
    /**
     * 删除
     * @param XTYHID 系统用户ID
     * @param CHANN_IDS 渠道IDS
     */
    public void txDelete(String XTYHID,String CHANN_IDS);
    
    /**
     * 查询用户信息
     * @param params
     * @return
     */
    public List<Map<String,String>>  childYhxxQuery(Map<String,String> params);
    /**
     * 编辑 按渠道维度
     * @param XTYHIDS
     * @param CHANN_ID
     * @param deleteIds
     */
    public void txEditRyxx(String XTYHIDS,String CHANN_ID,String deleteIds);
    /**
     * 以渠道维度 取消分管
     * @param CHANN_ID
     * @param XTYHIDS
     */
    public void txDeleteYhxx(String CHANN_ID,String XTYHIDS);

}
