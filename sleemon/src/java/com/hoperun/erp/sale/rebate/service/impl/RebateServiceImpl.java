package com.hoperun.erp.sale.rebate.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.base.chann.model.ChannModel;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.rebate.service.RebateService;
import com.hoperun.sys.model.UserBean;

public class RebateServiceImpl extends BaseService implements RebateService {
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map<String, String> params, int pageNo){
    	return this.pageQuery("chann.pageQuery", "chann.pageCount", params, pageNo);
    }
    /**
     * 加载.
     * 
     * @param param the param
     * 
     * @return the map
     */
    public Map<String,String> load(String param){
    	Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", param);
        return (Map<String, String>) load("chann.loadById", params);
    }
 
    /**
     * 保存返利
     * Description :.
     * @param modelList the chann list
     * @param userBean the user bean
     * 
     * @return the string
     */
    public String txEdit(List<ChannModel> modelList, UserBean userBean){
    	Map <String, Object> params = new HashMap <String, Object>();
    	
    	for(ChannModel model : modelList){
    		 params.put("REBATE_TOTAL", model.getREBATE_TOTAL());//返利总额
    		 params.put("REBATE_CURRT", model.getREBATE_TOTAL());//当前返利
    		 params.put("REBATE_UPD_NAME", userBean.getXM());//返利修改人
    		 params.put("REBATE_UPD_TIME", "sysdate");//返利修改时间
    		 params.put("CHANN_ID", model.getCHANN_ID());
            txUpdateById(params);
            clearCaches(model.getCHANN_ID());
    	}
        
       return null;
       
    }
 
	/**
     * 清空返利
     * Description :.
     *  1、插入 返利历史信息(ERP_REBATE_HIS)
     *  2、更新 BASE_CHANN  
     * @param modelList the chann list
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txRest(UserBean userBean){
	
    	 Map <String, Object> params = new HashMap <String, Object>();
    	 
    	 params.put("REBATE_HIS_ID",StringUtil.uuid32len());
		 params.put("CLEAN_PSON_ID",userBean.getRYXXID());
		 params.put("CLEAN_PSON_NAME",userBean.getYHM());
		 params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
		 this.insert("chann.insertRebateHis", params);
		 
		 params.clear();
		 params.put("REBATE_TOTAL", 0);//返利总额
		 params.put("REBATE_CURRT", 0);//当前返利
		 params.put("REBATE_UPD_NAME", userBean.getXM());//返利修改人
		 params.put("REBATE_UPD_TIME", "sysdate");//返利修改时间
		 params.put("STATE", BusinessConsts.JC_STATE_ENABLE);//
		 txUpdateByState(params);
    	return false;
    }
     
    /**
     * 看有没有被冻结的返利
     * @return
     */
     
	@SuppressWarnings("unchecked")
	public List isHaveRebateList(){
		Map <String, Object> params = new HashMap <String, Object>();
		params.put("STATE", BusinessConsts.JC_STATE_ENABLE);
    	List list = this.findList("chann.isHaveRebate",params);
    	return list;
    }
    
    /**
     * 增加记录 Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("chann.insert", params);
        return true;
    }
    
    /**
     * 更新使用返利Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateUseRebateById(Map <String, Object> params) {

        return update("chann.updateById", params);
    }
    
    /**
     * 更新使用返利Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx update by id
     */
    public boolean txUpdateRebateByIds(Map  params) {
    	String CHANN_IDS = (String)params.get("CHANN_NOS");
    	String LEDGER_ID = (String)params.get("LEDGER_ID");
    	String PRD_AMOUNT = (String)params.get("SET_REBATE");
    	String DESCON = (String)params.get("DESCON");
    	String DIRECTION = "0";
    	String BUSS_TYPE = "";
    	if("1".equals(DESCON)){
    		DIRECTION ="0";
    		BUSS_TYPE = "增加返利";
    	}else{
    		DIRECTION ="1";
    		BUSS_TYPE = "减少返利";
    	}
    	try{
    		LogicUtil.insRebateRegistJournal(BUSS_TYPE,LEDGER_ID,PRD_AMOUNT,CHANN_IDS,DIRECTION);
            update("chann.updateRebateByIds", params);
    	}catch(Exception ex){
    		throw new ServiceException(ex);
    	}
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

        return update("chann.updateById", params);
    }
    
    public boolean txUpdateByState(Map <String, Object> params) {
        return update("chann.updateRebateByState", params);
    }
    
    public List downQuery(Map<String,String> map){
    	return this.findList("chann.query", map);
    }

}
