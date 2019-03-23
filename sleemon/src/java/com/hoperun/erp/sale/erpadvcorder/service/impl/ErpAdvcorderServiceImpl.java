package com.hoperun.erp.sale.erpadvcorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcGiftModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderChldModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpAdvcorderModel;
import com.hoperun.erp.sale.erpadvcorder.model.ErpPaymentDtlModel;
import com.hoperun.erp.sale.erpadvcorder.service.ErpAdvcorderService;
import com.hoperun.sys.model.UserBean;

public class ErpAdvcorderServiceImpl extends BaseService implements ErpAdvcorderService{
	
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Erpadvcorder.pageQuery", "Erpadvcorder.pageCount",params, pageNo);
	}
	
	public List<ErpAdvcorderChldModel> childQuery(String ADVC_ORDER_ID) {
	    Map<String,String> params = new HashMap<String,String>();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Erpadvcorder.queryChldByFkId", params);
	}
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Erpadvcorder.insert", params);
		return true;
	}
	
	
	/**
	 * @删除
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Erpadvcorder.delete", params);
         //删除子表 付款明细
         update("Erpadvcorder.delPaymentByFkId", params);
         //删除子表  赠品
         update("Erpadvcorder.delGiftByFkId", params);
		 //删除子表 
         return  update("Erpadvcorder.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Erpadvcorder.updateById", params);
	}
	
 
	public Map<String, Object> load(String ADVC_ORDER_ID) {
		return (Map<String,Object>) load("Erpadvcorder.loadById", ADVC_ORDER_ID);
	}
	
	public void txBatch4DeleteChild(Map<String, String> params) {
        update("Erpadvcorder.deleteChldByIds", params);
        /**
    	 * 更新主表的应收总额\订金金额
    	 * @param ADVC_ORDER_ID
    	 */
    	updateMainAmoount(params.get("ADVC_ORDER_ID"));
	}
	
	
	/**
	 * 编辑
	 */
	public String txEdit(String ADVC_ORDER_ID, ErpAdvcorderModel model,
			List<ErpAdvcorderChldModel> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("GOODS_ORDER_ID",model.getGOODS_ORDER_ID());
		params.put("BILL_TYPE",model.getBILL_TYPE());
		params.put("SALE_DATE",model.getSALE_DATE());
		params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		params.put("CUST_NAME",model.getCUST_NAME());
		params.put("TEL",model.getTEL());
		params.put("RECV_ADDR",model.getRECV_ADDR());
		params.put("ORDER_RECV_DATE",model.getORDER_RECV_DATE());
		params.put("ADVC_AMOUNT",model.getADVC_AMOUNT());
		params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
		params.put("REMARK",model.getREMARK());
		params.put("MARKETING_ACT_ID",model.getMARKETING_ACT_ID());
		params.put("MARKETING_ACT_NO",model.getMARKETING_ACT_NO());
		params.put("MARKETING_ACT_NAME",model.getMARKETING_ACT_NAME());
		params.put("MARKETING_CARD_ID",model.getMARKETING_CARD_ID());
		params.put("MARKETING_CARD_NO",model.getMARKETING_CARD_NO());
		params.put("TERM_ID",model.getTERM_ID());
		params.put("TERM_NO",model.getTERM_NO());
		params.put("TERM_NAME",model.getTERM_NAME());
		
		if(StringUtil.isEmpty(ADVC_ORDER_ID)){
			ADVC_ORDER_ID = StringUtil.uuid32len();
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			params.put("ADVC_ORDER_NO",LogicUtil.getBmgz("ERP_ADVC_ORDER_NO"));
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
		    params.put("STATE",BusinessConsts.UNCOMMIT);//未提交
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    txInsert(params);
		    
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
			txUpdateById(params);
			clearCaches(ADVC_ORDER_ID);
		}
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	txChildEdit(ADVC_ORDER_ID, chldList,userBean);
		}
	    
		return null;
	}

	/**
	 *  子表信息保存
	 */
	public boolean txChildEdit(String ADVC_ORDER_ID, List<ErpAdvcorderChldModel> chldList,
			UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (ErpAdvcorderChldModel model : chldList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
        	params.put("PRD_ID", model.getPRD_ID());// 货品id
			params.put("PRD_NO", model.getPRD_NO());// 货品编号
			params.put("PRD_NAME", model.getPRD_NAME());// 货品名称
			params.put("PRD_TYPE", model.getPRD_TYPE());
			params.put("PRD_SPEC", model.getPRD_SPEC());// 规格型号
			params.put("PRD_COLOR", model.getPRD_COLOR());// 颜色
			params.put("BRAND", model.getBRAND());// 品牌
			params.put("STD_UNIT", model.getSTD_UNIT());// 标准单位
			params.put("PRICE", model.getPRICE());
			params.put("SPCL_TECH_ID", model.getSPCL_TECH_ID());
			params.put("DECT_RATE", model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());
			params.put("ORDER_NUM", model.getORDER_NUM());
			params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
			params.put("REMARK", model.getREMARK());
			params.put("ORDER_RECV_DATE", model.getORDER_RECV_DATE());
			params.put("STATE", model.getSTATE());
        	String ADVC_ORDER_DTL_ID = model.getADVC_ORDER_DTL_ID();
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(ADVC_ORDER_DTL_ID)) {
	        	ADVC_ORDER_DTL_ID = StringUtil.uuid32len();
	        	params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("ADVC_ORDER_DTL_ID", ADVC_ORDER_DTL_ID);
	            updateList.add(params);
	        }
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Erpadvcorder.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Erpadvcorder.insertChld", addList);
        }
        
        /**
    	 * 更新主表的应收总额\订金金额
    	 * @param ADVC_ORDER_ID
    	 */
    	updateMainAmoount(ADVC_ORDER_ID);
        
		return true;
	}

	@Override
	public List<ErpAdvcorderChldModel> loadChilds(Map<String, String> params) {
		return this.findList("Erpadvcorder.loadChldByIds", params);
	}

	/**
	 * 更新主表的应收总额\订金金额
	 * @param ADVC_ORDER_ID
	 */
	private void updateMainAmoount(String ADVC_ORDER_ID){
		//更新主表的应收总额
		update("Erpadvcorder.updateMainPayableAmoun",ADVC_ORDER_ID);
		//更新主表的订金金额
		//update("Erpadvcorder.updateMainAdvcAmoun",ADVC_ORDER_ID);
	}
	
	/**
	 * 付款明细编辑
	 */
	public void txPaymentEdit(String ADVC_ORDER_ID, List<ErpPaymentDtlModel> chldList,
			UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (ErpPaymentDtlModel model : chldList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
        	params.put("PAY_TYPE",model.getPAY_TYPE());
        	params.put("PAY_BILL_NO",model.getPAY_BILL_NO());
        	params.put("PAY_AMONT",model.getPAY_AMONT());
        	params.put("PAY_INFO",model.getPAY_INFO());
        	String PAYMENT_DTL_ID = model.getPAYMENT_DTL_ID();
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(PAYMENT_DTL_ID)) {
	        	PAYMENT_DTL_ID = StringUtil.uuid32len();
	        	params.put("PAYMENT_DTL_ID", PAYMENT_DTL_ID);
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("PAYMENT_DTL_ID", PAYMENT_DTL_ID);
	            updateList.add(params);
	        }
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Erpadvcorder.updatePaymentById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Erpadvcorder.insertPayment", addList);
        }
        
	}
	
	/**
	 * 根据主表ID查询付款明细
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public List<ErpPaymentDtlModel> paymentQuery(String ADVC_ORDER_ID) {
	    Map<String,String> params = new HashMap<String,String>();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Erpadvcorder.queryPaymentByFkId", params);
	}
	/**
	 * 付款明细
	 * @param params
	 * @return
	 */
	public List<ErpPaymentDtlModel> loadPayments(Map<String, String> params) {
		return this.findList("Erpadvcorder.loadPaymentByIds", params);
	}

	/**
	 * 删除付款明细
	 */
	public void txBatch4DeletePayment(Map<String, String> params) {
        update("Erpadvcorder.deletePaymentByIds", params);
	}
	
	
	/**
	 *   赠品信息编辑
	 */
	public void txGiftEdit(String ADVC_ORDER_ID, List<ErpAdvcGiftModel> chldList,
			UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (ErpAdvcGiftModel model : chldList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("ADVC_ORDER_ID",ADVC_ORDER_ID);
        	params.put("PRD_ID", model.getPRD_ID());// 货品id
			params.put("PRD_NO", model.getPRD_NO());// 货品编号
			params.put("PRD_NAME", model.getPRD_NAME());// 货品名称
			params.put("PRD_SPEC", model.getPRD_SPEC());// 规格型号
			params.put("BRAND", model.getBRAND());// 品牌
			params.put("STD_UNIT", model.getSTD_UNIT());// 标准单位
			params.put("PRICE", model.getPRICE());
			params.put("DECT_RATE", model.getDECT_RATE());
			params.put("DECT_PRICE", model.getDECT_PRICE());
			params.put("ORDER_NUM", model.getORDER_NUM());
			params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
			params.put("REMARK", model.getREMARK());
			params.put("STATE", model.getSTATE());
        	String ERP_ADVC_GIFT_DTL_ID = model.getERP_ADVC_GIFT_DTL_ID();
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(ERP_ADVC_GIFT_DTL_ID)) {
	        	ERP_ADVC_GIFT_DTL_ID = StringUtil.uuid32len();
	        	params.put("ERP_ADVC_GIFT_DTL_ID", ERP_ADVC_GIFT_DTL_ID);
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("ERP_ADVC_GIFT_DTL_ID", ERP_ADVC_GIFT_DTL_ID);
	            updateList.add(params);
	        }
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Erpadvcorder.updateGiftById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Erpadvcorder.insertGift", addList);
        }
        
	}
	
	
	
	/**
	 * 根据主表ID查询 赠品
	 * @param ADVC_ORDER_ID
	 * @return
	 */
	public List<ErpAdvcGiftModel> giftQuery(String ADVC_ORDER_ID) {
	    Map<String,String> params = new HashMap<String,String>();
        params.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Erpadvcorder.queryGiftByFkId", params);
	}
	/**
	 * 查询 赠品
	 * @param params
	 * @return
	 */
	public List<ErpAdvcGiftModel> loadGifts(Map<String, String> params) {
		return this.findList("Erpadvcorder.loadGiftByIds", params);
	}
	
	/**
	 * 删除 赠品明细
	 */
	public void txBatch4DeleteGift(Map<String, String> params) {
        update("Erpadvcorder.deleteGiftByIds", params);
	}
	
	/**
	 * 查询付款明细的和
	 * @return
	 */
	public double querySumPaymentDel(Map<String,String>paramMap){
		List<Map<String,Object>> list = this.findList("Erpadvcorder.querySumPaymentDel", paramMap);
		if(null == list || list.isEmpty()){
			return 0.0;
		}else{
			return StringUtil.getDouble(list.get(0).get("PAY_AMONT"));
		}
	}
}
