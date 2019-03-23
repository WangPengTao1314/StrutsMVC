package com.hoperun.drp.areaser.senddirectnotice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.areaser.senddirectnotice.model.SenddirectnoticeModelChld;
import com.hoperun.drp.areaser.senddirectnotice.service.SenddirectnoticeService;
import com.hoperun.erp.sale.saleorder.model.SaleorderModelChld;
import com.hoperun.sys.model.UserBean;

public class SenddirectnoticeServiceImpl extends BaseService implements SenddirectnoticeService {
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Senddirectnotice.pageQuery", "Senddirectnotice.pageCount",params, pageNo);
	}
	/**
	 * @分页查询销售订单
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public List<Map<String,String>> querySaleOrder(Map<String,String> params) {
		return this.findList("Senddirectnotice.querySaleOrder", params);
	}
	
	
	public Map<String, String> load(String BASE_DELIVER_NOTICE_ID) {
		return (Map<String, String>) load("Senddirectnotice.loadById", BASE_DELIVER_NOTICE_ID);
	}
	/**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @SuppressWarnings("unchecked")
    public List <SaleorderModelChld> childQuery(String BASE_DELIVER_NOTICE_ID) {
        Map params = new HashMap();
        params.put("BASE_DELIVER_NOTICE_ID", BASE_DELIVER_NOTICE_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Senddirectnotice.queryChldByFkId", params);
    }
	@SuppressWarnings("unchecked")
	@Override
	public void txbackSale(String BASE_DELIVER_NOTICE_ID,List<SenddirectnoticeModelChld> modelList,UserBean userBean) throws Exception{
		Map baseDeliverMap = this.load(BASE_DELIVER_NOTICE_ID);
		updateSaleOrderDtlForNum(modelList,userBean);
		genStoreinNoticeAndStoreOut(baseDeliverMap,modelList,userBean);
		updateBaseDeliverNotice(BASE_DELIVER_NOTICE_ID,userBean);
	}
	
	public boolean updateBaseDeliverNotice(String BASE_DELIVER_NOTICE_ID,UserBean userBean)throws Exception{
		Map params = new HashMap();
        params.put("BASE_DELIVER_NOTICE_ID", BASE_DELIVER_NOTICE_ID);
        params.put("STATE", BusinessConsts.DONE);
        params.put("UPDATOR", userBean.getRYXXID());
        params.put("UPD_NAME", userBean.getXM());
		return update("Senddirectnotice.updateStats", params);
	}
	
	/**分配发货数量
	 * @return
	 */
	private boolean updateSaleOrderDtlForNum(List<SenddirectnoticeModelChld> modelList,UserBean userBean){
		ArrayList dtlList = new ArrayList();
		for(int i=0;i<modelList.size();i++){
			SenddirectnoticeModelChld dtlMode = modelList.get(i);
			String SALE_ORDER_DTL_ID = dtlMode.getSALE_ORDER_DTL_ID();
			String ALLOT_NUM = dtlMode.getALLOT_NUM();
			Map params = new HashMap();
	        params.put("SALE_ORDER_DTL_ID", SALE_ORDER_DTL_ID);
	        params.put("SENDED_NUM", ALLOT_NUM);
	        dtlList.add(params);
		}
		 return batch4Update("Senddirectnotice.updateAllotNum", dtlList);
	}
	
	/**生成区域服务中心的多张入库通知单(跟据销售订单ID生成多张入库单)
	 * 生成区域服务中心的下级销售出库单
	 */
	@SuppressWarnings("unchecked")
	private void genStoreinNoticeAndStoreOut(Map<String,String> deliverMap,List<SenddirectnoticeModelChld> modelList,UserBean userBean)throws Exception{
		HashMap baseDeliverNoticeMap = new HashMap();
		//跟据销售订单ID过滤入库单(相同的销售订单,生成一个入库单)
		for(int i=0;i<modelList.size();i++){
			SenddirectnoticeModelChld dtlMode = modelList.get(i);
			String SALE_ORDER_ID = dtlMode.getSALE_ORDER_ID();
			if(baseDeliverNoticeMap.containsKey(SALE_ORDER_ID)){
				ArrayList storeinNoticeList = (ArrayList)baseDeliverNoticeMap.get(SALE_ORDER_ID);
				storeinNoticeList.add(dtlMode);
			}else{
				ArrayList tempList = new ArrayList();
				tempList.add(dtlMode);
				baseDeliverNoticeMap.put(SALE_ORDER_ID, tempList);
			}
		}
		Iterator iter = baseDeliverNoticeMap.entrySet().iterator(); 
		while(iter.hasNext()){ 
			Map.Entry entry = (Map.Entry) iter.next();
			ArrayList baseDeliverNoticeList = (ArrayList)entry.getValue();
			String STOREIN_ID = StringUtil.uuid32len();
			String STOREIN_NO = LogicUtil.getBmgz("DRP_STOREIN_NO");
			
			SenddirectnoticeModelChld dtlMode = (SenddirectnoticeModelChld)baseDeliverNoticeList.get(0);
			String SALE_ORDER_DTL_ID = dtlMode.getSALE_ORDER_DTL_ID();
			Map drpSaleOrderMap = (Map)this.load("Senddirectnotice.querySaleOrderById", SALE_ORDER_DTL_ID);

			
			genStoreinOrder(STOREIN_ID,STOREIN_NO,deliverMap,baseDeliverNoticeList,drpSaleOrderMap,userBean);
			String STOREOUT_NOTICE_ID = StringUtil.uuid32len();
			String STOREOUT_NOTICE_NO = LogicUtil.getBmgz("DRP_STOREOUT_NOTICE_NO");
			genStoreOutNoticeOrder(STOREOUT_NOTICE_ID,STOREOUT_NOTICE_NO,deliverMap,baseDeliverNoticeList,drpSaleOrderMap,userBean);
			String STOREOUT_ID = StringUtil.uuid32len();
			String STOREOUT_NO = LogicUtil.getBmgz("DRP_STOREOUT_NO");
			genStoreOutOrder(STOREOUT_ID,STOREOUT_NO,STOREIN_ID,STOREIN_NO,STOREOUT_NOTICE_ID,STOREOUT_NOTICE_NO,deliverMap,baseDeliverNoticeList,userBean);
			String STOREIN_NOTICE_ID = StringUtil.uuid32len();
			String STOREIN_NOTICE_NO = LogicUtil.getBmgz("DRP_STOREIN_NOTICE_NO");
			genDrpStoreinOrder(STOREIN_NOTICE_ID,STOREIN_NOTICE_NO,STOREOUT_ID,STOREOUT_NO,deliverMap,baseDeliverNoticeList,drpSaleOrderMap,userBean);
		}
		
	}
	
	/**生成区域服务中心的多张入库通知单(跟据销售订单ID生成多张入库单)
	 * @param modelList
	 * @param userBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean genStoreinOrder(String STOREIN_ID, String STOREIN_NO ,Map<String,String> deliverMap,List<SenddirectnoticeModelChld> modelList,Map drpSaleOrderMap,UserBean userBean)throws Exception{
			SenddirectnoticeModelChld dtlMode = (SenddirectnoticeModelChld)modelList.get(0);
			Map params = new HashMap();
			params.put("STOREIN_ID",STOREIN_ID);
			params.put("STOREIN_NO", STOREIN_NO);
			params.put("BILL_TYPE", "订货入库");
			params.put("FROM_BILL_ID",dtlMode.getBASE_DELIVER_NOTICE_ID());
			params.put("FROM_BILL_NO",deliverMap.get("BASE_DELIVER_NOTICE_NO"));
			params.put("ORDER_CHANN_ID",dtlMode.getORDER_CHANN_ID());
			params.put("ORDER_CHANN_NO", dtlMode.getORDER_CHANN_NO());
			params.put("ORDER_CHANN_NAME", dtlMode.getORDER_CHANN_NAME());
			params.put("SEND_CHANN_ID",userBean.getBASE_CHANN_ID());
			params.put("SEND_CHANN_NO",userBean.getBASE_CHANN_NO());
			params.put("SEND_CHANN_NAME",userBean.getBASE_CHANN_NAME());
			params.put("RECV_CHANN_ID",deliverMap.get("RECV_CHANN_ID"));
			params.put("RECV_CHANN_NO",deliverMap.get("RECV_CHANN_NO"));
			params.put("RECV_CHANN_NAME",deliverMap.get("RECV_CHANN_NAME"));
			params.put("STATE","已处理");
			params.put("REMARK",dtlMode.getREMARK());
			params.put("CREATOR", dtlMode.getORDER_CHANN_ID());
			params.put("CRE_NAME", dtlMode.getORDER_CHANN_NAME());
			params.put("DEPT_ID",  dtlMode.getORDER_CHANN_ID());
			params.put("DEPT_NAME",  dtlMode.getORDER_CHANN_NAME());
			params.put("ORG_NAME",  dtlMode.getORDER_CHANN_NAME());
			params.put("LEDGER_NAME",  dtlMode.getORDER_CHANN_NAME());
			params.put("ORG_ID",  dtlMode.getORDER_CHANN_ID());
			params.put("LEDGER_ID",  dtlMode.getORDER_CHANN_ID());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			insert("Storein.insert", params);
			ArrayList noticeDtlParamList = new ArrayList();
			for(int i=0;i<modelList.size();i++){
				SenddirectnoticeModelChld dtlData = (SenddirectnoticeModelChld)modelList.get(i);
				Map dtlParamMap = new HashMap();
				dtlParamMap.put("STOREIN_DTL_ID",StringUtil.uuid32len());
				dtlParamMap.put("STOREIN_ID",STOREIN_ID);
				dtlParamMap.put("PRD_ID",dtlData.getPRD_ID());
				dtlParamMap.put("PRD_NO",dtlData.getPRD_NO());
				dtlParamMap.put("PRD_NAME",dtlData.getPRD_NAME());
				dtlParamMap.put("PRD_SPEC",dtlData.getPRD_SPEC());
				dtlParamMap.put("PRD_COLOR",dtlData.getPRD_COLOR());
				dtlParamMap.put("BRAND",dtlData.getBRAND());
				dtlParamMap.put("STD_UNIT",dtlData.getSTD_UNIT());
				dtlParamMap.put("SPCL_TECH_ID",dtlData.getSPCL_TECH_ID());
				dtlParamMap.put("NOTICE_NUM",dtlData.getALLOT_NUM());
				dtlParamMap.put("REAL_NUM",dtlData.getALLOT_NUM());
				dtlParamMap.put("DEL_FLAG",0);
				dtlParamMap.put("SALE_ORDER_ID",dtlData.getSALE_ORDER_ID());
				dtlParamMap.put("SALE_ORDER_NO",dtlData.getSALE_ORDER_NO());
				dtlParamMap.put("SALE_ORDER_DTL_ID",dtlData.getSALE_ORDER_DTL_ID());
				dtlParamMap.put("GOODS_ORDER_NO",drpSaleOrderMap.get("GOODS_ORDER_NO"));
				noticeDtlParamList.add(dtlParamMap);
			}
			batch4Update("Storein.insertChld", noticeDtlParamList);
			LogicUtil.doJournalAcct(BusinessConsts.STOREIN_ORDER_CONF_ID,STOREIN_ID);
		return true;
	}
	
	
	/**生成加盟商的多张入库通知单(跟据销售订单ID生成多张入库单)
	 * @param modelList
	 * @param userBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private boolean genDrpStoreinOrder(String STOREIN_NOTICE_ID, String STOREIN_NOTICE_NO,String AreaStoreOutId,String AreaStoreOutNo,Map<String,String> deliverMap,List<SenddirectnoticeModelChld> modelList,Map drpSaleOrderMap,UserBean userBean)throws Exception{
			SenddirectnoticeModelChld dtlMode = (SenddirectnoticeModelChld)modelList.get(0);
			Map params = new HashMap();
			float STOREOUT_AMOUNT=0;
			params.put("STOREIN_NOTICE_ID",STOREIN_NOTICE_ID);
			params.put("STOREIN_NOTICE_NO", STOREIN_NOTICE_NO);
			params.put("BILL_TYPE", "订货入库");
			params.put("FROM_BILL_ID",AreaStoreOutId);
			params.put("FROM_BILL_NO", AreaStoreOutNo);
			params.put("RECV_CHANN_ID",dtlMode.getRECV_CHANN_ID()); //加盟商的订货方就是自己
			params.put("RECV_CHANN_NO",dtlMode.getRECV_CHANN_NO());
			params.put("RECV_CHANN_NAME", dtlMode.getRECV_CHANN_NAME());
			//0156464--start--刘曰刚--2014-06-18//
			//修改发货方为区域服务中心，原来是总部
			params.put("SEND_CHANN_ID",dtlMode.getORDER_CHANN_ID());
			params.put("SEND_CHANN_NO",dtlMode.getORDER_CHANN_NO());
			params.put("SEND_CHANN_NAME",dtlMode.getORDER_CHANN_NAME());
			//0156464--End--刘曰刚--2014-06-18//
			params.put("RECV_CHANN_ID",dtlMode.getRECV_CHANN_ID());
			params.put("RECV_CHANN_NO",dtlMode.getRECV_CHANN_NO());
			params.put("RECV_CHANN_NAME",dtlMode.getRECV_CHANN_NAME());
			params.put("STATE",BusinessConsts.UNCOMMIT);
			params.put("REMARK",dtlMode.getREMARK());
			params.put("CREATOR", dtlMode.getRECV_CHANN_ID());
			params.put("CRE_NAME", dtlMode.getRECV_CHANN_NAME());
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			params.put("ORG_NAME", dtlMode.getRECV_CHANN_NAME());
			params.put("DEPT_NAME", dtlMode.getRECV_CHANN_NAME());
			params.put("DEPT_ID",  dtlMode.getRECV_CHANN_ID());
			params.put("ORG_ID",  dtlMode.getRECV_CHANN_ID());
			params.put("LEDGER_ID",  dtlMode.getRECV_CHANN_ID());
			params.put("LEDGER_NAME",  dtlMode.getRECV_CHANN_NAME());
			ArrayList noticeDtlParamList = new ArrayList();
			for(int i=0;i<modelList.size();i++){
				SenddirectnoticeModelChld dtlData = (SenddirectnoticeModelChld)modelList.get(i);
				Map dtlParamMap = new HashMap();
				dtlParamMap.put("STOREIN_NOTICE_DTL_ID",StringUtil.uuid32len());
				dtlParamMap.put("STOREIN_NOTICE_ID",STOREIN_NOTICE_ID);
				dtlParamMap.put("PRD_ID",dtlData.getPRD_ID());
				dtlParamMap.put("PRD_NO",dtlData.getPRD_NO());
				dtlParamMap.put("PRD_NAME",dtlData.getPRD_NAME());
				dtlParamMap.put("PRD_SPEC",dtlData.getPRD_SPEC());
				dtlParamMap.put("PRD_COLOR",dtlData.getPRD_COLOR());
				dtlParamMap.put("BRAND",dtlData.getBRAND());
				dtlParamMap.put("STD_UNIT",dtlData.getSTD_UNIT());
				dtlParamMap.put("SPCL_TECH_ID",dtlData.getSPCL_TECH_ID());
				Object PRICE=dtlData.getPRICE();
				dtlParamMap.put("PRICE", PRICE);
				Object DECT_RATE=dtlData.getDECT_RATE();
				dtlParamMap.put("DECT_RATE", DECT_RATE);
				Object DECT_PRICE=dtlData.getDECT_PRICE();
				dtlParamMap.put("DECT_PRICE", DECT_PRICE);
				Object RECV_NUM=dtlData.getALLOT_NUM();
				float DECT_AMOUNT=Float.parseFloat(DECT_PRICE.toString())*Float.parseFloat(RECV_NUM.toString());
				STOREOUT_AMOUNT+=DECT_AMOUNT;
				dtlParamMap.put("DECT_AMOUNT", DECT_AMOUNT);
				dtlParamMap.put("NOTICE_NUM",dtlData.getALLOT_NUM());
				dtlParamMap.put("DEL_FLAG",0);
				dtlParamMap.put("SALE_ORDER_ID",dtlData.getSALE_ORDER_ID());
				dtlParamMap.put("SALE_ORDER_NO",dtlData.getSALE_ORDER_NO());
				dtlParamMap.put("SALE_ORDER_DTL_ID",dtlData.getSALE_ORDER_DTL_ID());
				dtlParamMap.put("GOODS_ORDER_NO",drpSaleOrderMap.get("GOODS_ORDER_NO"));
				noticeDtlParamList.add(dtlParamMap);
			}
			params.put("STOREOUT_AMOUNT", STOREOUT_AMOUNT+"");
			insert("Storeinnotice.insert", params);
			batch4Update("Storeinnotice.insertChld", noticeDtlParamList);
		return true;
	}
	
	/**
	 * 生成区域服务中心的下级销售出库通知单
	 */
	@SuppressWarnings("unchecked")
	private void genStoreOutNoticeOrder(String STOREOUT_NOTICE_ID,String STOREOUT_NOTICE_NO,Map<String,String> deliverMap,List<SenddirectnoticeModelChld> modelList,Map drpSaleOrderMap,UserBean userBean){
		SenddirectnoticeModelChld dtlMode = (SenddirectnoticeModelChld)modelList.get(0);
		Map params = new HashMap();
		float STOREOUT_AMOUNT=0;
		params.put("STOREOUT_NOTICE_ID",STOREOUT_NOTICE_ID);
		params.put("STOREOUT_NOTICE_NO",STOREOUT_NOTICE_NO); //发货方是区域服务中心,总部直发的话，订货方肯定是区域服务中心
		params.put("FROM_BILL_ID",dtlMode.getSALE_ORDER_ID());
		params.put("FROM_BILL_NO",dtlMode.getSALE_ORDER_NO());
		params.put("SEND_CHANN_ID",dtlMode.getORDER_CHANN_ID());
		params.put("SEND_CHANN_NO", dtlMode.getORDER_CHANN_NO());
		params.put("SEND_CHANN_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("RECV_CHANN_ID",deliverMap.get("RECV_CHANN_ID"));
		params.put("RECV_CHANN_NO",deliverMap.get("RECV_CHANN_NO"));
		params.put("RECV_CHANN_NAME", deliverMap.get("RECV_CHANN_NAME"));
		params.put("SALE_DATE", drpSaleOrderMap.get("SALE_DATE"));
		params.put("RECV_ADDR_NO", dtlMode.getRECV_ADDR_NO());
		params.put("RECV_ADDR", dtlMode.getRECV_ADDR());
		params.put("STATE", "审核通过");
		params.put("REMARK", dtlMode.getREMARK());
		params.put("CREATOR", dtlMode.getORDER_CHANN_ID());
		params.put("CRE_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("DEPT_ID",  dtlMode.getORDER_CHANN_ID());
		params.put("DEPT_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("ORG_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("ORG_ID",  dtlMode.getORDER_CHANN_ID());
		params.put("LEDGER_ID",  dtlMode.getORDER_CHANN_ID());
		params.put("LEDGER_NAME",  dtlMode.getORDER_CHANN_NAME());
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		ArrayList StoreOutnoticeDtlParamList = new ArrayList();
		for(int i=0;i<modelList.size();i++){
			SenddirectnoticeModelChld dtlData = (SenddirectnoticeModelChld)modelList.get(i);
			Map dtlParamMap = new HashMap();
			dtlParamMap.put("STOREOUT_NOTICE_DTL_ID",StringUtil.uuid32len());
			dtlParamMap.put("STOREOUT_NOTICE_ID",STOREOUT_NOTICE_ID);
			dtlParamMap.put("PRD_ID",dtlData.getPRD_ID());
			dtlParamMap.put("PRD_NO",dtlData.getPRD_NO());
			dtlParamMap.put("PRD_NAME",dtlData.getPRD_NAME());
			dtlParamMap.put("PRD_SPEC",dtlData.getPRD_SPEC());
			dtlParamMap.put("PRD_COLOR",dtlData.getPRD_COLOR());
			dtlParamMap.put("BRAND",dtlData.getBRAND());
			dtlParamMap.put("STD_UNIT",dtlData.getSTD_UNIT());
			Object DECT_PRICE=dtlData.getDECT_PRICE();
			dtlParamMap.put("DECT_PRICE", DECT_PRICE);
			Object PRICE=dtlData.getPRICE();
			dtlParamMap.put("PRICE", PRICE);
			Object DECT_RATE=dtlData.getDECT_RATE();
			dtlParamMap.put("DECT_RATE", DECT_RATE);
			Object RECV_NUM=dtlData.getALLOT_NUM();
			float DECT_AMOUNT=Float.parseFloat(DECT_PRICE.toString())*Float.parseFloat(RECV_NUM.toString());
			dtlParamMap.put("DECT_AMOUNT", DECT_AMOUNT);
			STOREOUT_AMOUNT+=DECT_AMOUNT;
			dtlParamMap.put("SPCL_TECH_ID",dtlData.getSPCL_TECH_ID());
			dtlParamMap.put("NOTICE_NUM",dtlData.getALLOT_NUM());
			dtlParamMap.put("REAL_NUM",dtlData.getALLOT_NUM());
			dtlParamMap.put("DEL_FLAG",0);
			dtlParamMap.put("FROM_BILL_DTL_ID",dtlData.getSALE_ORDER_DTL_ID());
			StoreOutnoticeDtlParamList.add(dtlParamMap);
		}
		params.put("STOREOUT_AMOUNT", STOREOUT_AMOUNT+"");
		insert("Storeoutnotice.insert", params);
		batch4Update("Storeoutnotice.insertChld", StoreOutnoticeDtlParamList);
		
	}
	
	/**生成下级销售出库单
	 * 
	 */
	@SuppressWarnings("unchecked")
	private void genStoreOutOrder(String STOREOUT_ID,String STOREOUT_NO,String STOREIN_ID,String STOREIN_NO, String STOREOUT_NOTICE_ID,String STOREOUT_NOTICE_NO,Map<String,String> deliverMap,ArrayList basedeliverOrderList,UserBean userBean )throws Exception{
		SenddirectnoticeModelChld dtlMode = (SenddirectnoticeModelChld)basedeliverOrderList.get(0);
		Map params = new HashMap();
		float STOREOUT_AMOUNT=0;
		params.put("STOREOUT_ID",STOREOUT_ID);
		params.put("STOREOUT_NO",STOREOUT_NO);
		params.put("BILL_TYPE","下级销售出库");
		params.put("FROM_BILL_ID", STOREOUT_NOTICE_ID);
		params.put("FROM_BILL_NO", STOREOUT_NOTICE_NO);
		params.put("SEND_CHANN_ID", dtlMode.getORDER_CHANN_ID());  //发货方是区域服务中心,总部直发的话，订货方肯定是区域服务中心
		params.put("SEND_CHANN_NO", dtlMode.getORDER_CHANN_NO());
		params.put("SEND_CHANN_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("RECV_CHANN_ID", dtlMode.getRECV_CHANN_ID());
		params.put("RECV_CHANN_NO", dtlMode.getRECV_CHANN_NO());
		params.put("RECV_CHANN_NAME", dtlMode.getRECV_CHANN_NAME());
		params.put("RECV_ADDR", dtlMode.getRECV_ADDR());
		params.put("STOREIN_ID", STOREIN_ID);
		params.put("STOREIN_NO", STOREIN_NO);
		params.put("STATE", "已处理");
		params.put("REMARK",dtlMode.getREMARK());
		//-- 0155995--Start--刘曰刚--2014-06-17//
		//修改制单信息为收货方信息，原来是订货方信息//二次修改，改回订货方信息
		params.put("CREATOR", dtlMode.getORDER_CHANN_ID());
		params.put("CRE_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		params.put("ORG_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("DEPT_NAME", dtlMode.getORDER_CHANN_NAME());
		params.put("DEPT_ID",  dtlMode.getORDER_CHANN_ID());
		params.put("ORG_ID",  dtlMode.getORDER_CHANN_ID());
		params.put("LEDGER_ID",  dtlMode.getORDER_CHANN_ID());
		params.put("LEDGER_NAME",  dtlMode.getORDER_CHANN_NAME());
		//-- 0155995--End--刘曰刚--2014-06-17//
		ArrayList storeoutDtlParamList = new ArrayList();
		for(int k=0;k<basedeliverOrderList.size();k++){
			SenddirectnoticeModelChld dtlData = (SenddirectnoticeModelChld)basedeliverOrderList.get(k);
			Map dtlParamMap = new HashMap();
			dtlParamMap.put("STOREOUT_DTL_ID",StringUtil.uuid32len());
			dtlParamMap.put("STOREOUT_ID",STOREOUT_ID);
			dtlParamMap.put("PRD_ID",dtlData.getPRD_ID());
			dtlParamMap.put("PRD_NO",dtlData.getPRD_NO());
			dtlParamMap.put("PRD_NAME",dtlData.getPRD_NAME());
			dtlParamMap.put("PRD_SPEC",dtlData.getPRD_SPEC());
			dtlParamMap.put("PRD_COLOR",dtlData.getPRD_COLOR());
			dtlParamMap.put("BRAND",dtlData.getBRAND());
			Object DECT_PRICE=dtlData.getDECT_PRICE();
			dtlParamMap.put("DECT_PRICE", DECT_PRICE);
			Object PRICE=dtlData.getPRICE();
			dtlParamMap.put("PRICE", PRICE);
			Object DECT_RATE=dtlData.getDECT_RATE();
			dtlParamMap.put("DECT_RATE", DECT_RATE);
			Object RECV_NUM=dtlData.getALLOT_NUM();
			float DECT_AMOUNT=Float.parseFloat(DECT_PRICE.toString())*Float.parseFloat(RECV_NUM.toString());
			STOREOUT_AMOUNT+=DECT_AMOUNT;
			dtlParamMap.put("DECT_AMOUNT", DECT_AMOUNT);
			dtlParamMap.put("STD_UNIT",dtlData.getSTD_UNIT());
			dtlParamMap.put("SPCL_TECH_ID",dtlData.getSPCL_TECH_ID());
			dtlParamMap.put("NOTICE_NUM",dtlData.getALLOT_NUM());
			dtlParamMap.put("REAL_NUM",dtlData.getALLOT_NUM());
			dtlParamMap.put("REMARK",dtlData.getREMARK());
			dtlParamMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
			dtlParamMap.put("FROM_BILL_DTL_ID",dtlData.getBASE_DELIVER_NOTICE_DTL_ID());
			storeoutDtlParamList.add(dtlParamMap);
		}
		params.put("STOREOUT_AMOUNT", STOREOUT_AMOUNT+"");
		insert("Storeout.insert", params);
		batch4Update("Storeout.insertChld", storeoutDtlParamList);
		LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,STOREOUT_ID);
	}
	@Override
	public String getTotalNum(String BASE_DELIVER_NOTICE_ID) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("BASE_DELIVER_NOTICE_ID", BASE_DELIVER_NOTICE_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		return this.load("Senddirectnotice.getTotalNum", map).toString();
	}
}
