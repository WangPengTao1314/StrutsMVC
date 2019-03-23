package com.hoperun.sys.service;

import java.util.Map;

import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.IBaseService;
import com.hoperun.sys.model.NumformatModel;
import com.hoperun.sys.model.UserBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface NumformatService.
 */
public interface NumformatService extends IBaseService {

	/**
	 * 列表页面信息.
	 * 
	 * @param params the params
	 * @param pageNo the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo);
	
	/**
	 * 详细页面信息.
	 * 
	 * @param numid the numid
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String numid);
	
	/**
	 * 编辑.
	 * 
	 * @param NUMFORMATID the nUMFORMATID
	 * @param model the model
	 * @param userBean the user bean
	 */
	public void txEdit(String NUMFORMATID,NumformatModel model, UserBean userBean);
	
	/**
	 * Chenagestate.
	 * 
	 * @param params the params
	 */
	public void chenagestate(Map<String,String> params);
	
	/**
	 * Delete one r.
	 * 
	 * @param NUMFORMATID the nUMFORMATID
	 */
	public void deleteOneR(String NUMFORMATID);
}
