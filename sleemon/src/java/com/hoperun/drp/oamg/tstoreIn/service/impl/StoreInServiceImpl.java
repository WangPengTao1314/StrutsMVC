package com.hoperun.drp.oamg.tstoreIn.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.oamg.tstoreIn.service.StoreInService;
import com.hoperun.drp.store.storeinnotice.model.StoreinnoticeModel;
import com.hoperun.drp.store.storeinnotice.model.StoreinnoticeModelChld;
import com.hoperun.sys.model.UserBean;

public class StoreInServiceImpl  extends BaseService implements StoreInService {
   
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
		return this.pageQuery("Storeinnotice.pageQuery",
				"Storeinnotice.pageCount", params, pageNo);
	}
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQueryByStoreIn(Map<String,String> params, int pageNo){
		return this.pageQuery("Storeinnotice.pageQueryByStoreIn",
				"Storeinnotice.pageCountByStoreIn", params, pageNo);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Storeinnotice.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param STOREIN_NOTICE_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		// 删除父
		update("Storeinnotice.delete", params);
		// 删除子表
		return update("Storeinnotice.delChldByFkId", params);
	}

	/**
	 * * update data * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String, String> params) {
		return update("Storeinnotice.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_NOTICE_ID
	 * @param StoreinnoticeModel
	 * @param userBean
	 * @return true, if tx txEdit
	 */
	public void txEdit(String STOREIN_NOTICE_ID, StoreinnoticeModel model,
			List<StoreinnoticeModelChld> chldList, UserBean userBean) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("DEF_STORE_ID", model.getDEF_STORE_ID());
		params.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());
		params.put("SEND_CHANN_ID", model.getSEND_CHANN_ID());
		params.put("FROM_BILL_ID", model.getFROM_BILL_ID());
		params.put("FROM_BILL_NO", model.getFROM_BILL_NO());
		params.put("STOREIN_NOTICE_ID", model.getSTOREIN_NOTICE_ID());
		params.put("BILL_TYPE", model.getBILL_TYPE());
		params.put("SEND_CHANN_NO", model.getSEND_CHANN_NO());
		params.put("SEND_CHANN_NAME", model.getSEND_CHANN_NAME());
		params.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());
		params.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());
		params.put("DEF_STORE_NO", model.getDEF_STORE_NO());
		params.put("DEF_STORE_NAME", model.getDEF_STORE_NAME());
		params.put("FROM_TYPE", model.getFROM_TYPE());
		params.put("STOREIN_TIME", model.getSTOREIN_TIME());
		params.put("REMARK", model.getREMARK());
		try {
		if (StringUtil.isEmpty(STOREIN_NOTICE_ID)) {
			STOREIN_NOTICE_ID = StringUtil.uuid32len();
			params.put("STOREIN_NOTICE_ID", STOREIN_NOTICE_ID);
			params.put("STOREIN_NOTICE_NO", LogicUtil.getBmgz("DRP_STOREIN_NOTICE_NO"));
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
			params.put("DEPT_ID", userBean.getBMXXID());
			params.put("DEPT_NAME", userBean.getBMMC());
			params.put("ORG_ID", userBean.getJGXXID());
			params.put("ORG_NAME", userBean.getJGMC());
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("STATE", BusinessConsts.UNCOMMIT);
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			txInsert(params);
		} else {
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
			params.put("STOREIN_NOTICE_ID", STOREIN_NOTICE_ID);
			txUpdateById(params);
		    txUpStoreinDtlInfo(STOREIN_NOTICE_ID);
			clearCaches(STOREIN_NOTICE_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(STOREIN_NOTICE_ID, chldList,userBean);
		}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	/**
	 * @详细
	 * @param param
	 *            STOREIN_NOTICE_ID
	 * @param param
	 *            the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Storeinnotice.loadById", param);
	}

	/**
	 * * 明细数据编辑
	 * 
	 * @param STOREIN_NOTICE_ID
	 *            the STOREIN_NOTICE_ID
	 * @param modelList
	 *            the model list
	 * 
	 * @return true, if tx child edit
	 */
	@Override
	public boolean txChildEdit(String STOREIN_NOTICE_ID,
			List<StoreinnoticeModelChld> chldList,UserBean userBean) {

		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		Map<String,String> p = new HashMap<String,String>();
		p.put("CHANN_ID", userBean.getCHANN_ID());
		Map channlMap = (Map) load("chann.loadById", p);
		//税率
		Double TAX_RATE  = StringUtil.getDouble(channlMap.get("TAX_RATE"));
		try {
		for (StoreinnoticeModelChld model : chldList) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("STOREIN_STORE_ID", model.getSTOREIN_STORE_ID());
			params.put("STOREIN_STORE_NO", model.getSTOREIN_STORE_NO());
			params.put("STOREIN_STORE_NAME", model.getSTOREIN_STORE_NAME());
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NO", model.getPRD_NO());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("STD_UNIT", model.getSTD_UNIT());
			params.put("PRICE", model.getPRICE());
			params.put("DECT_RATE", model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());//
			params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			params.put("GOODS_ORDER_NO", model.getGOODS_ORDER_NO());
			Double DECT_PRICE  = StringUtil.getDouble(model.getDECT_PRICE());
			Double NOTICE_NUM = StringUtil.getDouble(model.getNOTICE_NUM());
			//不含税折后单价=round(DECT_PRICE/1+税率,2)
			Double NO_TAX_DECT_PRICE = DECT_PRICE/(1+TAX_RATE);
			//不含税折后金额=不含税折后单价*数量
			Double NO_TAX_DECT_AMOUNT = NO_TAX_DECT_PRICE*NOTICE_NUM;
			//税额=折后金额-不含税折后金额
			Double TAX_AMOUNT = (DECT_PRICE-NO_TAX_DECT_PRICE)*NOTICE_NUM;
			
			params.put("TAX_RATE", TAX_RATE+"");
			params.put("NO_TAX_DECT_PRICE", String.format("%.2f", NO_TAX_DECT_PRICE));
			params.put("TAX_AMOUNT", String.format("%.2f", TAX_AMOUNT)); 
			params.put("NO_TAX_DECT_AMOUNT", String.format("%.2f", NO_TAX_DECT_AMOUNT));
			
			params.put("NOTICE_NUM", NOTICE_NUM+"");
			params.put("DECT_AMOUNT", model.getDECT_AMOUNT());
			params.put("STOREIN_NOTICE_ID", STOREIN_NOTICE_ID);
			params.put("REMARK", model.getREMARK());
			// 如果没有明细ID的则是新增，有的是修改
			if (StringUtil.isEmpty(model.getSTOREIN_NOTICE_DTL_ID())) {
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("STOREIN_NOTICE_DTL_ID", StringUtil.uuid32len());
				addList.add(params);
			} else {
				params.put("STOREIN_NOTICE_DTL_ID", model
						.getSTOREIN_NOTICE_DTL_ID());
				updateList.add(params);
			}
		}

		if (!updateList.isEmpty()) {
			this.batch4Update("Storeinnotice.updateChldById", updateList);
			txUpStoreinDtlInfo(STOREIN_NOTICE_ID);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Storeinnotice.insertChld", addList);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * * 根据主表Id查询子表记录
	 * 
	 * @param STOREIN_NOTICE_ID
	 *            the STOREIN_NOTICE_ID
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StoreinnoticeModelChld> childQuery(String STOREIN_NOTICE_ID) {
		Map params = new HashMap();
		params.put("STOREIN_NOTICE_ID", STOREIN_NOTICE_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Storeinnotice.queryChldByFkId", params);
	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param STOREIN_NOTICE_DTL_IDs
	 *            the STOREIN_NOTICE_DTL_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<StoreinnoticeModelChld> loadChilds(Map<String, String> params) {
		return findList("Storeinnotice.loadChldByIds", params);
	}
	
	/**
     * @param params
     * @return
     */
    public List <StoreinnoticeModelChld> loadChildsByModel(Map <String, String> params) {
    	return findList("Storeinnotice.loadChldByIdsByModel", params);
    }

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param STOREIN_NOTICE_DTL_IDs
	 *            the STOREIN_NOTICE_DTL_IDs
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void txBatch4DeleteChild(String STOREIN_NOTICE_DTL_IDs) {
		Map params = new HashMap();
		params.put("STOREIN_NOTICE_DTL_IDS", STOREIN_NOTICE_DTL_IDs);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Storeinnotice.deleteChldByIds", params);
	}

	/*
	 * 跟据ID查询主子表全部信息 (non-Javadoc)
	 * 
	 * @see
	 * com.hoperun.drp.store.storeinnotice.service.StoreinnoticeService#loadFullData
	 * (java.lang.String)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List loadFullData(String storeinNoticeId) {
		// TODO Auto-generated method stub
		return this.findList("Storeinnotice.queryStoreFullData",
				storeinNoticeId);
	}
	
	@SuppressWarnings("unchecked")
	public List loadFullDtlData(String storinNoticeId,String storeinStoreId) {
		// TODO Auto-generated method stub
		Map params = new HashMap();
		params.put("STOREIN_NOTICE_ID", storinNoticeId);
		params.put("STOREIN_STORE_ID", storeinStoreId);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.findList("Storeinnotice.queryStoreFullDtlData",
				params);
	}

	@SuppressWarnings("unchecked")
	public boolean txInsertStoreinData(List<Map> storList,List<Map> storeDtlList) {
		this.batch4Update("Storeinnotice.insertStoreData", storList);
		this.batch4Update("Storeinnotice.insertStoreDtlData", storeDtlList);
		return true;
	}
   
   /**
     * @return
     */
    public String  getStoreInCount(String RECV_CHANN_NO){
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("CHANN_NO", RECV_CHANN_NO);
        return (String) load("chann.getStoreInCount", params);	
    }
    
    /**
	 * 修改总部/渠道修改入库通知单时，如果已生成入库单处理，则统一数据
	 * @param STOREIN_NOTICE_ID
	 */
	public void txUpStoreinDtlInfo(String STOREIN_NOTICE_ID) throws Exception{
		String STATE=String.valueOf(this.load("Storeinnotice.getStoreinStateById",STOREIN_NOTICE_ID));
		//如果入库处理为已处理，说明已入库，则不能修改
		if(BusinessConsts.DONE.equals(STATE)){
			throw new Exception("对不起，您所选择的入库通知单已入库，不能修改 !");
		}
		//更新入库处理单订货数量与金额
		 this.update("Storeinnotice.upStoreinDtlInfo", STOREIN_NOTICE_ID);
	}
}
