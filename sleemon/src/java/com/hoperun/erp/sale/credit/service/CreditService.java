/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：CreditService.java
 */
package com.hoperun.erp.sale.credit.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.credit.model.CreditModel;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface TerminalService.
 * 
 * @module 销售管理
 * @func 信用额度设定
 * @version 1.1
 * @author 郭利伟
 */
public interface CreditService extends IBaseService {

    /**
     * 查询并分页
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
	@SuppressWarnings("unchecked")
    public IListPage pageQuery(Map params, int pageNo);

    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
	@SuppressWarnings("unchecked")
    public Map load(String param);


    /**
     * 编辑：新增/删除
     * 
     * @param CHANN_ID the channId
     * @param channModel the chann model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public void txEdit(String channId, CreditModel model, UserBean userBean);

   
    /**
     * 更新记录
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params);
   
}
