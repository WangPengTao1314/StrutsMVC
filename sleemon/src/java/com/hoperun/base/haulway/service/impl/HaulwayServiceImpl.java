/**
 *@module 系统管理
 *@fuc 单位维护实现
 *@version 1.0
 *@author 王栋斌
 */

package com.hoperun.base.haulway.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.haulway.model.HaulwayModel;
import com.hoperun.base.haulway.service.HaulwayService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

public class HaulwayServiceImpl extends BaseService implements HaulwayService {

	/**
	 * 分页查询 Description :.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		
		return this.pageQuery("HAULWAY.pageQuery", "HAULWAY.pageCount", params,pageNo);
	}

	/**
	 * 查询详细信息 Description :.
	 * 
	 * @param HAULWAY_ID
	 *            the HAULWAY id
	 * 
	 * @return the map< string, string>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String HAULWAY_ID) {
		return (Map<String, String>) load("HAULWAY.loadById", HAULWAY_ID);
	}

	/**
	 * 编辑.
	 * 
	 * @param HAULWAY_ID
	 *            the HAULWAY id
	 * @param haulwayModel
	 *            the haulway model
	 * @param userBean
	 *            the user bean
	 * 
	 * @return the String
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Boolean txEdit(String HAULWAY_ID, HaulwayModel haulwayModel,
			UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("HAULWAY_NO", haulwayModel.getHAULWAY_NO());
		params.put("HAULWAY_NAME", haulwayModel.getHAULWAY_NAME());
		params.put("DELV_CITY", haulwayModel.getDELV_CITY());
		params.put("SHIP_POIT_ID", haulwayModel.getSHIP_POIT_ID());
		params.put("SHIP_POIT_NO", haulwayModel.getSHIP_POIT_NO());
		params.put("SHIP_POIT_NAME", haulwayModel.getSHIP_POIT_NAME());
		params.put("ARRV_CITY", haulwayModel.getARRV_CITY());
		params.put("CHANN_ID", haulwayModel.getCHANN_ID());
		params.put("CHANN_NO", haulwayModel.getCHANN_NO());
		params.put("CHANN_NAME", haulwayModel.getCHANN_NAME());
		params.put("LENGTH", haulwayModel.getLENGTH());
		params.put("DAYS", haulwayModel.getDAYS());
		params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
		params.put("REMARK", haulwayModel.getREMARK());
			
		params.put("CREATOR", userBean.getXTYHID());
		params.put("CRE_NAME", userBean.getYHM());
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
		params.put("DEPT_ID", userBean.getBMBH());
		params.put("DEPT_NAME", userBean.getBMMC());
		params.put("ORG_ID", userBean.getJGXXID());
		params.put("ORG_NAME", userBean.getJGMC());
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);

		if (StringUtils.isEmpty(HAULWAY_ID)) {
			if (0 == this.queryForInt("HAULWAY.check", params)){
				params.put("HAULWAY_ID", StringUtil.uuid32len());
				this.insert("HAULWAY.insert", params);
			} else {
				return false; 
			}
		} else {
			params.put("HAULWAY_ID", HAULWAY_ID);
			clearCaches(HAULWAY_ID);
			this.update("HAULWAY.updateById", params);
		}
		return true;
	}

	/**
	 * 单条记录删除 Description :.
	 * 
	 * @param HAULWAY_ID
	 *            the HAULWAY id
	 * @param userBean
	 *            the user bean
	 * 
	 * @return true, if delete
	 */
	@Override
	public boolean txDelete(String HAULWAY_ID,UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("HAULWAY_ID", HAULWAY_ID);
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		return this.delete("HAULWAY.delete", params);
	}

	/**
	 * 状态更改 Description:
	 * 
	 * @param HAULWAY_ID
	 * @param STATE
	 * 
	 * @return boolean
	 */
	@Override
	public boolean updateState(String HAULWAY_ID, String STATE,UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("HAULWAY_ID", HAULWAY_ID);
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
		params.put("STATE", STATE);
		return this.update("HAULWAY.updateState", params);
	}
}
