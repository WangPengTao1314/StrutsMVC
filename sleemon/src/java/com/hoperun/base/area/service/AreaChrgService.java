package com.hoperun.base.area.service;

import java.util.List;
import java.util.Map;

import com.hoperun.base.area.model.AreaChrgModel;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.UserBean;

/**
 * 区域分管
 * @author zhang_zhongbin
 *
 */
public interface AreaChrgService extends IBaseService {
	
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
     * 根据ID加载详细信息.
     * 
     * @param param the param
     * 
     * @return the map< string, string>
     */
    public Map <String, String> loadById(String param);
    
    
    
    /**
     * 根据区域ID加载 区域分管明细.
     * 
     * @param areaID  区域ID
     * 
     * @return  区域分管明细
     */
    
    public List<AreaChrgModel> childQuery(String areaID);
    
    
    
    /**
     * 根据子表Id批量加载子表信息
     * Description :.
     * 
     * @param IDs  字表IDS
     * 
     * @return  
     */
    public List<AreaChrgModel> loadChilds(String IDs);
    
    
    /**
     * 子表记录编辑：新增/修改
     * Description :.
     * 
     * @param areaId 区域信息ID
     * @param  modelList 区域分管明细
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String areaId, List <AreaChrgModel> modelList);
    
    
    /**
     * 子表的批量删除 软删除
     * Description :.
     * @param  AREA_ID 区域ID
     * @param  areaChrgIDs 明细IDS
     * @param  CHRG_IDS 区域分管人员IDS
     */
    public void txBatch4DeleteChild(String AREA_ID,String areaChrgIDs,String CHRG_IDS, UserBean userBean);
    
    
    public List findList(Object paramMap);
	

}
