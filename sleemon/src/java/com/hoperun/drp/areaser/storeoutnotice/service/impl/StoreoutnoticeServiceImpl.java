package com.hoperun.drp.areaser.storeoutnotice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.areaser.storeoutnotice.model.StoreoutnoticeChldModel;
import com.hoperun.drp.areaser.storeoutnotice.model.StoreoutnoticeModel;
import com.hoperun.drp.areaser.storeoutnotice.service.StoreoutnoticeService;
import com.hoperun.sys.model.UserBean;

public class StoreoutnoticeServiceImpl extends BaseService implements StoreoutnoticeService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Storeoutnotice.pageQuery", "Storeoutnotice.pageCount",params, pageNo);
	}
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Storeoutnotice.insert", params);
		return true;
	}
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Storeoutnotice.updateById", params);
	}
	
	/**
     * * 根据主表Id查询子表记录
     * @param PRD_INV_ID the STOREOUT_NOTICE_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <StoreoutnoticeChldModel> childQuery(String STOREOUT_NOTICE_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeoutnotice.queryChldByFkId", params);
    }
    /**
	 * @详细
	 * @param param PRD_INV_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Storeoutnotice.loadById", param);
	}
	
	 /**
     * * 根据子表Id批量加载子表信息
     * @param PRD_INV_DTL_IDs the PRD_INV_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <StoreoutnoticeChldModel> loadChilds(Map <String, String> params) {
       return findList("Storeoutnotice.loadChldByIds",params);
    }
    
    /**
	 * @编辑
	 * @Description :
	 * @param PRD_INV_ID
	 * @param InventoryModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREOUT_NOTICE_ID, StoreoutnoticeModel model,List<StoreoutnoticeChldModel> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("FROM_BILL_ID", model.getFROM_BILL_ID());
		params.put("FROM_BILL_NO", model.getFROM_BILL_NO());
		params.put("SEND_CHANN_ID", model.getSEND_CHANN_ID());
		params.put("SEND_CHANN_NO", model.getSEND_CHANN_NO());
		params.put("SEND_CHANN_NAME", model.getSEND_CHANN_NAME());
		params.put("STOREOUT_STORE_ID", model.getSTOREOUT_STORE_ID());
		params.put("STOREOUT_STORE_NO", model.getSTOREOUT_STORE_NO());
		params.put("STOREOUT_STORE_NAME", model.getSTOREOUT_STORE_NAME());
		params.put("RECV_CHANN_ID", model.getRECV_CHANN_ID());
		params.put("RECV_CHANN_NO", model.getRECV_CHANN_NO());
		params.put("RECV_CHANN_NAME", model.getRECV_CHANN_NAME());
		params.put("SALE_DATE", model.getSALE_DATE());
		params.put("RECV_ADDR_NO", model.getRECV_ADDR_NO());
		params.put("OTHER_COST", model.getOTHER_COST());
		params.put("RECV_ADDR", model.getRECV_ADDR());
		params.put("REMARK", model.getREMARK());
		params.put("STOREOUT_AMOUNT", model.getSTOREOUT_AMOUNT());
		if(StringUtil.isEmpty(STOREOUT_NOTICE_ID)){
			STOREOUT_NOTICE_ID=StringUtil.uuid32len();
			String STOREOUT_NOTICE_NO=LogicUtil.getBmgz("DRP_STOREOUT_NOTICE_NO");
			params.put("STOREOUT_NOTICE_NO", STOREOUT_NOTICE_NO);
			params.put("STATE", BusinessConsts.UNCOMMIT);
			params.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
			params.putAll(LogicUtil.sysFild(userBean));
			txInsert(params);
		}else{
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPD_TIME", "数据库时间");
			params.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
			txUpdateById(params);
			clearCaches(STOREOUT_NOTICE_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(STOREOUT_NOTICE_ID, chldList, userBean,"edit");
		}
		upSTOREOUT_AMOUNT(STOREOUT_NOTICE_ID);
	}
	
	
	/**
	 * 查询 明细的库存
	 * @param modelList 明细
	 * @param STORE_ID 库房ID
	 * @param SALE_ORDER_ID 销售订单ID
	 * @return
	 */
	public boolean checkStoreNum(List <StoreoutnoticeChldModel> modelList,String STORE_ID,String SALE_ORDER_ID){
		Map<String,String> params = new HashMap<String,String>();
		for(StoreoutnoticeChldModel model : modelList){
			
		}
		 
		return true;
	}
	
	/**
	 * @删除
	 * @param PRD_INV_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Storeoutnotice.delete", params);
		 //删除子表 
		 return update("Storeoutnotice.delChldByFkId", params);
	}
	 /**
     * * 子表批量删除:软删除
     * 
     * @param STOREOUT_NOTICE_DTL_IDS the STOREOUT_NOTICE_DTL_IDS
     */
    @SuppressWarnings("unchecked")
    public void txBatch4DeleteChild(String STOREOUT_NOTICE_DTL_IDS) {
	   Map params = new HashMap();
	   params.put("STOREOUT_NOTICE_DTL_IDS", STOREOUT_NOTICE_DTL_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storeoutnotice.deleteChldByIds", params);
       upSTOREOUT_AMOUNT_DTL(STOREOUT_NOTICE_DTL_IDS);
    }
	public boolean txChildEdit(String STOREOUT_NOTICE_ID,List<StoreoutnoticeChldModel> chldList, UserBean userBean,String actionType) {
		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		String STOREOUT_NOTICE_DTL_ID;
		for (StoreoutnoticeChldModel model : chldList) {
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_NO", model.getPRD_NO());
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("NOTICE_NUM", model.getNOTICE_NUM());
			params.put("STD_UNIT", model.getSTD_UNIT());
			params.put("PRICE", model.getPRICE());
			params.put("DECT_RATE", model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());
			params.put("DECT_AMOUNT", model.getDECT_AMOUNT());
			params.put("FROM_BILL_DTL_ID", model.getFROM_BILL_DTL_ID());
			params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			params.put("REMARK", model.getREMARK());
			params.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
			if("list".equals(actionType)){
				// 如果没有明细ID的则是新增，有的是修改
				if (StringUtil.isEmpty(model.getSTOREOUT_NOTICE_DTL_ID())) {
					STOREOUT_NOTICE_DTL_ID = StringUtil.uuid32len();
					params.put("STOREOUT_NOTICE_DTL_ID", STOREOUT_NOTICE_DTL_ID);
					params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
					addList.add(params);
				} else {
					STOREOUT_NOTICE_DTL_ID = model.getSTOREOUT_NOTICE_DTL_ID();
					updateList.add(params);
				}
			}else{
				STOREOUT_NOTICE_DTL_ID = StringUtil.uuid32len();
				params.put("STOREOUT_NOTICE_DTL_ID", STOREOUT_NOTICE_DTL_ID);
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
				addList.add(params);
			}
			
		}
		if(!"list".equals(actionType)){
   		 this.delete("Storeoutnotice.delDtl", STOREOUT_NOTICE_ID);
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Storeoutnotice.updateChldById", updateList);
		}
		if (!addList.isEmpty()) {
			this.batch4Update("Storeoutnotice.insertChld", addList);
		}
		upSTOREOUT_AMOUNT(STOREOUT_NOTICE_ID);
		return true;
	}
	/**
	 * 根据主表Id更新主表出库总金额
	 * @param STOREOUT_NOTICE_ID
	 */
	public void upSTOREOUT_AMOUNT(String STOREOUT_NOTICE_ID){
		Map<String,String> map=new HashMap<String, String>();
		map.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		this.update("Storeoutnotice.upSTOREOUT_AMOUNT", map);
	}
	/**
	 * 根据选择明细id更新主表出库总金额
	 * @param STOREOUT_NOTICE_DTL_IDS
	 */
	public void upSTOREOUT_AMOUNT_DTL(String STOREOUT_NOTICE_DTL_IDS){
		Map<String,String> map=new HashMap<String, String>();
		map.put("STOREOUT_NOTICE_DTL_IDS", STOREOUT_NOTICE_DTL_IDS);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		this.update("Storeoutnotice.upSTOREOUT_AMOUNT_DTL", map);
	}
	/**
     * 点击提交按钮时改变单据状态为审核通过并且生成出库单
     * @param STOREOUT_NOTICE_ID
     * @param userBean
     */
	public void txCommit(String STOREOUT_NOTICE_ID,UserBean userBean){
		Map<String,String> map=new HashMap<String, String>();
    	map.put("UPDATOR", userBean.getXTYHID());
    	map.put("UPD_NAME", userBean.getXM());
    	map.put("UPD_TIME", "数据库时间");
    	map.put("STATE", BusinessConsts.PASS);
    	map.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
    	this.txUpdateById(map);
    	this.insertStoreOut(STOREOUT_NOTICE_ID, userBean);
	}
	/**
	 * 生成出库单主表
	 * @param STOREOUT_NOTICE_ID
	 * @param userBean
	 */
	public void insertStoreOut(String STOREOUT_NOTICE_ID,UserBean userBean){
		Map<String,String> map=new HashMap<String, String>();
		Map<String,String> params=this.load(STOREOUT_NOTICE_ID);
		map.putAll(LogicUtil.sysFild(userBean));
		String STOREOUT_ID=StringUtil.uuid32len();
    	map.put("STOREOUT_ID", STOREOUT_ID);
    	map.put("STOREOUT_NO", LogicUtil.getBmgz("DRP_STOREOUT_NO"));
    	map.put("BILL_TYPE", "下级销售出库");
    	map.put("STOREOUT_NOTICE_ID", STOREOUT_NOTICE_ID);
    	map.put("STATE", BusinessConsts.UNDONE);
    	map.put("STORAGE_FLAG","0");//状态
    	map.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_FALSE);
    	this.insert("Storeoutnotice.insertStoreOut",map );
    	this.inserStoreOutDtl(STOREOUT_NOTICE_ID,STOREOUT_ID);
	}
	/**
	 * 生成出库单明细
	 * @param STOREOUT_NOTICE_ID
	 * @param STOREOUT_ID
	 */
	public void inserStoreOutDtl(String STOREOUT_NOTICE_ID,String STOREOUT_ID){
		List<StoreoutnoticeChldModel> chldList=this.childQuery(STOREOUT_NOTICE_ID);
		//新增的出库单明细List
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		//需要反填的订货单list
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		Object obj;
		for (int i = 0; i < chldList.size(); i++) {
			Map<String,String> model=(Map<String, String>) chldList.get(i);
			Map<String,String> map=new HashMap<String, String>();
			obj=model.get("NOTICE_NUM");
			map.put("NOTICE_NUM", obj.toString());
			int size=Integer.parseInt(obj.toString());
			for (int j = 0; j < size; j++) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("STOREOUT_DTL_ID", StringUtil.uuid32len());
				params.put("STOREOUT_ID", STOREOUT_ID);
				params.put("PRD_ID", model.get("PRD_ID"));
				params.put("PRD_NO", model.get("PRD_NO"));
				params.put("PRD_NAME", model.get("PRD_NAME"));
				params.put("PRD_SPEC", model.get("PRD_SPEC"));
				params.put("PRD_COLOR", model.get("PRD_COLOR"));
				params.put("BRAND", model.get("BRAND"));
				params.put("STD_UNIT", model.get("STD_UNIT"));
				params.put("NOTICE_NUM", "1");
				obj=model.get("PRICE");
				params.put("PRICE", obj.toString());
				obj=model.get("DECT_RATE");
				params.put("DECT_RATE", obj.toString());
				obj=model.get("DECT_PRICE");
				params.put("DECT_PRICE", obj.toString());
				params.put("DECT_AMOUNT", obj.toString());
				params.put("REMARK", model.get("REMARK"));
				params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
				params.put("FROM_BILL_DTL_ID", model.get("STOREOUT_NOTICE_DTL_ID"));
				params.put("SPCL_TECH_ID", model.get("SPCL_TECH_ID"));
				addList.add(params);
			}
			map.put("FROM_BILL_DTL_ID", model.get("FROM_BILL_DTL_ID"));
			updateList.add(map);
		}
		this.batch4Update("Storeout.insertChld", addList);
		this.batch4Update("Storeoutnotice.upSaleOrderDtl", updateList);
	}
}
