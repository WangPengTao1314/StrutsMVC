package com.hoperun.drp.sale.advccancelsapp.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hoperun.base.area.model.AreaChrgModel;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModel;
import com.hoperun.drp.sale.advccancelsapp.model.AdvccancelsappModelChld;
import com.hoperun.drp.sale.advccancelsapp.service.AdvccancelsappService;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModelChld;
import com.hoperun.drp.sale.advcorder.service.impl.AdvcorderServiceImpl;
import com.hoperun.sys.model.UserBean;

public class AdvccancelsappServiceImpl extends BaseService implements
		AdvccancelsappService {

	private Logger logger = Logger.getLogger(AdvcorderServiceImpl.class);

	/**
	 * @查询
	 * @param params
	 *            map
	 * @param pageNo
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Advccancelsapp.pageQuery",
				"Advccancelsapp.pageCount", params, pageNo);
	}

	/**
	 * * 增加 * @param params
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String, String> params) {
		insert("Advccancelsapp.insert", params);
		return true;
	}

	/**
	 * @删除
	 * @param ADVC_SEND_CANCEL_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map<String, String> params) {
		// 删除父
		update("Advccancelsapp.delete", params);
		// 删除子表
		return update("Advccancelsapp.delChldByFkId", params);
	}

	/**
	 * @编辑
	 * @Description :
	 * @param ADVC_SEND_CANCEL_ID
	 * @param model
	 * @param userBean
	 *            .
	 */
	public String txEdit(String ADVC_SEND_CANCEL_ID, AdvccancelsappModel model,
			List<AdvccancelsappModelChld> chldList, UserBean userBean) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_SEND_REQ_ID", model.getADVC_SEND_REQ_ID());
		params.put("ADVC_SEND_REQ_NO", model.getADVC_SEND_REQ_NO());
		params.put("TERM_ID", model.getTERM_ID());
		params.put("TERM_NO", model.getTERM_NO());
		params.put("TERM_NAME", model.getTERM_NAME());
		params.put("SALE_DATE", model.getSALE_DATE());
		params.put("SALE_PSON_ID", model.getSALE_PSON_ID());
		params.put("SALE_PSON_NAME", model.getSALE_PSON_NAME());
		params.put("CUST_NAME", model.getCUST_NAME());
		params.put("TEL", model.getTEL());
		params.put("STOREOUT_STORE_ID", model.getSTOREOUT_STORE_ID());
		params.put("STOREOUT_STORE_NO", model.getSTOREOUT_STORE_NO());
		params.put("STOREOUT_STORE_NAME", model.getSTOREOUT_STORE_NAME());
		params.put("REMARK", model.getREMARK());
		
		if (StringUtil.isEmpty(ADVC_SEND_CANCEL_ID)) {
			ADVC_SEND_CANCEL_ID = StringUtil.uuid32len();
			String ADVC_SEND_CANCEL_NO = LogicUtil.getBmgz("DRP_ADVC_SEND_CANCEL_NO");
			params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
			params.put("ADVC_SEND_CANCEL_NO", ADVC_SEND_CANCEL_NO);
			params.put("CRE_NAME", userBean.getXM());
			params.put("CREATOR", userBean.getXTYHID());
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("DEPT_ID", userBean.getBMXXID());
			params.put("DEPT_NAME", userBean.getBMMC());
			params.put("ORG_ID", userBean.getJGXXID());
			params.put("ORG_NAME", userBean.getJGMC());
			params.put("STATE", BusinessConsts.UNCOMMIT);// 状态未 ‘未提交’
			params.put("LEDGER_ID", userBean.getLoginZTXXID());
			params.put("LEDGER_NAME", userBean.getLoginZTMC());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			txInsert(params);

		} else {
			params.put("UPD_NAME", userBean.getXM());
			params.put("UPDATOR", userBean.getXTYHID());
			params.put("UPD_TIME", "sysdate");
			params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
			txUpdateById(params);
			clearCaches(ADVC_SEND_CANCEL_ID);
		}
		// 子表信息保存
		if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(ADVC_SEND_CANCEL_ID, chldList);
		}   
		return ADVC_SEND_CANCEL_ID;
	}
	
	/**
	 * 更新主表
	 * @param params
	 */
	public void txUpdateById(Map<String,String>params){
		update("Advccancelsapp.updateById", params);
	}

	/**
	 * @详细
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String, String> load(String param) {
		return (Map<String, String>) load("Advccancelsapp.loadById", param);
	}

	/**
	 * * 明细数据编辑
	 * @param ADVC_SEND_CANCEL_ID
	 * @param modelList
	 */
	@Override
	public boolean txChildEdit(String ADVC_SEND_CANCEL_ID,
			List<AdvccancelsappModelChld> chldList) {
		// 修改列表
		List<Map<String, String>> updateList = new ArrayList<Map<String, String>>();
		// 新增列表
		List<Map<String, String>> addList = new ArrayList<Map<String, String>>();
		for (AdvccancelsappModelChld model : chldList) {
			String ADVC_SEND_CANCEL_DTL_ID = model.getADVC_SEND_CANCEL_DTL_ID();
			Map<String, String> params = new HashMap<String, String>();
			params.put("PRD_ID", model.getPRD_ID());
			params.put("PRD_NO", model.getPRD_NO());
			params.put("PRD_NAME", model.getPRD_NAME());
			params.put("PRD_SPEC", model.getPRD_SPEC());
			params.put("PRD_COLOR", model.getPRD_COLOR());
			params.put("BRAND", model.getBRAND());
			params.put("STD_UNIT", model.getSTD_UNIT());
			params.put("CANCEL_NUM", model.getCANCEL_NUM());
			params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
			params.put("PRICE", model.getPRICE());
			params.put("DECT_RATE", model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());
			params.put("DECT_AMOUNT", model.getDECT_AMOUNT());
			params.put("CANCEL_RSON", model.getCANCEL_RSON());
			params.put("FROM_BILL_DTL_ID", model.getFROM_BILL_DTL_ID());
			params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 
	        if(StringUtil.isEmpty(ADVC_SEND_CANCEL_DTL_ID)){
	        	params.put("ADVC_SEND_CANCEL_DTL_ID",StringUtil.uuid32len());
	        	addList.add(params);
	        }else{
	        	params.put("ADVC_SEND_CANCEL_DTL_ID",ADVC_SEND_CANCEL_DTL_ID);
	        	updateList.add(params);
	        }
		} 
		 
		if (!addList.isEmpty()) {
			this.batch4Update("Advccancelsapp.insertChld", addList);
		}
		if (!updateList.isEmpty()) {
			this.batch4Update("Advccancelsapp.updateChldById", updateList);
		}
		return true;
	}

	/**
	 * 
	 */
	public List<AdvccancelsappModelChld> childQuery(String ADVC_SEND_CANCEL_ID) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
		// 只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		// 查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		return this.findList("Advccancelsapp.queryChldByFkId", params);
	}

	/**
	 * * 根据子表Id批量加载子表信息
	 * 
	 * @param ADVC_SEND_REQ_DTL_IDs
	 *            the ADVC_SEND_REQ_DTL_IDs
	 * 
	 * @return the list< new master slavemx model>
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<AdvccancelsappModelChld> loadChilds(Map<String, String> params) {
		return findList("Advccancelsapp.loadChldByIds", params);
	}

	/**
	 * * 子表批量删除:软删除
	 * 
	 * @param ADVC_SEND_REQ_DTL_IDs
	 *            the ADVC_SEND_REQ_DTL_IDs
	 */
	@SuppressWarnings("unchecked")
	public void txBatch4DeleteChild(String ADVC_SEND_CANCEL_DTL_IDS) {
		Map params = new HashMap();
		params.put("ADVC_SEND_CANCEL_DTL_IDS", ADVC_SEND_CANCEL_DTL_IDS);
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
		update("Advccancelsapp.deleteChldByIds", params);
	}

	public int countFrom(Map<String, String> map) {
		Object count = load("Advccancelsapp.countFrom", map);
		if (count == null) {
			count = "0";
		}
		return Integer.parseInt(count.toString());
	}

	// 反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Advccancelsapp.upUSE_FLAG", map);
	}

	@SuppressWarnings("unchecked")
	public List<AreaChrgModel> Query(String areaID) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("AREA_ID", areaID);
		return this.findList("AreaChrg.query", params);
	}

	public void insertTrace(Map<String, String> map) {
		this.insert("Advcorder.insertTrace", map);
	}
 
	public String qeuryId(String QUERYID) {
		Object id = load("Advccancelsapp.queryDtlId", QUERYID);
		if (null == id) {
			return null;
		} else {
			return id.toString();
		}

	}
	
	/**
	 * 提交
	 * @param ADVC_SEND_CANCEL_ID 主表ID
	 */
	public void txCommit(String ADVC_SEND_CANCEL_ID,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
		params.put("UPD_NAME", userBean.getXM());
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_TIME", "sysdate");
		params.put("STATE", BusinessConsts.COMMIT);//提交
		txUpdateById(params);
	}
	
	
	/**
	 * 审核
	 * @param ADVC_SEND_CANCEL_ID 主表ID
	 * @param ADVC_SEND_REQ_ID 预订单发货申请ID
	 */
	public void txAudit(String ADVC_SEND_CANCEL_ID,String ADVC_SEND_REQ_ID,UserBean userBean){
		Map<String, String> params = new HashMap<String, String>();
		params.put("ADVC_SEND_CANCEL_ID", ADVC_SEND_CANCEL_ID);
		params.put("UPD_NAME", userBean.getXM());
		params.put("UPDATOR", userBean.getXTYHID());
		params.put("UPD_TIME", "sysdate");
		params.put("STATE", BusinessConsts.PASS);//审核通过
		txUpdateById(params);
		
		//更新 预订单发货申请明细
		params.put("CANCEL_FLAG", BusinessConsts.INTEGER_1);//1
		update("Advccancelsapp.updateAdvcReqChldById",params);
		
		//更新 状态=‘未提交’出库单 
		params.clear();
		params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
		params.put("STATE", "已取消");//已取消
		update("Advccancelsapp.updateStoreOutById",params);
		
	}
	
	/**
	 * 查询 已经选择的货品
	 * @param ADVC_SEND_REQ_ID 预订单发货申请ID
	 * @return
	 */
	public List<Map<String,String>> getPrdIdFrom(String ADVC_SEND_REQ_ID){
		List<Map<String,String>> list = null;
		Map<String,String> params = new HashMap<String,String>();
		params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
		list = this.findList("Advccancelsapp.getPrdIdFrom", params);
		return list;
	}


}
