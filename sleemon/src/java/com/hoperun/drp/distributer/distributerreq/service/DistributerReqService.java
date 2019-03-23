package com.hoperun.drp.distributer.distributerreq.service;

import java.util.Map;

import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.distributer.distributerreq.model.DistributerReqModel;
import com.hoperun.sys.model.UserBean;

/**
 *@module 渠道管理-分销商管理
 *@function   分销渠道信息登记
 *@version 1.1
 *@author gu_hx
 */
public interface DistributerReqService extends IBaseService  {

	/**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * @param DISTRIBUTOR_REQ_ID
     * @return
     */
    public Map <String, String> load(String DISTRIBUTOR_REQ_ID);    
    
    /**
     * 编辑
     * @param obj
     * @param userBean
     */
	public void txEdit(DistributerReqModel obj,UserBean userBean);
	
    /**
     * @param DISTRIBUTOR_REQ_ID
     * @param userBean
     * @return
     */
    public boolean txDelete(String DISTRIBUTOR_REQ_ID, UserBean userBean);
    
    /***
     * @param CHANN_ID
     * @return
     */
    public ChannModel getChannInfo(String CHANN_ID);
    
    /***
     * @param userId
     * @return
     */
    public int getMkmCount(String userId);
    
    public  String  queryMaxNo();
    
}
