package com.hoperun.erp.sale.cusregist.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.cusregist.service.CusRegistService;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleChldModel;
import com.hoperun.sys.model.UserBean;

public class CusRegistServiceImpl extends BaseService implements CusRegistService{
	
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("Cusregist.pageQuery", "Cusregist.pageCount",params, pageNo);
	}
	
	/**
	 * 加载
	 * @param CARD_SALE_ORDER_DTL_ID
	 * @return
	 */
	public Map<String,Object> load(String CARD_SALE_ORDER_DTL_ID){
		return (Map<String, Object>) this.load("Cusregist.loadById", CARD_SALE_ORDER_DTL_ID);
	}
	
	/**
	 * 根据卡券编号查询卡券信息
	 * @param MARKETING_CARD_NO
	 * @return
	 */
	public Map<String,String> loadCard(String MARKETING_CARD_NO){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("MARKETING_CARD_NO", MARKETING_CARD_NO);
		return (Map<String, String>) this.load("Cusregist.loadByCardNo", paramMap);
	}
	
	/**
	 * 根据卡券编号查询   未销售的卡券信息 
	 * @param MARKETING_CARD_NO
	 * @return
	 */
	public Map<String,String> loadNoSaleCard(String MARKETING_CARD_NO){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("MARKETING_CARD_NO", MARKETING_CARD_NO);
		return (Map<String, String>) this.load("Cusregist.loadNoSaleCard", paramMap);
	}
 
	
	/**
	 * 编辑
	 * @param childModel 卡券信息
	 * @param userBean
	 */
	public void txEdit(MarketcardSaleChldModel childModel,UserBean userBean){
		//首先根据卡券编号去查询 看是否已经销售过
		Map<String,String> params = loadCard(childModel.getMARKETING_CARD_NO());
		//如果返回空值，证明该卡券没有销售过 要增加新的卡券销售主表
		//根据当前登录人查询是否有做过 卡券销售主表
		if(null == params || params.isEmpty()){
			params = loadModelByUserId(userBean);
		}
	 
		String CARD_SALE_ORDER_ID = "";
		if(null == params || params.isEmpty()){
		    params = new HashMap<String,String>();
		}else{
			CARD_SALE_ORDER_ID = StringUtil.nullToSring(params.get("CARD_SALE_ORDER_ID"));
		}
	    
		if(StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
			CARD_SALE_ORDER_ID = StringUtil.uuid32len();
			params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
			params.put("CARD_SALE_ORDER_NO",LogicUtil.getBmgz("ERP_CARD_SALE_ORDER_NO"));
			
			params.put("MARKETING_CARD_NO",childModel.getMARKETING_CARD_NO());
			params.put("SALE_DATE",childModel.getSALE_DATE());
			params.put("TERM_ID",childModel.getTERM_ID()); 
			params.put("TERM_NO",childModel.getTERM_NO());
			params.put("TERM_NAME",childModel.getTERM_NAME());
			params.put("CHANN_ID",childModel.getCHANN_ID()); 
			params.put("CHANN_NO",childModel.getCHANN_NO());
			params.put("CHANN_NAME",childModel.getCHANN_NAME());
			
			params.put("SALE_PSON_ID",userBean.getRYXXID());
			params.put("SALE_PSON_NAME",userBean.getXM());
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
		    txInsertCardSale(params);
		} 
		
		//子表信息保存
	    if (null != childModel) {
	    	txChildEdit(CARD_SALE_ORDER_ID, childModel,userBean);
		}
	}
	
	public void txInsertCardSale(Map<String,String> params) {
		insert("Cusregist.insertCardSale", params);
		 
	}
	
	/**
	 * 子表保存
	 * @return
	 */
    public void txChildEdit(String CARD_SALE_ORDER_ID,  MarketcardSaleChldModel model,UserBean userBean){
    	 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        
    	Map<String,Object> params = new HashMap<String,Object>();
    	params.put("CARD_SALE_ORDER_ID",CARD_SALE_ORDER_ID);
    	params.put("MARKETING_CARD_ID", model.getMARKETING_CARD_ID());
    	params.put("MARKETING_CARD_NO", model.getMARKETING_CARD_NO());
    	params.put("CARD_TYPE", model.getCARD_TYPE());
    	params.put("CARD_VALUE", model.getCARD_VALUE());
    	params.put("CRE_TIME", model.getCRE_TIME());
    	params.put("STATE", model.getSTATE());
    	params.put("CUST_NAME", model.getCUST_NAME());
    	params.put("MOBILE_PHONE", model.getMOBILE_PHONE());
    	params.put("SEX", model.getSEX());
    	params.put("BIRTHDAY", model.getBIRTHDAY());
    	params.put("HOBBY", model.getHOBBY());
    	params.put("ADDRESS", model.getADDRESS());
    	params.put("REMARK", model.getREMARK());
    	params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
    	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
    	params.put("REGIST_FLAG", "1");
    	params.put("REGIST_TIME", "1");
    	params.put("REGIST_STATE", "已签到");
    	String CARD_SALE_ORDER_DTL_ID = model.getCARD_SALE_ORDER_DTL_ID();
        //如果没有明细ID的则是新增，有的是修改
        if (StringUtil.isEmpty(CARD_SALE_ORDER_DTL_ID)) {
        	CARD_SALE_ORDER_DTL_ID = StringUtil.uuid32len();
        	params.put("CARD_SALE_ORDER_DTL_ID", CARD_SALE_ORDER_DTL_ID);
            addList.add(params);
        } else {
        	params.put("CARD_SALE_ORDER_DTL_ID", CARD_SALE_ORDER_DTL_ID);
            updateList.add(params);
        }
	        
        if (!updateList.isEmpty()) {
            this.batch4Update("Cusregist.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Cusregist.insertChld", addList);
        }
        //子表更新的时候 同步更新主表的 销售卡券总额
        update("MarketcardSale.updateMainTableAmount",CARD_SALE_ORDER_ID);
    }
	
    
	/**
	 * 根据当前登录人 查询最近的一次卡券销售
	 * @param userBean
	 * @return
	 */
	private Map<String,String> loadModelByUserId(UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("SALE_PSON_ID",userBean.getRYXXID());
		params.put("SALE_PSON_NAME",userBean.getXM());
		return (Map<String, String>) this.load("MarketcardSale.queryLastOrder", params);
	}
	
	/**
	 * 更新
	 * @param params
	 */
	public void txUpdateCardMx(Map<String,String>params ){
		this.update("Cusregist.updateChldById", params);
	}

}
