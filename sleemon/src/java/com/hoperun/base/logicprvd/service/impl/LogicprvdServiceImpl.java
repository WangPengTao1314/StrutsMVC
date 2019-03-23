/**
 * 项目名称：平台
 * 系统名：基础数据
 * 文件名：JGXXServiceImpl.java
 */
package com.hoperun.base.logicprvd.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.base.logicprvd.model.LogicprvdModel;
import com.hoperun.base.logicprvd.service.LogicprvdService;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.sys.model.UserBean;

/**
 * The Class NewSingleTableServiceImpl.
 * 
 * @module 系统管理
 * @func 物流供应商管理
 * @version 1.0
 * @author 王栋斌
 */
public class LogicprvdServiceImpl extends BaseService implements
		LogicprvdService {

	/**
	 * 查询并分页.
	 * 
	 * @param params
	 *            the params
	 * @param pageNo
	 *            the page no
	 * 
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String, String> params, int pageNo) {

		return this.pageQuery("LOGICPRVD.pageQuery", "LOGICPRVD.pageCount",
				params, pageNo);
	}

	/**
	 * 增加记录 Description :.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {

		insert("LOGICPRVD.insert", params);
		return true;
	}

	/**
	 * 更新记录 Description :.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {

		return update("LOGICPRVD.updateById", params);
	}

	/**
	 * 软删除.
	 * 
	 * @param jgxxId
	 *            the jgxx id
	 * @param userBean
	 *            the user bean
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		this.delete("LOGICPRVD.truckDeleteByFkId", params);
		return delete("LOGICPRVD.delete", params);
	}

	/**
	 * 加载.
	 * 
	 * @param jgxxId
	 *            the jgxx id
	 * 
	 * @return the map
	 */
	@SuppressWarnings("unchecked")
	public Map load(String jgxxId) {

		return (Map) load("LOGICPRVD.loadById", jgxxId);
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
	 * @return the string
	 */
	@Override
	public Boolean txEdit(String prvd_id, LogicprvdModel logicprvdModel,
			UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("PRVD_ID", logicprvdModel.getPRVD_ID());
		params.put("PRVD_NO", logicprvdModel.getPRVD_NO());
		params.put("PRVD_NAME", logicprvdModel.getPRVD_NAME());
		params.put("PRVD_TYPE", logicprvdModel.getPRVD_TYPE());
		params.put("PRVD_LVL", logicprvdModel.getPRVD_LVL());
		params.put("PRVD_NATRUE", logicprvdModel.getPRVD_NATRUE());
		params.put("NATION", logicprvdModel.getNATION());
		params.put("PROV", logicprvdModel.getPROV());
		params.put("CITY", logicprvdModel.getCITY());
		params.put("COUNTY", logicprvdModel.getCOUNTY());
		params.put("PRVD_CY", logicprvdModel.getPRVD_CY());
		params.put("PRVD_CAP", logicprvdModel.getPRVD_CAP());
		params.put("PERSON_BUSS", logicprvdModel.getPERSON_BUSS());
		params.put("PERSON_CON", logicprvdModel.getPERSON_CON());
		params.put("TEL", logicprvdModel.getTEL());
		params.put("MOBILE", logicprvdModel.getMOBILE());
		params.put("TAX", logicprvdModel.getTAX());
		params.put("POST", logicprvdModel.getPOST());
		params.put("EMAIL", logicprvdModel.getEMAIL());
		params.put("WEB", logicprvdModel.getWEB());
		params.put("LEGAL_REPRE", logicprvdModel.getLEGAL_REPRE());
		params.put("BUSS_LIC", logicprvdModel.getBUSS_LIC());
		params.put("INVOICE_TI", logicprvdModel.getINVOICE_TI());
		params.put("INVOICE_ADDR", logicprvdModel.getINVOICE_ADDR());
		params.put("BANK_NAME", logicprvdModel.getBANK_NAME());
		params.put("BANK_ACCT", logicprvdModel.getBANK_ACCT());
		params.put("FI_CMP_NO", logicprvdModel.getFI_CMP_NO());
		params.put("ADDRESS", logicprvdModel.getADDRESS());
		params.put("REMARK", logicprvdModel.getREMARK());

		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_NAME", userBean.getYHM());

		// 新增供应商
		if (StringUtils.isEmpty(prvd_id)) {
			if (0 == this.queryForInt("LOGICPRVD.logicprvdCheck", params)){
				prvd_id = StringUtil.uuid32len();
				params.put("PRVD_ID", prvd_id);
				params.put("STATE", "制定");
				params.put("CREATOR", userBean.getXTYHID());
				params.put("CRE_NAME", userBean.getXM());

				params.put("UPDATOR", userBean.getXTYHID());
				params.put("UPD_NAME", userBean.getXM());
				params.put("DEPT_ID", userBean.getBMXXID());
				params.put("DEPT_NAME", userBean.getBMMC());
				params.put("ORG_ID", userBean.getJGXXID());
				params.put("ORG_NAME", userBean.getJGMC());
				params.put("LEDGER_ID", userBean.getLoginZTXXID());
				params.put("LEDGER_NAME", userBean.getLoginZTMC());
				txInsert(params);
			} else {
				return false;
			}
		} else {
			params.put("PRVD_ID", prvd_id);
			txUpdateById(params);
			clearCaches(prvd_id);
		}
		return true;
	}

	/**
	 * 修改状态为停用 Description :.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx stop by id
	 */
	public boolean txStopById(Map<String, String> params) {

		return update("LOGICPRVD.updStusById", params);
	}

	/**
	 * 修改状态为启用 Description :.
	 * 
	 * @param params
	 *            the params
	 * 
	 * @return true, if tx start by id
	 */
	public boolean txStartById(Map<String, String> params) {
		return update("LOGICPRVD.updStusById", params);
	}

	/**
	 * 车辆信息列表页面初始化 Description :.
	 * 
	 * @param PRVD_ID
	 * 
	 * @return List<LogicprvdModel>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LogicprvdModel> truckList(String PRVD_ID) {
		return this.findList("LOGICPRVD.truckQuery", PRVD_ID);
	}

	/**
	 * 车辆信息记录编辑页面跳转
	 * 
	 * @param TRUCK_ID
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LogicprvdModel> toEditTruck(String TRUCK_ID) {
		return this.findList("LOGICPRVD.truckEdit", TRUCK_ID);
	}

	/**
	 * 车辆信息记录删除
	 * 
	 * @param TRUCK_ID
	 * @return boolean
	 */
	@Override
	public boolean truckDelete(String TRUCK_ID) {
		return this.delete("LOGICPRVD.truckDelete", TRUCK_ID);
	}

	/**
	 * 车辆信息状态更改
	 * 
	 * @param params
	 * @return boolean
	 */
	@Override
	public boolean changeTruckState(Map<String, String> params) {
		return this.update("LOGICPRVD.changeTruckState", params);
	}

	/**
	 * 车辆信息修改/新增
	 * 
	 * @param logicprvdModel
	 * @return boolean
	 */
	@Override
	public void truckSave(List<LogicprvdModel> logicprvdModel, String PRVD_ID) {
		for (int i = 0; i < logicprvdModel.size(); i++) {
			if (StringUtil.isEmpty(logicprvdModel.get(i).getTRUCK_ID())) {
				logicprvdModel.get(i).setTRUCK_ID(StringUtil.uuid32len());
				logicprvdModel.get(i).setPRVD_ID(PRVD_ID);
				logicprvdModel.get(i).setSTATE(BusinessConsts.JC_STATE_DEFAULT);
				logicprvdModel.get(i).setDELFLAG(BusinessConsts.DEL_FLAG_COMMON);
				this.insert("LOGICPRVD.insertTruck", logicprvdModel.get(i));
			} else {
				this.update("LOGICPRVD.updateTruck", logicprvdModel.get(i));
			}
		}
	}

	/**
     * 合并路线一览
     * @param PRVD_ID
     * @return List<LogicprvdModel> 
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<LogicprvdModel> waymergeList(String PRVD_ID) {
		return this.findList("LOGICPRVD.waymergeQuery", PRVD_ID);
	}

	/**
	 * 合并路线记录编辑页面跳转
	 * 
	 * @param TRUCK_ID
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LogicprvdModel> toEditWaymerge(String WAY_MERGE_ID) {
		return this.findList("LOGICPRVD.waymergeEdit", WAY_MERGE_ID);
	}

	/**
	 * 合并路线记录删除
	 * 
	 * @param TRUCK_ID
	 * @return boolean
	 */
	@Override
	public boolean waymergeDelete(String WAY_MERGE_ID) {
		return this.delete("LOGICPRVD.waymergeDelete", WAY_MERGE_ID);
	}

	/**
	 * 合并路线状态更改
	 * 
	 * @param params
	 * @return boolean
	 */
	@Override
	public boolean changeWaymergeState(Map<String, String> params) {
		return this.update("LOGICPRVD.changeWaymergeState", params);
	}
	
	/**
	 * 合并路线修改/新增
	 * 
	 * @param logicprvdModel
	 * @return boolean
	 */
	@Override
	public Boolean waymergeSave(List<LogicprvdModel> logicprvdModel, String PRVD_ID) {
		for (int i = 0; i < logicprvdModel.size(); i++) {
			if (StringUtil.isEmpty(logicprvdModel.get(i).getWAY_MERGE_ID())) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("WAY_MERGE_NO", logicprvdModel.get(i).getWAY_MERGE_NO());
				params.put("PRVD_ID", PRVD_ID);
				int flag=this.queryForInt("LOGICPRVD.waymergeCheck", params);
				if (0 == flag){
					logicprvdModel.get(i).setPRVD_ID(PRVD_ID);
					logicprvdModel.get(i).setWAY_MERGE_ID(StringUtil.uuid32len());
					logicprvdModel.get(i).setSTATE(BusinessConsts.JC_STATE_DEFAULT);
					logicprvdModel.get(i).setDELFLAG(BusinessConsts.DEL_FLAG_COMMON);
					this.insert("LOGICPRVD.insertWaymerge", logicprvdModel.get(i));
				} else {
					throw new ServiceException("编号重复!");
				}
			} else {
				this.update("LOGICPRVD.updateWaymerge", logicprvdModel.get(i));
			}
		}
		return true;
	}

	/**
	 * 合并路线明细页面跳转
	 * 
	 * @param TRUCK_ID
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LogicprvdModel> toEditWaymergedtl(String WAY_MERGE_ID) {
		return this.findList("LOGICPRVD.waymergeEditdtl", WAY_MERGE_ID);
	}

	/**
     * 合并路线明细记录删除
     * @param TRUCK_ID
     * @return boolean
     */
	@Override
	public boolean waymergedtlDelete(String WAY_MERGE_DTL_ID) {
		return this.delete("LOGICPRVD.waymergedtlDelete", WAY_MERGE_DTL_ID);
	}

	/**
     * 合并路线明细修改/新增
     * @param logicprvdModel
     * @return void
     */
	@Override
	public void waymergedtlSave(List<LogicprvdModel> logicprvdModel, String WAY_MERGE_ID) {
		for (int i = 0; i < logicprvdModel.size(); i++) {
			if (StringUtil.isEmpty(logicprvdModel.get(i).getWAY_MERGE_DTL_ID())) {
				logicprvdModel.get(i).setWAY_MERGE_DTL_ID(StringUtil.uuid32len());
				logicprvdModel.get(i).setWAY_MERGE_ID(WAY_MERGE_ID);
				logicprvdModel.get(i).setDELFLAG(BusinessConsts.DEL_FLAG_COMMON);
				this.insert("LOGICPRVD.insertWaymergedtl", logicprvdModel.get(i));
			} else {
				this.update("LOGICPRVD.updateWaymergedtl", logicprvdModel.get(i));
			}
		}
	}
}
