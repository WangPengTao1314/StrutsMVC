/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqService.java
 */
package com.hoperun.drp.oamg.channcontractup.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.oamg.channcontractup.model.ChanncontractupModel;
import com.hoperun.sys.model.UserBean;
/**
 * The Class ChanncontractupService.
 * 
 * @module 渠道合同管理
 * @func 渠道合同上传
 * @version 1.1
 * @author 刘曰刚
 */
public interface ChanncontractupService extends IBaseService {
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
    public Map<String,String> load(String CHANN_CONTRACT_ID);
    /**
     * 编辑：新增/删除
     * Description :.
     * 
     * @param TempCreditReqModel the TempCreditReq model
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(String CHANN_CONTRACT_ID, ChanncontractupModel model, UserBean userBean);
    /**
     * 删除记录
     * Description :.
     * 
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String CHANN_CONTRACT_ID, UserBean userBean);
    //提交
    public void toCommit(Map<String,String> map);
}
