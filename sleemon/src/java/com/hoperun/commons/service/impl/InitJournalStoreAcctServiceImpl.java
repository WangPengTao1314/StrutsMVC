package com.hoperun.commons.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;

public class InitJournalStoreAcctServiceImpl extends BaseService {
	public List getAllChann(){
		StringBuffer selSql =  new StringBuffer();
		selSql.append("select b.chann_id from base_chann b where b.del_flag = 0 and b.state = '启用'");
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		List resultList = selcomList(params);
		return resultList;
	}
	
	public void txInitChannStoreAcct(){
		try{
			initInventoryStoreAcct();
			initDumpStoreAcct();
			initStoreInAcct();
			initStoreOutAcct();
		}catch(Exception ex){
			ex.printStackTrace();
			throw new ServiceException("");
		}
	}
	
	/**初始化盘点库存账(封仓盘)
	 * @param channId
	 * @throws Exception
	 */
	public void initInventoryStoreAcct() throws Exception{
		StringBuffer selSql =  new StringBuffer();
		selSql.append("select t.prd_inv_id from DRP_PRD_INV t where t.inv_type = '封仓盘点' and t.del_flag =0 and t.state = '审核通过' ");
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		List resultList = selcomList(params);
        for(int i=0;i<resultList.size();i++){
        	HashMap invenMap = (HashMap)resultList.get(i);
        	String invenToryId = (String)invenMap.get("PRD_INV_ID");
        	LogicUtil.doJournalAcct(BusinessConsts.INVEN_CONF_ID,invenToryId);
        }	
	}
	
	/**转储单库存流水账
	 * @param channId
	 * @throws Exception
	 */
	public void initDumpStoreAcct() throws Exception{
		StringBuffer selSql =  new StringBuffer();
		selSql.append("select u.dump_id from DRP_DUMP u where u.del_flag = 0  and u.state = '审核通过'");
		Map params = new HashMap();
		params.put("SelSQL", selSql.toString());
		List resultList = selcomList(params);
        for(int i=0;i<resultList.size();i++){
        	HashMap dumMap = (HashMap)resultList.get(i);
        	String dumpId = (String)dumMap.get("DUMP_ID");
        	LogicUtil.doJournalAcct(BusinessConsts.DUMP_OUT_CONF_ID,dumpId);
			LogicUtil.doJournalAcct(BusinessConsts.DUMP_IN_CONF_ID,dumpId);
        }	
	}
	
	
	/**入库单库存流水账
	 * @param channId
	 * @throws Exception
	 */
	public void initStoreInAcct() throws Exception{
		String [][] billTypess = {{"手工新增",BusinessConsts.STOREIN_ORDER_CONF2_ID},{"订货入库",BusinessConsts.STOREIN_ORDER_CONF_ID},{"返修入库",BusinessConsts.STOREIN_ORDER_CONF3_ID},{"终端退货",BusinessConsts.STOREIN_ORDER_CONF5_ID},{"下级退货入库",BusinessConsts.STOREIN_ORDER_CONF6_ID}};
		for(int k =0 ;k<billTypess.length;k++){
			String billType = billTypess[k][0];
			String bussId = billTypess[k][1];
			StringBuffer selSql =  new StringBuffer();
			selSql.append("select t.storein_id from drp_storein t where t.del_flag = 0 and  t.state = '已处理' and t.bill_type = '"+billType+"'");
			Map params = new HashMap();
			params.put("SelSQL", selSql.toString());
			List resultList = selcomList(params);
	        for(int i=0;i<resultList.size();i++){
	        	HashMap storeInMap = (HashMap)resultList.get(i);
	        	String storein_id = (String)storeInMap.get("STOREIN_ID");
	        	LogicUtil.doJournalAcct(bussId,storein_id);
	        }	
		}
		
	}
	
	/**出库单库存流水账
	 * @param channId
	 * @throws Exception
	 */
	public void initStoreOutAcct() throws Exception{
		String [][] billTypess = {{"销售出库",BusinessConsts.STOREOUT_ORDER_CONF_ID},{"退货出库",BusinessConsts.STOREOUT_ORDER_CONF2_ID},{"下级销售出库",BusinessConsts.STOREOUT_ORDER_CONF4_ID},{"返修出库",BusinessConsts.STOREOUT_ORDER_CONF3_ID}};
		for(int k =0 ;k<billTypess.length;k++){
			String billType = billTypess[k][0];
			String bussId = billTypess[k][1];
			StringBuffer selSql =  new StringBuffer();
			selSql.append("select u.STOREOUT_ID from DRP_STOREOUT u where u.del_flag = 0 and u.state = '已处理' and u.bill_type = '"+billType+"'");
			Map params = new HashMap();
			params.put("SelSQL", selSql.toString());
			List resultList = selcomList(params);
	        for(int i=0;i<resultList.size();i++){
	        	HashMap storeOutMap = (HashMap)resultList.get(i);
	        	String STOREOUT_ID = (String)storeOutMap.get("STOREOUT_ID");
	        	LogicUtil.doJournalAcct(bussId,STOREOUT_ID);
	        }	
		}
		
	}
	
	/**
	 * 获取列表信息
	 * 
	 * @param params
	 * @return
	 */
	public List selcomList(Map params) {
		return this.findList("sqlcom.query", params);
	}

	public boolean txUpdcom(Map params) {
		return update("sqlcom.update", params);
	}

	public boolean txInscom(Map params) {
		insert("sqlcom.insert", params);
		return true;
	}
	
	public boolean txDelcom(Map params) {
		return update("sqlcom.delete", params);
	}
}
