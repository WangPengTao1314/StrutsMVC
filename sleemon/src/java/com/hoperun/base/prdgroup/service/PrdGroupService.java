package com.hoperun.base.prdgroup.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.prdgroup.model.PrdGroupDtlModel;
import com.hoperun.base.prdgroup.model.PrdGroupModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

public interface PrdGroupService extends IBaseService{
	
	
	/**
     * 查询并分页.
     * 
     * @param params the params
     * @param pageNo the page no
     * 
     * @return the i list page
     */
    public IListPage pageQuery(Map <String, String> params, int pageNo);
    
    /**
     * 显示树.
     * 
     * @return the tree
     * 
     * @throws Exception the exception
     */
    public List <PrdGroupModel> getTree() throws Exception;
    
    
    /**
     * 详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> load(String param);
    
    
    /**
     * 判断是否存在重复.
     * 
     * @param params the params
     * 
     * @return true ->没有重复
     */
    public boolean queryForInt(Map <String, String> params);




    
    /**
     * 编辑.
     * 
     * @param model the model
     * @param userBean the user bean
     * @param modelList 子表
     * @return the string
     */
    public String txEdit(PrdGroupModel model, UserBean userBean,List <PrdGroupDtlModel> modelList);


    /**
     * 删除记录
     * Description :.
     * 
     * @param id the  id
     * @param userBean the user bean
     * 
     * @return true, if tx delete
     */
    public boolean txDelete(String id,UserBean userBean);
    
    /**
     * 修改状态为停用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx stop by id
     */
    public boolean txStopById(Map <String, String> params);


    /**
     * 修改状态为启用
     * Description :.
     * 
     * @param params the params
     * 
     * @return true, if tx start by id
     */
    public boolean txStartById(Map <String, String> params);

    
    
    /**
     * 根据主表id查询字表明细
     * 
     * @param pId  主表ID
     * 
     * @return  
     */
    
    public List<PrdGroupDtlModel> childQuery(String pId);
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param params 子表IDS
     * 
     * @return the list
     */
    public List <PrdGroupDtlModel> loadChilds(Map <String, String> params) ;
    
    
	
	 /**
    * * 子表记录编辑：新增/修改
    * @param groupId 主表ID
    * @param modelList the 子表 list
    * 
    * @return true, if tx child edit
    */
   public boolean txChildEdit(String groupId, List <PrdGroupDtlModel> modelList);
   
   
   
   /**
    * * 子表的批量删除
    * @param IDs the IDs
    */
   public void txBatch4DeleteChild(String IDs);
   
   /**
    * 查询
    * @param paramMap
    * @return
    */
   public List findList(Object paramMap);
   
}
