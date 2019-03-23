/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：CreditService.java
 */
package com.hoperun.erp.sale.annualClosing.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.IBaseService;

// TODO: Auto-generated Javadoc
/**
 * The Interface TerminalService.
 * 
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
 */
public interface AnnualClosingService extends IBaseService {

    /**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public List pageQuery(Map<String,String> params);
    
    /**
     * 验证所选年份是否存在记录
     * @param params
     * @return
     */
    public int checkInfoCount(Map<String,String> params);
    
    /**
     * 年结
     * @param params
     */
    public void txClosing(Map<String,String> params);
}
