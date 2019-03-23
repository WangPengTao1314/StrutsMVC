package com.hoperun.drp.distributer.distributerendreq.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.distributer.distributerendreq.model.DistributerEndReqModel;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   加盟商终止合作申请
 *@version 1.1
 *@author gu_hx
 */
public interface DistributerEndReqService extends IBaseService  {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param CHANN_END_REQ_ID
     * @return
     */
    public Map <String, String> load(String CHANN_END_REQ_ID);    
    
    /**
     * 编辑
     * @param obj
     * @param userBean
     */
	public void txEdit(DistributerEndReqModel obj,UserBean userBean);
	
    /**
     * @param CHANN_END_REQ_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String CHANN_END_REQ_ID, UserBean userBean);
    
    
    /**
     * @param RYXXID
     * @return
     */
    public  int  queryZW(String RYXXID,String GZZXXID);
    
    
    /**
     * @return
     */
    public List<String> queryGZZXXID();
    
}
