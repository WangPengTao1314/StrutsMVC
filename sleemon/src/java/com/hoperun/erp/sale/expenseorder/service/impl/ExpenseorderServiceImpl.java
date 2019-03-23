package com.hoperun.erp.sale.expenseorder.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.DateUtil;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.erp.sale.expenseorder.model.ExpenseorderChildModel;
import com.hoperun.erp.sale.expenseorder.model.ExpenseorderModel;
import com.hoperun.erp.sale.expenseorder.service.ExpenseorderService;
import com.hoperun.sys.model.UserBean;

public class ExpenseorderServiceImpl extends BaseService implements ExpenseorderService{
	
	
	public IListPage pageQuery(Map<String, String> params, int pageNo) {
		return this.pageQuery("Expenseorder.pageQuery", "Expenseorder.pageCount",params, pageNo);
	}
	
	/**
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pageQuerySH(Map<String,String> params, int pageNo){
		return this.pageQuery("Expenseorder.pageQuerySH", "Expenseorder.pageCountSH",params, pageNo);
	}
	
	public List<ExpenseorderChildModel> childQuery(String EXPENSE_ORDER_ID) {
	    Map<String,String> params = new HashMap<String,String>();
        params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Expenseorder.queryChldByFkId", params);
	}
	
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Expenseorder.insert", params);
		return true;
	}
	
	
	/**
	 * @删除
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("Expenseorder.delete", params);
		 //删除子表 
         return  update("Expenseorder.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Expenseorder.updateById", params);
	}
	
 
	public Map<String, Object> load(String EXPENSE_ORDER_ID) {
		return (Map<String,Object>) load("Expenseorder.loadById", EXPENSE_ORDER_ID);
	}
	/**
	 * 子表删除
	 */
	public void txBatch4DeleteChild(Map<String, String> params) {
        update("Expenseorder.deleteChldByIds", params);
        //子表编辑、删除的时候更新主表的报销金额    在子表删除动作之后
        updateMainExpAmount(params.get("EXPENSE_ORDER_ID"));
	}
	
	/**
	 * 编辑
	 */
	public String txEdit(String EXPENSE_ORDER_ID, ExpenseorderModel model,
			List<ExpenseorderChildModel> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("EXPENSE_TYPE",model.getEXPENSE_TYPE());
		params.put("BUDGET_ITEM_ID",model.getBUDGET_ITEM_ID());
		params.put("BUDGET_ITEM_NO",model.getBUDGET_ITEM_NO());
		params.put("BUDGET_ITEM_NAME",model.getBUDGET_ITEM_NAME());
		params.put("BUDGET_ITEM_TYPE",model.getBUDGET_ITEM_TYPE());
		params.put("MONTH",model.getMONTH());
		params.put("YEAR",model.getYEAR());
		params.put("QUARTER",model.getQUARTER());
		params.put("EXPENSE_PSON_ID",model.getEXPENSE_PSON_ID());
		params.put("EXPENSE_PSON_NAME",model.getEXPENSE_PSON_NAME());
		params.put("EXPENSE_DEPT_ID",model.getEXPENSE_DEPT_ID());
		params.put("EXPENSE_DEPT_NO",model.getEXPENSE_DEPT_NO());
		params.put("EXPENSE_DEPT_NAME",model.getEXPENSE_DEPT_NAME());
		params.put("EXPENSE_AMOUNT",model.getEXPENSE_AMOUNT());
		params.put("EXPENSE_DATE",model.getEXPENSE_DATE());
		params.put("RELATE_ORDER_NOS",model.getRELATE_ORDER_NOS());
		params.put("RELATE_AMOUNT_REQ",model.getRELATE_AMOUNT_REQ());
		params.put("EXPENSE_DATE",model.getEXPENSE_DATE());
		params.put("REMARK",model.getREMARK());
		params.put("BUDGET_QUOTA_ID",model.getBUDGET_QUOTA_ID());
		params.put("CHANN_ID", model.getCHANN_ID());
		params.put("CHANN_NO", model.getCHANN_NO());
		params.put("CHANN_NAME", model.getCHANN_NAME());
		
		 
		if(StringUtil.isEmpty(EXPENSE_ORDER_ID)){
			EXPENSE_ORDER_ID = StringUtil.uuid32len();
			params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
			params.put("EXPENSE_ORDER_NO",LogicUtil.getBmgz("ERP_EXPENSE_ORDER_NO"));
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
			params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
			txUpdateById(params);
			clearCaches(EXPENSE_ORDER_ID);
		}
		
		
		 //插入附件表
		String EXPENSE_ATT = model.getEXPENSE_ATT()+";"+model.getEXPENSE_ATT_PIC(); //附件
		
//		attParams.put("ATT_PATH",model.getPIC()
//				+";"+model.getZHUANGXIUSQ()
//				+";"+model.getYBXIANG()
//				+";"+model.getMCYSTAB()
//				+";"+model.getZGYANSHOU()
//				+";"+model.getZXPIC()
//				+";"+model.getZXFAPIAO()
//				+";"+model.getOLD_OA_ORDER_PIC());//图片路径
		
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("FROM_BILL_ID", EXPENSE_ORDER_ID);
		List<Map<String,String>> fileList = this.findList("Techorder.queryUploadFile", paramMap);
		if(null != fileList && !fileList.isEmpty()){
			updateFileUpload(EXPENSE_ORDER_ID, EXPENSE_ATT, userBean);
		}else{
			insertFileUpload(EXPENSE_ORDER_ID, EXPENSE_ATT, userBean);
		}
		
		
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
	    	txChildEdit(EXPENSE_ORDER_ID, chldList,userBean);
		}
	    
