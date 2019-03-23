/**
 * prjName:喜临门营销平台
 * ucName:出库单处理
 * fileName:StoreoutServiceImpl
*/
package com.hoperun.drp.store.storeout.service.impl;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.model.MsgInfo;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storeout.model.StoreoutModel;
import com.hoperun.drp.store.storeout.model.StoreoutModelChld;
import com.hoperun.drp.store.storeout.model.StoreoutModelGrandChld;
import com.hoperun.drp.store.storeout.service.StoreoutService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-09-15 14:59:50
 */
public class StoreoutServiceImpl extends BaseService implements StoreoutService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Storeout.pageQuery", "Storeout.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Storeout.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREOUT_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Storeout.delete", params);
		 //删除子表 
		 return update("Storeout.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Storeout.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREOUT_ID
	 * @param StoreoutconfirmModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREOUT_ID, StoreoutModel model,List<StoreoutModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(STOREOUT_ID)){
			STOREOUT_ID= StringUtil.uuid32len();
			params.put("STOREOUT_ID", STOREOUT_ID);
		    params.put("CRE_NAME",userBean.getXM());
		    params.put("CREATOR",userBean.getXTYHID());
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("DEPT_ID",userBean.getBMXXID());
		    params.put("DEPT_NAME",userBean.getBMMC());
		    params.put("ORG_ID",userBean.getJGXXID());
		    params.put("ORG_NAME",userBean.getJGMC());
		    params.put("LEDGER_ID",userBean.getLoginZTXXID());
		    params.put("LEDGER_NAME",userBean.getLoginZTMC());
			params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("STOREOUT_ID", STOREOUT_ID);
			txUpdateById(params);
			clearCaches(STOREOUT_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STOREOUT_ID, chldList,userBean);
		}
	}
	
	/**
	 * @详细
	 * @param param STOREOUT_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public HashMap<String,String> load(String param) {
		return (HashMap<String,String>) load("Storeout.loadById", param);
	}
	
	
	/**
	 * 加载渠道
	 * @param CHANN_ID
	 * @return
	 */
	public Map<String,String> loadChann(String CHANN_ID){
		Map<String, String> params = new HashMap<String, String>();
        params.put("CHANN_ID", CHANN_ID);
        return (Map<String, String>) load("chann.loadById", params);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STOREOUT_ID the STOREOUT_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREOUT_ID, List<StoreoutModelChld> chldList,UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StoreoutModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("REAL_NUM",model.getREAL_NUM());
            params.put("STOREOUT_ID",STOREOUT_ID); 
            String dectPrice = model.getDECT_PRICE();
            String realNum = model.getREAL_NUM();
            double dectAmount = multiply(realNum,dectPrice);
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTOREOUT_DTL_ID())) {
                params.put("STOREOUT_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREOUT_DTL_ID", model.getSTOREOUT_DTL_ID());
                params.put("DECT_AMOUNT",getFromDecimal(dectAmount));
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storeout.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storeout.insertChld", addList);
        }
        updateStoreOut(STOREOUT_ID,userBean);
        return true;
    }
    
    
    public boolean txScanChildEdit(String STOREOUT_ID, List<StoreoutModelChld> chldList,UserBean userBean) {
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StoreoutModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("STOREOUT_ID",STOREOUT_ID); 
            params.put("STOREOUT_DTL_ID", model.getSTOREOUT_DTL_ID());
            params.put("SN", model.getSN());
            params.put("REAL_NUM", model.getREAL_NUM());
            updateList.add(params);
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storeout.updateScanChldById", updateList);
            updateStoreOut(STOREOUT_ID,userBean);
        } 
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<StoreoutModelChld>  childQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeout.queryChldByFkId", params);
    }
    
	/**
     * * 根据主表Id查询子表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreoutModelChld> childQueryById(String STOREOUT_DTL_IDS) {
        Map params = new HashMap();
        params.put("STOREOUT_DTL_IDS", STOREOUT_DTL_IDS);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeout.loadChldByIds", params);
    }
    
    
	/**
     * * 根据主表Id查询主表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreoutModel> mainQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        return this.findList("Storeout.query", params);
    }
    /**
     * * 根据主表Id查询主表记录
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreoutModel> mainSaleToLowerQuery(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        return this.findList("Storeout.querySaleToLower", params);
    }
    
    

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreoutModelChld> loadChilds(Map <String, String> params) {
       return findList("Storeout.loadChldByIds",params);
    }
	
    /**
     * * 库存信息子表批量删除:软删除
     * 
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String STOREOUT_STORG_DTL_IDS) {
	   Map params = new HashMap();
	   params.put("STOREOUT_STORG_DTL_IDS", STOREOUT_STORG_DTL_IDS);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storeout.deleteStoregChldByIds", params);
    }
    
    public List <StoreoutModelGrandChld> storgChildQuery(Map <String, String> params) {
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeout.queryStorgChldByFkId", params);
    }
    
    /**
     * * 库位信息保存
     * 
     * @param STOREOUT_DTL_IDs the STOREOUT_DTL_IDs
     */
    public boolean txBatchStorgEdit(String storeoutId,String storeoutDtlId, List<StoreoutModelGrandChld> chldList,UserBean userBean) {
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        int intRealNum = 0;
        for (StoreoutModelGrandChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            if (StringUtil.isEmpty(model.getSTOREOUT_STORG_DTL_ID())) {
            	params = new HashMap <String, String>();
                params.put("STOREOUT_STORG_DTL_ID", StringUtil.uuid32len());
                params.put("STOREOUT_ID",storeoutId); 
                params.put("STOREOUT_DTL_ID",storeoutDtlId); 
            	params.put("PRD_ID",model.getPRD_ID());
                params.put("PRD_NO",model.getPRD_NO());
                params.put("PRD_NAME",model.getPRD_NAME());
                params.put("PRD_SPEC",model.getPRD_SPEC());
                params.put("PRD_COLOR",model.getPRD_COLOR());
                params.put("BRAND",model.getBRAND());
                params.put("STD_UNIT",model.getSTD_UNIT());
                params.put("STORG_ID",model.getSTORG_ID());
                params.put("STORG_NO",model.getSTORG_NO());
                params.put("STORG_NAME",model.getSTORG_NAME());
                params.put("REAL_NUM",model.getREAL_NUM());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
                intRealNum += Integer.parseInt(model.getREAL_NUM());
            } else {
            	
                params.put("STOREOUT_STORG_DTL_ID", model.getSTOREOUT_STORG_DTL_ID());
                params.put("REAL_NUM",model.getREAL_NUM());
                updateList.add(params);
                intRealNum += Integer.parseInt(model.getREAL_NUM());
            }
        }
        if (!updateList.isEmpty()) {
            this.batch4Update("Storeout.updateGrandChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storeout.insertGrandChld", addList);
        }
        updateStoreOutDtlNum(storeoutId,storeoutDtlId,intRealNum);
        return true;
    }
    
    /**更新主表信息
     * @param storeoutId
     * @param userBean
     */
    private void updateStoreOut(String storeoutId,UserBean userBean){
    	Map<String,String> upMap=new HashMap<String,String>();
        upMap.put("STOREOUT_ID", storeoutId);
        upMap.put("UPDATOR", userBean.getXTYHID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        update("Storeout.updateById",upMap);
    }
    
    /**更新明细表出库数量
     * @param storeoutId
     * @param storeoutDtlId
     * @param intRealNum
     */
    private void updateStoreOutDtlNum(String storeoutId,String storeoutDtlId,int intRealNum){
    	Map<String,String> upMap=new HashMap<String,String>();
    	upMap.put("STOREOUT_DTL_ID", storeoutDtlId);
    	upMap.put("STOREOUT_ID", storeoutId);
        upMap.put("REAL_NUM", String.valueOf(intRealNum));
        update("Storeout.updateChldById",upMap);
    }
    
    /**
     * 提交的时候判断
     * 出库数量不能大于预订单订货数量
     * (出库数量<=预订单订货数量-已发货数量)
     * @param STOREOUT_ID
     * @return
     */
    public Map<String,String> txCheckRealNumAndAdvcOrderNum(String STOREOUT_ID){
    	Map<String,String> params = new HashMap<String,String>();
    	//查询明细的预订单订货数量，已处理数量和当前数量
    	params.put("STOREOUT_ID", STOREOUT_ID);
    	List<Map<String,String>> list = this.findList("Storeout.checkRealNumAndAdvcOrderNum", params);
    	int index = 0;
    	if(null != list && !list.isEmpty()){
    		int size = list.size();
    		String STATE = null;
    		for(Map<String,String> map : list){
    			Integer FIRST_ORDER_NUM = StringUtil.getInteger(map.get("FIRST_ORDER_NUM"));    
        		Integer HAVE_DONE_REAL_NUM = StringUtil.getInteger(map.get("HAVE_DONE_REAL_NUM"));
        		Integer CURR_REAL_NUM = StringUtil.getInteger(map.get("CURR_REAL_NUM"));
        		//当前出库数量<=预订单订货数量-已发货数量
        		if(CURR_REAL_NUM <= (FIRST_ORDER_NUM-HAVE_DONE_REAL_NUM)){
        			index++;
        		}else {
        			return map;
        		}
    		}
    		
    		
    		
//    		if(index == size){
//    		   STATE = "已发货";
//    		}else if(index<size){
//    		   STATE = "部分发货";
//    		}
//    		
//    		//如果 出库数量 = 预订单订货数量-已发货数量
//    	    //更新预订单表的状态为已发货
//    	    //否则   状态为  部分发货
//    	    // 预订单查询也要加上部分发货
//    		if(!StringUtil.isEmpty(STATE)){
//    			params.put("STOREOUT_ID", STOREOUT_ID);
//    	    	params.put("STATE", STATE);
//    	    	update("Storeout.updateAdvcOrderByStoreoutId_",params);
//    		}
    		
    	}
		
    	return null;
    }
    
    
    /**销售出库处理
     * @param storeOutList
     * @param userBean
     */
    public void txSlCommit(List storeOutList, UserBean userBean,String storeOutId,List chldlist) throws Exception {
    	HashMap<String,String> storeOutMap =  (HashMap)storeOutList.get(0);
    	if(!checkOrderCancel(storeOutMap)){
    		throw new ServiceException("当前单据的正在做预订单发货取消，不能出库!");
    	}
         //根据出库单id验证来源单据是否只生成一张出库单
    	if(!checkAdvcSendRepeat(storeOutId)){
    		throw new ServiceException("所选单据存在重复来源，不能出库!");
    	}
    	//根据出库单id验证出库总金额是否大于预订单的订货总金额
//    	if(!checkStoreAmount(storeOutId)){
//    		throw new ServiceException("总出库金额大于订货总金额，不能出库!");
//    	}
    	//更新主表信息
    	updateStoreOutStats(storeOutMap,userBean);
    	//插预订单生命周期
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
    	params.put("ADVC_SEND_REQ_ID",storeOutMap.get("FROM_BILL_ID"));
    	params.put("ACTION", "销售收货确认");
    	params.put("ACTOR", userBean.getXM());
    	params.put("BILL_NO", storeOutMap.get("STOREOUT_NO"));
    	params.put("REMARK", "已生成");
    	insert("Storeout.insertAdvcOrderTrace", params);
    	//根据出库单id修改状态为已处理
    	params=new HashMap<String,String>();
    	params.put("STOREOUT_ID", storeOutId);
    	params.put("STATE", BusinessConsts.DONE);
    	update("Storeout.updateById",params);
    	//调接口 对库存进行扣减
    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);
    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);   	
		String checkStoreAcct = LogicUtil.doChkStoreNumByStoreOut(storeOutId);
    	if(!"true".equals(checkStoreAcct)){
    		throw new ServiceException(checkStoreAcct);
    	}
    	//回填预订单发货数量和解冻数量
    	for(int i=0 ;i<chldlist.size();i++){
    		HashMap chldMode = (HashMap)chldlist.get(i);
    		String STOREOUT_DTL_ID = (String)chldMode.get("STOREOUT_DTL_ID");
    		HashMap storeMap = new HashMap();
    		storeMap.put("STOREOUT_DTL_ID", STOREOUT_DTL_ID);
    	//  2015/10/26 lyg SLEEMON-37 sleemon项目系统优化问题集
    		//不管出货多少 都把冻结数量更新为0
//    		update("Storeout.updateAdvcSendNumAndUnFreeNum",storeMap);
    		update("Storeout.updateAdvcSendNumAndUnFreeNumIsZero",storeMap);
    		update("Storeout.updateAdvcAppSendNum",storeMap);
    	}
    	
    	//更新预订单状态和预订单发货申请状态 //理新发货申请单和预订单的状态
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("FROM_BILL_ID", storeOutMap.get("FROM_BILL_ID"));
//    	modify by zzb 2014-7-21 放在提交判断的时候更新状态 方法checkRealNumAndAdvcOrderNum()
    	
    	//去掉预订单修改多余状态
    	Map advcMap = (Map)this.load("Storeout.queryAdvcSendNumAndOrderNum", paramMap);
    	BigDecimal SEND_NUM = (BigDecimal)advcMap.get("SEND_NUM");
    	BigDecimal ORDER_NUM = (BigDecimal)advcMap.get("ORDER_NUM");
    	if(SEND_NUM.intValue()== ORDER_NUM.intValue()){
    	   	paramMap.put("STATE", "已发货");
    		update("Storeout.updateAdvcOrderById",paramMap);
    	}
