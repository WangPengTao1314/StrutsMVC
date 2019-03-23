/**
 * prjName:喜临门营销平台
 * ucName:返修单
 * fileName:RepairrecvServiceImpl
*/
package com.hoperun.drp.sale.repairrecv.service.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.Consts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.InterfaceInvokeUtil;
import com.hoperun.commons.util.JesonImplUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.repairapp.model.RepairappModel;
import com.hoperun.drp.sale.repairapp.model.RepairappModelChld;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.sale.repairrecv.service.RepairrecvService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-11-03 16:25:12
 */
public class RepairrecvServiceImpl extends BaseService implements RepairrecvService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Repairrecv.pageQuery", "Repairrecv.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Repairrecv.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param ERP_REPAIR_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Repairrecv.delete", params);
		 //删除子表 
		 return update("Repairrecv.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Repairrecv.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param ERP_REPAIR_ORDER_ID
	 * @param RepairstoreoutModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String ERP_REPAIR_ORDER_ID, RepairappModel model,List<RepairappModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		    params.put("REMARK",model.getREMARK());
		    params.put("REPAIR_CHANN_ID",model.getREPAIR_CHANN_ID());
		    params.put("REPAIR_CHANN_NO",model.getREPAIR_CHANN_NO());
		    params.put("REPAIR_CHANN_NAME",model.getREPAIR_CHANN_NAME());
		    params.put("AREA_ID",model.getAREA_ID());
		    params.put("AREA_NO",model.getAREA_NO());
		    params.put("AREA_NAME",model.getAREA_NAME());
		    params.put("REQ_FINISH_DATE",model.getREQ_FINISH_DATE());
		    params.put("DELIVER_ADDR_ID", model.getDELIVER_ADDR_ID());
		    params.put("DELIVER_ADDR_NO", model.getDELIVER_ADDR_NO());
		    params.put("DELIVER_DTL_ADDR", model.getDELIVER_DTL_ADDR());
		if(StringUtil.isEmpty(ERP_REPAIR_ORDER_ID)){
			ERP_REPAIR_ORDER_ID= StringUtil.uuid32len();
			params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
			String no = LogicUtil.getBmgz("ERP_REPAIR_ORDER_NO");
		    params.put("ERP_REPAIR_ORDER_NO",no);
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
			params.put("STATE","已发货");
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
			txUpdateById(params);
			clearCaches(ERP_REPAIR_ORDER_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(ERP_REPAIR_ORDER_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param ERP_REPAIR_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Repairrecv.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param ERP_REPAIR_ORDER_ID the ERP_REPAIR_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String ERP_REPAIR_ORDER_ID, List<RepairappModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (RepairappModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("REPAIR_ATT",model.getREPAIR_ATT());
		    params.put("REPAIR_RESON",model.getREPAIR_RESON());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("PRD_ID",model.getPRD_ID());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    Object REPAIR_PRICE=model.getREPAIR_PRICE();
		    Object REPAIR_NUM=model.getREPAIR_NUM();
		    Object VOLUME=model.getVOLUME();
		    double REPAIR_AMOUNT=StringUtil.getDouble(REPAIR_PRICE)*StringUtil.getDouble(REPAIR_NUM);
		    double TOTAL_VOLUME=StringUtil.getDouble(VOLUME)*StringUtil.getDouble(REPAIR_NUM);
		    params.put("REPAIR_PRICE", REPAIR_PRICE+"");
		    params.put("REPAIR_NUM", REPAIR_NUM+"");
		    params.put("REPAIR_AMOUNT", REPAIR_AMOUNT+"");
		    params.put("TOTAL_VOLUME", TOTAL_VOLUME+"");
		    params.put("BRAND",model.getBRAND());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("REPAIR_NUM",model.getREPAIR_NUM());
            params.put("ERP_REPAIR_ORDER_ID",ERP_REPAIR_ORDER_ID); 
            params.put("ERP_REPAIR_ORDER_ID",ERP_REPAIR_ORDER_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getREPAIR_ORDER_DTL_ID())) {
            	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                params.put("REPAIR_ORDER_DTL_ID", StringUtil.uuid32len());
                addList.add(params);
            } else {
                params.put("REPAIR_ORDER_DTL_ID", model.getREPAIR_ORDER_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Repairrecv.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Repairrecv.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param ERP_REPAIR_ORDER_ID the ERP_REPAIR_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <RepairappModelChld> childQuery(String ERP_REPAIR_ORDER_ID) {
        Map params = new HashMap();
        params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Repairrecv.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param REPAIR_ORDER_DTL_IDs the REPAIR_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <RepairappModelChld> loadChilds(Map <String, String> params) {
       return findList("Repairrecv.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param REPAIR_ORDER_DTL_IDs the REPAIR_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String REPAIR_ORDER_DTL_IDs) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("REPAIR_ORDER_DTL_IDS", REPAIR_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Repairrecv.deleteChldByIds", params);
    }
    
    
    public Map<String,String> getAreaByuserChann(String channId) {
        Map<String,String> params = new HashMap<String,String>();
        params.put("CHANN_ID", channId);
        //只查询0的记录。1为删除。0为正常
		 
        return (Map<String,String>)load("Repairrecv.queryAreaByuserChann", channId);
    }
    
    
    public void txSubmitById(Map<String, String> params,UserBean userBean){
    	//LogicUtil.instRepairSotreout(params.get("ERP_REPAIR_ORDER_ID"), userBean);//调用接口生成出库单
    	update("Repairrecv.updateStateById", params);
    }
    
    /**
     * 查询总体积 总数量 总金额
     * @param ERP_REPAIR_ORDER_ID
     * @return
     */
    public Map<String,Object> queryTotal(String ERP_REPAIR_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
    	 params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
    	return (Map<String, Object>) this.load("Repairapp.queryTotal", params);
    }
    
    public String txCommit(Map<String,String> map,Map params,UserBean userBean)throws Exception{
       	String strMessage = "操作成功";
    	String strJsonData = (String)map.get("JSON_DATA");
    	String strResult = "";
    	if("true".equals(Consts.INVOKE_SYS_FLG)&&isInvokeRepair(params)){
    		try{
    			strResult = LogicUtil.createRMA(strJsonData);  
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
    		Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
     		if(resMap!=null){
     			String stats = resMap.get("IsOK");
     			if("false".equals(stats)){
     				StringBuffer buf = new StringBuffer();
     				buf.append("操作失败!");
     				String Msg = resMap.get("Msg");
     				buf.append("错误信息:"+Msg);
     				throw new ServiceException(buf.toString());
     			}
     		}else{
     			strMessage = "调用接口失败,查看LOG日志";
     			throw new ServiceException(strMessage);
     		}
    	}
    	params.put("IS_CREATE_REPAIR", "1");
    	txSubmitById(params,null);
    	return strMessage;
    }
    
    /**是否调用返修接口
     * @param params
     * @return
     */
    private boolean isInvokeRepair(Map params){
    	String ERP_REPAIR_ORDER_ID = (String)params.get("ERP_REPAIR_ORDER_ID");
    	Map rePairMap = (Map<String,String>) load("Repairrecv.loadById", ERP_REPAIR_ORDER_ID);
    	BigDecimal IS_CREATE_REPAIR = (BigDecimal)rePairMap.get("IS_CREATE_REPAIR");
    	if(IS_CREATE_REPAIR!=null && IS_CREATE_REPAIR.intValue()==1){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**是否调用返修接口
     * @param params
     * @return
     */
    private boolean isInvokeSaleOrder(Map params){
    	String ERP_REPAIR_ORDER_ID = (String)params.get("ERP_REPAIR_ORDER_ID");
    	Map rePairMap = (Map<String,String>) load("Repairrecv.loadById", ERP_REPAIR_ORDER_ID);
    	BigDecimal IS_CREATE_SALEORDER = (BigDecimal)rePairMap.get("IS_CREATE_SALEORDER");
    	if(IS_CREATE_SALEORDER!=null && IS_CREATE_SALEORDER.intValue()==1){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    public String txSaleOrderCommit(Map<String,String> map,Map params,UserBean userBean)throws Exception{
    	String strMessage = "操作成功";
    	String SALE_ORDER_ID = StringUtil.uuid32len();
    	String SALE_ORDER_NO = LogicUtil.getBmgz("ERP_SALE_ORDER_NO");
    	txInsertSaleOrder(map.get("ERP_REPAIR_ORDER_ID"),SALE_ORDER_ID,SALE_ORDER_NO,userBean);
		String ServiceCode = "TC0300010";
		String AppCode = "DM";
		String UId = StringUtil.uuid32len();
		String OPRCODE = ServiceCode + ":" + "createSO";
		String strResult ="";
		String strJsonData ="";
    	if("true".equals(Consts.INVOKE_SYS_FLG)&&isInvokeSaleOrder(params)){
    		ArrayList bodyList = new ArrayList();
    		try{
        		strJsonData = InterfaceInvokeUtil.getStrCreateSO(SALE_ORDER_ID,null,UId);
        		LogicUtil.actLog("销售订单管理", "开始调入生成返修销售订单接口", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
        		String returnJsonData = InterfaceInvokeUtil.createSO(strJsonData); 
        		LogicUtil.actLog("销售订单管理", "返修销售订单接口返回值", "U9", "成功",returnJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
        		JesonImplUtil jsUtil = new JesonImplUtil(true,returnJsonData);
        		HashMap headMap =  jsUtil.getMbHead();
        		Map rspMap = jsUtil.getResponse();
        		bodyList = jsUtil.getMbBody();
        		HashMap bodyMap = (HashMap)bodyList.get(0);
        		strResult = new Gson().toJson(bodyMap);
    		}catch(Exception ex){
    			throw new ServiceException("调用ESB接口失败!");
    		}
        	Map<String, String> resMap = new Gson().fromJson(strResult, new TypeToken<Map<String, String>>() {}.getType());
        	if(resMap!=null){
         		String stats = resMap.get("IsOK");
         		if("false".equals(stats)){
         			StringBuffer buf = new StringBuffer();
         			buf.append("操作失败!");
         			String Msg = resMap.get("Msg");
         			buf.append("错误信息:"+Msg);
         			throw new ServiceException(buf.toString());
         		}else{
    	            LogicUtil.actLog("销售订单管理", "生成返修销售订单成功", "U9系统", "成功",strJsonData,AppCode,UId,OPRCODE,SALE_ORDER_NO);
         		}
         	}else{
         		strMessage = "调用接口失败,查看LOG日志";
         		throw new ServiceException(strMessage);
         	}
        	updateSaleOrderDtlU9Info(bodyList);
        	params.put("IS_CREATE_SALEORDER", "1");
        	txSubmitById(params,null);
    	}
    	return strMessage;
    }
    	
        /**把U9返回的销售订单ID和明细ID返填到DM销售订单
         * @param bodyList
         */
        private void updateSaleOrderDtlU9Info(ArrayList bodyList){
        	for(int i=0;bodyList!=null && i<bodyList.size();i++){
        		HashMap bodyMap = (HashMap)bodyList.get(i);
        		if(bodyMap!=null){
        			String SODocNo = (String)bodyMap.get("SODocNo");
            		String SOLineID = (String)bodyMap.get("SOLineID");
            		String PlanDocLineID = (String)bodyMap.get("PlanDocLineID");
            		String SARE_ORDER_DTL_ID = getSaleOrderDtlId(PlanDocLineID, "");
            		HashMap param = new HashMap();
            		param.put("U9_SALE_ORDER_NO", SODocNo);
            		param.put("U9_SALE_ORDER_DTL_NO", SOLineID);
            		param.put("SALE_ORDER_DTL_ID", SARE_ORDER_DTL_ID);
            		update("Saleordermge.updateU9Id",param);
        		}
        	}
        }
        
    	private String getSaleOrderDtlId(String strUCode, String Ship_Org) {
    		StringBuffer deliverBuf = new StringBuffer();
    		deliverBuf.append("select d.SALE_ORDER_DTL_ID ").append(
    				" from ERP_SALE_ORDER t, ERP_SALE_ORDER_DTL d").append(
    				" where t.sale_order_id = d.sale_order_id").append(
    				" and d.u_code = '" + strUCode + "'").append(
    				"  and t.ship_point_id = '" + Ship_Org + "'");
    		Map params = new HashMap();
    		params.put("SelSQL", deliverBuf.toString());
    		Map saleOrderMap = selcom(params);
    		String SALE_ORDER_DTL_ID = (String) saleOrderMap
    				.get("SALE_ORDER_DTL_ID");
    		return SALE_ORDER_DTL_ID;
    	}
    	
    	public Map selcom(Map params) {
    		return (Map) load("sqlcom.query", params);
    	}
    
    public void txInsertSaleOrder(String ERP_REPAIR_ORDER_ID,String SALE_ORDER_ID,String SALE_ORDER_NO,UserBean userBean){
    	 Map<String,String> params = new HashMap<String,String>();
    	 params.put("ERP_REPAIR_ORDER_ID", ERP_REPAIR_ORDER_ID);
    	 params.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	 params.put("SALE_ORDER_NO", SALE_ORDER_NO);
    	 params.put("STATE", "待发货");
    	 params.put("BILL_TYPE","返修订单");
    	 params.put("CREATOR", userBean.getXTYHID());
    	 params.put("CRE_NAME", userBean.getXM());
    	 params.put("CRE_TIME", "sysdate");
    	 params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	 params.put("ORDER_DATE", "");// 订货日期
 		 params.put("DEPT_ID", userBean.getBMXXID());
 		 params.put("DEPT_NAME", userBean.getBMMC());
 		 params.put("ORG_ID", userBean.getJGXXID());
 		 params.put("ORG_NAME", userBean.getJGMC());
 		 params.put("LEDGER_ID", userBean.getLoginZTXXID());
 		 params.put("LEDGER_NAME", userBean.getLoginZTMC());
 		 this.insert("Repairrecv.insertSaleOrder", params);
 		 params.put("STATE", "正常");
 		 this.insert("Repairrecv.insertSaleOrderDtl", params); 
    }
}