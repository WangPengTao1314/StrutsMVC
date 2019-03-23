package com.hoperun.drp.store.storeoutfewnotice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeoutfewnotice.model.StoreoutfewnoticeModel;
import com.hoperun.drp.store.storeoutfewnotice.model.StoreoutfewnoticeModelChld;
import com.hoperun.drp.store.storeoutfewnotice.service.StoreoutfewnoticeService;
import com.hoperun.sys.model.UserBean;

public class StoreoutfewnoticeServiceImpl extends BaseService implements StoreoutfewnoticeService{

	@Override
	public List<StoreoutfewnoticeModelChld> childQuery(String FEW_STOREOUT_REQ_ID) {
		Map params = new HashMap();
		params.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Storeoutfewnotice.queryChldByFkId", params);
	}

	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Storeoutfewnotice.loadById", param);
	}

	@Override
	public Map<String, String> loadChann(String CHANN_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoreoutfewnoticeModelChld> loadChilds(Map<String, String> params) {
		return findList("Storeoutfewnotice.loadChldByIds", params);
	}

	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Storeoutfewnotice.pageQuery",
				"Storeoutfewnotice.pageCount", params, pageNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void txBatch4DeleteChild(String FEW_STOREOUT_REQ_DTL_IDS) {
		Map params = new HashMap();
		params.put("FEW_STOREOUT_REQ_DTL_IDS", FEW_STOREOUT_REQ_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Storeoutfewnotice.deleteChldByIds", params);
	}

	@Override
	public boolean txChildEdit(String FEW_STOREOUT_REQ_ID,
			List<StoreoutfewnoticeModelChld> modelList, UserBean userBean) {
		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		for (StoreoutfewnoticeModelChld model : modelList) {
			Map<String,String> map=new HashMap<String, String>();
			map.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
			map.put("PRD_ID", model.getPRD_ID());
			map.put("PRD_NO", model.getPRD_NO());
			map.put("PRD_NAME", model.getPRD_NAME());
			map.put("PRD_SPEC", model.getPRD_SPEC());
			map.put("BRAND", model.getBRAND());
			map.put("STD_UNIT", model.getSTD_UNIT());
			map.put("NOTICE_NUM", model.getNOTICE_NUM());
			map.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			map.put("REMARK", model.getREMARK());
			String FEW_STOREOUT_REQ_DTL_ID=model.getFEW_STOREOUT_REQ_DTL_ID();
			if(StringUtil.isEmpty(FEW_STOREOUT_REQ_DTL_ID)){
				map.put("FEW_STOREOUT_REQ_DTL_ID", StringUtil.uuid32len());
				map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				addList.add(map);
			}else{
				map.put("FEW_STOREOUT_REQ_DTL_ID", FEW_STOREOUT_REQ_DTL_ID);
				updateList.add(map);
			}
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Storeoutfewnotice.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Storeoutfewnotice.insertChld", addList);
		}
		return true;
	}

	@Override
	public boolean txDelete(Map<String, String> params) {
		// TODO Auto-generated method stub
		// 删除父
		update("Storeoutfewnotice.delete", params);
		// 删除子表
		return update("Storeoutfewnotice.delChldByFkId", params);
	}
	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Storeoutfewnotice.updateById", params);
	}
	@Override
	public void txEdit(String FEW_STOREOUT_REQ_ID, StoreoutfewnoticeModel model,
			List<StoreoutfewnoticeModelChld> chldList, UserBean userBean) {
		// TODO Auto-generated method stub
		Map<String,String> map=new HashMap<String, String>();
		map.put("STOREOUT_STORE_ID", model.getSTOREOUT_STORE_ID());
		map.put("STOREOUT_STORE_NO", model.getSTOREOUT_STORE_NO());
		map.put("STOREOUT_STORE_NAME", model.getSTOREOUT_STORE_NAME());
		map.put("BUSS_PSON_ID", model.getBUSS_PSON_ID());
		map.put("BUSS_PSON_NAME", model.getBUSS_PSON_NAME());
		map.put("BUSS_DEPT_ID", model.getBUSS_DEPT_ID());
		map.put("BUSS_DEPT_NAME", model.getBUSS_DEPT_NAME());
		map.put("REMARK", model.getREMARK());
		if(StringUtil.isEmpty(FEW_STOREOUT_REQ_ID)){
			FEW_STOREOUT_REQ_ID=StringUtil.uuid32len();
			map.put("BILL_TYPE", "零星出库");
			map.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
			map.put("FEW_STOREOUT_REQ_NO", LogicUtil.getBmgz("DRP_FEW_STOREOUT_REQ_NO"));
			map.put("STATE", BusinessConsts.UNCOMMIT);
			map.putAll(LogicUtil.sysFild(userBean));
			this.insert("Storeoutfewnotice.insert", map);
		}else{
			map.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
			map.put("UPDATOR", userBean.getRYXXID());
			map.put("UPD_NAME", userBean.getXM());
			map.put("UPD_TIME", "sysdate");
			txUpdateById(map);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(FEW_STOREOUT_REQ_ID, chldList, userBean);
		}
	}
	//提交
	public void txUpdateState(Map<String,String> map,UserBean userBean){
		//更新状态
		this.update("Storeoutfewnotice.updateById", map);
		insertStoreOut(map.get("FEW_STOREOUT_REQ_ID"), userBean);
	}
	public void insertStoreOut(String FEW_STOREOUT_REQ_ID,UserBean userBean){
		Map<String,String> map=new HashMap<String, String>();
		map.put("STOREOUT_ID", StringUtil.uuid32len());
		map.put("STOREOUT_NO", LogicUtil.getBmgz("DRP_STOREOUT_NO"));
		map.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
		map.put("STATE", "未处理");
		map.put("FEW_STOREOUT_REQ_ID", FEW_STOREOUT_REQ_ID);
		map.putAll(LogicUtil.sysFild(userBean));
		this.insert("Storeoutfewnotice.insertStoreOut", map);
		this.insert("Storeoutfewnotice.insertStoreOutChld", map);
	}
}
