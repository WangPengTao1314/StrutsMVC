/**
 * 项目名称：喜临门
 * 系统名：基础数据
 * 文件名：ShipPointServiceImpl.java
 */
package com.hoperun.base.shipPoint.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.shipPoint.model.ShipPointModel;
import com.hoperun.base.shipPoint.service.ShipPointService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class ShipPointServiceImpl.
 * 
 * @module 系统管理
 * @func 生产基地维护
 * @version 1.0
 * @author 王志格
 */
public class ShipPointServiceImpl extends BaseService implements ShipPointService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@SuppressWarnings("unchecked")
	@Override
	public IListPage pageQuery(Map params, int pageNo) {

		return this.pageQuery("SHIPPOINT.pageQuery", "SHIPPOINT.pageCount", params, pageNo);
	}

	/**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String param) {

        Map params = new HashMap();
        params.put("SHIP_POINT_ID", param);
        return (Map) load("SHIPPOINT.loadById", params);
    }
    
    /**
     * 新增.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("SHIPPOINT.insert", params);
        return true;
    }


    
    /**
     * 编辑.
     * 
     * @param BMXXID the bMXXID
     * @param model the model
     * @param userBean the user bean
     */
    @Override
    public String txEdit(String SHIP_POINT_ID, ShipPointModel model, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();

        params.put("SHIP_POINT_NO", model.getSHIP_POINT_NO());//生产基地编号
        params.put("SHIP_POINT_NAME", model.getSHIP_POINT_NAME());//生产基地名称   
        params.put("ADDRESS", model.getADDRESS());//详细地址
        params.put("STATE", "制定");//状态

        params.put("UPDATOR", userBean.getXTYHID());//更新人ID
        params.put("UPD_NAME", userBean.getXM());//更新人名称
  
        String shipPointId = "";
        
        if (StringUtils.isEmpty(SHIP_POINT_ID)) {
        	
            params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构ID
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
            params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
            params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
            
            shipPointId = StringUtil.uuid32len();
            params.put("SHIP_POINT_ID", shipPointId); //生产基地ID
            txInsert(params);
            
        } else {
            params.put("SHIP_POINT_ID", SHIP_POINT_ID);//生产基地ID
            shipPointId = SHIP_POINT_ID;
            txUpdateById(params);
        }
        
        return shipPointId;
    }
    /**
     * 删除(伪删除).
     * 
     * @param SHIP_POINT_ID the SHIP_POINT_ID
     * @param userBean the user bean
     */
    @Override
    public boolean txDelete(String SHIP_POINT_ID, UserBean userBean) {

        Map <String, String> params = new HashMap <String, String>();
        params.put("SHIP_POINT_ID", SHIP_POINT_ID);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
        params.put("UPDATOR", userBean.getXTYHID());//更新人ID
        params.put("UPD_NAME", userBean.getXM());//更新人名称
             
        return txUpdateById(params);
    }

    /**
     * 修改
     */
	@SuppressWarnings("unchecked")
	@Override
	public boolean txUpdateById(Map params) {

		return update("SHIPPOINT.updateById", params);
	}

	 /**
     * 判断生产基地编号是否存在.
     * 
     * @param SHIP_POINT_ID the SHIP_POINT_ID
     * 
     * @return the SHIP_POINT_ID exits
     */
	
    @SuppressWarnings("unchecked")
	public int getShipExits(String SHIP_POINT_NO) {

    	Map params = new HashMap();
    	params.put("SHIP_POINT_NO", SHIP_POINT_NO);
    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	
        return queryForInt("SHIPPOINT.getExits", params);
    }
	
}
