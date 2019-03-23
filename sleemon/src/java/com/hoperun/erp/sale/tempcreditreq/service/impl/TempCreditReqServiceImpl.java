/**
 * 项目名称：平台
 * 系统名：销售管理
 * 文件名：TempCreditReqServiceImpl.java
 */
package com.hoperun.erp.sale.tempcreditreq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.erp.sale.tempcreditreq.model.TempCreditReqModel;
import com.hoperun.erp.sale.tempcreditreq.service.TempCreditReqService;
import com.hoperun.sys.model.UserBean;
/**
 * The Class TempCreditReqServiceImpl.
 * 
 * @module 销售管理
 * @func 临时额度调整申请
 * @version 1.1
 * @author 刘曰刚
 */
public class TempCreditReqServiceImpl extends BaseService implements TempCreditReqService {
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
		// TODO Auto-generated method stub
		return this.pageQuery("temp_credit_req.pageQuery", "temp_credit_req.pageCount", params, pageNo);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String TEMP_CREDIT_REQ_ID) {
		// TODO Auto-generated method stub
        return (Map<String, String>) load("temp_credit_req.loadById", TEMP_CREDIT_REQ_ID);
	}
	/**
     * 编辑.
     * 
     * @param CHANN_ID the chann id
     * @param ChannModel the chann model
     * @param userBean the user bean
     * 
     * @return the string
     */
	@Override
	public String txEdit(String TEMP_CREDIT_REQ_ID, TempCreditReqModel model, UserBean userBean) {
		// TODO Auto-generated method stub
		Map <String, Object> params = new HashMap <String, Object>();
		params.put("REQ_PSON_ID", model.getREQ_PSON_ID());//申请人id
        params.put("REQ_PSON_NAME", model.getREQ_PSON_NAME());//申请人名称
		params.put("CHANN_ID", model.getCHANN_ID());//渠道id
        params.put("CHANN_NO", model.getCHANN_NO());//渠道编号
        params.put("CHANN_NAME", model.getCHANN_NAME());//渠道名称
        params.put("AREA_NO", model.getAREA_NO());//区域编号
        params.put("AREA_NAME", model.getAREA_NAME());//区域名称
        params.put("REMARK", model.getREMARK());//备注
        params.put("LEDGER_ID", userBean.getLoginZTXXID());//帐套ID
        params.put("LEDGER_NAME", userBean.getLoginZTMC());//帐套名称
        params.put("TEMP_CREDIT_REQ", model.getTEMP_CREDIT_REQ());//申请临时信用
        params.put("TEMP_CREDIT_VALDT", model.getTEMP_CREDIT_VALDT());//临时信用有效期
        if (StringUtils.isEmpty(TEMP_CREDIT_REQ_ID)) {
        	params.put("TEMP_CREDIT_REQ_ID", LogicUtil.getBmgz("ERP_TEMP_CREDIT_REQ_NO"));//临时信用id
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", DateUtil.now());//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
        	params.put("STATE", BusinessConsts.UNCOMMIT);//状态
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//删除标记
            txInsert(params);
        } else {
        	params.put("TEMP_CREDIT_REQ_ID", TEMP_CREDIT_REQ_ID);//临时信用申请ID
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称
            params.put("UPD_TIME", "数据库时间");//更新时间
            txUpdateById(params);
            clearCaches(TEMP_CREDIT_REQ_ID);
        }
        return TEMP_CREDIT_REQ_ID;
	}
	
	  /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("temp_credit_req.insert", params);
        return true;
    }
    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, Object> params) {

        return update("temp_credit_req.updateById", params);
    }
    
    /**
     * 软删除.
     * 
     * @param CHANN_ID the chann id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
	public boolean txDelete(String TEMP_CREDIT_REQ_ID, UserBean userBean) {
		// TODO Auto-generated method stub
		 Map <String, String> params = new HashMap <String, String>();
        params.put("TEMP_CREDIT_REQ_ID", TEMP_CREDIT_REQ_ID);//临时信用申请ID
        params.put("UPDATOR", userBean.getXTYHID());//更新人id
        params.put("UPD_NAME", userBean.getXM());//更新人名称
        params.put("UPD_TIME", "数据库时间");//更新时间
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);//删除标记
        return delete("temp_credit_req.delete", params);
	}

	public void upTEMP_CREDIT_VALDT(Map<String, String> map) {
		this.update("temp_credit_req.upTEMP_CREDIT_VALDT", map);
	}
	
	public Map<String,String> loadChann(String CHANNID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANNID);
        return (Map<String, String>) load("chann.loadById", params);
	}
	
    /**
     * 关闭
     * @param TEMP_CREDIT_REQ_ID
     * @param userBean
     */
    public void txClose(String TEMP_CREDIT_REQ_ID, UserBean userBean){
    	 Map <String, String> params = new HashMap <String, String>();
         params.put("TEMP_CREDIT_REQ_ID", TEMP_CREDIT_REQ_ID);//临时信用申请ID
         params.put("UPDATOR", userBean.getXTYHID());//更新人id
         params.put("UPD_NAME", userBean.getXM());//更新人名称
         params.put("UPD_TIME", "数据库时间");//更新时间
         params.put("STATE", "关闭");
         update("temp_credit_req.updateById", params);
    }
    
}
