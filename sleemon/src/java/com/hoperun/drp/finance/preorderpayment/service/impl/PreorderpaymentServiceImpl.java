/**
 * prjName:喜临门营销平台
 * ucName:用户预付款
 * fileName:AdvpaymentServiceImpl
*/
package com.hoperun.drp.finance.preorderpayment.service.impl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import com.hoperun.commons.model.BusinessConsts;
import com.hoperun.commons.model.IListPage;
import com.hoperun.commons.service.BaseService;
import com.hoperun.commons.util.LogicUtil;
import com.hoperun.commons.util.StringUtil;
import com.hoperun.commons.exception.ServiceException;
import com.hoperun.sys.model.UserBean;
import com.hoperun.drp.finance.advpayment.service.AdvpaymentService;
import com.hoperun.drp.finance.preorderpayment.model.PreorderpaymentModel;
import com.hoperun.drp.finance.preorderpayment.model.PreorderpaymentModelChld;
import com.hoperun.drp.finance.preorderpayment.service.PreorderpaymentService;
/**
 * *@serviceImpl
 * *@func
 * *@version 1.1
 * *@author chenj
 * *@createdate 2013-10-20 18:57:47
 */
public class PreorderpaymentServiceImpl extends BaseService implements PreorderpaymentService {
    /**
	 * @查询
	 * @param params map
	 * @param pageNo 
	 *
	 * @return the ilistpage
	 */
	public IListPage pageQuery(Map params, int pageNo) {
		return this.pageQuery("Preorderpayment.pageQuery", "Preorderpayment.pageCount",params, pageNo);
	}
	
	/**
	 * * 增加
	 * * @param params 
	 * 
	 * @return true, if tx insert
	 */
	public boolean txInsert(Map<String,String> params) {
		insert("Preorderpayment.insert", params);
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
         update("Preorderpayment.delete", params);
		 //删除子表 
		 return update("Preorderpayment.delChldByFkId", params);
	}
	
