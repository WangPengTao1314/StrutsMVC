/**
 *@module 系统管理
 *@fuc 单位维护实现
 *@version 1.0
 *@author 王栋斌
 */

package com.hoperun.base.unit.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.unit.model.UnitModel;
import com.hoperun.base.unit.service.UnitService;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

public class UnitServiceImpl extends BaseService implements UnitService {

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
		
		return this.pageQuery("UNIT.pageQuery", "UNIT.pageCount", params,pageNo);
	}

	/**
	 * 查询详细信息 Description :.
	 * 
	 * @param MEAS_UNIT_ID
	 *            the MEAS_UNIT id
	 * 
	 * @return the map< string, string>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String MEAS_UNIT_ID) {
		return (Map<String, String>) load("UNIT.loadById", MEAS_UNIT_ID);
	}

	/**
	 * 编辑.
	 * 
	 * @param jgxxId
	 *            the jgxx id
	 * @param jgxxModel
	 *            the jgxx model
	 * @param userBean
	 *            the user bean
	 * 
	 * @return the String
	 */
	@Override
	public String txEdit(String MEAS_UNIT_ID, UnitModel unitModel,
			UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		
		params.put("MEAS_UNIT_NO", unitModel.getMEAS_UNIT_NO());
		params.put("MEAS_UNIT_NAME", unitModel.getMEAS_UNIT_NAME());
		params.put("UNIT_NAME_EN", unitModel.getUNIT_NAME_EN());
		params.put("UNIT_TYPE", unitModel.getUNIT_TYPE());
		params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);

		params.put("CREATOR", userBean.getXTYHID());
		params.put("CRE_NAME", userBean.getYHM());
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
		params.put("DEPT_ID", userBean.getBMBH());
		params.put("DEPT_NAME", userBean.getBMMC());
		params.put("ORG_ID", userBean.getJGXXID());
		params.put("ORG_NAME", userBean.getJGMC());

		if (StringUtils.isEmpty(MEAS_UNIT_ID)) {
			params.put("MEAS_UNIT_ID", StringUtil.uuid32len());
			this.insert("UNIT.insert", params);
		} else {
			params.put("MEAS_UNIT_ID", MEAS_UNIT_ID);
			clearCaches(MEAS_UNIT_ID);
			this.update("UNIT.updateById", params);
		}
		return MEAS_UNIT_ID;
	}

	/**
	 * 单条记录删除 Description :.
	 * 
	 * @param sjzdId
	 *            the sjzd id
	 * @param userBean
	 *            the user bean
	 * 
	 * @return true, if tx delete
	 */
	@Override
	public boolean txDelete(String MEAS_UNIT_ID,UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("MEAS_UNIT_ID", MEAS_UNIT_ID);
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
		return this.delete("UNIT.delete", params);
	}

	/**
	 * 状态更改 Description:
	 * 
	 * @param MEAS_UNIT_ID
	 * @param STATE
	 * 
	 * @return boolean
	 */
	@Override
	public boolean updateState(String MEAS_UNIT_ID, String STATE,UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("MEAS_UNIT_ID", MEAS_UNIT_ID);
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());
		params.put("STATE", STATE);
		return this.update("UNIT.updateState", params);
	}
}
