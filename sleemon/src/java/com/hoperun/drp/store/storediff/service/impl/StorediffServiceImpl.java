/**
 * prjName:喜临门营销平台
 * ucName:入库差异确认
 * fileName:StorediffServiceImpl
*/
package com.hoperun.drp.store.storediff.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hoperun.base.chann.service.impl.ChannServiceImpl;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.SpringContextUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.store.storediff.model.StorediffModel;
import com.hoperun.drp.store.storediff.model.StorediffModelChld;
import com.hoperun.drp.store.storediff.model.StorediffdealModelChld;
import com.hoperun.drp.store.storediff.service.StorediffService;
import com.hoperun.erp.sale.goodsorder.service.impl.GoodsorderServiceImpl;
import com.hoperun.erp.sale.prdreturn.service.impl.PrdreturnServiceImpl;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author wzg
 * *@createdate 2013-08-30 14:03:21
 */
public class StorediffServiceImpl extends BaseService implements StorediffService {
	private static String MANY_SALE_VAR = "MANY_SALE_VAR";   //已发货未付款
	private static String MANY_RETURN_VAR =  "MANY_RETURN_VAR"; //多发退回
	private static String FEW_SALE_VAR =  "FEW_SALE_VAR"; //已付款未发货
	private static String FEW_RETURN_VAR =  "FEW_RETURN_VAR"; //少发退回
	private static String TRAFFIC_RETURN_VAR =  "TRAFFIC_RETURN_VAR"; //运损退回
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo) {
		return this.pageQuery("Storediff.pageQuery", "Storediff.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Storediff.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STOREIN_DIFF_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Storediff.delete", params);
		 //删除子表 
		 return update("Storediff.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Storediff.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_DIFF_ID
	 * @param StorediffaffModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STOREIN_DIFF_ID, StorediffModel model,List<StorediffModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("RECV_DEAL_TIME","sysdate");
		params.put("RECV_DEAL_PSON_ID",userBean.getXTYHID());
		params.put("REVC_DEAL_PSON_NAME",userBean.getXM());
		params.put("STATE",BusinessConsts.WAIT_FOR_SENDER);
		    
		if(StringUtil.isEmpty(STOREIN_DIFF_ID)){
			STOREIN_DIFF_ID= StringUtil.uuid32len();
			params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
			params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    txInsert(params);
		} else{
			params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
			txUpdateById(params);
			clearCaches(STOREIN_DIFF_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			txChildEdit(STOREIN_DIFF_ID, chldList,userBean);
		}
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STOREIN_DIFF_ID
	 * @param StorediffaffModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txOver(String STOREIN_DIFF_ID, UserBean userBean){
		//a.	更新主表的状态：
	    Map<String,String> params = new HashMap<String,String>();
	    params.put("RECV_DEAL_TIME","sysdate");
	    params.put("RECV_DEAL_PSON_ID",userBean.getXTYHID());
	    params.put("REVC_DEAL_PSON_NAME",userBean.getXM());
	    params.put("STATE",BusinessConsts.DEAL_OVER);
		params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
		txUpdateById(params);
		clearCaches(STOREIN_DIFF_ID);
		
		//b.	生成对应的业务单据：
		genRelevantOrder(STOREIN_DIFF_ID,userBean);
		//c.	更新帐表：
		
	}
	
	
	/**跟据业务不同，生成不同的单据
	 * @throws Exception 
	 * 
	 */
	private void genRelevantOrder(String STOREIN_DIFF_ID,UserBean userBean){
		List storeDiffList = queryDiffList(STOREIN_DIFF_ID);
		HashMap diffCovnMap = new HashMap();
		for(int i=0;i<storeDiffList.size();i++){
			HashMap storeDiffMap = (HashMap)storeDiffList.get(i);
			String diffRson = (String)storeDiffMap.get("DIFF_RSON");
			String dealWay = (String)storeDiffMap.get("DEAL_WAY");
			if("多发货".equals(diffRson)){
				if("视同销售".equals(dealWay)){
					setDiffConvMap(MANY_SALE_VAR,diffCovnMap,storeDiffMap);
				}else if("退货".equals(dealWay)){
					setDiffConvMap(MANY_RETURN_VAR,diffCovnMap,storeDiffMap);
				}
			}else if("少发货".equals(diffRson)){
				if("补单".equals(dealWay)){
					setDiffConvMap(FEW_SALE_VAR,diffCovnMap,storeDiffMap);
					setDiffConvMap(FEW_RETURN_VAR,diffCovnMap,storeDiffMap);
				}else if("退货".equals(dealWay)){
					setDiffConvMap(FEW_RETURN_VAR,diffCovnMap,storeDiffMap);
				}
			}else if("运输破损".equals(diffRson)){
				if("退货".equals(dealWay)){
					setDiffConvMap(TRAFFIC_RETURN_VAR,diffCovnMap,storeDiffMap);
				}else if("换货".equals(dealWay)){
					setDiffConvMap(FEW_SALE_VAR,diffCovnMap,storeDiffMap);
					setDiffConvMap(TRAFFIC_RETURN_VAR,diffCovnMap,storeDiffMap);
				}
			}
		}
		handDiffOrder(diffCovnMap,userBean);
	}
	
	private void setDiffConvMap(String fldVar,HashMap covnMap,HashMap storeDiffMap){
		if(covnMap.containsKey(fldVar)){
			ArrayList saleList = (ArrayList)covnMap.get(fldVar);
			saleList.add(storeDiffMap);
			covnMap.put(fldVar, saleList);
		}else{
			ArrayList tempList = new ArrayList();
			tempList.add(storeDiffMap);
			covnMap.put(fldVar, tempList);
		}
	}
	
	/**跟据差异处理方式，生成不同的销售订单和退货单
	 * @param diffCovnMap
	 * @param userBean
	 */
	private void handDiffOrder(HashMap diffCovnMap,UserBean userBean){
		Iterator iter = diffCovnMap.entrySet().iterator(); 
		while(iter.hasNext()){ 
			Map.Entry entry = (Map.Entry) iter.next();
			String fldVar = (String)entry.getKey();
			ArrayList diffList = (ArrayList)entry.getValue();
			if(MANY_SALE_VAR.equals(fldVar)){
				genSaleOrder(diffList,"已发货未付款",userBean);
			}else if(MANY_RETURN_VAR.equals(fldVar)){
				genReturnOrder(diffList,"多发退货",BusinessConsts.UNCOMMIT,userBean);
			}else if(FEW_SALE_VAR.equals(fldVar)){
				genSaleOrder(diffList,"已付款未发货",userBean);
			}else if(FEW_RETURN_VAR.equals(fldVar)){
				genReturnOrder(diffList,"少发退货","已鉴定",userBean);
			}else if(TRAFFIC_RETURN_VAR.equals(fldVar)){
				genReturnOrder(diffList,"运损退货",BusinessConsts.UNCOMMIT,userBean);
			}
		}
	}
	
	
	
	/**生成退货单
	 * @param STOREIN_DIFF_ID
	 */
	private void genReturnOrder(List storeDiffList,String billType,String stats,UserBean userBean){
		HashMap<String,String> storeDiffMap = (HashMap)storeDiffList.get(0);
		Map<String,String> params = new HashMap<String,String>();
		String prdReturnId = StringUtil.uuid32len();
		String prdReturnNo = LogicUtil.getBmgz("ERP_PRD_RETURN_NO");
		params.put("PRD_RETURN_ID",prdReturnId);
		params.put("PRD_RETURN_NO",prdReturnNo);
		params.put("BILL_TYPE",billType);
		params.put("FROM_BILL_ID",storeDiffMap.get("STOREIN_DIFF_ID"));
		params.put("FROM_BILL_NO",storeDiffMap.get("STOREIN_DIFF_NO"));
		params.put("RETURN_CHANN_ID",storeDiffMap.get("ORDER_CHANN_ID"));
		params.put("RETURN_CHANN_NO",storeDiffMap.get("ORDER_CHANN_NO"));
		params.put("RETURN_CHANN_NAME",storeDiffMap.get("ORDER_CHANN_NAME"));	
		params.put("RECV_CHANN_ID",storeDiffMap.get("RECV_CHANN_ID"));
		params.put("RECV_CHANN_NO",storeDiffMap.get("RECV_CHANN_NO"));
		params.put("RECV_CHANN_NAME",storeDiffMap.get("RECV_CHANN_NAME"));
		params.put("STATE",stats);
		params.put("REMARK",storeDiffMap.get("REMARK"));
		 params.put("CRE_NAME",userBean.getXM());//制单人名称
		 params.put("CREATOR",userBean.getXTYHID());//制单人ID
		 params.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		 params.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		 params.put("ORG_ID",userBean.getJGXXID());//制单机构ID
	 	 params.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		 params.put("CRE_TIME","sysdate");//制单时间
		 params.put("LEDGER_ID",userBean.getBASE_CHANN_ID());//帐套ID
		 params.put("LEDGER_NAME",userBean.getBASE_CHANN_NAME());//帐套名称
		 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
		PrdreturnServiceImpl pdtTurnService = (PrdreturnServiceImpl) SpringContextUtil.getBean("prdreturnService");
		pdtTurnService.insert("Prdreturn.insert", params);
		instReturnOrderDtl(prdReturnId,storeDiffList,pdtTurnService);
	}
	
	private void instReturnOrderDtl(String prdReturnId,List childList,PrdreturnServiceImpl pdtTurnService){
		ArrayList returnOrderDtlList = new ArrayList();
		for(int i=0;i<childList.size();i++){
			HashMap<String,Object> storediffMap = (HashMap)childList.get(i);
			 Map<String,Object> params = new HashMap<String,Object>();
			 String prdReturnDtlId = StringUtil.uuid32len();
			 params.put("PRD_RETURN_DTL_ID",prdReturnDtlId);
			 params.put("PRD_RETURN_ID",prdReturnId);
			 params.put("FROM_BILL_DTL_ID",storediffMap.get("STOREIN_DIFF_DTL_ID"));
			 params.put("PRD_ID",storediffMap.get("PRD_ID"));
			 params.put("PRD_NO",storediffMap.get("PRD_NO"));
			 params.put("PRD_NAME",storediffMap.get("PRD_NAME"));
			 params.put("PRD_SPEC",storediffMap.get("PRD_SPEC"));
			 params.put("PRD_COLOR",storediffMap.get("PRD_COLOR"));
			 params.put("BRAND",storediffMap.get("BRAND"));
			 params.put("STD_UNIT",storediffMap.get("STD_UNIT"));
			 params.put("RETURN_PRICE",storediffMap.get("QD_PRICE"));
			 params.put("RETURN_NUM",storediffMap.get("QD_NUM"));//差异数量
			 params.put("RETURN_RSON", storediffMap.get("DIFF_RSON"));
			 params.put("RETURN_AMOUNT",storediffMap.get("QD_AMOUNT"));
			 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
			 params.put("GOODS_ORDER_NO",storediffMap.get("GOODS_ORDER_NO"));
			 params.put("SPCL_TECH_FLAG",storediffMap.get("SPCL_TECH_FLAG"));
			 params.put("SPCL_TECH_ID",storediffMap.get("SPCL_TECH_ID"));
			 returnOrderDtlList.add(params);
		}
		if(returnOrderDtlList.size()>0){
			pdtTurnService.batch4Update("Prdreturn.insertChld", returnOrderDtlList);
		}		
	}
	
	/**
	 * 生成销售补单
	 */
	private void genSaleOrder(List storeDiffList,String billType,UserBean userBean){
		HashMap<String,String> storeDiffMap = (HashMap)storeDiffList.get(0); 
		Map<String,String> params = new HashMap<String,String>();
		 String saleOrderId = StringUtil.uuid32len();
		 String orderChannId = storeDiffMap.get("ORDER_CHANN_ID");
		 ChannServiceImpl channService = (ChannServiceImpl) SpringContextUtil.getBean("channService");
		 params.put("CHANN_ID", orderChannId);
		 HashMap<String,String> channMap = (HashMap<String, String>) channService.load("chann.loadById", params);
		 params = new HashMap<String,String>();
		 params.put("SALE_ORDER_ID",saleOrderId);
		 params.put("SALE_ORDER_NO",LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
		 params.put("BILL_TYPE",billType);
		 params.put("FROM_BILL_ID",storeDiffMap.get("STOREIN_DIFF_ID"));
		 params.put("FROM_BILL_NO",storeDiffMap.get("STOREIN_DIFF_NO"));
		 params.put("ORDER_CHANN_ID",orderChannId);
		 params.put("ORDER_CHANN_NO",storeDiffMap.get("ORDER_CHANN_NO"));
		 params.put("ORDER_CHANN_NAME",storeDiffMap.get("ORDER_CHANN_NAME"));
		 params.put("SEND_CHANN_ID",storeDiffMap.get("SEND_CHANN_ID"));
		 params.put("SEND_CHANN_NO",storeDiffMap.get("SEND_CHANN_NO"));
		 params.put("SEND_CHANN_NAME",storeDiffMap.get("SEND_CHANN_NAME"));
		 params.put("RECV_CHANN_ID",storeDiffMap.get("RECV_CHANN_ID"));
		 params.put("RECV_CHANN_NO",storeDiffMap.get("RECV_CHANN_NO"));
		 params.put("RECV_CHANN_NAME",storeDiffMap.get("RECV_CHANN_NAME"));
		 if(null!=channMap){
			 params.put("PERSON_CON",channMap.get("PERSON_CON"));
			 params.put("TEL",channMap.get("TEL"));
			 params.put("RECV_ADDR",channMap.get("ADDRESS"));
			 params.put("SHIP_POINT_ID",channMap.get("SHIP_POINT_ID"));
			 params.put("SHIP_POINT_NAME",channMap.get("SHIP_POINT_NAME"));
		 }
		 params.put("STATE",BusinessConsts.PASS);
		 params.put("REMARK",storeDiffMap.get("REMARK"));
		 params.put("CRE_NAME",userBean.getXM());//制单人名称
		 params.put("CREATOR",userBean.getXTYHID());//制单人ID
		 params.put("DEPT_ID",userBean.getBMXXID());//制单部门ID
		 params.put("DEPT_NAME",userBean.getBMMC());//制单部门名称
		 params.put("ORG_ID",userBean.getJGXXID());//制单机构ID
	 	 params.put("ORG_NAME",userBean.getJGMC());//制单机构名称
		 params.put("CRE_TIME","sysdate");//制单时间
		 params.put("LEDGER_ID",userBean.getBASE_CHANN_ID());//帐套ID
		 params.put("LEDGER_NAME",userBean.getBASE_CHANN_NAME());//帐套名称
		 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//删除标记
		 GoodsorderServiceImpl goodsorderService = (GoodsorderServiceImpl) SpringContextUtil.getBean("goodsorderService");
		 goodsorderService.insert("Goodsorder.addSaleOrder", params);
		 instSaleOrderDtl(saleOrderId,storeDiffList,goodsorderService);
	}
	
	/**插销售补单明细
	 * @param saleOrderId
	 */
	private void instSaleOrderDtl(String saleOrderId,List childList,GoodsorderServiceImpl goodsorderService){
		ArrayList saleGoodsList = new ArrayList();
		for(int i=0;i<childList.size();i++){
			HashMap  storediffMap = (HashMap)childList.get(i);
			 Map<String,String> params = new HashMap<String,String>();
			 String saleOrderDtlId = StringUtil.uuid32len();
			 params.put("SALE_ORDER_DTL_ID",saleOrderDtlId);
			 params.put("SALE_ORDER_ID",saleOrderId);
			 params.put("PRD_ID",(String)storediffMap.get("PRD_ID"));
			 params.put("PRD_NO",(String)storediffMap.get("PRD_NO"));
			 params.put("PRD_NAME",(String)storediffMap.get("PRD_NAME"));
			 params.put("PRD_SPEC",(String)storediffMap.get("PRD_SPEC"));
			 params.put("PRD_COLOR",(String)storediffMap.get("PRD_COLOR"));
			 params.put("BRAND",(String)storediffMap.get("BRAND"));
			 params.put("STD_UNIT",(String)storediffMap.get("STD_UNIT"));
			 params.put("PRICE",storediffMap.get("PRICE").toString());
			 params.put("DECT_RATE",storediffMap.get("DECT_RATE").toString());
			 Object DECT_PRICE =storediffMap.get("QD_PRICE");
			 if(null==DECT_PRICE){
				 DECT_PRICE="0";
			 }
			 params.put("DECT_PRICE",DECT_PRICE.toString());
			 Object ORDER_NUM=storediffMap.get("QD_NUM");
			 if(null==ORDER_NUM){
				 ORDER_NUM="0";
			 }
			 params.put("ORDER_NUM",ORDER_NUM.toString());//差异数量
			 double  ORDER_AMOUNT=Double.parseDouble(DECT_PRICE.toString())*Double.parseDouble(ORDER_NUM.toString());
			 params.put("ORDER_AMOUNT",ORDER_AMOUNT+"");
			 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
			 params.put("GOODS_ORDER_NO",(String)storediffMap.get("GOODS_ORDER_NO"));
			 params.put("SPCL_TECH_FLAG",(String)storediffMap.get("SPCL_TECH_FLAG"));
			 params.put("SPCL_TECH_ID",(String)storediffMap.get("SPCL_TECH_ID"));
			 saleGoodsList.add(params);
		}
		if(saleGoodsList.size()>0){
			goodsorderService.batch4Update("Goodsorder.insertSaleOrderDtl", saleGoodsList);
		}
	}
	
    
    /**跟据差异单ID联合查询差异记录
     * @param STOREIN_DIFF_ID
     * @return
     */
    public List <StorediffdealModelChld> queryDiffList(String STOREIN_DIFF_ID) {
        Map<String,String> params = new HashMap<String,String>();
        params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        return this.findList("Storediff.queryStorediffList", params);
    }
	
	
	
	/**
	 * @详细
	 * @param param STOREIN_DIFF_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	@SuppressWarnings("unchecked")
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Storediff.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STOREIN_DIFF_ID, List<StorediffModelChld> chldList,UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for(int i=0;i<chldList.size();i++){
        	Map<String,String> model=(Map<String, String>) chldList.get(i);
            Map <String, String> params = new HashMap <String, String>();
            params.put("STOREIN_DIFF_ID",STOREIN_DIFF_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.get("STOREIN_DIFF_DTL_ID"))) {
                params.put("STOREIN_DIFF_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("STOREIN_DIFF_DTL_ID", model.get("STOREIN_DIFF_DTL_ID"));
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storediff.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storediff.insertChld", addList);
        }
        Map<String,String> upMap=new HashMap<String,String>();
		upMap.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        upMap.put("UPDATOR", userBean.getRYXXID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        update("Storediff.updateById",upMap);
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffModelChld> childQuery(String STOREIN_DIFF_ID) {
        Map params = new HashMap();
        params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Storediff.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STOREIN_DIFF_DTL_IDs the STOREIN_DIFF_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffModelChld> loadChilds(Map <String, String> params) {
       return findList("Storediff.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STOREIN_DIFF_DTL_IDs the STOREIN_DIFF_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteChild(String STOREIN_DIFF_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STOREIN_DIFF_DTL_IDS", STOREIN_DIFF_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storediff.deleteChldByIds", params);
    }
    
    /**
     * 差异处理
     */
    
    /**
     * * 根据主表Id查询子表记录
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffdealModelChld> dealQuery(String STOREIN_DIFF_ID,String STOREIN_DIFF_DTL_ID) {
        Map params = new HashMap();
        params.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		params.put("STOREIN_DIFF_DTL_ID",STOREIN_DIFF_DTL_ID);
		
        return this.findList("Storediff.dealqueryChldByFkId", params);
    }
    
    /**
     * * 根据子表Id批量加载子表信息
     * @param DIFF_DEAL_DTL_IDs the DIFF_DEAL_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <StorediffdealModelChld> loadDealChilds(Map <String, String> params) {
       return findList("Storediff.dealloadChldByIds",params);
    }
    
    /**
     * * 子表批量删除:软删除
     * 
     * @param DIFF_DEAL_DTL_IDs the DIFF_DEAL_DTL_IDs
     */
    @SuppressWarnings("unchecked")
	@Override
    public void txBatch4DeleteDealChild(String DIFF_DEAL_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("DIFF_DEAL_DTL_IDS", DIFF_DEAL_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Storediff.dealdeleteChldByIds", params);
    }
	 /**
     * * 明细数据编辑
     * 
     * @param STOREIN_DIFF_ID the STOREIN_DIFF_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txdealEdit(String STOREIN_DIFF_ID,String STOREIN_DIFF_DTL_ID, List<StorediffdealModelChld> chldList,UserBean userBean) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (StorediffdealModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("DECT_RATE",model.getDECT_RATE());
		    params.put("STD_UNIT",model.getSTD_UNIT());
		    params.put("DECT_PRICE",model.getDECT_PRICE());
		    params.put("PRICE",model.getPRICE());
		    params.put("REMARK",model.getREMARK());
		    params.put("PRD_COLOR",model.getPRD_COLOR());
		    params.put("PRD_SPEC",model.getPRD_SPEC());
		    params.put("DIFF_NUM",model.getDIFF_NUM());
		    params.put("DIFF_RSON",model.getDIFF_RSON());
		    params.put("BRAND",model.getBRAND());
		    params.put("DEAL_WAY",model.getDEAL_WAY());
		    params.put("PRD_NAME",model.getPRD_NAME());
		    params.put("DIFF_AMOUNT",model.getDIFF_AMOUNT());
		    params.put("DIFF_ATT",model.getDIFF_ATT());
		    params.put("PRD_NO",model.getPRD_NO());
		    params.put("PRD_ID",model.getPRD_ID());
            params.put("STOREIN_DIFF_ID",STOREIN_DIFF_ID); 
            params.put("STOREIN_DIFF_DTL_ID",STOREIN_DIFF_DTL_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getDIFF_DEAL_DTL_ID())) {
                params.put("DIFF_DEAL_DTL_ID", StringUtil.uuid32len());
                params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("DIFF_DEAL_DTL_ID", model.getDIFF_DEAL_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Storediff.dealupdateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Storediff.dealinsertChld", addList);
        }
        Map<String,String> upMap=new HashMap<String,String>();
		upMap.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
        upMap.put("UPDATOR", userBean.getRYXXID());
        upMap.put("UPD_NAME", userBean.getXM());
        upMap.put("UPD_TIME", "数据库时间");
        update("Storediff.updateById",upMap);
        return true;
    }
	public int countDeal(String STOREIN_DIFF_ID) {
		Map<String,String> map=new HashMap<String,String>();
		map.put("STOREIN_DIFF_ID", STOREIN_DIFF_ID);
		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
		int count = Integer.parseInt(load("Storediff.countDeal",map).toString());
		return count;
	}
    
}