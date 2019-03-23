/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentServiceImpl
*/
package com.hoperun.drp.finance.custrefundments.service.impl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hoperun.commons.exception.ServiceException;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModel;
import com.hoperun.drp.finance.advpayment.model.AdvpaymentModelChld;
import com.hoperun.drp.finance.custrefundments.service.CustRefundmentService;
import com.hoperun.sys.model.UserBean;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public class CustRefundmentServiceImpl extends BaseService implements CustRefundmentService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("CustRefundment.pageQuery", "CustRefundment.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("CustRefundment.insert", params);
		return true;
	}
	/**
	 * @删除
	 * @param STATEMENTS_ID
	 * 
	 * @return true, if tx delete
	 */
	public boolean txDelete(Map <String, String> params) {
	     //删除父
         update("CustRefundment.delete", params);
		 //删除子表 
		 return update("CustRefundment.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("CustRefundment.updateById", params);
	}
	
	/**
	 * @编辑
	 * @Description :
	 * @param STATEMENTS_ID
	 * @param PreorderpaymentModel
	 * @param userBean.
	 *
	 * @return true, if tx txEdit 
	 */
	public void txEdit(String STATEMENTS_ID, AdvpaymentModel model,List<AdvpaymentModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
		boolean updateMastRecord = false;
		
		    params.put("ADVC_ORDER_ID",model.getADVC_ORDER_ID());
		    params.put("ADVC_ORDER_NO",model.getADVC_ORDER_NO());
		    params.put("TERM_ID",model.getTERM_ID());
		    params.put("TERM_NO",model.getTERM_NO());
		    params.put("TERM_NAME",model.getTERM_NAME());
		    params.put("SALE_DATE",model.getSALE_DATE());
		    params.put("SALE_PSON_ID",model.getSALE_PSON_ID());
		    params.put("SALE_PSON_NAME",model.getSALE_PSON_NAME());
		    params.put("CUST_NAME",model.getCUST_NAME());
		    params.put("TEL",model.getTEL());
		    params.put("ORDER_RECV_DATE",model.getORDER_RECV_DATE());
		    params.put("ADVC_AMOUNT",model.getADVC_AMOUNT());
		    params.put("PAYED_TOTAL_AMOUNT", model.getPAYED_TOTAL_AMOUNT());
		    params.put("DEDUCTED_TOTAL_AMOUNT", model.getDEDUCTED_TOTAL_AMOUNT());
		    params.put("STATENEBTS_ATT", model.getSTATENEBTS_ATT());
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
		    params.put("STATEMENTS_AMOUNT",model.getSTATEMENTS_AMOUNT());
		    params.put("STATENEBTS_ATT", model.getSTATENEBTS_ATT());
		    params.put("DEDUCT_AMOUNT", model.getDEDUCT_AMOUNT());
		    params.put("BILL_TYPE",model.getBILL_TYPE());
		    params.put("REMARK",model.getREMARK());
		    String CRE_TIME = model.getCRE_TIME();
		    if(StringUtil.isEmpty(model.getSTATEMENT_DATE())){
		    	if(StringUtil.isEmpty(CRE_TIME)){
		    		params.put("STATEMENT_DATE",StringUtil.getCurrentDate());//结算日期
		    	}else{
		    		params.put("STATEMENT_DATE",CRE_TIME.substring(0,10));//结算日期
		    	}
		    	 
		    }else{
		    	params.put("STATEMENT_DATE",model.getSTATEMENT_DATE());//结算日期
		    }
		if(StringUtil.isEmpty(STATEMENTS_ID)){
			STATEMENTS_ID= StringUtil.uuid32len();
			params.put("STATEMENTS_ID", STATEMENTS_ID);
			params.put("STATEMENTS_NO",LogicUtil.getBmgz("DRP_STATEMENTS_NO"));
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
			params.put("STATE",BusinessConsts.ADVPAYMENT_NOSUBMIT_STATE);
		    params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		    txInsert(params);
		} else{
		    params.put("UPD_NAME",userBean.getXM());
		    params.put("UPDATOR",userBean.getXTYHID());
		    params.put("UPD_TIME","sysdate");
			params.put("STATEMENTS_ID", STATEMENTS_ID);
			txUpdateById(params);
			clearCaches(STATEMENTS_ID);
		}
		//子表信息保存
	    if (null != chldList && !chldList.isEmpty()) {
			    txChildEdit(STATEMENTS_ID, chldList,updateMastRecord);
		}
	}
	
	/**
	 * @详细
	 * @param param STATEMENTS_ID
	 * @param param the param
	 * 
	 * @return the map< string, string>
	 */
	public Map<String,String> load(String param) {
		return (Map<String,String>) load("CustRefundment.loadById", param);
	}
	
		 /**
     * * 明细数据编辑
     * 
     * @param STATEMENTS_ID the STATEMENTS_ID
     * @param modelList the model list
     * 
     * @return true, if tx child edit
     */
    @Override
    public boolean txChildEdit(String STATEMENTS_ID, List<AdvpaymentModelChld> chldList,boolean updateMastRecord) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        
        double addPayAmont = 0;
        Map<String,Object> updateParams = new HashMap<String,Object>();
        
        for (AdvpaymentModelChld model : chldList) {
            Map <String, String> params = new HashMap <String, String>();
		    params.put("PAY_TYPE",model.getPAY_TYPE());
		    params.put("PAY_BILL_NO",model.getPAY_BILL_NO());
		    params.put("PAY_AMONT",model.getPAY_AMONT());
		    params.put("PAY_INFO",model.getPAY_INFO());
            params.put("STATEMENTS_ID",STATEMENTS_ID); 
            //如果没有明细ID的则是新增，有的是修改
            if (StringUtil.isEmpty(model.getSTATEMENTS_PAYMENT_DTL_ID())) {
                params.put("STATEMENTS_PAYMENT_DTL_ID", StringUtil.uuid32len());
		        params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
		        addPayAmont = addPayAmont + Double.parseDouble(model.getPAY_AMONT());//如果是添加，则只需要加上价格
                addList.add(params);
            } else {
                params.put("STATEMENTS_PAYMENT_DTL_ID", model.getSTATEMENTS_PAYMENT_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("CustRefundment.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("CustRefundment.insertChld", addList);
        }
        
        if(updateMastRecord){//如果需要反填主表的金额，则更新
        	upPayAmount(STATEMENTS_ID);
        }
        
        
        return true;
    }
	
	/**
     * * 根据主表Id查询子表记录
     * @param STATEMENTS_ID the STATEMENTS_ID
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvpaymentModelChld> childQuery(String STATEMENTS_ID) {
        Map params = new HashMap();
        params.put("STATEMENTS_ID", STATEMENTS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("CustRefundment.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STATEMENTS_PAYMENT_DTL_IDs the STATEMENTS_PAYMENT_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <AdvpaymentModelChld> loadChilds(Map <String, String> params) {
       return findList("CustRefundment.loadChldByIds",params);
    }
	
    /**
     * * 子表批量删除:软删除
     * 
     * @param STATEMENTS_PAYMENT_DTL_IDs the STATEMENTS_PAYMENT_DTL_IDs
     */
    @Override
    public void txBatch4DeleteChild(String STATEMENTS_PAYMENT_DTL_IDs) {
	   Map params = new HashMap();
	   params.put("STATEMENTS_PAYMENT_DTL_IDS", STATEMENTS_PAYMENT_DTL_IDs);
	   params.put("DEL_FLAG", BusinessConsts.DEL_FLAG_DROP);
       update("CustRefundment.deleteChldByIds", params);
       
       List<Map<String,Object>> list = this.findList("CustRefundment.selectChldByIds", params);
       double douPayment = 0d;
       String STATEMENTS_ID = "";
       
       if(list!=null){
    	   for(Map<String,Object> childMap : list){
    		   BigDecimal payment = (BigDecimal)childMap.get("PAY_AMONT");
    		   douPayment = douPayment + payment.doubleValue();
    		   STATEMENTS_ID = (String)childMap.get("STATEMENTS_ID");
    	   }
       }
       params.put("STATEMENTS_AMOUNT", douPayment);
       params.put("STATEMENTS_ID", STATEMENTS_ID);
       this.update("CustRefundment.minusPayamont", params);
    }
    
    @Override
	public boolean txSub(Map<String, String> params,UserBean userBean) {
		String STATEMENTS_ID = params.get("STATEMENTS_ID");
		this.txUpdateById(params);
		if(BusinessConsts.STATE_IS_PAY.equals(params.get("STATE"))){
			//获取收款单金额和收款单编号
			Map<String,String> map=(Map<String, String>) this.load("Advpayment.getStatementsInfo",STATEMENTS_ID);
			//新增预订单付款记录
			String remark="确认销售退款单";
			boolean flag =LogicUtil.instAdvcAmountNotes(userBean,  map.get("ADVC_ORDER_ID"), StringUtil.nullToSring(map.get("STATEMENTS_AMOUNT")), map.get("STATEMENTS_NO"), BusinessConsts.YJLBJ_FLAG_TRUE,remark );
			if(!flag){
				throw new ServiceException("确认失败！生成预订单记录失败");
			}
			//更新订单表信息
//			boolean advcFlag=updateAdvcAmount(STATEMENTS_ID, map.get("ADVC_ORDER_ID"));
//			if(!advcFlag){
//				throw new ServiceException("操作失败！更新预订单尾款失败");
//			}
		}
		return false;
	}
    //验证已付款是否超过应收总额
    public boolean checkRefund(Map<String,String> map){
    	map.put("BILL_TYPE", BusinessConsts.BILL_TYPE_RETURN_OTHER);
    	map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
    	map.put("STATE", BusinessConsts.STATE_IS_PAY);
    	String FLAG=this.load("CustRefundment.checkRefund", map).toString();
    	if("0".equals(FLAG)){
    		return false;
    	}
    	return true;
    }
    
    
    /**
	 * 撤销
	 * @param STATEMENTS_ID
	 * @param userBean
	 */
	public void txRevoke(String STATEMENTS_ID,UserBean userBean){
		  Map<String,String> params = new HashMap<String,String>();
		  params.put("STATEMENTS_ID", STATEMENTS_ID);
		  Map<String,String> model = this.load(STATEMENTS_ID);
		  int WRITE_OFF_FLAG = StringUtil.getInteger(model.get("WRITE_OFF_FLAG"));
		  if(1 == WRITE_OFF_FLAG){
			  throw new ServiceException("该单据已经开始核销不能撤销");
		  }
		  boolean isMonthAcc = LogicUtil.isMonthAcc(userBean.getLoginZTXXID(),model.get("STATEMENT_DATE"));
		  if(isMonthAcc){
			 throw new ServiceException(model.get("STATEMENT_DATE")+"日期已经月结，不能撤销");
		  }
		//获取预订单id
			String ADVC_ORDER_ID=this.load("Advpayment.getFromId",STATEMENTS_ID).toString();
			//获取收款单金额和收款单编号
			Map<String,String> map=(Map<String, String>) this.load("Advpayment.getStatementsInfo",STATEMENTS_ID);
		   params.put("UPD_NAME",userBean.getXM());
		   params.put("UPDATOR",userBean.getXTYHID());
		   params.put("UPD_TIME","sysdate");
		   params.put("STATE", "未提交");
		   params.put("STATEMENTS_ID", STATEMENTS_ID);
		   txUpdateById(params);
			//新增预订单付款记录
			String remark="撤销销售退款单";
			boolean flag =LogicUtil.instAdvcAmountNotes(userBean, ADVC_ORDER_ID, StringUtil.nullToSring(map.get("STATEMENTS_AMOUNT")), map.get("STATEMENTS_NO"), BusinessConsts.YJLBJ_FLAG_FALSE,remark );
			if(!flag){
				throw new ServiceException("撤销失败！生成预订单记录失败");
			}
		 //更新订单表信息
//			boolean advcFlag=updateAdvcAmount(STATEMENTS_ID,ADVC_ORDER_ID);
//			if(!advcFlag){
//				throw new ServiceException("操作失败！更新预订单尾款失败");
//			}
		  
	}
	//更新预订单信息
//	public boolean updateAdvcAmount(String STATEMENTS_ID,String ADVC_ORDER_ID){
//		Map<String,String> map=new HashMap<String, String>();
//		map.put("ADVC_ORDER_ID", ADVC_ORDER_ID);
//		map.put("BILL_TYPE", "'订金','正常收款','预付款','销售退款'");
//		map.put("STATE", "已结算");
//		map.put("DEL_FLAG", BusinessConsts.DEL_FLAG_COMMON);
//		return this.update("Advpayment.updateAdvcAmount", map);
//	}
	public String getIS_RETURN_SALE_AUD_FLAG(String CHANN_ID){
		return (String) this.load("CustRefundment.getIS_RETURN_SALE_AUD_FLAG",CHANN_ID);
	}
	public void txToReturn(String STATEMENTS_ID,UserBean userBean,String RETURN_RSON){
		Map<String,String> map=new HashMap<String, String>();
		map.put("STATEMENTS_ID", STATEMENTS_ID);
		map.put("STATE", BusinessConsts.UNCOMMIT);
		map.put("RETURNREMARK", RETURN_RSON);
		map.put("UPDATOR", userBean.getRYXXID());
		map.put("UPD_NAME", userBean.getXM());
		map.put("UPD_TIME", "sysdate");
		this.update("CustRefundment.updateById", map);
	}
	public boolean upPayAmount(String STATEMENTS_ID){
		return this.update("CustRefundment.upPayAmount", STATEMENTS_ID);
	}
}

