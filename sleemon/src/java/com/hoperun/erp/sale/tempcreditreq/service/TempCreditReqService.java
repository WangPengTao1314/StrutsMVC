/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqService.java
 */
package com.hoperun.erp.sale.tempcreditreq.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.tempcreditreq.model.TempCreditReqModel;
import com.hoperun.sys.model.UserBean;
/**
 * The Class TempCreditReqService.
 * 
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
public interface TempCreditReqService extends IBaseService {
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
    public Map<String,String> load(String TEMP_CREDIT_REQ_ID);
    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param chann_id the chan id
     * @param TempCreditReqModel the TempCreditReq model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String TEMP_CREDIT_REQ_ID, TempCreditReqModel model, UserBean userBean);
    /**
     * 删除记录
     * Description :.
     * 
     * @param TEMP_CREDIT_REQ_ID the TEMP_CREDIT_REQ id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String TEMP_CREDIT_REQ_ID, UserBean userBean);
    //审核时更新临时信用有效期
    public void upTEMP_CREDIT_VALDT(Map<String,String> map);
    
    public Map<String,String> loadChann(String CHANNID);
    
    /**
     * 关闭
     * @param TEMP_CREDIT_REQ_ID
     * @param userBean
     */
    public void txClose(String TEMP_CREDIT_REQ_ID, UserBean userBean);
}
