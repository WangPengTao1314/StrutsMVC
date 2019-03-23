/**
 * prjName:喜临门营销平台
 * ucName:标准订单
 * fileName:SaleorderServiceImpl
*/
package com.hoperun.erp.sale.saleordersp.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModel;
import com.hoperun.erp.sale.saleordersp.model.SaleorderspModelChld;
import com.hoperun.erp.sale.saleordersp.service.SaleorderspService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author zzb
 * *@createdate 2013-10-12 13:52:19
 */
public class SaleorderspServiceImpl extends BaseService implements SaleorderspService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		//变颜色的日期
		params.put("BILL_DIFF_DATE_FLAG", BusinessConsts.BILL_DIFF_DATE_FLAG);
		return this.pageQuery("Saleordersp.pageQuery", "Saleordersp.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Saleordersp.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param SALE_ORDER_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Saleordersp.delete", params);
		 //删除子表 
		 return update("Saleordersp.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Saleordersp.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param SALE_ORDER_ID
	 * @param SaleorderModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String SALE_ORDER_ID, SaleorderspModel model,List<SaleorderspModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		if(StringUtil.isEmpty(SALE_ORDER_ID)){
			SALE_ORDER_ID= StringUtil.uuid32len();
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);
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
		    params.put("STATE",BusinessConsts.UNCOMMIT);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);
			txUpdateById(params);
			clearCaches(SALE_ORDER_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(SALE_ORDER_ID, chldList);
		}
	}
	
	/**
	 * @详细
	 * @param param SALE_ORDER_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("Saleordersp.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String SALE_ORDER_ID, List<SaleorderspModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (SaleorderspModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("SALE_ORDER_ID",SALE_ORDER_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSALE_ORDER_DTL_ID())) {
                params.put("SALE_ORDER_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
                addList.add(params);
            } else {
                params.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
                params.put("PRICE", model.getPRICE());
                params.put("DECT_PRICE", model.getDECT_PRICE());
                params.put("ORDER_AMOUNT", model.getORDER_AMOUNT());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Saleordersp.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Saleordersp.insertChld", addList);
        }
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SaleorderspModelChld> childQuery(String SALE_ORDER_ID) {
        Map params = new HashMap();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询 未取消预定的 0 正常
//		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
		
        return this.findList("Saleordersp.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SaleorderspModelChld> loadChilds(Map <String, String> params) {
       return findList("Saleordersp.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String SALE_ORDER_DTL_IDs) {
	   Map<String,String> params = new HashMap<String,String>();
	   params.put("SALE_ORDER_DTL_IDS", SALE_ORDER_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("Saleordersp.deleteChldByIds", params);
    }
    
    /**
     * 转标准订单
     * @param SALE_ORDER_ID_OLD 销售订单ID
     * @param SALE_ORDER_DTL_IDS 选择明细
     * @param isAll 是否全部明细 true->是
     */
    public void txConvertTechOrder(String SALE_ORDER_ID_OLD,String SALE_ORDER_DTL_IDS,boolean isAll,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		String SALE_ORDER_ID = StringUtil.uuid32len();
    	params.put("CRE_NAME",userBean.getXM());
	    params.put("CREATOR",userBean.getXTYHID());
	    params.put("CRE_TIME","sysdate");
	    params.put("DEPT_ID",userBean.getBMXXID());
	    params.put("DEPT_NAME",userBean.getBMMC());
	    params.put("ORG_ID",userBean.getJGXXID());
	    params.put("ORG_NAME",userBean.getJGMC());
	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
	    params.put("SALE_ORDER_NO",LogicUtil.getBmgz("ERP_SALE_ORDER_NO"));
		params.put("SALE_ORDER_ID", SALE_ORDER_ID);//新增订单
		params.put("SALE_ORDER_ID_OLD",SALE_ORDER_ID_OLD);
		params.put("BILL_TYPE", BusinessConsts.STANDARD);//标准
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_1);//1 是否非标
		params.put("STATE", BusinessConsts.PASS);//审核通过
		//转标准订单的时候 新增 订单
		insert("Saleordersp.insertBZOrder", params);
		
		//新增 明细
		params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_0);// 0 
		insert("Saleordersp.insertBZOrderDtl", params);
		
		//插入生命周期
		params.put("BILL_TYPE_", "标准销售订单");
		params.put("ACTION_NAME", "非标转标准订单");
		inertSaleordertrace(params,userBean);
		//删除 非标订单的明细
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);//1
		delete("Saleordersp.deleteChldByIds",params);
		
		//删除工艺单 明细
		this.delete("Saleordersp.deleteTechChilById", params);
		
		//如果是 全部的 明细 都转成 标准订单 那么则删除 相应的工艺单和相应的非标订单
		if(isAll){
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_ORDER_ID",SALE_ORDER_ID_OLD);//原始订单编号
			params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
		    //删除工艺单
		    delete("Saleordersp.deleteTechById", params);
			//删除 非标订单
			delete("Saleordersp.delete",params);
			
			//将新增的销售订单ID反填到订货订单明细里面
			params.put("SALE_ORDER_ID_OLD", SALE_ORDER_ID_OLD);//原始的销售订单ID
			params.put("SALE_ORDER_ID", SALE_ORDER_ID);//新生产的销售订单ID
			update("Saleorder.updateGoodsOrderDtl_SaleId",params);
			
		}
		
    }
    
    /**
     * 取消预定
     * @param SALE_ORDER_ID 销售订单id
     * @param SALE_ORDER_DTL_IDS 明细ids
     * @param FROM_BILL_DTL_IDS 来源单据ids
     */
    public void txCalcelSaleOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS,UserBean userBean)throws Exception{
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//1
    	params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);//1
    	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER1_CONF_ID,SALE_ORDER_ID, SALE_ORDER_DTL_IDS);
    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID);  
    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID);
    	this.update("Saleordersp.updateChldByIds", params);
    	
    	//如果该订单下 没有正常状态的明细 那么该订单状态更新为‘取消预订’
    	params.clear();
    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
    	params.put("DEL_FLAG",BusinessConsts.INTEGER_0);
    	int rst = this.queryForInt("Saleorder.queryChildForInt", params);
    	if(rst == 0){
    		params.put("STATE", "已取消");
    		params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
    		update("Saleorder.updateById",params);
    		
    		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_ORDER_ID",SALE_ORDER_ID);//原始订单编号
		    //删除工艺单
		   // delete("Saleordersp.deleteTechById", params);
		    
    	}
    	
    	//更新 订货订单明细
    	params.clear();
    	if(!StringUtil.isEmpty(FROM_BILL_DTL_IDS)){
    		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//取消标记 1
        	params.put("GOODS_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
        	update("Goodsorder.updateGoodDtlByIdS",params);
    	}
    	
    	
    }
    
    /**
     * 恢复预定
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_IDS  明细IDS
     * @param FROM_BILL_DTL_IDS 来源单据ids
     */
    public void txRecoverOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS,UserBean userBean )throws Exception{
    	String strSaleOrderDtlIds = "'"+SALE_ORDER_DTL_IDS.replaceAll(",", "','")+"'";
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
    	params.put("SALE_ORDER_DTL_IDS",strSaleOrderDtlIds);
    	this.update("Saleordersp.updateChldByIds", params);
    	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER_CONF_ID,SALE_ORDER_ID, strSaleOrderDtlIds);
    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER1_CONF_ID, SALE_ORDER_ID);
    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER1_CONF_ID, SALE_ORDER_ID);
    	
    	//如果单据已经‘取消预订’，又恢复预订的话，更新状态
    	params.clear();
    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
    	params.put("DEL_FLAG",BusinessConsts.INTEGER_0);
    	int rst = this.queryForInt("Saleorder.queryChildForInt", params);
    	if(0<rst){
    		params.put("STATE", "未提交");
    		params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
    		update("Saleorder.updateById",params);
    	}
    	
    	//更新 订货订单明细
    	String tempIDs = FROM_BILL_DTL_IDS.replaceAll(",", "");
    	if(!StringUtil.isEmpty(tempIDs)){
    		FROM_BILL_DTL_IDS = "'"+FROM_BILL_DTL_IDS.replaceAll(",", "','")+"'";
    		params.clear();
        	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
        	params.put("GOODS_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
        	update("Goodsorder.updateGoodDtlByIdS",params);
    	}
    	

    }
    
    /**
     * 查询 工艺单 状态
     * @param SALE_ORDER_ID 销售订单ID
     * @return
     */
    public Map<String,String> queryTechState(String SALE_ORDER_ID){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
    	return  (Map<String, String>) this.load("Saleordersp.queryStateAndCanPrd", params);
    	 
    }
    
    /**
     * 提交   审核通过
     * @param SALE_ORDER_ID 销售订单ID
     */
    public void txToCommit(String SALE_ORDER_ID,String SALE_ORDER_NO,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	params.put("STATE", BusinessConsts.PASS);
    	params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
	    params.put("AUDIT_ID", userBean.getXTYHID());
	    params.put("AUDIT_NAME", userBean.getXM());
	    params.put("AUDIT_TIME", BusinessConsts.UPDATE_TIME);
    	this.update("Saleordersp.updateById", params);
    }
    
    
    /**
	 * 插入销售订单生命周期
	 * @param parent 主表字段信息
	 * @param userBean
	 */
	public void inertSaleordertrace(Map<String,String> parent,UserBean userBean){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("GOODS_ORDER_TRACE_ID", StringUtil.uuid32len());
		paramMap.put("SALE_ORDER_ID", parent.get("SALE_ORDER_ID"));
		paramMap.put("BILL_NO", parent.get("SALE_ORDER_NO"));
		paramMap.put("DEAL_PSON_NAME", userBean.getXM());
		paramMap.put("DEAL_TIME", BusinessConsts.UPDATE_TIME);
		paramMap.put("STATE", parent.get("STATE"));// 
//		paramMap.put("TRACE_URL","");
		 
		paramMap.put("BILL_TYPE",  parent.get("BILL_TYPE_"));
		paramMap.put("ACTION_NAME", parent.get("ACTION_NAME"));
		
		this.insert("Saleorder.insertOrderTrack", paramMap);
	}
	
	
    /**
     * * 根据主表Id查询生命周期子表记录
     * @param SALE_ORDER_ID the SALE_ORDER_ID
     * @return the list
     */
    @SuppressWarnings("unchecked")
	public List <Map<String,String>> queryTrace(String SALE_ORDER_ID){
    	 Map<String,String> params = new HashMap<String,String>();
         params.put("SALE_ORDER_ID", SALE_ORDER_ID);
         return this.findList("Saleorder.queryTraceByFkId", params);
    }
    
    
    
}