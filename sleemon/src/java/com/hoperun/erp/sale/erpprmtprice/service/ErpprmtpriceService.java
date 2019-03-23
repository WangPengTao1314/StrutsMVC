/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：QDXXService.java
 */
package com.hoperun.erp.sale.erpprmtprice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.erpprmtprice.model.ErpprmtpriceModel;
import com.hoperun.sys.model.UserBean;
/**
 * The Interface channService.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public interface ErpprmtpriceService extends IBaseService {
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo);
    public void txInsertPrice(List<ErpprmtpriceModel> ModelList,String PRMT_PLAN_ID,UserBean userBean);
    public long getCount(Map<String,String> map);
    public void txUpdateState(String PRMT_PRICE_IDS,String STATE,UserBean userBean);
}
