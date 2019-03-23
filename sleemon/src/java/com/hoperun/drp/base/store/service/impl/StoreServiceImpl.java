/**
 * prjName:喜临门营销平台
 * ucName:库房库位信息
 * fileName:StoreServiceImpl
 */
package com.hoperun.drp.base.store.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.base.store.model.StoreModel;
import com.hoperun.drp.base.store.model.StoreModelChld;
import com.hoperun.drp.base.store.service.StoreService;
import com.hoperun.sys.model.UserBean;

/**
 * *@serviceImpl *@func *@version 1.1 *@author wdb *@createdate 2013-08-13
 * 14:01:22
 */
public class StoreServiceImpl extends BaseService implements StoreService {
	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * 
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Store.pageQuery", "Store.pageCount", params,
				pageNo);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Store.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param STORE_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		// 删除父
		update("Store.delete", params);
		// 删除子表
		return update("Store.delChldByFkId", params);
	}

	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Store.updateById", params);
	}

	/**
	 * @编辑
	 * @Description :
	 * @param STORE_ID
	 * @param StoreModel
	 * @param userBean
	 *            .
	 * 
	 * @return true, if tx txEdit
	 */
	public void txEdit(String STORE_ID, StoreModel model,
			List<StoreModelChld> chldList, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		
		params.put("STORE_TYPE", model.getSTORE_TYPE());
		params.put("STORE_NAME", model.getSTORE_NAME());
		params.put("BEL_UNIT_NAME", model.getBEL_UNIT_NAME());
		params.put("REMARK", model.getREMARK());
		params.put("BEL_UNIT_ID", model.getBEL_UNIT_ID());
		params.put("TERM_STROE_FLAG", model.getTERM_STROE_FLAG());
		params.put("PAR_STORE_ID", model.getPAR_STORE_ID());
		params.put("BEL_CHANN_NO", model.getBEL_CHANN_NO());
		params.put("DTL_ADDR", model.getDTL_ADDR());
		params.put("TOTAL_VOLUME", model.getTOTAL_VOLUME());//库房总容积
		if (StringUtil.isEmpty(STORE_ID)) {
			String STORE_NO=model.getSTORE_NO();
			params.put("STORE_NO", STORE_NO);
			STORE_ID = StringUtil.uuid32len();
			params.put("STORE_ID", STORE_ID);
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEPT_ID", userBean.getBMXXID());
			params.put("DEPT_NAME", userBean.getBMMC());
			params.put("ORG_ID", userBean.getJGXXID());
			params.put("ORG_NAME", userBean.getJGMC());
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			params.put("FREEZE_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
			params.put("STORAGE_FLAG", "0");
			txInsert(params);
		} else {
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
			params.put("STORE_ID", STORE_ID);
			txUpdateById(params);
			clearCaches(STORE_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(STORE_ID, chldList);
		}
	}

	/**
	 * @详细
	 * @param param
	 *            STORE_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Store.loadById", param);
	}

	/**
	 * * 明细数据编辑
	 * 
	 * @param STORE_ID
	 *            the STORE_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx child edit
	 */
	@Override
	public boolean txChildEdit(String STORE_ID, List<StoreModelChld> chldList) {

		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		for (StoreModelChld model : chldList) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("STORG_NAME", model.getSTORG_NAME());
			params.put("FLOOR", model.getFLOOR());
			params.put("LAY_ADDR", model.getLAY_ADDR());
			params.put("STORE_ID", STORE_ID);
			// 如果没有明细ID的则是新增，有的是修改
			if (StringUtil.isEmpty(model.getSTORG_ID())) {
				params.put("STORG_ID", StringUtil.uuid32len());
				params.put("STORG_NO", model.getSTORG_NO());
				params.put("STATE", BusinessConsts.JC_STATE_DEFAULT);
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				if (0 != this.queryForInt("Store.childCheck", params)) {
					return false;
				}
				addList.add(params);
			} else {
				params.put("STORG_ID", model.getSTORG_ID());
				updateList.add(params);
			}
		}

		if (!updateList.isEmpty()) {
			this.batch4Update("Store.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Store.insertChld", addList);
		}
		return true;
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param STORE_ID
	 *            the STORE_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StoreModelChld> childQuery(String STORE_ID) {
		Map params = new HashMap();
		params.put("STORE_ID", STORE_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Store.queryChldByFkId", params);
	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param STORG_IDs
	 *            the STORG_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StoreModelChld> loadChilds(Map<String, String> params) {
		return findList("Store.loadChldByIds", params);
	}

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param STORG_IDs
	 *            the STORG_IDs
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void txBatch4DeleteChild(String STORG_IDs) {
		Map params = new HashMap();
		params.put("STORG_IDS", STORG_IDs);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Store.deleteChldByIds", params);
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

		return update("Store.updStusById", params);
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
		return update("Store.updStusById", params);
	}

	/**
	 * 子表状态更改
	 * 
	 * @param params
	 * @return boolean
	 */
	@Override
	public boolean changeChildState(Map<String, String> params) {
		return this.update("Store.changeChildState", params);
	}
	/**
	 * 根据库房编号查询是否有重复数据
	 */
	public boolean queryRepeatNo(String NO){
		Object obj=this.load("Store.queryRepeatNo", NO);
		if(Integer.parseInt(obj.toString())>0){
			return false;
		}else{
			return true;
		}
	}
	public Integer getStoreFreezeFlag(String STORE_ID){
		return StringUtil.getInteger(this.load("Store.getStoreFreezeFlag", STORE_ID));
	}
}