//    		else if(SEND_NUM.intValue()>0){
//    		paramMap.put("STATE", "部分发货");
//    		update("Storeout.updateAdvcOrderById",paramMap);
//    	}
    	
    	//  2015/10/26 lyg SLEEMON-37 sleemon项目系统优化问题集
    	//预订单发货申请中，若实际扫码出库的数量并未达到预订单中商品的数量，同样需改变状态为已发货，并且把冻结数量改为0
    	
//      	Map advcSendAppMap = (Map)this.load("Storeout.queryAdvcAppSendNumAndOrderNum", paramMap);
//    	BigDecimal NOTICE_NUM = (BigDecimal)advcSendAppMap.get("NOTICE_NUM");
//    	BigDecimal REAL_NUM = (BigDecimal)advcSendAppMap.get("REAL_NUM");
//    	if(NOTICE_NUM.intValue()==REAL_NUM.intValue()){
	   	paramMap.put("STATE", "已发货");
		update("Storeout.updateAdvcSendById",paramMap);
		//修改预订单发货申请冻结数量为0
		update("Storeout.updateAdvcDtlFreezeNum",paramMap);
//    	}
    	//根据出库单id验证出库总数量不大于订货总数量
//    	if(!checkStoreNum(storeOutId)){
//    		throw new ServiceException("总出库数量大于订货总数量，不能出库!");
//    	}
    }
    
    
    /**零星出库处理
     * @param storeOutList
     * @param userBean
     */
    public MsgInfo txLxCommit(List storeOutList, UserBean userBean,String storeOutId) throws Exception {
    	HashMap<String,String> storeOutMap =  (HashMap)storeOutList.get(0);
    	//调接口 对库存进行扣减
    	String checkStoreAcctJson = LogicUtil.doChkCanUseStoreNum(BusinessConsts.STOREOUT_ORDER_CONF6_ID, storeOutId, BusinessConsts.STORE_DESC);
    	MsgInfo mess = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
        }.getType());
    	if(mess.isFLAG()){
	    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF6_ID,storeOutId);
	    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF6_ID,storeOutId);
	    	//更新主表信息
	    	updateStoreOutStats(storeOutMap,userBean);
    	}
    	return mess;
    }
    
    
    private boolean checkOrderCancel(HashMap<String,String> storeOutMap ){
    	String storeOutId = storeOutMap.get("STOREOUT_ID");
    	HashMap param = new HashMap();
    	param.put("STOREOUT_ID", storeOutId);
    	HashMap cancelMap = (HashMap<String,String>) load("Storeout.checkCaneclOrder", storeOutId);
    	BigDecimal CANCEL_NUM =(BigDecimal)cancelMap.get("CANCEL_NUM");
    	if(!"0".equals(CANCEL_NUM.toString())){
    		return false;
    	}
    	return true;
    }
    
    /**退货出库处理
     * @param storeOutList
     * @param userBean
     */
    public void txThCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId) throws Exception {
    	//调接口　进行　插流水账，扣减实时账
    	HashMap<String,String> storeOutMap =  (HashMap)storeOutList.get(0);
    	updateStoreOutStats(storeOutMap,userBean);
    	updateDrpReturnNum(storeOutMap,storeOutDtlList);
    	genErpReturnOrder(storeOutMap,storeOutDtlList,userBean);
    	//调接口 对库存进行扣减
    	 // modify by jack 2014-6-19 cehck库存  start
    	String checkStoreAcctJson = LogicUtil.doChkCanUseStoreNum(BusinessConsts.STOREOUT_ORDER_CONF2_ID, storeOutId, BusinessConsts.STORE_DESC);
    	MsgInfo model = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
        }.getType());
    	if(model.isFLAG()){
    		LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF2_ID,storeOutId);
    		LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF2_ID,storeOutId);
    	}else{
			throw new ServiceException(model.getMESS());
    	}
    	 // modify by jack 2014-6-19  end
    	
    }
    
    
    /**区域服务中心退货出库处理
     * @param storeOutList
     * @param userBean
     */
    public MsgInfo txAreaSerStoreOutDone(HashMap<String,String> storeOutMap,List<StoreoutModelChld> storeOutDtlList, UserBean userBean,String storeOutId)  throws Exception{
    	String checkStoreAcctJson = LogicUtil.doChkCanUseStoreNum(BusinessConsts.STOREOUT_ORDER_CONF5_ID, storeOutId, BusinessConsts.STORE_DESC);
    	MsgInfo mess = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
        }.getType());
    	if(mess.isFLAG()){
	    	//调接口　进行　插流水账，扣减实时账
	    	String billType = "下级退货入库";
			updateStoreOutStats(storeOutMap,userBean);
	    	updateDrpAllCateNum(storeOutMap,storeOutDtlList);
	    	genDrpStoreInNotice(billType,storeOutMap,storeOutDtlList,userBean,"");
	    	//调接口 对库存进行扣减
	    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF5_ID,storeOutId);
	    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF5_ID,storeOutId);
    	}
    	return mess;
    	
    }
    
    /**调拨出库处理
     * @param storeOutList
     * @param userBean
     */
    public MsgInfo txDbCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId)  throws Exception{
    	String checkStoreAcctJson = LogicUtil.doChkCanUseStoreNum(BusinessConsts.STOREOUT_ORDER_CONF_ID, storeOutId, BusinessConsts.STORE_DESC);
    	MsgInfo mess = new Gson().fromJson(checkStoreAcctJson, new TypeToken <MsgInfo>() {
        }.getType());
    	if(mess.isFLAG()){
	    	//调接口　进行　插流水账，扣减实时账
	    	String billType = "调拨入库";
	    	HashMap<String,String> storeOutMap =  (HashMap)storeOutList.get(0);
			updateStoreOutStats(storeOutMap,userBean);
	    	updateDrpAllCateNum(storeOutMap,storeOutDtlList);
	    	genDrpStoreInNotice(billType,storeOutMap,storeOutDtlList,userBean,"D_BILL_TYPE");
	    	//调接口 对库存进行扣减
	    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);
	    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);
    	}
    	return mess;
    	
    }
    
    /**分发出库处理
     * @param storeOutList
     * @param userBean
     */
    public void txFFCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId) throws Exception {
    	//调接口　进行　插流水账，扣减实时账
    	String billType = "分发入库";
    	HashMap<String,String> storeOutMap =  (HashMap)storeOutList.get(0);
    	updateStoreOutStats(storeOutMap,userBean);
    	updateDrpDstrNum(storeOutMap,storeOutDtlList);
    	//更新分发出库的出库总金额
    	this.update("Storeout.upSTOREOUT_AMOUNT", storeOutId);
    	genDrpStoreInNotice(billType,storeOutMap,storeOutDtlList,userBean,"");
    	//调接口 对库存进行扣减
    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);
    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);
    }
    
    
    /**返修出库处理
     * @param storeOutList
     * @param userBean
     */
    public void txFXCommit(List storeOutList,List storeOutDtlList, UserBean userBean,String storeOutId) throws Exception {
    	HashMap<String,String> storeOutMap =  (HashMap)storeOutList.get(0);
    	updateStoreOutStats(storeOutMap,userBean);
    	updateRepairOrder(storeOutMap,userBean);
    	//调接口 对库存进行扣减
    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF3_ID,storeOutId);
    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF3_ID,storeOutId);
		String checkStoreAcct = LogicUtil.doChkStoreNumByStoreOut(storeOutId);
    	if(!"true".equals(checkStoreAcct)){
    		throw new ServiceException(checkStoreAcct);
    	}
    }
    
    /**
     * 更新返修单状态
     */
    private void updateRepairOrder(HashMap<String,String> storeOutMap,UserBean userBean){
    	//更新反修单状态
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("ERP_REPAIR_ORDER_ID",storeOutMap.get("FROM_BILL_ID"));
    	params.put("UPDATOR", userBean.getXTYHID());
        params.put("UPD_NAME", userBean.getYHM());
        params.put("UPD_TIME", "数据库时间");
        params.put("STATE", "已发货");
    	update("Storeout.updateRepairOrder",params);
    }
    
    /**更新主表的更新人更新时间状态
     * @param storeOutMap
     * @param userBean
     */
    private void updateStoreOutStats(HashMap<String,String> storeOutMap,UserBean userBean){
    	//更新主表信息
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("DEAL_FLAG","1");
    	params.put("DEAL_PSON_ID", userBean.getXTYHID());
    	params.put("DEAL_PSON_NAME", userBean.getXM());
        params.put("DEAL_TIME", "数据库时间");
        params.put("STATE", "已处理");
    	params.put("STOREOUT_ID", storeOutMap.get("STOREOUT_ID"));
    	update("Storeout.updateDealById",params);
    }
    
    
    /**更新分销退货申请单状态的实际出库数量
     * 
     */
    private void updateDrpReturnNum(HashMap<String,String>storeOutMap,List storeOutDtlList){
    	ArrayList returnDtlList = new ArrayList();
    	String drpReturnId =  storeOutMap.get("FROM_BILL_ID");
    	for(int i=0;i<storeOutDtlList.size();i++){
    		HashMap storeOutDtlMap =  (HashMap) storeOutDtlList.get(i);
    		String drpReturnDtlId =  (String)storeOutDtlMap.get("FROM_BILL_DTL_ID");
    		BigDecimal strRealNum = (BigDecimal)storeOutDtlMap.get("REAL_NUM");
        	Map<String,String> params=new HashMap<String,String>();
        	params.put("PRD_RETURN_DTL_REQ_ID",drpReturnDtlId);
        	params.put("PRD_RETURN_REQ_ID",drpReturnId);
        	params.put("REAL_NUM",strRealNum.toString());
        	returnDtlList.add(params);
    	}
    	this.batch4Update("Storeout.updateReturnRealNum", returnDtlList);
    }
    
    /**生成总部退货单
     * 
     */
    private void genErpReturnOrder(HashMap<String,String> storeOutMap,List storeOutDtlList, UserBean userBean){
    	String pdtReturnOrderId = StringUtil.uuid32len();
    	instReturnOrder(pdtReturnOrderId,storeOutMap,userBean);
    	instReturnOrderDtl(pdtReturnOrderId,storeOutDtlList);
    }
    
    /**插入退货单主表
     * 
     */
    private void instReturnOrder(String pdtReturnId, HashMap<String,String> storeOutMap, UserBean userBean){
    	Map<String,String> params=new HashMap<String,String>();
    	String pdtReturnNo = LogicUtil.getBmgz("ERP_PRD_RETURN_NO");
    	params.put("PRD_RETURN_ID", pdtReturnId);
    	params.put("PRD_RETURN_NO", pdtReturnNo);
    	String PRD_RETURN_REQ_ID=storeOutMap.get("FROM_BILL_ID");
    	String BILL_TYPE=(String) this.load("Storeout.getReturnBillType",PRD_RETURN_REQ_ID);
    	params.put("BILL_TYPE", BILL_TYPE);
    	params.put("FROM_BILL_ID", storeOutMap.get("STOREOUT_ID"));
    	params.put("FROM_BILL_NO", storeOutMap.get("STOREOUT_NO"));
    	params.put("RETURN_CHANN_ID", storeOutMap.get("SEND_CHANN_ID"));
    	params.put("RETURN_CHANN_NO", storeOutMap.get("SEND_CHANN_NO"));
    	params.put("RETURN_CHANN_NAME", storeOutMap.get("SEND_CHANN_NAME"));
    	params.put("RECV_CHANN_ID", storeOutMap.get("RECV_CHANN_NO"));
    	params.put("RECV_CHANN_NO", storeOutMap.get("RECV_CHANN_NO"));
    	params.put("RECV_CHANN_NAME", storeOutMap.get("RECV_CHANN_NAME"));
    	params.put("STATE", "未提交");
    	params.put("CREATOR", userBean.getXTYHID());
        params.put("CRE_NAME", userBean.getYHM());
        params.put("DEPT_ID", userBean.getBMXXID());
        params.put("DEPT_NAME", userBean.getBMMC());
        params.put("ORG_ID", userBean.getJGXXID());
        params.put("ORG_NAME", userBean.getJGMC());
        params.put("LEDGER_ID", userBean.getBASE_CHANN_ID());
        params.put("LEDGER_NAME", userBean.getBASE_CHANN_NAME());
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        params.put("REMARK", storeOutMap.get("REMARK"));
        insert("Storeout.instPdtReturnReq", params);
    }
    
    /**插入退货单明细表
     * 
     */
    private void instReturnOrderDtl(String pdtReturnId,List storeOutDtlList){
    	
    	ArrayList returDtlList = new ArrayList();
    	for(int i=0;i<storeOutDtlList.size();i++){
    		HashMap storeOutDtlMap = (HashMap)storeOutDtlList.get(i);
    		Map<String,String> params=new HashMap<String,String>();
        	String pdtReturnDtlId = StringUtil.uuid32len();
        	params.put("PRD_RETURN_DTL_ID", pdtReturnDtlId);
        	params.put("PRD_RETURN_ID", pdtReturnId);
        	params.put("FROM_BILL_DTL_ID", (String)storeOutDtlMap.get("STOREOUT_DTL_ID"));
        	params.put("PRD_ID", (String)storeOutDtlMap.get("PRD_ID"));
        	params.put("PRD_NO", (String)storeOutDtlMap.get("PRD_NO"));
        	params.put("PRD_NAME", (String)storeOutDtlMap.get("PRD_NAME"));
        	params.put("PRD_SPEC", (String)storeOutDtlMap.get("PRD_SPEC"));
        	params.put("PRD_COLOR", (String)storeOutDtlMap.get("PRD_COLOR"));
        	params.put("BRAND", (String)storeOutDtlMap.get("BRAND"));
        	params.put("STD_UNIT", (String)storeOutDtlMap.get("STD_UNIT"));
        	BigDecimal strDetctPrice =  (BigDecimal)storeOutDtlMap.get("DECT_PRICE");
        	double dDetctPrice =0.00;
        	if(strDetctPrice!=null){
        		dDetctPrice = strDetctPrice.doubleValue();
        	}
        	params.put("RETURN_PRICE",getFromDecimal(dDetctPrice));
        	BigDecimal realNum = (BigDecimal)storeOutDtlMap.get("REAL_NUM");
        	params.put("RETURN_NUM", realNum.toString());
        	BigDecimal dectPrice =  (BigDecimal)storeOutDtlMap.get("DECT_PRICE");
        	double returnAmout = multiply(realNum.toString(),dectPrice.toString());
        	params.put("RETURN_AMOUNT",getFromDecimal(returnAmout));
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        	params.put("SN", (String)storeOutDtlMap.get("SN"));
        	params.put("SPCL_TECH_ID", (String)storeOutDtlMap.get("SPCL_TECH_ID"));
        	params.put("PRD_INV_DTL_ID", (String)storeOutDtlMap.get("FROM_BILL_DTL_ID"));
        	params.put("PRD_RETURN_DTL_REQ_ID", (String)storeOutDtlMap.get("FROM_BILL_DTL_ID"));
        	returDtlList.add(params);
    	}
    	this.batch4Update("Storeout.instPdtReturnDtlReq", returDtlList);
    }
    
    
    /**更新分销调拨单的实际出库数量
     * 
     */
    private void updateDrpAllCateNum(HashMap<String,String>storeOutMap,List<StoreoutModelChld> storeOutDtlList){
    	ArrayList returnDtlList = new ArrayList();
    	String drpAllcateId =  storeOutMap.get("FROM_BILL_ID");
    	for(int i=0;i<storeOutDtlList.size();i++){
    		StoreoutModelChld storeOutDtlMap = storeOutDtlList.get(i);
    		String drpAllcateDtlId =  (String)storeOutDtlMap.getFROM_BILL_DTL_ID();
    		Object strRealNum = storeOutDtlMap.getREAL_NUM();
    		if(null==strRealNum){
    			throw new ServiceException("还有未扫码的货品,请进行扫码!");
    		}
        	Map<String,String> params=new HashMap<String,String>();
        	params.put("ALLOCATE_DTL_ID",drpAllcateDtlId);
        	params.put("ALLOCATE_ID",drpAllcateId);
        	params.put("REAL_ALLOC_NUM",strRealNum.toString());
        	returnDtlList.add(params);
    	}
    	this.batch4Update("Storeout.updateAllcateRealNum", returnDtlList);
    }
    
    /**生成分销入库通知单
     * 
     */
    private void genDrpStoreInNotice(String billType,HashMap<String,String> storeOutMap,List storeOutDtlList, UserBean userBean,String type){
    	String storeinNoticeId = StringUtil.uuid32len();
    	instStoreInNotice(storeinNoticeId,billType,storeOutMap,userBean,type);
    	instStoreInNoticeDtl(storeinNoticeId,storeOutDtlList,storeOutMap);
    }
    
    /**插入入库通知单主表
     * 
     */
    private void instStoreInNotice(String storeinNoticeId, String billType,HashMap<String,String> storeOutMap, UserBean userBean,String type){
    	Map<String,String> params=new HashMap<String,String>();
    	String storeinNoticeNo = LogicUtil.getBmgz("DRP_STOREIN_NOTICE_NO");
    	params.put("STOREIN_NOTICE_ID", storeinNoticeId);
    	params.put("STOREIN_NOTICE_NO", storeinNoticeNo);
    	params.put("BILL_TYPE", billType);
    	Map<String,String> fromBill = new HashMap<String,String>();
    	if("分发入库".equals(billType)){
    		fromBill = (Map<String, String>) load("Dstrorder.loadById", storeOutMap.get("FROM_BILL_ID"));//分发指令接收
    	}else{
    		fromBill.put("DEF_STORE_ID", storeOutMap.get("STORE_ID"));
    		fromBill.put("DEF_STORE_NO", storeOutMap.get("STORE_NO"));
    		fromBill.put("DEF_STORE_NAME", storeOutMap.get("STORE_NAME"));
    	}
    	params.put("FROM_BILL_ID", storeOutMap.get("STOREOUT_ID"));
    	params.put("FROM_BILL_NO", storeOutMap.get("STOREOUT_NO"));
    	params.put("SEND_CHANN_ID", storeOutMap.get("SEND_CHANN_ID"));
    	params.put("SEND_CHANN_NO", storeOutMap.get("SEND_CHANN_NO"));
    	params.put("SEND_CHANN_NAME", storeOutMap.get("SEND_CHANN_NAME"));
    	params.put("RECV_CHANN_ID", storeOutMap.get("RECV_CHANN_ID"));
    	params.put("RECV_CHANN_NO", storeOutMap.get("RECV_CHANN_NO"));
    	params.put("RECV_CHANN_NAME", storeOutMap.get("RECV_CHANN_NAME"));
    	params.put("STATE", "未提交");
    	//修改生成的入库单制单人信息，原来为出库方当前登陆人信息，现在改为生成的入库方渠道信息
    	params.put("CREATOR", storeOutMap.get("RECV_CHANN_ID"));
        params.put("CRE_NAME", storeOutMap.get("RECV_CHANN_NAME"));
        params.put("DEPT_ID", storeOutMap.get("RECV_CHANN_ID"));
        params.put("DEPT_NAME", storeOutMap.get("RECV_CHANN_NAME"));
        params.put("ORG_ID", storeOutMap.get("RECV_CHANN_ID"));
        params.put("ORG_NAME", storeOutMap.get("RECV_CHANN_NAME"));
        params.put("LEDGER_ID", storeOutMap.get("RECV_CHANN_ID"));
        params.put("LEDGER_NAME",storeOutMap.get("RECV_CHANN_NAME"));
        params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        params.put("REMARK", storeOutMap.get("REMARK"));
    	params.put("ORDER_CHANN_ID", fromBill.get("ORDER_CHANN_ID"));
    	params.put("ORDER_CHANN_NO", fromBill.get("ORDER_CHANN_NO"));
    	params.put("ORDER_CHANN_NAME", fromBill.get("ORDER_CHANN_NAME"));
    	
    	params.put("STORE_ID", fromBill.get("DEF_STORE_ID"));
    	params.put("STORE_NO", fromBill.get("DEF_STORE_NO"));
    	params.put("STORE_NAME", fromBill.get("DEF_STORE_NAME"));
    	
    	
    	
        insert("Storeout.instStoreinNotice", params);
        //更新出库单表里的入库单id和编号
        Map<String,String> map=new HashMap<String,String>();
        map.put("UPDATOR", userBean.getRYXXID());
        map.put("UPD_NAME", userBean.getXM());
        map.put("UPD_TIME", "数据库时间");
        map.put("STOREIN_ID", storeinNoticeId);
        map.put("STOREIN_NO", storeinNoticeNo);
        map.put("STOREOUT_ID", storeOutMap.get("STOREOUT_ID"));
        this.update("Storeout.updateById", map);
        
    }
    
    /**插入入库通知单明细表
     * 
     */
    @SuppressWarnings("unchecked")
	private void instStoreInNoticeDtl(String storeinNoticeId,List<StoreoutModelChld> storeOutDtlList,Map<String,String> storeInfo){
    	ArrayList noteicDtlList = new ArrayList();
    	for(int i=0;i<storeOutDtlList.size();i++){
    		StoreoutModelChld storeOutDtlMap = storeOutDtlList.get(i);
    		Map<String,String> params=new HashMap<String,String>();
        	String ntoecDtlId = StringUtil.uuid32len();
        	params.put("STOREIN_NOTICE_DTL_ID", ntoecDtlId);
        	params.put("STOREIN_NOTICE_ID", storeinNoticeId);
        	params.put("PRD_ID", storeOutDtlMap.getPRD_ID());
        	params.put("PRD_NO", storeOutDtlMap.getPRD_NO());
        	params.put("PRD_NAME",storeOutDtlMap.getPRD_NAME());
        	params.put("PRD_SPEC",storeOutDtlMap.getPRD_SPEC());
        	params.put("PRD_COLOR",storeOutDtlMap.getPRD_COLOR());
        	params.put("BRAND",storeOutDtlMap.getBRAND());
        	params.put("STD_UNIT",storeOutDtlMap.getSTD_UNIT());
        	if(storeOutDtlMap.getPRICE()!=null){
        		params.put("PRICE",getFromDecimal(storeOutDtlMap.getPRICE()));
        	}
        	if(storeOutDtlMap.getDECT_RATE()!=null){
        		params.put("DECT_RATE",getFromDecimal(storeOutDtlMap.getDECT_RATE()));
        	}
        	String dectPrice =  String.valueOf(storeOutDtlMap.getDECT_PRICE());
        	Object realNum = storeOutDtlMap.getREAL_NUM();
        	if(realNum==null){
        		realNum="0";
        	}
        	params.put("NOTICE_NUM", realNum.toString());
        	if(storeOutDtlMap.getDECT_PRICE()!=null){
        		params.put("DECT_PRICE",getFromDecimal(storeOutDtlMap.getDECT_PRICE()));
        		double returnAmout = multiply(realNum.toString(),dectPrice);
        		params.put("DECT_AMOUNT",getFromDecimal(returnAmout));
        	}
        	
        	//add by zzb 2014-8-22 添加税率
			//不含税折后单价=round(DECT_PRICE/1+税率,2)
			//不含税折后金额=不含税折后单价*数量
			//税额=折后金额-不含税折后金额
        	String SEND_CHANN_ID = storeInfo.get("SEND_CHANN_ID");
        	Map<String,String> paramMap = new HashMap<String,String>();
        	paramMap.put("CHANN_ID", SEND_CHANN_ID);
        	Map<String,Object> chann = (Map<String, Object>) this.load("chann.loadById", paramMap);
        	//税率
    		Double TAX_RATE  = StringUtil.getDouble(chann.get("TAX_RATE"));
    		
    		Double DECT_PRICE  = StringUtil.getDouble(storeOutDtlMap.getDECT_PRICE());
			Integer NOTICE_NUM = StringUtil.getInteger(storeOutDtlMap.getREAL_NUM());
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
			
        	params.put("SPCL_TECH_ID", (String)storeOutDtlMap.getSPCL_TECH_ID());
        	params.put("GOODS_ORDER_NO", (String)storeOutDtlMap.getGOODS_ORDER_NO());
        	params.put("SALE_ORDER_ID", (String)storeOutDtlMap.getSALE_ORDER_ID());
        	params.put("SALE_ORDER_NO", (String)storeOutDtlMap.getSALE_ORDER_NO());
        	params.put("SALE_ORDER_DTL_ID", (String)storeOutDtlMap.getSALE_ORDER_DTL_ID());
        	if(null!=storeInfo){
        		params.put("STOREIN_STORE_ID", storeInfo.get("STORE_ID"));
            	params.put("STOREIN_STORE_NO",storeInfo.get("STORE_NO"));
            	params.put("STOREIN_STORE_NAME", storeInfo.get("STORE_NAME"));
        	}
        	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
        	noteicDtlList.add(params);
    	}
    	this.batch4Update("Storeout.instStoreinNoticeDtl", noteicDtlList);
    }
    
    
    /**更新分销分发指令的实际出库数量
     * 
     */
    private void updateDrpDstrNum(HashMap<String,String>storeOutMap,List<StoreoutModelChld> storeOutDtlList){
    	//少实际分发数量字段
    	ArrayList returnDtlList = new ArrayList();
    	String dstrOrderId =  storeOutMap.get("FROM_BILL_ID");
    	for(int i=0;i<storeOutDtlList.size();i++){
    		StoreoutModelChld storeOutDtlMap = storeOutDtlList.get(i);
    		String dstrOrderDtlId =  (String)storeOutDtlMap.getFROM_BILL_DTL_ID();
    		Object strRealNum = storeOutDtlMap.getREAL_NUM();
    		if(strRealNum==null){
    			strRealNum="0";
    		}
        	Map<String,String> params=new HashMap<String,String>();
        	params.put("DSTR_ORDER_DTL_ID",dstrOrderDtlId);
        	params.put("DSTR_ORDER_ID",dstrOrderId);
        	params.put("REAL_DSTR_NUM",strRealNum.toString());
        	returnDtlList.add(params);
    	}
    	this.batch4Update("Storeout.updateDstrOrderRealNum", returnDtlList);
    }
    
    
    /**跟据渫道ＩＤ查找渫道的账套ＩＤ
     * @param channId
     */
    private HashMap getLedgerIdByChannId(String channId){
    	List channList = findList("Storeout.queryChannLedger",channId);
    	if(channList!=null && channList.size()>0 ){
    		return (HashMap)channList.get(0);
    	}
    	return null;
    }
    
    
    /**格式化小数位　－－要做成共通
     * @param numObj
     * @return
     */
    private String getFromDecimal(Object numObj){
    	if(numObj!=null){
    		numObj = Double.valueOf(numObj.toString());
        	DecimalFormat format=new DecimalFormat("0.00");
        	return format.format(numObj);
    	}
    	return null;

    }
    
    /**乖法运算　－－要做成共通
     * @param x
     * @param y
     * @return
     */
    public static double multiply(String x, String y) {
    	if(x!=null && y!=null){
     	   BigDecimal bigX = new BigDecimal(x);
    	   BigDecimal bigY = new BigDecimal(y);
    	   return bigX.multiply(bigY).doubleValue();   		
    	}
    	return 0;
    }
    /**
     * 共通入库单新增
     */
    public void txAddStoreOut(Map<String,Object> model,List<Map<String,String>> chld){
    	System.out.println("---------------------------2");
    	insert("Storeout.insert", model);
    	if (!chld.isEmpty()) {
            this.batch4Update("Storeout.insertChld", chld);
        }
    }
    
    /**跟据序列号查表出库单详细(ERP_STOREOUT_DTL)
     * @param model
     * @param chld
     */
    public HashMap getErpStoreOutDtl(String pdtSN){
    	List storeoutDtlList = findList("Storeout.queryErpStoreOutDtl",pdtSN);
    	if(storeoutDtlList!=null && storeoutDtlList.size()>0 ){
    		return (HashMap)storeoutDtlList.get(0);
    	}
    	return null;
    }
    
    public HashMap getDrpStoreOutDtl(String pdtId){
    	List storeoutDtlList = findList("Storeout.queryDrpStoreOutDtl",pdtId);
    	if(storeoutDtlList!=null && storeoutDtlList.size()>0 ){
    		return (HashMap)storeoutDtlList.get(0);
    	}
    	return null;
    }    
    
    
    /**
     * 查询 出库单 是否已经扫过该序列号
     * @param SN
     * @return true 已扫过  false 未扫
     */
    @SuppressWarnings("unchecked")
	public boolean getDrpStoreOutDtlBySN(String SN){
    	List list = findList("Storeout.queryDrpStoreOutDtlBySN",SN);
    	if(null == list || list.isEmpty()){
    		return false;
    	}
    	return true;
    }
    /**
     *根据出库单id查询预订单的付款总金额和应收总金额
     */
    public Map<String,String> getSellInfo(String STOREOUT_ID){
    	return (Map<String, String>) load("Storeout.getSellInfo",STOREOUT_ID);
    }

    public Map<String,String> getReStoreOutInfo(String STOREOUT_ID){
    	return (Map<String, String>) load("Storeout.getReStoreOutInfo",STOREOUT_ID);
    }
    /**
     * 区域服务中心销售出库处理
     */
	@SuppressWarnings("unchecked")
	public void txXXCommit(List storeOutList,List storeOutDtlList,List dtlGroupList, UserBean userBean, String storeOutId)
			throws Exception {
		HashMap<String,String> storeOutMap =  (HashMap<String, String>) storeOutList.get(0);
    	//更新主表信息
    	updateStoreOutStats(storeOutMap,userBean);
    	//反填销售订单已实际发货数量
    	List<Map<String,String>> addList=new ArrayList<Map<String,String>>();
    	for (int i = 0; i < dtlGroupList.size(); i++) {
    		Map<String,String> map=(Map<String, String>) dtlGroupList.get(i);
    		addList.add(map);
		}
    	if(null!=addList){
    		this.batch4Update("Storeout.upSendedNum", addList);
    	}
    	String billType = "下级入库";
    	genDrpStoreInNotice(billType,storeOutMap,storeOutDtlList,userBean,"XX_BILL_TYPE");
    	//调接口 对库存进行扣减
    	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF4_ID,storeOutId);
    	LogicUtil.doJournalAcct(BusinessConsts.STOREOUT_ORDER_CONF4_ID,storeOutId);
		String checkStoreAcct = LogicUtil.doChkStoreNumByStoreOut(storeOutId);
    	if(!"true".equals(checkStoreAcct)){
    		throw new ServiceException(checkStoreAcct);
    	}
	}
	/**
     * * 根据主表Id查询子表记录并且合并
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <Map<String,String>> childQueryGroup(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeout.queryChldByFkIdGroup", params);
      }
    
    /**
     * * 获得出库单和销售出库通知的信息
     * @param STOREOUT_ID the STOREOUT_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StoreoutModelChld> childQuerySaleOutDtl(String STOREOUT_ID) {
        Map params = new HashMap();
        params.put("STOREOUT_ID", STOREOUT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storeout.qrySaleOutSql", params);
    }
    
    
    /**
     * 根据当前登录人所属渠道获取渠道类型
     * @param CHANN_ID
     * @return
     */
    public String getCHANN_TYPE(String CHANN_ID){
    	return this.load("Storeout.getCHANN_TYPE",CHANN_ID).toString();
    }
    
    
    
    public boolean txCancelStoreOut(String storeOutId)throws Exception{
    	HashMap paramMap = new HashMap();
    	paramMap.put("STOREOUT_ID", storeOutId);
    	boolean flg = update("Storeout.updateStoreOut",paramMap);
		HashMap<String,String> storeOutMap = load(storeOutId);
		String stats = storeOutMap.get("STATE");
    	if(flg&&"已处理".equals(stats)){
    		LogicUtil.doStoreFreeze(BusinessConsts.STOREOUT_ORDER_CONF_ID,storeOutId);
    	}
    	return flg;
    }
    
    /**
     * 修改备注
     * @param params
     */
    public void updateRemark(Map<String,Object> params){
    	this.update("Storeout.updateById", params);
    }
    /**
     * 关闭销售出库确认
     * @param STOREOUT_ID
     */
    public void closeDocument(String STOREOUT_ID,UserBean userBean){
    	try{
	    	LogicUtil.doStoreFreeze(BusinessConsts.STOREOUT_ORDER_CONF_ID,STOREOUT_ID);
	    	//获取当前单据状态
	    	Map<String,String> checkMap=this.load(STOREOUT_ID);
//			String state=getStoreoutState(STOREOUT_ID);
			if("已处理".equals(checkMap.get("STATE"))||"已取消".equals(checkMap.get("STATE"))){
				throw new ServiceException("该单据已处理或已被取消,不能关闭!");
			}else if("1".equals(String.valueOf(checkMap.get("DEL_FLAG")))){
				throw new ServiceException("该单据已关闭，不需要重复操作!");
			}
	    	Map<String,String> map=new HashMap<String, String>();
	    	map.put("STOREOUT_ID", STOREOUT_ID);
	    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
	    	map.put("UPD_NAME", userBean.getXM());
	    	map.put("UPDATOR", userBean.getRYXXID());
	    	map.put("STATE", BusinessConsts.UNCOMMIT);
	    	this.update("Storeout.delete", map);
	    	this.update("Storeout.upAdvcGoods", map);
	    	//插预订单生命周期
	    	Map<String,String> storeOutMap=load(STOREOUT_ID);
	    	Map<String,String> params=new HashMap<String,String>();
	    	params.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
	    	params.put("ADVC_SEND_REQ_ID",storeOutMap.get("FROM_BILL_ID"));
	    	params.put("ACTION", "销售收货确认");
	    	params.put("ACTOR", userBean.getXM());
	    	params.put("BILL_NO", storeOutMap.get("STOREOUT_NO"));
	    	params.put("REMARK", "已关闭");
	    	insert("Storeout.insertAdvcOrderTrace", params);
    	}catch(ServiceException s){
    		throw new ServiceException(s.getMessage());
    	}catch(Exception ex){
    		throw new ServiceException("关闭出库单出错!");
    	}
    }
    
    /**
     * 零星出库单处理关闭
     * @param params
     */
    public void txCloseSporadicStoreout(String STOREOUT_ID,UserBean userBean){
    	Map<String,String> map = new HashMap<String, String>();
    	map.put("STOREOUT_ID", STOREOUT_ID);
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
    	map.put("UPD_NAME", userBean.getXM());
    	map.put("UPDATOR", userBean.getRYXXID());
    	map.put("STATE", BusinessConsts.COMMIT);
    	this.update("Storeout.delete", map);
    	map.clear();
    	map.put("STOREOUT_ID", STOREOUT_ID);
    	map.put("UPD_NAME", userBean.getXM());
    	map.put("UPDATOR", userBean.getRYXXID());
    	map.put("STATE", BusinessConsts.UNCOMMIT);
    	this.update("Storeout.updateSporadicNotice", map);
    }
    public String getChannTel(String CHANN_ID){
    	return StringUtil.nullToSring(this.load("Storeout.getChannTel",CHANN_ID));
    }
    /**
     * 反出库处理
     * @param map
     */
    public void txReturnStore(Map<String,String> map,UserBean userBean)throws Exception{
    	String STOREOUT_ID = map.get("STOREOUT_ID"); 
    	Map<String,String> storeMap =load(STOREOUT_ID);
    	String STATE = storeMap.get("STATE");
    	String BILL_TYPE = storeMap.get("BILL_TYPE");
    	if("已处理".equals(STATE)){
    		this.upStoreDealState(map);
    		udpateAdvcOrder(map);
    		udpateAdvcSendOrder(map);
    		String in_out_type = null;
    		if("销售出库".equals(BILL_TYPE)){
    			in_out_type = "终端销售出库";
    		}else if("退货出库".equals(BILL_TYPE)){
    			in_out_type = "退货出库";
    			//删除已生成的退货单
    			delPrdReturn(STOREOUT_ID);
    		}else if("返修出库".equals(BILL_TYPE)){
    			in_out_type = "返修出库";
    		}else if("下级销售出库".equals(BILL_TYPE)){
    			in_out_type = "下级销售出库";
    		}else if("零星出库".equals(BILL_TYPE)){
    			in_out_type = "零星出库";
    		}
    		if(LogicUtil.checkJournalAcct(STOREOUT_ID,in_out_type)){
            	LogicUtil.doStoreAcct(BusinessConsts.STOREOUT_ORDER_CONF11_ID,STOREOUT_ID);
            	LogicUtil.delJournalAcct(STOREOUT_ID,in_out_type);  			
    		}else{
    			throw new ServiceException("无流水,不能做返出库");
    		}

    	}else{
    		throw new ServiceException("未处理状态下不能反出库");
    	}
    	//插预订单生命周期
    	Map<String,String> params=new HashMap<String,String>();
    	params.put("ADVC_ORDER_TRACE_ID", StringUtil.uuid32len());
    	params.put("ADVC_SEND_REQ_ID",storeMap.get("FROM_BILL_ID"));
    	params.put("ACTION", "销售出库处理");
    	params.put("ACTOR", userBean.getXM());
    	params.put("BILL_NO", storeMap.get("STOREOUT_NO"));
    	params.put("REMARK", "反出库");
    	insert("Storeout.insertAdvcOrderTrace", params);
    }
    
    //反出库修改出库单信息
    public boolean upStoreDealState(Map<String,String> map){
    	return this.update("Storeout.upStoreDealState", map);
    }
    
    /**返出库更新预订单实际出库数据
     * @return
     */
    public boolean udpateAdvcOrder(Map<String,String> map){
    	 this.update("Storeout.upAdvcOrderStats", map);
    	 return this.update("Storeout.upAdvcOrderDtl", map);
    }
    
    /**返出库更新预订单实际出库数据
     * @return
     */
    public boolean udpateAdvcSendOrder(Map<String,String> map){
    	 this.update("Storeout.upAdvcSendOrderStats", map);
    	 return this.update("Storeout.upAdvcSendOrderDtl", map);
    }
    /**
     * 根据出库单ID获取出库单进行验证
     * @param STOREOUT_ID
     * @return
     */
    public Map<String,Object> getStoreoutInfo(String STOREOUT_ID){
    	return (Map<String, Object>) this.load("Storeout.getStoreoutInfo",STOREOUT_ID);
    }
    /**
     * 根据出库单id获取已扫码明细条数
     * @param STOREOUT_ID
     * @return
     */
    public Integer countSnNum(String STOREOUT_ID){
    	return StringUtil.getInteger(this.load("Storeout.countSnNum", STOREOUT_ID));
    }
    /**
     * 根据出库单id验证出库总金额是否大于预订单的订货总金额
     * @param STOREOUT_ID
     * @return
     */
    public boolean checkStoreAmount(String STOREOUT_ID){
    	String ADVC_ORDER_ID=(String) this.load("Storeout.getAdvcIdByStoreId",STOREOUT_ID);
    	int flag=StringUtil.getInteger(this.load("Storeout.checkStoreAmount", ADVC_ORDER_ID));
    	if(flag>0){
    		return false;
    	}else{
    		return true;
    	}
    }
    /**
     * 根据出库单id验证出库总数量是否大于订货总数量
     * @param STOREOUT_ID
     * @return
     */