	/**
	 * * update data
	 * * @param params
	 * 
	 * 
	 * @return true, if tx update by id
	 */
	public boolean txUpdateById(Map<String,String> params) {
		return update("Preorderpayment.updateById", params);
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
	public void txEdit(String STATEMENTS_ID, PreorderpaymentModel model,List<PreorderpaymentModelChld> chldList, UserBean userBean) {
		Map<String,String> params = new HashMap<String,String>();
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
		    params.put("PAYABLE_AMOUNT",model.getPAYABLE_AMOUNT());
		    params.put("STATEMENTS_AMOUNT",model.getSTATEMENTS_AMOUNT());
		    params.put("ADD_ADVC_AMOUNT",model.getADD_ADVC_AMOUNT());
		    params.put("BILL_TYPE",BusinessConsts.BILL_TYPE_PREPAY);
		    params.put("REMARK",model.getREMARK());
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
			    txChildEdit(STATEMENTS_ID, chldList);
		}
	    
	    
	    //删除原有的明细表
		this.delete("Preorderpayment.deleteDtl", params);
		//获取出货单明细表
		params.put("BILL_TYPE", BusinessConsts.STORE_OUT_TYPE);
		params.put("ADVC_ORDER_ID", model.getADVC_ORDER_ID());
		
		List<Map<String,String>>  outList =  this.findList("Preorderpayment.queryStoreoutDtl", params);
		
		if(outList!=null){
			for(Map<String,String> outParamsMap : outList){
				Map<String,Object> mentdtlMap = new HashMap<String,Object>();
				mentdtlMap.put("STATEMENTS_DTL_ID", StringUtil.uuid32len());
				mentdtlMap.put("STATEMENTS_ID", STATEMENTS_ID);
				mentdtlMap.put("PRD_ID", outParamsMap.get("PRD_ID"));
				mentdtlMap.put("PRD_NO", outParamsMap.get("PRD_NO"));
				mentdtlMap.put("PRD_NAME", outParamsMap.get("PRD_NAME"));
				mentdtlMap.put("PRD_SPEC", outParamsMap.get("PRD_SPEC"));
				mentdtlMap.put("PRD_COLOR", outParamsMap.get("PRD_COLOR"));
				mentdtlMap.put("BRAND", outParamsMap.get("BRAND"));
				mentdtlMap.put("STD_UNIT", outParamsMap.get("STD_UNIT"));
				mentdtlMap.put("PRICE", outParamsMap.get("PRICE"));
				mentdtlMap.put("DECT_RATE", outParamsMap.get("DECT_RATE"));
				mentdtlMap.put("DECT_PRICE", outParamsMap.get("DECT_PRICE"));
				mentdtlMap.put("SEND_NUM", outParamsMap.get("REAL_NUM"));
				mentdtlMap.put("PAYABLE_AMOUNT", outParamsMap.get("DECT_AMOUNT"));
				mentdtlMap.put("REMARK", outParamsMap.get("REMARK"));
				mentdtlMap.put("DEL_FLAG", outParamsMap.get("DEL_FLAG"));
				
				mentdtlMap.put("STOREOUTIN_ID", outParamsMap.get("STOREOUT_ID"));
				mentdtlMap.put("STOREOUTIN_NO", outParamsMap.get("STOREOUT_NO"));
				mentdtlMap.put("STATEMENTS_NUM", outParamsMap.get("REAL_NUM"));
				mentdtlMap.put("STATEMENTS_AMOUNT", outParamsMap.get("DECT_AMOUNT"));
				
				if(!StringUtil.isEmpty(outParamsMap.get("DEAL_TIME"))){
					mentdtlMap.put("STOREOUTIN_DATE", outParamsMap.get("DEAL_TIME"));
				}
				
				this.insert("Preorderpayment.insertDtl", mentdtlMap);
			}
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
		return (Map<String,String>) load("Preorderpayment.loadById", param);
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
    public boolean txChildEdit(String STATEMENTS_ID, List<PreorderpaymentModelChld> chldList) {

        //新增列表
        List <Map <String, String>> addList = new ArrayList <Map <String, String>>();
        //修改列表
        List <Map <String, String>> updateList = new ArrayList <Map <String, String>>();
        for (PreorderpaymentModelChld model : chldList) {
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
                addList.add(params);
            } else {
                params.put("STATEMENTS_PAYMENT_DTL_ID", model.getSTATEMENTS_PAYMENT_DTL_ID());
                updateList.add(params);
            }
        }

        if (!updateList.isEmpty()) {
            this.batch4Update("Preorderpayment.updateChldById", updateList);
        }
        if (!addList.isEmpty()) {
            this.batch4Update("Preorderpayment.insertChld", addList);
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
    public List <PreorderpaymentModelChld> childQuery(String STATEMENTS_ID) {
        Map params = new HashMap();
        params.put("STATEMENTS_ID", STATEMENTS_ID);
        //只查询0的记录。1为删除。0为正常
		params.put("DEL_FLAG",BusinessConsts.DEL_FLAG_COMMON);
        return this.findList("Preorderpayment.queryChldByFkId", params);
    }

    /**
     * * 根据子表Id批量加载子表信息
     * @param STATEMENTS_PAYMENT_DTL_IDs the STATEMENTS_PAYMENT_DTL_IDs 
     * 
     * @return the list< new master slavemx model>
     */
    @Override
    @SuppressWarnings("unchecked")
    public List <PreorderpaymentModelChld> loadChilds(Map <String, String> params) {
       return findList("Preorderpayment.loadChldByIds",params);
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
       update("Preorderpayment.deleteChldByIds", params);
    }
    
    @Override
	public boolean txSub(Map<String, String> params) {
		String STATEMENTS_ID = params.get("STATEMENTS_ID");
		double allPayAmount = 0;
		
		Map<String,String> payMap = this.load(STATEMENTS_ID);
		params.put("STATEMENTS_ID", STATEMENTS_ID);
		List<Map<String,Object>> childList = this.findList("Preorderpayment.myQueryChild", params);
		
		if(childList!=null){
			for(Map<String,Object> child : childList){
				allPayAmount = allPayAmount + ((BigDecimal)child.get("PAY_AMONT")).doubleValue();
			}
		}
		
		params.put("STATE", BusinessConsts.STATE_IS_PAY);
		params.put("STATEMENTS_AMOUNT", String.valueOf(allPayAmount));
		this.txUpdateById(params);
		
		//更新订单表信息
		Map<String,Object> opParam = new HashMap<String,Object>();
		opParam.put("ADVC_AMOUNT_FLAG",BusinessConsts.DEL_FLAG_DROP);
		opParam.put("ADVC_ORDER_ID", payMap.get("ADVC_ORDER_ID"));
		return update("Advcorder.updateById", opParam);
	}
    
    @Override
	public List<Map<String, String>> queryChild(Map<String, String> params) {
		return this.findList("Preorderpayment.queryStateDtl", params);
	}
}