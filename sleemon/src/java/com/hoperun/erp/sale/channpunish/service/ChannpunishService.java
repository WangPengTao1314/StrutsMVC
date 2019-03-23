/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：QDXXService.java
 */
package com.hoperun.erp.sale.channpunish.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
/**
 * The Interface channService.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public interface ChannpunishService extends IBaseService {
	
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
     * 更新
     * @param params
     */
    public void txUpdateById(Map <String, String> params);
}
