package com.hoperun.erp.sale.marketcardsale.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleChldModel;
import com.hoperun.erp.sale.marketcardsale.model.MarketcardSaleModel;
import com.hoperun.erp.sale.marketcardsale.service.MarketcardSaleService;
import com.hoperun.sys.model.UserBean;

public class MarketcardSaleServiceImpl extends BaseService implements MarketcardSaleService{
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("MarketcardSale.pageQuery", "MarketcardSale.pageCount",params, pageNo);
	}
	
	 /**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_STATEMENTS_ORDER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String CARD_SALE_ORDER_ID, MarketcardSaleModel model,
			List<MarketcardSaleChldModel> chldList,  UserBean userBean){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("TERM_ID",model.getTERM_ID()); 
		params.put("TERM_NO",model.getTERM_NO());
		params.put("TERM_NAME",model.getTERM_NAME());
		params.put("CHANN_ID",model.getCHANN_ID()); 
		params.put("CHANN_NO",model.getCHANN_NO());
		params.put("CHANN_NAME",model.getCHANN_NAME());
		params.put("REMARK",model.getREMARK());
		params.put("MARKETING_ACT_ID",model.getMARKETING_ACT_ID());
		params.put("MARKETING_ACT_NO",model.getMARKETING_ACT_NO());
		params.put("MARKETING_ACT_NAME",model.getMARKETING_ACT_NAME());
		params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		params.put("SALE_DATE",model.getSALE_DATE());
		params.put("SALE_CARD_AMOUNT",model.getSALE_CARD_AMOUNT());
	 
		if(StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
			CARD_SALE_ORDER_ID = StringUtil.uuid32len();
			params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
			params.put("CARD_SALE_ORDER_NO",LogicUtil.getBmgz("ERP_CARD_SALE_ORDER_NO"));
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
			params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
			txUpdateById(params);
			clearCaches(CARD_SALE_ORDER_ID);
		}
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	txChildEdit(CARD_SALE_ORDER_ID, chldList,userBean);
		}
	    
		
	}
	
	
	/**
	 * 子表保存
	 * @return
	 */
    public void txChildEdit(String CARD_SALE_ORDER_ID, 
    		List<MarketcardSaleChldModel> chldList,UserBean userBean){
    	
    	 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (MarketcardSaleChldModel model : chldList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("CARD_SALE_ORDER_ID",CARD_SALE_ORDER_ID);
        	params.put("MARKETING_CARD_ID", model.getMARKETING_CARD_ID());
        	params.put("MARKETING_CARD_NO", model.getMARKETING_CARD_NO());
        	params.put("CARD_TYPE", model.getCARD_TYPE());
        	params.put("CARD_VALUE", model.getCARD_VALUE());
        	params.put("CRE_TIME", model.getCRE_TIME());
        	params.put("STATE", "正常");
        	params.put("CUST_NAME", model.getCUST_NAME());
        	params.put("MOBILE_PHONE", model.getMOBILE_PHONE());
        	params.put("SEX", model.getSEX());
        	params.put("BIRTHDAY", model.getBIRTHDAY());
        	params.put("HOBBY", model.getHOBBY());
        	params.put("ADDRESS", model.getADDRESS());
        	params.put("REMARK", model.getREMARK());
        	params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        	params.put("REGIST_STATE","未签到");
        	
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
	        
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("MarketcardSale.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("MarketcardSale.insertChld", addList);
        }
        //子表更新的时候 同步更新主表的 销售卡券总额
        update("MarketcardSale.updateMainTableAmount",CARD_SALE_ORDER_ID);
    }
    
    /**
     * 编辑会员信息表
     * @param CARD_SALE_ORDER_ID 卡券销售ID
     * @param userBean
     */
    public void txCreateMemberInfo(String CARD_SALE_ORDER_ID,UserBean userBean){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
    	paramMap.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        List<Map<String,String>> childList = this.findList("MarketcardSale.queryChldByFkId", paramMap);
        
        //新增列表
        List <Map <String, String>> addMemberList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateMemberList = new ArrayList <Map <String, String>>();
        
        for(Map<String,String> child : childList){
	        Map<String,String>params = new HashMap<String,String>();
	        params.put("MOBILE_PHONE", child.get("MOBILE_PHONE"));
	    	params.put("MEMBER_NAME",child.get("CUST_NAME"));
			params.put("MOBILE_PHONE",child.get("MOBILE_PHONE"));
			params.put("SEX",child.get("SEX"));
			params.put("POINT_VALUE",child.get("POINT_VALUE"));
			params.put("CHANN_ID",child.get("CHANN_ID"));
			params.put("CHANN_NO",child.get("CHANN_NO"));
			params.put("CHANN_NAME",child.get("CHANN_NAME"));
			params.put("BIRTHDAY",child.get("BIRTHDAY"));
			params.put("HOBBY",child.get("HOBBY"));
			params.put("ADDRESS",child.get("ADDRESS"));
			params.put("REMARK",child.get("REMARK"));
	        int rst = this.queryForInt("MarketcardSale.queryMemberByPhone", params);
	        if(0 == rst){
	        	String MEMBER_ID = StringUtil.uuid32len();
				String MEMBER_NO = LogicUtil.getBmgz("ERP_MEMBER_NO_NO");
				params.put("MEMBER_ID",MEMBER_ID);
				params.put("MEMBER_NO",MEMBER_NO);
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
			    params.put("STATE",BusinessConsts.JC_STATE_DEFAULT);//制定
			    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        	params.put("CHANN_ID",userBean.getCHANN_ID()); 
	    		params.put("CHANN_NO",userBean.getCHANN_NO());
	    		params.put("CHANN_NAME",userBean.getCHANN_NAME());
	    		addMemberList.add(params);
	        }else{
	        	updateMemberList.add(params);
	        }
        }
        
        if (!addMemberList.isEmpty()) {
            this.batch4Update("Member.insert", addMemberList);
        }
        //更新会员信息
        if (!updateMemberList.isEmpty()) {
            this.batch4Update("MarketcardSale.updateMemberByPhone", updateMemberList);
        }
//        Map<String,Object> model = this.load(CARD_SALE_ORDER_ID);
        Map<String,Object> params = new HashMap<String,Object>();
//        params.put("MARKETING_ACT_ID", model.get("MARKETING_ACT_ID"));
        params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
        //插入会员参加活动表
        this.insert("MarketcardSale.insertMemberAct", params);
        //插入会员卡券表
        this.insert("MarketcardSale.insertMemberCard", params);
        

    }
    
    /**
     * 同一活动下 同一个客户只能有一张卡券
     * @param CARD_SALE_ORDER_ID
     */
    public void queryCusNum(String CARD_SALE_ORDER_ID){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
    	paramMap.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
    	List<Map<String,String>> childList = this.findList("MarketcardSale.queryChldByFkId", paramMap);
    	for(Map<String,String> child : childList){
    		paramMap = new HashMap<String,String>();
    		paramMap.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
    		paramMap.put("MOBILE_PHONE", child.get("MOBILE_PHONE"));
    		paramMap.put("MARKETING_CARD_ID", child.get("MARKETING_CARD_ID"));
    		paramMap.put("CARD_SALE_ORDER_DTL_ID", child.get("CARD_SALE_ORDER_DTL_ID"));
    		List<Map<String,String>> list = this.findList("MarketcardSale.queryCusNum", paramMap);
        	if(null != list && !list.isEmpty()){
        		paramMap = list.get(0);
        		StringBuffer msg = new StringBuffer();
        		msg.append("活动[")
        		.append(paramMap.get("MARKETING_ACT_NAME"))
        		.append("]下，客户[")
        		.append(paramMap.get("CUST_NAME"))
        		.append("]手机号[")
        		.append(paramMap.get("MOBILE_PHONE"))
        		.append("],已经领券!券号["+paramMap.get("MARKETING_CARD_NO")+"]。");
        		throw new ServiceException(msg.toString()); 
        	}
    		 
    	}
    	 
    	
    }
	/** 
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("MarketcardSale.insert", params);
		return true;
	}
	
	
	/**
	 * @删除
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("MarketcardSale.delete", params);
         //删除会员卡券表的相关信息
         updateMemberCardBySate(params);
		 //删除子表 
         return  update("MarketcardSale.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("MarketcardSale.updateById", params);
	}
	
	/**
	 * @主表详细页面
	 * @param param  
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String CARD_SALE_ORDER_ID){
		return (Map<String,Object>) load("MarketcardSale.loadById", CARD_SALE_ORDER_ID);
	}
	
	public List <MarketcardSaleChldModel> childQuery(String CARD_SALE_ORDER_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("MarketcardSale.queryChldByFkId", params);
	}
	
	public List <MarketcardSaleChldModel> loadChilds(Map <String, String> params){
		return this.findList("MarketcardSale.loadChldByIds", params);
	}
	
    public void txBatch4DeleteChild(Map<String,String> params){
    	//删除会员卡券表的相关信息
        updateMemberCardBySate(params);
    	update("MarketcardSale.deleteChldByIds", params);
    }
    
    /**
     * 根据主表的状态是‘撤销’的删除，则删除会员卡券表的相关信息
     * @param CARD_SALE_ORDER_ID
     * @param CARD_SALE_ORDER_DTL_IDS
     */
    private void updateMemberCardBySate(Map<String,String> params){
    	String CARD_SALE_ORDER_ID = params.get("CARD_SALE_ORDER_ID");
    	String CARD_SALE_ORDER_DTL_IDS = params.get("CARD_SALE_ORDER_DTL_IDS");
    	Map<String,Object> entry = this.load(CARD_SALE_ORDER_ID);
		String STATE = StringUtil.nullToSring(entry.get("STATE"));
		if(BusinessConsts.REVOKE.equals(STATE)){//撤销
			params.clear();
			params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
			if(StringUtil.isEmpty(CARD_SALE_ORDER_DTL_IDS)){
				params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
			}else{
				params.put("CARD_SALE_ORDER_DTL_IDS", CARD_SALE_ORDER_DTL_IDS);
			}
			 //删除会员卡券信息表的相关数据
	         update("MarketcardSale.delMemberCardByCardId",params);
		}
    }
    /**
     * 根据手机号查询会员信息
     * @param MOBILE_PHONE
     * @return
     */
    public Map<String,String> getMemberInfo(String MOBILE_PHONE){
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("MOBILE_PHONE", MOBILE_PHONE);
    	List<Map<String,String>> list = this.findList("MarketcardSale.getMemberInfo", paramMap);
    	if(null == list || list.isEmpty()){
    		return null;
    	}else{
    		return list.get(0);
    	}
    }
    
    
    /**
     * 手机卡券销售
     * @param chldList 
     * @param userBean
     */
	public void txMobileCrdSaleEdit(String MARKETING_CARD_NO,List<MarketcardSaleChldModel> chldList, UserBean userBean){
		int cou = this.queryForInt("MarketcardSale.queryCradIsSale", MARKETING_CARD_NO);
		if(0 != cou){
			throw new ServiceException("该卡券已经销售");
		}
		Map<String,String> params = loadModelByUserId(MARKETING_CARD_NO,userBean);
		String CARD_SALE_ORDER_ID = "";
		if(null == params || params.isEmpty()){
		    params = new HashMap<String,String>();
		}else{
			CARD_SALE_ORDER_ID = StringUtil.nullToSring(params.get("CARD_SALE_ORDER_ID"));
		}
	    
		if(StringUtil.isEmpty(CARD_SALE_ORDER_ID)){
			CARD_SALE_ORDER_ID = StringUtil.uuid32len();
			params.put("MARKETING_CARD_NO", MARKETING_CARD_NO);
			params.put("CARD_SALE_ORDER_ID", CARD_SALE_ORDER_ID);
			params.put("CARD_SALE_ORDER_NO",LogicUtil.getBmgz("ERP_CARD_SALE_ORDER_NO"));
			params.put("SALE_DATE",DateUtil.formatDateToStr(new Date(),"yyyy-MM-dd"));
			params.put("TERM_ID",userBean.getTERM_ID()); 
			params.put("TERM_NO",userBean.getTERM_NO());
			params.put("TERM_NAME",userBean.getTERM_NAME());
			params.put("CHANN_ID",userBean.getCHANN_ID()); 
			params.put("CHANN_NO",userBean.getCHANN_NO());
			params.put("CHANN_NAME",userBean.getCHANN_NAME());
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
		    this.insert("MarketcardSale.insertMobileCardSale",  params);
		} 
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	txChildEdit(CARD_SALE_ORDER_ID, chldList,userBean);
		}
	}
    
	/**
	 * 根据当前登录人 查询最近的一次卡券销售
	 * @param userBean
	 * @return
	 */
	private Map<String,String> loadModelByUserId(String MARKETING_CARD_NO,UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("SALE_PSON_ID",userBean.getRYXXID());
		params.put("SALE_PSON_NAME",userBean.getXM());
		params.put("MARKETING_CARD_NO",MARKETING_CARD_NO);
		return (Map<String, String>) this.load("MarketcardSale.queryLastOrder", params);
	}
   
	
	/**
	 * 根据卡券编号 查询 已销售的卡券信息
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
     * 已销售未签到的卡券可以关闭
     * @param CARD_SALE_ORDER_ID 
     * @param CARD_SALE_ORDER_DTL_IDS
     * @param userBean
     */
	public void txChildClose(String CARD_SALE_ORDER_ID,String CARD_SALE_ORDER_DTL_IDS,UserBean userBean){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("CARD_SALE_ORDER_DTL_IDS", CARD_SALE_ORDER_DTL_IDS);
		List<Map<String,String>> list = this.findList("MarketcardSale.queryCardReged", paramMap);
		if(null != list && !list.isEmpty()){
			String CARD_NOS = StringUtil.nullToSring(list.get(0).get("CARD_NOS"));
			if(!StringUtil.isEmpty(CARD_NOS)){
				throw new ServiceException("卡券编号["+CARD_NOS+"]，已经签到不能关闭");
			}
		}
		this.update("MarketcardSale.updateCardNOReged", paramMap);
		
	}
	
	/**
	 * 开启
	 * @param CARD_SALE_ORDER_ID
	 * @param CARD_SALE_ORDER_DTL_IDS
	 * @param userBean
	 */
	public void txChildOpen(String CARD_SALE_ORDER_ID,String CARD_SALE_ORDER_DTL_IDS,UserBean userBean){
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("CARD_SALE_ORDER_DTL_IDS", CARD_SALE_ORDER_DTL_IDS);
		List<Map<String,String>> list = this.findList("MarketcardSale.queryClosed", paramMap);
		if(null != list && !list.isEmpty()){
			String CARD_NOS = StringUtil.nullToSring(list.get(0).get("CARD_NOS"));
			if(!StringUtil.isEmpty(CARD_NOS)){
				throw new ServiceException("卡券编号["+CARD_NOS+"]当前状态下不能开启");
			}
		}
		//已经关闭的记录中的卡券是否已经再次售卖
		list = this.findList("MarketcardSale.queryCardHaveSale", paramMap);
		if(null != list && !list.isEmpty()){
			String CARD_NOS = StringUtil.nullToSring(list.get(0).get("CARD_NOS"));
			if(!StringUtil.isEmpty(CARD_NOS)){
				throw new ServiceException("卡券["+CARD_NOS+"]对应的销售记录不能被开启，卡券已经售卖给他人");
			}
		}
		 
		this.update("MarketcardSale.updateCardOpen", paramMap);
		
	}
	
	
	 
}
