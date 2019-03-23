package com.hoperun.drp.sale.sersaleorder.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.sale.sersaleorder.model.SersaleorderModel;
import com.hoperun.drp.sale.sersaleorder.model.SersaleorderModelChld;
import com.hoperun.drp.sale.sersaleorder.service.SersaleorderService;
import com.hoperun.sys.model.UserBean;
/**
 * *@func 分销-区域服务中心-销售订单维护
 * *@version 1.1
 * *@author zzb
 * *@createdate 2014-5-23 13:52:19
 */
public class SersaleorderServiceImpl extends BaseService implements SersaleorderService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Sersaleorder.pageQuery", "Sersaleorder.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Sersaleorder.insert", params);
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
         update("Sersaleorder.delete", params);
		 //删除子表 
		 return update("Sersaleorder.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Sersaleorder.updateById", params);
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
	public void txEdit(String SALE_ORDER_ID, SersaleorderModel model,List<SersaleorderModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("REMARK",model.getREMARK());
		if(StringUtil.isEmpty(SALE_ORDER_ID)){
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
		return (Map<String,String>) load("Sersaleorder.loadById", param);
	}
	
    /**
     * * 明细数据编辑
     * 
     */
    @Override
    public boolean txChildEdit(String SALE_ORDER_ID, List<SersaleorderModelChld> chldList) {

        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (SersaleorderModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
            params.put("REMARK",model.getREMARK()); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSALE_ORDER_DTL_ID())) {
               
            } else {
                params.put("SALE_ORDER_DTL_ID", model.getSALE_ORDER_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Sersaleorder.updateChldById", updateList);
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
    public List <SersaleorderModelChld> childQuery(String SALE_ORDER_ID) {
        Map params = new HashMap();
        params.put("SALE_ORDER_ID", SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		//查询 未取消预定的 0 正常
		params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
		
        return this.findList("Sersaleorder.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param SALE_ORDER_DTL_IDs the SALE_ORDER_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <SersaleorderModelChld> loadChilds(Map <String, String> params) {
       return findList("Sersaleorder.loadChldByIds",params);
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
       update("Sersaleorder.deleteChldByIds", params);
    }
    
    /**
     * 转非标订单
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_IDS  明细IDS
     * @param isAll true->全部
     */
    public void txConvertTechOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_IDS,boolean isAll,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
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
		params.put("SALE_ORDER_ID", StringUtil.uuid32len());//新增 销售订单
		params.put("SALE_ORDER_ID_OLD",SALE_ORDER_ID);
		params.put("BILL_TYPE", BusinessConsts.UN_STANDARD);//非标
		
		params.put("IS_NO_STAND_FLAG",BusinessConsts.INTEGER_1);//1 是否非标
		params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
		//插入新的  销售订单
		insert("Sersaleorder.insertFBOrder", params);
		insert("Sersaleorder.insertFBOrderDtl", params);
		//删除 原始的 明细 单据
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
		delete("Sersaleorder.deleteChldByIds", params);
		
		//插入工艺单
		params.put("TECH_ORDER_ID",StringUtil.uuid32len());
		params.put("TECH_ORDER_NO",LogicUtil.getBmgz("ERP_TECH_ORDER_NO"));
		params.put("FROM_BILL_ID",params.get("SALE_ORDER_ID"));
		params.put("FROM_BILL_NO",params.get("FROM_BILL_NO"));
		params.put("STATE",BusinessConsts.COMMIT);//状态为 提交
		this.insert("Sersaleorder.insertTechOrder", params);
		//插入工艺单明细
		params.put("TECH_ORDER_DTL_ID",StringUtil.uuid32len());
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		params.put("IS_CAN_PRD_FLAG",BusinessConsts.INTEGER_0);//0
		
		this.insert("Sersaleorder.insertTechOrderDtl", params);
		
		if(isAll){
			//删除 原始的 标准订单
		  	params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);
			params.put("SALE_ORDER_ID",SALE_ORDER_ID);
			update("Sersaleorder.delete", params);
		}
		
    }
    

    /**
     * 取消预定
     * @param SALE_ORDER_DTL_ID 订单ID
     * @param SALE_ORDER_DTL_IDS 明细IDs
     * @param FROM_BILL_DTL_IDS 来源单据IDS
     */
    public void txCalcelSaleOrder(String SALE_ORDER_ID, String SALE_ORDER_DTL_IDS,String FROM_BILL_DTL_IDS,UserBean userBean)throws Exception{
    	Map<String,String> params = new HashMap<String,String>();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//1
    	params.put("SALE_ORDER_DTL_IDS",SALE_ORDER_DTL_IDS);
    	params.put("CREDIT_FREEZE_PRICE","0");
    	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER1_CONF_ID,SALE_ORDER_ID, SALE_ORDER_DTL_IDS);
    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID);
    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER_CONF_ID, SALE_ORDER_ID);
    	this.update("Sersaleorder.updateChldByIds", params);
    	
    	//如果该订单下 没有正常状态的明细 那么该订单状态更新为‘取消预订’
    	params.clear();
    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
    	params.put("DEL_FLAG",BusinessConsts.INTEGER_0);
    	int rst = this.queryForInt("Sersaleorder.queryChildForInt", params);
    	if(rst == 0){
    		params.put("STATE", "已取消");
    		params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
    		update("Sersaleorder.updateById",params);
    	}
    	//更新 订货订单明细
    	params.clear();
    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_1);//取消标记 1
    	params.put("GOODS_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
    	update("Goodsorder.updateGoodDtlByIdS",params);
    }
    
    /**
     * 恢复预定
     * @param SALE_ORDER_ID 销售订单ID
     * @param SALE_ORDER_DTL_ID  明细ID
     * @param FROM_BILL_DTL_IDS 来源单据ID
     */
    public void txRecoverOrder(String SALE_ORDER_ID,String SALE_ORDER_DTL_ID,String FROM_BILL_DTL_IDS ,UserBean userBean)throws Exception{
//    	String strSaleOrderDtlIds = "'"+SALE_ORDER_DTL_ID+"'";
//    	Map<String,String> dtlParam = new HashMap<String,String>();
//    	dtlParam.put("SALE_ORDER_DTL_IDS",strSaleOrderDtlIds);
//        List list = loadChilds(dtlParam);
//        Map dtlMap = (Map)list.get(0);
//        BigDecimal dectPrice = (BigDecimal)dtlMap.get("DECT_PRICE");
//        BigDecimal ratio = new BigDecimal("0.3");
//        double dfreeze =dectPrice.multiply(ratio).doubleValue();   	
//    	Map<String,String> params = new HashMap<String,String>();
//    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
//    	params.put("CREDIT_FREEZE_PRICE",String.valueOf(dfreeze));
//    	params.put("SALE_ORDER_DTL_IDS",strSaleOrderDtlIds);
//    	this.update("Sersaleorder.updateChldByIds", params);
//    	LogicUtil.updFreezeCredit(BusinessConsts.ACCT_SALE_ORDER_CONF_ID,SALE_ORDER_ID, strSaleOrderDtlIds);
//    	LogicUtil.updateRebate(BusinessConsts.FL_SALE_ORDER1_CONF_ID, SALE_ORDER_ID);
//    	LogicUtil.insRebateJournal(BusinessConsts.FL_SALE_ORDER1_CONF_ID, SALE_ORDER_ID);
//    	//如果单据已经‘取消预订’，又恢复预订的话，更新状态
//    	params.clear();
//    	params.put("SALE_ORDER_ID",SALE_ORDER_ID);
//    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);
//    	params.put("DEL_FLAG",BusinessConsts.INTEGER_0);
//    	int rst = this.queryForInt("Sersaleorder.queryChildForInt", params);
//    	if(0<rst){
//    		params.put("STATE", "未提交");
//    		params.put("UPD_NAME",userBean.getXM());
//		    params.put("UPDATOR",userBean.getXTYHID());
//		    params.put("UPD_TIME","sysdate");
//    		update("Sersaleorder.updateById",params);
//    	}
//    	
//    	//更新 订货订单明细
//    	params.clear();
//    	params.put("CANCEL_FLAG",BusinessConsts.INTEGER_0);//0
//    	params.put("GOODS_ORDER_DTL_IDS", FROM_BILL_DTL_IDS);
//    	update("Goodsorder.updateGoodDtlByIdS",params);
//    	
    }
    
    /**乖法运算
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
     * 提交   审核通过
     * @param SALE_ORDER_ID 销售订单ID
     */
    public void txCommit(String SALE_ORDER_ID,UserBean userBean){
    	Map<String,String> params = new HashMap<String,String>();
//    	params.put("SALE_ORDER_ID", SALE_ORDER_ID);
//    	String GOODS_ORDER_ID = StringUtil.uuid32len();
//		params.put("GOODS_ORDER_ID", GOODS_ORDER_ID);
//		params.put("GOODS_ORDER_NO", LogicUtil.getBmgz("DRP_GOODS_ORDER_NO"));
//    	params.put("BILL_TYPE", "备货订货");
//    	params.put("STATE", BusinessConsts.UNDONE);//未处理
//    	params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);//0
//    	params.put("CANCEL_FLAG", BusinessConsts.INTEGER_0);//0
//    	params.put("CRE_NAME",userBean.getXM());
//	    params.put("CREATOR",userBean.getXTYHID());
//	    params.put("CRE_TIME","sysdate");
//	    params.put("DEPT_ID",userBean.getBMXXID());
//	    params.put("DEPT_NAME",userBean.getBMMC());
//	    params.put("ORG_ID",userBean.getJGXXID());
//	    params.put("ORG_NAME",userBean.getJGMC());
//	    params.put("LEDGER_ID",userBean.getLoginZTXXID());
//	    params.put("LEDGER_NAME",userBean.getLoginZTMC());
//    	//生成 订货订单和订货订单明细
//    	this.insert("Sersaleorder.insertSergoodsorder", params);
//    	this.insert("Sersaleorder.insertSergoodsorderMX", params);
    	
    	//更新 销售订单的状态为‘提交’
    	params.clear();
    	params.put("SALE_ORDER_ID", SALE_ORDER_ID);
    	params.put("STATE", BusinessConsts.COMMIT);//提交
    	params.put("UPD_NAME",userBean.getXM());
	    params.put("UPDATOR",userBean.getXTYHID());
	    params.put("UPD_TIME","sysdate");
	    params.put("AUDIT_ID",userBean.getXTYHID());
	    params.put("AUDIT_NAME",userBean.getXM());
	    params.put("AUDIT_TIME","sysdate");
	    
    	this.update("Sersaleorder.updateById", params);
    }
}