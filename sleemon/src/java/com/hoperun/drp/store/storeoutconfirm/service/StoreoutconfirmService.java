
package com.hoperun.drp.store.storeoutconfirm.service;

import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.store.storeout.model.StoreoutModel;
import com.hoperun.drp.store.storeout.model.StoreoutModelChld;
import com.hoperun.drp.store.storeoutconfirm.model.StoreoutconfirmModelChld;
import com.hoperun.drp.store.storeoutconfirm.model.StoreoutconfirmModelGchld;
import com.hoperun.sys.model.UserBean;

public interface StoreoutconfirmService extends IBaseService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	/**
	 * @主表详细页面
	 * @param param STOREOUT_ID
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String STOREOUT_ID);
	/**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * @return the list
     */
    public List <StoreoutconfirmModelChld> childQuery(String STOREOUT_ID);
    /**
     * * 根据子表Id查询孙表记录
     * @param STOREOUT_DTL_ID the STOREOUT_DTL_ID
     * 
     * @return the list< new master slavemx model>
     */
    public List <StoreoutconfirmModelGchld> GchildQuery(Map <String, String> params);
    /**
     * * 子表记录编辑：新增/修改
     * @param STORE_ID the STORE_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    public boolean txChildEdit(List <StoreoutconfirmModelChld> modelList,Map<String,String> map,String STOREOUT_ID,UserBean userBean);
}
