package com.hoperun.erp.sale.creditreq.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.erp.sale.creditreq.model.CreditReqModel;
import com.hoperun.erp.sale.creditreq.service.CreditReqService;
import com.hoperun.sys.model.UserBean;

public class CreditReqServiceImpl extends BaseService implements CreditReqService {
	

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("CreditReq.pageQuery", "CreditReq.pageCount", params, pageNo);
    }
    
    
    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param){
    	 return (Map) load("CreditReq.loadById", param);
    }
    
    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("CreditReq.insert", params);
        return true;
    }


    /**
     * 更新记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateById(Map <String, String> params) {

        return update("CreditReq.updateById", params);
    }
    
    /**
     * 编辑
     * @param id
     * @param model
     * @param userBean
     * @return
     */
    public String txEdit(String id,CreditReqModel model, UserBean userBean){
    	Map <String, String> params = new HashMap <String, String>();
    	params.put("CHANN_ID",model.getCHANN_ID());
    	params.put("CHANN_NO",model.getCHANN_NO());
    	params.put("CHANN_NAME",model.getCHANN_NAME());
    	params.put("AREA_NO",model.getAREA_NO());
    	params.put("AREA_NAME",model.getAREA_NAME());
    	params.put("OLD_CREDIT_TOTAL",model.getOLD_CREDIT_TOTAL());
    	params.put("NEW_CREDIT_TOTAL",model.getNEW_CREDIT_TOTAL());
    	params.put("REQ_PSON_ID",model.getREQ_PSON_ID());
    	params.put("REQ_PSON_NAME",model.getREQ_PSON_NAME());
    	params.put("STATE",model.getSTATE());
    	params.put("REMARK",model.getREMARK()); 
       if (StringUtils.isEmpty(id)) {
    	   id = LogicUtil.getBmgz("ERP_CREDIT_REQ_NO");
           params.put("CREDIT_REQ_ID", id);
       	   params.put("CREATOR", userBean.getXTYHID());
           params.put("CRE_NAME", userBean.getYHM());
           params.put("DEPT_ID", userBean.getBMXXID());
           params.put("DEPT_NAME", userBean.getBMMC());
           params.put("ORG_ID", userBean.getJGXXID());
           params.put("ORG_NAME", userBean.getJGMC());
           params.put("STATE", BusinessConsts.UNCOMMIT);// 带审批的 新增的时候状态是未提交
           params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
           params.put("LEDGER_ID", userBean.getCHANN_ID());
           params.put("LEDGER_NAME", userBean.getCHANN_NAME());
           txInsert(params);
       } else {
    	   params.put("CREDIT_REQ_ID", model.getCREDIT_REQ_ID());
           params.put("UPDATOR", userBean.getXTYHID());
           params.put("UPD_NAME", userBean.getYHM());
           params.put("UPD_TIME", "数据库时间");
           txUpdateById(params);
           clearCaches(model.getCREDIT_REQ_ID());
       }
    	
    	
    	return null;
    }
    
    
    
    /**
     * 删除记录
     * Description :.
     * 
     * @param modelID the model id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String modelID, UserBean userBean){
    	Map <String, String> params = new HashMap <String, String>();
    	
        params.put("CREDIT_REQ_ID", modelID);
        params.put("DEL_FLAG", "1");
        params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
        try{
        	update("CreditReq.updateStateById", params);
        }catch(Exception e){
        	return false;
        }
        return true;
    }
    
    public Map<String,String> loadChann(String CHANNID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANNID);
        return (Map<String, String>) load("chann.loadById", params);
	}
}
