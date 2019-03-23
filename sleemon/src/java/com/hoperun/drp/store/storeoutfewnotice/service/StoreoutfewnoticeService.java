package com.hoperun.drp.store.storeoutfewnotice.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.storeoutfewnotice.model.StoreoutfewnoticeModel;
import com.hoperun.drp.store.storeoutfewnotice.model.StoreoutfewnoticeModelChld;
import com.hoperun.sys.model.UserBean;

public interface StoreoutfewnoticeService extends IBaseService{
	   /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
    /**
	 * @主从保存编辑
	 * @Description :
	 * @param STOREOUT_ID
	 * @param StoreoutconfirmModel
	 * @param userBean.
	 *
	 * @return 
	 */
	public void txEdit(String FEW_STOREOUT_REQ_ID, StoreoutfewnoticeModel model,List<StoreoutfewnoticeModelChld> chldList, UserBean userBean);
	/**
	 * @主表删除
	 * @param Map
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params);
	
	/**
	 * @主表详细页面
	 * @param param STOREOUT_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREOUT_ID);
	
	
	
	/**
	 * 加载渠道
	 * @param CHANN_ID
	 * @return
	 */
	public Map<String,String> loadChann(String CHANN_ID);
	
	 /**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * @return the list
     */
    public List <StoreoutfewnoticeModelChld> childQuery(String STOREOUT_ID);
    
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     * 
     * @return the list
     */
    public List <StoreoutfewnoticeModelChld> loadChilds(Map <String, String> params) ;
	
	 /**
     * * 子表记录编辑：新增/修改
     * @param FEW_STOREOUT_REQ_ID the FEW_STOREOUT_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(String FEW_STOREOUT_REQ_ID, List <StoreoutfewnoticeModelChld> modelList,UserBean userBean);
	 /**
     * * 子表的批量删除
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    public void txBatch4DeleteChild(String STOREOUT_DTL_IDs);
    public void txUpdateState(Map<String,String> map,UserBean userBean);
    
}
