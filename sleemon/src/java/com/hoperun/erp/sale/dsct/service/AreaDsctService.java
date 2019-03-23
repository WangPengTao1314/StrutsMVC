/**
 * 项目名称：喜临门
 * 系统名：区域折扣信息
 * 文件名：AreaDsctAction.java
 */
package com.hoperun.erp.sale.dsct.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.dsct.model.AreaDsctModel;
import com.hoperun.sys.model.UserBean;

/**
 * The Class AreaDsctModel.
 * 
 * @module 总部->销售管理->价格管理
 * @func 区域折扣信息
 * @version 1.0
 * @author 王志格
 */
public interface AreaDsctService extends IBaseService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map params, int pageNo);
    
    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map load(String param);
    
    /**
     * 编辑
     * 
     */
	public void txEdit(String AREA_DSCT_ID, AreaDsctModel model, UserBean userBean);
	
	 /**
     * 删除 
     * @param AREA_DSCT_ID  区域折扣ID
     * @param AREA_ID 区域ID
     */
    public void txDeleteById(String AREA_DSCT_ID,String AREA_ID);
    
}
