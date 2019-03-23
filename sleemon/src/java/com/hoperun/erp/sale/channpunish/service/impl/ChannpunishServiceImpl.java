/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：ChannServiceImpl.java
 */
package com.hoperun.erp.sale.channpunish.service.impl;

import java.util.HashMap;
import java.util.Map;


import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.erp.sale.channpunish.service.ChannpunishService;

/**
 * The Class ChannServiceImpl.
 * 
 * @module 系统管理
 * @func 渠道信息
 * @version 1.0
 * @author 刘曰刚
 */
public class ChannpunishServiceImpl extends BaseService implements ChannpunishService {

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Channpunish.pageQuery", "Channpunish.pageCount", params, pageNo);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", param);
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        return (Map<String, String>) load("Channpunish.loadById", params);
	}

    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public void txUpdateById(Map <String, String> params) {
        update("Channpunish.updateById", params);
    }

}
