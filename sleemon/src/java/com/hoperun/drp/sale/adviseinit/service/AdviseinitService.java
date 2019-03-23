/**
 * prjName:喜临门营销平台
 * ucName:投诉与建议
 * fileName:AdviseService
*/
package com.hoperun.drp.sale.adviseinit.service;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.service.IBaseService;
import com.hoperun.drp.sale.adviseinit.model.AdviseinitModel;
import com.hoperun.sys.model.UserBean;
/**
 * *@service
 * *@func
 * *@version 1.1
 * *@author wdb
 * *@createdate 2013-08-17 10:29:35
 */
public interface AdviseinitService extends IBaseService {
	
	/**
	 * 新增产品投诉
	 * @param model
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	public void txSaveProCmpl(AdviseinitModel model, List <AdviseinitModel> chldModelList,UserBean userBean);
	
	/**
	 * 新增服务投诉
	 * @param model
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	public void saveSerCmpl(AdviseinitModel model, String CMPL_ADVS_CONTENT,UserBean userBean);
	
	/**
	 * 新增建议
	 * @param model
	 * @param chldModelList
	 * @param userBean
	 * @return
	 */
	public void saveAdvsCmpl(AdviseinitModel model, String CMPL_ADVS_CONTENT,UserBean userBean);
	
	/**
	 * 区域经理查询
	 * @param AREA_ID
	 * @return
	 */
	public Map<String,String> selectManager(String AREA_ID);
		
	/**
	 * 区域总监查询
	 * @param AREA_ID
	 * @return
	 */
	public Map<String,String> selectChief(String AREA_ID);
	
	public Map<String,String> getCHANNInfo(String CHANN_ID);
}