//    public boolean checkStoreNum(String STOREOUT_ID){
//    	String ADVC_ORDER_ID=(String) this.load("Storeout.getAdvcIdByStoreId",STOREOUT_ID);
//    	int flag=StringUtil.getInteger(this.load("Storeout.checkStoreNum", ADVC_ORDER_ID));
//    	if(flag>0){
//    		return false;
//    	}else{
//    		return true;
//    	}
//    }
    /**
     * 根据出库单id验证来源单据是否只生成一张出库单
     * @param STOREOUT_ID
     * @return
     */
    public boolean checkAdvcSendRepeat(String STOREOUT_ID){
    	int count=StringUtil.getInteger(this.load("Storeout.checkAdvcSendRepeat", STOREOUT_ID));
    	if(count>1){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
     * 查询出库总金额
     * @param paramMap
     * @return
     */
    public Map<String,Object> quereyDealAmount(Map<String,String> paramMap){
//    	String  DEAL_TIME_BEG = paramMap.get("DEAL_TIME_BEG");
//		String  DEAL_TIME_END = paramMap.get("DEAL_TIME_END");
//		//如果没有写查询处理时间 默认当前时间
//		if(StringUtil.isEmpty(DEAL_TIME_BEG) && StringUtil.isEmpty(DEAL_TIME_END)){
//			paramMap.put("SYS_DATE", BusinessConsts.UPDATE_TIME);
//		}
		
    	List<Map<String,Object>> list = this.findList("Storeout.quereyDealAmount", paramMap);
    	if(null  == list || list.isEmpty()){
    		return new HashMap<String,Object>();
    	}else{
    		return list.get(0);
    	}
    }
  //获取赠品总金额
    public double getDtlGiftAmount(String STOREOUT_ID){
    	return StringUtil.getDouble(this.load("Storeout.getDtlGiftAmount",STOREOUT_ID));
    }
  //获取明细行数
    public int getDtlCount(String STOREOUT_ID){
    	return StringUtil.getInteger(this.load("Storeout.getDtlCount",STOREOUT_ID));
    }
    //导出
    public List<Map<String,String>> downQuery(Map<String,String> map){
    	return this.findList("Storeout.downGet", map);
    }
    /**
     * 根据出库单id验证是否存在退货单
     * @param STOREOUT_ID
     * @return
     */
    public boolean checkReturnAdvc(String STOREOUT_ID){
    	int count=StringUtil.getInteger(this.load("Storeout.checkReturnAdvc", STOREOUT_ID));
    	if(count>0){
    		return false;
    	}
    	return true;
    }
  //获取当前单据状态
    public String getStoreoutState(String STOREOUT_ID){
    	return (String) this.load("Storeout.getStoreoutState",STOREOUT_ID);
    }
    /**
     * 删除总部退货单
     * @param STOREOUT_ID
     */
    public void delPrdReturn(String STOREOUT_ID){
    	//获取退货单信息
    	Map<String,String> map=(Map<String, String>) this.load("Storeout.getPrdReturnInfo",STOREOUT_ID);
    	String STATE=map.get("STATE");
    	String PRD_RETURN_ID=map.get("PRD_RETURN_ID");
    	if(!StringUtil.isEmpty(STATE)&&!BusinessConsts.UNCOMMIT.equals(STATE)){
    		throw new ServiceException("后续退货单已提交，不能反出库");
    	}else{
    		this.delete("Storeout.delPrdReturn", PRD_RETURN_ID);
    		this.delete("Storeout.delPrdReturnDtl", PRD_RETURN_ID);
    	}
    	
    }
}