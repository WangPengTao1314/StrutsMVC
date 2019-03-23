/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXServiceImpl.java
 */
package com.hoperun.base.provider.service.impl;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.base.provider.model.ProviderModel;
import com.hoperun.base.provider.service.ProviderService;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Class NewSingleTableServiceImpl.
 * 
 * @module 系统管理
 * @func 单表示例
 * @version 1.1
 * @author 朱晓文
 */
public class ProviderServiceImpl extends BaseService implements ProviderService {

    /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("PROVIDER.pageQuery", "PROVIDER.pageCount", params, pageNo);
    }


    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, String> params) {

        insert("PROVIDER.insert", params);
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

        return update("PROVIDER.updateById", params);
    }


    /**
     * 软删除.
     * 
     * @param jgxxId the jgxx id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params) {
        return delete("PROVIDER.delete", params);
    }
    

    /**
     * 加载.
     * 
     * @param jgxxId the jgxx id
     * 
     * @return the map
     */
    @SuppressWarnings("unchecked")
    public Map load(String jgxxId) {

        return (Map) load("PROVIDER.loadById", jgxxId);
    }

    
    /**
     * 判断是否存在重复机构.
     * 
     * @param params the params
     * 
     * @return true, 无重复
     */
    public int queryForInt(String PRVD_NO) {

    	return queryForInt("PROVIDER.queryBhForInt", PRVD_NO);
    }
    
    
    /**
     * 编辑.
     * 
     * @param jgxxId the jgxx id
     * @param jgxxModel the jgxx model
     * @param userBean the user bean
     * 
     * @return the string
     */
    @Override
    public String txEdit(String prvd_id, ProviderModel providerModel, UserBean userBean) {
        Map <String, String> params = new HashMap <String, String>();
        params.put("PRVD_ID", providerModel.getPRVD_ID());
        params.put("PRVD_NO", providerModel.getPRVD_NO());
        params.put("PRVD_NAME", providerModel.getPRVD_NAME());
        params.put("PRVD_TYPE", providerModel.getPRVD_TYPE());
        params.put("PRVD_LVL", providerModel.getPRVD_LVL());
        params.put("PRVD_NATRUE", providerModel.getPRVD_NATRUE());
        params.put("NATION", providerModel.getNATION());
        params.put("PROV", providerModel.getPROV());
        params.put("CITY", providerModel.getCITY());
        params.put("COUNTY", providerModel.getCOUNTY());
        params.put("PRVD_CY", providerModel.getPRVD_CY());
        params.put("PRVD_CAP", providerModel.getPRVD_CAP());
        params.put("PERSON_BUSS", providerModel.getPERSON_BUSS());
        params.put("PERSON_CON", providerModel.getPERSON_CON());
        params.put("TEL", providerModel.getTEL());
        params.put("MOBILE_PHONE", providerModel.getMOBILE_PHONE());
        params.put("TAX", providerModel.getTAX());
        params.put("POST", providerModel.getPOST());
        params.put("EMAIL", providerModel.getEMAIL());
        params.put("WEB", providerModel.getWEB());
        params.put("LEGAL_REPRE", providerModel.getLEGAL_REPRE());
        params.put("BUSS_LIC", providerModel.getBUSS_LIC());
        params.put("INVOICE_TI", providerModel.getINVOICE_TI());
        params.put("INVOICE_ADDR", providerModel.getINVOICE_ADDR());
        params.put("OPENING_BANK", providerModel.getOPENING_BANK());
        params.put("BANK_ACCT", providerModel.getBANK_ACCT());
        params.put("FI_CMP_NO", providerModel.getFI_CMP_NO());
        params.put("ADDRESS", providerModel.getADDRESS());
        params.put("REMARK", providerModel.getREMARK());
        params.put("DEFAULT_FLAG", providerModel.getDEFAULT_FLAG());
        params.put("UPDATOR", userBean.getXTYHID());
	    params.put("UPD_NAME", userBean.getYHM());        
	    
	    //新增供应商
        if (StringUtils.isEmpty(prvd_id)) {
        	prvd_id = StringUtil.uuid32len();
            params.put("PRVD_ID", prvd_id);
            params.put("STATE", "制定");
            params.put("CREATOR", userBean.getXTYHID());
            params.put("CRE_NAME", userBean.getXM());
            
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getXM());
            params.put("DEPT_ID", userBean.getBMBH());        
            params.put("DEPT_NAME", userBean.getBMMC());
            params.put("ORG_ID", userBean.getJGBH());
            params.put("ORG_NAME", userBean.getJGMC());
            params.put("LEDGER_ID", userBean.getLoginZTXXID());
            params.put("LEDGER_NAME", userBean.getLoginZTMC());
            
            txInsert(params);
        } else {
            params.put("PRVD_ID", prvd_id);
            txUpdateById(params);
            clearCaches(prvd_id);
        }
        if(checkDEFAULT_FLAG()>1){
        	throw new ServiceException("对不起，已有选择过的默认供应商，不能多选!");
        }
        return prvd_id;
    }
    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params) {

        return update("PROVIDER.updStusById", params);
    }


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params) {
        return update("PROVIDER.updStusById", params);
    }
    public int checkDEFAULT_FLAG(){
    	return StringUtil.getInteger(this.load("PROVIDER.checkDEFAULT_FLAG", ""));
    }
}
