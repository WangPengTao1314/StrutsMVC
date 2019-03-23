package com.hoperun.erp.sale.erpadvcorderstatements.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.erpadvcorderstatements.model.ErpAdvcorderStatementsChldModel;
import com.hoperun.erp.sale.erpadvcorderstatements.model.ErpAdvcorderStatementsModel;
import com.hoperun.erp.sale.erpadvcorderstatements.service.ErpAdvcorderStatementsService;
import com.hoperun.sys.model.UserBean;

public class ErpAdvcorderStatementsServiceImpl extends BaseService implements ErpAdvcorderStatementsService{
	
	/**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the i list page
	 */
	public IListPage pageQuery(Map<String,String> params, int pageNo){
		return this.pageQuery("Erpadvcorderstatements.pageQuery", "Erpadvcorderstatements.pageCount",params, pageNo);
	}
	
	 /**
	 * @主从保存编辑
	 * @Description :
	 * @param ADVC_STATEMENTS_ORDER_ID
	 * @param model
	 * @param userBean.
	 * @return 
	 */
	public void txEdit(String ADVC_STATEMENTS_ORDER_ID, ErpAdvcorderStatementsModel model,
			List<ErpAdvcorderStatementsChldModel> chldList,  UserBean userBean){
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("ADVC_STATEMENTS_ORDER_ID",model.getADVC_STATEMENTS_ORDER_ID());
		params.put("CHANN_ID",model.getCHANN_ID()); 
		params.put("CHANN_NO",model.getCHANN_NO());
		params.put("CHANN_NAME",model.getCHANN_NAME());
		params.put("STATEMENTS_DATE",model.getSTATEMENTS_DATE());
		params.put("STATEMENTS_AMOUNT",model.getSTATEMENTS_AMOUNT());
		params.put("ADVCS_AMOUNT",model.getADVCS_AMOUNT());
		params.put("BANK_RATE",model.getBANK_RATE());
		params.put("BANK_AMOUNT",model.getBANK_AMOUNT());
		params.put("COMMISSION_PERCENTAGE",model.getCOMMISSION_PERCENTAGE());
		params.put("COMMISSION_AMOUNT",model.getCOMMISSION_AMOUNT());
		params.put("GIFT_AMOUNT",model.getGIFT_AMOUNT());
		params.put("OTHER_AMOUNT",model.getOTHER_AMOUNT());
		params.put("LAST_STATEMENTS_AMOUNT",model.getLAST_STATEMENTS_AMOUNT());
		params.put("REMARK",model.getREMARK());
		params.put("MARKETING_ACT_ID",model.getMARKETING_ACT_ID());
		params.put("MARKETING_ACT_NO",model.getMARKETING_ACT_NO());
		params.put("MARKETING_ACT_NAME",model.getMARKETING_ACT_NAME());
		
		if(StringUtil.isEmpty(ADVC_STATEMENTS_ORDER_ID)){
			ADVC_STATEMENTS_ORDER_ID = StringUtil.uuid32len();
			params.put("ADVC_STATEMENTS_ORDER_ID", ADVC_STATEMENTS_ORDER_ID);
			params.put("ADVC_STATEMENTS_ORDER_NO",LogicUtil.getBmgz("ERP_ADVC_STATEMENTS_ORDER_NO"));
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
			params.put("ADVC_STATEMENTS_ORDER_ID", ADVC_STATEMENTS_ORDER_ID);
			txUpdateById(params);
			clearCaches(ADVC_STATEMENTS_ORDER_ID);
		}
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	txChildEdit(ADVC_STATEMENTS_ORDER_ID, chldList,userBean);
		}
	    
		
	}
	
	
	/**
	 * 子表保存
	 * @return
	 */
    public void txChildEdit(String ADVC_STATEMENTS_ORDER_ID, 
    		List<ErpAdvcorderStatementsChldModel> chldList,UserBean userBean){
    	
    	 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (ErpAdvcorderStatementsChldModel model : chldList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("ADVC_STATEMENTS_ORDER_ID",ADVC_STATEMENTS_ORDER_ID);
        	params.put("ADVC_ORDER_ID", model.getADVC_ORDER_ID());
        	params.put("ADVC_ORDER_NO", model.getADVC_ORDER_NO());
        	params.put("BILL_TYPE", model.getBILL_TYPE());
        	params.put("SALE_DATE", model.getSALE_DATE());
        	params.put("CUST_NAME", model.getCUST_NAME());
        	params.put("TEL", model.getTEL());
        	params.put("ADVC_AMOUNT", model.getADVC_AMOUNT());
        	params.put("CUR_STATEMENTS_AMOUNT", model.getCUR_STATEMENTS_AMOUNT());
        	params.put("PAYABLE_AMOUNT", model.getPAYABLE_AMOUNT());
        	params.put("MARKETING_CARD_ID", model.getMARKETING_CARD_ID());
        	params.put("MARKETING_CARD_NO", model.getMARKETING_CARD_NO());
        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        	String ADVC_STATEMENTS_ORDER_DTL_ID = model.getADVC_STATEMENTS_ORDER_DTL_ID();
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(ADVC_STATEMENTS_ORDER_DTL_ID)) {
	        	ADVC_STATEMENTS_ORDER_DTL_ID = StringUtil.uuid32len();
	        	params.put("ADVC_STATEMENTS_ORDER_DTL_ID", ADVC_STATEMENTS_ORDER_DTL_ID);
	            addList.add(params);
	        } else {
	        	params.put("ADVC_STATEMENTS_ORDER_DTL_ID", ADVC_STATEMENTS_ORDER_DTL_ID);
	            updateList.add(params);
	        }
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Erpadvcorderstatements.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Erpadvcorderstatements.insertChld", addList);
        }
        /**
         * 子表 编辑、删除的时候 更新主表的相关数据
         * @param ADVC_STATEMENTS_ORDER_ID
         */
       updateMainTable(ADVC_STATEMENTS_ORDER_ID);
        
    	
    }
    
    /**
     * 子表 编辑、删除的时候 更新主表的相关数据
     * @param ADVC_STATEMENTS_ORDER_ID
     */
    private void updateMainTable(String ADVC_STATEMENTS_ORDER_ID){
    	//更新主表的  //订金总额 = 明细预订单的订金金额的汇总
        //结算总额  = 明细预订单的应收金额的汇总
        //礼品费 = 明细选择的所有预订单的礼品金额的汇总
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("ADVC_STATEMENTS_ORDER_ID",ADVC_STATEMENTS_ORDER_ID);
        this.update("Erpadvcorderstatements.updateAmountById", paramMap);
        //银行扣点金额 = 银行扣点比例*订金总额 
        //导购员提成金额 = 导购员提成比例* 结算金额
        // 实际结算金额 = 结算金额-银行扣点金额-导购员提成金额-礼品费用-其它费用
        this.update("Erpadvcorderstatements.updateBankCommLastAmountById", paramMap);
    }
    
    
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Erpadvcorderstatements.insert", params);
		return true;
	}
	
	
	/**
	 * @删除
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Erpadvcorderstatements.delete", params);
		 //删除子表 
         return  update("Erpadvcorderstatements.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Erpadvcorderstatements.updateById", params);
	}
	
	/**
	 * @主表详细页面
	 * @param param  
	 * @return the map< string, string>
	 */
	public Map<String,Object> load(String ADVC_STATEMENTS_ORDER_ID){
		return (Map<String,Object>) load("Erpadvcorderstatements.loadById", ADVC_STATEMENTS_ORDER_ID);
	}
	
	public List <ErpAdvcorderStatementsChldModel> childQuery(String ADVC_STATEMENTS_ORDER_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("ADVC_STATEMENTS_ORDER_ID", ADVC_STATEMENTS_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Erpadvcorderstatements.queryChldByFkId", params);
	}
	
	public List <ErpAdvcorderStatementsChldModel> loadChilds(Map <String, String> params){
		return this.findList("Erpadvcorderstatements.loadChldByIds", params);
	}
	/**
	 * 查询付款方式
	 * @param params
	 * @return
	 */
	public List <Map<String,Object>> querySumPaymentDel(Map <String, String> params){
		return this.findList("Erpadvcorderstatements.querySumPaymentDel", params);
	}
	
    public void txBatch4DeleteChild(String ADVC_STATEMENTS_ORDER_ID,Map<String,String> params){
    	update("Erpadvcorderstatements.deleteChldByIds", params);
         //子表 编辑、删除的时候 更新主表的相关数据
        updateMainTable(ADVC_STATEMENTS_ORDER_ID);
    }
    
    /**
     * 查询已经选择的预订单ID
     * @param ADVC_STATEMENTS_ORDER_ID
     * @return
     */
    public String queryAdvcIds(String ADVC_STATEMENTS_ORDER_ID){
    	Map <String, String> params = new HashMap<String,String>();
    	params.put("ADVC_STATEMENTS_ORDER_ID", ADVC_STATEMENTS_ORDER_ID);
    	List<Map<String,Object>> list = this.findList("Erpadvcorderstatements.queryAdvcIds", params);
    	if(null == list || list.isEmpty()){
    		return "";
    	}
    	return StringUtil.nullToSring(list.get(0).get("ADVC_ORDER_IDS"));
    	
    }
    

}
