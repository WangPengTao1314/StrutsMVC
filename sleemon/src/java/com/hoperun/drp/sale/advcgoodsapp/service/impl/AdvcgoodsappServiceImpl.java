/**
 * prjName:喜临门营销平台
 * ucName:预订单发货申请
 * fileName:AdvcgoodsappServiceImpl
*/
package com.hoperun.drp.sale.advcgoodsapp.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.base.area.model.AreaChrgModel;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModel;
import com.hoperun.drp.sale.advcgoodsapp.model.AdvcgoodsappModelChld;
import com.hoperun.drp.sale.advcgoodsapp.service.AdvcgoodsappService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author lyg
 * *@createdate 2013-11-02 18:55:53
 */
public class AdvcgoodsappServiceImpl extends BaseService implements AdvcgoodsappService {
	private Logger logger = Logger.getLogger(AdvcgoodsappServiceImpl.class);
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	@SuppressWarnings("unchecked")
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Advcgoodsapp.pageQuery", "Advcgoodsapp.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Advcgoodsapp.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param ADVC_SEND_REQ_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params,UserBean userBean) {
		String ADVC_SEND_REQ_ID = (String)params.get("ADVC_SEND_REQ_ID");
		robackFreeNumAndToSendNum(ADVC_SEND_REQ_ID);
		Map<String,String> advcMap=load(ADVC_SEND_REQ_ID);
		Map<String,String> map=new HashMap<String,String>();
        map.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
        map.put("ADVC_ORDER_ID", advcMap.get("FROM_BILL_ID"));
        map.put("ACTION", "预订单发货申请");
        map.put("REMARK", "已删除");
        map.put("ACTOR", userBean.getXM());
        map.put("BILL_NO", advcMap.get("ADVC_SEND_REQ_NO"));
        this.insertTrace(map);
        
	     //删除父
         update("Advcgoodsapp.delete", params);
	   //删除子表 
	     return update("Advcgoodsapp.delChldByFkId", params);
	}
	
	/** 全部删除时 要恢复预订单明细中的冻结转订货数量
	 * @param ADVC_SEND_REQ_ID
	 */
	@SuppressWarnings("unchecked")
	private void robackFreeNumAndToSendNum(String ADVC_SEND_REQ_ID){
		List chldList = childQuery(ADVC_SEND_REQ_ID);
		for(int i= 0;i<chldList.size();i++){
			HashMap dtlMode = (HashMap)chldList.get(i);
			robackNum(dtlMode);
		}
	}
	private void robackFreeNumAndToSendNumByIds(String ADVC_SEND_REQ_DTL_IDs){
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("ADVC_SEND_REQ_DTL_IDS",ADVC_SEND_REQ_DTL_IDs);
	      List chldList = loadChilds(params);
	      for(int i= 0;i<chldList.size();i++){
				HashMap dtlMode = (HashMap)chldList.get(i);
				robackNum(dtlMode);
			}
	}
	
	private void robackFreeNumAndToSendNumByList(List chldList){
		for(int i= 0;i<chldList.size();i++){
			HashMap dtlMode = (HashMap)chldList.get(i);
			robackNum(dtlMode);
		}
	}

	
	private void robackNum(HashMap dtlMode){
		String ADVC_ORDER_DTL_ID = (String)dtlMode.get("FROM_BILL_DTL_ID");
		Map adverDtlMap =  (Map)load("Advcgoodsapp.queryAdvcdtlById", ADVC_ORDER_DTL_ID);
		BigDecimal IS_FREEZE_FLAG = (BigDecimal)adverDtlMap.get("IS_FREEZE_FLAG");
		if("1".equals(IS_FREEZE_FLAG.toString())){
			String ADVC_SEND_REQ_DTL_ID = (String)dtlMode.get("ADVC_SEND_REQ_DTL_ID");
			HashMap paramMap = new HashMap();
			paramMap.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
			paramMap.put("ADVC_SEND_REQ_DTL_ID", ADVC_SEND_REQ_DTL_ID);
			update("Advcgoodsapp.updateAdvcFreeToSendNum", paramMap);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,String> queryAllOrderNum(HashMap checkMap ) {
		return (Map<String,String>) load("Advcgoodsapp.queryNumByAdvcdtlId", checkMap);
	}
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> paramMap,UserBean userBean,List dtlList) throws Exception{
		String stats = (String)paramMap.get("STATE");
		String advcSendReqId = (String)paramMap.get("ADVC_SEND_REQ_ID");
		if("审核通过".equals(stats)){
			MsgInfo info = LogicUtil.handStoreAcct(advcSendReqId,BusinessConsts.STORE_DESC,BusinessConsts.ADVC_ORDER_SEQ_CONF_ID);
			if(!info.isFLAG()&&!"0".equals(info.getMESS())){
				throw new ServiceException(info.getMESS());
			}
			//生成出库单
			LogicUtil.genStoreOutOrder(advcSendReqId, "DRP_ADVC_SEND_REQ", userBean, null, null);
			
		}else if("已发货".equals(stats)){
			String sendType = (String)paramMap.get("SEND_TYPE");
			if("终端发货".equals(sendType)){
				String checkStoreAcctJson = LogicUtil.doChkCanUseStoreNum(BusinessConsts.ADVC_ORDER_SEQ_CONF1_ID, advcSendReqId, BusinessConsts.STORE_DESC);
		    	MsgInfo model = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
		        }.getType());
		    	if(model.isFLAG()){
//		    		LogicUtil.doStoreFreeze(BusinessConsts.ADVC_ORDER_SEQ_CONF2_ID, advcSendReqId); //按冻结转发货数量解冻
//					//反填预订单明细中的解冻数量=冻结转发货数量 以及已发数量
					for(int i=0 ;i<dtlList.size();i++){
						HashMap dtlMap = (HashMap)dtlList.get(i);
						String FROM_BILL_DTL_ID = (String)dtlMap.get("FROM_BILL_DTL_ID");
						String PRD_ID = (String)dtlMap.get("PRD_ID");
						String SPCL_TECH_ID = (String)dtlMap.get("SPCL_TECH_ID");
						String FREEZE_TO_SEND_NUM = (String)dtlMap.get("FREEZE_TO_SEND_NUM");
						HashMap<String,Object> params = new HashMap<String,Object>();
						params.put("PRD_ID", PRD_ID);
						params.put("SPCL_TECH_ID", SPCL_TECH_ID);
						params.put("FREEZE_NUM", FREEZE_TO_SEND_NUM);
						params.put("SEND_NUM",dtlMap.get("NOTICE_NUM"));
						params.put("ADVC_ORDER_DTL_ID", FROM_BILL_DTL_ID);
						params.put("ADVC_SEND_REQ_DTL_ID", dtlMap.get("ADVC_SEND_REQ_DTL_ID"));
						update("Advcgoodsapp.updateAdvcCommitFreeNum", params);
						update("Advcgoodsapp.updateSendCommitFreeNum", params);
					}
					String STOREOUT_ID=StringUtil.uuid32len();
					instStoreOut(advcSendReqId,STOREOUT_ID, userBean);
		    		LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID, STOREOUT_ID);  //按照通知减库存
					LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,STOREOUT_ID);
					//根据预订单发货申请，查找预订单，只要明细的已发数量和订货数量相等 则修改为已发货
					this.update("Advcgoodsapp.upAdvcStateByNumId", advcSendReqId);
		    	}else{
		    		throw new ServiceException(model.getMESS());
		    	}

			}
		}
		Map<String,String> model= this.load(advcSendReqId);
		Map<String,String> maps=new HashMap<String,String>();
        maps.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
        maps.put("ADVC_ORDER_ID", model.get("FROM_BILL_ID"));
        maps.put("ACTOR", userBean.getXM());
        maps.put("BILL_NO", model.get("ADVC_SEND_REQ_NO"));
		if("提交".equals(stats)){
            maps.put("ACTION", "预订单发货审核");
            maps.put("REMARK", "已生成");
            
       }else if("退回".equals(stats)){
    	   maps.put("ACTION", "预订单发货申请");
           maps.put("REMARK", "已退回");
       }else if("审核通过".equals(stats)){
    	   maps.put("ACTION", "销售出库单");
           maps.put("REMARK", "已生成");
       }else if("已发货".equals(stats)){
    	   maps.put("ACTION", "已终端发货");
           maps.put("REMARK", "已生成");
       }
		if(!StringUtil.isEmpty(stats)){
			this.insertTrace(maps);
		}
		String message=checkAdvcState(advcSendReqId, stats);
		if(!StringUtil.isEmpty(message)){
			throw new ServiceException(message);
		}
		return update("Advcgoodsapp.updateById", paramMap);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param ADVC_SEND_REQ_ID
	 * @param AdvcgoodsappModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public String txEdit(String ADVC_SEND_REQ_ID, AdvcgoodsappModel model,List<AdvcgoodsappModelChld> chldList, UserBean userBean)throws Exception {
		String ADVC_SEND_REQ_NO;
		Map<String,String> params = new HashMap<String,String>();
		    params.put("SEND_CHANN_NO",model.getSEND_CHANN_NO());
		    params.put("SEND_CHANN_ID",model.getSEND_CHANN_ID());
		    params.put("SEND_TYPE", model.getSEND_TYPE());
		    params.put("SEND_CHANN_NAME",model.getSEND_CHANN_NAME());
		    params.put("FROM_BILL_NO",model.getFROM_BILL_NO());
		    params.put("FROM_BILL_ID",model.getFROM_BILL_ID());
		    params.put("TERM_ID",model.getTERM_ID());
		    params.put("TERM_NO",model.getTERM_NO());
		    params.put("TERM_NAME",model.getTERM_NAME());
		    params.put("SALE_DATE",model.getSALE_DATE());
		    params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("CUST_NAME",model.getCUST_NAME());
		    params.put("TEL",model.getTEL());
		    params.put("ORDER_RECV_DATE",model.getORDER_RECV_DATE());
		    params.put("STOREOUT_STORE_ID",model.getSTOREOUT_STORE_ID());
		    params.put("STOREOUT_STORE_NO",model.getSTOREOUT_STORE_NO());
		    params.put("STOREOUT_STORE_NAME",model.getSTOREOUT_STORE_NAME());
		    params.put("STORAGE_FLAG",model.getSTORAGE_FLAG());
		    params.put("RECV_ADDR",model.getRECV_ADDR());
		    params.put("BILL_TYPE", "终端销售");
		    params.put("REMARK",model.getREMARK());
		    params.put("SEND_TYPE", model.getSEND_TYPE());
		    params.put("ADVC_AMOUNT", model.getADVC_AMOUNT());
		    params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
		    params.put("PAYED_TOTAL_AMOUNT", model.getPAYED_TOTAL_AMOUNT());
		    params.put("CONTRACT_NO", model.getCONTRACT_NO());//合同号
		    
		if(StringUtil.isEmpty(ADVC_SEND_REQ_ID)){
			ADVC_SEND_REQ_ID= StringUtil.uuid32len();
			ADVC_SEND_REQ_NO=LogicUtil.getBmgz("DRP_ADVC_SEND_REQ_NO");
			params.put("ADVC_SEND_REQ_NO",ADVC_SEND_REQ_NO);
			params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("STATE",BusinessConsts.UNCOMMIT);//状态未 ‘未提交’
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
			txInsert(params);
			Map<String,String> map=new HashMap<String,String>();
	        map.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
	        map.put("ADVC_ORDER_ID", model.getFROM_BILL_ID());
	        map.put("ACTION", "预订单发货申请");
	        map.put("REMARK", "已生成");
	        map.put("ACTOR", userBean.getXM());
	        map.put("BILL_NO", ADVC_SEND_REQ_NO);
	        this.insertTrace(map);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
			this.update("Advcgoodsapp.updateById", params);
			txUpdateById(params,userBean,null);
			clearCaches(ADVC_SEND_REQ_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ADVC_SEND_REQ_ID, chldList,"edit",userBean);
		}else{
			this.delete("Advcgoodsapp.delDtl", ADVC_SEND_REQ_ID);
		}
		String mssage = checkOrderNum(chldList);
		if(!"true".equals(mssage)){
			throw new ServiceException(mssage); 
		}
	    return ADVC_SEND_REQ_ID;
	}
	
	

	
	/**增加时要更新的数理
	 * @param chldList
	 */
	private void handFreeNumAndToSendNum_ADD(List chldList){
		for(int i= 0;i<chldList.size();i++){
			HashMap advcAppMap = new HashMap();
			HashMap advcDtlMap = new HashMap();
			HashMap dtlMode = (HashMap)chldList.get(i);
			String ADVC_SEND_REQ_DTL_ID = (String)dtlMode.get("ADVC_SEND_REQ_DTL_ID");
			String ADVC_ORDER_DTL_ID =  (String)dtlMode.get("FROM_BILL_DTL_ID");
			Map adverDtlMap =  (Map)load("Advcgoodsapp.queryAdvcdtlById", ADVC_ORDER_DTL_ID);
			BigDecimal IS_FREEZE_FLAG = (BigDecimal)adverDtlMap.get("IS_FREEZE_FLAG");
			String NOTICE_NUM = (String)dtlMode.get("NOTICE_NUM");
			BigDecimal FREEZE_NUM = (BigDecimal)adverDtlMap.get("FREEZE_NUM");
			BigDecimal FREEZE_TO_SEND_NUM = (BigDecimal)adverDtlMap.get("FREEZE_TO_SEND_NUM");
			setFreeNumAndToSendNum(IS_FREEZE_FLAG.toString(),NOTICE_NUM.toString(),FREEZE_NUM.toString(),FREEZE_TO_SEND_NUM.toString(),advcAppMap,advcDtlMap);
			advcAppMap.put("ADVC_SEND_REQ_DTL_ID", ADVC_SEND_REQ_DTL_ID);
			advcDtlMap.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
			if("0".equals(IS_FREEZE_FLAG.toString())){
				update("Advcgoodsapp.updateAdvcAppById", advcAppMap);
			}else{
				update("Advcgoodsapp.updateAdvcById", advcDtlMap);
				update("Advcgoodsapp.updateAdvcAppById", advcAppMap);
			}
		}
	}
	
	 /**
     * 设置冻结数量和可转冻结数量
     */
    private void setFreeNumAndToSendNum(String IS_FREEZE_FLAG,String NOTICE_NUM ,String FREEZE_NUM,String FREEZE_TO_SEND_NUM,HashMap advcAppMap,HashMap advcDtl){
    	float I_CURR_FREEZE_NUM = 0;             //发货申请单  本次冻结数量
    	float I_CURR_FREEZE_TO_SEND_NUM = 0;    //发货申请单   冻结转发货数量
    	float I_ADVC_FREEZE_TO_SEND_NUM = 0;   //预订单中冻结转发货数量
    	float I_FREEZE_NUM = Float.parseFloat(FREEZE_NUM);
    	float I_NOTICE_NUM = Float.parseFloat(NOTICE_NUM);
    	float I_FREEZE_TO_SEND_NUM = Integer.parseInt(FREEZE_TO_SEND_NUM);
    	float I_CURR_SEND_NUM = I_FREEZE_NUM - I_FREEZE_TO_SEND_NUM;
    	if("0".equals(IS_FREEZE_FLAG)){
    		I_CURR_FREEZE_NUM = I_NOTICE_NUM;
    		advcAppMap.put("FREEZE_NUM", String.valueOf(I_CURR_FREEZE_NUM));
    	}else if(I_NOTICE_NUM>I_CURR_SEND_NUM){
    		I_CURR_FREEZE_NUM = I_NOTICE_NUM - I_CURR_SEND_NUM;
    		I_CURR_FREEZE_TO_SEND_NUM = I_FREEZE_NUM - I_FREEZE_TO_SEND_NUM;
    		I_ADVC_FREEZE_TO_SEND_NUM = I_FREEZE_TO_SEND_NUM + I_CURR_SEND_NUM;
    		advcAppMap.put("FREEZE_NUM", String.valueOf(I_CURR_FREEZE_NUM));
    		advcAppMap.put("FREEZE_TO_SEND_NUM", String.valueOf(I_CURR_FREEZE_TO_SEND_NUM));
    		advcDtl.put("FREEZE_TO_SEND_NUM", I_ADVC_FREEZE_TO_SEND_NUM);
    	}else if(I_NOTICE_NUM<=I_CURR_SEND_NUM){
    		I_CURR_FREEZE_NUM = 0;
    		I_CURR_FREEZE_TO_SEND_NUM = I_NOTICE_NUM;
    		I_ADVC_FREEZE_TO_SEND_NUM = I_FREEZE_TO_SEND_NUM +I_NOTICE_NUM;
    		advcAppMap.put("FREEZE_NUM", String.valueOf(I_CURR_FREEZE_NUM));
    		advcAppMap.put("FREEZE_TO_SEND_NUM", String.valueOf(I_CURR_FREEZE_TO_SEND_NUM));
    		advcDtl.put("FREEZE_TO_SEND_NUM", I_ADVC_FREEZE_TO_SEND_NUM);
    	}
    	
    }
    
    @SuppressWarnings("unchecked")
	private String checkOrderNum(String ADVC_SEND_REQ_ID){
    	List chldList = childQuery(ADVC_SEND_REQ_ID);
    	for(int i=0;chldList!=null && i<chldList.size();i++){
    		HashMap dtlMode = (HashMap)chldList.get(i);
			String FROM_BILL_DTL_ID = (String)dtlMode.get("FROM_BILL_DTL_ID");
			Map adverDtlMap =  (Map)load("Advcgoodsapp.queryAdvcdtlById", FROM_BILL_DTL_ID);
			BigDecimal NO_SEND_NUM =  (BigDecimal)adverDtlMap.get("NO_SEND_NUM");
			double I_ORDER_NUM = NO_SEND_NUM.doubleValue();
			String PRD_ID = (String)dtlMode.get("PRD_ID");
			String PRD_NO = (String)dtlMode.get("PRD_NO");
			String SPCL_TECH_ID = (String)dtlMode.get("SPCL_TECH_ID");
			if("".equals(SPCL_TECH_ID)){
				SPCL_TECH_ID = null;
			}
			HashMap checkMap = new HashMap();
			checkMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
			checkMap.put("PRD_ID", PRD_ID);
			checkMap.put("FROM_BILL_DTL_ID", FROM_BILL_DTL_ID);
			
			
			Map allOrderNum = queryAllOrderNum(checkMap);
			BigDecimal ALL_NUM = (BigDecimal)allOrderNum.get("ALL_NUM");
			double I_ALL_NUM = ALL_NUM.doubleValue();
			if((I_ORDER_NUM - I_ALL_NUM) <0){
				return "货品编号："+PRD_NO+",当前申请发货数量超过最大可发数量，不能发货！";
			}
		}
		return "true";
    }
	
	@SuppressWarnings("unchecked")
	private String checkOrderNum(List<AdvcgoodsappModelChld> chldList){
		for(int i=0;chldList!=null && i<chldList.size();i++){
			AdvcgoodsappModelChld dtlMode = chldList.get(i);
			String FROM_BILL_DTL_ID = dtlMode.getFROM_BILL_DTL_ID();
			Map adverDtlMap =  (Map)load("Advcgoodsapp.queryAdvcdtlById", FROM_BILL_DTL_ID);
			BigDecimal NO_SEND_NUM =  (BigDecimal)adverDtlMap.get("NO_SEND_NUM");
			int I_ORDER_NUM = NO_SEND_NUM.intValue();
			String PRD_ID = dtlMode.getPRD_ID();
			String PRD_NO = dtlMode.getPRD_NO();
			String SPCL_TECH_ID = dtlMode.getSPCL_TECH_ID();
			if("".equals(SPCL_TECH_ID)){
				SPCL_TECH_ID = null;
			}
			HashMap checkMap = new HashMap();
			checkMap.put("SPCL_TECH_ID", SPCL_TECH_ID);
			checkMap.put("PRD_ID", PRD_ID);
			checkMap.put("FROM_BILL_DTL_ID", FROM_BILL_DTL_ID);
			
			
			Map allOrderNum = queryAllOrderNum(checkMap);
			BigDecimal ALL_NUM = (BigDecimal)allOrderNum.get("ALL_NUM");
			int I_ALL_NUM = ALL_NUM.intValue();
			if((I_ORDER_NUM - I_ALL_NUM) <0){
				return "货品编号："+PRD_NO+",当前申请发货数量超过最大可发数量，不能发货！";
			}
		}
		return "true";
	}
	
	/**
	 * @详细
	 * @param param ADVC_SEND_REQ_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Advcgoodsapp.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param ADVC_SEND_REQ_ID the ADVC_SEND_REQ_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String ADVC_SEND_REQ_ID, List<AdvcgoodsappModelChld> chldList,String actionType,UserBean userBean) {
    	String SPCL_TECH_IDS="";
    	//修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        for (AdvcgoodsappModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    String ADVC_SEND_REQ_DTL_ID= model.getADVC_SEND_REQ_DTL_ID();
            String FROM_BILL_DTL_ID=model.getFROM_BILL_DTL_ID();
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("BRAND",model.getBRAND());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("NOTICE_NUM",model.getNOTICE_NUM());
            params.put("ADVC_SEND_REQ_ID",ADVC_SEND_REQ_ID);
            params.put("PRICE", model.getPRICE());
            params.put("DECT_RATE", model.getDECT_RATE());
            params.put("DECT_PRICE", model.getDECT_PRICE());
            params.put("DECT_AMOUNT", model.getDECT_AMOUNT());
            params.put("REMARK",model.getREMARK()); 
            params.put("FROM_BILL_DTL_ID", model.getFROM_BILL_DTL_ID());
            params.put("ORDER_RECV_DATE", model.getORDER_RECV_DATE());
            params.put("FREEZE_NUM", model.getFREEZE_NUM());
            params.put("PRD_TYPE", model.getPRD_TYPE());
            String SPCL_TECH_ID=model.getSPCL_TECH_ID();
            if(!StringUtil.isEmpty(SPCL_TECH_ID)){
            	SPCL_TECH_IDS+="'"+SPCL_TECH_ID+"',";
            }
            params.put("SPCL_TECH_ID", SPCL_TECH_ID);
          //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
        	if("list".equals(actionType)){
        		 //如果没有明细ID的则是新增，有的是修改
                if (StringUtil.isEmpty(ADVC_SEND_REQ_DTL_ID)) {
                	Map<String,String> map=new HashMap<String,String>();
                	map.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
                	map.put("ADVC_SEND_REQ_DTL_ID", ADVC_SEND_REQ_DTL_ID);
                	map.put("FROM_BILL_DTL_ID", model.getFROM_BILL_DTL_ID());
                	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
                	int count=countFrom(map);
                	if(count>0){
                		throw new ServiceException("对不起，明细存在货品重复，请重新选择 !");
                	}
                    params.put("ADVC_SEND_REQ_DTL_ID", StringUtil.uuid32len());
    		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                    addList.add(params);
                } else {
                    params.put("ADVC_SEND_REQ_DTL_ID", model.getADVC_SEND_REQ_DTL_ID());
                    updateList.add(params);
                }
        	}else{
            	Map<String,String> map=new HashMap<String,String>();
            	map.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
            	map.put("ADVC_SEND_REQ_DTL_ID", ADVC_SEND_REQ_DTL_ID);
            	map.put("FROM_BILL_DTL_ID", model.getFROM_BILL_DTL_ID());
            	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
            	int count=countFrom(map);
            	if(count>0){
            		throw new ServiceException("对不起，明细存在货品重复，请重新选择 !");
            	}
                params.put("ADVC_SEND_REQ_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
        	}
//            if(ORDER_NUM<Float.parseFloat(model.getNOTICE_NUM())){
//            	throw new ServiceException("通知出库数量不得大于订货数量 !");
//            }
        }
        if(!StringUtil.isEmpty(SPCL_TECH_IDS)){
        	SPCL_TECH_IDS=SPCL_TECH_IDS.substring(0,SPCL_TECH_IDS.length()-1);
        	upUSE_FLAG(SPCL_TECH_IDS);
        }
      //action类型，如果是在住子表编辑页面过来 则把明细全部删掉，重新新增，如果是明细编辑页面，则新增或修改明细
    	if(!"list".equals(actionType)){
    		robackFreeNumAndToSendNum(ADVC_SEND_REQ_ID);
    		this.delete("Advcgoodsapp.delDtl", ADVC_SEND_REQ_ID);
    	}
        if (!addList.isEmpty()) {
            this.batch4Update("Advcgoodsapp.insertChld", addList);
            handFreeNumAndToSendNum_ADD(addList);
        }
        if (!updateList.isEmpty()) {
        	robackFreeNumAndToSendNumByList(updateList);
            this.batch4Update("Advcgoodsapp.updateChldById", updateList);
            handFreeNumAndToSendNum_ADD(updateList);
            
        }
		String mssage = checkOrderNum(ADVC_SEND_REQ_ID);
		if(!"true".equals(mssage)){
			throw new ServiceException(mssage); 
		}
		txgetPAYABLE_AMOUNT(userBean, ADVC_SEND_REQ_ID);
        return true;
    }
    
    
   
	
	/**
     * * 根据主表Id查询子表记录
     * @param ADVC_SEND_REQ_ID the ADVC_SEND_REQ_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvcgoodsappModelChld> childQuery(String ADVC_SEND_REQ_ID) {
        Map params = new HashMap();
        params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询特殊工艺使用标记为1的
		params.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
        return this.findList("Advcgoodsapp.queryChldByFkId", params);
    }
    //check 选择的预订是否存在变更
    public String checkChangeOrder(String ADVC_ORDER_ID) {
          List changOrderList = this.findList("Advcgoodsapp.checkChangeOrder", ADVC_ORDER_ID);
          if(changOrderList!=null && changOrderList.size()>0){
        	  HashMap changeMap = (HashMap)changOrderList.get(0);
        	  String ADVC_ORDER_CHANGE_NO = (String)changeMap.get("ADVC_ORDER_CHANGE_NO");
        	  return "存在有没处理完成的预订单变更:"+ADVC_ORDER_CHANGE_NO+",不能发货申请!";
          }
          return "true";
    }
    
	/**
     * * 根据主表Id查询主表记录
     * @param ADVC_SEND_REQ_ID the ADVC_SEND_REQ_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvcgoodsappModel> mainQuery(String ADVC_SEND_REQ_ID) {
        Map params = new HashMap();
        params.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
        return this.findList("Advcgoodsapp.query", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param ADVC_SEND_REQ_DTL_IDs the ADVC_SEND_REQ_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvcgoodsappModelChld> loadChilds(Map <String, String> params) {
       return findList("Advcgoodsapp.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param ADVC_SEND_REQ_DTL_IDs the ADVC_SEND_REQ_DTL_IDs
     */
    @SuppressWarnings("unchecked")
    public void txBatch4DeleteChild(String ADVC_SEND_REQ_DTL_IDs,UserBean userBean,String ADVC_SEND_REQ_ID) {
       robackFreeNumAndToSendNumByIds(ADVC_SEND_REQ_DTL_IDs);
	   Map params = new HashMap();
	   params.put("ADVC_SEND_REQ_DTL_IDS", ADVC_SEND_REQ_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Advcgoodsapp.deleteChldByIds", params);
       this.txgetPAYABLE_AMOUNT(userBean, ADVC_SEND_REQ_ID);
    }
    public int countFrom(Map<String,String> map){
    	Object count =load("Advcgoodsapp.countFrom",map);
    	if(count==null){
    		count="0";
    	}
    	return Integer.parseInt(count.toString());
    }
  //反填特殊工艺使用标识
	public void upUSE_FLAG(String SPCL_TECH_IDS){
		Map<String,String> map=new HashMap<String,String>();
		map.put("SPCL_TECH_IDS", SPCL_TECH_IDS);
		map.put("USE_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		this.update("Advcgoodsapp.upUSE_FLAG", map);
	}
	
	@SuppressWarnings("unchecked")
	public List<AreaChrgModel> Query(String areaID){
   	 Map<String,String> params = new HashMap<String,String>();
        params.put("AREA_ID", areaID);
        return this.findList("AreaChrg.query", params);
   }
	public void insertTrace(Map<String,String> map){
		this.insert("Advcorder.insertTrace", map);
	}
	 /**
     * 求和更新应收总金额和出库总金额
     */
    public boolean txgetPAYABLE_AMOUNT(UserBean userBean,String ADVC_SEND_REQ_ID){
    	Map<String,Object> map=new HashMap<String,Object>();
    	double STOREOUT_AMOUNT=StringUtil.getDouble(load("Advcgoodsapp.getSTOREOUT_AMOUNT",ADVC_SEND_REQ_ID));
    	double PAYABLE_AMOUNT=StringUtil.getDouble(load("Advcgoodsapp.getPAYABLE_AMOUNT",ADVC_SEND_REQ_ID));
    	map.put("UPDATOR", userBean.getRYXXID());
        map.put("UPD_NAME", userBean.getXM());
        map.put("UPD_TIME", "数据库时间");
        map.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
        map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        map.put("PAYABLE_AMOUNT", PAYABLE_AMOUNT);
        map.put("STOREOUT_AMOUNT", STOREOUT_AMOUNT);
        return update("Advcgoodsapp.updateById", map);
    }


	public String qeuryId(String QUERYID) {
		Object id=load("Advcgoodsapp.queryDtlId",QUERYID);
		if(null==id){
			return null;
		}else{
			return  id.toString();
		}
	}
	
	//终端发货提交时生成出库单为已处理，已确认
	public void instStoreOut(String ADVC_SEND_REQ_ID,String STOREOUT_ID,UserBean userBean){
		List <AdvcgoodsappModelChld> chldList=this.childQuery(ADVC_SEND_REQ_ID);
		Map<String,String> map=this.load(ADVC_SEND_REQ_ID);
		//创建出库单map
		Map<String,Object> model=new HashMap<String,Object>();
		String STOREOUT_NO=LogicUtil.getBmgz("DRP_STOREOUT_NO");
		model.put("STOREOUT_ID", STOREOUT_ID);//出库单ID
		model.put("STOREOUT_NO", STOREOUT_NO);//出库单编号
		model.put("BILL_TYPE", "销售出库");//单据类型
		model.put("CONTRACT_NO", map.get("CONTRACT_NO"));//合同编号
		model.put("FROM_BILL_ID", map.get("ADVC_SEND_REQ_ID"));//来源单据id
		model.put("FROM_BILL_NO", map.get("ADVC_SEND_REQ_NO"));//来源单据编号
		model.put("SEND_CHANN_ID", map.get("SEND_CHANN_ID"));//发货方ID
		model.put("SEND_CHANN_NO", map.get("SEND_CHANN_NO"));//发货方编号
		model.put("SEND_CHANN_NAME", map.get("SEND_CHANN_NAME"));//发货方名称
		model.put("STOREOUT_STORE_ID", map.get("STOREOUT_STORE_ID"));//出库库房id
		model.put("STOREOUT_STORE_NO", map.get("STOREOUT_STORE_NO"));//出库库房编号
		model.put("DEAL_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);//处理标记
		model.put("DEAL_TIME", "数据库当前时间");//处理时间
		model.put("DEAL_PSON_ID", userBean.getRYXXID());//处理人id
		model.put("DEAL_PSON_NAME", userBean.getXM());//处理人名称
		model.put("STOREOUT_STORE_NAME", map.get("STOREOUT_STORE_NAME"));//出库库房名称
		model.put("REMARK", map.get("REMARK"));
		model.put("TERM_ID", map.get("TERM_ID"));//终端信息id
		model.put("TERM_NO", map.get("TERM_NO"));//终端编号
		model.put("TERM_NAME", map.get("TERM_NAME"));//终端名称
		model.put("SALE_DATE", map.get("SALE_DATE"));//销售日期
		model.put("SALE_PSON_ID", map.get("SALE_PSON_ID"));//业务员id
		model.put("SALE_PSON_NAME", map.get("SALE_PSON_NAME"));//业务员名称
		model.put("CUST_NAME", map.get("CUST_NAME"));//客户名称
		model.put("TEL", map.get("TEL"));//电话
		model.put("RECV_ADDR", map.get("RECV_ADDR"));//收货地址
		model.put("ORDER_RECV_DATE", map.get("ORDER_RECV_DATE"));//要求到货日期
		model.put("ADVC_AMOUNT", map.get("ADVC_AMOUNT"));//订金金额
		model.put("RECV_FLAG", BusinessConsts.YJLBJ_FLAG_TRUE);
		model.put("RECV_TIME", "sysdate");
		model.put("RECV_PSON_ID", userBean.getRYXXID());
		model.put("RECV_PSON_NAME", userBean.getXM());
		model.put("STOREOUT_AMOUNT", map.get("STOREOUT_AMOUNT"));//出库总金额
		model.put("PAYABLE_AMOUNT", map.get("PAYABLE_AMOUNT"));//应收总额
		model.put("PAYED_TOTAL_AMOUNT", map.get("PAYED_TOTAL_AMOUNT"));//已付款金额
		model.put("STATE", BusinessConsts.DONE);//状态
		model.put("CRE_NAME",userBean.getXM());//制单人名称
		model.put("CREATOR",userBean.getXTYHID());//制单人ID
		model.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		model.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		model.put("ORG_ID",userBean.getJGXXID());//制单机构ID
		model.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		model.put("CRE_TIME","sysdate");//制单时间
		model.put("LEDGER_ID", userBean.getCHANN_ID());//帐套ID
		model.put("LEDGER_NAME",userBean.getCHANN_NAME());//帐套名称
		model.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
		List<Map<String,String>> chld=new ArrayList<Map<String,String>>();
		for (int j=0;j<chldList.size();j++) {
			Map<String,String> chldMap=(Map<String, String>) chldList.get(j);
    		Map<String,String> params=new HashMap<String,String>();
    		String STOREOUT_DTL_ID=StringUtil.uuid32len();
            params.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
            params.put("STOREOUT_ID", STOREOUT_ID);
            params.put("PRD_ID", chldMap.get("PRD_ID"));
            params.put("PRD_NO", chldMap.get("PRD_NO"));
            params.put("PRD_NAME", chldMap.get("PRD_NAME"));
            params.put("PRD_SPEC", chldMap.get("PRD_SPEC"));
            params.put("PRD_COLOR", chldMap.get("PRD_COLOR"));
            params.put("BRAND", chldMap.get("BRAND"));
            params.put("STD_UNIT", chldMap.get("STD_UNIT"));
            params.put("REMARK", chldMap.get("REMARK"));
            params.put("PRD_TYPE", chldMap.get("PRD_TYPE"));
		    params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		    params.put("SPCL_TECH_ID", chldMap.get("SPCL_TECH_ID"));
		    params.put("FROM_BILL_DTL_ID", chldMap.get("ADVC_SEND_REQ_DTL_ID"));
		    Object obj=chldMap.get("NOTICE_NUM");
		    params.put("NOTICE_NUM",obj.toString());
		    params.put("RECV_NUM", obj.toString());
		    params.put("REAL_NUM", obj.toString());
		    params.put("FREEZE_NUM","0");
		    obj=chldMap.get("PRICE");
		    params.put("PRICE", obj.toString());
		    obj=chldMap.get("DECT_RATE");
		    params.put("DECT_RATE", obj.toString());
		    obj=chldMap.get("DECT_PRICE");
		    params.put("DECT_PRICE", obj.toString());
		    obj=chldMap.get("DECT_AMOUNT");
		    params.put("DECT_AMOUNT", obj.toString());
		    
            chld.add(params);
		}
		insert("Advcgoodsapp.insertStore", model);
		
		if (!chld.isEmpty()) {
            this.batch4Update("Advcgoodsapp.insertStoreChld", chld);
        }
	}
	public boolean upOrderDate(Map<String,String> map){
		return this.update("Advcgoodsapp.updateById", map);
	}
	 public int checkAmount(String ADVC_SEND_REQ_ID){
		 Map<String,String> map=new HashMap<String, String>();
		 map.put("ADVC_SEND_REQ_ID", ADVC_SEND_REQ_ID);
		 map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		 return StringUtil.getInteger(this.load("Advcgoodsapp.checkAmount", map));
	 }
	 public String checkAdvcState(String ADVC_SEND_REQ_ID,String STATE){
		 Map<String,String> checkModel = this.load(ADVC_SEND_REQ_ID);
		 String advcState=checkModel.get("STATE");
		 boolean flag=true;
		 if("提交".equals(STATE)&&!("未提交".equals(advcState)||"退回".equals(advcState))){
			 flag=false;
		 }else if("退回".equals(STATE)&&!"提交".equals(advcState)){
			 flag=false;
		 }else if("审核通过".equals(STATE)&&!"提交".equals(advcState)){
			 flag=false;
		 }else if("已发货".equals(STATE)&&!"未提交".equals(advcState)){
			 flag=false;
		 }
		 if(!flag){
			 return "该单据已被"+checkModel.get("UPD_NAME")+"操作，当前状态为"+advcState+"，不能"+STATE;
		 }else{
			 return null;
		 }
	 }
}