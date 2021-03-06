/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：CreditServiceImpl.java
 */
package com.hoperun.erp.sale.annualClosing.service.impl;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.annualClosing.service.AnnualClosingService;

// TODO: Auto-generated Javadoc
/**
 * The Class BrandServiceImpl.
 * 
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
 */
public class AnnualClosingServiceImpl extends BaseService implements AnnualClosingService {

	
	/**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	public List pageQuery(Map params) {
		return this.findList("AnnualClosing.pageQuery", params);
	}
	/**
     * 验证所选年份是否存在记录
     * @param params
     * @return
     */
    public int checkInfoCount(Map<String,String> params){
    	return Integer.parseInt((String.valueOf(load("AnnualClosing.pageCount", params))));
    }
    /**
     * 年结
     * @param params
     */
    public void txClosing(Map<String,String> params){
    	//先修改已存在年结
    	this.update("AnnualClosing.update", params);
    	//新增年结
    	this.insert("AnnualClosing.insert", params);
    }
}
