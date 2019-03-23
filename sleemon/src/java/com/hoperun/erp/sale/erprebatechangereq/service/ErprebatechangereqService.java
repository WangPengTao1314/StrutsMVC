/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqService.java
 */
package com.hoperun.erp.sale.erprebatechangereq.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.erp.sale.erprebatechangereq.model.ErprebatechangereqModel;
import com.hoperun.sys.model.UserBean;
/**
 * The Class TempCreditReqService.
 * 
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
public interface ErprebatechangereqService extends IBaseService {
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
    public Map<String,String> load(String REBATE_CHANGE_REQ_ID);
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
    public String txEdit(String REBATE_CHANGE_REQ_ID, ErprebatechangereqModel model, UserBean userBean);
    /**
     * 删除记录
     * Description :.
     * 
     * @param REBATE_CHANGE_REQ_ID the TEMP_CREDIT_REQ id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String REBATE_CHANGE_REQ_ID, UserBean userBean);
    //审核时更新临时信用有效期
    public void upTEMP_CREDIT_VALDT(Map<String,String> map);
    
    public Map<String,String> loadChann(String CHANNID);
    
    /**
     * 关闭
     * @param REBATE_CHANGE_REQ_ID
     * @param userBean
     */
    public void txClose(String REBATE_CHANGE_REQ_ID, UserBean userBean);
    //获取渠道返利
    public Map<String,String> getRebate(String CHANN_ID);
    /**
     * 导出
     * @param map
     * @return
     */
    public List downQuery(Map<String,String> map);
    
    public void txBatchCommit(HttpServletRequest request,String AUD_FLOW_INS,String AUD_BUSS_TYPE,String REBATE_CHANGE_REQ_IDS)throws Exception;

    public void txRebateRevoke(HttpServletRequest request,String REBATE_CHANGE_REQ_ID)throws Exception;

    public void txRebateAudit(HttpServletRequest request,String AUD_FLOW_INS,String REBATE_CHANGE_REQ_IDS)throws Exception;
    
    public void txBatchAudit(HttpServletRequest request,String AUD_FLOW_INS,String AUD_BUSS_TYPE,String REBATE_CHANGE_REQ_IDS)throws Exception;

    public void txCancelAudit(HttpServletRequest request,String REBATE_CHANGE_REQ_IDS)throws Exception;
    
    public String txCopyRecord(HttpServletRequest request,String REBATE_CHANGE_REQ_ID)throws Exception;
}
