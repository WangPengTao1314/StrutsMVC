package com.hoperun.base.prdgroup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.base.prdgroup.model.PrdGroupDtlModel;
import com.hoperun.base.prdgroup.model.PrdGroupModel;
import com.hoperun.base.prdgroup.service.PrdGroupService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

public class PrdGroupServiceImpl extends BaseService implements PrdGroupService{
	

	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo){
    	return this.pageQuery("PrdGroup.pageQuery", "PrdGroup.pageCount",params, pageNo);
    }
    
    /**
     * 显示树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <PrdGroupModel> getTree() throws Exception{
    	return null;
    }
    
    
    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param){
    	return (Map<String,String>) load("PrdGroup.loadById", param);
    }
    
    
    /**
     * 判断是否存在重复.
     * 
     * @param params the params
     * 
     * @return true ->没有重复
     */
    public boolean queryForInt(Map <String, String> params){
    	 if (0 == queryForInt("PrdGroup.queryBhForInt", params)) {
             return true;
         } else{
             return false;
         }
    }




    
    /**
     * 编辑.
     * 
     * @param model the model
     * @param userBean the user bean
     * @param modelList 子表
     * @return the string
     */
    public String txEdit(PrdGroupModel model, UserBean userBean,List <PrdGroupDtlModel> chldList){
    	
    	Map<String,String> params = new HashMap<String,String>();
    	
    	params.put("PRD_GROUP_NO", model.getPRD_GROUP_NO());
    	params.put("PRD_GROUP_NAME", model.getPRD_GROUP_NAME());
    	params.put("REMARK", model.getREMARK());
    	
    	String id = model.getPRD_GROUP_ID();
        if(StringUtil.isEmpty(id)){
        	id = model.getPRD_GROUP_NO();
        	params.put("PRD_GROUP_ID", id);
        	params.put("CREATOR", userBean.getXTYHID());
            params.put("CRE_NAME", userBean.getYHM());
            params.put("DEPT_ID", userBean.getBMXXID());
            params.put("DEPT_NAME", userBean.getBMMC());
            params.put("ORG_ID", userBean.getJGXXID());
            params.put("ORG_NAME", userBean.getJGMC());
            params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);//新增的时候 状态是 制定
            params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            txInsert(params);
            
        }else{
        	params.put("PRD_GROUP_ID", id);
            params.put("UPDATOR", userBean.getXTYHID());
            params.put("UPD_NAME", userBean.getYHM());
        	txUpdateById(params);
			clearCaches(model.getPRD_GROUP_ID());
        }
    	
        //子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(id, chldList);
		}
	    
    	return id;
    }

    
    /**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("PrdGroup.updateById", params);
	}
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("PrdGroup.insert", params);
		return true;
	}

    /**
     * 删除记录
     * Description :.
     * 
     * @param id the  id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String id,UserBean userBean){
    	   Map <String, String> params = new HashMap <String, String>();
           params.put("PRD_GROUP_ID", id);
           params.put("UPDATOR", userBean.getXTYHID());
           params.put("UPD_NAME", userBean.getYHM());
           params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
           try{
           	update("PrdGroup.updateStateById", params);
           	update("PrdGroup.updateChldDelMainId", id);
           	clearCaches(id);
           }catch(Exception e){
           	return false;
           }
           return true;
    }
    
    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params){
    	 return update("PrdGroup.updateStateById", params);
    	
    }


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params){
    	 return update("PrdGroup.updateStateById", params);
    }

    
    
    /**
     * 根据主表id查询字表明细
     * 
     * @param pId  主表ID
     * 
     * @return  
     */
    
    public List<PrdGroupDtlModel> childQuery(String pId){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("PRD_GROUP_ID", pId);
         //只查询0的记录。1为删除。0为正常
 		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("PrdGroup.loadChldByMainId", params);
    }
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param params the IDS
     * 
     * @return the list
     */
    public List <PrdGroupDtlModel> loadChilds(Map <String, String> params){
    	 return findList("PrdGroup.loadChldByIds",params);
    }
    
    
	
	 /**
    * * 子表记录编辑：新增/修改
    * @param groupId the 主表ID
    * @param modelList the 子表 list
    * 
    * @return true, if tx child edit
    */
   public boolean txChildEdit(String groupId, List <PrdGroupDtlModel> modelList){
	   
	   //新增列表
       List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
       //修改列表
       List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
       
       for(PrdGroupDtlModel model : modelList){
    	   Map <String, String> params = new HashMap <String, String>();
    	   params.put("PRD_GROUP_ID", groupId);
    	   params.put("PRD_ID", model.getPRD_ID());
    	   params.put("PRD_NO", model.getPRD_NO());
    	   
    	   params.put("PRD_NAME", model.getPRD_NAME());
    	   params.put("PRD_SPEC", model.getPRD_SPEC());
    	   params.put("PRD_COLOR", model.getPRD_COLOR());
    	   params.put("BRAND", model.getBRAND());
    	   params.put("STD_UNIT", model.getSTD_UNIT());
    	   
    	   if(StringUtil.isEmpty(model.getPRD_GROUP_DTL_ID())){
    		   params.put("PRD_GROUP_DTL_ID", StringUtil.uuid32len());
    		   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    		   
    		   addList.add(params);
    	   }else{
    		   params.put("PRD_GROUP_DTL_ID", model.getPRD_GROUP_DTL_ID());
    		   updateList.add(params);
    	   }
          
       }
       
       if (!addList.isEmpty()) {
           this.batch4Update("PrdGroup.insertChld", addList);
       }
	   
	   if (!updateList.isEmpty()) {
           this.batch4Update("PrdGroup.updateChldById", updateList);
       }
       
	   return true;
   }
   
   
   
   /**
    * * 子表的批量删除
    * @param IDs the IDs
    */
   public void txBatch4DeleteChild(String IDs){
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("IDS", IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	   
	   update("PrdGroup.deleteChldByIds", params);
   }
   
   /**
    * 查询
    * @param paramMap
    * @return
    */
   public List findList(Object paramMap){
	   return this.findList("PrdGroup.findRepeat", paramMap);
   }

}
