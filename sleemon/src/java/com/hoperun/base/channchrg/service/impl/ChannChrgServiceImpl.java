package com.hoperun.base.channchrg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.base.channchrg.service.ChannChrgService;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;

public class ChannChrgServiceImpl extends BaseService implements ChannChrgService  {
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {
        return this.pageQuery("AreaChrg.pageQuery", "AreaChrg.pageCount", params, pageNo);
    }
    
    
    public  List<Map<String,String>>  childQuery(Map<String,String> params){
    	String XTYHID = params.get("XTYHID");
    	if(StringUtil.isEmpty(XTYHID)){
    		return this.findList("Channchrg.queryChann", params);
    	}
    	return this.findList("Channchrg.queryChannByparams", params);
    }
    
    /**
     * 编辑 按用户维度
     */
    public void txEdit(String XTYHID,String CHANN_IDS,String deleteIds){
    	if(!StringUtil.isEmpty(deleteIds)){
    		Map<String, String> params = new HashMap<String, String>();
        	params.put("XTYHID", XTYHID);
        	params.put("CHANN_IDS", deleteIds);
        	this.delete("Channchrg.deleteByIds", params);
        	params.clear();
    	}
    	
    	List<Map<String,String>> addList = new ArrayList<Map<String,String>>();
    	if(!StringUtil.isEmpty(CHANN_IDS)){
    		String[] ids = CHANN_IDS.split(",");
        	for(int i = 0;i<ids.length;i++){
        		Map<String, String> map = new HashMap<String, String>();
        		String USER_CHANN_CHRG_ID = StringUtil.uuid32len();
        		map.put("USER_CHANN_CHRG_ID", USER_CHANN_CHRG_ID);
        		map.put("XTYHID", XTYHID);
        		map.put("CHANN_ID", ids[i]);
        		addList.add(map);
        	}
        	
        	if(!addList.isEmpty()){
        		this.batch4Update("Channchrg.insert", addList);
        	}
    	}
    	
    }
    
    
    /**
     * 删除
     * @param XTYHID 系统用户ID
     * @param CHANN_IDS 渠道IDS
     */
    public void txDelete(String XTYHID,String CHANN_IDS){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("XTYHID", XTYHID);
    	params.put("CHANN_IDS", CHANN_IDS);
    	this.delete("Channchrg.deleteByIds", params);
    }
    
    /**
     * 查询用户信息
     * @param params
     * @return
     */
    public List<Map<String,String>>  childYhxxQuery(Map<String,String> params){
    	return this.findList("Channchrg.queryRyxxByparams", params);
    }
    
    
    /**
     * 编辑 按渠道维度
     * @param XTYHIDS
     * @param CHANN_ID
     * @param deleteIds
     */
    public void txEditRyxx(String XTYHIDS,String CHANN_ID,String deleteIds){
    	if(!StringUtil.isEmpty(deleteIds)){
    		Map<String, String> params = new HashMap<String, String>();
        	params.put("CHANN_ID", CHANN_ID);
        	params.put("XTYHIDS", deleteIds);
        	this.delete("Channchrg.deleteByYhxxIds", params);
        	params.clear();
    	}
    	
    	List<Map<String,String>> addList = new ArrayList<Map<String,String>>();
    	if(!StringUtil.isEmpty(XTYHIDS)){
    		String[] ids = XTYHIDS.split(",");
        	for(int i = 0;i<ids.length;i++){
        		Map<String, String> map = new HashMap<String, String>();
        		String USER_CHANN_CHRG_ID = StringUtil.uuid32len();
        		map.put("USER_CHANN_CHRG_ID", USER_CHANN_CHRG_ID);
        		map.put("XTYHID", ids[i]);
        		map.put("CHANN_ID",CHANN_ID);
        		addList.add(map);
        	}
        	
        	if(!addList.isEmpty()){
        		this.batch4Update("Channchrg.insert", addList);
        	}
    	}
    	
    }
    
    /**
     * 以渠道维度 取消分管
     * @param CHANN_ID
     * @param XTYHIDS
     */
    public void txDeleteYhxx(String CHANN_ID,String XTYHIDS){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("XTYHIDS", XTYHIDS);
    	params.put("CHANN_ID", CHANN_ID);
    	this.delete("Channchrg.deleteByYhxxIds", params);
    }
    
}
