package com.hoperun.erp.sale.marketing.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.marketing.model.MarketActivitiyModel;
import com.hoperun.erp.sale.marketing.model.MarketCardModel;
import com.hoperun.erp.sale.marketing.model.MarketChannModel;
import com.hoperun.erp.sale.marketing.service.MarketActivitiyService;
import com.hoperun.sys.model.UserBean;

public class MarketActivitiyServiceImpl extends BaseService implements MarketActivitiyService{

	public List<MarketChannModel> marketChannQuery(String MARKETING_ACT_ID) {
	    Map<String,String> params = new HashMap<String,String>();
        params.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("MarketActivity.queryMarketChannByFkId", params);
	}
	
	 /**
     * * 根据主表Id查询子表记录
     */
    public List <MarketCardModel> marketCardQuery(String MARKETING_ACT_ID){
    	  Map<String,String> params = new HashMap<String,String>();
          params.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
          //只查询0的记录。1为删除。0为正常
  		  params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
          return this.findList("MarketActivity.queryMarketCardByFkId", params);
    	
    }

	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("MarketActivity.insert", params);
		return true;
	}
	
	
	/**
	 * @删除
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("MarketActivity.delete", params);
		 //删除子表 
		 update("MarketActivity.delMarketCardByFkId", params);
		 //删除子表 
		 return update("MarketActivity.delMarketChannByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("MarketActivity.updateById", params);
	}
	
	@Override
	public Map<String, Object> load(String MARKETING_ACT_ID) {
		return (Map<String,Object>) load("MarketActivity.loadById", MARKETING_ACT_ID);
	}

	@Override
	public Map<String, Object> loadChann(String CHANN_ID) {
		return null;
	}

	@Override
	public List<MarketCardModel> loadMarketCardModels(Map<String, String> params) {
		return findList("MarketActivity.loadMarketCardByIds",params);
	}

	@Override
	public List<MarketChannModel> loadMarketChannModels(
			Map<String, String> params) {
		return findList("MarketActivity.loadMarketChannByIds",params);
	}

	@Override
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("MarketActivity.pageQuery", "MarketActivity.pageCount",params, pageNo);
	}
	
	/**
	 * 卡券子表 分页
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageCardQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("MarketActivity.pageCardQuery", "MarketActivity.pageCardCount",params, pageNo);
	}
	

	public void txBatch4DeleteMarketCard(Map<String, String> params) {
        update("MarketActivity.deleteMarketCardByIds", params);
	}

	public void txBatch4DeleteMarketChann(Map<String, String> params) {
        update("MarketActivity.deleteMarketChannByIds", params);
	}
	
	public String txEdit(String MARKETING_ACT_ID, MarketActivitiyModel model,
			List<MarketChannModel> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("MARKETING_ACT_NAME",model.getMARKETING_ACT_NAME());
		params.put("START_DATE",model.getSTART_DATE());
		params.put("END_DATE",model.getEND_DATE());
		params.put("SPONSOR_NAME",model.getSPONSOR_NAME());
		params.put("COMMISSION_PERCENTAGE",model.getCOMMISSION_PERCENTAGE());
		params.put("REMARK",model.getREMARK());
		 
		if(StringUtil.isEmpty(MARKETING_ACT_ID)){
			MARKETING_ACT_ID = StringUtil.uuid32len();
			params.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
			String prefix = "";//  单头
			int length = BusinessConsts.ADVC_ORDER_NO_LENGTH;//编号段长
			String MARKETING_ACT_NO = LogicUtil.getBillNo("MARKETING_ACT_NO","ERP_MARKETING_ACT",prefix,length,userBean);
			params.put("MARKETING_ACT_NO",MARKETING_ACT_NO);
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
			params.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
			txUpdateById(params);
			clearCaches(MARKETING_ACT_ID);
		}
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	txMarketChannEdit(MARKETING_ACT_ID, chldList);
		}
	    
		return null;
	}

	/**
	 * 卡券编辑
	 */
	public boolean txMarketCardEdit(String MARKETING_ACT_ID, MarketCardModel model,UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        int CARD_NUM = StringUtil.getInteger(model.getCARD_NUM());
        String LAST_BILL_NO = "";
        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("MARKETING_ACT_ID",MARKETING_ACT_ID);
        paramMap.put("CARD_TYPE",model.getCARD_TYPE());
        //查询最大的卡券序号
        List<Map<String,Object>> list = this.findList("MarketActivity.queryMaxBillNo", paramMap);
        int MAX_CARD_SEQ_NO = 0;
        if(null != list && !list.isEmpty()){
        	MAX_CARD_SEQ_NO = StringUtil.getInteger(list.get(0).get("CARD_SEQ_NO"));
        }
        
        for (int i=0;i<CARD_NUM;i++) {
        	Map<String,Object> params = new HashMap<String,Object>();
    		params.put("MARKETING_ACT_ID",MARKETING_ACT_ID);
    		params.put("CARD_TYPE",model.getCARD_TYPE());
    		params.put("CARD_VALUE",model.getCARD_VALUE());
    		params.put("CRE_TIME",model.getCRE_TIME());
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(model.getMARKETING_CARD_ID())) {
	        	MAX_CARD_SEQ_NO = MAX_CARD_SEQ_NO+1;//卡券序号递增
	        	params.put("CARD_SEQ_NO",MAX_CARD_SEQ_NO);
	        	params.put("STATE", "制定");
	        	params.put("MARKETING_CARD_ID", StringUtil.uuid32len());
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        	String prefix = "";//  单头
	    		int length = 6;//编号段长
	    		String MARKETING_CARD_NO = LogicUtil.getBillSequenceNo("MARKETING_CARD_NO","ERP_MARKETING_CARD",
	    				prefix,length,LAST_BILL_NO,null);
	    		LAST_BILL_NO = MARKETING_CARD_NO;
	    		params.put("MARKETING_CARD_NO",MARKETING_CARD_NO);
	            addList.add(params);
	        } else {
	        	params.put("MARKETING_CARD_ID", model.getMARKETING_CARD_ID());
	            updateList.add(params);
	        }
        
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("MarketActivity.updateMarketCardById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("MarketActivity.insertMarketCard", addList);
        }
        
        
		return true;
	}

	@Override
	public boolean txMarketChannEdit(String MARKETING_ACT_ID,
			List<MarketChannModel> modelList) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (MarketChannModel model : modelList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("MARKETING_ACT_ID",MARKETING_ACT_ID);
    		params.put("CHANN_ID",model.getCHANN_ID());
    		params.put("CHANN_NO",model.getCHANN_NO());
    		params.put("CHANN_NAME",model.getCHANN_NAME());
    		params.put("CHANN_TYPE",model.getCHANN_TYPE());
    		params.put("WAREA_ID",model.getWAREA_ID());
    		params.put("WAREA_NO",model.getWAREA_NO());
    		params.put("WAREA_NAME",model.getWAREA_NAME());
    		params.put("AREA_MANAGE_ID",model.getAREA_MANAGE_ID());
    		params.put("AREA_MANAGE_NAME",model.getAREA_MANAGE_NAME());
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(model.getMARKETING_CHANN_ID())) {
	        	params.put("MARKETING_CHANN_ID", StringUtil.uuid32len());
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("MARKETING_CHANN_ID", model.getMARKETING_CHANN_ID());
	            updateList.add(params);
	        }
        
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("MarketActivity.updateMarketChannById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("MarketActivity.insertMarketChann", addList);
        }
        
 
		return true;
	}
	
	  /**
     * 卡券开启
     * @param MARKETING_CARD_IDS
     * @param userBean
     */
	public void txMarketCardStart(String MARKETING_CARD_IDS, UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("MARKETING_CARD_IDS",MARKETING_CARD_IDS);
		params.put("STATE", BusinessConsts.JC_STATE_ENABLE);//启用
		this.update("MarketActivity.updateCardState", params);
		 
	}
	
	  /**
     * 卡券关闭
     * @param MARKETING_CARD_IDS
     * @param userBean
     */
	public void txMarketCardStop(String MARKETING_CARD_IDS, UserBean userBean){
		Map<String,String> params = new HashMap<String,String>();
		params.put("MARKETING_CARD_IDS",MARKETING_CARD_IDS);
		params.put("STATE","关闭");
		this.update("MarketActivity.updateCardState", params);
		 
	}
	
	/**
     * 查找已经参加的活动加盟商ID
     * @param MARKETING_ACT_ID
     * @return
     */
    public String queryChannIdsByFkId(String MARKETING_ACT_ID){
    	Map<String,String>paramMap = new HashMap<String,String>();
    	paramMap.put("MARKETING_ACT_ID", MARKETING_ACT_ID);
    	List<Map<String,String>> list = this.findList("MarketActivity.queryChannIdsByFkId", paramMap);
    	if(null != list && !list.isEmpty()){
    		return StringUtil.nullToSring(list.get(0).get("CHANN_IDS"));
    	}
    	return "";
    }
    
    
    /**
     * 导出excel
     * @param params
     * @return
     */
    public List exportExcel(Map<String,String> params){
    	return this.findList("MarketActivity.exportExcel", params);
    }
    

}
