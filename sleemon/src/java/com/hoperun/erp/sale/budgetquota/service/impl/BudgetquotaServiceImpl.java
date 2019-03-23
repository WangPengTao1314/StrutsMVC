package com.hoperun.erp.sale.budgetquota.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.ResourceUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaModel;
import com.hoperun.erp.sale.budgetquota.model.BudgetquotaTree;
import com.hoperun.erp.sale.budgetquota.service.BudgetquotaService;
import com.hoperun.sys.model.UserBean;

public class BudgetquotaServiceImpl extends BaseService implements BudgetquotaService{
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo) {

        return this.pageQuery("Budgetquota.pageQuery", "Budgetquota.pageCount", params, pageNo);
    }

    /**
     * 增加记录 Description :.
     * @param params the params
     * @return true, if tx insert
     */
    public boolean txInsert(Map <String, Object> params) {
        insert("Budgetquota.insert", params);
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
        return update("Budgetquota.updateById", params);
    }

    /**
     * 删除.
     * 
     * @param userBean the user bean
     * @return true, if tx delete
     */
    public boolean txDelete(Map <String, String> params) {
       return update("Budgetquota.delete", params);
    }


    
    /**
     * 加载.
     * 
     */
    @SuppressWarnings("unchecked")
    public Map load(String BUDGET_ITEM_ID) {
        return (Map) load("Budgetquota.loadById", BUDGET_ITEM_ID);
    }
    

    /**
     * 编辑.
     * 
     */
    public String txEdit(String BUDGET_QUOTA_ID, BudgetquotaModel model, UserBean userBean) {
        Map <String, Object> params = new HashMap <String, Object>();
        params.put("BUDGET_ITEM_ID", model.getBUDGET_ITEM_ID()); 
        params.put("BUDGET_ITEM_NO", model.getBUDGET_ITEM_NO()); 
        params.put("BUDGET_ITEM_NAME", model.getBUDGET_ITEM_NAME());
        params.put("BUDGET_ITEM_TYPE", model.getBUDGET_ITEM_TYPE());
        params.put("PAR_BUDGET_ITEM_ID", model.getPAR_BUDGET_ITEM_ID());
        params.put("PAR_BUDGET_ITEM_NO", model.getPAR_BUDGET_ITEM_NO());
        params.put("PAR_BUDGET_ITEM_NAME", model.getPAR_BUDGET_ITEM_NAME());
        params.put("YEAR", model.getYEAR());
        params.put("MONTH", model.getMONTH());
        String QUARTER =  model.getQUARTER();
        if(StringUtil.isEmpty(QUARTER)){
        	QUARTER = StringUtil.getQuarterFromMonth(model.getMONTH());
        }
        params.put("QUARTER", QUARTER);
        params.put("BUDGET_DEPT_ID", model.getBUDGET_DEPT_ID());
        params.put("BUDGET_DEPT_NO", model.getBUDGET_DEPT_NO());
        params.put("BUDGET_DEPT_NAME", model.getBUDGET_DEPT_NAME());
        params.put("RELATE_AREA_ID", model.getRELATE_AREA_ID());
        params.put("RELATE_AREA_NO", model.getRELATE_AREA_NO());
        params.put("RELATE_AREA_NAME", model.getRELATE_AREA_NAME());
        params.put("BUDGET_QUOTA", model.getBUDGET_QUOTA());
        params.put("FREEZE_BUDGET_QUOTA", model.getFREEZE_BUDGET_QUOTA());
        params.put("USE_BUDGET_QUOTA", model.getUSE_BUDGET_QUOTA());
        String action = "新增"; 
        if (StringUtils.isEmpty(BUDGET_QUOTA_ID)) {
        	BUDGET_QUOTA_ID = StringUtil.uuid32len();
        	params.put("BUDGET_QUOTA_ID", BUDGET_QUOTA_ID);
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", "sysdate");//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
        	params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//状态
            txInsert(params);
        }else{
        	action = "修改";
        	params.put("BUDGET_QUOTA_ID", BUDGET_QUOTA_ID);
            params.put("UPDATOR", userBean.getXTYHID());//更新人id
            params.put("UPD_NAME", userBean.getXM());//更新人名称
            params.put("UPD_TIME", "sysdate");//更新时间
            txUpdateById(params);
            clearCaches(BUDGET_QUOTA_ID);
        }
        //关联区域不为空的情况下更新对应的战区额度  
        if(!StringUtil.isEmpty(model.getRELATE_AREA_ID())){
        	params.clear();
        	params.put("RELATE_AREA_ID", model.getRELATE_AREA_ID());
        	update("Budgetquota.updateParItemQuota", params);
        }
       
    	String content = "用户ID:"+userBean.getXTYHID()
    	+" 姓名："+userBean.getXM()
    	+" 时间："+DateUtil.newDataTime()
    	+" 单据ID: "+BUDGET_QUOTA_ID
    	+" 科目："+model.getBUDGET_ITEM_NAME()
    	+" 额度："+model.getBUDGET_QUOTA()
    	;
    	LogicUtil.actLog("预算额度编辑", action, "DM", "成功", content, "", "", "","");
        return BUDGET_QUOTA_ID;
    }
    
    
    public void txStartById(Map<String, String> params){
    	update("Budgetquota.updateStateById", params);
    }
    
    /**
     * 修改状态为停用
     * Description :.
     * @param params the params
     * @return true, if tx stop by id
     */
    public void txStopById(Map <String, String> params) {
         update("Budgetquota.updateStateById", params);
    }
    
    
    /**
     * 树
     * @return
     * @throws Exception
     */
    public List <BudgetquotaTree> getTree() throws Exception {
        List<BudgetquotaTree> menus = this.findList("Budgetquota.queryTree", "");
        return ResourceUtil.getZTreeFromList(menus, BudgetquotaTree.class);
    }
    
    /**
     * 批量编辑.
     * 
     */
    public void txBatchEdit(String BUDGET_QUOTA_ID, BudgetquotaModel model, UserBean userBean) {
        Map <String, Object> params = new HashMap <String, Object>();
        params.put("BUDGET_ITEM_ID", model.getBUDGET_ITEM_ID()); 
        params.put("BUDGET_ITEM_NO", model.getBUDGET_ITEM_NO()); 
        params.put("BUDGET_ITEM_NAME", model.getBUDGET_ITEM_NAME());
        
        params.put("PAR_BUDGET_ITEM_ID", model.getPAR_BUDGET_ITEM_ID());
        params.put("PAR_BUDGET_ITEM_NO", model.getPAR_BUDGET_ITEM_NO());
        params.put("PAR_BUDGET_ITEM_NAME", model.getPAR_BUDGET_ITEM_NAME());
//        params.put("QUARTER", model.getQUARTER());
//        params.put("MONTH", model.getMONTH());
        params.put("BUDGET_DEPT_ID", model.getBUDGET_DEPT_ID());
        params.put("BUDGET_DEPT_NO", model.getBUDGET_DEPT_NO());
        params.put("BUDGET_DEPT_NAME", model.getBUDGET_DEPT_NAME());
        params.put("RELATE_AREA_ID", model.getRELATE_AREA_ID());
        params.put("RELATE_AREA_NO", model.getRELATE_AREA_NO());
        params.put("RELATE_AREA_NAME", model.getRELATE_AREA_NAME());
//        params.put("BUDGET_QUOTA", model.getBUDGET_QUOTA());
        params.put("FREEZE_BUDGET_QUOTA", model.getFREEZE_BUDGET_QUOTA());
        params.put("USE_BUDGET_QUOTA", model.getUSE_BUDGET_QUOTA());
         
//        if (StringUtils.isEmpty(BUDGET_QUOTA_ID)) {
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
        	params.put("CREATOR", userBean.getXTYHID());//制单人ID
            params.put("CRE_NAME", userBean.getXM());//制单人名称
            params.put("CRE_TIME", "sysdate");//制单时间
            params.put("DEPT_ID", userBean.getBMXXID());//制单部门ID
            params.put("DEPT_NAME", userBean.getBMMC());//制单部门名称
            params.put("ORG_ID", userBean.getJGXXID());//制单机构id
            params.put("ORG_NAME", userBean.getJGMC());//制单机构名称
        	params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//状态
//            txInsert(params);
//        }else{
//        	 params.put("BUDGET_QUOTA_ID", BUDGET_QUOTA_ID);
//             params.put("UPDATOR", userBean.getXTYHID());//更新人id
//             params.put("UPD_NAME", userBean.getXM());//更新人名称
//             params.put("UPD_TIME", "sysdate");//更新时间
//             txUpdateById(params);
//             clearCaches(BUDGET_QUOTA_ID);
//        }
        List<Map<String, Object>> addList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> updateList = new ArrayList<Map<String, Object>>();
        String BUDGET_ITEM_TYPE = model.getBUDGET_ITEM_TYPE();
        params.put("BUDGET_ITEM_TYPE", BUDGET_ITEM_TYPE);
        params.put("YEAR", model.getYEAR());
        if("按年".equals(BUDGET_ITEM_TYPE)){
        	String BUDGET_QUOTA = model.getBUDGET_QUOTA();
        	params.put("BUDGET_QUOTA_ID", StringUtil.uuid32len());
        	params.put("BUDGET_QUOTA", BUDGET_QUOTA);
        	addList.add(params);
        }
        if("按季".equals(BUDGET_ITEM_TYPE)){
        	String BUDGET_QUOTA = model.getBUDGET_QUOTA();
        	String[] BUDGET_QUOTAS = BUDGET_QUOTA.split(",");
        	for(int i=0;i<BUDGET_QUOTAS.length;i++){
        		 Map<String,Object> map = new HashMap<String,Object>();
        		 map.putAll(params);
        		 String[] arr = BUDGET_QUOTAS[i].split("-");//用'-'区分 前面是值 后面是ID
        		 if(arr.length == 0){
        			 continue;
        		 }
        		 map.put("QUARTER", replaceQuarterStr(i));
        		 map.put("BUDGET_QUOTA", arr[0]);
        		 
        		 if(1 == arr.length || StringUtil.isEmpty(arr[1])){
        			 if(!StringUtil.isEmpty(arr[0])){
        				 map.put("BUDGET_QUOTA_ID", StringUtil.uuid32len());
            			 addList.add(map);
        			 }
        		 }else{
        			 map.put("BUDGET_QUOTA_ID", arr[1]);
        			 updateList.add(map);
        		 }
        	}
        	
        }
        if("按月".equals(BUDGET_ITEM_TYPE)){
        	String BUDGET_QUOTA = model.getBUDGET_QUOTA();
        	String[] BUDGET_QUOTAS = BUDGET_QUOTA.split(",");
        	for(int i=0;i<BUDGET_QUOTAS.length;i++){
        		Map<String,Object> map = new HashMap<String,Object>();
       		    map.putAll(params);
	       		String[] arr = BUDGET_QUOTAS[i].split("-");//用'-'区分 前面是值 后面是ID
	    		if(arr.length == 0){
	    			continue;
	    		}
	    		map.put("BUDGET_QUOTA", arr[0]);
	    		if(1 == arr.length || StringUtil.isEmpty(arr[1])){
	       			 if(!StringUtil.isEmpty(arr[0])){
	       				String MONTH = "";
	           		    if(i<9){
	           		    	MONTH = "0"+(i+1);
	           		    }else{
	           		    	MONTH = ""+(i+1);
	           		    }
	           		    map.put("MONTH", MONTH);
	       				map.put("BUDGET_QUOTA_ID", StringUtil.uuid32len());
	           			addList.add(map);
	       			 }
	       		}else{
	       			 map.put("BUDGET_QUOTA_ID", arr[1]);
	       			 updateList.add(map);
	       		}
        	}
        }
        if(!addList.isEmpty()){
        	if(StringUtil.isEmpty(BUDGET_QUOTA_ID)){
        		List<Map<String,Object>> removeList = new ArrayList<Map<String, Object>>();
        		for(Map<String,Object> map : addList){
            		List<Map<String,String>> list = this.findList("Budgetquota.queryBatchBudget", map);
            		if(null != list && !list.isEmpty()){
            			String ID = StringUtil.nullToSring(list.get(0).get("BUDGET_QUOTA_ID"));
            			map.put("BUDGET_QUOTA_ID", ID);
            			updateList.add(map);
            			removeList.add(map);
            		}
            	}
        		addList.removeAll(removeList);
        	}
        	if(!addList.isEmpty()){
        		this.batch4Update("Budgetquota.insert", addList);
        		for(Map<String,Object> map : addList){
        			String content = "用户ID:"+userBean.getXTYHID()
        	    	+" 姓名："+userBean.getXM()
        	    	+" 时间："+DateUtil.newDataTime()
        	    	+" 单据ID: "+map.get("BUDGET_QUOTA_ID")
        	    	+" 科目："+map.get("BUDGET_ITEM_NAME")
        	    	+" 额度："+map.get("BUDGET_QUOTA")
        	    	;
        	    	LogicUtil.actLog("预算额度编辑", "新增", "DM", "成功", content, "", "", "","");
        		}
        	}
        	
        }
        if(!updateList.isEmpty()){
        	this.batch4Update("Budgetquota.updateById", updateList);
        	for(Map<String,Object> map : updateList){
    			String content = "用户ID:"+userBean.getXTYHID()
    	    	+" 姓名："+userBean.getXM()
    	    	+" 时间："+DateUtil.newDataTime()
    	    	+" 单据ID: "+map.get("BUDGET_QUOTA_ID")
    	    	+" 科目："+map.get("BUDGET_ITEM_NAME")
    	    	+" 额度："+map.get("BUDGET_QUOTA")
    	    	;
    	    	LogicUtil.actLog("预算额度编辑", "修改", "DM", "成功", content, "", "", "","");
    		}
        }
        //关联区域不为空的情况下更新对应的战区额度  
        if(!StringUtil.isEmpty(model.getRELATE_AREA_ID())){
        	params.clear();
        	params.put("RELATE_AREA_ID", model.getRELATE_AREA_ID());
        	update("Budgetquota.updateParItemQuota", params);
        }
    }
    
    public Map<String,String> loadBatchById(String param){
    	Map<String,String>  enty = (Map<String, String>) this.load("Budgetquota.loadBatchById", param);
    	return enty;
    }
    
    private String replaceQuarterStr(int i){
    	String str = "";
    	i = i+1;
    	if(i == 1){
    		str =  "一季度";
    	}
    	if(i == 2){
    		str =  "二季度";
    	}
    	if(i == 3){
    		str =  "三季度";
    	}
    	if(i == 4){
    		str =  "四季度";
    	}
    	return str;
    }
    
    /**
     * 同一个科目下 年份 月份 季度不能重复设置额度
     * @param model
     * @return 
     */
    public boolean isRepeatBudgetItem(String BUDGET_QUOTA_ID,BudgetquotaModel model){
    	boolean bool = false;
    	Map <String, Object> params = new HashMap <String, Object>();
    	params.put("BUDGET_QUOTA_ID", BUDGET_QUOTA_ID); 
        params.put("BUDGET_ITEM_ID", model.getBUDGET_ITEM_ID()); 
        params.put("BUDGET_DEPT_ID", model.getBUDGET_DEPT_ID()); 
        params.put("YEAR", model.getYEAR()); 
        String PARAM_COLUMN = "";
        if("按季".equals(model.getBUDGET_ITEM_TYPE())){
        	PARAM_COLUMN = " and u.QUARTER='"+model.getQUARTER()+"' ";
        }
        if("按月".equals(model.getBUDGET_ITEM_TYPE())){
        	PARAM_COLUMN = " and u.MONTH='"+model.getMONTH()+"'  ";
        }
        params.put("PARAM_COLUMN", PARAM_COLUMN); 
        int rst = this.queryForInt("Budgetquota.isRepeatBudgetItem", params);
        if(rst>0){
        	bool = true;
        }
    	return bool;
    }
  
    /**
     * @param BUDGET_QUOTA_ID
     * @return
     */
    public BudgetquotaModel queryJudgeModelByQuota(String BUDGET_QUOTA_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("BUDGET_QUOTA_ID", BUDGET_QUOTA_ID);
     	List<BudgetquotaModel> list = this.findList("Budgetquota.selectBrothersByQuota", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
    }
}
