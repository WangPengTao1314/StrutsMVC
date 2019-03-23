package com.hoperun.drp.base.paramset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.paramset.model.ParamsetModel;
import com.hoperun.drp.base.paramset.service.ParamsetService;
import com.hoperun.sys.model.UserBean;

public class ParamsetServiceImpl extends BaseService implements ParamsetService {

	 /**
     * 分页查询
     * Description :.
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("Paramset.pageQuery", "Paramset.pageCount", params,pageNo);
    }
    
    /**
     * 查询表单条记录详细信息
     * Description :.
     * @param DATA_CONF_ID
     * @return the map< string, string>
     */
    public Map <String, String> load(String LEDGER_ID){
    	
    	return (Map<String, String>) load("Paramset.loadById", LEDGER_ID);
    }
    
    public Map <String, String> loadByConfId(String DATA_CONF_ID){
    	return (Map<String, String>) load("Paramset.loadByConfId", DATA_CONF_ID);
    }
    
    /**
     * 编辑：新增/修改
     * Description :.
     * @param CHANN_ID the 渠道ID
     * @param modelList the  参数list
     * @param userBean the user bean
     */
    public void  txEdit(String CHANN_ID, List<ParamsetModel> modelList,UserBean userBean) {
    	List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
    	List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
        for(ParamsetModel model : modelList){
        	Map<String, String> params = new HashMap<String, String>();
        	String DATA_CONF_ID = model.getDATA_CONF_ID();
    		params.put("DATA_TYPE", model.getDATA_TYPE());
    		params.put("DATA_VAL",  model.getDATA_VAL());
    		params.put("LEDGER_ID", userBean.getLoginZTXXID());
    		params.put("DEL_FLAG",  BusinessConsts.DEL_FLAG_COMMON);
    		params.put("DATA_NAME", model.getDATA_NAME());
    		params.put("IS_DEAL_FLAG", model.getIS_DEAL_FLAG());
    	    if(StringUtil.isEmpty(DATA_CONF_ID)){
    	    	DATA_CONF_ID = StringUtil.uuid32len();
    	    	params.put("DATA_CONF_ID", DATA_CONF_ID);
    	    	addList.add(params);
    	    }else{
    	    	params.put("DATA_CONF_ID", DATA_CONF_ID);
    	    	updateList.add(params);
    	    }
    		
        }
		
        if(!addList.isEmpty()){
        	this.batch4Update("Paramset.insert", addList);
        	 
        }
        
        if(!updateList.isEmpty()){
        	this.batch4Update("Paramset.updateById", updateList);
        }
        
	}
    
    /**
     * 查询
     * @param DATA_TYPE 
     * @param LEDGER_ID
     * @return
     */
    public List<ParamsetModel> query(String DATA_TYPE,String LEDGER_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG",  BusinessConsts.DEL_FLAG_COMMON);
		params.put("DATA_TYPE", DATA_TYPE);
		params.put("LEDGER_ID", LEDGER_ID);
		return this.findList("Paramset.queryParamsByType", params);
    }
    
    /**
     * 查询 参数类型
     */
    public List<Map<String,String>> loadType(String LEDGER_ID){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("DEL_FLAG",  BusinessConsts.DEL_FLAG_COMMON);
		params.put("LEDGER_ID", LEDGER_ID);
		return this.findList("Paramset.loadType", params);
    }
    
    /**
     * 删除数据
     * 
     * @param DATA_CONF 
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String DATA_CONF_IDS, UserBean userBean){
    	Map <String, String> params = new HashMap <String, String>();
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);//
        params.put("DATA_CONF_IDS", DATA_CONF_IDS);
        params.put("LEDGER_ID", userBean.getLoginZTXXID());
        return update("Paramset.delete", params);
    }
    
    /**
     * 修改最大冻结天数
     */
    public void  upFreenDaysById(Map<String,String> map){
    	this.update("Paramset.upFreenDaysById", map);
    }
    
    /**
     * 按渠道id查询渠道信息
     */
	public Map<String,String> getChannInfo(String CHANN_ID){
		 return (Map<String, String>) this.load("Paramset.getChannInfo",CHANN_ID);
	 }
	
	 /**
     * 查询数据表中是否有参数名称、类型、值的记录存在
     */
    public int  queryParams(String dataName,String dataType,String dataVal){
    	
    	Map <String, String> params = new HashMap <String, String>();
        params.put("DATA_NAME",dataName);
        params.put("DATA_TYPE",dataType);
        params.put("DATA_VAL",dataVal);
    	return this.queryForInt("Paramset.queryParams",params);
    }
    
    public List<ParamsetModel> isHaveParamsetList(String dataName,String dataType,String dataVal){
    	Map <String, Object> params = new HashMap <String, Object>();
		params.put("DATA_NAME", dataName);
		params.put("DATA_TYPE", dataType);
		params.put("DATA_VAL", dataVal);
    	List list = this.findList("Paramset.isHaveParamset",params);
    	return list;
    }
}
