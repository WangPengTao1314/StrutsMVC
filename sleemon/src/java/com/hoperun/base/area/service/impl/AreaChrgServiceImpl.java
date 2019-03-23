package com.hoperun.base.area.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.area.model.AreaChrgModel;
import com.hoperun.base.area.service.AreaChrgService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

public class AreaChrgServiceImpl extends BaseService implements AreaChrgService {

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
    
    
    /**
     * 根据ID加载详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    @SuppressWarnings("unchecked")
	public Map <String, String> loadById(String param){
    	return (Map) load("AreaChrg.loadById", param);
    }
    
    
    /**
     * 根据区域ID加载 区域分管明细.
     * 
     * @param areaID  区域ID
     * 
     * @return  区域分管明细
     */
    
    @SuppressWarnings("unchecked")
	public List<AreaChrgModel> childQuery(String areaID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("AREA_ID", areaID);
         return this.findList("AreaChrg.query", params);
    }
    
 
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * 
     * @param IDs  字表IDS
     * 
     * @return  
     */
    @SuppressWarnings("unchecked")
	public List<AreaChrgModel> loadChilds(String IDs){
    	   return findList("AreaChrg.loadByIds", IDs);
    }
	
    
    /**
     * 子表记录编辑：新增/修改
     * Description :.
     * 
     * @param areaId 区域信息ID
     * @param  modelList 区域分管明细
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String areaId, List <AreaChrgModel> modelList){
    	 //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        //根据分管编号 查询
        List <Map <String, String>> updateAreaFlatList = new ArrayList <Map <String, String>>();
        int size = modelList.size();
        int i=0;
        StringBuffer bf = new StringBuffer();
        
        for(AreaChrgModel model : modelList){
        	Map <String, String> params = new HashMap <String, String>();
        	params.put("AREA_ID", areaId);
        	params.put("CHRG_TYPE", model.getCHRG_TYPE());
        	params.put("CHRG_NO", model.getCHRG_NO());
        	params.put("CHRG_NAME", model.getCHRG_NAME());
        	params.put("JOB", model.getJOB());
        	
        	//如果没有区域分管ID的则是新增，有的是修改
            if (StringUtils.isEmpty(model.getAREA_CHRG_ID())) {
            	params.put("AREA_CHRG_ID", StringUtil.uuid32len());
            	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            	addList.add(params);
            }else{
            	params.put("AREA_CHRG_ID", model.getAREA_CHRG_ID());
            	updateList.add(params);
            }
            
            if(!StringUtil.isEmpty(model.getCHRG_ID())){
            	Map <String, String> p = new HashMap <String, String>();
            	p.put("CHRG_NO", model.getCHRG_NO());
            	p.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            	updateAreaFlatList.add(p);
            	
            	bf.append("'" + model.getCHRG_ID() + "'");
                 if(i != size){
                 	bf.append(",");
                 }
            }
            
        }
        
        if(!addList.isEmpty()){
        	this.batch4Update("AreaChrg.insert", addList);
        }
        if(!updateList.isEmpty()){
        	this.batch4Update("AreaChrg.updateById", updateList);
        }
        
        //区域分管 更新的时候 更新   区域分管扁平表
        updateAreaFlat(updateAreaFlatList,bf.toString());
        	
        return true;
            
    }
    
    /**
     * 区域分管扁平表
     * 先根据“分管对象ID” 删除所有的信息
     * 在根据“分管对象ID” 查询与之相关的所有信息再插入
     * @param datas 区域
     * @param CHRGIDS 分管对象ID 存用户ID
     */
    @SuppressWarnings("unchecked")
	private void updateAreaFlat(List datas,String CHRGIDS){
        this.delete("Area.deleteareaflat", CHRGIDS);
        this.batch4Update("Area.insertareaflat", datas);
    }
    
    
    /**
     * 子表的批量删除 软删除
     * Description :.
     * 
     * @param  areaChrgIDs 明细IDS
     * @param  
     */
    public void txBatch4DeleteChild(String AREA_ID,String areaChrgIDs,String CHRG_IDS, UserBean userBean){
    	Map<String,String> param = new HashMap<String,String>();
    	param.put("AREA_ID", AREA_ID);
    	param.put("CHRG_IDS", CHRG_IDS);
    	//删除区域扁平表的相关信息
    	this.delete("Area.deleteAreaflatByAreaID", param);
        update("AreaChrg.updateDELByIds", areaChrgIDs);
    }
    
    
    @SuppressWarnings("unchecked")
	public List findList(Object paramMap){
    	return super.findList("AreaChrg.findRepeat", paramMap);
    }
    
    

}
