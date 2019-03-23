package com.hoperun.drp.finance.advcverify.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advcverify.model.WriteOffDtlModel;
import com.hoperun.drp.finance.advcverify.service.AdvcverifyService;
import com.hoperun.sys.model.UserBean;

public class AdvcverifyServiceImpl extends BaseService implements AdvcverifyService{
	
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Advcverify.pageQuery", "Advcverify.pageCount",params, pageNo);
	}
	
	/**
	 * 核销
	 * @param STATEMENTS_IDS 结算单IDS
	 * @param STATEMENTS_PAYMENT_DTL_IDS 结算付款明细IDS
	 */
	public void txCheckPayment(String STATEMENTS_IDS,
			String STATEMENTS_PAYMENT_DTL_IDS,List<WriteOffDtlModel> delModelList,UserBean userBean){
		 Map<String,String> params = new HashMap<String,String>();
		 params.put("WRITE_OFF_FLAG",BusinessConsts.INTEGER_1);//1
	     params.put("WRITE_OFF_PSON_ID",userBean.getXTYHID());
	     params.put("WRITE_OFF_PSON_NAME",userBean.getXM());
	     params.put("WRITE_OFF_PSON_TIME",BusinessConsts.UPDATE_TIME);
	     params.put("STATEMENTS_PAYMENT_DTL_IDS",STATEMENTS_PAYMENT_DTL_IDS);
	     update("Advcverify.updateChldById",params);
	     
	     txEditWriteoffDtl(delModelList,userBean);
	     
	     //更新预订单-付款单的核销标记WRITE_OFF_FLAG=1
	     String[] pIds = STATEMENTS_IDS.split(",");
	     HashSet<String> IdSet = new HashSet<String>();
	     for(int i=0;i<pIds.length;i++){
	    	 IdSet.add(pIds[i]);
	     }
	     if(IdSet.size()>0){
	    	 String ids = IdSet.toString().replace("[", "(").replace("]", ")");
		     params.clear();
	         params.put("UPD_NAME",userBean.getXM());
		     params.put("UPDATOR",userBean.getXTYHID());
		     params.put("UPD_TIME",BusinessConsts.UPDATE_TIME);
		     params.put("WRITE_OFF_FLAG",BusinessConsts.INTEGER_1);//1
		     params.put("STATEMENTS_IDS",ids);
	    	 update("Advcverify.updateByIds",params);
	     }
	     
	     
	     
	     
	     
	}
	
	/**
	 * 反核销
	 * @param STATEMENTS_ID 结算单ID
	 * @param STATEMENTS_PAYMENT_DTL_IDS 结算付款明细IDS
	 */
	public void txUnCheckPayment(String STATEMENTS_ID,String STATEMENTS_PAYMENT_DTL_IDS,UserBean userBean){
		 Map<String,String> params = new HashMap<String,String>();
		
		 params.put("WRITE_OFF_FLAG",BusinessConsts.INTEGER_0);//0
	     params.put("WRITE_OFF_PSON_ID",userBean.getXTYHID());
	     params.put("WRITE_OFF_PSON_NAME",userBean.getXM());
	     params.put("WRITE_OFF_PSON_TIME",BusinessConsts.UPDATE_TIME);
	     params.put("STATEMENTS_PAYMENT_DTL_IDS",STATEMENTS_PAYMENT_DTL_IDS);
	     
	     params.put("STATE",BusinessConsts.STATE_IS_PAY);//已结算
	     params.put("UPD_NAME",userBean.getXM());
	     params.put("UPDATOR",userBean.getXTYHID());
	     params.put("UPD_TIME",BusinessConsts.UPDATE_TIME);
	     params.put("STATEMENTS_ID",STATEMENTS_ID);
	     
	     update("Advcverify.updateChldById",params);
	     
	     //查明细已核销记录 如果记录数为0 表示全部的明细是反核销  则主表更新为已结算
//	     boolean flag = queryForInt(STATEMENTS_ID,BusinessConsts.INTEGER_1);
//	     if(flag){
	    	 update("Advcverify.updateById",params);
//	     }
	}
	
	
	/**
	 * 反核销
	 * @param STATEMENTS_ID  预订单_结算单
	 * @param STATEMENTS_PAYMENT_DTL_ID  预订单_结算付款明细
	 * @param WRITE_OFF_DTL_IDS 核销明细IDS
	 */
	public void txUnCheckPayment(String STATEMENTS_ID,String STATEMENTS_PAYMENT_DTL_ID,
			String WRITE_OFF_DTL_IDS,UserBean userBean){
		
		//删除核销明细表的记录
		 Map<String,String> params = new HashMap<String,String>();
		 WRITE_OFF_DTL_IDS = "'"+WRITE_OFF_DTL_IDS.replaceAll(",", "','")+"'";
	     params.put("WRITE_OFF_DTL_IDS",WRITE_OFF_DTL_IDS);	 
    	 params.put("WRITE_OFF_PSON_ID",userBean.getXTYHID());
		 params.put("WRITE_OFF_PSON_NAME",userBean.getXM());
		 params.put("WRITE_OFF_PSON_TIME",BusinessConsts.UPDATE_TIME);
		 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_DROP);//1
		 update("Advcverify.delWriteoffDtlById",params);
		 
		//查询核销明细表的记录没有的情况下 更新预订单_结算付款明细表的核销标记为0 
		 params.put("STATEMENTS_PAYMENT_DTL_ID", STATEMENTS_PAYMENT_DTL_ID);
		 params.put("DEL_FLAG","0");
		 int hexiaomx = this.queryForInt("Advcverify.queryHXChildsCount", params);
		 if(hexiaomx == 0){
			 params.put("WRITE_OFF_FLAG",BusinessConsts.INTEGER_0);//0
		     params.put("WRITE_OFF_PSON_ID",userBean.getXTYHID());
		     params.put("WRITE_OFF_PSON_NAME",userBean.getXM());
		     params.put("WRITE_OFF_PSON_TIME",BusinessConsts.UPDATE_TIME);
		     params.put("STATEMENTS_PAYMENT_DTL_IDS","'"+STATEMENTS_PAYMENT_DTL_ID+"'");
		     update("Advcverify.updateChldById",params);
		 }
		 
		 //预订单_结算单明细的核销标记全部为0时更新主表的核销标记为0
		 params.put("WRITE_OFF_FLAG","1");
		 params.put("STATEMENTS_ID", STATEMENTS_ID);
		 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
		 int hexiao = this.queryForInt("Advcverify.queryHXCount", params);
	     if(hexiao == 0){
	    	 params.clear();
	         params.put("UPD_NAME",userBean.getXM());
		     params.put("UPDATOR",userBean.getXTYHID());
		     params.put("UPD_TIME",BusinessConsts.UPDATE_TIME);
		     params.put("WRITE_OFF_FLAG",BusinessConsts.INTEGER_0);//0
		     params.put("STATEMENTS_IDS","'"+STATEMENTS_ID+"'");
	    	 update("Advcverify.updateByIds",params);
	     }
	}
	
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public boolean queryForInt(String STATEMENTS_ID,String flag) {
		 Map<String,String> params = new HashMap<String,String>();
		 params.put("STATEMENTS_ID",STATEMENTS_ID);
		 params.put("WRITE_OFF_FLAG",flag);//1
		 params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
         if(0 == queryForInt("Advcverify.countPay", params)) {
            return true;
         }else{
            return false;
         }
	  }
	
	/**
	 * 核销时插入核销明细表
	 * @param delModelList  核销明细表
	 * @param userBean
	 */
	
	public void txEditWriteoffDtl(List<WriteOffDtlModel> delModelList, 
			UserBean userBean){
		List<Map<String,String>> addList = new ArrayList<Map<String,String>>();
		for(WriteOffDtlModel delModel:delModelList){
			Map<String,String> params = new HashMap<String,String>();
			String WRITE_OFF_DTL_ID = StringUtil.uuid32len();
			params.put("STATEMENTS_PAYMENT_DTL_ID",delModel.getSTATEMENTS_PAYMENT_DTL_ID());
			params.put("WRITE_OFF_DTL_ID",WRITE_OFF_DTL_ID);
			params.put("PAY_TYPE",delModel.getPAY_TYPE());
			params.put("PAY_BILL_NO",delModel.getPAY_BILL_NO());
			params.put("WRITE_OFF_AMONT",delModel.getWRITE_OFF_AMONT());
			params.put("WRITE_OFF_PSON_ID",userBean.getXTYHID());
			params.put("WRITE_OFF_PSON_NAME",userBean.getXM());
			params.put("WRITE_OFF_PSON_TIME",BusinessConsts.UPDATE_TIME);
			params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
			addList.add(params);
		}
		 
		if(!addList.isEmpty()){
			this.batch4Update("Advcverify.insertWriteoffDtl", addList);
		}
	 
	}
	
	
	/**
	 * 查询已核销的明细
	 * @param STATEMENTS_PAYMENT_DTL_ID
	 * @return
	 */
	public List<Map<String,Object>> queryChild(String STATEMENTS_PAYMENT_DTL_ID){
		Map<String,String> params = new HashMap<String,String>();
		params.put("STATEMENTS_PAYMENT_DTL_ID",STATEMENTS_PAYMENT_DTL_ID);
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);//0
		return this.findList("Advcverify.queryChilds", params);
	}
	
	/**
	 * 检查主表的状态
	 * @param STATEMENTS_IDS
	 * @return
	 */
	public int checkState(String STATEMENTS_IDS,String STATES){
		Map<String,String> params = new HashMap<String,String>();
		params.put("STATEMENTS_IDS",STATEMENTS_IDS);
		params.put("STATES",STATES);
		return this.queryForInt("Advcverify.queryStatementSateCount", params);
	}

}
