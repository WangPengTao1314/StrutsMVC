package com.hoperun.drp.base.promote.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.promote.model.PromoteModel;
import com.hoperun.drp.base.promote.service.PromoteService;
import com.hoperun.sys.model.UserBean;
/***
 * 活动
 * @author zhang_zhongbin
 *
 */
public class PromoteServiceImpl extends BaseService implements PromoteService {
	
	   /**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {
        return this.pageQuery("Promote.pageQuery", "Promote.pageCount", params, pageNo);
    }
    
    /**
     * 加载.
     */
    @SuppressWarnings("unchecked")
    public Map load(String param) {
        return (Map) load("Promote.loadById", param);
    }
    
    
    
   /**
    * 编辑
    */
    public String txEdit(String PROMOTE_ID,PromoteModel model, UserBean userBean) {
        Map <String, String> params = new HashMap <String, String>();
        
        params.put("PROMOTE_NO", model.getPROMOTE_NO());
        params.put("PROMOTE_NAME", model.getPROMOTE_NAME());
        params.put("BEG_DATE", model.getBEG_DATE());
        params.put("END_DATE", model.getEND_DATE());
        params.put("REMARK", model.getREMARK());
        params.put("PROMOTE_DESC", model.getPROMOTE_DESC());
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
        params.put("LEDGER_NAME", userBean.getLoginZTMC());
        
        if (StringUtils.isEmpty(PROMOTE_ID)) {
        	PROMOTE_ID = StringUtil.uuid32len();
        	params.put("PROMOTE_ID", PROMOTE_ID);
        	params.put("CREATOR", userBean.getXTYHID());
            params.put("CRE_NAME", userBean.getYHM());
            params.put("DEPT_ID", userBean.getBMXXID());
            params.put("DEPT_NAME", userBean.getBMMC());
            params.put("ORG_ID", userBean.getJGXXID());
            params.put("ORG_NAME", userBean.getJGMC());
            params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);// 新增的时候状态是制定
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);// 新增的时候删除标记是0
            txInsert(params);
        } else {
            params.put("PROMOTE_ID", PROMOTE_ID);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getYHM());
            
            txUpdateById(params);
            clearCaches(PROMOTE_ID);
        }
        
        
       
        return PROMOTE_ID;
    }
    
    
    /**
     * 增加记录 
     */
    public boolean txInsert(Map <String, String> params) {
        insert("Promote.insert", params);
        return true;
    }


    /**
     * 更新记录  
     */
    public boolean txUpdateById(Map <String, String> params) {
        return update("Promote.updateById", params);
    }
    
    
    
    
    /**
     * 软删除.
     */
    public boolean txDelete(String PROMOTE_ID, UserBean userBean) {
        try{
        	Map <String, String> params = new HashMap <String, String>();
            params.put("PROMOTE_ID", PROMOTE_ID);
            params.put("DEL_FLAG", "1");
            params.put("UPDATOR", userBean.getXTYHID());
    		params.put("UPD_NAME", userBean.getYHM());
        	update("Promote.delete", params);
        	
        }catch(Exception e){
        	return false;
        }
        return true;
    }
    
    
    /**
     * 修改状态为停用
     */
    public boolean txStopById(Map <String, String> params) {
        return update("Promote.updateStateById", params);
    }

    /**
     * 修改状态为启用
     */
    public boolean txStartById(Map <String, String> params) {
        return update("Promote.updateStateById", params);
    }
    
    
    /**
     * 判断是否存在重复编号.
     * 
     * @param params the params
     * 
     * @return true, 无重复
     */
    public boolean queryForInt(Map <String, String> params) {
       if (0 == queryForInt("Promote.queryBhForInt", params)) {
            return true;
        } else{
            return false;
        }
    }
    
    

}