		return null;
	}

	/**
	 *  子表信息保存
	 */
	public boolean txChildEdit(String EXPENSE_ORDER_ID, List<ExpenseorderChildModel> chldList,UserBean userBean) {
		 //新增列表
        List <Map <String, Object>> addList = new ArrayList <Map <String, Object>>();
        //修改列表
        List <Map <String, Object>> updateList = new ArrayList <Map <String, Object>>();
        for (ExpenseorderChildModel child : chldList) {
        	Map<String,Object> params = new HashMap<String,Object>();
        	params.put("EXPENSE_ORDER_ID",EXPENSE_ORDER_ID);
        	params.put("EXPENSE_TYPE", child.getEXPENSE_TYPE());
        	params.put("EXPENSE_REMARK", child.getEXPENSE_REMARK());
        	params.put("BUSS_DATE", child.getBUSS_DATE());
        	params.put("EXPENSE_AMOUNT", child.getEXPENSE_AMOUNT());
        	params.put("OTHER_REMARK", child.getOTHER_REMARK());
        	String EXPENSE_ORDER_DTL_ID = child.getEXPENSE_ORDER_DTL_ID();
	        //如果没有明细ID的则是新增，有的是修改
	        if (StringUtil.isEmpty(EXPENSE_ORDER_DTL_ID)) {
	        	EXPENSE_ORDER_DTL_ID = StringUtil.uuid32len();
	        	params.put("EXPENSE_ORDER_DTL_ID", EXPENSE_ORDER_DTL_ID);
	        	params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	            addList.add(params);
	        } else {
	        	params.put("EXPENSE_ORDER_DTL_ID", child.getEXPENSE_ORDER_DTL_ID());
	            updateList.add(params);
	        }
	        
	        //插入附件表
    		String EXPENSE_ATT = child.getEXPENSE_ATT();//附件
    		Map<String,String> paramMap = new HashMap<String,String>();
    		paramMap.put("FROM_BILL_ID", EXPENSE_ORDER_DTL_ID);
    		List<Map<String,String>> fileList = this.findList("Techorder.queryUploadFile", paramMap);
    		if(null != fileList && !fileList.isEmpty()){
    			updateFileUpload(EXPENSE_ORDER_DTL_ID, EXPENSE_ATT, userBean);
    		}else{
    			insertFileUpload(EXPENSE_ORDER_DTL_ID, EXPENSE_ATT, userBean);
    		}
    		
        
        }
        
        if (!updateList.isEmpty()) {
            this.batch4Update("Expenseorder.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Expenseorder.insertChld", addList);
        }
        //子表编辑、删除的时候更新主表的报销金额
        updateMainExpAmount(EXPENSE_ORDER_ID);
		return true;
	}
 
	
	/**
	 * 子表编辑、删除的时候更新主表的报销金额
	 */
	private void updateMainExpAmount(String EXPENSE_ORDER_ID){
		//更新主表的报销金额
        update("Expenseorder.updateMainExpAmount",EXPENSE_ORDER_ID);
	}
 
	
	/**
     * 插入附件表 
     * @param FROM_BILL_ID 表ID
     * @param ATT_PATH 附件路径
     * @param userBean
     */
    public void insertFileUpload(String FROM_BILL_ID,String ATT_PATH,UserBean userBean){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("ATT_ID", StringUtil.uuid32len());
    	paramMap.put("FROM_BILL_ID", FROM_BILL_ID);
    	paramMap.put("ATT_PATH", ATT_PATH);
    	paramMap.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	paramMap.put("CREATOR", userBean.getXTYHID());//制单人ID
    	paramMap.put("CRE_NAME", userBean.getXM());//制单人名称
    	paramMap.put("CRE_TIME", DateUtil.now());//制单时间
    	this.insert("Techorder.insertFileUpload", paramMap);
    }
    
    /**
     * 更新附件信息
     * @param ATT_ID 附件ID
     * @param ATT_PATH 附件路径
     * @param userbean
     */
    public void updateFileUpload(String FROM_BILL_ID,String ATT_PATH,UserBean userBean){
    	Map<String, String> paramMap = new HashMap<String, String>();
    	paramMap.put("FROM_BILL_ID", FROM_BILL_ID);
    	paramMap.put("ATT_PATH", ATT_PATH);
    	this.insert("Techorder.updateFileUpload", paramMap);
    }
    
	public List<ExpenseorderChildModel> loadChilds(Map<String, String> params) {
		return this.findList("Expenseorder.loadChldByIds", params);
	}

	/**
	 * 查看推广费
	 * @param params
	 * @param pageNo
	 * @return
	 */
	public IListPage pagePromoexpenQuery(Map params, int pageNo) {
		return this.pageQuery("Promoexpen.pageQuery", "Promoexpen.pageCount",params, pageNo);
	}
	
	/**
	 * @param RELATE_ORDER_NOS
	 * @return
	 */
	public String queryRelateOrder(String RELATE_ORDER_NOS){
		
		int relateOrders = 0;
    	Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("RELATE_ORDER_NOS",RELATE_ORDER_NOS);
     	List<ExpenseorderModel> list = this.findList("Expenseorder.queryRelateOrder", paramMap);
     	if(null != list && !list.isEmpty()){
     		for(int i=0;i<list.size();i++){
     			ExpenseorderModel  expendseOrder = (ExpenseorderModel)list.get(i);
     			relateOrders += Integer.parseInt(expendseOrder.getEXPENSE_AMOUNT());
     		}
     		return String.valueOf(relateOrders);
     	}
     	return String.valueOf("0");
     	
     	
//    	Map<String,String> params = new HashMap<String,String>();
//		params.put("OPEN_TERMINAL_REQ_ID", OPEN_TERMINAL_REQ_ID);
//		return this.findList("openTerminal.queryTerminal",
//				params);
    }
	
	 public ExpenseorderModel expenseOrderQuery(String EXPENSE_ORDER_ID) {
	        Map params = new HashMap();
	        params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
	        return (ExpenseorderModel) this.load("Expenseorder.expenseOrderQuery", params);
	 }

	@Override
	public List downQuery(Map<String, String> map) {
		return this.findList("Expenseorder.downData", map);
	}
	
	/**
	 * @param map
	 * @return
	 */
	public List downQuerySH(Map<String,String> map){
		return this.findList("Expenseorder.downDataSH", map);
	}
	
	/**
	 * @param CHANN_ID
	 * @param YEAR
	 * @return
	 */
	public Map<String,Object> queryJudgeModelT(String CHANN_ID,String YEAR){
		Map<String,String> paramMap = new HashMap<String,String>();
    	paramMap.put("CHANN_ID", CHANN_ID);
    	paramMap.put("YEAR", YEAR);
     	List<Map<String,Object>> list = this.findList("Decorationreit.queryJudgeModelT", paramMap);
     	if(null != list && !list.isEmpty()){
     		return list.get(0);
     	}
     	return null;
	}
	
	/**
     * @param CHANN_ID
     * @param YEAR
     * @return
     */
    public String queryChannExpenseAmount(String CHANN_ID,String YEAR){
    	Map<String, String> params = new HashMap<String, String>();
		params.put("CHANN_ID", CHANN_ID);
		params.put("YEAR", YEAR);
		List<Map<String,Object>> list =  this.findList("Expenseorder.queryChannExpenseAmount", params);
		if(null != list && !list.isEmpty()){
        Map<String, Object> map1 = list.get(0);
        if(map1.get("EXPENSE_AMOUNT")!=null){
             return  String.valueOf(map1.get("EXPENSE_AMOUNT").toString());
        }
		}
        return null;
    }
    
    /**
	 * @param EXPENSE_ORDER_ID
	 * @return
	 */
	public List <ExpenseorderModel> childQueryT(String EXPENSE_ORDER_ID){
		Map<String,String> params = new HashMap<String,String>();
        params.put("EXPENSE_ORDER_ID", EXPENSE_ORDER_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Expenseorder.queryById", params);
	}
}
